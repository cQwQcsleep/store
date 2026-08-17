package com.sun.jna.platform.win32;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.W32APITypeMapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DsGetDC {
    public static final int DS_DOMAIN_DIRECT_INBOUND = 32;
    public static final int DS_DOMAIN_DIRECT_OUTBOUND = 2;
    public static final int DS_DOMAIN_IN_FOREST = 1;
    public static final int DS_DOMAIN_NATIVE_MODE = 16;
    public static final int DS_DOMAIN_PRIMARY = 8;
    public static final int DS_DOMAIN_TREE_ROOT = 4;
    public static final int DS_DOMAIN_VALID_FLAGS = 63;

    @Structure.FieldOrder({"dci"})
    public static class PDOMAIN_CONTROLLER_INFO extends Structure {
        public DOMAIN_CONTROLLER_INFO.ByReference dci;

        public static class ByReference extends PDOMAIN_CONTROLLER_INFO implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"DomainControllerName", "DomainControllerAddress", "DomainControllerAddressType", "DomainGuid", "DomainName", "DnsForestName", "Flags", "DcSiteName", "ClientSiteName"})
    public static class DOMAIN_CONTROLLER_INFO extends Structure {
        public String ClientSiteName;
        public String DcSiteName;
        public String DnsForestName;
        public String DomainControllerAddress;
        public int DomainControllerAddressType;
        public String DomainControllerName;
        public Guid.GUID DomainGuid;
        public String DomainName;
        public int Flags;

        public static class ByReference extends DOMAIN_CONTROLLER_INFO implements Structure.ByReference {
        }

        public DOMAIN_CONTROLLER_INFO(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.DEFAULT);
            read();
        }

        public DOMAIN_CONTROLLER_INFO() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    @Structure.FieldOrder({"NetbiosDomainName", "DnsDomainName", "Flags", "ParentIndex", "TrustType", "TrustAttributes", "DomainSid", "DomainGuid"})
    public static class DS_DOMAIN_TRUSTS extends Structure {
        public String DnsDomainName;
        public Guid.GUID DomainGuid;
        public WinNT.PSID.ByReference DomainSid;
        public int Flags;
        public String NetbiosDomainName;
        public int ParentIndex;
        public int TrustAttributes;
        public int TrustType;

        public static class ByReference extends DS_DOMAIN_TRUSTS implements Structure.ByReference {
        }

        public DS_DOMAIN_TRUSTS(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.DEFAULT);
            read();
        }

        public DS_DOMAIN_TRUSTS() {
            super(W32APITypeMapper.DEFAULT);
        }
    }
}
