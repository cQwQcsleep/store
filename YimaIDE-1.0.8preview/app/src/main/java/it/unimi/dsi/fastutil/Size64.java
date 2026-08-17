package it.unimi.dsi.fastutil;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Size64 {
    static long sizeOf(Collection<?> collection) {
        return collection instanceof Size64 ? ((Size64) collection).size64() : collection.size();
    }

    @Deprecated
    default int size() {
        return (int) Math.min(2147483647L, size64());
    }

    long size64();

    static long sizeOf(Map<?, ?> map) {
        return map instanceof Size64 ? ((Size64) map).size64() : map.size();
    }
}
