package com.sun.jna.platform.win32;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LMJoin {

    public static abstract class NETSETUP_JOIN_STATUS {
        public static final int NetSetupDomainName = 3;
        public static final int NetSetupUnjoined = 1;
        public static final int NetSetupUnknownStatus = 0;
        public static final int NetSetupWorkgroupName = 2;
    }
}
