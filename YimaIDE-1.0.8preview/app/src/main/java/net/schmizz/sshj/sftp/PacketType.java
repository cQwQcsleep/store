package net.schmizz.sshj.sftp;

import org.bouncycastle.asn1.x509.DisplayText;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public enum PacketType {
    UNKNOWN(0),
    INIT(1),
    VERSION(2),
    OPEN(3),
    CLOSE(4),
    READ(5),
    WRITE(6),
    LSTAT(7),
    FSTAT(8),
    SETSTAT(9),
    FSETSTAT(10),
    OPENDIR(11),
    READDIR(12),
    REMOVE(13),
    MKDIR(14),
    RMDIR(15),
    REALPATH(16),
    STAT(17),
    RENAME(18),
    READLINK(19),
    SYMLINK(20),
    STATUS(101),
    HANDLE(102),
    DATA(103),
    NAME(104),
    ATTRS(105),
    EXTENDED(DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE),
    EXTENDED_REPLY(201);

    private static final PacketType[] cache = new PacketType[256];
    private final byte b;

    static {
        for (PacketType packetType : values()) {
            cache[packetType.toByte() & 255] = packetType;
        }
    }

    PacketType(int i) {
        this.b = (byte) i;
    }

    public static PacketType fromByte(byte b) {
        return cache[b & 255];
    }

    public byte toByte() {
        return this.b;
    }
}
