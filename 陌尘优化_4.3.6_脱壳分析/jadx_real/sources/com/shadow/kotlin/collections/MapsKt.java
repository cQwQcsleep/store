package com.shadow.kotlin.collections;

import com.shadow.kotlin.io.CloseableKt;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class MapsKt extends MapsKt__MapsJVMKt {
    public static Map a() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        CloseableKt.checkNotNull(emptyMap, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return emptyMap;
    }

    public static int b(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map c(Map map) {
        CloseableKt.checkNotNullParameter(map, "<this>");
        int size = map.size();
        if (size == 0) {
            return a();
        }
        if (size != 1) {
            return new LinkedHashMap(map);
        }
        CloseableKt.checkNotNullParameter(map, "<this>");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        CloseableKt.checkNotNullExpressionValue(mapSingletonMap, "with(...)");
        return mapSingletonMap;
    }
}
