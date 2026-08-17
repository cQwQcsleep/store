package org.jetbrains.kotlin.extensions;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.StorageComponentContainer;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.platform.TargetPlatform;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \n2\u00020\u0001:\u0001\nJ \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/extensions/StorageComponentContainerContributor;", Argument.Delimiters.none, "registerModuleComponents", Argument.Delimiters.none, "container", "Lorg/jetbrains/kotlin/container/StorageComponentContainer;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface StorageComponentContainerContributor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/extensions/StorageComponentContainerContributor$Companion;", "Lorg/jetbrains/kotlin/extensions/ProjectExtensionDescriptor;", "Lorg/jetbrains/kotlin/extensions/StorageComponentContainerContributor;", "<init>", "()V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends ProjectExtensionDescriptor<StorageComponentContainerContributor> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("org.jetbrains.kotlin.storageComponentContainerContributor", StorageComponentContainerContributor.class);
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void registerModuleComponents(StorageComponentContainerContributor storageComponentContainerContributor, StorageComponentContainer storageComponentContainer, TargetPlatform targetPlatform, ModuleDescriptor moduleDescriptor) {
            storageComponentContainer.getClass();
            targetPlatform.getClass();
            moduleDescriptor.getClass();
            StorageComponentContainerContributor.super.registerModuleComponents(storageComponentContainer, targetPlatform, moduleDescriptor);
        }
    }

    default void registerModuleComponents(StorageComponentContainer container, TargetPlatform platform, ModuleDescriptor moduleDescriptor) {
        container.getClass();
        platform.getClass();
        moduleDescriptor.getClass();
    }
}
