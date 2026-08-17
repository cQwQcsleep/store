package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface OleAuto extends StdCallLibrary {
    public static final int DISPATCH_METHOD = 1;
    public static final int DISPATCH_PROPERTYGET = 2;
    public static final int DISPATCH_PROPERTYPUT = 4;
    public static final int DISPATCH_PROPERTYPUTREF = 8;
    public static final int FADF_AUTO = 1;
    public static final int FADF_BSTR = 256;
    public static final int FADF_DISPATCH = 1024;
    public static final int FADF_EMBEDDED = 4;
    public static final int FADF_FIXEDSIZE = 16;
    public static final int FADF_HAVEIID = 64;
    public static final int FADF_HAVEVARTYPE = 128;
    public static final int FADF_RECORD = 32;
    public static final int FADF_RESERVED = 61448;
    public static final int FADF_STATIC = 2;
    public static final int FADF_UNKNOWN = 512;
    public static final int FADF_VARIANT = 2048;
    public static final OleAuto INSTANCE = (OleAuto) Native.load("OleAut32", OleAuto.class, W32APIOptions.DEFAULT_OPTIONS);
    public static final short VARIANT_ALPHABOOL = 2;
    public static final short VARIANT_CALENDAR_GREGORIAN = 64;
    public static final short VARIANT_CALENDAR_HIJRI = 8;
    public static final short VARIANT_CALENDAR_THAI = 32;
    public static final short VARIANT_LOCALBOOL = 16;
    public static final short VARIANT_NOUSEROVERRIDE = 4;
    public static final short VARIANT_NOVALUEPROP = 1;
    public static final short VARIANT_USE_NLS = 128;

    WinNT.HRESULT GetActiveObject(Guid.GUID guid, WinDef.PVOID pvoid, PointerByReference pointerByReference);

    WinNT.HRESULT LoadRegTypeLib(Guid.GUID guid, int i, int i2, WinDef.LCID lcid, PointerByReference pointerByReference);

    WinNT.HRESULT LoadTypeLib(String str, PointerByReference pointerByReference);

    WinNT.HRESULT SafeArrayAccessData(OaIdl.SAFEARRAY safearray, PointerByReference pointerByReference);

    OaIdl.SAFEARRAY.ByReference SafeArrayCreate(WTypes.VARTYPE vartype, WinDef.UINT uint, OaIdl.SAFEARRAYBOUND[] safearrayboundArr);

    WinNT.HRESULT SafeArrayDestroy(OaIdl.SAFEARRAY safearray);

    WinDef.UINT SafeArrayGetDim(OaIdl.SAFEARRAY safearray);

    WinNT.HRESULT SafeArrayGetElement(OaIdl.SAFEARRAY safearray, WinDef.LONG[] longArr, Pointer pointer);

    WinDef.UINT SafeArrayGetElemsize(OaIdl.SAFEARRAY safearray);

    WinNT.HRESULT SafeArrayGetLBound(OaIdl.SAFEARRAY safearray, WinDef.UINT uint, WinDef.LONGByReference lONGByReference);

    WinNT.HRESULT SafeArrayGetUBound(OaIdl.SAFEARRAY safearray, WinDef.UINT uint, WinDef.LONGByReference lONGByReference);

    WinNT.HRESULT SafeArrayGetVartype(OaIdl.SAFEARRAY safearray, WTypes.VARTYPEByReference vARTYPEByReference);

    WinNT.HRESULT SafeArrayLock(OaIdl.SAFEARRAY safearray);

    WinNT.HRESULT SafeArrayPtrOfIndex(OaIdl.SAFEARRAY safearray, WinDef.LONG[] longArr, PointerByReference pointerByReference);

    WinNT.HRESULT SafeArrayPutElement(OaIdl.SAFEARRAY safearray, WinDef.LONG[] longArr, Pointer pointer);

    WinNT.HRESULT SafeArrayRedim(OaIdl.SAFEARRAY safearray, OaIdl.SAFEARRAYBOUND safearraybound);

    WinNT.HRESULT SafeArrayUnaccessData(OaIdl.SAFEARRAY safearray);

    WinNT.HRESULT SafeArrayUnlock(OaIdl.SAFEARRAY safearray);

    WTypes.BSTR SysAllocString(String str);

    void SysFreeString(WTypes.BSTR bstr);

    int SysStringByteLen(WTypes.BSTR bstr);

    int SysStringLen(WTypes.BSTR bstr);

    int SystemTimeToVariantTime(WinBase.SYSTEMTIME systemtime, DoubleByReference doubleByReference);

    WinNT.HRESULT VariantChangeType(Variant.VARIANT.ByReference byReference, Variant.VARIANT.ByReference byReference2, short s, WTypes.VARTYPE vartype);

    WinNT.HRESULT VariantChangeType(Variant.VARIANT variant, Variant.VARIANT variant2, short s, WTypes.VARTYPE vartype);

    WinNT.HRESULT VariantClear(Variant.VARIANT variant);

    WinNT.HRESULT VariantCopy(Pointer pointer, Variant.VARIANT variant);

    void VariantInit(Variant.VARIANT.ByReference byReference);

    void VariantInit(Variant.VARIANT variant);

    @Structure.FieldOrder({"rgvarg", "rgdispidNamedArgs", "cArgs", "cNamedArgs"})
    public static class DISPPARAMS extends Structure {
        public WinDef.UINT cArgs;
        public WinDef.UINT cNamedArgs;
        public Pointer rgdispidNamedArgs;
        public Variant.VariantArg.ByReference rgvarg;

        public static class ByReference extends DISPPARAMS implements Structure.ByReference {
        }

        public DISPPARAMS(Pointer pointer) {
            super(pointer);
            this.rgdispidNamedArgs = Pointer.NULL;
            this.cArgs = new WinDef.UINT(0L);
            this.cNamedArgs = new WinDef.UINT(0L);
            read();
        }

        public Variant.VARIANT[] getArgs() {
            Variant.VariantArg.ByReference byReference = this.rgvarg;
            if (byReference == null) {
                return new Variant.VARIANT[0];
            }
            byReference.setArraySize(this.cArgs.intValue());
            return this.rgvarg.variantArg;
        }

        public OaIdl.DISPID[] getRgdispidNamedArgs() {
            int iIntValue = this.cNamedArgs.intValue();
            Pointer pointer = this.rgdispidNamedArgs;
            if (pointer == null || iIntValue <= 0) {
                return new OaIdl.DISPID[0];
            }
            int[] intArray = pointer.getIntArray(0L, iIntValue);
            OaIdl.DISPID[] dispidArr = new OaIdl.DISPID[iIntValue];
            for (int i = 0; i < iIntValue; i++) {
                dispidArr[i] = new OaIdl.DISPID(intArray[i]);
            }
            return dispidArr;
        }

        public void setArgs(Variant.VARIANT[] variantArr) {
            if (variantArr == null) {
                variantArr = new Variant.VARIANT[0];
            }
            this.rgvarg = new Variant.VariantArg.ByReference(variantArr);
            this.cArgs = new WinDef.UINT(variantArr.length);
        }

        public void setRgdispidNamedArgs(OaIdl.DISPID[] dispidArr) {
            if (dispidArr == null) {
                dispidArr = new OaIdl.DISPID[0];
            }
            this.cNamedArgs = new WinDef.UINT(dispidArr.length);
            this.rgdispidNamedArgs = new Memory(WinDef.LONG.SIZE * dispidArr.length);
            int length = dispidArr.length;
            int[] iArr = new int[length];
            for (int i = 0; i < length; i++) {
                iArr[i] = dispidArr[i].intValue();
            }
            this.rgdispidNamedArgs.write(0L, iArr, 0, dispidArr.length);
        }

        public DISPPARAMS() {
            this.rgdispidNamedArgs = Pointer.NULL;
            this.cArgs = new WinDef.UINT(0L);
            this.cNamedArgs = new WinDef.UINT(0L);
        }
    }
}
