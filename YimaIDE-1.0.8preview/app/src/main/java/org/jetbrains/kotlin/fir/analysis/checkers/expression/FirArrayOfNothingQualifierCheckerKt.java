package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0006"}, d2 = {"unsupportedArrayOfNothingKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ArrayOfNothingKind;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "unsupportedKindOfNothingAsReifiedOrInArray", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirArrayOfNothingQualifierCheckerKt {
    public static final ArrayOfNothingKind unsupportedArrayOfNothingKind(ConeKotlinType coneKotlinType, LanguageVersionSettings languageVersionSettings) {
        ConeTypeProjection coneTypeProjection;
        ConeKotlinType type;
        coneKotlinType.getClass();
        languageVersionSettings.getClass();
        if (!ConeBuiltinTypeUtilsKt.isArrayTypeOrNullableArrayType(coneKotlinType) || (coneTypeProjection = (ConeTypeProjection) ArraysKt.firstOrNull(coneKotlinType.getTypeArguments())) == null || (type = ConeTypeProjectionKt.getType(coneTypeProjection)) == null) {
            return null;
        }
        return unsupportedKindOfNothingAsReifiedOrInArray(type, languageVersionSettings);
    }

    public static final ArrayOfNothingKind unsupportedKindOfNothingAsReifiedOrInArray(ConeKotlinType coneKotlinType, LanguageVersionSettings languageVersionSettings) {
        coneKotlinType.getClass();
        languageVersionSettings.getClass();
        if (ConeBuiltinTypeUtilsKt.isNothing(coneKotlinType)) {
            return ArrayOfNothingKind.ArrayOfNothing;
        }
        if (!ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinType) || languageVersionSettings.supportsFeature(LanguageFeature.NullableNothingInReifiedPosition)) {
            return null;
        }
        return ArrayOfNothingKind.ArrayOfNullableNothing;
    }
}
