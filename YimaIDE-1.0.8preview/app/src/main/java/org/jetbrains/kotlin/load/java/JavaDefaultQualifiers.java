package org.jetbrains.kotlin.load.java;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.load.java.typeEnhancement.NullabilityQualifier;
import org.jetbrains.kotlin.load.java.typeEnhancement.WithMigrationStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0010\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0017\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\nHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JK\u0010\u001c\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004R\u001b\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/load/java/JavaDefaultQualifiers;", "", "nullabilityQualifier", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/WithMigrationStatus;", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifier;", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifierWithMigrationStatus;", "qualifierApplicabilityTypes", "", "Lorg/jetbrains/kotlin/load/java/AnnotationQualifierApplicabilityType;", "definitelyNotNull", "", "preferQualifierOverBound", "preferQualifierOverSupertype", "<init>", "(Lorg/jetbrains/kotlin/load/java/typeEnhancement/WithMigrationStatus;Ljava/util/Collection;ZZZ)V", "getNullabilityQualifier", "()Lorg/jetbrains/kotlin/load/java/typeEnhancement/WithMigrationStatus;", "getQualifierApplicabilityTypes", "()Ljava/util/Collection;", "getDefinitelyNotNull", "()Z", "getPreferQualifierOverBound", "getPreferQualifierOverSupertype", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class JavaDefaultQualifiers {
    private final boolean definitelyNotNull;
    private final WithMigrationStatus<NullabilityQualifier> nullabilityQualifier;
    private final boolean preferQualifierOverBound;
    private final boolean preferQualifierOverSupertype;
    private final Collection<AnnotationQualifierApplicabilityType> qualifierApplicabilityTypes;

    public /* synthetic */ JavaDefaultQualifiers(WithMigrationStatus withMigrationStatus, Collection collection, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(withMigrationStatus, collection, (i & 4) != 0 ? withMigrationStatus.getQualifier() == NullabilityQualifier.NOT_NULL : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JavaDefaultQualifiers copy$default(JavaDefaultQualifiers javaDefaultQualifiers, WithMigrationStatus withMigrationStatus, Collection collection, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            withMigrationStatus = javaDefaultQualifiers.nullabilityQualifier;
        }
        if ((i & 2) != 0) {
            collection = javaDefaultQualifiers.qualifierApplicabilityTypes;
        }
        if ((i & 4) != 0) {
            z = javaDefaultQualifiers.definitelyNotNull;
        }
        if ((i & 8) != 0) {
            z2 = javaDefaultQualifiers.preferQualifierOverBound;
        }
        if ((i & 16) != 0) {
            z3 = javaDefaultQualifiers.preferQualifierOverSupertype;
        }
        boolean z4 = z3;
        boolean z5 = z;
        return javaDefaultQualifiers.copy(withMigrationStatus, collection, z5, z2, z4);
    }

    public final WithMigrationStatus<NullabilityQualifier> component1() {
        return this.nullabilityQualifier;
    }

    public final Collection<AnnotationQualifierApplicabilityType> component2() {
        return this.qualifierApplicabilityTypes;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getDefinitelyNotNull() {
        return this.definitelyNotNull;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getPreferQualifierOverBound() {
        return this.preferQualifierOverBound;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getPreferQualifierOverSupertype() {
        return this.preferQualifierOverSupertype;
    }

    public final JavaDefaultQualifiers copy(WithMigrationStatus<NullabilityQualifier> nullabilityQualifier, Collection<? extends AnnotationQualifierApplicabilityType> qualifierApplicabilityTypes, boolean definitelyNotNull, boolean preferQualifierOverBound, boolean preferQualifierOverSupertype) {
        nullabilityQualifier.getClass();
        qualifierApplicabilityTypes.getClass();
        return new JavaDefaultQualifiers(nullabilityQualifier, qualifierApplicabilityTypes, definitelyNotNull, preferQualifierOverBound, preferQualifierOverSupertype);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JavaDefaultQualifiers)) {
            return false;
        }
        JavaDefaultQualifiers javaDefaultQualifiers = (JavaDefaultQualifiers) other;
        return Intrinsics.areEqual(this.nullabilityQualifier, javaDefaultQualifiers.nullabilityQualifier) && Intrinsics.areEqual(this.qualifierApplicabilityTypes, javaDefaultQualifiers.qualifierApplicabilityTypes) && this.definitelyNotNull == javaDefaultQualifiers.definitelyNotNull && this.preferQualifierOverBound == javaDefaultQualifiers.preferQualifierOverBound && this.preferQualifierOverSupertype == javaDefaultQualifiers.preferQualifierOverSupertype;
    }

    public final boolean getDefinitelyNotNull() {
        return this.definitelyNotNull;
    }

    public final WithMigrationStatus<NullabilityQualifier> getNullabilityQualifier() {
        return this.nullabilityQualifier;
    }

    public final boolean getPreferQualifierOverBound() {
        return this.preferQualifierOverBound;
    }

    public final boolean getPreferQualifierOverSupertype() {
        return this.preferQualifierOverSupertype;
    }

    public final Collection<AnnotationQualifierApplicabilityType> getQualifierApplicabilityTypes() {
        return this.qualifierApplicabilityTypes;
    }

    public int hashCode() {
        return (((((((this.nullabilityQualifier.hashCode() * 31) + this.qualifierApplicabilityTypes.hashCode()) * 31) + Boolean.hashCode(this.definitelyNotNull)) * 31) + Boolean.hashCode(this.preferQualifierOverBound)) * 31) + Boolean.hashCode(this.preferQualifierOverSupertype);
    }

    public String toString() {
        return "JavaDefaultQualifiers(nullabilityQualifier=" + this.nullabilityQualifier + ", qualifierApplicabilityTypes=" + this.qualifierApplicabilityTypes + ", definitelyNotNull=" + this.definitelyNotNull + ", preferQualifierOverBound=" + this.preferQualifierOverBound + ", preferQualifierOverSupertype=" + this.preferQualifierOverSupertype + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JavaDefaultQualifiers(WithMigrationStatus<NullabilityQualifier> withMigrationStatus, Collection<? extends AnnotationQualifierApplicabilityType> collection, boolean z, boolean z2, boolean z3) {
        withMigrationStatus.getClass();
        collection.getClass();
        this.nullabilityQualifier = withMigrationStatus;
        this.qualifierApplicabilityTypes = collection;
        this.definitelyNotNull = z;
        this.preferQualifierOverBound = z2;
        this.preferQualifierOverSupertype = z3;
    }
}
