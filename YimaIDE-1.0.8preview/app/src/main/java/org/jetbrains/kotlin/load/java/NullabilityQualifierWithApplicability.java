package org.jetbrains.kotlin.load.java;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.load.java.typeEnhancement.NullabilityQualifier;
import org.jetbrains.kotlin.load.java.typeEnhancement.WithMigrationStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0010\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000f\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u001b\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/load/java/NullabilityQualifierWithApplicability;", "", "nullabilityQualifier", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/WithMigrationStatus;", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifier;", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifierWithMigrationStatus;", "qualifierApplicabilityTypes", "", "Lorg/jetbrains/kotlin/load/java/AnnotationQualifierApplicabilityType;", "<init>", "(Lorg/jetbrains/kotlin/load/java/typeEnhancement/WithMigrationStatus;Ljava/util/Collection;)V", "getNullabilityQualifier", "()Lorg/jetbrains/kotlin/load/java/typeEnhancement/WithMigrationStatus;", "getQualifierApplicabilityTypes", "()Ljava/util/Collection;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class NullabilityQualifierWithApplicability {
    private final WithMigrationStatus<NullabilityQualifier> nullabilityQualifier;
    private final Collection<AnnotationQualifierApplicabilityType> qualifierApplicabilityTypes;

    /* JADX WARN: Multi-variable type inference failed */
    public NullabilityQualifierWithApplicability(WithMigrationStatus<NullabilityQualifier> withMigrationStatus, Collection<? extends AnnotationQualifierApplicabilityType> collection) {
        withMigrationStatus.getClass();
        collection.getClass();
        this.nullabilityQualifier = withMigrationStatus;
        this.qualifierApplicabilityTypes = collection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NullabilityQualifierWithApplicability copy$default(NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability, WithMigrationStatus withMigrationStatus, Collection collection, int i, Object obj) {
        if ((i & 1) != 0) {
            withMigrationStatus = nullabilityQualifierWithApplicability.nullabilityQualifier;
        }
        if ((i & 2) != 0) {
            collection = nullabilityQualifierWithApplicability.qualifierApplicabilityTypes;
        }
        return nullabilityQualifierWithApplicability.copy(withMigrationStatus, collection);
    }

    public final WithMigrationStatus<NullabilityQualifier> component1() {
        return this.nullabilityQualifier;
    }

    public final Collection<AnnotationQualifierApplicabilityType> component2() {
        return this.qualifierApplicabilityTypes;
    }

    public final NullabilityQualifierWithApplicability copy(WithMigrationStatus<NullabilityQualifier> nullabilityQualifier, Collection<? extends AnnotationQualifierApplicabilityType> qualifierApplicabilityTypes) {
        nullabilityQualifier.getClass();
        qualifierApplicabilityTypes.getClass();
        return new NullabilityQualifierWithApplicability(nullabilityQualifier, qualifierApplicabilityTypes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NullabilityQualifierWithApplicability)) {
            return false;
        }
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = (NullabilityQualifierWithApplicability) other;
        return Intrinsics.areEqual(this.nullabilityQualifier, nullabilityQualifierWithApplicability.nullabilityQualifier) && Intrinsics.areEqual(this.qualifierApplicabilityTypes, nullabilityQualifierWithApplicability.qualifierApplicabilityTypes);
    }

    public final WithMigrationStatus<NullabilityQualifier> getNullabilityQualifier() {
        return this.nullabilityQualifier;
    }

    public final Collection<AnnotationQualifierApplicabilityType> getQualifierApplicabilityTypes() {
        return this.qualifierApplicabilityTypes;
    }

    public int hashCode() {
        return (this.nullabilityQualifier.hashCode() * 31) + this.qualifierApplicabilityTypes.hashCode();
    }

    public String toString() {
        return "NullabilityQualifierWithApplicability(nullabilityQualifier=" + this.nullabilityQualifier + ", qualifierApplicabilityTypes=" + this.qualifierApplicabilityTypes + ')';
    }
}
