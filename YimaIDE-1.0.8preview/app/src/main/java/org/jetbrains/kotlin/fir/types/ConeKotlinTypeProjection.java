package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0004\u0005\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeKotlinTypeProjection;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "<init>", "()V", "type", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "equals", "", "other", "", "hashCode", "", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinTypeConflictingProjection;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinTypeProjectionIn;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinTypeProjectionOut;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ConeKotlinTypeProjection extends ConeTypeProjection {
    private ConeKotlinTypeProjection() {
        super(null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConeKotlinTypeProjection)) {
            return false;
        }
        ConeKotlinTypeProjection coneKotlinTypeProjection = (ConeKotlinTypeProjection) other;
        return getKind() == coneKotlinTypeProjection.getKind() && Intrinsics.areEqual(getType(), coneKotlinTypeProjection.getType());
    }

    public abstract ConeKotlinType getType();

    public int hashCode() {
        return (getType().hashCode() * 31) + getKind().hashCode();
    }

    public /* synthetic */ ConeKotlinTypeProjection(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
