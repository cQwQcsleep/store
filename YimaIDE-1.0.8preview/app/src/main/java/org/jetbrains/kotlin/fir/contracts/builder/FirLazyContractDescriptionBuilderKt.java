package org.jetbrains.kotlin.fir.contracts.builder;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription;
import org.jetbrains.kotlin.fir.contracts.impl.FirLazyContractDescriptionImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"buildLazyContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLazyContractDescription;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLazyContractDescriptionBuilderKt {
    public static final FirLazyContractDescription buildLazyContractDescription() {
        return new FirLazyContractDescriptionImpl();
    }
}
