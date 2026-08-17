package org.jetbrains.kotlin.fir.types.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/ConeTypeParameterTypeImpl;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeParameterType;", "lookupTag", "Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "isMarkedNullable", Argument.Delimiters.none, "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;ZLorg/jetbrains/kotlin/fir/types/ConeAttributes;)V", "getLookupTag", "()Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "()Z", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeParameterTypeImpl extends ConeTypeParameterType {
    private final ConeAttributes attributes;
    private final boolean isMarkedNullable;
    private final ConeTypeParameterLookupTag lookupTag;

    public ConeTypeParameterTypeImpl(ConeTypeParameterLookupTag coneTypeParameterLookupTag, boolean z, ConeAttributes coneAttributes) {
        coneTypeParameterLookupTag.getClass();
        coneAttributes.getClass();
        this.lookupTag = coneTypeParameterLookupTag;
        this.isMarkedNullable = z;
        this.attributes = coneAttributes;
    }

    public ConeAttributes getAttributes() {
        return this.attributes;
    }

    public ConeTypeProjection[] getTypeArguments() {
        return ConeTypeProjection.Companion.getEMPTY_ARRAY();
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
    /* JADX INFO: renamed from: isMarkedNullable, reason: from getter */
    public boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeTypeParameterType, org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
    public ConeTypeParameterLookupTag getLookupTag() {
        return this.lookupTag;
    }

    public /* synthetic */ ConeTypeParameterTypeImpl(ConeTypeParameterLookupTag coneTypeParameterLookupTag, boolean z, ConeAttributes coneAttributes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneTypeParameterLookupTag, z, (i & 4) != 0 ? ConeAttributes.INSTANCE.getEmpty() : coneAttributes);
    }
}
