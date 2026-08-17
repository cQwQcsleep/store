package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.JsStandardClassIds;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsActualExternalInterfaceSuggestJsNoRuntimeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "hasJsNoRuntime", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;)Z", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsActualExternalInterfaceSuggestJsNoRuntimeChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJsActualExternalInterfaceSuggestJsNoRuntimeChecker INSTANCE = new FirJsActualExternalInterfaceSuggestJsNoRuntimeChecker();

    private FirJsActualExternalInterfaceSuggestJsNoRuntimeChecker() {
        super(MppCheckerKind.Platform);
    }

    private final boolean hasJsNoRuntime(CheckerContext checkerContext, FirAnnotationContainer firAnnotationContainer) {
        return FirAnnotationUtilsKt.hasAnnotation(firAnnotationContainer, JsStandardClassIds.Annotations.JsNoRuntime, checkerContext.getSession());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirClassLikeSymbol<FirClassLikeDeclaration> symbol;
        FirBasedSymbol firBasedSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        FirMemberDeclaration firMemberDeclaration = firDeclaration instanceof FirMemberDeclaration ? (FirMemberDeclaration) firDeclaration : null;
        if (firMemberDeclaration != null) {
            if (!firMemberDeclaration.getStatus().isActual()) {
                firMemberDeclaration = null;
            }
            if (firMemberDeclaration == null) {
                return;
            }
            if (firMemberDeclaration instanceof FirTypeAlias) {
                symbol = ToSymbolUtilsKt.toClassSymbol(checkerContext, AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(FirTypeUtilsKt.getConeType(((FirTypeAlias) firMemberDeclaration).getExpandedTypeRef())));
                if (symbol == null) {
                    return;
                }
            } else if (!(firMemberDeclaration instanceof FirClassLikeDeclaration)) {
                return;
            } else {
                symbol = ((FirClassLikeDeclaration) firMemberDeclaration).getSymbol();
            }
            E fir = symbol.getFir();
            FirClass firClass = fir instanceof FirClass ? (FirClass) fir : null;
            if (firClass != null) {
                ClassKind classKind = firClass.getClassKind();
                ClassKind classKind2 = ClassKind.INTERFACE;
                if (classKind != classKind2) {
                    firClass = null;
                }
                if (firClass == null) {
                    return;
                }
                FirClassLikeDeclaration firClassLikeDeclaration = (FirClassLikeDeclaration) firMemberDeclaration;
                Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> expectForActual = ExpectActualAttributesKt.getExpectForActual(firClassLikeDeclaration.getSymbol());
                if (expectForActual == null) {
                    expectForActual = MapsKt.emptyMap();
                }
                List<FirBasedSymbol<?>> list = expectForActual.get(ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE);
                if (list == null || (firBasedSymbol = (FirBasedSymbol) CollectionsKt.singleOrNull(list)) == null) {
                    return;
                }
                FirDeclaration fir2 = firBasedSymbol.getFir();
                FirClass firClass2 = fir2 instanceof FirClass ? (FirClass) fir2 : null;
                if (firClass2 != null) {
                    FirAnnotationContainer firAnnotationContainer = firClass2.getClassKind() == classKind2 ? firClass2 : null;
                    if (firAnnotationContainer != null) {
                        if (firClass.getStatus().isExternal()) {
                            if (hasJsNoRuntime(checkerContext, firAnnotationContainer)) {
                                return;
                            }
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClassLikeDeclaration.getSource(), FirJsErrors.INSTANCE.getJS_ACTUAL_EXTERNAL_INTERFACE_WHILE_EXPECT_WITHOUT_JS_NO_RUNTIME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        } else {
                            if (!hasJsNoRuntime(checkerContext, firAnnotationContainer) || hasJsNoRuntime(checkerContext, firClass)) {
                                return;
                            }
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClassLikeDeclaration.getSource(), FirJsErrors.INSTANCE.getJS_NO_RUNTIME_ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                    }
                }
            }
        }
    }
}
