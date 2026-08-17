package net.schmizz.sshj.sftp;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public enum RenameFlags {
    OVERWRITE(1),
    ATOMIC(2),
    NATIVE(4);

    private final long flag;

    RenameFlags(long j) {
        this.flag = j;
    }

    public long longValue() {
        return this.flag;
    }
}
