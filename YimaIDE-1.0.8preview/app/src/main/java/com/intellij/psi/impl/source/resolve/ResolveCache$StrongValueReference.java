package com.intellij.psi.impl.source.resolve;

import com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class ResolveCache$StrongValueReference<K, V> implements ConcurrentWeakKeySoftValueHashMap.ValueReference<K, V> {
    private final V myValue;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "value";
        } else {
            objArr[0] = "com/intellij/psi/impl/source/resolve/ResolveCache$StrongValueReference";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/psi/impl/source/resolve/ResolveCache$StrongValueReference";
        } else {
            objArr[1] = "get";
        }
        if (i != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public ResolveCache$StrongValueReference(V v) {
        if (v == null) {
            $$$reportNull$$$0(0);
        }
        this.myValue = v;
    }

    @Override // com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap.ValueReference, java.util.function.Supplier
    public V get() {
        V v = this.myValue;
        if (v == null) {
            $$$reportNull$$$0(1);
        }
        return v;
    }

    @Override // com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap.ValueReference
    public ConcurrentWeakKeySoftValueHashMap.KeyReference<K, V> getKeyReference() {
        throw new UnsupportedOperationException();
    }
}
