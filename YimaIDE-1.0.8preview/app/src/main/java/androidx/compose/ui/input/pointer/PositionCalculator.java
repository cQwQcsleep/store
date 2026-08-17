package androidx.compose.ui.input.pointer;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&¢\u0006\u0004\b\t\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/pointer/PositionCalculator;", "", "screenToLocal", "Landroidx/compose/ui/geometry/Offset;", "positionOnScreen", "screenToLocal-MK-Hz9U", "(J)J", "localToScreen", "localPosition", "localToScreen-MK-Hz9U", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface PositionCalculator {
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U, reason: not valid java name */
    long mo4538localToScreenMKHz9U(long localPosition);

    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U, reason: not valid java name */
    long mo4539screenToLocalMKHz9U(long positionOnScreen);
}
