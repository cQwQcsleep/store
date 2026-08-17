package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import androidx.compose.compiler.plugins.kotlin.ComposeNames;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.WeakBindingTraceKt;
import androidx.compose.compiler.plugins.kotlin.analysis.ComposeWritableSlices;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.inference.ApplierInferencer;
import androidx.compose.compiler.plugins.kotlin.inference.ErrorReporter;
import androidx.compose.compiler.plugins.kotlin.inference.Item;
import androidx.compose.compiler.plugins.kotlin.inference.LazyScheme;
import androidx.compose.compiler.plugins.kotlin.inference.LazySchemeStorage;
import androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter;
import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import androidx.compose.compiler.plugins.kotlin.inference.Open;
import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import androidx.compose.compiler.plugins.kotlin.inference.SchemeKt;
import androidx.compose.compiler.plugins.kotlin.inference.Token;
import androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableTargetAnnotationsTransformer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty;
import org.jetbrains.kotlin.ir.declarations.IrMetadataSourceOwner;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBody;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetField;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrReturn;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.IrValueParameterSymbol;
import org.jetbrains.kotlin.ir.symbols.IrVariableSymbol;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypeProjection;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeBuilder;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020\u001cH\u0016J\u0010\u0010-\u001a\u00020.2\u0006\u0010,\u001a\u00020\u0013H\u0016J\u0010\u0010/\u001a\u00020.2\u0006\u0010,\u001a\u000200H\u0016J\u0010\u00101\u001a\u00020.2\u0006\u0010,\u001a\u000202H\u0016J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0016J\u0012\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020:H\u0002J\u0014\u0010;\u001a\u0004\u0018\u00010&2\b\u00105\u001a\u0004\u0018\u00010\u0012H\u0002J\n\u0010F\u001a\u00020G*\u00020\u0013J\u0012\u0010H\u001a\u00020C*\u00020I2\u0006\u0010J\u001a\u00020=J\u0011\u0010N\u001a\u00020G*\u000206H\u0000¢\u0006\u0002\bOJ\u000e\u0010P\u001a\u0004\u0018\u00010Q*\u00020\u0012H\u0002J\f\u0010R\u001a\u00020G*\u00020QH\u0002J\u0011\u0010S\u001a\u00020Q*\u00020\u0012H\u0000¢\u0006\u0002\bTJ\u0011\u0010U\u001a\u00020Q*\u000206H\u0000¢\u0006\u0002\bVJ\u000e\u0010W\u001a\u0004\u0018\u00010X*\u00020=H\u0002J\u0012\u0010Y\u001a\b\u0012\u0004\u0012\u00020X0>*\u00020=H\u0002J\u0012\u0010Y\u001a\b\u0012\u0004\u0012\u00020X0>*\u00020CH\u0002J\u0010\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020\rH\u0002J\u001c\u0010]\u001a\b\u0012\u0004\u0012\u00020X0>2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020?0>H\u0002J\u0010\u0010_\u001a\u00020G2\u0006\u0010`\u001a\u00020?H\u0002J\u0016\u0010a\u001a\u00020(2\u0006\u0010b\u001a\u00020c2\u0006\u0010<\u001a\u00020=J\u0016\u0010d\u001a\u00020(2\u0006\u0010,\u001a\u00020e2\u0006\u0010<\u001a\u00020=J\u0016\u0010d\u001a\u00020(2\u0006\u0010,\u001a\u00020e2\u0006\u0010B\u001a\u00020CJ\u001e\u0010f\u001a\u00020(2\u0006\u0010,\u001a\u00020e2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020X0>H\u0002J\u000e\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020\u0013J\u000e\u0010j\u001a\u00020k2\u0006\u0010b\u001a\u00020IJ\f\u0010r\u001a\u00020G*\u00020\u0013H\u0002J\f\u0010s\u001a\u00020G*\u000206H\u0002R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0014\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00170\u00160\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00190\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010<\u001a\u00020=*\b\u0012\u0004\u0012\u00020?0>8F¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0017\u0010B\u001a\u0004\u0018\u00010C*\u00020\u00138F¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001a\u0010K\u001a\u00020G*\u0004\u0018\u00010\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u001a\u0010M\u001a\u00020G*\u0004\u0018\u00010\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bM\u0010LR\u0018\u0010l\u001a\u00020G*\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bl\u0010mR\u0018\u0010n\u001a\u00020G*\u00020I8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0018\u0010K\u001a\u00020G*\u00020I8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bK\u0010oR\u0018\u0010p\u001a\u00020G*\u00020I8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bq\u0010oR\u0018\u0010l\u001a\u00020G*\u00020I8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bl\u0010o¨\u0006t"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "ComposableTargetClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "ComposableOpenTargetClass", "ComposableInferredTargetClass", "ownerMap", "", "Lorg/jetbrains/kotlin/ir/IrElement;", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "parameterOwners", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "Lkotlin/Pair;", "", "variableDeclarations", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceVariable;", "currentOwner", "currentFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "transformer", "getTransformer", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "lineInfoOf", "", "element", "infer", "Landroidx/compose/compiler/plugins/kotlin/inference/ApplierInferencer;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "visitFile", "declaration", "visitFunction", "Lorg/jetbrains/kotlin/ir/IrStatement;", "visitVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "visitLocalDelegatedProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrLocalDelegatedProperty;", "visitCall", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "inferenceParameterOrNull", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceResolvedParameter;", "getValue", "Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;", "resolveExpressionOrNull", "target", "Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "", "Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;", "getTarget", "(Ljava/util/List;)Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "scheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "getScheme", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "hasSchemeSpecified", "", "toScheme", "Lorg/jetbrains/kotlin/ir/types/IrType;", "defaultTarget", "isComposableLambda", "(Lorg/jetbrains/kotlin/ir/IrElement;)Z", "isComposableParameter", "hasTransformedLambda", "hasTransformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "findTransformedLambda", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionExpression;", "isTransformedLambda", "transformedLambda", "transformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "singletonFunctionExpression", "singletonFunctionExpression$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "toAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "toAnnotations", "annotation", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrAnnotationImpl;", "classSymbol", "filteredAnnotations", "annotations", "isComposableTargetAnnotation", "it", "addAnnotationToType", "type", "Lorg/jetbrains/kotlin/ir/types/impl/IrSimpleTypeBuilder;", "addAnnotationToDeclaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "addMetadataVisibleAnnotations", "inferenceFunctionOf", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionDeclaration;", "function", "inferenceFunctionTypeOf", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionType;", "isComposable", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Z", "isSamComposable", "(Lorg/jetbrains/kotlin/ir/types/IrType;)Z", "isOrHasComposableLambda", "isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "hasComposableParameter", "hasComposableArguments", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableTargetAnnotationsTransformer extends AbstractComposeLowering {
    private final IrClassSymbol ComposableInferredTargetClass;
    private final IrClassSymbol ComposableOpenTargetClass;
    private final IrClassSymbol ComposableTargetClass;
    private IrFile currentFile;
    private IrFunction currentOwner;
    private final ApplierInferencer<InferenceFunction, InferenceNode> infer;
    private final Map<IrElement, IrFunction> ownerMap;
    private final Map<IrSymbol, Pair<IrFunction, Integer>> parameterOwners;
    private final Map<IrSymbol, InferenceVariable> variableDeclarations;

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IrTypeOperator.values().length];
            try {
                iArr[IrTypeOperator.CAST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[IrTypeOperator.IMPLICIT_CAST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.compiler.plugins.kotlin.lower.ComposableTargetAnnotationsTransformer$addMetadataVisibleAnnotations$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<IrConstructorCall, Boolean> {
        public AnonymousClass1(Object obj) {
            super(1, obj, ComposableTargetAnnotationsTransformer.class, "isComposableTargetAnnotation", "isComposableTargetAnnotation(Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;)Z", 0);
        }

        public final Boolean invoke(IrConstructorCall irConstructorCall) {
            irConstructorCall.getClass();
            return Boolean.valueOf(((ComposableTargetAnnotationsTransformer) ((CallableReference) this).receiver).isComposableTargetAnnotation(irConstructorCall));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableTargetAnnotationsTransformer(IrPluginContext irPluginContext, final ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        ComposeClassIds composeClassIds = ComposeClassIds.INSTANCE;
        this.ComposableTargetClass = getTopLevelClassOrNull(composeClassIds.getComposableTarget());
        this.ComposableOpenTargetClass = getTopLevelClassOrNull(composeClassIds.getComposableOpenTarget());
        this.ComposableInferredTargetClass = getTopLevelClassOrNull(composeClassIds.getComposableInferredTarget());
        this.ownerMap = new LinkedHashMap();
        this.parameterOwners = new LinkedHashMap();
        this.variableDeclarations = new LinkedHashMap();
        this.infer = new ApplierInferencer<>(new TypeAdapter<InferenceFunction>() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableTargetAnnotationsTransformer$infer$1
            private final Map<InferenceFunction, Scheme> current = new LinkedHashMap();

            @Override // androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter
            public Scheme currentInferredSchemeOf(InferenceFunction type) {
                type.getClass();
                if (!type.getSchemeIsUpdatable()) {
                    return null;
                }
                Scheme scheme = this.current.get(type);
                return scheme == null ? declaredSchemaOf(type) : scheme;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter
            public Scheme declaredSchemaOf(InferenceFunction type) {
                type.getClass();
                Scheme declaredScheme$default = InferenceFunction.toDeclaredScheme$default(type, null, 1, null);
                type.recordScheme(declaredScheme$default);
                return declaredScheme$default;
            }

            public final Map<InferenceFunction, Scheme> getCurrent() {
                return this.current;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter
            public void updatedInferredScheme(InferenceFunction type, Scheme scheme) {
                type.getClass();
                scheme.getClass();
                type.recordScheme(scheme);
                type.updateScheme(scheme);
                this.current.put(type, scheme);
            }
        }, new NodeAdapter<InferenceFunction, InferenceNode>() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableTargetAnnotationsTransformer$infer$2
            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public InferenceNode containerOf(InferenceNode node) {
                InferenceNode inferenceNodeInferenceNodeOf;
                node.getClass();
                IrFunction irFunction = (IrFunction) this.this$0.ownerMap.get(node.getElement());
                if (irFunction != null && (inferenceNodeInferenceNodeOf = ComposableTargetAnnotationsTransformerKt.inferenceNodeOf(irFunction, this.this$0.getTransformer())) != null) {
                    return inferenceNodeInferenceNodeOf;
                }
                InferenceResolvedParameter inferenceResolvedParameter = node instanceof InferenceResolvedParameter ? (InferenceResolvedParameter) node : null;
                return inferenceResolvedParameter != null ? inferenceResolvedParameter.getReferenceContainer() : node;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public NodeKind kindOf(InferenceNode node) {
                node.getClass();
                return node.getKind();
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public InferenceNode referencedContainerOf(InferenceNode node) {
                node.getClass();
                return node.getReferenceContainer();
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public int schemeParameterIndexOf(InferenceNode node, InferenceNode container) {
                node.getClass();
                container.getClass();
                return node.parameterIndex(container);
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public InferenceFunction typeOf(InferenceNode node) {
                node.getClass();
                return node.getFunction();
            }
        }, new LazySchemeStorage<InferenceNode>() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableTargetAnnotationsTransformer$infer$3
            private final Map<InferenceNode, LazyScheme> map = new LinkedHashMap();

            @Override // androidx.compose.compiler.plugins.kotlin.inference.LazySchemeStorage
            public LazyScheme getLazyScheme(InferenceNode node) {
                node.getClass();
                return this.map.get(node);
            }

            public final Map<InferenceNode, LazyScheme> getMap() {
                return this.map;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.LazySchemeStorage
            public void storeLazyScheme(InferenceNode node, LazyScheme value) {
                node.getClass();
                value.getClass();
                this.map.put(node, value);
            }
        }, new ErrorReporter<InferenceNode>() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableTargetAnnotationsTransformer$infer$4
            @Override // androidx.compose.compiler.plugins.kotlin.inference.ErrorReporter
            public void log(InferenceNode node, String message) {
                message.getClass();
                IrElement element = node != null ? node.getElement() : null;
                if (moduleMetrics.isEmpty()) {
                    return;
                }
                moduleMetrics.log("applier inference" + this.lineInfoOf(element) + ": " + message);
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.ErrorReporter
            public void reportCallError(InferenceNode node, String expected, String received) {
                node.getClass();
                expected.getClass();
                received.getClass();
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.ErrorReporter
            public void reportParameterError(InferenceNode node, int index, String expected, String received) {
                node.getClass();
                expected.getClass();
                received.getClass();
            }
        });
    }

    private final void addMetadataVisibleAnnotations(IrDeclaration declaration, List<? extends IrAnnotation> annotations) {
        List<? extends IrAnnotation> list = annotations;
        declaration.setAnnotations(CollectionsKt.plus(filteredAnnotations(declaration.getAnnotations()), list));
        if ((declaration instanceof IrMetadataSourceOwner) && (((IrMetadataSourceOwner) declaration).getMetadata() instanceof FirMetadataSource)) {
            List<IrAnnotation> metadataVisibleAnnotationsForElement = getContext().getMetadataDeclarationRegistrar().getMetadataVisibleAnnotationsForElement(declaration);
            final AnonymousClass1 anonymousClass1 = new AnonymousClass1(this);
            metadataVisibleAnnotationsForElement.removeIf(new Predicate() { // from class: cm2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ComposableTargetAnnotationsTransformer.k(anonymousClass1, obj);
                }
            });
            CollectionsKt.addAll(metadataVisibleAnnotationsForElement, list);
        }
    }

    private final IrAnnotationImpl annotation(IrClassSymbol classSymbol) {
        return BuildersKt.IrAnnotationImpl$default(-1, -1, IrTypesKt.getDefaultType(classSymbol), (IrConstructorSymbol) SequencesKt.first(IrUtilsKt.getConstructors(classSymbol)), 0, 0, (IrStatementOrigin) null, (SourceElement) null, 128, (Object) null);
    }

    private final List<IrAnnotation> filteredAnnotations(List<? extends IrConstructorCall> annotations) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : annotations) {
            if (!isComposableTargetAnnotation((IrConstructorCall) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (obj2 instanceof IrAnnotation) {
                arrayList2.add(obj2);
            }
        }
        return arrayList2;
    }

    private final IrFunctionExpression findTransformedLambda(IrElement irElement) {
        if (irElement instanceof IrCall) {
            for (IrExpression irExpression : ComposableTargetAnnotationsTransformerKt.getTargetArguments((IrCall) irElement)) {
                IrFunctionExpression irFunctionExpressionFindTransformedLambda = irExpression != null ? findTransformedLambda(irExpression) : null;
                if (irFunctionExpressionFindTransformedLambda != null) {
                    return irFunctionExpressionFindTransformedLambda;
                }
            }
            return null;
        }
        if (irElement instanceof IrGetField) {
            IrExpressionBody initializer = ((IrGetField) irElement).getSymbol().getOwner().getInitializer();
            if (initializer != null) {
                return findTransformedLambda(initializer);
            }
            return null;
        }
        if (irElement instanceof IrBody) {
            Iterator it = IrUtilsKt.getStatements((IrBody) irElement).iterator();
            while (it.hasNext()) {
                IrFunctionExpression irFunctionExpressionFindTransformedLambda2 = findTransformedLambda((IrElement) ((IrStatement) it.next()));
                if (irFunctionExpressionFindTransformedLambda2 != null) {
                    return irFunctionExpressionFindTransformedLambda2;
                }
            }
            return null;
        }
        if (irElement instanceof IrReturn) {
            return findTransformedLambda(((IrReturn) irElement).getValue());
        }
        if (irElement instanceof IrFunctionExpression) {
            IrFunctionExpression irFunctionExpression = (IrFunctionExpression) irElement;
            if (isTransformedLambda(irFunctionExpression)) {
                return irFunctionExpression;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ComposableTargetAnnotationsTransformer getTransformer() {
        return this;
    }

    private final boolean hasComposableArguments(IrCall irCall) {
        IrType type;
        List<IrExpression> targetArguments = ComposableTargetAnnotationsTransformerKt.getTargetArguments(irCall);
        if ((targetArguments instanceof Collection) && targetArguments.isEmpty()) {
            return false;
        }
        for (IrExpression irExpression : targetArguments) {
            if (irExpression != null && (type = irExpression.getType()) != null && (isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(type) || isSamComposable(type))) {
                return true;
            }
        }
        return false;
    }

    private final boolean hasComposableParameter(IrFunction irFunction) {
        List targetParameters = ComposableTargetAnnotationsTransformerKt.getTargetParameters(irFunction);
        if ((targetParameters instanceof Collection) && targetParameters.isEmpty()) {
            return false;
        }
        Iterator it = targetParameters.iterator();
        while (it.hasNext()) {
            if (isComposable(((IrValueParameter) it.next()).getType())) {
                return true;
            }
        }
        return false;
    }

    private final InferenceResolvedParameter inferenceParameterOrNull(IrGetValue getValue) {
        Pair<IrFunction, Integer> pair = this.parameterOwners.get(getValue.getSymbol());
        if (pair != null) {
            return new InferenceResolvedParameter(getValue, inferenceFunctionOf((IrFunction) pair.getFirst()), ComposableTargetAnnotationsTransformerKt.inferenceNodeOf((IrElement) pair.getFirst(), this), ((Number) pair.getSecond()).intValue());
        }
        return null;
    }

    private final boolean isComposable(IrFunction irFunction) {
        if (AdditionalIrUtilsKt.hasAnnotation(irFunction.getAnnotations(), ComposeFqNames.INSTANCE.getComposable())) {
            return true;
        }
        List<IrValueParameter> parameters = irFunction.getParameters();
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            return false;
        }
        for (IrValueParameter irValueParameter : parameters) {
            if (irValueParameter.getKind() == IrParameterKind.Regular && Intrinsics.areEqual(irValueParameter.getName(), ComposeNames.INSTANCE.getComposerParameter())) {
                return true;
            }
        }
        return false;
    }

    private final boolean isComposableLambda(IrType irType) {
        List arguments;
        if (Intrinsics.areEqual(IrTypesKt.getClassFqName(irType), ComposeFqNames.INSTANCE.getComposableLambdaType())) {
            return true;
        }
        IrSimpleType irSimpleType = irType instanceof IrSimpleType ? (IrSimpleType) irType : null;
        if (irSimpleType == null || (arguments = irSimpleType.getArguments()) == null) {
            return false;
        }
        List list = arguments;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            IrType typeOrNull = IrTypesKt.getTypeOrNull((IrTypeArgument) it.next());
            if (Intrinsics.areEqual(typeOrNull != null ? IrTypesKt.getClassFqName(typeOrNull) : null, ComposeFqNames.INSTANCE.getComposer())) {
                return true;
            }
        }
        return false;
    }

    private final boolean isComposableParameter(IrElement irElement) {
        if (irElement instanceof IrGetValue) {
            IrGetValue irGetValue = (IrGetValue) irElement;
            if (this.parameterOwners.get(irGetValue.getSymbol()) != null && isComposable(irGetValue.getType())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isComposableTargetAnnotation(IrConstructorCall it) {
        return ComposableTargetAnnotationsTransformerKt.isComposableTarget(it) || ComposableTargetAnnotationsTransformerKt.isComposableOpenTarget(it) || ComposableTargetAnnotationsTransformerKt.isComposableInferredTarget(it);
    }

    private final boolean isSamComposable(IrType irType) {
        IrSimpleFunction irSimpleFunctionSamOwnerOrNull = ComposableTargetAnnotationsTransformerKt.samOwnerOrNull(irType);
        return irSimpleFunctionSamOwnerOrNull != null && isComposable((IrFunction) irSimpleFunctionSamOwnerOrNull);
    }

    private final boolean isTransformedLambda(IrFunctionExpression irFunctionExpression) {
        return Intrinsics.areEqual(WeakBindingTraceKt.getIrTrace(getContext()).get(ComposeWritableSlices.INSTANCE.getIS_TRANSFORMED_LAMBDA(), irFunctionExpression), Boolean.TRUE);
    }

    public static boolean k(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String lineInfoOf(IrElement element) {
        IrFile irFile = this.currentFile;
        if (element == null || irFile == null) {
            return "";
        }
        return " " + IrDeclarationsKt.getName(irFile) + ':' + (irFile.getFileEntry().getLineNumber(element.getStartOffset()) + 1) + ':' + (irFile.getFileEntry().getColumnNumber(element.getStartOffset()) + 1);
    }

    private final InferenceNode resolveExpressionOrNull(IrElement expression) {
        if (expression instanceof IrTypeOperatorCall) {
            IrTypeOperatorCall irTypeOperatorCall = (IrTypeOperatorCall) expression;
            int i = WhenMappings.$EnumSwitchMapping$0[irTypeOperatorCall.getOperator().ordinal()];
            if (i == 1 || i == 2) {
                return resolveExpressionOrNull(irTypeOperatorCall.getArgument());
            }
            return null;
        }
        if (expression instanceof IrGetValue) {
            IrGetValue irGetValue = (IrGetValue) expression;
            InferenceResolvedParameter inferenceResolvedParameterInferenceParameterOrNull = inferenceParameterOrNull(irGetValue);
            return inferenceResolvedParameterInferenceParameterOrNull != null ? inferenceResolvedParameterInferenceParameterOrNull : this.variableDeclarations.get(irGetValue.getSymbol());
        }
        if (expression instanceof IrCall) {
            return this.variableDeclarations.get(((IrCall) expression).getSymbol());
        }
        return null;
    }

    private final IrAnnotation toAnnotation(Item item) {
        IrClassSymbol irClassSymbol = this.ComposableTargetClass;
        if (irClassSymbol != null && this.ComposableOpenTargetClass != null) {
            if (item instanceof Token) {
                IrAnnotationImpl irAnnotationImplAnnotation = annotation(irClassSymbol);
                irAnnotationImplAnnotation.getArguments().set(0, irConst(((Token) item).getValue()));
                return irAnnotationImplAnnotation;
            }
            if (item instanceof Open) {
                Open open = (Open) item;
                if (open.getIndex() < 0) {
                    return null;
                }
                IrAnnotationImpl irAnnotationImplAnnotation2 = annotation(this.ComposableOpenTargetClass);
                irAnnotationImplAnnotation2.getArguments().set(0, irConst(open.getIndex()));
                return irAnnotationImplAnnotation2;
            }
            bu8.a();
        }
        return null;
    }

    private final List<IrAnnotation> toAnnotations(Scheme scheme) {
        IrClassSymbol irClassSymbol = this.ComposableInferredTargetClass;
        if (irClassSymbol == null) {
            return CollectionsKt.emptyList();
        }
        IrAnnotationImpl irAnnotationImplAnnotation = annotation(irClassSymbol);
        irAnnotationImplAnnotation.getArguments().set(0, irConst(scheme.serialize()));
        return CollectionsKt.listOf(irAnnotationImplAnnotation);
    }

    private static final Scheme toScheme$lambda$0$toScheme(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer, Item item, IrTypeArgument irTypeArgument) {
        if (!(irTypeArgument instanceof IrTypeProjection)) {
            return null;
        }
        IrTypeProjection irTypeProjection = (IrTypeProjection) irTypeArgument;
        if (composableTargetAnnotationsTransformer.isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(irTypeProjection.getType())) {
            return composableTargetAnnotationsTransformer.toScheme(irTypeProjection.getType(), item);
        }
        return null;
    }

    private static final void visitFunction$recordParameter(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer, IrFunction irFunction, Ref.IntRef intRef, IrValueParameter irValueParameter) {
        if (composableTargetAnnotationsTransformer.isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(irValueParameter.getType())) {
            Map<IrSymbol, Pair<IrFunction, Integer>> map = composableTargetAnnotationsTransformer.parameterOwners;
            IrValueParameterSymbol symbol = irValueParameter.getSymbol();
            int i = intRef.element;
            intRef.element = i + 1;
            map.put(symbol, TuplesKt.to(irFunction, Integer.valueOf(i)));
        }
    }

    public final void addAnnotationToDeclaration(IrDeclaration declaration, Item target) {
        declaration.getClass();
        target.getClass();
        addMetadataVisibleAnnotations(declaration, toAnnotations(target));
    }

    public final void addAnnotationToType(IrSimpleTypeBuilder type, Item target) {
        type.getClass();
        target.getClass();
        type.setAnnotations(CollectionsKt.plus(filteredAnnotations(type.getAnnotations()), toAnnotations(target)));
    }

    public final Scheme getScheme(IrFunction irFunction) {
        Object next;
        irFunction.getClass();
        Iterator it = irFunction.getAnnotations().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!ComposableTargetAnnotationsTransformerKt.isComposableInferredTarget((IrAnnotation) next));
        IrAnnotation irAnnotation = (IrAnnotation) next;
        if (irAnnotation != null) {
            Object objFirstOrNull = CollectionsKt.firstOrNull(irAnnotation.getArguments());
            IrConst irConst = objFirstOrNull instanceof IrConst ? (IrConst) objFirstOrNull : null;
            Object value = irConst != null ? irConst.getValue() : null;
            if (!(value instanceof String)) {
                value = null;
            }
            String str = (String) value;
            if (str != null) {
                return SchemeKt.deserializeScheme(str);
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:49:0x0098  */
    public final Item getTarget(List<? extends IrConstructorCall> list) {
        Token token;
        Object next;
        Object next2;
        Open open;
        Object next3;
        FqName fqNameWhenAvailable;
        list.getClass();
        List<? extends IrConstructorCall> list2 = list;
        Iterator<T> it = list2.iterator();
        do {
            token = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!ComposableTargetAnnotationsTransformerKt.isComposableTarget((IrConstructorCall) next));
        IrConstructorCall irConstructorCall = (IrConstructorCall) next;
        if (irConstructorCall != null) {
            Object objFirstOrNull = CollectionsKt.firstOrNull(irConstructorCall.getArguments());
            IrConst irConst = objFirstOrNull instanceof IrConst ? (IrConst) objFirstOrNull : null;
            Object value = irConst != null ? irConst.getValue() : null;
            if (!(value instanceof String)) {
                value = null;
            }
            String str = (String) value;
            Token token2 = str != null ? new Token(str) : null;
            if (token2 != null) {
                return token2;
            }
        }
        Iterator<T> it2 = list2.iterator();
        do {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
        } while (!ComposableTargetAnnotationsTransformerKt.isComposableOpenTarget((IrConstructorCall) next2));
        IrConstructorCall irConstructorCall2 = (IrConstructorCall) next2;
        if (irConstructorCall2 != null) {
            Object objFirstOrNull2 = CollectionsKt.firstOrNull(irConstructorCall2.getArguments());
            IrConst irConst2 = objFirstOrNull2 instanceof IrConst ? (IrConst) objFirstOrNull2 : null;
            Object value2 = irConst2 != null ? irConst2.getValue() : null;
            if (!(value2 instanceof Integer)) {
                value2 = null;
            }
            Integer num = (Integer) value2;
            if (num != null) {
                open = new Open(num.intValue(), false, 2, token);
            } else {
                open = null;
            }
        } else {
            open = null;
        }
        if (open != null) {
            return open;
        }
        Iterator<T> it3 = list2.iterator();
        do {
            if (!it3.hasNext()) {
                next3 = null;
                break;
            }
            next3 = it3.next();
        } while (!ComposableTargetAnnotationsTransformerKt.isComposableTargetMarked((IrConstructorCall) next3));
        IrConstructorCall irConstructorCall3 = (IrConstructorCall) next3;
        if (irConstructorCall3 != null && (fqNameWhenAvailable = IrUtilsKt.getFqNameWhenAvailable(IrUtilsKt.getParentAsClass(irConstructorCall3.getSymbol().getOwner()))) != null) {
            token = new Token(fqNameWhenAvailable.asString());
        }
        return token != null ? token : new Open(-1, true);
    }

    public final boolean hasSchemeSpecified(IrFunction irFunction) {
        irFunction.getClass();
        List<IrAnnotation> annotations = irFunction.getAnnotations();
        if ((annotations instanceof Collection) && annotations.isEmpty()) {
            return false;
        }
        for (IrAnnotation irAnnotation : annotations) {
            if (ComposableTargetAnnotationsTransformerKt.isComposableTarget(irAnnotation) || ComposableTargetAnnotationsTransformerKt.isComposableOpenTarget(irAnnotation) || ComposableTargetAnnotationsTransformerKt.isComposableInferredTarget(irAnnotation) || ComposableTargetAnnotationsTransformerKt.isComposableTargetMarked(irAnnotation)) {
                return true;
            }
        }
        return false;
    }

    public final boolean hasTransformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(IrCall irCall) {
        irCall.getClass();
        return Intrinsics.areEqual(WeakBindingTraceKt.getIrTrace(getContext()).get(ComposeWritableSlices.INSTANCE.getHAS_TRANSFORMED_LAMBDA(), irCall), Boolean.TRUE);
    }

    public final InferenceFunctionDeclaration inferenceFunctionOf(IrFunction function) {
        function.getClass();
        return new InferenceFunctionDeclaration(this, function);
    }

    public final InferenceFunctionType inferenceFunctionTypeOf(IrType type) {
        type.getClass();
        return new InferenceFunctionType(this, type);
    }

    public final boolean isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(IrType irType) {
        List arguments;
        irType.getClass();
        if (!isComposableLambda(irType) && !isSamComposable(irType)) {
            IrSimpleType irSimpleType = irType instanceof IrSimpleType ? (IrSimpleType) irType : null;
            if (irSimpleType == null || (arguments = irSimpleType.getArguments()) == null) {
                return false;
            }
            List list = arguments;
            if ((list instanceof Collection) && list.isEmpty()) {
                return false;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                IrType typeOrNull = IrTypesKt.getTypeOrNull((IrTypeArgument) it.next());
                if (typeOrNull == null || !isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(typeOrNull)) {
                }
            }
            return false;
        }
        return true;
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        if (this.ComposableTargetClass == null || this.ComposableInferredTargetClass == null || this.ComposableOpenTargetClass == null) {
            return;
        }
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
    }

    public final IrFunctionExpression singletonFunctionExpression$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(IrCall irCall) {
        IrFunctionExpression irFunctionExpressionFindTransformedLambda;
        irCall.getClass();
        IrBody body = irCall.getSymbol().getOwner().getBody();
        if (body != null && (irFunctionExpressionFindTransformedLambda = findTransformedLambda(body)) != null) {
            return irFunctionExpressionFindTransformedLambda;
        }
        f2f.a("Could not find the singleton lambda for ", DumpIrTreeKt.dump$default(irCall, (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }

    public final Scheme toScheme(IrType irType, Item item) {
        irType.getClass();
        item.getClass();
        List arguments = ((irType instanceof IrSimpleType) && IrTypeUtilsKt.isFunction(irType)) ? ((IrSimpleType) irType).getArguments() : CollectionsKt.emptyList();
        Item target = getTarget(irType.getAnnotations());
        Item item2 = target.getIsUnspecified() ? item : target;
        List listTakeUpTo = ComposableTargetAnnotationsTransformerKt.takeUpTo(arguments, arguments.size() - 1);
        ArrayList arrayList = new ArrayList();
        Iterator it = listTakeUpTo.iterator();
        while (it.hasNext()) {
            Scheme scheme$lambda$0$toScheme = toScheme$lambda$0$toScheme(this, item, (IrTypeArgument) it.next());
            if (scheme$lambda$0$toScheme != null) {
                arrayList.add(scheme$lambda$0$toScheme);
            }
        }
        IrTypeArgument irTypeArgument = (IrTypeArgument) CollectionsKt.lastOrNull(arguments);
        return new Scheme(item2, arrayList, irTypeArgument != null ? toScheme$lambda$0$toScheme(this, item, irTypeArgument) : null, false, 8, null);
    }

    public final IrFunctionExpression transformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(IrElement irElement) {
        irElement.getClass();
        IrFunctionExpression irFunctionExpressionFindTransformedLambda = findTransformedLambda(irElement);
        if (irFunctionExpressionFindTransformedLambda != null) {
            return irFunctionExpressionFindTransformedLambda;
        }
        f2f.a("Could not find the lambda for ", DumpIrTreeKt.dump$default(irElement, (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x00f9  */
    public IrExpression visitCall(IrCall expression) {
        InferenceNode inferenceNodeResolveExpressionOrNull;
        IrType type;
        IrExpression dispatchReceiver;
        IrType type2;
        expression.getClass();
        IrFunction irFunction = this.currentOwner;
        if (irFunction != null && (isComposableCall(expression) || hasComposableArguments(expression))) {
            FqName fqNameWhenAvailable = IrUtilsKt.getFqNameWhenAvailable(expression.getSymbol().getOwner());
            ComposeFqNames composeFqNames = ComposeFqNames.INSTANCE;
            if (!Intrinsics.areEqual(fqNameWhenAvailable, composeFqNames.getGetCurrentComposerFullName()) && !Intrinsics.areEqual(fqNameWhenAvailable, composeFqNames.getComposableLambdaFullName())) {
                this.ownerMap.put(expression, irFunction);
                IrExpression irExpressionVisitCall = super.visitCall(expression);
                if (isInvoke(expression) || !((dispatchReceiver = expression.getDispatchReceiver()) == null || (type2 = dispatchReceiver.getType()) == null || !isSamComposable(type2))) {
                    IrExpression dispatchReceiver2 = expression.getDispatchReceiver();
                    inferenceNodeResolveExpressionOrNull = dispatchReceiver2 != null ? resolveExpressionOrNull(dispatchReceiver2) : null;
                } else {
                    inferenceNodeResolveExpressionOrNull = resolveExpressionOrNull(expression);
                }
                if (inferenceNodeResolveExpressionOrNull == null) {
                    inferenceNodeResolveExpressionOrNull = new InferenceCallTargetNode(this, expression);
                }
                if (inferenceNodeResolveExpressionOrNull.isOverlyWide()) {
                    return irExpressionVisitCall;
                }
                List targetArguments = ComposableTargetAnnotationsTransformerKt.getTargetArguments(expression);
                ArrayList arrayList = new ArrayList();
                int i = 0;
                for (Object obj : targetArguments) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    IrContainerExpression irContainerExpression = (IrExpression) obj;
                    if (irContainerExpression != null) {
                        if (isComposableLambda((IrElement) irContainerExpression) || isComposableParameter(irContainerExpression)) {
                            arrayList.add(obj);
                        } else {
                            if (((irContainerExpression instanceof IrContainerExpression) && Intrinsics.areEqual(irContainerExpression.getOrigin(), IrStatementOrigin.Companion.getDEFAULT_VALUE())) || (irContainerExpression instanceof IrBlock) || IrUtilsKt.isNullConst(irContainerExpression)) {
                                List targetParameters = ComposableTargetAnnotationsTransformerKt.getTargetParameters(expression.getSymbol().getOwner());
                                type = i < targetParameters.size() ? ((IrValueParameter) targetParameters.get(i)).getType() : null;
                            } else {
                                type = irContainerExpression.getType();
                            }
                            if (type != null && isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(type)) {
                                arrayList.add(obj);
                            }
                        }
                    }
                    i = i2;
                }
                List mutableList = CollectionsKt.toMutableList(CollectionsKt.filterNotNull(arrayList));
                ApplierInferencer<InferenceFunction, InferenceNode> applierInferencer = this.infer;
                InferenceNode inferenceNodeInferenceNodeOf = ComposableTargetAnnotationsTransformerKt.inferenceNodeOf(expression, getTransformer());
                List<IrExpression> list = mutableList;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (IrExpression irExpression : list) {
                    InferenceNode inferenceNodeResolveExpressionOrNull2 = resolveExpressionOrNull(irExpression);
                    if (inferenceNodeResolveExpressionOrNull2 == null) {
                        inferenceNodeResolveExpressionOrNull2 = ComposableTargetAnnotationsTransformerKt.inferenceNodeOf(irExpression, getTransformer());
                    }
                    arrayList2.add(inferenceNodeResolveExpressionOrNull2);
                }
                applierInferencer.visitCall(inferenceNodeInferenceNodeOf, inferenceNodeResolveExpressionOrNull, arrayList2);
                return irExpressionVisitCall;
            }
        }
        return super.visitCall(expression);
    }

    public IrFile visitFile(IrFile declaration) throws Exception {
        declaration.getClass();
        try {
            this.currentFile = declaration;
            IrFile irFileVisitFile = super.visitFile(declaration);
            this.currentFile = null;
            return irFileVisitFile;
        } catch (Exception e) {
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
            throw new Exception("IR lowering failed at: " + IrDeclarationsKt.getName(declaration), e);
        }
    }

    public IrStatement visitFunction(IrFunction declaration) {
        declaration.getClass();
        if (hasSchemeSpecified(declaration) || (!(isComposable(declaration) || hasComposableParameter(declaration)) || ComposableTargetAnnotationsTransformerKt.hasOverlyWideParameters(declaration) || ComposableTargetAnnotationsTransformerKt.hasOpenTypeParameters(declaration))) {
            return super.visitFunction(declaration);
        }
        IrFunction irFunction = this.currentOwner;
        this.currentOwner = declaration;
        Ref.IntRef intRef = new Ref.IntRef();
        Iterator it = ComposableTargetAnnotationsTransformerKt.getTargetParameters(declaration).iterator();
        while (it.hasNext()) {
            visitFunction$recordParameter(this, declaration, intRef, (IrValueParameter) it.next());
        }
        IrStatement irStatementVisitFunction = super.visitFunction(declaration);
        this.currentOwner = irFunction;
        return irStatementVisitFunction;
    }

    public IrStatement visitLocalDelegatedProperty(IrLocalDelegatedProperty declaration) {
        IrVariableSymbol symbol;
        InferenceVariable inferenceVariable;
        declaration.getClass();
        IrStatement irStatementVisitLocalDelegatedProperty = super.visitLocalDelegatedProperty(declaration);
        if (isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(declaration.getType())) {
            Map<IrSymbol, InferenceVariable> map = this.variableDeclarations;
            IrVariable delegate = declaration.getDelegate();
            if (delegate != null && (symbol = delegate.getSymbol()) != null && (inferenceVariable = map.get(symbol)) != null) {
                this.variableDeclarations.put(declaration.getGetter().getSymbol(), inferenceVariable);
            }
        }
        return irStatementVisitLocalDelegatedProperty;
    }

    public IrStatement visitVariable(IrVariable declaration) {
        declaration.getClass();
        if (isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(declaration.getType())) {
            IrFunction irFunction = this.currentOwner;
            if (irFunction != null) {
                this.ownerMap.put(declaration, irFunction);
            }
            IrExpression initializer = declaration.getInitializer();
            if (initializer != null) {
                InferenceNode inferenceNodeResolveExpressionOrNull = resolveExpressionOrNull(initializer);
                if (inferenceNodeResolveExpressionOrNull == null) {
                    inferenceNodeResolveExpressionOrNull = new InferenceElementExpression(getTransformer(), initializer);
                }
                InferenceVariable inferenceVariable = new InferenceVariable(this, declaration);
                this.variableDeclarations.put(declaration.getSymbol(), inferenceVariable);
                this.infer.visitVariable(inferenceVariable, inferenceNodeResolveExpressionOrNull);
            }
        }
        return super.visitVariable(declaration);
    }

    public final void addAnnotationToDeclaration(IrDeclaration declaration, Scheme scheme) {
        declaration.getClass();
        scheme.getClass();
        addMetadataVisibleAnnotations(declaration, toAnnotations(scheme));
    }

    private final List<IrAnnotation> toAnnotations(Item item) {
        List<IrAnnotation> listListOf;
        IrAnnotation annotation = toAnnotation(item);
        return (annotation == null || (listListOf = CollectionsKt.listOf(annotation)) == null) ? CollectionsKt.emptyList() : listListOf;
    }

    private final boolean isComposable(IrType irType) {
        return isComposableLambda(irType) || isSamComposable(irType);
    }

    private final boolean isComposableLambda(IrElement irElement) {
        if (irElement instanceof IrFunctionExpression) {
            return isComposable((IrFunction) ((IrFunctionExpression) irElement).getFunction());
        }
        if (irElement instanceof IrCall) {
            IrCall irCall = (IrCall) irElement;
            return isComposableSingletonGetter(irCall) || hasTransformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(irCall);
        }
        if (irElement instanceof IrGetField) {
            IrExpressionBody initializer = ((IrGetField) irElement).getSymbol().getOwner().getInitializer();
            if ((initializer != null ? findTransformedLambda(initializer) : null) != null) {
                return true;
            }
        }
        return false;
    }
}
