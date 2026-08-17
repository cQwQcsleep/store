package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0012\u0010\u000b\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002\"!\u0010\u0003\u001a\u0004\u0018\u00010\u0002*\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\"\u0015\u0010\t\u001a\u00020\u0002*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006¨\u0006\u000e"}, d2 = {"sessionNotBoundError", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirModuleData;", "nullableModuleData", "Lorg/jetbrains/kotlin/fir/FirSession;", "getNullableModuleData", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirModuleData;", "nullableModuleData$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "moduleData", "getModuleData", "canSeeInternalsOf", Argument.Delimiters.none, "otherModule", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirModuleDataKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirModuleDataKt.class, "nullableModuleData", "getNullableModuleData(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirModuleData;", 1)};
    private static final NullableArrayMapAccessor nullableModuleData$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirModuleData.class));

    public static final boolean canSeeInternalsOf(FirModuleData firModuleData, FirModuleData firModuleData2) {
        firModuleData.getClass();
        firModuleData2.getClass();
        return Intrinsics.areEqual(firModuleData, firModuleData2) || firModuleData.getFriendDependencies().contains(firModuleData2) || firModuleData.getAllDependsOnDependencies().contains(firModuleData2) || firModuleData2.getAllDependsOnDependencies().contains(firModuleData);
    }

    public static final FirModuleData getModuleData(FirSession firSession) {
        firSession.getClass();
        FirModuleData nullableModuleData = getNullableModuleData(firSession);
        if (nullableModuleData != null) {
            return nullableModuleData;
        }
        w04.a("Module data is not registered in ", firSession);
        return null;
    }

    public static final FirModuleData getNullableModuleData(FirSession firSession) {
        firSession.getClass();
        return (FirModuleData) nullableModuleData$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void sessionNotBoundError(FirModuleData firModuleData) {
        throw new IllegalStateException(("module data " + Reflection.getOrCreateKotlinClass(firModuleData.getClass()).getSimpleName() + ':' + firModuleData.getName() + " not bound to session").toString());
    }
}
