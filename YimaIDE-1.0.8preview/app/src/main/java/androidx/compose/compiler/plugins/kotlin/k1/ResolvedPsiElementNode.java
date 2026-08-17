package androidx.compose.compiler.plugins.kotlin.k1;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k1/ResolvedPsiElementNode;", "Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNode;", "element", "Lcom/intellij/psi/PsiElement;", "type", "Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;", "<init>", "(Lcom/intellij/psi/PsiElement;Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;)V", "getType", "()Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class ResolvedPsiElementNode extends InferenceNode {
    private final InferenceNodeType type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResolvedPsiElementNode(PsiElement psiElement, InferenceNodeType inferenceNodeType) {
        super(psiElement, null);
        psiElement.getClass();
        inferenceNodeType.getClass();
        this.type = inferenceNodeType;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k1.InferenceNode
    public NodeKind getKind() {
        return NodeKind.Function;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k1.InferenceNode
    public InferenceNodeType getType() {
        return this.type;
    }
}
