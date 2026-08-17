package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.ArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"identityLessPlatformDeterminer", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirIdentityLessPlatformDeterminer;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getIdentityLessPlatformDeterminer", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/checkers/FirIdentityLessPlatformDeterminer;", "identityLessPlatformDeterminer$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIdentityLessPlatformDeterminerKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirIdentityLessPlatformDeterminerKt.class, "identityLessPlatformDeterminer", "getIdentityLessPlatformDeterminer(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/checkers/FirIdentityLessPlatformDeterminer;", 1)};
    private static final ArrayMapAccessor identityLessPlatformDeterminer$delegate = FirSession.INSTANCE.generateAccessor(Reflection.getOrCreateKotlinClass(FirIdentityLessPlatformDeterminer.class), FirIdentityLessPlatformDeterminer.Default.INSTANCE);

    public static final FirIdentityLessPlatformDeterminer getIdentityLessPlatformDeterminer(FirSession firSession) {
        firSession.getClass();
        return (FirIdentityLessPlatformDeterminer) identityLessPlatformDeterminer$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
