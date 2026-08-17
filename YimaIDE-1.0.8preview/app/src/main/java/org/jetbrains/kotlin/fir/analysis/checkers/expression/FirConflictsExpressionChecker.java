package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirConflictsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirDeclarationCollector;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirConflictsExpressionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBlockChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "checkForLocalConflictingFunctions", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConflictsExpressionChecker extends FirExpressionChecker<FirBlock> {
    public static final FirConflictsExpressionChecker INSTANCE = new FirConflictsExpressionChecker();

    private FirConflictsExpressionChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkForLocalConflictingFunctions(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBlock firBlock) {
        FirDeclarationCollector<FirFunctionSymbol<?>> firDeclarationCollectorCollectConflictingLocalFunctionsFrom = FirConflictsHelpersKt.collectConflictingLocalFunctionsFrom(checkerContext, firBlock);
        if (firDeclarationCollectorCollectConflictingLocalFunctionsFrom == null) {
            return;
        }
        for (Map.Entry entry : firDeclarationCollectorCollectConflictingLocalFunctionsFrom.getDeclarationConflictingSymbols().entrySet()) {
            FirFunctionSymbol firFunctionSymbol = (FirFunctionSymbol) entry.getKey();
            SmartSet smartSet = (SmartSet) entry.getValue();
            if (!smartSet.isEmpty()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionSymbol.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCONFLICTING_OVERLOADS(), (Object) smartSet, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
        for (Map.Entry entry2 : firDeclarationCollectorCollectConflictingLocalFunctionsFrom.getDeclarationShadowedViaContextParameters().entrySet()) {
            FirFunctionSymbol firFunctionSymbol2 = (FirFunctionSymbol) entry2.getKey();
            SmartSet smartSet2 = (SmartSet) entry2.getValue();
            if (!smartSet2.isEmpty()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionSymbol2.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCONTEXTUAL_OVERLOAD_SHADOWED(), (Object) smartSet2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBlock firBlock) {
        List arrayList;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firBlock.getClass();
        List<FirStatement> statements = firBlock.getStatements();
        if ((statements instanceof Collection) && statements.isEmpty()) {
            arrayList = firBlock.getStatements();
        } else {
            Iterator<T> it = statements.iterator();
            while (it.hasNext()) {
                if (FirDeclarationUtilKt.isDestructuredParameter((FirStatement) it.next())) {
                    List<FirStatement> statements2 = firBlock.getStatements();
                    arrayList = new ArrayList();
                    for (Object obj : statements2) {
                        if (!FirDeclarationUtilKt.isDestructuredParameter((FirStatement) obj)) {
                            arrayList.add(obj);
                        }
                    }
                }
            }
            arrayList = firBlock.getStatements();
        }
        FirConflictsHelpersKt.checkForLocalRedeclarations(checkerContext, diagnosticReporter, arrayList);
        checkForLocalConflictingFunctions(checkerContext, diagnosticReporter, firBlock);
    }
}
