package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableType;", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "isMarkedNullable", Argument.Delimiters.none, "typeConstructor", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableTypeConstructor;", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "<init>", "(ZLorg/jetbrains/kotlin/fir/types/ConeTypeVariableTypeConstructor;Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)V", "()Z", "getTypeConstructor", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableTypeConstructor;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeVariableType extends ConeSimpleKotlinType {
    private final ConeAttributes attributes;
    private final boolean isMarkedNullable;
    private final ConeTypeVariableTypeConstructor typeConstructor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeTypeVariableType(boolean z, ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor, ConeAttributes coneAttributes) {
        super(null);
        coneTypeVariableTypeConstructor.getClass();
        coneAttributes.getClass();
        this.isMarkedNullable = z;
        this.typeConstructor = coneTypeVariableTypeConstructor;
        this.attributes = coneAttributes;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConeTypeVariableType)) {
            return false;
        }
        ConeTypeVariableType coneTypeVariableType = (ConeTypeVariableType) other;
        return this.isMarkedNullable == coneTypeVariableType.isMarkedNullable && Intrinsics.areEqual(this.typeConstructor, coneTypeVariableType.typeConstructor);
    }

    public ConeAttributes getAttributes() {
        return this.attributes;
    }

    public ConeTypeProjection[] getTypeArguments() {
        return ConeTypeProjection.Companion.getEMPTY_ARRAY();
    }

    public final ConeTypeVariableTypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.isMarkedNullable) * 31) + this.typeConstructor.hashCode();
    }

    /* JADX INFO: renamed from: isMarkedNullable, reason: from getter */
    public final boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    public /* synthetic */ ConeTypeVariableType(boolean z, ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor, ConeAttributes coneAttributes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, coneTypeVariableTypeConstructor, (i & 4) != 0 ? ConeAttributes.INSTANCE.getEmpty() : coneAttributes);
    }
}
