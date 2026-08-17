package org.jetbrains.kotlin.fir.types;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutorKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u00020\u00012\u0018\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0001¨\u0006\t"}, d2 = {"createExpectActualTypeParameterSubstitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "expectActualTypeParameters", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "parentSubstitutor", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExpectActualUtilsKt {
    public static final ConeSubstitutor createExpectActualTypeParameterSubstitutor(List<Pair<FirTypeParameterSymbol, FirTypeParameterSymbol>> list, FirSession firSession, ConeSubstitutor coneSubstitutor) {
        list.getClass();
        firSession.getClass();
        List<Pair<FirTypeParameterSymbol, FirTypeParameterSymbol>> list2 = list;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            Pair pair2 = TuplesKt.to((FirTypeParameterSymbol) pair.component1(), TypeConstructionUtilsKt.constructType$default((ConeClassifierLookupTag) ((FirTypeParameterSymbol) pair.component2()).getLookupTag(), (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null));
            linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
        }
        ConeSubstitutor coneSubstitutorSubstitutorByMap$default = ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, firSession, false, 4, null);
        return coneSubstitutor == null ? coneSubstitutorSubstitutorByMap$default : ChainedSubstitutorKt.chain(coneSubstitutorSubstitutorByMap$default, coneSubstitutor);
    }

    public static /* synthetic */ ConeSubstitutor createExpectActualTypeParameterSubstitutor$default(List list, FirSession firSession, ConeSubstitutor coneSubstitutor, int i, Object obj) {
        if ((i & 4) != 0) {
            coneSubstitutor = null;
        }
        return createExpectActualTypeParameterSubstitutor(list, firSession, coneSubstitutor);
    }
}
