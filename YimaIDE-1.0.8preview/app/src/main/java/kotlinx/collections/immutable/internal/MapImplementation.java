package kotlinx.collections.immutable.internal;

import defpackage.w01;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010&\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JA\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u000bH\u0000¢\u0006\u0002\b\fJ=\u0010\r\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u00072\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\t2\u000e\u0010\u000f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\tH\u0000¢\u0006\u0002\b\u0010J-\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\tH\u0000¢\u0006\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lkotlinx/collections/immutable/internal/MapImplementation;", HttpUrl.FRAGMENT_ENCODE_SET, "<init>", "()V", "containsEntry", HttpUrl.FRAGMENT_ENCODE_SET, "K", "V", "map", HttpUrl.FRAGMENT_ENCODE_SET, "element", HttpUrl.FRAGMENT_ENCODE_SET, "containsEntry$kotlinx_collections_immutable", "equals", "thisMap", "otherMap", "equals$kotlinx_collections_immutable", "hashCode", HttpUrl.FRAGMENT_ENCODE_SET, "hashCode$kotlinx_collections_immutable", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MapImplementation {
    public static final MapImplementation INSTANCE = new MapImplementation();

    private MapImplementation() {
    }

    public final <K, V> boolean containsEntry$kotlinx_collections_immutable(Map<K, ? extends V> map, Map.Entry<? extends K, ? extends V> element) {
        map.getClass();
        element.getClass();
        V v = map.get(element.getKey());
        if (v != null) {
            return Intrinsics.areEqual(v, element.getValue());
        }
        return element.getValue() == null && map.containsKey(element.getKey());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <K, V> boolean equals$kotlinx_collections_immutable(Map<K, ? extends V> thisMap, Map<?, ?> otherMap) {
        thisMap.getClass();
        otherMap.getClass();
        if (thisMap.size() != otherMap.size()) {
            w01.a("Failed requirement.");
            return false;
        }
        if (otherMap.isEmpty()) {
            return true;
        }
        Iterator<Map.Entry<?, ?>> it = otherMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!INSTANCE.containsEntry$kotlinx_collections_immutable(thisMap, it.next())) {
                return false;
            }
        }
        return true;
    }

    public final <K, V> int hashCode$kotlinx_collections_immutable(Map<K, ? extends V> map) {
        map.getClass();
        return map.entrySet().hashCode();
    }
}
