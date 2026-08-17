package com.intellij.psi;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface PsiNameIdentifierOwner extends PsiNamedElement {
    default PsiElement getIdentifyingElement() {
        return getNameIdentifier();
    }

    PsiElement getNameIdentifier();
}
