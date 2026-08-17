package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplicitNothingReturnTypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirImplicitNothingReturnTypeChecker extends FirDeclarationChecker<FirCallableDeclaration> {
    public static final FirImplicitNothingReturnTypeChecker INSTANCE = new FirImplicitNothingReturnTypeChecker();

    private FirImplicitNothingReturnTypeChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
        KtDiagnosticFactory0 implicit_nothing_property_type;
        KtDiagnosticFactory0 abbreviated_nothing_property_type;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableDeclaration.getClass();
        if ((firCallableDeclaration instanceof FirNamedFunction) || (firCallableDeclaration instanceof FirProperty)) {
            if (((firCallableDeclaration instanceof FirProperty) && (((FirProperty) firCallableDeclaration).getSymbol() instanceof FirLocalPropertySymbol)) || firCallableDeclaration.getStatus().isOverride() || Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ResultProperty.INSTANCE)) {
                return;
            }
            if (!DeclarationUtilsKt.getHasExplicitReturnType(firCallableDeclaration.getSymbol())) {
                if (ConeBuiltinTypeUtilsKt.isNothing(FirTypeUtilsKt.getConeType(firCallableDeclaration.getReturnTypeRef()))) {
                    if (firCallableDeclaration instanceof FirNamedFunction) {
                        implicit_nothing_property_type = FirErrors.INSTANCE.getIMPLICIT_NOTHING_RETURN_TYPE();
                    } else {
                        if (!(firCallableDeclaration instanceof FirProperty)) {
                            k2d.a("Should not be here");
                            return;
                        }
                        implicit_nothing_property_type = FirErrors.INSTANCE.getIMPLICIT_NOTHING_PROPERTY_TYPE();
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirTypeParametersOwner) firCallableDeclaration).getSource(), implicit_nothing_property_type, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
                return;
            }
            boolean zIsNothing = ConeBuiltinTypeUtilsKt.isNothing(AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(FirTypeUtilsKt.getConeType(firCallableDeclaration.getReturnTypeRef())));
            boolean zIsNothing2 = ConeBuiltinTypeUtilsKt.isNothing(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firCallableDeclaration.getReturnTypeRef())));
            if (zIsNothing || !zIsNothing2) {
                return;
            }
            if (firCallableDeclaration instanceof FirNamedFunction) {
                abbreviated_nothing_property_type = FirErrors.INSTANCE.getABBREVIATED_NOTHING_RETURN_TYPE();
            } else {
                if (!(firCallableDeclaration instanceof FirProperty)) {
                    k2d.a("Should not be here");
                    return;
                }
                abbreviated_nothing_property_type = FirErrors.INSTANCE.getABBREVIATED_NOTHING_PROPERTY_TYPE();
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirTypeParametersOwner) firCallableDeclaration).getSource(), abbreviated_nothing_property_type, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
