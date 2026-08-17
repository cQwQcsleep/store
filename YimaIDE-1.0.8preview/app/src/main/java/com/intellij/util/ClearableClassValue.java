package com.intellij.util;

import com.intellij.util.containers.ContainerUtil;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Unit;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\nJ\u001b\u0010\f\u001a\u0004\u0018\u00018\u00002\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0014¢\u0006\u0002\u0010\u000eJ\u001b\u0010\u000f\u001a\u0004\u0018\u00018\u00002\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0007H&¢\u0006\u0002\u0010\u000eR7\u0010\u0005\u001a+\u0012\u0012\u0012\u0010\u0012\u0002\b\u00030\u0007¢\u0006\u0002\b\b¢\u0006\u0002\b\t\u0012\u000e\u0012\f0\n¢\u0006\u0002\b\b¢\u0006\u0002\b\t0\u0006¢\u0006\u0002\b\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/intellij/util/ClearableClassValue;", "T", "Ljava/lang/ClassValue;", "<init>", "()V", "typeCache", "Ljava/util/concurrent/ConcurrentMap;", "Ljava/lang/Class;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "", "clear", "computeValue", "aClass", "(Ljava/lang/Class;)Ljava/lang/Object;", "computeValueImpl", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class ClearableClassValue<T> extends ClassValue<T> {
    private final ConcurrentMap<Class<?>, Unit> typeCache;

    public ClearableClassValue() {
        ConcurrentMap<Class<?>, Unit> concurrentMapCreateConcurrentWeakMap = ContainerUtil.createConcurrentWeakMap();
        concurrentMapCreateConcurrentWeakMap.getClass();
        this.typeCache = concurrentMapCreateConcurrentWeakMap;
    }

    @Override // java.lang.ClassValue
    public T computeValue(Class<?> aClass) {
        aClass.getClass();
        this.typeCache.put(aClass, Unit.INSTANCE);
        return computeValueImpl(aClass);
    }

    public abstract T computeValueImpl(Class<?> aClass);
}
