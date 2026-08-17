package net.schmizz.sshj.userauth.keyprovider;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import net.schmizz.sshj.common.KeyType;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface KeyProvider {
    PrivateKey getPrivate() throws IOException;

    PublicKey getPublic() throws IOException;

    KeyType getType() throws IOException;
}
