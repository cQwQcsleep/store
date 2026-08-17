package com.sun.jna.platform.linux;

import com.sun.jna.IntegerType;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XAttr extends Library {
    public static final int E2BIG = 7;
    public static final int EDQUOT = 122;
    public static final int EEXIST = 17;
    public static final int ENOATTR = 61;
    public static final int ENODATA = 61;
    public static final int ENOSPC = 28;
    public static final int ENOTSUP = 95;
    public static final int EPERM = 1;
    public static final int ERANGE = 34;
    public static final XAttr INSTANCE = (XAttr) Native.load(XAttr.class);
    public static final int XATTR_CREATE = 1;
    public static final int XATTR_REPLACE = 2;

    ssize_t fgetxattr(int i, String str, Pointer pointer, size_t size_tVar);

    ssize_t fgetxattr(int i, String str, byte[] bArr, size_t size_tVar);

    ssize_t flistxattr(int i, Pointer pointer, size_t size_tVar);

    ssize_t flistxattr(int i, byte[] bArr, size_t size_tVar);

    int fremovexattr(int i, String str);

    int fsetxattr(int i, String str, Pointer pointer, size_t size_tVar, int i2);

    int fsetxattr(int i, String str, byte[] bArr, size_t size_tVar, int i2);

    ssize_t getxattr(String str, String str2, Pointer pointer, size_t size_tVar);

    ssize_t getxattr(String str, String str2, byte[] bArr, size_t size_tVar);

    ssize_t lgetxattr(String str, String str2, Pointer pointer, size_t size_tVar);

    ssize_t lgetxattr(String str, String str2, byte[] bArr, size_t size_tVar);

    ssize_t listxattr(String str, Pointer pointer, size_t size_tVar);

    ssize_t listxattr(String str, byte[] bArr, size_t size_tVar);

    ssize_t llistxattr(String str, Pointer pointer, size_t size_tVar);

    ssize_t llistxattr(String str, byte[] bArr, size_t size_tVar);

    int lremovexattr(String str, String str2);

    int lsetxattr(String str, String str2, Pointer pointer, size_t size_tVar, int i);

    int lsetxattr(String str, String str2, byte[] bArr, size_t size_tVar, int i);

    int removexattr(String str, String str2);

    int setxattr(String str, String str2, Pointer pointer, size_t size_tVar, int i);

    int setxattr(String str, String str2, byte[] bArr, size_t size_tVar, int i);

    public static class size_t extends IntegerType {
        public static final size_t ZERO = new size_t();
        private static final long serialVersionUID = 1;

        public size_t(long j) {
            super(Native.SIZE_T_SIZE, j, true);
        }

        public size_t() {
            this(0L);
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
