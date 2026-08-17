package org.bouncycastle.est.jcajce;

import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface SSLSocketFactoryCreator {
    SSLSocketFactory createFactory() throws Exception;

    boolean isTrusted();
}
