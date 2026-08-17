package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificCastChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.FirTypeCompatibilityHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCastOperatorsChecker;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeCastChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformSpecificCastChecker;", "<init>", "()V", "runApplicabilityCheck", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "fromType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "toType", "checker", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "isCastToAForwardDeclaration", Argument.Delimiters.none, "forwardDeclarationType", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeCastChecker extends FirPlatformSpecificCastChecker {
    public static final FirNativeCastChecker INSTANCE = new FirNativeCastChecker();

    private FirNativeCastChecker() {
    }

    private final boolean isCastToAForwardDeclaration(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneKotlinType, checkerContext.getSession());
        return (regularClassSymbol != null ? FirNativeHelpersKt.forwardDeclarationKindOrNull(regularClassSymbol) : null) != null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificCastChecker
    public FirCastOperatorsChecker.Applicability runApplicabilityCheck(CheckerContext checkerContext, FirTypeOperatorCall firTypeOperatorCall, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirCastOperatorsChecker firCastOperatorsChecker) {
        checkerContext.getClass();
        firTypeOperatorCall.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firCastOperatorsChecker.getClass();
        FirCastOperatorsChecker.Applicability applicabilityCheckGeneralApplicability = firCastOperatorsChecker.checkGeneralApplicability(checkerContext, firTypeOperatorCall, FirTypeCompatibilityHelpersKt.toTypeInfo(coneKotlinType, checkerContext.getSession()), FirTypeCompatibilityHelpersKt.toTypeInfo(coneKotlinType2, checkerContext.getSession()));
        if (applicabilityCheckGeneralApplicability == FirCastOperatorsChecker.Applicability.IMPOSSIBLE_CAST && INSTANCE.isCastToAForwardDeclaration(checkerContext, coneKotlinType2)) {
            applicabilityCheckGeneralApplicability = null;
        }
        return applicabilityCheckGeneralApplicability == null ? FirCastOperatorsChecker.Applicability.APPLICABLE : applicabilityCheckGeneralApplicability;
    }
}
