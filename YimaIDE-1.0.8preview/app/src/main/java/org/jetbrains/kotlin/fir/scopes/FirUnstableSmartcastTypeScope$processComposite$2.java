package org.jetbrains.kotlin.fir.scopes;

import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnstableSmartcastTypeScope$processComposite$2 implements Function1 {
    final /* synthetic */ Function1 $processor;
    final /* synthetic */ Set $unique;
    final /* synthetic */ FirUnstableSmartcastTypeScope this$0;

    public FirUnstableSmartcastTypeScope$processComposite$2(Set set, FirUnstableSmartcastTypeScope firUnstableSmartcastTypeScope, Function1 function1) {
        this.$unique = set;
        this.this$0 = firUnstableSmartcastTypeScope;
        this.$processor = function1;
    }

    public final void invoke(FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (this.$unique.contains(firCallableSymbol)) {
            return;
        }
        this.this$0.markSymbolFromUnstableSmartcast(firCallableSymbol);
        this.$processor.invoke(firCallableSymbol);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FirCallableSymbol) obj);
        return Unit.INSTANCE;
    }
}
