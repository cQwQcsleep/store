package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirTypeCompatibilityHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.TypeInfo;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001a \u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\t"}, d2 = {"isCaseMissedByK1Intersector", Argument.Delimiters.none, "a", "Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "b", "isCaseMissedByAdditionalK1IncompatibleEnumsCheck", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEqualityCompatibilityCheckerKt {
    public static final boolean isCaseMissedByAdditionalK1IncompatibleEnumsCheck(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firSession.getClass();
        if (FirTypeCompatibilityHelpersKt.isEnum(coneKotlinType, firSession) || FirTypeCompatibilityHelpersKt.isEnum(coneKotlinType2, firSession)) {
            return (ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneKotlinType) && ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneKotlinType2)) || ConeBuiltinTypeUtilsKt.isNothingOrNullableNothing(coneKotlinType) || ConeBuiltinTypeUtilsKt.isNothingOrNullableNothing(coneKotlinType2) || !FirTypeCompatibilityHelpersKt.isClass(coneKotlinType, firSession) || !FirTypeCompatibilityHelpersKt.isClass(coneKotlinType2, firSession);
        }
        return true;
    }

    public static final boolean isCaseMissedByK1Intersector(TypeInfo typeInfo, TypeInfo typeInfo2) {
        typeInfo.getClass();
        typeInfo2.getClass();
        return typeInfo.getCanHaveSubtypesAccordingToK1() && typeInfo2.getCanHaveSubtypesAccordingToK1();
    }
}
