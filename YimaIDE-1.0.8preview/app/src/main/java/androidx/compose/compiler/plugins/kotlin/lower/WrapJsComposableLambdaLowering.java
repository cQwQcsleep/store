package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds;
import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.lower.WrapJsComposableLambdaLowering;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrFunctionExpressionImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrFunctionReferenceImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DeepCopyIrTreeWithSymbols;
import org.jetbrains.kotlin.ir.util.DeepCopySymbolRemapper;
import org.jetbrains.kotlin.ir.util.DeepCopyTypeRemapper;
import org.jetbrains.kotlin.ir.util.DescriptorsRemapper;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.PatchDeclarationParentsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0019H\u0002J\"\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u001b2\b\u0010#\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010$\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u001bH\u0002J\u0018\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0002J(\u0010*\u001a\u00020)2\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010+\u001a\u00020\r2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-H\u0002R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006/"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/WrapJsComposableLambdaLowering;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "rememberFunSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getRememberFunSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "rememberFunSymbol$delegate", "Lkotlin/Lazy;", "shouldSkipLowering", "", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "visitCall", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "functionReferenceForComposableLambda", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrFunctionReferenceImpl;", "lambda", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionExpression;", "dispatchReceiver", "transformComposableLambdaCall", "originalCall", "currentComposer", "transformComposableLambdaInstanceCall", "callRun", "returnType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "runBlock", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrFunctionExpressionImpl;", "createLambda0", "functionSymbol", "statements", "", "Lorg/jetbrains/kotlin/ir/IrStatement;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WrapJsComposableLambdaLowering extends AbstractComposeLowering {

    /* JADX INFO: renamed from: rememberFunSymbol$delegate, reason: from kotlin metadata */
    private final Lazy rememberFunSymbol;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WrapJsComposableLambdaLowering(final IrPluginContext irPluginContext, final ModuleMetrics moduleMetrics, final StabilityInferencer stabilityInferencer, final FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        this.rememberFunSymbol = LazyKt.lazy(new Function0() { // from class: suf
            public final Object invoke() {
                return WrapJsComposableLambdaLowering.k(irPluginContext, stabilityInferencer, moduleMetrics, featureFlags, this);
            }
        });
    }

    private final IrCall callRun(IrType returnType, IrFunctionExpressionImpl runBlock) {
        FqName fqName = new FqName("kotlin");
        Name nameIdentifier = Name.identifier("run");
        nameIdentifier.getClass();
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(-2, -2, returnType, getTopLevelFunction(new CallableId(fqName, nameIdentifier)), 1, (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
        irCallImplIrCallImpl$default.getTypeArguments().set(0, returnType);
        irCallImplIrCallImpl$default.getArguments().set(0, runBlock);
        return irCallImplIrCallImpl$default;
    }

    private final IrFunctionExpressionImpl createLambda0(IrType returnType, IrSimpleFunctionSymbol functionSymbol, List<? extends IrStatement> statements) {
        IrSimpleType irSimpleTypeTypeWith = IrTypesKt.typeWith(getContext().getIrBuiltIns().functionN(0), new IrType[]{returnType});
        IrStatementOriginImpl lambda = IrStatementOrigin.Companion.getLAMBDA();
        IrFactory irFactory = getContext().getIrFactory();
        IrDeclarationOrigin local_function_for_lambda = IrDeclarationOrigin.Companion.getLOCAL_FUNCTION_FOR_LAMBDA();
        Name name = SpecialNames.ANONYMOUS;
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.LOCAL;
        descriptorVisibility.getClass();
        IrSimpleFunction irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactory, -2, -2, local_function_for_lambda, name, descriptorVisibility, true, false, returnType, Modality.FINAL, functionSymbol, false, false, false, false, false, (DeserializedContainerSource) null, false, 98304, (Object) null);
        IrBlockBody irBlockBodyCreateBlockBody = getContext().getIrFactory().createBlockBody(-2, -2);
        irBlockBodyCreateBlockBody.getStatements().addAll(statements);
        irSimpleFunctionCreateSimpleFunction$default.setBody(irBlockBodyCreateBlockBody);
        return BuildersKt.IrFunctionExpressionImpl(-2, -2, irSimpleTypeTypeWith, irSimpleFunctionCreateSimpleFunction$default, lambda);
    }

    public static /* synthetic */ IrFunctionExpressionImpl createLambda0$default(WrapJsComposableLambdaLowering wrapJsComposableLambdaLowering, IrType irType, IrSimpleFunctionSymbol irSimpleFunctionSymbol, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            irSimpleFunctionSymbol = new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        }
        return wrapJsComposableLambdaLowering.createLambda0(irType, irSimpleFunctionSymbol, list);
    }

    private final IrFunctionReferenceImpl functionReferenceForComposableLambda(IrFunctionExpression lambda, IrExpression dispatchReceiver) {
        int i;
        int size = lambda.getFunction().getParameters().size();
        Object obj = null;
        boolean z = false;
        for (Object obj2 : IrUtilsKt.getFunctions(getTopLevelClass(ComposeClassIds.INSTANCE.getComposableLambda()))) {
            IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) obj2;
            if (Intrinsics.areEqual(irSimpleFunctionSymbol.getOwner().getName().asString(), "invoke")) {
                List parameters = irSimpleFunctionSymbol.getOwner().getParameters();
                if ((parameters instanceof Collection) && parameters.isEmpty()) {
                    i = 0;
                } else {
                    Iterator it = parameters.iterator();
                    i = 0;
                    while (it.hasNext()) {
                        if (((IrValueParameter) it.next()).getKind() != IrParameterKind.DispatchReceiver && (i = i + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                if (size != i) {
                    continue;
                } else {
                    if (z) {
                        w01.a("Sequence contains more than one matching element.");
                        return null;
                    }
                    z = true;
                    obj = obj2;
                }
            }
        }
        if (!z) {
            hb9.a("Sequence contains no element matching the predicate.");
            return null;
        }
        IrSimpleFunctionSymbol irSimpleFunctionSymbol2 = (IrSimpleFunctionSymbol) obj;
        IrFunctionReferenceImpl irFunctionReferenceImplIrFunctionReferenceImpl$default = BuildersKt.IrFunctionReferenceImpl$default(-1, -1, lambda.getType(), irSimpleFunctionSymbol2, irSimpleFunctionSymbol2.getOwner().getTypeParameters().size(), (IrFunctionSymbol) null, (IrStatementOrigin) null, 96, (Object) null);
        irFunctionReferenceImplIrFunctionReferenceImpl$default.setDispatchReceiver(dispatchReceiver);
        return irFunctionReferenceImplIrFunctionReferenceImpl$default;
    }

    private final IrSimpleFunctionSymbol getRememberFunSymbol() {
        return (IrSimpleFunctionSymbol) this.rememberFunSymbol.getValue();
    }

    public static IrSimpleFunctionSymbol k(IrPluginContext irPluginContext, StabilityInferencer stabilityInferencer, ModuleMetrics moduleMetrics, FeatureFlags featureFlags, WrapJsComposableLambdaLowering wrapJsComposableLambdaLowering) {
        ComposerParamTransformer composerParamTransformer = new ComposerParamTransformer(irPluginContext, stabilityInferencer, moduleMetrics, featureFlags);
        List<IrSimpleFunctionSymbol> topLevelFunctions = wrapJsComposableLambdaLowering.getTopLevelFunctions(ComposeCallableIds.INSTANCE.getRemember());
        ArrayList<IrSimpleFunction> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(topLevelFunctions, 10));
        Iterator<T> it = topLevelFunctions.iterator();
        while (it.hasNext()) {
            arrayList.add(((IrSimpleFunctionSymbol) it.next()).getOwner());
        }
        for (IrSimpleFunction irSimpleFunction : arrayList) {
            if (irSimpleFunction.getParameters().size() == 2 && !AdditionalIrUtilsKt.isVararg((IrValueParameter) CollectionsKt.first(irSimpleFunction.getParameters()))) {
                IrSimpleFunction irSimpleFunctionVisitSimpleFunction = composerParamTransformer.visitSimpleFunction((IrSimpleFunction) irSimpleFunction.getSymbol().getOwner());
                irSimpleFunctionVisitSimpleFunction.getClass();
                return irSimpleFunctionVisitSimpleFunction.getSymbol();
            }
        }
        hb9.a("Collection contains no element matching the predicate.");
        return null;
    }

    private final boolean shouldSkipLowering() {
        IrClass irClassFunctionN = getContext().getIrBuiltIns().functionN(2);
        List superTypes = getTopLevelClass(ComposeClassIds.INSTANCE.getComposableLambda()).getOwner().getSuperTypes();
        if ((superTypes instanceof Collection) && superTypes.isEmpty()) {
            return false;
        }
        Iterator it = superTypes.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(IrTypesKt.getClassOrNull((IrType) it.next()), irClassFunctionN)) {
                return true;
            }
        }
        return false;
    }

    private final IrExpression transformComposableLambdaCall(IrCall originalCall, IrExpression currentComposer, IrFunctionExpression lambda) {
        IrVariableImpl irVariableImplIrTemporary$default = AbstractComposeLowering.irTemporary$default(this, originalCall, "dispatchReceiver", null, false, null, 28, null);
        IrFunctionReferenceImpl irFunctionReferenceImplFunctionReferenceForComposableLambda = functionReferenceForComposableLambda(lambda, irGet(irVariableImplIrTemporary$default));
        IrSimpleFunctionSymbolImpl irSimpleFunctionSymbolImpl = new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        IrFunctionExpressionImpl irFunctionExpressionImplCreateLambda0 = createLambda0(lambda.getType(), irSimpleFunctionSymbolImpl, CollectionsKt.listOf(AbstractComposeLowering.irReturn$default(this, irSimpleFunctionSymbolImpl, irFunctionReferenceImplFunctionReferenceForComposableLambda, null, 4, null)));
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(-2, -2, lambda.getType(), getRememberFunSymbol(), 1, (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
        irCallImplIrCallImpl$default.getTypeArguments().set(0, lambda.getType());
        irCallImplIrCallImpl$default.getArguments().set(0, irGet(irVariableImplIrTemporary$default));
        irCallImplIrCallImpl$default.getArguments().set(1, irFunctionExpressionImplCreateLambda0);
        irCallImplIrCallImpl$default.getArguments().set(2, currentComposer);
        irCallImplIrCallImpl$default.getArguments().set(3, irConst(0));
        IrSimpleFunctionSymbolImpl irSimpleFunctionSymbolImpl2 = new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        IrType type = lambda.getType();
        ArrayList arrayList = new ArrayList();
        arrayList.add(irVariableImplIrTemporary$default);
        arrayList.add(AbstractComposeLowering.irReturn$default(this, irSimpleFunctionSymbolImpl2, irCallImplIrCallImpl$default, null, 4, null));
        Unit unit = Unit.INSTANCE;
        return callRun(lambda.getType(), createLambda0(type, irSimpleFunctionSymbolImpl2, arrayList));
    }

    private final IrExpression transformComposableLambdaInstanceCall(IrCall originalCall) {
        Object objLast = CollectionsKt.last(originalCall.getArguments());
        objLast.getClass();
        return functionReferenceForComposableLambda((IrFunctionExpression) objLast, originalCall);
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        if (shouldSkipLowering()) {
            return;
        }
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
        PatchDeclarationParentsKt.patchDeclarationParents$default(irModule, (IrDeclarationParent) null, 1, (Object) null);
    }

    public IrExpression visitCall(IrCall expression) {
        expression.getClass();
        IrExpression irExpressionVisitCall = super.visitCall(expression);
        irExpressionVisitCall.getClass();
        IrCall irCall = (IrCall) irExpressionVisitCall;
        FqName fqNameForIrSerialization = AdditionalIrUtilsKt.getFqNameForIrSerialization(expression.getSymbol().getOwner());
        ComposeCallableIds composeCallableIds = ComposeCallableIds.INSTANCE;
        if (Intrinsics.areEqual(fqNameForIrSerialization, composeCallableIds.getComposableLambda().asSingleFqName())) {
            DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
            IrVisitorsKt.acceptVoid(irCall, deepCopySymbolRemapper);
            IrElement irElementTransform = irCall.transform(new DeepCopyIrTreeWithSymbols(deepCopySymbolRemapper, new DeepCopyTypeRemapper(deepCopySymbolRemapper)), (Object) null);
            if (irElementTransform == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.expressions.IrCall");
                return null;
            }
            IrExpression irExpression = (IrExpression) CollectionsKt.first(irCall.getArguments());
            Object objLast = CollectionsKt.last(irCall.getArguments());
            objLast.getClass();
            return transformComposableLambdaCall((IrCall) irElementTransform, irExpression, (IrFunctionExpression) objLast);
        }
        if (!Intrinsics.areEqual(fqNameForIrSerialization, composeCallableIds.getRememberComposableLambda().asSingleFqName())) {
            return Intrinsics.areEqual(fqNameForIrSerialization, composeCallableIds.getComposableLambdaInstance().asSingleFqName()) ? transformComposableLambdaInstanceCall(irCall) : irCall;
        }
        DeepCopySymbolRemapper deepCopySymbolRemapper2 = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
        IrVisitorsKt.acceptVoid(irCall, deepCopySymbolRemapper2);
        IrElement irElementTransform2 = irCall.transform(new DeepCopyIrTreeWithSymbols(deepCopySymbolRemapper2, new DeepCopyTypeRemapper(deepCopySymbolRemapper2)), (Object) null);
        if (irElementTransform2 == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.expressions.IrCall");
            return null;
        }
        IrExpression irExpression2 = (IrExpression) irCall.getArguments().get(3);
        Object obj = irCall.getArguments().get(2);
        obj.getClass();
        return transformComposableLambdaCall((IrCall) irElementTransform2, irExpression2, (IrFunctionExpression) obj);
    }
}
