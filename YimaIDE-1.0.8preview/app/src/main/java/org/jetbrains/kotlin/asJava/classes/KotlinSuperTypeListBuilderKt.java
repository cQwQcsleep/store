package org.jetbrains.kotlin.asJava.classes;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiQualifiedReference;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"nameFromSource", "", "Lcom/intellij/psi/PsiQualifiedReference;", "getNameFromSource", "(Lcom/intellij/psi/PsiQualifiedReference;)Ljava/lang/String;", "org.jetbrains.kotlin:light-classes-base"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class KotlinSuperTypeListBuilderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String getNameFromSource(PsiQualifiedReference psiQualifiedReference) {
        String referenceName = psiQualifiedReference.getReferenceName();
        if (referenceName == null) {
            return null;
        }
        PsiElement qualifier = psiQualifiedReference.getQualifier();
        PsiQualifiedReference psiQualifiedReference2 = qualifier instanceof PsiQualifiedReference ? (PsiQualifiedReference) qualifier : null;
        if (psiQualifiedReference2 == null) {
            return referenceName;
        }
        return getNameFromSource(psiQualifiedReference2) + '.' + referenceName;
    }
}
