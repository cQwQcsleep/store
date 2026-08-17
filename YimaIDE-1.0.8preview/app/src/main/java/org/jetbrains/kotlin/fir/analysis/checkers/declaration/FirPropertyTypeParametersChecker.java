package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyTypeParametersChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPropertyTypeParametersChecker extends FirDeclarationChecker<FirProperty> {
    public static final FirPropertyTypeParametersChecker INSTANCE = new FirPropertyTypeParametersChecker();

    private FirPropertyTypeParametersChecker() {
        super(MppCheckerKind.Common);
    }

    private static final void check$collectAllTypes(Set<ConeKotlinType> set, Map<Name, ? extends List<? extends FirResolvedTypeRef>> map, ConeKotlinType coneKotlinType) {
        List<? extends FirResolvedTypeRef> list;
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneKotlinType);
        if (set.add(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound)) {
            for (ConeTypeProjection coneTypeProjection : coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound.getTypeArguments()) {
                ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                if (type != null) {
                    check$collectAllTypes(set, map, type);
                }
            }
            if (!(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeTypeParameterType) || (list = map.get(((ConeTypeParameterType) coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound).getLookupTag().getName())) == null) {
                return;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                check$collectAllTypes(set, map, ((FirResolvedTypeRef) it.next()).getConeType());
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        FirTypeRef typeRef;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        if (firProperty.getSymbol() instanceof FirLocalPropertySymbol) {
            return;
        }
        List<FirTypeParameter> typeParameters = firProperty.getTypeParameters();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(typeParameters, 10)), 16));
        for (FirTypeParameter firTypeParameter : typeParameters) {
            Pair pair = TuplesKt.to(firTypeParameter.getName(), firTypeParameter.getSymbol().getResolvedBounds());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        FirReceiverParameter receiverParameter = firProperty.getReceiverParameter();
        if (receiverParameter != null && (typeRef = receiverParameter.getTypeRef()) != null) {
            check$collectAllTypes(linkedHashSet, linkedHashMap, FirTypeUtilsKt.getConeType(typeRef));
        }
        Iterator<T> it = firProperty.getContextParameters().iterator();
        while (it.hasNext()) {
            check$collectAllTypes(linkedHashSet, linkedHashMap, FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef()));
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : linkedHashSet) {
            if (obj instanceof ConeTypeParameterType) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((ConeTypeParameterType) it2.next()).getLookupTag().getName());
        }
        List<FirTypeParameter> typeParameters2 = firProperty.getTypeParameters();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : typeParameters2) {
            if (!arrayList2.contains(((FirTypeParameter) obj2).getName())) {
                arrayList3.add(obj2);
            }
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirTypeParameter) it3.next()).getSource(), FirErrors.INSTANCE.getINCORRECT_TYPE_PARAMETER_OF_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
