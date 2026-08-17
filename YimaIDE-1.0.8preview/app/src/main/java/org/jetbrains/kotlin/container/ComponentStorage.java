package org.jetbrains.kotlin.container;

import com.intellij.util.containers.MultiMap;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.ComponentDescriptor;
import org.jetbrains.kotlin.container.ComponentStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#J\u001c\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001b0(2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eJ\u001f\u0010)\u001a\u00020 2\u0010\u0010*\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170+H\u0000¢\u0006\u0002\b,J#\u0010-\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000f0+H\u0000¢\u0006\u0002\b0J\u000e\u00101\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020.J\u001e\u00102\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020.2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f03H\u0002J\u001e\u00104\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020.2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u000f03H\u0002J$\u00106\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u001d\u001a\u00020.2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f03H\u0002J4\u00107\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020.2\u0006\u00108\u001a\u00020\u000f2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00130:2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u001e\u0010<\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001d\u001a\u00020.2\n\u0010=\u001a\u0006\u0012\u0002\b\u00030>H\u0002J\u0018\u00104\u001a\u00020 2\u0006\u0010?\u001a\u00020@2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0006\u0010A\u001a\u00020 J\u000e\u0010B\u001a\b\u0012\u0004\u0012\u00020\u000f0+H\u0002J\u0010\u0010C\u001a\u00020 2\u0006\u00108\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u0010\u001a#\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u000f0\u000f\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00130\u00130\u0011¢\u0006\u0002\b\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010$\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/container/ComponentStorage;", "Lorg/jetbrains/kotlin/container/ValueResolver;", "myId", Argument.Delimiters.none, "parent", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/container/ComponentStorage;)V", "state", "Lorg/jetbrains/kotlin/container/ComponentStorageState;", "getState", "()Lorg/jetbrains/kotlin/container/ComponentStorageState;", "setState", "(Lorg/jetbrains/kotlin/container/ComponentStorageState;)V", "descriptors", "Ljava/util/LinkedHashSet;", "Lorg/jetbrains/kotlin/container/ComponentDescriptor;", "dependencies", "Lcom/intellij/util/containers/MultiMap;", "kotlin.jvm.PlatformType", "Ljava/lang/reflect/Type;", "Lorg/jetbrains/annotations/NotNull;", "clashResolvers", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver;", "registry", "Lorg/jetbrains/kotlin/container/ComponentRegistry;", "resolve", "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "request", "context", "Lorg/jetbrains/kotlin/container/ValueResolveContext;", "registerDependency", Argument.Delimiters.none, "dump", "printer", "Ljava/io/PrintStream;", "containerId", "getContainerId", "()Ljava/lang/String;", "resolveMultiple", Argument.Delimiters.none, "registerClashResolvers", "resolvers", Argument.Delimiters.none, "registerClashResolvers$org_jetbrains_kotlin_container", "registerDescriptors", "Lorg/jetbrains/kotlin/container/ComponentResolveContext;", "items", "registerDescriptors$org_jetbrains_kotlin_container", "compose", "composeDescriptors", Argument.Delimiters.none, "injectProperties", "components", "inspectDependenciesAndRegisterAdhoc", "collectAdhocComponents", "descriptor", "visitedTypes", "Ljava/util/HashSet;", "adhocDescriptors", "getImplicitlyDefinedDependency", "rawType", "Ljava/lang/Class;", "instance", Argument.Delimiters.none, "dispose", "getDescriptorsInDisposeOrder", "disposeDescriptor", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComponentStorage implements ValueResolver {
    private final ArrayList<PlatformExtensionsClashResolver<?>> clashResolvers;
    private final MultiMap<ComponentDescriptor, Type> dependencies;
    private final LinkedHashSet<ComponentDescriptor> descriptors;
    private final String myId;
    private final ComponentRegistry registry;
    private ComponentStorageState state;

    public ComponentStorage(String str, ComponentStorage componentStorage) {
        str.getClass();
        this.myId = str;
        this.state = ComponentStorageState.Initial;
        this.descriptors = new LinkedHashSet<>();
        MultiMap<ComponentDescriptor, Type> multiMapCreateLinkedSet = MultiMap.createLinkedSet();
        multiMapCreateLinkedSet.getClass();
        this.dependencies = multiMapCreateLinkedSet;
        ArrayList<PlatformExtensionsClashResolver<?>> arrayList = new ArrayList<>();
        this.clashResolvers = arrayList;
        ComponentRegistry componentRegistry = new ComponentRegistry();
        this.registry = componentRegistry;
        if (componentStorage != null) {
            componentRegistry.addAll(componentStorage.registry);
            arrayList.addAll(componentStorage.clashResolvers);
        }
    }

    public static Iterable a(ComponentStorage componentStorage, ComponentDescriptor componentDescriptor) {
        componentDescriptor.getClass();
        ArrayList arrayList = new ArrayList();
        for (Type type : componentStorage.dependencies.get(componentDescriptor)) {
            ComponentRegistry componentRegistry = componentStorage.registry;
            type.getClass();
            Iterator<ComponentDescriptor> it = componentRegistry.tryGetEntry(type).iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0040  */
    private final void collectAdhocComponents(ComponentResolveContext context, ComponentDescriptor descriptor, HashSet<Type> visitedTypes, LinkedHashSet<ComponentDescriptor> adhocDescriptors) {
        Class<?> cls;
        ComponentDescriptor implicitlyDefinedDependency;
        for (Type type : descriptor.getDependencies(context)) {
            if (visitedTypes.add(type) && this.registry.tryGetEntry(type).isEmpty()) {
                if (type instanceof Class) {
                    cls = (Class) type;
                } else if (type instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) type).getRawType();
                    if (rawType instanceof Class) {
                        cls = (Class) rawType;
                    } else {
                        cls = null;
                    }
                } else {
                    cls = null;
                }
                if (cls != null && (implicitlyDefinedDependency = getImplicitlyDefinedDependency(context, cls)) != null) {
                    adhocDescriptors.add(implicitlyDefinedDependency);
                    collectAdhocComponents(context, implicitlyDefinedDependency, visitedTypes, adhocDescriptors);
                }
            }
        }
    }

    private final void composeDescriptors(ComponentResolveContext context, Collection<? extends ComponentDescriptor> descriptors) throws Throwable {
        if (descriptors.isEmpty()) {
            return;
        }
        this.registry.addAll(descriptors);
        LinkedHashSet<ComponentDescriptor> linkedHashSetInspectDependenciesAndRegisterAdhoc = inspectDependenciesAndRegisterAdhoc(context, descriptors);
        this.registry.resolveClashesIfAny(context.getContainer(), this.clashResolvers);
        injectProperties(context, CollectionsKt.plus(descriptors, linkedHashSetInspectDependenciesAndRegisterAdhoc));
    }

    private final void disposeDescriptor(ComponentDescriptor descriptor) throws IOException {
        if (descriptor instanceof Closeable) {
            ((Closeable) descriptor).close();
        }
    }

    private final List<ComponentDescriptor> getDescriptorsInDisposeOrder() {
        return DataStructuresKt.topologicalSort$default(this.descriptors, false, new Function1() { // from class: kb2
            public final Object invoke(Object obj) {
                return ComponentStorage.a(this.b, (ComponentDescriptor) obj);
            }
        }, 2, null);
    }

    private final ComponentDescriptor getImplicitlyDefinedDependency(ComponentResolveContext context, Class<?> rawType) {
        Field field;
        Object obj;
        if (!Modifier.isAbstract(rawType.getModifiers()) && !rawType.isPrimitive()) {
            return new ImplicitSingletonTypeComponentDescriptor(context.getContainer(), rawType);
        }
        Class<?> defaultImplementation = CacheKt.getInfo(rawType).getDefaultImplementation();
        if (defaultImplementation != null && CacheKt.getInfo(defaultImplementation).getConstructorInfo() != null) {
            return new DefaultSingletonTypeComponentDescriptor(context.getContainer(), defaultImplementation);
        }
        if (defaultImplementation == null || (field = defaultImplementation.getField("INSTANCE")) == null || (obj = field.get(null)) == null) {
            return null;
        }
        return new DefaultInstanceComponentDescriptor(obj);
    }

    private final void injectProperties(Object instance, ValueResolveContext context) throws Throwable {
        Iterator<T> it = CacheKt.getInfo(instance.getClass()).getSetterInfos().iterator();
        while (it.hasNext()) {
            ResolveKt.bindToMethod(((SetterInfo) it.next()).getMethod(), getContainerId(), context).invoke(instance);
        }
    }

    private final LinkedHashSet<ComponentDescriptor> inspectDependenciesAndRegisterAdhoc(ComponentResolveContext context, Collection<? extends ComponentDescriptor> descriptors) {
        LinkedHashSet<ComponentDescriptor> linkedHashSet = new LinkedHashSet<>();
        HashSet<Type> hashSet = new HashSet<>();
        Iterator<? extends ComponentDescriptor> it = descriptors.iterator();
        while (it.hasNext()) {
            collectAdhocComponents(context, it.next(), hashSet, linkedHashSet);
        }
        this.registry.addAll(linkedHashSet);
        return linkedHashSet;
    }

    private final void registerDependency(Type request, ValueResolveContext context) {
        if (context instanceof ComponentResolveContext) {
            ValueDescriptor requestingDescriptor = ((ComponentResolveContext) context).getRequestingDescriptor();
            if (requestingDescriptor instanceof ComponentDescriptor) {
                this.dependencies.putValue(requestingDescriptor, request);
            }
        }
    }

    private static final boolean resolve$isDefaultComponent(ComponentDescriptor componentDescriptor) {
        return (componentDescriptor instanceof DefaultInstanceComponentDescriptor) || (componentDescriptor instanceof DefaultSingletonTypeComponentDescriptor);
    }

    public final void compose(ComponentResolveContext context) throws Throwable {
        context.getClass();
        if (this.state == ComponentStorageState.Initial) {
            this.state = ComponentStorageState.Initialized;
            composeDescriptors(context, this.descriptors);
            return;
        }
        throw new ContainerConsistencyException(getContainerId() + ' ' + this.myId + " was already composed.");
    }

    public final void dispose() throws ContainerConsistencyException, IOException {
        ComponentStorageState componentStorageState = this.state;
        if (componentStorageState == ComponentStorageState.Initialized) {
            this.state = ComponentStorageState.Disposing;
            Iterator<ComponentDescriptor> it = getDescriptorsInDisposeOrder().iterator();
            while (it.hasNext()) {
                disposeDescriptor(it.next());
            }
            this.state = ComponentStorageState.Disposed;
            return;
        }
        if (componentStorageState == ComponentStorageState.Initial) {
            return;
        }
        throw new ContainerConsistencyException("Component container cannot be disposed in the " + this.state + " state.");
    }

    public final void dump(PrintStream printer) {
        printer.getClass();
        String containerId = getContainerId();
        printer.println(containerId);
        printer.println(StringsKt.repeat("=", containerId.length()));
        printer.println();
        for (ComponentDescriptor componentDescriptor : getDescriptorsInDisposeOrder()) {
            printer.println(componentDescriptor);
            Collection<Type> collection = this.dependencies.get(componentDescriptor);
            collection.getClass();
            for (Type type : collection) {
                printer.print("   -> ");
                String string = type.toString();
                printer.print(StringsKt.substringBefore$default(string, Argument.Delimiters.space, (String) null, 2, (Object) null));
                printer.print(Argument.Delimiters.space);
                printer.print(StringsKt.substringAfterLast$default(string, ".", (String) null, 2, (Object) null));
                Collection<ComponentDescriptor> collectionTryGetEntry = this.registry.tryGetEntry(type);
                printer.print(" as ");
                printer.print(collectionTryGetEntry);
                printer.println();
            }
            printer.println();
        }
    }

    public final String getContainerId() {
        return "Container: " + this.myId;
    }

    public final ComponentStorageState getState() {
        return this.state;
    }

    public final void registerClashResolvers$org_jetbrains_kotlin_container(List<? extends PlatformExtensionsClashResolver<?>> resolvers) {
        resolvers.getClass();
        this.clashResolvers.addAll(resolvers);
    }

    public final void registerDescriptors$org_jetbrains_kotlin_container(ComponentResolveContext context, List<? extends ComponentDescriptor> items) throws Throwable {
        context.getClass();
        items.getClass();
        if (this.state == ComponentStorageState.Disposed) {
            throw new ContainerConsistencyException("Cannot register descriptors in " + this.state + " state");
        }
        Iterator<? extends ComponentDescriptor> it = items.iterator();
        while (it.hasNext()) {
            this.descriptors.add(it.next());
        }
        if (this.state == ComponentStorageState.Initialized) {
            composeDescriptors(context, items);
        }
    }

    @Override // org.jetbrains.kotlin.container.ValueResolver
    public ValueDescriptor resolve(Type request, ValueResolveContext context) throws ContainerConsistencyException, InvalidCardinalityException {
        request.getClass();
        context.getClass();
        if (this.state == ComponentStorageState.Initial) {
            throw new ContainerConsistencyException("Container was not composed before resolving");
        }
        Collection<ComponentDescriptor> collectionTryGetEntry = this.registry.tryGetEntry(request);
        if (collectionTryGetEntry.isEmpty()) {
            return null;
        }
        registerDependency(request, context);
        if (collectionTryGetEntry.size() == 1) {
            return (ValueDescriptor) CollectionsKt.single(collectionTryGetEntry);
        }
        Collection<ComponentDescriptor> collection = collectionTryGetEntry;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (!resolve$isDefaultComponent((ComponentDescriptor) obj)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return (ValueDescriptor) CollectionsKt.first(collection);
        }
        ComponentDescriptor componentDescriptor = (ComponentDescriptor) CollectionsKt.singleOrNull(arrayList);
        if (componentDescriptor != null) {
            return componentDescriptor;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getContainerId());
        sb.append(": Request ");
        sb.append(request);
        String strJoinToString$default = CollectionsKt.joinToString$default(collection, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null);
        sb.append(" cannot be satisfied because there is more than one type registered\nClashed registrations: ");
        sb.append(strJoinToString$default);
        throw new InvalidCardinalityException(sb.toString());
    }

    public final Iterable<ValueDescriptor> resolveMultiple(Type request, ValueResolveContext context) {
        request.getClass();
        context.getClass();
        registerDependency(request, context);
        return this.registry.tryGetEntry(request);
    }

    public final void setState(ComponentStorageState componentStorageState) {
        componentStorageState.getClass();
        this.state = componentStorageState;
    }

    private final void injectProperties(ComponentResolveContext context, Collection<? extends ComponentDescriptor> components) throws Throwable {
        for (ComponentDescriptor componentDescriptor : components) {
            if (componentDescriptor.getShouldInjectProperties()) {
                injectProperties(componentDescriptor.getValue(), context.getContainer().createResolveContext(componentDescriptor));
            }
        }
    }
}
