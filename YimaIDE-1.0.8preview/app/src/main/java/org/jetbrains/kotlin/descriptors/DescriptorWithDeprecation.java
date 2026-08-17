package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u0017*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003:\u0001\u0017B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\r\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J(\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\f¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/DescriptorWithDeprecation;", "T", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "", "descriptor", "isDeprecated", "", "<init>", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;Z)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "()Z", "component1", "component2", "copy", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;Z)Lorg/jetbrains/kotlin/descriptors/DescriptorWithDeprecation;", "equals", "other", "hashCode", "", "toString", "", "Companion", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final /* data */ class DescriptorWithDeprecation<T extends DeclarationDescriptor> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final T descriptor;
    private final boolean isDeprecated;

    public DescriptorWithDeprecation(T t, boolean z) {
        t.getClass();
        this.descriptor = t;
        this.isDeprecated = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DescriptorWithDeprecation copy$default(DescriptorWithDeprecation descriptorWithDeprecation, DeclarationDescriptor declarationDescriptor, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            declarationDescriptor = descriptorWithDeprecation.descriptor;
        }
        if ((i & 2) != 0) {
            z = descriptorWithDeprecation.isDeprecated;
        }
        return descriptorWithDeprecation.copy(declarationDescriptor, z);
    }

    public final T component1() {
        return this.descriptor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsDeprecated() {
        return this.isDeprecated;
    }

    public final DescriptorWithDeprecation<T> copy(T descriptor, boolean isDeprecated) {
        descriptor.getClass();
        return new DescriptorWithDeprecation<>(descriptor, isDeprecated);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DescriptorWithDeprecation)) {
            return false;
        }
        DescriptorWithDeprecation descriptorWithDeprecation = (DescriptorWithDeprecation) other;
        return Intrinsics.areEqual(this.descriptor, descriptorWithDeprecation.descriptor) && this.isDeprecated == descriptorWithDeprecation.isDeprecated;
    }

    public final T getDescriptor() {
        return this.descriptor;
    }

    public int hashCode() {
        return (this.descriptor.hashCode() * 31) + Boolean.hashCode(this.isDeprecated);
    }

    public final boolean isDeprecated() {
        return this.isDeprecated;
    }

    public String toString() {
        return "DescriptorWithDeprecation(descriptor=" + this.descriptor + ", isDeprecated=" + this.isDeprecated + ')';
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0001\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u0006¢\u0006\u0002\u0010\tJ#\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0001\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u0006¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/DescriptorWithDeprecation$Companion;", "", "<init>", "()V", "createNonDeprecated", "Lorg/jetbrains/kotlin/descriptors/DescriptorWithDeprecation;", "T", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "descriptor", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;)Lorg/jetbrains/kotlin/descriptors/DescriptorWithDeprecation;", "createDeprecated", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <T extends DeclarationDescriptor> DescriptorWithDeprecation<T> createDeprecated(T descriptor) {
            descriptor.getClass();
            return new DescriptorWithDeprecation<>(descriptor, true);
        }

        public final <T extends DeclarationDescriptor> DescriptorWithDeprecation<T> createNonDeprecated(T descriptor) {
            descriptor.getClass();
            return new DescriptorWithDeprecation<>(descriptor, false);
        }

        private Companion() {
        }
    }
}
