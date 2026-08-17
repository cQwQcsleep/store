package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", "target", "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class EditingBufferKt {
    /* JADX INFO: renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m5643updateRangeAfterDeletepWDy79M(long j, long j2) {
        int iM5475getLengthimpl;
        int iM5477getMinimpl = TextRange.m5477getMinimpl(j);
        int iM5476getMaximpl = TextRange.m5476getMaximpl(j);
        if (TextRange.m5481intersects5zctL8(j2, j)) {
            if (TextRange.m5469contains5zctL8(j2, j)) {
                iM5477getMinimpl = TextRange.m5477getMinimpl(j2);
                iM5476getMaximpl = iM5477getMinimpl;
            } else {
                if (TextRange.m5469contains5zctL8(j, j2)) {
                    iM5475getLengthimpl = TextRange.m5475getLengthimpl(j2);
                } else if (TextRange.m5470containsimpl(j2, iM5477getMinimpl)) {
                    iM5477getMinimpl = TextRange.m5477getMinimpl(j2);
                    iM5475getLengthimpl = TextRange.m5475getLengthimpl(j2);
                } else {
                    iM5476getMaximpl = TextRange.m5477getMinimpl(j2);
                }
                iM5476getMaximpl -= iM5475getLengthimpl;
            }
        } else if (iM5476getMaximpl > TextRange.m5477getMinimpl(j2)) {
            iM5477getMinimpl -= TextRange.m5475getLengthimpl(j2);
            iM5475getLengthimpl = TextRange.m5475getLengthimpl(j2);
            iM5476getMaximpl -= iM5475getLengthimpl;
        }
        return TextRangeKt.TextRange(iM5477getMinimpl, iM5476getMaximpl);
    }
}
