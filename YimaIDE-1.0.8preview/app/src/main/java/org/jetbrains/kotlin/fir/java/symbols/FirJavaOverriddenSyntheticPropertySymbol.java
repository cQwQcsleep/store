package org.jetbrains.kotlin.fir.java.symbols;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0001H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/symbols/FirJavaOverriddenSyntheticPropertySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "propertyId", "Lorg/jetbrains/kotlin/name/CallableId;", "getterId", "<init>", "(Lorg/jetbrains/kotlin/name/CallableId;Lorg/jetbrains/kotlin/name/CallableId;)V", "copy", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaOverriddenSyntheticPropertySymbol extends FirSyntheticPropertySymbol {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJavaOverriddenSyntheticPropertySymbol(CallableId callableId, CallableId callableId2) {
        super(callableId, callableId2);
        callableId.getClass();
        callableId2.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol
    public FirSyntheticPropertySymbol copy() {
        return new FirJavaOverriddenSyntheticPropertySymbol(getCallableId(), getGetterId());
    }
}
