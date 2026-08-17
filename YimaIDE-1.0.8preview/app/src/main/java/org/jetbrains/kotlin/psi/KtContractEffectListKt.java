package org.jetbrains.kotlin.psi;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtStubBasedElementTypes;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003¨\u0006\u0004"}, d2 = {"getContractEffects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtContractEffect;", "Lorg/jetbrains/kotlin/psi/KtContractEffectList;", "org.jetbrains.kotlin:psi-api"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtContractEffectListKt {
    public static final List<KtContractEffect> getContractEffects(KtContractEffectList ktContractEffectList) {
        ktContractEffectList.getClass();
        List<KtContractEffect> stubOrPsiChildrenAsList = ktContractEffectList.getStubOrPsiChildrenAsList(KtStubBasedElementTypes.CONTRACT_EFFECT);
        stubOrPsiChildrenAsList.getClass();
        return stubOrPsiChildrenAsList;
    }
}
