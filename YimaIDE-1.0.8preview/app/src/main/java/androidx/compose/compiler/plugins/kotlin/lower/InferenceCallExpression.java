package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.expressions.IrCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceCallExpression;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "element", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;Lorg/jetbrains/kotlin/ir/expressions/IrCall;)V", "getElement", "()Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "isSingletonLambda", "", "isTransformedLambda", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "function", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "getFunction", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "referenceContainer", "getReferenceContainer", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InferenceCallExpression extends InferenceNode {
    private final IrCall element;
    private final InferenceFunction function;
    private final boolean isSingletonLambda;
    private final boolean isTransformedLambda;
    private final ComposableTargetAnnotationsTransformer transformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferenceCallExpression(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer, IrCall irCall) {
        super(null);
        composableTargetAnnotationsTransformer.getClass();
        irCall.getClass();
        this.transformer = composableTargetAnnotationsTransformer;
        this.element = irCall;
        boolean zIsComposableSingletonGetter = composableTargetAnnotationsTransformer.isComposableSingletonGetter(getElement());
        this.isSingletonLambda = zIsComposableSingletonGetter;
        boolean zHasTransformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin = composableTargetAnnotationsTransformer.hasTransformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(getElement());
        this.isTransformedLambda = zHasTransformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin;
        this.function = zIsComposableSingletonGetter ? composableTargetAnnotationsTransformer.inferenceFunctionOf(composableTargetAnnotationsTransformer.singletonFunctionExpression$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(getElement()).getFunction()) : zHasTransformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin ? composableTargetAnnotationsTransformer.inferenceFunctionOf(composableTargetAnnotationsTransformer.transformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(getElement()).getFunction()) : composableTargetAnnotationsTransformer.inferenceFunctionTypeOf(getElement().getType());
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceFunction getFunction() {
        return this.function;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public NodeKind getKind() {
        return (this.isSingletonLambda || this.isTransformedLambda) ? NodeKind.Lambda : NodeKind.Expression;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceNode getReferenceContainer() {
        ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer = this.transformer;
        if (this.isSingletonLambda) {
            return ComposableTargetAnnotationsTransformerKt.inferenceNodeOf(composableTargetAnnotationsTransformer.singletonFunctionExpression$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(getElement()).getFunction(), this.transformer);
        }
        if (this.isTransformedLambda) {
            return ComposableTargetAnnotationsTransformerKt.inferenceNodeOf(composableTargetAnnotationsTransformer.transformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(getElement()).getFunction(), this.transformer);
        }
        return null;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public IrCall getElement() {
        return this.element;
    }
}
