package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.util.containers.SLRUMap;
import com.intellij.util.containers.hash.EqualityPolicy;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class SLRUMap<K, V> {
    private static final int FACTOR = Integer.getInteger("idea.slru.factor", 1).intValue();
    private int misses;
    private int probationalHits;
    private final LinkedCustomHashMap<K, V> probationalQueue;
    private final int probationalQueueSize;
    private int protectedHits;
    private final LinkedCustomHashMap<K, V> protectedQueue;
    private final int protectedQueueSize;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 6 || i == 7) ? 2 : 3];
        switch (i) {
            case 1:
            case 2:
            case 3:
                objArr[0] = "value";
                break;
            case 4:
                objArr[0] = "keyConsumer";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "com/intellij/util/containers/SLRUMap";
                break;
            default:
                objArr[0] = "hashingStrategy";
                break;
        }
        if (i == 5) {
            objArr[1] = "entrySet";
        } else if (i == 6) {
            objArr[1] = "values";
        } else if (i != 7) {
            objArr[1] = "com/intellij/util/containers/SLRUMap";
        } else {
            objArr[1] = "dumpStats";
        }
        switch (i) {
            case 1:
                objArr[2] = "putToProtectedQueue";
                break;
            case 2:
                objArr[2] = "put";
                break;
            case 3:
                objArr[2] = "onDropFromCache";
                break;
            case 4:
                objArr[2] = "iterateKeys";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 6 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public SLRUMap(int i, int i2, EqualityPolicy<? super K> equalityPolicy) {
        if (equalityPolicy == null) {
            $$$reportNull$$$0(0);
        }
        int i3 = FACTOR;
        this.protectedQueueSize = i * i3;
        this.probationalQueueSize = i2 * i3;
        this.probationalQueue = new LinkedCustomHashMap<>(equalityPolicy, new LinkedCustomHashMap.RemoveCallback() { // from class: vnc
            @Override // com.intellij.util.containers.LinkedCustomHashMap.RemoveCallback
            public final boolean check(int i4, Map.Entry entry, Object obj, Object obj2) {
                return SLRUMap.b(this.a, i4, entry, obj, obj2);
            }
        });
        this.protectedQueue = new LinkedCustomHashMap<>(equalityPolicy, new LinkedCustomHashMap.RemoveCallback() { // from class: wnc
            @Override // com.intellij.util.containers.LinkedCustomHashMap.RemoveCallback
            public final boolean check(int i4, Map.Entry entry, Object obj, Object obj2) {
                return SLRUMap.a(this.a, i4, entry, obj, obj2);
            }
        });
    }

    public static /* synthetic */ boolean a(SLRUMap sLRUMap, int i, Map.Entry entry, Object obj, Object obj2) {
        if (i <= sLRUMap.protectedQueueSize) {
            return false;
        }
        sLRUMap.probationalQueue.put(obj, obj2);
        return true;
    }

    public static /* synthetic */ boolean b(SLRUMap sLRUMap, int i, Map.Entry entry, Object obj, Object obj2) {
        if (i <= sLRUMap.probationalQueueSize) {
            return false;
        }
        sLRUMap.onDropFromCache(obj, obj2);
        return true;
    }

    private K getStableKey(K k) {
        return k instanceof ShareableKey ? (K) ((ShareableKey) k).getStableCopy() : k;
    }

    public void clear() {
        try {
            if (!this.protectedQueue.isEmpty()) {
                for (Map.Entry<K, V> entry : this.protectedQueue.entrySet()) {
                    onDropFromCache(entry.getKey(), entry.getValue());
                }
            }
            if (!this.probationalQueue.isEmpty()) {
                for (Map.Entry<K, V> entry2 : this.probationalQueue.entrySet()) {
                    onDropFromCache(entry2.getKey(), entry2.getValue());
                }
            }
        } finally {
            this.protectedQueue.clear();
            this.probationalQueue.clear();
        }
    }

    public String dumpStats() {
        return "probational hits = " + this.probationalHits + ", protected hits = " + this.protectedHits + ", misses = " + this.misses;
    }

    public V get(K k) {
        V v = this.protectedQueue.get(k);
        if (v != null) {
            this.protectedHits++;
            return v;
        }
        V vRemove = this.probationalQueue.remove(k);
        if (vRemove == null) {
            this.misses++;
            return null;
        }
        this.probationalHits++;
        putToProtectedQueue(k, vRemove);
        return vRemove;
    }

    public void iterateKeys(Consumer<? super K> consumer) {
        if (consumer == null) {
            $$$reportNull$$$0(4);
        }
        this.protectedQueue.keySet().forEach(consumer);
        this.probationalQueue.keySet().forEach(consumer);
    }

    public void onDropFromCache(K k, V v) {
        if (v == null) {
            $$$reportNull$$$0(3);
        }
    }

    public void put(K k, V v) {
        if (v == null) {
            $$$reportNull$$$0(2);
        }
        V vRemove = this.protectedQueue.remove(k);
        if (vRemove != null) {
            onDropFromCache(k, vRemove);
        }
        V vPut = this.probationalQueue.put(getStableKey(k), v);
        if (vPut != null) {
            onDropFromCache(k, vPut);
        }
    }

    public void putToProtectedQueue(K k, V v) {
        if (v == null) {
            $$$reportNull$$$0(1);
        }
        this.protectedQueue.put(getStableKey(k), v);
    }

    public boolean remove(K k) {
        V vRemove = this.protectedQueue.remove(k);
        if (vRemove != null) {
            onDropFromCache(k, vRemove);
            return true;
        }
        V vRemove2 = this.probationalQueue.remove(k);
        if (vRemove2 == null) {
            return false;
        }
        onDropFromCache(k, vRemove2);
        return true;
    }

    public SLRUMap(int i, int i2) {
        this(i, i2, EqualityPolicy.CANONICAL);
    }
}
