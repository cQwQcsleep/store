package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCompanionExtensionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "checkReceiver", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCompanionExtensionChecker extends FirDeclarationChecker<FirCallableDeclaration> {
    public static final FirCompanionExtensionChecker INSTANCE = new FirCompanionExtensionChecker();

    private FirCompanionExtensionChecker() {
        super(MppCheckerKind.Platform);
    }

    private final void checkReceiver(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirReceiverParameter firReceiverParameter) {
        Iterator<T> it = firReceiverParameter.getAnnotations().iterator();
        while (it.hasNext()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirAnnotation) it.next()).getSource(), FirErrors.INSTANCE.getCOMPANION_EXTENSION_RECEIVER_ANNOTATED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        FirTypeRef typeRef = firReceiverParameter.getTypeRef();
        Iterator<T> it2 = typeRef.getAnnotations().iterator();
        while (it2.hasNext()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirAnnotation) it2.next()).getSource(), FirErrors.INSTANCE.getCOMPANION_EXTENSION_RECEIVER_ANNOTATED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        ConeKotlinType abbreviatedTypeOrSelf = AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(FirTypeUtilsKt.getConeType(typeRef));
        if (!(abbreviatedTypeOrSelf.getTypeArguments().length == 0)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) typeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCOMPANION_EXTENSION_RECEIVER_WITH_TYPE_ARGUMENTS(), (Object) abbreviatedTypeOrSelf, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        if (ConeTypeUtilsKt.isMarkedNullable(abbreviatedTypeOrSelf)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) typeRef.getSource(), FirErrors.INSTANCE.getCOMPANION_EXTENSION_NULLABLE_RECEIVER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(checkerContext, ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(abbreviatedTypeOrSelf));
        if (symbol == null) {
            return;
        }
        if (symbol instanceof FirTypeParameterSymbol) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) typeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCOMPANION_EXTENSION_RECEIVER_IS_TYPE_PARAMETER(), (Object) abbreviatedTypeOrSelf, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else if ((symbol instanceof FirClassLikeSymbol) && FirHelpersKt.getClassKind((FirClassLikeSymbol) symbol) == ClassKind.OBJECT) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) typeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCOMPANION_EXTENSION_RECEIVER_IS_OBJECT(), (Object) abbreviatedTypeOrSelf, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableDeclaration.getClass();
        if (FirStatusUtilsKt.isCompanionExtension(firCallableDeclaration)) {
            LanguageFeature languageFeature = LanguageFeature.CompanionBlocksAndExtensions;
            if (LanguageVersionUtilsKt.isDisabled(checkerContext, languageFeature)) {
                KtModifierKeywordToken ktModifierKeywordToken = KtTokens.COMPANION_KEYWORD;
                ktModifierKeywordToken.getClass();
                FirModifier<?> modifier = FirKeywordUtilsKt.getModifier(firCallableDeclaration, ktModifierKeywordToken);
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (modifier != null ? modifier.getSource() : null), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(languageFeature, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (firCallableDeclaration.getStatus().isOperator() && !Intrinsics.areEqual(firCallableDeclaration.getSymbol().getName(), OperatorNameConventions.INVOKE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableDeclaration.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINAPPLICABLE_OPERATOR_MODIFIER(), (Object) "companion extension", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            FirReceiverParameter receiverParameter = firCallableDeclaration.getReceiverParameter();
            if (receiverParameter != null) {
                INSTANCE.checkReceiver(checkerContext, diagnosticReporter, receiverParameter);
            }
        }
    }
}
