package com.intellij.util.keyFMap;

import com.intellij.openapi.util.Key;
import com.intellij.util.containers.UnmodifiableHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class EmptyFMap implements KeyFMap {
    private static final Key[] EMPTY_KEYS_ARRAY = new Key[0];

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 5) ? 2 : 3];
        if (i == 1) {
            objArr[0] = "value";
        } else if (i == 3 || i == 5) {
            objArr[0] = "com/intellij/util/keyFMap/EmptyFMap";
        } else if (i != 6) {
            objArr[0] = "key";
        } else {
            objArr[0] = "other";
        }
        if (i == 3) {
            objArr[1] = "minus";
        } else if (i != 5) {
            objArr[1] = "com/intellij/util/keyFMap/EmptyFMap";
        } else {
            objArr[1] = "getKeys";
        }
        if (i == 2) {
            objArr[2] = "minus";
        } else if (i != 3) {
            if (i == 4) {
                objArr[2] = "get";
            } else if (i != 5) {
                if (i != 6) {
                    objArr[2] = "plus";
                } else {
                    objArr[2] = "equalsByReference";
                }
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static KeyFMap create() {
        return DebugFMap.DEBUG_FMAP ? new DebugFMap(UnmodifiableHashMap.empty()) : new EmptyFMap();
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <V> V get(Key<V> key) {
        if (key != null) {
            return null;
        }
        $$$reportNull$$$0(4);
        return null;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public Key[] getKeys() {
        Key[] keyArr = EMPTY_KEYS_ARRAY;
        if (keyArr == null) {
            $$$reportNull$$$0(5);
        }
        return keyArr;
    }

    public int hashCode() {
        return 0;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public boolean isEmpty() {
        return true;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public KeyFMap minus(Key<?> key) {
        if (key == null) {
            $$$reportNull$$$0(2);
        }
        return this;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <V> KeyFMap plus(Key<V> key, V v) {
        if (key == null) {
            $$$reportNull$$$0(0);
        }
        if (v == null) {
            $$$reportNull$$$0(1);
        }
        return new OneElementFMap(key, v);
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public int size() {
        return 0;
    }

    public String toString() {
        return "{}";
    }
}
