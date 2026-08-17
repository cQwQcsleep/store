package com.fasterxml.aalto.in;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PNameN extends ByteBasedPName {
    final int mQuadLen;
    final int[] mQuads;

    public PNameN(String str, String str2, String str3, int i, int[] iArr, int i2) {
        super(str, str2, str3, i);
        this.mQuads = iArr;
        this.mQuadLen = i2;
    }

    @Override // com.fasterxml.aalto.in.PName
    public PName createBoundName(NsBinding nsBinding) {
        PNameN pNameN = new PNameN(this._prefixedName, this._prefix, this._localName, this.mHash, this.mQuads, this.mQuadLen);
        pNameN._namespaceBinding = nsBinding;
        return pNameN;
    }

    @Override // com.fasterxml.aalto.in.ByteBasedPName
    public boolean equals(int i, int i2) {
        int i3 = this.mQuadLen;
        if (i3 < 3) {
            int[] iArr = this.mQuads;
            if (i3 == 1) {
                return iArr[0] == i && i2 == 0;
            }
            if (iArr[0] == i && iArr[1] == i2) {
                return true;
            }
        }
        return false;
    }

    @Override // com.fasterxml.aalto.in.PName
    public int getQuad(int i) {
        if (i < this.mQuadLen) {
            return this.mQuads[i];
        }
        return 0;
    }

    @Override // com.fasterxml.aalto.in.ByteBasedPName
    public boolean hashEquals(int i, int i2, int i3) {
        int i4;
        if (i == this.mHash && (i4 = this.mQuadLen) < 3) {
            int[] iArr = this.mQuads;
            if (i4 == 1) {
                return iArr[0] == i2 && i3 == 0;
            }
            if (iArr[0] == i2 && iArr[1] == i3) {
                return true;
            }
        }
        return false;
    }

    @Override // com.fasterxml.aalto.in.PName
    public int sizeInQuads() {
        return this.mQuadLen;
    }

    @Override // com.fasterxml.aalto.in.ByteBasedPName
    public boolean equals(int[] iArr, int i) {
        if (i != this.mQuadLen) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != this.mQuads[i2]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.fasterxml.aalto.in.ByteBasedPName
    public boolean hashEquals(int i, int[] iArr, int i2) {
        if (i != this.mHash || i2 != this.mQuadLen) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (iArr[i3] != this.mQuads[i3]) {
                return false;
            }
        }
        return true;
    }
}
