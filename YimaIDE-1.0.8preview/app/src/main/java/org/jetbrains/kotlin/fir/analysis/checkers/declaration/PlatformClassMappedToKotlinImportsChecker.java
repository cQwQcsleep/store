package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapperKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/PlatformClassMappedToKotlinImportsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PlatformClassMappedToKotlinImportsChecker extends FirDeclarationChecker<FirFile> {
    public static final PlatformClassMappedToKotlinImportsChecker INSTANCE = new PlatformClassMappedToKotlinImportsChecker();

    private PlatformClassMappedToKotlinImportsChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFile firFile) {
        FirImport firImport;
        FqName importedFqName;
        ClassId resolvedParentClassId;
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFile.getClass();
        Iterator<T> it = firFile.getImports().iterator();
        while (it.hasNext() && (importedFqName = (firImport = (FirImport) it.next()).getImportedFqName()) != null && !importedFqName.isRoot()) {
            String strAsString = importedFqName.shortName().asString();
            strAsString.getClass();
            if (strAsString.length() == 0) {
                return;
            }
            FirResolvedImport firResolvedImport = firImport instanceof FirResolvedImport ? (FirResolvedImport) firImport : null;
            if (firResolvedImport == null || (resolvedParentClassId = firResolvedImport.getResolvedParentClassId()) == null) {
                resolvedParentClassId = ClassId.Companion.topLevel(importedFqName);
            }
            if (!Intrinsics.areEqual(resolvedParentClassId.asSingleFqName(), importedFqName)) {
                return;
            }
            ClassId correspondingKotlinClass = FirPlatformClassMapperKt.getPlatformClassMapper(checkerContext.getSession()).getCorrespondingKotlinClass(resolvedParentClassId);
            if (correspondingKotlinClass != null) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firImport.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getPLATFORM_CLASS_MAPPED_TO_KOTLIN(), (Object) correspondingKotlinClass, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }
}
