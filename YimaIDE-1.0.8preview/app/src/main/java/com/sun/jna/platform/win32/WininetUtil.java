package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WininetUtil {
    /* JADX WARN: Code duplicated, block: B:38:0x0083 A[Catch: all -> 0x006c, Win32Exception -> 0x0070, TryCatch #5 {Win32Exception -> 0x0070, all -> 0x006c, blocks: (B:21:0x004a, B:22:0x004d, B:24:0x005a, B:40:0x0089, B:41:0x008d, B:43:0x0093, B:47:0x00ac, B:46:0x00a8, B:30:0x0066, B:31:0x006b, B:36:0x0074, B:38:0x0083, B:53:0x00bb, B:54:0x00c0, B:55:0x00c1, B:56:0x00c5, B:57:0x00ce), top: B:73:0x0048 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:76:0x0089 A[EDGE_INSN: B:76:0x0089->B:40:0x0089 BREAK  A[LOOP:0: B:22:0x004d->B:55:0x00c1], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x00c1 A[SYNTHETIC] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static Map<String, String> getCache() throws Throwable {
        WinNT.HANDLE handleFindFirstUrlCacheEntry;
        Wininet.INTERNET_CACHE_ENTRY_INFO internet_cache_entry_info;
        int lastError;
        ArrayList<Wininet.INTERNET_CACHE_ENTRY_INFO> arrayList = new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        WinNT.HANDLE handle = null;
        Win32Exception win32Exception = null;
        try {
            IntByReference intByReference = new IntByReference();
            Wininet wininet = Wininet.INSTANCE;
            WinNT.HANDLE handleFindFirstUrlCacheEntry2 = wininet.FindFirstUrlCacheEntry(null, null, intByReference);
            try {
                int lastError2 = Native.getLastError();
                if (lastError2 != 259) {
                    if (lastError2 != 0 && lastError2 != 122) {
                        throw new Win32Exception(lastError2);
                    }
                    Wininet.INTERNET_CACHE_ENTRY_INFO internet_cache_entry_info2 = new Wininet.INTERNET_CACHE_ENTRY_INFO(intByReference.getValue());
                    handleFindFirstUrlCacheEntry = wininet.FindFirstUrlCacheEntry(null, internet_cache_entry_info2, intByReference);
                    try {
                        if (handleFindFirstUrlCacheEntry == null) {
                            throw new Win32Exception(Native.getLastError());
                        }
                        arrayList.add(internet_cache_entry_info2);
                        while (true) {
                            IntByReference intByReference2 = new IntByReference();
                            Wininet wininet2 = Wininet.INSTANCE;
                            if (!wininet2.FindNextUrlCacheEntry(handleFindFirstUrlCacheEntry, null, intByReference2)) {
                                int lastError3 = Native.getLastError();
                                if (lastError3 == 259) {
                                    break;
                                }
                                if (lastError3 != 0 && lastError3 != 122) {
                                    throw new Win32Exception(lastError3);
                                }
                                internet_cache_entry_info = new Wininet.INTERNET_CACHE_ENTRY_INFO(intByReference2.getValue());
                                if (wininet2.FindNextUrlCacheEntry(handleFindFirstUrlCacheEntry, internet_cache_entry_info, intByReference2)) {
                                    lastError = Native.getLastError();
                                    if (lastError == 259) {
                                        break;
                                        break;
                                    }
                                    if (lastError != 0) {
                                        continue;
                                    }
                                }
                                arrayList.add(internet_cache_entry_info);
                            } else {
                                internet_cache_entry_info = new Wininet.INTERNET_CACHE_ENTRY_INFO(intByReference2.getValue());
                                if (wininet2.FindNextUrlCacheEntry(handleFindFirstUrlCacheEntry, internet_cache_entry_info, intByReference2)) {
                                    lastError = Native.getLastError();
                                    if (lastError == 259) {
                                        break;
                                    }
                                    if (lastError != 0 && lastError != 122) {
                                        throw new Win32Exception(lastError);
                                    }
                                }
                                arrayList.add(internet_cache_entry_info);
                            }
                        }
                        for (Wininet.INTERNET_CACHE_ENTRY_INFO internet_cache_entry_info3 : arrayList) {
                            String wideString = internet_cache_entry_info3.lpszSourceUrlName.getWideString(0L);
                            Pointer pointer = internet_cache_entry_info3.lpszLocalFileName;
                            linkedHashMap.put(wideString, pointer == null ? "" : pointer.getWideString(0L));
                        }
                        Wininet.INSTANCE.FindCloseUrlCache(handleFindFirstUrlCacheEntry);
                        if (win32Exception != null) {
                            throw win32Exception;
                        }
                    } catch (Win32Exception e) {
                        e = e;
                        win32Exception = e;
                        if (handleFindFirstUrlCacheEntry != null) {
                            Win32Exception win32Exception2 = new Win32Exception(Native.getLastError());
                            win32Exception2.addSuppressedReflected(win32Exception);
                            win32Exception = win32Exception2;
                        }
                    } catch (Throwable th) {
                        th = th;
                        handle = handleFindFirstUrlCacheEntry;
                        if (handle != null) {
                            Wininet.INSTANCE.FindCloseUrlCache(handle);
                        }
                        throw th;
                    }
                } else if (handleFindFirstUrlCacheEntry2 != null) {
                    wininet.FindCloseUrlCache(handleFindFirstUrlCacheEntry2);
                }
            } catch (Win32Exception e2) {
                win32Exception = e2;
                handleFindFirstUrlCacheEntry = handleFindFirstUrlCacheEntry2;
                if (handleFindFirstUrlCacheEntry != null && !Wininet.INSTANCE.FindCloseUrlCache(handleFindFirstUrlCacheEntry)) {
                    Win32Exception win32Exception3 = new Win32Exception(Native.getLastError());
                    win32Exception3.addSuppressedReflected(win32Exception);
                    win32Exception = win32Exception3;
                }
            } catch (Throwable th2) {
                th = th2;
                handle = handleFindFirstUrlCacheEntry2;
            }
        } catch (Win32Exception e3) {
            e = e3;
            handleFindFirstUrlCacheEntry = null;
        } catch (Throwable th3) {
            th = th3;
        }
        return linkedHashMap;
    }
}
