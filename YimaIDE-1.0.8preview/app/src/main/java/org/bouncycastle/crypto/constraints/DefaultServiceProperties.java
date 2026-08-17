package org.bouncycastle.crypto.constraints;

import defpackage.w01;
import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class DefaultServiceProperties implements CryptoServiceProperties {
    private final String algorithm;
    private final int bitsOfSecurity;
    private final Object params;
    private final CryptoServicePurpose purpose;

    public DefaultServiceProperties(String str, int i, Object obj, CryptoServicePurpose cryptoServicePurpose) {
        this.algorithm = str;
        this.bitsOfSecurity = i;
        this.params = obj;
        if (obj instanceof CryptoServicePurpose) {
            w01.a("params should not be CryptoServicePurpose");
            throw null;
        }
        this.purpose = cryptoServicePurpose;
    }

    @Override // org.bouncycastle.crypto.CryptoServiceProperties
    public int bitsOfSecurity() {
        return this.bitsOfSecurity;
    }

    @Override // org.bouncycastle.crypto.CryptoServiceProperties
    public Object getParams() {
        return this.params;
    }

    @Override // org.bouncycastle.crypto.CryptoServiceProperties
    public CryptoServicePurpose getPurpose() {
        return this.purpose;
    }

    @Override // org.bouncycastle.crypto.CryptoServiceProperties
    public String getServiceName() {
        return this.algorithm;
    }

    public DefaultServiceProperties(String str, int i, Object obj) {
        this(str, i, obj, CryptoServicePurpose.ANY);
    }

    public DefaultServiceProperties(String str, int i) {
        this(str, i, null, CryptoServicePurpose.ANY);
    }
}
