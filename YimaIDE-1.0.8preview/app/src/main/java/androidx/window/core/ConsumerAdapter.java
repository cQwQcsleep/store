package androidx.window.core;

import android.app.Activity;
import android.content.Context;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u0002\u001f B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007H\u0000¢\u0006\u0002\b\bJ\f\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0002J4\u0010\n\u001a\u00020\u0001\"\b\b\u0000\u0010\u000b*\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002JB\u0010\u0011\u001a\u00020\u0010\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00100\u000fJT\u0010\u0015\u001a\u00020\u0016\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00100\u000fH\u0007JL\u0010\u001b\u001a\u00020\u0016\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00100\u000fH\u0007JT\u0010\u0015\u001a\u00020\u0016\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00100\u000fH\u0007JJ\u0010\u001e\u001a\u00020\u0010\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00100\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/window/core/ConsumerAdapter;", "", "loader", "Ljava/lang/ClassLoader;", "<init>", "(Ljava/lang/ClassLoader;)V", "consumerClassOrNull", "Ljava/lang/Class;", "consumerClassOrNull$window_release", "unsafeConsumerClass", "buildConsumer", "T", "clazz", "Lkotlin/reflect/KClass;", "consumer", "Lkotlin/Function1;", "", "addConsumer", "obj", "methodName", "", "createSubscription", "Landroidx/window/core/ConsumerAdapter$Subscription;", "addMethodName", "removeMethodName", "activity", "Landroid/app/Activity;", "createSubscriptionNoActivity", "context", "Landroid/content/Context;", "createConsumer", "Subscription", "ConsumerHandler", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ConsumerAdapter {
    private final ClassLoader loader;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ0\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0010\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0010H\u0096\u0002¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00028\u0000¢\u0006\u0002\u0010\u0014J#\u0010\u0015\u001a\u00020\u0016*\u00020\u000e2\u0010\u0010\u0017\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0010H\u0002¢\u0006\u0002\u0010\u0018J#\u0010\u0019\u001a\u00020\u0016*\u00020\u000e2\u0010\u0010\u0017\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0010H\u0002¢\u0006\u0002\u0010\u0018J#\u0010\u001a\u001a\u00020\u0016*\u00020\u000e2\u0010\u0010\u0017\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0010H\u0002¢\u0006\u0002\u0010\u0018J#\u0010\u001b\u001a\u00020\u0016*\u00020\u000e2\u0010\u0010\u0017\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0010H\u0002¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/window/core/ConsumerAdapter$ConsumerHandler;", "T", "", "Ljava/lang/reflect/InvocationHandler;", "clazz", "Lkotlin/reflect/KClass;", "consumer", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function1;)V", "invoke", "obj", "method", "Ljava/lang/reflect/Method;", "parameters", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "invokeAccept", "parameter", "(Ljava/lang/Object;)V", "isEquals", "", "args", "(Ljava/lang/reflect/Method;[Ljava/lang/Object;)Z", "isHashCode", "isAccept", "isToString", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class ConsumerHandler<T> implements InvocationHandler {
        private final KClass<T> clazz;
        private final Function1<T, Unit> consumer;

        /* JADX WARN: Multi-variable type inference failed */
        public ConsumerHandler(KClass<T> kClass, Function1<? super T, Unit> function1) {
            kClass.getClass();
            function1.getClass();
            this.clazz = kClass;
            this.consumer = function1;
        }

        private final boolean isAccept(Method method, Object[] objArr) {
            return Intrinsics.areEqual(method.getName(), "accept") && objArr != null && objArr.length == 1;
        }

        private final boolean isEquals(Method method, Object[] objArr) {
            return Intrinsics.areEqual(method.getName(), "equals") && method.getReturnType().equals(Boolean.TYPE) && objArr != null && objArr.length == 1;
        }

        private final boolean isHashCode(Method method, Object[] objArr) {
            return Intrinsics.areEqual(method.getName(), "hashCode") && method.getReturnType().equals(Integer.TYPE) && objArr == null;
        }

        private final boolean isToString(Method method, Object[] objArr) {
            return Intrinsics.areEqual(method.getName(), "toString") && method.getReturnType().equals(String.class) && objArr == null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] parameters) {
            obj.getClass();
            method.getClass();
            if (isAccept(method, parameters)) {
                invokeAccept(KClasses.cast(this.clazz, parameters != null ? parameters[0] : null));
                return Unit.INSTANCE;
            }
            if (isEquals(method, parameters)) {
                return Boolean.valueOf(obj == (parameters != null ? parameters[0] : null));
            }
            if (isHashCode(method, parameters)) {
                return Integer.valueOf(this.consumer.hashCode());
            }
            if (isToString(method, parameters)) {
                return this.consumer.toString();
            }
            throw new UnsupportedOperationException("Unexpected method call object:" + obj + ", method: " + method + ", args: " + parameters);
        }

        public final void invokeAccept(T parameter) {
            parameter.getClass();
            this.consumer.invoke(parameter);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Landroidx/window/core/ConsumerAdapter$Subscription;", "", "dispose", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public interface Subscription {
        void dispose();
    }

    public ConsumerAdapter(ClassLoader classLoader) {
        classLoader.getClass();
        this.loader = classLoader;
    }

    private final <T> Object buildConsumer(KClass<T> clazz, Function1<? super T, Unit> consumer) {
        Object objNewProxyInstance = Proxy.newProxyInstance(this.loader, new Class[]{unsafeConsumerClass()}, new ConsumerHandler(clazz, consumer));
        objNewProxyInstance.getClass();
        return objNewProxyInstance;
    }

    private final Class<?> unsafeConsumerClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.JAVA_CONSUMER);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    public final <T> void addConsumer(Object obj, KClass<T> clazz, String methodName, Function1<? super T, Unit> consumer) throws IllegalAccessException, InvocationTargetException {
        obj.getClass();
        clazz.getClass();
        methodName.getClass();
        consumer.getClass();
        obj.getClass().getMethod(methodName, unsafeConsumerClass()).invoke(obj, buildConsumer(clazz, consumer));
    }

    public final Class<?> consumerClassOrNull$window_release() {
        try {
            return unsafeConsumerClass();
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public final <T> void createConsumer(Object obj, KClass<T> clazz, String addMethodName, Activity activity, Function1<? super T, Unit> consumer) throws IllegalAccessException, InvocationTargetException {
        obj.getClass();
        clazz.getClass();
        addMethodName.getClass();
        activity.getClass();
        consumer.getClass();
        obj.getClass().getMethod(addMethodName, Activity.class, unsafeConsumerClass()).invoke(obj, activity, buildConsumer(clazz, consumer));
    }

    public final <T> Subscription createSubscription(final Object obj, KClass<T> clazz, String addMethodName, String removeMethodName, Activity activity, Function1<? super T, Unit> consumer) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        obj.getClass();
        clazz.getClass();
        addMethodName.getClass();
        removeMethodName.getClass();
        activity.getClass();
        consumer.getClass();
        final Object objBuildConsumer = buildConsumer(clazz, consumer);
        obj.getClass().getMethod(addMethodName, Activity.class, unsafeConsumerClass()).invoke(obj, activity, objBuildConsumer);
        final Method method = obj.getClass().getMethod(removeMethodName, unsafeConsumerClass());
        return new Subscription() { // from class: androidx.window.core.ConsumerAdapter.createSubscription.1
            @Override // androidx.window.core.ConsumerAdapter.Subscription
            public void dispose() throws IllegalAccessException, InvocationTargetException {
                method.invoke(obj, objBuildConsumer);
            }
        };
    }

    public final <T> Subscription createSubscriptionNoActivity(final Object obj, KClass<T> clazz, String addMethodName, String removeMethodName, Function1<? super T, Unit> consumer) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        obj.getClass();
        clazz.getClass();
        addMethodName.getClass();
        removeMethodName.getClass();
        consumer.getClass();
        final Object objBuildConsumer = buildConsumer(clazz, consumer);
        obj.getClass().getMethod(addMethodName, unsafeConsumerClass()).invoke(obj, objBuildConsumer);
        final Method method = obj.getClass().getMethod(removeMethodName, unsafeConsumerClass());
        return new Subscription() { // from class: androidx.window.core.ConsumerAdapter.createSubscriptionNoActivity.1
            @Override // androidx.window.core.ConsumerAdapter.Subscription
            public void dispose() throws IllegalAccessException, InvocationTargetException {
                method.invoke(obj, objBuildConsumer);
            }
        };
    }

    public final <T> Subscription createSubscription(final Object obj, KClass<T> clazz, String addMethodName, String removeMethodName, Context context, Function1<? super T, Unit> consumer) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        obj.getClass();
        clazz.getClass();
        addMethodName.getClass();
        removeMethodName.getClass();
        context.getClass();
        consumer.getClass();
        final Object objBuildConsumer = buildConsumer(clazz, consumer);
        obj.getClass().getMethod(addMethodName, Context.class, unsafeConsumerClass()).invoke(obj, context, objBuildConsumer);
        final Method method = obj.getClass().getMethod(removeMethodName, unsafeConsumerClass());
        return new Subscription() { // from class: androidx.window.core.ConsumerAdapter.createSubscription.2
            @Override // androidx.window.core.ConsumerAdapter.Subscription
            public void dispose() throws IllegalAccessException, InvocationTargetException {
                method.invoke(obj, objBuildConsumer);
            }
        };
    }
}
