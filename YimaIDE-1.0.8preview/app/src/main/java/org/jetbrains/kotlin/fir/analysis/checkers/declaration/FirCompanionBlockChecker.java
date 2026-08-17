package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import com.intellij.psi.tree.IElementType;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.CompanionBlockInfo;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCompanionBlockChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "companionModifierSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCompanionBlockChecker extends FirDeclarationChecker<FirClass> {
    public static final FirCompanionBlockChecker INSTANCE = new FirCompanionBlockChecker();

    private FirCompanionBlockChecker() {
        super(MppCheckerKind.Common);
    }

    private final KtSourceElement companionModifierSource(KtSourceElement ktSourceElement) {
        IElementType iElementType = KtNodeTypes.MODIFIER_LIST;
        iElementType.getClass();
        KtSourceElement child$default = FirSourceUtilsKt.getChild$default(ktSourceElement, iElementType, 0, 0, false, 14, (Object) null);
        if (child$default == null) {
            return null;
        }
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.COMPANION_KEYWORD;
        ktModifierKeywordToken.getClass();
        return FirSourceUtilsKt.getChild$default(child$default, (IElementType) ktModifierKeywordToken, 0, 0, false, 14, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        CompanionBlockInfo companionBlocks = ClassMembersKt.getCompanionBlocks(firClass);
        if (companionBlocks == null) {
            return;
        }
        KtSourceElement ktSourceElement = (KtSourceElement) CollectionsKt.first(companionBlocks.getValidCompanionBlocks());
        LanguageFeature languageFeature = LanguageFeature.CompanionBlocksAndExtensions;
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, languageFeature)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) companionModifierSource(ktSourceElement), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(languageFeature, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        if (firClass.getClassKind() == ClassKind.ENUM_ENTRY) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) companionModifierSource(ktSourceElement), (KtDiagnosticFactory1) FirErrors.INSTANCE.getILLEGAL_COMPANION_BLOCK(), CollectionsKt.last(checkerContext.getContainingDeclarations()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else if (firClass.getClassKind() == ClassKind.OBJECT || (firClass instanceof FirAnonymousObject)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) companionModifierSource(ktSourceElement), (KtDiagnosticFactory1) FirErrors.INSTANCE.getILLEGAL_COMPANION_BLOCK(), (Object) firClass.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        Iterator<T> it = companionBlocks.getNestedCompanionBlocks().iterator();
        while (it.hasNext()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) INSTANCE.companionModifierSource((KtSourceElement) it.next()), FirErrors.INSTANCE.getCOMPANION_BLOCK_NESTED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
