package org.jetbrains.kotlin.cli.metadata;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.analyzer.common.CommonDependenciesContainer;
import org.jetbrains.kotlin.analyzer.common.CommonPlatformAnalyzerServices;
import org.jetbrains.kotlin.backend.common.LoadMetadataKlibsKt;
import org.jetbrains.kotlin.builtins.DefaultBuiltIns;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.K2MetadataConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.jetbrains.kotlin.descriptors.deserialization.AdditionalClassPartsProvider;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.components.KlibMetadataComponent;
import org.jetbrains.kotlin.library.metadata.CustomMetadataProtoLoader;
import org.jetbrains.kotlin.library.metadata.DeserializedKlibModuleOrigin;
import org.jetbrains.kotlin.library.metadata.KlibMetadataDeserializationUtilsKt;
import org.jetbrains.kotlin.library.metadata.KlibModuleDescriptorFactory;
import org.jetbrains.kotlin.library.metadata.impl.KlibMetadataModuleDescriptorFactoryImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.CommonPlatforms;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.resolve.KlibCompilerDeserializationConfiguration;
import org.jetbrains.kotlin.resolve.PlatformDependentAnalyzerServices;
import org.jetbrains.kotlin.storage.LockBasedStorageManager;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001:\u00011B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0017H\u0016J\u0018\u0010$\u001a\u00020%2\u0006\u0010#\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0015H\u0016J\u0012\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010#\u001a\u00020\u0017H\u0016J\u0010\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00150\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00170\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u001b\u0010)\u001a\u00020*8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b+\u0010,¨\u00062"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/KlibMetadataDependencyContainer;", "Lorg/jetbrains/kotlin/analyzer/common/CommonDependenciesContainer;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/storage/StorageManager;)V", "kotlinLibraries", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "friendPaths", Argument.Delimiters.none, Argument.Delimiters.none, "refinesPaths", "builtIns", "Lorg/jetbrains/kotlin/builtins/DefaultBuiltIns;", "getBuiltIns", "()Lorg/jetbrains/kotlin/builtins/DefaultBuiltIns;", "mutableDependenciesForAllModuleDescriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "mutableDependenciesForAllModules", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "moduleDescriptorsForKotlinLibraries", Argument.Delimiters.none, "moduleInfos", "getModuleInfos", "()Ljava/util/List;", "friendModuleInfos", "getFriendModuleInfos", "refinesModuleInfos", "getRefinesModuleInfos", "moduleDescriptorForModuleInfo", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "moduleInfo", "registerDependencyForAllModules", Argument.Delimiters.none, "descriptorForModule", "packageFragmentProviderForModuleInfo", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "klibMetadataModuleDescriptorFactory", "Lorg/jetbrains/kotlin/library/metadata/impl/KlibMetadataModuleDescriptorFactoryImpl;", "getKlibMetadataModuleDescriptorFactory", "()Lorg/jetbrains/kotlin/library/metadata/impl/KlibMetadataModuleDescriptorFactoryImpl;", "klibMetadataModuleDescriptorFactory$delegate", "Lkotlin/Lazy;", "packageFragmentProviderForKotlinLibrary", "library", "KlibModuleInfo", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class KlibMetadataDependencyContainer implements CommonDependenciesContainer {
    private final CompilerConfiguration configuration;
    private final List<ModuleInfo> friendModuleInfos;
    private final Set<String> friendPaths;

    /* JADX INFO: renamed from: klibMetadataModuleDescriptorFactory$delegate, reason: from kotlin metadata */
    private final Lazy klibMetadataModuleDescriptorFactory;
    private final List<KotlinLibrary> kotlinLibraries;
    private final Map<KotlinLibrary, ModuleDescriptorImpl> moduleDescriptorsForKotlinLibraries;
    private final List<KlibModuleInfo> moduleInfos;
    private final List<ModuleDescriptorImpl> mutableDependenciesForAllModuleDescriptors;
    private final List<ModuleInfo> mutableDependenciesForAllModules;
    private final List<ModuleInfo> refinesModuleInfos;
    private final Set<String> refinesPaths;
    private final StorageManager storageManager;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/KlibMetadataDependencyContainer$KlibModuleInfo;", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "kotlinLibrary", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "dependOnModules", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/library/KotlinLibrary;Ljava/util/List;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getKotlinLibrary", "()Lorg/jetbrains/kotlin/library/KotlinLibrary;", "dependencies", "dependencyOnBuiltIns", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo$DependencyOnBuiltIns;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "analyzerServices", "Lorg/jetbrains/kotlin/resolve/PlatformDependentAnalyzerServices;", "getAnalyzerServices", "()Lorg/jetbrains/kotlin/resolve/PlatformDependentAnalyzerServices;", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class KlibModuleInfo implements ModuleInfo {
        private final List<ModuleInfo> dependOnModules;
        private final KotlinLibrary kotlinLibrary;
        private final Name name;

        public KlibModuleInfo(Name name, KotlinLibrary kotlinLibrary, List<? extends ModuleInfo> list) {
            name.getClass();
            kotlinLibrary.getClass();
            list.getClass();
            this.name = name;
            this.kotlinLibrary = kotlinLibrary;
            this.dependOnModules = list;
        }

        public List<ModuleInfo> dependencies() {
            return this.dependOnModules;
        }

        public ModuleInfo.DependencyOnBuiltIns dependencyOnBuiltIns() {
            return ModuleInfo.DependencyOnBuiltIns.LAST;
        }

        public PlatformDependentAnalyzerServices getAnalyzerServices() {
            return CommonPlatformAnalyzerServices.INSTANCE;
        }

        public final KotlinLibrary getKotlinLibrary() {
            return this.kotlinLibrary;
        }

        public Name getName() {
            return this.name;
        }

        public TargetPlatform getPlatform() {
            return CommonPlatforms.INSTANCE.getDefaultCommonPlatform();
        }
    }

    public KlibMetadataDependencyContainer(CompilerConfiguration compilerConfiguration, StorageManager storageManager) {
        File file;
        compilerConfiguration.getClass();
        storageManager.getClass();
        this.configuration = compilerConfiguration;
        this.storageManager = storageManager;
        List<ContentRoot> contentRoots = CLIConfigurationKeysKt.getContentRoots(compilerConfiguration);
        ArrayList arrayList = new ArrayList();
        for (ContentRoot contentRoot : contentRoots) {
            String path = null;
            JvmClasspathRoot jvmClasspathRoot = contentRoot instanceof JvmClasspathRoot ? (JvmClasspathRoot) contentRoot : null;
            if (jvmClasspathRoot != null && (file = jvmClasspathRoot.getFile()) != null) {
                path = file.getPath();
            }
            if (path != null) {
                arrayList.add(path);
            }
        }
        List<KotlinLibrary> all = LoadMetadataKlibsKt.loadMetadataKlibs(arrayList, this.configuration).getAll();
        this.kotlinLibraries = all;
        CompilerConfiguration compilerConfiguration2 = this.configuration;
        K2MetadataConfigurationKeys k2MetadataConfigurationKeys = K2MetadataConfigurationKeys.INSTANCE;
        List list = (List) compilerConfiguration2.get(k2MetadataConfigurationKeys.getFRIEND_PATHS());
        this.friendPaths = CollectionsKt.toSet(list == null ? CollectionsKt.emptyList() : list);
        List list2 = (List) this.configuration.get(k2MetadataConfigurationKeys.getREFINES_PATHS());
        this.refinesPaths = CollectionsKt.toSet(list2 == null ? CollectionsKt.emptyList() : list2);
        ArrayList arrayList2 = new ArrayList();
        ModuleDescriptorImpl builtInsModule = getBuiltIns().getBuiltInsModule();
        builtInsModule.getClass();
        arrayList2.add(builtInsModule);
        this.mutableDependenciesForAllModuleDescriptors = arrayList2;
        this.mutableDependenciesForAllModules = new ArrayList();
        Map<KotlinLibrary, ModuleDescriptorImpl> mapKeysToMap = org.jetbrains.kotlin.utils.CollectionsKt.keysToMap(all, new Function1() { // from class: org.jetbrains.kotlin.cli.metadata.a
            public final Object invoke(Object obj) {
                return KlibMetadataDependencyContainer.b(this.b, (KotlinLibrary) obj);
            }
        });
        Collection<ModuleDescriptorImpl> collectionValues = mapKeysToMap.values();
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            ((ModuleDescriptorImpl) it.next()).setDependencies(this.mutableDependenciesForAllModuleDescriptors);
        }
        this.mutableDependenciesForAllModuleDescriptors.addAll(collectionValues);
        this.moduleDescriptorsForKotlinLibraries = mapKeysToMap;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList(mapKeysToMap.size());
        for (Map.Entry<KotlinLibrary, ModuleDescriptorImpl> entry : mapKeysToMap.entrySet()) {
            KotlinLibrary key = entry.getKey();
            Name name = entry.getValue().getName();
            name.getClass();
            arrayList4.add(new KlibModuleInfo(name, key, this.mutableDependenciesForAllModules));
        }
        arrayList3.addAll(arrayList4);
        this.mutableDependenciesForAllModules.addAll(arrayList3);
        this.moduleInfos = arrayList3;
        List<ModuleInfo> moduleInfos = getModuleInfos();
        ArrayList arrayList5 = new ArrayList();
        for (Object obj : moduleInfos) {
            if (this.friendPaths.contains(((KlibModuleInfo) obj).getKotlinLibrary().getLibraryFile().getAbsolutePath())) {
                arrayList5.add(obj);
            }
        }
        this.friendModuleInfos = arrayList5;
        List<ModuleInfo> moduleInfos2 = getModuleInfos();
        ArrayList arrayList6 = new ArrayList();
        for (Object obj2 : moduleInfos2) {
            if (this.refinesPaths.contains(((KlibModuleInfo) obj2).getKotlinLibrary().getLibraryFile().getAbsolutePath())) {
                arrayList6.add(obj2);
            }
        }
        this.refinesModuleInfos = arrayList6;
        this.klibMetadataModuleDescriptorFactory = LazyKt.lazy(new Function0() { // from class: org.jetbrains.kotlin.cli.metadata.b
            public final Object invoke() {
                return KlibMetadataDependencyContainer.a();
            }
        });
    }

    public static KlibMetadataModuleDescriptorFactoryImpl a() {
        return new KlibMetadataModuleDescriptorFactoryImpl(K1MetadataKlibSerializerKt.MetadataFactories.getDefaultDescriptorFactory(), K1MetadataKlibSerializerKt.MetadataFactories.getDefaultPackageFragmentsFactory(), K1MetadataKlibSerializerKt.MetadataFactories.getFlexibleTypeDeserializer(), (AdditionalClassPartsProvider) null, (List) null, 24, (DefaultConstructorMarker) null);
    }

    public static ModuleDescriptorImpl b(KlibMetadataDependencyContainer klibMetadataDependencyContainer, KotlinLibrary kotlinLibrary) {
        kotlinLibrary.getClass();
        KlibMetadataComponent component = kotlinLibrary.getComponent(KlibMetadataComponent.Kind);
        component.getClass();
        Name nameSpecial = Name.special(KlibMetadataDeserializationUtilsKt.parseModuleHeader(component.getModuleHeaderData()).getModuleName());
        nameSpecial.getClass();
        return KlibModuleDescriptorFactory.createDescriptor$default(K1MetadataKlibSerializerKt.MetadataFactories.getDefaultDescriptorFactory(), nameSpecial, klibMetadataDependencyContainer.storageManager, klibMetadataDependencyContainer.getBuiltIns(), new DeserializedKlibModuleOrigin(kotlinLibrary), (Map) null, 16, (Object) null);
    }

    private final DefaultBuiltIns getBuiltIns() {
        return DefaultBuiltIns.Companion.getInstance();
    }

    private final KlibMetadataModuleDescriptorFactoryImpl getKlibMetadataModuleDescriptorFactory() {
        return (KlibMetadataModuleDescriptorFactoryImpl) this.klibMetadataModuleDescriptorFactory.getValue();
    }

    private final PackageFragmentProvider packageFragmentProviderForKotlinLibrary(KotlinLibrary library) {
        LanguageVersionSettings languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(this.configuration);
        ModuleDescriptorImpl moduleDescriptorImpl = (ModuleDescriptorImpl) MapsKt.getValue(this.moduleDescriptorsForKotlinLibraries, library);
        PackageFragmentProvider packageFragmentProviderCreatePackageFragmentProvider = getKlibMetadataModuleDescriptorFactory().createPackageFragmentProvider(library, (CustomMetadataProtoLoader) null, new LockBasedStorageManager("KlibMetadataPackageFragmentProvider"), moduleDescriptorImpl, new KlibCompilerDeserializationConfiguration(languageVersionSettings), (PackageFragmentProvider) null, LookupTracker.DO_NOTHING.INSTANCE);
        moduleDescriptorImpl.initialize(packageFragmentProviderCreatePackageFragmentProvider);
        return packageFragmentProviderCreatePackageFragmentProvider;
    }

    public List<ModuleInfo> getFriendModuleInfos() {
        return this.friendModuleInfos;
    }

    public List<ModuleInfo> getModuleInfos() {
        return this.moduleInfos;
    }

    public List<ModuleInfo> getRefinesModuleInfos() {
        return this.refinesModuleInfos;
    }

    public ModuleDescriptor moduleDescriptorForModuleInfo(ModuleInfo moduleInfo) {
        moduleInfo.getClass();
        if (getModuleInfos().contains(moduleInfo)) {
            packageFragmentProviderForModuleInfo(moduleInfo);
            return (ModuleDescriptor) MapsKt.getValue(this.moduleDescriptorsForKotlinLibraries, ((KlibModuleInfo) moduleInfo).getKotlinLibrary());
        }
        w04.a("Unknown module info ", moduleInfo);
        return null;
    }

    public PackageFragmentProvider packageFragmentProviderForModuleInfo(ModuleInfo moduleInfo) {
        moduleInfo.getClass();
        if (getModuleInfos().contains(moduleInfo)) {
            return packageFragmentProviderForKotlinLibrary(((KlibModuleInfo) moduleInfo).getKotlinLibrary());
        }
        return null;
    }

    public void registerDependencyForAllModules(ModuleInfo moduleInfo, ModuleDescriptorImpl descriptorForModule) {
        moduleInfo.getClass();
        descriptorForModule.getClass();
        this.mutableDependenciesForAllModules.add(moduleInfo);
        this.mutableDependenciesForAllModuleDescriptors.add(descriptorForModule);
    }
}
