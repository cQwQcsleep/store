package net.schmizz.sshj.common;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class SSHPacket extends Buffer<SSHPacket> {
    public SSHPacket(SSHPacket sSHPacket) {
        this.data = Arrays.copyOf(sSHPacket.data, sSHPacket.wpos);
        this.rpos = sSHPacket.rpos;
        this.wpos = sSHPacket.wpos;
    }

    public SSHPacket putMessageID(Message message) {
        return putByte(message.toByte());
    }

    public Message readMessageID() throws Buffer.BufferException {
        return Message.fromByte(readByte());
    }

    public SSHPacket(int i) {
        super(i);
    }

    public SSHPacket(byte[] bArr) {
        super(bArr);
    }

    public SSHPacket(Message message) {
        this.wpos = 5;
        this.rpos = 5;
        putMessageID(message);
    }

    public SSHPacket() {
    }
}
