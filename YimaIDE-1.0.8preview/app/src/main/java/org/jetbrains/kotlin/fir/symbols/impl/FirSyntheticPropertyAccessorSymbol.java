package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertyAccessorSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;", "<init>", "()V", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "getPropertySymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "delegateFunctionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getDelegateFunctionSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSyntheticPropertyAccessorSymbol extends FirPropertyAccessorSymbol {
    /* JADX WARN: Multi-variable type inference failed */
    public final FirNamedFunctionSymbol getDelegateFunctionSymbol() {
        D fir = getFir();
        fir.getClass();
        return ((FirSyntheticPropertyAccessor) fir).getDelegate().getSymbol();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol
    public FirSyntheticPropertySymbol getPropertySymbol() {
        FirPropertySymbol propertySymbol = super.getPropertySymbol();
        propertySymbol.getClass();
        return (FirSyntheticPropertySymbol) propertySymbol;
    }
}
