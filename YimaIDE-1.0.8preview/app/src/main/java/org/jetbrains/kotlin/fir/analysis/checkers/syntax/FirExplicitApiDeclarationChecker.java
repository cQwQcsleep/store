package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.ExplicitApiMode;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.impl.FirPrimaryConstructor;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.resolve.transformers.PublishedApiEffectiveVisibilityKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u000fJ=\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0018J=\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001bJ\f\u0010\u001c\u001a\u00020\u0017*\u00020\u001aH\u0002¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirExplicitApiDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirDeclarationSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "<init>", "()V", "checkPsiOrLightTree", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkVisibilityModifier", "state", "Lorg/jetbrains/kotlin/config/ExplicitApiMode;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/config/ExplicitApiMode;Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;)V", "explicitVisibilityIsNotRequired", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)Z", "checkExplicitReturnType", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/config/ExplicitApiMode;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;)V", "returnTypeCheckIsApplicable", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExplicitApiDeclarationChecker extends FirDeclarationSyntaxChecker<FirDeclaration, KtDeclaration> {
    public static final FirExplicitApiDeclarationChecker INSTANCE = new FirExplicitApiDeclarationChecker();

    private FirExplicitApiDeclarationChecker() {
    }

    private final void checkExplicitReturnType(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ExplicitApiMode explicitApiMode, FirCallableDeclaration firCallableDeclaration, KtSourceElement ktSourceElement) {
        if (returnTypeCheckIsApplicable(firCallableDeclaration)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, explicitApiMode == ExplicitApiMode.STRICT ? FirErrors.INSTANCE.getNO_EXPLICIT_RETURN_TYPE_IN_API_MODE() : FirErrors.INSTANCE.getNO_EXPLICIT_RETURN_TYPE_IN_API_MODE_WARNING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private static final ExplicitApiMode checkPsiOrLightTree$extractState(CheckerContext checkerContext, AnalysisFlag<? extends ExplicitApiMode> analysisFlag) {
        Object flag = checkerContext.get$languageVersionSettings().getFlag(analysisFlag);
        if (((ExplicitApiMode) flag) == ExplicitApiMode.DISABLED) {
            flag = null;
        }
        return (ExplicitApiMode) flag;
    }

    private final void checkVisibilityModifier(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ExplicitApiMode explicitApiMode, FirMemberDeclaration firMemberDeclaration, KtSourceElement ktSourceElement) {
        KtSourceElement child$default;
        IElementType iElementType = KtNodeTypes.MODIFIER_LIST;
        iElementType.getClass();
        KtSourceElement child$default2 = FirSourceUtilsKt.getChild$default(ktSourceElement, iElementType, 0, 1, false, 10, (Object) null);
        if (child$default2 != null) {
            TokenSet tokenSet = KtTokens.VISIBILITY_MODIFIERS;
            tokenSet.getClass();
            child$default = FirSourceUtilsKt.getChild$default(child$default2, tokenSet, 0, 0, false, 14, (Object) null);
        } else {
            child$default = null;
        }
        if (child$default == null && !explicitVisibilityIsNotRequired(checkerContext, firMemberDeclaration)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, explicitApiMode == ExplicitApiMode.STRICT ? FirErrors.INSTANCE.getNO_EXPLICIT_VISIBILITY_IN_API_MODE() : FirErrors.INSTANCE.getNO_EXPLICIT_VISIBILITY_IN_API_MODE_WARNING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final boolean explicitVisibilityIsNotRequired(CheckerContext checkerContext, FirMemberDeclaration firMemberDeclaration) {
        if ((firMemberDeclaration instanceof FirPrimaryConstructor) || (firMemberDeclaration instanceof FirPropertyAccessor) || (firMemberDeclaration instanceof FirValueParameter) || (firMemberDeclaration instanceof FirAnonymousFunction)) {
            return true;
        }
        if (!(firMemberDeclaration instanceof FirCallableDeclaration)) {
            return false;
        }
        Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        FirRegularClassSymbol firRegularClassSymbol = objLastOrNull instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) objLastOrNull : null;
        return ((firMemberDeclaration instanceof FirProperty) && firRegularClassSymbol != null && (firRegularClassSymbol.getRawStatus().isData() || firRegularClassSymbol.getClassKind() == ClassKind.ANNOTATION_CLASS)) || firMemberDeclaration.getStatus().isOverride() || DeclarationUtilsKt.isLocalDeclaredInBlock(firMemberDeclaration);
    }

    private final boolean returnTypeCheckIsApplicable(FirCallableDeclaration firCallableDeclaration) {
        KtSourceElement source = firCallableDeclaration.getReturnTypeRef().getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtRealSourceElementKind.INSTANCE)) {
            return false;
        }
        if (firCallableDeclaration instanceof FirProperty) {
            return true;
        }
        return (firCallableDeclaration instanceof FirFunction) && !(firCallableDeclaration instanceof FirPropertyAccessor) && (((FirFunction) firCallableDeclaration).getBody() instanceof FirSingleExpressionBlock);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsiOrLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement) {
        EffectiveVisibility effectiveVisibility;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        ktSourceElement.getClass();
        boolean z = ktSourceElement.getKind() instanceof KtRealSourceElementKind;
        if ((z || Intrinsics.areEqual(ktSourceElement.getKind(), KtFakeSourceElementKind.PropertyFromParameter.INSTANCE)) && !Intrinsics.areEqual(firDeclaration.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ResultProperty.INSTANCE) && (firDeclaration instanceof FirMemberDeclaration)) {
            ExplicitApiMode explicitApiModeCheckPsiOrLightTree$extractState = checkPsiOrLightTree$extractState(checkerContext, AnalysisFlags.getExplicitApiMode());
            ExplicitApiMode explicitApiModeCheckPsiOrLightTree$extractState2 = checkPsiOrLightTree$extractState(checkerContext, AnalysisFlags.getExplicitReturnTypes());
            if ((explicitApiModeCheckPsiOrLightTree$extractState == null && explicitApiModeCheckPsiOrLightTree$extractState2 == null) || (firDeclaration instanceof FirEnumEntry)) {
                return;
            }
            FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
            FirDeclarationStatus status = firMemberDeclaration.getStatus();
            EffectiveVisibility effectiveVisibility2 = null;
            FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
            if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
                effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
            }
            if (effectiveVisibility.getPublicApi() || PublishedApiEffectiveVisibilityKt.getPublishedApiEffectiveVisibility(firDeclaration) != null) {
                FirBasedSymbol firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
                if (firBasedSymbol instanceof FirClassSymbol) {
                    effectiveVisibility2 = ((FirClassLikeSymbol) firBasedSymbol).getResolvedStatus().getEffectiveVisibility();
                } else if (firBasedSymbol instanceof FirCallableSymbol) {
                    effectiveVisibility2 = ((FirCallableSymbol) firBasedSymbol).getResolvedStatus().getEffectiveVisibility();
                }
                if (effectiveVisibility2 == null || effectiveVisibility2.getPublicApi()) {
                    if (explicitApiModeCheckPsiOrLightTree$extractState != null) {
                        checkVisibilityModifier(checkerContext, diagnosticReporter, explicitApiModeCheckPsiOrLightTree$extractState, firMemberDeclaration, ktSourceElement);
                    }
                    if (z && (firDeclaration instanceof FirCallableDeclaration)) {
                        if (explicitApiModeCheckPsiOrLightTree$extractState == null) {
                            explicitApiModeCheckPsiOrLightTree$extractState2.getClass();
                            explicitApiModeCheckPsiOrLightTree$extractState = explicitApiModeCheckPsiOrLightTree$extractState2;
                        }
                        checkExplicitReturnType(checkerContext, diagnosticReporter, explicitApiModeCheckPsiOrLightTree$extractState, (FirCallableDeclaration) firDeclaration, ktSourceElement);
                    }
                }
            }
        }
    }
}
