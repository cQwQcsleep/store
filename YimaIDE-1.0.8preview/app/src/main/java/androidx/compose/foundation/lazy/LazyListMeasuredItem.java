package androidx.compose.foundation.lazy;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u0091\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010@\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J(\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u0004H\u0016J\u001e\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u0004J\u000e\u0010G\u001a\u00020B2\u0006\u00109\u001a\u00020\u0004J\u0017\u0010*\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0004H\u0016¢\u0006\u0004\bH\u0010IJ\u0016\u0010J\u001a\u00020B2\u0006\u0010K\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\tJ\u0016\u0010M\u001a\u00020B2\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020\tJ(\u0010W\u001a\u00020\u0015*\u00020\u00152\u0012\u0010X\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040YH\u0082\b¢\u0006\u0004\bZ\u0010[R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010!R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b&\u0010'R\u001e\u0010)\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0004@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u0014\u0010+\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010 R\u0014\u0010-\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b.\u0010 R\u0014\u0010/\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b0\u0010 R\u0014\u00101\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010 R\u0011\u00103\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010 R\u001a\u00105\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010!\"\u0004\b7\u00108R\u000e\u00109\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010 R\u0018\u0010Q\u001a\u00020\u0004*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0018\u0010T\u001a\u00020\u0004*\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bU\u0010V¨\u0006\\"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "Landroidx/compose/foundation/lazy/LazyListItemInfo;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "index", "", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "isVertical", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "reverseLayout", "beforeContentPadding", "afterContentPadding", "spacing", "visualOffset", "Landroidx/compose/ui/unit/IntOffset;", "key", "", "contentType", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "<init>", "(ILjava/util/List;ZLandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZIIIJLjava/lang/Object;Ljava/lang/Object;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getIndex", "()I", "()Z", "J", "getKey", "()Ljava/lang/Object;", "getContentType", "getConstraints-msEJaDk", "()J", "value", "offset", "getOffset", "size", "getSize", "lane", "getLane", "span", "getSpan", "mainAxisSizeWithSpacings", "getMainAxisSizeWithSpacings", "crossAxisSize", "getCrossAxisSize", "nonScrollableItem", "getNonScrollableItem", "setNonScrollableItem", "(Z)V", "mainAxisLayoutSize", "minMainAxisOffset", "maxMainAxisOffset", "placeableOffsets", "", "placeablesCount", "getPlaceablesCount", "getParentData", "position", "", "mainAxisOffset", "crossAxisOffset", "layoutWidth", "layoutHeight", "updateMainAxisLayoutSize", "getOffset-Bjo55l4", "(I)J", "applyScrollDelta", "delta", "updateAnimations", "place", "scope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "isLookingAhead", "mainAxis", "getMainAxis--gyyYBs", "(J)I", "mainAxisSize", "getMainAxisSize", "(Landroidx/compose/ui/layout/Placeable;)I", "copy", "mainAxisMap", "Lkotlin/Function1;", "copy-4Tuh3kE", "(JLkotlin/jvm/functions/Function1;)J", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyListMeasuredItem implements LazyListItemInfo, LazyLayoutMeasuredItem {
    public static final int $stable = 8;
    private final int afterContentPadding;
    private final LazyLayoutItemAnimator<LazyListMeasuredItem> animator;
    private final int beforeContentPadding;
    private final long constraints;
    private final Object contentType;
    private final int crossAxisSize;
    private final Alignment.Horizontal horizontalAlignment;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final int lane;
    private final LayoutDirection layoutDirection;
    private int mainAxisLayoutSize;
    private final int mainAxisSizeWithSpacings;
    private int maxMainAxisOffset;
    private int minMainAxisOffset;
    private boolean nonScrollableItem;
    private int offset;
    private final int[] placeableOffsets;
    private final List<Placeable> placeables;
    private final boolean reverseLayout;
    private final int size;
    private final int spacing;
    private final int span;
    private final Alignment.Vertical verticalAlignment;
    private final long visualOffset;

    private LazyListMeasuredItem(int i, List<? extends Placeable> list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z2, int i2, int i3, int i4, long j, Object obj, Object obj2, LazyLayoutItemAnimator<LazyListMeasuredItem> lazyLayoutItemAnimator, long j2) {
        this.index = i;
        this.placeables = list;
        this.isVertical = z;
        this.horizontalAlignment = horizontal;
        this.verticalAlignment = vertical;
        this.layoutDirection = layoutDirection;
        this.reverseLayout = z2;
        this.beforeContentPadding = i2;
        this.afterContentPadding = i3;
        this.spacing = i4;
        this.visualOffset = j;
        this.key = obj;
        this.contentType = obj2;
        this.animator = lazyLayoutItemAnimator;
        this.constraints = j2;
        this.span = 1;
        this.mainAxisLayoutSize = Integer.MIN_VALUE;
        int size = list.size();
        int height = 0;
        int iMax = 0;
        for (int i5 = 0; i5 < size; i5++) {
            Placeable placeable = list.get(i5);
            height += getIsVertical() ? placeable.getHeight() : placeable.getWidth();
            iMax = Math.max(iMax, !getIsVertical() ? placeable.getHeight() : placeable.getWidth());
        }
        this.size = height;
        this.mainAxisSizeWithSpacings = RangesKt.coerceAtLeast(getSize() + this.spacing, 0);
        this.crossAxisSize = iMax;
        this.placeableOffsets = new int[this.placeables.size() * 2];
    }

    /* JADX INFO: renamed from: copy-4Tuh3kE, reason: not valid java name */
    private final long m1060copy4Tuh3kE(long j, Function1<? super Integer, Integer> function1) {
        int iIntValue;
        int iIntValue2;
        if (getIsVertical()) {
            iIntValue = IntOffset.getX-impl(j);
            iIntValue2 = ((Number) function1.invoke(Integer.valueOf(IntOffset.getY-impl(j)))).intValue();
        } else {
            iIntValue = ((Number) function1.invoke(Integer.valueOf(IntOffset.getX-impl(j)))).intValue();
            iIntValue2 = IntOffset.getY-impl(j);
        }
        return IntOffset.constructor-impl((((long) iIntValue2) & 4294967295L) | (((long) iIntValue) << 32));
    }

    /* JADX INFO: renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m1061getMainAxisgyyYBs(long j) {
        return getIsVertical() ? IntOffset.getY-impl(j) : IntOffset.getX-impl(j);
    }

    private final int getMainAxisSize(Placeable placeable) {
        return getIsVertical() ? placeable.getHeight() : placeable.getWidth();
    }

    public final void applyScrollDelta(int delta, boolean updateAnimations) {
        int iIntValue;
        int iIntValue2;
        if (getNonScrollableItem()) {
            return;
        }
        this.offset = getOffset() + delta;
        int length = this.placeableOffsets.length;
        for (int i = 0; i < length; i++) {
            int i2 = i & 1;
            if ((getIsVertical() && i2 != 0) || (!getIsVertical() && i2 == 0)) {
                int[] iArr = this.placeableOffsets;
                iArr[i] = iArr[i] + delta;
            }
        }
        if (updateAnimations) {
            int placeablesCount = getPlaceablesCount();
            for (int i3 = 0; i3 < placeablesCount; i3++) {
                LazyLayoutItemAnimation animation = this.animator.getAnimation(getKey(), i3);
                if (animation != null) {
                    long rawOffset = animation.getRawOffset();
                    if (getIsVertical()) {
                        iIntValue = IntOffset.getX-impl(rawOffset);
                        iIntValue2 = Integer.valueOf(IntOffset.getY-impl(rawOffset) + delta).intValue();
                    } else {
                        iIntValue = Integer.valueOf(IntOffset.getX-impl(rawOffset) + delta).intValue();
                        iIntValue2 = IntOffset.getY-impl(rawOffset);
                    }
                    animation.m1121setRawOffsetgyyYBs(IntOffset.constructor-impl((((long) iIntValue) << 32) | (4294967295L & ((long) iIntValue2))));
                }
            }
        }
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* JADX INFO: renamed from: getConstraints-msEJaDk, reason: not valid java name and from getter */
    public long getConstraints() {
        return this.constraints;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public Object getContentType() {
        return this.contentType;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
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

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getOffset() {
        return this.offset;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* JADX INFO: renamed from: getOffset-Bjo55l4, reason: not valid java name */
    public long mo1063getOffsetBjo55l4(int index) {
        if (index == 0 && getPlaceablesCount() == 0) {
            boolean isVertical = getIsVertical();
            int offset = getOffset();
            return isVertical ? IntOffset.constructor-impl(((long) offset) & 4294967295L) : IntOffset.constructor-impl(((long) offset) << 32);
        }
        int[] iArr = this.placeableOffsets;
        int i = index * 2;
        return IntOffset.constructor-impl((((long) iArr[i + 1]) & 4294967295L) | (((long) iArr[i]) << 32));
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public Object getParentData(int index) {
        return this.placeables.get(index).getParentData();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getPlaceablesCount() {
        return this.placeables.size();
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getSize() {
        return this.size;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
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
        long j;
        int i = 0;
        if (!(this.mainAxisLayoutSize != Integer.MIN_VALUE)) {
            InlineClassHelperKt.throwIllegalArgumentException("position() should be called first");
        }
        int placeablesCount = getPlaceablesCount();
        while (i < placeablesCount) {
            Placeable placeable = this.placeables.get(i);
            int mainAxisSize = this.minMainAxisOffset - getMainAxisSize(placeable);
            int i2 = this.maxMainAxisOffset;
            long jMo1063getOffsetBjo55l4 = mo1063getOffsetBjo55l4(i);
            LazyLayoutItemAnimation animation = this.animator.getAnimation(getKey(), i);
            if (animation != null) {
                if (isLookingAhead) {
                    animation.m1120setLookaheadOffsetgyyYBs(jMo1063getOffsetBjo55l4);
                } else {
                    if (!IntOffset.equals-impl0(animation.getLookaheadOffset(), LazyLayoutItemAnimation.INSTANCE.m1122getNotInitializednOccac())) {
                        jMo1063getOffsetBjo55l4 = animation.getLookaheadOffset();
                    }
                    long j2 = IntOffset.plus-qkQi6aY(jMo1063getOffsetBjo55l4, animation.m1117getPlacementDeltanOccac());
                    if ((m1061getMainAxisgyyYBs(jMo1063getOffsetBjo55l4) <= mainAxisSize && m1061getMainAxisgyyYBs(j2) <= mainAxisSize) || (m1061getMainAxisgyyYBs(jMo1063getOffsetBjo55l4) >= i2 && m1061getMainAxisgyyYBs(j2) >= i2)) {
                        animation.cancelPlacementAnimation();
                    }
                    jMo1063getOffsetBjo55l4 = j2;
                }
                layer = animation.getLayer();
            } else {
                layer = null;
            }
            if (this.reverseLayout) {
                if (getIsVertical()) {
                    j = (((long) ((this.mainAxisLayoutSize - IntOffset.getY-impl(jMo1063getOffsetBjo55l4)) - getMainAxisSize(placeable))) & 4294967295L) | (((long) IntOffset.getX-impl(jMo1063getOffsetBjo55l4)) << 32);
                } else {
                    j = (((long) ((this.mainAxisLayoutSize - IntOffset.getX-impl(jMo1063getOffsetBjo55l4)) - getMainAxisSize(placeable))) << 32) | (4294967295L & ((long) IntOffset.getY-impl(jMo1063getOffsetBjo55l4)));
                }
                jMo1063getOffsetBjo55l4 = IntOffset.constructor-impl(j);
            }
            long j3 = IntOffset.plus-qkQi6aY(jMo1063getOffsetBjo55l4, this.visualOffset);
            if (!isLookingAhead && animation != null) {
                animation.m1119setFinalOffsetgyyYBs(j3);
            }
            if (!getIsVertical()) {
                placementScope = scope;
                GraphicsLayer graphicsLayer = layer;
                if (graphicsLayer != null) {
                    Placeable.PlacementScope.placeRelativeWithLayer-aW-9-wM$default(placementScope, placeable, j3, graphicsLayer, 0.0f, 4, (Object) null);
                } else {
                    Placeable.PlacementScope.placeRelativeWithLayer-aW-9-wM$default(placementScope, placeable, j3, 0.0f, (Function1) null, 6, (Object) null);
                }
            } else if (layer != null) {
                placementScope = scope;
                Placeable.PlacementScope.placeWithLayer-aW-9-wM$default(placementScope, placeable, j3, layer, 0.0f, 4, (Object) null);
            } else {
                placementScope = scope;
                Placeable.PlacementScope.placeWithLayer-aW-9-wM$default(placementScope, placeable, j3, 0.0f, (Function1) null, 6, (Object) null);
            }
            i++;
            scope = placementScope;
        }
    }

    public final void position(int mainAxisOffset, int layoutWidth, int layoutHeight) {
        int width;
        this.offset = mainAxisOffset;
        this.mainAxisLayoutSize = getIsVertical() ? layoutHeight : layoutWidth;
        List<Placeable> list = this.placeables;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Placeable placeable = list.get(i);
            int i2 = i * 2;
            boolean isVertical = getIsVertical();
            int[] iArr = this.placeableOffsets;
            if (isVertical) {
                Alignment.Horizontal horizontal = this.horizontalAlignment;
                if (horizontal == null) {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalAlignment when isVertical == true");
                    wq6.a();
                    return;
                } else {
                    iArr[i2] = horizontal.align(placeable.getWidth(), layoutWidth, this.layoutDirection);
                    this.placeableOffsets[i2 + 1] = mainAxisOffset;
                    width = placeable.getHeight();
                }
            } else {
                iArr[i2] = mainAxisOffset;
                int i3 = i2 + 1;
                Alignment.Vertical vertical = this.verticalAlignment;
                if (vertical == null) {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null verticalAlignment when isVertical == false");
                    wq6.a();
                    return;
                } else {
                    iArr[i3] = vertical.align(placeable.getHeight(), layoutHeight);
                    width = placeable.getWidth();
                }
            }
            mainAxisOffset += width;
        }
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

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public void position(int mainAxisOffset, int crossAxisOffset, int layoutWidth, int layoutHeight) {
        position(mainAxisOffset, layoutWidth, layoutHeight);
    }

    public /* synthetic */ LazyListMeasuredItem(int i, List list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z2, int i2, int i3, int i4, long j, Object obj, Object obj2, LazyLayoutItemAnimator lazyLayoutItemAnimator, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, list, z, horizontal, vertical, layoutDirection, z2, i2, i3, i4, j, obj, obj2, lazyLayoutItemAnimator, j2);
    }
}
