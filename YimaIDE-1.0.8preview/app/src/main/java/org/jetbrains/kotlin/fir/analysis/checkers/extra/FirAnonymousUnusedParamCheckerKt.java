package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"createLambdaBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;", "lambda", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousUnusedParamCheckerKt {
    public static final FirAnonymousUnusedParamChecker.LambdaBodyContext createLambdaBodyContext(FirAnonymousFunction firAnonymousFunction, CheckerContext checkerContext) {
        firAnonymousFunction.getClass();
        checkerContext.getClass();
        FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext = checkerContext.getLambdaBodyContext();
        return lambdaBodyContext == null ? new FirAnonymousUnusedParamChecker.LambdaBodyContext(firAnonymousFunction) : lambdaBodyContext;
    }
}
