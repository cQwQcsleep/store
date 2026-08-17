package org.jetbrains.kotlin.psi;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiReference;
import kotlin.Deprecated;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u0002J/\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\t\u001a\u0002H\u0005H&¢\u0006\u0002\u0010\nJ5\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\t\u001a\u0002H\u0005H&¢\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000fH'b\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/KtElement;", "Lcom/intellij/psi/NavigatablePsiElement;", "Lorg/jetbrains/kotlin/psi/KtPureElement;", "acceptChildren", "", "D", "visitor", "Lorg/jetbrains/kotlin/psi/KtVisitor;", "Ljava/lang/Void;", "data", "(Lorg/jetbrains/kotlin/psi/KtVisitor;Ljava/lang/Object;)V", "accept", "R", "(Lorg/jetbrains/kotlin/psi/KtVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "getReference", "Lcom/intellij/psi/PsiReference;", "Lkotlin/Deprecated;", "message", "Don't use getReference() on KtElement for the choice is unpredictable", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KtElement extends NavigatablePsiElement, KtPureElement {
    <R, D> R accept(KtVisitor<R, D> visitor, D data);

    <D> void acceptChildren(KtVisitor<Void, D> visitor, D data);

    @Deprecated(message = "Don't use getReference() on KtElement for the choice is unpredictable")
    PsiReference getReference();
}
