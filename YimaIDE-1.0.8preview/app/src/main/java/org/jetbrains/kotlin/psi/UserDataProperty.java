package org.jetbrains.kotlin.psi;

import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolder;
import kotlin.Metadata;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0004B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ)\u0010\u000b\u001a\t\u0018\u00018\u0001¢\u0006\u0002\b\f2\u0006\u0010\r\u001a\u00028\u00002\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0086\u0002¢\u0006\u0002\u0010\u0010J,\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00028\u00002\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00018\u0001H\u0086\u0002¢\u0006\u0002\u0010\u0014R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/psi/UserDataProperty;", "R", "Lcom/intellij/openapi/util/UserDataHolder;", "T", "", "key", "Lcom/intellij/openapi/util/Key;", "<init>", "(Lcom/intellij/openapi/util/Key;)V", "getKey", "()Lcom/intellij/openapi/util/Key;", "getValue", "Lorg/jetbrains/annotations/Nullable;", "thisRef", "desc", "Lkotlin/reflect/KProperty;", "(Lcom/intellij/openapi/util/UserDataHolder;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Lcom/intellij/openapi/util/UserDataHolder;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class UserDataProperty<R extends UserDataHolder, T> {
    private final Key<T> key;

    public UserDataProperty(Key<T> key) {
        key.getClass();
        this.key = key;
    }

    public final Key<T> getKey() {
        return this.key;
    }

    public final T getValue(R thisRef, KProperty<?> desc) {
        thisRef.getClass();
        desc.getClass();
        return (T) thisRef.getUserData(this.key);
    }

    public final void setValue(R thisRef, KProperty<?> desc, T value) {
        thisRef.getClass();
        desc.getClass();
        thisRef.putUserData(this.key, value);
    }
}
