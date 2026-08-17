package com.fasterxml.aalto.in;

import com.fasterxml.aalto.impl.ErrorConsts;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PNameC extends PName {
    protected final int mHash;

    public PNameC(String str, String str2, String str3, int i) {
        super(str, str2, str3);
        this.mHash = i;
    }

    public static PNameC construct(String str, int i) {
        int iIndexOf = str.indexOf(58);
        return iIndexOf < 0 ? new PNameC(str, null, str, i) : new PNameC(str, str.substring(0, iIndexOf).intern(), str.substring(iIndexOf + 1).intern(), i);
    }

    @Override // com.fasterxml.aalto.in.PName
    public PName createBoundName(NsBinding nsBinding) {
        PNameC pNameC = new PNameC(this._prefixedName, this._prefix, this._localName, this.mHash);
        pNameC._namespaceBinding = nsBinding;
        return pNameC;
    }

    public boolean equalsPName(char[] cArr, int i, int i2, int i3) {
        if (i3 != this.mHash) {
            return false;
        }
        String str = this._prefixedName;
        if (i2 != str.length()) {
            return false;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (cArr[i + i4] != str.charAt(i4)) {
                return false;
            }
        }
        return true;
    }

    public int getCustomHash() {
        return this.mHash;
    }

    @Override // com.fasterxml.aalto.in.PName
    public int getQuad(int i) {
        ErrorConsts.throwInternalError();
        return 0;
    }

    @Override // com.fasterxml.aalto.in.PName
    public int hashCode() {
        return this.mHash;
    }

    @Override // com.fasterxml.aalto.in.PName
    public int sizeInQuads() {
        ErrorConsts.throwInternalError();
        return 0;
    }
}
