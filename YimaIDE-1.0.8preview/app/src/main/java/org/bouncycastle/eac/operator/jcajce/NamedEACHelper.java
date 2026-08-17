package org.bouncycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class NamedEACHelper extends EACHelper {
    private final String providerName;

    public NamedEACHelper(String str) {
        this.providerName = str;
    }

    @Override // org.bouncycastle.eac.operator.jcajce.EACHelper
    public Signature createSignature(String str) throws NoSuchAlgorithmException, NoSuchProviderException {
        return Signature.getInstance(str, this.providerName);
    }
}
