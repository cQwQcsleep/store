package net.schmizz.sshj.transport.verification;

import java.security.PublicKey;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface HostKeyVerifier {
    List<String> findExistingAlgorithms(String str, int i);

    boolean verify(String str, int i, PublicKey publicKey);
}
