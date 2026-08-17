package org.jetbrains.kotlin.fir.caches;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.time.Duration;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aJ\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b\"\b\b\u0000\u0010\t*\u00020\f\"\u0004\b\u0001\u0010\n*\u00020\u00012\u0014\b\u0004\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u000eH\u0086\bø\u0001\u0000\u001a}\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b\"\b\b\u0000\u0010\t*\u00020\f\"\u0004\b\u0001\u0010\n*\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0014\b\u0004\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u000eH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001a"}, d2 = {"firCachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getFirCachesFactory", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "firCachesFactory$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "createCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "K", "V", Argument.Delimiters.none, Argument.Delimiters.none, "createValue", "Lkotlin/Function1;", "createCacheWithSuggestedLimits", "expirationAfterAccess", "Lkotlin/time/Duration;", "maximumSize", Argument.Delimiters.none, "keyHardness", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$KeyReferenceStrength;", "valueHardness", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$ValueReferenceStrength;", "createCacheWithSuggestedLimits-i8z2VEo", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;Lkotlin/time/Duration;Ljava/lang/Long;Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$KeyReferenceStrength;Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$ValueReferenceStrength;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/caches/FirCache;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCachesFactoryKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirCachesFactoryKt.class, "firCachesFactory", "getFirCachesFactory(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", 1)};
    private static final ArrayMapAccessor firCachesFactory$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirCachesFactory.class), (Object) null, 2, (Object) null);

    public static final <K, V> FirCache createCache(FirCachesFactory firCachesFactory, final Function1<? super K, ? extends V> function1) {
        firCachesFactory.getClass();
        function1.getClass();
        return firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt.createCache.1
            /* JADX WARN: Type inference failed for: r0v2, types: [V, java.lang.Object] */
            public final V invoke(K k, Void r2) {
                k.getClass();
                return function1.invoke(k);
            }
        });
    }

    /* JADX INFO: renamed from: createCacheWithSuggestedLimits-i8z2VEo, reason: not valid java name */
    public static final <K, V> FirCache m264createCacheWithSuggestedLimitsi8z2VEo(FirCachesFactory firCachesFactory, Duration duration, Long l, FirCachesFactory.KeyReferenceStrength keyReferenceStrength, FirCachesFactory.ValueReferenceStrength valueReferenceStrength, Function1<? super K, ? extends V> function1) {
        firCachesFactory.getClass();
        keyReferenceStrength.getClass();
        valueReferenceStrength.getClass();
        function1.getClass();
        return firCachesFactory.mo263createCacheWithSuggestedLimitsn8is24I(duration, l, keyReferenceStrength, valueReferenceStrength, new FirCachesFactoryKt$createCacheWithSuggestedLimits$1(function1));
    }

    /* JADX INFO: renamed from: createCacheWithSuggestedLimits-i8z2VEo$default, reason: not valid java name */
    public static /* synthetic */ FirCache m265createCacheWithSuggestedLimitsi8z2VEo$default(FirCachesFactory firCachesFactory, Duration duration, Long l, FirCachesFactory.KeyReferenceStrength keyReferenceStrength, FirCachesFactory.ValueReferenceStrength valueReferenceStrength, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            duration = null;
        }
        if ((i & 2) != 0) {
            l = null;
        }
        if ((i & 4) != 0) {
            keyReferenceStrength = FirCachesFactory.KeyReferenceStrength.STRONG;
        }
        if ((i & 8) != 0) {
            valueReferenceStrength = FirCachesFactory.ValueReferenceStrength.STRONG;
        }
        firCachesFactory.getClass();
        keyReferenceStrength.getClass();
        valueReferenceStrength.getClass();
        function1.getClass();
        return firCachesFactory.mo263createCacheWithSuggestedLimitsn8is24I(duration, l, keyReferenceStrength, valueReferenceStrength, new FirCachesFactoryKt$createCacheWithSuggestedLimits$1(function1));
    }

    public static final FirCachesFactory getFirCachesFactory(FirSession firSession) {
        firSession.getClass();
        return (FirCachesFactory) firCachesFactory$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
