package androidx.compose.compiler.plugins.kotlin.k2;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R$\u0010\u0002\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00050\u0004j\u0002`\u00060\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\n0\u0004j\u0002`\u000b0\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR$\u0010\r\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u000e0\u0004j\u0002`\u000f0\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b¨\u0006\u0011"}, d2 = {"androidx/compose/compiler/plugins/kotlin/k2/ComposeFirCheckersExtension$expressionCheckers$1", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "functionCallCheckers", "", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "getFunctionCallCheckers", "()Ljava/util/Set;", "propertyAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirPropertyAccessExpressionChecker;", "getPropertyAccessExpressionCheckers", "callableReferenceAccessCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallableReferenceAccessChecker;", "getCallableReferenceAccessCheckers", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeFirCheckersExtension$expressionCheckers$1 extends ExpressionCheckers {
    private final Set<FirExpressionChecker<FirFunctionCall>> functionCallCheckers = SetsKt.setOf(new FirExpressionChecker[]{ComposableFunctionCallChecker.INSTANCE, ComposableTargetChecker.INSTANCE});
    private final Set<FirExpressionChecker<FirPropertyAccessExpression>> propertyAccessExpressionCheckers = SetsKt.setOf(ComposablePropertyAccessExpressionChecker.INSTANCE);
    private final Set<FirExpressionChecker<FirCallableReferenceAccess>> callableReferenceAccessCheckers = SetsKt.setOf(ComposablePropertyReferenceChecker.INSTANCE);

    public Set<FirExpressionChecker<FirCallableReferenceAccess>> getCallableReferenceAccessCheckers() {
        return this.callableReferenceAccessCheckers;
    }

    public Set<FirExpressionChecker<FirFunctionCall>> getFunctionCallCheckers() {
        return this.functionCallCheckers;
    }

    public Set<FirExpressionChecker<FirPropertyAccessExpression>> getPropertyAccessExpressionCheckers() {
        return this.propertyAccessExpressionCheckers;
    }
}
