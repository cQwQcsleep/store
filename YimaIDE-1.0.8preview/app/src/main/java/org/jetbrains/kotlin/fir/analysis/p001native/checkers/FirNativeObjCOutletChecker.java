package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.FirNativeObjCOutletChecker;
import org.jetbrains.kotlin.fir.backend.p002native.interop.FirObjCInteropKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.name.NativeStandardInteropNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCOutletChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeObjCOutletChecker extends FirDeclarationChecker<FirClass> {
    public static final FirNativeObjCOutletChecker INSTANCE = new FirNativeObjCOutletChecker();

    private FirNativeObjCOutletChecker() {
        super(MppCheckerKind.Platform);
    }

    public static Unit b(FirSession firSession, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if ((firCallableSymbol instanceof FirPropertySymbol) && FirAnnotationUtilsKt.hasAnnotation(firCallableSymbol, NativeStandardInteropNames.INSTANCE.getObjCOutletClassId(), firSession)) {
            check$checkCanGenerateOutletSetterImp(checkerContext, diagnosticReporter, firSession, (FirPropertySymbol) firCallableSymbol);
        }
        return Unit.INSTANCE;
    }

    private static final void check$checkCanGenerateFunctionImp(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirPropertyAccessorSymbol firPropertyAccessorSymbol) {
        if (firPropertyAccessorSymbol.getValueParameterSymbols().size() > 2) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessorSymbol.getSource(), FirNativeErrors.INSTANCE.getTWO_OR_LESS_PARAMETERS_ARE_SUPPORTED_HERE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private static final void check$checkCanGenerateOutletSetterImp(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirSession firSession, FirPropertySymbol firPropertySymbol) {
        if (!firPropertySymbol.isVar()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertySymbol.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getPROPERTY_MUST_BE_VAR(), (Object) NativeStandardInteropNames.INSTANCE.getObjCOutletClassId().asSingleFqName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        FirReceiverParameterSymbol receiverParameterSymbol = firPropertySymbol.getReceiverParameterSymbol();
        if (receiverParameterSymbol != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) receiverParameterSymbol.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getMUST_NOT_HAVE_EXTENSION_RECEIVER(), (Object) (PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT + NativeStandardInteropNames.INSTANCE.getObjCOutletClassId().asFqNameString()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        FirResolvedTypeRef resolvedReturnTypeRef = firPropertySymbol.getResolvedReturnTypeRef();
        if (!FirObjCInteropKt.isObjCObjectType(resolvedReturnTypeRef, firSession)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertySymbol.getResolvedReturnTypeRef().getSource(), (KtDiagnosticFactory2) FirNativeErrors.INSTANCE.getMUST_BE_OBJC_OBJECT_TYPE(), (Object) (PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT + NativeStandardInteropNames.INSTANCE.getObjCOutletClassId().asSingleFqName() + " type"), (Object) resolvedReturnTypeRef.getConeType(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
        FirPropertyAccessorSymbol setterSymbol = firPropertySymbol.getSetterSymbol();
        setterSymbol.getClass();
        check$checkCanGenerateFunctionImp(checkerContext, diagnosticReporter, setterSymbol);
    }

    private static final void check$checkKotlinObjCClass(final CheckerContext checkerContext, final FirSession firSession, final DiagnosticReporter diagnosticReporter, FirClass firClass) {
        DeclarationUtilsKt.processAllDeclaredCallables$default(firClass.getSymbol(), checkerContext.getSession(), null, new Function1() { // from class: ab5
            public final Object invoke(Object obj) {
                return FirNativeObjCOutletChecker.b(firSession, checkerContext, diagnosticReporter, (FirCallableSymbol) obj);
            }
        }, 2, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        FirSession session = checkerContext.getSession();
        if (firClass.getStatus().isExpect() || !FirObjCInteropKt.isKotlinObjCClass(firClass.getSymbol(), checkerContext.getSession())) {
            return;
        }
        check$checkKotlinObjCClass(checkerContext, session, diagnosticReporter, firClass);
    }
}
