package j$.util;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/* renamed from: j$.util.Map$-EL, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class Map$EL {
    public static Object computeIfAbsent(Map map, Object obj, Function function) {
        Object objApply;
        Object objApply2;
        if (map instanceof InterfaceC0089m) {
            return ((InterfaceC0089m) map).computeIfAbsent(obj, function);
        }
        if (map instanceof ConcurrentMap) {
            ConcurrentMap concurrentMap = (ConcurrentMap) map;
            Objects.requireNonNull(function);
            Object objPutIfAbsent = concurrentMap.get(obj);
            return (objPutIfAbsent == null && (objApply2 = function.apply(obj)) != null && (objPutIfAbsent = concurrentMap.putIfAbsent(obj, objApply2)) == null) ? objApply2 : objPutIfAbsent;
        }
        Objects.requireNonNull(function);
        Object obj2 = map.get(obj);
        if (obj2 != null || (objApply = function.apply(obj)) == null) {
            return obj2;
        }
        map.put(obj, objApply);
        return objApply;
    }
}
