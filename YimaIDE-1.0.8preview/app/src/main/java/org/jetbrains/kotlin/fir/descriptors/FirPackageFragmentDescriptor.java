package org.jetbrains.kotlin.fir.descriptors;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithSource;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\u0005H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016JC\u0010\u0013\u001a\u0002H\u0014\"\n\b\u0000\u0010\u0014*\u0004\u0018\u00010\u0015\"\n\b\u0001\u0010\u0016*\u0004\u0018\u00010\u00152\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u0002H\u0016H\u0016¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u001e\u0010\u001d\u001a\u00020\u001e2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u0018H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/descriptors/FirPackageFragmentDescriptor;", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;)V", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getModuleDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getContainingDeclaration", "getMemberScope", "Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "getOriginal", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorWithSource;", "getName", "Lorg/jetbrains/kotlin/name/Name;", "accept", "R", Argument.Delimiters.none, "D", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "data", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "getSource", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "acceptVoid", Argument.Delimiters.none, "Ljava/lang/Void;", "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "getAnnotations", "()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirPackageFragmentDescriptor implements PackageFragmentDescriptor {
    private final FqName fqName;
    private final ModuleDescriptor moduleDescriptor;

    public FirPackageFragmentDescriptor(FqName fqName, ModuleDescriptor moduleDescriptor) {
        fqName.getClass();
        moduleDescriptor.getClass();
        this.fqName = fqName;
        this.moduleDescriptor = moduleDescriptor;
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        if (visitor != null) {
            return (R) visitor.visitPackageFragmentDescriptor(this, data);
        }
        return null;
    }

    public void acceptVoid(DeclarationDescriptorVisitor<Void, Void> visitor) {
        if (visitor != null) {
        }
    }

    public Annotations getAnnotations() {
        return Annotations.INSTANCE.getEMPTY();
    }

    public FqName getFqName() {
        return this.fqName;
    }

    public MemberScope getMemberScope() {
        return MemberScope.Empty.INSTANCE;
    }

    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    public Name getName() {
        return getFqName().shortName();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public SourceElement getSource() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    public ModuleDescriptor getContainingDeclaration() {
        return this.moduleDescriptor;
    }

    public DeclarationDescriptorWithSource getOriginal() {
        return this;
    }
}
