package org.bouncycastle.est;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ESTClientProvider {
    boolean isTrusted();

    ESTClient makeClient() throws ESTException;
}
