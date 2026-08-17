package org.jetbrains.kotlin.container;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006\u001a%\u0010\u0007\u001a\u0004\u0018\u00010\b\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u0006*\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\b\u001a)\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u0006*\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\b¨\u0006\u000e"}, d2 = {"registerSingleton", "Lorg/jetbrains/kotlin/container/StorageComponentContainer;", "klass", "Ljava/lang/Class;", "registerInstance", "instance", Argument.Delimiters.none, "resolve", "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "T", "context", "Lorg/jetbrains/kotlin/container/ValueResolveContext;", "resolveMultiple", Argument.Delimiters.none, "org.jetbrains.kotlin:container"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContainerKt {
    public static final StorageComponentContainer registerInstance(StorageComponentContainer storageComponentContainer, Object obj) {
        storageComponentContainer.getClass();
        obj.getClass();
        return storageComponentContainer.registerDescriptors$org_jetbrains_kotlin_container(CollectionsKt.listOf(new InstanceComponentDescriptor(obj)));
    }

    public static final StorageComponentContainer registerSingleton(StorageComponentContainer storageComponentContainer, Class<?> cls) {
        storageComponentContainer.getClass();
        cls.getClass();
        return storageComponentContainer.registerDescriptors$org_jetbrains_kotlin_container(CollectionsKt.listOf(new SingletonTypeComponentDescriptor(storageComponentContainer, cls)));
    }

    public static final /* synthetic */ <T> ValueDescriptor resolve(StorageComponentContainer storageComponentContainer, ValueResolveContext valueResolveContext) {
        storageComponentContainer.getClass();
        valueResolveContext.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return storageComponentContainer.resolve(Object.class, valueResolveContext);
    }

    public static /* synthetic */ ValueDescriptor resolve$default(StorageComponentContainer storageComponentContainer, ValueResolveContext valueResolveContext, int i, Object obj) {
        if ((i & 1) != 0) {
            valueResolveContext = storageComponentContainer.getUnknownContext();
        }
        storageComponentContainer.getClass();
        valueResolveContext.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return storageComponentContainer.resolve(Object.class, valueResolveContext);
    }

    public static final /* synthetic */ <T> Iterable<ValueDescriptor> resolveMultiple(StorageComponentContainer storageComponentContainer, ValueResolveContext valueResolveContext) {
        storageComponentContainer.getClass();
        valueResolveContext.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return storageComponentContainer.resolveMultiple(Object.class, valueResolveContext);
    }

    public static /* synthetic */ Iterable resolveMultiple$default(StorageComponentContainer storageComponentContainer, ValueResolveContext valueResolveContext, int i, Object obj) {
        if ((i & 1) != 0) {
            valueResolveContext = storageComponentContainer.getUnknownContext();
        }
        storageComponentContainer.getClass();
        valueResolveContext.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return storageComponentContainer.resolveMultiple(Object.class, valueResolveContext);
    }
}
