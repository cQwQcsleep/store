package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeMetadata;
import androidx.compose.compiler.plugins.kotlin.ComposeNames;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.lower.ComposerParamTransformer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.jvm.ir.JvmIrTypeUtilsKt;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.builders.declarations.DeclarationBuildersKt;
import org.jetbrains.kotlin.ir.builders.declarations.IrFunctionBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrValueParameterBuilder;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationContainer;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrBody;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrExpressionsKt;
import org.jetbrains.kotlin.ir.expressions.IrFunctionReference;
import org.jetbrains.kotlin.ir.expressions.IrLocalDelegatedPropertyReference;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrPropertyReference;
import org.jetbrains.kotlin.ir.expressions.IrRichFunctionReference;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCompositeImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrFunctionReferenceImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeParameterSymbol;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.SimpleTypeNullability;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DeepCopySymbolRemapper;
import org.jetbrains.kotlin.ir.util.DeepCopyTypeRemapper;
import org.jetbrains.kotlin.ir.util.DescriptorsRemapper;
import org.jetbrains.kotlin.ir.util.DumpIrTreeKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.InlineClassesKt;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.PatchDeclarationParentsKt;
import org.jetbrains.kotlin.ir.util.SymbolRemapper;
import org.jetbrains.kotlin.ir.util.TypeRemapper;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.jvm.JvmPlatformKt;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0015H\u0016J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020\"2\u0006\u0010#\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\"2\u0006\u0010#\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\"2\u0006\u0010#\u001a\u00020,H\u0016J\u000e\u0010-\u001a\u0004\u0018\u00010.*\u00020/H\u0002J\u0018\u00100\u001a\u00020\"2\u0006\u0010#\u001a\u00020*2\u0006\u00101\u001a\u00020\u0015H\u0002J \u00102\u001a\u00020\"2\u0006\u0010#\u001a\u00020*2\u0006\u00101\u001a\u00020\u00152\u0006\u00103\u001a\u000204H\u0002J\u0010\u00105\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u000206H\u0016J\b\u00107\u001a\u000208H\u0002J\u0012\u00109\u001a\u00020.*\u00020.2\u0006\u0010:\u001a\u00020;J\u0012\u0010<\u001a\u0004\u0018\u00010\"2\u0006\u0010=\u001a\u00020;H\u0002J\"\u0010>\u001a\u0004\u0018\u00010\"*\u00020\u00192\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010A\u001a\u00020@H\u0002J\f\u00109\u001a\u00020\u0015*\u00020\u0015H\u0002J\f\u0010B\u001a\u00020\u0015*\u00020\u0015H\u0002J\u0010\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0002J\f\u0010G\u001a\u000204*\u00020\u0015H\u0002J\f\u0010H\u001a\u000204*\u00020\u0015H\u0002J\u0014\u0010I\u001a\u000204*\u00020\u00152\u0006\u0010J\u001a\u00020@H\u0002JE\u0010K\u001a\u0002HL\"\n\b\u0000\u0010L\u0018\u0001*\u00020M*\u0002HL2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u001b2\u0014\b\u0002\u0010O\u001a\u000e\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020R0PH\u0080\bø\u0001\u0000¢\u0006\u0004\bS\u0010TJ(\u0010U\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020Y\u0018\u00010W0V*\u00020\u00152\u0006\u0010Z\u001a\u00020\u0015H\u0002J\f\u0010[\u001a\u00020\u0015*\u00020\u0015H\u0002J\u000e\u0010\\\u001a\u0004\u0018\u00010\u0015*\u00020\u0015H\u0002J\f\u0010]\u001a\u000204*\u00020\u0019H\u0002J\n\u0010^\u001a\u000204*\u00020\u0019J'\u0010_\u001a\u0004\u0018\u00010\u0015*\u00020\u00152\u0017\u0010`\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u0002040P¢\u0006\u0002\baH\u0002J\u0012\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00150V*\u00020\u0015H\u0002J\f\u0010c\u001a\u000204*\u00020\u0015H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006d"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposerParamTransformer;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "Landroidx/compose/compiler/plugins/kotlin/lower/ModuleLoweringPass;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "inlineLambdaInfo", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposeInlineLambdaLocator;", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "transformedFunctions", "", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "transformedFunctionSet", "", "composerType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "currentParent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "visitDeclaration", "Lorg/jetbrains/kotlin/ir/IrStatement;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationBase;", "visitSimpleFunction", "visitLocalDelegatedPropertyReference", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrLocalDelegatedPropertyReference;", "visitPropertyReference", "Lorg/jetbrains/kotlin/ir/expressions/IrPropertyReference;", "visitBlock", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "visitFunctionReference", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionReference;", "visitRichFunctionReference", "Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;", "findCallInBody", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "transformComposableFunctionReference", "fn", "adaptComposableReference", "useAdaptedOrigin", "", "visitLocalDelegatedProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrLocalDelegatedProperty;", "createComposableAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrAnnotationImpl;", "withComposerParamIfNeeded", "composerParam", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "defaultArgumentFor", "param", "defaultValue", "startOffset", "", "endOffset", "lambdaInvokeWithComposerParam", "jvmNameAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "name", "", "requiresDefaultParameter", "isLegacyOpenFunctionWithDefault", "hasDefaultForParam", "index", "deepCopyWithSymbolsAndMetadata", "T", "Lorg/jetbrains/kotlin/ir/IrElement;", "initialParent", "createTypeRemapper", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/util/SymbolRemapper;", "Lorg/jetbrains/kotlin/ir/util/TypeRemapper;", "deepCopyWithSymbolsAndMetadata$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "(Lorg/jetbrains/kotlin/ir/IrElement;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/ir/IrElement;", "toComparableParams", "", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "Lorg/jetbrains/kotlin/ir/types/SimpleTypeNullability;", "referenceFn", "copyWithComposerParam", "makeValueClassNonPrimitiveStub", "isPrimaryConstructorPrivate", "constructorVisibilityIsAtLeastAsAccessibleAsType", "makeValueClassInaccessibleConstructorDefaultStub", "visibilityCheck", "Lkotlin/ExtensionFunctionType;", "makeStubsForDefaultValueClassIfNeeded", "isPublicComposableFunction", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposerParamTransformer extends AbstractComposeLowering implements ModuleLoweringPass {
    private final IrType composerType;
    private IrDeclarationParent currentParent;
    private ComposeInlineLambdaLocator inlineLambdaInfo;
    private final Set<IrSimpleFunction> transformedFunctionSet;
    private final Map<IrSimpleFunction, IrSimpleFunction> transformedFunctions;

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IrParameterKind.values().length];
            try {
                iArr[IrParameterKind.Context.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[IrParameterKind.DispatchReceiver.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[IrParameterKind.ExtensionReceiver.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[IrParameterKind.Regular.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerParamTransformer(IrPluginContext irPluginContext, StabilityInferencer stabilityInferencer, ModuleMetrics moduleMetrics, FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        irPluginContext.getClass();
        stabilityInferencer.getClass();
        moduleMetrics.getClass();
        featureFlags.getClass();
        this.inlineLambdaInfo = new ComposeInlineLambdaLocator(irPluginContext);
        this.transformedFunctions = new LinkedHashMap();
        this.transformedFunctionSet = new LinkedHashSet();
        this.composerType = replaceArgumentsWithStarProjections(IrUtilsKt.getDefaultType(getComposerIrClass()));
    }

    private final IrExpression adaptComposableReference(IrFunctionReference expression, final IrSimpleFunction fn, boolean useAdaptedOrigin) {
        int i;
        Object next;
        IrValueParameter irValueParameter;
        IrType type = expression.getType();
        IrStatementOriginImpl adapted_function_reference = useAdaptedOrigin ? IrStatementOrigin.Companion.getADAPTED_FUNCTION_REFERENCE() : null;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        IrDeclarationParent irDeclarationParent = this.currentParent;
        if (irDeclarationParent == null) {
            f2f.a("No parent found for ", DumpIrTreeKt.dump$default(expression, (DumpIrTreeOptions) null, 1, (Object) null));
            return null;
        }
        IrFactory irFactory = getContext().getIrFactory();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        irFunctionBuilder.setOrigin(useAdaptedOrigin ? IrDeclarationOrigin.Companion.getADAPTER_FOR_CALLABLE_REFERENCE() : irFunctionBuilder.getOrigin());
        irFunctionBuilder.setName(fn.getName());
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.LOCAL;
        descriptorVisibility.getClass();
        irFunctionBuilder.setVisibility(descriptorVisibility);
        irFunctionBuilder.setModality(Modality.FINAL);
        irFunctionBuilder.setReturnType(fn.getReturnType());
        final IrSimpleFunction irSimpleFunctionBuildFunction = DeclarationBuildersKt.buildFunction(irFactory, irFunctionBuilder);
        IrUtilsKt.copyAnnotationsFrom(irSimpleFunctionBuildFunction, fn);
        copyParametersFrom$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(irSimpleFunctionBuildFunction, fn, false);
        List<IrValueParameter> parameters = irSimpleFunctionBuildFunction.getParameters();
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            i = 0;
        } else {
            i = 0;
            for (IrValueParameter irValueParameter2 : parameters) {
                if (irValueParameter2.getKind() == IrParameterKind.ExtensionReceiver || irValueParameter2.getKind() == IrParameterKind.DispatchReceiver) {
                    i++;
                    if (i < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
        }
        if (i > 1) {
            dt1.a("Function references are not allowed to have multiple receivers: ", DumpIrTreeKt.dump$default(expression, (DumpIrTreeOptions) null, 1, (Object) null));
            return null;
        }
        List listCreateListBuilder2 = CollectionsKt.createListBuilder();
        Iterator it = irSimpleFunctionBuildFunction.getParameters().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            irValueParameter = (IrValueParameter) next;
            if (irValueParameter.getKind() == IrParameterKind.DispatchReceiver) {
                break;
            }
        } while (irValueParameter.getKind() != IrParameterKind.ExtensionReceiver);
        IrValueParameter irValueParameter3 = (IrValueParameter) next;
        if (irValueParameter3 != null) {
            irValueParameter3.setKind(IrParameterKind.ExtensionReceiver);
            Name nameIdentifier = Name.identifier("receiver");
            nameIdentifier.getClass();
            irValueParameter3.setName(nameIdentifier);
            listCreateListBuilder2.add(irValueParameter3);
        }
        IrSimpleType type2 = expression.getType();
        type2.getClass();
        int size = type2.getArguments().size() - 1;
        List parameters2 = irSimpleFunctionBuildFunction.getParameters();
        int size2 = parameters2.size();
        int i2 = size;
        for (int i3 = 0; i3 < size2; i3++) {
            IrValueParameter irValueParameter4 = (IrValueParameter) parameters2.get(i3);
            if (irValueParameter4.getKind() == IrParameterKind.Regular) {
                int i4 = i2 - 1;
                if (i2 > 0) {
                    listCreateListBuilder2.add(irValueParameter4);
                }
                i2 = i4;
            }
        }
        irSimpleFunctionBuildFunction.setParameters(CollectionsKt.build(listCreateListBuilder2));
        ComposePluginAttributesKt.setComposableReferenceAdapter(irSimpleFunctionBuildFunction, true);
        irSimpleFunctionBuildFunction.setBody(IrFactoryHelpersKt.createBlockBody(getContext().getIrFactory(), -2, -2, new Function1() { // from class: qn2
            public final Object invoke(Object obj) {
                return ComposerParamTransformer.adaptComposableReference$lambda$0$4(this.b, irSimpleFunctionBuildFunction, fn, (IrBlockBody) obj);
            }
        }));
        irSimpleFunctionBuildFunction.setParent(irDeclarationParent);
        listCreateListBuilder.add(irSimpleFunctionBuildFunction);
        IrFunctionReferenceImpl IrFunctionReferenceImpl = BuildersKt.IrFunctionReferenceImpl(expression.getStartOffset(), expression.getEndOffset(), expression.getType(), irSimpleFunctionBuildFunction.getSymbol(), expression.getTypeArguments().size(), fn.getSymbol(), useAdaptedOrigin ? IrStatementOrigin.Companion.getADAPTED_FUNCTION_REFERENCE() : null);
        AddToStdlibKt.assignFrom(IrFunctionReferenceImpl.getTypeArguments(), expression.getTypeArguments());
        AddToStdlibKt.assignFrom(IrFunctionReferenceImpl.getArguments(), expression.getArguments());
        listCreateListBuilder.add(IrFunctionReferenceImpl);
        Unit unit = Unit.INSTANCE;
        return visitBlock(AbstractComposeLowering.irBlock$default(this, type, adapted_function_reference, 0, 0, CollectionsKt.build(listCreateListBuilder), 12, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit adaptComposableReference$lambda$0$4(ComposerParamTransformer composerParamTransformer, IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2, IrBlockBody irBlockBody) {
        IrValueParameter irValueParameter;
        IrValueParameter irValueParameter2;
        irBlockBody.getClass();
        List statements = irBlockBody.getStatements();
        IrSimpleFunctionSymbol symbol = irSimpleFunction.getSymbol();
        IrCallImpl irCallImplIrCall = composerParamTransformer.irCall(irSimpleFunction2.getSymbol());
        List parameters = irSimpleFunction2.getParameters();
        int size = parameters.size();
        for (int i = 0; i < size; i++) {
            IrValueParameter irValueParameter3 = (IrValueParameter) parameters.get(i);
            IrMemberAccessExpression.ValueArgumentsList arguments = irCallImplIrCall.getArguments();
            int indexInParameters = irValueParameter3.getIndexInParameters();
            int i2 = WhenMappings.$EnumSwitchMapping$0[irValueParameter3.getKind().ordinal()];
            IrExpression irExpressionIrGet = null;
            if (i2 == 1) {
                k2d.a("Context parameters are not supported in function references");
                return null;
            }
            if (i2 == 2 || i2 == 3) {
                Iterator it = irSimpleFunction.getParameters().iterator();
                do {
                    if (!it.hasNext()) {
                        hb9.a("Collection contains no element matching the predicate.");
                        return null;
                    }
                    irValueParameter = (IrValueParameter) it.next();
                } while (irValueParameter.getKind() != IrParameterKind.ExtensionReceiver);
                irValueParameter2 = irValueParameter;
            } else {
                if (i2 != 4) {
                    bu8.a();
                    return null;
                }
                irValueParameter2 = (IrValueParameter) CollectionsKt.getOrNull(irSimpleFunction.getParameters(), irValueParameter3.getIndexInParameters());
            }
            if (irValueParameter2 != null) {
                irExpressionIrGet = composerParamTransformer.irGet(irValueParameter2);
            }
            arguments.set(indexInParameters, irExpressionIrGet);
        }
        Unit unit = Unit.INSTANCE;
        statements.add(AbstractComposeLowering.irReturn$default(composerParamTransformer, symbol, irCallImplIrCall, null, 4, null));
        return Unit.INSTANCE;
    }

    private final IrSimpleFunction copyWithComposerParam(IrSimpleFunction irSimpleFunction) {
        int i;
        IrValueParameter irValueParameter = (IrValueParameter) CollectionsKt.lastOrNull(irSimpleFunction.getParameters());
        Intrinsics.areEqual(irValueParameter != null ? irValueParameter.getName() : null, ComposeNames.INSTANCE.getComposerParameter());
        IrDeclarationParent parent = irSimpleFunction.getParent();
        DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
        IrVisitorsKt.acceptVoid(irSimpleFunction, deepCopySymbolRemapper);
        IrSimpleFunction irSimpleFunctionTransform = irSimpleFunction.transform(new DeepCopyPreservingMetadata(deepCopySymbolRemapper, new DeepCopyTypeRemapper(deepCopySymbolRemapper)), (Object) null);
        if (irSimpleFunctionTransform == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrSimpleFunction");
            return null;
        }
        IrElement irElement = (IrSimpleFunction) PatchDeclarationParentsKt.patchDeclarationParents(irSimpleFunctionTransform, parent);
        this.transformedFunctionSet.add(irElement);
        this.transformedFunctions.put(irSimpleFunction, irElement);
        irElement.setMetadata(irSimpleFunction.getMetadata());
        List overriddenSymbols = irSimpleFunction.getOverriddenSymbols();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenSymbols, 10));
        Iterator it = overriddenSymbols.iterator();
        while (it.hasNext()) {
            arrayList.add(withComposerParamIfNeeded((IrSimpleFunction) ((IrSimpleFunctionSymbol) it.next()).getOwner()).getSymbol());
        }
        irElement.setOverriddenSymbols(arrayList);
        IrPropertySymbol correspondingPropertySymbol = irSimpleFunction.getCorrespondingPropertySymbol();
        if (correspondingPropertySymbol != null) {
            irElement.setCorrespondingPropertySymbol(correspondingPropertySymbol);
            if (Intrinsics.areEqual(correspondingPropertySymbol.getOwner().getGetter(), irSimpleFunction)) {
                correspondingPropertySymbol.getOwner().setGetter(irElement);
            }
            if (Intrinsics.areEqual(correspondingPropertySymbol.getOwner().getSetter(), irSimpleFunction)) {
                correspondingPropertySymbol.getOwner().setSetter(irElement);
            }
        }
        IrPropertySymbol correspondingPropertySymbol2 = irElement.getCorrespondingPropertySymbol();
        if (correspondingPropertySymbol2 != null) {
            FqName fqName = DescriptorUtils.JVM_NAME;
            fqName.getClass();
            if (!IrUtilsKt.hasAnnotation(irElement, fqName)) {
                String identifier = correspondingPropertySymbol2.getOwner().getName().getIdentifier();
                identifier.getClass();
                irElement.setAnnotations(CollectionsKt.plus(irElement.getAnnotations(), jvmNameAnnotation(AdditionalIrUtilsKt.isGetter(irElement) ? JvmAbi.getterName(identifier) : JvmAbi.setterName(identifier))));
            }
        }
        List parameters = irElement.getParameters();
        int size = parameters.size();
        for (int i2 = 0; i2 < size; i2++) {
            IrValueParameter irValueParameter2 = (IrValueParameter) parameters.get(i2);
            if (irValueParameter2.getKind() == IrParameterKind.Regular || irValueParameter2.getKind() == IrParameterKind.Context) {
                irValueParameter2.setName(dexSafeName(irValueParameter2.getName()));
            }
            irValueParameter2.setAssignable(irValueParameter2.getDefaultValue() != null);
        }
        List parameters2 = irElement.getParameters();
        if ((parameters2 instanceof Collection) && parameters2.isEmpty()) {
            i = 0;
        } else {
            Iterator it2 = parameters2.iterator();
            i = 0;
            while (it2.hasNext()) {
                if (((IrValueParameter) it2.next()).getKind() == IrParameterKind.Regular && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        IrValueParameterBuilder irValueParameterBuilder = new IrValueParameterBuilder();
        ComposeNames composeNames = ComposeNames.INSTANCE;
        irValueParameterBuilder.setName(composeNames.getComposerParameter());
        irValueParameterBuilder.setType(IrTypesKt.makeNullable(this.composerType));
        irValueParameterBuilder.setOrigin(IrDeclarationOrigin.Companion.getDEFINED());
        irValueParameterBuilder.setAssignable(true);
        irValueParameterBuilder.setKind(IrParameterKind.Regular);
        final IrValueParameter irValueParameterBuildValueParameter = DeclarationBuildersKt.buildValueParameter(irElement.getFactory(), irValueParameterBuilder, irElement);
        irElement.setParameters(CollectionsKt.plus(irElement.getParameters(), irValueParameterBuildValueParameter));
        String identifier2 = composeNames.getChangedParameter().getIdentifier();
        identifier2.getClass();
        int iChangedParamCount = ComposableFunctionBodyTransformerKt.changedParamCount(i, ComposableFunctionBodyTransformerKt.getThisParamCount(irElement));
        for (int i3 = 0; i3 < iChangedParamCount; i3++) {
            DeclarationBuildersKt.addValueParameter$default(irElement, i3 == 0 ? identifier2 : identifier2 + i3, getContext().getIrBuiltIns().getIntType(), (IrDeclarationOrigin) null, 4, (Object) null);
        }
        if (requiresDefaultParameter(irElement)) {
            String identifier3 = ComposeNames.INSTANCE.getDefaultParameter().getIdentifier();
            identifier3.getClass();
            int iDefaultParamCount = ComposableFunctionBodyTransformerKt.defaultParamCount(i);
            for (int i4 = 0; i4 < iDefaultParamCount; i4++) {
                DeclarationBuildersKt.addValueParameter(irElement, i4 == 0 ? identifier3 : identifier3 + i4, getContext().getIrBuiltIns().getIntType(), IrDeclarationOrigin.Companion.getMASK_FOR_DEFAULT_FUNCTION());
            }
        }
        List<IrSimpleFunction> listMakeStubsForDefaultValueClassIfNeeded = makeStubsForDefaultValueClassIfNeeded(irElement);
        List parameters3 = irElement.getParameters();
        int size2 = parameters3.size();
        for (int i5 = 0; i5 < size2; i5++) {
            IrValueParameter irValueParameter3 = (IrValueParameter) parameters3.get(i5);
            if (hasDefaultForParam(irElement, irValueParameter3.getIndexInParameters())) {
                irValueParameter3.setType(defaultParameterType(irValueParameter3.getType()));
            }
        }
        IrDeclarationContainer parent2 = irElement.getParent();
        if ((parent2 instanceof IrClass) || (parent2 instanceof IrFile)) {
            Set setMutableSetOf = SetsKt.mutableSetOf(new List[]{toComparableParams(irElement, irElement)});
            for (IrSimpleFunction irSimpleFunction2 : listMakeStubsForDefaultValueClassIfNeeded) {
                if (setMutableSetOf.add(toComparableParams(irSimpleFunction2, irElement))) {
                    IrUtilsKt.addChild(parent2, irSimpleFunction2);
                }
            }
        }
        this.inlineLambdaInfo.scan(irElement);
        IrElementTransformerVoidKt.transformChildrenVoid(irElement, new IrElementTransformerVoid() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposerParamTransformer$copyWithComposerParam$2$6
            private boolean isNestedScope;

            /* JADX INFO: renamed from: isNestedScope, reason: from getter */
            public final boolean getIsNestedScope() {
                return this.isNestedScope;
            }

            public final void setNestedScope(boolean z) {
                this.isNestedScope = z;
            }

            public IrExpression visitCall(IrCall expression) {
                expression.getClass();
                if (!this.isNestedScope) {
                    expression = this.this$0.withComposerParamIfNeeded(expression, irValueParameterBuildValueParameter);
                }
                return super.visitCall(expression);
            }

            /* JADX WARN: Code duplicated, block: B:12:0x0020  */
            public IrStatement visitFunction(IrFunction declaration) {
                boolean z;
                declaration.getClass();
                boolean z2 = this.isNestedScope;
                if (z2) {
                    z = true;
                } else {
                    try {
                        if (!this.this$0.inlineLambdaInfo.isInlineLambda(declaration) || this.this$0.hasComposableAnnotation(declaration)) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } finally {
                        this.isNestedScope = z2;
                    }
                }
                this.isNestedScope = z;
                return super.visitFunction(declaration);
            }
        });
        return irElement;
    }

    private final IrAnnotationImpl createComposableAnnotation() {
        IrSimpleType defaultType = IrUtilsKt.getDefaultType(getComposableIrClass());
        IrConstructor primaryConstructor = IrUtilsKt.getPrimaryConstructor(getComposableIrClass());
        primaryConstructor.getClass();
        return BuildersKt.IrAnnotationImpl$default(-2, -2, defaultType, primaryConstructor.getSymbol(), 0, 0, (IrStatementOrigin) null, (SourceElement) null, 192, (Object) null);
    }

    public static /* synthetic */ IrElement deepCopyWithSymbolsAndMetadata$org_jetbrains_kotlin_kotlin_compose_compiler_plugin$default(ComposerParamTransformer composerParamTransformer, IrElement irElement, IrDeclarationParent irDeclarationParent, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            irDeclarationParent = null;
        }
        if ((i & 2) != 0) {
            function1 = ComposerParamTransformer$deepCopyWithSymbolsAndMetadata$1.INSTANCE;
        }
        irElement.getClass();
        function1.getClass();
        DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
        IrVisitorsKt.acceptVoid(irElement, deepCopySymbolRemapper);
        IrElement irElementTransform = irElement.transform(new DeepCopyPreservingMetadata(deepCopySymbolRemapper, (TypeRemapper) function1.invoke(deepCopySymbolRemapper)), (Object) null);
        Intrinsics.reifiedOperationMarker(1, "T");
        return PatchDeclarationParentsKt.patchDeclarationParents(irElementTransform, irDeclarationParent);
    }

    private final IrExpression defaultArgumentFor(IrValueParameter param) {
        IrExpression irExpressionDefaultValue$default = defaultValue$default(this, param.getType(), 0, 0, 3, null);
        if (irExpressionDefaultValue$default == null) {
            IrExpressionBody defaultValue = param.getDefaultValue();
            irExpressionDefaultValue$default = defaultValue != null ? defaultValue.getExpression() : null;
        }
        if (irExpressionDefaultValue$default != null) {
            return new IrCompositeImpl(irExpressionDefaultValue$default.getStartOffset(), irExpressionDefaultValue$default.getEndOffset(), irExpressionDefaultValue$default.getType(), IrStatementOrigin.Companion.getDEFAULT_VALUE(), CollectionsKt.listOf(irExpressionDefaultValue$default));
        }
        return null;
    }

    private final IrExpression defaultValue(IrType irType, int i, int i2) {
        Object next;
        IrExpression irExpressionDefaultValue;
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irType);
        if (irType instanceof IrSimpleType) {
            IrSimpleType irSimpleType = (IrSimpleType) irType;
            if (!IrTypePredicatesKt.isMarkedNullable(irSimpleType) && JvmIrTypeUtilsKt.isInlineClassType(irType)) {
                if (JvmPlatformKt.isJvm(getContext().getPlatform())) {
                    IrType irTypeUnboxInlineClass = unboxInlineClass(irType);
                    return coerceInlineClasses(IrUtilsKt.defaultValueForType(IrConstImpl.Companion, i, i2, irTypeUnboxInlineClass), irTypeUnboxInlineClass, irType);
                }
                classOrNull.getClass();
                Iterator it = IrUtilsKt.getConstructors(classOrNull).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!((IrConstructorSymbol) next).getOwner().isPrimary());
                IrConstructorSymbol irConstructorSymbol = (IrConstructorSymbol) next;
                if (irConstructorSymbol == null || (irExpressionDefaultValue = defaultValue(InlineClassesKt.getInlineClassUnderlyingType(classOrNull.getOwner()), i, i2)) == null) {
                    return null;
                }
                IrConstructorCallImpl irConstructorCallImplIrConstructorCallImpl$default = BuildersKt.IrConstructorCallImpl$default(i, i2, irType, irConstructorSymbol, classOrNull.getOwner().getTypeParameters().size(), 0, (IrStatementOrigin) null, (SourceElement) null, 128, (Object) null);
                irConstructorCallImplIrConstructorCallImpl$default.getArguments().set(0, irExpressionDefaultValue);
                int size = classOrNull.getOwner().getTypeParameters().size();
                for (int i3 = 0; i3 < size; i3++) {
                    List typeArguments = irConstructorCallImplIrConstructorCallImpl$default.getTypeArguments();
                    IrSimpleType typeOrNull = IrTypesKt.getTypeOrNull((IrTypeArgument) irSimpleType.getArguments().get(i3));
                    if (typeOrNull == null) {
                        typeOrNull = IrSimpleTypeImplKt.IrSimpleTypeImpl(((IrTypeParameter) classOrNull.getOwner().getTypeParameters().get(i3)).getSymbol(), false, CollectionsKt.emptyList(), CollectionsKt.emptyList());
                    }
                    typeArguments.set(i3, typeOrNull);
                }
                return irConstructorCallImplIrConstructorCallImpl$default;
            }
        }
        return IrTypePredicatesKt.isMarkedNullable(irType) ? IrConstImpl.Companion.constNull(i, i2, getContext().getIrBuiltIns().getNothingNType()) : IrUtilsKt.defaultValueForType(IrConstImpl.Companion, i, i2, irType);
    }

    public static /* synthetic */ IrExpression defaultValue$default(ComposerParamTransformer composerParamTransformer, IrType irType, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = -1;
        }
        if ((i3 & 2) != 0) {
            i2 = -1;
        }
        return composerParamTransformer.defaultValue(irType, i, i2);
    }

    private final IrCall findCallInBody(IrFunction irFunction) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        IrBody body = irFunction.getBody();
        if (body != null) {
            IrVisitorsKt.acceptChildrenVoid(body, new IrVisitorVoid() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposerParamTransformer.findCallInBody.1
                public void visitCall(IrCall expression) {
                    expression.getClass();
                    Ref.ObjectRef<IrCall> objectRef2 = objectRef;
                    if (objectRef2.element == null) {
                        objectRef2.element = expression;
                    }
                }

                public void visitElement(IrElement element) {
                    element.getClass();
                    IrVisitorsKt.acceptChildrenVoid(element, this);
                }
            });
        }
        return (IrCall) objectRef.element;
    }

    private final boolean hasDefaultForParam(IrSimpleFunction irSimpleFunction, int i) {
        if (((IrValueParameter) irSimpleFunction.getParameters().get(i)).getKind() != IrParameterKind.Regular) {
            return false;
        }
        if (((IrValueParameter) irSimpleFunction.getParameters().get(i)).getDefaultValue() != null) {
            return true;
        }
        List<IrSimpleFunctionSymbol> overriddenSymbols = irSimpleFunction.getOverriddenSymbols();
        if ((overriddenSymbols instanceof Collection) && overriddenSymbols.isEmpty()) {
            return false;
        }
        for (IrSimpleFunctionSymbol irSimpleFunctionSymbol : overriddenSymbols) {
            if (irSimpleFunctionSymbol.getOwner().getParameters().size() == irSimpleFunction.getParameters().size() && hasDefaultForParam((IrSimpleFunction) irSimpleFunctionSymbol.getOwner(), i)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0059 A[EDGE_INSN: B:21:0x0057->B:22:0x0059 BREAK  A[LOOP:0: B:14:0x003b->B:37:?]] */
    /* JADX WARN: Code duplicated, block: B:24:0x0063  */
    /* JADX WARN: Code duplicated, block: B:30:0x0077  */
    /* JADX WARN: Code duplicated, block: B:39:0x0089 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:40:? A[LOOP:1: B:28:0x0071->B:40:?, LOOP_END, SYNTHETIC] */
    private final boolean isLegacyOpenFunctionWithDefault(IrSimpleFunction irSimpleFunction) {
        List overriddenSymbols;
        Iterator it;
        if (!irSimpleFunction.getOverriddenSymbols().isEmpty() || irSimpleFunction.getModality() != Modality.OPEN || !Intrinsics.areEqual(irSimpleFunction.getOrigin(), IrDeclarationOrigin.Companion.getIR_EXTERNAL_DECLARATION_STUB())) {
            overriddenSymbols = irSimpleFunction.getOverriddenSymbols();
            if (!(overriddenSymbols instanceof Collection) && overriddenSymbols.isEmpty()) {
                return false;
            }
            it = overriddenSymbols.iterator();
            while (it.hasNext()) {
                if (isLegacyOpenFunctionWithDefault((IrSimpleFunction) ((IrSimpleFunctionSymbol) it.next()).getOwner())) {
                }
            }
            return false;
        }
        List parameters = irSimpleFunction.getParameters();
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            overriddenSymbols = irSimpleFunction.getOverriddenSymbols();
            if (!(overriddenSymbols instanceof Collection)) {
            }
            it = overriddenSymbols.iterator();
            while (it.hasNext()) {
                if (isLegacyOpenFunctionWithDefault((IrSimpleFunction) ((IrSimpleFunctionSymbol) it.next()).getOwner())) {
                }
            }
            return false;
        }
        Iterator it2 = parameters.iterator();
        while (it2.hasNext()) {
            if (IrUtilsKt.hasDefaultValue((IrValueParameter) it2.next())) {
                byte[] bArrM276getComposeMetadatafNhgvTQ = m276getComposeMetadatafNhgvTQ(irSimpleFunction);
                if (bArrM276getComposeMetadatafNhgvTQ != null && ComposeMetadata.m272supportsOpenFunctionsWithDefaultParamsimpl(bArrM276getComposeMetadatafNhgvTQ)) {
                    break;
                }
            }
        }
        overriddenSymbols = irSimpleFunction.getOverriddenSymbols();
        if (!(overriddenSymbols instanceof Collection)) {
        }
        it = overriddenSymbols.iterator();
        while (it.hasNext()) {
            if (isLegacyOpenFunctionWithDefault((IrSimpleFunction) ((IrSimpleFunctionSymbol) it.next()).getOwner())) {
            }
        }
        return false;
        return true;
    }

    private final boolean isPrimaryConstructorPrivate(IrType irType) {
        IrClass owner;
        IrConstructor primaryConstructor;
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irType.getType());
        return (classOrNull == null || (owner = classOrNull.getOwner()) == null || (primaryConstructor = IrUtilsKt.getPrimaryConstructor(owner)) == null || !Visibilities.INSTANCE.isPrivate(primaryConstructor.getVisibility().getDelegate())) ? false : true;
    }

    private final boolean isPublicComposableFunction(IrSimpleFunction irSimpleFunction) {
        if (hasComposableAnnotation(irSimpleFunction)) {
            return irSimpleFunction.getVisibility().isPublicAPI() || IrUtilsKt.isPublishedApi(irSimpleFunction);
        }
        return false;
    }

    private final IrAnnotation jvmNameAnnotation(String name) {
        IrClassSymbol topLevelClass = getTopLevelClass(JvmStandardClassIds.Annotations.INSTANCE.getJvmName());
        for (IrConstructorSymbol irConstructorSymbol : IrUtilsKt.getConstructors(topLevelClass)) {
            if (irConstructorSymbol.getOwner().isPrimary()) {
                IrAnnotationImpl irAnnotationImplIrAnnotationImpl$default = BuildersKt.IrAnnotationImpl$default(-1, -1, IrTypesKt.createType(topLevelClass, false, CollectionsKt.emptyList()), irConstructorSymbol, 0, 0, (IrStatementOrigin) null, (SourceElement) null, 192, (Object) null);
                irAnnotationImplIrAnnotationImpl$default.getArguments().set(0, irConst(name));
                return irAnnotationImplIrAnnotationImpl$default;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static boolean l(ComposerParamTransformer composerParamTransformer, IrType irType) {
        irType.getClass();
        return composerParamTransformer.isPrimaryConstructorPrivate(irType);
    }

    private final IrSimpleFunction lambdaInvokeWithComposerParam(IrSimpleFunction irSimpleFunction) {
        int size = irSimpleFunction.getParameters().size();
        for (IrSimpleFunction irSimpleFunction2 : IrUtilsKt.getFunctions(getContext().getIrBuiltIns().functionN((size + ComposableFunctionBodyTransformerKt.composeSyntheticParamCount$default(size, 0, 2, null)) - 1))) {
            if (Intrinsics.areEqual(irSimpleFunction2.getName(), OperatorNameConventions.INVOKE)) {
                return irSimpleFunction2;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    private final List<IrSimpleFunction> makeStubsForDefaultValueClassIfNeeded(IrSimpleFunction irSimpleFunction) {
        if (!isPublicComposableFunction(irSimpleFunction)) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        IrSimpleFunction irSimpleFunctionMakeValueClassNonPrimitiveStub = makeValueClassNonPrimitiveStub(irSimpleFunction);
        if (irSimpleFunctionMakeValueClassNonPrimitiveStub != null) {
            arrayList.add(irSimpleFunctionMakeValueClassNonPrimitiveStub);
        }
        if (!JvmPlatformKt.isJvm(getContext().getPlatform())) {
            IrSimpleFunction irSimpleFunctionMakeValueClassInaccessibleConstructorDefaultStub = makeValueClassInaccessibleConstructorDefaultStub(irSimpleFunction, new Function1() { // from class: tn2
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ComposerParamTransformer.l(this.b, (IrType) obj));
                }
            });
            if (irSimpleFunctionMakeValueClassInaccessibleConstructorDefaultStub != null) {
                arrayList.add(irSimpleFunctionMakeValueClassInaccessibleConstructorDefaultStub);
            }
            IrSimpleFunction irSimpleFunctionMakeValueClassInaccessibleConstructorDefaultStub2 = makeValueClassInaccessibleConstructorDefaultStub(irSimpleFunction, new Function1() { // from class: un2
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ComposerParamTransformer.n(this.b, (IrType) obj));
                }
            });
            if (irSimpleFunctionMakeValueClassInaccessibleConstructorDefaultStub2 != null) {
                arrayList.add(irSimpleFunctionMakeValueClassInaccessibleConstructorDefaultStub2);
            }
        }
        return arrayList;
    }

    private final IrSimpleFunction makeValueClassInaccessibleConstructorDefaultStub(final IrSimpleFunction irSimpleFunction, Function1<? super IrType, Boolean> function1) {
        final boolean[] zArr = new boolean[irSimpleFunction.getParameters().size()];
        int size = irSimpleFunction.getParameters().size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            IrValueParameter irValueParameter = (IrValueParameter) irSimpleFunction.getParameters().get(i);
            if (hasDefaultForParam(irSimpleFunction, i) && JvmIrTypeUtilsKt.isInlineClassType(irValueParameter.getType()) && !IrTypeUtilsKt.isNullable(irValueParameter.getType()) && IrTypePredicatesKt.isPrimitiveType$default(unboxInlineClass(irValueParameter.getType()), false, 1, (Object) null) && ((Boolean) function1.invoke(irValueParameter.getType())).booleanValue()) {
                zArr[i] = true;
                z = true;
            }
        }
        if (!z) {
            return null;
        }
        final IrSimpleFunction irSimpleFunctionMakeStub = makeStub(irSimpleFunction);
        this.transformedFunctions.put(irSimpleFunctionMakeStub, irSimpleFunctionMakeStub);
        this.transformedFunctionSet.add(irSimpleFunctionMakeStub);
        List parameters = irSimpleFunctionMakeStub.getParameters();
        int size2 = parameters.size();
        for (int i2 = 0; i2 < size2; i2++) {
            IrValueParameter irValueParameter2 = (IrValueParameter) parameters.get(i2);
            if (zArr[i2]) {
                irValueParameter2.setType(IrTypesKt.makeNullable(irValueParameter2.getType()));
            } else if (irValueParameter2.getDefaultValue() != null) {
                irValueParameter2.setType(defaultParameterType(irValueParameter2.getType()));
                irValueParameter2.setDefaultValue((IrExpressionBody) null);
            }
        }
        irSimpleFunctionMakeStub.setBody(IrFactoryHelpersKt.createBlockBody(getContext().getIrFactory(), -1, -1, new Function1() { // from class: sn2
            public final Object invoke(Object obj) {
                return ComposerParamTransformer.makeValueClassInaccessibleConstructorDefaultStub$lambda$0$1(this.b, irSimpleFunctionMakeStub, irSimpleFunction, zArr, (IrBlockBody) obj);
            }
        }));
        return irSimpleFunctionMakeStub;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit makeValueClassInaccessibleConstructorDefaultStub$lambda$0$1(ComposerParamTransformer composerParamTransformer, IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2, boolean[] zArr, IrBlockBody irBlockBody) {
        List list;
        irBlockBody.getClass();
        List statements = irBlockBody.getStatements();
        IrSimpleFunctionSymbol symbol = irSimpleFunction.getSymbol();
        ComposerParamTransformer composerParamTransformer2 = composerParamTransformer;
        IrCall irCallIrCall$default = AbstractComposeLowering.irCall$default(composerParamTransformer2, irSimpleFunction2, 0, 0, 6, null);
        List typeParameters = irSimpleFunction.getTypeParameters();
        int size = typeParameters.size();
        for (int i = 0; i < size; i++) {
            irCallIrCall$default.getTypeArguments().set(i, IrTypesKt.getDefaultType((IrTypeParameter) typeParameters.get(i)));
        }
        List parameters = irSimpleFunction.getParameters();
        int size2 = parameters.size();
        int i2 = 0;
        while (i2 < size2) {
            IrValueParameter irValueParameter = (IrValueParameter) parameters.get(i2);
            if (zArr[i2]) {
                IrValueParameter irValueParameter2 = (IrValueParameter) irSimpleFunction2.getParameters().get(i2);
                IrType type = irValueParameter2.getType();
                IrElement irElementIrTemporary$default = AbstractComposeLowering.irTemporary$default(composerParamTransformer2, composerParamTransformer2.irGet(irValueParameter), "$tmp_for_arg_" + i2, null, false, null, 28, null);
                IrMemberAccessExpression.ValueArgumentsList arguments = irCallIrCall$default.getArguments();
                int indexInParameters = irValueParameter.getIndexInParameters();
                IrStatementOriginImpl elvis = IrStatementOrigin.Companion.getELVIS();
                IrExpression irExpressionIrNotEqual = composerParamTransformer2.irNotEqual(composerParamTransformer2.irGet(irElementIrTemporary$default), composerParamTransformer2.irNull());
                IrExpression irExpressionIrGet = composerParamTransformer2.irGet(irElementIrTemporary$default);
                IrExpression irExpressionDefaultArgumentFor = composerParamTransformer2.defaultArgumentFor(irValueParameter2);
                irExpressionDefaultArgumentFor.getClass();
                list = parameters;
                List listListOf = CollectionsKt.listOf(new IrElement[]{irElementIrTemporary$default, AbstractComposeLowering.irIfThenElse$default(composerParamTransformer2, type, irExpressionIrNotEqual, irExpressionIrGet, irExpressionDefaultArgumentFor, 0, 0, 48, null)});
                composerParamTransformer2 = composerParamTransformer;
                arguments.set(indexInParameters, AbstractComposeLowering.irBlock$default(composerParamTransformer2, type, elvis, 0, 0, listListOf, 12, null));
            } else {
                list = parameters;
                irCallIrCall$default.getArguments().set(irValueParameter.getIndexInParameters(), composerParamTransformer2.irGet(irValueParameter));
            }
            i2++;
            parameters = list;
        }
        Unit unit = Unit.INSTANCE;
        statements.add(composerParamTransformer2.irReturn(symbol, irCallIrCall$default, irSimpleFunction.getReturnType()));
        return Unit.INSTANCE;
    }

    private final IrSimpleFunction makeValueClassNonPrimitiveStub(final IrSimpleFunction irSimpleFunction) {
        int size = irSimpleFunction.getParameters().size();
        for (int i = 0; i < size; i++) {
            IrValueParameter irValueParameter = (IrValueParameter) irSimpleFunction.getParameters().get(i);
            if (hasDefaultForParam(irSimpleFunction, i) && JvmIrTypeUtilsKt.isInlineClassType(irValueParameter.getType()) && !IrTypeUtilsKt.isNullable(irValueParameter.getType())) {
                IrType irTypeUnboxInlineClass = unboxInlineClass(irValueParameter.getType());
                if (!IrTypePredicatesKt.isPrimitiveType$default(irTypeUnboxInlineClass, false, 1, (Object) null) && !IrTypeUtilsKt.isNullable(irTypeUnboxInlineClass)) {
                    final IrSimpleFunction irSimpleFunctionMakeStub = makeStub(irSimpleFunction);
                    this.transformedFunctions.put(irSimpleFunctionMakeStub, irSimpleFunctionMakeStub);
                    this.transformedFunctionSet.add(irSimpleFunctionMakeStub);
                    irSimpleFunctionMakeStub.setBody(IrFactoryHelpersKt.createBlockBody(getContext().getIrFactory(), -1, -1, new Function1() { // from class: rn2
                        public final Object invoke(Object obj) {
                            return ComposerParamTransformer.makeValueClassNonPrimitiveStub$lambda$1$0(this.b, irSimpleFunctionMakeStub, irSimpleFunction, (IrBlockBody) obj);
                        }
                    }));
                    return irSimpleFunctionMakeStub;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit makeValueClassNonPrimitiveStub$lambda$1$0(ComposerParamTransformer composerParamTransformer, IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2, IrBlockBody irBlockBody) {
        irBlockBody.getClass();
        List statements = irBlockBody.getStatements();
        IrSimpleFunctionSymbol symbol = irSimpleFunction.getSymbol();
        IrCall irCallIrCall$default = AbstractComposeLowering.irCall$default(composerParamTransformer, irSimpleFunction2, 0, 0, 6, null);
        List typeParameters = irSimpleFunction.getTypeParameters();
        int size = typeParameters.size();
        for (int i = 0; i < size; i++) {
            irCallIrCall$default.getTypeArguments().set(i, IrTypesKt.getDefaultType((IrTypeParameter) typeParameters.get(i)));
        }
        List parameters = irSimpleFunction.getParameters();
        int size2 = parameters.size();
        for (int i2 = 0; i2 < size2; i2++) {
            IrValueParameter irValueParameter = (IrValueParameter) parameters.get(i2);
            irCallIrCall$default.getArguments().set(irValueParameter.getIndexInParameters(), composerParamTransformer.irGet(irValueParameter));
        }
        Unit unit = Unit.INSTANCE;
        statements.add(composerParamTransformer.irReturn(symbol, irCallIrCall$default, irSimpleFunction.getReturnType()));
        return Unit.INSTANCE;
    }

    public static boolean n(ComposerParamTransformer composerParamTransformer, IrType irType) {
        irType.getClass();
        return !composerParamTransformer.constructorVisibilityIsAtLeastAsAccessibleAsType(irType);
    }

    private final boolean requiresDefaultParameter(IrSimpleFunction irSimpleFunction) {
        if (ComposePluginAttributesKt.isDefaultParamStub(irSimpleFunction) || isLegacyOpenFunctionWithDefault(irSimpleFunction)) {
            return true;
        }
        if (isVirtualFunctionWithDefaultParam(irSimpleFunction)) {
            return false;
        }
        if (!irSimpleFunction.isFakeOverride()) {
            List parameters = irSimpleFunction.getParameters();
            if ((parameters instanceof Collection) && parameters.isEmpty()) {
                return false;
            }
            Iterator it = parameters.iterator();
            while (it.hasNext()) {
                if (((IrValueParameter) it.next()).getDefaultValue() != null) {
                    return true;
                }
            }
            return false;
        }
        List<IrSimpleFunctionSymbol> overriddenSymbols = irSimpleFunction.getOverriddenSymbols();
        if ((overriddenSymbols instanceof Collection) && overriddenSymbols.isEmpty()) {
            return false;
        }
        for (IrSimpleFunctionSymbol irSimpleFunctionSymbol : overriddenSymbols) {
            if (irSimpleFunctionSymbol.getOwner().getModality() == Modality.FINAL && requiresDefaultParameter((IrSimpleFunction) irSimpleFunctionSymbol.getOwner())) {
                return true;
            }
        }
        return false;
    }

    private final List<Pair<IrClassifierSymbol, SimpleTypeNullability>> toComparableParams(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2) {
        Pair pair;
        List parameters = irSimpleFunction.getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        Iterator it = parameters.iterator();
        while (it.hasNext()) {
            IrSimpleType type = ((IrValueParameter) it.next()).getType();
            if (type instanceof IrSimpleType) {
                IrSimpleType irSimpleType = type;
                if (irSimpleType.getClassifier() instanceof IrTypeParameterSymbol) {
                    IrTypeParameter owner = irSimpleType.getClassifier().getOwner();
                    owner.getClass();
                    IrTypeParameter irTypeParameter = owner;
                    pair = new Pair(Intrinsics.areEqual(irTypeParameter.getParent(), irSimpleFunction) ? ((IrTypeParameter) irSimpleFunction2.getTypeParameters().get(irTypeParameter.getIndex())).getSymbol() : irSimpleType.getClassifier(), irSimpleType.getNullability());
                } else {
                    pair = new Pair(irSimpleType.getClassifier(), irSimpleType.getNullability());
                }
            } else {
                pair = null;
            }
            arrayList.add(pair);
        }
        return arrayList;
    }

    private final IrExpression transformComposableFunctionReference(IrFunctionReference expression, IrSimpleFunction fn) {
        IrFunction owner;
        IrSimpleType type = expression.getType();
        type.getClass();
        IrSimpleType irSimpleType = type;
        int size = irSimpleType.getArguments().size();
        int iChangedParamCount = ComposableFunctionBodyTransformerKt.changedParamCount(size - 1, 0);
        int i = size + iChangedParamCount;
        IrClassSymbol symbol = AbstractComposeLoweringKt.isKComposableFunction(expression.getType()) ? getContext().getIrBuiltIns().kFunctionN(i).getSymbol() : getContext().getIrBuiltIns().functionN(i).getSymbol();
        boolean zIsNullable = IrTypeUtilsKt.isNullable(irSimpleType);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.addAll(CollectionsKt.dropLast(irSimpleType.getArguments(), 1));
        listCreateListBuilder.add(this.composerType);
        for (int i2 = 0; i2 < iChangedParamCount; i2++) {
            listCreateListBuilder.add(getContext().getIrBuiltIns().getIntType());
        }
        listCreateListBuilder.add(CollectionsKt.last(irSimpleType.getArguments()));
        Unit unit = Unit.INSTANCE;
        IrSimpleType irSimpleTypeIrSimpleTypeImpl = IrSimpleTypeImplKt.IrSimpleTypeImpl(symbol, zIsNullable, CollectionsKt.build(listCreateListBuilder), irSimpleType.getAnnotations());
        transformChildrenVoid(expression);
        if (Intrinsics.areEqual(fn.getOrigin(), IrDeclarationOrigin.Companion.getADAPTER_FOR_CALLABLE_REFERENCE()) && !hasComposableAnnotation(fn)) {
            fn.setAnnotations(CollectionsKt.plus(fn.getAnnotations(), createComposableAnnotation()));
        }
        int startOffset = expression.getStartOffset();
        int endOffset = expression.getEndOffset();
        IrSimpleFunctionSymbol symbol2 = withComposerParamIfNeeded(fn).getSymbol();
        int size2 = expression.getTypeArguments().size();
        IrFunctionSymbol reflectionTarget = expression.getReflectionTarget();
        IrFunctionReferenceImpl IrFunctionReferenceImpl = BuildersKt.IrFunctionReferenceImpl(startOffset, endOffset, irSimpleTypeIrSimpleTypeImpl, symbol2, size2, (reflectionTarget == null || (owner = reflectionTarget.getOwner()) == null) ? null : owner instanceof IrSimpleFunction ? withComposerParamIfNeeded((IrSimpleFunction) owner).getSymbol() : owner.getSymbol(), expression.getOrigin());
        AddToStdlibKt.assignFrom(IrFunctionReferenceImpl.getTypeArguments(), expression.getTypeArguments());
        AddToStdlibKt.assignFrom(IrFunctionReferenceImpl.getArguments(), expression.getArguments());
        int size3 = i - expression.getArguments().size();
        for (int i3 = 0; i3 < size3; i3++) {
            IrFunctionReferenceImpl.getArguments().add(null);
        }
        return IrFunctionReferenceImpl;
    }

    public final boolean constructorVisibilityIsAtLeastAsAccessibleAsType(IrType irType) {
        Integer numCompare;
        DescriptorVisibility visibility;
        DescriptorVisibility visibility2;
        irType.getClass();
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irType.getType());
        Visibility delegate = null;
        IrClass owner = classOrNull != null ? classOrNull.getOwner() : null;
        IrConstructor primaryConstructor = owner != null ? IrUtilsKt.getPrimaryConstructor(owner) : null;
        Visibility delegate2 = (owner == null || (visibility2 = owner.getVisibility()) == null) ? null : visibility2.getDelegate();
        if (primaryConstructor != null && (visibility = primaryConstructor.getVisibility()) != null) {
            delegate = visibility.getDelegate();
        }
        return (delegate == null || delegate2 == null || (numCompare = Visibilities.INSTANCE.compare(delegate, delegate2)) == null || numCompare.intValue() < 0) ? false : true;
    }

    public final /* synthetic */ <T extends IrElement> T deepCopyWithSymbolsAndMetadata$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(T t, IrDeclarationParent irDeclarationParent, Function1<? super SymbolRemapper, ? extends TypeRemapper> function1) {
        t.getClass();
        function1.getClass();
        DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
        IrVisitorsKt.acceptVoid(t, deepCopySymbolRemapper);
        IrElement irElementTransform = t.transform(new DeepCopyPreservingMetadata(deepCopySymbolRemapper, (TypeRemapper) function1.invoke(deepCopySymbolRemapper)), (Object) null);
        Intrinsics.reifiedOperationMarker(1, "T");
        return (T) PatchDeclarationParentsKt.patchDeclarationParents(irElementTransform, irDeclarationParent);
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        this.inlineLambdaInfo.scan(irModule);
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, new ComposableTypeTransformer(getContext(), new ComposableTypeRemapper(getContext(), this.composerType)));
        PatchDeclarationParentsKt.patchDeclarationParents$default(irModule, (IrDeclarationParent) null, 1, (Object) null);
    }

    public IrExpression visitBlock(IrBlock expression) {
        IrFunctionReference irFunctionReference;
        expression.getClass();
        if (Intrinsics.areEqual(expression.getOrigin(), IrStatementOrigin.Companion.getADAPTED_FUNCTION_REFERENCE()) && !this.inlineLambdaInfo.isInlineFunctionExpression(expression)) {
            IrFunctionReference irFunctionReference2 = (IrStatement) CollectionsKt.lastOrNull(expression.getStatements());
            if (irFunctionReference2 instanceof IrFunctionReference) {
                irFunctionReference = irFunctionReference2;
            } else {
                if (!(irFunctionReference2 instanceof IrTypeOperatorCall)) {
                    f2f.a("Unexpected adapted function reference shape: ", DumpIrTreeKt.dump$default(expression, (DumpIrTreeOptions) null, 1, (Object) null));
                    return null;
                }
                IrFunctionReference argument = ((IrTypeOperatorCall) irFunctionReference2).getArgument();
                irFunctionReference = argument instanceof IrFunctionReference ? argument : null;
                if (irFunctionReference == null) {
                    return super.visitBlock(expression);
                }
            }
            if (!AbstractComposeLoweringKt.isKComposableFunction(irFunctionReference.getType()) && !AbstractComposeLoweringKt.isSyntheticComposableFunction(irFunctionReference.getType())) {
                return super.visitBlock(expression);
            }
            IrSimpleFunction owner = irFunctionReference.getSymbol().getOwner();
            IrSimpleFunction irSimpleFunction = owner instanceof IrSimpleFunction ? owner : null;
            if (irSimpleFunction == null) {
                return super.visitBlock(expression);
            }
            IrCall irCallFindCallInBody = findCallInBody(irSimpleFunction);
            if (irCallFindCallInBody != null) {
                IrSimpleFunction owner2 = irCallFindCallInBody.getSymbol().getOwner();
                return shouldBeRestartable(owner2) ? super.visitBlock(expression) : adaptComposableReference(irFunctionReference, owner2, false);
            }
            f2f.a("Expected a call in ", DumpIrTreeKt.dump$default(irSimpleFunction, (DumpIrTreeOptions) null, 1, (Object) null));
            return null;
        }
        return super.visitBlock(expression);
    }

    public IrStatement visitDeclaration(IrDeclarationBase declaration) {
        declaration.getClass();
        IrDeclarationParent irDeclarationParent = this.currentParent;
        if (declaration instanceof IrDeclarationParent) {
            this.currentParent = (IrDeclarationParent) declaration;
        }
        IrStatement irStatementVisitDeclaration = super.visitDeclaration(declaration);
        this.currentParent = irDeclarationParent;
        return irStatementVisitDeclaration;
    }

    public IrExpression visitFunctionReference(IrFunctionReference expression) {
        expression.getClass();
        if (!AbstractComposeLoweringKt.isKComposableFunction(expression.getType()) && !AbstractComposeLoweringKt.isSyntheticComposableFunction(expression.getType())) {
            return super.visitFunctionReference(expression);
        }
        IrSimpleFunction owner = expression.getSymbol().getOwner();
        IrSimpleFunction irSimpleFunction = owner instanceof IrSimpleFunction ? owner : null;
        if (irSimpleFunction == null) {
            return super.visitFunctionReference(expression);
        }
        if (ComposePluginAttributesKt.isComposableReferenceAdapter(irSimpleFunction) || Intrinsics.areEqual(irSimpleFunction.getOrigin(), IrDeclarationOrigin.Companion.getADAPTER_FOR_CALLABLE_REFERENCE()) || this.inlineLambdaInfo.isInlineFunctionExpression(expression) || shouldBeRestartable(irSimpleFunction)) {
            return (ComposePluginAttributesKt.isComposableReferenceAdapter(irSimpleFunction) || !requiresDefaultParameter(irSimpleFunction)) ? transformComposableFunctionReference(expression, irSimpleFunction) : adaptComposableReference(expression, irSimpleFunction, true);
        }
        return adaptComposableReference(expression, irSimpleFunction, false);
    }

    public IrStatement visitLocalDelegatedProperty(IrLocalDelegatedProperty declaration) {
        declaration.getClass();
        if (isComposableDelegatedAccessor(declaration.getGetter())) {
            IrSimpleFunction getter = declaration.getGetter();
            getter.setAnnotations(CollectionsKt.plus(getter.getAnnotations(), createComposableAnnotation()));
        }
        IrSimpleFunction setter = declaration.getSetter();
        if (setter != null && isComposableDelegatedAccessor(setter)) {
            IrSimpleFunction setter2 = declaration.getSetter();
            setter2.getClass();
            setter2.setAnnotations(CollectionsKt.plus(setter2.getAnnotations(), createComposableAnnotation()));
        }
        return super.visitLocalDelegatedProperty(declaration);
    }

    public IrExpression visitLocalDelegatedPropertyReference(IrLocalDelegatedPropertyReference expression) {
        expression.getClass();
        expression.setGetter(withComposerParamIfNeeded((IrSimpleFunction) expression.getGetter().getOwner()).getSymbol());
        IrSimpleFunctionSymbol setter = expression.getSetter();
        expression.setSetter(setter != null ? withComposerParamIfNeeded((IrSimpleFunction) setter.getOwner()).getSymbol() : null);
        return super.visitLocalDelegatedPropertyReference(expression);
    }

    public IrExpression visitPropertyReference(IrPropertyReference expression) {
        expression.getClass();
        IrSimpleFunctionSymbol getter = expression.getGetter();
        expression.setGetter(getter != null ? withComposerParamIfNeeded((IrSimpleFunction) getter.getOwner()).getSymbol() : null);
        IrSimpleFunctionSymbol setter = expression.getSetter();
        expression.setSetter(setter != null ? withComposerParamIfNeeded((IrSimpleFunction) setter.getOwner()).getSymbol() : null);
        return super.visitPropertyReference(expression);
    }

    public IrExpression visitRichFunctionReference(IrRichFunctionReference expression) {
        expression.getClass();
        expression.setOverriddenFunctionSymbol(withComposerParamIfNeeded((IrSimpleFunction) expression.getOverriddenFunctionSymbol().getOwner()).getSymbol());
        return super.visitRichFunctionReference(expression);
    }

    public IrStatement visitSimpleFunction(IrSimpleFunction declaration) {
        declaration.getClass();
        return super.visitSimpleFunction(withComposerParamIfNeeded(declaration));
    }

    public final IrCall withComposerParamIfNeeded(IrCall irCall, IrValueParameter irValueParameter) {
        IrSimpleFunction irSimpleFunctionWithComposerParamIfNeeded;
        int i;
        irCall.getClass();
        irValueParameter.getClass();
        if (isComposableDelegatedAccessor(irCall.getSymbol().getOwner())) {
            if (!hasComposableAnnotation(irCall.getSymbol().getOwner())) {
                IrSimpleFunction owner = irCall.getSymbol().getOwner();
                owner.setAnnotations(CollectionsKt.plus(owner.getAnnotations(), createComposableAnnotation()));
            }
            irSimpleFunctionWithComposerParamIfNeeded = withComposerParamIfNeeded((IrSimpleFunction) irCall.getSymbol().getOwner());
        } else if (isComposableLambdaInvoke(irCall)) {
            irSimpleFunctionWithComposerParamIfNeeded = lambdaInvokeWithComposerParam((IrSimpleFunction) irCall.getSymbol().getOwner());
        } else {
            if (!hasComposableAnnotation(irCall.getSymbol().getOwner())) {
                return irCall;
            }
            irSimpleFunctionWithComposerParamIfNeeded = withComposerParamIfNeeded((IrSimpleFunction) irCall.getSymbol().getOwner());
        }
        IrCallImpl IrCallImpl = BuildersKt.IrCallImpl(irCall.getStartOffset(), irCall.getEndOffset(), irCall.getType(), irSimpleFunctionWithComposerParamIfNeeded.getSymbol(), irCall.getTypeArguments().size(), irCall.getOrigin(), irCall.getSuperQualifierSymbol());
        IrDeclarationsKt.copyAttributes$default(IrCallImpl, irCall, false, 2, (Object) null);
        IrExpressionsKt.copyTypeArgumentsFrom$default(IrCallImpl, irCall, 0, 2, (Object) null);
        ArrayList arrayList = new ArrayList();
        IrMemberAccessExpression.ValueArgumentsList arguments = irCall.getArguments();
        int size = arguments.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                Collection indices = CollectionsKt.getIndices(irCall.getArguments());
                if ((indices instanceof Collection) && indices.isEmpty()) {
                    i = 0;
                } else {
                    IntIterator it = indices.iterator();
                    i = 0;
                    while (it.hasNext()) {
                        if (((IrValueParameter) irSimpleFunctionWithComposerParamIfNeeded.getParameters().get(it.nextInt())).getKind() == IrParameterKind.Regular && (i = i + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                int size2 = irCall.getArguments().size();
                int i3 = size2 + 1;
                IrCallImpl.getArguments().set(size2, irGet(irValueParameter));
                int iChangedParamCount = ComposableFunctionBodyTransformerKt.changedParamCount(i, ComposableFunctionBodyTransformerKt.getThisParamCount(irSimpleFunctionWithComposerParamIfNeeded));
                int i4 = 0;
                while (i4 < iChangedParamCount) {
                    if (i3 >= irSimpleFunctionWithComposerParamIfNeeded.getParameters().size()) {
                        uw4.a("expected $сhanged parameter at index ", i3, ":\n", IrSourcePrinterKt.dumpSrc$default(irSimpleFunctionWithComposerParamIfNeeded, false, 1, null));
                        return null;
                    }
                    IrCallImpl.getArguments().set(i3, irConst(0));
                    i4++;
                    i3++;
                }
                int iDefaultParamCount = ComposableFunctionBodyTransformerKt.defaultParamCount(i);
                for (int i5 = 0; i5 < iDefaultParamCount; i5++) {
                    int i6 = i5 * 31;
                    int iMin = Math.min(i6 + 31, i);
                    if (i3 < irSimpleFunctionWithComposerParamIfNeeded.getParameters().size()) {
                        boolean[] zArrSliceArray = ArraysKt.sliceArray(CollectionsKt.toBooleanArray(arrayList), RangesKt.until(i6, iMin));
                        IrCallImpl.getArguments().set(i3, irConst(bitMask(Arrays.copyOf(zArrSliceArray, zArrSliceArray.length))));
                        i3++;
                    } else if (arrayList.isEmpty()) {
                        continue;
                    } else {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            if (((Boolean) it2.next()).booleanValue()) {
                                uw4.a("expected $default parameter at index ", i3, ":\n", IrSourcePrinterKt.dumpSrc$default(irSimpleFunctionWithComposerParamIfNeeded, false, 1, null));
                                return null;
                            }
                        }
                    }
                }
                return IrCallImpl;
            }
            IrExpression irExpression = (IrExpression) arguments.get(i2);
            IrValueParameter irValueParameter2 = (IrValueParameter) irSimpleFunctionWithComposerParamIfNeeded.getParameters().get(i2);
            int i7 = WhenMappings.$EnumSwitchMapping$0[irValueParameter2.getKind().ordinal()];
            if (i7 == 1 || i7 == 2 || i7 == 3) {
                IrCallImpl.getArguments().set(irValueParameter2.getIndexInParameters(), irExpression);
            } else {
                if (i7 != 4) {
                    bu8.a();
                    return null;
                }
                boolean zHasDefaultForParam = hasDefaultForParam(irSimpleFunctionWithComposerParamIfNeeded, i2);
                arrayList.add(Boolean.valueOf(irExpression == null && zHasDefaultForParam));
                if (irExpression != null) {
                    IrCallImpl.getArguments().set(irValueParameter2.getIndexInParameters(), irExpression);
                } else if (zHasDefaultForParam) {
                    IrCallImpl.getArguments().set(irValueParameter2.getIndexInParameters(), defaultArgumentFor(irValueParameter2));
                }
            }
            i2++;
        }
    }

    private final IrSimpleFunction withComposerParamIfNeeded(IrSimpleFunction irSimpleFunction) {
        if (this.transformedFunctionSet.contains(irSimpleFunction) || !hasComposableAnnotation(irSimpleFunction) || irSimpleFunction.isExpect()) {
            return irSimpleFunction;
        }
        IrSimpleFunction irSimpleFunction2 = this.transformedFunctions.get(irSimpleFunction);
        return irSimpleFunction2 == null ? copyWithComposerParam(irSimpleFunction) : irSimpleFunction2;
    }
}
