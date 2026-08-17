package androidx.compose.foundation.lazy.layout;

import kotlin.Metadata;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¨\u0006\u0006"}, d2 = {"LazyLayoutKeyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "itemIndexRange", "Lkotlin/ranges/IntRange;", "intervalContent", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutKeyIndexMapKt {
    public static final LazyLayoutKeyIndexMap LazyLayoutKeyIndexMap(IntRange intRange, LazyLayoutIntervalContent<?> lazyLayoutIntervalContent) {
        return new NearestRangeKeyIndexMap(intRange, lazyLayoutIntervalContent);
    }
}
