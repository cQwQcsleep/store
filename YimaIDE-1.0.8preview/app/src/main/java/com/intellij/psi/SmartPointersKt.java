package com.intellij.psi;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"createSmartPointer", "Lcom/intellij/psi/SmartPsiElementPointer;", "E", "Lcom/intellij/psi/PsiElement;", "(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/SmartPsiElementPointer;", "intellij.platform.core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SmartPointersKt {
    public static final <E extends PsiElement> SmartPsiElementPointer<E> createSmartPointer(E e) {
        e.getClass();
        SmartPsiElementPointer<E> smartPsiElementPointerCreateSmartPsiElementPointer = SmartPointerManager.getInstance(e.getProject()).createSmartPsiElementPointer(e);
        smartPsiElementPointerCreateSmartPsiElementPointer.getClass();
        return smartPsiElementPointerCreateSmartPsiElementPointer;
    }
}
