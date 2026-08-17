package com.intellij.util;

import com.intellij.diagnostic.PluginException;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Key;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.containers.ConcurrentFactoryMap;
import com.intellij.util.containers.ContainerUtil;
import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class CachedValueStabilityChecker {
    private static final Logger LOG = Logger.getInstance(CachedValueStabilityChecker.class);
    private static final Set<String> ourReportedKeys = ContainerUtil.newConcurrentSet();
    private static final ConcurrentMap<Class<?>, List<Field>> ourFieldCache = ConcurrentFactoryMap.createMap(new Function() { // from class: u91
        @Override // com.intellij.util.Function
        public final Object fun(Object obj) {
            return ReflectionUtil.collectFields((Class) obj);
        }
    });
    private static final boolean DO_CHECKS = shouldDoChecks();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "pluginClass";
        } else {
            objArr[0] = "com/intellij/util/CachedValueStabilityChecker";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/CachedValueStabilityChecker";
        } else {
            objArr[1] = "nonEquivalence";
        }
        if (i != 1) {
            if (i != 2) {
                objArr[2] = "checkFieldEquivalence";
            } else {
                objArr[2] = "complain";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    private static boolean areEqual(Object obj, Object obj2) {
        if (Objects.equals(obj, obj2)) {
            return true;
        }
        if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
            return Arrays.deepEquals((Object[]) obj, (Object[]) obj2);
        }
        if ((obj instanceof Reference) && (obj2 instanceof Reference)) {
            return Objects.equals(((Reference) obj).get(), ((Reference) obj2).get());
        }
        return false;
    }

    private static boolean checkFieldEquivalence(Object obj, Object obj2, String str, int i, Class<?> cls) {
        if (cls == null) {
            $$$reportNull$$$0(0);
        }
        if (i > 100) {
            complain("Too deep function delegation inside CachedValueProvider. If you have cyclic dependencies, please remove them.", str, cls);
            return false;
        }
        for (Field field : ourFieldCache.get(obj.getClass())) {
            try {
                field.setAccessible(true);
                Object obj3 = field.get(obj);
                Object obj4 = field.get(obj2);
                if (!areEqual(obj3, obj4) && (obj3 == null || obj4 == null || !seemConcurrentlyCreatedLambdas(obj3.getClass(), obj4.getClass()))) {
                    if (obj3 == null || obj4 == null || obj3.getClass() != obj4.getClass() || !shouldGoDeeper(obj3)) {
                        complain(nonEquivalence(obj.getClass(), field, obj3, obj4), str, cls);
                        return false;
                    }
                    if (!checkFieldEquivalence(obj3, obj4, str, 1 + i, obj3.getClass())) {
                        return false;
                    }
                }
            } catch (Exception unused) {
                c41.a("Please allow full reflective access");
                return false;
            }
        }
        return true;
    }

    public static void checkProvidersEquivalent(CachedValueProvider<?> cachedValueProvider, CachedValueProvider<?> cachedValueProvider2, Key<?> key) {
        if (cachedValueProvider == cachedValueProvider2 || !DO_CHECKS || ApplicationManagerEx.isInStressTest()) {
            return;
        }
        if (cachedValueProvider.getClass() == cachedValueProvider2.getClass()) {
            checkFieldEquivalence(cachedValueProvider, cachedValueProvider2, key.toString(), 0, cachedValueProvider.getClass());
            return;
        }
        if (seemConcurrentlyCreatedLambdas(cachedValueProvider.getClass(), cachedValueProvider2.getClass())) {
            return;
        }
        complain("Incorrect CachedValue use: different providers supplied for the same key: " + cachedValueProvider + " and " + cachedValueProvider2, key.toString(), cachedValueProvider.getClass());
    }

    private static void complain(String str, String str2, Class<?> cls) {
        if (cls == null) {
            $$$reportNull$$$0(2);
        }
        if (ourReportedKeys.add(str2)) {
            PluginException.logPluginError(LOG, str, (Throwable) null, cls);
        }
    }

    private static String nonEquivalence(Class<?> cls, Field field, Object obj, Object obj2) {
        String str;
        StringBuilder sb = new StringBuilder("Incorrect CachedValue use: same CachedValue with different captured context, this can cause unstable results and invalid PSI access.\nField ");
        sb.append(field.getName());
        sb.append(" in ");
        sb.append(cls);
        sb.append(" has non-equivalent values:\n  ");
        sb.append(obj);
        String str2 = "";
        if (obj == null) {
            str = "";
        } else {
            str = " (" + obj.getClass().getName() + ")";
        }
        sb.append(str);
        sb.append(" and\n  ");
        sb.append(obj2);
        if (obj2 != null) {
            str2 = " (" + obj2.getClass().getName() + ")";
        }
        sb.append(str2);
        sb.append("\nEither make `equals()` hold for these values, or avoid this dependency, e.g. by extracting CachedValueProvider into a static method.");
        return sb.toString();
    }

    private static boolean seemConcurrentlyCreatedLambdas(Class<?> cls, Class<?> cls2) {
        if (cls == cls2) {
            return false;
        }
        String name = cls.getName();
        String name2 = cls2.getName();
        int iIndexOf = name.indexOf("$$Lambda");
        if (iIndexOf > 0 && iIndexOf == name2.indexOf("$$Lambda") && name2.startsWith(name.substring(0, iIndexOf))) {
            ConcurrentMap<Class<?>, List<Field>> concurrentMap = ourFieldCache;
            if (concurrentMap.get(cls).size() == concurrentMap.get(cls2).size()) {
                return true;
            }
        }
        return false;
    }

    private static boolean shouldDoChecks() {
        Application application = ApplicationManager.getApplication();
        return application.isUnitTestMode() || application.isInternal() || application.isEAP();
    }

    private static boolean shouldGoDeeper(Object obj) {
        if (obj instanceof CachedValueProvider) {
            return true;
        }
        Class<?> cls = obj.getClass();
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null) {
            return false;
        }
        if (((obj instanceof kotlin.Function) || (obj instanceof Supplier) || (obj instanceof java.util.function.Function)) && Object.class.equals(cls.getSuperclass())) {
            return true;
        }
        return "kotlin.jvm.internal.Lambda".equals(superclass.getName());
    }
}
