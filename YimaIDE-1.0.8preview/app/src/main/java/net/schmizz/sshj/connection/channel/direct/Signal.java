package net.schmizz.sshj.connection.channel.direct;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public enum Signal {
    ABRT,
    ALRM,
    FPE,
    HUP,
    ILL,
    INT,
    KILL,
    PIPE,
    QUIT,
    SEGV,
    TERM,
    USR1,
    USR2,
    UNKNOWN;

    public static Signal fromString(String str) {
        for (Signal signal : values()) {
            if (signal.toString().equals(str)) {
                return signal;
            }
        }
        return UNKNOWN;
    }
}
