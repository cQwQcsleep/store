package org.bouncycastle.pqc.crypto.xmss;

import defpackage.x0e;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class WOTSPlusSignature {
    private byte[][] signature;

    public WOTSPlusSignature(WOTSPlusParameters wOTSPlusParameters, byte[][] bArr) {
        if (wOTSPlusParameters == null) {
            x0e.a("params == null");
            throw null;
        }
        if (bArr == null) {
            x0e.a("signature == null");
            throw null;
        }
        if (XMSSUtil.hasNullPointer(bArr)) {
            x0e.a("signature byte array == null");
            throw null;
        }
        if (bArr.length != wOTSPlusParameters.getLen()) {
            w01.a("wrong signature size");
            throw null;
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != wOTSPlusParameters.getTreeDigestSize()) {
                w01.a("wrong signature format");
                throw null;
            }
        }
        this.signature = XMSSUtil.cloneArray(bArr);
    }

    public byte[][] toByteArray() {
        return XMSSUtil.cloneArray(this.signature);
    }
}
