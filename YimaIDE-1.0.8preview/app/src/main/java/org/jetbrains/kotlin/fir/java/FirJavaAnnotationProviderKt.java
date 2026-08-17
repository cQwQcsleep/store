package org.jetbrains.kotlin.fir.java;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"javaAnnotationProvider", "Lorg/jetbrains/kotlin/fir/java/FirJavaAnnotationProvider;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getJavaAnnotationProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/java/FirJavaAnnotationProvider;", "javaAnnotationProvider$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaAnnotationProviderKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirJavaAnnotationProviderKt.class, "javaAnnotationProvider", "getJavaAnnotationProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/java/FirJavaAnnotationProvider;", 1)};
    private static final NullableArrayMapAccessor javaAnnotationProvider$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirJavaAnnotationProvider.class));

    public static final FirJavaAnnotationProvider getJavaAnnotationProvider(FirSession firSession) {
        firSession.getClass();
        return (FirJavaAnnotationProvider) javaAnnotationProvider$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
