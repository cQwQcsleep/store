package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class ConeOverloadConflictResolver$overrides$overriddenProducer$1 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirNamedFunctionSymbol, Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>, ProcessorAction> {
    public static final ConeOverloadConflictResolver$overrides$overriddenProducer$1 INSTANCE = new ConeOverloadConflictResolver$overrides$overriddenProducer$1();

    public ConeOverloadConflictResolver$overrides$overriddenProducer$1() {
        super(3, FirTypeScopeKt.class, "processOverriddenFunctions", "processOverriddenFunctions(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 1);
    }

    public final ProcessorAction invoke(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction> function1) {
        firTypeScope.getClass();
        firNamedFunctionSymbol.getClass();
        function1.getClass();
        return FirTypeScopeKt.processOverriddenFunctions(firTypeScope, firNamedFunctionSymbol, function1);
    }
}
