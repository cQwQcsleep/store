package org.jetbrains.kotlin.fir.analysis.cfa.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u001b\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", Argument.Delimiters.none, "range", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "mustBeLateinit", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;Z)V", "getRange", "()Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", "getMustBeLateinit", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class EventOccurrencesRangeAtNode {
    private final boolean mustBeLateinit;
    private final MarkedEventOccurrencesRange<CFGNode<?>> range;

    /* JADX WARN: Multi-variable type inference failed */
    public EventOccurrencesRangeAtNode(MarkedEventOccurrencesRange<? extends CFGNode<?>> markedEventOccurrencesRange, boolean z) {
        markedEventOccurrencesRange.getClass();
        this.range = markedEventOccurrencesRange;
        this.mustBeLateinit = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EventOccurrencesRangeAtNode copy$default(EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode, MarkedEventOccurrencesRange markedEventOccurrencesRange, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            markedEventOccurrencesRange = eventOccurrencesRangeAtNode.range;
        }
        if ((i & 2) != 0) {
            z = eventOccurrencesRangeAtNode.mustBeLateinit;
        }
        return eventOccurrencesRangeAtNode.copy(markedEventOccurrencesRange, z);
    }

    public final MarkedEventOccurrencesRange<CFGNode<?>> component1() {
        return this.range;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getMustBeLateinit() {
        return this.mustBeLateinit;
    }

    public final EventOccurrencesRangeAtNode copy(MarkedEventOccurrencesRange<? extends CFGNode<?>> range, boolean mustBeLateinit) {
        range.getClass();
        return new EventOccurrencesRangeAtNode(range, mustBeLateinit);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventOccurrencesRangeAtNode)) {
            return false;
        }
        EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode = (EventOccurrencesRangeAtNode) other;
        return Intrinsics.areEqual(this.range, eventOccurrencesRangeAtNode.range) && this.mustBeLateinit == eventOccurrencesRangeAtNode.mustBeLateinit;
    }

    public final boolean getMustBeLateinit() {
        return this.mustBeLateinit;
    }

    public final MarkedEventOccurrencesRange<CFGNode<?>> getRange() {
        return this.range;
    }

    public int hashCode() {
        return (this.range.hashCode() * 31) + Boolean.hashCode(this.mustBeLateinit);
    }

    public String toString() {
        return "EventOccurrencesRangeAtNode(range=" + this.range + ", mustBeLateinit=" + this.mustBeLateinit + ')';
    }
}
