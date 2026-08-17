package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b!\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018J/\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJO\u0010!\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010#2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010\u001a\u001a\u00020\u000eH&¢\u0006\u0004\b(\u0010)R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006*"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureProvider;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItemProvider;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "isVertical", "", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "measureScope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "resolvedSlots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "<init>", "(ZLandroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;)V", "childConstraints", "Landroidx/compose/ui/unit/Constraints;", "slot", "", "span", "childConstraints-JhjzzOo", "(II)J", "getAndMeasure", "index", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "getAndMeasure-jy6DScQ", "(IJ)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "lane", "constraints", "getAndMeasure--hBUhpc", "(IIIJ)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "getKeyIndexMap", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "createItem", "key", "", "contentType", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "createItem-pitSLOA", "(IIILjava/lang/Object;Ljava/lang/Object;Ljava/util/List;J)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class LazyStaggeredGridMeasureProvider extends LazyLayoutMeasuredItemProvider<LazyStaggeredGridMeasuredItem> {
    public static final int $stable = 8;
    private final boolean isVertical;
    private final LazyStaggeredGridItemProvider itemProvider;
    private final LazyLayoutMeasureScope measureScope;
    private final LazyStaggeredGridSlots resolvedSlots;

    public LazyStaggeredGridMeasureProvider(boolean z, LazyStaggeredGridItemProvider lazyStaggeredGridItemProvider, LazyLayoutMeasureScope lazyLayoutMeasureScope, LazyStaggeredGridSlots lazyStaggeredGridSlots) {
        this.isVertical = z;
        this.itemProvider = lazyStaggeredGridItemProvider;
        this.measureScope = lazyLayoutMeasureScope;
        this.resolvedSlots = lazyStaggeredGridSlots;
    }

    /* JADX INFO: renamed from: childConstraints-JhjzzOo, reason: not valid java name */
    private final long m1199childConstraintsJhjzzOo(int slot, int span) {
        int i;
        LazyStaggeredGridSlots lazyStaggeredGridSlots = this.resolvedSlots;
        if (span == 1) {
            i = lazyStaggeredGridSlots.getSizes()[slot];
        } else {
            int i2 = lazyStaggeredGridSlots.getPositions()[slot];
            int i3 = (slot + span) - 1;
            i = (this.resolvedSlots.getPositions()[i3] + this.resolvedSlots.getSizes()[i3]) - i2;
        }
        return this.isVertical ? Constraints.Companion.fixedWidth-OenEA2s(i) : Constraints.Companion.fixedHeight-OenEA2s(i);
    }

    /* JADX INFO: renamed from: createItem-pitSLOA */
    public abstract LazyStaggeredGridMeasuredItem mo1194createItempitSLOA(int index, int lane, int span, Object key, Object contentType, List<? extends Placeable> placeables, long constraints);

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider
    /* JADX INFO: renamed from: getAndMeasure--hBUhpc */
    public LazyStaggeredGridMeasuredItem mo1065getAndMeasurehBUhpc(int index, int lane, int span, long constraints) {
        return mo1194createItempitSLOA(index, lane, span, this.itemProvider.getKey(index), this.itemProvider.getContentType(index), m1142getPlaceables3p2s80s(this.measureScope, index, constraints), constraints);
    }

    /* JADX INFO: renamed from: getAndMeasure-jy6DScQ, reason: not valid java name */
    public final LazyStaggeredGridMeasuredItem m1200getAndMeasurejy6DScQ(int index, long span) {
        Object key = this.itemProvider.getKey(index);
        Object contentType = this.itemProvider.getContentType(index);
        int length = this.resolvedSlots.getSizes().length;
        int i = (int) (span >> 32);
        int iCoerceAtMost = RangesKt.coerceAtMost(i, length - 1);
        int iCoerceAtMost2 = RangesKt.coerceAtMost(((int) (span & 4294967295L)) - i, length - iCoerceAtMost);
        long jM1199childConstraintsJhjzzOo = m1199childConstraintsJhjzzOo(iCoerceAtMost, iCoerceAtMost2);
        return mo1194createItempitSLOA(index, iCoerceAtMost, iCoerceAtMost2, key, contentType, m1142getPlaceables3p2s80s(this.measureScope, index, jM1199childConstraintsJhjzzOo), jM1199childConstraintsJhjzzOo);
    }

    public final LazyLayoutKeyIndexMap getKeyIndexMap() {
        return this.itemProvider.getKeyIndexMap();
    }
}
