package org.jetbrains.kotlin.fir.scopes;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnstableSmartcastTypeScope$processDirectOverriddenWithBaseScope$3 implements Function2 {
    final /* synthetic */ FirCallableSymbol $originalSymbol;
    final /* synthetic */ Function2 $processor;
    final /* synthetic */ Set $unique;
    final /* synthetic */ FirUnstableSmartcastTypeScope this$0;

    public FirUnstableSmartcastTypeScope$processDirectOverriddenWithBaseScope$3(FirCallableSymbol firCallableSymbol, Set set, FirUnstableSmartcastTypeScope firUnstableSmartcastTypeScope, Function2 function2) {
        this.$originalSymbol = firCallableSymbol;
        this.$unique = set;
        this.this$0 = firUnstableSmartcastTypeScope;
        this.$processor = function2;
    }

    public final ProcessorAction invoke(FirCallableSymbol firCallableSymbol, FirTypeScope firTypeScope) {
        firCallableSymbol.getClass();
        firTypeScope.getClass();
        if (!Intrinsics.areEqual(firCallableSymbol, this.$originalSymbol) && !this.$unique.contains(firCallableSymbol)) {
            this.this$0.markSymbolFromUnstableSmartcast(firCallableSymbol);
            return (ProcessorAction) this.$processor.invoke(firCallableSymbol, firTypeScope);
        }
        return ProcessorAction.NEXT;
    }
}
