package org.jetbrains.kotlin.container;

import java.io.Closeable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u001c\u0010\u0011\u001a\u00020\u000b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u00132\u0006\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/container/SingletonTypeComponentDescriptor;", "Lorg/jetbrains/kotlin/container/SingletonDescriptor;", "container", "Lorg/jetbrains/kotlin/container/ComponentContainer;", "klass", "Ljava/lang/Class;", "<init>", "(Lorg/jetbrains/kotlin/container/ComponentContainer;Ljava/lang/Class;)V", "getKlass", "()Ljava/lang/Class;", "createInstance", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/container/ValueResolveContext;", "getRegistrations", Argument.Delimiters.none, "Ljava/lang/reflect/Type;", "createInstanceOf", "getDependencies", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class SingletonTypeComponentDescriptor extends SingletonDescriptor {
    private final Class<?> klass;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingletonTypeComponentDescriptor(ComponentContainer componentContainer, Class<?> cls) {
        super(componentContainer);
        componentContainer.getClass();
        cls.getClass();
        this.klass = cls;
    }

    private final Object createInstanceOf(Class<?> klass, ValueResolveContext context) throws Throwable {
        ConstructorBinding constructorBindingBindToConstructor = ResolveKt.bindToConstructor(klass, getContainer().getContainerId(), context);
        setState(ComponentState.Initializing);
        for (ValueDescriptor valueDescriptor : constructorBindingBindToConstructor.getArgumentDescriptors()) {
            if ((valueDescriptor instanceof Closeable) && !(valueDescriptor instanceof SingletonDescriptor)) {
                registerDisposableObject((Closeable) valueDescriptor);
            }
        }
        Constructor<?> constructor = constructorBindingBindToConstructor.getConstructor();
        try {
            Object[] array = ResolveKt.computeArguments(constructorBindingBindToConstructor.getArgumentDescriptors()).toArray(new Object[0]);
            Object objNewInstance = constructor.newInstance(Arrays.copyOf(array, array.length));
            objNewInstance.getClass();
            setState(ComponentState.Initialized);
            return objNewInstance;
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException == null) {
                throw e;
            }
            throw targetException;
        }
    }

    @Override // org.jetbrains.kotlin.container.SingletonDescriptor
    public Object createInstance(ValueResolveContext context) {
        context.getClass();
        return createInstanceOf(this.klass, context);
    }

    @Override // org.jetbrains.kotlin.container.ComponentDescriptor
    public Collection<Type> getDependencies(ValueResolveContext context) {
        context.getClass();
        ClassInfo info = CacheKt.getInfo(this.klass);
        ConstructorInfo constructorInfo = info.getConstructorInfo();
        List<Type> parameters = constructorInfo != null ? constructorInfo.getParameters() : null;
        if (parameters == null) {
            parameters = CollectionsKt.emptyList();
        }
        List<SetterInfo> setterInfos = info.getSetterInfos();
        if (setterInfos.isEmpty()) {
            return parameters;
        }
        List<Type> list = parameters;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = setterInfos.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((SetterInfo) it.next()).getParameters());
        }
        return CollectionsKt.plus(list, arrayList);
    }

    public final Class<?> getKlass() {
        return this.klass;
    }

    @Override // org.jetbrains.kotlin.container.ComponentDescriptor
    public Iterable<Type> getRegistrations() {
        return CacheKt.getInfo(this.klass).getRegistrations();
    }

    public String toString() {
        return "Singleton: ".concat(this.klass.getSimpleName());
    }
}
