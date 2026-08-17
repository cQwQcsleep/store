package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.StandardTypes;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aU\u0010\u0000\u001a\u00020\u00012\u001e\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\b0\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eR\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u000f\u001aE\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u001e\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\b0\u0007H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"checkInconsistentTypeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "firTypeRefClasses", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isValues", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/List;Lorg/jetbrains/kotlin/KtSourceElement;Z)V", "buildDeepSubstitutionMultimap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ClassSymbolAndProjections;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;)Ljava/util/Map;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInconsistentTypeParameterHelpersKt {
    private static final Map<FirTypeParameterSymbol, ClassSymbolAndProjections> buildDeepSubstitutionMultimap(CheckerContext checkerContext, List<? extends Pair<? extends FirTypeRef, ? extends FirClassSymbol<?>>> list) {
        ConeKotlinType coneType;
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        FirSession session = checkerContext.getSession();
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(session);
        FE10LikeConeSubstitutor fE10LikeConeSubstitutor = new FE10LikeConeSubstitutor(linkedHashMap2, session);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Pair<? extends FirTypeRef, ? extends FirClassSymbol<?>> pair : list) {
            FirTypeRef firTypeRef = (FirTypeRef) pair.component1();
            FirClassSymbol firClassSymbol = (FirClassSymbol) pair.component2();
            ConeTypeProjection[] typeArguments = (firTypeRef == null || (coneType = FirTypeUtilsKt.getConeType(firTypeRef)) == null || (coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneType)) == null) ? null : coneKotlinTypeFullyExpandedType.getTypeArguments();
            CheckerContext checkerContext2 = checkerContext;
            buildDeepSubstitutionMultimap$fillInDeepSubstitutor(checkerContext2, fE10LikeConeSubstitutor, linkedHashMap2, linkedHashMap, linkedHashSet, typeContext, typeArguments, firClassSymbol);
            checkerContext = checkerContext2;
        }
        return linkedHashMap;
    }

    private static final void buildDeepSubstitutionMultimap$fillInDeepSubstitutor(CheckerContext checkerContext, FE10LikeConeSubstitutor fE10LikeConeSubstitutor, Map<FirTypeParameterSymbol, ConeTypeProjection> map, Map<FirTypeParameterSymbol, ClassSymbolAndProjections> map2, Set<ConeKotlinType> set, ConeInferenceContext coneInferenceContext, ConeTypeProjection[] coneTypeProjectionArr, FirClassSymbol<?> firClassSymbol) {
        boolean zEqualTypes$default;
        Map<FirTypeParameterSymbol, ClassSymbolAndProjections> map3 = map2;
        if (coneTypeProjectionArr != null) {
            List<FirTypeParameterSymbol> typeParameterSymbols = firClassSymbol.getTypeParameterSymbols();
            int iMin = Math.min(coneTypeProjectionArr.length, typeParameterSymbols.size());
            for (int i = 0; i < iMin; i++) {
                ConeTypeProjection coneTypeProjection = coneTypeProjectionArr[i];
                ConeTypeProjection coneTypeProjectionSubstituteArgument = fE10LikeConeSubstitutor.substituteArgument(coneTypeProjection, i);
                if (coneTypeProjectionSubstituteArgument != null) {
                    coneTypeProjection = coneTypeProjectionSubstituteArgument;
                }
                FirTypeParameterSymbol firTypeParameterSymbol = typeParameterSymbols.get(i);
                map.put(firTypeParameterSymbol, coneTypeProjection);
                ClassSymbolAndProjections classSymbolAndProjections = map3.get(firTypeParameterSymbol);
                if (classSymbolAndProjections == null) {
                    classSymbolAndProjections = new ClassSymbolAndProjections(firClassSymbol, new ArrayList());
                    map3.put(firTypeParameterSymbol, classSymbolAndProjections);
                }
                List<ConeTypeProjection> projections = classSymbolAndProjections.getProjections();
                ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                List<ConeTypeProjection> list = projections;
                if ((list instanceof Collection) && list.isEmpty()) {
                    projections.add(coneTypeProjection);
                    break;
                    break;
                }
                Iterator<T> it = list.iterator();
                do {
                    if (!it.hasNext()) {
                        projections.add(coneTypeProjection);
                        break;
                    }
                    ConeTypeProjection coneTypeProjection2 = (ConeTypeProjection) it.next();
                    if (type == null || ConeTypeProjectionKt.getType(coneTypeProjection2) == null) {
                        zEqualTypes$default = coneTypeProjection2 == coneTypeProjection;
                    } else {
                        AbstractTypeChecker abstractTypeChecker = AbstractTypeChecker.INSTANCE;
                        ConeKotlinType type2 = ConeTypeProjectionKt.getType(coneTypeProjection2);
                        type2.getClass();
                        zEqualTypes$default = AbstractTypeChecker.equalTypes$default(abstractTypeChecker, coneInferenceContext, type2, type, false, false, 24, (Object) null);
                    }
                } while (!zEqualTypes$default);
            }
        }
        Iterator<FirResolvedTypeRef> it2 = firClassSymbol.getResolvedSuperTypeRefs().iterator();
        while (it2.hasNext()) {
            ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, it2.next().getConeType());
            if (set.add(coneKotlinTypeFullyExpandedType)) {
                FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneKotlinTypeFullyExpandedType);
                if (!ConeBuiltinTypeUtilsKt.isEnum(coneKotlinTypeFullyExpandedType) && regularClassSymbol != null) {
                    buildDeepSubstitutionMultimap$fillInDeepSubstitutor(checkerContext, fE10LikeConeSubstitutor, map, map3, set, coneInferenceContext, coneKotlinTypeFullyExpandedType.getTypeArguments(), regularClassSymbol);
                }
            }
            map3 = map2;
        }
    }

    public static final void checkInconsistentTypeParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List<? extends Pair<? extends FirTypeRef, ? extends FirClassSymbol<?>>> list, KtSourceElement ktSourceElement, boolean z) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        list.getClass();
        for (Map.Entry<FirTypeParameterSymbol, ClassSymbolAndProjections> entry : buildDeepSubstitutionMultimap(checkerContext, list).entrySet()) {
            FirTypeParameterSymbol key = entry.getKey();
            ClassSymbolAndProjections value = entry.getValue();
            List<ConeTypeProjection> projections = value.getProjections();
            if (projections.size() > 1) {
                FirErrors firErrors = FirErrors.INSTANCE;
                KtDiagnosticFactory3<FirTypeParameterSymbol, FirClassSymbol<?>, Collection<ConeKotlinType>> inconsistent_type_parameter_values = z ? firErrors.getINCONSISTENT_TYPE_PARAMETER_VALUES() : firErrors.getINCONSISTENT_TYPE_PARAMETER_BOUNDS();
                FirClassSymbol<?> classSymbol = value.getClassSymbol();
                List<ConeTypeProjection> list2 = projections;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    ConeKotlinType type = ConeTypeProjectionKt.getType((ConeTypeProjection) it.next());
                    if (type == null) {
                        type = StandardTypes.INSTANCE.getNullableAny();
                    }
                    arrayList.add(type);
                }
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory3<FirTypeParameterSymbol, FirClassSymbol<?>, ArrayList>) ((KtDiagnosticFactory3<Object, Object, Object>) inconsistent_type_parameter_values), key, classSymbol, arrayList, (64 & 64) != 0 ? null : null);
            }
        }
    }
}
