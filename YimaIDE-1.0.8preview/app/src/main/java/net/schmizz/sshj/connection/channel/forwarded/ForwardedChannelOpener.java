package net.schmizz.sshj.connection.channel.forwarded;

import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.transport.TransportException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ForwardedChannelOpener {
    String getChannelType();

    void handleOpen(SSHPacket sSHPacket) throws ConnectionException, TransportException;
}
