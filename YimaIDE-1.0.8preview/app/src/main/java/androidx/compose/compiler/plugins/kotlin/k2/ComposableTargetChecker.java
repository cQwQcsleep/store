package androidx.compose.compiler.plugins.kotlin.k2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposableTargetChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", "", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableTargetChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final ComposableTargetChecker INSTANCE = new ComposableTargetChecker();

    private ComposableTargetChecker() {
        super(MppCheckerKind.Common);
    }

    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        FirInferenceNode firInferenceNodeInferenceNodeOf;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firFunctionCall.getCalleeReference(), false, 1, (Object) null);
        if (resolvedCallableSymbol$default != null && FirUtilsKt.isComposable(resolvedCallableSymbol$default, checkerContext.getSession())) {
            ComposableTargetCheckerKt.updateParents(checkerContext);
            FirApplierInferencer firApplierInferencer = new FirApplierInferencer(checkerContext, diagnosticReporter);
            FirInferenceNode firInferenceNodeInferenceNodeOf2 = ComposableTargetCheckerKt.inferenceNodeOf(firFunctionCall, checkerContext);
            FirInferenceNode firInferenceNodeCallableInferenceNodeOf = ComposableTargetCheckerKt.callableInferenceNodeOf(firFunctionCall, resolvedCallableSymbol$default, checkerContext);
            List<FirValueParameterSymbol> listParameters = ComposableTargetCheckerKt.parameters(checkerContext, resolvedCallableSymbol$default);
            FirResolvedArgumentList argumentList = firFunctionCall.getArgumentList();
            LinkedHashMap mapping = argumentList instanceof FirResolvedArgumentList ? argumentList.getMapping() : null;
            ArrayList arrayList = new ArrayList();
            for (FirValueParameterSymbol firValueParameterSymbol : listParameters) {
                if (mapping == null) {
                    firInferenceNodeInferenceNodeOf = null;
                    break;
                }
                Iterator it = mapping.entrySet().iterator();
                do {
                    if (!it.hasNext()) {
                        firInferenceNodeInferenceNodeOf = null;
                        break;
                    } else {
                        Map.Entry entry = (Map.Entry) it.next();
                        firInferenceNodeInferenceNodeOf = Intrinsics.areEqual(entry.getValue(), firValueParameterSymbol.getFir()) ? ComposableTargetCheckerKt.inferenceNodeOf((FirElement) entry.getKey(), checkerContext) : null;
                    }
                } while (firInferenceNodeInferenceNodeOf == null);
                if (firInferenceNodeInferenceNodeOf != null) {
                    arrayList.add(firInferenceNodeInferenceNodeOf);
                }
            }
            firApplierInferencer.visitCall(firInferenceNodeInferenceNodeOf2, firInferenceNodeCallableInferenceNodeOf, arrayList);
        }
    }
}
