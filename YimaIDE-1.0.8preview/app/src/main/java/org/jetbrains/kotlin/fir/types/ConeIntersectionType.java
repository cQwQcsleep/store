package org.jetbrains.kotlin.fir.types;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.IntersectionTypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B!\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeIntersectionType;", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "Lorg/jetbrains/kotlin/types/model/IntersectionTypeConstructorMarker;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeConstructorMarker;", "intersectedTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "upperBoundForApproximation", "<init>", "(Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getIntersectedTypes", "()Ljava/util/Collection;", "getUpperBoundForApproximation", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "hashCode", Argument.Delimiters.none, "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeIntersectionType extends ConeSimpleKotlinType implements ConeTypeConstructorMarker, IntersectionTypeConstructorMarker {
    private final ConeAttributes attributes;
    private int hashCode;
    private final Collection<ConeKotlinType> intersectedTypes;
    private final ConeKotlinType upperBoundForApproximation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeIntersectionType(Collection<? extends ConeKotlinType> collection, ConeKotlinType coneKotlinType) {
        super(null);
        collection.getClass();
        this.intersectedTypes = collection;
        this.upperBoundForApproximation = coneKotlinType;
        Iterator<? extends ConeKotlinType> it = collection.iterator();
        ConeAttributes attributes = it.next().getAttributes();
        while (it.hasNext()) {
            attributes = attributes.intersect(it.next().getAttributes());
        }
        this.attributes = attributes;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ConeIntersectionType.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        return Intrinsics.areEqual(this.intersectedTypes, ((ConeIntersectionType) other).intersectedTypes);
    }

    public ConeAttributes getAttributes() {
        return this.attributes;
    }

    public final Collection<ConeKotlinType> getIntersectedTypes() {
        return this.intersectedTypes;
    }

    public ConeTypeProjection[] getTypeArguments() {
        return ConeTypeProjection.Companion.getEMPTY_ARRAY();
    }

    public final ConeKotlinType getUpperBoundForApproximation() {
        return this.upperBoundForApproximation;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = this.intersectedTypes.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    public /* synthetic */ ConeIntersectionType(Collection collection, ConeKotlinType coneKotlinType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(collection, (i & 2) != 0 ? null : coneKotlinType);
    }
}
