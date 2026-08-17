package com.sun.jna.platform.win32.COM;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class COMBindingBaseObject extends COMInvoker {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final WinDef.LCID LOCALE_SYSTEM_DEFAULT;
    public static final WinDef.LCID LOCALE_USER_DEFAULT;
    private IDispatch iDispatch;
    private IUnknown iUnknown;
    private PointerByReference pDispatch;
    private PointerByReference pUnknown;

    static {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        LOCALE_USER_DEFAULT = kernel32.GetUserDefaultLCID();
        LOCALE_SYSTEM_DEFAULT = kernel32.GetSystemDefaultLCID();
    }

    public COMBindingBaseObject(String str, boolean z, int i) throws COMException {
        this.pDispatch = new PointerByReference();
        this.pUnknown = new PointerByReference();
        Guid.CLSID.ByReference byReference = new Guid.CLSID.ByReference();
        COMUtils.checkRC(Ole32.INSTANCE.CLSIDFromProgID(str, byReference));
        init(z, byReference, i);
    }

    private void init(boolean z, Guid.GUID guid, int i) throws COMException {
        WinNT.HRESULT hresultCoCreateInstance;
        if (z && COMUtils.SUCCEEDED(OleAuto.INSTANCE.GetActiveObject(guid, null, this.pUnknown))) {
            Unknown unknown = new Unknown(this.pUnknown.getValue());
            this.iUnknown = unknown;
            hresultCoCreateInstance = unknown.QueryInterface(new Guid.REFIID(IDispatch.IID_IDISPATCH), this.pDispatch);
        } else {
            hresultCoCreateInstance = Ole32.INSTANCE.CoCreateInstance(guid, null, i, IDispatch.IID_IDISPATCH, this.pDispatch);
        }
        COMUtils.checkRC(hresultCoCreateInstance);
        this.iDispatch = new Dispatch(this.pDispatch.getValue());
    }

    @Deprecated
    public void checkFailed(WinNT.HRESULT hresult) {
        COMUtils.checkRC(hresult);
    }

    public IDispatch getIDispatch() {
        return this.iDispatch;
    }

    public PointerByReference getIDispatchPointer() {
        return this.pDispatch;
    }

    public IUnknown getIUnknown() {
        return this.iUnknown;
    }

    public PointerByReference getIUnknownPointer() {
        return this.pUnknown;
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, IDispatch iDispatch, OaIdl.DISPID dispid, Variant.VARIANT[] variantArr) throws COMException {
        Variant.VARIANT[] variantArr2;
        if (iDispatch == null) {
            throw new COMException("pDisp (IDispatch) parameter is null!");
        }
        OleAuto.DISPPARAMS.ByReference byReference2 = new OleAuto.DISPPARAMS.ByReference();
        OaIdl.EXCEPINFO.ByReference byReference3 = new OaIdl.EXCEPINFO.ByReference();
        IntByReference intByReference = new IntByReference();
        int i2 = 0;
        if (variantArr == null || variantArr.length <= 0) {
            variantArr2 = null;
        } else {
            int length = variantArr.length;
            variantArr2 = new Variant.VARIANT[length];
            int i3 = length;
            while (i2 < length) {
                i3--;
                variantArr2[i2] = variantArr[i3];
                i2++;
            }
            i2 = length;
        }
        if (i == 4) {
            byReference2.setRgdispidNamedArgs(new OaIdl.DISPID[]{OaIdl.DISPID_PROPERTYPUT});
        }
        if (i2 > 0) {
            byReference2.setArgs(variantArr2);
            byReference2.write();
        }
        if (i == 1 || i == 2) {
            i = 3;
        }
        WinNT.HRESULT hresultInvoke = iDispatch.Invoke(dispid, new Guid.REFIID(Guid.IID_NULL), LOCALE_SYSTEM_DEFAULT, new WinDef.WORD(i), byReference2, byReference, byReference3, intByReference);
        COMUtils.checkRC(hresultInvoke, byReference3, intByReference);
        return hresultInvoke;
    }

    public void release() {
        IDispatch iDispatch = this.iDispatch;
        if (iDispatch != null) {
            iDispatch.Release();
        }
    }

    public COMBindingBaseObject(Guid.CLSID clsid, boolean z) {
        this(clsid, z, 21);
    }

    public COMBindingBaseObject(Guid.CLSID clsid, boolean z, int i) {
        this.pDispatch = new PointerByReference();
        this.pUnknown = new PointerByReference();
        init(z, clsid, i);
    }

    public COMBindingBaseObject(IDispatch iDispatch) {
        this.pDispatch = new PointerByReference();
        this.pUnknown = new PointerByReference();
        this.iDispatch = iDispatch;
    }

    public COMBindingBaseObject(String str, boolean z) throws COMException {
        this(str, z, 21);
    }

    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, OaIdl.DISPID dispid, Variant.VARIANT[] variantArr) throws COMException {
        Variant.VARIANT[] variantArr2;
        OleAuto.DISPPARAMS.ByReference byReference2 = new OleAuto.DISPPARAMS.ByReference();
        OaIdl.EXCEPINFO.ByReference byReference3 = new OaIdl.EXCEPINFO.ByReference();
        IntByReference intByReference = new IntByReference();
        int i2 = 0;
        if (variantArr == null || variantArr.length <= 0) {
            variantArr2 = null;
        } else {
            int length = variantArr.length;
            variantArr2 = new Variant.VARIANT[length];
            int i3 = length;
            while (i2 < length) {
                i3--;
                variantArr2[i2] = variantArr[i3];
                i2++;
            }
            i2 = length;
        }
        if (i == 4) {
            byReference2.setRgdispidNamedArgs(new OaIdl.DISPID[]{OaIdl.DISPID_PROPERTYPUT});
        }
        if (i2 > 0) {
            byReference2.setArgs(variantArr2);
            byReference2.write();
        }
        if (i == 1 || i == 2) {
            i = 3;
        }
        WinNT.HRESULT hresultInvoke = this.iDispatch.Invoke(dispid, new Guid.REFIID(Guid.IID_NULL), LOCALE_SYSTEM_DEFAULT, new WinDef.WORD(i), byReference2, byReference, byReference3, intByReference);
        COMUtils.checkRC(hresultInvoke, byReference3, intByReference);
        return hresultInvoke;
    }

    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, String str, Variant.VARIANT variant) throws COMException {
        return oleMethod(i, byReference, str, new Variant.VARIANT[]{variant});
    }

    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, OaIdl.DISPID dispid, Variant.VARIANT variant) throws COMException {
        return oleMethod(i, byReference, dispid, new Variant.VARIANT[]{variant});
    }

    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, String str) throws COMException {
        return oleMethod(i, byReference, str, (Variant.VARIANT[]) null);
    }

    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, OaIdl.DISPID dispid) throws COMException {
        return oleMethod(i, byReference, dispid, (Variant.VARIANT[]) null);
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, IDispatch iDispatch, String str, Variant.VARIANT[] variantArr) throws COMException {
        if (iDispatch != null) {
            WString[] wStringArr = {new WString(str)};
            OaIdl.DISPIDByReference dISPIDByReference = new OaIdl.DISPIDByReference();
            COMUtils.checkRC(iDispatch.GetIDsOfNames(new Guid.REFIID(Guid.IID_NULL), wStringArr, 1, LOCALE_USER_DEFAULT, dISPIDByReference));
            return oleMethod(i, byReference, iDispatch, dISPIDByReference.getValue(), variantArr);
        }
        throw new COMException("pDisp (IDispatch) parameter is null!");
    }

    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, String str, Variant.VARIANT[] variantArr) throws COMException {
        WString[] wStringArr = {new WString(str)};
        OaIdl.DISPIDByReference dISPIDByReference = new OaIdl.DISPIDByReference();
        COMUtils.checkRC(this.iDispatch.GetIDsOfNames(new Guid.REFIID(Guid.IID_NULL), wStringArr, 1, LOCALE_USER_DEFAULT, dISPIDByReference));
        return oleMethod(i, byReference, dISPIDByReference.getValue(), variantArr);
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, IDispatch iDispatch, String str, Variant.VARIANT variant) throws COMException {
        return oleMethod(i, byReference, iDispatch, str, new Variant.VARIANT[]{variant});
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, IDispatch iDispatch, OaIdl.DISPID dispid, Variant.VARIANT variant) throws COMException {
        return oleMethod(i, byReference, iDispatch, dispid, new Variant.VARIANT[]{variant});
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, IDispatch iDispatch, String str) throws COMException {
        return oleMethod(i, byReference, iDispatch, str, (Variant.VARIANT[]) null);
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, IDispatch iDispatch, OaIdl.DISPID dispid) throws COMException {
        return oleMethod(i, byReference, iDispatch, dispid, (Variant.VARIANT[]) null);
    }
}
