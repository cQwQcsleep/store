package com.sun.jna.platform.unix;

import com.sun.jna.IntegerType;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LibCAPI extends Reboot, Resource {
    public static final int HOST_NAME_MAX = 255;

    int close(int i);

    int getdomainname(byte[] bArr, int i);

    int getegid();

    String getenv(String str);

    int geteuid();

    int getgid();

    int gethostname(byte[] bArr, int i);

    int getloadavg(double[] dArr, int i);

    int getuid();

    int msync(Pointer pointer, size_t size_tVar, int i);

    int munmap(Pointer pointer, size_t size_tVar);

    int setdomainname(String str, int i);

    int setegid(int i);

    int setenv(String str, String str2, int i);

    int seteuid(int i);

    int setgid(int i);

    int sethostname(String str, int i);

    int setuid(int i);

    int unsetenv(String str);

    public static class size_t extends IntegerType {
        public static final size_t ZERO = new size_t();
        private static final long serialVersionUID = 1;

        public size_t(long j) {
            super(Native.SIZE_T_SIZE, j, true);
        }

        public size_t() {
            this(0L);
        }

        public static class ByReference extends com.sun.jna.ptr.ByReference {
            public ByReference(long j) {
                this(new size_t(j));
            }

            public size_t getValue() {
                return new size_t(longValue());
            }

            public long longValue() {
                int i = Native.SIZE_T_SIZE;
                Pointer pointer = getPointer();
                return i > 4 ? pointer.getLong(0L) : pointer.getInt(0L);
            }

            public void setValue(size_t size_tVar) {
                if (Native.SIZE_T_SIZE > 4) {
                    getPointer().setLong(0L, size_tVar.longValue());
                } else {
                    getPointer().setInt(0L, size_tVar.intValue());
                }
            }

            public ByReference() {
                this(0L);
            }

            public ByReference(size_t size_tVar) {
                super(Native.SIZE_T_SIZE);
                setValue(size_tVar);
            }

            public void setValue(long j) {
                setValue(new size_t(j));
            }
        }
    }

    public static class ssize_t extends IntegerType {
        public static final ssize_t ZERO = new ssize_t();
        private static final long serialVersionUID = 1;

        public ssize_t(long j) {
            super(Native.SIZE_T_SIZE, j, false);
        }

        public ssize_t() {
            this(0L);
        }
    }
}
