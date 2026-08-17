package org.jetbrains.kotlin.container;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.StorageComponentContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\u0000J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0017H\u0016J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0012J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0012H\u0002J\"\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140!2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\"2\b\b\u0002\u0010\u001e\u001a\u00020\u0012J\u001b\u0010#\u001a\u00020\u00002\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%H\u0000¢\u0006\u0002\b'J\u001f\u0010(\u001a\u00020\u00002\u0010\u0010)\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030*0%H\u0000¢\u0006\u0002\b+J!\u0010,\u001a\u0002H-\"\u0004\b\u0000\u0010-2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H-0\"H\u0016¢\u0006\u0002\u0010.J\n\u00102\u001a\u00020\u0005H\u0096\u0080\u0004R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u00101¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/container/StorageComponentContainer;", "Lorg/jetbrains/kotlin/container/ComponentContainer;", "Lorg/jetbrains/kotlin/container/ComponentProvider;", "Ljava/io/Closeable;", "id", Argument.Delimiters.none, "parent", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/container/StorageComponentContainer;)V", "unknownContext", "Lorg/jetbrains/kotlin/container/ComponentResolveContext;", "getUnknownContext", "()Lorg/jetbrains/kotlin/container/ComponentResolveContext;", "unknownContext$delegate", "Lkotlin/Lazy;", "componentStorage", "Lorg/jetbrains/kotlin/container/ComponentStorage;", "createResolveContext", "Lorg/jetbrains/kotlin/container/ValueResolveContext;", "requestingDescriptor", "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "compose", "dump", Argument.Delimiters.none, "printer", "Ljava/io/PrintStream;", "close", "resolve", "request", "Ljava/lang/reflect/Type;", "context", "resolveIterable", "resolveMultiple", Argument.Delimiters.none, "Ljava/lang/Class;", "registerDescriptors", "descriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/container/ComponentDescriptor;", "registerDescriptors$org_jetbrains_kotlin_container", "registerClashResolvers", "resolvers", "Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver;", "registerClashResolvers$org_jetbrains_kotlin_container", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "T", "(Ljava/lang/Class;)Ljava/lang/Object;", "containerId", "getContainerId", "()Ljava/lang/String;", "toString", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StorageComponentContainer implements Closeable, ComponentContainer, ComponentProvider {
    private final ComponentStorage componentStorage;
    private final String id;

    /* JADX INFO: renamed from: unknownContext$delegate, reason: from kotlin metadata */
    private final Lazy unknownContext;

    public StorageComponentContainer(String str, final StorageComponentContainer storageComponentContainer) {
        str.getClass();
        this.id = str;
        this.unknownContext = LazyKt.lazy(new Function0() { // from class: mnd
            public final Object invoke() {
                return StorageComponentContainer.a(this.b, this);
            }
        });
        this.componentStorage = new ComponentStorage(str, storageComponentContainer != null ? storageComponentContainer.componentStorage : null);
    }

    public static ComponentResolveContext a(StorageComponentContainer storageComponentContainer, StorageComponentContainer storageComponentContainer2) {
        return new ComponentResolveContext(storageComponentContainer2, DynamicComponentDescriptor.INSTANCE, storageComponentContainer != null ? new ComponentResolveContext(storageComponentContainer, DynamicComponentDescriptor.INSTANCE, null, 4, null) : null);
    }

    private final ValueDescriptor resolveIterable(Type request, ValueResolveContext context) {
        if (!(request instanceof ParameterizedType)) {
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType) request;
        if (!Intrinsics.areEqual(parameterizedType.getRawType(), Iterable.class)) {
            return null;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            return null;
        }
        Type type = actualTypeArguments[0];
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length != 1) {
                return null;
            }
            type = upperBounds[0];
        } else if (!(type instanceof Class) && !(type instanceof ParameterizedType)) {
            return null;
        }
        ComponentStorage componentStorage = this.componentStorage;
        type.getClass();
        return new IterableDescriptor(componentStorage.resolveMultiple(type, context));
    }

    public static /* synthetic */ Iterable resolveMultiple$default(StorageComponentContainer storageComponentContainer, Class cls, ValueResolveContext valueResolveContext, int i, Object obj) {
        if ((i & 2) != 0) {
            valueResolveContext = storageComponentContainer.getUnknownContext();
        }
        return storageComponentContainer.resolveMultiple(cls, valueResolveContext);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws ContainerConsistencyException, IOException {
        this.componentStorage.dispose();
    }

    public final StorageComponentContainer compose() throws Throwable {
        this.componentStorage.compose(getUnknownContext());
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.container.ComponentProvider
    public <T> T create(Class<T> request) throws Throwable {
        request.getClass();
        ConstructorBinding constructorBindingBindToConstructor = ResolveKt.bindToConstructor(request, getContainerId(), getUnknownContext());
        List<ValueDescriptor> argumentDescriptors = constructorBindingBindToConstructor.getArgumentDescriptors();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(argumentDescriptors, 10));
        Iterator<T> it = argumentDescriptors.iterator();
        while (it.hasNext()) {
            arrayList.add(((ValueDescriptor) it.next()).getValue());
        }
        Object[] array = arrayList.toArray(new Object[0]);
        try {
            return (T) constructorBindingBindToConstructor.getConstructor().newInstance(Arrays.copyOf(array, array.length));
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException == null) {
                throw e;
            }
            throw targetException;
        }
    }

    @Override // org.jetbrains.kotlin.container.ComponentContainer
    public ValueResolveContext createResolveContext(ValueDescriptor requestingDescriptor) {
        requestingDescriptor.getClass();
        return Intrinsics.areEqual(requestingDescriptor, DynamicComponentDescriptor.INSTANCE) ? getUnknownContext() : new ComponentResolveContext(this, requestingDescriptor, null, 4, null);
    }

    public final void dump(PrintStream printer) {
        printer.getClass();
        this.componentStorage.dump(printer);
    }

    @Override // org.jetbrains.kotlin.container.ComponentContainer
    public String getContainerId() {
        return "Container: " + this.id;
    }

    public final ComponentResolveContext getUnknownContext() {
        return (ComponentResolveContext) this.unknownContext.getValue();
    }

    public final StorageComponentContainer registerClashResolvers$org_jetbrains_kotlin_container(List<? extends PlatformExtensionsClashResolver<?>> resolvers) {
        resolvers.getClass();
        this.componentStorage.registerClashResolvers$org_jetbrains_kotlin_container(resolvers);
        return this;
    }

    public final StorageComponentContainer registerDescriptors$org_jetbrains_kotlin_container(List<? extends ComponentDescriptor> descriptors) throws Throwable {
        descriptors.getClass();
        this.componentStorage.registerDescriptors$org_jetbrains_kotlin_container(getUnknownContext(), descriptors);
        return this;
    }

    public final ValueDescriptor resolve(Type request, ValueResolveContext context) {
        request.getClass();
        context.getClass();
        ValueDescriptor valueDescriptorResolve = this.componentStorage.resolve(request, context);
        return valueDescriptorResolve == null ? resolveIterable(request, context) : valueDescriptorResolve;
    }

    public final Iterable<ValueDescriptor> resolveMultiple(Class<?> request, ValueResolveContext context) {
        request.getClass();
        context.getClass();
        return this.componentStorage.resolveMultiple(request, context);
    }

    public String toString() {
        return getContainerId();
    }

    @Override // org.jetbrains.kotlin.container.ComponentProvider
    public ValueDescriptor resolve(Type request) {
        request.getClass();
        return resolve(request, getUnknownContext());
    }

    public /* synthetic */ StorageComponentContainer(String str, StorageComponentContainer storageComponentContainer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : storageComponentContainer);
    }
}
