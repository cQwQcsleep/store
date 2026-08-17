package org.jetbrains.kotlin.fir.caches;

import kotlin.Metadata;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u0001*\u0016\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0006\u001a\u0002H\u0002H\u0086\b¢\u0006\u0002\u0010\u0007\u001a<\u0010\b\u001a\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u0001*\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010\n\u001a4\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0086\u0002¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"getValue", "V", "K", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/caches/FirCache;", Argument.Delimiters.none, "key", "(Lorg/jetbrains/kotlin/fir/caches/FirCache;Ljava/lang/Object;)Ljava/lang/Object;", "contains", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/caches/FirCache;Ljava/lang/Object;)Z", "Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCacheWithPostComputeKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> boolean contains(FirCache<? super K, ? extends V, ?> firCache, K k) {
        firCache.getClass();
        k.getClass();
        return firCache.getValueIfComputed(k) != null;
    }

    public static final <K, V> V getValue(FirCache firCache, K k) {
        firCache.getClass();
        k.getClass();
        return (V) firCache.getValue(k, null);
    }

    public static final <V> V getValue(FirLazyValue<? extends V> firLazyValue, Object obj, KProperty<?> kProperty) {
        firLazyValue.getClass();
        kProperty.getClass();
        return firLazyValue.getValue();
    }
}
