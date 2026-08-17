package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class PdhUtil {
    private static final int CHAR_TO_BYTES;
    private static final String ENGLISH_COUNTER_KEY = "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Perflib\\009";
    private static final String ENGLISH_COUNTER_VALUE = "Counter";

    public static class PdhEnumObjectItems {
        private final List<String> counters;
        private final List<String> instances;

        public PdhEnumObjectItems(List<String> list, List<String> list2) {
            this.counters = copyAndEmptyListForNullList(list);
            this.instances = copyAndEmptyListForNullList(list2);
        }

        private List<String> copyAndEmptyListForNullList(List<String> list) {
            return list == null ? new ArrayList() : new ArrayList(list);
        }

        public List<String> getCounters() {
            return this.counters;
        }

        public List<String> getInstances() {
            return this.instances;
        }

        public String toString() {
            return "PdhEnumObjectItems{counters=" + this.counters + ", instances=" + this.instances + '}';
        }
    }

    public static final class PdhException extends RuntimeException {
        private final int errorCode;

        public PdhException(int i) {
            super(String.format("Pdh call failed with error code 0x%08X", Integer.valueOf(i)));
            this.errorCode = i;
        }

        public int getErrorCode() {
            return this.errorCode;
        }
    }

    static {
        CHAR_TO_BYTES = Boolean.getBoolean("w32.ascii") ? 1 : Native.WCHAR_SIZE;
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
    public static PdhEnumObjectItems PdhEnumObjectItems(String str, String str2, String str3, int i) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        WinDef.DWORDByReference dWORDByReference = new WinDef.DWORDByReference(new WinDef.DWORD(0L));
        WinDef.DWORDByReference dWORDByReference2 = new WinDef.DWORDByReference(new WinDef.DWORD(0L));
        Pdh pdh = Pdh.INSTANCE;
        int iPdhEnumObjectItems = pdh.PdhEnumObjectItems(str, str2, str3, null, dWORDByReference, null, dWORDByReference2, i, 0);
        if (iPdhEnumObjectItems != 0 && iPdhEnumObjectItems != -2147481646) {
            throw new PdhException(iPdhEnumObjectItems);
        }
        Memory memory = dWORDByReference.getValue().intValue() > 0 ? new Memory(dWORDByReference.getValue().intValue() * CHAR_TO_BYTES) : null;
        Memory memory2 = dWORDByReference2.getValue().intValue() > 0 ? new Memory(dWORDByReference2.getValue().intValue() * CHAR_TO_BYTES) : null;
        int iPdhEnumObjectItems2 = pdh.PdhEnumObjectItems(str, str2, str3, memory, dWORDByReference, memory2, dWORDByReference2, i, 0);
        if (iPdhEnumObjectItems2 != 0) {
            throw new PdhException(iPdhEnumObjectItems2);
        }
        int length = 0;
        if (memory != null) {
            int length2 = 0;
            while (true) {
                long j = length2;
                if (j >= memory.size()) {
                    break;
                }
                int i2 = CHAR_TO_BYTES;
                String string = i2 == 1 ? memory.getString(j) : memory.getWideString(j);
                if (string.isEmpty()) {
                    break;
                }
                arrayList.add(string);
                length2 += (string.length() + 1) * i2;
            }
        }
        if (memory2 != null) {
            while (true) {
                long j2 = length;
                if (j2 >= memory2.size()) {
                    break;
                }
                int i3 = CHAR_TO_BYTES;
                String string2 = i3 == 1 ? memory2.getString(j2) : memory2.getWideString(j2);
                if (string2.isEmpty()) {
                    break;
                }
                arrayList2.add(string2);
                length += (string2.length() + 1) * i3;
            }
        }
        return new PdhEnumObjectItems(arrayList, arrayList2);
    }

    public static int PdhLookupPerfIndexByEnglishName(String str) {
        String[] strArrRegistryGetStringArray = Advapi32Util.registryGetStringArray(WinReg.HKEY_LOCAL_MACHINE, ENGLISH_COUNTER_KEY, ENGLISH_COUNTER_VALUE);
        for (int i = 1; i < strArrRegistryGetStringArray.length; i += 2) {
            if (strArrRegistryGetStringArray[i].equals(str)) {
                try {
                    return Integer.parseInt(strArrRegistryGetStringArray[i - 1]);
                } catch (NumberFormatException unused) {
                    return 0;
                }
            }
        }
        return 0;
    }

    public static String PdhLookupPerfNameByIndex(String str, int i) {
        int iPdhLookupPerfNameByIndex;
        WinDef.DWORDByReference dWORDByReference = new WinDef.DWORDByReference(new WinDef.DWORD(0L));
        Pdh pdh = Pdh.INSTANCE;
        Memory memory = null;
        int iPdhLookupPerfNameByIndex2 = pdh.PdhLookupPerfNameByIndex(str, i, null, dWORDByReference);
        if (iPdhLookupPerfNameByIndex2 == -1073738819) {
            for (int i2 = 32; i2 <= 1024; i2 *= 2) {
                WinDef.DWORDByReference dWORDByReference2 = new WinDef.DWORDByReference(new WinDef.DWORD(i2));
                memory = new Memory(CHAR_TO_BYTES * i2);
                iPdhLookupPerfNameByIndex2 = Pdh.INSTANCE.PdhLookupPerfNameByIndex(str, i, memory, dWORDByReference2);
                if (iPdhLookupPerfNameByIndex2 != -1073738819 && iPdhLookupPerfNameByIndex2 != -1073738814) {
                    break;
                }
            }
            iPdhLookupPerfNameByIndex = iPdhLookupPerfNameByIndex2;
        } else {
            if (iPdhLookupPerfNameByIndex2 != 0 && iPdhLookupPerfNameByIndex2 != -2147481646) {
                throw new PdhException(iPdhLookupPerfNameByIndex2);
            }
            if (dWORDByReference.getValue().intValue() < 1) {
                return "";
            }
            memory = new Memory(dWORDByReference.getValue().intValue() * CHAR_TO_BYTES);
            iPdhLookupPerfNameByIndex = pdh.PdhLookupPerfNameByIndex(str, i, memory, dWORDByReference);
        }
        if (iPdhLookupPerfNameByIndex == 0) {
            return CHAR_TO_BYTES == 1 ? memory.getString(0L) : memory.getWideString(0L);
        }
        throw new PdhException(iPdhLookupPerfNameByIndex);
    }
}
