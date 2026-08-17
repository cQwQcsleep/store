package org.jetbrains.kotlin.utils;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001\u001bB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u000f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0011J \u0010\u0012\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00028\u0001H\u0086\u0002¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0002J!\u0010\u0017\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00028\u00002\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u0019¢\u0006\u0002\u0010\u001aR\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/utils/SmartIdentityTable;", "K", "V", "", "<init>", "()V", "keysArray", "", "valuesArray", "largeMap", "Ljava/util/IdentityHashMap;", "size", "", "getSize", "()I", "get", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "set", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "convertToHashMap", "", "getOrCreate", "factory", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class SmartIdentityTable<K, V> {
    private IdentityHashMap<K, V> largeMap;
    private List<K> keysArray = new ArrayList(10);
    private List<V> valuesArray = new ArrayList(10);

    private final void convertToHashMap() {
        IdentityHashMap<K, V> identityHashMap = new IdentityHashMap<>();
        List<K> list = this.keysArray;
        list.getClass();
        List<V> list2 = this.valuesArray;
        list2.getClass();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            identityHashMap.put(list.get(i), list2.get(i));
        }
        this.largeMap = identityHashMap;
        this.keysArray = null;
        this.valuesArray = null;
    }

    public final V get(K key) {
        List<K> list = this.keysArray;
        if (list == null) {
            IdentityHashMap<K, V> identityHashMap = this.largeMap;
            identityHashMap.getClass();
            return identityHashMap.get(key);
        }
        Iterator<T> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            if (it.next() == key) {
                List<V> list2 = this.valuesArray;
                list2.getClass();
                return list2.get(i);
            }
            i = i2;
        }
        return null;
    }

    public final V getOrCreate(K key, Function0<? extends V> factory) {
        factory.getClass();
        V v = get(key);
        if (v != null) {
            return v;
        }
        V v2 = (V) factory.invoke();
        set(key, v2);
        return v2;
    }

    public final int getSize() {
        List<K> list = this.keysArray;
        if (list != null) {
            return list.size();
        }
        IdentityHashMap<K, V> identityHashMap = this.largeMap;
        identityHashMap.getClass();
        return identityHashMap.size();
    }

    public final V set(K key, V value) {
        List<K> list = this.keysArray;
        if (list != null) {
            List<V> list2 = this.valuesArray;
            list2.getClass();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i) == key) {
                    V v = list2.get(i);
                    list2.set(i, value);
                    return v;
                }
            }
            if (list.size() < 10) {
                list.add(key);
                list2.add(value);
                return null;
            }
            convertToHashMap();
        }
        IdentityHashMap<K, V> identityHashMap = this.largeMap;
        identityHashMap.getClass();
        return identityHashMap.put(key, value);
    }
}
