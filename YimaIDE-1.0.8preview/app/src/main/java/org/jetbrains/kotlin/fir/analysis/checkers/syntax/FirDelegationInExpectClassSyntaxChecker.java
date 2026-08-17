package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J=\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0013J5\u0010\u0014\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0015H\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirDelegationInExpectClassSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirDeclarationSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "checkPsi", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "psi", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/KtPsiSourceElement;Lorg/jetbrains/kotlin/psi/KtClassOrObject;)V", "checkLightTree", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/KtLightSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegationInExpectClassSyntaxChecker extends FirDeclarationSyntaxChecker<FirRegularClass, KtClassOrObject> {
    public static final FirDelegationInExpectClassSyntaxChecker INSTANCE = new FirDelegationInExpectClassSyntaxChecker();

    private FirDelegationInExpectClassSyntaxChecker() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass, KtLightSourceElement ktLightSourceElement) {
        LighterASTNode lighterASTNode;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        ktLightSourceElement.getClass();
        Iterator<FirTypeRef> it = firRegularClass.getSuperTypeRefs().iterator();
        while (it.hasNext()) {
            KtSourceElement source = it.next().getSource();
            if (source != null && (lighterASTNode = (LighterASTNode) source.getTreeStructure().getParent(source.getLighterASTNode())) != null && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.DELEGATED_SUPER_TYPE_ENTRY)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) new KtLightSourceElement(lighterASTNode, lighterASTNode.getStartOffset(), lighterASTNode.getEndOffset(), source.getTreeStructure(), KtRealSourceElementKind.INSTANCE), FirErrors.INSTANCE.getIMPLEMENTATION_BY_DELEGATION_IN_EXPECT_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsi(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass, KtPsiSourceElement ktPsiSourceElement, KtClassOrObject ktClassOrObject) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        ktPsiSourceElement.getClass();
        ktClassOrObject.getClass();
        Iterator<FirTypeRef> it = firRegularClass.getSuperTypeRefs().iterator();
        while (it.hasNext()) {
            KtSourceElement source = it.next().getSource();
            if (source != null) {
                PsiElement psi = KtSourceElementKt.getPsi(source);
                PsiElement parent = psi != null ? psi.getParent() : null;
                KtDelegatedSuperTypeEntry ktDelegatedSuperTypeEntry = parent instanceof KtDelegatedSuperTypeEntry ? (KtDelegatedSuperTypeEntry) parent : null;
                if (ktDelegatedSuperTypeEntry != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) new KtRealPsiSourceElement(ktDelegatedSuperTypeEntry), FirErrors.INSTANCE.getIMPLEMENTATION_BY_DELEGATION_IN_EXPECT_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public boolean isApplicable(FirRegularClass element, KtSourceElement source) {
        element.getClass();
        source.getClass();
        return element.getStatus().isExpect();
    }
}
