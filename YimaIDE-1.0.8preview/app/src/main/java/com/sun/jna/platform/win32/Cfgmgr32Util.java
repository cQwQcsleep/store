package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APITypeMapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Cfgmgr32Util {

    public static class Cfgmgr32Exception extends RuntimeException {
        private final int errorCode;

        public Cfgmgr32Exception(int i) {
            this.errorCode = i;
        }

        public int getErrorCode() {
            return this.errorCode;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return super.toString() + String.format(" [errorCode: 0x%08x]", Integer.valueOf(this.errorCode));
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static Object CM_Get_DevNode_Registry_Property(int i, int i2) throws Cfgmgr32Exception {
        Memory memory;
        IntByReference intByReference = new IntByReference();
        IntByReference intByReference2 = new IntByReference();
        Cfgmgr32 cfgmgr32 = Cfgmgr32.INSTANCE;
        int iCM_Get_DevNode_Registry_Property = cfgmgr32.CM_Get_DevNode_Registry_Property(i, i2, intByReference2, null, intByReference, 0);
        if (iCM_Get_DevNode_Registry_Property == 37) {
            return null;
        }
        if (iCM_Get_DevNode_Registry_Property != 26) {
            throw new Cfgmgr32Exception(iCM_Get_DevNode_Registry_Property);
        }
        if (intByReference.getValue() > 0) {
            memory = new Memory(intByReference.getValue());
            int iCM_Get_DevNode_Registry_Property2 = cfgmgr32.CM_Get_DevNode_Registry_Property(i, i2, intByReference2, memory, intByReference, 0);
            if (iCM_Get_DevNode_Registry_Property2 != 0) {
                throw new Cfgmgr32Exception(iCM_Get_DevNode_Registry_Property2);
            }
        } else {
            memory = null;
        }
        int value = intByReference2.getValue();
        if (value == 0) {
            return null;
        }
        if (value == 1) {
            if (memory == null) {
                return "";
            }
            return W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE ? memory.getWideString(0L) : memory.getString(0L);
        }
        if (value != 4) {
            if (value != 7) {
                return memory == null ? new byte[0] : memory.getByteArray(0L, (int) memory.size());
            }
            return memory == null ? new String[0] : Advapi32Util.regMultiSzBufferToStringArray(memory);
        }
        if (memory == null) {
            return 0;
        }
        return Integer.valueOf(memory.getInt(0L));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static String CM_Get_Device_ID(int i) throws Cfgmgr32Exception {
        int i2 = Boolean.getBoolean("w32.ascii") ? 1 : Native.WCHAR_SIZE;
        IntByReference intByReference = new IntByReference();
        Cfgmgr32 cfgmgr32 = Cfgmgr32.INSTANCE;
        int iCM_Get_Device_ID_Size = cfgmgr32.CM_Get_Device_ID_Size(intByReference, i, 0);
        if (iCM_Get_Device_ID_Size != 0) {
            throw new Cfgmgr32Exception(iCM_Get_Device_ID_Size);
        }
        Memory memory = new Memory((intByReference.getValue() + 1) * i2);
        memory.clear();
        int iCM_Get_Device_ID = cfgmgr32.CM_Get_Device_ID(i, memory, intByReference.getValue(), 0);
        if (iCM_Get_Device_ID == 26) {
            int iCM_Get_Device_ID_Size2 = cfgmgr32.CM_Get_Device_ID_Size(intByReference, i, 0);
            if (iCM_Get_Device_ID_Size2 != 0) {
                throw new Cfgmgr32Exception(iCM_Get_Device_ID_Size2);
            }
            memory = new Memory((intByReference.getValue() + 1) * i2);
            memory.clear();
            iCM_Get_Device_ID = cfgmgr32.CM_Get_Device_ID(i, memory, intByReference.getValue(), 0);
        }
        if (iCM_Get_Device_ID == 0) {
            return i2 == 1 ? memory.getString(0L) : memory.getWideString(0L);
        }
        throw new Cfgmgr32Exception(iCM_Get_Device_ID);
    }
}
