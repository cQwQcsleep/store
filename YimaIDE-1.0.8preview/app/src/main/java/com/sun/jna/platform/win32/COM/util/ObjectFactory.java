package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.COMException;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Dispatch;
import com.sun.jna.platform.win32.COM.IDispatchCallback;
import com.sun.jna.platform.win32.COM.util.annotation.ComObject;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.PointerByReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ObjectFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final WinDef.LCID LOCALE_USER_DEFAULT = Kernel32.INSTANCE.GetUserDefaultLCID();
    private WinDef.LCID LCID;
    private final List<WeakReference<ProxyObject>> registeredObjects = new LinkedList();

    public IDispatchCallback createDispatchCallback(Class<?> cls, IComEventCallbackListener iComEventCallbackListener) {
        return new CallbackProxy(this, cls, iComEventCallbackListener);
    }

    public <T> T createObject(Class<T> cls) {
        ComObject comObject = (ComObject) cls.getAnnotation(ComObject.class);
        if (comObject == null) {
            throw new COMException("createObject: Interface must define a value for either clsId or progId via the ComInterface annotation");
        }
        Guid.GUID guidDiscoverClsId = discoverClsId(comObject);
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(Ole32.INSTANCE.CoCreateInstance(guidDiscoverClsId, null, 21, com.sun.jna.platform.win32.COM.IDispatch.IID_IDISPATCH, pointerByReference));
        Dispatch dispatch = new Dispatch(pointerByReference.getValue());
        T t = (T) createProxy(cls, dispatch);
        dispatch.Release();
        return t;
    }

    public <T> T createProxy(Class<T> cls, com.sun.jna.platform.win32.COM.IDispatch iDispatch) {
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new ProxyObject(cls, iDispatch, this)));
    }

    public Guid.GUID discoverClsId(ComObject comObject) {
        String strClsId = comObject.clsId();
        String strProgId = comObject.progId();
        if (strClsId != null && !strClsId.isEmpty()) {
            return new Guid.CLSID(strClsId);
        }
        if (strProgId == null || strProgId.isEmpty()) {
            throw new COMException("ComObject must define a value for either clsId or progId");
        }
        Guid.CLSID.ByReference byReference = new Guid.CLSID.ByReference();
        COMUtils.checkRC(Ole32.INSTANCE.CLSIDFromProgID(strProgId, byReference));
        return byReference;
    }

    public void disposeAll() {
        synchronized (this.registeredObjects) {
            try {
                Iterator it = new ArrayList(this.registeredObjects).iterator();
                while (it.hasNext()) {
                    ProxyObject proxyObject = (ProxyObject) ((WeakReference) it.next()).get();
                    if (proxyObject != null) {
                        proxyObject.dispose();
                    }
                }
                this.registeredObjects.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public <T> T fetchObject(Class<T> cls) throws COMException {
        ComObject comObject = (ComObject) cls.getAnnotation(ComObject.class);
        if (comObject == null) {
            throw new COMException("createObject: Interface must define a value for either clsId or progId via the ComInterface annotation");
        }
        Guid.GUID guidDiscoverClsId = discoverClsId(comObject);
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(OleAuto.INSTANCE.GetActiveObject(guidDiscoverClsId, null, pointerByReference));
        Dispatch dispatch = new Dispatch(pointerByReference.getValue());
        T t = (T) createProxy(cls, dispatch);
        dispatch.Release();
        return t;
    }

    public void finalize() throws Throwable {
        try {
            disposeAll();
        } finally {
            super.finalize();
        }
    }

    public WinDef.LCID getLCID() {
        WinDef.LCID lcid = this.LCID;
        return lcid != null ? lcid : LOCALE_USER_DEFAULT;
    }

    public IRunningObjectTable getRunningObjectTable() {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(Ole32.INSTANCE.GetRunningObjectTable(new WinDef.DWORD(0L), pointerByReference));
        return new RunningObjectTable(new com.sun.jna.platform.win32.COM.RunningObjectTable(pointerByReference.getValue()), this);
    }

    public void register(ProxyObject proxyObject) {
        synchronized (this.registeredObjects) {
            this.registeredObjects.add(new WeakReference<>(proxyObject));
        }
    }

    public void setLCID(WinDef.LCID lcid) {
        this.LCID = lcid;
    }

    public void unregister(ProxyObject proxyObject) {
        synchronized (this.registeredObjects) {
            try {
                Iterator<WeakReference<ProxyObject>> it = this.registeredObjects.iterator();
                while (it.hasNext()) {
                    ProxyObject proxyObject2 = it.next().get();
                    if (proxyObject2 == null || proxyObject2 == proxyObject) {
                        it.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
