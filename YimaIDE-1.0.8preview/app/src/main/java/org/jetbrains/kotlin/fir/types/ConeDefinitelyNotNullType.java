package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.DefinitelyNotNullTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001a2\u00020\u00012\u00020\u0002:\u0001\u001aB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeDefinitelyNotNullType;", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "Lorg/jetbrains/kotlin/types/model/DefinitelyNotNullTypeMarker;", "original", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;)V", "getOriginal", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getTypeArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ConeDefinitelyNotNullType extends ConeRigidType implements DefinitelyNotNullTypeMarker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ConeSimpleKotlinType original;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeDefinitelyNotNullType(ConeSimpleKotlinType coneSimpleKotlinType) {
        super(null);
        coneSimpleKotlinType.getClass();
        this.original = coneSimpleKotlinType;
    }

    public static /* synthetic */ ConeDefinitelyNotNullType copy$default(ConeDefinitelyNotNullType coneDefinitelyNotNullType, ConeSimpleKotlinType coneSimpleKotlinType, int i, Object obj) {
        if ((i & 1) != 0) {
            coneSimpleKotlinType = coneDefinitelyNotNullType.original;
        }
        return coneDefinitelyNotNullType.copy(coneSimpleKotlinType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ConeSimpleKotlinType getOriginal() {
        return this.original;
    }

    public final ConeDefinitelyNotNullType copy(ConeSimpleKotlinType original) {
        original.getClass();
        return new ConeDefinitelyNotNullType(original);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ConeDefinitelyNotNullType) && Intrinsics.areEqual(this.original, ((ConeDefinitelyNotNullType) other).original);
    }

    public ConeAttributes getAttributes() {
        return this.original.getAttributes();
    }

    public final ConeSimpleKotlinType getOriginal() {
        return this.original;
    }

    public ConeTypeProjection[] getTypeArguments() {
        return ConeTypeProjection.Companion.getEMPTY_ARRAY();
    }

    public int hashCode() {
        return this.original.hashCode();
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeDefinitelyNotNullType$Companion;", Argument.Delimiters.none, "<init>", "()V", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
