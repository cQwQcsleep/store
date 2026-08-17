package com.sun.org.apache.xerces.internal.dom;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class LCount {
    static final Map<String, LCount> lCounts = new ConcurrentHashMap();
    public int defaults;
    public int captures = 0;
    public int bubbles = 0;
    public int total = 0;

    public static /* synthetic */ LCount a(String str) {
        return new LCount();
    }

    public static LCount lookup(String str) {
        return lCounts.computeIfAbsent(str, new Function() { // from class: com.sun.org.apache.xerces.internal.dom.a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LCount.a((String) obj);
            }
        });
    }
}
