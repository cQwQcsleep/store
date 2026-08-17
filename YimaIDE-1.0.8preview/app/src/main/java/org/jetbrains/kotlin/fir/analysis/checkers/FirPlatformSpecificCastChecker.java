package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCastOperatorsChecker;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH&R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u000f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformSpecificCastChecker;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "runApplicabilityCheck", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "fromType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "toType", "checker", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "Default", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirPlatformSpecificCastChecker implements FirSessionComponent {

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformSpecificCastChecker$Default;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformSpecificCastChecker;", "<init>", "()V", "runApplicabilityCheck", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "fromType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "toType", "checker", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends FirPlatformSpecificCastChecker {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificCastChecker
        public FirCastOperatorsChecker.Applicability runApplicabilityCheck(CheckerContext checkerContext, FirTypeOperatorCall firTypeOperatorCall, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirCastOperatorsChecker firCastOperatorsChecker) {
            checkerContext.getClass();
            firTypeOperatorCall.getClass();
            coneKotlinType.getClass();
            coneKotlinType2.getClass();
            firCastOperatorsChecker.getClass();
            return firCastOperatorsChecker.checkGeneralApplicability(checkerContext, firTypeOperatorCall, FirTypeCompatibilityHelpersKt.toTypeInfo(coneKotlinType, checkerContext.get$session()), FirTypeCompatibilityHelpersKt.toTypeInfo(coneKotlinType2, checkerContext.get$session()));
        }
    }

    public abstract FirCastOperatorsChecker.Applicability runApplicabilityCheck(CheckerContext checkerContext, FirTypeOperatorCall firTypeOperatorCall, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirCastOperatorsChecker firCastOperatorsChecker);
}
