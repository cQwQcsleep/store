package org.jetbrains.kotlin.util.vavr;

import io.vavr.Tuple2;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\"\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0002\b\u00030\u000fH\u0086\u0002¢\u0006\u0002\u0010\u0010\u001a\"\u0010\u0011\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u000e0\u000fH\u0086\u0002¢\u0006\u0002\u0010\u0010\u001aA\u0010\u0012\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002`\u00132\u0006\u0010\u0014\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0015*.\u0010\u0000\u001a\u0004\b\u0000\u0010\u0001\u001a\u0004\b\u0001\u0010\u0002\"\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003*.\u0010\u0004\u001a\u0004\b\u0000\u0010\u0001\u001a\u0004\b\u0001\u0010\u0002\"\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00052\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0005*\u001c\u0010\u0006\u001a\u0004\b\u0000\u0010\u0007\"\b\u0012\u0004\u0012\u0002H\u00070\b2\b\u0012\u0004\u0012\u0002H\u00070\b*\u001c\u0010\t\u001a\u0004\b\u0000\u0010\u0007\"\b\u0012\u0004\u0012\u0002H\u00070\n2\b\u0012\u0004\u0012\u0002H\u00070\n*\u001c\u0010\u000b\u001a\u0004\b\u0000\u0010\u0007\"\b\u0012\u0004\u0012\u0002H\u00070\f2\b\u0012\u0004\u0012\u0002H\u00070\f¨\u0006\u0016"}, d2 = {"ImmutableMap", "K", "V", "Lio/vavr/collection/Map;", "ImmutableHashMap", "Lio/vavr/collection/HashMap;", "ImmutableSet", "E", "Lio/vavr/collection/Set;", "ImmutableHashSet", "Lio/vavr/collection/HashSet;", "ImmutableLinkedHashSet", "Lio/vavr/collection/LinkedHashSet;", "component1", "T", "Lio/vavr/Tuple2;", "(Lio/vavr/Tuple2;)Ljava/lang/Object;", "component2", "getOrNull", "Lorg/jetbrains/kotlin/util/vavr/ImmutableMap;", "k", "(Lio/vavr/collection/Map;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class VavrAdaptersKt {
    public static final <T> T component1(Tuple2<T, ?> tuple2) {
        tuple2.getClass();
        return (T) tuple2._1();
    }

    public static final <T> T component2(Tuple2<?, T> tuple2) {
        tuple2.getClass();
        return (T) tuple2._2();
    }

    public static final <K, V> V getOrNull(Map<K, V> map, K k) {
        map.getClass();
        Option option = map.get(k);
        if (option != null) {
            return (V) option.getOrElse((Object) null);
        }
        return null;
    }
}
