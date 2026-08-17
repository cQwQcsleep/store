package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceVariable;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "element", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;Lorg/jetbrains/kotlin/ir/declarations/IrVariable;)V", "getElement", "()Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "function", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "getFunction", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "referenceContainer", "getReferenceContainer", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InferenceVariable extends InferenceNode {
    private final IrVariable element;
    private final ComposableTargetAnnotationsTransformer transformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferenceVariable(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer, IrVariable irVariable) {
        super(null);
        composableTargetAnnotationsTransformer.getClass();
        irVariable.getClass();
        this.transformer = composableTargetAnnotationsTransformer;
        this.element = irVariable;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceFunction getFunction() {
        return this.transformer.inferenceFunctionTypeOf(getElement().getType());
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public NodeKind getKind() {
        return NodeKind.Variable;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceNode getReferenceContainer() {
        return null;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public IrVariable getElement() {
        return this.element;
    }
}
