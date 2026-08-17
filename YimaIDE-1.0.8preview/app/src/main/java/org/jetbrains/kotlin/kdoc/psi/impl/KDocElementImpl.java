package org.jetbrains.kotlin.kdoc.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import org.jetbrains.kotlin.idea.KotlinLanguage;
import org.jetbrains.kotlin.kdoc.psi.api.KDocElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class KDocElementImpl extends ASTWrapperPsiElement implements KDocElement {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[i != 1 ? 2 : 3];
        if (i != 1) {
            objArr[0] = "org/jetbrains/kotlin/kdoc/psi/impl/KDocElementImpl";
        } else {
            objArr[0] = "node";
        }
        if (i != 1) {
            objArr[1] = "getLanguage";
        } else {
            objArr[1] = "org/jetbrains/kotlin/kdoc/psi/impl/KDocElementImpl";
        }
        if (i == 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalArgumentException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KDocElementImpl(ASTNode aSTNode) {
        super(aSTNode);
        if (aSTNode == null) {
            $$$reportNull$$$0(1);
        }
    }

    public Language getLanguage() {
        KotlinLanguage kotlinLanguage = KotlinLanguage.INSTANCE;
        if (kotlinLanguage == null) {
            $$$reportNull$$$0(0);
        }
        return kotlinLanguage;
    }

    public String toString() {
        return getNode().getElementType().toString();
    }
}
