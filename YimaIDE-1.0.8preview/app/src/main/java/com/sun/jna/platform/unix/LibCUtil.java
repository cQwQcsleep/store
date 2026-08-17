package com.sun.jna.platform.unix;

import com.sun.jna.Function;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LibCUtil {
    private static final NativeLibrary LIBC;
    private static Function ftruncate;
    private static boolean ftruncate64;
    private static Function mmap;
    private static boolean mmap64;

    static {
        NativeLibrary nativeLibrary = NativeLibrary.getInstance("c");
        LIBC = nativeLibrary;
        mmap = null;
        mmap64 = false;
        ftruncate = null;
        ftruncate64 = false;
        try {
            mmap = nativeLibrary.getFunction("mmap64", 64);
            mmap64 = true;
        } catch (UnsatisfiedLinkError unused) {
            mmap = LIBC.getFunction("mmap", 64);
        }
        try {
            ftruncate = LIBC.getFunction("ftruncate64", 64);
            ftruncate64 = true;
        } catch (UnsatisfiedLinkError unused2) {
            ftruncate = LIBC.getFunction("ftruncate", 64);
        }
    }

    private LibCUtil() {
    }

    public static int ftruncate(int i, long j) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        if (ftruncate64 || Native.LONG_SIZE > 4) {
            objArr[1] = Long.valueOf(j);
        } else {
            require32Bit(j, "length");
            objArr[1] = Integer.valueOf((int) j);
        }
        return ftruncate.invokeInt(objArr);
    }

    public static Pointer mmap(Pointer pointer, long j, int i, int i2, int i3, long j2) {
        Object[] objArr = new Object[6];
        objArr[0] = pointer;
        if (Native.SIZE_T_SIZE == 4) {
            require32Bit(j, "length");
            objArr[1] = Integer.valueOf((int) j);
        } else {
            objArr[1] = Long.valueOf(j);
        }
        objArr[2] = Integer.valueOf(i);
        objArr[3] = Integer.valueOf(i2);
        objArr[4] = Integer.valueOf(i3);
        if (mmap64 || Native.LONG_SIZE > 4) {
            objArr[5] = Long.valueOf(j2);
        } else {
            require32Bit(j2, "offset");
            objArr[5] = Integer.valueOf((int) j2);
        }
        return mmap.invokePointer(objArr);
    }

    public static void require32Bit(long j, String str) {
        if (j <= 2147483647L) {
            return;
        }
        e4b.a(str, " exceeds 32bit");
    }
}
