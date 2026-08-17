package com.sun.jna.platform.win32;

import com.sun.jna.IntegerType;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface BaseTSD {

    public static class DWORD_PTR extends IntegerType {
        public DWORD_PTR() {
            this(0L);
        }

        public DWORD_PTR(long j) {
            super(Native.POINTER_SIZE, j);
        }
    }

    public static class LONG_PTR extends IntegerType {
        public LONG_PTR() {
            this(0L);
        }

        public Pointer toPointer() {
            return Pointer.createConstant(longValue());
        }

        public LONG_PTR(long j) {
            super(Native.POINTER_SIZE, j);
        }
    }

    public static class SIZE_T extends ULONG_PTR {
        public SIZE_T() {
            this(0L);
        }

        public SIZE_T(long j) {
            super(j);
        }
    }

    public static class SSIZE_T extends LONG_PTR {
        public SSIZE_T() {
            this(0L);
        }

        public SSIZE_T(long j) {
            super(j);
        }
    }

    public static class ULONG_PTR extends IntegerType {
        public ULONG_PTR(long j) {
            super(Native.POINTER_SIZE, j, true);
        }

        public Pointer toPointer() {
            return Pointer.createConstant(longValue());
        }

        public ULONG_PTR() {
            this(0L);
        }
    }

    public static class ULONG_PTRByReference extends ByReference {
        public ULONG_PTRByReference() {
            this(new ULONG_PTR(0L));
        }

        public ULONG_PTR getValue() {
            return new ULONG_PTR(Native.POINTER_SIZE == 4 ? getPointer().getInt(0L) : getPointer().getLong(0L));
        }

        public void setValue(ULONG_PTR ulong_ptr) {
            if (Native.POINTER_SIZE == 4) {
                getPointer().setInt(0L, ulong_ptr.intValue());
            } else {
                getPointer().setLong(0L, ulong_ptr.longValue());
            }
        }

        public ULONG_PTRByReference(ULONG_PTR ulong_ptr) {
            super(Native.POINTER_SIZE);
            setValue(ulong_ptr);
        }
    }
}
