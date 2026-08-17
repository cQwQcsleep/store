package com.intellij.util.indexing;

import com.intellij.openapi.diagnostic.Logger;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class IndexId<K, V> {
    private static final Logger LOG = Logger.getInstance(IndexId.class);
    private static final Map<String, IndexId<?, ?>> ourInstances = new HashMap();
    private final String myName;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "name";
        } else {
            objArr[0] = "com/intellij/util/indexing/IndexId";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/indexing/IndexId";
        } else {
            objArr[1] = "getName";
        }
        if (i != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public IndexId(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        if (str.length() >= 75) {
            LOG.warn("Too long name for ID, please make it shorter than 75: ".concat(str));
        }
        this.myName = str;
    }

    public static <K, V> IndexId<K, V> create(String str) {
        Object obj;
        Map<String, IndexId<?, ?>> map = ourInstances;
        synchronized (map) {
            try {
                obj = (IndexId<K, V>) map.get(str);
                if (obj == null) {
                    obj = (IndexId<K, V>) new IndexId(str);
                    map.put(str, (IndexId<?, ?>) obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return (IndexId<K, V>) obj;
    }

    public final String getName() {
        String str = this.myName;
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        return str;
    }

    public String toString() {
        return getName();
    }
}
