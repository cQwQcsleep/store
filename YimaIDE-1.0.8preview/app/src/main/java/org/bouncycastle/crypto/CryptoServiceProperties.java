package org.bouncycastle.crypto;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface CryptoServiceProperties {
    int bitsOfSecurity();

    Object getParams();

    CryptoServicePurpose getPurpose();

    String getServiceName();
}
