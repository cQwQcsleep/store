package org.jetbrains.kotlin.descriptors;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0000H&J7\u0010\u0012\u001a\u0004\u0018\u0001H\u0013\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00140\u00162\u0006\u0010\u0017\u001a\u0002H\u0014H\u0016¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH&J*\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100 H&J#\u0010+\u001a\u0004\u0018\u0001H,\"\u0004\b\u0000\u0010,2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0.H&¢\u0006\u0002\u0010/J\b\u00102\u001a\u000203H&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u0004\u0018\u00010\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0018\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00000\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0018\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00000\"X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010$R\u0018\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00000(X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0012\u00100\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b0\u00101¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "getContainingDeclaration", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "getBuiltIns", "()Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "stableName", "Lorg/jetbrains/kotlin/name/Name;", "getStableName", "()Lorg/jetbrains/kotlin/name/Name;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "shouldSeeInternalsOf", Argument.Delimiters.none, "targetModule", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "data", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "getPackage", "Lorg/jetbrains/kotlin/descriptors/PackageViewDescriptor;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "getSubPackagesOf", Argument.Delimiters.none, "nameFilter", "Lkotlin/Function1;", "allDependencyModules", Argument.Delimiters.none, "getAllDependencyModules", "()Ljava/util/List;", "expectedByModules", "getExpectedByModules", "allExpectedByModules", Argument.Delimiters.none, "getAllExpectedByModules", "()Ljava/util/Set;", "getCapability", "T", "capability", "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "(Lorg/jetbrains/kotlin/descriptors/ModuleCapability;)Ljava/lang/Object;", "isValid", "()Z", "assertValid", Argument.Delimiters.none, "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ModuleDescriptor extends DeclarationDescriptor {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DefaultImpls {
        public static <R, D> R accept(ModuleDescriptor moduleDescriptor, DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
            declarationDescriptorVisitor.getClass();
            return (R) declarationDescriptorVisitor.visitModuleDeclaration(moduleDescriptor, d);
        }

        public static DeclarationDescriptor getContainingDeclaration(ModuleDescriptor moduleDescriptor) {
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data);

    void assertValid();

    List<ModuleDescriptor> getAllDependencyModules();

    Set<ModuleDescriptor> getAllExpectedByModules();

    KotlinBuiltIns getBuiltIns();

    <T> T getCapability(ModuleCapability<T> capability);

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    DeclarationDescriptor getContainingDeclaration();

    List<ModuleDescriptor> getExpectedByModules();

    PackageViewDescriptor getPackage(FqName fqName);

    TargetPlatform getPlatform();

    Name getStableName();

    Collection<FqName> getSubPackagesOf(FqName fqName, Function1<? super Name, Boolean> nameFilter);

    boolean isValid();

    boolean shouldSeeInternalsOf(ModuleDescriptor targetModule);
}
