package com.sun.jna.platform.win32;

import com.intellij.psi.PsiKeyword;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Wininet extends StdCallLibrary {
    public static final int COOKIE_CACHE_ENTRY = 1048576;
    public static final int EDITED_CACHE_ENTRY = 8;
    public static final Wininet INSTANCE = (Wininet) Native.load("wininet", Wininet.class, W32APIOptions.DEFAULT_OPTIONS);
    public static final int NORMAL_CACHE_ENTRY = 1;
    public static final int SPARSE_CACHE_ENTRY = 65536;
    public static final int STICKY_CACHE_ENTRY = 4;
    public static final int TRACK_OFFLINE_CACHE_ENTRY = 16;
    public static final int TRACK_ONLINE_CACHE_ENTRY = 32;
    public static final int URLHISTORY_CACHE_ENTRY = 2097152;

    @Structure.FieldOrder({"dwStructSize", "lpszSourceUrlName", "lpszLocalFileName", "CacheEntryType", "dwUseCount", "dwHitRate", "dwSizeLow", "dwSizeHigh", "LastModifiedTime", "ExpireTime", "LastAccessTime", "LastSyncTime", "lpHeaderInfo", "dwHeaderInfoSize", "lpszFileExtension", "u", "additional"})
    public static class INTERNET_CACHE_ENTRY_INFO extends Structure {
        public int CacheEntryType;
        public WinBase.FILETIME ExpireTime;
        public WinBase.FILETIME LastAccessTime;
        public WinBase.FILETIME LastModifiedTime;
        public WinBase.FILETIME LastSyncTime;
        public byte[] additional;
        public int dwHeaderInfoSize;
        public int dwHitRate;
        public int dwSizeHigh;
        public int dwSizeLow;
        public int dwStructSize;
        public int dwUseCount;
        public Pointer lpHeaderInfo;
        public Pointer lpszFileExtension;
        public Pointer lpszLocalFileName;
        public Pointer lpszSourceUrlName;
        public UNION u;

        public static class UNION extends Union {
            public int dwExemptDelta;
            public int dwReserved;
        }

        public INTERNET_CACHE_ENTRY_INFO(int i) {
            this.additional = new byte[i];
        }

        @Override // com.sun.jna.Structure
        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            if (this.lpszLocalFileName == null) {
                str = "";
            } else {
                str = this.lpszLocalFileName.getWideString(0L) + " => ";
            }
            sb.append(str);
            Pointer pointer = this.lpszSourceUrlName;
            sb.append(pointer == null ? PsiKeyword.NULL : pointer.getWideString(0L));
            return sb.toString();
        }
    }

    boolean DeleteUrlCacheEntry(String str);

    boolean FindCloseUrlCache(WinNT.HANDLE handle);

    WinNT.HANDLE FindFirstUrlCacheEntry(String str, INTERNET_CACHE_ENTRY_INFO internet_cache_entry_info, IntByReference intByReference);

    boolean FindNextUrlCacheEntry(WinNT.HANDLE handle, INTERNET_CACHE_ENTRY_INFO internet_cache_entry_info, IntByReference intByReference);
}
