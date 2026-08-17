package com.intellij.util.keyFMap;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Key;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class PairElementsFMap<V1, V2> implements KeyFMap {
    private final Key<V1> key1;
    private final Key<V2> key2;
    private final V1 value1;
    private final V2 value2;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 7 || i == 9) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 7 || i == 9) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "value1";
                break;
            case 2:
                objArr[0] = "key2";
                break;
            case 3:
                objArr[0] = "value2";
                break;
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
                objArr[0] = "key";
                break;
            case 5:
                objArr[0] = "value";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
                objArr[0] = "com/intellij/util/keyFMap/PairElementsFMap";
                break;
            case 10:
                objArr[0] = "o";
                break;
            default:
                objArr[0] = "key1";
                break;
        }
        if (i == 7) {
            objArr[1] = "minus";
        } else if (i != 9) {
            objArr[1] = "com/intellij/util/keyFMap/PairElementsFMap";
        } else {
            objArr[1] = "getKeys";
        }
        switch (i) {
            case 4:
            case 5:
                objArr[2] = "plus";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "minus";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
                break;
            case 8:
                objArr[2] = "get";
                break;
            case 10:
                objArr[2] = "equalsByReference";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 7 && i != 9) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PairElementsFMap(Key<V1> key, V1 v1, Key<V2> key2, V2 v2) {
        if (key == 0) {
            $$$reportNull$$$0(0);
        }
        if (v1 == 0) {
            $$$reportNull$$$0(1);
        }
        if (key2 == 0) {
            $$$reportNull$$$0(2);
        }
        if (v2 == 0) {
            $$$reportNull$$$0(3);
        }
        int iCompare = Integer.compare(key.hashCode(), key2.hashCode());
        if (iCompare < 0) {
            this.key1 = key;
            this.value1 = v1;
            this.key2 = key2;
            this.value2 = v2;
            return;
        }
        if (iCompare > 0) {
            this.key1 = key2;
            this.value1 = v2;
            this.key2 = key;
            this.value2 = v1;
            return;
        }
        throw new IllegalArgumentException("Must not pass equal keys but got: key1: " + key + ":" + v1 + "; key2: " + key2 + ":" + v2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PairElementsFMap)) {
            return false;
        }
        PairElementsFMap pairElementsFMap = (PairElementsFMap) obj;
        return this.key1 == pairElementsFMap.key1 && this.value1.equals(pairElementsFMap.value1) && this.key2 == pairElementsFMap.key2 && this.value2.equals(pairElementsFMap.value2);
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <V> V get(Key<V> key) {
        if (key == null) {
            $$$reportNull$$$0(8);
        }
        if (key == this.key1) {
            return this.value1;
        }
        if (key == this.key2) {
            return this.value2;
        }
        return null;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public Key[] getKeys() {
        return new Key[]{this.key1, this.key2};
    }

    public int hashCode() {
        return (this.key1.hashCode() ^ this.value1.hashCode()) + (this.value2.hashCode() ^ this.key2.hashCode());
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public boolean isEmpty() {
        return false;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public KeyFMap minus(Key<?> key) {
        if (key == null) {
            $$$reportNull$$$0(6);
        }
        Key<V1> key2 = this.key1;
        Key<V2> key3 = this.key2;
        if (key == key2) {
            return new OneElementFMap(key3, this.value2);
        }
        return key == key3 ? new OneElementFMap(key2, this.value1) : this;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <V> KeyFMap plus(Key<V> key, V v) {
        if (key == null) {
            $$$reportNull$$$0(4);
        }
        if (v == null) {
            $$$reportNull$$$0(5);
        }
        Key<V1> key2 = this.key1;
        if (key == key2) {
            if (v != this.value1) {
                return new PairElementsFMap(key, v, this.key2, this.value2);
            }
        } else {
            if (key != this.key2) {
                if (key.hashCode() < this.key1.hashCode()) {
                    return new ArrayBackedFMap(new int[]{key.hashCode(), this.key1.hashCode(), this.key2.hashCode()}, new Object[]{v, this.value1, this.value2});
                }
                int iHashCode = key.hashCode();
                int iHashCode2 = this.key2.hashCode();
                Key<V1> key3 = this.key1;
                return iHashCode < iHashCode2 ? new ArrayBackedFMap(new int[]{key3.hashCode(), key.hashCode(), this.key2.hashCode()}, new Object[]{this.value1, v, this.value2}) : new ArrayBackedFMap(new int[]{key3.hashCode(), this.key2.hashCode(), key.hashCode()}, new Object[]{this.value1, this.value2, v});
            }
            if (v != this.value2) {
                return new PairElementsFMap(key2, this.value1, key, v);
            }
        }
        return this;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public int size() {
        return 2;
    }

    public String toString() {
        return "{" + this.key1 + "=" + this.value1 + ", " + this.key2 + "=" + this.value2 + "}";
    }
}
