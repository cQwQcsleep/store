package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"createInlinableParameterContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInlineBodyResolvableExpressionCheckerKt {
    public static final FirInlineBodyResolvableExpressionChecker.InlinableParameterContext createInlinableParameterContext(FirFunction firFunction, FirSession firSession) {
        firFunction.getClass();
        firSession.getClass();
        List<FirValueParameter> valueParameters = firFunction.getValueParameters();
        ArrayList arrayList = new ArrayList();
        for (FirValueParameter firValueParameter : valueParameters) {
            if (!DeclarationUtilsKt.isInlinable(firValueParameter, firSession)) {
                firValueParameter = null;
            }
            FirValueParameterSymbol symbol = firValueParameter != null ? firValueParameter.getSymbol() : null;
            if (symbol != null) {
                arrayList.add(symbol);
            }
        }
        return new FirInlineBodyResolvableExpressionChecker.InlinableParameterContext(firFunction, arrayList, firSession);
    }
}
