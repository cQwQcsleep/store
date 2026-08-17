package com.intellij.psi;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface PsiAnonymousClass extends PsiClass {
    PsiExpressionList getArgumentList();

    PsiJavaCodeReferenceElement getBaseClassReference();

    PsiClassType getBaseClassType();

    String getName();

    @Override // 
    PsiIdentifier getNameIdentifier();

    String getQualifiedName();

    boolean isInQualifiedNew();
}
