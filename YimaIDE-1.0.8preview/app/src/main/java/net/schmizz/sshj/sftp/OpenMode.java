package net.schmizz.sshj.sftp;

import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public enum OpenMode {
    READ(1),
    WRITE(2),
    APPEND(4),
    CREAT(8),
    TRUNC(16),
    EXCL(32);

    private final int pflag;

    OpenMode(int i) {
        this.pflag = i;
    }

    public static int toMask(Set<OpenMode> set) {
        Iterator<OpenMode> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            i |= it.next().pflag;
        }
        return i;
    }
}
