package com.intellij.util.keyFMap;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Key;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class OneElementFMap<V> implements KeyFMap {
    private final Key<V> myKey;
    private final V myValue;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 7) ? 2 : 3];
        if (i == 1 || i == 3) {
            objArr[0] = "value";
        } else if (i == 5 || i == 7) {
            objArr[0] = "com/intellij/util/keyFMap/OneElementFMap";
        } else if (i != 8) {
            objArr[0] = "key";
        } else {
            objArr[0] = "o";
        }
        if (i == 5) {
            objArr[1] = "minus";
        } else if (i != 7) {
            objArr[1] = "com/intellij/util/keyFMap/OneElementFMap";
        } else {
            objArr[1] = "getKeys";
        }
        switch (i) {
            case 2:
            case 3:
                objArr[2] = "plus";
                break;
            case 4:
                objArr[2] = "minus";
                break;
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "get";
                break;
            case 8:
                objArr[2] = "equalsByReference";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public OneElementFMap(Key<V> key, V v) {
        if (key == null) {
            $$$reportNull$$$0(0);
        }
        if (v == null) {
            $$$reportNull$$$0(1);
        }
        this.myKey = key;
        this.myValue = v;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OneElementFMap)) {
            return false;
        }
        OneElementFMap oneElementFMap = (OneElementFMap) obj;
        return this.myKey == oneElementFMap.myKey && this.myValue.equals(oneElementFMap.myValue);
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <T> T get(Key<T> key) {
        if (key == null) {
            $$$reportNull$$$0(6);
        }
        if (this.myKey == key) {
            return this.myValue;
        }
        return null;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public Key[] getKeys() {
        return new Key[]{this.myKey};
    }

    public int hashCode() {
        return this.myValue.hashCode() ^ this.myKey.hashCode();
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public boolean isEmpty() {
        return false;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public KeyFMap minus(Key<?> key) {
        if (key == null) {
            $$$reportNull$$$0(4);
        }
        if (key == this.myKey) {
            this = (OneElementFMap<V>) KeyFMap.EMPTY_MAP;
        }
        if (this == null) {
            $$$reportNull$$$0(5);
        }
        return this;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <T> KeyFMap plus(Key<T> key, T t) {
        if (key == null) {
            $$$reportNull$$$0(2);
        }
        if (t == null) {
            $$$reportNull$$$0(3);
        }
        Key<V> key2 = this.myKey;
        V v = this.myValue;
        if (key2 == key) {
            return t == v ? this : new OneElementFMap(key, t);
        }
        return new PairElementsFMap(key2, v, key, t);
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public int size() {
        return 1;
    }

    public String toString() {
        return "{" + this.myKey + "=" + this.myValue + "}";
    }
}
