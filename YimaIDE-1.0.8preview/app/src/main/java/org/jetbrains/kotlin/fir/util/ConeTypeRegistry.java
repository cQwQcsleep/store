package org.jetbrains.kotlin.fir.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.fir.util.ConeTypeRegistry;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J4\u0010\u0007\u001a\u00020\b*\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\t2\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/util/ConeTypeRegistry;", "K", "", "V", "Lorg/jetbrains/kotlin/util/TypeRegistry;", "<init>", "()V", "customComputeIfAbsent", "", "Ljava/util/concurrent/ConcurrentHashMap;", "", "key", "compute", "Lkotlin/Function1;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ConeTypeRegistry<K, V> extends TypeRegistry<K, V> {
    public static Integer b(Function1 function1, Object obj) {
        return (Integer) function1.invoke(obj);
    }

    public int customComputeIfAbsent(ConcurrentHashMap<String, Integer> concurrentHashMap, String str, final Function1<? super String, Integer> function1) {
        concurrentHashMap.getClass();
        str.getClass();
        function1.getClass();
        Integer numComputeIfAbsent = concurrentHashMap.computeIfAbsent(str, new Function() { // from class: mq2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ConeTypeRegistry.b(function1, obj);
            }
        });
        numComputeIfAbsent.getClass();
        return numComputeIfAbsent.intValue();
    }
}
