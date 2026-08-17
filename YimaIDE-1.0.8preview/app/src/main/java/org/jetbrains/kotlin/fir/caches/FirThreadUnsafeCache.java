package org.jetbrains.kotlin.fir.caches;

import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u0003*\u0004\b\u0002\u0010\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0005B7\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\r\u001a\u00028\u00012\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0010J\u0017\u0010\u0011\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0012R\u001c\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR \u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00148VX\u0097\u0004r\u0002\b\u0019¢\u0006\f\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/caches/FirThreadUnsafeCache;", "K", Argument.Delimiters.none, "V", "CONTEXT", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "map", "Lorg/jetbrains/kotlin/fir/caches/NullableMap;", "createValue", "Lkotlin/Function2;", "<init>", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Ljava/util/Map;", "getValue", "key", "context", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getValueIfComputed", "(Ljava/lang/Object;)Ljava/lang/Object;", "cachedValues", Argument.Delimiters.none, "getCachedValues$annotations", "()V", "getCachedValues", "()Ljava/util/Collection;", "Lorg/jetbrains/kotlin/fir/caches/FirCacheInternals;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirThreadUnsafeCache<K, V, CONTEXT> extends FirCache<K, V, CONTEXT> {
    private final Function2<K, CONTEXT, V> createValue;
    private final Map<K, Object> map;

    /* JADX WARN: Multi-variable type inference failed */
    private FirThreadUnsafeCache(Map<K, Object> map, Function2<? super K, ? super CONTEXT, ? extends V> function2) {
        map.getClass();
        function2.getClass();
        this.map = map;
        this.createValue = function2;
    }

    @FirCacheInternals
    public static /* synthetic */ void getCachedValues$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.caches.FirCache
    public Collection<V> getCachedValues() {
        return CollectionsKt.toList(NullableMap.m272getValuesSnapshotimpl(this.map));
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // org.jetbrains.kotlin.fir.caches.FirCache
    public V getValue(K key, CONTEXT context) {
        key.getClass();
        V v = (V) this.map.get(key);
        if (v == null) {
            V v2 = (V) this.createValue.invoke(key, context);
            this.map.put(key, v2 == null ? NullableMap.NullValue.INSTANCE : v2);
            return v2;
        }
        if (Intrinsics.areEqual(v, NullableMap.NullValue.INSTANCE)) {
            return null;
        }
        return v;
    }

    @Override // org.jetbrains.kotlin.fir.caches.FirCache
    public V getValueIfComputed(K key) {
        key.getClass();
        V v = (V) this.map.get(key);
        if (v == null || Intrinsics.areEqual(v, NullableMap.NullValue.INSTANCE)) {
            return null;
        }
        return v;
    }

    public /* synthetic */ FirThreadUnsafeCache(Map map, Function2 function2, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, function2);
    }

    public /* synthetic */ FirThreadUnsafeCache(Map map, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? NullableMap.m268constructorimpl$default(null, 1, null) : map, function2, null);
    }
}
