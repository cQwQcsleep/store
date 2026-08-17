package org.jetbrains.kotlin.fir.analysis.checkers.type;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
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
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.config.FirContextParametersLanguageVersionSettingsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirContextParametersDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.psi.KtContextParameterList;
import org.jetbrains.kotlin.psi.KtFunctionType;
import org.jetbrains.kotlin.psi.KtNullableType;
import org.jetbrains.kotlin.psi.KtTypeElement;
import org.jetbrains.kotlin.psi.KtTypeReference;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.psi.stubs.elements.KtParameterElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes;
import org.jetbrains.kotlin.psi.stubs.elements.KtTokenSets;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u000e\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\u00020\u0013H\u0002R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirContextualFunctionTypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "valueParameterElementSet", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/stubs/elements/KtParameterElementType;", "kotlin.jvm.PlatformType", "findContextReceiverListSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContextualFunctionTypeChecker extends FirTypeChecker<FirResolvedTypeRef> {
    public static final FirContextualFunctionTypeChecker INSTANCE = new FirContextualFunctionTypeChecker();
    private static final Set<KtParameterElementType> valueParameterElementSet = SetsKt.setOf(KtStubElementTypes.VALUE_PARAMETER);

    private FirContextualFunctionTypeChecker() {
        super(MppCheckerKind.Platform);
    }

    private final KtSourceElement findContextReceiverListSource(KtSourceElement ktSourceElement) {
        if (ktSourceElement instanceof KtPsiSourceElement) {
            return findContextReceiverListSource$findContextReceiverListSource(((KtPsiSourceElement) ktSourceElement).getPsi());
        }
        if (ktSourceElement instanceof KtLightSourceElement) {
            KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
            return findContextReceiverListSource$findContextReceiverListSource$0(ktLightSourceElement.getLighterASTNode(), ktLightSourceElement.getTreeStructure());
        }
        bu8.a();
        return null;
    }

    private static final KtPsiSourceElement findContextReceiverListSource$findContextReceiverListSource(PsiElement psiElement) {
        KtContextParameterList contextParameterList;
        if (psiElement instanceof KtTypeReference) {
            KtTypeElement typeElement = ((KtTypeReference) psiElement).getTypeElement();
            if (typeElement != null) {
                return findContextReceiverListSource$findContextReceiverListSource(typeElement);
            }
            return null;
        }
        if (psiElement instanceof KtNullableType) {
            KtTypeElement innerType = ((KtNullableType) psiElement).getInnerType();
            if (innerType != null) {
                return findContextReceiverListSource$findContextReceiverListSource(innerType);
            }
            return null;
        }
        if ((psiElement instanceof KtFunctionType) && (contextParameterList = ((KtFunctionType) psiElement).getContextParameterList()) != null) {
            if (KtRealSourceElementKind.INSTANCE != null) {
                return new KtRealPsiSourceElement(contextParameterList);
            }
            bu8.a();
        }
        return null;
    }

    private static final KtLightSourceElement findContextReceiverListSource$findContextReceiverListSource$0(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        IElementType tokenType = lighterASTNode.getTokenType();
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE) || Intrinsics.areEqual(tokenType, KtNodeTypes.NULLABLE_TYPE)) {
            TokenSet tokenSet = KtTokenSets.TYPE_ELEMENT_TYPES;
            tokenSet.getClass();
            LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(flyweightCapableTreeStructure, lighterASTNode, tokenSet);
            if (lighterASTNodeFindChildByType != null) {
                return findContextReceiverListSource$findContextReceiverListSource$0(lighterASTNodeFindChildByType, flyweightCapableTreeStructure);
            }
            return null;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUNCTION_TYPE)) {
            IElementType iElementType = KtNodeTypes.CONTEXT_PARAMETER_LIST;
            iElementType.getClass();
            LighterASTNode lighterASTNodeFindChildByType2 = LightTreePositioningStrategiesKt.findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
            if (lighterASTNodeFindChildByType2 != null) {
                return new KtLightSourceElement(lighterASTNodeFindChildByType2, lighterASTNodeFindChildByType2.getStartOffset(), lighterASTNodeFindChildByType2.getEndOffset(), flyweightCapableTreeStructure, KtRealSourceElementKind.INSTANCE);
            }
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:47:0x014c  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedTypeRef firResolvedTypeRef) throws KotlinIllegalArgumentExceptionWithAttachments {
        KtPsiSourceElement ktPsiSourceElementFindContextReceiverListSource;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedTypeRef.getClass();
        KtSourceElement source = firResolvedTypeRef.getSource();
        if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) && CompilerConeAttributesKt.getHasContextParameters(AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(firResolvedTypeRef.getConeType()))) {
            KtSourceElement source2 = firResolvedTypeRef.getSource();
            if (source2 == null || (ktPsiSourceElementFindContextReceiverListSource = findContextReceiverListSource(source2)) == null) {
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Source for type ref of contextual function type doesn't contain context list.", (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "fir", firResolvedTypeRef);
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
            Set<KtParameterElementType> set = valueParameterElementSet;
            if (ktPsiSourceElementFindContextReceiverListSource instanceof KtPsiSourceElement) {
                List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(ktPsiSourceElementFindContextReceiverListSource.getPsi(), 0)});
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
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) new KtRealPsiSourceElement(psiElement), FirErrors.INSTANCE.getNAMED_CONTEXT_PARAMETER_IN_FUNCTION_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
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
                if (!(ktPsiSourceElementFindContextReceiverListSource instanceof KtLightSourceElement)) {
                    bu8.a();
                    return;
                }
                KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktPsiSourceElementFindContextReceiverListSource;
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
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) new KtLightSourceElement(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE), FirErrors.INSTANCE.getNAMED_CONTEXT_PARAMETER_IN_FUNCTION_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
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
            if (!LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ContextReceivers)) {
                FirHelpersKt.requireFeatureSupport$default(checkerContext, diagnosticReporter, (KtSourceElement) ktPsiSourceElementFindContextReceiverListSource, LanguageFeature.ContextParameters, (SourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            if (FirContextParametersDeclarationChecker.INSTANCE.checkSubTypes(checkerContext, FunctionalTypeUtilsKt.contextParameterTypes(firResolvedTypeRef.getConeType(), checkerContext.getSession()))) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktPsiSourceElementFindContextReceiverListSource, FirErrors.INSTANCE.getSUBTYPING_BETWEEN_CONTEXT_RECEIVERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedTypeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCONTEXT_RECEIVERS_DEPRECATED(), (Object) FirContextParametersLanguageVersionSettingsChecker.INSTANCE.getDIAGNOSTIC_MESSAGE(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }
}
