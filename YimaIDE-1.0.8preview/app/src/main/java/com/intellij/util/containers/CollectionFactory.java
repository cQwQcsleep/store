package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.SystemInfoRt;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class CollectionFactory {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 1:
            case 2:
            case 18:
                objArr[0] = "evictionListener";
                break;
            case 3:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 17:
            case 19:
                objArr[0] = "hashingStrategy";
                break;
            case 4:
            case 16:
            default:
                objArr[0] = "strategy";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[0] = "items";
                break;
            case 9:
            case 13:
                objArr[0] = "source";
                break;
            case 10:
            case 11:
            case 12:
                objArr[0] = "paths";
                break;
            case 14:
            case 20:
                objArr[0] = "map";
                break;
            case 15:
                objArr[0] = "collection";
                break;
            case 21:
                objArr[0] = "set";
                break;
        }
        objArr[1] = "com/intellij/util/containers/CollectionFactory";
        switch (i) {
            case 1:
                objArr[2] = "createConcurrentWeakValueMap";
                break;
            case 2:
                objArr[2] = "createConcurrentSoftValueMap";
                break;
            case 3:
                objArr[2] = "createWeakMap";
                break;
            case 4:
                objArr[2] = "createConcurrentWeakKeyWeakValueMap";
                break;
            case 5:
            default:
                objArr[2] = "createConcurrentWeakMap";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "createConcurrentWeakKeySoftValueMap";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "createCharSequenceSet";
                break;
            case 8:
                objArr[2] = "createCaseInsensitiveStringSet";
                break;
            case 9:
                objArr[2] = "createCaseInsensitiveStringMap";
                break;
            case 10:
            case 11:
            case 12:
                objArr[2] = "createFilePathSet";
                break;
            case 13:
                objArr[2] = "createFilePathLinkedSet";
                break;
            case 14:
                objArr[2] = "createSmallMemoryFootprintMap";
                break;
            case 15:
                objArr[2] = "createSmallMemoryFootprintSet";
                break;
            case 16:
            case 17:
                objArr[2] = "createSoftMap";
                break;
            case 18:
            case 19:
                objArr[2] = "createConcurrentSoftMap";
                break;
            case 20:
                objArr[2] = "trimMap";
                break;
            case 21:
                objArr[2] = "trimSet";
                break;
            case 22:
            case 24:
                objArr[2] = "createCustomHashingStrategyMap";
                break;
            case 23:
                objArr[2] = "adaptStrategy";
                break;
            case 25:
                objArr[2] = "createCustomHashingStrategySet";
                break;
            case 26:
                objArr[2] = "createLinkedCustomHashingStrategyMap";
                break;
            case 27:
                objArr[2] = "createLinkedCustomHashingStrategySet";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    private static <K> Hash.Strategy<K> adaptStrategy(final HashingStrategy<? super K> hashingStrategy) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(23);
        }
        return new FastUtilHashingStrategies.SerializableHashStrategy() { // from class: com.intellij.util.containers.CollectionFactory.1
            public boolean equals(K k, K k2) {
                return hashingStrategy.equals(k, k2);
            }

            public int hashCode(K k) {
                return hashingStrategy.hashCode(k);
            }
        };
    }

    public static <V> Map<String, V> createCaseInsensitiveStringMap(Map<String, V> map) {
        if (map == null) {
            $$$reportNull$$$0(9);
        }
        return new Object2ObjectOpenCustomHashMap(map, FastUtilHashingStrategies.getCaseInsensitiveStringStrategy());
    }

    public static Set<String> createCaseInsensitiveStringSet() {
        return new ObjectOpenCustomHashSet(FastUtilHashingStrategies.getCaseInsensitiveStringStrategy());
    }

    public static <T> Map<CharSequence, T> createCharSequenceMap(boolean z) {
        return new Object2ObjectOpenCustomHashMap(FastUtilHashingStrategies.getCharSequenceStrategy(z));
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentSoftKeySoftValueMap(int i, float f, int i2) {
        return new ConcurrentSoftKeySoftValueHashMap(i, f, i2, HashingStrategy.canonical());
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentSoftMap() {
        return new ConcurrentSoftHashMap(null);
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentSoftValueMap() {
        return new ConcurrentSoftValueHashMap(null);
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakIdentityMap() {
        return new ConcurrentWeakHashMap(HashingStrategy.identity());
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakKeySoftValueMap() {
        return createConcurrentWeakKeySoftValueMap(100, 0.75f, Runtime.getRuntime().availableProcessors(), HashingStrategy.canonical());
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakKeyWeakValueMap(HashingStrategy<? super K> hashingStrategy) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(4);
        }
        return new ConcurrentWeakKeyWeakValueHashMap(100, 0.75f, Runtime.getRuntime().availableProcessors(), hashingStrategy);
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakMap() {
        return new ConcurrentWeakHashMap(0.75f);
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakValueMap() {
        return new ConcurrentWeakValueHashMap(null);
    }

    public static <K, V> Map<K, V> createCustomHashingStrategyMap(HashingStrategy<? super K> hashingStrategy) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(22);
        }
        return new Object2ObjectOpenCustomHashMap(adaptStrategy(hashingStrategy));
    }

    public static <K> Set<K> createCustomHashingStrategySet(HashingStrategy<? super K> hashingStrategy) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(25);
        }
        return new ObjectOpenCustomHashSet(adaptStrategy(hashingStrategy));
    }

    public static <V> Map<String, V> createFilePathMap() {
        return SystemInfoRt.isFileSystemCaseSensitive ? new HashMap() : createCaseInsensitiveStringMap();
    }

    public static Set<String> createFilePathSet() {
        return SystemInfoRt.isFileSystemCaseSensitive ? new HashSet() : createCaseInsensitiveStringSet();
    }

    public static <K, V> Map<K, V> createSmallMemoryFootprintMap() {
        return new Object2ObjectOpenHashMap();
    }

    public static <K> Set<K> createSmallMemoryFootprintSet(Collection<? extends K> collection) {
        if (collection == null) {
            $$$reportNull$$$0(15);
        }
        return new ObjectOpenHashSet(collection);
    }

    public static <K, V> Map<K, V> createSoftKeySoftValueMap() {
        return new SoftKeySoftValueHashMap();
    }

    public static <K, V> Map<K, V> createWeakIdentityMap(int i, float f) {
        return createWeakMap(i, f, HashingStrategy.identity());
    }

    public static <K, V> Map<K, V> createWeakMap(int i, float f, HashingStrategy<? super K> hashingStrategy) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(3);
        }
        return new WeakHashMap(i, f, hashingStrategy);
    }

    public static <V> Map<String, V> createCaseInsensitiveStringMap() {
        return new Object2ObjectOpenCustomHashMap(FastUtilHashingStrategies.getCaseInsensitiveStringStrategy());
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakKeySoftValueMap(int i, float f, int i2, HashingStrategy<? super K> hashingStrategy) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(6);
        }
        return new ConcurrentWeakKeySoftValueHashMap(i, f, i2, hashingStrategy);
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakKeyWeakValueMap() {
        return new ConcurrentWeakKeyWeakValueHashMap(100, 0.75f, Runtime.getRuntime().availableProcessors(), HashingStrategy.canonical());
    }
}
