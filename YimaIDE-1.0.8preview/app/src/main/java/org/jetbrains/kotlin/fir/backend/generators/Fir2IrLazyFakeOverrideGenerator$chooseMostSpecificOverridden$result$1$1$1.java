package org.jetbrains.kotlin.fir.backend.generators;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyFakeOverrideGenerator$chooseMostSpecificOverridden$result$1$1$1 implements Function1 {
    final /* synthetic */ FirCallableSymbol $s2;

    public Fir2IrLazyFakeOverrideGenerator$chooseMostSpecificOverridden$result$1$1$1(FirCallableSymbol firCallableSymbol) {
        this.$s2 = firCallableSymbol;
    }

    public final Boolean invoke(FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        return Boolean.valueOf(Intrinsics.areEqual(firCallableSymbol, this.$s2));
    }
}
