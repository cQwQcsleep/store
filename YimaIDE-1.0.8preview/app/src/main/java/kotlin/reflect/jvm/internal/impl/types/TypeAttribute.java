package kotlin.reflect.jvm.internal.impl.types;

import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.impl.types.TypeAttribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class TypeAttribute<T extends TypeAttribute<? extends T>> {
    public abstract T add(T t);

    public abstract KClass<? extends T> getKey();

    public abstract T intersect(T t);
}
