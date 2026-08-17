package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface WinReg {
    public static final HKEY HKEY_CLASSES_ROOT = new HKEY(Integer.MIN_VALUE);
    public static final HKEY HKEY_CURRENT_USER = new HKEY(-2147483647);
    public static final HKEY HKEY_LOCAL_MACHINE = new HKEY(WinPerf.PERF_QUERY_COSTLY);
    public static final HKEY HKEY_USERS = new HKEY(-2147483645);
    public static final HKEY HKEY_PERFORMANCE_DATA = new HKEY(-2147483644);
    public static final HKEY HKEY_PERFORMANCE_TEXT = new HKEY(-2147483568);
    public static final HKEY HKEY_PERFORMANCE_NLSTEXT = new HKEY(-2147483552);
    public static final HKEY HKEY_CURRENT_CONFIG = new HKEY(-2147483643);
    public static final HKEY HKEY_DYN_DATA = new HKEY(WinNT.IO_REPARSE_TAG_HSM2);
    public static final HKEY HKEY_CURRENT_USER_LOCAL_SETTINGS = new HKEY(WinNT.IO_REPARSE_TAG_SIS);

    public static class HKEYByReference extends ByReference {
        public HKEYByReference(HKEY hkey) {
            super(Native.POINTER_SIZE);
            setValue(hkey);
        }

        public HKEY getValue() {
            Pointer pointer = getPointer().getPointer(0L);
            if (pointer == null) {
                return null;
            }
            WinNT.HANDLE handle = WinBase.INVALID_HANDLE_VALUE;
            if (handle.getPointer().equals(pointer)) {
                return (HKEY) handle;
            }
            HKEY hkey = new HKEY();
            hkey.setPointer(pointer);
            return hkey;
        }

        public void setValue(HKEY hkey) {
            getPointer().setPointer(0L, hkey != null ? hkey.getPointer() : null);
        }

        public HKEYByReference() {
            this(null);
        }
    }

    public static class HKEY extends WinNT.HANDLE {
        public HKEY(int i) {
            super(new Pointer(i));
        }

        public HKEY(Pointer pointer) {
            super(pointer);
        }

        public HKEY() {
        }
    }
}
