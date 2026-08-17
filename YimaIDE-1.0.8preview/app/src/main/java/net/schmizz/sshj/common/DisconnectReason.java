package net.schmizz.sshj.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public enum DisconnectReason {
    UNKNOWN,
    HOST_NOT_ALLOWED_TO_CONNECT,
    PROTOCOL_ERROR,
    KEY_EXCHANGE_FAILED,
    RESERVED,
    MAC_ERROR,
    COMPRESSION_ERROR,
    SERVICE_NOT_AVAILABLE,
    PROTOCOL_VERSION_NOT_SUPPORTED,
    HOST_KEY_NOT_VERIFIABLE,
    CONNECTION_LOST,
    BY_APPLICATION,
    TOO_MANY_CONNECTIONS,
    AUTH_CANCELLED_BY_USER,
    NO_MORE_AUTH_METHODS_AVAILABLE,
    ILLEGAL_USER_NAME;

    public static DisconnectReason fromInt(int i) {
        return (i < 0 || i > values().length) ? UNKNOWN : values()[i];
    }

    public int toInt() {
        return ordinal();
    }
}
