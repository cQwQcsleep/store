package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/PrimitiveConeNumericComparisonInfo;", Argument.Delimiters.none, "comparisonType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "leftType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "rightType", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getComparisonType", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "getLeftType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getRightType", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PrimitiveConeNumericComparisonInfo {
    private final ConeClassLikeType comparisonType;
    private final ConeKotlinType leftType;
    private final ConeKotlinType rightType;

    public PrimitiveConeNumericComparisonInfo(ConeClassLikeType coneClassLikeType, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        coneClassLikeType.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        this.comparisonType = coneClassLikeType;
        this.leftType = coneKotlinType;
        this.rightType = coneKotlinType2;
    }

    public final ConeClassLikeType getComparisonType() {
        return this.comparisonType;
    }

    public final ConeKotlinType getLeftType() {
        return this.leftType;
    }

    public final ConeKotlinType getRightType() {
        return this.rightType;
    }
}
