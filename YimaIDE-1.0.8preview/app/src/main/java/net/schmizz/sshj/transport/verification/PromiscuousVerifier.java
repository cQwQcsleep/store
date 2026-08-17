package net.schmizz.sshj.transport.verification;

import java.security.PublicKey;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class PromiscuousVerifier implements HostKeyVerifier {
    public List<String> findExistingAlgorithms(String str, int i) {
        return Collections.EMPTY_LIST;
    }

    public boolean verify(String str, int i, PublicKey publicKey) {
        return true;
    }
}
