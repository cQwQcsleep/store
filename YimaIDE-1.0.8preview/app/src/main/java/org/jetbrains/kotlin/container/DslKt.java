package org.jetbrains.kotlin.container;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b\u001a\u0019\u0010\t\u001a\u00020\u0007\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u000b*\u00020\u0001H\u0086\b\u001a!\u0010\f\u001a\u00020\u0007\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u000b*\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0086\b\u001a\u001e\u0010\u000f\u001a\u0002H\n\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u000b*\u00020\u0010H\u0086\b¢\u0006\u0002\u0010\u0011\u001a)\u0010\u0012\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u000b*\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\n0\u0014¢\u0006\u0002\u0010\u0015\u001a'\u0010\u0016\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b*\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\n0\u0014¢\u0006\u0002\u0010\u0015\u001a\u0012\u0010\u0017\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u000b\u001a\u0014\u0010\u0019\u001a\u00020\u0007*\u00020\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u000b\u001a\u0016\u0010\u001a\u001a\u00020\u0007*\u00020\u00012\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c\u001a4\u0010\u001d\u001a\u0002H\n\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u000b*\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u000b2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0086\n¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"composeContainer", "Lorg/jetbrains/kotlin/container/StorageComponentContainer;", "id", Argument.Delimiters.none, "parent", "init", "Lkotlin/Function1;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "useImpl", "T", Argument.Delimiters.none, "useImplIf", "cond", Argument.Delimiters.none, "get", "Lorg/jetbrains/kotlin/container/ComponentProvider;", "(Lorg/jetbrains/kotlin/container/ComponentProvider;)Ljava/lang/Object;", "tryGetService", "request", "Ljava/lang/Class;", "(Lorg/jetbrains/kotlin/container/ComponentProvider;Ljava/lang/Class;)Ljava/lang/Object;", "getService", "useInstance", "instance", "useInstanceIfNotNull", "useClashResolver", "clashResolver", "Lorg/jetbrains/kotlin/container/PlatformExtensionsClashResolver;", "getValue", "thisRef", "desc", "Lkotlin/reflect/KProperty;", "(Lorg/jetbrains/kotlin/container/ComponentProvider;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "org.jetbrains.kotlin:container"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DslKt {
    public static final StorageComponentContainer composeContainer(String str, StorageComponentContainer storageComponentContainer, Function1<? super StorageComponentContainer, Unit> function1) throws Throwable {
        str.getClass();
        function1.getClass();
        StorageComponentContainer storageComponentContainer2 = new StorageComponentContainer(str, storageComponentContainer);
        function1.invoke(storageComponentContainer2);
        storageComponentContainer2.compose();
        return storageComponentContainer2;
    }

    public static /* synthetic */ StorageComponentContainer composeContainer$default(String str, StorageComponentContainer storageComponentContainer, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            storageComponentContainer = null;
        }
        return composeContainer(str, storageComponentContainer, function1);
    }

    public static final /* synthetic */ <T> T get(ComponentProvider componentProvider) {
        componentProvider.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) getService(componentProvider, Object.class);
    }

    public static final <T> T getService(ComponentProvider componentProvider, Class<T> cls) {
        componentProvider.getClass();
        cls.getClass();
        T t = (T) tryGetService(componentProvider, cls);
        if (t != null) {
            return t;
        }
        throw new UnresolvedServiceException(componentProvider, cls);
    }

    public static final /* synthetic */ <T> T getValue(ComponentProvider componentProvider, Object obj, KProperty<?> kProperty) {
        componentProvider.getClass();
        kProperty.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) getService(componentProvider, Object.class);
    }

    public static final <T> T tryGetService(ComponentProvider componentProvider, Class<T> cls) {
        componentProvider.getClass();
        cls.getClass();
        ValueDescriptor valueDescriptorResolve = componentProvider.resolve(cls);
        if (valueDescriptorResolve != null) {
            return (T) valueDescriptorResolve.getValue();
        }
        return null;
    }

    public static final void useClashResolver(StorageComponentContainer storageComponentContainer, PlatformExtensionsClashResolver<?> platformExtensionsClashResolver) {
        storageComponentContainer.getClass();
        platformExtensionsClashResolver.getClass();
        storageComponentContainer.registerClashResolvers$org_jetbrains_kotlin_container(CollectionsKt.listOf(platformExtensionsClashResolver));
    }

    public static final /* synthetic */ <T> void useImpl(StorageComponentContainer storageComponentContainer) {
        storageComponentContainer.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        ContainerKt.registerSingleton(storageComponentContainer, Object.class);
    }

    public static final /* synthetic */ <T> void useImplIf(StorageComponentContainer storageComponentContainer, boolean z) {
        storageComponentContainer.getClass();
        if (z) {
            Intrinsics.reifiedOperationMarker(4, "T");
            ContainerKt.registerSingleton(storageComponentContainer, Object.class);
        }
    }

    public static final void useInstance(StorageComponentContainer storageComponentContainer, Object obj) {
        storageComponentContainer.getClass();
        obj.getClass();
        ContainerKt.registerInstance(storageComponentContainer, obj);
    }

    public static final void useInstanceIfNotNull(StorageComponentContainer storageComponentContainer, Object obj) {
        storageComponentContainer.getClass();
        if (obj != null) {
            ContainerKt.registerInstance(storageComponentContainer, obj);
        }
    }
}
