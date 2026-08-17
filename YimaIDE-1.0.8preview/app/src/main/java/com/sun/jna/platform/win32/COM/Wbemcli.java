package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.OaIdlUtil;
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Wbemcli {
    public static final int CIM_BOOLEAN = 11;
    public static final int CIM_CHAR16 = 103;
    public static final int CIM_DATETIME = 101;
    public static final int CIM_EMPTY = 0;
    public static final int CIM_FLAG_ARRAY = 8192;
    public static final int CIM_ILLEGAL = 4095;
    public static final int CIM_OBJECT = 13;
    public static final int CIM_REAL32 = 4;
    public static final int CIM_REAL64 = 5;
    public static final int CIM_REFERENCE = 102;
    public static final int CIM_SINT16 = 2;
    public static final int CIM_SINT32 = 3;
    public static final int CIM_SINT64 = 20;
    public static final int CIM_SINT8 = 16;
    public static final int CIM_STRING = 8;
    public static final int CIM_UINT16 = 18;
    public static final int CIM_UINT32 = 19;
    public static final int CIM_UINT64 = 21;
    public static final int CIM_UINT8 = 17;
    public static final int WBEM_E_INVALID_CLASS = -2147217392;
    public static final int WBEM_E_INVALID_NAMESPACE = -2147217394;
    public static final int WBEM_E_INVALID_QUERY = -2147217385;
    public static final int WBEM_FLAG_FORWARD_ONLY = 32;
    public static final int WBEM_FLAG_RETURN_IMMEDIATELY = 16;
    public static final int WBEM_INFINITE = -1;
    public static final int WBEM_S_FALSE = 1;
    public static final int WBEM_S_NO_ERROR = 0;
    public static final int WBEM_S_NO_MORE_DATA = 262149;
    public static final int WBEM_S_TIMEDOUT = 262148;

    public interface WBEM_CONDITION_FLAG_TYPE {
        public static final int WBEM_FLAG_ALWAYS = 0;
        public static final int WBEM_FLAG_CLASS_LOCAL_AND_OVERRIDES = 512;
        public static final int WBEM_FLAG_CLASS_OVERRIDES_ONLY = 256;
        public static final int WBEM_FLAG_KEYS_ONLY = 4;
        public static final int WBEM_FLAG_LOCAL_ONLY = 16;
        public static final int WBEM_FLAG_NONSYSTEM_ONLY = 64;
        public static final int WBEM_FLAG_ONLY_IF_FALSE = 2;
        public static final int WBEM_FLAG_ONLY_IF_IDENTICAL = 3;
        public static final int WBEM_FLAG_ONLY_IF_TRUE = 1;
        public static final int WBEM_FLAG_PROPAGATED_ONLY = 32;
        public static final int WBEM_FLAG_REFS_ONLY = 8;
        public static final int WBEM_FLAG_SYSTEM_ONLY = 48;
        public static final int WBEM_MASK_CLASS_CONDITION = 768;
        public static final int WBEM_MASK_CONDITION_ORIGIN = 112;
        public static final int WBEM_MASK_PRIMARY_CONDITION = 3;
    }

    public static class IEnumWbemClassObject extends Unknown {
        public IEnumWbemClassObject() {
        }

        public IWbemClassObject[] Next(int i, int i2) {
            Pointer[] pointerArr = new Pointer[i2];
            IntByReference intByReference = new IntByReference();
            COMUtils.checkRC(Next(i, i2, pointerArr, intByReference));
            IWbemClassObject[] iWbemClassObjectArr = new IWbemClassObject[intByReference.getValue()];
            for (int i3 = 0; i3 < intByReference.getValue(); i3++) {
                iWbemClassObjectArr[i3] = new IWbemClassObject(pointerArr[i3]);
            }
            return iWbemClassObjectArr;
        }

        public IEnumWbemClassObject(Pointer pointer) {
            super(pointer);
        }

        public WinNT.HRESULT Next(int i, int i2, Pointer[] pointerArr, IntByReference intByReference) {
            return (WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), Integer.valueOf(i), Integer.valueOf(i2), pointerArr, intByReference}, WinNT.HRESULT.class);
        }
    }

    public static class IWbemClassObject extends Unknown {
        public IWbemClassObject() {
        }

        public WinNT.HRESULT Get(WString wString, int i, Variant.VARIANT.ByReference byReference, IntByReference intByReference, IntByReference intByReference2) {
            return (WinNT.HRESULT) _invokeNativeObject(4, new Object[]{getPointer(), wString, Integer.valueOf(i), byReference, intByReference, intByReference2}, WinNT.HRESULT.class);
        }

        public String[] GetNames(String str, int i, Variant.VARIANT.ByReference byReference) {
            PointerByReference pointerByReference = new PointerByReference();
            COMUtils.checkRC(GetNames(str, i, byReference, pointerByReference));
            Object[] objArr = (Object[]) OaIdlUtil.toPrimitiveArray(new OaIdl.SAFEARRAY(pointerByReference.getValue()), true);
            String[] strArr = new String[objArr.length];
            for (int i2 = 0; i2 < objArr.length; i2++) {
                strArr[i2] = (String) objArr[i2];
            }
            return strArr;
        }

        public IWbemClassObject(Pointer pointer) {
            super(pointer);
        }

        public WinNT.HRESULT Get(String str, int i, Variant.VARIANT.ByReference byReference, IntByReference intByReference, IntByReference intByReference2) {
            return Get(str == null ? null : new WString(str), i, byReference, intByReference, intByReference2);
        }

        public WinNT.HRESULT GetNames(WString wString, int i, Variant.VARIANT.ByReference byReference, PointerByReference pointerByReference) {
            return (WinNT.HRESULT) _invokeNativeObject(7, new Object[]{getPointer(), wString, Integer.valueOf(i), byReference, pointerByReference}, WinNT.HRESULT.class);
        }

        public WinNT.HRESULT GetNames(String str, int i, Variant.VARIANT.ByReference byReference, PointerByReference pointerByReference) {
            return GetNames(str == null ? null : new WString(str), i, byReference, pointerByReference);
        }
    }

    public static class IWbemContext extends Unknown {
        public IWbemContext() {
        }

        public IWbemContext(Pointer pointer) {
            super(pointer);
        }
    }

    public static class IWbemLocator extends Unknown {
        public static final Guid.CLSID CLSID_WbemLocator = new Guid.CLSID("4590f811-1d3a-11d0-891f-00aa004b2e24");
        public static final Guid.GUID IID_IWbemLocator = new Guid.GUID("dc12a687-737f-11cf-884d-00aa004b2e24");

        public IWbemLocator() {
        }

        public static IWbemLocator create() {
            PointerByReference pointerByReference = new PointerByReference();
            if (COMUtils.FAILED(Ole32.INSTANCE.CoCreateInstance(CLSID_WbemLocator, null, 1, IID_IWbemLocator, pointerByReference))) {
                return null;
            }
            return new IWbemLocator(pointerByReference.getValue());
        }

        public IWbemServices ConnectServer(String str, String str2, String str3, String str4, int i, String str5, IWbemContext iWbemContext) {
            OleAuto oleAuto = OleAuto.INSTANCE;
            WTypes.BSTR bstrSysAllocString = oleAuto.SysAllocString(str);
            WTypes.BSTR bstrSysAllocString2 = oleAuto.SysAllocString(str2);
            WTypes.BSTR bstrSysAllocString3 = oleAuto.SysAllocString(str3);
            WTypes.BSTR bstrSysAllocString4 = oleAuto.SysAllocString(str4);
            WTypes.BSTR bstrSysAllocString5 = oleAuto.SysAllocString(str5);
            PointerByReference pointerByReference = new PointerByReference();
            try {
                COMUtils.checkRC(ConnectServer(bstrSysAllocString, bstrSysAllocString2, bstrSysAllocString3, bstrSysAllocString4, i, bstrSysAllocString5, iWbemContext, pointerByReference));
                return new IWbemServices(pointerByReference.getValue());
            } finally {
                oleAuto = OleAuto.INSTANCE;
                oleAuto.SysFreeString(bstrSysAllocString);
                oleAuto.SysFreeString(bstrSysAllocString2);
                oleAuto.SysFreeString(bstrSysAllocString3);
                oleAuto.SysFreeString(bstrSysAllocString4);
                oleAuto.SysFreeString(bstrSysAllocString5);
            }
        }

        private IWbemLocator(Pointer pointer) {
            super(pointer);
        }

        public WinNT.HRESULT ConnectServer(WTypes.BSTR bstr, WTypes.BSTR bstr2, WTypes.BSTR bstr3, WTypes.BSTR bstr4, int i, WTypes.BSTR bstr5, IWbemContext iWbemContext, PointerByReference pointerByReference) {
            return (WinNT.HRESULT) _invokeNativeObject(3, new Object[]{getPointer(), bstr, bstr2, bstr3, bstr4, Integer.valueOf(i), bstr5, iWbemContext, pointerByReference}, WinNT.HRESULT.class);
        }
    }

    public static class IWbemServices extends Unknown {
        public IWbemServices() {
        }

        public IEnumWbemClassObject ExecQuery(String str, String str2, int i, IWbemContext iWbemContext) {
            OleAuto oleAuto = OleAuto.INSTANCE;
            WTypes.BSTR bstrSysAllocString = oleAuto.SysAllocString(str);
            WTypes.BSTR bstrSysAllocString2 = oleAuto.SysAllocString(str2);
            try {
                PointerByReference pointerByReference = new PointerByReference();
                COMUtils.checkRC(ExecQuery(bstrSysAllocString, bstrSysAllocString2, i, iWbemContext, pointerByReference));
                return new IEnumWbemClassObject(pointerByReference.getValue());
            } finally {
                oleAuto = OleAuto.INSTANCE;
                oleAuto.SysFreeString(bstrSysAllocString);
                oleAuto.SysFreeString(bstrSysAllocString2);
            }
        }

        public IWbemServices(Pointer pointer) {
            super(pointer);
        }

        public WinNT.HRESULT ExecQuery(WTypes.BSTR bstr, WTypes.BSTR bstr2, int i, IWbemContext iWbemContext, PointerByReference pointerByReference) {
            return (WinNT.HRESULT) _invokeNativeObject(20, new Object[]{getPointer(), bstr, bstr2, Integer.valueOf(i), iWbemContext, pointerByReference}, WinNT.HRESULT.class);
        }
    }
}
