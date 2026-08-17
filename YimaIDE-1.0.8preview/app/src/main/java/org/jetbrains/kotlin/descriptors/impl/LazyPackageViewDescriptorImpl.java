package org.jetbrains.kotlin.descriptors.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentProviderKt;
import org.jetbrains.kotlin.descriptors.PackageViewDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.LazyPackageViewDescriptorImpl;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.scopes.ChainedMemberScope;
import org.jetbrains.kotlin.resolve.scopes.LazyScopeAdapter;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;
import org.jetbrains.kotlin.storage.NotNullLazyValue;
import org.jetbrains.kotlin.storage.StorageKt;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u001b\u001a\u00020\u0017H\u0016J\n\u0010 \u001a\u0004\u0018\u00010\u0002H\u0016J\u0014\u0010!\u001a\u00020\u00172\b\u0010\"\u001a\u0004\u0018\u00010#H\u0096\u0082\u0004J\n\u0010$\u001a\u00020%H\u0096\u0080\u0004J5\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010'\"\u0004\b\u0001\u0010(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H(0*2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u0010,R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0016\u001a\u00020\u00178DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u001dX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/LazyPackageViewDescriptorImpl;", "Lorg/jetbrains/kotlin/descriptors/impl/DeclarationDescriptorImpl;", "Lorg/jetbrains/kotlin/descriptors/PackageViewDescriptor;", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/storage/StorageManager;)V", "getModule", "()Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "fragments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "getFragments", "()Ljava/util/List;", "fragments$delegate", "Lorg/jetbrains/kotlin/storage/NotNullLazyValue;", "empty", Argument.Delimiters.none, "getEmpty", "()Z", "empty$delegate", "isEmpty", "memberScope", "Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "getMemberScope", "()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "getContainingDeclaration", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "data", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class LazyPackageViewDescriptorImpl extends DeclarationDescriptorImpl implements PackageViewDescriptor {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(LazyPackageViewDescriptorImpl.class, "fragments", "getFragments()Ljava/util/List;", 0), new PropertyReference1Impl<>(LazyPackageViewDescriptorImpl.class, "empty", "getEmpty()Z", 0)};

    /* JADX INFO: renamed from: empty$delegate, reason: from kotlin metadata */
    private final NotNullLazyValue empty;
    private final FqName fqName;

    /* JADX INFO: renamed from: fragments$delegate, reason: from kotlin metadata */
    private final NotNullLazyValue fragments;
    private final MemberScope memberScope;
    private final ModuleDescriptorImpl module;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyPackageViewDescriptorImpl(ModuleDescriptorImpl moduleDescriptorImpl, FqName fqName, StorageManager storageManager) {
        super(Annotations.INSTANCE.getEMPTY(), fqName.shortNameOrSpecial());
        moduleDescriptorImpl.getClass();
        fqName.getClass();
        storageManager.getClass();
        this.module = moduleDescriptorImpl;
        this.fqName = fqName;
        this.fragments = storageManager.createLazyValue(new Function0() { // from class: aw8
            public final Object invoke() {
                return LazyPackageViewDescriptorImpl.a(this.b);
            }
        });
        this.empty = storageManager.createLazyValue(new Function0() { // from class: bw8
            public final Object invoke() {
                return Boolean.valueOf(LazyPackageViewDescriptorImpl.b(this.b));
            }
        });
        this.memberScope = new LazyScopeAdapter(storageManager, new Function0() { // from class: cw8
            public final Object invoke() {
                return LazyPackageViewDescriptorImpl.c(this.b);
            }
        });
    }

    public static List a(LazyPackageViewDescriptorImpl lazyPackageViewDescriptorImpl) {
        return PackageFragmentProviderKt.packageFragments(lazyPackageViewDescriptorImpl.getModule().getPackageFragmentProvider(), lazyPackageViewDescriptorImpl.getFqName());
    }

    public static boolean b(LazyPackageViewDescriptorImpl lazyPackageViewDescriptorImpl) {
        return PackageFragmentProviderKt.isEmpty(lazyPackageViewDescriptorImpl.getModule().getPackageFragmentProvider(), lazyPackageViewDescriptorImpl.getFqName());
    }

    public static MemberScope c(LazyPackageViewDescriptorImpl lazyPackageViewDescriptorImpl) {
        if (lazyPackageViewDescriptorImpl.isEmpty()) {
            return MemberScope.Empty.INSTANCE;
        }
        List<PackageFragmentDescriptor> fragments = lazyPackageViewDescriptorImpl.getFragments();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(fragments, 10));
        Iterator<T> it = fragments.iterator();
        while (it.hasNext()) {
            arrayList.add(((PackageFragmentDescriptor) it.next()).getMemberScope());
        }
        List listPlus = CollectionsKt.plus(arrayList, new SubpackagesScope(lazyPackageViewDescriptorImpl.getModule(), lazyPackageViewDescriptorImpl.getFqName()));
        return ChainedMemberScope.Companion.create("package view scope for " + lazyPackageViewDescriptorImpl.getFqName() + " in " + lazyPackageViewDescriptorImpl.getModule().getName(), listPlus);
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        visitor.getClass();
        return (R) visitor.visitPackageViewDescriptor(this, data);
    }

    public boolean equals(Object other) {
        PackageViewDescriptor packageViewDescriptor = other instanceof PackageViewDescriptor ? (PackageViewDescriptor) other : null;
        return packageViewDescriptor != null && Intrinsics.areEqual(getFqName(), packageViewDescriptor.getFqName()) && Intrinsics.areEqual(getModule(), packageViewDescriptor.getModule());
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public PackageViewDescriptor getContainingDeclaration() {
        if (getFqName().isRoot()) {
            return null;
        }
        return getModule().getPackage(getFqName().parent());
    }

    public final boolean getEmpty() {
        return ((Boolean) StorageKt.getValue(this.empty, this, $$delegatedProperties[1])).booleanValue();
    }

    public FqName getFqName() {
        return this.fqName;
    }

    public List<PackageFragmentDescriptor> getFragments() {
        return (List) StorageKt.getValue(this.fragments, this, $$delegatedProperties[0]);
    }

    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    public int hashCode() {
        return (getModule().hashCode() * 31) + getFqName().hashCode();
    }

    public boolean isEmpty() {
        return getEmpty();
    }

    public ModuleDescriptorImpl getModule() {
        return this.module;
    }
}
