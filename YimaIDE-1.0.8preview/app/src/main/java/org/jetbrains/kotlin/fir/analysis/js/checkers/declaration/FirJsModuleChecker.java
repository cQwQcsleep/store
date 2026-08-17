package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsModuleCheckUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.JsStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0014\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u000f\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsModuleChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkSuperClass", "isEitherModuleOrNonModule", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsModuleChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJsModuleChecker INSTANCE = new FirJsModuleChecker();

    private FirJsModuleChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkSuperClass(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        ConeClassLikeType coneClassLikeTypeSuperClassNotAny;
        FirClassLikeSymbol<?> symbol;
        KtSourceElement source;
        Object obj = null;
        FirClass firClass = firDeclaration instanceof FirClass ? (FirClass) firDeclaration : null;
        if (firClass == null || (coneClassLikeTypeSuperClassNotAny = FirJsHelpersKt.superClassNotAny(firClass, checkerContext.getSession())) == null || (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeTypeSuperClassNotAny)) == null) {
            return;
        }
        for (Object obj2 : firClass.getSuperTypeRefs()) {
            ConeKotlinType coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull((FirTypeRef) obj2);
            if (Intrinsics.areEqual(coneTypeOrNull != null ? ToSymbolUtilsKt.toSymbol(checkerContext, coneTypeOrNull) : null, symbol)) {
                obj = obj2;
                break;
            }
        }
        FirTypeRef firTypeRef = (FirTypeRef) obj;
        if (firTypeRef == null || (source = firTypeRef.getSource()) == null) {
            source = ((FirClass) firDeclaration).getSource();
        }
        FirJsModuleCheckUtilsKt.checkJsModuleUsage(checkerContext, diagnosticReporter, symbol, source);
    }

    private final boolean isEitherModuleOrNonModule(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        return FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, JsStandardClassIds.Annotations.JsModule, firSession) || FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, JsStandardClassIds.Annotations.JsNonModule, firSession);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirFileSymbol containingFileSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        checkSuperClass(checkerContext, diagnosticReporter, firDeclaration);
        if ((firDeclaration instanceof FirFile) || !isEitherModuleOrNonModule(firDeclaration, checkerContext.getSession())) {
            return;
        }
        if (firDeclaration instanceof FirProperty) {
            FirProperty firProperty = (FirProperty) firDeclaration;
            if (firProperty.getIsVar()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty.getSource(), FirWebCommonErrors.INSTANCE.getJS_MODULE_PROHIBITED_ON_VAR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        FirBasedSymbol<?> firBasedSymbolClosestNonLocalWith = FirHelpersKt.closestNonLocalWith(checkerContext, firDeclaration);
        if (firBasedSymbolClosestNonLocalWith == null) {
            return;
        }
        if (!FirJsHelpersKt.isNativeObject(checkerContext, firBasedSymbolClosestNonLocalWith)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), FirJsErrors.INSTANCE.getJS_MODULE_PROHIBITED_ON_NON_NATIVE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (FirHelpersKt.isTopLevel(checkerContext) && (containingFileSymbol = checkerContext.getContainingFileSymbol()) != null && isEitherModuleOrNonModule(containingFileSymbol, checkerContext.getSession())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), FirWebCommonErrors.INSTANCE.getNESTED_JS_MODULE_PROHIBITED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final boolean isEitherModuleOrNonModule(FirDeclaration firDeclaration, FirSession firSession) {
        return isEitherModuleOrNonModule(firDeclaration.getSymbol(), firSession);
    }
}
