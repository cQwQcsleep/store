package net.schmizz.sshj.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public enum Message {
    UNKNOWN(0),
    DISCONNECT(1),
    IGNORE(2),
    UNIMPLEMENTED(3),
    DEBUG(4),
    SERVICE_REQUEST(5),
    SERVICE_ACCEPT(6),
    EXT_INFO(7),
    KEXINIT(20),
    NEWKEYS(21),
    KEXDH_INIT(30),
    KEXDH_31(31),
    KEX_DH_GEX_INIT(32),
    KEX_DH_GEX_REPLY(33),
    KEX_DH_GEX_REQUEST(34),
    USERAUTH_REQUEST(50),
    USERAUTH_FAILURE(51),
    USERAUTH_SUCCESS(52),
    USERAUTH_BANNER(53),
    USERAUTH_60(60),
    USERAUTH_INFO_RESPONSE(61),
    USERAUTH_GSSAPI_EXCHANGE_COMPLETE(63),
    USERAUTH_GSSAPI_MIC(66),
    GLOBAL_REQUEST(80),
    REQUEST_SUCCESS(81),
    REQUEST_FAILURE(82),
    CHANNEL_OPEN(90),
    CHANNEL_OPEN_CONFIRMATION(91),
    CHANNEL_OPEN_FAILURE(92),
    CHANNEL_WINDOW_ADJUST(93),
    CHANNEL_DATA(94),
    CHANNEL_EXTENDED_DATA(95),
    CHANNEL_EOF(96),
    CHANNEL_CLOSE(97),
    CHANNEL_REQUEST(98),
    CHANNEL_SUCCESS(99),
    CHANNEL_FAILURE(100);

    private static final Message[] cache = new Message[256];
    private final byte b;

    static {
        for (Message message : values()) {
            cache[message.toByte()] = message;
        }
        for (int i = 0; i < 256; i++) {
            Message[] messageArr = cache;
            if (messageArr[i] == null) {
                messageArr[i] = UNKNOWN;
            }
        }
    }

    Message(int i) {
        this.b = (byte) i;
    }

    public static Message fromByte(byte b) {
        return cache[b];
    }

    public boolean geq(int i) {
        return this.b >= i;
    }

    public boolean gt(int i) {
        return this.b > i;
    }

    public boolean in(int i, int i2) {
        byte b = this.b;
        return b >= i && b <= i2;
    }

    public boolean leq(int i) {
        return this.b <= i;
    }

    public boolean lt(int i) {
        return this.b < i;
    }

    public byte toByte() {
        return this.b;
    }
}
