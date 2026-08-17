package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toAnnotationArgumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "Lorg/jetbrains/kotlin/fir/expressions/impl/FirResolvedArgumentList;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationArgumentMappingImplKt {
    public static final FirAnnotationArgumentMapping toAnnotationArgumentMapping(FirResolvedArgumentList firResolvedArgumentList) {
        firResolvedArgumentList.getClass();
        Set<Map.Entry<FirExpression, FirValueParameter>> setEntrySet = firResolvedArgumentList.getMapping().entrySet();
        setEntrySet.getClass();
        Set<Map.Entry<FirExpression, FirValueParameter>> set = setEntrySet;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set, 10)), 16));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Name name = ((FirValueParameter) entry.getValue()).getName();
            Object key = entry.getKey();
            key.getClass();
            linkedHashMap.put(name, FirExpressionUtilKt.unwrapArgument((FirExpression) key));
        }
        return new FirAnnotationArgumentMappingImpl(null, linkedHashMap);
    }
}
