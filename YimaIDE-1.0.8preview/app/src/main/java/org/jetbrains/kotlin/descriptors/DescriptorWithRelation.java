package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/DescriptorWithRelation;", Argument.Delimiters.none, "descriptor", "Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptor;", "relation", "Lorg/jetbrains/kotlin/descriptors/RelationToType;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptor;Lorg/jetbrains/kotlin/descriptors/RelationToType;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptor;", "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "toString", Argument.Delimiters.none, "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class DescriptorWithRelation {
    private final ClassifierDescriptor descriptor;
    private final RelationToType relation;

    public DescriptorWithRelation(ClassifierDescriptor classifierDescriptor, RelationToType relationToType) {
        classifierDescriptor.getClass();
        relationToType.getClass();
        this.descriptor = classifierDescriptor;
        this.relation = relationToType;
    }

    public static /* synthetic */ DescriptorWithRelation copy$default(DescriptorWithRelation descriptorWithRelation, ClassifierDescriptor classifierDescriptor, RelationToType relationToType, int i, Object obj) {
        if ((i & 1) != 0) {
            classifierDescriptor = descriptorWithRelation.descriptor;
        }
        if ((i & 2) != 0) {
            relationToType = descriptorWithRelation.relation;
        }
        return descriptorWithRelation.copy(classifierDescriptor, relationToType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ClassifierDescriptor getDescriptor() {
        return this.descriptor;
    }

    public final DescriptorWithRelation copy(ClassifierDescriptor descriptor, RelationToType relation) {
        descriptor.getClass();
        relation.getClass();
        return new DescriptorWithRelation(descriptor, relation);
    }

    public final EffectiveVisibility effectiveVisibility() {
        DescriptorVisibility visibility;
        EffectiveVisibility effectiveVisibility;
        ClassDescriptor classDescriptor = this.descriptor;
        ClassDescriptor classDescriptor2 = classDescriptor instanceof ClassDescriptor ? classDescriptor : null;
        return (classDescriptor2 == null || (visibility = classDescriptor2.getVisibility()) == null || (effectiveVisibility = EffectiveVisibilityUtilsKt.effectiveVisibility(visibility, (DeclarationDescriptor) this.descriptor, false)) == null) ? EffectiveVisibility.Public.INSTANCE : effectiveVisibility;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DescriptorWithRelation)) {
            return false;
        }
        DescriptorWithRelation descriptorWithRelation = (DescriptorWithRelation) other;
        return Intrinsics.areEqual(this.descriptor, descriptorWithRelation.descriptor) && this.relation == descriptorWithRelation.relation;
    }

    public final ClassifierDescriptor getDescriptor() {
        return this.descriptor;
    }

    public int hashCode() {
        return (this.descriptor.hashCode() * 31) + this.relation.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.relation);
        sb.append(' ');
        sb.append(this.descriptor.getName());
        return sb.toString();
    }
}
