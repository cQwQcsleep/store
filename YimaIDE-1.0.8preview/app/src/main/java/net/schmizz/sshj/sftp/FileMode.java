package net.schmizz.sshj.sftp;

import java.util.Collections;
import java.util.Set;
import net.schmizz.sshj.xfer.FilePermission;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class FileMode {
    private final int mask;
    private final Type type = Type.fromMask(getTypeMask());
    private final Set<FilePermission> perms = FilePermission.fromMask(getPermissionsMask());

    public enum Type {
        BLOCK_SPECIAL(24576),
        CHAR_SPECIAL(8192),
        FIFO_SPECIAL(4096),
        SOCKET_SPECIAL(49152),
        REGULAR(32768),
        DIRECTORY(16384),
        SYMLINK(40960),
        UNKNOWN(0);

        private final int val;

        Type(int i) {
            this.val = i;
        }

        public static Type fromMask(int i) {
            for (Type type : values()) {
                if (type.val == i) {
                    return type;
                }
            }
            return UNKNOWN;
        }

        public int toMask() {
            return this.val;
        }
    }

    public FileMode(int i) {
        this.mask = i;
    }

    public int getMask() {
        return this.mask;
    }

    public Set<FilePermission> getPermissions() {
        return Collections.unmodifiableSet(this.perms);
    }

    public int getPermissionsMask() {
        return this.mask & 4095;
    }

    public Type getType() {
        return this.type;
    }

    public int getTypeMask() {
        return this.mask & 61440;
    }

    public String toString() {
        return "[mask=" + Integer.toOctalString(this.mask) + "]";
    }
}
