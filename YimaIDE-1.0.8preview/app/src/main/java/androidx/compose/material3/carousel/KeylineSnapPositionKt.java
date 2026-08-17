package androidx.compose.material3.carousel;

import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¨\u0006\n"}, d2 = {"getSnapPositionOffset", "", "strategy", "Landroidx/compose/material3/carousel/Strategy;", "itemIndex", "itemCount", "KeylineSnapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "pageSize", "Landroidx/compose/material3/carousel/CarouselPageSize;", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class KeylineSnapPositionKt {
    public static final SnapPosition KeylineSnapPosition(final CarouselPageSize carouselPageSize) {
        return new SnapPosition() { // from class: androidx.compose.material3.carousel.KeylineSnapPositionKt.KeylineSnapPosition.1
            public int position(int layoutSize, int itemSize, int beforeContentPadding, int afterContentPadding, int itemIndex, int itemCount) {
                return KeylineSnapPositionKt.getSnapPositionOffset(carouselPageSize.getStrategy(), itemIndex, itemCount);
            }
        };
    }

    public static final int getSnapPositionOffset(Strategy strategy, int i, int i2) {
        if (!strategy.getIsValid()) {
            return 0;
        }
        int iRoundToInt = MathKt.roundToInt(strategy.getDefaultKeylines().getFirstFocal().getUnadjustedOffset() - (strategy.getItemMainAxisSize() / 2.0f));
        if (i <= CollectionsKt.getLastIndex(strategy.getStartKeylineSteps())) {
            iRoundToInt = MathKt.roundToInt(strategy.getStartKeylineSteps().get(RangesKt.coerceIn(CollectionsKt.getLastIndex(strategy.getStartKeylineSteps()) - i, 0, CollectionsKt.getLastIndex(strategy.getStartKeylineSteps()))).getFirstFocal().getUnadjustedOffset() - (strategy.getItemMainAxisSize() / 2.0f));
        }
        int i3 = i2 - 1;
        if (i < i3 - CollectionsKt.getLastIndex(strategy.getEndKeylineSteps()) || i2 <= strategy.getDefaultKeylines().getFocalCount()) {
            return iRoundToInt;
        }
        return MathKt.roundToInt(strategy.getEndKeylineSteps().get(RangesKt.coerceIn(CollectionsKt.getLastIndex(strategy.getEndKeylineSteps()) - (i3 - i), 0, CollectionsKt.getLastIndex(strategy.getEndKeylineSteps()))).getFirstFocal().getUnadjustedOffset() - (strategy.getItemMainAxisSize() / 2.0f));
    }
}
