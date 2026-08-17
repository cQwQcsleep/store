package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import defpackage.kka;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Secur32Util {

    public static class SecurityPackage {
        public String comment;
        public String name;
    }

    public static SecurityPackage[] getSecurityPackages() {
        IntByReference intByReference = new IntByReference();
        Sspi.PSecPkgInfo pSecPkgInfo = new Sspi.PSecPkgInfo();
        int iEnumerateSecurityPackages = Secur32.INSTANCE.EnumerateSecurityPackages(intByReference, pSecPkgInfo);
        if (iEnumerateSecurityPackages != 0) {
            kka.a(iEnumerateSecurityPackages);
            return null;
        }
        Sspi.SecPkgInfo.ByReference[] array = pSecPkgInfo.toArray(intByReference.getValue());
        ArrayList arrayList = new ArrayList(intByReference.getValue());
        for (Sspi.SecPkgInfo.ByReference byReference : array) {
            SecurityPackage securityPackage = new SecurityPackage();
            securityPackage.name = byReference.Name.toString();
            securityPackage.comment = byReference.Comment.toString();
            arrayList.add(securityPackage);
        }
        int iFreeContextBuffer = Secur32.INSTANCE.FreeContextBuffer(pSecPkgInfo.pPkgInfo.getPointer());
        if (iFreeContextBuffer == 0) {
            return (SecurityPackage[]) arrayList.toArray(new SecurityPackage[0]);
        }
        kka.a(iFreeContextBuffer);
        return null;
    }

    public static String getUserNameEx(int i) {
        char[] cArr = new char[128];
        IntByReference intByReference = new IntByReference(128);
        Secur32 secur32 = Secur32.INSTANCE;
        boolean zGetUserNameEx = secur32.GetUserNameEx(i, cArr, intByReference);
        if (!zGetUserNameEx) {
            if (Kernel32.INSTANCE.GetLastError() != 234) {
                throw new Win32Exception(Native.getLastError());
            }
            cArr = new char[intByReference.getValue() + 1];
            zGetUserNameEx = secur32.GetUserNameEx(i, cArr, intByReference);
        }
        if (zGetUserNameEx) {
            return Native.toString(cArr);
        }
        throw new Win32Exception(Native.getLastError());
    }
}
