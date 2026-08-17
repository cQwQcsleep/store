package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.StubTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0082\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000bR\u001c\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0001\u0001\u001a¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeStubType;", "Lorg/jetbrains/kotlin/types/model/StubTypeMarker;", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "constructor", "Lorg/jetbrains/kotlin/fir/types/ConeStubTypeConstructor;", "isMarkedNullable", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeStubTypeConstructor;Z)V", "getConstructor", "()Lorg/jetbrains/kotlin/fir/types/ConeStubTypeConstructor;", "()Z", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeStubTypeForTypeVariableInSubtyping;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeStubType extends ConeSimpleKotlinType implements StubTypeMarker {
    private final ConeStubTypeConstructor constructor;
    private final boolean isMarkedNullable;

    private ConeStubType(ConeStubTypeConstructor coneStubTypeConstructor, boolean z) {
        super(null);
        this.constructor = coneStubTypeConstructor;
        this.isMarkedNullable = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        ConeStubType coneStubType = (ConeStubType) other;
        return Intrinsics.areEqual(this.constructor, coneStubType.constructor) && this.isMarkedNullable == coneStubType.isMarkedNullable;
    }

    public ConeAttributes getAttributes() {
        return ConeAttributes.INSTANCE.getEmpty();
    }

    public final ConeStubTypeConstructor getConstructor() {
        return this.constructor;
    }

    public ConeTypeProjection[] getTypeArguments() {
        return ConeTypeProjection.Companion.getEMPTY_ARRAY();
    }

    public int hashCode() {
        return (this.constructor.hashCode() * 31) + Boolean.hashCode(this.isMarkedNullable);
    }

    /* JADX INFO: renamed from: isMarkedNullable, reason: from getter */
    public final boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    public /* synthetic */ ConeStubType(ConeStubTypeConstructor coneStubTypeConstructor, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneStubTypeConstructor, z);
    }
}
