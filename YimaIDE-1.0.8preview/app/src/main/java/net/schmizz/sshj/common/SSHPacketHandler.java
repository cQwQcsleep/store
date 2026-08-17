package net.schmizz.sshj.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface SSHPacketHandler {
    void handle(Message message, SSHPacket sSHPacket) throws SSHException;
}
