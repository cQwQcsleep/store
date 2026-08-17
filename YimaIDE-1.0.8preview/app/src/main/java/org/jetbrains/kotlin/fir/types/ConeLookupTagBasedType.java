package org.jetbrains.kotlin.fir.types;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "<init>", "()V", "lookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "getLookupTag", "()Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "isMarkedNullable", Argument.Delimiters.none, "()Z", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeLookupTagBasedType extends ConeSimpleKotlinType {
    public ConeLookupTagBasedType() {
        super(null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConeLookupTagBasedType)) {
            return false;
        }
        ConeLookupTagBasedType coneLookupTagBasedType = (ConeLookupTagBasedType) other;
        return Intrinsics.areEqual(getLookupTag(), coneLookupTagBasedType.getLookupTag()) && Arrays.equals(getTypeArguments(), coneLookupTagBasedType.getTypeArguments()) && isMarkedNullable() == coneLookupTagBasedType.isMarkedNullable() && !getAttributes().definitelyDifferFrom(coneLookupTagBasedType.getAttributes());
    }

    public abstract ConeClassifierLookupTag getLookupTag();

    public int hashCode() {
        return (((getLookupTag().hashCode() * 31) + Arrays.hashCode(getTypeArguments())) * 31) + Boolean.hashCode(isMarkedNullable());
    }

    public abstract boolean isMarkedNullable();
}
