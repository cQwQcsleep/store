package com.sun.jna.platform.win32;

import com.sun.jna.ptr.IntByReference;
import defpackage.kka;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class NtDllUtil {
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
    public static String getKeyName(WinReg.HKEY hkey) {
        IntByReference intByReference = new IntByReference();
        NtDll ntDll = NtDll.INSTANCE;
        int iZwQueryKey = ntDll.ZwQueryKey(hkey, 0, null, 0, intByReference);
        if (iZwQueryKey != -1073741789 || intByReference.getValue() <= 0) {
            kka.a(iZwQueryKey);
            return null;
        }
        Wdm.KEY_BASIC_INFORMATION key_basic_information = new Wdm.KEY_BASIC_INFORMATION(intByReference.getValue());
        int iZwQueryKey2 = ntDll.ZwQueryKey(hkey, 0, key_basic_information, intByReference.getValue(), intByReference);
        if (iZwQueryKey2 == 0) {
            return key_basic_information.getName();
        }
        kka.a(iZwQueryKey2);
        return null;
    }
}
