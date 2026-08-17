package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\fJI\u0010\u0002\u001a\u0014\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004\u0018\u00010\u00032\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00052\u0010\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\nH&R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/PlatformConflictDeclarationsDiagnosticDispatcher;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "getDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "conflictingDeclaration", "symbols", "Lorg/jetbrains/kotlin/utils/SmartSet;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/utils/SmartSet;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "DEFAULT", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface PlatformConflictDeclarationsDiagnosticDispatcher extends FirSessionComponent {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JG\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00060\u00052\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00072\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\fH\u0016R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/PlatformConflictDeclarationsDiagnosticDispatcher$DEFAULT;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/PlatformConflictDeclarationsDiagnosticDispatcher;", "<init>", "()V", "getDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "conflictingDeclaration", "symbols", "Lorg/jetbrains/kotlin/utils/SmartSet;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/utils/SmartSet;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DEFAULT implements PlatformConflictDeclarationsDiagnosticDispatcher {
        public static final DEFAULT INSTANCE = new DEFAULT();

        private DEFAULT() {
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.PlatformConflictDeclarationsDiagnosticDispatcher
        public KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> getDiagnostic(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, SmartSet<FirBasedSymbol<?>> smartSet) {
            checkerContext.getClass();
            firBasedSymbol.getClass();
            smartSet.getClass();
            if ((firBasedSymbol instanceof FirNamedFunctionSymbol) || (firBasedSymbol instanceof FirConstructorSymbol)) {
                return FirErrors.INSTANCE.getCONFLICTING_OVERLOADS();
            }
            if ((firBasedSymbol instanceof FirClassLikeSymbol) && ContainingClassUtilsKt.getContainingClassSymbol(firBasedSymbol) == null && (smartSet == null || !smartSet.isEmpty())) {
                Iterator it = smartSet.iterator();
                while (it.hasNext()) {
                    if (((FirBasedSymbol) it.next()) instanceof FirClassLikeSymbol) {
                        return FirErrors.INSTANCE.getCLASSIFIER_REDECLARATION();
                    }
                }
            }
            return FirErrors.INSTANCE.getREDECLARATION();
        }
    }

    KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> getDiagnostic(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, SmartSet<FirBasedSymbol<?>> smartSet);
}
