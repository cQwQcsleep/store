package com.sun.jna.platform.win32;

import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APITypeMapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ShellAPI extends StdCallLibrary {
    public static final int ABE_BOTTOM = 3;
    public static final int ABE_LEFT = 0;
    public static final int ABE_RIGHT = 2;
    public static final int ABE_TOP = 1;
    public static final int ABM_ACTIVATE = 6;
    public static final int ABM_GETAUTOHIDEBAR = 7;
    public static final int ABM_GETSTATE = 4;
    public static final int ABM_GETTASKBARPOS = 5;
    public static final int ABM_NEW = 0;
    public static final int ABM_QUERYPOS = 2;
    public static final int ABM_REMOVE = 1;
    public static final int ABM_SETAUTOHIDEBAR = 8;
    public static final int ABM_SETPOS = 3;
    public static final int ABM_SETSTATE = 10;
    public static final int ABM_WINDOWPOSCHANGED = 9;
    public static final int FOF_ALLOWUNDO = 64;
    public static final int FOF_CONFIRMMOUSE = 2;
    public static final int FOF_FILESONLY = 128;
    public static final int FOF_MULTIDESTFILES = 1;
    public static final int FOF_NOCONFIRMATION = 16;
    public static final int FOF_NOCONFIRMMKDIR = 512;
    public static final int FOF_NOCOPYSECURITYATTRIBS = 2048;
    public static final int FOF_NOERRORUI = 1024;
    public static final int FOF_NORECURSEREPARSE = 32768;
    public static final int FOF_NORECURSION = 4096;
    public static final int FOF_NO_CONNECTED_ELEMENTS = 8192;
    public static final int FOF_NO_UI = 1556;
    public static final int FOF_RENAMEONCOLLISION = 8;
    public static final int FOF_SILENT = 4;
    public static final int FOF_SIMPLEPROGRESS = 256;
    public static final int FOF_WANTMAPPINGHANDLE = 32;
    public static final int FOF_WANTNUKEWARNING = 16384;
    public static final int FO_COPY = 2;
    public static final int FO_DELETE = 3;
    public static final int FO_MOVE = 1;
    public static final int FO_RENAME = 4;
    public static final int PO_DELETE = 19;
    public static final int PO_PORTCHANGE = 32;
    public static final int PO_RENAME = 20;
    public static final int PO_REN_PORT = 52;
    public static final int STRUCTURE_ALIGNMENT = !Platform.is64Bit() ? 1 : 0;
    public static final TypeMapper TYPE_MAPPER;

    @Structure.FieldOrder({"cbSize", "fMask", "hwnd", "lpVerb", "lpFile", "lpParameters", "lpDirectory", "nShow", "hInstApp", "lpIDList", "lpClass", "hKeyClass", "dwHotKey", "hMonitor", "hProcess"})
    public static class SHELLEXECUTEINFO extends Structure {
        public int cbSize = size();
        public int dwHotKey;
        public int fMask;
        public WinDef.HINSTANCE hInstApp;
        public WinReg.HKEY hKeyClass;
        public WinNT.HANDLE hMonitor;
        public WinNT.HANDLE hProcess;
        public WinDef.HWND hwnd;
        public String lpClass;
        public String lpDirectory;
        public String lpFile;
        public Pointer lpIDList;
        public String lpParameters;
        public String lpVerb;
        public int nShow;
    }

    @Structure.FieldOrder({"hwnd", "wFunc", "pFrom", "pTo", "fFlags", "fAnyOperationsAborted", "pNameMappings", "lpszProgressTitle"})
    public static class SHFILEOPSTRUCT extends Structure {
        public boolean fAnyOperationsAborted;
        public short fFlags;
        public WinNT.HANDLE hwnd;
        public String lpszProgressTitle;
        public String pFrom;
        public Pointer pNameMappings;
        public String pTo;
        public int wFunc;

        public String encodePaths(String[] strArr) {
            String strConcat = "";
            for (String str : strArr) {
                strConcat = (strConcat + str).concat("\u0000");
            }
            return strConcat.concat("\u0000");
        }
    }

    static {
        TYPE_MAPPER = Boolean.getBoolean("w32.ascii") ? W32APITypeMapper.ASCII : W32APITypeMapper.UNICODE;
    }

    @Structure.FieldOrder({"cbSize", "hWnd", "uCallbackMessage", "uEdge", "rc", "lParam"})
    public static class APPBARDATA extends Structure {
        public WinDef.DWORD cbSize;
        public WinDef.HWND hWnd;
        public WinDef.LPARAM lParam;
        public WinDef.RECT rc;
        public WinDef.UINT uCallbackMessage;
        public WinDef.UINT uEdge;

        public static class ByReference extends APPBARDATA implements Structure.ByReference {
        }

        public APPBARDATA() {
        }

        public APPBARDATA(Pointer pointer) {
            super(pointer);
        }
    }
}
