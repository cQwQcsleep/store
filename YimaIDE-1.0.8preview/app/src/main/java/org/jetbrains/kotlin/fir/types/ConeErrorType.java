package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0014\u0010\"\u001a\u00020\u00052\b\u0010#\u001a\u0004\u0018\u00010$H\u0096\u0082\u0004J\n\u0010%\u001a\u00020&H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0012R\u001e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\tX\u0096\u0004¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeErrorType;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "isUninferredParameter", Argument.Delimiters.none, "delegatedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "nullable", "lookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeErrorLookupTag;", "<init>", "(Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;ZLorg/jetbrains/kotlin/fir/types/ConeKotlinType;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;Lorg/jetbrains/kotlin/fir/types/ConeAttributes;Ljava/lang/Boolean;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeErrorLookupTag;)V", "()Z", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getNullable", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLookupTag", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeErrorLookupTag;", "isMarkedNullable", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getDelegatedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeErrorType extends ConeClassLikeType {
    private final ConeAttributes attributes;
    private final boolean isUninferredParameter;
    private final ConeClassLikeErrorLookupTag lookupTag;
    private final Boolean nullable;
    private final ConeTypeProjection[] typeArguments;

    public /* synthetic */ ConeErrorType(ConeDiagnostic coneDiagnostic, boolean z, ConeKotlinType coneKotlinType, ConeTypeProjection[] coneTypeProjectionArr, ConeAttributes coneAttributes, Boolean bool, ConeClassLikeErrorLookupTag coneClassLikeErrorLookupTag, int i, DefaultConstructorMarker defaultConstructorMarker) {
        ClassId classId;
        z = (i & 2) != 0 ? false : z;
        coneKotlinType = (i & 4) != 0 ? null : coneKotlinType;
        coneTypeProjectionArr = (i & 8) != 0 ? ConeTypeProjection.Companion.getEMPTY_ARRAY() : coneTypeProjectionArr;
        coneAttributes = (i & 16) != 0 ? ConeAttributes.INSTANCE.getEmpty() : coneAttributes;
        bool = (i & 32) != 0 ? null : bool;
        if ((i & 64) != 0) {
            coneClassLikeErrorLookupTag = new ConeClassLikeErrorLookupTag((coneKotlinType == null || (classId = ConeTypeUtilsKt.getClassId(coneKotlinType)) == null) ? ClassId.Companion.fromString$default(ClassId.Companion, "<error>", false, 2, (Object) null) : classId, coneDiagnostic, coneKotlinType);
        }
        this(coneDiagnostic, z, coneKotlinType, coneTypeProjectionArr, coneAttributes, bool, coneClassLikeErrorLookupTag);
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
    public boolean equals(Object other) {
        return this == other;
    }

    public ConeAttributes getAttributes() {
        return this.attributes;
    }

    public final ConeKotlinType getDelegatedType() {
        return getLookupTag().getDelegatedType();
    }

    public final ConeDiagnostic getDiagnostic() {
        return getLookupTag().getDiagnostic();
    }

    public final Boolean getNullable() {
        return this.nullable;
    }

    public ConeTypeProjection[] getTypeArguments() {
        return this.typeArguments;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
    /* JADX INFO: renamed from: isMarkedNullable */
    public boolean getIsMarkedNullable() {
        return Intrinsics.areEqual(this.nullable, Boolean.TRUE);
    }

    /* JADX INFO: renamed from: isUninferredParameter, reason: from getter */
    public final boolean getIsUninferredParameter() {
        return this.isUninferredParameter;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeClassLikeType, org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
    public ConeClassLikeErrorLookupTag getLookupTag() {
        return this.lookupTag;
    }

    public ConeErrorType(ConeDiagnostic coneDiagnostic, boolean z, ConeKotlinType coneKotlinType, ConeTypeProjection[] coneTypeProjectionArr, ConeAttributes coneAttributes, Boolean bool, ConeClassLikeErrorLookupTag coneClassLikeErrorLookupTag) {
        coneDiagnostic.getClass();
        coneTypeProjectionArr.getClass();
        coneAttributes.getClass();
        coneClassLikeErrorLookupTag.getClass();
        this.isUninferredParameter = z;
        this.typeArguments = coneTypeProjectionArr;
        this.attributes = coneAttributes;
        this.nullable = bool;
        this.lookupTag = coneClassLikeErrorLookupTag;
    }
}
