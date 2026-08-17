package org.bouncycastle.pqc.crypto.xmss;

import defpackage.x0e;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class WOTSPlusPublicKeyParameters {
    private final byte[][] publicKey;

    public WOTSPlusPublicKeyParameters(WOTSPlusParameters wOTSPlusParameters, byte[][] bArr) {
        if (wOTSPlusParameters == null) {
            x0e.a("params == null");
            throw null;
        }
        if (bArr == null) {
            x0e.a("publicKey == null");
            throw null;
        }
        if (XMSSUtil.hasNullPointer(bArr)) {
            x0e.a("publicKey byte array == null");
            throw null;
        }
        if (bArr.length != wOTSPlusParameters.getLen()) {
            w01.a("wrong publicKey size");
            throw null;
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != wOTSPlusParameters.getTreeDigestSize()) {
                w01.a("wrong publicKey format");
                throw null;
            }
        }
        this.publicKey = XMSSUtil.cloneArray(bArr);
    }

    public byte[][] toByteArray() {
        return XMSSUtil.cloneArray(this.publicKey);
    }
}
