package androidx.compose.compiler.plugins.kotlin.k1;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.psi.KtFunction;
import org.jetbrains.kotlin.psi.KtFunctionLiteral;
import org.jetbrains.kotlin.psi.KtLambdaExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0003\u0015\u0016\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNode;", "", "element", "Lcom/intellij/psi/PsiElement;", "<init>", "(Lcom/intellij/psi/PsiElement;)V", "getElement", "()Lcom/intellij/psi/PsiElement;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "type", "Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;", "getType", "()Landroidx/compose/compiler/plugins/kotlin/k1/InferenceNodeType;", "hashCode", "", "equals", "", "other", "Landroidx/compose/compiler/plugins/kotlin/k1/PsiElementNode;", "Landroidx/compose/compiler/plugins/kotlin/k1/ResolvedPsiElementNode;", "Landroidx/compose/compiler/plugins/kotlin/k1/ResolvedPsiParameterReference;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
abstract class InferenceNode {
    private final PsiElement element;

    private InferenceNode(PsiElement psiElement) {
        this.element = psiElement;
    }

    public boolean equals(Object other) {
        return (other instanceof InferenceNode) && Intrinsics.areEqual(((InferenceNode) other).element, this.element);
    }

    public final PsiElement getElement() {
        return this.element;
    }

    public NodeKind getKind() {
        PsiElement psiElement = this.element;
        if ((psiElement instanceof KtLambdaExpression) || (psiElement instanceof KtFunctionLiteral)) {
            return NodeKind.Lambda;
        }
        return psiElement instanceof KtFunction ? NodeKind.Function : NodeKind.Expression;
    }

    public abstract InferenceNodeType getType();

    public int hashCode() {
        return this.element.hashCode() * 31;
    }

    public /* synthetic */ InferenceNode(PsiElement psiElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(psiElement);
    }
}
