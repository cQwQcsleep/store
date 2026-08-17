package androidx.compose.material3;

import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarMonth;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0001\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0012"}, d2 = {"Landroidx/compose/material3/SelectedRangeInfo;", "", "gridStartCoordinates", "Landroidx/compose/ui/unit/IntOffset;", "gridEndCoordinates", "firstIsSelectionStart", "", "lastIsSelectionEnd", "<init>", "(JJZZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getGridStartCoordinates-nOcc-ac", "()J", "J", "getGridEndCoordinates-nOcc-ac", "getFirstIsSelectionStart", "()Z", "getLastIsSelectionEnd", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SelectedRangeInfo {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean firstIsSelectionStart;
    private final long gridEndCoordinates;
    private final long gridStartCoordinates;
    private final boolean lastIsSelectionEnd;

    private SelectedRangeInfo(long j, long j2, boolean z, boolean z2) {
        this.gridStartCoordinates = j;
        this.gridEndCoordinates = j2;
        this.firstIsSelectionStart = z;
        this.lastIsSelectionEnd = z2;
    }

    public final boolean getFirstIsSelectionStart() {
        return this.firstIsSelectionStart;
    }

    /* JADX INFO: renamed from: getGridEndCoordinates-nOcc-ac, reason: not valid java name and from getter */
    public final long getGridEndCoordinates() {
        return this.gridEndCoordinates;
    }

    /* JADX INFO: renamed from: getGridStartCoordinates-nOcc-ac, reason: not valid java name and from getter */
    public final long getGridStartCoordinates() {
        return this.gridStartCoordinates;
    }

    public final boolean getLastIsSelectionEnd() {
        return this.lastIsSelectionEnd;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t¨\u0006\u000b"}, d2 = {"Landroidx/compose/material3/SelectedRangeInfo$Companion;", "", "<init>", "()V", "calculateRangeInfo", "Landroidx/compose/material3/SelectedRangeInfo;", "month", "Landroidx/compose/material3/internal/CalendarMonth;", "startDate", "Landroidx/compose/material3/internal/CalendarDate;", "endDate", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SelectedRangeInfo calculateRangeInfo(CalendarMonth month, CalendarDate startDate, CalendarDate endDate) {
            if (startDate.getUtcTimeMillis() > month.getEndUtcTimeMillis() || endDate.getUtcTimeMillis() < month.getStartUtcTimeMillis()) {
                return null;
            }
            boolean z = startDate.getUtcTimeMillis() >= month.getStartUtcTimeMillis();
            boolean z2 = endDate.getUtcTimeMillis() <= month.getEndUtcTimeMillis();
            int daysFromStartOfWeekToFirstOfMonth = z ? (month.getDaysFromStartOfWeekToFirstOfMonth() + startDate.getDayOfMonth()) - 1 : month.getDaysFromStartOfWeekToFirstOfMonth();
            int daysFromStartOfWeekToFirstOfMonth2 = z2 ? (month.getDaysFromStartOfWeekToFirstOfMonth() + endDate.getDayOfMonth()) - 1 : (month.getDaysFromStartOfWeekToFirstOfMonth() + month.getNumberOfDays()) - 1;
            return new SelectedRangeInfo(IntOffset.m6144constructorimpl((((long) (daysFromStartOfWeekToFirstOfMonth % 7)) << 32) | (((long) (daysFromStartOfWeekToFirstOfMonth / 7)) & 4294967295L)), IntOffset.m6144constructorimpl((((long) (daysFromStartOfWeekToFirstOfMonth2 / 7)) & 4294967295L) | (((long) (daysFromStartOfWeekToFirstOfMonth2 % 7)) << 32)), z, z2, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ SelectedRangeInfo(long j, long j2, boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, z, z2);
    }
}
