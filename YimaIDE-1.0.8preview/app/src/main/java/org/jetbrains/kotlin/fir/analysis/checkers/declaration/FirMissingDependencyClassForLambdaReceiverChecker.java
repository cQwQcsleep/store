package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.LinkedHashSet;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirMissingDependencyClassProxy;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u00032\u00020\u0004B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J-\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u00020\tR\u00020\u000bj\u0006\u0010\n\u001a\u00020\tj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMissingDependencyClassForLambdaReceiverChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousFunctionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMissingDependencyClassForLambdaReceiverChecker extends FirDeclarationChecker<FirAnonymousFunction> implements FirMissingDependencyClassProxy {
    public static final FirMissingDependencyClassForLambdaReceiverChecker INSTANCE = new FirMissingDependencyClassForLambdaReceiverChecker();

    private FirMissingDependencyClassForLambdaReceiverChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnonymousFunction firAnonymousFunction) {
        ConeKotlinType receiverType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firAnonymousFunction.getClass();
        if (firAnonymousFunction.isLambda() && (receiverType = InferenceUtilsKt.getReceiverType(firAnonymousFunction)) != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            considerType(checkerContext, receiverType, linkedHashSet);
            reportMissingTypes(checkerContext, diagnosticReporter, firAnonymousFunction.getSource(), linkedHashSet, FirMissingDependencyClassProxy.MissingTypeOrigin.LambdaReceiver.INSTANCE);
        }
    }
}
