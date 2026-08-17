package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import defpackage.kka;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class WevtapiUtil {
    public static String EvtFormatMessage(Winevt.EVT_HANDLE evt_handle, Winevt.EVT_HANDLE evt_handle2, int i, int i2, Winevt.EVT_VARIANT[] evt_variantArr, int i3) {
        IntByReference intByReference = new IntByReference();
        Wevtapi wevtapi = Wevtapi.INSTANCE;
        boolean zEvtFormatMessage = wevtapi.EvtFormatMessage(evt_handle, evt_handle2, i, i2, evt_variantArr, i3, 0, null, intByReference);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iGetLastError = kernel32.GetLastError();
        if (!zEvtFormatMessage && iGetLastError != 122) {
            kka.a(iGetLastError);
            return null;
        }
        int value = intByReference.getValue();
        char[] cArr = new char[value];
        if (wevtapi.EvtFormatMessage(evt_handle, evt_handle2, i, i2, evt_variantArr, i3, value, cArr, intByReference)) {
            return Native.toString(cArr);
        }
        throw new Win32Exception(kernel32.GetLastError());
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
    public static Winevt.EVT_VARIANT EvtGetChannelConfigProperty(Winevt.EVT_HANDLE evt_handle, int i) {
        IntByReference intByReference = new IntByReference();
        Wevtapi wevtapi = Wevtapi.INSTANCE;
        boolean zEvtGetChannelConfigProperty = wevtapi.EvtGetChannelConfigProperty(evt_handle, i, 0, 0, null, intByReference);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iGetLastError = kernel32.GetLastError();
        if (!zEvtGetChannelConfigProperty && iGetLastError != 122) {
            kka.a(iGetLastError);
            return null;
        }
        Memory memory = new Memory(intByReference.getValue());
        if (!wevtapi.EvtGetChannelConfigProperty(evt_handle, i, 0, (int) memory.size(), memory, intByReference)) {
            throw new Win32Exception(kernel32.GetLastError());
        }
        Winevt.EVT_VARIANT evt_variant = new Winevt.EVT_VARIANT(memory);
        evt_variant.read();
        return evt_variant;
    }

    public static String EvtGetExtendedStatus() {
        IntByReference intByReference = new IntByReference();
        Wevtapi wevtapi = Wevtapi.INSTANCE;
        int iEvtGetExtendedStatus = wevtapi.EvtGetExtendedStatus(0, null, intByReference);
        if (iEvtGetExtendedStatus != 0 && iEvtGetExtendedStatus != 122) {
            kka.a(iEvtGetExtendedStatus);
            return null;
        }
        if (intByReference.getValue() == 0) {
            return "";
        }
        int value = intByReference.getValue();
        char[] cArr = new char[value];
        int iEvtGetExtendedStatus2 = wevtapi.EvtGetExtendedStatus(value, cArr, intByReference);
        if (iEvtGetExtendedStatus2 == 0) {
            return Native.toString(cArr);
        }
        kka.a(iEvtGetExtendedStatus2);
        return null;
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
    public static Memory EvtGetPublisherMetadataProperty(Winevt.EVT_HANDLE evt_handle, int i, int i2) {
        IntByReference intByReference = new IntByReference();
        Wevtapi wevtapi = Wevtapi.INSTANCE;
        boolean zEvtGetPublisherMetadataProperty = wevtapi.EvtGetPublisherMetadataProperty(evt_handle, i, i2, 0, null, intByReference);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iGetLastError = kernel32.GetLastError();
        if (!zEvtGetPublisherMetadataProperty && iGetLastError != 122) {
            kka.a(iGetLastError);
            return null;
        }
        Memory memory = new Memory(intByReference.getValue());
        if (wevtapi.EvtGetPublisherMetadataProperty(evt_handle, i, i2, (int) memory.size(), memory, intByReference)) {
            return memory;
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static String EvtNextPublisherId(Winevt.EVT_HANDLE evt_handle) {
        IntByReference intByReference = new IntByReference();
        Wevtapi wevtapi = Wevtapi.INSTANCE;
        boolean zEvtNextPublisherId = wevtapi.EvtNextPublisherId(evt_handle, 0, null, intByReference);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iGetLastError = kernel32.GetLastError();
        if (!zEvtNextPublisherId && iGetLastError != 122) {
            kka.a(iGetLastError);
            return null;
        }
        int value = intByReference.getValue();
        char[] cArr = new char[value];
        if (wevtapi.EvtNextPublisherId(evt_handle, value, cArr, intByReference)) {
            return Native.toString(cArr);
        }
        throw new Win32Exception(kernel32.GetLastError());
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
    public static Memory EvtRender(Winevt.EVT_HANDLE evt_handle, Winevt.EVT_HANDLE evt_handle2, int i, IntByReference intByReference) {
        IntByReference intByReference2 = new IntByReference();
        Wevtapi wevtapi = Wevtapi.INSTANCE;
        boolean zEvtRender = wevtapi.EvtRender(evt_handle, evt_handle2, i, 0, null, intByReference2, intByReference);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iGetLastError = kernel32.GetLastError();
        if (!zEvtRender && iGetLastError != 122) {
            kka.a(iGetLastError);
            return null;
        }
        Memory memory = new Memory(intByReference2.getValue());
        if (wevtapi.EvtRender(evt_handle, evt_handle2, i, (int) memory.size(), memory, intByReference2, intByReference)) {
            return memory;
        }
        throw new Win32Exception(kernel32.GetLastError());
    }
}
