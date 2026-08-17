package org.jetbrains.kotlin.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.InvalidModuleExceptionKt;
import org.jetbrains.kotlin.descriptors.ModuleCapability;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.jetbrains.kotlin.descriptors.PackageViewDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.storage.MemoizedFunctionToNotNull;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u00012\u00020\u0002BU\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u001a\b\u0002\u0010\u000b\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010&\u001a\u00020'H\u0016J\u0010\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020*H\u0016J*\u0010<\u001a\b\u0012\u0004\u0012\u00020*0=2\u0006\u0010;\u001a\u00020*2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"0?H\u0016J\u000e\u0010G\u001a\u00020'2\u0006\u0010\u001a\u001a\u00020\u001bJ\u001f\u0010G\u001a\u00020'2\u0012\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000I\"\u00020\u0000¢\u0006\u0002\u0010JJ\u0014\u0010G\u001a\u00020'2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00000-J\"\u0010G\u001a\u00020'2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00000-2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u000007J\u0010\u0010L\u001a\u00020\"2\u0006\u0010M\u001a\u00020\u0002H\u0016J\u000e\u0010R\u001a\u00020'2\u0006\u0010S\u001a\u00020\u001dJ#\u0010V\u001a\u0004\u0018\u0001HW\"\u0004\b\u0000\u0010W2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HW0\rH\u0016¢\u0006\u0002\u0010YJ\n\u0010Z\u001a\u00020OH\u0096\u0080\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R \u0010\u000b\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010#\"\u0004\b$\u0010%R\u001a\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0)X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010,\u001a\b\u0012\u0004\u0012\u00020\u00000-8FX\u0087\u0004¢\u0006\f\u0012\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u00101R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00101R\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020\u0002078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u001b\u0010@\u001a\u00020A8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bB\u0010CR\u0014\u0010F\u001a\u00020\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bF\u0010#R\u0014\u0010N\u001a\u00020O8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0011\u0010T\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\bU\u0010 ¨\u0006["}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "Lorg/jetbrains/kotlin/descriptors/impl/DeclarationDescriptorImpl;", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "moduleName", "Lorg/jetbrains/kotlin/name/Name;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "capabilities", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", Argument.Delimiters.none, "stableName", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/storage/StorageManager;Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;Lorg/jetbrains/kotlin/platform/TargetPlatform;Ljava/util/Map;Lorg/jetbrains/kotlin/name/Name;)V", "getBuiltIns", "()Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getStableName", "()Lorg/jetbrains/kotlin/name/Name;", "packageViewDescriptorFactory", "Lorg/jetbrains/kotlin/descriptors/impl/PackageViewDescriptorFactory;", "dependencies", "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDependencies;", "packageFragmentProviderForModuleContent", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "packageFragmentProviderForModuleContentWithoutDependencies", "getPackageFragmentProviderForModuleContentWithoutDependencies", "()Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "isValid", Argument.Delimiters.none, "()Z", "setValid", "(Z)V", "assertValid", Argument.Delimiters.none, "packages", "Lorg/jetbrains/kotlin/storage/MemoizedFunctionToNotNull;", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/descriptors/PackageViewDescriptor;", "testOnly_AllDependentModules", Argument.Delimiters.none, "getTestOnly_AllDependentModules$annotations", "()V", "getTestOnly_AllDependentModules", "()Ljava/util/List;", "allDependencyModules", "getAllDependencyModules", "expectedByModules", "getExpectedByModules", "allExpectedByModules", Argument.Delimiters.none, "getAllExpectedByModules", "()Ljava/util/Set;", "getPackage", "fqName", "getSubPackagesOf", Argument.Delimiters.none, "nameFilter", "Lkotlin/Function1;", "packageFragmentProviderForWholeModuleWithDependencies", "Lorg/jetbrains/kotlin/descriptors/impl/CompositePackageFragmentProvider;", "getPackageFragmentProviderForWholeModuleWithDependencies", "()Lorg/jetbrains/kotlin/descriptors/impl/CompositePackageFragmentProvider;", "packageFragmentProviderForWholeModuleWithDependencies$delegate", "Lkotlin/Lazy;", "isInitialized", "setDependencies", "descriptors", Argument.Delimiters.none, "([Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;)V", "friends", "shouldSeeInternalsOf", "targetModule", "id", Argument.Delimiters.none, "getId", "()Ljava/lang/String;", "initialize", "providerForModuleContent", "packageFragmentProvider", "getPackageFragmentProvider", "getCapability", "T", "capability", "(Lorg/jetbrains/kotlin/descriptors/ModuleCapability;)Ljava/lang/Object;", "toString", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ModuleDescriptorImpl extends DeclarationDescriptorImpl implements ModuleDescriptor {
    private final KotlinBuiltIns builtIns;
    private final Map<ModuleCapability<?>, Object> capabilities;
    private ModuleDependencies dependencies;
    private boolean isValid;
    private PackageFragmentProvider packageFragmentProviderForModuleContent;

    /* JADX INFO: renamed from: packageFragmentProviderForWholeModuleWithDependencies$delegate, reason: from kotlin metadata */
    private final Lazy packageFragmentProviderForWholeModuleWithDependencies;
    private final PackageViewDescriptorFactory packageViewDescriptorFactory;
    private final MemoizedFunctionToNotNull<FqName, PackageViewDescriptor> packages;
    private final TargetPlatform platform;
    private final Name stableName;
    private final StorageManager storageManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDescriptorImpl(Name name, StorageManager storageManager, KotlinBuiltIns kotlinBuiltIns, TargetPlatform targetPlatform, Map<ModuleCapability<?>, ? extends Object> map, Name name2) {
        super(Annotations.INSTANCE.getEMPTY(), name);
        name.getClass();
        storageManager.getClass();
        kotlinBuiltIns.getClass();
        map.getClass();
        this.storageManager = storageManager;
        this.builtIns = kotlinBuiltIns;
        this.platform = targetPlatform;
        this.stableName = name2;
        if (!name.isSpecial()) {
            aca.a("Module name must be special: ", name);
            throw null;
        }
        this.capabilities = map;
        PackageViewDescriptorFactory packageViewDescriptorFactory = (PackageViewDescriptorFactory) getCapability(PackageViewDescriptorFactory.INSTANCE.getCAPABILITY());
        this.packageViewDescriptorFactory = packageViewDescriptorFactory == null ? PackageViewDescriptorFactory.Default.INSTANCE : packageViewDescriptorFactory;
        this.isValid = true;
        this.packages = storageManager.createMemoizedFunction(new Function1() { // from class: y4a
            public final Object invoke(Object obj) {
                return ModuleDescriptorImpl.a(this.b, (FqName) obj);
            }
        });
        this.packageFragmentProviderForWholeModuleWithDependencies = LazyKt.lazy(new Function0() { // from class: z4a
            public final Object invoke() {
                return ModuleDescriptorImpl.b(this.b);
            }
        });
    }

    public static PackageViewDescriptor a(ModuleDescriptorImpl moduleDescriptorImpl, FqName fqName) {
        fqName.getClass();
        return moduleDescriptorImpl.packageViewDescriptorFactory.compute(moduleDescriptorImpl, fqName, moduleDescriptorImpl.storageManager);
    }

    public static CompositePackageFragmentProvider b(ModuleDescriptorImpl moduleDescriptorImpl) {
        ModuleDependencies moduleDependencies = moduleDescriptorImpl.dependencies;
        if (moduleDependencies == null) {
            hih.a("Dependencies of module ", moduleDescriptorImpl.getId(), " were not set before querying module content");
            return null;
        }
        List<ModuleDescriptorImpl> allDependencies = moduleDependencies.getAllDependencies();
        moduleDescriptorImpl.assertValid();
        allDependencies.contains(moduleDescriptorImpl);
        List<ModuleDescriptorImpl> list = allDependencies;
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ((ModuleDescriptorImpl) it.next()).isInitialized();
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            PackageFragmentProvider packageFragmentProvider = ((ModuleDescriptorImpl) it2.next()).packageFragmentProviderForModuleContent;
            packageFragmentProvider.getClass();
            arrayList.add(packageFragmentProvider);
        }
        return new CompositePackageFragmentProvider(arrayList, "CompositeProvider@ModuleDescriptor for " + moduleDescriptorImpl.getName());
    }

    private final String getId() {
        String string = getName().toString();
        string.getClass();
        return string;
    }

    private final CompositePackageFragmentProvider getPackageFragmentProviderForWholeModuleWithDependencies() {
        return (CompositePackageFragmentProvider) this.packageFragmentProviderForWholeModuleWithDependencies.getValue();
    }

    @Deprecated(message = "This method is not going to be supported. Please do not use it")
    public static /* synthetic */ void getTestOnly_AllDependentModules$annotations() {
    }

    private final boolean isInitialized() {
        return this.packageFragmentProviderForModuleContent != null;
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public /* bridge */ <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return (R) ModuleDescriptor.DefaultImpls.accept(this, declarationDescriptorVisitor, d);
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public void assertValid() {
        if (getIsValid()) {
            return;
        }
        InvalidModuleExceptionKt.moduleInvalidated(this);
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public List<ModuleDescriptor> getAllDependencyModules() {
        ModuleDependencies moduleDependencies = this.dependencies;
        if (moduleDependencies == null) {
            hih.a("Dependencies of module ", getId(), " were not set");
            return null;
        }
        List<ModuleDescriptorImpl> allDependencies = moduleDependencies.getAllDependencies();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allDependencies) {
            if (!Intrinsics.areEqual((ModuleDescriptorImpl) obj, this)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public Set<ModuleDescriptor> getAllExpectedByModules() {
        ModuleDependencies moduleDependencies = this.dependencies;
        if (moduleDependencies != null) {
            return moduleDependencies.getAllExpectedByDependencies();
        }
        hih.a("Dependencies of module ", getId(), " were not set");
        return null;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public KotlinBuiltIns getBuiltIns() {
        return this.builtIns;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public <T> T getCapability(ModuleCapability<T> capability) {
        capability.getClass();
        T t = (T) this.capabilities.get(capability);
        if (t == null) {
            return null;
        }
        return t;
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public /* bridge */ DeclarationDescriptor getContainingDeclaration() {
        return ModuleDescriptor.DefaultImpls.getContainingDeclaration(this);
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public List<ModuleDescriptor> getExpectedByModules() {
        ModuleDependencies moduleDependencies = this.dependencies;
        if (moduleDependencies != null) {
            return moduleDependencies.getDirectExpectedByDependencies();
        }
        hih.a("Dependencies of module ", getId(), " were not set");
        return null;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public PackageViewDescriptor getPackage(FqName fqName) {
        fqName.getClass();
        assertValid();
        return (PackageViewDescriptor) this.packages.invoke(fqName);
    }

    public final PackageFragmentProvider getPackageFragmentProvider() {
        assertValid();
        return getPackageFragmentProviderForWholeModuleWithDependencies();
    }

    public final PackageFragmentProvider getPackageFragmentProviderForModuleContentWithoutDependencies() {
        PackageFragmentProvider packageFragmentProvider = this.packageFragmentProviderForModuleContent;
        if (packageFragmentProvider != null) {
            return packageFragmentProvider;
        }
        zia.a("Module ", getId(), " was not initialized by the time it's content without dependencies was queried");
        return null;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public TargetPlatform getPlatform() {
        return this.platform;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public Name getStableName() {
        return this.stableName;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public Collection<FqName> getSubPackagesOf(FqName fqName, Function1<? super Name, Boolean> nameFilter) {
        fqName.getClass();
        nameFilter.getClass();
        assertValid();
        return getPackageFragmentProvider().getSubPackagesOf(fqName, nameFilter);
    }

    public final List<ModuleDescriptorImpl> getTestOnly_AllDependentModules() {
        ModuleDependencies moduleDependencies = this.dependencies;
        moduleDependencies.getClass();
        return moduleDependencies.getAllDependencies();
    }

    public final void initialize(PackageFragmentProvider providerForModuleContent) {
        providerForModuleContent.getClass();
        isInitialized();
        this.packageFragmentProviderForModuleContent = providerForModuleContent;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    /* JADX INFO: renamed from: isValid, reason: from getter */
    public boolean getIsValid() {
        return this.isValid;
    }

    public final void setDependencies(List<ModuleDescriptorImpl> descriptors, Set<ModuleDescriptorImpl> friends) {
        descriptors.getClass();
        friends.getClass();
        setDependencies(new ModuleDependenciesImpl(descriptors, friends, CollectionsKt.emptyList(), SetsKt.emptySet()));
    }

    public void setValid(boolean z) {
        this.isValid = z;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public boolean shouldSeeInternalsOf(ModuleDescriptor targetModule) {
        targetModule.getClass();
        if (Intrinsics.areEqual(this, targetModule)) {
            return true;
        }
        ModuleDependencies moduleDependencies = this.dependencies;
        moduleDependencies.getClass();
        return CollectionsKt.contains(moduleDependencies.getModulesWhoseInternalsAreVisible(), targetModule) || getExpectedByModules().contains(targetModule) || targetModule.getExpectedByModules().contains(this);
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.DeclarationDescriptorImpl
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (!getIsValid()) {
            sb.append(" !isValid");
        }
        sb.append(" packageFragmentProvider: ");
        PackageFragmentProvider packageFragmentProvider = this.packageFragmentProviderForModuleContent;
        sb.append(packageFragmentProvider != null ? packageFragmentProvider.getClass().getSimpleName() : null);
        return sb.toString();
    }

    public final void setDependencies(ModuleDescriptorImpl... descriptors) {
        descriptors.getClass();
        setDependencies(ArraysKt.toList(descriptors));
    }

    public final void setDependencies(List<ModuleDescriptorImpl> descriptors) {
        descriptors.getClass();
        setDependencies(descriptors, SetsKt.emptySet());
    }

    public final void setDependencies(ModuleDependencies dependencies) {
        dependencies.getClass();
        this.dependencies = dependencies;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ModuleDescriptorImpl(Name name, StorageManager storageManager, KotlinBuiltIns kotlinBuiltIns, TargetPlatform targetPlatform) {
        this(name, storageManager, kotlinBuiltIns, targetPlatform, null, null, 48, null);
        name.getClass();
        storageManager.getClass();
        kotlinBuiltIns.getClass();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ModuleDescriptorImpl(Name name, StorageManager storageManager, KotlinBuiltIns kotlinBuiltIns, TargetPlatform targetPlatform, Map<ModuleCapability<?>, ? extends Object> map) {
        this(name, storageManager, kotlinBuiltIns, targetPlatform, map, null, 32, null);
        name.getClass();
        storageManager.getClass();
        kotlinBuiltIns.getClass();
        map.getClass();
    }

    public /* synthetic */ ModuleDescriptorImpl(Name name, StorageManager storageManager, KotlinBuiltIns kotlinBuiltIns, TargetPlatform targetPlatform, Map map, Name name2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(name, storageManager, kotlinBuiltIns, (i & 8) != 0 ? null : targetPlatform, (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) != 0 ? null : name2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ModuleDescriptorImpl(Name name, StorageManager storageManager, KotlinBuiltIns kotlinBuiltIns) {
        this(name, storageManager, kotlinBuiltIns, null, null, null, 56, null);
        name.getClass();
        storageManager.getClass();
        kotlinBuiltIns.getClass();
    }
}
