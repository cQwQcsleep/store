package org.jetbrains.kotlin.cli.jvm.compiler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.builtins.jvm.JvmBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVMKt;
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.konan.properties.PropertiesKt;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.metadata.KlibMetadataFactories;
import org.jetbrains.kotlin.library.metadata.KlibMetadataModuleDescriptorFactory;
import org.jetbrains.kotlin.library.metadata.NullFlexibleTypeDeserializer;
import org.jetbrains.kotlin.storage.LockBasedStorageManager;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\u001a&\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0002\u001a.\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00040\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"jvmFactories", "Lorg/jetbrains/kotlin/library/metadata/KlibMetadataFactories;", "getKlibModules", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "klibList", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "dependencyModule", "getModuleDescriptorByLibrary", "current", "mapping", Argument.Delimiters.none, Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TopDownAnalyzerFacadeForJVMKt {
    private static final KlibMetadataFactories jvmFactories = new KlibMetadataFactories(new Function1() { // from class: qhe
        public final Object invoke(Object obj) {
            return TopDownAnalyzerFacadeForJVMKt.a((StorageManager) obj);
        }
    }, NullFlexibleTypeDeserializer.INSTANCE);

    public static KotlinBuiltIns a(StorageManager storageManager) {
        storageManager.getClass();
        return new JvmBuiltIns(storageManager, JvmBuiltIns.Kind.FROM_DEPENDENCIES);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<ModuleDescriptorImpl> getKlibModules(List<? extends KotlinLibrary> list, ModuleDescriptorImpl moduleDescriptorImpl) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<? extends KotlinLibrary> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (KotlinLibrary kotlinLibrary : list2) {
            String path = kotlinLibrary.getLocation().getPath();
            Object moduleDescriptorByLibrary = linkedHashMap.get(path);
            if (moduleDescriptorByLibrary == null) {
                moduleDescriptorByLibrary = getModuleDescriptorByLibrary(kotlinLibrary, linkedHashMap, moduleDescriptorImpl);
                linkedHashMap.put(path, moduleDescriptorByLibrary);
            }
            arrayList.add((ModuleDescriptorImpl) moduleDescriptorByLibrary);
        }
        return arrayList;
    }

    private static final ModuleDescriptorImpl getModuleDescriptorByLibrary(KotlinLibrary kotlinLibrary, Map<String, ModuleDescriptorImpl> map, ModuleDescriptorImpl moduleDescriptorImpl) {
        KlibMetadataModuleDescriptorFactory defaultDeserializedDescriptorFactory = jvmFactories.getDefaultDeserializedDescriptorFactory();
        LanguageVersionSettingsImpl languageVersionSettingsImpl = LanguageVersionSettingsImpl.DEFAULT;
        StorageManager storageManager = LockBasedStorageManager.NO_LOCKS;
        storageManager.getClass();
        ModuleDescriptorImpl moduleDescriptorImplCreateDescriptorOptionalBuiltIns = defaultDeserializedDescriptorFactory.createDescriptorOptionalBuiltIns(kotlinLibrary, languageVersionSettingsImpl, storageManager, (KotlinBuiltIns) null, LookupTracker.DO_NOTHING.INSTANCE);
        List<String> listPropertyList$default = PropertiesKt.propertyList$default(kotlinLibrary.getManifestProperties(), "depends", (String) null, true, 2, (Object) null);
        ArrayList arrayList = new ArrayList();
        for (String str : listPropertyList$default) {
            ModuleDescriptorImpl moduleDescriptorImpl2 = map.get(str);
            if (moduleDescriptorImpl2 == null) {
                if (!Intrinsics.areEqual(str, "stdlib")) {
                    Intrinsics.areEqual(str, "kotlin");
                }
                moduleDescriptorImpl2 = null;
            }
            if (moduleDescriptorImpl2 != null) {
                arrayList.add(moduleDescriptorImpl2);
            }
        }
        moduleDescriptorImplCreateDescriptorOptionalBuiltIns.setDependencies(CollectionsKt.plus(CollectionsKt.plus(CollectionsKt.listOf(moduleDescriptorImplCreateDescriptorOptionalBuiltIns), arrayList), CollectionsKt.listOfNotNull(moduleDescriptorImpl)));
        return moduleDescriptorImplCreateDescriptorOptionalBuiltIns;
    }
}
