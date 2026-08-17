package org.jetbrains.kotlin.fir.types;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentListMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u001d\u001a\u00020\u001eH\u0086\u0080\u0004J\u0014\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H¦\u0082\u0004J\n\u0010#\u001a\u00020$H¦\u0080\u0004R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR4\u0010\u000f\u001a\u00020\u00008FX\u0087\u0004r\u0018\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\n\b\u0016\u0012\u0006\b\n0\u00178\u0018¢\u0006\f\u0012\u0004\b\u0010\u0010\u0005\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0019\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u0082\u0001\u0002%&¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinTypeProjection;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "Lorg/jetbrains/kotlin/types/model/TypeArgumentListMarker;", "<init>", "()V", "kind", "Lorg/jetbrains/kotlin/fir/types/ProjectionKind;", "getKind", "()Lorg/jetbrains/kotlin/fir/types/ProjectionKind;", "typeArguments", "", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "type", "getType$annotations", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lkotlin/Deprecated;", "message", "Useless call. Receiver is already a ConeKotlinType.", "level", "Lkotlin/DeprecationLevel;", "ERROR", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "toString", "", "equals", "", "other", "", "hashCode", "", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ConeKotlinType extends ConeKotlinTypeProjection implements KotlinTypeMarker, TypeArgumentListMarker {
    private ConeKotlinType() {
        super(null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Useless call. Receiver is already a ConeKotlinType.")
    public static /* synthetic */ void getType$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection
    public abstract boolean equals(Object other);

    public abstract ConeAttributes getAttributes();

    @Override // org.jetbrains.kotlin.fir.types.ConeTypeProjection
    public final ProjectionKind getKind() {
        return ProjectionKind.INVARIANT;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection
    public final ConeKotlinType getType() {
        return this;
    }

    public abstract ConeTypeProjection[] getTypeArguments();

    @Override // org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection
    public abstract int hashCode();

    public final String toString() {
        return ConeTypeUtilsKt.renderForDebugging(this);
    }

    public /* synthetic */ ConeKotlinType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
