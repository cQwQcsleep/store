package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/FirCallableElementInferenceNode;", "Landroidx/compose/compiler/plugins/kotlin/k2/FirElementInferenceNode;", "callable", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/FirElement;)V", "getCallable", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "type", "Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "getType", "()Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class FirCallableElementInferenceNode extends FirElementInferenceNode {
    private final FirCallableSymbol<?> callable;
    private final InferenceNodeType type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirCallableElementInferenceNode(FirCallableSymbol<?> firCallableSymbol, FirElement firElement) {
        super(firElement);
        firCallableSymbol.getClass();
        firElement.getClass();
        this.callable = firCallableSymbol;
        this.type = new InferenceCallableType(firCallableSymbol);
    }

    public final FirCallableSymbol<?> getCallable() {
        return this.callable;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public NodeKind getKind() {
        return NodeKind.Function;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirElementInferenceNode, androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public InferenceNodeType getType() {
        return this.type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.callable.getName());
        sb.append("()@");
        KtSourceElement source = getElement().getSource();
        sb.append(source != null ? Integer.valueOf(source.getStartOffset()) : null);
        return sb.toString();
    }
}
