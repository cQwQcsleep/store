package androidx.compose.compiler.plugins.kotlin.k1;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k1/ResolvedPsiParameterReference;", "Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNode;", "element", "Lcom/intellij/psi/PsiElement;", "type", "Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;", "index", "", "container", "<init>", "(Lcom/intellij/psi/PsiElement;Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;ILcom/intellij/psi/PsiElement;)V", "getType", "()Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;", "getIndex", "()I", "getContainer", "()Lcom/intellij/psi/PsiElement;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class ResolvedPsiParameterReference extends InferenceNode {
    private final PsiElement container;
    private final int index;
    private final InferenceNodeType type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResolvedPsiParameterReference(PsiElement psiElement, InferenceNodeType inferenceNodeType, int i, PsiElement psiElement2) {
        super(psiElement, null);
        psiElement.getClass();
        inferenceNodeType.getClass();
        psiElement2.getClass();
        this.type = inferenceNodeType;
        this.index = i;
        this.container = psiElement2;
    }

    public final PsiElement getContainer() {
        return this.container;
    }

    public final int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k1.InferenceNode
    public NodeKind getKind() {
        return NodeKind.ParameterReference;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.k1.InferenceNode
    public InferenceNodeType getType() {
        return this.type;
    }
}
