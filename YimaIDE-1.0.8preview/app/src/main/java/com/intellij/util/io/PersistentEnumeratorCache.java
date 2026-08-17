package com.intellij.util.io;

import com.intellij.util.SystemProperties;
import com.intellij.util.containers.SLRUMap;
import com.intellij.util.containers.ShareableKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PersistentEnumeratorCache {
    private static final int ENUMERATION_CACHE_SIZE;
    private static final SLRUMap<Object, Integer> ourEnumerationCache;
    private static final CacheKey ourFlyweight;

    public static class CacheKey implements ShareableKey {
        public Object key;
        public DataEnumeratorEx<?> owner;

        private CacheKey(Object obj, DataEnumeratorEx<?> dataEnumeratorEx) {
            this.key = obj;
            this.owner = dataEnumeratorEx;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CacheKey)) {
                return false;
            }
            CacheKey cacheKey = (CacheKey) obj;
            return this.key.equals(cacheKey.key) && this.owner.equals(cacheKey.owner);
        }

        @Override // com.intellij.util.containers.ShareableKey
        public ShareableKey getStableCopy() {
            return this;
        }

        public int hashCode() {
            return this.key.hashCode();
        }
    }

    public static final class FlyweightKey extends CacheKey {
        /* JADX WARN: Multi-variable type inference failed */
        public FlyweightKey() {
            super(null, 0 == true ? 1 : 0);
        }

        @Override // com.intellij.util.io.PersistentEnumeratorCache.CacheKey, com.intellij.util.containers.ShareableKey
        public ShareableKey getStableCopy() {
            return new CacheKey(this.key, this.owner);
        }
    }

    static {
        int intProperty = SystemProperties.getIntProperty("idea.enumerationCacheSize", 8192);
        ENUMERATION_CACHE_SIZE = intProperty;
        ourFlyweight = new FlyweightKey();
        ourEnumerationCache = new SLRUMap<>(intProperty, intProperty);
    }

    public static void cacheId(Object obj, int i, DataEnumeratorEx<?> dataEnumeratorEx) {
        SLRUMap<Object, Integer> sLRUMap = ourEnumerationCache;
        synchronized (sLRUMap) {
            sLRUMap.put(new CacheKey(obj, dataEnumeratorEx), Integer.valueOf(i));
        }
    }

    public static int getCachedId(Object obj, DataEnumeratorEx<?> dataEnumeratorEx) {
        SLRUMap<Object, Integer> sLRUMap = ourEnumerationCache;
        synchronized (sLRUMap) {
            try {
                Integer num = sLRUMap.get(sharedKey(obj, dataEnumeratorEx));
                if (num == null) {
                    return 0;
                }
                return num.intValue();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static CacheKey sharedKey(Object obj, DataEnumeratorEx<?> dataEnumeratorEx) {
        CacheKey cacheKey = ourFlyweight;
        cacheKey.key = obj;
        cacheKey.owner = dataEnumeratorEx;
        return cacheKey;
    }
}
