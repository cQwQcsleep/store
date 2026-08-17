package androidx.compose.foundation.pager;

import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.gestures.Orientation;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"mainAxisViewportSize", "", "Landroidx/compose/foundation/pager/PagerLayoutInfo;", "getMainAxisViewportSize", "(Landroidx/compose/foundation/pager/PagerLayoutInfo;)I", "calculateContentSize", "pageCount", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PagerLayoutInfoKt {
    public static final int calculateContentSize(PagerLayoutInfo pagerLayoutInfo, int i) {
        int beforeContentPadding = pagerLayoutInfo.getBeforeContentPadding() + pagerLayoutInfo.getAfterContentPadding();
        if (i == 0) {
            return beforeContentPadding;
        }
        long j = i;
        long pageSize = (((long) pagerLayoutInfo.getPageSize()) * j) + ((j - 1) * ((long) pagerLayoutInfo.getPageSpacing())) + ((long) beforeContentPadding);
        if (pageSize > SieveCacheKt.NodeLinkMask) {
            pageSize = 2147483647L;
        }
        return (int) pageSize;
    }

    public static final int getMainAxisViewportSize(PagerLayoutInfo pagerLayoutInfo) {
        return (int) (pagerLayoutInfo.getOrientation() == Orientation.Vertical ? pagerLayoutInfo.mo1230getViewportSizeYbymL2g() & 4294967295L : pagerLayoutInfo.mo1230getViewportSizeYbymL2g() >> 32);
    }
}
