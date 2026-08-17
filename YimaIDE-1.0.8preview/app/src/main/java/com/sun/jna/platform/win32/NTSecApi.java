package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface NTSecApi {
    public static final int ForestTrustDomainInfo = 2;
    public static final int ForestTrustTopLevelName = 0;
    public static final int ForestTrustTopLevelNameEx = 1;

    @Structure.FieldOrder({"Length", "Buffer"})
    public static class LSA_FOREST_TRUST_BINARY_DATA extends Structure {
        public Pointer Buffer;
        public int Length;
    }

    @Structure.FieldOrder({"Sid", "DnsName", "NetbiosName"})
    public static class LSA_FOREST_TRUST_DOMAIN_INFO extends Structure {
        public LSA_UNICODE_STRING DnsName;
        public LSA_UNICODE_STRING NetbiosName;
        public WinNT.PSID.ByReference Sid;
    }

    @Structure.FieldOrder({"RecordCount", "Entries"})
    public static class LSA_FOREST_TRUST_INFORMATION extends Structure {
        public PLSA_FOREST_TRUST_RECORD.ByReference Entries;
        public int RecordCount;

        public static class ByReference extends LSA_FOREST_TRUST_INFORMATION implements Structure.ByReference {
        }

        public PLSA_FOREST_TRUST_RECORD[] getEntries() {
            return (PLSA_FOREST_TRUST_RECORD[]) this.Entries.toArray(this.RecordCount);
        }
    }

    @Structure.FieldOrder({"Flags", "ForestTrustType", "Time", "u"})
    public static class LSA_FOREST_TRUST_RECORD extends Structure {
        public int Flags;
        public int ForestTrustType;
        public WinNT.LARGE_INTEGER Time;
        public UNION u;

        public static class ByReference extends LSA_FOREST_TRUST_RECORD implements Structure.ByReference {
        }

        public static class UNION extends Union {
            public LSA_FOREST_TRUST_BINARY_DATA Data;
            public LSA_FOREST_TRUST_DOMAIN_INFO DomainInfo;
            public LSA_UNICODE_STRING TopLevelName;

            public static class ByReference extends UNION implements Structure.ByReference {
            }
        }

        @Override // com.sun.jna.Structure
        public void read() {
            super.read();
            int i = this.ForestTrustType;
            if (i == 0 || i == 1) {
                this.u.setType(LSA_UNICODE_STRING.class);
            } else {
                UNION union = this.u;
                if (i != 2) {
                    union.setType(LSA_FOREST_TRUST_BINARY_DATA.class);
                } else {
                    union.setType(LSA_FOREST_TRUST_DOMAIN_INFO.class);
                }
            }
            this.u.read();
        }
    }

    @Structure.FieldOrder({"Length", "MaximumLength", "Buffer"})
    public static class LSA_UNICODE_STRING extends Structure {
        public Pointer Buffer;
        public short Length;
        public short MaximumLength;

        public static class ByReference extends LSA_UNICODE_STRING implements Structure.ByReference {
        }

        public String getString() {
            byte[] byteArray = this.Buffer.getByteArray(0L, this.Length);
            if (byteArray.length >= 2 && byteArray[byteArray.length - 1] == 0) {
                return this.Buffer.getWideString(0L);
            }
            Memory memory = new Memory(byteArray.length + 2);
            memory.write(0L, byteArray, 0, byteArray.length);
            return memory.getWideString(0L);
        }
    }

    @Structure.FieldOrder({"fti"})
    public static class PLSA_FOREST_TRUST_INFORMATION extends Structure {
        public LSA_FOREST_TRUST_INFORMATION.ByReference fti;

        public static class ByReference extends PLSA_FOREST_TRUST_INFORMATION implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"tr"})
    public static class PLSA_FOREST_TRUST_RECORD extends Structure {
        public LSA_FOREST_TRUST_RECORD.ByReference tr;

        public static class ByReference extends PLSA_FOREST_TRUST_RECORD implements Structure.ByReference {
        }
    }

    public static class PLSA_UNICODE_STRING {
        public LSA_UNICODE_STRING.ByReference s;

        public static class ByReference extends PLSA_UNICODE_STRING implements Structure.ByReference {
        }
    }
}
