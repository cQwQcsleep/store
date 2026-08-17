package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class ConeOverloadConflictResolver$overrides$overriddenProducer$2 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirPropertySymbol, Function1<? super FirPropertySymbol, ? extends ProcessorAction>, ProcessorAction> {
    public static final ConeOverloadConflictResolver$overrides$overriddenProducer$2 INSTANCE = new ConeOverloadConflictResolver$overrides$overriddenProducer$2();

    public ConeOverloadConflictResolver$overrides$overriddenProducer$2() {
        super(3, FirTypeScopeKt.class, "processOverriddenProperties", "processOverriddenProperties(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 1);
    }

    public final ProcessorAction invoke(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, Function1<? super FirPropertySymbol, ? extends ProcessorAction> function1) {
        firTypeScope.getClass();
        firPropertySymbol.getClass();
        function1.getClass();
        return FirTypeScopeKt.processOverriddenProperties(firTypeScope, firPropertySymbol, function1);
    }
}
