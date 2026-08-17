package net.schmizz.sshj.connection.channel;

import net.schmizz.sshj.connection.ConnectionException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class OpenFailException extends ConnectionException {
    private final String channelType;
    private final String message;
    private final Reason reason;

    public enum Reason {
        UNKNOWN(0),
        ADMINISTRATIVELY_PROHIBITED(1),
        CONNECT_FAILED(2),
        UNKNOWN_CHANNEL_TYPE(3),
        RESOURCE_SHORTAGE(4);

        private final int code;

        Reason(int i) {
            this.code = i;
        }

        public static Reason fromInt(int i) {
            for (Reason reason : values()) {
                if (reason.code == i) {
                    return reason;
                }
            }
            return UNKNOWN;
        }

        public int getCode() {
            return this.code;
        }
    }

    public OpenFailException(String str, int i, String str2) {
        super(str2);
        this.channelType = str;
        this.reason = Reason.fromInt(i);
        this.message = str2;
    }

    public String getChannelType() {
        return this.channelType;
    }

    @Override // net.schmizz.sshj.common.SSHException, java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public Reason getReason() {
        return this.reason;
    }

    @Override // net.schmizz.sshj.common.SSHException, java.lang.Throwable
    public String toString() {
        return "Opening `" + this.channelType + "` channel failed: " + getMessage();
    }

    public OpenFailException(String str, Reason reason, String str2) {
        super(str2);
        this.channelType = str;
        this.reason = reason;
        this.message = str2;
    }
}
