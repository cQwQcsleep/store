package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.COMException;
import com.sun.jna.platform.win32.COM.IDispatchCallback;
import com.sun.jna.platform.win32.COM.util.annotation.ComObject;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Factory extends ObjectFactory {
    private ComThread comThread;

    public class CallbackProxy2 extends CallbackProxy {
        public CallbackProxy2(ObjectFactory objectFactory, Class<?> cls, IComEventCallbackListener iComEventCallbackListener) {
            super(objectFactory, cls, iComEventCallbackListener);
        }

        @Override // com.sun.jna.platform.win32.COM.util.CallbackProxy, com.sun.jna.platform.win32.COM.IDispatch
        public WinNT.HRESULT Invoke(OaIdl.DISPID dispid, Guid.REFIID refiid, WinDef.LCID lcid, WinDef.WORD word, OleAuto.DISPPARAMS.ByReference byReference, Variant.VARIANT.ByReference byReference2, OaIdl.EXCEPINFO.ByReference byReference3, IntByReference intByReference) {
            ComThread.setComThread(true);
            try {
                return super.Invoke(dispid, refiid, lcid, word, byReference, byReference2, byReference3, intByReference);
            } finally {
                ComThread.setComThread(false);
            }
        }
    }

    public class ProxyObject2 implements InvocationHandler {
        private final Object delegate;

        public ProxyObject2(Object obj) {
            this.delegate = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, final Method method, final Object[] objArr) throws Throwable {
            if (objArr != null) {
                for (int i = 0; i < objArr.length; i++) {
                    Object obj2 = objArr[i];
                    if (obj2 != null && Proxy.isProxyClass(obj2.getClass())) {
                        InvocationHandler invocationHandler = Proxy.getInvocationHandler(objArr[i]);
                        if (invocationHandler instanceof ProxyObject2) {
                            objArr[i] = ((ProxyObject2) invocationHandler).delegate;
                        }
                    }
                }
            }
            return Factory.this.runInComThread(new Callable<Object>() { // from class: com.sun.jna.platform.win32.COM.util.Factory.ProxyObject2.1
                @Override // java.util.concurrent.Callable
                public Object call() throws Exception {
                    return method.invoke(ProxyObject2.this.delegate, objArr);
                }
            });
        }
    }

    public Factory() {
        this(new ComThread("Default Factory COM Thread", 5000L, new Thread.UncaughtExceptionHandler() { // from class: com.sun.jna.platform.win32.COM.util.Factory.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
            }
        }));
    }

    private static void appendStacktrace(Exception exc, Throwable th) {
        StackTraceElement[] stackTrace = exc.getStackTrace();
        StackTraceElement[] stackTrace2 = th.getStackTrace();
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[stackTrace.length + stackTrace2.length];
        System.arraycopy(stackTrace, 0, stackTraceElementArr, stackTrace2.length, stackTrace.length);
        System.arraycopy(stackTrace2, 0, stackTraceElementArr, 0, stackTrace2.length);
        th.setStackTrace(stackTraceElementArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T runInComThread(Callable<T> callable) {
        try {
            return (T) this.comThread.execute(callable);
        } catch (InterruptedException e) {
            rc6.a(e);
            return null;
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                appendStacktrace(e2, cause);
                throw ((RuntimeException) cause);
            }
            if (cause instanceof InvocationTargetException) {
                Throwable targetException = ((InvocationTargetException) cause).getTargetException();
                if (targetException instanceof RuntimeException) {
                    appendStacktrace(e2, targetException);
                    throw ((RuntimeException) targetException);
                }
            }
            rc6.a(e2);
            return null;
        } catch (TimeoutException e3) {
            rc6.a(e3);
            return null;
        }
    }

    @Override // com.sun.jna.platform.win32.COM.util.ObjectFactory
    public IDispatchCallback createDispatchCallback(Class<?> cls, IComEventCallbackListener iComEventCallbackListener) {
        return new CallbackProxy2(this, cls, iComEventCallbackListener);
    }

    @Override // com.sun.jna.platform.win32.COM.util.ObjectFactory
    public <T> T createObject(final Class<T> cls) {
        return (T) runInComThread(new Callable<T>() { // from class: com.sun.jna.platform.win32.COM.util.Factory.4
            @Override // java.util.concurrent.Callable
            public T call() throws Exception {
                return (T) Factory.super.createObject(cls);
            }
        });
    }

    @Override // com.sun.jna.platform.win32.COM.util.ObjectFactory
    public <T> T createProxy(Class<T> cls, com.sun.jna.platform.win32.COM.IDispatch iDispatch) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new ProxyObject2(super.createProxy(cls, iDispatch)));
    }

    @Override // com.sun.jna.platform.win32.COM.util.ObjectFactory
    public Guid.GUID discoverClsId(final ComObject comObject) {
        return (Guid.GUID) runInComThread(new Callable<Guid.GUID>() { // from class: com.sun.jna.platform.win32.COM.util.Factory.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Guid.GUID call() throws Exception {
                return Factory.super.discoverClsId(comObject);
            }
        });
    }

    @Override // com.sun.jna.platform.win32.COM.util.ObjectFactory
    public <T> T fetchObject(final Class<T> cls) throws COMException {
        return (T) runInComThread(new Callable<T>() { // from class: com.sun.jna.platform.win32.COM.util.Factory.3
            @Override // java.util.concurrent.Callable
            public T call() throws Exception {
                return (T) Factory.super.fetchObject(cls);
            }
        });
    }

    public ComThread getComThread() {
        return this.comThread;
    }

    @Override // com.sun.jna.platform.win32.COM.util.ObjectFactory
    public IRunningObjectTable getRunningObjectTable() {
        return super.getRunningObjectTable();
    }

    public Factory(ComThread comThread) {
        this.comThread = comThread;
    }
}
