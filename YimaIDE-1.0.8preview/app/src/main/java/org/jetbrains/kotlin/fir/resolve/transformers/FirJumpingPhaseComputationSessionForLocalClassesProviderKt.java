package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\"%\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0084\u0002¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"jumpingPhaseComputationSessionForLocalClassesProvider", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirJumpingPhaseComputationSessionForLocalClassesProvider;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getJumpingPhaseComputationSessionForLocalClassesProvider$annotations", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getJumpingPhaseComputationSessionForLocalClassesProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/resolve/transformers/FirJumpingPhaseComputationSessionForLocalClassesProvider;", "jumpingPhaseComputationSessionForLocalClassesProvider$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJumpingPhaseComputationSessionForLocalClassesProviderKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirJumpingPhaseComputationSessionForLocalClassesProviderKt.class, "jumpingPhaseComputationSessionForLocalClassesProvider", "getJumpingPhaseComputationSessionForLocalClassesProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/resolve/transformers/FirJumpingPhaseComputationSessionForLocalClassesProvider;", 1)};
    private static final ArrayMapAccessor jumpingPhaseComputationSessionForLocalClassesProvider$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirJumpingPhaseComputationSessionForLocalClassesProvider.class), (Object) null, 2, (Object) null);

    public static final FirJumpingPhaseComputationSessionForLocalClassesProvider getJumpingPhaseComputationSessionForLocalClassesProvider(FirSession firSession) {
        firSession.getClass();
        return (FirJumpingPhaseComputationSessionForLocalClassesProvider) jumpingPhaseComputationSessionForLocalClassesProvider$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    public static /* synthetic */ void getJumpingPhaseComputationSessionForLocalClassesProvider$annotations(FirSession firSession) {
    }
}
