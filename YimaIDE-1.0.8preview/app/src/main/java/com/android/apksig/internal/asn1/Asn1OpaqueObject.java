package com.android.apksig.internal.asn1;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class Asn1OpaqueObject {
    private final ByteBuffer mEncoded;

    public Asn1OpaqueObject(ByteBuffer byteBuffer) {
        this.mEncoded = byteBuffer.slice();
    }

    public ByteBuffer getEncoded() {
        return this.mEncoded.slice();
    }

    public Asn1OpaqueObject(byte[] bArr) {
        this.mEncoded = ByteBuffer.wrap(bArr);
    }
}
