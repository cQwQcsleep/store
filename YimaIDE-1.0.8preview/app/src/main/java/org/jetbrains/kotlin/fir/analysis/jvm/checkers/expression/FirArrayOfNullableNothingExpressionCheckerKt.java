package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isArrayOfNullableNothing", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:checkers.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirArrayOfNullableNothingExpressionCheckerKt {
    public static final boolean isArrayOfNullableNothing(ConeKotlinType coneKotlinType) {
        ConeTypeProjection coneTypeProjection;
        ConeKotlinType type;
        coneKotlinType.getClass();
        if (!ConeBuiltinTypeUtilsKt.isArrayTypeOrNullableArrayType(coneKotlinType) || (coneTypeProjection = (ConeTypeProjection) ArraysKt.firstOrNull(coneKotlinType.getTypeArguments())) == null || (type = ConeTypeProjectionKt.getType(coneTypeProjection)) == null) {
            return false;
        }
        return ConeBuiltinTypeUtilsKt.isNullableNothing(type);
    }
}
