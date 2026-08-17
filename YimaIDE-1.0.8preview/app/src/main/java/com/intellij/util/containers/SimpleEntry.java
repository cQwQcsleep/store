package com.intellij.util.containers;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SimpleEntry<V> implements IntObjectMap.Entry<V> {
    private final int myKey;
    private final V myValue;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "value";
        } else {
            objArr[0] = "com/intellij/util/containers/SimpleEntry";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/containers/SimpleEntry";
        } else {
            objArr[1] = "getValue";
        }
        if (i != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public SimpleEntry(int i, V v) {
        if (v == null) {
            $$$reportNull$$$0(0);
        }
        this.myKey = i;
        this.myValue = v;
    }

    @Override // com.intellij.util.containers.IntObjectMap.Entry
    public int getKey() {
        return this.myKey;
    }

    @Override // com.intellij.util.containers.IntObjectMap.Entry
    public V getValue() {
        V v = this.myValue;
        if (v == null) {
            $$$reportNull$$$0(1);
        }
        return v;
    }
}
