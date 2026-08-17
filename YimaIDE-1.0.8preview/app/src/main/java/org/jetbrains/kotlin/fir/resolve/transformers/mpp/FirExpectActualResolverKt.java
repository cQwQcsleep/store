package org.jetbrains.kotlin.fir.resolve.transformers.mpp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\f\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"filterContainedInTheFirstWaveOfDependsOnDominatorTree", Argument.Delimiters.none, "T", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", Argument.Delimiters.none, "graphStartingNode", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpectActualResolverKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends FirBasedSymbol<?>> List<T> filterContainedInTheFirstWaveOfDependsOnDominatorTree(Iterable<? extends T> iterable, FirModuleData firModuleData) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t : iterable) {
            FirModuleData moduleData = t.getModuleData();
            Object arrayList = linkedHashMap.get(moduleData);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(moduleData, arrayList);
            }
            ((List) arrayList).add(t);
        }
        ArrayList arrayList2 = new ArrayList(1);
        filterContainedInTheFirstWaveOfDependsOnDominatorTree$dfs(new HashSet(), linkedHashMap, arrayList2, firModuleData);
        return arrayList2;
    }

    private static final <T extends FirBasedSymbol<?>> void filterContainedInTheFirstWaveOfDependsOnDominatorTree$dfs(Set<FirModuleData> set, Map<FirModuleData, ? extends List<? extends T>> map, List<T> list, FirModuleData firModuleData) {
        if (set.add(firModuleData)) {
            List<? extends T> list2 = map.get(firModuleData);
            if (list2 != null) {
                list.addAll(list2);
                return;
            }
            Iterator<FirModuleData> it = firModuleData.getDependsOnDependencies().iterator();
            while (it.hasNext()) {
                filterContainedInTheFirstWaveOfDependsOnDominatorTree$dfs(set, map, list, it.next());
            }
        }
    }
}
