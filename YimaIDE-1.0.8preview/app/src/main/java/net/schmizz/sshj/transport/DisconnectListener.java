package net.schmizz.sshj.transport;

import net.schmizz.sshj.common.DisconnectReason;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface DisconnectListener {
    void notifyDisconnect(DisconnectReason disconnectReason, String str);
}
