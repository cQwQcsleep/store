package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyBackingField;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExplicitBackingFieldForbiddenChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBackingFieldChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExplicitBackingFieldForbiddenChecker extends FirDeclarationChecker<FirBackingField> {
    public static final FirExplicitBackingFieldForbiddenChecker INSTANCE = new FirExplicitBackingFieldForbiddenChecker();

    private FirExplicitBackingFieldForbiddenChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBackingField firBackingField) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firBackingField.getClass();
        if (firBackingField instanceof FirDefaultPropertyBackingField) {
            return;
        }
        FirClassSymbol<?> firClassSymbolFindClosestClassOrObject = FirHelpersKt.findClosestClassOrObject(checkerContext);
        if ((firClassSymbolFindClosestClassOrObject != null ? firClassSymbolFindClosestClassOrObject.getClassKind() : null) == ClassKind.INTERFACE) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firBackingField.getSource(), FirErrors.INSTANCE.getEXPLICIT_BACKING_FIELD_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else if (firBackingField.getPropertySymbol().getResolvedStatus().getModality() == Modality.ABSTRACT) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firBackingField.getSource(), FirErrors.INSTANCE.getEXPLICIT_BACKING_FIELD_IN_ABSTRACT_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else if (!DeclarationUtilsKt.isEffectivelyFinal(firBackingField.getPropertySymbol())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firBackingField.getSource(), FirErrors.INSTANCE.getNON_FINAL_PROPERTY_WITH_EXPLICIT_BACKING_FIELD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (FirSymbolStatusUtilsKt.isExtension(firBackingField.getPropertySymbol())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firBackingField.getSource(), FirErrors.INSTANCE.getEXPLICIT_BACKING_FIELD_IN_EXTENSION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (firBackingField.getPropertySymbol().getRawStatus().isExpect()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firBackingField.getPropertySymbol().getSource(), FirErrors.INSTANCE.getEXPECT_PROPERTY_WITH_EXPLICIT_BACKING_FIELD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (Visibilities.INSTANCE.isPrivate(firBackingField.getPropertySymbol().getResolvedStatus().getVisibility())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firBackingField.getPropertySymbol().getSource(), FirErrors.INSTANCE.getEXPLICIT_FIELD_VISIBILITY_MUST_BE_LESS_PERMISSIVE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
