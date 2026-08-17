package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
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
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKindKt;
import org.jetbrains.kotlin.fir.declarations.impl.FirPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.psi.KtContextParameterList;
import org.jetbrains.kotlin.psi.KtModifierList;
import org.jetbrains.kotlin.resolve.ModifierCheckerHelpersKt;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u0002H\u0002J\u0012\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0012*\u00020\u0015H\u0002J%\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0012R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirContextParametersDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "isDelegationOperator", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getContextParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "findContextReceiverListSources", "Lorg/jetbrains/kotlin/KtSourceElement;", "checkSubTypes", "types", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;)Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContextParametersDeclarationChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirContextParametersDeclarationChecker INSTANCE = new FirContextParametersDeclarationChecker();

    private FirContextParametersDeclarationChecker() {
        super(MppCheckerKind.Platform);
    }

    private static final ConeClassLikeType checkSubTypes$replaceTypeParametersByStarProjections(ConeClassLikeType coneClassLikeType) {
        ConeStarProjection[] typeArguments = coneClassLikeType.getTypeArguments();
        ArrayList arrayList = new ArrayList(typeArguments.length);
        for (ConeStarProjection coneStarProjectionCheckSubTypes$replaceTypeParametersByStarProjections : typeArguments) {
            ConeKotlinType type = ConeTypeProjectionKt.getType(coneStarProjectionCheckSubTypes$replaceTypeParametersByStarProjections);
            if (type != null) {
                if (type instanceof ConeTypeParameterType) {
                    coneStarProjectionCheckSubTypes$replaceTypeParametersByStarProjections = ConeStarProjection.INSTANCE;
                } else if (type instanceof ConeClassLikeType) {
                    coneStarProjectionCheckSubTypes$replaceTypeParametersByStarProjections = checkSubTypes$replaceTypeParametersByStarProjections((ConeClassLikeType) type);
                }
            }
            arrayList.add(coneStarProjectionCheckSubTypes$replaceTypeParametersByStarProjections);
        }
        return ConeTypeUtilsKt.withArguments(coneClassLikeType, (ConeTypeProjection[]) arrayList.toArray(new ConeTypeProjection[0]));
    }

    private final List<KtSourceElement> findContextReceiverListSources(KtSourceElement ktSourceElement) {
        List contextParameterLists;
        ArrayList arrayList = null;
        if (ktSourceElement instanceof KtPsiSourceElement) {
            KtModifierList childOfType = PsiTreeUtil.getChildOfType(((KtPsiSourceElement) ktSourceElement).getPsi(), KtModifierList.class);
            if (childOfType != null && (contextParameterLists = childOfType.getContextParameterLists()) != null) {
                List<KtContextParameterList> list = contextParameterLists;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (KtContextParameterList ktContextParameterList : list) {
                    ktContextParameterList.getClass();
                    if (KtRealSourceElementKind.INSTANCE == null) {
                        bu8.a();
                        return null;
                    }
                    arrayList2.add(new KtRealPsiSourceElement(ktContextParameterList));
                }
                arrayList = arrayList2;
            }
            return arrayList == null ? CollectionsKt.emptyList() : arrayList;
        }
        if (!(ktSourceElement instanceof KtLightSourceElement)) {
            bu8.a();
            return null;
        }
        KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
        FlyweightCapableTreeStructure treeStructure = ktLightSourceElement.getTreeStructure();
        LighterASTNode lighterASTNode = ktLightSourceElement.getLighterASTNode();
        IElementType iElementType = KtNodeTypes.MODIFIER_LIST;
        iElementType.getClass();
        LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType((FlyweightCapableTreeStructure<LighterASTNode>) treeStructure, lighterASTNode, iElementType);
        if (lighterASTNodeFindChildByType != null) {
            FlyweightCapableTreeStructure treeStructure2 = ktLightSourceElement.getTreeStructure();
            IElementType iElementType2 = KtNodeTypes.CONTEXT_PARAMETER_LIST;
            iElementType2.getClass();
            List<LighterASTNode> listFindChildrenByType = LightTreePositioningStrategiesKt.findChildrenByType(treeStructure2, lighterASTNodeFindChildByType, iElementType2);
            if (listFindChildrenByType != null) {
                List<LighterASTNode> list2 = listFindChildrenByType;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (LighterASTNode lighterASTNode2 : list2) {
                    arrayList.add(new KtLightSourceElement(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE));
                }
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private final List<FirValueParameter> getContextParameters(FirDeclaration firDeclaration) {
        if (firDeclaration instanceof FirCallableDeclaration) {
            return ((FirCallableDeclaration) firDeclaration).getContextParameters();
        }
        return firDeclaration instanceof FirRegularClass ? ((FirRegularClass) firDeclaration).getContextParameters() : CollectionsKt.emptyList();
    }

    private final boolean isDelegationOperator(FirCallableDeclaration firCallableDeclaration) {
        return firCallableDeclaration.getStatus().isOperator() && OperatorNameConventions.DELEGATED_PROPERTY_OPERATORS.contains(FirDeclarationUtilKt.getNameOrSpecialName(firCallableDeclaration));
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirModifierList modifierList;
        List<FirModifier<?>> modifiers;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        KtSourceElement source = firDeclaration.getSource();
        String str = null;
        if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) {
            return;
        }
        KtSourceElement source2 = firDeclaration instanceof FirFile ? ((FirFile) firDeclaration).getPackageDirective().getSource() : firDeclaration.getSource();
        List<KtSourceElement> listFindContextReceiverListSources = source2 != null ? findContextReceiverListSources(source2) : null;
        if (listFindContextReceiverListSources == null) {
            listFindContextReceiverListSources = CollectionsKt.emptyList();
        }
        List<KtSourceElement> list = listFindContextReceiverListSources;
        if (list.isEmpty()) {
            return;
        }
        List<KtSourceElement> list2 = list;
        AbstractKtSourceElement abstractKtSourceElement = (KtSourceElement) CollectionsKt.first(list2);
        if (list2.size() > 1) {
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement, FirErrors.INSTANCE.getMULTIPLE_CONTEXT_LISTS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            abstractKtSourceElement = abstractKtSourceElement;
        }
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ContextReceivers);
        LanguageFeature languageFeature = LanguageFeature.ContextParameters;
        boolean zIsEnabled2 = LanguageVersionUtilsKt.isEnabled(checkerContext, languageFeature);
        if (firDeclaration instanceof FirTypeAlias) {
            str = "Context parameters on type aliases are unsupported.";
        } else if (firDeclaration instanceof FirAnonymousInitializer) {
            str = "Context parameters on initializers are unsupported.";
        } else if (firDeclaration instanceof FirEnumEntry) {
            str = "Context parameters on enum entries are unsupported.";
        } else if (firDeclaration instanceof FirPropertyAccessor) {
            str = "Context parameters on property accessors are unsupported.";
        } else if (firDeclaration instanceof FirBackingField) {
            str = "Context parameters on backing fields are unsupported.";
        } else if (firDeclaration instanceof FirPrimaryConstructor) {
            str = "Context parameters on primary constructors are unsupported.";
        } else {
            boolean z = firDeclaration instanceof FirProperty;
            if (z && (((FirProperty) firDeclaration).getSymbol() instanceof FirLocalPropertySymbol)) {
                if (zIsEnabled2) {
                    str = "Context parameters on local properties are unsupported.";
                }
            } else if (firDeclaration instanceof FirConstructor) {
                if (zIsEnabled2) {
                    str = "Context parameters on constructors are unsupported.";
                }
            } else if (firDeclaration instanceof FirClass) {
                if (zIsEnabled2) {
                    str = "Context parameters on classes are unsupported.";
                }
            } else if ((firDeclaration instanceof FirCallableDeclaration) && isDelegationOperator((FirCallableDeclaration) firDeclaration)) {
                if (zIsEnabled2) {
                    str = "Context parameters on delegation operators are unsupported.";
                }
            } else if (!z || ((FirProperty) firDeclaration).getDelegate() == null) {
                if (!(firDeclaration instanceof FirNamedFunction) && !z && !(firDeclaration instanceof FirAnonymousFunction)) {
                    str = "Context parameters are unsupported in this position.";
                }
            } else if (zIsEnabled2) {
                str = "Context parameters on delegated properties are unsupported.";
            }
        }
        String str2 = str;
        if (str2 != null) {
            AbstractKtSourceElement abstractKtSourceElement2 = abstractKtSourceElement;
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement2, FirErrors.INSTANCE.getUNSUPPORTED(), str2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            abstractKtSourceElement = abstractKtSourceElement2;
        }
        List<FirValueParameter> contextParameters = getContextParameters(firDeclaration);
        if (contextParameters.isEmpty()) {
            return;
        }
        if (!zIsEnabled && !zIsEnabled2) {
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement, FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), TuplesKt.to(languageFeature, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (zIsEnabled) {
            List<FirValueParameter> list3 = contextParameters;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            Iterator<T> it = list3.iterator();
            while (it.hasNext()) {
                arrayList.add(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef()));
            }
            if (checkSubTypes(checkerContext, arrayList)) {
                KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement, FirErrors.INSTANCE.getSUBTYPING_BETWEEN_CONTEXT_RECEIVERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            for (FirValueParameter firValueParameter : contextParameters) {
                if (!FirValueParameterKindKt.isLegacyContextReceiver(firValueParameter)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(LanguageFeature.ContextParameters, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
        if (zIsEnabled2) {
            for (FirValueParameter firValueParameter2 : contextParameters) {
                if (FirValueParameterKindKt.isLegacyContextReceiver(firValueParameter2)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter2.getSource(), FirErrors.INSTANCE.getCONTEXT_PARAMETER_WITHOUT_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                KtSourceElement source3 = firValueParameter2.getSource();
                if (source3 != null && (modifierList = FirKeywordUtilsKt.getModifierList(source3)) != null && (modifiers = modifierList.getModifiers()) != null) {
                    Iterator<T> it2 = modifiers.iterator();
                    while (it2.hasNext()) {
                        FirModifier firModifier = (FirModifier) it2.next();
                        if (!ModifierCheckerHelpersKt.getAllowedInContextParameters().contains(firModifier.getToken())) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firModifier.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_MODIFIER_TARGET(), (Object) firModifier.getToken(), (Object) "context parameter", (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                        }
                    }
                }
                FirFunctionParameterChecker.INSTANCE.checkValOrVar$org_jetbrains_kotlin_checkers(diagnosticReporter, checkerContext, firValueParameter2);
            }
        }
    }

    public final boolean checkSubTypes(CheckerContext checkerContext, List<? extends ConeKotlinType> list) {
        Collection collectionListOf;
        checkerContext.getClass();
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (ConeKotlinType coneKotlinType : list) {
            if (coneKotlinType instanceof ConeTypeParameterType) {
                List<FirResolvedTypeRef> resolvedBounds = ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().getResolvedBounds();
                collectionListOf = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
                Iterator<T> it = resolvedBounds.iterator();
                while (it.hasNext()) {
                    collectionListOf.add(((FirResolvedTypeRef) it.next()).getConeType());
                }
            } else {
                collectionListOf = coneKotlinType instanceof ConeClassLikeType ? CollectionsKt.listOf(checkSubTypes$replaceTypeParametersByStarProjections((ConeClassLikeType) coneKotlinType)) : CollectionsKt.listOf(coneKotlinType);
            }
            CollectionsKt.addAll(arrayList, collectionListOf);
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            int size2 = arrayList.size();
            for (int i3 = i2; i3 < size2; i3++) {
                if (TypeUtilsKt.isSubtypeOf$default((ConeKotlinType) arrayList.get(i), (ConeKotlinType) arrayList.get(i3), checkerContext.getSession(), false, 4, null) || TypeUtilsKt.isSubtypeOf$default((ConeKotlinType) arrayList.get(i3), (ConeKotlinType) arrayList.get(i), checkerContext.getSession(), false, 4, null)) {
                    return true;
                }
            }
            i = i2;
        }
        return false;
    }
}
