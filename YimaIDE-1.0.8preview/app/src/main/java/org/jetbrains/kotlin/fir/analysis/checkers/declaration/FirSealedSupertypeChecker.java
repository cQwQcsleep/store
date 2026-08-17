package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSealedSupertypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "checkGlobalDeclaration", "checkLocalDeclaration", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSealedSupertypeChecker extends FirDeclarationChecker<FirClass> {
    public static final FirSealedSupertypeChecker INSTANCE = new FirSealedSupertypeChecker();

    private FirSealedSupertypeChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkGlobalDeclaration(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        FqName packageFqName = FirDeclarationUtilKt.getClassId(firClass).getPackageFqName();
        for (FirTypeRef firTypeRef : firClass.getSuperTypeRefs()) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firTypeRef)));
            if (regularClassSymbol != null && !((FirClassLikeDeclaration) regularClassSymbol.getFir()).getIsLocal() && regularClassSymbol.getResolvedStatus().getModality() == Modality.SEALED) {
                if (regularClassSymbol.getOrigin() instanceof FirDeclarationOrigin.Java) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getCLASS_INHERITS_JAVA_SEALED_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else {
                    if (!Intrinsics.areEqual(regularClassSymbol.getClassId().getPackageFqName(), packageFqName)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getSEALED_INHERITOR_IN_DIFFERENT_PACKAGE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                    if (!Intrinsics.areEqual(regularClassSymbol.getModuleData(), firClass.getModuleData()) && !regularClassSymbol.getRawStatus().isExpect()) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getSEALED_INHERITOR_IN_DIFFERENT_MODULE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkLocalDeclaration(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        for (FirTypeRef firTypeRef : firClass.getSuperTypeRefs()) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firTypeRef)));
            if (regularClassSymbol != null && !((FirClassLikeDeclaration) regularClassSymbol.getFir()).getIsLocal() && regularClassSymbol.getResolvedStatus().getModality() == Modality.SEALED) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getSEALED_SUPERTYPE_IN_LOCAL_CLASS(), (Object) (firClass instanceof FirAnonymousObject ? "Anonymous object" : "Local class"), (Object) regularClassSymbol.getClassKind(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                return;
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        if (firClass.getIsLocal()) {
            checkLocalDeclaration(checkerContext, diagnosticReporter, firClass);
        } else {
            checkGlobalDeclaration(checkerContext, diagnosticReporter, firClass);
        }
    }
}
