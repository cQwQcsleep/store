package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0003\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0001\u0003\t\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker;", "T", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "<init>", "()V", "ResolvedQualifier", "CallableReference", "FunctionCall", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker$CallableReference;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker$FunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker$ResolvedQualifier;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker<T extends FirExpression> extends FirExpressionChecker<T> {

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J-\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u00020\u0007R\u00020\tj\u0006\u0010\b\u001a\u00020\u0007j\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker$CallableReference;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CallableReference extends FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker<FirCallableReferenceAccess> {
        public static final CallableReference INSTANCE = new CallableReference();

        private CallableReference() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableReferenceAccess firCallableReferenceAccess) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firCallableReferenceAccess.getClass();
            ClassId classId = null;
            FirConstructorSymbol resolvedConstructorSymbol$default = FirReferenceUtilsKt.toResolvedConstructorSymbol$default(firCallableReferenceAccess.getCalleeReference(), false, 1, null);
            if (resolvedConstructorSymbol$default != null && (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(resolvedConstructorSymbol$default)) != null) {
                classId = coneClassLikeLookupTagContainingClassLookupTag.getClassId();
            }
            if (Intrinsics.areEqual(classId, StandardClassIds$Annotations.INSTANCE.getKotlinActual())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableReferenceAccess.getSource(), FirErrors.INSTANCE.getKOTLIN_ACTUAL_ANNOTATION_HAS_NO_EFFECT_IN_KOTLIN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J-\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u00020\u0007R\u00020\tj\u0006\u0010\b\u001a\u00020\u0007j\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker$FunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FunctionCall extends FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker<FirFunctionCall> {
        public static final FunctionCall INSTANCE = new FunctionCall();

        private FunctionCall() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firFunctionCall.getClass();
            ClassId classId = null;
            FirConstructorSymbol resolvedConstructorSymbol$default = FirReferenceUtilsKt.toResolvedConstructorSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
            if (resolvedConstructorSymbol$default != null && (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(resolvedConstructorSymbol$default)) != null) {
                classId = coneClassLikeLookupTagContainingClassLookupTag.getClassId();
            }
            if (Intrinsics.areEqual(classId, StandardClassIds$Annotations.INSTANCE.getKotlinActual())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), FirErrors.INSTANCE.getKOTLIN_ACTUAL_ANNOTATION_HAS_NO_EFFECT_IN_KOTLIN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J-\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u00020\u0007R\u00020\tj\u0006\u0010\b\u001a\u00020\u0007j\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker$ResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ResolvedQualifier extends FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker<FirResolvedQualifier> {
        public static final ResolvedQualifier INSTANCE = new ResolvedQualifier();

        private ResolvedQualifier() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firResolvedQualifier.getClass();
            if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getResolvedType(firResolvedQualifier)), StandardClassIds$Annotations.INSTANCE.getKotlinActual())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifier.getSource(), FirErrors.INSTANCE.getKOTLIN_ACTUAL_ANNOTATION_HAS_NO_EFFECT_IN_KOTLIN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker() {
        super(MppCheckerKind.Common);
    }

    public /* synthetic */ FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
