package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.mpp.ActualTypealiasToSpecialAnnotationUtils;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ)\u0010\u000e\u001a\u00020\u0007*\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u001e\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00102\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0002J5\u0010\u0014\u001a\u00020\u0007*\u00020\u00022\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0017J1\u0010\u0018\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001bJ1\u0010\u001c\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001b¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirActualTypeAliasChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeAliasChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)V", "checkDefaultArgumentsInExpectWithActualTypeAlias", "getMembersWithDefaultValueParametersUnlessAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "checkTypeAliasToClassWithDeclarationSiteVariance", "expandedTypeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)V", "checkTypeAliasWithUseSiteVariance", "expandedType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;)V", "checkTypeAliasWithComplexSubstitution", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirActualTypeAliasChecker extends FirDeclarationChecker<FirTypeAlias> {
    public static final FirActualTypeAliasChecker INSTANCE = new FirActualTypeAliasChecker();

    private FirActualTypeAliasChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkDefaultArgumentsInExpectWithActualTypeAlias(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeAlias firTypeAlias) {
        FirBasedSymbol<?> singleMatchedExpectForActualOrNull;
        LanguageVersionSettings languageVersionSettings = checkerContext.get$languageVersionSettings();
        LanguageFeature languageFeature = LanguageFeature.MultiplatformRestrictions;
        if (languageVersionSettings.supportsFeature(languageFeature) && languageVersionSettings.supportsFeature(languageFeature) && (singleMatchedExpectForActualOrNull = ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull(firTypeAlias.getSymbol())) != null) {
            FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) singleMatchedExpectForActualOrNull;
            List<FirFunctionSymbol<?>> membersWithDefaultValueParametersUnlessAnnotation = getMembersWithDefaultValueParametersUnlessAnnotation(firRegularClassSymbol);
            if (membersWithDefaultValueParametersUnlessAnnotation.isEmpty()) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeAlias.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getDEFAULT_ARGUMENTS_IN_EXPECT_WITH_ACTUAL_TYPEALIAS(), (Object) firRegularClassSymbol, (Object) membersWithDefaultValueParametersUnlessAnnotation, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    private final void checkTypeAliasToClassWithDeclarationSiteVariance(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeAlias firTypeAlias, FirClassLikeSymbol<?> firClassLikeSymbol) {
        Iterator<FirTypeParameterSymbol> it = firClassLikeSymbol.getTypeParameterSymbols().iterator();
        while (it.hasNext()) {
            if (it.next().getVariance() != Variance.INVARIANT) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeAlias.getSource(), FirErrors.INSTANCE.getACTUAL_TYPE_ALIAS_TO_CLASS_WITH_DECLARATION_SITE_VARIANCE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
        }
    }

    private final void checkTypeAliasWithComplexSubstitution(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeAlias firTypeAlias, ConeClassLikeType coneClassLikeType) {
        if (firTypeAlias.getTypeParameters().size() == coneClassLikeType.getTypeArguments().length) {
            int size = firTypeAlias.getTypeParameters().size();
            int i = 0;
            while (i < size) {
                ConeTypeParameterType coneTypeParameterType = coneClassLikeType.getTypeArguments()[i];
                if (coneTypeParameterType instanceof ConeTypeParameterType) {
                    if (!Intrinsics.areEqual(firTypeAlias.getTypeParameters().get(i).getSymbol(), coneTypeParameterType.getLookupTag().getTypeParameterSymbol())) {
                    }
                    i++;
                    checkerContext = checkerContext;
                    diagnosticReporter = diagnosticReporter;
                } else {
                    if (coneTypeParameterType instanceof ConeKotlinType) {
                        if (!(coneTypeParameterType.getTypeArguments().length == 0)) {
                        }
                    }
                    i++;
                    checkerContext = checkerContext;
                    diagnosticReporter = diagnosticReporter;
                }
            }
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeAlias.getSource(), FirErrors.INSTANCE.getACTUAL_TYPE_ALIAS_WITH_COMPLEX_SUBSTITUTION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private final void checkTypeAliasWithUseSiteVariance(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeAlias firTypeAlias, ConeClassLikeType coneClassLikeType) {
        for (ConeTypeProjection coneTypeProjection : coneClassLikeType.getTypeArguments()) {
            if (coneTypeProjection.getKind() != ProjectionKind.INVARIANT) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeAlias.getSource(), FirErrors.INSTANCE.getACTUAL_TYPE_ALIAS_WITH_USE_SITE_VARIANCE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
        }
    }

    private final List<FirFunctionSymbol<?>> getMembersWithDefaultValueParametersUnlessAnnotation(FirClassSymbol<?> classSymbol) {
        ArrayList arrayList = new ArrayList();
        getMembersWithDefaultValueParametersUnlessAnnotation$collectFunctions(arrayList, classSymbol);
        return arrayList;
    }

    private static final void getMembersWithDefaultValueParametersUnlessAnnotation$collectFunctions(List<FirFunctionSymbol<?>> list, FirClassSymbol<?> firClassSymbol) {
        if (firClassSymbol.getClassKind() == ClassKind.ANNOTATION_CLASS) {
            return;
        }
        FirContainingNamesAwareScope firContainingNamesAwareScopeDeclaredMemberScope = FirDeclaredMemberScopeProviderKt.declaredMemberScope(firClassSymbol, firClassSymbol.getModuleData().getSession(), (FirResolvePhase) null);
        List<FirFunctionSymbol<?>> list2 = list;
        for (Object obj : CollectionsKt.plus(FirContainingNamesAwareScopeKt.collectAllFunctions(firContainingNamesAwareScopeDeclaredMemberScope), FirScopeKt.getDeclaredConstructors(firContainingNamesAwareScopeDeclaredMemberScope))) {
            List<FirValueParameterSymbol> valueParameterSymbols = ((FirFunctionSymbol) obj).getValueParameterSymbols();
            if (!(valueParameterSymbols instanceof Collection) || !valueParameterSymbols.isEmpty()) {
                Iterator<T> it = valueParameterSymbols.iterator();
                while (it.hasNext()) {
                    if (((FirValueParameterSymbol) it.next()).getHasDefaultValue()) {
                        list2.add(obj);
                        break;
                    }
                }
            }
        }
        Set<Name> classifierNames = firContainingNamesAwareScopeDeclaredMemberScope.getClassifierNames();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = classifierNames.iterator();
        while (it2.hasNext()) {
            FirClassifierSymbol<?> singleClassifier = FirScopeKt.getSingleClassifier(firContainingNamesAwareScopeDeclaredMemberScope, (Name) it2.next());
            FirClassSymbol firClassSymbol2 = singleClassifier instanceof FirClassSymbol ? (FirClassSymbol) singleClassifier : null;
            if (firClassSymbol2 != null) {
                arrayList.add(firClassSymbol2);
            }
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            getMembersWithDefaultValueParametersUnlessAnnotation$collectFunctions(list, (FirClassSymbol) it3.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeAlias firTypeAlias) {
        FirClassLikeSymbol<?> symbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeAlias.getClass();
        if (firTypeAlias.getStatus().isActual()) {
            checkDefaultArgumentsInExpectWithActualTypeAlias(checkerContext, diagnosticReporter, firTypeAlias);
            ConeKotlinType abbreviatedTypeOrSelf = AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(FirTypeUtilsKt.getConeType(firTypeAlias.getExpandedTypeRef()));
            ConeClassLikeType coneClassLikeType = abbreviatedTypeOrSelf instanceof ConeClassLikeType ? (ConeClassLikeType) abbreviatedTypeOrSelf : null;
            if (coneClassLikeType == null || (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeType)) == null) {
                return;
            }
            if (symbol instanceof FirTypeAliasSymbol) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeAlias.getSource(), FirErrors.INSTANCE.getACTUAL_TYPE_ALIAS_NOT_TO_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            checkTypeAliasToClassWithDeclarationSiteVariance(checkerContext, diagnosticReporter, firTypeAlias, symbol);
            checkTypeAliasWithUseSiteVariance(checkerContext, diagnosticReporter, firTypeAlias, coneClassLikeType);
            checkTypeAliasWithComplexSubstitution(checkerContext, diagnosticReporter, firTypeAlias, coneClassLikeType);
            if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.MultiplatformRestrictions)) {
                if (ConeBuiltinTypeUtilsKt.isNothing(coneClassLikeType)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeAlias.getSource(), FirErrors.INSTANCE.getACTUAL_TYPE_ALIAS_TO_NOTHING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (coneClassLikeType.getIsMarkedNullable()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeAlias.getSource(), FirErrors.INSTANCE.getACTUAL_TYPE_ALIAS_TO_NULLABLE_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (FirHelpersKt.getClassKind(symbol) == ClassKind.ANNOTATION_CLASS) {
                    ClassId classId = symbol.getClassId();
                    if (ActualTypealiasToSpecialAnnotationUtils.INSTANCE.isAnnotationProhibitedInActualTypeAlias(classId)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeAlias.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getACTUAL_TYPEALIAS_TO_SPECIAL_ANNOTATION(), (Object) classId, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                }
            }
        }
    }
}
