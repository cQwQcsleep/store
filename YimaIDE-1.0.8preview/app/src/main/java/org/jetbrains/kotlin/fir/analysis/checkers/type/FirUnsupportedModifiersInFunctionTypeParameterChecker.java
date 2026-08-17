package org.jetbrains.kotlin.fir.analysis.checkers.type;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirUnsupportedModifiersInFunctionTypeParameterChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.psi.KtValVarKeywordOwner;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u000eJ-\u0010\u000f\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0002R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0010J-\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\rH\u0002R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013J-\u0010\u0014\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\rH\u0002R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirUnsupportedModifiersInFunctionTypeParameterChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirFunctionalTypeParameterSyntaxChecker;", "<init>", "()V", "checkPsiOrLightTree", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "element", "Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkValOrVarKeyword", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkModifiers", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/KtSourceElement;)Z", "checkAnnotations", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnsupportedModifiersInFunctionTypeParameterChecker extends FirFunctionalTypeParameterSyntaxChecker {
    public static final FirUnsupportedModifiersInFunctionTypeParameterChecker INSTANCE = new FirUnsupportedModifiersInFunctionTypeParameterChecker();

    private FirUnsupportedModifiersInFunctionTypeParameterChecker() {
    }

    public static boolean a(ASTNode aSTNode) {
        aSTNode.getClass();
        return Intrinsics.areEqual(aSTNode.getElementType(), KtNodeTypes.ANNOTATION_ENTRY);
    }

    public static KtRealPsiSourceElement b(ASTNode aSTNode) {
        aSTNode.getClass();
        PsiElement psi = aSTNode.getPsi();
        psi.getClass();
        return new KtRealPsiSourceElement(psi);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean checkAnnotations(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, KtSourceElement ktSourceElement) throws KotlinIllegalArgumentExceptionWithAttachments {
        List list;
        FirModifierList modifierList = FirKeywordUtilsKt.getModifierList(ktSourceElement);
        if (modifierList == null) {
            return true;
        }
        if (modifierList instanceof FirModifierList.FirLightModifierList) {
            FirModifierList.FirLightModifierList firLightModifierList = (FirModifierList.FirLightModifierList) modifierList;
            FlyweightCapableTreeStructure<LighterASTNode> tree = firLightModifierList.getTree();
            List children = LightTreeUtilsKt.getChildren(firLightModifierList.getModifierList(), tree);
            ArrayList<LighterASTNode> arrayList = new ArrayList();
            for (Object obj : children) {
                if (Intrinsics.areEqual(((LighterASTNode) obj).getTokenType(), KtNodeTypes.ANNOTATION_ENTRY)) {
                    arrayList.add(obj);
                }
            }
            list = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            for (LighterASTNode lighterASTNode : arrayList) {
                list.add(new KtLightSourceElement(lighterASTNode, lighterASTNode.getStartOffset(), lighterASTNode.getEndOffset(), tree, KtRealSourceElementKind.INSTANCE));
            }
        } else {
            if (!(modifierList instanceof FirModifierList.FirPsiModifierList)) {
                bu8.a();
                return false;
            }
            ASTNode node = ((FirModifierList.FirPsiModifierList) modifierList).getModifierList().getNode();
            node.getClass();
            list = SequencesKt.toList(SequencesKt.map(SequencesKt.filter(PsiUtilsKt.children(node), new Function1() { // from class: ig5
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(FirUnsupportedModifiersInFunctionTypeParameterChecker.a((ASTNode) obj2));
                }
            }), new Function1() { // from class: jg5
                public final Object invoke(Object obj2) {
                    return FirUnsupportedModifiersInFunctionTypeParameterChecker.b((ASTNode) obj2);
                }
            }));
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) it.next(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Function type parameters cannot have annotations.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean checkModifiers(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, KtSourceElement ktSourceElement) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirModifierList modifierList = FirKeywordUtilsKt.getModifierList(ktSourceElement);
        if (modifierList == null) {
            return true;
        }
        Iterator<FirModifier<?>> it = modifierList.getModifiers().iterator();
        while (it.hasNext()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) it.next().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Function type parameters cannot have modifiers.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        return false;
    }

    private final void checkValOrVarKeyword(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, KtSourceElement ktSourceElement) {
        PsiElement valOrVarKeyword;
        KtRealPsiSourceElement ktLightSourceElement = null;
        if (ktSourceElement instanceof KtPsiSourceElement) {
            PsiElement psi = ((KtPsiSourceElement) ktSourceElement).getPsi();
            KtValVarKeywordOwner ktValVarKeywordOwner = psi instanceof KtValVarKeywordOwner ? (KtValVarKeywordOwner) psi : null;
            if (ktValVarKeywordOwner != null && (valOrVarKeyword = ktValVarKeywordOwner.getValOrVarKeyword()) != null) {
                if (KtRealSourceElementKind.INSTANCE == null) {
                    bu8.a();
                    return;
                }
                ktLightSourceElement = new KtRealPsiSourceElement(valOrVarKeyword);
            }
        } else {
            if (!(ktSourceElement instanceof KtLightSourceElement)) {
                bu8.a();
                return;
            }
            KtLightSourceElement ktLightSourceElement2 = (KtLightSourceElement) ktSourceElement;
            LighterASTNode lighterASTNodeValOrVarKeyword = LightTreePositioningStrategiesKt.valOrVarKeyword(ktLightSourceElement2.getTreeStructure(), ktLightSourceElement2.getLighterASTNode());
            if (lighterASTNodeValOrVarKeyword != null) {
                ktLightSourceElement = new KtLightSourceElement(lighterASTNodeValOrVarKeyword, lighterASTNodeValOrVarKeyword.getStartOffset(), lighterASTNodeValOrVarKeyword.getEndOffset(), ktLightSourceElement2.getTreeStructure(), KtRealSourceElementKind.INSTANCE);
            }
        }
        KtRealPsiSourceElement ktRealPsiSourceElement = ktLightSourceElement;
        if (ktRealPsiSourceElement == null) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktRealPsiSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Function type parameters cannot be 'val' or 'var'.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsiOrLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionTypeParameter firFunctionTypeParameter, KtSourceElement ktSourceElement) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionTypeParameter.getClass();
        ktSourceElement.getClass();
        checkModifiers(diagnosticReporter, checkerContext, ktSourceElement);
        checkAnnotations(diagnosticReporter, checkerContext, ktSourceElement);
        checkValOrVarKeyword(diagnosticReporter, checkerContext, ktSourceElement);
    }
}
