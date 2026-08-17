package net.schmizz.sshj.xfer;

import net.schmizz.sshj.common.StreamCopier;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface TransferListener {
    TransferListener directory(String str);

    StreamCopier.Listener file(String str, long j);
}
