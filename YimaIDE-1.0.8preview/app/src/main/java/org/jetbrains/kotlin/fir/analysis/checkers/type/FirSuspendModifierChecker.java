package org.jetbrains.kotlin.fir.analysis.checkers.type;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.psi.stubs.elements.KtModifierListElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirSuspendModifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeRefChecker;", "<init>", "()V", "suspendTokenElementSet", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "kotlin.jvm.PlatformType", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSuspendModifierChecker extends FirTypeChecker<FirTypeRef> {
    public static final FirSuspendModifierChecker INSTANCE = new FirSuspendModifierChecker();
    private static final Set<KtModifierKeywordToken> suspendTokenElementSet = SetsKt.setOf(KtTokens.SUSPEND_KEYWORD);

    private FirSuspendModifierChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeRef.getClass();
        if (firTypeRef instanceof FirUnresolvedTypeRef) {
            ArrayList arrayList = new ArrayList();
            KtSourceElement source = ((FirUnresolvedTypeRef) firTypeRef).getSource();
            KtModifierListElementType ktModifierListElementType = KtStubElementTypes.MODIFIER_LIST;
            ktModifierListElementType.getClass();
            KtPsiSourceElement child$default = FirSourceUtilsKt.getChild$default(source, (IElementType) ktModifierListElementType, 0, 1, false, 10, (Object) null);
            if (child$default != null) {
                Set<KtModifierKeywordToken> set = suspendTokenElementSet;
                if (child$default instanceof KtPsiSourceElement) {
                    List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(child$default.getPsi(), 0)});
                    while (true) {
                        List list = listMutableListOf;
                        if (list.isEmpty()) {
                            break;
                        }
                        Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
                        Object objComponent1 = pair.component1();
                        int iIntValue = ((Number) pair.component2()).intValue();
                        if (iIntValue != 0) {
                            PsiElement psiElement = (PsiElement) objComponent1;
                            IElementType elementType = psiElement.getNode().getElementType();
                            elementType.getClass();
                            if (set.contains(elementType)) {
                                if (KtRealSourceElementKind.INSTANCE == null) {
                                    bu8.a();
                                    return;
                                }
                                arrayList.add(new KtRealPsiSourceElement(psiElement));
                            }
                        }
                        if (iIntValue != 1) {
                            Iterator it = CollectionsKt.asReversed(SequencesKt.toList(PsiUtilsKt.getAllChildren((PsiElement) objComponent1))).iterator();
                            while (it.hasNext()) {
                                list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                            }
                        }
                    }
                } else {
                    if (!(child$default instanceof KtLightSourceElement)) {
                        bu8.a();
                        return;
                    }
                    KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) child$default;
                    LighterASTNode lighterASTNode = ktLightSourceElement.getLighterASTNode();
                    FlyweightCapableTreeStructure treeStructure = ktLightSourceElement.getTreeStructure();
                    List listMutableListOf2 = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(lighterASTNode, 0)});
                    while (true) {
                        List list2 = listMutableListOf2;
                        if (list2.isEmpty()) {
                            break;
                        }
                        Pair pair2 = (Pair) AddToStdlibKt.popLast(listMutableListOf2);
                        Object objComponent2 = pair2.component1();
                        int iIntValue2 = ((Number) pair2.component2()).intValue();
                        if (iIntValue2 != 0) {
                            LighterASTNode lighterASTNode2 = (LighterASTNode) objComponent2;
                            IElementType tokenType = lighterASTNode2.getTokenType();
                            tokenType.getClass();
                            if (set.contains(tokenType)) {
                                arrayList.add(new KtLightSourceElement(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE));
                            }
                        }
                        if (iIntValue2 != 1) {
                            Iterator it2 = CollectionsKt.asReversed(LightTreeUtilsKt.getChildren((LighterASTNode) objComponent2, treeStructure)).iterator();
                            while (it2.hasNext()) {
                                list2.add(TuplesKt.to(it2.next(), Integer.valueOf(iIntValue2 + 1)));
                            }
                        }
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            if (!(firTypeRef instanceof FirFunctionTypeRef) || ((FirFunctionTypeRef) firTypeRef).isMarkedNullable()) {
                KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, (AbstractKtSourceElement) CollectionsKt.first(arrayList), FirErrors.INSTANCE.getWRONG_MODIFIER_TARGET(), KtTokens.SUSPEND_KEYWORD, "non-functional type", (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            } else if (arrayList.size() > 1) {
                KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, (AbstractKtSourceElement) CollectionsKt.first(arrayList), FirErrors.INSTANCE.getREPEATED_MODIFIER(), KtTokens.SUSPEND_KEYWORD, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }
}
