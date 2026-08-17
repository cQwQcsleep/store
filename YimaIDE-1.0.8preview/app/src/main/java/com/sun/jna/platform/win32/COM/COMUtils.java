package com.sun.jna.platform.win32.COM;

import com.sun.jna.LastErrorException;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinReg;
import com.sun.jna.ptr.IntByReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class COMUtils {
    public static final int E_UNEXPECTED = -2147418113;
    public static final int S_FALSE = 1;
    public static final int S_OK = 0;

    public static boolean FAILED(WinNT.HRESULT hresult) {
        return FAILED(hresult.intValue());
    }

    public static boolean SUCCEEDED(WinNT.HRESULT hresult) {
        return SUCCEEDED(hresult.intValue());
    }

    public static void checkRC(WinNT.HRESULT hresult, OaIdl.EXCEPINFO excepinfo, IntByReference intByReference) {
        String value;
        Integer num;
        String value2;
        Integer num2;
        String str;
        Integer num3;
        String value3;
        if (!FAILED(hresult)) {
            if (excepinfo != null) {
                WTypes.BSTR bstr = excepinfo.bstrSource;
                if (bstr != null) {
                    OleAuto.INSTANCE.SysFreeString(bstr);
                }
                WTypes.BSTR bstr2 = excepinfo.bstrDescription;
                if (bstr2 != null) {
                    OleAuto.INSTANCE.SysFreeString(bstr2);
                }
                WTypes.BSTR bstr3 = excepinfo.bstrHelpFile;
                if (bstr3 != null) {
                    OleAuto.INSTANCE.SysFreeString(bstr3);
                    return;
                }
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        Integer numValueOf = intByReference != null ? Integer.valueOf(intByReference.getValue()) : null;
        try {
            sb.append(Kernel32Util.formatMessage(hresult));
        } catch (LastErrorException unused) {
        }
        sb.append("(HRESULT: ");
        sb.append(Integer.toHexString(hresult.intValue()));
        sb.append(")");
        if (excepinfo != null) {
            Integer numValueOf2 = Integer.valueOf(excepinfo.wCode.intValue());
            Integer numValueOf3 = Integer.valueOf(excepinfo.scode.intValue());
            Integer numValueOf4 = Integer.valueOf(excepinfo.dwHelpContext.intValue());
            WTypes.BSTR bstr4 = excepinfo.bstrSource;
            if (bstr4 != null) {
                value3 = bstr4.getValue();
                sb.append("\nSource:      ");
                sb.append(value3);
            } else {
                value3 = null;
            }
            WTypes.BSTR bstr5 = excepinfo.bstrDescription;
            if (bstr5 != null) {
                value = bstr5.getValue();
                sb.append("\nDescription: ");
                sb.append(value);
            } else {
                value = null;
            }
            WTypes.BSTR bstr6 = excepinfo.bstrHelpFile;
            num3 = numValueOf2;
            value2 = bstr6 != null ? bstr6.getValue() : null;
            num2 = numValueOf3;
            num = numValueOf4;
            str = value3;
        } else {
            value = null;
            num = null;
            value2 = null;
            num2 = null;
            str = null;
            num3 = null;
        }
        throw new COMInvokeException(sb.toString(), hresult, numValueOf, value, num, value2, num2, str, num3);
    }

    public static boolean comIsInitialized() {
        Ole32 ole32 = Ole32.INSTANCE;
        WinNT.HRESULT hresultCoInitializeEx = ole32.CoInitializeEx(Pointer.NULL, 0);
        if (hresultCoInitializeEx.equals(WinError.S_OK)) {
            ole32.CoUninitialize();
            return false;
        }
        if (hresultCoInitializeEx.equals(WinError.S_FALSE)) {
            ole32.CoUninitialize();
            return true;
        }
        if (hresultCoInitializeEx.intValue() == -2147417850) {
            return true;
        }
        checkRC(hresultCoInitializeEx);
        return false;
    }

    public static ArrayList<COMInfo> getAllCOMInfoOnSystem() {
        WinReg.HKEYByReference hKEYByReference = new WinReg.HKEYByReference();
        WinReg.HKEYByReference hKEYByReference2 = new WinReg.HKEYByReference();
        ArrayList<COMInfo> arrayList = new ArrayList<>();
        try {
            hKEYByReference = Advapi32Util.registryGetKey(WinReg.HKEY_CLASSES_ROOT, "CLSID", 131097);
            Advapi32Util.InfoKey infoKeyRegistryQueryInfoKey = Advapi32Util.registryQueryInfoKey(hKEYByReference.getValue(), 131097);
            for (int i = 0; i < infoKeyRegistryQueryInfoKey.lpcSubKeys.getValue(); i++) {
                String string = Native.toString(Advapi32Util.registryRegEnumKey(hKEYByReference.getValue(), i).lpName);
                COMInfo cOMInfo = new COMInfo(string);
                hKEYByReference2 = Advapi32Util.registryGetKey(hKEYByReference.getValue(), string, 131097);
                Advapi32Util.InfoKey infoKeyRegistryQueryInfoKey2 = Advapi32Util.registryQueryInfoKey(hKEYByReference2.getValue(), 131097);
                for (int i2 = 0; i2 < infoKeyRegistryQueryInfoKey2.lpcSubKeys.getValue(); i2++) {
                    String string2 = Native.toString(Advapi32Util.registryRegEnumKey(hKEYByReference2.getValue(), i2).lpName);
                    if (string2.equals("InprocHandler32")) {
                        cOMInfo.inprocHandler32 = (String) Advapi32Util.registryGetValue(hKEYByReference2.getValue(), string2, null);
                    } else if (string2.equals("InprocServer32")) {
                        cOMInfo.inprocServer32 = (String) Advapi32Util.registryGetValue(hKEYByReference2.getValue(), string2, null);
                    } else if (string2.equals("LocalServer32")) {
                        cOMInfo.localServer32 = (String) Advapi32Util.registryGetValue(hKEYByReference2.getValue(), string2, null);
                    } else if (string2.equals("ProgID")) {
                        cOMInfo.progID = (String) Advapi32Util.registryGetValue(hKEYByReference2.getValue(), string2, null);
                    } else if (string2.equals("TypeLib")) {
                        cOMInfo.typeLib = (String) Advapi32Util.registryGetValue(hKEYByReference2.getValue(), string2, null);
                    }
                }
                Advapi32.INSTANCE.RegCloseKey(hKEYByReference2.getValue());
                arrayList.add(cOMInfo);
            }
            return arrayList;
        } finally {
            Advapi32 advapi32 = Advapi32.INSTANCE;
            advapi32.RegCloseKey(hKEYByReference.getValue());
            advapi32.RegCloseKey(hKEYByReference2.getValue());
        }
    }

    public static class COMInfo {
        public String clsid;
        public String inprocHandler32;
        public String inprocServer32;
        public String localServer32;
        public String progID;
        public String typeLib;

        public COMInfo(String str) {
            this.clsid = str;
        }

        public COMInfo() {
        }
    }

    public static boolean FAILED(int i) {
        return i < 0;
    }

    public static boolean SUCCEEDED(int i) {
        return i >= 0;
    }

    public static void checkRC(WinNT.HRESULT hresult) {
        String str;
        if (FAILED(hresult)) {
            try {
                str = Kernel32Util.formatMessage(hresult) + "(HRESULT: " + Integer.toHexString(hresult.intValue()) + ")";
            } catch (LastErrorException unused) {
                str = "(HRESULT: " + Integer.toHexString(hresult.intValue()) + ")";
            }
            throw new COMException(str, hresult);
        }
    }
}
