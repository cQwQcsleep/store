package org.jetbrains.kotlin.descriptors;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.storage.StorageManager;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.SimpleType;
import org.jetbrains.kotlin.types.TypeConstructor;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0001H\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0092\u0001\u0010\u0010\u001a\n \u0012*\u0004\u0018\u0001H\u0011H\u0011\"\u0010\b\u0000\u0010\u0011*\n \u0012*\u0004\u0018\u00010\u00130\u0013\"\u0010\b\u0001\u0010\u0014*\n \u0012*\u0004\u0018\u00010\u00130\u00132F\u0010\u0015\u001aB\u0012\f\u0012\n \u0012*\u0004\u0018\u0001H\u0011H\u0011\u0012\f\u0012\n \u0012*\u0004\u0018\u0001H\u0014H\u0014 \u0012* \u0012\f\u0012\n \u0012*\u0004\u0018\u0001H\u0011H\u0011\u0012\f\u0012\n \u0012*\u0004\u0018\u0001H\u0014H\u0014\u0018\u00010\u00160\u00162\u000e\u0010\u0017\u001a\n \u0012*\u0004\u0018\u0001H\u0014H\u0014H\u0096\u0001¢\u0006\u0002\u0010\u0018JQ\u0010\u0019\u001a\u00020\u001a2F\u0010\u0015\u001aB\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u001b0\u001b\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u001b0\u001b \u0012* \u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u001b0\u001b\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u001b0\u001b\u0018\u00010\u00160\u0016H\u0096\u0001J\u000e\u0010\u001c\u001a\u00070\u001d¢\u0006\u0002\b\u001eH\u0097\u0001J\u000e\u0010\u001f\u001a\u00070 ¢\u0006\u0002\b\u001eH\u0097\u0001J\u000e\u0010!\u001a\u00070\"¢\u0006\u0002\b\u001eH\u0097\u0001J\u000e\u0010#\u001a\u00070$¢\u0006\u0002\b\u001eH\u0097\u0001J\u000e\u0010%\u001a\u00070&¢\u0006\u0002\b\u001eH\u0097\u0001J5\u0010'\u001a.\u0012\f\u0012\n \u0012*\u0004\u0018\u00010)0) \u0012*\u0015\u0012\f\u0012\n \u0012*\u0004\u0018\u00010)0)0*¢\u0006\u0002\b\u001e0(¢\u0006\u0002\b\u001eH\u0097\u0001J\u000e\u0010+\u001a\u00070,¢\u0006\u0002\b\u001eH\u0097\u0001J\t\u0010-\u001a\u00020\nH\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010.\u001a\u00020/X\u0096\u0005¢\u0006\u0006\u001a\u0004\b0\u00101¨\u00062"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/CapturedTypeParameterDescriptor;", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "originalDescriptor", "declarationDescriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "declaredTypeParametersCount", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;I)V", "isCapturedFromOuterDeclaration", Argument.Delimiters.none, "getOriginal", "getContainingDeclaration", "getIndex", "toString", Argument.Delimiters.none, "accept", "R", "kotlin.jvm.PlatformType", Argument.Delimiters.none, "D", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "data", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "acceptVoid", Argument.Delimiters.none, "Ljava/lang/Void;", "getDefaultType", "Lorg/jetbrains/kotlin/types/SimpleType;", "Lkotlin/jvm/internal/EnhancedNullability;", "getName", "Lorg/jetbrains/kotlin/name/Name;", "getSource", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "getStorageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "getTypeConstructor", "Lorg/jetbrains/kotlin/types/TypeConstructor;", "getUpperBounds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/KotlinType;", Argument.Delimiters.none, "getVariance", "Lorg/jetbrains/kotlin/types/Variance;", "isReified", "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "getAnnotations", "()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class CapturedTypeParameterDescriptor implements TypeParameterDescriptor {
    private final DeclarationDescriptor declarationDescriptor;
    private final int declaredTypeParametersCount;
    private final TypeParameterDescriptor originalDescriptor;

    public CapturedTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor, DeclarationDescriptor declarationDescriptor, int i) {
        typeParameterDescriptor.getClass();
        declarationDescriptor.getClass();
        this.originalDescriptor = typeParameterDescriptor;
        this.declarationDescriptor = declarationDescriptor;
        this.declaredTypeParametersCount = i;
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        return (R) this.originalDescriptor.accept(visitor, data);
    }

    public void acceptVoid(DeclarationDescriptorVisitor<Void, Void> visitor) {
        this.originalDescriptor.acceptVoid(visitor);
    }

    public Annotations getAnnotations() {
        return this.originalDescriptor.getAnnotations();
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptorNonRoot
    /* JADX INFO: renamed from: getContainingDeclaration, reason: from getter */
    public DeclarationDescriptor getDeclarationDescriptor() {
        return this.declarationDescriptor;
    }

    @Override // org.jetbrains.kotlin.descriptors.ClassifierDescriptor
    public SimpleType getDefaultType() {
        SimpleType defaultType = this.originalDescriptor.getDefaultType();
        defaultType.getClass();
        return defaultType;
    }

    @Override // org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
    public int getIndex() {
        return this.declaredTypeParametersCount + this.originalDescriptor.getIndex();
    }

    public Name getName() {
        Name name = this.originalDescriptor.getName();
        name.getClass();
        return name;
    }

    @Override // org.jetbrains.kotlin.descriptors.TypeParameterDescriptor, org.jetbrains.kotlin.descriptors.ClassifierDescriptor
    /* JADX INFO: renamed from: getOriginal */
    public TypeParameterDescriptor mo86getOriginal() {
        TypeParameterDescriptor typeParameterDescriptorMo86getOriginal = this.originalDescriptor.mo86getOriginal();
        typeParameterDescriptorMo86getOriginal.getClass();
        return typeParameterDescriptorMo86getOriginal;
    }

    public SourceElement getSource() {
        SourceElement source = this.originalDescriptor.getSource();
        source.getClass();
        return source;
    }

    @Override // org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
    public StorageManager getStorageManager() {
        StorageManager storageManager = this.originalDescriptor.getStorageManager();
        storageManager.getClass();
        return storageManager;
    }

    @Override // org.jetbrains.kotlin.descriptors.TypeParameterDescriptor, org.jetbrains.kotlin.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        TypeConstructor typeConstructor = this.originalDescriptor.getTypeConstructor();
        typeConstructor.getClass();
        return typeConstructor;
    }

    @Override // org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
    public List<KotlinType> getUpperBounds() {
        List<KotlinType> upperBounds = this.originalDescriptor.getUpperBounds();
        upperBounds.getClass();
        return upperBounds;
    }

    @Override // org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
    public Variance getVariance() {
        Variance variance = this.originalDescriptor.getVariance();
        variance.getClass();
        return variance;
    }

    @Override // org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
    public boolean isCapturedFromOuterDeclaration() {
        return true;
    }

    @Override // org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
    public boolean isReified() {
        return this.originalDescriptor.isReified();
    }

    public String toString() {
        return this.originalDescriptor + "[inner-copy]";
    }

    @Override // org.jetbrains.kotlin.descriptors.TypeParameterDescriptor, org.jetbrains.kotlin.descriptors.ClassifierDescriptor
    /* JADX INFO: renamed from: getOriginal, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ DeclarationDescriptor mo86getOriginal() {
        return (DeclarationDescriptor) mo86getOriginal();
    }
}
