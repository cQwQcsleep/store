package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
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
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.name.JvmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirOverloadsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOverloadsChecker extends FirDeclarationChecker<FirFunction> {
    public static final FirOverloadsChecker INSTANCE = new FirOverloadsChecker();

    private FirOverloadsChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firFunction, JvmStandardClassIds.INSTANCE.getJVM_OVERLOADS_CLASS_ID(), checkerContext.getSession());
        if (annotationByClassId == null) {
            return;
        }
        FirFunctionSymbol<?> symbol = firFunction.getSymbol();
        if (symbol.getRawStatus().isActual()) {
            symbol = null;
        }
        if (symbol == null && (symbol = ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull((FirFunctionSymbol<?>) firFunction.getSymbol())) == null) {
            return;
        }
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firFunction);
        if ((containingClassSymbol != null ? FirHelpersKt.getClassKind(containingClassSymbol) : null) == ClassKind.INTERFACE) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirJvmErrors.INSTANCE.getOVERLOADS_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (firFunction.getStatus().getModality() == Modality.ABSTRACT) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirJvmErrors.INSTANCE.getOVERLOADS_ABSTRACT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (!(firFunction instanceof FirNamedFunction) || !Intrinsics.areEqual(((FirNamedFunction) firFunction).getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
            List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
            if (!(containingDeclarations instanceof Collection) || !containingDeclarations.isEmpty()) {
                Iterator<T> it = containingDeclarations.iterator();
                while (it.hasNext()) {
                    if (FirSymbolStatusUtilsKt.isLocalClassLike((FirBasedSymbol) it.next())) {
                    }
                }
            }
            if (firFunction instanceof FirConstructor) {
                if ((containingClassSymbol != null ? FirHelpersKt.getClassKind(containingClassSymbol) : null) == ClassKind.ANNOTATION_CLASS) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirJvmErrors.INSTANCE.getOVERLOADS_ANNOTATION_CLASS_CONSTRUCTOR_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
            }
            if (!firFunction.getStatus().getVisibility().getIsPublicAPI() && !Intrinsics.areEqual(firFunction.getStatus().getVisibility(), Visibilities.Internal.INSTANCE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirJvmErrors.INSTANCE.getOVERLOADS_PRIVATE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            List<FirValueParameterSymbol> valueParameterSymbols = symbol.getValueParameterSymbols();
            if (!(valueParameterSymbols instanceof Collection) || !valueParameterSymbols.isEmpty()) {
                Iterator<T> it2 = valueParameterSymbols.iterator();
                while (it2.hasNext()) {
                    if (((FirValueParameterSymbol) it2.next()).getHasDefaultValue()) {
                        return;
                    }
                }
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirJvmErrors.INSTANCE.getOVERLOADS_WITHOUT_DEFAULT_ARGUMENTS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirJvmErrors.INSTANCE.getOVERLOADS_LOCAL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
