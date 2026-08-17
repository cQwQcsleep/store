package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.FlexibleTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0086\u0082\u0004J\n\u0010\u001a\u001a\u00020\u001bH\u0086\u0080\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\rR\u0019\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/types/model/FlexibleTypeMarker;", "lowerBound", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "upperBound", "isTrivial", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeRigidType;Lorg/jetbrains/kotlin/fir/types/ConeRigidType;Z)V", "getLowerBound", "()Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "getUpperBound", "()Z", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ConeFlexibleType extends ConeKotlinType implements FlexibleTypeMarker {
    private final boolean isTrivial;
    private final ConeRigidType lowerBound;
    private final ConeRigidType upperBound;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeFlexibleType(ConeRigidType coneRigidType, ConeRigidType coneRigidType2, boolean z) {
        super((DefaultConstructorMarker) null);
        coneRigidType.getClass();
        coneRigidType2.getClass();
        this.lowerBound = coneRigidType;
        this.upperBound = coneRigidType2;
        this.isTrivial = z;
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConeFlexibleType)) {
            return false;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) other;
        if (Intrinsics.areEqual(this.lowerBound, coneFlexibleType.lowerBound)) {
            return (this.isTrivial && coneFlexibleType.isTrivial) || Intrinsics.areEqual(this.upperBound, coneFlexibleType.upperBound);
        }
        return false;
    }

    public final ConeAttributes getAttributes() {
        return this.lowerBound.getAttributes();
    }

    public final ConeRigidType getLowerBound() {
        return this.lowerBound;
    }

    public final ConeTypeProjection[] getTypeArguments() {
        return this.lowerBound.getTypeArguments();
    }

    public final ConeRigidType getUpperBound() {
        return this.upperBound;
    }

    public final int hashCode() {
        return (this.lowerBound.hashCode() * 31) + 2999;
    }

    /* JADX INFO: renamed from: isTrivial, reason: from getter */
    public final boolean getIsTrivial() {
        return this.isTrivial;
    }
}
