package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;

/* JADX INFO: Add missing generic type declarations: [D] */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 176)
public final class FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1<D> implements Function1<D, Unit> {
    final /* synthetic */ List<D> $resultForScope;

    public FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1(List<D> list) {
        this.$resultForScope = list;
    }

    /* JADX WARN: Incorrect types in method signature: (TD;)V */
    public final void invoke(FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (firCallableSymbol instanceof FirConstructorSymbol) {
            return;
        }
        this.$resultForScope.add(firCallableSymbol);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FirCallableSymbol) obj);
        return Unit.INSTANCE;
    }
}
