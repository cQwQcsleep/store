package org.jetbrains.kotlin.fir.java.enhancement;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttributeWithConeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0000H\u0016J\u0014\u0010\r\u001a\u0004\u0018\u00010\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000e\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000f\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\u0010\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u001f\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010 HÖ\u0083\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u001c\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\nR\u0014\u0010\u001a\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\n¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedTypeForWarningAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributeWithConeType;", "coneType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isDeprecation", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)V", "getConeType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "()Z", "union", "other", "intersect", "add", "isSubtypeOf", "toString", Argument.Delimiters.none, "copyWith", "newType", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "implementsEquality", "getImplementsEquality", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class EnhancedTypeForWarningAttribute extends ConeAttributeWithConeType<EnhancedTypeForWarningAttribute> {
    private final ConeKotlinType coneType;
    private final boolean isDeprecation;

    public EnhancedTypeForWarningAttribute(ConeKotlinType coneKotlinType, boolean z) {
        coneKotlinType.getClass();
        this.coneType = coneKotlinType;
        this.isDeprecation = z;
    }

    public static /* synthetic */ EnhancedTypeForWarningAttribute copy$default(EnhancedTypeForWarningAttribute enhancedTypeForWarningAttribute, ConeKotlinType coneKotlinType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            coneKotlinType = enhancedTypeForWarningAttribute.coneType;
        }
        if ((i & 2) != 0) {
            z = enhancedTypeForWarningAttribute.isDeprecation;
        }
        return enhancedTypeForWarningAttribute.copy(coneKotlinType, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ConeKotlinType getConeType() {
        return this.coneType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsDeprecation() {
        return this.isDeprecation;
    }

    public final EnhancedTypeForWarningAttribute copy(ConeKotlinType coneType, boolean isDeprecation) {
        coneType.getClass();
        return new EnhancedTypeForWarningAttribute(coneType, isDeprecation);
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttributeWithConeType
    public EnhancedTypeForWarningAttribute copyWith(ConeKotlinType newType) {
        newType.getClass();
        return new EnhancedTypeForWarningAttribute(newType, this.isDeprecation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EnhancedTypeForWarningAttribute)) {
            return false;
        }
        EnhancedTypeForWarningAttribute enhancedTypeForWarningAttribute = (EnhancedTypeForWarningAttribute) other;
        return Intrinsics.areEqual(this.coneType, enhancedTypeForWarningAttribute.coneType) && this.isDeprecation == enhancedTypeForWarningAttribute.isDeprecation;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttributeWithConeType
    public ConeKotlinType getConeType() {
        return this.coneType;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean getImplementsEquality() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean getKeepInInferredDeclarationType() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public KClass<? extends EnhancedTypeForWarningAttribute> getKey() {
        return Reflection.getOrCreateKotlinClass(EnhancedTypeForWarningAttribute.class);
    }

    public int hashCode() {
        return (this.coneType.hashCode() * 31) + Boolean.hashCode(this.isDeprecation);
    }

    public final boolean isDeprecation() {
        return this.isDeprecation;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public String toString() {
        return "Enhanced for warning(" + ConeTypeUtilsKt.renderForDebugging(getConeType()) + ')';
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public EnhancedTypeForWarningAttribute add(EnhancedTypeForWarningAttribute other) {
        return other == null ? this : other;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public EnhancedTypeForWarningAttribute intersect(EnhancedTypeForWarningAttribute other) {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean isSubtypeOf(EnhancedTypeForWarningAttribute other) {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public EnhancedTypeForWarningAttribute union(EnhancedTypeForWarningAttribute other) {
        return null;
    }
}
