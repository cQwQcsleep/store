package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.CapturedTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B#\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u0015\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0082\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J'\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeCapturedType;", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "Lorg/jetbrains/kotlin/types/model/CapturedTypeMarker;", "isMarkedNullable", Argument.Delimiters.none, "constructor", "Lorg/jetbrains/kotlin/fir/types/ConeCapturedTypeConstructor;", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "<init>", "(ZLorg/jetbrains/kotlin/fir/types/ConeCapturedTypeConstructor;Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)V", "()Z", "getConstructor", "()Lorg/jetbrains/kotlin/fir/types/ConeCapturedTypeConstructor;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "component1", "component2", "component3", "copy", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ConeCapturedType extends ConeSimpleKotlinType implements CapturedTypeMarker {
    private final ConeAttributes attributes;
    private final ConeCapturedTypeConstructor constructor;
    private final boolean isMarkedNullable;

    public /* synthetic */ ConeCapturedType(boolean z, ConeCapturedTypeConstructor coneCapturedTypeConstructor, ConeAttributes coneAttributes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, coneCapturedTypeConstructor, (i & 4) != 0 ? ConeAttributes.INSTANCE.getEmpty() : coneAttributes);
    }

    public static /* synthetic */ ConeCapturedType copy$default(ConeCapturedType coneCapturedType, boolean z, ConeCapturedTypeConstructor coneCapturedTypeConstructor, ConeAttributes coneAttributes, int i, Object obj) {
        if ((i & 1) != 0) {
            z = coneCapturedType.isMarkedNullable;
        }
        if ((i & 2) != 0) {
            coneCapturedTypeConstructor = coneCapturedType.constructor;
        }
        if ((i & 4) != 0) {
            coneAttributes = coneCapturedType.attributes;
        }
        return coneCapturedType.copy(z, coneCapturedTypeConstructor, coneAttributes);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ConeCapturedTypeConstructor getConstructor() {
        return this.constructor;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ConeAttributes getAttributes() {
        return this.attributes;
    }

    public final ConeCapturedType copy(boolean isMarkedNullable, ConeCapturedTypeConstructor constructor, ConeAttributes attributes) {
        constructor.getClass();
        attributes.getClass();
        return new ConeCapturedType(isMarkedNullable, constructor, attributes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ConeCapturedType.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        ConeCapturedType coneCapturedType = (ConeCapturedType) other;
        return Intrinsics.areEqual(this.constructor, coneCapturedType.constructor) && this.isMarkedNullable == coneCapturedType.isMarkedNullable;
    }

    public ConeAttributes getAttributes() {
        return this.attributes;
    }

    public final ConeCapturedTypeConstructor getConstructor() {
        return this.constructor;
    }

    public ConeTypeProjection[] getTypeArguments() {
        return ConeTypeProjection.Companion.getEMPTY_ARRAY();
    }

    public int hashCode() {
        return ((217 + this.constructor.hashCode()) * 31) + Boolean.hashCode(this.isMarkedNullable);
    }

    public final boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeCapturedType(boolean z, ConeCapturedTypeConstructor coneCapturedTypeConstructor, ConeAttributes coneAttributes) {
        super(null);
        coneCapturedTypeConstructor.getClass();
        coneAttributes.getClass();
        this.isMarkedNullable = z;
        this.constructor = coneCapturedTypeConstructor;
        this.attributes = coneAttributes;
    }
}
