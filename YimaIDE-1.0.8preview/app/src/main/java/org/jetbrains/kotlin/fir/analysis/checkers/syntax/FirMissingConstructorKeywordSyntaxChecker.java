package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtConstructor;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J=\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0013J5\u0010\u0014\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0015H\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0016R\u001c\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00190\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirMissingConstructorKeywordSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirDeclarationSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "checkPsi", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "psi", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;Lorg/jetbrains/kotlin/KtPsiSourceElement;Lorg/jetbrains/kotlin/psi/KtDeclaration;)V", "checkLightTree", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;Lorg/jetbrains/kotlin/KtLightSourceElement;)V", "importantNodeTypes", Argument.Delimiters.none, "Lcom/intellij/psi/tree/IElementType;", "kotlin.jvm.PlatformType", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMissingConstructorKeywordSyntaxChecker extends FirDeclarationSyntaxChecker<FirConstructor, KtDeclaration> {
    public static final FirMissingConstructorKeywordSyntaxChecker INSTANCE = new FirMissingConstructorKeywordSyntaxChecker();
    private static final Set<IElementType> importantNodeTypes = SetsKt.setOf(new IElementType[]{KtNodeTypes.MODIFIER_LIST, KtTokens.CONSTRUCTOR_KEYWORD});

    private FirMissingConstructorKeywordSyntaxChecker() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirConstructor firConstructor, KtLightSourceElement ktLightSourceElement) {
        boolean z = false;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firConstructor.getClass();
        ktLightSourceElement.getClass();
        if (ktLightSourceElement.getKind() instanceof KtRealSourceElementKind) {
            Set<IElementType> set = importantNodeTypes;
            LighterASTNode lighterASTNode = ktLightSourceElement.getLighterASTNode();
            FlyweightCapableTreeStructure treeStructure = ktLightSourceElement.getTreeStructure();
            List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(lighterASTNode, 0)});
            boolean z2 = false;
            while (true) {
                List list = listMutableListOf;
                if (list.isEmpty()) {
                    break;
                }
                Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
                Object objComponent1 = pair.component1();
                int iIntValue = ((Number) pair.component2()).intValue();
                if (iIntValue != 0) {
                    LighterASTNode lighterASTNode2 = (LighterASTNode) objComponent1;
                    IElementType tokenType = lighterASTNode2.getTokenType();
                    tokenType.getClass();
                    if (set.contains(tokenType)) {
                        if (Intrinsics.areEqual(new KtLightSourceElement(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE).getElementType(), KtTokens.CONSTRUCTOR_KEYWORD)) {
                            z2 = true;
                        } else {
                            z = true;
                        }
                    }
                }
                if (iIntValue != 1) {
                    Iterator it = CollectionsKt.asReversed(LightTreeUtilsKt.getChildren((LighterASTNode) objComponent1, treeStructure)).iterator();
                    while (it.hasNext()) {
                        list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                    }
                }
            }
            if (!z || z2) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktLightSourceElement, FirErrors.INSTANCE.getMISSING_CONSTRUCTOR_KEYWORD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsi(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirConstructor firConstructor, KtPsiSourceElement ktPsiSourceElement, KtDeclaration ktDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firConstructor.getClass();
        ktPsiSourceElement.getClass();
        ktDeclaration.getClass();
        if (ktDeclaration instanceof KtConstructor) {
            KtConstructor ktConstructor = (KtConstructor) ktDeclaration;
            if (ktConstructor.getModifierList() == null || ktConstructor.getConstructorKeyword() != null) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktPsiSourceElement, FirErrors.INSTANCE.getMISSING_CONSTRUCTOR_KEYWORD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public boolean isApplicable(FirConstructor element, KtSourceElement source) {
        element.getClass();
        source.getClass();
        return true;
    }
}
