package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00040\u0003B\u0007¢\u0006\u0004\b\u0005\u0010\u0006Jf\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\bj\b\u0012\u0004\u0012\u00028\u0000`\t2\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\bj\b\u0012\u0004\u0012\u00028\u0000`\t2\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\bj\b\u0012\u0004\u0012\u00028\u0000`\t2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventCollectingControlFlowGraphVisitor;", "K", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowGraphVisitor;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "<init>", "()V", "mergeInfo", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeInfo;", "a", "b", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class EventCollectingControlFlowGraphVisitor<K> extends PathAwareControlFlowGraphVisitor<K, EventOccurrencesRangeAtNode> {
    public EventCollectingControlFlowGraphVisitor() {
        super(null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.PathAwareControlFlowGraphVisitor
    public PersistentMap<K, EventOccurrencesRangeAtNode> mergeInfo(PersistentMap<K, EventOccurrencesRangeAtNode> a, PersistentMap<K, EventOccurrencesRangeAtNode> b, CFGNode<?> node) {
        MarkedEventOccurrencesRange<CFGNode<?>> markedEventOccurrencesRangeAt;
        MarkedEventOccurrencesRange<CFGNode<?>> range;
        CFGNode<?> location;
        a.getClass();
        b.getClass();
        node.getClass();
        boolean zIsUnion = node.isUnion();
        if (zIsUnion && a.isEmpty()) {
            return b;
        }
        Set<K> setKeySet = zIsUnion ? b.keySet() : CollectionsKt.union(a.keySet(), b.keySet());
        PersistentMap.Builder builder = a.builder();
        for (Object obj : setKeySet) {
            EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode = (EventOccurrencesRangeAtNode) a.get(obj);
            if (eventOccurrencesRangeAtNode == null || (markedEventOccurrencesRangeAt = eventOccurrencesRangeAtNode.getRange()) == null) {
                markedEventOccurrencesRangeAt = MarkedEventOccurrencesRange.Zero.INSTANCE;
            }
            EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode2 = (EventOccurrencesRangeAtNode) b.get(obj);
            if (eventOccurrencesRangeAtNode2 == null || (range = eventOccurrencesRangeAtNode2.getRange()) == null) {
                range = MarkedEventOccurrencesRange.Zero.INSTANCE;
            }
            if (markedEventOccurrencesRangeAt.getLocation() == null || !Intrinsics.areEqual(markedEventOccurrencesRangeAt, range)) {
                if (zIsUnion) {
                    MarkedEventOccurrencesRange.Zero zero = MarkedEventOccurrencesRange.Zero.INSTANCE;
                    if (Intrinsics.areEqual(markedEventOccurrencesRangeAt, zero)) {
                        markedEventOccurrencesRangeAt = range;
                    } else if (!Intrinsics.areEqual(range, zero)) {
                        markedEventOccurrencesRangeAt = markedEventOccurrencesRangeAt.getWithoutMarker().plus(range.getWithoutMarker()).at(null);
                    }
                } else {
                    MarkedEventOccurrencesRange.Zero zero2 = MarkedEventOccurrencesRange.Zero.INSTANCE;
                    if (Intrinsics.areEqual(markedEventOccurrencesRangeAt, zero2)) {
                        location = range.getLocation();
                    } else if (Intrinsics.areEqual(range, zero2)) {
                        location = markedEventOccurrencesRangeAt.getLocation();
                    } else {
                        CFGNode<?> location2 = markedEventOccurrencesRangeAt.getLocation();
                        location = Intrinsics.areEqual(location2, range.getLocation()) ? location2 : null;
                        if (location == null) {
                            location = node;
                        }
                    }
                    markedEventOccurrencesRangeAt = markedEventOccurrencesRangeAt.getWithoutMarker().or(range.getWithoutMarker()).at(location);
                }
            }
            EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode3 = (EventOccurrencesRangeAtNode) a.get(obj);
            boolean z = false;
            boolean z2 = eventOccurrencesRangeAtNode3 != null && eventOccurrencesRangeAtNode3.getMustBeLateinit();
            EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode4 = (EventOccurrencesRangeAtNode) b.get(obj);
            boolean z3 = eventOccurrencesRangeAtNode4 != null && eventOccurrencesRangeAtNode4.getMustBeLateinit();
            if (z2 || z3) {
                z = true;
            }
            builder.put(obj, new EventOccurrencesRangeAtNode(markedEventOccurrencesRangeAt, z));
        }
        return builder.build();
    }
}
