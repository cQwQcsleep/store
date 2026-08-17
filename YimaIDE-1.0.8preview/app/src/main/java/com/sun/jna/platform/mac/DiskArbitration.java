package com.sun.jna.platform.mac;

import com.sun.jna.Library;
import com.sun.jna.Native;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DiskArbitration extends Library {
    public static final DiskArbitration INSTANCE = (DiskArbitration) Native.load("DiskArbitration", DiskArbitration.class);

    public static class DADiskRef extends CoreFoundation.CFTypeRef {
    }

    public static class DASessionRef extends CoreFoundation.CFTypeRef {
    }

    CoreFoundation.CFDictionaryRef DADiskCopyDescription(DADiskRef dADiskRef);

    DADiskRef DADiskCreateFromBSDName(CoreFoundation.CFAllocatorRef cFAllocatorRef, DASessionRef dASessionRef, String str);

    DADiskRef DADiskCreateFromIOMedia(CoreFoundation.CFAllocatorRef cFAllocatorRef, DASessionRef dASessionRef, IOKit.IOObject iOObject);

    String DADiskGetBSDName(DADiskRef dADiskRef);

    DASessionRef DASessionCreate(CoreFoundation.CFAllocatorRef cFAllocatorRef);
}
