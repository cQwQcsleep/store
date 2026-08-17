package org.jetbrains.kotlin.fir.types;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 #2\u00020\u00012\u00020\u0002:\u0001#B\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u001b\u001a\u00020\u000b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH&J\u0014\u0010\u001e\u001a\u00020\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0086\u0082\u0004J\n\u0010!\u001a\u00020\"H\u0086\u0080\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u00138F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u0082\u0001\u0002$%¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeIntegerLiteralType;", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeConstructorMarker;", "isUnsigned", Argument.Delimiters.none, "isMarkedNullable", "<init>", "(ZZ)V", "()Z", "possibleTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "getPossibleTypes", "()Ljava/util/Collection;", "supertypes", Argument.Delimiters.none, "getSupertypes", "()Ljava/util/List;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getApproximatedType", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "Companion", "Lorg/jetbrains/kotlin/fir/types/ConeIntegerConstantOperatorType;", "Lorg/jetbrains/kotlin/fir/types/ConeIntegerLiteralConstantType;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeIntegerLiteralType extends ConeSimpleKotlinType implements ConeTypeConstructorMarker {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean isMarkedNullable;
    private final boolean isUnsigned;

    private ConeIntegerLiteralType(boolean z, boolean z2) {
        super(null);
        this.isUnsigned = z;
        this.isMarkedNullable = z2;
    }

    public static /* synthetic */ ConeClassLikeType getApproximatedType$default(ConeIntegerLiteralType coneIntegerLiteralType, ConeKotlinType coneKotlinType, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getApproximatedType");
            return null;
        }
        if ((i & 1) != 0) {
            coneKotlinType = null;
        }
        return coneIntegerLiteralType.getApproximatedType(coneKotlinType);
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        ConeIntegerLiteralType coneIntegerLiteralType = (ConeIntegerLiteralType) other;
        return this.isUnsigned == coneIntegerLiteralType.isUnsigned && Intrinsics.areEqual(getPossibleTypes(), coneIntegerLiteralType.getPossibleTypes()) && this.isMarkedNullable == coneIntegerLiteralType.isMarkedNullable;
    }

    public abstract ConeClassLikeType getApproximatedType(ConeKotlinType expectedType);

    public final ConeAttributes getAttributes() {
        return ConeAttributes.INSTANCE.getEmpty();
    }

    public abstract Collection<ConeClassLikeType> getPossibleTypes();

    public abstract List<ConeClassLikeType> getSupertypes();

    public final ConeTypeProjection[] getTypeArguments() {
        return ConeTypeProjection.Companion.getEMPTY_ARRAY();
    }

    public final int hashCode() {
        return (getPossibleTypes().hashCode() * 31) + Boolean.hashCode(this.isMarkedNullable);
    }

    /* JADX INFO: renamed from: isMarkedNullable, reason: from getter */
    public final boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    /* JADX INFO: renamed from: isUnsigned, reason: from getter */
    public final boolean getIsUnsigned() {
        return this.isUnsigned;
    }

    public /* synthetic */ ConeIntegerLiteralType(boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2);
    }
}
