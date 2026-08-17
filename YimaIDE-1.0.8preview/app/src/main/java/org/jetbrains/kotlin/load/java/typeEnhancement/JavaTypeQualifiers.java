package org.jetbrains.kotlin.load.java.typeEnhancement;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J?\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/load/java/typeEnhancement/JavaTypeQualifiers;", "", "nullability", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifier;", "mutability", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/MutabilityQualifier;", "definitelyNotNull", "", "isNullabilityQualifierForWarning", "isMutabilityQualifierForWarning", "<init>", "(Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifier;Lorg/jetbrains/kotlin/load/java/typeEnhancement/MutabilityQualifier;ZZZ)V", "getNullability", "()Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifier;", "getMutability", "()Lorg/jetbrains/kotlin/load/java/typeEnhancement/MutabilityQualifier;", "getDefinitelyNotNull", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "Companion", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class JavaTypeQualifiers {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final JavaTypeQualifiers NONE = new JavaTypeQualifiers(null, null, false, false, false, 24, null);
    private final boolean definitelyNotNull;
    private final boolean isMutabilityQualifierForWarning;
    private final boolean isNullabilityQualifierForWarning;
    private final MutabilityQualifier mutability;
    private final NullabilityQualifier nullability;

    public /* synthetic */ JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nullabilityQualifier, mutabilityQualifier, z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3);
    }

    public static /* synthetic */ JavaTypeQualifiers copy$default(JavaTypeQualifiers javaTypeQualifiers, NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            nullabilityQualifier = javaTypeQualifiers.nullability;
        }
        if ((i & 2) != 0) {
            mutabilityQualifier = javaTypeQualifiers.mutability;
        }
        if ((i & 4) != 0) {
            z = javaTypeQualifiers.definitelyNotNull;
        }
        if ((i & 8) != 0) {
            z2 = javaTypeQualifiers.isNullabilityQualifierForWarning;
        }
        if ((i & 16) != 0) {
            z3 = javaTypeQualifiers.isMutabilityQualifierForWarning;
        }
        boolean z4 = z3;
        boolean z5 = z;
        return javaTypeQualifiers.copy(nullabilityQualifier, mutabilityQualifier, z5, z2, z4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final NullabilityQualifier getNullability() {
        return this.nullability;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MutabilityQualifier getMutability() {
        return this.mutability;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getDefinitelyNotNull() {
        return this.definitelyNotNull;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsNullabilityQualifierForWarning() {
        return this.isNullabilityQualifierForWarning;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsMutabilityQualifierForWarning() {
        return this.isMutabilityQualifierForWarning;
    }

    public final JavaTypeQualifiers copy(NullabilityQualifier nullability, MutabilityQualifier mutability, boolean definitelyNotNull, boolean isNullabilityQualifierForWarning, boolean isMutabilityQualifierForWarning) {
        return new JavaTypeQualifiers(nullability, mutability, definitelyNotNull, isNullabilityQualifierForWarning, isMutabilityQualifierForWarning);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JavaTypeQualifiers)) {
            return false;
        }
        JavaTypeQualifiers javaTypeQualifiers = (JavaTypeQualifiers) other;
        return this.nullability == javaTypeQualifiers.nullability && this.mutability == javaTypeQualifiers.mutability && this.definitelyNotNull == javaTypeQualifiers.definitelyNotNull && this.isNullabilityQualifierForWarning == javaTypeQualifiers.isNullabilityQualifierForWarning && this.isMutabilityQualifierForWarning == javaTypeQualifiers.isMutabilityQualifierForWarning;
    }

    public final boolean getDefinitelyNotNull() {
        return this.definitelyNotNull;
    }

    public final MutabilityQualifier getMutability() {
        return this.mutability;
    }

    public final NullabilityQualifier getNullability() {
        return this.nullability;
    }

    public int hashCode() {
        NullabilityQualifier nullabilityQualifier = this.nullability;
        int iHashCode = (nullabilityQualifier == null ? 0 : nullabilityQualifier.hashCode()) * 31;
        MutabilityQualifier mutabilityQualifier = this.mutability;
        return ((((((iHashCode + (mutabilityQualifier != null ? mutabilityQualifier.hashCode() : 0)) * 31) + Boolean.hashCode(this.definitelyNotNull)) * 31) + Boolean.hashCode(this.isNullabilityQualifierForWarning)) * 31) + Boolean.hashCode(this.isMutabilityQualifierForWarning);
    }

    public final boolean isMutabilityQualifierForWarning() {
        return this.isMutabilityQualifierForWarning;
    }

    public final boolean isNullabilityQualifierForWarning() {
        return this.isNullabilityQualifierForWarning;
    }

    public String toString() {
        return "JavaTypeQualifiers(nullability=" + this.nullability + ", mutability=" + this.mutability + ", definitelyNotNull=" + this.definitelyNotNull + ", isNullabilityQualifierForWarning=" + this.isNullabilityQualifierForWarning + ", isMutabilityQualifierForWarning=" + this.isMutabilityQualifierForWarning + ')';
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/load/java/typeEnhancement/JavaTypeQualifiers$Companion;", "", "<init>", "()V", "NONE", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/JavaTypeQualifiers;", "getNONE", "()Lorg/jetbrains/kotlin/load/java/typeEnhancement/JavaTypeQualifiers;", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final JavaTypeQualifiers getNONE() {
            return JavaTypeQualifiers.NONE;
        }

        private Companion() {
        }
    }

    public JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, boolean z3) {
        this.nullability = nullabilityQualifier;
        this.mutability = mutabilityQualifier;
        this.definitelyNotNull = z;
        this.isNullabilityQualifierForWarning = z2;
        this.isMutabilityQualifierForWarning = z3;
    }
}
