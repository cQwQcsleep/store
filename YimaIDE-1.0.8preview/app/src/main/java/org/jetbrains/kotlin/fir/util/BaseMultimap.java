package org.jetbrains.kotlin.fir.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractCollection;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.utils.addToStdlib.ChainedIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\u0010&\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u000e\b\u0002\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004*\u000e\b\u0003\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007B\u0007¢\u0006\u0004\b\b\u0010\tJ\r\u0010\f\u001a\u00028\u0003H$¢\u0006\u0002\u0010\rJ\r\u0010\u000e\u001a\u00028\u0002H$¢\u0006\u0002\u0010\rJ\u0016\u0010\u000f\u001a\u00028\u00022\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0014J\u001d\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001eJ\u001d\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001eJ\u0015\u0010 \u001a\u00028\u00022\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\b\u0010!\u001a\u00020\u001cH\u0016J\u001c\u0010\"\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020$0#H\u0096\u0082\u0004R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00030\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\r¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/util/BaseMultimap;", "K", "V", "C", "", "MC", "", "Lorg/jetbrains/kotlin/fir/util/MutableMultimap;", "<init>", "()V", "map", "", "createContainer", "()Ljava/util/Collection;", "createEmptyContainer", "get", "key", "(Ljava/lang/Object;)Ljava/util/Collection;", "contains", "", "(Ljava/lang/Object;)Z", "keys", "", "getKeys", "()Ljava/util/Set;", "values", "getValues", "put", "", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "remove", "removeKey", "clear", "iterator", "", "", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class BaseMultimap<K, V, C extends Collection<? extends V>, MC extends Collection<V>> implements MutableMultimap<K, V, C> {
    private final Map<K, MC> map = new LinkedHashMap();

    @Override // org.jetbrains.kotlin.fir.util.MutableMultimap
    public void clear() {
        this.map.clear();
    }

    @Override // org.jetbrains.kotlin.fir.util.Multimap
    public boolean contains(K key) {
        return this.map.containsKey(key);
    }

    public abstract MC createContainer();

    public abstract C createEmptyContainer();

    @Override // org.jetbrains.kotlin.fir.util.Multimap
    public C get(K key) {
        MC mc = this.map.get(key);
        return mc == null ? (C) createEmptyContainer() : mc;
    }

    @Override // org.jetbrains.kotlin.fir.util.Multimap
    public Set<K> getKeys() {
        return this.map.keySet();
    }

    @Override // org.jetbrains.kotlin.fir.util.Multimap
    public Collection<V> getValues() {
        return new AbstractCollection<V>(this) { // from class: org.jetbrains.kotlin.fir.util.BaseMultimap$values$1
            final /* synthetic */ BaseMultimap<K, V, C, MC> this$0;

            {
                this.this$0 = this;
            }

            public int getSize() {
                Iterator<T> it = ((BaseMultimap) this.this$0).map.values().iterator();
                int size = 0;
                while (it.hasNext()) {
                    size += ((Collection) it.next()).size();
                }
                return size;
            }

            public Iterator<V> iterator() {
                Collection<V> collectionValues = ((BaseMultimap) this.this$0).map.values();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionValues, 10));
                Iterator<T> it = collectionValues.iterator();
                while (it.hasNext()) {
                    arrayList.add(((Collection) it.next()).iterator());
                }
                return new ChainedIterator(arrayList);
            }
        };
    }

    @Override // org.jetbrains.kotlin.fir.util.Multimap, java.lang.Iterable
    public Iterator<Map.Entry<K, C>> iterator() {
        Iterator<Map.Entry<K, MC>> it = this.map.entrySet().iterator();
        it.getClass();
        return it;
    }

    @Override // org.jetbrains.kotlin.fir.util.MutableMultimap
    public void put(K key, V value) {
        Map<K, MC> map = this.map;
        Collection collectionCreateContainer = map.get(key);
        if (collectionCreateContainer == null) {
            collectionCreateContainer = createContainer();
            map.put(key, (MC) collectionCreateContainer);
        }
        collectionCreateContainer.add(value);
    }

    @Override // org.jetbrains.kotlin.fir.util.MutableMultimap
    public void remove(K key, V value) {
        MC mc = this.map.get(key);
        if (mc == null) {
            return;
        }
        mc.remove(value);
        if (mc.isEmpty()) {
            this.map.remove(key);
        }
    }

    @Override // org.jetbrains.kotlin.fir.util.MutableMultimap
    public C removeKey(K key) {
        MC mcRemove = this.map.remove(key);
        return mcRemove == null ? (C) createEmptyContainer() : mcRemove;
    }
}
