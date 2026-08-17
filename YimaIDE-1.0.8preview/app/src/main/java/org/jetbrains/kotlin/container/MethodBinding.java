package org.jetbrains.kotlin.container;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/container/MethodBinding;", Argument.Delimiters.none, "method", "Ljava/lang/reflect/Method;", "argumentDescriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "<init>", "(Ljava/lang/reflect/Method;Ljava/util/List;)V", "getMethod", "()Ljava/lang/reflect/Method;", "invoke", Argument.Delimiters.none, "instance", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MethodBinding {
    private final List<ValueDescriptor> argumentDescriptors;
    private final Method method;

    /* JADX WARN: Multi-variable type inference failed */
    public MethodBinding(Method method, List<? extends ValueDescriptor> list) {
        method.getClass();
        list.getClass();
        this.method = method;
        this.argumentDescriptors = list;
    }

    public final Method getMethod() {
        return this.method;
    }

    public final void invoke(Object instance) throws Throwable {
        instance.getClass();
        Object[] array = ResolveKt.computeArguments(this.argumentDescriptors).toArray(new Object[0]);
        try {
            this.method.invoke(instance, Arrays.copyOf(array, array.length));
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException != null) {
                throw targetException;
            }
        }
    }
}
