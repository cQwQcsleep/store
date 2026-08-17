package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import org.jetbrains.kotlin.fir.declarations.FirFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/FirFunctionInferenceNode;", "Landroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "getFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "type", "Landroidx/compose/compiler/plugins/kotlin/k2/InferenceCallableType;", "getType", "()Landroidx/compose/compiler/plugins/kotlin/k2/InferenceCallableType;", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class FirFunctionInferenceNode extends FirInferenceNode {
    private final FirFunction function;
    private final InferenceCallableType type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirFunctionInferenceNode(FirFunction firFunction) {
        super(firFunction, null);
        firFunction.getClass();
        this.function = firFunction;
        this.type = new InferenceCallableType(firFunction.getSymbol());
    }

    public final FirFunction getFunction() {
        return this.function;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public NodeKind getKind() {
        return NodeKind.Function;
    }

    public String toString() {
        String string = this.function.getSymbol().getName().toString();
        string.getClass();
        return string;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public InferenceCallableType getType() {
        return this.type;
    }
}
