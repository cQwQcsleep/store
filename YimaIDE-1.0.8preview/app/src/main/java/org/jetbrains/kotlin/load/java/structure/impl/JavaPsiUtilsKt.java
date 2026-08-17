package org.jetbrains.kotlin.load.java.structure.impl;

import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isCompiledElement", "", "Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JavaPsiUtilsKt {
    public static final boolean isCompiledElement(PsiElement psiElement) {
        psiElement.getClass();
        return psiElement instanceof PsiCompiledElement;
    }
}
