package androidx.core.util;

import android.util.Range;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ranges.ClosedRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\u0002H\u00022\u0006\u0010\u0004\u001a\u0002H\u0002H\u0086\f¢\u0006\u0002\u0010\u0005\u001a6\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0007\u001a\u0002H\u0002H\u0086\n¢\u0006\u0002\u0010\b\u001a7\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0086\n\u001a7\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0086\f\u001a&\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001a&\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\f¨\u0006\u000e"}, d2 = {"rangeTo", "Landroid/util/Range;", "T", "", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Landroid/util/Range;", "plus", "value", "(Landroid/util/Range;Ljava/lang/Comparable;)Landroid/util/Range;", "other", "and", "toClosedRange", "Lkotlin/ranges/ClosedRange;", "toRange", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RangeKt {
    public static final <T extends Comparable<? super T>> Range<T> and(Range<T> range, Range<T> range2) {
        range.getClass();
        range2.getClass();
        Range<T> rangeIntersect = range.intersect(range2);
        rangeIntersect.getClass();
        return rangeIntersect;
    }

    public static final <T extends Comparable<? super T>> Range<T> plus(Range<T> range, T t) {
        range.getClass();
        t.getClass();
        Range<T> rangeExtend = range.extend(t);
        rangeExtend.getClass();
        return rangeExtend;
    }

    public static final <T extends Comparable<? super T>> Range<T> rangeTo(T t, T t2) {
        t.getClass();
        t2.getClass();
        return new Range<>(t, t2);
    }

    public static final <T extends Comparable<? super T>> ClosedRange<T> toClosedRange(final Range<T> range) {
        range.getClass();
        return (ClosedRange<T>) new ClosedRange<T>() { // from class: androidx.core.util.RangeKt.toClosedRange.1
            /* JADX WARN: Incorrect types in method signature: (TT;)Z */
            public /* bridge */ boolean contains(Comparable comparable) {
                return ClosedRange.DefaultImpls.contains(this, comparable);
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            public Comparable getEndInclusive() {
                return range.getUpper();
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            public Comparable getStart() {
                return range.getLower();
            }

            public /* bridge */ boolean isEmpty() {
                return ClosedRange.DefaultImpls.isEmpty(this);
            }
        };
    }

    public static final <T extends Comparable<? super T>> Range<T> toRange(ClosedRange<T> closedRange) {
        closedRange.getClass();
        return new Range<>(closedRange.getStart(), closedRange.getEndInclusive());
    }

    public static final <T extends Comparable<? super T>> Range<T> plus(Range<T> range, Range<T> range2) {
        range.getClass();
        range2.getClass();
        Range<T> rangeExtend = range.extend(range2);
        rangeExtend.getClass();
        return rangeExtend;
    }
}
