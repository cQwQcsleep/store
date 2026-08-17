package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/FirSamInferenceNode;", "Landroidx/compose/compiler/plugins/kotlin/k2/FirElementInferenceNode;", "sam", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;)V", "getSam", "()Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "type", "Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "getType", "()Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class FirSamInferenceNode extends FirElementInferenceNode {
    private final FirFunctionTypeConversionExpression sam;
    private final InferenceNodeType type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirSamInferenceNode(FirFunctionTypeConversionExpression firFunctionTypeConversionExpression) {
        super(firFunctionTypeConversionExpression);
        firFunctionTypeConversionExpression.getClass();
        this.sam = firFunctionTypeConversionExpression;
        FirAnonymousFunctionExpression expression = firFunctionTypeConversionExpression.getExpression();
        FirAnonymousFunctionExpression firAnonymousFunctionExpression = expression instanceof FirAnonymousFunctionExpression ? expression : null;
        this.type = firAnonymousFunctionExpression != null ? new InferenceCallableType(firAnonymousFunctionExpression.getAnonymousFunction().getSymbol()) : null;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public NodeKind getKind() {
        return NodeKind.Lambda;
    }

    public final FirFunctionTypeConversionExpression getSam() {
        return this.sam;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k2.FirElementInferenceNode, androidx.compose.compiler.plugins.kotlin.k2.FirInferenceNode
    public InferenceNodeType getType() {
        return this.type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<sam:");
        KtSourceElement source = this.sam.getSource();
        sb.append(source != null ? Integer.valueOf(source.getStartOffset()) : null);
        sb.append('>');
        return sb.toString();
    }
}
