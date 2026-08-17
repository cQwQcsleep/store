package androidx.window.core;

import android.util.Pair;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u0003\u0016\u0017\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007H\u0000¢\u0006\u0002\b\bJ\f\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0002J2\u0010\n\u001a\u00020\u0001\"\b\b\u0000\u0010\u000b*\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00100\u000fJP\u0010\u0011\u001a\u00020\u0001\"\b\b\u0000\u0010\u000b*\u00020\u0001\"\b\b\u0001\u0010\u0012*\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\r2\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u00020\u00100\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/window/core/PredicateAdapter;", "", "loader", "Ljava/lang/ClassLoader;", "<init>", "(Ljava/lang/ClassLoader;)V", "predicateClassOrNull", "Ljava/lang/Class;", "predicateClassOrNull$window_release", "predicateClassOrThrow", "buildPredicate", "T", "clazz", "Lkotlin/reflect/KClass;", "predicate", "Lkotlin/Function1;", "", "buildPairPredicate", "U", "firstClazz", "secondClazz", "Lkotlin/Function2;", "BaseHandler", "PredicateStubHandler", "PairPredicateStubHandler", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PredicateAdapter {
    private final ClassLoader loader;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\"\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J0\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0010\u0010\f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\rH\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0012J#\u0010\u0013\u001a\u00020\u0010*\u00020\u000b2\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\rH\u0004¢\u0006\u0002\u0010\u0015J#\u0010\u0016\u001a\u00020\u0010*\u00020\u000b2\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\rH\u0004¢\u0006\u0002\u0010\u0015J#\u0010\u0017\u001a\u00020\u0010*\u00020\u000b2\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\rH\u0004¢\u0006\u0002\u0010\u0015J#\u0010\u0018\u001a\u00020\u0010*\u00020\u000b2\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\rH\u0004¢\u0006\u0002\u0010\u0015R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/window/core/PredicateAdapter$BaseHandler;", "T", "", "Ljava/lang/reflect/InvocationHandler;", "clazz", "Lkotlin/reflect/KClass;", "<init>", "(Lkotlin/reflect/KClass;)V", "invoke", "obj", "method", "Ljava/lang/reflect/Method;", "parameters", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "invokeTest", "", "parameter", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "isEquals", "args", "(Ljava/lang/reflect/Method;[Ljava/lang/Object;)Z", "isHashCode", "isTest", "isToString", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static abstract class BaseHandler<T> implements InvocationHandler {
        private final KClass<T> clazz;

        public BaseHandler(KClass<T> kClass) {
            kClass.getClass();
            this.clazz = kClass;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] parameters) {
            obj.getClass();
            method.getClass();
            if (isTest(method, parameters)) {
                return Boolean.valueOf(invokeTest(obj, KClasses.cast(this.clazz, parameters != null ? parameters[0] : null)));
            }
            if (isEquals(method, parameters)) {
                Object obj2 = parameters != null ? parameters[0] : null;
                obj2.getClass();
                return Boolean.valueOf(obj == obj2);
            }
            if (isHashCode(method, parameters)) {
                return Integer.valueOf(hashCode());
            }
            if (isToString(method, parameters)) {
                return toString();
            }
            throw new UnsupportedOperationException("Unexpected method call object:" + obj + ", method: " + method + ", args: " + parameters);
        }

        public abstract boolean invokeTest(Object obj, T parameter);

        public final boolean isEquals(Method method, Object[] objArr) {
            method.getClass();
            return Intrinsics.areEqual(method.getName(), "equals") && method.getReturnType().equals(Boolean.TYPE) && objArr != null && objArr.length == 1;
        }

        public final boolean isHashCode(Method method, Object[] objArr) {
            method.getClass();
            return Intrinsics.areEqual(method.getName(), "hashCode") && method.getReturnType().equals(Integer.TYPE) && objArr == null;
        }

        public final boolean isTest(Method method, Object[] objArr) {
            method.getClass();
            return Intrinsics.areEqual(method.getName(), "test") && method.getReturnType().equals(Boolean.TYPE) && objArr != null && objArr.length == 1;
        }

        public final boolean isToString(Method method, Object[] objArr) {
            method.getClass();
            return Intrinsics.areEqual(method.getName(), "toString") && method.getReturnType().equals(String.class) && objArr == null;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00050\u0004B=\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ \u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00022\u000e\u0010\u0010\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/window/core/PredicateAdapter$PairPredicateStubHandler;", "T", "", "U", "Landroidx/window/core/PredicateAdapter$BaseHandler;", "Landroid/util/Pair;", "clazzT", "Lkotlin/reflect/KClass;", "clazzU", "predicate", "Lkotlin/Function2;", "", "<init>", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function2;)V", "invokeTest", "obj", "parameter", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class PairPredicateStubHandler<T, U> extends BaseHandler<Pair<?, ?>> {
        private final KClass<T> clazzT;
        private final KClass<U> clazzU;
        private final Function2<T, U, Boolean> predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public PairPredicateStubHandler(KClass<T> kClass, KClass<U> kClass2, Function2<? super T, ? super U, Boolean> function2) {
            super(Reflection.getOrCreateKotlinClass(Pair.class));
            kClass.getClass();
            kClass2.getClass();
            function2.getClass();
            this.clazzT = kClass;
            this.clazzU = kClass2;
            this.predicate = function2;
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        @Override // androidx.window.core.PredicateAdapter.BaseHandler
        public boolean invokeTest(Object obj, Pair<?, ?> parameter) {
            obj.getClass();
            parameter.getClass();
            return ((Boolean) this.predicate.invoke(KClasses.cast(this.clazzT, parameter.first), KClasses.cast(this.clazzU, parameter.second))).booleanValue();
        }

        public String toString() {
            return this.predicate.toString();
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/window/core/PredicateAdapter$PredicateStubHandler;", "T", "", "Landroidx/window/core/PredicateAdapter$BaseHandler;", "clazzT", "Lkotlin/reflect/KClass;", "predicate", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function1;)V", "invokeTest", "obj", "parameter", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class PredicateStubHandler<T> extends BaseHandler<T> {
        private final Function1<T, Boolean> predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public PredicateStubHandler(KClass<T> kClass, Function1<? super T, Boolean> function1) {
            super(kClass);
            kClass.getClass();
            function1.getClass();
            this.predicate = function1;
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        @Override // androidx.window.core.PredicateAdapter.BaseHandler
        public boolean invokeTest(Object obj, T parameter) {
            obj.getClass();
            parameter.getClass();
            return ((Boolean) this.predicate.invoke(parameter)).booleanValue();
        }

        public String toString() {
            return this.predicate.toString();
        }
    }

    public PredicateAdapter(ClassLoader classLoader) {
        classLoader.getClass();
        this.loader = classLoader;
    }

    private final Class<?> predicateClassOrThrow() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass("java.util.function.Predicate");
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    public final <T, U> Object buildPairPredicate(KClass<T> firstClazz, KClass<U> secondClazz, Function2<? super T, ? super U, Boolean> predicate) {
        firstClazz.getClass();
        secondClazz.getClass();
        predicate.getClass();
        Object objNewProxyInstance = Proxy.newProxyInstance(this.loader, new Class[]{predicateClassOrThrow()}, new PairPredicateStubHandler(firstClazz, secondClazz, predicate));
        objNewProxyInstance.getClass();
        return objNewProxyInstance;
    }

    public final <T> Object buildPredicate(KClass<T> clazz, Function1<? super T, Boolean> predicate) {
        clazz.getClass();
        predicate.getClass();
        Object objNewProxyInstance = Proxy.newProxyInstance(this.loader, new Class[]{predicateClassOrThrow()}, new PredicateStubHandler(clazz, predicate));
        objNewProxyInstance.getClass();
        return objNewProxyInstance;
    }

    public final Class<?> predicateClassOrNull$window_release() {
        try {
            return predicateClassOrThrow();
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
