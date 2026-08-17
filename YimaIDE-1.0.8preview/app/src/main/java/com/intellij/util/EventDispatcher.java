package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.util.EventDispatcher;
import com.intellij.util.containers.DisposableWrapperList;
import com.intellij.util.lang.CompoundRuntimeException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class EventDispatcher<T extends EventListener> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = Logger.getInstance(EventDispatcher.class);
    private final Class<T> myListenerClass;
    private final DisposableWrapperList<T> myListeners;
    private final Map<String, Object> myMethodReturnValues;
    private T myMulticaster;

    /* JADX WARN: Code duplicated, block: B:28:0x0052  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 10 || i == 11 || i == 20) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 10 || i == 11 || i == 20) ? 2 : 3];
        if (i == 2 || i == 3) {
            objArr[0] = "methodReturnValues";
        } else if (i != 7) {
            switch (i) {
                case 9:
                case 12:
                    objArr[0] = "listeners";
                    break;
                case 10:
                case 11:
                case 20:
                    objArr[0] = "com/intellij/util/EventDispatcher";
                    break;
                case 13:
                    objArr[0] = "method";
                    break;
                case 14:
                    objArr[0] = "e";
                    break;
                case 15:
                    objArr[0] = "exceptions";
                    break;
                case 16:
                case 17:
                case 19:
                    objArr[0] = "listener";
                    break;
                case 18:
                    objArr[0] = "parentDisposable";
                    break;
                case 21:
                    objArr[0] = "disposable";
                    break;
                default:
                    objArr[0] = "listenerClass";
                    break;
            }
        } else {
            objArr[0] = "listeners";
        }
        if (i == 10) {
            objArr[1] = "createMulticaster";
        } else if (i == 11) {
            objArr[1] = "getMulticaster";
        } else if (i != 20) {
            objArr[1] = "com/intellij/util/EventDispatcher";
        } else {
            objArr[1] = "getListeners";
        }
        switch (i) {
            case 3:
            case 4:
                objArr[2] = "assertNonVoidMethodReturnValuesAreDeclared";
                break;
            case 5:
                objArr[2] = "<init>";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
                objArr[2] = "createMulticaster";
                break;
            case 10:
            case 11:
            case 20:
                break;
            case 12:
            case 13:
                objArr[2] = "dispatchVoidMethod";
                break;
            case 14:
                objArr[2] = "handleException";
                break;
            case 15:
                objArr[2] = "throwExceptions";
                break;
            case 16:
            case 17:
            case 18:
                objArr[2] = "addListener";
                break;
            case 19:
                objArr[2] = "removeListener";
                break;
            case 21:
                objArr[2] = "neuterMultiCasterWhilePerformanceTestIsRunningUntil";
                break;
            default:
                objArr[2] = "create";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 10 && i != 11 && i != 20) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private EventDispatcher(Class<T> cls, Map<String, Object> map) {
        if (cls == null) {
            $$$reportNull$$$0(5);
        }
        this.myListeners = new DisposableWrapperList<>();
        this.myListenerClass = cls;
        this.myMethodReturnValues = map;
    }

    public static /* synthetic */ Object b(Map map, Supplier supplier, Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        if (method.getDeclaringClass().getName().equals("java.lang.Object")) {
            return handleObjectMethod(obj, objArr, name);
        }
        if (map != null && map.containsKey(name)) {
            return map.get(name);
        }
        dispatchVoidMethod((Iterable) supplier.get(), method, objArr);
        return null;
    }

    public static <T extends EventListener> EventDispatcher<T> create(Class<T> cls) {
        if (cls == null) {
            $$$reportNull$$$0(0);
        }
        return new EventDispatcher<>(cls, null);
    }

    public static <T> T createMulticaster(Class<T> cls, final Map<String, Object> map, final Supplier<? extends Iterable<T>> supplier) {
        if (cls == null) {
            $$$reportNull$$$0(8);
        }
        if (supplier == null) {
            $$$reportNull$$$0(9);
        }
        LOG.assertTrue(cls.isInterface(), "listenerClass must be an interface: ".concat(cls.getName()));
        T t = (T) ReflectionUtil.proxy(cls, new InvocationHandler() { // from class: zc4
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                return EventDispatcher.b(map, supplier, obj, method, objArr);
            }
        });
        if (t == null) {
            $$$reportNull$$$0(10);
        }
        return t;
    }

    private static <T> void dispatchVoidMethod(Iterable<? extends T> iterable, Method method, Object[] objArr) {
        if (iterable == null) {
            $$$reportNull$$$0(12);
        }
        if (method == null) {
            $$$reportNull$$$0(13);
        }
        method.setAccessible(true);
        Iterator<? extends T> it = iterable.iterator();
        List<Throwable> listHandleException = null;
        while (it.hasNext()) {
            try {
                method.invoke(it.next(), objArr);
            } catch (Throwable th) {
                listHandleException = handleException(th, listHandleException);
            }
        }
        if (listHandleException != null) {
            throwExceptions(listHandleException);
        }
    }

    public static List<Throwable> handleException(Throwable th, List<Throwable> list) {
        Throwable cause;
        if (th == null) {
            $$$reportNull$$$0(14);
        }
        if ((th instanceof InvocationTargetException) && (cause = th.getCause()) != null) {
            if (cause instanceof AbstractMethodError) {
                return list;
            }
            th = cause;
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(th);
        return list;
    }

    public static Object handleObjectMethod(Object obj, Object[] objArr, String str) {
        str.getClass();
        switch (str) {
            case "toString":
                return "Multicaster";
            case "equals":
                return obj == objArr[0] ? Boolean.TRUE : Boolean.FALSE;
            case "hashCode":
                return Integer.valueOf(System.identityHashCode(obj));
            default:
                LOG.error("Incorrect Object's method invoked for proxy:".concat(str));
                return null;
        }
    }

    private static boolean isEventDispatcherErrorPropagationEnabled() {
        return Boolean.parseBoolean(System.getProperty("ijpl.event.dispatcher.rethrows.errors.from.listeners", "false"));
    }

    private static void throwExceptions(List<? extends Throwable> list) {
        if (list == null) {
            $$$reportNull$$$0(15);
        }
        if (!isEventDispatcherErrorPropagationEnabled()) {
            for (Throwable th : list) {
                if (!(th instanceof CancellationException)) {
                    LOG.error(th);
                }
            }
            return;
        }
        if (list.size() == 1) {
            ExceptionUtil.rethrow(list.get(0));
            return;
        }
        for (Throwable th2 : list) {
            if (th2 instanceof ProcessCanceledException) {
                throw ((ProcessCanceledException) th2);
            }
        }
        throw new CompoundRuntimeException(list);
    }

    public void addListener(T t) {
        if (t == null) {
            $$$reportNull$$$0(16);
        }
        this.myListeners.add(t);
    }

    public T getMulticaster() {
        T t = this.myMulticaster;
        if (t == null) {
            t = (T) createMulticaster(this.myListenerClass, this.myMethodReturnValues, new Supplier() { // from class: yc4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.b.myListeners;
                }
            });
            this.myMulticaster = t;
        }
        if (t == null) {
            $$$reportNull$$$0(11);
        }
        return t;
    }

    public void removeListener(T t) {
        if (t == null) {
            $$$reportNull$$$0(19);
        }
        this.myListeners.remove(t);
    }
}
