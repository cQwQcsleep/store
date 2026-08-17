package org.jetbrains.kotlin.fir.descriptors;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.PackageViewDescriptor;
import org.jetbrains.kotlin.descriptors.PackageViewDescriptor$DefaultImpls;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016JC\u0010\u001c\u001a\u0002H\u001d\"\n\b\u0000\u0010\u001d*\u0004\u0018\u00010\u001e\"\n\b\u0001\u0010\u001f*\u0004\u0018\u00010\u001e2\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001f\u0018\u00010!2\u0006\u0010\"\u001a\u0002H\u001fH\u0016¢\u0006\u0002\u0010#J\u001e\u0010$\u001a\u00020%2\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0018\u00010!H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000bR\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010'\u001a\u00020(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/fir/descriptors/FirPackageViewDescriptor;", "Lorg/jetbrains/kotlin/descriptors/PackageViewDescriptor;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;)V", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getModuleDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getContainingDeclaration", "memberScope", "Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "getMemberScope", "()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", ModuleXmlParser.MODULE, "getModule", "fragments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "getFragments", "()Ljava/util/List;", "getOriginal", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "getName", "Lorg/jetbrains/kotlin/name/Name;", "accept", "R", Argument.Delimiters.none, "D", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "data", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "acceptVoid", Argument.Delimiters.none, "Ljava/lang/Void;", "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "getAnnotations", "()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPackageViewDescriptor implements PackageViewDescriptor {
    private final FqName fqName;
    private final ModuleDescriptor moduleDescriptor;

    public FirPackageViewDescriptor(FqName fqName, ModuleDescriptor moduleDescriptor) {
        fqName.getClass();
        moduleDescriptor.getClass();
        this.fqName = fqName;
        this.moduleDescriptor = moduleDescriptor;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void acceptVoid(DeclarationDescriptorVisitor<Void, Void> visitor) throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    public Annotations getAnnotations() {
        return Annotations.INSTANCE.getEMPTY();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public PackageViewDescriptor getContainingDeclaration() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    public FqName getFqName() {
        return this.fqName;
    }

    public List<PackageFragmentDescriptor> getFragments() {
        return CollectionsKt.listOf(new FirPackageFragmentDescriptor(getFqName(), this.moduleDescriptor));
    }

    public MemberScope getMemberScope() {
        return MemberScope.Empty.INSTANCE;
    }

    /* JADX INFO: renamed from: getModule, reason: from getter */
    public ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public Name getName() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public DeclarationDescriptor getOriginal() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    public /* bridge */ boolean isEmpty() {
        return PackageViewDescriptor$DefaultImpls.isEmpty(this);
    }
}
