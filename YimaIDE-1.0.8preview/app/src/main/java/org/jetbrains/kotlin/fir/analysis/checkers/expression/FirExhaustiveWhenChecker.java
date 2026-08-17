package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatus;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirElseIfTrueCondition;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyExpressionBlock;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ?\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\u0018*\u00020\u0019H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001aJ-\u0010 \u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010!J-\u0010\"\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rR\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010#\u001a\u00020\u0018*\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0018\u0010%\u001a\u00020\u0018*\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010$¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExhaustiveWhenChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)V", "reportEmptyThenInExpression", "whenExpression", "reportNotExhaustive", "reportNoElseInWhen", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "subjectClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "isJavaNonAbstractSealed", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/name/ClassId;)Z", "missingCases", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "getMissingCases", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)Ljava/util/List;", "reportElseMisplaced", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)V", "reportRedundantElse", "isIfExpression", "(Lorg/jetbrains/kotlin/KtSourceElement;)Z", "isWhenExpression", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExhaustiveWhenChecker extends FirExpressionChecker<FirWhenExpression> {
    public static final FirExhaustiveWhenChecker INSTANCE = new FirExhaustiveWhenChecker();

    private FirExhaustiveWhenChecker() {
        super(MppCheckerKind.Common);
    }

    private final List<WhenMissingCase> getMissingCases(FirWhenExpression firWhenExpression) {
        ExhaustivenessStatus exhaustivenessStatus = firWhenExpression.getExhaustivenessStatus();
        exhaustivenessStatus.getClass();
        return ((ExhaustivenessStatus.NotExhaustive) exhaustivenessStatus).getReasons();
    }

    private final boolean isIfExpression(KtSourceElement ktSourceElement) {
        return Intrinsics.areEqual(ktSourceElement.getElementType(), KtNodeTypes.IF);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isJavaNonAbstractSealed(CheckerContext checkerContext, ClassId classId) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(checkerContext, classId);
        FirClassLikeSymbol firClassLikeSymbol = symbol instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) symbol : null;
        if (firClassLikeSymbol == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(checkerContext, (FirClassLikeSymbol<?>) firClassLikeSymbol)) == null) {
            return false;
        }
        return Intrinsics.areEqual(ClassMembersKt.isJavaNonAbstractSealed((FirRegularClass) firRegularClassSymbolFullyExpandedClass.getFir()), Boolean.TRUE);
    }

    private final boolean isWhenExpression(KtSourceElement ktSourceElement) {
        return Intrinsics.areEqual(ktSourceElement.getElementType(), KtNodeTypes.WHEN);
    }

    private final void reportElseMisplaced(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirWhenExpression firWhenExpression) {
        int size = firWhenExpression.getBranches().size();
        for (IndexedValue indexedValue : CollectionsKt.withIndex(firWhenExpression.getBranches())) {
            FirWhenBranch firWhenBranch = (FirWhenBranch) indexedValue.getValue();
            if ((firWhenBranch.getCondition() instanceof FirElseIfTrueCondition) && indexedValue.getIndex() < size - 1) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firWhenBranch.getSource(), FirErrors.INSTANCE.getELSE_MISPLACED_IN_WHEN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final void reportEmptyThenInExpression(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression) {
        KtSourceElement source = firWhenExpression.getSource();
        if (source != null && isIfExpression(source) && firWhenExpression.getUsedAsExpression()) {
            FirWhenBranch firWhenBranch = (FirWhenBranch) CollectionsKt.firstOrNull(firWhenExpression.getBranches());
            if (firWhenBranch == null || (firWhenBranch.getResult() instanceof FirEmptyExpressionBlock)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getINVALID_IF_AS_EXPRESSION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0044  */
    private final void reportNoElseInWhen(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, KtSourceElement ktSourceElement, FirWhenExpression firWhenExpression, FirRegularClassSymbol firRegularClassSymbol) {
        String str;
        List<WhenMissingCase> missingCases = getMissingCases(firWhenExpression);
        List<WhenMissingCase> list = missingCases;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    WhenMissingCase whenMissingCase = (WhenMissingCase) it.next();
                    if (!(whenMissingCase instanceof WhenMissingCase.IsTypeCheckIsMissing) || !INSTANCE.isJavaNonAbstractSealed(checkerContext, ((WhenMissingCase.IsTypeCheckIsMissing) whenMissingCase).getClassId())) {
                    }
                } else if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ProperExhaustivenessCheckForJavaOpenSealedClass)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getMISSING_BRANCH_FOR_NON_ABSTRACT_SEALED_CLASS(), (Object) missingCases, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    return;
                }
            }
        } else if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ProperExhaustivenessCheckForJavaOpenSealedClass)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getMISSING_BRANCH_FOR_NON_ABSTRACT_SEALED_CLASS(), (Object) missingCases, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (Intrinsics.areEqual(firRegularClassSymbol != null ? Boolean.valueOf(firRegularClassSymbol.getRawStatus().isExpect()) : null, Boolean.TRUE)) {
            str = " ('when' with expect " + (firRegularClassSymbol.getClassKind() == ClassKind.ENUM_CLASS ? "enum" : "sealed") + " subject cannot be exhaustive without else branch)";
        } else {
            str = Argument.Delimiters.none;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getNO_ELSE_IN_WHEN(), (Object) missingCases, (Object) str, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final void reportNotExhaustive(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression) {
        ExhaustivenessStatus exhaustivenessStatus = firWhenExpression.getExhaustivenessStatus();
        if (exhaustivenessStatus == null) {
            return;
        }
        if (!(exhaustivenessStatus instanceof ExhaustivenessStatus.NotExhaustive)) {
            reportEmptyThenInExpression(checkerContext, diagnosticReporter, firWhenExpression);
            return;
        }
        KtSourceElement source = firWhenExpression.getSource();
        if (source == null) {
            return;
        }
        ConeKotlinType subjectType = ((ExhaustivenessStatus.NotExhaustive) exhaustivenessStatus).getSubjectType();
        FirRegularClassSymbol regularClassSymbol = subjectType != null ? ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, subjectType) : null;
        if (firWhenExpression.getUsedAsExpression()) {
            if (isIfExpression(source)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getINVALID_IF_AS_EXPRESSION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            } else {
                if (isWhenExpression(source)) {
                    reportNoElseInWhen(diagnosticReporter, checkerContext, source, firWhenExpression, regularClassSymbol);
                    return;
                }
                return;
            }
        }
        if (regularClassSymbol == null) {
            return;
        }
        if (regularClassSymbol.getResolvedStatus().getModality() == Modality.SEALED || regularClassSymbol.getClassKind() == ClassKind.ENUM_CLASS || ConeBuiltinTypeUtilsKt.isBooleanOrNullableBoolean(subjectType)) {
            reportNoElseInWhen(diagnosticReporter, checkerContext, source, firWhenExpression, regularClassSymbol);
        }
    }

    private final void reportRedundantElse(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression) {
        if (Intrinsics.areEqual(firWhenExpression.getExhaustivenessStatus(), ExhaustivenessStatus.RedundantlyExhaustive.INSTANCE)) {
            for (FirWhenBranch firWhenBranch : firWhenExpression.getBranches()) {
                if (firWhenBranch.getSource() != null && (firWhenBranch.getCondition() instanceof FirElseIfTrueCondition)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firWhenBranch.getSource(), FirErrors.INSTANCE.getREDUNDANT_ELSE_IN_WHEN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firWhenExpression.getClass();
        reportNotExhaustive(checkerContext, diagnosticReporter, firWhenExpression);
        reportElseMisplaced(diagnosticReporter, checkerContext, firWhenExpression);
        reportRedundantElse(checkerContext, diagnosticReporter, firWhenExpression);
    }
}
