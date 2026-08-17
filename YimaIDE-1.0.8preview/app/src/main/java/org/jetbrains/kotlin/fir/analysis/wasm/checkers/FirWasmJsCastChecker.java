package org.jetbrains.kotlin.fir.analysis.wasm.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificCastChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.FirTypeCompatibilityHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.TypeInfo;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCastOperatorsChecker;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.name.JsStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u000fJ\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0005H\u0002J9\u0010\u0012\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/wasm/checkers/FirWasmJsCastChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformSpecificCastChecker;", "<init>", "()V", "runApplicabilityCheck", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "fromType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "toType", "checker", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "isImpossibleCastOrIsCheck", Argument.Delimiters.none, "shouldSuppressImpossibleCastOrIsCheck", "Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;)Z", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmJsCastChecker extends FirPlatformSpecificCastChecker {
    public static final FirWasmJsCastChecker INSTANCE = new FirWasmJsCastChecker();

    private FirWasmJsCastChecker() {
    }

    private final boolean isImpossibleCastOrIsCheck(FirCastOperatorsChecker.Applicability applicability) {
        return applicability == FirCastOperatorsChecker.Applicability.IMPOSSIBLE_CAST || applicability == FirCastOperatorsChecker.Applicability.IMPOSSIBLE_IS_CHECK;
    }

    private final boolean shouldSuppressImpossibleCastOrIsCheck(CheckerContext checkerContext, FirTypeOperatorCall firTypeOperatorCall, ConeKotlinType coneKotlinType, TypeInfo typeInfo, FirCastOperatorsChecker firCastOperatorsChecker) {
        ConeKotlinType type;
        if (!Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType), JsStandardClassIds.JsReference) || coneKotlinType.getTypeArguments().length != 1) {
            return Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType), JsStandardClassIds.JsAny);
        }
        ConeKotlinTypeProjectionOut coneKotlinTypeProjectionOut = coneKotlinType.getTypeArguments()[0];
        if (coneKotlinTypeProjectionOut instanceof ConeKotlinTypeProjectionOut) {
            type = coneKotlinTypeProjectionOut.getType();
        } else {
            if (!(coneKotlinTypeProjectionOut instanceof ConeKotlinType)) {
                return true;
            }
            type = (ConeKotlinType) coneKotlinTypeProjectionOut;
        }
        return !isImpossibleCastOrIsCheck(firCastOperatorsChecker.checkGeneralApplicability(checkerContext, firTypeOperatorCall, FirTypeCompatibilityHelpersKt.toTypeInfo(type, checkerContext.getSession()), typeInfo));
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificCastChecker
    public FirCastOperatorsChecker.Applicability runApplicabilityCheck(CheckerContext checkerContext, FirTypeOperatorCall firTypeOperatorCall, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirCastOperatorsChecker firCastOperatorsChecker) {
        checkerContext.getClass();
        firTypeOperatorCall.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firCastOperatorsChecker.getClass();
        TypeInfo typeInfo = FirTypeCompatibilityHelpersKt.toTypeInfo(coneKotlinType, checkerContext.getSession());
        TypeInfo typeInfo2 = FirTypeCompatibilityHelpersKt.toTypeInfo(coneKotlinType2, checkerContext.getSession());
        FirCastOperatorsChecker.Applicability applicabilityCheckGeneralApplicability = firCastOperatorsChecker.checkGeneralApplicability(checkerContext, firTypeOperatorCall, typeInfo, typeInfo2);
        FirWasmJsCastChecker firWasmJsCastChecker = INSTANCE;
        if (firWasmJsCastChecker.isImpossibleCastOrIsCheck(applicabilityCheckGeneralApplicability) && firWasmJsCastChecker.shouldSuppressImpossibleCastOrIsCheck(checkerContext, firTypeOperatorCall, coneKotlinType, typeInfo2, firCastOperatorsChecker)) {
            applicabilityCheckGeneralApplicability = null;
        }
        return applicabilityCheckGeneralApplicability == null ? FirCastOperatorsChecker.Applicability.APPLICABLE : applicabilityCheckGeneralApplicability;
    }
}
