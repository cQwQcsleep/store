package com.intellij.util.keyFMap;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Key;
import com.intellij.util.containers.UnmodifiableHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class DebugFMap implements KeyFMap {
    static final boolean DEBUG_FMAP = Boolean.getBoolean("idea.keyfmap.debug.implementation");
    private final UnmodifiableHashMap<Key<?>, Object> myMap;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 5 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 5 ? 3 : 2];
        switch (i) {
            case 1:
            case 3:
            case 4:
                objArr[0] = "key";
                break;
            case 2:
                objArr[0] = "value";
                break;
            case 5:
                objArr[0] = "com/intellij/util/keyFMap/DebugFMap";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "other";
                break;
            default:
                objArr[0] = "map";
                break;
        }
        if (i != 5) {
            objArr[1] = "com/intellij/util/keyFMap/DebugFMap";
        } else {
            objArr[1] = "getKeys";
        }
        switch (i) {
            case 1:
            case 2:
                objArr[2] = "plus";
                break;
            case 3:
                objArr[2] = "minus";
                break;
            case 4:
                objArr[2] = "get";
                break;
            case 5:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "equalsByReference";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 5) {
            throw new IllegalStateException(str2);
        }
    }

    public DebugFMap(UnmodifiableHashMap<Key<?>, Object> unmodifiableHashMap) {
        if (unmodifiableHashMap == null) {
            $$$reportNull$$$0(0);
        }
        this.myMap = unmodifiableHashMap;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KeyFMap)) {
            return false;
        }
        final KeyFMap keyFMap = (KeyFMap) obj;
        if (size() != keyFMap.size()) {
            return false;
        }
        return this.myMap.entrySet().stream().allMatch(new Predicate() { // from class: com.intellij.util.keyFMap.a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj2) {
                Map.Entry entry = (Map.Entry) obj2;
                return Objects.equals(entry.getValue(), keyFMap.get((Key) entry.getKey()));
            }
        });
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <V> V get(Key<V> key) {
        if (key == null) {
            $$$reportNull$$$0(4);
        }
        return (V) this.myMap.get(key);
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public Key[] getKeys() {
        Key[] keyArr = (Key[]) this.myMap.keySet().toArray(new Key[0]);
        if (keyArr == null) {
            $$$reportNull$$$0(5);
        }
        return keyArr;
    }

    public int hashCode() {
        return this.myMap.hashCode();
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public boolean isEmpty() {
        return this.myMap.isEmpty();
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public KeyFMap minus(Key<?> key) {
        if (key == null) {
            $$$reportNull$$$0(3);
        }
        UnmodifiableHashMap<Key<?>, Object> unmodifiableHashMapWithout = this.myMap.without(key);
        return unmodifiableHashMapWithout == this.myMap ? this : new DebugFMap(unmodifiableHashMapWithout);
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <V> KeyFMap plus(Key<V> key, V v) {
        if (key == null) {
            $$$reportNull$$$0(1);
        }
        if (v == null) {
            $$$reportNull$$$0(2);
        }
        UnmodifiableHashMap<Key<?>, Object> unmodifiableHashMapWith = this.myMap.with(key, v);
        return unmodifiableHashMapWith == this.myMap ? this : new DebugFMap(unmodifiableHashMapWith);
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public int size() {
        return this.myMap.size();
    }

    public String toString() {
        return this.myMap.toString();
    }
}
