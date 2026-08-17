package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMultiDollarInterpolationCheckerLiteral;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMultiDollarInterpolationChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "<init>", "()V", "getInterpolationPrefix", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMultiDollarInterpolationCheckerLiteral extends FirMultiDollarInterpolationChecker<FirLiteralExpression> {
    public static final FirMultiDollarInterpolationCheckerLiteral INSTANCE = new FirMultiDollarInterpolationCheckerLiteral();

    private FirMultiDollarInterpolationCheckerLiteral() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirMultiDollarInterpolationChecker
    public String getInterpolationPrefix(FirLiteralExpression firLiteralExpression) {
        firLiteralExpression.getClass();
        String prefix = firLiteralExpression.getPrefix();
        if (prefix == null || !Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.String.INSTANCE)) {
            return null;
        }
        return prefix;
    }
}
