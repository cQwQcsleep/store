package org.jetbrains.kotlin.psi;

import com.intellij.psi.util.PsiTreeUtil;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"getExpression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "Lorg/jetbrains/kotlin/psi/KtContractEffect;", "org.jetbrains.kotlin:psi-api"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtContractEffectKt {
    public static final KtExpression getExpression(KtContractEffect ktContractEffect) {
        ktContractEffect.getClass();
        KtExpression childOfType = PsiTreeUtil.getChildOfType(ktContractEffect, KtExpression.class);
        childOfType.getClass();
        return childOfType;
    }
}
