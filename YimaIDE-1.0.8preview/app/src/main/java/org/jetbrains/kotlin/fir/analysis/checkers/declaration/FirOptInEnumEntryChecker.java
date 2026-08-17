package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirOptInUsageBaseChecker;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOptInEnumEntryChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirEnumEntryChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInEnumEntryChecker extends FirDeclarationChecker<FirEnumEntry> {
    public static final FirOptInEnumEntryChecker INSTANCE = new FirOptInEnumEntryChecker();

    private FirOptInEnumEntryChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirEnumEntry firEnumEntry) {
        FirClassLikeSymbol<?> containingClassSymbol;
        FirConstructorSymbol primaryConstructorSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firEnumEntry.getClass();
        if (firEnumEntry.getInitializer() != null || (containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firEnumEntry.getSymbol())) == null || (primaryConstructorSymbol = ScopeUtilsKt.getPrimaryConstructorSymbol(containingClassSymbol, checkerContext.getSession(), checkerContext.getScopeSession())) == null) {
            return;
        }
        Set<FirOptInUsageBaseChecker.Experimentality> setLoadExperimentalitiesFromConstructor = FirOptInUsageBaseChecker.INSTANCE.loadExperimentalitiesFromConstructor(checkerContext, primaryConstructorSymbol);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setLoadExperimentalitiesFromConstructor, 10));
        for (FirOptInUsageBaseChecker.Experimentality experimentalityCopy$default : setLoadExperimentalitiesFromConstructor) {
            if (!LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.CheckOptInOnPureEnumEntries)) {
                experimentalityCopy$default = FirOptInUsageBaseChecker.Experimentality.copy$default(experimentalityCopy$default, null, FirOptInUsageBaseChecker.Experimentality.Severity.WARNING, null, null, false, 29, null);
            }
            arrayList.add(experimentalityCopy$default);
        }
        FirOptInUsageBaseChecker.reportNotAcceptedExperimentalities$default(FirOptInUsageBaseChecker.INSTANCE, checkerContext, diagnosticReporter, arrayList, firEnumEntry, null, false, 48, null);
    }
}
