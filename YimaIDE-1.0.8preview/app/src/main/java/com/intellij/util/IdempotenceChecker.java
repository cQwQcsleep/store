package com.intellij.util;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.diagnostic.PluginException;
import com.intellij.model.Symbol;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.RecursionGuard;
import com.intellij.openapi.util.RecursionManager;
import com.intellij.openapi.util.Trinity;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.util.registry.RegistryValue;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.ResolveResult;
import com.intellij.util.Function;
import com.intellij.util.IdempotenceChecker;
import com.intellij.util.concurrency.SynchronizedClearableLazy;
import com.intellij.util.containers.ConcurrentFactoryMap;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.JBIterable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class IdempotenceChecker {
    private static final Logger LOG = Logger.getInstance(IdempotenceChecker.class);
    private static final Set<Class<?>> ourReportedValueClasses = Collections.synchronizedSet(new HashSet());
    private static final ThreadLocal<AtomicInteger> ourRandomCheckNesting = ThreadLocal.withInitial(new Supplier() { // from class: jj6
        @Override // java.util.function.Supplier
        public final Object get() {
            return IdempotenceChecker.b();
        }
    });
    private static final ThreadLocal<List<String>> ourLog = new ThreadLocal<>();
    private static final Supplier<RegistryValue> rateCheckProperty = new SynchronizedClearableLazy(new Function0() { // from class: kj6
        public final Object invoke() {
            return Registry.get("platform.random.idempotence.check.rate");
        }
    });
    private static final Map<Class, Set<Class>> allSupersWithEquals = ConcurrentFactoryMap.createMap(new Function() { // from class: lj6
        @Override // com.intellij.util.Function
        public final Object fun(Object obj) {
            return JBIterable.generate((Class) obj, new Function() { // from class: mj6
                @Override // com.intellij.util.Function
                public final Object fun(Object obj2) {
                    return ((Class) obj2).getSuperclass();
                }
            }).filter(new Condition() { // from class: nj6
                public final boolean value(Object obj2) {
                    return IdempotenceChecker.c((Class) obj2);
                }
            }).toSet();
        }
    });

    public static final class ResultWithLog<T> {
        private final List<String> log;
        private final T result;

        private ResultWithLog(T t, List<String> list) {
            this.result = t;
            this.log = list;
        }

        public static /* synthetic */ String a(String str) {
            return "  " + str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ResultWithLog) {
                return Arrays.deepEquals(new Object[]{this.result}, new Object[]{((ResultWithLog) obj).result});
            }
            return false;
        }

        public T getResult() {
            return this.result;
        }

        public int hashCode() {
            return Objects.hash(this.result);
        }

        public String printLog() {
            return StringUtil.join(this.log, new Function() { // from class: oj6
                @Override // com.intellij.util.Function
                public final Object fun(Object obj) {
                    return IdempotenceChecker.ResultWithLog.a((String) obj);
                }
            }, "\n");
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("ResultWithLog{");
            sb.append(this.result);
            if (this.log.isEmpty()) {
                str = "";
            } else {
                str = ", log='\n" + printLog() + '\'';
            }
            sb.append(str);
            sb.append('}');
            return sb.toString();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 6 || i == 28) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 6 || i == 28) ? 2 : 3];
        switch (i) {
            case 3:
            case 5:
            case 32:
                objArr[0] = "recomputeValue";
                break;
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 28:
                objArr[0] = "com/intellij/util/IdempotenceChecker";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 12:
                objArr[0] = "o";
                break;
            case 9:
                objArr[0] = "field";
                break;
            case 10:
            case 13:
            case 15:
            case 16:
            case 18:
            case 21:
                objArr[0] = "existing";
                break;
            case 11:
            case 14:
            case 17:
            case 19:
            case 22:
                objArr[0] = "fresh";
                break;
            case 20:
                objArr[0] = "psi";
                break;
            case 23:
                objArr[0] = "original1";
                break;
            case 24:
                objArr[0] = "a1";
                break;
            case 25:
                objArr[0] = "a2";
                break;
            case 26:
            case 33:
                objArr[0] = "message";
                break;
            case 27:
                objArr[0] = "detail";
                break;
            case 29:
                objArr[0] = "parentDisposable";
                break;
            case 30:
                objArr[0] = "data";
                break;
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[0] = "provider";
                break;
            default:
                objArr[0] = "providerClass";
                break;
        }
        if (i == 4) {
            objArr[1] = "recomputeWithLogging";
        } else if (i == 6) {
            objArr[1] = "computeWithLogging";
        } else if (i != 28) {
            objArr[1] = "com/intellij/util/IdempotenceChecker";
        } else {
            objArr[1] = "appendDetail";
        }
        switch (i) {
            case 2:
                objArr[2] = "reportFailure";
                break;
            case 3:
                objArr[2] = "recomputeWithLogging";
                break;
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 28:
                break;
            case 5:
                objArr[2] = "computeWithLogging";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "isOrderedMap";
                break;
            case 8:
                objArr[2] = "isOrderedSet";
                break;
            case 9:
            case 10:
            case 11:
                objArr[2] = "whichIsField";
                break;
            case 12:
                objArr[2] = "asArray";
                break;
            case 13:
            case 14:
                objArr[2] = "checkCachedValueData";
                break;
            case 15:
                objArr[2] = "isExpectedToHaveSaneEquals";
                break;
            case 16:
            case 17:
                objArr[2] = "objectsOfDifferentClassesCanStillBeEquivalent";
                break;
            case 18:
            case 19:
                objArr[2] = "checkPsiEquivalence";
                break;
            case 20:
                objArr[2] = "seemsToBeResolveTarget";
                break;
            case 21:
            case 22:
                objArr[2] = "checkCollectionElements";
                break;
            case 23:
            case 24:
            case 25:
                objArr[2] = "checkArrayEquivalence";
                break;
            case 26:
            case 27:
                objArr[2] = "appendDetail";
                break;
            case 29:
                objArr[2] = "disableRandomChecksUntil";
                break;
            case 30:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 32:
                objArr[2] = "applyForRandomCheck";
                break;
            case 33:
                objArr[2] = "logTrace";
                break;
            default:
                objArr[2] = "checkEquivalence";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 6 && i != 28) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private static String appendDetail(String str, String str2) {
        if (str == null) {
            $$$reportNull$$$0(26);
        }
        if (str2 == null) {
            $$$reportNull$$$0(27);
        }
        return str + "\n  " + StringUtil.trimLog(str2, 10000);
    }

    public static <T> void applyForRandomCheck(T t, Object obj, Computable<? extends T> computable) {
        if (t == null) {
            $$$reportNull$$$0(30);
        }
        if (obj == null) {
            $$$reportNull$$$0(31);
        }
        if (computable == null) {
            $$$reportNull$$$0(32);
        }
        if (areRandomChecksEnabled() && shouldPerformRandomCheck()) {
            RecursionGuard.StackStamp stackStampMarkStack = RecursionManager.markStack();
            AtomicInteger atomicInteger = ourRandomCheckNesting.get();
            atomicInteger.incrementAndGet();
            try {
                Object objCompute = computable.compute();
                if (stackStampMarkStack.mayCacheNow()) {
                    checkEquivalence(t, objCompute, obj.getClass(), computable);
                }
            } finally {
                atomicInteger.decrementAndGet();
            }
        }
    }

    public static boolean areRandomChecksEnabled() {
        return ApplicationManager.getApplication().isUnitTestMode() && !ApplicationManagerEx.isInStressTest();
    }

    private static Object[] asArray(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(12);
        }
        if (obj instanceof Object[]) {
            return (Object[]) obj;
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return new Object[]{entry.getKey(), entry.getValue()};
        }
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            return new Object[]{pair.first, pair.second};
        }
        if (!(obj instanceof Trinity)) {
            return null;
        }
        Trinity trinity = (Trinity) obj;
        return new Object[]{trinity.first, trinity.second, trinity.third};
    }

    public static /* synthetic */ AtomicInteger b() {
        return new AtomicInteger();
    }

    public static /* synthetic */ boolean c(Class cls) {
        return (cls == Object.class || ReflectionUtil.getDeclaredMethod(cls, "equals", Object.class) == null) ? false : true;
    }

    private static String checkArrayEquivalence(Object[] objArr, Object[] objArr2, Object obj) {
        String str;
        if (obj == null) {
            $$$reportNull$$$0(23);
        }
        if (objArr == null) {
            $$$reportNull$$$0(24);
        }
        if (objArr2 == null) {
            $$$reportNull$$$0(25);
        }
        int length = objArr.length;
        int length2 = objArr2.length;
        if (length != length2) {
            return appendDetail(reportProblem(Integer.valueOf(length), Integer.valueOf(length2)), "which is length of " + Arrays.toString(objArr) + " and " + Arrays.toString(objArr2));
        }
        int i = 0;
        while (i < length) {
            String strCheckValueEquivalence = checkValueEquivalence(objArr[i], objArr2[i]);
            if (strCheckValueEquivalence != null) {
                if (obj instanceof Map.Entry) {
                    str = i == 0 ? "key" : "value";
                } else {
                    str = i + "th element";
                }
                return whichIsField(str, Arrays.toString(objArr), Arrays.toString(objArr2), strCheckValueEquivalence);
            }
            i++;
        }
        return null;
    }

    private static String checkCachedValueData(CachedValueBase.Data<?> data, CachedValueBase.Data<?> data2) {
        if (data == null) {
            $$$reportNull$$$0(13);
        }
        if (data2 == null) {
            $$$reportNull$$$0(14);
        }
        Object[] dependencies = data.getDependencies();
        Object[] dependencies2 = data2.getDependencies();
        Object obj = data.get();
        Object obj2 = data2.get();
        if (dependencies.length == dependencies2.length) {
            return checkValueEquivalence(obj, obj2);
        }
        return appendDetail(appendDetail(reportProblem(Integer.valueOf(dependencies.length), Integer.valueOf(dependencies2.length)), "which is length of CachedValue dependencies: " + Arrays.toString(dependencies) + " and " + Arrays.toString(dependencies2)), "where values are  " + objAndClass(obj) + " and " + objAndClass(obj2));
    }

    private static String checkClassEquivalence(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return reportProblem(obj, obj2);
        }
        Class<?> cls = obj.getClass();
        Class<?> cls2 = obj2.getClass();
        if (cls == cls2 || objectsOfDifferentClassesCanStillBeEquivalent(obj, obj2)) {
            return null;
        }
        return whichIsField("class", obj, obj2, reportProblem(cls, cls2));
    }

    private static String checkCollectionElements(Collection<?> collection, Collection<?> collection2) {
        if (collection == null) {
            $$$reportNull$$$0(21);
        }
        if (collection2 == null) {
            $$$reportNull$$$0(22);
        }
        if (collection2.isEmpty()) {
            return null;
        }
        return checkArrayEquivalence(collection.toArray(), collection2.toArray(), collection);
    }

    private static String checkCollectionSizes(int i, int i2) {
        if (i2 == 0 || i == i2) {
            return null;
        }
        return reportProblem(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static <T> void checkEquivalence(T t, T t2, Class<?> cls, Computable<? extends T> computable, Supplier<String> supplier) {
        String str;
        if (cls == null) {
            $$$reportNull$$$0(1);
        }
        String strCheckValueEquivalence = checkValueEquivalence(t, t2);
        if (strCheckValueEquivalence != null) {
            if (supplier == null) {
                str = "";
            } else {
                str = "\n" + supplier.get();
            }
            reportFailure(t, t2, cls, computable, strCheckValueEquivalence.concat(str));
        }
    }

    private static String checkPsiEquivalence(PsiElement psiElement, PsiElement psiElement2) {
        if (psiElement == null) {
            $$$reportNull$$$0(18);
        }
        if (psiElement2 == null) {
            $$$reportNull$$$0(19);
        }
        if (psiElement.equals(psiElement2) || psiElement.isEquivalentTo(psiElement2) || psiElement2.isEquivalentTo(psiElement)) {
            return null;
        }
        if (seemsToBeResolveTarget(psiElement) || seemsToBeResolveTarget(psiElement2)) {
            return reportProblem(psiElement, psiElement2);
        }
        return null;
    }

    private static String checkValueEquivalence(Object obj, Object obj2) {
        PsiElement element;
        PsiElement element2;
        if (obj == obj2) {
            return null;
        }
        String strCheckClassEquivalence = checkClassEquivalence(obj, obj2);
        if (strCheckClassEquivalence != null) {
            return strCheckClassEquivalence;
        }
        Object[] objArrAsArray = asArray(obj);
        if (objArrAsArray != null) {
            Object[] objArrAsArray2 = asArray(obj2);
            Objects.requireNonNull(objArrAsArray2);
            return checkArrayEquivalence(objArrAsArray, objArrAsArray2, obj);
        }
        if (obj instanceof ResultWithLog) {
            return whichIsField("result", obj, obj2, checkValueEquivalence(((ResultWithLog) obj).getResult(), ((ResultWithLog) obj2).getResult()));
        }
        if (obj instanceof CachedValueBase.Data) {
            return checkCachedValueData((CachedValueBase.Data) obj, (CachedValueBase.Data) obj2);
        }
        if ((obj instanceof List) || isOrderedSet(obj)) {
            return checkCollectionElements((Collection) obj, (Collection) obj2);
        }
        if (isOrderedMap(obj)) {
            return checkCollectionElements(((Map) obj).entrySet(), ((Map) obj2).entrySet());
        }
        if (obj instanceof Set) {
            return whichIsField("size", obj, obj2, checkCollectionSizes(((Set) obj).size(), ((Set) obj2).size()));
        }
        if (obj instanceof Map) {
            if (obj instanceof ConcurrentMap) {
                return null;
            }
            return whichIsField("size", obj, obj2, checkCollectionSizes(((Map) obj).size(), ((Map) obj2).size()));
        }
        if (isExpectedToHaveSaneEquals(obj) && !obj.equals(obj2)) {
            return reportProblem(obj, obj2);
        }
        if (obj instanceof PsiNamedElement) {
            return checkPsiEquivalence((PsiElement) obj, (PsiElement) obj2);
        }
        if (!(obj instanceof ResolveResult) || (element = ((ResolveResult) obj).getElement()) == (element2 = ((ResolveResult) obj2).getElement())) {
            return null;
        }
        String strCheckClassEquivalence2 = checkClassEquivalence(element, element2);
        if (strCheckClassEquivalence2 == null) {
            strCheckClassEquivalence2 = checkPsiEquivalence(element, element2);
        }
        return whichIsField("element", obj, obj2, strCheckClassEquivalence2);
    }

    public static <T> ResultWithLog<T> computeWithLogging(Computable<? extends T> computable) {
        if (computable == null) {
            $$$reportNull$$$0(5);
        }
        ThreadLocal<List<String>> threadLocal = ourLog;
        List<String> arrayList = threadLocal.get();
        boolean z = arrayList == null;
        if (z) {
            arrayList = new ArrayList<>();
            threadLocal.set(arrayList);
        }
        try {
            ResultWithLog<T> resultWithLog = new ResultWithLog<>(computable.compute(), new ArrayList(arrayList.subList(arrayList.size(), arrayList.size())));
            if (z) {
            }
            return resultWithLog;
        } finally {
            if (z) {
                ourLog.remove();
            }
        }
    }

    private static boolean isExpectedToHaveSaneEquals(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(15);
        }
        return (obj instanceof Comparable) || (obj instanceof Symbol);
    }

    public static boolean isLoggingEnabled() {
        return ourLog.get() != null;
    }

    private static boolean isOrderedMap(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(7);
        }
        return (obj instanceof LinkedHashMap) || (obj instanceof SortedMap);
    }

    private static boolean isOrderedSet(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(8);
        }
        return (obj instanceof LinkedHashSet) || (obj instanceof SortedSet);
    }

    public static void logTrace(String str) {
        if (str == null) {
            $$$reportNull$$$0(33);
        }
        List<String> list = ourLog.get();
        if (list != null) {
            list.add(str);
        }
    }

    private static String objAndClass(Object obj) {
        Object obj2;
        if (obj == null) {
            return "null";
        }
        String string = obj instanceof Object[] ? Arrays.toString((Object[]) obj) : obj.toString();
        if (string.contains(obj.getClass().getSimpleName()) || (obj instanceof String) || (obj instanceof Number) || (obj instanceof Class)) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" (");
        if (obj.getClass().isArray()) {
            obj2 = obj.getClass().getComponentType() + "[]";
        } else {
            obj2 = obj.getClass();
        }
        sb.append(obj2);
        sb.append(")");
        return sb.toString();
    }

    private static boolean objectsOfDifferentClassesCanStillBeEquivalent(Object obj, Object obj2) {
        if (obj == null) {
            $$$reportNull$$$0(16);
        }
        if (obj2 == null) {
            $$$reportNull$$$0(17);
        }
        if ((obj instanceof Map) && (obj2 instanceof Map) && isOrderedMap(obj) == isOrderedMap(obj2)) {
            return true;
        }
        if ((obj instanceof Set) && (obj2 instanceof Set) && isOrderedSet(obj) == isOrderedSet(obj2)) {
            return true;
        }
        if ((obj instanceof List) && (obj2 instanceof List)) {
            return true;
        }
        if ((obj instanceof PsiNamedElement) && (obj2 instanceof PsiNamedElement)) {
            return true;
        }
        Map<Class, Set<Class>> map = allSupersWithEquals;
        return ContainerUtil.intersects(map.get(obj.getClass()), map.get(obj2.getClass()));
    }

    private static <T> String recomputeWithLogging(T t, T t2, Computable<? extends T> computable) {
        String strConcat;
        if (computable == null) {
            $$$reportNull$$$0(3);
        }
        ResultWithLog resultWithLogComputeWithLogging = computeWithLogging(computable);
        Object obj = resultWithLogComputeWithLogging.result;
        String str = "\n\nRecomputation gives " + objAndClass(obj);
        if (checkValueEquivalence(t, obj) == null) {
            strConcat = str.concat(" which is equivalent to 'existing'");
        } else {
            strConcat = checkValueEquivalence(t2, obj) == null ? str.concat(" which is equivalent to 'fresh'") : str.concat(" which is different from both values");
        }
        if (resultWithLogComputeWithLogging.log.isEmpty() || (obj instanceof ResultWithLog)) {
            return strConcat;
        }
        return strConcat + "\nRecomputation log:\n" + resultWithLogComputeWithLogging.printLog();
    }

    private static <T> void reportFailure(T t, T t2, Class<?> cls, Computable<? extends T> computable, String str) {
        if (cls == null) {
            $$$reportNull$$$0(2);
        }
        if ((ApplicationManager.getApplication().isUnitTestMode() || ourReportedValueClasses.add(cls)) && !"true".equals(System.getProperty("idea.disable.idempotence.checker", "false"))) {
            if (computable != null) {
                str = str + recomputeWithLogging(t, t2, computable);
            }
            LOG.error(PluginException.createByClass(str, (Throwable) null, cls));
        }
    }

    private static String reportProblem(Object obj, Object obj2) {
        return appendDetail("Non-idempotent computation: it returns different results when invoked multiple times or on different threads:", objAndClass(obj) + " != " + objAndClass(obj2));
    }

    private static boolean seemsToBeResolveTarget(PsiElement psiElement) {
        if (psiElement == null) {
            $$$reportNull$$$0(20);
        }
        if (psiElement.isPhysical()) {
            return true;
        }
        PsiElement navigationElement = psiElement.getNavigationElement();
        return navigationElement != null && navigationElement.isPhysical();
    }

    private static boolean shouldPerformRandomCheck() {
        int iAsInteger = rateCheckProperty.get().asInteger();
        return iAsInteger > 0 && ThreadLocalRandom.current().nextInt(iAsInteger) == 0 && !ApplicationManagerEx.isInStressTest();
    }

    private static String whichIsField(String str, Object obj, Object obj2, String str2) {
        if (str == null) {
            $$$reportNull$$$0(9);
        }
        if (obj == null) {
            $$$reportNull$$$0(10);
        }
        if (obj2 == null) {
            $$$reportNull$$$0(11);
        }
        if (str2 == null) {
            return null;
        }
        return appendDetail(str2, "which is `." + str + "` of " + obj + " and " + obj2);
    }

    public static <T> void checkEquivalence(T t, T t2, Class<?> cls, Computable<? extends T> computable) {
        if (cls == null) {
            $$$reportNull$$$0(0);
        }
        checkEquivalence(t, t2, cls, computable, null);
    }
}
