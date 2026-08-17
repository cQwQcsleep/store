package androidx.compose.compiler.plugins.kotlin.k2;

import kotlin.Metadata;
import org.jetbrains.kotlin.fir.FirElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/FirElementInferenceNode;", "Landroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;)V", "type", "Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "getType", "()Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
class FirElementInferenceNode extends FirInferenceNode {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirElementInferenceNode(FirElement firElement) {
        super(firElement, null);
        firElement.getClass();
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public InferenceNodeType getType() {
        return null;
    }
}
