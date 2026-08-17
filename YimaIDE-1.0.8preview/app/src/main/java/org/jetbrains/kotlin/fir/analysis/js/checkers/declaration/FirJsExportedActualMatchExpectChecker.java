package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsHelpersKt;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u0010H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsExportedActualMatchExpectChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "isReachableOutsideOfKotlin", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsExportedActualMatchExpectChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJsExportedActualMatchExpectChecker INSTANCE = new FirJsExportedActualMatchExpectChecker();

    private FirJsExportedActualMatchExpectChecker() {
        super(MppCheckerKind.Platform);
    }

    private final boolean isReachableOutsideOfKotlin(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            return FirJsHelpersKt.isExportedObject(checkerContext, firBasedSymbol) || FirJsHelpersKt.isEffectivelyExternal(checkerContext, firBasedSymbol);
        }
        return FirJsHelpersKt.isExportedObject(checkerContext, firBasedSymbol);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirBasedSymbol firBasedSymbol;
        FirBasedSymbol<FirDeclaration> symbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.AllowExpectDeclarationsInJsExport) || !(firDeclaration instanceof FirMemberDeclaration)) {
            return;
        }
        FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
        if (firMemberDeclaration.getStatus().isActual()) {
            Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> expectForActual = ExpectActualAttributesKt.getExpectForActual(firMemberDeclaration.getSymbol());
            if (expectForActual == null) {
                expectForActual = MapsKt.emptyMap();
            }
            List<FirBasedSymbol<?>> list = expectForActual.get(ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE);
            if (list == null || (firBasedSymbol = (FirBasedSymbol) CollectionsKt.singleOrNull(list)) == null || !FirJsHelpersKt.isExportedObject(checkerContext, (FirBasedSymbol<?>) firBasedSymbol)) {
                return;
            }
            if (firMemberDeclaration instanceof FirTypeAlias) {
                symbol = ToSymbolUtilsKt.toClassSymbol(checkerContext, AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(FirTypeUtilsKt.getConeType(((FirTypeAlias) firDeclaration).getExpandedTypeRef())));
                if (symbol == null) {
                    return;
                }
            } else {
                symbol = firMemberDeclaration.getSymbol();
            }
            if (isReachableOutsideOfKotlin(checkerContext, symbol)) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firMemberDeclaration.getSource(), FirJsErrors.INSTANCE.getNOT_EXPORTED_OR_EXTERNAL_ACTUAL_DECLARATION_WHILE_EXPECT_IS_EXPORTED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
