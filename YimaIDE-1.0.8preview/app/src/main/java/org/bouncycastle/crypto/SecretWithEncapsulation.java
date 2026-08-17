package org.bouncycastle.crypto;

import javax.security.auth.Destroyable;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface SecretWithEncapsulation extends Destroyable {
    byte[] getEncapsulation();

    byte[] getSecret();
}
