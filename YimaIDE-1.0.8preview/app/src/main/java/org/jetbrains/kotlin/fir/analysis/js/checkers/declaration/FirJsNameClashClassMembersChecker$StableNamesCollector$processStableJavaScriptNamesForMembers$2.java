package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirJsNameClashClassMembersChecker$StableNamesCollector$processStableJavaScriptNamesForMembers$2 extends AdaptedFunctionReference implements Function1<FirCallableSymbol<?>, Unit> {
    public FirJsNameClashClassMembersChecker$StableNamesCollector$processStableJavaScriptNamesForMembers$2(Object obj) {
        super(1, obj, Set.class, "add", "add(Ljava/lang/Object;)Z", 8);
    }

    public final void invoke(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        ((Set) ((AdaptedFunctionReference) this).receiver).add(firCallableSymbol);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FirCallableSymbol<?>) obj);
        return Unit.INSTANCE;
    }
}
