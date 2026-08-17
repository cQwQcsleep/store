package kotlin.reflect.jvm.internal.impl.util;

import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class NullableArrayMapAccessor<K, V, T extends V> extends AbstractArrayMapOwner.AbstractArrayMapAccessor<K, V, T> implements ReadOnlyProperty<AbstractArrayMapOwner<K, V>, V> {
    public NullableArrayMapAccessor(int i) {
        super(i);
    }

    public T getValue(AbstractArrayMapOwner<K, V> abstractArrayMapOwner, KProperty<?> kProperty) {
        abstractArrayMapOwner.getClass();
        kProperty.getClass();
        return extractValue(abstractArrayMapOwner);
    }

    public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
        return getValue((AbstractArrayMapOwner) obj, (KProperty<?>) kProperty);
    }
}
