package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0018B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0086\u0004J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0000H\u0086\u0002J%\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\b\b\u0000\u0010\u0014*\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u0001H\u0014¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", Argument.Delimiters.none, "left", Argument.Delimiters.none, "right", "<init>", "(Ljava/lang/String;III)V", "ZERO", "AT_MOST_ONCE", "EXACTLY_ONCE", "AT_LEAST_ONCE", "MORE_THAN_ONCE", "UNKNOWN", "or", "other", "plus", "contains", Argument.Delimiters.none, "at", "Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", "D", Argument.Delimiters.none, "marker", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/contracts/description/MarkedEventOccurrencesRange;", "Companion", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum EventOccurrencesRange {
    ZERO(0, 0),
    AT_MOST_ONCE(0, 1),
    EXACTLY_ONCE(1, 1),
    AT_LEAST_ONCE(1, 3),
    MORE_THAN_ONCE(2, 3),
    UNKNOWN(0, 3);

    private final int left;
    private final int right;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EventOccurrencesRange.values().length];
            try {
                iArr[EventOccurrencesRange.ZERO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EventOccurrencesRange.AT_MOST_ONCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EventOccurrencesRange.EXACTLY_ONCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EventOccurrencesRange.AT_LEAST_ONCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[EventOccurrencesRange.MORE_THAN_ONCE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[EventOccurrencesRange.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    EventOccurrencesRange(int i, int i2) {
        this.left = i;
        this.right = i2;
    }

    public static EnumEntries<EventOccurrencesRange> getEntries() {
        return $ENTRIES;
    }

    public final <D> MarkedEventOccurrencesRange<D> at(D marker) {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return MarkedEventOccurrencesRange.Zero.INSTANCE;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                if (marker != null) {
                    return new MarkedEventOccurrencesRange.AtMostOnce(marker);
                }
                x01.a("AT_MOST_ONCE event requires location");
                return null;
            case 3:
                if (marker != null) {
                    return new MarkedEventOccurrencesRange.ExactlyOnce(marker);
                }
                x01.a("EXACTLY_ONCE event requires location");
                return null;
            case 4:
                return MarkedEventOccurrencesRange.AtLeastOnce.INSTANCE;
            case 5:
                return MarkedEventOccurrencesRange.MoreThanOnce.INSTANCE;
            case 6:
                return MarkedEventOccurrencesRange.Unknown.INSTANCE;
            default:
                bu8.a();
                return null;
        }
    }

    public final boolean contains(EventOccurrencesRange other) {
        other.getClass();
        return this.left <= other.left && other.right <= this.right;
    }

    public final EventOccurrencesRange or(EventOccurrencesRange other) {
        other.getClass();
        return INSTANCE.or(this, other);
    }

    public final EventOccurrencesRange plus(EventOccurrencesRange other) {
        other.getClass();
        return INSTANCE.plus(this, other);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0016\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u0016\u0010\f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange$Companion;", Argument.Delimiters.none, "<init>", "()V", "fromRange", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "left", Argument.Delimiters.none, "right", "or", "x", "y", "plus", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final EventOccurrencesRange fromRange(int left, int right) {
            Pair pair = TuplesKt.to(Integer.valueOf(Math.min(left, 2)), Integer.valueOf(Math.min(right, 3)));
            if (Intrinsics.areEqual(pair, TuplesKt.to(0, 0))) {
                return EventOccurrencesRange.ZERO;
            }
            if (Intrinsics.areEqual(pair, TuplesKt.to(0, 1))) {
                return EventOccurrencesRange.AT_MOST_ONCE;
            }
            if (!Intrinsics.areEqual(pair, TuplesKt.to(0, 2)) && !Intrinsics.areEqual(pair, TuplesKt.to(0, 3))) {
                if (Intrinsics.areEqual(pair, TuplesKt.to(1, 1))) {
                    return EventOccurrencesRange.EXACTLY_ONCE;
                }
                if (!Intrinsics.areEqual(pair, TuplesKt.to(1, 2)) && !Intrinsics.areEqual(pair, TuplesKt.to(1, 3))) {
                    if (!Intrinsics.areEqual(pair, TuplesKt.to(2, 2)) && !Intrinsics.areEqual(pair, TuplesKt.to(2, 3)) && !Intrinsics.areEqual(pair, TuplesKt.to(3, 3))) {
                        j2d.a();
                        return null;
                    }
                    return EventOccurrencesRange.MORE_THAN_ONCE;
                }
                return EventOccurrencesRange.AT_LEAST_ONCE;
            }
            return EventOccurrencesRange.UNKNOWN;
        }

        public final EventOccurrencesRange or(EventOccurrencesRange x, EventOccurrencesRange y) {
            x.getClass();
            y.getClass();
            return fromRange(Math.min(x.left, y.left), Math.max(x.right, y.right));
        }

        public final EventOccurrencesRange plus(EventOccurrencesRange x, EventOccurrencesRange y) {
            x.getClass();
            y.getClass();
            return fromRange(x.left + y.left, x.right + y.right);
        }

        private Companion() {
        }
    }
}
