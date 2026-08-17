package org.jetbrains.kotlin.resolve.calls.smartcasts;

import io.vavr.collection.LinkedHashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0085\u0001\u0010\u0007\u001a.\u0012\u0004\u0012\u0002H\u0001\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002H\u00020\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\u00040\u0005j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002`\b\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*.\u0012\u0004\u0012\u0002H\u0001\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002H\u00020\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\u00040\u0005j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u0002H\u00012\u0006\u0010\n\u001a\u0002H\u0002H\u0002¢\u0006\u0002\u0010\u000b*f\b\u0002\u0010\u0000\u001a\u0004\b\u0000\u0010\u0001\u001a\u0004\b\u0001\u0010\u0002\"\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u0002`\u0004`\u00032>\u0012\u0004\u0012\u0002H\u0001\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002H\u00020\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\u00040\u0005j\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002H\u00020\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\u0004`\u0003¨\u0006\f"}, d2 = {"ImmutableMultimap", "K", "V", "Lorg/jetbrains/kotlin/util/vavr/ImmutableMap;", "Lorg/jetbrains/kotlin/util/vavr/ImmutableSet;", "Lio/vavr/collection/Map;", "Lio/vavr/collection/Set;", "put", "Lorg/jetbrains/kotlin/resolve/calls/smartcasts/ImmutableMultimap;", "key", "value", "(Lio/vavr/collection/Map;Ljava/lang/Object;Ljava/lang/Object;)Lio/vavr/collection/Map;", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class DataFlowInfoImplKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> Map<K, Set<V>> put(Map<K, Set<V>> map, K k, V v) {
        Set set = (Set) map.get(k).getOrElse(LinkedHashSet.empty());
        if (set.contains(v)) {
            return map;
        }
        Map<K, Set<V>> mapPut = map.put(k, set.add(v));
        mapPut.getClass();
        return mapPut;
    }
}
