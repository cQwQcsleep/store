package org.jetbrains.kotlin.fir.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0010\u001c\n\u0002\u0010&\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010(\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u0001*\u0010\b\u0002\u0010\u0003 \u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00060\u0005J\u0016\u0010\u0007\u001a\u00028\u00022\u0006\u0010\b\u001a\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\fJ\u001c\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u00060\u0015H¦\u0082\u0004R\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/util/Multimap;", "K", "V", "C", "", "", "", "get", "key", "(Ljava/lang/Object;)Ljava/util/Collection;", "contains", "", "(Ljava/lang/Object;)Z", "keys", "", "getKeys", "()Ljava/util/Set;", "values", "getValues", "()Ljava/util/Collection;", "iterator", "", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface Multimap<K, V, C extends Collection<? extends V>> extends Iterable<Map.Entry<? extends K, ? extends C>>, KMappedMarker {
    boolean contains(K key);

    C get(K key);

    Set<K> getKeys();

    Collection<V> getValues();

    @Override // java.lang.Iterable
    Iterator<Map.Entry<K, C>> iterator();
}
