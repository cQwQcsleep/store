package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.PrivateKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface DilithiumPrivateKey extends PrivateKey, DilithiumKey {
    DilithiumPublicKey getPublicKey();
}
