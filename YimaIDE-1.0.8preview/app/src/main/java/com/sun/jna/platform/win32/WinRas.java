package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.win32.StdCallLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface WinRas {
    public static final int DNLEN = 15;
    public static final int ERROR_BUFFER_TOO_SMALL = 603;
    public static final int ERROR_CANNOT_FIND_PHONEBOOK_ENTRY = 623;
    public static final int MAX_PATH = 260;
    public static final int PWLEN = 256;
    public static final int RASCM_Domain = 4;
    public static final int RASCM_Password = 2;
    public static final int RASCM_UserName = 1;
    public static final int RASCS_Connected = 8192;
    public static final int RASCS_Disconnected = 8193;
    public static final String RASDT_Modem = "modem";
    public static final int RASTUNNELENDPOINT_IPv4 = 1;
    public static final int RASTUNNELENDPOINT_IPv6 = 2;
    public static final int RAS_MaxAreaCode = 10;
    public static final int RAS_MaxCallbackNumber = 128;
    public static final int RAS_MaxDeviceName = 128;
    public static final int RAS_MaxDeviceType = 16;
    public static final int RAS_MaxDnsSuffix = 256;
    public static final int RAS_MaxEntryName = 256;
    public static final int RAS_MaxFacilities = 200;
    public static final int RAS_MaxIpAddress = 15;
    public static final int RAS_MaxPadType = 32;
    public static final int RAS_MaxPhoneNumber = 128;
    public static final int RAS_MaxUserData = 200;
    public static final int RAS_MaxX25Address = 200;
    public static final int UNLEN = 256;

    public interface RasDialFunc2 extends StdCallLibrary.StdCallCallback {
        int dialNotification(int i, int i2, WinNT.HANDLE handle, int i3, int i4, int i5, int i6);
    }

    @Structure.FieldOrder({"dwType", "u"})
    public static class RASTUNNELENDPOINT extends Structure {
        public int dwType;
        public UNION u;

        public static class UNION extends Union {
            public RASIPV4ADDR ipv4;
            public RASIPV6ADDR ipv6;

            public static class ByReference extends UNION implements Structure.ByReference {
            }
        }

        public RASTUNNELENDPOINT(Pointer pointer) {
            super(pointer);
            read();
        }

        @Override // com.sun.jna.Structure
        public void read() {
            super.read();
            int i = this.dwType;
            if (i != 1) {
                UNION union = this.u;
                if (i != 2) {
                    union.setType(RASIPV4ADDR.class);
                } else {
                    union.setType(RASIPV6ADDR.class);
                }
            } else {
                this.u.setType(RASIPV4ADDR.class);
            }
            this.u.read();
        }

        public RASTUNNELENDPOINT() {
        }
    }

    @Structure.FieldOrder({"dwSize", "dwfOptions", "hwndParent", "reserved", "reserved1", "RasEapInfo", "fSkipPppAuth", "RasDevSpecificInfo"})
    public static class RASDIALEXTENSIONS extends Structure {
        public RASDEVSPECIFICINFO RasDevSpecificInfo;
        public RASEAPINFO RasEapInfo;
        public int dwSize;
        public int dwfOptions;
        public WinDef.BOOL fSkipPppAuth;
        public WinDef.HWND hwndParent;
        public BaseTSD.ULONG_PTR reserved;
        public BaseTSD.ULONG_PTR reserved1;

        public static class ByReference extends RASDIALEXTENSIONS implements Structure.ByReference {
        }

        public RASDIALEXTENSIONS() {
            this.dwSize = size();
        }

        public RASDIALEXTENSIONS(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"dwSize", "dwBytesXmited", "dwBytesRcved", "dwFramesXmited", "dwFramesRcved", "dwCrcErr", "dwTimeoutErr", "dwAlignmentErr", "dwHardwareOverrunErr", "dwFramingErr", "dwBufferOverrunErr", "dwCompressionRatioIn", "dwCompressionRatioOut", "dwBps", "dwConnectDuration"})
    public static class RAS_STATS extends Structure {
        public int dwAlignmentErr;
        public int dwBps;
        public int dwBufferOverrunErr;
        public int dwBytesRcved;
        public int dwBytesXmited;
        public int dwCompressionRatioIn;
        public int dwCompressionRatioOut;
        public int dwConnectDuration;
        public int dwCrcErr;
        public int dwFramesRcved;
        public int dwFramesXmited;
        public int dwFramingErr;
        public int dwHardwareOverrunErr;
        public int dwSize;
        public int dwTimeoutErr;

        public RAS_STATS() {
            this.dwSize = size();
        }

        public RAS_STATS(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"addr"})
    public static class RASIPADDR extends Structure {
        public byte[] addr;

        public RASIPADDR(Pointer pointer) {
            super(pointer);
            this.addr = new byte[4];
            read();
        }

        public RASIPADDR() {
            this.addr = new byte[4];
        }
    }

    @Structure.FieldOrder({"addr"})
    public static class RASIPV4ADDR extends Structure {
        public byte[] addr;

        public RASIPV4ADDR(Pointer pointer) {
            super(pointer);
            this.addr = new byte[8];
            read();
        }

        public RASIPV4ADDR() {
            this.addr = new byte[8];
        }
    }

    @Structure.FieldOrder({"addr"})
    public static class RASIPV6ADDR extends Structure {
        public byte[] addr;

        public RASIPV6ADDR(Pointer pointer) {
            super(pointer);
            this.addr = new byte[16];
            read();
        }

        public RASIPV6ADDR() {
            this.addr = new byte[16];
        }
    }

    @Structure.FieldOrder({"dwSize", "dwError", "szIpAddress", "szServerIpAddress", "dwOptions", "dwServerOptions"})
    public static class RASPPPIP extends Structure {
        public int dwError;
        public int dwOptions;
        public int dwServerOptions;
        public int dwSize;
        public char[] szIpAddress;
        public char[] szServerIpAddress;

        public static class ByReference extends RASPPPIP implements Structure.ByReference {
        }

        public RASPPPIP() {
            this.szIpAddress = new char[16];
            this.szServerIpAddress = new char[16];
            this.dwSize = size();
        }

        public RASPPPIP(Pointer pointer) {
            super(pointer);
            this.szIpAddress = new char[16];
            this.szServerIpAddress = new char[16];
            read();
        }
    }

    @Structure.FieldOrder({"dwSize", "rasconnstate", "dwError", "szDeviceType", "szDeviceName", "szPhoneNumber", "localEndPoint", "remoteEndPoint", "rasconnsubstate"})
    public static class RASCONNSTATUS extends Structure {
        public int dwError;
        public int dwSize;
        public RASTUNNELENDPOINT localEndPoint;
        public int rasconnstate;
        public int rasconnsubstate;
        public RASTUNNELENDPOINT remoteEndPoint;
        public char[] szDeviceName;
        public char[] szDeviceType;
        public char[] szPhoneNumber;

        public RASCONNSTATUS() {
            this.szDeviceType = new char[17];
            this.szDeviceName = new char[129];
            this.szPhoneNumber = new char[129];
            this.dwSize = size();
        }

        public RASCONNSTATUS(Pointer pointer) {
            super(pointer);
            this.szDeviceType = new char[17];
            this.szDeviceName = new char[129];
            this.szPhoneNumber = new char[129];
            read();
        }
    }

    @Structure.FieldOrder({"dwSize", "dwMask", "szUserName", "szPassword", "szDomain"})
    public static class RASCREDENTIALS extends Structure {
        public int dwMask;
        public int dwSize;
        public char[] szDomain;
        public char[] szPassword;
        public char[] szUserName;

        public static class ByReference extends RASCREDENTIALS implements Structure.ByReference {
        }

        public RASCREDENTIALS() {
            this.szUserName = new char[257];
            this.szPassword = new char[257];
            this.szDomain = new char[16];
            this.dwSize = size();
        }

        public RASCREDENTIALS(Pointer pointer) {
            super(pointer);
            this.szUserName = new char[257];
            this.szPassword = new char[257];
            this.szDomain = new char[16];
            read();
        }
    }

    @Structure.FieldOrder({"dwSize", "pbDevSpecificInfo"})
    public static class RASDEVSPECIFICINFO extends Structure {
        public int dwSize;
        public Pointer pbDevSpecificInfo;

        public RASDEVSPECIFICINFO(byte[] bArr) {
            Memory memory = new Memory(bArr.length);
            this.pbDevSpecificInfo = memory;
            memory.write(0L, bArr, 0, bArr.length);
            this.dwSize = bArr.length;
            allocateMemory();
        }

        public byte[] getData() {
            Pointer pointer = this.pbDevSpecificInfo;
            if (pointer == null) {
                return null;
            }
            return pointer.getByteArray(0L, this.dwSize);
        }

        public RASDEVSPECIFICINFO(Pointer pointer) {
            super(pointer);
            read();
        }

        public RASDEVSPECIFICINFO() {
        }

        public RASDEVSPECIFICINFO(String str) {
            this(Native.toByteArray(str));
        }
    }

    @Structure.FieldOrder({"dwSizeofEapInfo", "pbEapInfo"})
    public static class RASEAPINFO extends Structure {
        public int dwSizeofEapInfo;
        public Pointer pbEapInfo;

        public RASEAPINFO(byte[] bArr) {
            Memory memory = new Memory(bArr.length);
            this.pbEapInfo = memory;
            memory.write(0L, bArr, 0, bArr.length);
            this.dwSizeofEapInfo = bArr.length;
            allocateMemory();
        }

        public byte[] getData() {
            Pointer pointer = this.pbEapInfo;
            if (pointer == null) {
                return null;
            }
            return pointer.getByteArray(0L, this.dwSizeofEapInfo);
        }

        public RASEAPINFO(Pointer pointer) {
            super(pointer);
            read();
        }

        public RASEAPINFO() {
        }

        public RASEAPINFO(String str) {
            this(Native.toByteArray(str));
        }
    }

    @Structure.FieldOrder({"dwSize", "hrasconn", "szEntryName", "szDeviceType", "szDeviceName", "szPhonebook", "dwSubEntry", "guidEntry", "dwFlags", "luid", "guidCorrelationId"})
    public static class RASCONN extends Structure {
        public int dwFlags;
        public int dwSize;
        public int dwSubEntry;
        public Guid.GUID guidCorrelationId;
        public Guid.GUID guidEntry;
        public WinNT.HANDLE hrasconn;
        public WinNT.LUID luid;
        public char[] szDeviceName;
        public char[] szDeviceType;
        public char[] szEntryName;
        public char[] szPhonebook;

        public static class ByReference extends RASCONN implements Structure.ByReference {
        }

        public RASCONN() {
            this.szEntryName = new char[257];
            this.szDeviceType = new char[17];
            this.szDeviceName = new char[129];
            this.szPhonebook = new char[260];
            this.dwSize = size();
        }

        public RASCONN(Pointer pointer) {
            super(pointer);
            this.szEntryName = new char[257];
            this.szDeviceType = new char[17];
            this.szDeviceName = new char[129];
            this.szPhonebook = new char[260];
            read();
        }
    }

    @Structure.FieldOrder({"dwSize", "szEntryName", "szPhoneNumber", "szCallbackNumber", "szUserName", "szPassword", "szDomain"})
    public static class RASDIALPARAMS extends Structure {
        public int dwSize;
        public char[] szCallbackNumber;
        public char[] szDomain;
        public char[] szEntryName;
        public char[] szPassword;
        public char[] szPhoneNumber;
        public char[] szUserName;

        public static class ByReference extends RASDIALPARAMS implements Structure.ByReference {
        }

        public RASDIALPARAMS() {
            this.szEntryName = new char[257];
            this.szPhoneNumber = new char[129];
            this.szCallbackNumber = new char[129];
            this.szUserName = new char[257];
            this.szPassword = new char[257];
            this.szDomain = new char[16];
            this.dwSize = size();
        }

        public RASDIALPARAMS(Pointer pointer) {
            super(pointer);
            this.szEntryName = new char[257];
            this.szPhoneNumber = new char[129];
            this.szCallbackNumber = new char[129];
            this.szUserName = new char[257];
            this.szPassword = new char[257];
            this.szDomain = new char[16];
            read();
        }
    }

    @Structure.FieldOrder({"dwSize", "dwfOptions", "dwCountryID", "dwCountryCode", "szAreaCode", "szLocalPhoneNumber", "dwAlternateOffset", "ipaddr", "ipaddrDns", "ipaddrDnsAlt", "ipaddrWins", "ipaddrWinsAlt", "dwFrameSize", "dwfNetProtocols", "dwFramingProtocol", "szScript", "szAutodialDll", "szAutodialFunc", "szDeviceType", "szDeviceName", "szX25PadType", "szX25Address", "szX25Facilities", "szX25UserData", "dwChannels", "dwReserved1", "dwReserved2", "dwSubEntries", "dwDialMode", "dwDialExtraPercent", "dwDialExtraSampleSeconds", "dwHangUpExtraPercent", "dwHangUpExtraSampleSeconds", "dwIdleDisconnectSeconds", "dwType", "dwEncryptionType", "dwCustomAuthKey", "guidId", "szCustomDialDll", "dwVpnStrategy", "dwfOptions2", "dwfOptions3", "szDnsSuffix", "dwTcpWindowSize", "szPrerequisitePbk", "szPrerequisiteEntry", "dwRedialCount", "dwRedialPause", "ipv6addrDns", "ipv6addrDnsAlt", "dwIPv4InterfaceMetric", "dwIPv6InterfaceMetric", "ipv6addr", "dwIPv6PrefixLength", "dwNetworkOutageTime"})
    public static class RASENTRY extends Structure {
        public int dwAlternateOffset;
        public int dwChannels;
        public int dwCountryCode;
        public int dwCountryID;
        public int dwCustomAuthKey;
        public int dwDialExtraPercent;
        public int dwDialExtraSampleSeconds;
        public int dwDialMode;
        public int dwEncryptionType;
        public int dwFrameSize;
        public int dwFramingProtocol;
        public int dwHangUpExtraPercent;
        public int dwHangUpExtraSampleSeconds;
        public int dwIPv4InterfaceMetric;
        public int dwIPv6InterfaceMetric;
        public int dwIPv6PrefixLength;
        public int dwIdleDisconnectSeconds;
        public int dwNetworkOutageTime;
        public int dwRedialCount;
        public int dwRedialPause;
        public int dwReserved1;
        public int dwReserved2;
        public int dwSize;
        public int dwSubEntries;
        public int dwTcpWindowSize;
        public int dwType;
        public int dwVpnStrategy;
        public int dwfNetProtocols;
        public int dwfOptions;
        public int dwfOptions2;
        public int dwfOptions3;
        public Guid.GUID guidId;
        public RASIPADDR ipaddr;
        public RASIPADDR ipaddrDns;
        public RASIPADDR ipaddrDnsAlt;
        public RASIPADDR ipaddrWins;
        public RASIPADDR ipaddrWinsAlt;
        public RASIPV6ADDR ipv6addr;
        public RASIPV6ADDR ipv6addrDns;
        public RASIPV6ADDR ipv6addrDnsAlt;
        public char[] szAreaCode;
        public char[] szAutodialDll;
        public char[] szAutodialFunc;
        public char[] szCustomDialDll;
        public char[] szDeviceName;
        public char[] szDeviceType;
        public char[] szDnsSuffix;
        public char[] szLocalPhoneNumber;
        public char[] szPrerequisiteEntry;
        public char[] szPrerequisitePbk;
        public char[] szScript;
        public char[] szX25Address;
        public char[] szX25Facilities;
        public char[] szX25PadType;
        public char[] szX25UserData;

        public static class ByReference extends RASENTRY implements Structure.ByReference {
        }

        public RASENTRY() {
            this.szAreaCode = new char[11];
            this.szLocalPhoneNumber = new char[129];
            this.szScript = new char[260];
            this.szAutodialDll = new char[260];
            this.szAutodialFunc = new char[260];
            this.szDeviceType = new char[17];
            this.szDeviceName = new char[129];
            this.szX25PadType = new char[33];
            this.szX25Address = new char[201];
            this.szX25Facilities = new char[201];
            this.szX25UserData = new char[201];
            this.szCustomDialDll = new char[260];
            this.szDnsSuffix = new char[256];
            this.szPrerequisitePbk = new char[260];
            this.szPrerequisiteEntry = new char[257];
            this.dwSize = size();
        }

        public RASENTRY(Pointer pointer) {
            super(pointer);
            this.szAreaCode = new char[11];
            this.szLocalPhoneNumber = new char[129];
            this.szScript = new char[260];
            this.szAutodialDll = new char[260];
            this.szAutodialFunc = new char[260];
            this.szDeviceType = new char[17];
            this.szDeviceName = new char[129];
            this.szX25PadType = new char[33];
            this.szX25Address = new char[201];
            this.szX25Facilities = new char[201];
            this.szX25UserData = new char[201];
            this.szCustomDialDll = new char[260];
            this.szDnsSuffix = new char[256];
            this.szPrerequisitePbk = new char[260];
            this.szPrerequisiteEntry = new char[257];
            read();
        }
    }
}
