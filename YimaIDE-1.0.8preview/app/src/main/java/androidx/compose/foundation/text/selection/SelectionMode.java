package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH ¢\u0006\u0004\b\f\u0010\rJ'\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\u000f*\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0016\u0010\u0017j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionMode;", "", "<init>", "(Ljava/lang/String;I)V", "Vertical", "Horizontal", "compare", "", "position", "Landroidx/compose/ui/geometry/Offset;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "compare-3MmeM6k$foundation", "(JLandroidx/compose/ui/geometry/Rect;)I", "isSelected", "", "start", "end", "isSelected-2x9bVx0$foundation", "(Landroidx/compose/ui/geometry/Rect;JJ)Z", "containsInclusive", "offset", "containsInclusive-Uv8p0NA", "(Landroidx/compose/ui/geometry/Rect;J)Z", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public enum SelectionMode {
    Vertical { // from class: androidx.compose.foundation.text.selection.SelectionMode.Vertical
        @Override // androidx.compose.foundation.text.selection.SelectionMode
        /* JADX INFO: renamed from: compare-3MmeM6k$foundation */
        public int mo1797compare3MmeM6k$foundation(long position, Rect bounds) {
            if (SelectionManagerKt.m1793containsInclusiveUv8p0NA(bounds, position)) {
                return 0;
            }
            int i = (int) (4294967295L & position);
            if (Float.intBitsToFloat(i) < bounds.getTop()) {
                return -1;
            }
            return (Float.intBitsToFloat((int) (position >> 32)) >= bounds.getLeft() || Float.intBitsToFloat(i) >= bounds.getBottom()) ? 1 : -1;
        }
    },
    Horizontal { // from class: androidx.compose.foundation.text.selection.SelectionMode.Horizontal
        @Override // androidx.compose.foundation.text.selection.SelectionMode
        /* JADX INFO: renamed from: compare-3MmeM6k$foundation */
        public int mo1797compare3MmeM6k$foundation(long position, Rect bounds) {
            if (SelectionManagerKt.m1793containsInclusiveUv8p0NA(bounds, position)) {
                return 0;
            }
            int i = (int) (position >> 32);
            if (Float.intBitsToFloat(i) < bounds.getLeft()) {
                return -1;
            }
            return (Float.intBitsToFloat((int) (position & 4294967295L)) >= bounds.getTop() || Float.intBitsToFloat(i) >= bounds.getRight()) ? 1 : -1;
        }
    };

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* synthetic */ SelectionMode(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: renamed from: containsInclusive-Uv8p0NA, reason: not valid java name */
    private final boolean m1796containsInclusiveUv8p0NA(Rect rect, long j) {
        float left = rect.getLeft();
        float right = rect.getRight();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        if (left > fIntBitsToFloat || fIntBitsToFloat > right) {
            return false;
        }
        float top = rect.getTop();
        float bottom = rect.getBottom();
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
        return top <= fIntBitsToFloat2 && fIntBitsToFloat2 <= bottom;
    }

    public static EnumEntries<SelectionMode> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: compare-3MmeM6k$foundation, reason: not valid java name */
    public abstract int mo1797compare3MmeM6k$foundation(long position, Rect bounds);

    /* JADX INFO: renamed from: isSelected-2x9bVx0$foundation, reason: not valid java name */
    public final boolean m1798isSelected2x9bVx0$foundation(Rect bounds, long start, long end) {
        if (m1796containsInclusiveUv8p0NA(bounds, start) || m1796containsInclusiveUv8p0NA(bounds, end)) {
            return true;
        }
        return (mo1797compare3MmeM6k$foundation(start, bounds) > 0) ^ (mo1797compare3MmeM6k$foundation(end, bounds) > 0);
    }
}
