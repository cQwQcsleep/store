package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ;\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0014J3\u0010\u0015\u001a\u00020\u00072\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirEnumEntryInitializationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "checkClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "enumEntrySymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Ljava/util/Set;)V", "checkEnumEntries", "enumEntries", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/List;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEnumEntryInitializationChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirEnumEntryInitializationChecker INSTANCE = new FirEnumEntryInitializationChecker();

    private FirEnumEntryInitializationChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkClass(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, Set<FirEnumEntrySymbol> set) {
        ControlFlowGraph controlFlowGraph;
        FirControlFlowGraphReference controlFlowGraphReference = firClass.getControlFlowGraphReference();
        if (controlFlowGraphReference == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) == null) {
            return;
        }
        EnumEntryInitializationCheckProcessor.INSTANCE.check(checkerContext, diagnosticReporter, new EnumEntryInitializationInfoData(set, firClass.getSymbol(), controlFlowGraph), true);
    }

    private final void checkEnumEntries(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List<? extends FirEnumEntry> list) {
        FirAnonymousObject anonymousObject;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((FirEnumEntry) it.next()).getSymbol());
        }
        for (FirEnumEntry firEnumEntry : list) {
            FirExpression initializer = firEnumEntry.getInitializer();
            FirAnonymousObjectExpression firAnonymousObjectExpression = initializer instanceof FirAnonymousObjectExpression ? (FirAnonymousObjectExpression) initializer : null;
            if (firAnonymousObjectExpression != null && (anonymousObject = firAnonymousObjectExpression.getAnonymousObject()) != null) {
                checkClass(checkerContext, diagnosticReporter, anonymousObject, linkedHashSet);
                linkedHashSet.remove(firEnumEntry.getSymbol());
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (firRegularClass.getClassKind() != ClassKind.ENUM_CLASS || LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ProperUninitializedEnumEntryAccessAnalysis)) {
            return;
        }
        List<FirEnumEntry> listCollectEnumEntries = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.collectEnumEntries(firRegularClass, checkerContext.getSession());
        if (listCollectEnumEntries.isEmpty()) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = listCollectEnumEntries.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((FirEnumEntry) it.next()).getSymbol());
        }
        checkClass(checkerContext, diagnosticReporter, firRegularClass, linkedHashSet);
        checkEnumEntries(checkerContext, diagnosticReporter, listCollectEnumEntries);
    }
}
