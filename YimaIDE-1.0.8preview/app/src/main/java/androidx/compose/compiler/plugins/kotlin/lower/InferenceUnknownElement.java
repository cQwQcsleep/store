package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.IrElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceUnknownElement;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "element", "Lorg/jetbrains/kotlin/ir/IrElement;", "<init>", "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "getElement", "()Lorg/jetbrains/kotlin/ir/IrElement;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "function", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "getFunction", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "referenceContainer", "getReferenceContainer", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InferenceUnknownElement extends InferenceNode {
    private final IrElement element;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferenceUnknownElement(IrElement irElement) {
        super(null);
        irElement.getClass();
        this.element = irElement;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public IrElement getElement() {
        return this.element;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceFunction getFunction() {
        return null;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public NodeKind getKind() {
        return NodeKind.Expression;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceNode getReferenceContainer() {
        return null;
    }
}
