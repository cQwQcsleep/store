package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class SJ {
    public static final /* synthetic */ boolean a = true;

    /* JADX WARN: Code duplicated, block: B:29:0x0077  */
    public static Map a(Map map, IntFunction intFunction, BiFunction biFunction, InterfaceC1938ki0 interfaceC1938ki0, InterfaceC1938ki0 interfaceC1938ki1, InterfaceC1938ki0 interfaceC1938ki2) {
        Object obj;
        final Map map2 = null;
        ArrayList arrayList = null;
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object objApply = biFunction.apply(key, value);
            Object objA = interfaceC1938ki0.a(key, value, objApply);
            if (objA != null) {
                Object objA2 = interfaceC1938ki1.a(key, value, objApply);
                if (objA != key || objA2 != value) {
                    if (map2 == null) {
                        map2 = (Map) intFunction.apply(map.size());
                        Objects.requireNonNull(map2);
                        IM.a(map, new BiConsumer() { // from class: knc
                            @Override // java.util.function.BiConsumer
                            public final void accept(Object obj2, Object obj3) {
                                map2.put(obj2, obj3);
                            }
                        }, key);
                        if (arrayList != null) {
                            if (!a && arrayList.isEmpty()) {
                                x1f.a();
                                return null;
                            }
                            map2.keySet().removeAll(arrayList);
                            arrayList = null;
                        }
                    }
                    obj = map2.get(objA);
                    if (obj != null) {
                        objA2 = interfaceC1938ki2.a(objA, objA2, obj);
                    }
                    map2.put(objA, objA2);
                } else if (map2 != null) {
                    obj = map2.get(objA);
                    if (obj != null) {
                        objA2 = interfaceC1938ki2.a(objA, objA2, obj);
                    }
                    map2.put(objA, objA2);
                }
            } else if (map2 == null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(key);
            }
        }
        if (map2 != null) {
            if (!a && arrayList != null) {
                x1f.a();
                return null;
            }
            if (map2.size() >= map.size()) {
                return map2;
            }
            Map map3 = (Map) intFunction.apply(map2.size());
            map3.putAll(map2);
            return map3;
        }
        if (arrayList == null) {
            return map;
        }
        if (!a && arrayList.isEmpty()) {
            x1f.a();
            return null;
        }
        map.keySet().removeAll(arrayList);
        Map map4 = (Map) intFunction.apply(map.size());
        map4.putAll(map);
        return map4;
    }

    public static void a(AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.F2 f2, Consumer consumer) {
        com.android.tools.r8.graph.F2 f2B = abstractC3148ys.b(f2, (AbstractC3148ys) null);
        f2B.getClass();
        if ((f2B instanceof com.android.tools.r8.graph.I2) && f2B.r0().T0()) {
            return;
        }
        consumer.accept(f2B);
    }
}
