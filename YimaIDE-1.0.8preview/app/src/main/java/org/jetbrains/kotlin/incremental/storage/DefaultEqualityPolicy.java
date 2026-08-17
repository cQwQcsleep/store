package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.containers.hash.EqualityPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\bJ\u001d\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/DefaultEqualityPolicy;", "T", "Lcom/intellij/util/containers/hash/EqualityPolicy;", "<init>", "()V", "getHashCode", "", "value", "(Ljava/lang/Object;)I", "isEqual", "", "value1", "value2", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultEqualityPolicy<T> implements EqualityPolicy<T> {
    public int getHashCode(T value) {
        if (value != null) {
            return value.hashCode();
        }
        return 0;
    }

    public boolean isEqual(T value1, T value2) {
        return Intrinsics.areEqual(value1, value2);
    }
}
