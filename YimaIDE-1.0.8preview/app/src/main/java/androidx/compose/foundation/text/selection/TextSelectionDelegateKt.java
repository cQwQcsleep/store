package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010\t\u001a$\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¨\u0006\f"}, d2 = {"getSelectionHandleCoordinates", "Landroidx/compose/ui/geometry/Offset;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "offset", "", "isStart", "", "areHandlesCrossed", "(Landroidx/compose/ui/text/TextLayoutResult;IZZ)J", "getHorizontalPosition", "", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextSelectionDelegateKt {
    public static final float getHorizontalPosition(TextLayoutResult textLayoutResult, int i, boolean z, boolean z2) {
        return textLayoutResult.getHorizontalPosition(i, textLayoutResult.getBidiRunDirection(((!z || z2) && (z || !z2)) ? Math.max(i + (-1), 0) : i) == textLayoutResult.getParagraphDirection(i));
    }

    public static final long getSelectionHandleCoordinates(TextLayoutResult textLayoutResult, int i, boolean z, boolean z2) {
        int lineForOffset = textLayoutResult.getLineForOffset(i);
        if (lineForOffset >= textLayoutResult.getLineCount()) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(RangesKt.coerceIn(getHorizontalPosition(textLayoutResult, i, z, z2), 0.0f, (int) (textLayoutResult.getSize-YbymL2g() >> 32)))) << 32) | (((long) Float.floatToRawIntBits(RangesKt.coerceIn(textLayoutResult.getLineBottom(lineForOffset), 0.0f, (int) (textLayoutResult.getSize-YbymL2g() & 4294967295L)))) & 4294967295L));
    }
}
