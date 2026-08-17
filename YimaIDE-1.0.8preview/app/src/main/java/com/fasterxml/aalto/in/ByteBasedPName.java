package com.fasterxml.aalto.in;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ByteBasedPName extends PName {
    protected final int mHash;

    public ByteBasedPName(String str, String str2, String str3, int i) {
        super(str, str2, str3);
        this.mHash = i;
    }

    public abstract boolean equals(int i, int i2);

    public abstract boolean equals(int[] iArr, int i);

    @Override // com.fasterxml.aalto.in.PName
    public int hashCode() {
        return this.mHash;
    }

    public abstract boolean hashEquals(int i, int i2, int i3);

    public abstract boolean hashEquals(int i, int[] iArr, int i2);
}
