package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.mpp.EnumEntrySymbolMarker;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/mpp/EnumEntrySymbolMarker;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "<init>", "(Lorg/jetbrains/kotlin/name/CallableId;)V", "getCallableId", "()Lorg/jetbrains/kotlin/name/CallableId;", "initializerObjectSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousObjectSymbol;", "getInitializerObjectSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousObjectSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEnumEntrySymbol extends FirVariableSymbol<FirEnumEntry> implements EnumEntrySymbolMarker {
    private final CallableId callableId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirEnumEntrySymbol(CallableId callableId) {
        super(null);
        callableId.getClass();
        this.callableId = callableId;
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
    public CallableId getCallableId() {
        return this.callableId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirAnonymousObjectSymbol getInitializerObjectSymbol() {
        FirAnonymousObject anonymousObject;
        FirExpression initializer = ((FirEnumEntry) getFir()).getInitializer();
        FirAnonymousObjectExpression firAnonymousObjectExpression = initializer instanceof FirAnonymousObjectExpression ? (FirAnonymousObjectExpression) initializer : null;
        if (firAnonymousObjectExpression == null || (anonymousObject = firAnonymousObjectExpression.getAnonymousObject()) == null) {
            return null;
        }
        return anonymousObject.getSymbol();
    }
}
