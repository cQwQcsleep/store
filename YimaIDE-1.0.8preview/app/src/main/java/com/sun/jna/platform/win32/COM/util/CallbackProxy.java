package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.COM.COMException;
import com.sun.jna.platform.win32.COM.DispatchListener;
import com.sun.jna.platform.win32.COM.IDispatchCallback;
import com.sun.jna.platform.win32.COM.util.annotation.ComEventCallback;
import com.sun.jna.platform.win32.COM.util.annotation.ComInterface;
import com.sun.jna.platform.win32.COM.util.annotation.ComMethod;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import defpackage.yba;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CallbackProxy implements IDispatchCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean DEFAULT_BOOLEAN;
    private static byte DEFAULT_BYTE;
    private static double DEFAULT_DOUBLE;
    private static float DEFAULT_FLOAT;
    private static int DEFAULT_INT;
    private static long DEFAULT_LONG;
    private static short DEFAULT_SHORT;
    Class<?> comEventCallbackInterface;
    IComEventCallbackListener comEventCallbackListener;
    public DispatchListener dispatchListener = new DispatchListener(this);
    Map<OaIdl.DISPID, Method> dsipIdMap;
    ObjectFactory factory;
    Guid.REFIID listenedToRiid;

    public CallbackProxy(ObjectFactory objectFactory, Class<?> cls, IComEventCallbackListener iComEventCallbackListener) {
        this.factory = objectFactory;
        this.comEventCallbackInterface = cls;
        this.comEventCallbackListener = iComEventCallbackListener;
        this.listenedToRiid = createRIID(cls);
        this.dsipIdMap = createDispIdMap(cls);
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknown
    public int AddRef() {
        return 0;
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT GetIDsOfNames(Guid.REFIID refiid, WString[] wStringArr, int i, WinDef.LCID lcid, OaIdl.DISPIDByReference dISPIDByReference) {
        return new WinNT.HRESULT(WinError.E_NOTIMPL);
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT GetTypeInfo(WinDef.UINT uint, WinDef.LCID lcid, PointerByReference pointerByReference) {
        return new WinNT.HRESULT(WinError.E_NOTIMPL);
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT GetTypeInfoCount(WinDef.UINTByReference uINTByReference) {
        return new WinNT.HRESULT(WinError.E_NOTIMPL);
    }

    @Override // com.sun.jna.platform.win32.COM.IDispatch
    public WinNT.HRESULT Invoke(OaIdl.DISPID dispid, Guid.REFIID refiid, WinDef.LCID lcid, WinDef.WORD word, OleAuto.DISPPARAMS.ByReference byReference, Variant.VARIANT.ByReference byReference2, OaIdl.EXCEPINFO.ByReference byReference3, IntByReference intByReference) {
        invokeOnThread(dispid, refiid, lcid, word, byReference);
        return WinError.S_OK;
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknown
    public WinNT.HRESULT QueryInterface(Guid.REFIID refiid, PointerByReference pointerByReference) {
        if (pointerByReference == null) {
            return new WinNT.HRESULT(WinError.E_POINTER);
        }
        if (refiid.equals(this.listenedToRiid)) {
            pointerByReference.setValue(getPointer());
            return WinError.S_OK;
        }
        if (refiid.getValue().equals(com.sun.jna.platform.win32.COM.IUnknown.IID_IUNKNOWN)) {
            pointerByReference.setValue(getPointer());
            return WinError.S_OK;
        }
        if (!refiid.getValue().equals(com.sun.jna.platform.win32.COM.IDispatch.IID_IDISPATCH)) {
            return new WinNT.HRESULT(WinError.E_NOINTERFACE);
        }
        pointerByReference.setValue(getPointer());
        return WinError.S_OK;
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknown
    public int Release() {
        return 0;
    }

    public Map<OaIdl.DISPID, Method> createDispIdMap(Class<?> cls) {
        HashMap map = new HashMap();
        for (Method method : cls.getMethods()) {
            ComEventCallback comEventCallback = (ComEventCallback) method.getAnnotation(ComEventCallback.class);
            ComMethod comMethod = (ComMethod) method.getAnnotation(ComMethod.class);
            if (comMethod != null) {
                int iDispId = comMethod.dispId();
                if (-1 == iDispId) {
                    iDispId = fetchDispIdFromName(comEventCallback);
                }
                if (iDispId == -1) {
                    this.comEventCallbackListener.errorReceivingCallbackEvent("DISPID for " + method.getName() + " not found", null);
                }
                map.put(new OaIdl.DISPID(iDispId), method);
            } else if (comEventCallback != null) {
                int iDispid = comEventCallback.dispid();
                if (-1 == iDispid) {
                    iDispid = fetchDispIdFromName(comEventCallback);
                }
                if (iDispid == -1) {
                    this.comEventCallbackListener.errorReceivingCallbackEvent("DISPID for " + method.getName() + " not found", null);
                }
                map.put(new OaIdl.DISPID(iDispid), method);
            }
        }
        return map;
    }

    public Guid.REFIID createRIID(Class<?> cls) {
        ComInterface comInterface = (ComInterface) cls.getAnnotation(ComInterface.class);
        if (comInterface == null) {
            throw new COMException("advise: Interface must define a value for either iid via the ComInterface annotation");
        }
        String strIid = comInterface.iid();
        if (strIid == null || strIid.isEmpty()) {
            throw new COMException("ComInterface must define a value for iid");
        }
        return new Guid.REFIID(new Guid.IID(strIid).getPointer());
    }

    public int fetchDispIdFromName(ComEventCallback comEventCallback) {
        return -1;
    }

    @Override // com.sun.jna.platform.win32.COM.IUnknownCallback
    public Pointer getPointer() {
        return this.dispatchListener.getPointer();
    }

    public void invokeOnThread(OaIdl.DISPID dispid, Guid.REFIID refiid, WinDef.LCID lcid, WinDef.WORD word, OleAuto.DISPPARAMS.ByReference byReference) {
        Variant.VARIANT[] args = byReference.getArgs();
        Method method = this.dsipIdMap.get(dispid);
        if (method == null) {
            this.comEventCallbackListener.errorReceivingCallbackEvent("No method found with dispId = " + dispid, null);
            return;
        }
        OaIdl.DISPID[] rgdispidNamedArgs = byReference.getRgdispidNamedArgs();
        Class<?>[] parameterTypes = method.getParameterTypes();
        int length = parameterTypes.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length && (args.length - rgdispidNamedArgs.length) - i > 0; i++) {
            objArr[i] = Convert.toJavaObject(args[(args.length - i) - 1], parameterTypes[i], this.factory, true, false);
        }
        for (int i2 = 0; i2 < rgdispidNamedArgs.length; i2++) {
            int iIntValue = rgdispidNamedArgs[i2].intValue();
            if (iIntValue < length) {
                objArr[iIntValue] = Convert.toJavaObject(args[i2], parameterTypes[iIntValue], this.factory, true, false);
            }
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (objArr[i3] == null && parameterTypes[i3].isPrimitive()) {
                if (parameterTypes[i3].equals(Boolean.TYPE)) {
                    objArr[i3] = Boolean.valueOf(DEFAULT_BOOLEAN);
                } else if (parameterTypes[i3].equals(Byte.TYPE)) {
                    objArr[i3] = Byte.valueOf(DEFAULT_BYTE);
                } else if (parameterTypes[i3].equals(Short.TYPE)) {
                    objArr[i3] = Short.valueOf(DEFAULT_SHORT);
                } else if (parameterTypes[i3].equals(Integer.TYPE)) {
                    objArr[i3] = Integer.valueOf(DEFAULT_INT);
                } else if (parameterTypes[i3].equals(Long.TYPE)) {
                    objArr[i3] = Long.valueOf(DEFAULT_LONG);
                } else if (parameterTypes[i3].equals(Float.TYPE)) {
                    objArr[i3] = Float.valueOf(DEFAULT_FLOAT);
                } else {
                    if (!parameterTypes[i3].equals(Double.TYPE)) {
                        yba.a("Class type ", parameterTypes[i3].getName(), " not mapped to primitive default value.");
                        return;
                    }
                    objArr[i3] = Double.valueOf(DEFAULT_DOUBLE);
                }
            }
        }
        try {
            method.invoke(this.comEventCallbackListener, objArr);
        } catch (Exception e) {
            ArrayList arrayList = new ArrayList(length);
            for (int i4 = 0; i4 < length; i4++) {
                Object obj = objArr[i4];
                if (obj == null) {
                    arrayList.add("NULL");
                } else {
                    arrayList.add(obj.getClass().getName());
                }
            }
            this.comEventCallbackListener.errorReceivingCallbackEvent("Exception invoking method " + method + " supplied: " + arrayList.toString(), e);
        }
    }
}
