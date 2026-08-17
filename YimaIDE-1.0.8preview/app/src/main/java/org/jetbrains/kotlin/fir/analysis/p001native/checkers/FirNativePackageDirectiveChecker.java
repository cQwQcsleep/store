package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
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
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativePackageDirectiveChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativePackageDirectiveChecker extends FirDeclarationChecker<FirFile> {
    public static final FirNativePackageDirectiveChecker INSTANCE = new FirNativePackageDirectiveChecker();

    private FirNativePackageDirectiveChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFile firFile) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFile.getClass();
        KtPsiSourceElement source = firFile.getPackageDirective().getSource();
        if (source == null) {
            return;
        }
        Set of = SetsKt.setOf(KtNodeTypes.REFERENCE_EXPRESSION);
        if (source instanceof KtPsiSourceElement) {
            List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(source.getPsi(), 0)});
            while (true) {
                List list = listMutableListOf;
                if (list.isEmpty()) {
                    return;
                }
                Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
                Object objComponent1 = pair.component1();
                int iIntValue = ((Number) pair.component2()).intValue();
                if (iIntValue != 0) {
                    PsiElement psiElement = (PsiElement) objComponent1;
                    IElementType elementType = psiElement.getNode().getElementType();
                    elementType.getClass();
                    if (of.contains(elementType)) {
                        if (KtRealSourceElementKind.INSTANCE == null) {
                            bu8.a();
                            return;
                        }
                        KtSourceElement ktRealPsiSourceElement = new KtRealPsiSourceElement(psiElement);
                        FirNativeIdentifierChecker firNativeIdentifierChecker = FirNativeIdentifierChecker.INSTANCE;
                        Name nameIdentifier = Name.identifier(String.valueOf(KtSourceElementKt.getText(ktRealPsiSourceElement)));
                        nameIdentifier.getClass();
                        firNativeIdentifierChecker.checkNameAndReport$org_jetbrains_kotlin_checkers_native(checkerContext, diagnosticReporter, nameIdentifier, ktRealPsiSourceElement);
                    }
                }
                if (iIntValue != -1) {
                    Iterator it = CollectionsKt.asReversed(SequencesKt.toList(PsiUtilsKt.getAllChildren((PsiElement) objComponent1))).iterator();
                    while (it.hasNext()) {
                        list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                    }
                }
            }
        } else {
            if (!(source instanceof KtLightSourceElement)) {
                bu8.a();
                return;
            }
            KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) source;
            LighterASTNode lighterASTNode = ktLightSourceElement.getLighterASTNode();
            FlyweightCapableTreeStructure treeStructure = ktLightSourceElement.getTreeStructure();
            List listMutableListOf2 = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(lighterASTNode, 0)});
            while (true) {
                List list2 = listMutableListOf2;
                if (list2.isEmpty()) {
                    return;
                }
                Pair pair2 = (Pair) AddToStdlibKt.popLast(listMutableListOf2);
                Object objComponent2 = pair2.component1();
                int iIntValue2 = ((Number) pair2.component2()).intValue();
                if (iIntValue2 != 0) {
                    LighterASTNode lighterASTNode2 = (LighterASTNode) objComponent2;
                    IElementType tokenType = lighterASTNode2.getTokenType();
                    tokenType.getClass();
                    if (of.contains(tokenType)) {
                        KtSourceElement ktLightSourceElement2 = new KtLightSourceElement(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE);
                        FirNativeIdentifierChecker firNativeIdentifierChecker2 = FirNativeIdentifierChecker.INSTANCE;
                        Name nameIdentifier2 = Name.identifier(String.valueOf(KtSourceElementKt.getText(ktLightSourceElement2)));
                        nameIdentifier2.getClass();
                        firNativeIdentifierChecker2.checkNameAndReport$org_jetbrains_kotlin_checkers_native(checkerContext, diagnosticReporter, nameIdentifier2, ktLightSourceElement2);
                    }
                }
                if (iIntValue2 != -1) {
                    Iterator it2 = CollectionsKt.asReversed(LightTreeUtilsKt.getChildren((LighterASTNode) objComponent2, treeStructure)).iterator();
                    while (it2.hasNext()) {
                        list2.add(TuplesKt.to(it2.next(), Integer.valueOf(iIntValue2 + 1)));
                    }
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
