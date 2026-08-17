package org.jetbrains.kotlin.fir.backend;

import com.intellij.util.containers.MultiMap;
import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtPsiSourceFileLinesMapping;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtSourceFileLinesMappingFromLineStartOffsets;
import org.jetbrains.kotlin.backend.common.CommonIrAttributesKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGeneratorKt;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.CodeFragmentConversionData;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.InjectedValue;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DestructuringDeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.descriptors.FirModuleDescriptor;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension;
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.GeneratedDeclarationsUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.PsiIrFileEntry;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrReplSnippet;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeAlias;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrFileImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrFileImplKt;
import org.jetbrains.kotlin.ir.declarations.impl.IrModuleFragmentImpl;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrScriptSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrConstructorSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.NaiveSourceBasedFileEntryImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ú\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 ª\u00012\u00020\u0001:\u0002ª\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ&\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u000fH\u0002J\u0010\u0010 \u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u000fH\u0002J\u0016\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0015J\u001d\u0010%\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u0015H\u0000¢\u0006\u0002\b&J\u0018\u0010'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020)2\u0006\u0010$\u001a\u00020\u0015H\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010(\u001a\u00020)H\u0002J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\n2\u0006\u0010$\u001a\u00020\u0015H\u0002J\u001c\u0010.\u001a\b\u0012\u0004\u0012\u00020-0/2\f\u00100\u001a\b\u0012\u0004\u0012\u00020-0/H\u0002J\u0018\u00101\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u00102\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u0015H\u0002J\u0010\u00103\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u00104\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J8\u00105\u001a\u00020\r2\u0006\u00106\u001a\u00020-2\b\u00107\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0014\u00108\u001a\u0010\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020;\u0018\u000109H\u0002J\u001e\u0010<\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\f\u00100\u001a\b\u0012\u0004\u0012\u00020-0\nH\u0002J\u0016\u0010=\u001a\u0004\u0018\u00010:*\u00020;2\u0006\u0010>\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010?\u001a\u00020@X\u0096\u0005¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0012\u0010C\u001a\u00020DX\u0096\u0005¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0012\u0010G\u001a\u00020HX\u0096\u0005¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0012\u0010K\u001a\u00020LX\u0096\u0005¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0012\u0010O\u001a\u00020PX\u0096\u0005¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0012\u0010S\u001a\u00020TX\u0096\u0005¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0012\u0010W\u001a\u00020XX\u0096\u0005¢\u0006\u0006\u001a\u0004\bY\u0010ZR\u0012\u0010[\u001a\u00020\\X\u0096\u0005¢\u0006\u0006\u001a\u0004\b]\u0010^R\u0012\u0010_\u001a\u00020`X\u0096\u0005¢\u0006\u0006\u001a\u0004\ba\u0010bR\u0012\u0010c\u001a\u00020\u0000X\u0096\u0005¢\u0006\u0006\u001a\u0004\bd\u0010eR\u0012\u0010f\u001a\u00020gX\u0096\u0005¢\u0006\u0006\u001a\u0004\bh\u0010iR\u0012\u0010j\u001a\u00020kX\u0096\u0005¢\u0006\u0006\u001a\u0004\bl\u0010mR\u0012\u0010n\u001a\u00020oX\u0096\u0005¢\u0006\u0006\u001a\u0004\bp\u0010qR\u001a\u0010r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010sX\u0096\u0005¢\u0006\u0006\u001a\u0004\bt\u0010uR\u0012\u0010v\u001a\u00020wX\u0096\u0005¢\u0006\u0006\u001a\u0004\bx\u0010yR\u0012\u0010z\u001a\u00020{X\u0096\u0005¢\u0006\u0006\u001a\u0004\b|\u0010}R\u0014\u0010~\u001a\u00020\u007fX\u0096\u0005¢\u0006\b\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001R\u001c\u0010\u0082\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010\nX\u0096\u0005¢\u0006\b\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0016\u0010\u0086\u0001\u001a\u00030\u0087\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0016\u0010\u008a\u0001\u001a\u00030\u008b\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0016\u0010\u008e\u0001\u001a\u00030\u008f\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0016\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0016\u0010\u0096\u0001\u001a\u00030\u0097\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0098\u0001\u0010\u0099\u0001R\u0018\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0016\u0010\u009e\u0001\u001a\u00030\u009f\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b \u0001\u0010¡\u0001R\u0016\u0010¢\u0001\u001a\u00030£\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¤\u0001\u0010¥\u0001R\u0016\u0010¦\u0001\u001a\u00030§\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¨\u0001\u0010©\u0001¨\u0006«\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "moduleDescriptor", "Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;", "c", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;)V", "generatorExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "runSourcesConversion", Argument.Delimiters.none, "allFirFiles", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "irModuleFragment", "Lorg/jetbrains/kotlin/ir/declarations/impl/IrModuleFragmentImpl;", "fir2irVisitor", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;", "processLocalClassAndNestedClassesOnTheFly", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "processLocalClassAndNestedClasses", "registerFileAndClasses", "file", "moduleFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "processClassHeaders", "processFileAndClassMembers", "processAnonymousObjectHeaders", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "irClass", "processClassMembers", "processClassMembers$org_jetbrains_kotlin_fir2ir", "processCodeFragmentMembers", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "computeCodeFragmentReturnType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "delegatedMembers", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "syntheticPropertiesLast", Argument.Delimiters.none, "declarations", "registerClassAndNestedClasses", "registerNestedClasses", "processClassAndNestedClassHeaders", "processNestedClassHeaders", "processMemberDeclaration", "declaration", "containingClass", "delegateFieldToPropertyMap", "Lcom/intellij/util/containers/MultiMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "processScriptLikeDeclaration", "findCorrespondingDelegateProperty", "owner", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "Companion", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrConverter implements Fir2IrComponents {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Fir2IrComponents c;
    private final Fir2IrConversionScope conversionScope;
    private final List<FirDeclarationGenerationExtension> generatorExtensions;
    private final FirModuleDescriptor moduleDescriptor;

    public Fir2IrConverter(FirModuleDescriptor firModuleDescriptor, Fir2IrComponents fir2IrComponents, Fir2IrConversionScope fir2IrConversionScope) {
        firModuleDescriptor.getClass();
        fir2IrComponents.getClass();
        fir2IrConversionScope.getClass();
        this.moduleDescriptor = firModuleDescriptor;
        this.c = fir2IrComponents;
        this.conversionScope = fir2IrConversionScope;
        this.generatorExtensions = FirDeclarationGenerationExtensionKt.getDeclarationGenerators(FirExtensionServiceKt.getExtensionService(getSession()));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrType computeCodeFragmentReturnType(FirCodeFragment codeFragment) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirStatement firStatement = (FirStatement) CollectionsKt.lastOrNull(codeFragment.getBlock().getStatements());
        if (!(firStatement instanceof FirExpression)) {
            return getBuiltins().getUnitType();
        }
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType((FirExpression) firStatement);
        return ConeBuiltinTypeUtilsKt.isNothingOrNullableNothing(resolvedType) ? IrTypesKt.makeNullable(getBuiltins().getAnyType()) : Fir2IrTypeConverterKt.toIrType$default(this, resolvedType, (ConversionTypeOrigin) null, 2, (Object) null);
    }

    public static IrClass d(IrDeclarationParent irDeclarationParent) {
        irDeclarationParent.getClass();
        return (IrClass) irDeclarationParent;
    }

    private final List<FirDeclaration> delegatedMembers(IrClass irClass) {
        List declarations = irClass.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (Intrinsics.areEqual(((IrDeclaration) obj).getOrigin(), IrDeclarationOrigin.Companion.getDELEGATED_MEMBER())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            FirDeclaration firDeclarationOriginalDeclarationForDelegated = this.c.getDeclarationStorage().originalDeclarationForDelegated((IrDeclaration) it.next());
            if (firDeclarationOriginalDeclarationForDelegated != null) {
                arrayList2.add(firDeclarationOriginalDeclarationForDelegated);
            }
        }
        return arrayList2;
    }

    private final FirProperty findCorrespondingDelegateProperty(FirField firField, FirClass firClass) {
        FirValueParameterSymbol resolvedValueParameterSymbol$default;
        FirExpression initializer = firField.getInitializer();
        Object obj = null;
        if (!(initializer instanceof FirQualifiedAccessExpression)) {
            return null;
        }
        FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) initializer;
        if (firQualifiedAccessExpression.getExplicitReceiver() != null || (resolvedValueParameterSymbol$default = FirReferenceUtilsKt.toResolvedValueParameterSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null)) == null) {
            return null;
        }
        List<FirDeclaration> declarations = firClass.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : declarations) {
            if (obj2 instanceof FirProperty) {
                arrayList.add(obj2);
            }
        }
        for (Object obj3 : arrayList) {
            if (Intrinsics.areEqual(DeclarationAttributesKt.getCorrespondingValueParameterFromPrimaryConstructor((FirProperty) obj3), resolvedValueParameterSymbol$default)) {
                obj = obj3;
                break;
            }
        }
        return (FirProperty) obj;
    }

    private final void processClassAndNestedClassHeaders(FirClass klass) {
        Fir2IrClassifiersGenerator.processClassHeader$default(getClassifiersGenerator(), klass, null, 2, null);
        processNestedClassHeaders(klass);
        getClassifierStorage().getIrClass(klass).getDeclarations().clear();
    }

    private final void processClassHeaders(FirFile file) {
        IrFile irFile = getDeclarationStorage().getIrFile(file);
        for (FirDeclaration firDeclaration : file.getDeclarations()) {
            if (firDeclaration instanceof FirRegularClass) {
                processClassAndNestedClassHeaders((FirClass) firDeclaration);
            } else if (firDeclaration instanceof FirReplSnippet) {
                processClassAndNestedClassHeaders(((FirReplSnippet) firDeclaration).getSnippetClass());
            } else if (firDeclaration instanceof FirTypeAlias) {
                getClassifierStorage().createAndCacheIrTypeAlias((FirTypeAlias) firDeclaration, irFile);
            }
        }
        irFile.getDeclarations().clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit processClassMembers$lambda$0$0(FirClass firClass, List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (Intrinsics.areEqual(firNamedFunctionSymbol.getOrigin(), FirDeclarationOrigin.Enhancement.INSTANCE) && Intrinsics.areEqual(firNamedFunctionSymbol.getCallableId().getClassId(), FirDeclarationUtilKt.getClassId(firClass))) {
            list.add(firNamedFunctionSymbol.getFir());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrClass processClassMembers$lambda$1$0(IrClass irClass) {
        return irClass;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final IrClass processCodeFragmentMembers(FirCodeFragment codeFragment, IrClass irClass) throws KotlinIllegalArgumentExceptionWithAttachments {
        CodeFragmentConversionData codeFragmentConversionData = getExtensions().codeFragmentConversionData(codeFragment);
        getDeclarationStorage().enterScope(irClass.getSymbol());
        IrConstructorSymbolImpl irConstructorSymbolImpl = new IrConstructorSymbolImpl((ClassConstructorDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
        IrDeclarationOrigin defined = companion.getDEFINED();
        Name nameSpecial = Name.special("<init>");
        nameSpecial.getClass();
        IrConstructor irConstructorCreateConstructor$default = IrFactory.createConstructor$default(irFactoryImpl, -1, -1, defined, nameSpecial, irClass.getVisibility(), false, false, IrUtilsKt.getDefaultType(irClass), irConstructorSymbolImpl, true, false, (DeserializedContainerSource) null, 2048, (Object) null);
        Fir2IrCallableDeclarationsGeneratorKt.setParent(irConstructorCreateConstructor$default, irClass);
        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irConstructorCreateConstructor$default, irClass);
        FirRegularClassSymbol regularClassSymbol = TypeUtilsKt.toRegularClassSymbol(getSession().getBuiltinTypes().getAnyType(), getSession());
        regularClassSymbol.getClass();
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny((FirClass) regularClassSymbol.getFir(), getSession());
        firConstructorSymbolPrimaryConstructorIfAny.getClass();
        int i = 0;
        IrConstructorSymbol irConstructorSymbol$default = Fir2IrDeclarationStorage.getIrConstructorSymbol$default(getDeclarationStorage(), firConstructorSymbolPrimaryConstructorIfAny, false, 2, null);
        IrBlockBody irBlockBodyCreateBlockBody = irFactoryImpl.createBlockBody(-1, -1);
        irBlockBodyCreateBlockBody.getStatements().add(BuildersKt.IrDelegatingConstructorCallImplWithShape$default(-1, -1, getBuiltins().getUnitType(), irConstructorSymbol$default, 0, 0, 0, false, false, (IrStatementOrigin) null, 512, (Object) null));
        irConstructorCreateConstructor$default.setBody(irBlockBodyCreateBlockBody);
        IrSimpleFunctionSymbolImpl irSimpleFunctionSymbolImpl = new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        IrDeclarationOrigin defined2 = companion.getDEFINED();
        Name methodName = codeFragmentConversionData.getMethodName();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PUBLIC;
        descriptorVisibility.getClass();
        IrSimpleFunction irSimpleFunctionCreateSimpleFunction = irFactoryImpl.createSimpleFunction(-1, -1, defined2, methodName, descriptorVisibility, false, false, computeCodeFragmentReturnType(codeFragment), Modality.FINAL, irSimpleFunctionSymbolImpl, false, false, false, false, false, (DeserializedContainerSource) null, false);
        Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction, irClass);
        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction, irClass);
        List<InjectedValue> injectedValues = codeFragmentConversionData.getInjectedValues();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(injectedValues, 10));
        for (Object obj : injectedValues) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            InjectedValue injectedValue = (InjectedValue) obj;
            boolean isMutated = injectedValue.getIsMutated();
            IrFactoryImpl irFactoryImpl2 = IrFactoryImpl.INSTANCE;
            IrDeclarationOrigin.Companion companion2 = IrDeclarationOrigin.Companion;
            IrDeclarationOrigin shared_variable_in_evaluator_fragment = isMutated ? companion2.getSHARED_VARIABLE_IN_EVALUATOR_FRAGMENT() : companion2.getDEFINED();
            IrParameterKind irParameterKind = IrParameterKind.Regular;
            Name nameIdentifier = Name.identifier("p" + i);
            nameIdentifier.getClass();
            IrValueParameter irValueParameterCreateValueParameter = irFactoryImpl2.createValueParameter(-1, -1, shared_variable_in_evaluator_fragment, irParameterKind, nameIdentifier, Fir2IrTypeConverterKt.toIrType$default(this, injectedValue.getTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null), isMutated, injectedValue.getIrParameterSymbol(), (IrType) null, false, false, false);
            irValueParameterCreateValueParameter.setParent(irSimpleFunctionCreateSimpleFunction);
            arrayList.add(irValueParameterCreateValueParameter);
            i = i2;
        }
        irSimpleFunctionCreateSimpleFunction.setParameters(arrayList);
        getDeclarationStorage().leaveScope(irClass.getSymbol());
        return irClass;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void processFileAndClassMembers(FirFile file) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrFile irFile = getDeclarationStorage().getIrFile(file);
        Iterator<FirDeclaration> it = file.getDeclarations().iterator();
        while (it.hasNext()) {
            processMemberDeclaration(it.next(), null, irFile, null);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void processMemberDeclaration(FirDeclaration declaration, FirClass containingClass, final IrDeclarationParent parent, MultiMap<FirProperty, FirField> delegateFieldToPropertyMap) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrFieldSymbol symbol;
        Collection<FirField> collectionRemove;
        boolean z = containingClass != null && (!(containingClass instanceof FirRegularClass) || ((FirRegularClass) containingClass).getIsLocal());
        if (declaration instanceof FirRegularClass) {
            FirClass firClass = (FirClass) declaration;
            IrClass irClass = getClassifierStorage().getIrClass(firClass);
            processMemberDeclaration$addDeclarationToParentIfNeeded(parent, irClass);
            processClassMembers$org_jetbrains_kotlin_fir2ir(firClass, irClass);
            return;
        }
        if (declaration instanceof FirScript) {
            if (!(parent instanceof IrFile)) {
                w01.a("Failed requirement.");
                return;
            }
            FirScript firScript = (FirScript) declaration;
            IrScript irScriptCreateIrScript = getDeclarationStorage().createIrScript(firScript);
            processMemberDeclaration$addDeclarationToParentIfNeeded(parent, irScriptCreateIrScript);
            irScriptCreateIrScript.setParent(parent);
            Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
            IrScriptSymbol symbol2 = irScriptCreateIrScript.getSymbol();
            declarationStorage.enterScope(symbol2);
            processScriptLikeDeclaration(irScriptCreateIrScript, firScript.getDeclarations());
            declarationStorage.leaveScope(symbol2);
            return;
        }
        if (declaration instanceof FirReplSnippet) {
            if (!(parent instanceof IrFile)) {
                w01.a("Failed requirement.");
                return;
            }
            FirReplSnippet firReplSnippet = (FirReplSnippet) declaration;
            IrReplSnippet irReplSnippetCreateIrReplSnippet = getDeclarationStorage().createIrReplSnippet(firReplSnippet);
            processMemberDeclaration$addDeclarationToParentIfNeeded(parent, irReplSnippetCreateIrReplSnippet);
            irReplSnippetCreateIrReplSnippet.setParent(parent);
            processMemberDeclaration(firReplSnippet.getSnippetClass(), containingClass, parent, delegateFieldToPropertyMap);
            return;
        }
        if (declaration instanceof FirNamedFunction) {
            Fir2IrDeclarationStorage.createAndCacheIrFunction$default(getDeclarationStorage(), (FirFunction) declaration, parent, null, z, null, false, 52, null);
            return;
        }
        if (declaration instanceof FirProperty) {
            if (containingClass == null || !UtilsKt.isEnumEntries((FirVariable) declaration, containingClass) || LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.EnumEntries)) {
                IrProperty irPropertyCreateAndCacheIrProperty$default = Fir2IrDeclarationStorage.createAndCacheIrProperty$default(getDeclarationStorage(), (FirProperty) declaration, parent, null, null, false, 28, null);
                if (delegateFieldToPropertyMap == null || (collectionRemove = delegateFieldToPropertyMap.remove(declaration)) == null) {
                    return;
                }
                IrField backingField = irPropertyCreateAndCacheIrProperty$default.getBackingField();
                backingField.getClass();
                for (FirField firField : collectionRemove) {
                    Fir2IrDeclarationStorage declarationStorage2 = getDeclarationStorage();
                    firField.getClass();
                    declarationStorage2.recordSupertypeDelegateFieldMappedToBackingField(firField, backingField.getSymbol());
                }
                return;
            }
            return;
        }
        if (declaration instanceof FirField) {
            if (!(declaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) {
                f2f.a("Unexpected non-synthetic field: ", UtilsKt.render(declaration));
                return;
            }
            if (containingClass == null) {
                w01.a("Required value was null.");
                return;
            }
            if (delegateFieldToPropertyMap == null) {
                w01.a("Required value was null.");
                return;
            }
            if (!(parent instanceof IrClass)) {
                w01.a("Failed requirement.");
                return;
            }
            FirField firField2 = (FirField) declaration;
            FirProperty firPropertyFindCorrespondingDelegateProperty = findCorrespondingDelegateProperty(firField2, containingClass);
            if (firPropertyFindCorrespondingDelegateProperty == null || firPropertyFindCorrespondingDelegateProperty.getIsVar()) {
                symbol = getDeclarationStorage().createSupertypeDelegateIrField$org_jetbrains_kotlin_fir2ir(firField2, (IrClass) parent).getSymbol();
            } else {
                delegateFieldToPropertyMap.putValue(firPropertyFindCorrespondingDelegateProperty, declaration);
                IrSymbol irPropertySymbol$default = Fir2IrDeclarationStorage.getIrPropertySymbol$default(getDeclarationStorage(), firPropertyFindCorrespondingDelegateProperty.getSymbol(), null, 2, null);
                Fir2IrDeclarationStorage declarationStorage3 = getDeclarationStorage();
                irPropertySymbol$default.getClass();
                symbol = declarationStorage3.findBackingFieldOfProperty((IrPropertySymbol) irPropertySymbol$default);
                if (symbol == null) {
                    f2f.a("Backing field not found for property ", firPropertyFindCorrespondingDelegateProperty.getReturnTypeRef());
                    return;
                }
            }
            getDeclarationStorage().recordSupertypeDelegationInformation(containingClass, (IrClass) parent, Fir2IrTypeConverterKt.toIrType$default(this, firField2.getReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null), symbol);
            return;
        }
        if (declaration instanceof FirConstructor) {
            FirConstructor firConstructor = (FirConstructor) declaration;
            if (firConstructor.getIsPrimary()) {
                return;
            }
            Fir2IrDeclarationStorage.createAndCacheIrConstructor$default(getDeclarationStorage(), firConstructor, new Function0() { // from class: cv4
                public final Object invoke() {
                    return Fir2IrConverter.d(parent);
                }
            }, null, z, 4, null);
            return;
        }
        if (declaration instanceof FirEnumEntry) {
            parent.getClass();
            Fir2IrClassifierStorage.createAndCacheIrEnumEntry$default(getClassifierStorage(), (FirEnumEntry) declaration, (IrClass) parent, null, 4, null);
            return;
        }
        if (declaration instanceof FirAnonymousInitializer) {
            parent.getClass();
            getDeclarationStorage().createIrAnonymousInitializer((FirAnonymousInitializer) declaration, (IrClass) parent);
            return;
        }
        if (declaration instanceof FirTypeAlias) {
            IrTypeAlias cachedTypeAlias$org_jetbrains_kotlin_fir2ir = getClassifierStorage().getCachedTypeAlias$org_jetbrains_kotlin_fir2ir((FirTypeAlias) declaration);
            if (cachedTypeAlias$org_jetbrains_kotlin_fir2ir != null) {
                processMemberDeclaration$addDeclarationToParentIfNeeded(parent, cachedTypeAlias$org_jetbrains_kotlin_fir2ir);
                return;
            }
            return;
        }
        if (!(declaration instanceof FirCodeFragment)) {
            f2f.a("Unexpected member: ", UtilsKt.render(declaration));
            return;
        }
        FirCodeFragment firCodeFragment = (FirCodeFragment) declaration;
        IrClass cachedIrCodeFragment = getClassifierStorage().getCachedIrCodeFragment(firCodeFragment);
        cachedIrCodeFragment.getClass();
        processCodeFragmentMembers(firCodeFragment, cachedIrCodeFragment);
        processMemberDeclaration$addDeclarationToParentIfNeeded(parent, cachedIrCodeFragment);
    }

    private static final void processMemberDeclaration$addDeclarationToParentIfNeeded(IrDeclarationParent irDeclarationParent, IrDeclaration irDeclaration) {
        if (irDeclarationParent instanceof IrFile) {
            ((IrFile) irDeclarationParent).getDeclarations().add(irDeclaration);
        } else if (irDeclarationParent instanceof IrClass) {
            ((IrClass) irDeclarationParent).getDeclarations().add(irDeclaration);
        }
    }

    private final void processNestedClassHeaders(FirClass klass) {
        for (FirDeclaration firDeclaration : klass.getDeclarations()) {
            if (firDeclaration instanceof FirRegularClass) {
                processClassAndNestedClassHeaders((FirClass) firDeclaration);
            }
        }
        if (!(klass instanceof FirRegularClass) || this.generatorExtensions.isEmpty()) {
            return;
        }
        for (FirClassLikeDeclaration firClassLikeDeclaration : GeneratedDeclarationsUtilsKt.generatedNestedClassifiers((FirRegularClass) klass, getSession())) {
            if (firClassLikeDeclaration instanceof FirRegularClass) {
                processClassAndNestedClassHeaders((FirClass) firClassLikeDeclaration);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processScriptLikeDeclaration(IrDeclarationParent parent, List<? extends FirDeclaration> declarations) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (obj instanceof FirRegularClass) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            registerClassAndNestedClasses((FirRegularClass) it.next(), parent);
        }
        for (FirDeclaration firDeclaration : declarations) {
            if (firDeclaration instanceof FirRegularClass) {
                processClassAndNestedClassHeaders((FirClass) firDeclaration);
            } else if (firDeclaration instanceof FirTypeAlias) {
                getClassifierStorage().createAndCacheIrTypeAlias((FirTypeAlias) firDeclaration, parent);
            }
        }
        for (FirDeclaration firDeclaration2 : declarations) {
            if (!(firDeclaration2 instanceof FirAnonymousInitializer)) {
                if (firDeclaration2 instanceof FirProperty) {
                    FirProperty firProperty = (FirProperty) firDeclaration2;
                    if ((firProperty.getSymbol() instanceof FirRegularPropertySymbol) && (!Intrinsics.areEqual(firProperty.getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR) || DestructuringDeclarationAttributesKt.getDestructuringDeclarationContainerVariable(firProperty) == null)) {
                        processMemberDeclaration(firDeclaration2, null, parent, null);
                    }
                } else if (firDeclaration2 instanceof FirClassLikeDeclaration) {
                    if (!((FirClassLikeDeclaration) firDeclaration2).getIsLocal()) {
                        processMemberDeclaration(firDeclaration2, null, parent, null);
                    }
                } else if (!(firDeclaration2 instanceof FirNamedFunction) || !Intrinsics.areEqual(((FirNamedFunction) firDeclaration2).getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
                    processMemberDeclaration(firDeclaration2, null, parent, null);
                }
            }
        }
    }

    private final IrClass registerClassAndNestedClasses(FirClass klass, IrDeclarationParent parent) {
        IrClass cachedIrLocalClass = getClassifierStorage().getCachedIrLocalClass(klass);
        if (cachedIrLocalClass != null) {
            cachedIrLocalClass.setParent(parent);
        } else if (klass instanceof FirRegularClass) {
            cachedIrLocalClass = Fir2IrClassifierStorage.createAndCacheIrClass$default(getClassifierStorage(), (FirRegularClass) klass, parent, null, 4, null);
        } else {
            if (!(klass instanceof FirAnonymousObject)) {
                bu8.a();
                return null;
            }
            cachedIrLocalClass = Fir2IrClassifierStorage.createAndCacheAnonymousObject$default(getClassifierStorage(), (FirAnonymousObject) klass, null, null, parent, 6, null);
        }
        registerNestedClasses(klass, cachedIrLocalClass);
        return cachedIrLocalClass;
    }

    private final void registerFileAndClasses(FirFile file, IrModuleFragment moduleFragment) {
        PsiIrFileEntry naiveSourceBasedFileEntryImpl;
        PsiIrFileEntry naiveSourceBasedFileEntryImpl2;
        String name;
        String name2;
        FirDeclarationOrigin origin = file.getOrigin();
        if (Intrinsics.areEqual(origin, FirDeclarationOrigin.Source.INSTANCE)) {
            KtFile psi = UtilsKt.getPsi(file);
            if (psi != null) {
                naiveSourceBasedFileEntryImpl = new PsiIrFileEntry(psi);
            } else {
                KtSourceFileLinesMappingFromLineStartOffsets sourceFileLinesMapping = file.getSourceFileLinesMapping();
                if (sourceFileLinesMapping instanceof KtSourceFileLinesMappingFromLineStartOffsets) {
                    KtSourceFile sourceFile = file.getSourceFile();
                    if (sourceFile == null || (name2 = sourceFile.getPath()) == null) {
                        KtSourceFile sourceFile2 = file.getSourceFile();
                        name2 = sourceFile2 != null ? sourceFile2.getName() : file.getName();
                    }
                    KtSourceFileLinesMappingFromLineStartOffsets ktSourceFileLinesMappingFromLineStartOffsets = sourceFileLinesMapping;
                    naiveSourceBasedFileEntryImpl2 = new NaiveSourceBasedFileEntryImpl(name2, ktSourceFileLinesMappingFromLineStartOffsets.getLineStartOffsets(), ktSourceFileLinesMappingFromLineStartOffsets.getLastOffset(), 0, 8, (DefaultConstructorMarker) null);
                } else if (sourceFileLinesMapping instanceof KtPsiSourceFileLinesMapping) {
                    naiveSourceBasedFileEntryImpl = new PsiIrFileEntry(((KtPsiSourceFileLinesMapping) sourceFileLinesMapping).getPsiFile());
                } else {
                    KtSourceFile sourceFile3 = file.getSourceFile();
                    if (sourceFile3 == null || (name = sourceFile3.getPath()) == null) {
                        KtSourceFile sourceFile4 = file.getSourceFile();
                        name = sourceFile4 != null ? sourceFile4.getName() : file.getName();
                    }
                    naiveSourceBasedFileEntryImpl2 = new NaiveSourceBasedFileEntryImpl(name, (int[]) null, 0, 0, 14, (DefaultConstructorMarker) null);
                }
                naiveSourceBasedFileEntryImpl = naiveSourceBasedFileEntryImpl2;
            }
        } else {
            if (!(origin instanceof FirDeclarationOrigin.Synthetic)) {
                StringBuilder sb = new StringBuilder("Unsupported file origin ");
                sb.append(file.getOrigin());
                ej7.a(sb, "; file: ", file.getName());
                return;
            }
            naiveSourceBasedFileEntryImpl = new NaiveSourceBasedFileEntryImpl(file.getName(), (int[]) null, 0, 0, 14, (DefaultConstructorMarker) null);
        }
        IrFileImpl IrFileImpl = IrFileImplKt.IrFileImpl(naiveSourceBasedFileEntryImpl, (PackageFragmentDescriptor) CollectionsKt.first(this.moduleDescriptor.getPackage(UtilsKt.getPackageFqName(file)).getFragments()), moduleFragment);
        if (file.getOrigin() instanceof FirDeclarationOrigin.Synthetic.PluginFile) {
            CommonIrAttributesKt.setFileForTopLevelPluginDeclarations(IrFileImpl, true);
        }
        getDeclarationStorage().registerFile(file, IrFileImpl);
        for (FirDeclaration firDeclaration : file.getDeclarations()) {
            if (firDeclaration instanceof FirRegularClass) {
                registerClassAndNestedClasses((FirClass) firDeclaration, IrFileImpl);
            } else if (firDeclaration instanceof FirReplSnippet) {
                registerClassAndNestedClasses(((FirReplSnippet) firDeclaration).getSnippetClass(), IrFileImpl);
            } else if (firDeclaration instanceof FirCodeFragment) {
                getClassifierStorage().createAndCacheCodeFragmentClass((FirCodeFragment) firDeclaration, IrFileImpl);
            } else {
                Unit unit = Unit.INSTANCE;
            }
        }
        moduleFragment.getFiles().add(IrFileImpl);
    }

    private final void registerNestedClasses(FirClass klass, IrClass irClass) {
        for (FirDeclaration firDeclaration : klass.getDeclarations()) {
            if (firDeclaration instanceof FirRegularClass) {
                registerClassAndNestedClasses((FirClass) firDeclaration, irClass);
            }
        }
        if (!(klass instanceof FirRegularClass) || this.generatorExtensions.isEmpty()) {
            return;
        }
        for (FirClassLikeDeclaration firClassLikeDeclaration : GeneratedDeclarationsUtilsKt.generatedNestedClassifiers((FirRegularClass) klass, getSession())) {
            if (firClassLikeDeclaration instanceof FirRegularClass) {
                registerClassAndNestedClasses((FirClass) firClassLikeDeclaration, irClass);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void runSourcesConversion(List<? extends FirFile> allFirFiles, IrModuleFragmentImpl irModuleFragment, Fir2IrVisitor fir2irVisitor) throws KotlinIllegalArgumentExceptionWithAttachments {
        Iterator<? extends FirFile> it = allFirFiles.iterator();
        while (it.hasNext()) {
            registerFileAndClasses(it.next(), irModuleFragment);
        }
        Iterator<? extends FirFile> it2 = allFirFiles.iterator();
        while (it2.hasNext()) {
            processClassHeaders(it2.next());
        }
        Iterator<? extends FirFile> it3 = allFirFiles.iterator();
        while (it3.hasNext()) {
            processFileAndClassMembers(it3.next());
        }
        getClassifierStorage().processMembersOfClassesCreatedOnTheFly();
        for (FirFile firFile : allFirFiles) {
            try {
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(firFile.getModuleData().getSession()).handleExceptionOnFileAnalysis(firFile, th);
                wq6.a();
                return;
            }
        }
        if (getConfiguration().getAllowNonCachedDeclarations()) {
            getDeclarationStorage().fillUnboundSymbols$org_jetbrains_kotlin_fir2ir();
        }
    }

    private final Iterable<FirDeclaration> syntheticPropertiesLast(Iterable<? extends FirDeclaration> declarations) {
        return CollectionsKt.sortedWith(declarations, new Comparator() { // from class: org.jetbrains.kotlin.fir.backend.Fir2IrConverter$syntheticPropertiesLast$$inlined$sortedBy$1
            /* JADX WARN: Code duplicated, block: B:15:0x002f  */
            /* JADX WARN: Code duplicated, block: B:4:0x000d  */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                boolean z;
                boolean z2;
                FirDeclaration firDeclaration = (FirDeclaration) t;
                if (firDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic) {
                    KtSourceElement source = firDeclaration.getSource();
                    if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.ClassDelegationField) {
                        z = false;
                    } else {
                        z = true;
                    }
                } else {
                    z = false;
                }
                Boolean boolValueOf = Boolean.valueOf(z);
                FirDeclaration firDeclaration2 = (FirDeclaration) t2;
                if (firDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic) {
                    KtSourceElement source2 = firDeclaration2.getSource();
                    z2 = (source2 != null ? source2.getKind() : null) instanceof KtFakeSourceElementKind.ClassDelegationField ? false : true;
                }
                return ComparisonsKt.compareValues(boolValueOf, Boolean.valueOf(z2));
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.c.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.c.getAnnotationGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.c.getBuiltins();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.c.getCallGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.c.getCallablesGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.c.getClassifierStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.c.getClassifiersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.c.getConfiguration();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.c.getConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.c.getDataClassMembersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.c.getDeclarationStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.c.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.c.getFilesBeingCompiled();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.c.getFirProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.c.getImplicitCastInserter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.c.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.c.getIrProviders();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.c.getLazyDeclarationsGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.c.getLazyFakeOverrideGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.c.getLock();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.c.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.c.getSymbolsMappingForLazyClasses();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.c.getTypeConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    public final void processAnonymousObjectHeaders(FirAnonymousObject anonymousObject, IrClass irClass) {
        anonymousObject.getClass();
        irClass.getClass();
        registerNestedClasses(anonymousObject, irClass);
        processNestedClassHeaders(anonymousObject);
        irClass.getDeclarations().clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrClass processClassMembers$org_jetbrains_kotlin_fir2ir(final FirClass klass, final IrClass irClass) {
        klass.getClass();
        irClass.getClass();
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(klass.getDeclarations());
        boolean z = klass instanceof FirRegularClass;
        if (z && !this.generatorExtensions.isEmpty()) {
            FirRegularClass firRegularClass = (FirRegularClass) klass;
            arrayList.addAll(GeneratedDeclarationsUtilsKt.generatedMembers(firRegularClass, getSession()));
            arrayList.addAll(GeneratedDeclarationsUtilsKt.generatedNestedClassifiers(firRegularClass, getSession()));
        }
        if (JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(FirDeclarationUtilKt.getClassId(klass).asSingleFqName().toUnsafe()) != null) {
            FirContainingNamesAwareScopeKt.processAllFunctions(ScopeUtilsKt.unsubstitutedScope(this, klass), new Function1() { // from class: dv4
                public final Object invoke(Object obj) {
                    return Fir2IrConverter.processClassMembers$lambda$0$0(klass, arrayList, (FirNamedFunctionSymbol) obj);
                }
            });
        }
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(klass, getSession());
        IrConstructor irConstructorCreateAndCacheIrConstructor$default = firConstructorSymbolPrimaryConstructorIfAny != null ? Fir2IrDeclarationStorage.createAndCacheIrConstructor$default(getDeclarationStorage(), (FirConstructor) firConstructorSymbolPrimaryConstructorIfAny.getFir(), new Function0() { // from class: ev4
            public final Object invoke() {
                return Fir2IrConverter.processClassMembers$lambda$1$0(irClass);
            }
        }, null, klass.getIsLocal(), 4, null) : null;
        MultiMap<FirProperty, FirField> multiMap = new MultiMap<>();
        Iterator<FirDeclaration> it = syntheticPropertiesLast(arrayList).iterator();
        while (it.hasNext()) {
            processMemberDeclaration(it.next(), klass, irClass, multiMap);
        }
        CollectionsKt.addAll(arrayList, delegatedMembers(irClass));
        if (z && irConstructorCreateAndCacheIrConstructor$default != null && (irClass.isValue() || irClass.isData())) {
            getDeclarationStorage().enterScope(irConstructorCreateAndCacheIrConstructor$default.getSymbol());
            if (IrDeclarationsKt.isSingleFieldValueClass(irClass)) {
                CollectionsKt.addAll(arrayList, getDataClassMembersGenerator().generateSingleFieldValueClassMembers((FirRegularClass) klass, irClass));
            }
            if (IrDeclarationsKt.isMultiFieldValueClass(irClass)) {
                CollectionsKt.addAll(arrayList, getDataClassMembersGenerator().generateMultiFieldValueClassMembers((FirRegularClass) klass, irClass));
            }
            if (irClass.isData()) {
                CollectionsKt.addAll(arrayList, getDataClassMembersGenerator().generateDataClassMembers((FirRegularClass) klass, irClass));
            }
            getDeclarationStorage().leaveScope(irConstructorCreateAndCacheIrConstructor$default.getSymbol());
        }
        return irClass;
    }

    public final IrClass processLocalClassAndNestedClasses(FirClass klass, IrDeclarationParent parent) {
        klass.getClass();
        parent.getClass();
        IrClass irClassRegisterClassAndNestedClasses = registerClassAndNestedClasses(klass, parent);
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        fir2IrConversionScope.getContainingFirClassStack().add(klass);
        try {
            processClassAndNestedClassHeaders(klass);
            processClassMembers$org_jetbrains_kotlin_fir2ir(klass, irClassRegisterClassAndNestedClasses);
            return irClassRegisterClassAndNestedClasses;
        } finally {
            fir2IrConversionScope.getContainingFirClassStack().remove(fir2IrConversionScope.getContainingFirClassStack().size() - 1);
        }
    }

    public final IrClass processLocalClassAndNestedClassesOnTheFly(FirClass klass, IrDeclarationParent parent) {
        klass.getClass();
        parent.getClass();
        IrClass irClassRegisterClassAndNestedClasses = registerClassAndNestedClasses(klass, parent);
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        fir2IrConversionScope.getContainingFirClassStack().add(klass);
        try {
            processClassAndNestedClassHeaders(klass);
            return irClassRegisterClassAndNestedClasses;
        } finally {
            fir2IrConversionScope.getContainingFirClassStack().remove(fir2IrConversionScope.getContainingFirClassStack().size() - 1);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter$Companion;", Argument.Delimiters.none, "<init>", "()V", "generateIrModuleFragment", "Lorg/jetbrains/kotlin/ir/declarations/impl/IrModuleFragmentImpl;", "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponentsStorage;", "firFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public final IrModuleFragmentImpl generateIrModuleFragment(Fir2IrComponentsStorage components, List<? extends FirFile> firFiles) throws KotlinIllegalArgumentExceptionWithAttachments {
            components.getClass();
            firFiles.getClass();
            FirSession session = components.getSession();
            FirLazyDeclarationResolverKt.getLazyDeclarationResolver(session).disableLazyResolveContractChecks();
            IrModuleFragmentImpl irModuleFragmentImpl = new IrModuleFragmentImpl(components.getModuleDescriptor());
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            listCreateListBuilder.addAll(IrElementsCreationUtilsKt.createFilesWithBuiltinsSyntheticDeclarationsIfNeeded(session));
            listCreateListBuilder.addAll(firFiles);
            List listBuild = CollectionsKt.build(listCreateListBuilder);
            for (FirFile firFile : firFiles) {
                if (Intrinsics.areEqual(firFile.getOrigin(), FirDeclarationOrigin.Synthetic.PluginFile.INSTANCE)) {
                    components.getFirProvider().recordFile(firFile);
                }
            }
            components.getConverter().runSourcesConversion(listBuild, irModuleFragmentImpl, components.getFir2IrVisitor());
            return irModuleFragmentImpl;
        }

        private Companion() {
        }
    }
}
