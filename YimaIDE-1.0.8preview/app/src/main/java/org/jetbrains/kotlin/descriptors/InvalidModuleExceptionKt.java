package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\"\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"moduleInvalidated", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "INVALID_MODULE_NOTIFIER_CAPABILITY", "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "Lorg/jetbrains/kotlin/descriptors/InvalidModuleNotifier;", "getINVALID_MODULE_NOTIFIER_CAPABILITY", "()Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "org.jetbrains.kotlin:descriptors"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InvalidModuleExceptionKt {
    private static final ModuleCapability<InvalidModuleNotifier> INVALID_MODULE_NOTIFIER_CAPABILITY = new ModuleCapability<>("InvalidModuleNotifier");

    public static final ModuleCapability<InvalidModuleNotifier> getINVALID_MODULE_NOTIFIER_CAPABILITY() {
        return INVALID_MODULE_NOTIFIER_CAPABILITY;
    }

    public static final void moduleInvalidated(ModuleDescriptor moduleDescriptor) {
        moduleDescriptor.getClass();
        InvalidModuleNotifier invalidModuleNotifier = (InvalidModuleNotifier) moduleDescriptor.getCapability(INVALID_MODULE_NOTIFIER_CAPABILITY);
        if (invalidModuleNotifier != null) {
            invalidModuleNotifier.notifyModuleInvalidated(moduleDescriptor);
        } else {
            throw new InvalidModuleException("Accessing invalid module descriptor " + moduleDescriptor);
        }
    }
}
