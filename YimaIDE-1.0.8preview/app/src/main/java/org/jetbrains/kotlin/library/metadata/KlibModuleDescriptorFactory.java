package org.jetbrains.kotlin.library.metadata;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.descriptors.ModuleCapability;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JD\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u001a\b\u0002\u0010\f\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH&J<\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u001a\b\u0002\u0010\f\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/metadata/KlibModuleDescriptorFactory;", "", "createDescriptor", "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "name", "Lorg/jetbrains/kotlin/name/Name;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "origin", "Lorg/jetbrains/kotlin/library/metadata/KlibModuleOrigin;", "customCapabilities", "", "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "createDescriptorAndNewBuiltIns", "org.jetbrains.kotlin:kotlin-util-klib-metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface KlibModuleDescriptorFactory {
    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ ModuleDescriptorImpl createDescriptor$default(KlibModuleDescriptorFactory klibModuleDescriptorFactory, Name name, StorageManager storageManager, KotlinBuiltIns kotlinBuiltIns, KlibModuleOrigin klibModuleOrigin, Map map, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: createDescriptor");
            return null;
        }
        if ((i & 16) != 0) {
            map = MapsKt.emptyMap();
        }
        return klibModuleDescriptorFactory.createDescriptor(name, storageManager, kotlinBuiltIns, klibModuleOrigin, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ ModuleDescriptorImpl createDescriptorAndNewBuiltIns$default(KlibModuleDescriptorFactory klibModuleDescriptorFactory, Name name, StorageManager storageManager, KlibModuleOrigin klibModuleOrigin, Map map, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: createDescriptorAndNewBuiltIns");
            return null;
        }
        if ((i & 8) != 0) {
            map = MapsKt.emptyMap();
        }
        return klibModuleDescriptorFactory.createDescriptorAndNewBuiltIns(name, storageManager, klibModuleOrigin, map);
    }

    ModuleDescriptorImpl createDescriptor(Name name, StorageManager storageManager, KotlinBuiltIns builtIns, KlibModuleOrigin origin, Map<ModuleCapability<?>, ? extends Object> customCapabilities);

    ModuleDescriptorImpl createDescriptorAndNewBuiltIns(Name name, StorageManager storageManager, KlibModuleOrigin origin, Map<ModuleCapability<?>, ? extends Object> customCapabilities);
}
