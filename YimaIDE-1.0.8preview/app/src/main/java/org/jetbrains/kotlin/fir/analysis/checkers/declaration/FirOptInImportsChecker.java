package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirOptInUsageBaseChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ6\u0010\u000e\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0082\u0010R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOptInImportsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "checkContainingClasses", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInImportsChecker extends FirDeclarationChecker<FirFile> {
    public static final FirOptInImportsChecker INSTANCE = new FirOptInImportsChecker();

    private FirOptInImportsChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkContainingClasses(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClassLikeSymbol<?> firClassLikeSymbol, KtSourceElement ktSourceElement) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        KtSourceElement ktSourceElement2;
        while (true) {
            if (FirOptInUsageBaseChecker.INSTANCE.isExperimentalMarker(firClassLikeSymbol, checkerContext.getSession())) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                ktSourceElement2 = ktSourceElement;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) ktSourceElement2, FirErrors.INSTANCE.getOPT_IN_MARKER_CAN_ONLY_BE_USED_AS_ANNOTATION_OR_ARGUMENT_IN_OPT_IN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                ktSourceElement2 = ktSourceElement;
            }
            ConeClassLikeLookupTag containingClassLookupTag = ClassMembersKt.getContainingClassLookupTag(firClassLikeSymbol);
            if (containingClassLookupTag == null || (firClassLikeSymbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext2, containingClassLookupTag)) == null) {
                return;
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
            ktSourceElement = ktSourceElement2;
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFile firFile) {
        FirResolvedImport firResolvedImport;
        KtSourceElement source;
        ClassId resolvedParentClassId;
        FirClassLikeSymbol<?> classLikeSymbolByClassId;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFile.getClass();
        for (FirImport firImport : firFile.getImports()) {
            if ((firImport instanceof FirResolvedImport) && (source = (firResolvedImport = (FirResolvedImport) firImport).getSource()) != null && (resolvedParentClassId = firResolvedImport.getResolvedParentClassId()) != null && (classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()).getClassLikeSymbolByClassId(resolvedParentClassId)) != null) {
                if (classLikeSymbolByClassId instanceof FirRegularClassSymbol) {
                    INSTANCE.checkContainingClasses(checkerContext, diagnosticReporter, classLikeSymbolByClassId, source);
                } else if ((classLikeSymbolByClassId instanceof FirTypeAliasSymbol) && (firRegularClassSymbolFullyExpandedClass = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.fullyExpandedClass(checkerContext, classLikeSymbolByClassId)) != null) {
                    INSTANCE.checkContainingClasses(checkerContext, diagnosticReporter, firRegularClassSymbolFullyExpandedClass, source);
                }
            }
        }
    }
}
