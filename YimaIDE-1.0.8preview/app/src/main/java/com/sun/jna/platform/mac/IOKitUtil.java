package com.sun.jna.platform.mac;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IOKitUtil {
    private static final IOKit IO = IOKit.INSTANCE;
    private static final SystemB SYS = SystemB.INSTANCE;

    private IOKitUtil() {
    }

    public static CoreFoundation.CFMutableDictionaryRef getBSDNameMatchingDict(String str) {
        int masterPort = getMasterPort();
        CoreFoundation.CFMutableDictionaryRef cFMutableDictionaryRefIOBSDNameMatching = IO.IOBSDNameMatching(masterPort, 0, str);
        SystemB systemB = SYS;
        systemB.mach_port_deallocate(systemB.mach_task_self(), masterPort);
        return cFMutableDictionaryRefIOBSDNameMatching;
    }

    public static int getMasterPort() {
        IntByReference intByReference = new IntByReference();
        IO.IOMasterPort(0, intByReference);
        return intByReference.getValue();
    }

    public static IOKit.IOService getMatchingService(CoreFoundation.CFDictionaryRef cFDictionaryRef) {
        int masterPort = getMasterPort();
        IOKit.IOService iOServiceIOServiceGetMatchingService = IO.IOServiceGetMatchingService(masterPort, cFDictionaryRef);
        SystemB systemB = SYS;
        systemB.mach_port_deallocate(systemB.mach_task_self(), masterPort);
        return iOServiceIOServiceGetMatchingService;
    }

    public static IOKit.IOIterator getMatchingServices(CoreFoundation.CFDictionaryRef cFDictionaryRef) {
        int masterPort = getMasterPort();
        PointerByReference pointerByReference = new PointerByReference();
        int iIOServiceGetMatchingServices = IO.IOServiceGetMatchingServices(masterPort, cFDictionaryRef, pointerByReference);
        SystemB systemB = SYS;
        systemB.mach_port_deallocate(systemB.mach_task_self(), masterPort);
        if (iIOServiceGetMatchingServices != 0 || pointerByReference.getValue() == null) {
            return null;
        }
        return new IOKit.IOIterator(pointerByReference.getValue());
    }

    public static IOKit.IORegistryEntry getRoot() {
        int masterPort = getMasterPort();
        IOKit.IORegistryEntry iORegistryEntryIORegistryGetRootEntry = IO.IORegistryGetRootEntry(masterPort);
        SystemB systemB = SYS;
        systemB.mach_port_deallocate(systemB.mach_task_self(), masterPort);
        return iORegistryEntryIORegistryGetRootEntry;
    }

    public static IOKit.IOService getMatchingService(String str) {
        CoreFoundation.CFMutableDictionaryRef cFMutableDictionaryRefIOServiceMatching = IO.IOServiceMatching(str);
        if (cFMutableDictionaryRefIOServiceMatching != null) {
            return getMatchingService(cFMutableDictionaryRefIOServiceMatching);
        }
        return null;
    }

    public static IOKit.IOIterator getMatchingServices(String str) {
        CoreFoundation.CFMutableDictionaryRef cFMutableDictionaryRefIOServiceMatching = IO.IOServiceMatching(str);
        if (cFMutableDictionaryRefIOServiceMatching != null) {
            return getMatchingServices(cFMutableDictionaryRefIOServiceMatching);
        }
        return null;
    }
}
