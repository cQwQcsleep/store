package org.bouncycastle.jce.interfaces;

import java.security.PublicKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface MQVPublicKey extends PublicKey {
    PublicKey getEphemeralKey();

    PublicKey getStaticKey();
}
