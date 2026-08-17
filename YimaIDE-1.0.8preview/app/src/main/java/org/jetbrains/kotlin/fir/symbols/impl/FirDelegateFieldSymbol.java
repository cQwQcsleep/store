package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "correspondingPropertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)V", "getCorrespondingPropertySymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "getCallableId", "()Lorg/jetbrains/kotlin/name/CallableId;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegateFieldSymbol extends FirVariableSymbol<FirProperty> {
    private final FirPropertySymbol correspondingPropertySymbol;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDelegateFieldSymbol(FirPropertySymbol firPropertySymbol) {
        super(null);
        firPropertySymbol.getClass();
        this.correspondingPropertySymbol = firPropertySymbol;
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
    public CallableId getCallableId() {
        return this.correspondingPropertySymbol.getCallableId();
    }

    public final FirPropertySymbol getCorrespondingPropertySymbol() {
        return this.correspondingPropertySymbol;
    }
}
