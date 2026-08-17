package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirPackageDirective;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPackageConflictsWithClassifierChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNamesUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0012\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014*\u00020\u0015H\u0002¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPackageConflictsWithClassifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "areDependentMultiplatformModules", Argument.Delimiters.none, "module1", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "module2", "parentsIncludingSelf", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPackageConflictsWithClassifierChecker extends FirDeclarationChecker<FirFile> {
    public static final FirPackageConflictsWithClassifierChecker INSTANCE = new FirPackageConflictsWithClassifierChecker();

    private FirPackageConflictsWithClassifierChecker() {
        super(MppCheckerKind.Platform);
    }

    private final boolean areDependentMultiplatformModules(FirModuleData module1, FirModuleData module2) {
        return module2.getAllDependsOnDependencies().contains(module1) || module1.getAllDependsOnDependencies().contains(module2);
    }

    public static FqName b(FqName fqName) {
        fqName.getClass();
        return FqNamesUtilKt.parentOrNull(fqName);
    }

    private final Sequence<FqName> parentsIncludingSelf(FqName fqName) {
        return SequencesKt.generateSequence(fqName, new Function1() { // from class: vb5
            public final Object invoke(Object obj) {
                return FirPackageConflictsWithClassifierChecker.b((FqName) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFile firFile) {
        ClassId classId;
        FirClassLikeSymbol<?> classLikeSymbolByClassId;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFile.getClass();
        FirPackageDirective packageDirective = firFile.getPackageDirective();
        for (FqName fqName : parentsIncludingSelf(packageDirective.getPackageFqName())) {
            if (!fqName.isRoot() && (classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()).getClassLikeSymbolByClassId((classId = new ClassId(fqName.parent(), fqName.shortName())))) != null && (classLikeSymbolByClassId.getOrigin() instanceof FirDeclarationOrigin.Source) && (Intrinsics.areEqual(classLikeSymbolByClassId.getModuleData(), firFile.getModuleData()) || areDependentMultiplatformModules(classLikeSymbolByClassId.getModuleData(), firFile.getModuleData()))) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) packageDirective.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getPACKAGE_CONFLICTS_WITH_CLASSIFIER(), (Object) classId, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }
}
