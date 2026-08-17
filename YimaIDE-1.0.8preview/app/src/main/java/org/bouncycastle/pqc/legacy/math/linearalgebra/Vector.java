package org.bouncycastle.pqc.legacy.math.linearalgebra;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class Vector {
    protected int length;

    public abstract Vector add(Vector vector);

    public abstract boolean equals(Object obj);

    public abstract byte[] getEncoded();

    public final int getLength() {
        return this.length;
    }

    public abstract int hashCode();

    public abstract boolean isZero();

    public abstract Vector multiply(Permutation permutation);

    public abstract String toString();
}
