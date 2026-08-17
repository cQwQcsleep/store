package com.intellij.util.indexing.impl;

import com.intellij.util.io.KeyDescriptor;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class IndexStorageUtil {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        objArr[0] = "keyDescriptor";
        objArr[1] = "com/intellij/util/indexing/impl/IndexStorageUtil";
        if (i != 1) {
            objArr[2] = "createKeyDescriptorHashedMap";
        } else {
            objArr[2] = "adaptKeyDescriptorToStrategy";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static <K, V> Map<K, V> createKeyDescriptorHashedMap(final KeyDescriptor<? super K> keyDescriptor) {
        if (keyDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        return new Object2ObjectOpenCustomHashMap(new Hash.Strategy<K>() { // from class: com.intellij.util.indexing.impl.IndexStorageUtil.1
            public boolean equals(K k, K k2) {
                if (k != k2) {
                    return (k == null || k2 == null || !keyDescriptor.isEqual(k, k2)) ? false : true;
                }
                return true;
            }

            public int hashCode(K k) {
                if (k == null) {
                    return 0;
                }
                return keyDescriptor.getHashCode(k);
            }
        });
    }
}
