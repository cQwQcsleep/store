package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.symbols.SymbolInternals;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\u0000H&R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u00020\u000b8FX\u0087\u0004r\u0002\b\u0010¢\u0006\f\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularPropertySymbol;", "propertyId", "Lorg/jetbrains/kotlin/name/CallableId;", "getterId", "<init>", "(Lorg/jetbrains/kotlin/name/CallableId;Lorg/jetbrains/kotlin/name/CallableId;)V", "getGetterId", "()Lorg/jetbrains/kotlin/name/CallableId;", "copy", "syntheticProperty", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "getSyntheticProperty$annotations", "()V", "getSyntheticProperty", "()Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "Lorg/jetbrains/kotlin/fir/symbols/SymbolInternals;", "getterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertyAccessorSymbol;", "getGetterSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertyAccessorSymbol;", "setterSymbol", "getSetterSymbol", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSyntheticPropertySymbol extends FirRegularPropertySymbol {
    private final CallableId getterId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirSyntheticPropertySymbol(CallableId callableId, CallableId callableId2) {
        super(callableId);
        callableId.getClass();
        callableId2.getClass();
        this.getterId = callableId2;
    }

    @SymbolInternals
    public static /* synthetic */ void getSyntheticProperty$annotations() {
    }

    public abstract FirSyntheticPropertySymbol copy();

    public final CallableId getGetterId() {
        return this.getterId;
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
    public FirSyntheticPropertyAccessorSymbol getGetterSymbol() {
        return (FirSyntheticPropertyAccessorSymbol) super.getGetterSymbol();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
    public FirSyntheticPropertyAccessorSymbol getSetterSymbol() {
        return (FirSyntheticPropertyAccessorSymbol) super.getSetterSymbol();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirSyntheticProperty getSyntheticProperty() {
        D fir = getFir();
        fir.getClass();
        return (FirSyntheticProperty) fir;
    }
}
