package org.jetbrains.kotlin.psi;

import com.intellij.psi.PsiElement;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface KtDeclarationWithBody extends KtDeclaration {
    default KtBlockExpression getBodyBlockExpression() {
        KtBlockExpression bodyExpression = getBodyExpression();
        if (bodyExpression instanceof KtBlockExpression) {
            return bodyExpression;
        }
        return null;
    }

    KtExpression getBodyExpression();

    default KtContractEffectList getContractDescription() {
        return null;
    }

    PsiElement getEqualsToken();

    String getName();

    List<KtParameter> getValueParameters();

    boolean hasBlockBody();

    boolean hasBody();

    default boolean hasContractEffectList() {
        return getContractDescription() != null;
    }

    boolean hasDeclaredReturnType();

    default boolean mayHaveContract() {
        return false;
    }
}
