package com.sun.jna.win32;

import com.sun.jna.FunctionMapper;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;
import defpackage.aca;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StdCallFunctionMapper implements FunctionMapper {
    public int getArgumentNativeStackSize(Class<?> cls) {
        if (NativeMapped.class.isAssignableFrom(cls)) {
            cls = NativeMappedConverter.getInstance(cls).nativeType();
        }
        if (cls.isArray()) {
            return Native.POINTER_SIZE;
        }
        try {
            return Native.getNativeSize(cls);
        } catch (IllegalArgumentException unused) {
            aca.a("Unknown native stack allocation size for ", cls);
            return 0;
        }
    }

    @Override // com.sun.jna.FunctionMapper
    public String getFunctionName(NativeLibrary nativeLibrary, Method method) {
        String name = method.getName();
        int argumentNativeStackSize = 0;
        for (Class<?> cls : method.getParameterTypes()) {
            argumentNativeStackSize += getArgumentNativeStackSize(cls);
        }
        String str = name + "@" + argumentNativeStackSize;
        try {
            try {
                return nativeLibrary.getFunction(str, 63).getName();
            } catch (UnsatisfiedLinkError unused) {
                return name;
            }
        } catch (UnsatisfiedLinkError unused2) {
            return nativeLibrary.getFunction("_" + str, 63).getName();
        }
    }
}
