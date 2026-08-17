package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMultiDollarInterpolationCheckerConcatenation;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMultiDollarInterpolationChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "<init>", "()V", "getInterpolationPrefix", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMultiDollarInterpolationCheckerConcatenation extends FirMultiDollarInterpolationChecker<FirStringConcatenationCall> {
    public static final FirMultiDollarInterpolationCheckerConcatenation INSTANCE = new FirMultiDollarInterpolationCheckerConcatenation();

    private FirMultiDollarInterpolationCheckerConcatenation() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirMultiDollarInterpolationChecker
    public String getInterpolationPrefix(FirStringConcatenationCall firStringConcatenationCall) {
        firStringConcatenationCall.getClass();
        return firStringConcatenationCall.getInterpolationPrefix();
    }
}
