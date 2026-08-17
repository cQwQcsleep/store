package org.jetbrains.kotlin.fir.pipeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.BackendException;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.backend.common.actualizer.IrActualizedResult;
import org.jetbrains.kotlin.backend.common.actualizer.IrActualizer;
import org.jetbrains.kotlin.backend.common.actualizer.IrExpectActualMap;
import org.jetbrains.kotlin.backend.common.actualizer.IrExtraActualDeclarationExtractor;
import org.jetbrains.kotlin.backend.common.actualizer.SpecialFakeOverrideSymbolsResolver;
import org.jetbrains.kotlin.backend.common.actualizer.SpecialFakeOverrideSymbolsResolverVisitor;
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.IrVerificationMode;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrCommonMemberStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponentsStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDelegatedMembersGenerationStrategy;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrFakeOverrideStrategy;
import org.jetbrains.kotlin.fir.backend.Fir2IrPluginContext;
import org.jetbrains.kotlin.fir.backend.Fir2IrSyntheticIrBuiltinsSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.IrBuiltInsOverFir;
import org.jetbrains.kotlin.fir.backend.LenientModeMissingActualDeclarationProvider;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassGeneratedMemberBodyGenerator;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrFileEntry;
import org.jetbrains.kotlin.ir.KtDiagnosticReporterWithImplicitIrBasedContext;
import org.jetbrains.kotlin.ir.LineAndColumn;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrModuleFragmentImpl;
import org.jetbrains.kotlin.ir.declarations.lazy.IrLazyDeclarationBase;
import org.jetbrains.kotlin.ir.overrides.IrFakeOverrideBuilder;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeSystemContext;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IdSignatureComposer;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.NameProvider;
import org.jetbrains.kotlin.ir.util.SymbolTable;
import org.jetbrains.kotlin.ir.validation.IrValidationError;
import org.jetbrains.kotlin.ir.validation.IrValidatorConfig;
import org.jetbrains.kotlin.ir.validation.IrValidatorConfigKt;
import org.jetbrains.kotlin.ir.validation.IrValidatorKt;
import org.jetbrains.kotlin.ir.validation.checkers.IrChecker;
import org.jetbrains.kotlin.ir.validation.checkers.IrNestedOffsetRangeChecker;
import org.jetbrains.kotlin.ir.validation.checkers.declaration.IrExpressionBodyInFunctionChecker;
import org.jetbrains.kotlin.ir.validation.checkers.declaration.IrFieldVisibilityChecker;
import org.jetbrains.kotlin.ir.validation.checkers.expression.IrCallTypeArgumentCountChecker;
import org.jetbrains.kotlin.ir.validation.checkers.expression.IrCallValueArgumentCountChecker;
import org.jetbrains.kotlin.ir.validation.checkers.expression.IrCrossFileFieldUsageChecker;
import org.jetbrains.kotlin.ir.validation.checkers.expression.IrValueAccessScopeChecker;
import org.jetbrains.kotlin.ir.validation.checkers.symbol.IrVisibilityChecker;
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.platform.jvm.JvmPlatformKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001eB\u009f\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00030\u0013\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u0013¢\u0006\u0004\b \u0010!J\u0006\u00108\u001a\u000209J\b\u0010:\u001a\u00020;H\u0002J$\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020>0=2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020BH\u0002J\f\u0010C\u001a\u000209*\u00020;H\u0002J\u000e\u0010D\u001a\u0004\u0018\u00010E*\u00020;H\u0002J\f\u0010F\u001a\u00020\u001f*\u00020;H\u0002J\"\u0010G\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020I0=*\u00020;2\b\u0010J\u001a\u0004\u0018\u00010EH\u0002J\"\u0010K\u001a\u000e\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020I0=*\u00020;2\b\u0010J\u001a\u0004\u0018\u00010EH\u0002J\u0014\u0010M\u001a\u00020\u001f*\u00020;2\u0006\u0010N\u001a\u00020HH\u0002J\f\u0010O\u001a\u00020\u001f*\u00020;H\u0002J\f\u0010P\u001a\u00020\u001f*\u00020;H\u0002J\f\u0010Q\u001a\u00020\u001f*\u00020;H\u0002J\"\u0010R\u001a\u00020\u001f*\u00020H2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\u0006\u0010T\u001a\u00020UH\u0002J\u0018\u0010V\u001a\u00020\u001f2\u0006\u0010W\u001a\u00020X2\u0006\u0010T\u001a\u00020UH\u0002J\f\u0010Y\u001a\u00020\u001f*\u00020;H\u0002J\u001e\u0010]\u001a\u00020[*\u00020^2\b\u0010_\u001a\u0004\u0018\u00010\u000b2\u0006\u0010`\u001a\u00020\u001eH\u0002J \u0010a\u001a\u00020\u001f*\u00020b2\u0006\u0010c\u001a\u00020\u001e2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R#\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00030\u0013¢\u0006\b\n\u0000\u001a\u0004\b4\u00101R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u001d\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u0013¢\u0006\b\n\u0000\u001a\u0004\b7\u00101R\u000e\u0010Z\u001a\u00020[X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020[X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrPipeline;", Argument.Delimiters.none, "outputs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "fir2IrExtensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "fir2IrConfiguration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "irGeneratorExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/common/extensions/IrGenerationExtension;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "kotlinBuiltIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "typeSystemContextProvider", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "Lorg/jetbrains/kotlin/ir/types/IrTypeSystemContext;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "extraActualDeclarationExtractorsInitializer", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrExtraActualDeclarationExtractor;", "commonMemberStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;", "irModuleFragmentPostCompute", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", Argument.Delimiters.none, "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;Ljava/util/Collection;Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;Lkotlin/jvm/functions/Function1;Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;Lkotlin/jvm/functions/Function1;Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;Lkotlin/jvm/functions/Function1;)V", "getOutputs", "()Ljava/util/List;", "getFir2IrExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getFir2IrConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getIrGeneratorExtensions", "()Ljava/util/Collection;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getKotlinBuiltIns", "()Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "getTypeSystemContextProvider", "()Lkotlin/jvm/functions/Function1;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getExtraActualDeclarationExtractorsInitializer", "getCommonMemberStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;", "getIrModuleFragmentPostCompute", "convertToIrAndActualize", "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "runFir2IrConversion", "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrPipeline$Fir2IrConversionResult;", "createBuiltInsAndSymbolTable", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/ir/util/SymbolTable;", "componentsStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponentsStorage;", "syntheticIrBuiltinsSymbolsContainer", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSyntheticIrBuiltinsSymbolsContainer;", "runActualizationPipeline", "createIrActualizer", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizer;", "generateSyntheticBodiesOfDataValueMembers", "createFakeOverrideBuilder", "Lorg/jetbrains/kotlin/ir/overrides/IrFakeOverrideBuilder;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDelegatedMembersGenerationStrategy;", "irActualizer", "buildFakeOverridesAndPlatformSpecificDeclarations", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrFakeOverrideStrategy;", "buildFakeOverrides", "fakeOverrideBuilder", "resolveFakeOverrideSymbols", "checkUnboundSymbols", "inlineConstants", "buildForAll", ModuleXmlParser.MODULES, "resolver", "Lorg/jetbrains/kotlin/backend/common/actualizer/SpecialFakeOverrideSymbolsResolver;", "resolveOverridenSymbolsInLazyClass", "clazz", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyClass;", "removeGeneratedBuiltinsDeclarationsIfNeeded", "hasIrValidationErrorFromFrontend", Argument.Delimiters.none, "hasIrValidationErrorFromPlugin", "runMandatoryIrValidation", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "extension", ModuleXmlParser.MODULE, "applyIrGenerationExtensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext;", "irModuleFragment", "irGenerationExtensions", "Fir2IrConversionResult", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class Fir2IrPipeline {
    private final Fir2IrCommonMemberStorage commonMemberStorage;
    private final Function1<Fir2IrComponents, List<IrExtraActualDeclarationExtractor>> extraActualDeclarationExtractorsInitializer;
    private final Fir2IrConfiguration fir2IrConfiguration;
    private final Fir2IrExtensions fir2IrExtensions;
    private boolean hasIrValidationErrorFromFrontend;
    private boolean hasIrValidationErrorFromPlugin;
    private final Collection<IrGenerationExtension> irGeneratorExtensions;
    private final KotlinMangler.IrMangler irMangler;
    private final Function1<IrModuleFragment, Unit> irModuleFragmentPostCompute;
    private final KotlinBuiltIns kotlinBuiltIns;
    private final List<SingleModuleFrontendOutput> outputs;
    private final IrSpecialAnnotationsProvider specialAnnotationsProvider;
    private final Function1<IrBuiltIns, IrTypeSystemContext> typeSystemContextProvider;
    private final Fir2IrVisibilityConverter visibilityConverter;

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0002\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrPipeline$Fir2IrConversionResult;", Argument.Delimiters.none, "mainIrFragment", "Lorg/jetbrains/kotlin/ir/declarations/impl/IrModuleFragmentImpl;", "dependentIrFragments", Argument.Delimiters.none, "componentsStoragePerSourceSession", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponentsStorage;", "commonMemberStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;", "generatedDataValueClassSyntheticFunctions", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage$DataValueClassGeneratedMembersInfo;", "irBuiltIns", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "symbolTable", "Lorg/jetbrains/kotlin/ir/util/SymbolTable;", "irTypeSystemContext", "Lorg/jetbrains/kotlin/ir/types/IrTypeSystemContext;", "fakeOverrideResolver", "Lorg/jetbrains/kotlin/backend/common/actualizer/SpecialFakeOverrideSymbolsResolver;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/impl/IrModuleFragmentImpl;Ljava/util/List;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;Ljava/util/Map;Lorg/jetbrains/kotlin/ir/IrBuiltIns;Lorg/jetbrains/kotlin/ir/util/SymbolTable;Lorg/jetbrains/kotlin/ir/types/IrTypeSystemContext;Lorg/jetbrains/kotlin/backend/common/actualizer/SpecialFakeOverrideSymbolsResolver;)V", "getMainIrFragment", "()Lorg/jetbrains/kotlin/ir/declarations/impl/IrModuleFragmentImpl;", "getDependentIrFragments", "()Ljava/util/List;", "getComponentsStoragePerSourceSession", "()Ljava/util/Map;", "getCommonMemberStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;", "getGeneratedDataValueClassSyntheticFunctions", "getIrBuiltIns", "()Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "getSymbolTable", "()Lorg/jetbrains/kotlin/ir/util/SymbolTable;", "getIrTypeSystemContext", "()Lorg/jetbrains/kotlin/ir/types/IrTypeSystemContext;", "getFakeOverrideResolver", "()Lorg/jetbrains/kotlin/backend/common/actualizer/SpecialFakeOverrideSymbolsResolver;", "componentsStorage", "getComponentsStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponentsStorage;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Fir2IrConversionResult {
        private final Fir2IrCommonMemberStorage commonMemberStorage;
        private final Fir2IrComponentsStorage componentsStorage;
        private final Map<FirSession, Fir2IrComponentsStorage> componentsStoragePerSourceSession;
        private final List<IrModuleFragmentImpl> dependentIrFragments;
        private final SpecialFakeOverrideSymbolsResolver fakeOverrideResolver;
        private final Map<IrClass, Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo> generatedDataValueClassSyntheticFunctions;
        private final IrBuiltIns irBuiltIns;
        private final IrTypeSystemContext irTypeSystemContext;
        private final IrModuleFragmentImpl mainIrFragment;
        private final SymbolTable symbolTable;

        public Fir2IrConversionResult(IrModuleFragmentImpl irModuleFragmentImpl, List<IrModuleFragmentImpl> list, Map<FirSession, Fir2IrComponentsStorage> map, Fir2IrCommonMemberStorage fir2IrCommonMemberStorage, Map<IrClass, Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo> map2, IrBuiltIns irBuiltIns, SymbolTable symbolTable, IrTypeSystemContext irTypeSystemContext, SpecialFakeOverrideSymbolsResolver specialFakeOverrideSymbolsResolver) {
            irModuleFragmentImpl.getClass();
            list.getClass();
            map.getClass();
            fir2IrCommonMemberStorage.getClass();
            map2.getClass();
            irBuiltIns.getClass();
            symbolTable.getClass();
            irTypeSystemContext.getClass();
            specialFakeOverrideSymbolsResolver.getClass();
            this.mainIrFragment = irModuleFragmentImpl;
            this.dependentIrFragments = list;
            this.componentsStoragePerSourceSession = map;
            this.commonMemberStorage = fir2IrCommonMemberStorage;
            this.generatedDataValueClassSyntheticFunctions = map2;
            this.irBuiltIns = irBuiltIns;
            this.symbolTable = symbolTable;
            this.irTypeSystemContext = irTypeSystemContext;
            this.fakeOverrideResolver = specialFakeOverrideSymbolsResolver;
            this.componentsStorage = (Fir2IrComponentsStorage) CollectionsKt.last(map.values());
        }

        public final Fir2IrCommonMemberStorage getCommonMemberStorage() {
            return this.commonMemberStorage;
        }

        public final Fir2IrComponentsStorage getComponentsStorage() {
            return this.componentsStorage;
        }

        public final Map<FirSession, Fir2IrComponentsStorage> getComponentsStoragePerSourceSession() {
            return this.componentsStoragePerSourceSession;
        }

        public final List<IrModuleFragmentImpl> getDependentIrFragments() {
            return this.dependentIrFragments;
        }

        public final SpecialFakeOverrideSymbolsResolver getFakeOverrideResolver() {
            return this.fakeOverrideResolver;
        }

        public final Map<IrClass, Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo> getGeneratedDataValueClassSyntheticFunctions() {
            return this.generatedDataValueClassSyntheticFunctions;
        }

        public final IrBuiltIns getIrBuiltIns() {
            return this.irBuiltIns;
        }

        public final IrTypeSystemContext getIrTypeSystemContext() {
            return this.irTypeSystemContext;
        }

        public final IrModuleFragmentImpl getMainIrFragment() {
            return this.mainIrFragment;
        }

        public final SymbolTable getSymbolTable() {
            return this.symbolTable;
        }
    }

    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\r"}, d2 = {"org/jetbrains/kotlin/fir/pipeline/Fir2IrPipeline$buildForAll$ClassVisitor", "Lorg/jetbrains/kotlin/ir/visitors/IrVisitorVoid;", "<init>", "(Ljava/util/Set;Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrPipeline;Lorg/jetbrains/kotlin/backend/common/actualizer/SpecialFakeOverrideSymbolsResolver;Lorg/jetbrains/kotlin/ir/overrides/IrFakeOverrideBuilder;)V", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/ir/IrElement;", "isIgnoredClass", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "visitClass", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ClassVisitor extends IrVisitorVoid {
        final /* synthetic */ Set<IrClass> $builtFakeOverridesClasses;
        final /* synthetic */ SpecialFakeOverrideSymbolsResolver $resolver;
        final /* synthetic */ IrFakeOverrideBuilder $this_buildForAll;
        final /* synthetic */ Fir2IrPipeline this$0;

        public ClassVisitor(Set<IrClass> set, Fir2IrPipeline fir2IrPipeline, SpecialFakeOverrideSymbolsResolver specialFakeOverrideSymbolsResolver, IrFakeOverrideBuilder irFakeOverrideBuilder) {
            this.$builtFakeOverridesClasses = set;
            this.this$0 = fir2IrPipeline;
            this.$resolver = specialFakeOverrideSymbolsResolver;
            this.$this_buildForAll = irFakeOverrideBuilder;
        }

        private final boolean isIgnoredClass(IrClass declaration) {
            return declaration.isExpect() || (declaration.getMetadata() instanceof MetadataSource.CodeFragment);
        }

        public void visitClass(IrClass declaration) {
            declaration.getClass();
            if (!isIgnoredClass(declaration)) {
                Fir2IrPipeline.buildForAll$buildFakeOverrides(this.$builtFakeOverridesClasses, this.this$0, this.$resolver, this.$this_buildForAll, declaration);
            }
            IrVisitorsKt.acceptChildrenVoid(declaration, this);
        }

        public void visitElement(IrElement element) {
            element.getClass();
            IrVisitorsKt.acceptChildrenVoid(element, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Fir2IrPipeline(List<SingleModuleFrontendOutput> list, Fir2IrExtensions fir2IrExtensions, Fir2IrConfiguration fir2IrConfiguration, Collection<? extends IrGenerationExtension> collection, KotlinMangler.IrMangler irMangler, Fir2IrVisibilityConverter fir2IrVisibilityConverter, KotlinBuiltIns kotlinBuiltIns, Function1<? super IrBuiltIns, ? extends IrTypeSystemContext> function1, IrSpecialAnnotationsProvider irSpecialAnnotationsProvider, Function1<? super Fir2IrComponents, ? extends List<? extends IrExtraActualDeclarationExtractor>> function2, Fir2IrCommonMemberStorage fir2IrCommonMemberStorage, Function1<? super IrModuleFragment, Unit> function3) {
        list.getClass();
        fir2IrExtensions.getClass();
        fir2IrConfiguration.getClass();
        collection.getClass();
        irMangler.getClass();
        fir2IrVisibilityConverter.getClass();
        kotlinBuiltIns.getClass();
        function1.getClass();
        function2.getClass();
        fir2IrCommonMemberStorage.getClass();
        function3.getClass();
        this.outputs = list;
        this.fir2IrExtensions = fir2IrExtensions;
        this.fir2IrConfiguration = fir2IrConfiguration;
        this.irGeneratorExtensions = collection;
        this.irMangler = irMangler;
        this.visibilityConverter = fir2IrVisibilityConverter;
        this.kotlinBuiltIns = kotlinBuiltIns;
        this.typeSystemContextProvider = function1;
        this.specialAnnotationsProvider = irSpecialAnnotationsProvider;
        this.extraActualDeclarationExtractorsInitializer = function2;
        this.commonMemberStorage = fir2IrCommonMemberStorage;
        this.irModuleFragmentPostCompute = function3;
    }

    public static Pair a(IrFile irFile, int i) {
        IrFileEntry fileEntry = irFile.getFileEntry();
        if (!fileEntry.getSupportsDebugInfo()) {
            fileEntry = null;
        }
        if (fileEntry == null) {
            return null;
        }
        LineAndColumn lineAndColumnNumbers = fileEntry.getLineAndColumnNumbers(i);
        return TuplesKt.to(Integer.valueOf(lineAndColumnNumbers.component1()), Integer.valueOf(lineAndColumnNumbers.component2()));
    }

    public static CompilerMessageSeverity b(boolean z, IrPluginContext irPluginContext, CompilerMessageSeverity compilerMessageSeverity, IrValidationError irValidationError) {
        irValidationError.getClass();
        if (z) {
            IrValidationError.Cause cause = irValidationError.getCause();
            if (!(cause instanceof IrValidationError.Cause.UnboundSymbol) && !(cause instanceof IrExpressionBodyInFunctionChecker) && !(cause instanceof IrFieldVisibilityChecker)) {
                if (cause instanceof IrCrossFileFieldUsageChecker) {
                    return irPluginContext.getLanguageVersionSettings().supportsFeature(LanguageFeature.ForbidCrossFileIrFieldAccessInKlibs) ? CompilerMessageSeverity.ERROR : CompilerMessageSeverity.WARNING;
                }
            }
            return CompilerMessageSeverity.ERROR;
        }
        return compilerMessageSeverity;
    }

    private final void buildFakeOverrides(Fir2IrConversionResult fir2IrConversionResult, IrFakeOverrideBuilder irFakeOverrideBuilder) {
        buildForAll(irFakeOverrideBuilder, CollectionsKt.plus(fir2IrConversionResult.getDependentIrFragments(), fir2IrConversionResult.getMainIrFragment()), fir2IrConversionResult.getFakeOverrideResolver());
        fir2IrConversionResult.getComponentsStorage().getSymbolsMappingForLazyClasses().enableRemapper();
    }

    private final Pair<Fir2IrFakeOverrideStrategy, Fir2IrDelegatedMembersGenerationStrategy> buildFakeOverridesAndPlatformSpecificDeclarations(Fir2IrConversionResult fir2IrConversionResult, IrActualizer irActualizer) {
        Pair<IrFakeOverrideBuilder, Fir2IrDelegatedMembersGenerationStrategy> pairCreateFakeOverrideBuilder = createFakeOverrideBuilder(fir2IrConversionResult, irActualizer);
        IrFakeOverrideBuilder irFakeOverrideBuilder = (IrFakeOverrideBuilder) pairCreateFakeOverrideBuilder.component1();
        Fir2IrDelegatedMembersGenerationStrategy fir2IrDelegatedMembersGenerationStrategy = (Fir2IrDelegatedMembersGenerationStrategy) pairCreateFakeOverrideBuilder.component2();
        buildFakeOverrides(fir2IrConversionResult, irFakeOverrideBuilder);
        if (!fir2IrConversionResult.getComponentsStorage().getConfiguration().getSkipBodies()) {
            fir2IrDelegatedMembersGenerationStrategy.generateDelegatedBodies();
        }
        Fir2IrFakeOverrideStrategy strategy = irFakeOverrideBuilder.getStrategy();
        strategy.getClass();
        return TuplesKt.to(strategy, fir2IrDelegatedMembersGenerationStrategy);
    }

    private final void buildForAll(IrFakeOverrideBuilder irFakeOverrideBuilder, List<? extends IrModuleFragment> list, SpecialFakeOverrideSymbolsResolver specialFakeOverrideSymbolsResolver) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<? extends IrModuleFragment> it = list.iterator();
        while (it.hasNext()) {
            for (final IrFile irFile : it.next().getFiles()) {
                try {
                    IrVisitorsKt.acceptVoid(irFile, new ClassVisitor(linkedHashSet, this, specialFakeOverrideSymbolsResolver, irFakeOverrideBuilder));
                } catch (Throwable th) {
                    BackendException.Companion.report$default(BackendException.Companion, th, "IR fake override builder", irFile.getFileEntry().getName(), (String) null, new Function1() { // from class: org.jetbrains.kotlin.fir.pipeline.c
                        public final Object invoke(Object obj) {
                            return Fir2IrPipeline.a(irFile, ((Integer) obj).intValue());
                        }
                    }, 8, (Object) null);
                    wq6.a();
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildForAll$buildFakeOverrides(Set<IrClass> set, Fir2IrPipeline fir2IrPipeline, SpecialFakeOverrideSymbolsResolver specialFakeOverrideSymbolsResolver, IrFakeOverrideBuilder irFakeOverrideBuilder, IrClass irClass) {
        if (set.add(irClass)) {
            Iterator it = irClass.getSuperTypes().iterator();
            while (it.hasNext()) {
                IrClass irClass2 = IrTypesKt.getClass((IrType) it.next());
                if (irClass2 != null) {
                    buildForAll$buildFakeOverrides(set, fir2IrPipeline, specialFakeOverrideSymbolsResolver, irFakeOverrideBuilder, irClass2);
                }
            }
            if (irClass instanceof IrLazyDeclarationBase) {
                fir2IrPipeline.resolveOverridenSymbolsInLazyClass((Fir2IrLazyClass) irClass, specialFakeOverrideSymbolsResolver);
            } else {
                irFakeOverrideBuilder.buildFakeOverridesForClass(irClass, false);
            }
        }
    }

    public static boolean c(IrFile irFile) {
        irFile.getClass();
        return Intrinsics.areEqual(IrDeclarationsKt.getName(irFile), IrElementsCreationUtilsKt.generatedBuiltinsDeclarationsFileName);
    }

    private final void checkUnboundSymbols(Fir2IrConversionResult fir2IrConversionResult) {
        IrValidatorKt.validateIr$default(fir2IrConversionResult.getMainIrFragment(), fir2IrConversionResult.getIrBuiltIns(), new IrValidatorConfig(false, true, (Set) null, 5, (DefaultConstructorMarker) null), this.fir2IrConfiguration.getMessageCollector(), IrVerificationMode.ERROR, (String) null, (String) null, 96, (Object) null);
    }

    private final Pair<IrBuiltIns, SymbolTable> createBuiltInsAndSymbolTable(Fir2IrComponentsStorage componentsStorage, Fir2IrSyntheticIrBuiltinsSymbolsContainer syntheticIrBuiltinsSymbolsContainer) {
        return TuplesKt.to(new IrBuiltInsOverFir(componentsStorage, syntheticIrBuiltinsSymbolsContainer), new SymbolTable((IdSignatureComposer) null, IrFactoryImpl.INSTANCE, (NameProvider) null, componentsStorage.getLock(), 4, (DefaultConstructorMarker) null));
    }

    private final Pair<IrFakeOverrideBuilder, Fir2IrDelegatedMembersGenerationStrategy> createFakeOverrideBuilder(Fir2IrConversionResult fir2IrConversionResult, IrActualizer irActualizer) {
        FirSession session = fir2IrConversionResult.getComponentsStorage().getSession();
        Fir2IrDelegatedMembersGenerationStrategy fir2IrDelegatedMembersGenerationStrategy = new Fir2IrDelegatedMembersGenerationStrategy(fir2IrConversionResult.getSymbolTable().getIrFactory(), fir2IrConversionResult.getIrBuiltIns(), this.fir2IrExtensions, fir2IrConversionResult.getCommonMemberStorage().getDelegatedClassesInfo(), irActualizer != null ? irActualizer.getClassActualizationInfo() : null);
        return TuplesKt.to(new IrFakeOverrideBuilder(fir2IrConversionResult.getIrTypeSystemContext(), new Fir2IrFakeOverrideStrategy(JvmPlatformKt.isJvm(FirModuleDataKt.getModuleData(session).getPlatform()), JvmPlatformKt.isJvm(FirModuleDataKt.getModuleData(session).getPlatform()), fir2IrDelegatedMembersGenerationStrategy), fir2IrConversionResult.getComponentsStorage().getExtensions().getExternalOverridabilityConditions()), fir2IrDelegatedMembersGenerationStrategy);
    }

    private final IrActualizer createIrActualizer(Fir2IrConversionResult fir2IrConversionResult) {
        if (fir2IrConversionResult.getDependentIrFragments().isEmpty()) {
            return null;
        }
        ReferenceAllCommonDependenciesKt.referenceAllCommonDependencies(this.outputs);
        return new IrActualizer(new KtDiagnosticReporterWithImplicitIrBasedContext(this.fir2IrConfiguration.getDiagnosticReporter(), this.fir2IrConfiguration.getLanguageVersionSettings()), fir2IrConversionResult.getIrTypeSystemContext(), this.fir2IrConfiguration.getLanguageVersionSettings(), this.fir2IrConfiguration.getExpectActualTracker(), fir2IrConversionResult.getMainIrFragment(), fir2IrConversionResult.getDependentIrFragments(), (List) this.extraActualDeclarationExtractorsInitializer.invoke(fir2IrConversionResult.getComponentsStorage()), LenientModeMissingActualDeclarationProvider.INSTANCE.initializeIfNeeded(fir2IrConversionResult.getComponentsStorage()), IrCommonToPlatformDependencyActualizerMapContributor.INSTANCE.create(((SingleModuleFrontendOutput) CollectionsKt.last(this.outputs)).getSession(), fir2IrConversionResult.getComponentsStoragePerSourceSession()), ((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(fir2IrConversionResult.getComponentsStorage().getSession()).getFlag(AnalysisFlags.INSTANCE.getHierarchicalMultiplatformCompilation())).booleanValue());
    }

    private final void generateSyntheticBodiesOfDataValueMembers(Fir2IrConversionResult fir2IrConversionResult) {
        new Fir2IrDataClassGeneratedMemberBodyGenerator(fir2IrConversionResult.getIrBuiltIns()).generateBodiesForClassesWithSyntheticDataClassMembers(fir2IrConversionResult.getGeneratedDataValueClassSyntheticFunctions(), fir2IrConversionResult.getSymbolTable());
    }

    private final void inlineConstants(Fir2IrConversionResult fir2IrConversionResult) {
        InlineConstTracker inlineConstTracker = fir2IrConversionResult.getComponentsStorage().getConfiguration().getInlineConstTracker();
        for (IrFile irFile : fir2IrConversionResult.getMainIrFragment().getFiles()) {
            irFile.transform(new ConstInliner(irFile, inlineConstTracker), (Object) null);
        }
    }

    private final void removeGeneratedBuiltinsDeclarationsIfNeeded(Fir2IrConversionResult fir2IrConversionResult) {
        if (((Boolean) this.fir2IrConfiguration.getLanguageVersionSettings().getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue()) {
            CollectionsKt.removeAll(fir2IrConversionResult.getMainIrFragment().getFiles(), new Function1() { // from class: org.jetbrains.kotlin.fir.pipeline.b
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Fir2IrPipeline.c((IrFile) obj));
                }
            });
        }
    }

    private final void resolveFakeOverrideSymbols(Fir2IrConversionResult fir2IrConversionResult) {
        IrVisitorsKt.acceptVoid(fir2IrConversionResult.getMainIrFragment(), new SpecialFakeOverrideSymbolsResolverVisitor(fir2IrConversionResult.getFakeOverrideResolver()));
    }

    private final void resolveOverridenSymbolsInLazyClass(Fir2IrLazyClass clazz, SpecialFakeOverrideSymbolsResolver resolver) {
        Iterator<IrDeclaration> it = clazz.getDeclarations().iterator();
        while (it.hasNext()) {
            IrProperty irProperty = (IrDeclaration) it.next();
            if (irProperty instanceof IrSimpleFunction) {
                IrSimpleFunction irSimpleFunction = (IrSimpleFunction) irProperty;
                List overriddenSymbols = irSimpleFunction.getOverriddenSymbols();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenSymbols, 10));
                Iterator it2 = overriddenSymbols.iterator();
                while (it2.hasNext()) {
                    arrayList.add(resolver.getReferencedSimpleFunction((IrSimpleFunctionSymbol) it2.next()));
                }
                irSimpleFunction.setOverriddenSymbols(arrayList);
            } else if (irProperty instanceof IrProperty) {
                IrProperty irProperty2 = irProperty;
                List overriddenSymbols2 = irProperty2.getOverriddenSymbols();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenSymbols2, 10));
                Iterator it3 = overriddenSymbols2.iterator();
                while (it3.hasNext()) {
                    arrayList2.add(resolver.getReferencedProperty((IrPropertySymbol) it3.next()));
                }
                irProperty2.setOverriddenSymbols(arrayList2);
                IrSimpleFunction getter = irProperty2.getGetter();
                if (getter != null) {
                    List overriddenSymbols3 = getter.getOverriddenSymbols();
                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenSymbols3, 10));
                    Iterator it4 = overriddenSymbols3.iterator();
                    while (it4.hasNext()) {
                        arrayList3.add(resolver.getReferencedSimpleFunction((IrSimpleFunctionSymbol) it4.next()));
                    }
                    getter.setOverriddenSymbols(arrayList3);
                }
                IrSimpleFunction setter = irProperty2.getSetter();
                if (setter != null) {
                    List overriddenSymbols4 = setter.getOverriddenSymbols();
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenSymbols4, 10));
                    Iterator it5 = overriddenSymbols4.iterator();
                    while (it5.hasNext()) {
                        arrayList4.add(resolver.getReferencedSimpleFunction((IrSimpleFunctionSymbol) it5.next()));
                    }
                    setter.setOverriddenSymbols(arrayList4);
                }
            }
        }
    }

    private final Fir2IrActualizedResult runActualizationPipeline(Fir2IrConversionResult fir2IrConversionResult) {
        IrExpectActualMap irExpectActualMap;
        IrActualizer irActualizerCreateIrActualizer = createIrActualizer(fir2IrConversionResult);
        if (irActualizerCreateIrActualizer != null) {
            irActualizerCreateIrActualizer.actualizeClassifiers();
        }
        generateSyntheticBodiesOfDataValueMembers(fir2IrConversionResult);
        Pair<Fir2IrFakeOverrideStrategy, Fir2IrDelegatedMembersGenerationStrategy> pairBuildFakeOverridesAndPlatformSpecificDeclarations = buildFakeOverridesAndPlatformSpecificDeclarations(fir2IrConversionResult, irActualizerCreateIrActualizer);
        Fir2IrFakeOverrideStrategy fir2IrFakeOverrideStrategy = (Fir2IrFakeOverrideStrategy) pairBuildFakeOverridesAndPlatformSpecificDeclarations.component1();
        Fir2IrDelegatedMembersGenerationStrategy fir2IrDelegatedMembersGenerationStrategy = (Fir2IrDelegatedMembersGenerationStrategy) pairBuildFakeOverridesAndPlatformSpecificDeclarations.component2();
        if (irActualizerCreateIrActualizer == null || (irExpectActualMap = irActualizerCreateIrActualizer.actualizeCallablesAndMergeModules()) == null) {
            irExpectActualMap = new IrExpectActualMap();
        }
        Fir2IrPluginContext fir2IrPluginContext = new Fir2IrPluginContext(fir2IrConversionResult.getComponentsStorage(), fir2IrConversionResult.getIrBuiltIns(), fir2IrConversionResult.getComponentsStorage().getModuleDescriptor(), fir2IrConversionResult.getSymbolTable(), this.fir2IrConfiguration.getMessageCollector(), this.fir2IrConfiguration.getDiagnosticReporter());
        if (this.fir2IrConfiguration.getDiagnosticReporter().getHasErrors()) {
            if (irActualizerCreateIrActualizer != null) {
                irActualizerCreateIrActualizer.runChecksAndFinalize(irExpectActualMap);
            }
            return new Fir2IrActualizedResult(fir2IrConversionResult.getMainIrFragment(), fir2IrConversionResult.getComponentsStorage(), fir2IrPluginContext, null, fir2IrConversionResult.getIrBuiltIns(), fir2IrConversionResult.getSymbolTable());
        }
        resolveFakeOverrideSymbols(fir2IrConversionResult);
        fir2IrDelegatedMembersGenerationStrategy.updateMetadataSources(fir2IrConversionResult.getCommonMemberStorage().getFirClassesWithInheritanceByDelegation(), ((SingleModuleFrontendOutput) CollectionsKt.last(this.outputs)).getSession(), ((SingleModuleFrontendOutput) CollectionsKt.last(this.outputs)).getScopeSession(), fir2IrConversionResult.getComponentsStorage().getDeclarationStorage(), fir2IrConversionResult.getFakeOverrideResolver());
        checkUnboundSymbols(fir2IrConversionResult);
        inlineConstants(fir2IrConversionResult);
        IrActualizedResult irActualizedResultRunChecksAndFinalize = irActualizerCreateIrActualizer != null ? irActualizerCreateIrActualizer.runChecksAndFinalize(irExpectActualMap) : null;
        fir2IrConversionResult.getFakeOverrideResolver().cacheFakeOverridesOfAllClasses(fir2IrConversionResult.getMainIrFragment());
        fir2IrFakeOverrideStrategy.clearFakeOverrideFields();
        removeGeneratedBuiltinsDeclarationsIfNeeded(fir2IrConversionResult);
        this.hasIrValidationErrorFromFrontend = runMandatoryIrValidation(fir2IrPluginContext, null, fir2IrConversionResult.getMainIrFragment());
        applyIrGenerationExtensions(fir2IrPluginContext, fir2IrConversionResult.getMainIrFragment(), this.irGeneratorExtensions);
        return new Fir2IrActualizedResult(fir2IrConversionResult.getMainIrFragment(), fir2IrConversionResult.getComponentsStorage(), fir2IrPluginContext, irActualizedResultRunChecksAndFinalize, fir2IrConversionResult.getIrBuiltIns(), fir2IrConversionResult.getSymbolTable());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final Fir2IrConversionResult runFir2IrConversion() throws KotlinIllegalArgumentExceptionWithAttachments {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<SingleModuleFrontendOutput> it = this.outputs.iterator();
        while (it.hasNext()) {
            FirSession session = it.next().getSession();
            linkedHashMap.put(FirModuleDataKt.getModuleData(session), new FirProviderWithGeneratedFiles(session, linkedHashMap));
        }
        Fir2IrSyntheticIrBuiltinsSymbolsContainer fir2IrSyntheticIrBuiltinsSymbolsContainer = new Fir2IrSyntheticIrBuiltinsSymbolsContainer();
        SpecialFakeOverrideSymbolsResolver specialFakeOverrideSymbolsResolver = new SpecialFakeOverrideSymbolsResolver();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        List<SingleModuleFrontendOutput> list = this.outputs;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Iterator it2 = list.iterator(); it2.hasNext(); it2 = it2) {
            SingleModuleFrontendOutput singleModuleFrontendOutput = (SingleModuleFrontendOutput) it2.next();
            LinkedHashMap linkedHashMap4 = linkedHashMap3;
            Fir2IrComponentsStorage fir2IrComponentsStorage = new Fir2IrComponentsStorage(singleModuleFrontendOutput.getSession(), singleModuleFrontendOutput.getScopeSession(), singleModuleFrontendOutput.getFir(), this.fir2IrExtensions, this.fir2IrConfiguration, this.visibilityConverter, this.commonMemberStorage, linkedHashMap4, this.irMangler, this.kotlinBuiltIns, this.specialAnnotationsProvider, (FirProviderWithGeneratedFiles) MapsKt.getValue(linkedHashMap, FirModuleDataKt.getModuleData(singleModuleFrontendOutput.getSession())), fir2IrSyntheticIrBuiltinsSymbolsContainer, specialFakeOverrideSymbolsResolver);
            linkedHashMap2.put(singleModuleFrontendOutput.getSession(), fir2IrComponentsStorage);
            IrModuleFragmentImpl irModuleFragmentImplGenerateIrModuleFragment = Fir2IrConverter.INSTANCE.generateIrModuleFragment(fir2IrComponentsStorage, singleModuleFrontendOutput.getFir());
            this.irModuleFragmentPostCompute.invoke(irModuleFragmentImplGenerateIrModuleFragment);
            arrayList.add(irModuleFragmentImplGenerateIrModuleFragment);
            linkedHashMap3 = linkedHashMap4;
        }
        List listDropLast = CollectionsKt.dropLast(arrayList, 1);
        IrModuleFragmentImpl irModuleFragmentImpl = (IrModuleFragmentImpl) CollectionsKt.last(arrayList);
        Pair<IrBuiltIns, SymbolTable> pairCreateBuiltInsAndSymbolTable = createBuiltInsAndSymbolTable((Fir2IrComponentsStorage) CollectionsKt.last(linkedHashMap2.values()), fir2IrSyntheticIrBuiltinsSymbolsContainer);
        IrBuiltIns irBuiltIns = (IrBuiltIns) pairCreateBuiltInsAndSymbolTable.component1();
        return new Fir2IrConversionResult(irModuleFragmentImpl, listDropLast, linkedHashMap2, this.commonMemberStorage, linkedHashMap3, irBuiltIns, (SymbolTable) pairCreateBuiltInsAndSymbolTable.component2(), (IrTypeSystemContext) this.typeSystemContextProvider.invoke(irBuiltIns), specialFakeOverrideSymbolsResolver);
    }

    private final boolean runMandatoryIrValidation(final IrPluginContext irPluginContext, IrGenerationExtension irGenerationExtension, IrModuleFragment irModuleFragment) {
        String string;
        IrVerificationMode mode = this.fir2IrConfiguration.getIrVerificationSettings().getMode();
        final boolean validateForKlibSerialization = this.fir2IrConfiguration.getIrVerificationSettings().getValidateForKlibSerialization();
        if (mode == IrVerificationMode.NONE && !validateForKlibSerialization) {
            return false;
        }
        final CompilerMessageSeverity compilerMessageSeverity = null;
        if (!this.hasIrValidationErrorFromFrontend) {
            if (mode == IrVerificationMode.WARNING) {
                compilerMessageSeverity = CompilerMessageSeverity.WARNING;
            } else if (mode == IrVerificationMode.ERROR) {
                compilerMessageSeverity = CompilerMessageSeverity.ERROR;
            }
        }
        IrBuiltIns irBuiltIns = irPluginContext.getIrBuiltIns();
        IrValidatorConfig irValidatorConfigWithCheckers = IrValidatorConfigKt.withBasicFirstStageChecks(new IrValidatorConfig(true, true, (Set) null, 4, (DefaultConstructorMarker) null)).withCheckers(new IrChecker[]{IrCallValueArgumentCountChecker.INSTANCE, IrCrossFileFieldUsageChecker.INSTANCE, IrValueAccessScopeChecker.INSTANCE});
        if (this.fir2IrConfiguration.getIrVerificationSettings().getEnableIrVisibilityChecks()) {
            irValidatorConfigWithCheckers = irValidatorConfigWithCheckers.withCheckers(new IrChecker[]{IrVisibilityChecker.Companion.getStrict()});
        }
        if (this.fir2IrConfiguration.getIrVerificationSettings().getEnableIrNestedOffsetsChecks()) {
            irValidatorConfigWithCheckers = irValidatorConfigWithCheckers.withCheckers(new IrChecker[]{IrNestedOffsetRangeChecker.INSTANCE});
        }
        if (irGenerationExtension == null) {
            irValidatorConfigWithCheckers = irValidatorConfigWithCheckers.withCheckers(new IrChecker[]{IrCallTypeArgumentCountChecker.INSTANCE});
        }
        if (this.fir2IrConfiguration.getIrVerificationSettings().getEnableIrVarargTypesChecks()) {
            irValidatorConfigWithCheckers = IrValidatorConfigKt.withVarargChecks(irValidatorConfigWithCheckers);
        }
        if (validateForKlibSerialization) {
            irValidatorConfigWithCheckers = irValidatorConfigWithCheckers.withCheckers(new IrChecker[]{IrFieldVisibilityChecker.INSTANCE});
        }
        if (validateForKlibSerialization) {
            irValidatorConfigWithCheckers = irValidatorConfigWithCheckers.withCheckers(new IrChecker[]{IrExpressionBodyInFunctionChecker.INSTANCE});
        }
        IrValidatorConfig irValidatorConfig = irValidatorConfigWithCheckers;
        MessageCollector messageCollector = this.fir2IrConfiguration.getMessageCollector();
        Function1 function1 = new Function1() { // from class: org.jetbrains.kotlin.fir.pipeline.a
            public final Object invoke(Object obj) {
                return Fir2IrPipeline.b(validateForKlibSerialization, irPluginContext, compilerMessageSeverity, (IrValidationError) obj);
            }
        };
        String str = irGenerationExtension == null ? "FIR2IR" : "Applying IR compiler plugins";
        if (irGenerationExtension == null) {
            string = "The frontend generated invalid IR. This is a compiler bug, please report it to https://kotl.in/issue.";
        } else {
            StringBuilder sb = new StringBuilder("The compiler plugin '");
            sb.append(irGenerationExtension.getClass().getName());
            sb.append('\'');
            sb.append(this.hasIrValidationErrorFromPlugin ? " (or another, previously executed plugin, check error above)" : Argument.Delimiters.none);
            sb.append(" generated invalid IR. Please report this bug to the plugin vendor.");
            string = sb.toString();
        }
        return IrValidatorKt.validateIr(irModuleFragment, irBuiltIns, irValidatorConfig, messageCollector, function1, str, string);
    }

    public final void applyIrGenerationExtensions(Fir2IrPluginContext fir2IrPluginContext, IrModuleFragment irModuleFragment, Collection<? extends IrGenerationExtension> collection) {
        fir2IrPluginContext.getClass();
        irModuleFragment.getClass();
        collection.getClass();
        for (IrGenerationExtension irGenerationExtension : collection) {
            try {
                irGenerationExtension.generate(irModuleFragment, fir2IrPluginContext);
                if (runMandatoryIrValidation(fir2IrPluginContext, irGenerationExtension, irModuleFragment)) {
                    this.hasIrValidationErrorFromPlugin = true;
                }
            } catch (Throwable th) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
                throw new IrGenerationExtensionException(th, irGenerationExtension.getClass());
            }
        }
        fir2IrPluginContext.recordLookupsWithoutSpecificFile(irModuleFragment);
    }

    public final Fir2IrActualizedResult convertToIrAndActualize() {
        if (!this.outputs.isEmpty()) {
            return runActualizationPipeline(runFir2IrConversion());
        }
        w01.a("No modules found");
        return null;
    }

    public final Fir2IrCommonMemberStorage getCommonMemberStorage() {
        return this.commonMemberStorage;
    }

    public final Function1<Fir2IrComponents, List<IrExtraActualDeclarationExtractor>> getExtraActualDeclarationExtractorsInitializer() {
        return this.extraActualDeclarationExtractorsInitializer;
    }

    public final Fir2IrConfiguration getFir2IrConfiguration() {
        return this.fir2IrConfiguration;
    }

    public final Fir2IrExtensions getFir2IrExtensions() {
        return this.fir2IrExtensions;
    }

    public final Collection<IrGenerationExtension> getIrGeneratorExtensions() {
        return this.irGeneratorExtensions;
    }

    public final KotlinMangler.IrMangler getIrMangler() {
        return this.irMangler;
    }

    public final Function1<IrModuleFragment, Unit> getIrModuleFragmentPostCompute() {
        return this.irModuleFragmentPostCompute;
    }

    public final KotlinBuiltIns getKotlinBuiltIns() {
        return this.kotlinBuiltIns;
    }

    public final List<SingleModuleFrontendOutput> getOutputs() {
        return this.outputs;
    }

    public final IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.specialAnnotationsProvider;
    }

    public final Function1<IrBuiltIns, IrTypeSystemContext> getTypeSystemContextProvider() {
        return this.typeSystemContextProvider;
    }

    public final Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.visibilityConverter;
    }
}
