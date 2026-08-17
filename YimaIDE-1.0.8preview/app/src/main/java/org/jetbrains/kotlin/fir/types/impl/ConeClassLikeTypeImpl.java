package org.jetbrains.kotlin.fir.types.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.util.WeakPair;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000fR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R&\u0010\u0015\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/ConeClassLikeTypeImpl;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "lookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "isMarkedNullable", Argument.Delimiters.none, "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;ZLorg/jetbrains/kotlin/fir/types/ConeAttributes;)V", "getLookupTag", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "()Z", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "cachedExpandedType", "Lorg/jetbrains/kotlin/util/WeakPair;", "getCachedExpandedType", "()Lorg/jetbrains/kotlin/util/WeakPair;", "setCachedExpandedType", "(Lorg/jetbrains/kotlin/util/WeakPair;)V", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeClassLikeTypeImpl extends ConeClassLikeType {
    private final ConeAttributes attributes;
    private WeakPair<?, ConeClassLikeType> cachedExpandedType;
    private final boolean isMarkedNullable;
    private final ConeClassLikeLookupTag lookupTag;
    private final ConeTypeProjection[] typeArguments;

    public ConeClassLikeTypeImpl(ConeClassLikeLookupTag coneClassLikeLookupTag, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes) {
        coneClassLikeLookupTag.getClass();
        coneTypeProjectionArr.getClass();
        coneAttributes.getClass();
        this.lookupTag = coneClassLikeLookupTag;
        this.isMarkedNullable = z;
        this.attributes = coneAttributes;
        this.typeArguments = coneTypeProjectionArr.length == 0 ? ConeTypeProjection.Companion.getEMPTY_ARRAY() : coneTypeProjectionArr;
    }

    public ConeAttributes getAttributes() {
        return this.attributes;
    }

    public final WeakPair<?, ConeClassLikeType> getCachedExpandedType() {
        return this.cachedExpandedType;
    }

    public ConeTypeProjection[] getTypeArguments() {
        return this.typeArguments;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
    /* JADX INFO: renamed from: isMarkedNullable, reason: from getter */
    public boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    public final void setCachedExpandedType(WeakPair<?, ConeClassLikeType> weakPair) {
        this.cachedExpandedType = weakPair;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeClassLikeType, org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
    public ConeClassLikeLookupTag getLookupTag() {
        return this.lookupTag;
    }

    public /* synthetic */ ConeClassLikeTypeImpl(ConeClassLikeLookupTag coneClassLikeLookupTag, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneClassLikeLookupTag, coneTypeProjectionArr, z, (i & 8) != 0 ? ConeAttributes.INSTANCE.getEmpty() : coneAttributes);
    }
}
