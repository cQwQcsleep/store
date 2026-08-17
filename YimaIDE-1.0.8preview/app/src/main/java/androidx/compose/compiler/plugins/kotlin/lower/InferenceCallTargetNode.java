package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceCallTargetNode;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "element", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;Lorg/jetbrains/kotlin/ir/expressions/IrCall;)V", "getElement", "()Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "equals", "", "other", "", "hashCode", "", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "function", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "getFunction", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "referenceContainer", "getReferenceContainer", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InferenceCallTargetNode extends InferenceNode {
    private final IrCall element;
    private final InferenceFunction function;
    private final InferenceNode referenceContainer;
    private final ComposableTargetAnnotationsTransformer transformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferenceCallTargetNode(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer, IrCall irCall) {
        super(null);
        composableTargetAnnotationsTransformer.getClass();
        irCall.getClass();
        InferenceNode inferenceNodeInferenceNodeOf = null;
        this.transformer = composableTargetAnnotationsTransformer;
        this.element = irCall;
        this.function = composableTargetAnnotationsTransformer.hasSchemeSpecified(getElement().getSymbol().getOwner()) ? new InferenceFunctionDeclaration(composableTargetAnnotationsTransformer, getElement().getSymbol().getOwner()) : new InferenceFunctionCallType(composableTargetAnnotationsTransformer, getElement());
        if (!ComposableTargetAnnotationsTransformerKt.isGenericFunction(getElement().getSymbol())) {
            IrSimpleFunction function = composableTargetAnnotationsTransformer.isComposableSingletonGetter(getElement()) ? composableTargetAnnotationsTransformer.singletonFunctionExpression$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(getElement()).getFunction() : composableTargetAnnotationsTransformer.hasTransformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(getElement()) ? composableTargetAnnotationsTransformer.transformedLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(getElement()).getFunction() : getElement().getSymbol().getOwner();
            if (((function.getBody() == null || !function.getTypeParameters().isEmpty()) ? null : function) != null) {
                inferenceNodeInferenceNodeOf = ComposableTargetAnnotationsTransformerKt.inferenceNodeOf(function, composableTargetAnnotationsTransformer);
            }
        }
        this.referenceContainer = inferenceNodeInferenceNodeOf;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public boolean equals(Object other) {
        return (other instanceof InferenceCallTargetNode) && super.equals(other);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceFunction getFunction() {
        return this.function;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public NodeKind getKind() {
        return NodeKind.Function;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceNode getReferenceContainer() {
        return this.referenceContainer;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public int hashCode() {
        return super.hashCode() * 31;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public IrCall getElement() {
        return this.element;
    }
}
