package org.bouncycastle.crypto.kems;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.security.auth.DestroyFailedException;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class SecretWithEncapsulationImpl implements SecretWithEncapsulation {
    private final byte[] cipher_text;
    private final AtomicBoolean hasBeenDestroyed = new AtomicBoolean(false);
    private final byte[] sessionKey;

    public SecretWithEncapsulationImpl(byte[] bArr, byte[] bArr2) {
        this.sessionKey = bArr;
        this.cipher_text = bArr2;
    }

    public void checkDestroyed() {
        if (isDestroyed()) {
            k2d.a("data has been destroyed");
        }
    }

    public void destroy() throws DestroyFailedException {
        if (this.hasBeenDestroyed.getAndSet(true)) {
            return;
        }
        Arrays.clear(this.sessionKey);
        Arrays.clear(this.cipher_text);
    }

    public byte[] getEncapsulation() {
        byte[] bArrClone = Arrays.clone(this.cipher_text);
        checkDestroyed();
        return bArrClone;
    }

    public byte[] getSecret() {
        byte[] bArrClone = Arrays.clone(this.sessionKey);
        checkDestroyed();
        return bArrClone;
    }

    public boolean isDestroyed() {
        return this.hasBeenDestroyed.get();
    }
}
