package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0005\u001a\u000e\u0010\u0003\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0005\u001a\u000e\u0010\u0004\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0005\"\u0017\u0010\u0006\u001a\u00020\u0001*\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"isDefinitelyVisited", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "canBeVisited", "canBeRevisited", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", "isInPlace", "(Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;)Z", "org.jetbrains.kotlin:compiler.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EventOccurrencesRangeKt {
    public static final boolean canBeRevisited(EventOccurrencesRange eventOccurrencesRange) {
        eventOccurrencesRange.getClass();
        return eventOccurrencesRange == EventOccurrencesRange.UNKNOWN || eventOccurrencesRange == EventOccurrencesRange.AT_LEAST_ONCE || eventOccurrencesRange == EventOccurrencesRange.MORE_THAN_ONCE;
    }

    public static final boolean canBeVisited(MarkedEventOccurrencesRange<?> markedEventOccurrencesRange) {
        markedEventOccurrencesRange.getClass();
        return canBeVisited(markedEventOccurrencesRange.getWithoutMarker());
    }

    public static final boolean isDefinitelyVisited(EventOccurrencesRange eventOccurrencesRange) {
        eventOccurrencesRange.getClass();
        return eventOccurrencesRange == EventOccurrencesRange.EXACTLY_ONCE || eventOccurrencesRange == EventOccurrencesRange.AT_LEAST_ONCE || eventOccurrencesRange == EventOccurrencesRange.MORE_THAN_ONCE;
    }

    public static final boolean isInPlace(EventOccurrencesRange eventOccurrencesRange) {
        return eventOccurrencesRange != null;
    }

    public static final boolean canBeVisited(EventOccurrencesRange eventOccurrencesRange) {
        eventOccurrencesRange.getClass();
        return eventOccurrencesRange != EventOccurrencesRange.ZERO;
    }

    public static final boolean canBeRevisited(MarkedEventOccurrencesRange<?> markedEventOccurrencesRange) {
        markedEventOccurrencesRange.getClass();
        return canBeRevisited(markedEventOccurrencesRange.getWithoutMarker());
    }

    public static final boolean isDefinitelyVisited(MarkedEventOccurrencesRange<?> markedEventOccurrencesRange) {
        markedEventOccurrencesRange.getClass();
        return isDefinitelyVisited(markedEventOccurrencesRange.getWithoutMarker());
    }
}
