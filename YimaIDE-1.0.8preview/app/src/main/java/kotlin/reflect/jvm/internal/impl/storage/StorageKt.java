package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class StorageKt {
    public static final <T> T getValue(NotNullLazyValue<? extends T> notNullLazyValue, Object obj, KProperty<?> kProperty) {
        notNullLazyValue.getClass();
        kProperty.getClass();
        return (T) notNullLazyValue.invoke();
    }

    public static final <T> T getValue(NullableLazyValue<? extends T> nullableLazyValue, Object obj, KProperty<?> kProperty) {
        nullableLazyValue.getClass();
        kProperty.getClass();
        return (T) nullableLazyValue.invoke();
    }
}
