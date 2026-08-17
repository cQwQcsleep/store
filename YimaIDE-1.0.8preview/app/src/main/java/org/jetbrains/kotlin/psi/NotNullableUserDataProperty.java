package org.jetbrains.kotlin.psi;

import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0004B\u001d\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\b\u0010\tJ'\u0010\u000f\u001a\u00078\u0001¢\u0006\u0002\b\u00102\u0006\u0010\u0011\u001a\u00028\u00002\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0086\u0002¢\u0006\u0002\u0010\u0014J*\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00028\u00002\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0017\u001a\u00028\u0001H\u0086\u0002¢\u0006\u0002\u0010\u0018R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/psi/NotNullableUserDataProperty;", "R", "Lcom/intellij/openapi/util/UserDataHolder;", "T", "", "key", "Lcom/intellij/openapi/util/Key;", "defaultValue", "<init>", "(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V", "getKey", "()Lcom/intellij/openapi/util/Key;", "getDefaultValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "Lorg/jetbrains/annotations/Nullable;", "thisRef", "desc", "Lkotlin/reflect/KProperty;", "(Lcom/intellij/openapi/util/UserDataHolder;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Lcom/intellij/openapi/util/UserDataHolder;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "org.jetbrains.kotlin:psi-utils"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NotNullableUserDataProperty<R extends UserDataHolder, T> {
    private final T defaultValue;
    private final Key<T> key;

    public NotNullableUserDataProperty(Key<T> key, T t) {
        key.getClass();
        t.getClass();
        this.key = key;
        this.defaultValue = t;
    }

    public final T getDefaultValue() {
        return this.defaultValue;
    }

    public final Key<T> getKey() {
        return this.key;
    }

    public final T getValue(R thisRef, KProperty<?> desc) {
        thisRef.getClass();
        desc.getClass();
        T t = (T) thisRef.getUserData(this.key);
        return t == null ? this.defaultValue : t;
    }

    public final void setValue(R thisRef, KProperty<?> desc, T value) {
        thisRef.getClass();
        desc.getClass();
        value.getClass();
        Key<T> key = this.key;
        if (Intrinsics.areEqual(value, this.defaultValue)) {
            value = null;
        }
        thisRef.putUserData(key, value);
    }
}
