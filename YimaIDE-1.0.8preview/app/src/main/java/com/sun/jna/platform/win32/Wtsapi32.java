package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.win32.W32APITypeMapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Wtsapi32 extends StdCallLibrary {
    public static final int DOMAIN_LENGTH = 17;
    public static final int NOTIFY_FOR_ALL_SESSIONS = 1;
    public static final int NOTIFY_FOR_THIS_SESSION = 0;
    public static final int USERNAME_LENGTH = 20;
    public static final int WINSTATIONNAME_LENGTH = 32;
    public static final int WTS_ANY_SESSION = -2;
    public static final int WTS_CONSOLE_CONNECT = 1;
    public static final int WTS_CONSOLE_DISCONNECT = 2;
    public static final int WTS_CURRENT_SESSION = -1;
    public static final int WTS_PROCESS_INFO_LEVEL_0 = 0;
    public static final int WTS_PROCESS_INFO_LEVEL_1 = 1;
    public static final int WTS_REMOTE_CONNECT = 3;
    public static final int WTS_REMOTE_DISCONNECT = 4;
    public static final int WTS_SESSION_LOCK = 7;
    public static final int WTS_SESSION_LOGOFF = 6;
    public static final int WTS_SESSION_LOGON = 5;
    public static final int WTS_SESSION_REMOTE_CONTROL = 9;
    public static final int WTS_SESSION_UNLOCK = 8;
    public static final Wtsapi32 INSTANCE = (Wtsapi32) Native.load("Wtsapi32", Wtsapi32.class, W32APIOptions.DEFAULT_OPTIONS);
    public static final WinNT.HANDLE WTS_CURRENT_SERVER_HANDLE = new WinNT.HANDLE(null);

    public interface WTS_CONNECTSTATE_CLASS {
        public static final int WTSActive = 0;
        public static final int WTSConnectQuery = 2;
        public static final int WTSConnected = 1;
        public static final int WTSDisconnected = 4;
        public static final int WTSDown = 8;
        public static final int WTSIdle = 5;
        public static final int WTSInit = 9;
        public static final int WTSListen = 6;
        public static final int WTSReset = 7;
        public static final int WTSShadow = 3;
    }

    public interface WTS_INFO_CLASS {
        public static final int WTSApplicationName = 1;
        public static final int WTSClientAddress = 14;
        public static final int WTSClientBuildNumber = 9;
        public static final int WTSClientDirectory = 11;
        public static final int WTSClientDisplay = 15;
        public static final int WTSClientHardwareId = 13;
        public static final int WTSClientInfo = 23;
        public static final int WTSClientName = 10;
        public static final int WTSClientProductId = 12;
        public static final int WTSClientProtocolType = 16;
        public static final int WTSConfigInfo = 26;
        public static final int WTSConnectState = 8;
        public static final int WTSDomainName = 7;
        public static final int WTSIdleTime = 17;
        public static final int WTSIncomingBytes = 19;
        public static final int WTSIncomingFrames = 21;
        public static final int WTSInitialProgram = 0;
        public static final int WTSIsRemoteSession = 29;
        public static final int WTSLogonTime = 18;
        public static final int WTSOEMId = 3;
        public static final int WTSOutgoingBytes = 20;
        public static final int WTSOutgoingFrames = 22;
        public static final int WTSSessionAddressV4 = 28;
        public static final int WTSSessionId = 4;
        public static final int WTSSessionInfo = 24;
        public static final int WTSSessionInfoEx = 25;
        public static final int WTSUserName = 5;
        public static final int WTSValidationInfo = 27;
        public static final int WTSWinStationName = 6;
        public static final int WTSWorkingDirectory = 2;
    }

    boolean WTSEnumerateProcessesEx(WinNT.HANDLE handle, IntByReference intByReference, int i, PointerByReference pointerByReference, IntByReference intByReference2);

    boolean WTSEnumerateSessions(WinNT.HANDLE handle, int i, int i2, PointerByReference pointerByReference, IntByReference intByReference);

    void WTSFreeMemory(Pointer pointer);

    boolean WTSFreeMemoryEx(int i, Pointer pointer, int i2);

    boolean WTSQuerySessionInformation(WinNT.HANDLE handle, int i, int i2, PointerByReference pointerByReference, IntByReference intByReference);

    boolean WTSRegisterSessionNotification(WinDef.HWND hwnd, int i);

    boolean WTSUnRegisterSessionNotification(WinDef.HWND hwnd);

    @Structure.FieldOrder({"SessionId", "ProcessId", "pProcessName", "pUserSid", "NumberOfThreads", "HandleCount", "PagefileUsage", "PeakPagefileUsage", "WorkingSetSize", "PeakWorkingSetSize", "UserTime", "KernelTime"})
    public static class WTS_PROCESS_INFO_EX extends Structure {
        public int HandleCount;
        public WinNT.LARGE_INTEGER KernelTime;
        public int NumberOfThreads;
        public int PagefileUsage;
        public int PeakPagefileUsage;
        public int PeakWorkingSetSize;
        public int ProcessId;
        public int SessionId;
        public WinNT.LARGE_INTEGER UserTime;
        public int WorkingSetSize;
        public String pProcessName;
        public WinNT.PSID pUserSid;

        public WTS_PROCESS_INFO_EX(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.DEFAULT);
            read();
        }

        public WTS_PROCESS_INFO_EX() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    @Structure.FieldOrder({"SessionId", "pWinStationName", "State"})
    public static class WTS_SESSION_INFO extends Structure {
        public int SessionId;
        public int State;
        public String pWinStationName;

        public WTS_SESSION_INFO(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.DEFAULT);
            read();
        }

        public WTS_SESSION_INFO() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    @Structure.FieldOrder({"AddressFamily", "Address"})
    public static class WTS_CLIENT_ADDRESS extends Structure {
        public byte[] Address;
        public int AddressFamily;

        public WTS_CLIENT_ADDRESS(Pointer pointer) {
            super(pointer);
            this.Address = new byte[20];
            read();
        }

        public WTS_CLIENT_ADDRESS() {
            this.Address = new byte[20];
        }
    }

    @Structure.FieldOrder({"State", "SessionId", "IncomingBytes", "OutgoingBytes", "IncomingFrames", "OutgoingFrames", "IncomingCompressedBytes", "OutgoingCompressedBytes", "WinStationName", "Domain", "UserName", "ConnectTime", "DisconnectTime", "LastInputTime", "LogonTime", "CurrentTime"})
    public static class WTSINFO extends Structure {
        private static final int CHAR_WIDTH;
        public WinNT.LARGE_INTEGER ConnectTime;
        public WinNT.LARGE_INTEGER CurrentTime;
        public WinNT.LARGE_INTEGER DisconnectTime;
        public final byte[] Domain;
        public int IncomingBytes;
        public int IncomingCompressedBytes;
        public int IncomingFrames;
        public WinNT.LARGE_INTEGER LastInputTime;
        public WinNT.LARGE_INTEGER LogonTime;
        public int OutgoingBytes;
        public int OutgoingCompressedBytes;
        public int OutgoingFrames;
        public int SessionId;
        public int State;
        public final byte[] UserName;
        public final byte[] WinStationName;

        static {
            CHAR_WIDTH = Boolean.getBoolean("w32.ascii") ? 1 : 2;
        }

        public WTSINFO(Pointer pointer) {
            super(pointer);
            int i = CHAR_WIDTH;
            this.WinStationName = new byte[i * 32];
            this.Domain = new byte[i * 17];
            this.UserName = new byte[i * 21];
            read();
        }

        private String getStringAtOffset(int i) {
            int i2 = CHAR_WIDTH;
            Pointer pointer = getPointer();
            return i2 == 1 ? pointer.getString(i) : pointer.getWideString(i);
        }

        public String getDomain() {
            return getStringAtOffset(fieldOffset("Domain"));
        }

        public String getUserName() {
            return getStringAtOffset(fieldOffset("UserName"));
        }

        public String getWinStationName() {
            return getStringAtOffset(fieldOffset("WinStationName"));
        }

        public WTSINFO() {
            int i = CHAR_WIDTH;
            this.WinStationName = new byte[i * 32];
            this.Domain = new byte[i * 17];
            this.UserName = new byte[i * 21];
        }
    }
}
