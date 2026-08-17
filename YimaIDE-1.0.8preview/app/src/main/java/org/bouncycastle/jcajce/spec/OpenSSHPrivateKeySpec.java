package org.bouncycastle.jcajce.spec;

import defpackage.w01;
import java.security.spec.EncodedKeySpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class OpenSSHPrivateKeySpec extends EncodedKeySpec {
    private final String format;

    public OpenSSHPrivateKeySpec(byte[] bArr) {
        String str;
        super(bArr);
        byte b = bArr[0];
        if (b == 48) {
            str = "ASN.1";
        } else {
            if (b != 111) {
                w01.a("unknown byte encoding");
                throw null;
            }
            str = "OpenSSH";
        }
        this.format = str;
    }

    @Override // java.security.spec.EncodedKeySpec
    public String getFormat() {
        return this.format;
    }
}
