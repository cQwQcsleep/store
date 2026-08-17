package com.sun.jna.platform.win32;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.W32APITypeMapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LMShare {
    public static final int STYPE_DEVICE = 2;
    public static final int STYPE_DISKTREE = 0;
    public static final int STYPE_IPC = 3;
    public static final int STYPE_PRINTQ = 1;
    public static final int STYPE_SPECIAL = Integer.MIN_VALUE;
    public static final int STYPE_TEMPORARY = 1073741824;

    @Structure.FieldOrder({"shi2_netname", "shi2_type", "shi2_remark", "shi2_permissions", "shi2_max_uses", "shi2_current_uses", "shi2_path", "shi2_passwd"})
    public static class SHARE_INFO_2 extends Structure {
        public int shi2_current_uses;
        public int shi2_max_uses;
        public String shi2_netname;
        public String shi2_passwd;
        public String shi2_path;
        public int shi2_permissions;
        public String shi2_remark;
        public int shi2_type;

        public SHARE_INFO_2(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.UNICODE);
            read();
        }

        public SHARE_INFO_2() {
            super(W32APITypeMapper.UNICODE);
        }
    }

    @Structure.FieldOrder({"shi502_netname", "shi502_type", "shi502_remark", "shi502_permissions", "shi502_max_uses", "shi502_current_uses", "shi502_path", "shi502_passwd", "shi502_reserved", "shi502_security_descriptor"})
    public static class SHARE_INFO_502 extends Structure {
        public int shi502_current_uses;
        public int shi502_max_uses;
        public String shi502_netname;
        public String shi502_passwd;
        public String shi502_path;
        public int shi502_permissions;
        public String shi502_remark;
        public int shi502_reserved;
        public Pointer shi502_security_descriptor;
        public int shi502_type;

        public SHARE_INFO_502(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.UNICODE);
            read();
        }

        public SHARE_INFO_502() {
            super(W32APITypeMapper.UNICODE);
        }
    }
}
