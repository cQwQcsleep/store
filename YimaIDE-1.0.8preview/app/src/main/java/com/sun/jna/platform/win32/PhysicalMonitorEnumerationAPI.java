package com.sun.jna.platform.win32;

import com.sun.jna.Structure;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface PhysicalMonitorEnumerationAPI {
    public static final int PHYSICAL_MONITOR_DESCRIPTION_SIZE = 128;

    @Structure.FieldOrder({"hPhysicalMonitor", "szPhysicalMonitorDescription"})
    public static class PHYSICAL_MONITOR extends Structure {
        public WinNT.HANDLE hPhysicalMonitor;
        public char[] szPhysicalMonitorDescription = new char[128];
    }
}
