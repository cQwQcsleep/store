package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"annotationPlatformSupport", "Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getAnnotationPlatformSupport", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent;", "annotationPlatformSupport$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationsPlatformSpecificSupportComponentKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirAnnotationsPlatformSpecificSupportComponentKt.class, "annotationPlatformSupport", "getAnnotationPlatformSupport(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent;", 1)};
    private static final ArrayMapAccessor annotationPlatformSupport$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirAnnotationsPlatformSpecificSupportComponent.class), (Object) null, 2, (Object) null);

    public static final FirAnnotationsPlatformSpecificSupportComponent getAnnotationPlatformSupport(FirSession firSession) {
        firSession.getClass();
        return (FirAnnotationsPlatformSpecificSupportComponent) annotationPlatformSupport$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
