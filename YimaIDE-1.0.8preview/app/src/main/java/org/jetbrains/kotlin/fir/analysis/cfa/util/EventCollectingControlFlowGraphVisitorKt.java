package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.Map;
import kotlin.Metadata;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\u001a\u008d\u0001\u0010\b\u001a4\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00070\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\t\"\b\b\u0000\u0010\u0001*\u00020\n*4\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00070\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\t2\u0006\u0010\u000b\u001a\u0002H\u00012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\r\u001a\u008d\u0001\u0010\u000e\u001a4\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00070\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\t\"\b\b\u0000\u0010\u0001*\u00020\n*4\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00070\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\t2\u0006\u0010\u000b\u001a\u0002H\u00012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\r\u001a\u008d\u0001\u0010\u000f\u001a4\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00070\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\t\"\b\b\u0000\u0010\u0001*\u00020\n*4\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00070\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\t2\u0006\u0010\u000b\u001a\u0002H\u00012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\r\u001a\u0085\u0001\u0010\u0010\u001a4\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00070\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\t\"\b\b\u0000\u0010\u0001*\u00020\n*4\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00070\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\t2\u0006\u0010\u000b\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0011*(\u0010\u0000\u001a\u0004\b\u0000\u0010\u0001\"\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002*T\u0010\u0004\u001a\u0004\b\u0000\u0010\u0001\"\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00052:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u00070\u0002j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003`\u0005¨\u0006\u0012"}, d2 = {"EventOccurrencesRangeInfo", "K", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "PathAwareEventOccurrencesRangeInfo", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "addRange", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareEventOccurrencesRangeInfo;", Argument.Delimiters.none, "key", "range", "(Lkotlinx/collections/immutable/PersistentMap;Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;)Lkotlinx/collections/immutable/PersistentMap;", "addRangeIfEmpty", "overwriteRange", "removeRange", "(Lkotlinx/collections/immutable/PersistentMap;Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentMap;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EventCollectingControlFlowGraphVisitorKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <K> PersistentMap<EdgeLabel, PersistentMap<K, EventOccurrencesRangeAtNode>> addRange(PersistentMap<EdgeLabel, ? extends PersistentMap<K, EventOccurrencesRangeAtNode>> persistentMap, K k, EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode) {
        persistentMap.getClass();
        k.getClass();
        eventOccurrencesRangeAtNode.getClass();
        if (!EventOccurrencesRangeKt.canBeVisited(eventOccurrencesRangeAtNode.getRange()) && !eventOccurrencesRangeAtNode.getMustBeLateinit()) {
            return persistentMap;
        }
        PersistentMap.Builder builder = persistentMap.builder();
        for (Map.Entry entry : persistentMap.entrySet()) {
            Object key = entry.getKey();
            PersistentMap persistentMap2 = (PersistentMap) entry.getValue();
            EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode2 = (EventOccurrencesRangeAtNode) persistentMap2.get(k);
            builder.put(key, eventOccurrencesRangeAtNode2 == null ? persistentMap2.put(k, eventOccurrencesRangeAtNode) : persistentMap2.put(k, new EventOccurrencesRangeAtNode(!EventOccurrencesRangeKt.canBeVisited(eventOccurrencesRangeAtNode.getRange()) ? eventOccurrencesRangeAtNode2.getRange() : eventOccurrencesRangeAtNode2.getRange().getWithoutMarker().plus(eventOccurrencesRangeAtNode.getRange().getWithoutMarker()).at(eventOccurrencesRangeAtNode.getRange().getLocation()), eventOccurrencesRangeAtNode2.getMustBeLateinit() || eventOccurrencesRangeAtNode.getMustBeLateinit())));
        }
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K> PersistentMap<EdgeLabel, PersistentMap<K, EventOccurrencesRangeAtNode>> addRangeIfEmpty(PersistentMap<EdgeLabel, ? extends PersistentMap<K, EventOccurrencesRangeAtNode>> persistentMap, K k, EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode) {
        persistentMap.getClass();
        k.getClass();
        eventOccurrencesRangeAtNode.getClass();
        if (!EventOccurrencesRangeKt.canBeVisited(eventOccurrencesRangeAtNode.getRange()) && !eventOccurrencesRangeAtNode.getMustBeLateinit()) {
            return persistentMap;
        }
        PersistentMap.Builder builder = persistentMap.builder();
        for (Map.Entry entry : persistentMap.entrySet()) {
            Object key = entry.getKey();
            PersistentMap persistentMapPut = (PersistentMap) entry.getValue();
            if (!persistentMapPut.containsKey(k)) {
                persistentMapPut = persistentMapPut.put(k, eventOccurrencesRangeAtNode);
            }
            builder.put(key, persistentMapPut);
        }
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K> PersistentMap<EdgeLabel, PersistentMap<K, EventOccurrencesRangeAtNode>> overwriteRange(PersistentMap<EdgeLabel, ? extends PersistentMap<K, EventOccurrencesRangeAtNode>> persistentMap, K k, EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode) {
        persistentMap.getClass();
        k.getClass();
        eventOccurrencesRangeAtNode.getClass();
        PersistentMap.Builder builder = persistentMap.builder();
        for (Map.Entry entry : persistentMap.entrySet()) {
            builder.put(entry.getKey(), ((PersistentMap) entry.getValue()).put(k, eventOccurrencesRangeAtNode));
        }
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K> PersistentMap<EdgeLabel, PersistentMap<K, EventOccurrencesRangeAtNode>> removeRange(PersistentMap<EdgeLabel, ? extends PersistentMap<K, EventOccurrencesRangeAtNode>> persistentMap, K k) {
        persistentMap.getClass();
        k.getClass();
        PersistentMap.Builder builder = persistentMap.builder();
        for (Map.Entry entry : persistentMap.entrySet()) {
            builder.put(entry.getKey(), ((PersistentMap) entry.getValue()).remove(k));
        }
        return builder.build();
    }
}
