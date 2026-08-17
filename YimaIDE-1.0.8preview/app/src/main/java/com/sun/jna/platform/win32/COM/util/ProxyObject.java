package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.internal.ReflectionUtils;
import com.sun.jna.platform.win32.COM.COMException;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.ConnectionPoint;
import com.sun.jna.platform.win32.COM.ConnectionPointContainer;
import com.sun.jna.platform.win32.COM.Dispatch;
import com.sun.jna.platform.win32.COM.IDispatchCallback;
import com.sun.jna.platform.win32.COM.util.annotation.ComInterface;
import com.sun.jna.platform.win32.COM.util.annotation.ComMethod;
import com.sun.jna.platform.win32.COM.util.annotation.ComProperty;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProxyObject implements IConnectionPoint, IDispatch, IRawDispatchHandle, InvocationHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ObjectFactory factory;
    private final com.sun.jna.platform.win32.COM.IDispatch rawDispatch;
    private final Class<?> theInterface;
    private long unknownId = -1;

    public ProxyObject(Class<?> cls, com.sun.jna.platform.win32.COM.IDispatch iDispatch, ObjectFactory objectFactory) {
        this.rawDispatch = iDispatch;
        this.theInterface = cls;
        this.factory = objectFactory;
        iDispatch.AddRef();
        getUnknownId();
        objectFactory.register(this);
    }

    private ConnectionPoint fetchRawConnectionPoint(Guid.IID iid) {
        ConnectionPointContainer connectionPointContainer = new ConnectionPointContainer(((Dispatch) ((IConnectionPointContainer) queryInterface(IConnectionPointContainer.class)).getRawDispatch()).getPointer());
        Guid.REFIID refiid = new Guid.REFIID(iid.getPointer());
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(connectionPointContainer.FindConnectionPoint(refiid, pointerByReference));
        return new ConnectionPoint(pointerByReference.getValue());
    }

    private String getAccessorName(Method method, ComProperty comProperty) {
        if (!comProperty.name().isEmpty()) {
            return comProperty.name();
        }
        String name = method.getName();
        if (name.startsWith("get")) {
            return name.replaceFirst("get", "");
        }
        f63.a("Property Accessor name must start with 'get', or set the anotation 'name' value");
        return null;
    }

    private Guid.IID getIID(ComInterface comInterface) {
        String strIid = comInterface.iid();
        if (strIid == null || strIid.isEmpty()) {
            throw new COMException("ComInterface must define a value for iid");
        }
        return new Guid.IID(strIid);
    }

    private String getMethodName(Method method, ComMethod comMethod) {
        return comMethod.name().isEmpty() ? method.getName() : comMethod.name();
    }

    private String getMutatorName(Method method, ComProperty comProperty) {
        if (!comProperty.name().isEmpty()) {
            return comProperty.name();
        }
        String name = method.getName();
        if (name.startsWith("set")) {
            return name.replaceFirst("set", "");
        }
        f63.a("Property Mutator name must start with 'set', or set the anotation 'name' value");
        return null;
    }

    private long getUnknownId() {
        if (-1 == this.unknownId) {
            try {
                PointerByReference pointerByReference = new PointerByReference();
                Thread.currentThread().getName();
                WinNT.HRESULT hresultQueryInterface = getRawDispatch().QueryInterface(new Guid.REFIID(com.sun.jna.platform.win32.COM.IUnknown.IID_IUNKNOWN), pointerByReference);
                if (!WinError.S_OK.equals(hresultQueryInterface)) {
                    throw new COMException("getUnknownId: " + Kernel32Util.formatMessage(hresultQueryInterface), hresultQueryInterface);
                }
                Dispatch dispatch = new Dispatch(pointerByReference.getValue());
                this.unknownId = Pointer.nativeValue(dispatch.getPointer());
                dispatch.Release();
            } catch (RuntimeException e) {
                if (e instanceof COMException) {
                    throw e;
                }
                throw new COMException("Error occured when trying get Unknown Id ", e);
            }
        }
        return this.unknownId;
    }

    private void setProperty(OaIdl.DISPID dispid, Object... objArr) {
        Variant.VARIANT[] variantArr = objArr == null ? new Variant.VARIANT[0] : new Variant.VARIANT[objArr.length];
        for (int i = 0; i < variantArr.length; i++) {
            variantArr[i] = Convert.toVariant(objArr[i]);
        }
        WinNT.HRESULT hresultOleMethod = oleMethod(4, (Variant.VARIANT.ByReference) null, getRawDispatch(), dispid, variantArr);
        for (int i2 = 0; i2 < variantArr.length; i2++) {
            Convert.free(variantArr[i2], objArr[i2]);
        }
        COMUtils.checkRC(hresultOleMethod);
    }

    private Object[] unfoldWhenVarargs(Method method, Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0 || !method.isVarArgs() || !(objArr[objArr.length - 1] instanceof Object[])) {
            return objArr;
        }
        Object[] objArr2 = (Object[]) objArr[objArr.length - 1];
        Object[] objArr3 = new Object[(objArr.length - 1) + objArr2.length];
        System.arraycopy(objArr, 0, objArr3, 0, objArr.length - 1);
        System.arraycopy(objArr2, 0, objArr3, objArr.length - 1, objArr2.length);
        return objArr3;
    }

    @Override // com.sun.jna.platform.win32.COM.util.IConnectionPoint
    public IComEventCallbackCookie advise(Class<?> cls, IComEventCallbackListener iComEventCallbackListener) throws COMException {
        try {
            ComInterface comInterface = (ComInterface) cls.getAnnotation(ComInterface.class);
            if (comInterface == null) {
                throw new COMException("advise: Interface must define a value for either iid via the ComInterface annotation");
            }
            ConnectionPoint connectionPointFetchRawConnectionPoint = fetchRawConnectionPoint(getIID(comInterface));
            IDispatchCallback iDispatchCallbackCreateDispatchCallback = this.factory.createDispatchCallback(cls, iComEventCallbackListener);
            iComEventCallbackListener.setDispatchCallbackListener(iDispatchCallbackCreateDispatchCallback);
            WinDef.DWORDByReference dWORDByReference = new WinDef.DWORDByReference();
            WinNT.HRESULT hresultAdvise = connectionPointFetchRawConnectionPoint.Advise(iDispatchCallbackCreateDispatchCallback, dWORDByReference);
            connectionPointFetchRawConnectionPoint.Release();
            COMUtils.checkRC(hresultAdvise);
            return new ComEventCallbackCookie(dWORDByReference.getValue());
        } catch (RuntimeException e) {
            if (e instanceof COMException) {
                throw e;
            }
            throw new COMException("Error occured in advise when trying to connect the listener " + iComEventCallbackListener, e);
        }
    }

    public synchronized void dispose() {
        Pointer pointer = ((Dispatch) this.rawDispatch).getPointer();
        Pointer pointer2 = Pointer.NULL;
        if (pointer != pointer2) {
            this.rawDispatch.Release();
            ((Dispatch) this.rawDispatch).setPointer(pointer2);
            this.factory.unregister(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof ProxyObject) {
            return getUnknownId() == ((ProxyObject) obj).getUnknownId();
        }
        if (Proxy.isProxyClass(obj.getClass())) {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(obj);
            if (invocationHandler instanceof ProxyObject) {
                try {
                    if (getUnknownId() == ((ProxyObject) invocationHandler).getUnknownId()) {
                        return true;
                    }
                } catch (Exception unused) {
                }
            }
        }
        return false;
    }

    public void finalize() throws Throwable {
        dispose();
        super.finalize();
    }

    @Override // com.sun.jna.platform.win32.COM.util.IDispatch
    public <T> T getProperty(Class<T> cls, OaIdl.DISPID dispid, Object... objArr) {
        Variant.VARIANT[] variantArr = objArr == null ? new Variant.VARIANT[0] : new Variant.VARIANT[objArr.length];
        for (int i = 0; i < variantArr.length; i++) {
            variantArr[i] = Convert.toVariant(objArr[i]);
        }
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        WinNT.HRESULT hresultOleMethod = oleMethod(2, byReference, getRawDispatch(), dispid, variantArr);
        for (int i2 = 0; i2 < variantArr.length; i2++) {
            Convert.free(variantArr[i2], objArr[i2]);
        }
        COMUtils.checkRC(hresultOleMethod);
        return (T) Convert.toJavaObject(byReference, cls, this.factory, false, true);
    }

    @Override // com.sun.jna.platform.win32.COM.util.IRawDispatchHandle
    public com.sun.jna.platform.win32.COM.IDispatch getRawDispatch() {
        return this.rawDispatch;
    }

    public int hashCode() {
        long unknownId = getUnknownId();
        return ((int) (unknownId >>> 32)) + ((int) unknownId);
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        boolean z = (method.getAnnotation(ComMethod.class) == null && method.getAnnotation(ComProperty.class) == null) ? false : true;
        if (!z && (method.getDeclaringClass().equals(Object.class) || method.getDeclaringClass().equals(IRawDispatchHandle.class) || method.getDeclaringClass().equals(IUnknown.class) || method.getDeclaringClass().equals(IDispatch.class) || method.getDeclaringClass().equals(IConnectionPoint.class))) {
            try {
                return method.invoke(this, objArr);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
        if (!z && ReflectionUtils.isDefault(method)) {
            return ReflectionUtils.invokeDefaultMethod(obj, ReflectionUtils.getMethodHandle(method), objArr);
        }
        Class<?> returnType = method.getReturnType();
        boolean zEquals = Void.TYPE.equals(returnType);
        ComProperty comProperty = (ComProperty) method.getAnnotation(ComProperty.class);
        if (comProperty == null) {
            ComMethod comMethod = (ComMethod) method.getAnnotation(ComMethod.class);
            if (comMethod == null) {
                return null;
            }
            Object[] objArrUnfoldWhenVarargs = unfoldWhenVarargs(method, objArr);
            int iDispId = comMethod.dispId();
            return iDispId != -1 ? invokeMethod(returnType, new OaIdl.DISPID(iDispId), objArrUnfoldWhenVarargs) : invokeMethod(returnType, getMethodName(method, comMethod), objArrUnfoldWhenVarargs);
        }
        int iDispId2 = comProperty.dispId();
        Object[] objArrUnfoldWhenVarargs2 = unfoldWhenVarargs(method, objArr);
        if (!zEquals) {
            return iDispId2 != -1 ? getProperty(returnType, new OaIdl.DISPID(iDispId2), objArr) : getProperty(returnType, getAccessorName(method, comProperty), objArr);
        }
        if (iDispId2 != -1) {
            setProperty(new OaIdl.DISPID(iDispId2), objArrUnfoldWhenVarargs2);
            return null;
        }
        setProperty(getMutatorName(method, comProperty), objArrUnfoldWhenVarargs2);
        return null;
    }

    @Override // com.sun.jna.platform.win32.COM.util.IDispatch
    public <T> T invokeMethod(Class<T> cls, OaIdl.DISPID dispid, Object... objArr) {
        Variant.VARIANT[] variantArr = objArr == null ? new Variant.VARIANT[0] : new Variant.VARIANT[objArr.length];
        for (int i = 0; i < variantArr.length; i++) {
            variantArr[i] = Convert.toVariant(objArr[i]);
        }
        Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
        WinNT.HRESULT hresultOleMethod = oleMethod(1, byReference, getRawDispatch(), dispid, variantArr);
        for (int i2 = 0; i2 < variantArr.length; i2++) {
            Convert.free(variantArr[i2], objArr[i2]);
        }
        COMUtils.checkRC(hresultOleMethod);
        return (T) Convert.toJavaObject(byReference, cls, this.factory, false, true);
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, com.sun.jna.platform.win32.COM.IDispatch iDispatch, OaIdl.DISPID dispid, Variant.VARIANT[] variantArr) throws COMException {
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
        if (i == 1 || i == 2) {
            i = 3;
        }
        if (i2 > 0) {
            byReference2.setArgs(variantArr2);
            byReference2.write();
        }
        WinNT.HRESULT hresultInvoke = iDispatch.Invoke(dispid, new Guid.REFIID(Guid.IID_NULL), this.factory.getLCID(), new WinDef.WORD(i), byReference2, byReference, byReference3, intByReference);
        COMUtils.checkRC(hresultInvoke, byReference3, intByReference);
        return hresultInvoke;
    }

    @Override // com.sun.jna.platform.win32.COM.util.IUnknown
    public <T> T queryInterface(Class<T> cls) throws COMException {
        try {
            ComInterface comInterface = (ComInterface) cls.getAnnotation(ComInterface.class);
            if (comInterface == null) {
                throw new COMException("queryInterface: Interface must define a value for iid via the ComInterface annotation");
            }
            Guid.IID iid = getIID(comInterface);
            PointerByReference pointerByReference = new PointerByReference();
            WinNT.HRESULT hresultQueryInterface = getRawDispatch().QueryInterface(new Guid.REFIID(iid), pointerByReference);
            if (WinError.S_OK.equals(hresultQueryInterface)) {
                Dispatch dispatch = new Dispatch(pointerByReference.getValue());
                T t = (T) this.factory.createProxy(cls, dispatch);
                dispatch.Release();
                return t;
            }
            throw new COMException("queryInterface: " + Kernel32Util.formatMessage(hresultQueryInterface), hresultQueryInterface);
        } catch (RuntimeException e) {
            if (e instanceof COMException) {
                throw e;
            }
            throw new COMException("Error occured when trying to query for interface ".concat(cls.getName()), e);
        }
    }

    @Deprecated
    public OaIdl.DISPID resolveDispId(com.sun.jna.platform.win32.COM.IDispatch iDispatch, String str) {
        if (iDispatch == null) {
            throw new COMException("pDisp (IDispatch) parameter is null!");
        }
        WString[] wStringArr = {new WString(str)};
        OaIdl.DISPIDByReference dISPIDByReference = new OaIdl.DISPIDByReference();
        COMUtils.checkRC(iDispatch.GetIDsOfNames(new Guid.REFIID(Guid.IID_NULL), wStringArr, 1, this.factory.getLCID(), dISPIDByReference));
        return dISPIDByReference.getValue();
    }

    public String toString() {
        return this.theInterface.getName() + "{unk=" + hashCode() + "}";
    }

    @Override // com.sun.jna.platform.win32.COM.util.IConnectionPoint
    public void unadvise(Class<?> cls, IComEventCallbackCookie iComEventCallbackCookie) throws COMException {
        try {
            ComInterface comInterface = (ComInterface) cls.getAnnotation(ComInterface.class);
            if (comInterface == null) {
                throw new COMException("unadvise: Interface must define a value for iid via the ComInterface annotation");
            }
            ConnectionPoint connectionPointFetchRawConnectionPoint = fetchRawConnectionPoint(getIID(comInterface));
            WinNT.HRESULT hresultUnadvise = connectionPointFetchRawConnectionPoint.Unadvise(((ComEventCallbackCookie) iComEventCallbackCookie).getValue());
            connectionPointFetchRawConnectionPoint.Release();
            COMUtils.checkRC(hresultUnadvise);
        } catch (RuntimeException e) {
            if (e instanceof COMException) {
                throw e;
            }
            throw new COMException("Error occured in unadvise when trying to disconnect the listener from " + this, e);
        }
    }

    public OaIdl.DISPID resolveDispId(String str) {
        return resolveDispId(getRawDispatch(), str);
    }

    @Override // com.sun.jna.platform.win32.COM.util.IDispatch
    public <T> void setProperty(OaIdl.DISPID dispid, T t) {
        Variant.VARIANT variant = Convert.toVariant(t);
        WinNT.HRESULT hresultOleMethod = oleMethod(4, (Variant.VARIANT.ByReference) null, getRawDispatch(), dispid, variant);
        Convert.free(variant, t);
        COMUtils.checkRC(hresultOleMethod);
    }

    private void setProperty(String str, Object... objArr) {
        setProperty(resolveDispId(getRawDispatch(), str), objArr);
    }

    @Override // com.sun.jna.platform.win32.COM.util.IDispatch
    public <T> void setProperty(String str, T t) {
        setProperty(resolveDispId(getRawDispatch(), str), t);
    }

    @Override // com.sun.jna.platform.win32.COM.util.IDispatch
    public <T> T getProperty(Class<T> cls, String str, Object... objArr) {
        return (T) getProperty(cls, resolveDispId(getRawDispatch(), str), objArr);
    }

    @Override // com.sun.jna.platform.win32.COM.util.IDispatch
    public <T> T invokeMethod(Class<T> cls, String str, Object... objArr) {
        return (T) invokeMethod(cls, resolveDispId(getRawDispatch(), str), objArr);
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

    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, String str, Variant.VARIANT[] variantArr) throws COMException {
        return oleMethod(i, byReference, resolveDispId(str), variantArr);
    }

    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, OaIdl.DISPID dispid, Variant.VARIANT[] variantArr) throws COMException {
        return oleMethod(i, byReference, getRawDispatch(), dispid, variantArr);
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, com.sun.jna.platform.win32.COM.IDispatch iDispatch, String str, Variant.VARIANT variant) throws COMException {
        return oleMethod(i, byReference, iDispatch, str, new Variant.VARIANT[]{variant});
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, com.sun.jna.platform.win32.COM.IDispatch iDispatch, OaIdl.DISPID dispid, Variant.VARIANT variant) throws COMException {
        return oleMethod(i, byReference, iDispatch, dispid, new Variant.VARIANT[]{variant});
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, com.sun.jna.platform.win32.COM.IDispatch iDispatch, String str) throws COMException {
        return oleMethod(i, byReference, iDispatch, str, (Variant.VARIANT[]) null);
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, com.sun.jna.platform.win32.COM.IDispatch iDispatch, OaIdl.DISPID dispid) throws COMException {
        return oleMethod(i, byReference, iDispatch, dispid, (Variant.VARIANT[]) null);
    }

    @Deprecated
    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, com.sun.jna.platform.win32.COM.IDispatch iDispatch, String str, Variant.VARIANT[] variantArr) throws COMException {
        return oleMethod(i, byReference, iDispatch, resolveDispId(iDispatch, str), variantArr);
    }

    public WinNT.HRESULT oleMethod(int i, Variant.VARIANT.ByReference byReference, String str, Variant.VARIANT variant) throws COMException {
        return oleMethod(i, byReference, str, new Variant.VARIANT[]{variant});
    }
}
