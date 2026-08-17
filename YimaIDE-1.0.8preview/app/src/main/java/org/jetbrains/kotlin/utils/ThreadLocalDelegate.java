package org.jetbrains.kotlin.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\u000b\u001a\u00028\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0096\u0082\u0004¢\u0006\u0002\u0010\u000fJ-\u0010\u0010\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0012\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0013J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/utils/ThreadLocalDelegate;", "T", "Lkotlin/properties/ReadWriteProperty;", "", "initializer", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "map", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/lang/Thread;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "toString", "", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
final class ThreadLocalDelegate<T> implements ReadWriteProperty<Object, T> {
    private final Function0<T> initializer;
    private final ConcurrentHashMap<Thread, T> map;

    /* JADX WARN: Multi-variable type inference failed */
    public ThreadLocalDelegate(Function0<? extends T> function0) {
        function0.getClass();
        this.initializer = function0;
        this.map = new ConcurrentHashMap<>();
    }

    public static CharSequence a(Map.Entry entry) {
        entry.getClass();
        return "#" + ((Thread) entry.getKey()).getId() + "=>" + entry.getValue();
    }

    public T getValue(Object thisRef, KProperty<?> property) {
        property.getClass();
        ConcurrentHashMap<Thread, T> concurrentHashMap = this.map;
        Thread threadCurrentThread = Thread.currentThread();
        T t = concurrentHashMap.get(threadCurrentThread);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.initializer.invoke();
        T tPutIfAbsent = concurrentHashMap.putIfAbsent(threadCurrentThread, t2);
        return tPutIfAbsent == null ? t2 : tPutIfAbsent;
    }

    public void setValue(Object thisRef, KProperty<?> property, T value) {
        property.getClass();
        this.map.put(Thread.currentThread(), value);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ThreadLocalDelegate(");
        Set<Map.Entry<Thread, T>> setEntrySet = this.map.entrySet();
        setEntrySet.getClass();
        sb.append(kotlin.collections.CollectionsKt.joinToString$default(setEntrySet, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: org.jetbrains.kotlin.utils.l
            public final Object invoke(Object obj) {
                return ThreadLocalDelegate.a((Map.Entry) obj);
            }
        }, 31, (Object) null));
        sb.append(')');
        return sb.toString();
    }
}
