package net.schmizz.sshj.transport.verification;

import net.schmizz.sshj.transport.NegotiatedAlgorithms;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface AlgorithmsVerifier {
    boolean verify(NegotiatedAlgorithms negotiatedAlgorithms);
}
