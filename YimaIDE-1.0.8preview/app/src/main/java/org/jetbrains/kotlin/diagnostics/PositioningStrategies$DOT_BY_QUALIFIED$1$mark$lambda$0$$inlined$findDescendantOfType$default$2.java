package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006¸\u0006\u0007"}, d2 = {"org/jetbrains/kotlin/psi/psiUtil/PsiUtilsKt$findDescendantOfType$4", "Lcom/intellij/psi/PsiRecursiveElementWalkingVisitor;", "visitElement", "", "element", "Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:psi-api", "org/jetbrains/kotlin/psi/psiUtil/PsiUtilsKt$findDescendantOfType$$inlined$findDescendantOfType$1"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class PositioningStrategies$DOT_BY_QUALIFIED$1$mark$lambda$0$$inlined$findDescendantOfType$default$2 extends PsiRecursiveElementWalkingVisitor {
    final /* synthetic */ Function1 $predicate;
    final /* synthetic */ Ref.ObjectRef $result;

    public PositioningStrategies$DOT_BY_QUALIFIED$1$mark$lambda$0$$inlined$findDescendantOfType$default$2(Function1 function1, Ref.ObjectRef objectRef) {
        this.$predicate = function1;
        this.$result = objectRef;
    }

    public void visitElement(PsiElement element) {
        element.getClass();
        if (!(element instanceof KtDotQualifiedExpression) || !((Boolean) this.$predicate.invoke(element)).booleanValue()) {
            super.visitElement(element);
        } else {
            this.$result.element = element;
            stopWalking();
        }
    }
}
