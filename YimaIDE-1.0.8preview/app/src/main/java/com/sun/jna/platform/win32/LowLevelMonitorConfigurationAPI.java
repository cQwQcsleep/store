package com.sun.jna.platform.win32;

import com.sun.jna.Structure;
import com.sun.jna.platform.EnumUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LowLevelMonitorConfigurationAPI {

    @Structure.FieldOrder({"dwHorizontalFrequencyInHZ", "dwVerticalFrequencyInHZ", "bTimingStatusByte"})
    public static class MC_TIMING_REPORT extends Structure {
        public WinDef.BYTE bTimingStatusByte;
        public WinDef.DWORD dwHorizontalFrequencyInHZ;
        public WinDef.DWORD dwVerticalFrequencyInHZ;
    }

    public enum MC_VCP_CODE_TYPE {
        MC_MOMENTARY,
        MC_SET_PARAMETER;

        public static class ByReference extends com.sun.jna.ptr.ByReference {
            public ByReference(MC_VCP_CODE_TYPE mc_vcp_code_type) {
                super(4);
                setValue(mc_vcp_code_type);
            }

            public MC_VCP_CODE_TYPE getValue() {
                return (MC_VCP_CODE_TYPE) EnumUtils.fromInteger(getPointer().getInt(0L), MC_VCP_CODE_TYPE.class);
            }

            public void setValue(MC_VCP_CODE_TYPE mc_vcp_code_type) {
                getPointer().setInt(0L, EnumUtils.toInteger(mc_vcp_code_type));
            }

            public ByReference() {
                super(4);
            }
        }
    }
}
