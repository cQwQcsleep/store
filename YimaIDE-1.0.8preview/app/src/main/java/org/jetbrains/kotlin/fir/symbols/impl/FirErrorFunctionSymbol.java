package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirErrorFunction;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirErrorFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionWithoutNameSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorFunction;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirErrorCallableSymbol;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirErrorFunctionSymbol extends FirFunctionWithoutNameSymbol<FirErrorFunction> implements FirErrorCallableSymbol<FirErrorFunction> {
    /* JADX WARN: Illegal instructions before constructor call */
    public FirErrorFunctionSymbol() {
        Name nameIdentifier = Name.identifier("error");
        nameIdentifier.getClass();
        super(nameIdentifier, null);
    }
}
