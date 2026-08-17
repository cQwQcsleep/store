package org.jetbrains.kotlin.fir.caches;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aE\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"getOrPut", "V", "K", "Lorg/jetbrains/kotlin/fir/caches/NullableMap;", "key", "defaultValue", "Lkotlin/Function0;", "getOrPut-LcU4HiU", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NullableMapKt {
    /* JADX INFO: renamed from: getOrPut-LcU4HiU, reason: not valid java name */
    public static final <K, V> V m277getOrPutLcU4HiU(Map<K, Object> map, K k, Function0<? extends V> function0) {
        map.getClass();
        function0.getClass();
        V v = (V) map.get(k);
        if (v == null) {
            V v2 = (V) function0.invoke();
            map.put(k, v2 == null ? NullableMap.NullValue.INSTANCE : v2);
            return v2;
        }
        if (Intrinsics.areEqual(v, NullableMap.NullValue.INSTANCE)) {
            return null;
        }
        return v;
    }
}
