package org.jetbrains.kotlin.psi;

import com.intellij.psi.PsiElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface KtPureElement {
    KtFile getContainingKtFile();

    PsiElement getParent();

    KtElement getPsiOrParent();
}
