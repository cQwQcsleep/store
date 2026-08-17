package org.jetbrains.kotlin.psi.psiUtil;

import com.intellij.psi.PsiElement;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 176)
public final class PsiUtilsKt$collectDescendantsOfType$$inlined$collectDescendantsOfType$1<T> implements Function1<T, Unit> {
    final /* synthetic */ Function1 $predicate;
    final /* synthetic */ Collection $to;

    public PsiUtilsKt$collectDescendantsOfType$$inlined$collectDescendantsOfType$1(Function1 function1, Collection collection) {
        this.$predicate = function1;
        this.$to = collection;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    public final void invoke(PsiElement psiElement) {
        psiElement.getClass();
        if (((Boolean) this.$predicate.invoke(psiElement)).booleanValue()) {
            this.$to.add(psiElement);
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PsiElement) obj);
        return Unit.INSTANCE;
    }
}
