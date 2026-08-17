package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$2 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
    public static final FirTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$2 INSTANCE = new FirTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$2();

    public FirTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$2() {
        super(3, FirTypeScope.class, "processDirectOverriddenPropertiesWithBaseScope", "processDirectOverriddenPropertiesWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
    }

    public final ProcessorAction invoke(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
        firTypeScope.getClass();
        firPropertySymbol.getClass();
        function2.getClass();
        return firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, function2);
    }
}
