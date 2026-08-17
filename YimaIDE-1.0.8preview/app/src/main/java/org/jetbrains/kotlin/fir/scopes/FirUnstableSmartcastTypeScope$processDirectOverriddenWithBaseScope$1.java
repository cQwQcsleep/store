package org.jetbrains.kotlin.fir.scopes;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnstableSmartcastTypeScope$processDirectOverriddenWithBaseScope$1 implements Function2 {
    final /* synthetic */ Function2 $processor;
    final /* synthetic */ Set $unique;

    public FirUnstableSmartcastTypeScope$processDirectOverriddenWithBaseScope$1(Set set, Function2 function2) {
        this.$unique = set;
        this.$processor = function2;
    }

    public final ProcessorAction invoke(FirCallableSymbol firCallableSymbol, FirTypeScope firTypeScope) {
        firCallableSymbol.getClass();
        firTypeScope.getClass();
        this.$unique.add(firCallableSymbol);
        return (ProcessorAction) this.$processor.invoke(firCallableSymbol, firTypeScope);
    }
}
