package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0001H\u0016J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0096\u0082\u0004J\n\u0010\u001f\u001a\u00020\bH\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0010¨\u0006 "}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceResolvedParameter;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "element", "Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;", "function", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "container", "index", "", "<init>", "(Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;I)V", "getElement", "()Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;", "getFunction", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "getContainer", "()Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "getIndex", "()I", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "parameterIndex", "node", "referenceContainer", "getReferenceContainer", "equals", "", "other", "", "hashCode", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InferenceResolvedParameter extends InferenceNode {
    private final InferenceNode container;
    private final IrGetValue element;
    private final InferenceFunction function;
    private final int index;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferenceResolvedParameter(IrGetValue irGetValue, InferenceFunction inferenceFunction, InferenceNode inferenceNode, int i) {
        super(null);
        irGetValue.getClass();
        inferenceFunction.getClass();
        inferenceNode.getClass();
        this.element = irGetValue;
        this.function = inferenceFunction;
        this.container = inferenceNode;
        this.index = i;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public boolean equals(Object other) {
        return (other instanceof InferenceResolvedParameter) && Intrinsics.areEqual(((InferenceResolvedParameter) other).getElement(), getElement());
    }

    public final InferenceNode getContainer() {
        return this.container;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceFunction getFunction() {
        return this.function;
    }

    public final int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public NodeKind getKind() {
        return NodeKind.ParameterReference;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public InferenceNode getReferenceContainer() {
        return this.container;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public int hashCode() {
        return (getElement().hashCode() * 31) + 103;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public int parameterIndex(InferenceNode node) {
        node.getClass();
        if (Intrinsics.areEqual(node.getFunction(), getFunction())) {
            return this.index;
        }
        return -1;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceNode
    public IrGetValue getElement() {
        return this.element;
    }
}
