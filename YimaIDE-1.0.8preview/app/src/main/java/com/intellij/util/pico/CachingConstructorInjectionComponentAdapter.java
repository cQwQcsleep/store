package com.intellij.util.pico;

import androidx.collection.ScatterMapKt;
import com.intellij.util.ExceptionUtilRt;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Set;
import org.picocontainer.ComponentAdapter;
import org.picocontainer.PicoContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Deprecated
final class CachingConstructorInjectionComponentAdapter implements ComponentAdapter {
    private static final ThreadLocal<Set<Class<?>>> ourGuard = new ThreadLocal<>();
    private final Class<?> componentImplementation;
    private PicoContainer container;
    private final Object key;
    private Object myInstance;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 8 || i == 9 || i == 12 || i == 13) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 8 || i == 9 || i == 12 || i == 13) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "key";
                break;
            case 2:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 11:
                objArr[0] = "componentImplementation";
                break;
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 10:
            default:
                objArr[0] = "container";
                break;
            case 5:
            case 8:
            case 9:
            case 12:
            case 13:
                objArr[0] = "com/intellij/util/pico/CachingConstructorInjectionComponentAdapter";
                break;
            case 14:
                objArr[0] = "constructor";
                break;
        }
        if (i == 5) {
            objArr[1] = "instantiateGuarded";
        } else if (i == 8 || i == 9) {
            objArr[1] = "doGetComponentInstance";
        } else if (i == 12 || i == 13) {
            objArr[1] = "getGreediestSatisfiableConstructor";
        } else {
            objArr[1] = "com/intellij/util/pico/CachingConstructorInjectionComponentAdapter";
        }
        switch (i) {
            case 3:
            case 4:
                objArr[2] = "instantiateGuarded";
                break;
            case 5:
            case 8:
            case 9:
            case 12:
            case 13:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "doGetComponentInstance";
                break;
            case 10:
            case 11:
                objArr[2] = "getGreediestSatisfiableConstructor";
                break;
            case 14:
                objArr[2] = "isNonInjectable";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 8 && i != 9 && i != 12 && i != 13) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public CachingConstructorInjectionComponentAdapter(PicoContainer picoContainer, Object obj, Class<?> cls) {
        if (picoContainer == null) {
            $$$reportNull$$$0(0);
        }
        if (obj == null) {
            $$$reportNull$$$0(1);
        }
        if (cls == null) {
            $$$reportNull$$$0(2);
        }
        this.container = picoContainer;
        this.key = obj;
        this.componentImplementation = cls;
    }

    public static /* synthetic */ int a(Constructor constructor, Constructor constructor2) {
        return constructor2.getParameterCount() - constructor.getParameterCount();
    }

    private static Object doGetComponentInstance(ComponentAdapter componentAdapter, DefaultPicoContainer defaultPicoContainer, Class<?> cls) {
        if (defaultPicoContainer == null) {
            $$$reportNull$$$0(6);
        }
        if (cls == null) {
            $$$reportNull$$$0(7);
        }
        try {
            Constructor<?> greediestSatisfiableConstructor = getGreediestSatisfiableConstructor(componentAdapter, defaultPicoContainer, cls);
            try {
                greediestSatisfiableConstructor.setAccessible(true);
                if (greediestSatisfiableConstructor.getParameterCount() == 0) {
                    Object objNewInstance = greediestSatisfiableConstructor.newInstance(null);
                    if (objNewInstance == null) {
                        $$$reportNull$$$0(8);
                    }
                    return objNewInstance;
                }
                Class<?>[] parameterTypes = greediestSatisfiableConstructor.getParameterTypes();
                Object[] objArr = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    ComponentAdapter componentAdapterResolveAdapter = ComponentParameter.resolveAdapter(defaultPicoContainer, componentAdapter, parameterTypes[i]);
                    if (componentAdapterResolveAdapter != null) {
                        objArr[i] = defaultPicoContainer.getComponentInstance(componentAdapterResolveAdapter.getComponentKey());
                    }
                }
                Object objNewInstance2 = greediestSatisfiableConstructor.newInstance(objArr);
                if (objNewInstance2 == null) {
                    $$$reportNull$$$0(9);
                }
                return objNewInstance2;
            } catch (IllegalAccessException e) {
                throw new PicoInitializationException(e);
            } catch (InstantiationException unused) {
                throw new PicoInitializationException("Should never get here");
            } catch (InvocationTargetException e2) {
                ExceptionUtilRt.rethrowUnchecked(e2.getTargetException());
                Throwable targetException = e2.getTargetException();
                throw new PicoInitializationException("InvocationTargetException: " + targetException.getClass().getName() + " " + targetException.getMessage(), targetException);
            }
        } catch (AmbiguousComponentResolutionException e3) {
            e3.setComponent(cls);
            throw e3;
        }
    }

    private static Constructor<?> getGreediestSatisfiableConstructor(ComponentAdapter componentAdapter, DefaultPicoContainer defaultPicoContainer, Class<?> cls) throws PicoIntrospectionException {
        Class<?> cls2;
        boolean z;
        if (defaultPicoContainer == null) {
            $$$reportNull$$$0(10);
        }
        if (cls == null) {
            $$$reportNull$$$0(11);
        }
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Arrays.sort(declaredConstructors, new Comparator() { // from class: com.intellij.util.pico.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return CachingConstructorInjectionComponentAdapter.a((Constructor) obj, (Constructor) obj2);
            }
        });
        Constructor<?> constructor = null;
        int length = -1;
        Class<?> cls3 = null;
        for (Constructor<?> constructor2 : declaredConstructors) {
            if (!constructor2.isSynthetic() && !isNonInjectable(constructor2)) {
                Class<?>[] parameterTypes = constructor2.getParameterTypes();
                int length2 = parameterTypes.length;
                int i = 0;
                while (true) {
                    if (i >= length2) {
                        cls2 = cls3;
                        z = false;
                        break;
                    }
                    cls2 = parameterTypes[i];
                    if (ComponentParameter.resolveAdapter(defaultPicoContainer, componentAdapter, cls2) == null) {
                        hashSet2.add(parameterTypes);
                        z = true;
                        break;
                    }
                    i++;
                }
                if (constructor != null && parameterTypes.length != length) {
                    if (hashSet.isEmpty()) {
                        return constructor;
                    }
                    hashSet.add(constructor2);
                } else if (!z && length == parameterTypes.length) {
                    hashSet.add(constructor2);
                    hashSet.add(constructor);
                } else if (!z) {
                    length = parameterTypes.length;
                    constructor = constructor2;
                }
                cls3 = cls2;
            }
        }
        if (!hashSet.isEmpty()) {
            throw new TooManySatisfiableConstructorsException(hashSet);
        }
        if (constructor != null || hashSet2.isEmpty()) {
            if (constructor != null) {
                return constructor;
            }
            throw new PicoInitializationException("Either do the specified parameters not match any of the following constructors: " + new HashSet(Arrays.asList(cls.getDeclaredConstructors())) + " or the constructors were not accessible for '" + cls + "'");
        }
        throw new PicoIntrospectionException(cls.getName() + " has unsatisfied dependency: " + cls3 + " among unsatisfiable dependencies: " + hashSet2 + " where " + defaultPicoContainer + " was the leaf container being asked for dependencies.");
    }

    public static Object instantiateGuarded(CachingConstructorInjectionComponentAdapter cachingConstructorInjectionComponentAdapter, PicoContainer picoContainer, Class<?> cls) {
        if (picoContainer == null) {
            $$$reportNull$$$0(3);
        }
        if (cls == null) {
            $$$reportNull$$$0(4);
        }
        ThreadLocal<Set<Class<?>>> threadLocal = ourGuard;
        Set<Class<?>> setNewSetFromMap = threadLocal.get();
        if (setNewSetFromMap == null) {
            setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(1));
            threadLocal.set(setNewSetFromMap);
        }
        try {
            if (!setNewSetFromMap.add(cls)) {
                throw new CyclicDependencyException(cls);
            }
            try {
                Object objDoGetComponentInstance = doGetComponentInstance(cachingConstructorInjectionComponentAdapter, (DefaultPicoContainer) picoContainer, cls);
                setNewSetFromMap.remove(cls);
                if (objDoGetComponentInstance == null) {
                    $$$reportNull$$$0(5);
                }
                return objDoGetComponentInstance;
            } catch (CyclicDependencyException e) {
                e.push(cls);
                throw e;
            }
        } catch (Throwable th) {
            setNewSetFromMap.remove(cls);
            throw th;
        }
    }

    private static boolean isNonInjectable(Constructor<?> constructor) {
        if (constructor == null) {
            $$$reportNull$$$0(14);
        }
        for (Annotation annotation : constructor.getAnnotations()) {
            String name = annotation.annotationType().getName();
            if ("com.intellij.serviceContainer.NonInjectable".equals(name) || "java.lang.Deprecated".equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.picocontainer.ComponentAdapter
    public Class<?> getComponentImplementation() {
        return this.componentImplementation;
    }

    @Override // org.picocontainer.ComponentAdapter
    public Object getComponentInstance() {
        Object obj = this.myInstance;
        if (obj != null) {
            return obj;
        }
        Object objInstantiateGuarded = instantiateGuarded(this, this.container, getComponentImplementation());
        this.myInstance = objInstantiateGuarded;
        return objInstantiateGuarded;
    }

    @Override // org.picocontainer.ComponentAdapter
    public Object getComponentKey() {
        return this.key;
    }

    public String toString() {
        return CachingConstructorInjectionComponentAdapter.class.getName() + "[" + this.key + "]";
    }
}
