package com.shadow.okhttp3.internal.tls;

import java.security.cert.X509Certificate;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface TrustRootIndex {
    X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate);
}
