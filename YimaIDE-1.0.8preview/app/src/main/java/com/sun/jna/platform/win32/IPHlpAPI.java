package com.sun.jna.platform.win32;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APIOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IPHlpAPI extends Library {
    public static final int AF_BTH = 32;
    public static final int AF_INET = 2;
    public static final int AF_INET6 = 23;
    public static final int AF_IPX = 6;
    public static final int AF_IRDA = 26;
    public static final int AF_NETBIOS = 17;
    public static final int AF_UNSPEC = 0;
    public static final int IF_MAX_PHYS_ADDRESS_LENGTH = 32;
    public static final int IF_MAX_STRING_SIZE = 256;
    public static final IPHlpAPI INSTANCE = (IPHlpAPI) Native.load("IPHlpAPI", IPHlpAPI.class, W32APIOptions.DEFAULT_OPTIONS);
    public static final int MAXLEN_IFDESCR = 256;
    public static final int MAXLEN_PHYSADDR = 8;
    public static final int MAX_DOMAIN_NAME_LEN = 128;
    public static final int MAX_HOSTNAME_LEN = 128;
    public static final int MAX_INTERFACE_NAME_LEN = 256;
    public static final int MAX_SCOPE_ID_LEN = 256;

    @Structure.FieldOrder({"String"})
    public static class IP_ADDRESS_STRING extends Structure {
        public byte[] String = new byte[16];
    }

    @Structure.FieldOrder({"Next", "IpAddress", "IpMask", "Context"})
    public static class IP_ADDR_STRING extends Structure {
        public int Context;
        public IP_ADDRESS_STRING IpAddress;
        public IP_ADDRESS_STRING IpMask;
        public ByReference Next;

        public static class ByReference extends IP_ADDR_STRING implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"wszName", "dwIndex", "dwType", "dwMtu", "dwSpeed", "dwPhysAddrLen", "bPhysAddr", "dwAdminStatus", "dwOperStatus", "dwLastChange", "dwInOctets", "dwInUcastPkts", "dwInNUcastPkts", "dwInDiscards", "dwInErrors", "dwInUnknownProtos", "dwOutOctets", "dwOutUcastPkts", "dwOutNUcastPkts", "dwOutDiscards", "dwOutErrors", "dwOutQLen", "dwDescrLen", "bDescr"})
    public static class MIB_IFROW extends Structure {
        public int dwAdminStatus;
        public int dwDescrLen;
        public int dwInDiscards;
        public int dwInErrors;
        public int dwInNUcastPkts;
        public int dwInOctets;
        public int dwInUcastPkts;
        public int dwInUnknownProtos;
        public int dwIndex;
        public int dwLastChange;
        public int dwMtu;
        public int dwOperStatus;
        public int dwOutDiscards;
        public int dwOutErrors;
        public int dwOutNUcastPkts;
        public int dwOutOctets;
        public int dwOutQLen;
        public int dwOutUcastPkts;
        public int dwPhysAddrLen;
        public int dwSpeed;
        public int dwType;
        public char[] wszName = new char[256];
        public byte[] bPhysAddr = new byte[8];
        public byte[] bDescr = new byte[256];
    }

    @Structure.FieldOrder({"InterfaceLuid", "InterfaceIndex", "InterfaceGuid", "Alias", "Description", "PhysicalAddressLength", "PhysicalAddress", "PermanentPhysicalAddress", "Mtu", "Type", "TunnelType", "MediaType", "PhysicalMediumType", "AccessType", "DirectionType", "InterfaceAndOperStatusFlags", "OperStatus", "AdminStatus", "MediaConnectState", "NetworkGuid", "ConnectionType", "TransmitLinkSpeed", "ReceiveLinkSpeed", "InOctets", "InUcastPkts", "InNUcastPkts", "InDiscards", "InErrors", "InUnknownProtos", "InUcastOctets", "InMulticastOctets", "InBroadcastOctets", "OutOctets", "OutUcastPkts", "OutNUcastPkts", "OutDiscards", "OutErrors", "OutUcastOctets", "OutMulticastOctets", "OutBroadcastOctets", "OutQLen"})
    public static class MIB_IF_ROW2 extends Structure {
        public int AccessType;
        public int AdminStatus;
        public int ConnectionType;
        public int DirectionType;
        public long InBroadcastOctets;
        public long InDiscards;
        public long InErrors;
        public long InMulticastOctets;
        public long InNUcastPkts;
        public long InOctets;
        public long InUcastOctets;
        public long InUcastPkts;
        public long InUnknownProtos;
        public byte InterfaceAndOperStatusFlags;
        public Guid.GUID InterfaceGuid;
        public int InterfaceIndex;
        public long InterfaceLuid;
        public int MediaConnectState;
        public int MediaType;
        public int Mtu;
        public Guid.GUID NetworkGuid;
        public int OperStatus;
        public long OutBroadcastOctets;
        public long OutDiscards;
        public long OutErrors;
        public long OutMulticastOctets;
        public long OutNUcastPkts;
        public long OutOctets;
        public long OutQLen;
        public long OutUcastOctets;
        public long OutUcastPkts;
        public int PhysicalAddressLength;
        public int PhysicalMediumType;
        public long ReceiveLinkSpeed;
        public long TransmitLinkSpeed;
        public int TunnelType;
        public int Type;
        public char[] Alias = new char[257];
        public char[] Description = new char[257];
        public byte[] PhysicalAddress = new byte[32];
        public byte[] PermanentPhysicalAddress = new byte[32];
    }

    @Structure.FieldOrder({"LocalAddr", "dwLocalScopeId", "dwLocalPort", "RemoteAddr", "dwRemoteScopeId", "dwRemotePort", "State", "dwOwningPid"})
    public static class MIB_TCP6ROW_OWNER_PID extends Structure {
        public byte[] LocalAddr = new byte[16];
        public byte[] RemoteAddr = new byte[16];
        public int State;
        public int dwLocalPort;
        public int dwLocalScopeId;
        public int dwOwningPid;
        public int dwRemotePort;
        public int dwRemoteScopeId;
    }

    @Structure.FieldOrder({"dwNumEntries", "table"})
    public static class MIB_TCP6TABLE_OWNER_PID extends Structure {
        public int dwNumEntries;
        public MIB_TCP6ROW_OWNER_PID[] table;

        public MIB_TCP6TABLE_OWNER_PID(Pointer pointer) {
            super(pointer);
            this.table = new MIB_TCP6ROW_OWNER_PID[1];
            read();
        }

        @Override // com.sun.jna.Structure
        public void read() {
            int i = getPointer().getInt(0L);
            this.dwNumEntries = i;
            if (i <= 0) {
                this.table = new MIB_TCP6ROW_OWNER_PID[0];
            } else {
                this.table = (MIB_TCP6ROW_OWNER_PID[]) new MIB_TCP6ROW_OWNER_PID().toArray(this.dwNumEntries);
                super.read();
            }
        }
    }

    @Structure.FieldOrder({"dwState", "dwLocalAddr", "dwLocalPort", "dwRemoteAddr", "dwRemotePort", "dwOwningPid"})
    public static class MIB_TCPROW_OWNER_PID extends Structure {
        public int dwLocalAddr;
        public int dwLocalPort;
        public int dwOwningPid;
        public int dwRemoteAddr;
        public int dwRemotePort;
        public int dwState;
    }

    @Structure.FieldOrder({"dwRtoAlgorithm", "dwRtoMin", "dwRtoMax", "dwMaxConn", "dwActiveOpens", "dwPassiveOpens", "dwAttemptFails", "dwEstabResets", "dwCurrEstab", "dwInSegs", "dwOutSegs", "dwRetransSegs", "dwInErrs", "dwOutRsts", "dwNumConns"})
    public static class MIB_TCPSTATS extends Structure {
        public int dwActiveOpens;
        public int dwAttemptFails;
        public int dwCurrEstab;
        public int dwEstabResets;
        public int dwInErrs;
        public int dwInSegs;
        public int dwMaxConn;
        public int dwNumConns;
        public int dwOutRsts;
        public int dwOutSegs;
        public int dwPassiveOpens;
        public int dwRetransSegs;
        public int dwRtoAlgorithm;
        public int dwRtoMax;
        public int dwRtoMin;
    }

    @Structure.FieldOrder({"dwNumEntries", "table"})
    public static class MIB_TCPTABLE_OWNER_PID extends Structure {
        public int dwNumEntries;
        public MIB_TCPROW_OWNER_PID[] table;

        public MIB_TCPTABLE_OWNER_PID(Pointer pointer) {
            super(pointer);
            this.table = new MIB_TCPROW_OWNER_PID[1];
            read();
        }

        @Override // com.sun.jna.Structure
        public void read() {
            int i = getPointer().getInt(0L);
            this.dwNumEntries = i;
            if (i <= 0) {
                this.table = new MIB_TCPROW_OWNER_PID[0];
            } else {
                this.table = (MIB_TCPROW_OWNER_PID[]) new MIB_TCPROW_OWNER_PID().toArray(this.dwNumEntries);
                super.read();
            }
        }
    }

    public interface MIB_TCP_STATE {
        public static final int MIB_TCP_STATE_CLOSED = 1;
        public static final int MIB_TCP_STATE_CLOSE_WAIT = 8;
        public static final int MIB_TCP_STATE_CLOSING = 9;
        public static final int MIB_TCP_STATE_DELETE_TCB = 12;
        public static final int MIB_TCP_STATE_ESTAB = 5;
        public static final int MIB_TCP_STATE_FIN_WAIT1 = 6;
        public static final int MIB_TCP_STATE_FIN_WAIT2 = 7;
        public static final int MIB_TCP_STATE_LAST_ACK = 10;
        public static final int MIB_TCP_STATE_LISTEN = 2;
        public static final int MIB_TCP_STATE_SYN_RCVD = 4;
        public static final int MIB_TCP_STATE_SYN_SENT = 3;
        public static final int MIB_TCP_STATE_TIME_WAIT = 11;
    }

    @Structure.FieldOrder({"ucLocalAddr", "dwLocalScopeId", "dwLocalPort", "dwOwningPid"})
    public static class MIB_UDP6ROW_OWNER_PID extends Structure {
        public int dwLocalPort;
        public int dwLocalScopeId;
        public int dwOwningPid;
        public byte[] ucLocalAddr = new byte[16];
    }

    @Structure.FieldOrder({"dwNumEntries", "table"})
    public static class MIB_UDP6TABLE_OWNER_PID extends Structure {
        public int dwNumEntries;
        public MIB_UDP6ROW_OWNER_PID[] table;

        public MIB_UDP6TABLE_OWNER_PID(Pointer pointer) {
            super(pointer);
            this.table = new MIB_UDP6ROW_OWNER_PID[1];
            read();
        }

        @Override // com.sun.jna.Structure
        public void read() {
            int i = getPointer().getInt(0L);
            this.dwNumEntries = i;
            if (i <= 0) {
                this.table = new MIB_UDP6ROW_OWNER_PID[0];
            } else {
                this.table = (MIB_UDP6ROW_OWNER_PID[]) new MIB_UDP6ROW_OWNER_PID().toArray(this.dwNumEntries);
                super.read();
            }
        }
    }

    @Structure.FieldOrder({"dwLocalAddr", "dwLocalPort", "dwOwningPid"})
    public static class MIB_UDPROW_OWNER_PID extends Structure {
        public int dwLocalAddr;
        public int dwLocalPort;
        public int dwOwningPid;
    }

    @Structure.FieldOrder({"dwInDatagrams", "dwNoPorts", "dwInErrors", "dwOutDatagrams", "dwNumAddrs"})
    public static class MIB_UDPSTATS extends Structure {
        public int dwInDatagrams;
        public int dwInErrors;
        public int dwNoPorts;
        public int dwNumAddrs;
        public int dwOutDatagrams;
    }

    @Structure.FieldOrder({"dwNumEntries", "table"})
    public static class MIB_UDPTABLE_OWNER_PID extends Structure {
        public int dwNumEntries;
        public MIB_UDPROW_OWNER_PID[] table;

        public MIB_UDPTABLE_OWNER_PID(Pointer pointer) {
            super(pointer);
            this.table = new MIB_UDPROW_OWNER_PID[1];
            read();
        }

        @Override // com.sun.jna.Structure
        public void read() {
            int i = getPointer().getInt(0L);
            this.dwNumEntries = i;
            if (i <= 0) {
                this.table = new MIB_UDPROW_OWNER_PID[0];
            } else {
                this.table = (MIB_UDPROW_OWNER_PID[]) new MIB_UDPROW_OWNER_PID().toArray(this.dwNumEntries);
                super.read();
            }
        }
    }

    public interface TCP_TABLE_CLASS {
        public static final int TCP_TABLE_BASIC_ALL = 2;
        public static final int TCP_TABLE_BASIC_CONNECTIONS = 1;
        public static final int TCP_TABLE_BASIC_LISTENER = 0;
        public static final int TCP_TABLE_OWNER_MODULE_ALL = 8;
        public static final int TCP_TABLE_OWNER_MODULE_CONNECTIONS = 7;
        public static final int TCP_TABLE_OWNER_MODULE_LISTENER = 6;
        public static final int TCP_TABLE_OWNER_PID_ALL = 5;
        public static final int TCP_TABLE_OWNER_PID_CONNECTIONS = 4;
        public static final int TCP_TABLE_OWNER_PID_LISTENER = 3;
    }

    public interface UDP_TABLE_CLASS {
        public static final int UDP_TABLE_BASIC = 0;
        public static final int UDP_TABLE_OWNER_MODULE = 2;
        public static final int UDP_TABLE_OWNER_PID = 1;
    }

    int GetExtendedTcpTable(Pointer pointer, IntByReference intByReference, boolean z, int i, int i2, int i3);

    int GetExtendedUdpTable(Pointer pointer, IntByReference intByReference, boolean z, int i, int i2, int i3);

    int GetIfEntry(MIB_IFROW mib_ifrow);

    int GetIfEntry2(MIB_IF_ROW2 mib_if_row2);

    int GetNetworkParams(Pointer pointer, IntByReference intByReference);

    int GetTcpStatistics(MIB_TCPSTATS mib_tcpstats);

    int GetTcpStatisticsEx(MIB_TCPSTATS mib_tcpstats, int i);

    int GetUdpStatistics(MIB_UDPSTATS mib_udpstats);

    int GetUdpStatisticsEx(MIB_UDPSTATS mib_udpstats, int i);

    @Structure.FieldOrder({"HostName", "DomainName", "CurrentDnsServer", "DnsServerList", "NodeType", "ScopeId", "EnableRouting", "EnableProxy", "EnableDns"})
    public static class FIXED_INFO extends Structure {
        public IP_ADDR_STRING.ByReference CurrentDnsServer;
        public IP_ADDR_STRING DnsServerList;
        public byte[] DomainName;
        public int EnableDns;
        public int EnableProxy;
        public int EnableRouting;
        public byte[] HostName;
        public int NodeType;
        public byte[] ScopeId;

        public FIXED_INFO(Pointer pointer) {
            super(pointer);
            this.HostName = new byte[132];
            this.DomainName = new byte[132];
            this.ScopeId = new byte[260];
            read();
        }

        public FIXED_INFO() {
            this.HostName = new byte[132];
            this.DomainName = new byte[132];
            this.ScopeId = new byte[260];
        }
    }
}
