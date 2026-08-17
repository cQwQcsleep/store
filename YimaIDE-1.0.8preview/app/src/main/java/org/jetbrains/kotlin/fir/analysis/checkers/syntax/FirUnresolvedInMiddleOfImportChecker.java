package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedParentInImport;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.ImportUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PackageResolutionResult;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u000fJ-\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013J\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0016H\u0002¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirUnresolvedInMiddleOfImportChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirDeclarationSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/psi/KtFile;", "<init>", "()V", "checkPsiOrLightTree", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lorg/jetbrains/kotlin/KtSourceElement;)V", "processPossiblyUnresolvedImport", "import", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;)V", "getOutermostClassName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "isClassIdPointingToEnumEntry", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "classId", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnresolvedInMiddleOfImportChecker extends FirDeclarationSyntaxChecker<FirFile, KtFile> {
    public static final FirUnresolvedInMiddleOfImportChecker INSTANCE = new FirUnresolvedInMiddleOfImportChecker();

    private FirUnresolvedInMiddleOfImportChecker() {
    }

    private final String getOutermostClassName(ClassId classId) {
        String strAsString = ((Name) CollectionsKt.first(classId.getRelativeClassName().pathSegments())).asString();
        strAsString.getClass();
        return strAsString;
    }

    private final boolean isClassIdPointingToEnumEntry(FirSession session, ClassId classId) {
        ClassId outerClassId = classId.getOuterClassId();
        if (outerClassId == null) {
            return false;
        }
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(session).getClassLikeSymbolByClassId(outerClassId);
        FirRegularClassSymbol firRegularClassSymbol = classLikeSymbolByClassId instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbolByClassId : null;
        if (firRegularClassSymbol != null) {
            FirRegularClassSymbol firRegularClassSymbol2 = firRegularClassSymbol.getClassKind() == ClassKind.ENUM_CLASS ? firRegularClassSymbol : null;
            if (firRegularClassSymbol2 != null) {
                List<FirEnumEntrySymbol> listCollectEnumEntries = DeclarationUtilsKt.collectEnumEntries(firRegularClassSymbol2, session);
                if ((listCollectEnumEntries instanceof Collection) && listCollectEnumEntries.isEmpty()) {
                    return false;
                }
                Iterator<T> it = listCollectEnumEntries.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(((FirEnumEntrySymbol) it.next()).getCallableId().getCallableName(), classId.getShortClassName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final void processPossiblyUnresolvedImport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedImport firResolvedImport) {
        ClassId resolvedParentClassId;
        KtSourceElement source;
        KtSourceElementKind kind;
        KtSourceElement source2 = firResolvedImport.getSource();
        if ((source2 == null || (kind = source2.getKind()) == null || !kind.getShouldSkipErrorTypeReporting()) && (resolvedParentClassId = firResolvedImport.getResolvedParentClassId()) != null) {
            PackageResolutionResult packageResolutionResultResolveToPackageOrClass = ImportUtilsKt.resolveToPackageOrClass(FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()), resolvedParentClassId);
            if (packageResolutionResultResolveToPackageOrClass instanceof PackageResolutionResult.Error) {
                ConeDiagnostic diagnostic = ((PackageResolutionResult.Error) packageResolutionResultResolveToPackageOrClass).getDiagnostic();
                if (!(diagnostic instanceof ConeUnresolvedParentInImport) || (source = firResolvedImport.getSource()) == null) {
                    return;
                }
                ClassId parentClassId = ((ConeUnresolvedParentInImport) diagnostic).getParentClassId();
                if (firResolvedImport.getIsAllUnder() && isClassIdPointingToEnumEntry(checkerContext.getSession(), parentClassId)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getCANNOT_ALL_UNDER_IMPORT_FROM_SINGLETON(), (Object) parentClassId.getShortClassName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    return;
                }
                int i = 1 ^ (firResolvedImport.getIsAllUnder() ? 1 : 0);
                ClassId outerClassId = parentClassId.getOuterClassId();
                while (outerClassId != null && FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()).getClassLikeSymbolByClassId(outerClassId) == null) {
                    outerClassId = outerClassId.getOuterClassId();
                    i++;
                }
                KtSourceElement sourceForImportSegment = FirSourceUtilsKt.getSourceForImportSegment(firResolvedImport, i);
                if (sourceForImportSegment == null) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) sourceForImportSegment, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNRESOLVED_IMPORT(), (Object) getOutermostClassName(parentClassId), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsiOrLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFile firFile, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFile.getClass();
        ktSourceElement.getClass();
        for (FirImport firImport : firFile.getImports()) {
            if (firImport instanceof FirResolvedImport) {
                processPossiblyUnresolvedImport(checkerContext, diagnosticReporter, (FirResolvedImport) firImport);
            }
        }
    }
}
