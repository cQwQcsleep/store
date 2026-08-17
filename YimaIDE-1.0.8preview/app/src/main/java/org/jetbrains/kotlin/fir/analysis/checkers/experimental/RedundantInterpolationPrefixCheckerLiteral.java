package org.jetbrains.kotlin.fir.analysis.checkers.experimental;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/experimental/RedundantInterpolationPrefixCheckerLiteral;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLiteralExpressionChecker;", "<init>", "()V", "redundancyRegex", "Lkotlin/text/Regex;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantInterpolationPrefixCheckerLiteral extends FirExpressionChecker<FirLiteralExpression> {
    public static final RedundantInterpolationPrefixCheckerLiteral INSTANCE = new RedundantInterpolationPrefixCheckerLiteral();
    private static final Regex redundancyRegex = new Regex("(\\$+)(\\w|\\{|`[^`])");

    private RedundantInterpolationPrefixCheckerLiteral() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirLiteralExpression firLiteralExpression) {
        CharSequence text;
        CharSequence charSequenceDrop;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firLiteralExpression.getClass();
        String prefix = firLiteralExpression.getPrefix();
        if (prefix != null) {
            int length = prefix.length();
            if (length == 1) {
                RedundantInterpolationPrefixCheckerLiteralKt.reportRedundantInterpolationPrefix(checkerContext, diagnosticReporter, firLiteralExpression);
            } else {
                if (!Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.String.INSTANCE) || (text = KtSourceElementKt.getText(firLiteralExpression.getSource())) == null || (charSequenceDrop = StringsKt.drop(text, length)) == null || redundancyRegex.containsMatchIn(charSequenceDrop)) {
                    return;
                }
                RedundantInterpolationPrefixCheckerLiteralKt.reportRedundantInterpolationPrefix(checkerContext, diagnosticReporter, firLiteralExpression);
            }
        }
    }
}
