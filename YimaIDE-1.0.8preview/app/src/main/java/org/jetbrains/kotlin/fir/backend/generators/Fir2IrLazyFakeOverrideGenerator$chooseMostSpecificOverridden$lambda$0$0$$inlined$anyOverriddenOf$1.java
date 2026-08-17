package org.jetbrains.kotlin.fir.backend.generators;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyFakeOverrideGenerator$chooseMostSpecificOverridden$lambda$0$0$$inlined$anyOverriddenOf$1 implements Function1 {
    final /* synthetic */ Function1 $predicate;
    final /* synthetic */ Ref.BooleanRef $result;

    public Fir2IrLazyFakeOverrideGenerator$chooseMostSpecificOverridden$lambda$0$0$$inlined$anyOverriddenOf$1(Function1 function1, Ref.BooleanRef booleanRef) {
        this.$predicate = function1;
        this.$result = booleanRef;
    }

    public final ProcessorAction invoke(FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (!((Boolean) this.$predicate.invoke(firCallableSymbol)).booleanValue()) {
            return ProcessorAction.NEXT;
        }
        this.$result.element = true;
        return ProcessorAction.STOP;
    }
}
