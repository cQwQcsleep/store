package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u0095\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00000\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u0004\u0012\u0006\u0010\u001b\u001a\u00020\u0004¢\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u00103\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\u0017\u0010>\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u0004H\u0016¢\u0006\u0004\b?\u0010@J(\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u0004H\u0016J6\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u0004J\u000e\u0010K\u001a\u00020F2\u0006\u00100\u001a\u00020\u0004J\u0016\u0010L\u001a\u00020F2\u0006\u0010M\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\bJ\u0016\u0010O\u001a\u00020F2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020\bJ(\u0010W\u001a\u00020\u0014*\u00020\u00142\u0012\u0010X\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040YH\u0082\b¢\u0006\u0004\bZ\u0010[R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\"R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00000\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\u00020\u0019X\u0096\u0004¢\u0006\n\n\u0002\u0010$\u001a\u0004\b&\u0010'R\u0014\u0010\u001a\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0014\u0010\u001b\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0011\u0010*\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0014\u0010,\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0014\u0010.\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u001fR\u000e\u00100\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00104\u001a\u000205X\u0096\u0004¢\u0006\n\n\u0002\u0010$\u001a\u0004\b6\u0010'R \u00108\u001a\u00020\u00142\u0006\u00107\u001a\u00020\u0014@RX\u0096\u000e¢\u0006\n\n\u0002\u0010$\u001a\u0004\b9\u0010'R\u001e\u0010:\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u001fR\u001e\u0010<\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u001fR\u001a\u0010A\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\"\"\u0004\bC\u0010DR\u0018\u0010S\u001a\u00020\u0004*\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0018\u0010*\u001a\u00020\u0004*\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010V¨\u0006\\"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemInfo;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "index", "", "key", "", "isVertical", "", "crossAxisSize", "mainAxisSpacing", "reverseLayout", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "beforeContentPadding", "afterContentPadding", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "visualOffset", "Landroidx/compose/ui/unit/IntOffset;", "contentType", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "lane", "span", "<init>", "(ILjava/lang/Object;ZIIZLandroidx/compose/ui/unit/LayoutDirection;IILjava/util/List;JLjava/lang/Object;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;JIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getIndex", "()I", "getKey", "()Ljava/lang/Object;", "()Z", "getCrossAxisSize", "J", "getContentType", "getConstraints-msEJaDk", "()J", "getLane", "getSpan", "mainAxisSize", "getMainAxisSize", "mainAxisSizeWithSpacings", "getMainAxisSizeWithSpacings", "placeablesCount", "getPlaceablesCount", "mainAxisLayoutSize", "minMainAxisOffset", "maxMainAxisOffset", "getParentData", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "value", "offset", "getOffset-nOcc-ac", "row", "getRow", "column", "getColumn", "getOffset", "getOffset-Bjo55l4", "(I)J", "nonScrollableItem", "getNonScrollableItem", "setNonScrollableItem", "(Z)V", "position", "", "mainAxisOffset", "crossAxisOffset", "layoutWidth", "layoutHeight", "updateMainAxisLayoutSize", "applyScrollDelta", "delta", "updateAnimations", "place", "scope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "isLookingAhead", "mainAxis", "getMainAxis--gyyYBs", "(J)I", "(Landroidx/compose/ui/layout/Placeable;)I", "copy", "mainAxisMap", "Lkotlin/Function1;", "copy-4Tuh3kE", "(JLkotlin/jvm/functions/Function1;)J", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyGridMeasuredItem implements LazyGridItemInfo, LazyLayoutMeasuredItem {
    public static final int $stable = 8;
    private final int afterContentPadding;
    private final LazyLayoutItemAnimator<LazyGridMeasuredItem> animator;
    private final int beforeContentPadding;
    private int column;
    private final long constraints;
    private final Object contentType;
    private final int crossAxisSize;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final int lane;
    private final LayoutDirection layoutDirection;
    private int mainAxisLayoutSize;
    private final int mainAxisSize;
    private final int mainAxisSizeWithSpacings;
    private int maxMainAxisOffset;
    private int minMainAxisOffset;
    private boolean nonScrollableItem;
    private long offset;
    private final List<Placeable> placeables;
    private final boolean reverseLayout;
    private int row;
    private final long size;
    private final int span;
    private final long visualOffset;

    private LazyGridMeasuredItem(int i, Object obj, boolean z, int i2, int i3, boolean z2, LayoutDirection layoutDirection, int i4, int i5, List<? extends Placeable> list, long j, Object obj2, LazyLayoutItemAnimator<LazyGridMeasuredItem> lazyLayoutItemAnimator, long j2, int i6, int i7) {
        this.index = i;
        this.key = obj;
        this.isVertical = z;
        this.crossAxisSize = i2;
        this.reverseLayout = z2;
        this.layoutDirection = layoutDirection;
        this.beforeContentPadding = i4;
        this.afterContentPadding = i5;
        this.placeables = list;
        this.visualOffset = j;
        this.contentType = obj2;
        this.animator = lazyLayoutItemAnimator;
        this.constraints = j2;
        this.lane = i6;
        this.span = i7;
        this.mainAxisLayoutSize = Integer.MIN_VALUE;
        int size = list.size();
        int iMax = 0;
        for (int i8 = 0; i8 < size; i8++) {
            Placeable placeable = list.get(i8);
            iMax = Math.max(iMax, getIsVertical() ? placeable.getHeight() : placeable.getWidth());
        }
        this.mainAxisSize = iMax;
        this.mainAxisSizeWithSpacings = RangesKt.coerceAtLeast(i3 + iMax, 0);
        boolean isVertical = getIsVertical();
        int i9 = this.crossAxisSize;
        this.size = isVertical ? IntSize.constructor-impl((((long) i9) << 32) | (((long) iMax) & 4294967295L)) : IntSize.constructor-impl((((long) i9) & 4294967295L) | (((long) iMax) << 32));
        this.offset = IntOffset.Companion.getZero-nOcc-ac();
        this.row = -1;
        this.column = -1;
    }

    /* JADX INFO: renamed from: copy-4Tuh3kE, reason: not valid java name */
    private final long m1092copy4Tuh3kE(long j, Function1<? super Integer, Integer> function1) {
        return IntOffset.constructor-impl((((long) (getIsVertical() ? IntOffset.getX-impl(j) : ((Number) function1.invoke(Integer.valueOf(IntOffset.getX-impl(j)))).intValue())) << 32) | (((long) (getIsVertical() ? ((Number) function1.invoke(Integer.valueOf(IntOffset.getY-impl(j)))).intValue() : IntOffset.getY-impl(j))) & 4294967295L));
    }

    /* JADX INFO: renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m1093getMainAxisgyyYBs(long j) {
        return getIsVertical() ? IntOffset.getY-impl(j) : IntOffset.getX-impl(j);
    }

    private final int getMainAxisSize(Placeable placeable) {
        return getIsVertical() ? placeable.getHeight() : placeable.getWidth();
    }

    public final void applyScrollDelta(int delta, boolean updateAnimations) {
        if (getNonScrollableItem()) {
            return;
        }
        long offset = getOffset();
        int i = getIsVertical() ? IntOffset.getX-impl(offset) : IntOffset.getX-impl(offset) + delta;
        boolean isVertical = getIsVertical();
        int i2 = IntOffset.getY-impl(offset);
        if (isVertical) {
            i2 += delta;
        }
        this.offset = IntOffset.constructor-impl((((long) i) << 32) | (((long) i2) & 4294967295L));
        if (updateAnimations) {
            int placeablesCount = getPlaceablesCount();
            for (int i3 = 0; i3 < placeablesCount; i3++) {
                LazyLayoutItemAnimation animation = this.animator.getAnimation(getKey(), i3);
                if (animation != null) {
                    long rawOffset = animation.getRawOffset();
                    int i4 = getIsVertical() ? IntOffset.getX-impl(rawOffset) : Integer.valueOf(IntOffset.getX-impl(rawOffset) + delta).intValue();
                    boolean isVertical2 = getIsVertical();
                    int iIntValue = IntOffset.getY-impl(rawOffset);
                    if (isVertical2) {
                        iIntValue = Integer.valueOf(iIntValue + delta).intValue();
                    }
                    animation.m1121setRawOffsetgyyYBs(IntOffset.constructor-impl((((long) iIntValue) & 4294967295L) | (((long) i4) << 32)));
                }
            }
        }
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public int getColumn() {
        return this.column;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* JADX INFO: renamed from: getConstraints-msEJaDk, reason: from getter */
    public long getConstraints() {
        return this.constraints;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public Object getContentType() {
        return this.contentType;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public Object getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getLane() {
        return this.lane;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getMainAxisSizeWithSpacings() {
        return this.mainAxisSizeWithSpacings;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public boolean getNonScrollableItem() {
        return this.nonScrollableItem;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* JADX INFO: renamed from: getOffset-Bjo55l4 */
    public long mo1063getOffsetBjo55l4(int index) {
        return getOffset();
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    /* JADX INFO: renamed from: getOffset-nOcc-ac, reason: from getter */
    public long getOffset() {
        return this.offset;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public Object getParentData(int index) {
        return this.placeables.get(index).getParentData();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getPlaceablesCount() {
        return this.placeables.size();
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public int getRow() {
        return this.row;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    /* JADX INFO: renamed from: getSize-YbymL2g, reason: from getter */
    public long getSize() {
        return this.size;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getSpan() {
        return this.span;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* JADX INFO: renamed from: isVertical, reason: from getter */
    public boolean getIsVertical() {
        return this.isVertical;
    }

    public final void place(Placeable.PlacementScope scope, boolean isLookingAhead) {
        GraphicsLayer layer;
        Placeable.PlacementScope placementScope;
        int i;
        int i2;
        int i3 = 0;
        if (!(this.mainAxisLayoutSize != Integer.MIN_VALUE)) {
            InlineClassHelperKt.throwIllegalArgumentException("position() should be called first");
        }
        int placeablesCount = getPlaceablesCount();
        while (i3 < placeablesCount) {
            Placeable placeable = this.placeables.get(i3);
            int mainAxisSize = this.minMainAxisOffset - getMainAxisSize(placeable);
            int i4 = this.maxMainAxisOffset;
            long offset = getOffset();
            LazyLayoutItemAnimation animation = this.animator.getAnimation(getKey(), i3);
            if (animation != null) {
                if (isLookingAhead) {
                    animation.m1120setLookaheadOffsetgyyYBs(offset);
                } else {
                    long j = IntOffset.plus-qkQi6aY(!IntOffset.equals-impl0(animation.getLookaheadOffset(), LazyLayoutItemAnimation.INSTANCE.m1122getNotInitializednOccac()) ? animation.getLookaheadOffset() : offset, animation.m1117getPlacementDeltanOccac());
                    if ((m1093getMainAxisgyyYBs(offset) <= mainAxisSize && m1093getMainAxisgyyYBs(j) <= mainAxisSize) || (m1093getMainAxisgyyYBs(offset) >= i4 && m1093getMainAxisgyyYBs(j) >= i4)) {
                        animation.cancelPlacementAnimation();
                    }
                    offset = j;
                }
                layer = animation.getLayer();
            } else {
                layer = null;
            }
            if (this.reverseLayout) {
                if (getIsVertical()) {
                    i = IntOffset.getX-impl(offset);
                } else {
                    i = (this.mainAxisLayoutSize - IntOffset.getX-impl(offset)) - getMainAxisSize(placeable);
                }
                if (getIsVertical()) {
                    i2 = (this.mainAxisLayoutSize - IntOffset.getY-impl(offset)) - getMainAxisSize(placeable);
                } else {
                    i2 = IntOffset.getY-impl(offset);
                }
                offset = IntOffset.constructor-impl((((long) i2) & 4294967295L) | (((long) i) << 32));
            }
            long j2 = IntOffset.plus-qkQi6aY(offset, this.visualOffset);
            if (!isLookingAhead && animation != null) {
                animation.m1119setFinalOffsetgyyYBs(j2);
            }
            if (!getIsVertical()) {
                placementScope = scope;
                GraphicsLayer graphicsLayer = layer;
                if (graphicsLayer != null) {
                    Placeable.PlacementScope.placeRelativeWithLayer-aW-9-wM$default(placementScope, placeable, j2, graphicsLayer, 0.0f, 4, (Object) null);
                } else {
                    Placeable.PlacementScope.placeRelativeWithLayer-aW-9-wM$default(placementScope, placeable, j2, 0.0f, (Function1) null, 6, (Object) null);
                }
            } else if (layer != null) {
                placementScope = scope;
                Placeable.PlacementScope.placeWithLayer-aW-9-wM$default(placementScope, placeable, j2, layer, 0.0f, 4, (Object) null);
            } else {
                placementScope = scope;
                Placeable.PlacementScope.placeWithLayer-aW-9-wM$default(placementScope, placeable, j2, 0.0f, (Function1) null, 6, (Object) null);
            }
            i3++;
            scope = placementScope;
        }
    }

    public final void position(int mainAxisOffset, int crossAxisOffset, int layoutWidth, int layoutHeight, int row, int column) {
        long j;
        this.mainAxisLayoutSize = getIsVertical() ? layoutHeight : layoutWidth;
        if (!getIsVertical()) {
            layoutWidth = layoutHeight;
        }
        if (getIsVertical() && this.layoutDirection == LayoutDirection.Rtl) {
            crossAxisOffset = (layoutWidth - crossAxisOffset) - this.crossAxisSize;
        }
        if (getIsVertical()) {
            j = IntOffset.constructor-impl((((long) crossAxisOffset) << 32) | (4294967295L & ((long) mainAxisOffset)));
        } else {
            j = IntOffset.constructor-impl((((long) crossAxisOffset) & 4294967295L) | (((long) mainAxisOffset) << 32));
        }
        this.offset = j;
        this.row = row;
        this.column = column;
        this.minMainAxisOffset = -this.beforeContentPadding;
        this.maxMainAxisOffset = this.mainAxisLayoutSize + this.afterContentPadding;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public void setNonScrollableItem(boolean z) {
        this.nonScrollableItem = z;
    }

    public final void updateMainAxisLayoutSize(int mainAxisLayoutSize) {
        this.mainAxisLayoutSize = mainAxisLayoutSize;
        this.maxMainAxisOffset = mainAxisLayoutSize + this.afterContentPadding;
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public void position(int mainAxisOffset, int crossAxisOffset, int layoutWidth, int layoutHeight) {
        position(mainAxisOffset, crossAxisOffset, layoutWidth, layoutHeight, -1, -1);
    }

    public /* synthetic */ LazyGridMeasuredItem(int i, Object obj, boolean z, int i2, int i3, boolean z2, LayoutDirection layoutDirection, int i4, int i5, List list, long j, Object obj2, LazyLayoutItemAnimator lazyLayoutItemAnimator, long j2, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, obj, z, i2, i3, z2, layoutDirection, i4, i5, list, j, obj2, lazyLayoutItemAnimator, j2, i6, i7);
    }
}
