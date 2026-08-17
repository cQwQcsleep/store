package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import org.jetbrains.kotlin.fir.FirElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/FirParameterReferenceNode;", "Landroidx/compose/compiler/plugins/kotlin/k2/FirElementInferenceNode;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "parameterIndex", "", "referenceContainer", "Landroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;ILandroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;)V", "getParameterIndex", "()I", "getReferenceContainer", "()Landroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class FirParameterReferenceNode extends FirElementInferenceNode {
    private final int parameterIndex;
    private final FirInferenceNode referenceContainer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirParameterReferenceNode(FirElement firElement, int i, FirInferenceNode firInferenceNode) {
        super(firElement);
        firElement.getClass();
        firInferenceNode.getClass();
        this.parameterIndex = i;
        this.referenceContainer = firInferenceNode;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public NodeKind getKind() {
        return NodeKind.ParameterReference;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public int getParameterIndex() {
        return this.parameterIndex;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public FirInferenceNode getReferenceContainer() {
        return this.referenceContainer;
    }

    public String toString() {
        return "param:" + getParameterIndex();
    }
}
