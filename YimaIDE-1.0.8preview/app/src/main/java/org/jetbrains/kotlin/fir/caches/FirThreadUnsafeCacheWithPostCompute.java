package org.jetbrains.kotlin.fir.caches;

import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u0003*\u0004\b\u0002\u0010\u0004*\u0004\b\u0003\u0010\u00052\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0006BM\u0012$\u0010\u0007\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00030\t0\b\u0012\u001e\u0010\n\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0015J\u0017\u0010\u0016\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017R,\u0010\u0007\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00030\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\n\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u00198VX\u0097\u0004r\u0002\b\u001e¢\u0006\f\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/caches/FirThreadUnsafeCacheWithPostCompute;", "K", Argument.Delimiters.none, "V", "CONTEXT", "DATA", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "createValue", "Lkotlin/Function2;", "Lkotlin/Pair;", "postCompute", "Lkotlin/Function3;", Argument.Delimiters.none, "<init>", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)V", "map", "Lorg/jetbrains/kotlin/fir/caches/NullableMap;", "Ljava/util/Map;", "getValue", "key", "context", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getValueIfComputed", "(Ljava/lang/Object;)Ljava/lang/Object;", "cachedValues", Argument.Delimiters.none, "getCachedValues$annotations", "()V", "getCachedValues", "()Ljava/util/Collection;", "Lorg/jetbrains/kotlin/fir/caches/FirCacheInternals;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirThreadUnsafeCacheWithPostCompute<K, V, CONTEXT, DATA> extends FirCache<K, V, CONTEXT> {
    private final Function2<K, CONTEXT, Pair<V, DATA>> createValue;
    private final Map<K, Object> map;
    private final Function3<K, V, DATA, Unit> postCompute;

    /* JADX WARN: Multi-variable type inference failed */
    public FirThreadUnsafeCacheWithPostCompute(Function2<? super K, ? super CONTEXT, ? extends Pair<? extends V, ? extends DATA>> function2, Function3<? super K, ? super V, ? super DATA, Unit> function3) {
        function2.getClass();
        function3.getClass();
        this.createValue = function2;
        this.postCompute = function3;
        this.map = NullableMap.m268constructorimpl$default(null, 1, null);
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
        if (v != null) {
            if (Intrinsics.areEqual(v, NullableMap.NullValue.INSTANCE)) {
                return null;
            }
            return v;
        }
        Pair pair = (Pair) this.createValue.invoke(key, context);
        V v2 = (V) pair.component1();
        Object objComponent2 = pair.component2();
        this.map.put(key, v2 == null ? NullableMap.NullValue.INSTANCE : v2);
        this.postCompute.invoke(key, v2, objComponent2);
        return v2;
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
}
