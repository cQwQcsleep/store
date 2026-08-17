package org.jetbrains.kotlin.fir.analysis.checkers.experimental;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0002H\u0002¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/experimental/EmptyRangeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "compareLeftAndRight", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)Ljava/lang/Integer;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EmptyRangeChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final EmptyRangeChecker INSTANCE = new EmptyRangeChecker();

    private EmptyRangeChecker() {
        super(MppCheckerKind.Common);
    }

    private final Integer compareLeftAndRight(FirFunctionCall firFunctionCall) {
        Object value;
        Object value2;
        FirExpression explicitReceiver = firFunctionCall.getExplicitReceiver();
        FirLiteralExpression firLiteralExpression = explicitReceiver instanceof FirLiteralExpression ? (FirLiteralExpression) explicitReceiver : null;
        if (firLiteralExpression != null && (value = firLiteralExpression.getValue()) != null) {
            Object orNull = CollectionsKt.getOrNull(firFunctionCall.getArgumentList().getArguments(), 0);
            FirLiteralExpression firLiteralExpression2 = orNull instanceof FirLiteralExpression ? (FirLiteralExpression) orNull : null;
            if (firLiteralExpression2 != null && (value2 = firLiteralExpression2.getValue()) != null) {
                if (value instanceof Long) {
                    Long l = value2 instanceof Long ? (Long) value2 : null;
                    if (l != null) {
                        return Integer.valueOf(Intrinsics.compare(((Number) value).longValue(), l.longValue()));
                    }
                    return null;
                }
                if (value instanceof Float) {
                    Float f = value2 instanceof Float ? (Float) value2 : null;
                    if (f != null) {
                        return Integer.valueOf(Float.compare(((Number) value).floatValue(), f.floatValue()));
                    }
                    return null;
                }
                if (value instanceof Double) {
                    Double d = value2 instanceof Double ? (Double) value2 : null;
                    if (d != null) {
                        return Integer.valueOf(Double.compare(((Number) value).doubleValue(), d.doubleValue()));
                    }
                    return null;
                }
                if (value instanceof Character) {
                    Character ch = value2 instanceof Character ? (Character) value2 : null;
                    if (ch != null) {
                        return Integer.valueOf(Intrinsics.compare((int) ((Character) value).charValue(), (int) ch.charValue()));
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        Integer numCompareLeftAndRight;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        KtSourceElement source = firFunctionCall.getSource();
        if (((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) || (numCompareLeftAndRight = compareLeftAndRight(firFunctionCall)) == null) {
            return;
        }
        int iIntValue = numCompareLeftAndRight.intValue();
        String strAsString = firFunctionCall.getCalleeReference().getName().asString();
        switch (strAsString.hashCode()) {
            case -1824484319:
                if (!strAsString.equals("rangeUntil")) {
                }
                if (iIntValue < 0) {
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), FirErrors.INSTANCE.getEMPTY_RANGE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                break;
            case -1325887811:
                if (!strAsString.equals("downTo") || iIntValue >= 0) {
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), FirErrors.INSTANCE.getEMPTY_RANGE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                break;
            case 111443806:
                if (!strAsString.equals("until")) {
                }
                if (iIntValue < 0) {
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), FirErrors.INSTANCE.getEMPTY_RANGE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                break;
            case 977987736:
                if (!strAsString.equals("rangeTo") || iIntValue <= 0) {
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), FirErrors.INSTANCE.getEMPTY_RANGE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                break;
        }
    }
}
