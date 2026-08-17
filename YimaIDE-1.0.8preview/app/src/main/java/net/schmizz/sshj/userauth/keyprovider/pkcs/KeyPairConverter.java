package net.schmizz.sshj.userauth.keyprovider.pkcs;

import java.io.IOException;
import org.bouncycastle.openssl.PEMKeyPair;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface KeyPairConverter<T> {
    PEMKeyPair getKeyPair(T t) throws IOException;
}
