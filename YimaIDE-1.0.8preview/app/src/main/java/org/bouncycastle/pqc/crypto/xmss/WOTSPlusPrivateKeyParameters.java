package org.bouncycastle.pqc.crypto.xmss;

import defpackage.x0e;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class WOTSPlusPrivateKeyParameters {
    private final byte[][] privateKey;

    public WOTSPlusPrivateKeyParameters(WOTSPlusParameters wOTSPlusParameters, byte[][] bArr) {
        if (wOTSPlusParameters == null) {
            x0e.a("params == null");
            throw null;
        }
        if (bArr == null) {
            x0e.a("privateKey == null");
            throw null;
        }
        if (XMSSUtil.hasNullPointer(bArr)) {
            x0e.a("privateKey byte array == null");
            throw null;
        }
        if (bArr.length != wOTSPlusParameters.getLen()) {
            w01.a("wrong privateKey format");
            throw null;
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != wOTSPlusParameters.getTreeDigestSize()) {
                w01.a("wrong privateKey format");
                throw null;
            }
        }
        this.privateKey = XMSSUtil.cloneArray(bArr);
    }

    public byte[][] toByteArray() {
        return XMSSUtil.cloneArray(this.privateKey);
    }
}
