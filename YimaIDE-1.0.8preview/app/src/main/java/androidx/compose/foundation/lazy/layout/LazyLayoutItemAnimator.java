package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSetKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0003KLMB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0082\u0001\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\t\u001a\u00020\n2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*J\u0010\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u0003H\u0002J\u0006\u0010-\u001a\u00020\u0019J\b\u0010.\u001a\u00020\u0019H\u0002J1\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00028\u00002\u0006\u00101\u001a\u00020\f2\u0012\b\u0002\u00102\u001a\f0\bR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002¢\u0006\u0002\u00103J\u001f\u00104\u001a\u00020\u00192\u0006\u00100\u001a\u00028\u00002\b\b\u0002\u00105\u001a\u00020!H\u0002¢\u0006\u0002\u00106J\u0018\u00107\u001a\u0004\u0018\u00010\u00152\u0006\u0010,\u001a\u00020\u00032\u0006\u00108\u001a\u00020\fJ\u0019\u00109\u001a\u00020\f*\u00020:2\u0006\u00100\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010;R$\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u000e\u0012\f0\bR\b\u0012\u0004\u0012\u00028\u00000\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010<\u001a\u00020=8F¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0011\u0010@\u001a\u00020A¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0018\u0010D\u001a\u00020!*\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0018\u00101\u001a\u00020\f*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0018\u0010I\u001a\u00020\f*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010H¨\u0006N"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "T", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "", "<init>", "()V", "keyToItemInfoMap", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "firstVisibleIndex", "", "movingAwayKeys", "Landroidx/collection/MutableScatterSet;", "movingInFromStartBound", "", "movingInFromEndBound", "movingAwayToStartBound", "movingAwayToEndBound", "disappearingItems", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "displayingNode", "Landroidx/compose/ui/node/DrawModifierNode;", "onMeasured", "", "consumedScroll", "layoutWidth", "layoutHeight", "positionedItems", "itemProvider", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItemProvider;", "isVertical", "", "isLookingAhead", "laneCount", "hasLookaheadOccurred", "layoutMinOffset", "layoutMaxOffset", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "removeInfoForKey", "key", "reset", "releaseAnimations", "initializeAnimation", "item", "mainAxisOffset", "itemInfo", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;ILandroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;)V", "startPlacementAnimationsIfNeeded", "isMovingAway", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;Z)V", "getAnimation", "placeableIndex", "updateAndReturnOffsetFor", "", "([ILandroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "minSizeToFitDisappearingItems", "Landroidx/compose/ui/unit/IntSize;", "getMinSizeToFitDisappearingItems-YbymL2g", "()J", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "hasAnimations", "getHasAnimations", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)Z", "getMainAxisOffset", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "crossAxisOffset", "getCrossAxisOffset", "ItemInfo", "DisplayingDisappearingItemsElement", "DisplayingDisappearingItemsNode", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutItemAnimator<T extends LazyLayoutMeasuredItem> {
    public static final int $stable = 8;
    private DrawModifierNode displayingNode;
    private int firstVisibleIndex;
    private LazyLayoutKeyIndexMap keyIndexMap;
    private final MutableScatterMap<Object, LazyLayoutItemAnimator<T>.ItemInfo> keyToItemInfoMap = ScatterMapKt.mutableScatterMapOf();
    private final MutableScatterSet<Object> movingAwayKeys = ScatterSetKt.mutableScatterSetOf();
    private final List<T> movingInFromStartBound = new ArrayList();
    private final List<T> movingInFromEndBound = new ArrayList();
    private final List<T> movingAwayToStartBound = new ArrayList();
    private final List<T> movingAwayToEndBound = new ArrayList();
    private final List<LazyLayoutItemAnimation> disappearingItems = new ArrayList();
    private final Modifier modifier = new DisplayingDisappearingItemsElement(this);

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0012\u0010\f\u001a\u00020\b2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004J\r\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u000e\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "onAttach", "onDetach", "setAnimator", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class DisplayingDisappearingItemsNode extends Modifier.Node implements DrawModifierNode {
        private LazyLayoutItemAnimator<?> animator;

        public DisplayingDisappearingItemsNode(LazyLayoutItemAnimator<?> lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        private final LazyLayoutItemAnimator<?> component1() {
            return this.animator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DisplayingDisappearingItemsNode copy$default(DisplayingDisappearingItemsNode displayingDisappearingItemsNode, LazyLayoutItemAnimator lazyLayoutItemAnimator, int i, Object obj) {
            if ((i & 1) != 0) {
                lazyLayoutItemAnimator = displayingDisappearingItemsNode.animator;
            }
            return displayingDisappearingItemsNode.copy(lazyLayoutItemAnimator);
        }

        public final DisplayingDisappearingItemsNode copy(LazyLayoutItemAnimator<?> animator) {
            return new DisplayingDisappearingItemsNode(animator);
        }

        public void draw(ContentDrawScope contentDrawScope) {
            List list = ((LazyLayoutItemAnimator) this.animator).disappearingItems;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation = (LazyLayoutItemAnimation) list.get(i);
                GraphicsLayer layer = lazyLayoutItemAnimation.getLayer();
                if (layer != null) {
                    float f = IntOffset.getX-impl(lazyLayoutItemAnimation.getFinalOffset());
                    float f2 = IntOffset.getY-impl(lazyLayoutItemAnimation.getFinalOffset());
                    float f3 = f - IntOffset.getX-impl(layer.getTopLeft-nOcc-ac());
                    float f4 = f2 - IntOffset.getY-impl(layer.getTopLeft-nOcc-ac());
                    contentDrawScope.getDrawContext().getTransform().translate(f3, f4);
                    try {
                        GraphicsLayerKt.drawLayer(contentDrawScope, layer);
                        contentDrawScope.getDrawContext().getTransform().translate(-f3, -f4);
                    } catch (Throwable th) {
                        contentDrawScope.getDrawContext().getTransform().translate(-f3, -f4);
                        throw th;
                    }
                }
            }
            contentDrawScope.drawContent();
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DisplayingDisappearingItemsNode) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsNode) other).animator);
        }

        public int hashCode() {
            return this.animator.hashCode();
        }

        public void onAttach() {
            ((LazyLayoutItemAnimator) this.animator).displayingNode = this;
        }

        public void onDetach() {
            this.animator.reset();
        }

        public final void setAnimator(LazyLayoutItemAnimator<?> animator) {
            if (Intrinsics.areEqual(this.animator, animator) || !getNode().isAttached()) {
                return;
            }
            this.animator.reset();
            ((LazyLayoutItemAnimator) animator).displayingNode = this;
            this.animator = animator;
        }

        public String toString() {
            return "DisplayingDisappearingItemsNode(animator=" + this.animator + ')';
        }
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J=\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010+R0\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u0014\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u001e\u0010 \u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u001e\u0010\"\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014¨\u0006,"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;", "", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "value", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "animations", "getAnimations", "()[Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "getConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "setConstraints-_Sx5XlM", "(Landroidx/compose/ui/unit/Constraints;)V", "crossAxisOffset", "", "getCrossAxisOffset", "()I", "setCrossAxisOffset", "(I)V", "lane", "getLane", "setLane", "span", "getSpan", "setSpan", "isRunningPlacement", "", "()Z", "layoutMinOffset", "getLayoutMinOffset", "layoutMaxOffset", "getLayoutMaxOffset", "updateAnimation", "", "positionedItem", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;III)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class ItemInfo {
        private Constraints constraints;
        private int crossAxisOffset;
        private int lane;
        private int layoutMaxOffset;
        private int layoutMinOffset;
        private LazyLayoutItemAnimation[] animations = LazyLayoutItemAnimatorKt.EmptyArray;
        private int span = 1;

        public ItemInfo() {
        }

        private final boolean isRunningPlacement() {
            for (LazyLayoutItemAnimation lazyLayoutItemAnimation : this.animations) {
                if (lazyLayoutItemAnimation != null && lazyLayoutItemAnimation.getIsRunningMovingAwayAnimation()) {
                    return true;
                }
            }
            return false;
        }

        public static /* synthetic */ void updateAnimation$default(ItemInfo itemInfo, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 32) != 0) {
                i3 = LazyLayoutItemAnimator.this.getCrossAxisOffset(lazyLayoutMeasuredItem);
            }
            itemInfo.updateAnimation(lazyLayoutMeasuredItem, coroutineScope, graphicsContext, i, i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit updateAnimation$lambda$0$0(LazyLayoutItemAnimator lazyLayoutItemAnimator) {
            DrawModifierNode drawModifierNode = lazyLayoutItemAnimator.displayingNode;
            if (drawModifierNode != null) {
                DrawModifierNodeKt.invalidateDraw(drawModifierNode);
            }
            return Unit.INSTANCE;
        }

        public final LazyLayoutItemAnimation[] getAnimations() {
            return this.animations;
        }

        /* JADX INFO: renamed from: getConstraints-DWUhwKw, reason: not valid java name and from getter */
        public final Constraints getConstraints() {
            return this.constraints;
        }

        public final int getCrossAxisOffset() {
            return this.crossAxisOffset;
        }

        public final int getLane() {
            return this.lane;
        }

        public final int getLayoutMaxOffset() {
            return this.layoutMaxOffset;
        }

        public final int getLayoutMinOffset() {
            return this.layoutMinOffset;
        }

        public final int getSpan() {
            return this.span;
        }

        /* JADX INFO: renamed from: setConstraints-_Sx5XlM, reason: not valid java name */
        public final void m1126setConstraints_Sx5XlM(Constraints constraints) {
            this.constraints = constraints;
        }

        public final void setCrossAxisOffset(int i) {
            this.crossAxisOffset = i;
        }

        public final void setLane(int i) {
            this.lane = i;
        }

        public final void setSpan(int i) {
            this.span = i;
        }

        public final void updateAnimation(T positionedItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int layoutMinOffset, int layoutMaxOffset, int crossAxisOffset) {
            LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr;
            if (!isRunningPlacement()) {
                this.layoutMinOffset = layoutMinOffset;
                this.layoutMaxOffset = layoutMaxOffset;
            }
            int placeablesCount = positionedItem.getPlaceablesCount();
            int length = this.animations.length;
            while (true) {
                lazyLayoutItemAnimationArr = this.animations;
                if (placeablesCount >= length) {
                    break;
                }
                LazyLayoutItemAnimation lazyLayoutItemAnimation = lazyLayoutItemAnimationArr[placeablesCount];
                if (lazyLayoutItemAnimation != null) {
                    lazyLayoutItemAnimation.release();
                }
                placeablesCount++;
            }
            if (lazyLayoutItemAnimationArr.length != positionedItem.getPlaceablesCount()) {
                this.animations = (LazyLayoutItemAnimation[]) Arrays.copyOf(this.animations, positionedItem.getPlaceablesCount());
            }
            this.constraints = Constraints.box-impl(positionedItem.getConstraints());
            this.crossAxisOffset = crossAxisOffset;
            this.lane = positionedItem.getLane();
            this.span = positionedItem.getSpan();
            int placeablesCount2 = positionedItem.getPlaceablesCount();
            final LazyLayoutItemAnimator<T> lazyLayoutItemAnimator = LazyLayoutItemAnimator.this;
            for (int i = 0; i < placeablesCount2; i++) {
                LazyLayoutAnimationSpecsNode specs = LazyLayoutItemAnimatorKt.getSpecs(positionedItem.getParentData(i));
                LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr2 = this.animations;
                if (specs == null) {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation2 = lazyLayoutItemAnimationArr2[i];
                    if (lazyLayoutItemAnimation2 != null) {
                        lazyLayoutItemAnimation2.release();
                    }
                    this.animations[i] = null;
                } else {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation3 = lazyLayoutItemAnimationArr2[i];
                    if (lazyLayoutItemAnimation3 == null) {
                        lazyLayoutItemAnimation3 = new LazyLayoutItemAnimation(coroutineScope, graphicsContext, new Function0() { // from class: androidx.compose.foundation.lazy.layout.d
                            public final Object invoke() {
                                return LazyLayoutItemAnimator.ItemInfo.updateAnimation$lambda$0$0(lazyLayoutItemAnimator);
                            }
                        });
                        this.animations[i] = lazyLayoutItemAnimation3;
                    }
                    lazyLayoutItemAnimation3.setFadeInSpec(specs.getFadeInSpec());
                    lazyLayoutItemAnimation3.setPlacementSpec(specs.getPlacementSpec());
                    lazyLayoutItemAnimation3.setFadeOutSpec(specs.getFadeOutSpec());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCrossAxisOffset(LazyLayoutMeasuredItem lazyLayoutMeasuredItem) {
        long jMo1063getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo1063getOffsetBjo55l4(0);
        return !lazyLayoutMeasuredItem.getIsVertical() ? IntOffset.getY-impl(jMo1063getOffsetBjo55l4) : IntOffset.getX-impl(jMo1063getOffsetBjo55l4);
    }

    private final boolean getHasAnimations(T t) {
        int placeablesCount = t.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            if (LazyLayoutItemAnimatorKt.getSpecs(t.getParentData(i)) != null) {
                return true;
            }
        }
        return false;
    }

    private final int getMainAxisOffset(LazyLayoutMeasuredItem lazyLayoutMeasuredItem) {
        long jMo1063getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo1063getOffsetBjo55l4(0);
        return lazyLayoutMeasuredItem.getIsVertical() ? IntOffset.getY-impl(jMo1063getOffsetBjo55l4) : IntOffset.getX-impl(jMo1063getOffsetBjo55l4);
    }

    private final void initializeAnimation(T item, int mainAxisOffset, LazyLayoutItemAnimator<T>.ItemInfo itemInfo) {
        int i = 0;
        long jMo1063getOffsetBjo55l4 = item.mo1063getOffsetBjo55l4(0);
        long j = item.getIsVertical() ? IntOffset.copy-iSbpLlY$default(jMo1063getOffsetBjo55l4, 0, mainAxisOffset, 1, (Object) null) : IntOffset.copy-iSbpLlY$default(jMo1063getOffsetBjo55l4, mainAxisOffset, 0, 2, (Object) null);
        LazyLayoutItemAnimation[] animations = itemInfo.getAnimations();
        int length = animations.length;
        int i2 = 0;
        while (i < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i];
            int i3 = i2 + 1;
            if (lazyLayoutItemAnimation != null) {
                lazyLayoutItemAnimation.m1121setRawOffsetgyyYBs(IntOffset.plus-qkQi6aY(j, IntOffset.minus-qkQi6aY(item.mo1063getOffsetBjo55l4(i2), jMo1063getOffsetBjo55l4)));
            }
            i++;
            i2 = i3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void initializeAnimation$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, int i, ItemInfo itemInfo, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            LazyLayoutItemAnimator<T>.ItemInfo itemInfo2 = lazyLayoutItemAnimator.keyToItemInfoMap.get(lazyLayoutMeasuredItem.getKey());
            itemInfo2.getClass();
            itemInfo = itemInfo2;
        }
        lazyLayoutItemAnimator.initializeAnimation(lazyLayoutMeasuredItem, i, itemInfo);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0059 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x005b A[LOOP:0: B:7:0x0015->B:22:0x005b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:26:0x005e A[EDGE_INSN: B:26:0x005e->B:23:0x005e BREAK  A[LOOP:0: B:7:0x0015->B:22:0x005b], SYNTHETIC] */
    private final void releaseAnimations() {
        if (this.keyToItemInfoMap.isNotEmpty()) {
            MutableScatterMap<Object, LazyLayoutItemAnimator<T>.ItemInfo> mutableScatterMap = this.keyToItemInfoMap;
            Object[] objArr = mutableScatterMap.values;
            long[] jArr = mutableScatterMap.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i != length) {
                            break;
                            break;
                        }
                        i++;
                    } else {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                for (LazyLayoutItemAnimation lazyLayoutItemAnimation : ((ItemInfo) objArr[(i << 3) + i3]).getAnimations()) {
                                    if (lazyLayoutItemAnimation != null) {
                                        lazyLayoutItemAnimation.release();
                                    }
                                }
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        } else if (i != length) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            this.keyToItemInfoMap.clear();
        }
    }

    private final void removeInfoForKey(Object key) {
        LazyLayoutItemAnimation[] animations;
        LazyLayoutItemAnimator<T>.ItemInfo itemInfoRemove = this.keyToItemInfoMap.remove(key);
        if (itemInfoRemove == null || (animations = itemInfoRemove.getAnimations()) == null) {
            return;
        }
        for (LazyLayoutItemAnimation lazyLayoutItemAnimation : animations) {
            if (lazyLayoutItemAnimation != null) {
                lazyLayoutItemAnimation.release();
            }
        }
    }

    private final void startPlacementAnimationsIfNeeded(T item, boolean isMovingAway) {
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo = this.keyToItemInfoMap.get(item.getKey());
        itemInfo.getClass();
        LazyLayoutItemAnimation[] animations = itemInfo.getAnimations();
        int length = animations.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i];
            int i3 = i2 + 1;
            if (lazyLayoutItemAnimation != null) {
                long jMo1063getOffsetBjo55l4 = item.mo1063getOffsetBjo55l4(i2);
                long rawOffset = lazyLayoutItemAnimation.getRawOffset();
                if (!IntOffset.equals-impl0(rawOffset, LazyLayoutItemAnimation.INSTANCE.m1122getNotInitializednOccac()) && !IntOffset.equals-impl0(rawOffset, jMo1063getOffsetBjo55l4)) {
                    lazyLayoutItemAnimation.m1114animatePlacementDeltaar5cAso(IntOffset.minus-qkQi6aY(jMo1063getOffsetBjo55l4, rawOffset), isMovingAway);
                }
                lazyLayoutItemAnimation.m1121setRawOffsetgyyYBs(jMo1063getOffsetBjo55l4);
            }
            i++;
            i2 = i3;
        }
    }

    public static /* synthetic */ void startPlacementAnimationsIfNeeded$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        lazyLayoutItemAnimator.startPlacementAnimationsIfNeeded(lazyLayoutMeasuredItem, z);
    }

    private final int updateAndReturnOffsetFor(int[] iArr, T t) {
        int lane = t.getLane();
        int span = t.getSpan() + lane;
        int iMax = 0;
        while (lane < span) {
            int mainAxisSizeWithSpacings = iArr[lane] + t.getMainAxisSizeWithSpacings();
            iArr[lane] = mainAxisSizeWithSpacings;
            iMax = Math.max(iMax, mainAxisSizeWithSpacings);
            lane++;
        }
        return iMax;
    }

    public final LazyLayoutItemAnimation getAnimation(Object key, int placeableIndex) {
        LazyLayoutItemAnimation[] animations;
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo = this.keyToItemInfoMap.get(key);
        if (itemInfo == null || (animations = itemInfo.getAnimations()) == null) {
            return null;
        }
        return animations[placeableIndex];
    }

    /* JADX INFO: renamed from: getMinSizeToFitDisappearingItems-YbymL2g, reason: not valid java name */
    public final long m1123getMinSizeToFitDisappearingItemsYbymL2g() {
        long j = IntSize.Companion.getZero-YbymL2g();
        List<LazyLayoutItemAnimation> list = this.disappearingItems;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = list.get(i);
            GraphicsLayer layer = lazyLayoutItemAnimation.getLayer();
            if (layer != null) {
                int iMax = Math.max((int) (j >> 32), IntOffset.getX-impl(lazyLayoutItemAnimation.getRawOffset()) + ((int) (layer.getSize-YbymL2g() >> 32)));
                j = IntSize.constructor-impl((((long) Math.max((int) (j & 4294967295L), IntOffset.getY-impl(lazyLayoutItemAnimation.getRawOffset()) + ((int) (layer.getSize-YbymL2g() & 4294967295L)))) & 4294967295L) | (((long) iMax) << 32));
            }
        }
        return j;
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    /* JADX WARN: Code duplicated, block: B:226:0x00c7 A[EDGE_INSN: B:226:0x00c7->B:42:0x00c7 BREAK  A[LOOP:1: B:28:0x007f->B:40:0x00bc], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:39:0x00ba A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x00bc A[LOOP:1: B:28:0x007f->B:40:0x00bc, LOOP_END] */
    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<T> positionedItems, final LazyLayoutKeyIndexMap keyIndexMap, LazyLayoutMeasuredItemProvider<T> itemProvider, boolean isVertical, boolean isLookingAhead, int laneCount, boolean hasLookaheadOccurred, int layoutMinOffset, int layoutMaxOffset, CoroutineScope coroutineScope, GraphicsContext graphicsContext) {
        List<T> list;
        boolean z;
        long[] jArr;
        Object[] objArr;
        long[] jArr2;
        Object[] objArr2;
        int i;
        Object obj;
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo;
        int i2;
        int i3;
        int i4;
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap = this.keyIndexMap;
        this.keyIndexMap = keyIndexMap;
        List<T> list2 = positionedItems;
        int size = list2.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size) {
                if (!this.keyToItemInfoMap.isEmpty()) {
                    break;
                }
                releaseAnimations();
                return;
            } else if (getHasAnimations(positionedItems.get(i5))) {
                break;
            } else {
                i5++;
            }
        }
        int i6 = this.firstVisibleIndex;
        LazyLayoutMeasuredItem lazyLayoutMeasuredItem = (LazyLayoutMeasuredItem) CollectionsKt.firstOrNull(positionedItems);
        this.firstVisibleIndex = lazyLayoutMeasuredItem != null ? lazyLayoutMeasuredItem.getIndex() : 0;
        long j = isVertical ? IntOffset.constructor-impl(((long) consumedScroll) & 4294967295L) : IntOffset.constructor-impl(((long) consumedScroll) << 32);
        boolean z2 = isLookingAhead || !hasLookaheadOccurred;
        MutableScatterMap<Object, LazyLayoutItemAnimator<T>.ItemInfo> mutableScatterMap = this.keyToItemInfoMap;
        Object[] objArr3 = mutableScatterMap.keys;
        long[] jArr3 = mutableScatterMap.metadata;
        int length = jArr3.length - 2;
        if (length >= 0) {
            list = list2;
            int i7 = 0;
            while (true) {
                long j2 = jArr3[i7];
                z = z2;
                Object[] objArr4 = objArr3;
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i8 = 8 - ((~(i7 - length)) >>> 31);
                    for (int i9 = 0; i9 < i8; i9++) {
                        if ((j2 & 255) < 128) {
                            this.movingAwayKeys.add(objArr4[(i7 << 3) + i9]);
                        }
                        j2 >>= 8;
                    }
                    if (i8 != 8) {
                        break;
                    }
                    if (i7 != length) {
                        break;
                    }
                    i7++;
                    z2 = z;
                    objArr3 = objArr4;
                } else if (i7 != length) {
                    break;
                    break;
                } else {
                    i7++;
                    z2 = z;
                    objArr3 = objArr4;
                }
            }
        } else {
            list = list2;
            z = z2;
        }
        int size2 = list.size();
        int i10 = 0;
        while (i10 < size2) {
            T t = positionedItems.get(i10);
            this.movingAwayKeys.remove(t.getKey());
            if (getHasAnimations(t)) {
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo2 = this.keyToItemInfoMap.get(t.getKey());
                int index = lazyLayoutKeyIndexMap != null ? lazyLayoutKeyIndexMap.getIndex(t.getKey()) : -1;
                boolean z3 = index == -1 && lazyLayoutKeyIndexMap != null;
                if (itemInfo2 == null) {
                    LazyLayoutItemAnimator<T>.ItemInfo itemInfo3 = new ItemInfo();
                    ItemInfo.updateAnimation$default(itemInfo3, t, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, 0, 32, null);
                    i3 = size2;
                    i4 = i10;
                    this.keyToItemInfoMap.set(t.getKey(), itemInfo3);
                    if (t.getIndex() == index || index == -1) {
                        long jMo1063getOffsetBjo55l4 = t.mo1063getOffsetBjo55l4(0);
                        initializeAnimation(t, t.getIsVertical() ? IntOffset.getY-impl(jMo1063getOffsetBjo55l4) : IntOffset.getX-impl(jMo1063getOffsetBjo55l4), itemInfo3);
                        if (z3) {
                            LazyLayoutItemAnimation[] animations = itemInfo3.getAnimations();
                            for (LazyLayoutItemAnimation lazyLayoutItemAnimation : animations) {
                                if (lazyLayoutItemAnimation != null) {
                                    lazyLayoutItemAnimation.animateAppearance();
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                    } else if (index < i6) {
                        this.movingInFromStartBound.add(t);
                    } else {
                        this.movingInFromEndBound.add(t);
                    }
                } else {
                    i3 = size2;
                    i4 = i10;
                    if (z) {
                        ItemInfo.updateAnimation$default(itemInfo2, t, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, 0, 32, null);
                        LazyLayoutItemAnimation[] animations2 = itemInfo2.getAnimations();
                        int length2 = animations2.length;
                        int i11 = 0;
                        while (i11 < length2) {
                            LazyLayoutItemAnimation lazyLayoutItemAnimation2 = animations2[i11];
                            LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr = animations2;
                            int i12 = length2;
                            if (lazyLayoutItemAnimation2 != null && !IntOffset.equals-impl0(lazyLayoutItemAnimation2.getRawOffset(), LazyLayoutItemAnimation.INSTANCE.m1122getNotInitializednOccac())) {
                                lazyLayoutItemAnimation2.m1121setRawOffsetgyyYBs(IntOffset.plus-qkQi6aY(lazyLayoutItemAnimation2.getRawOffset(), j));
                            }
                            i11++;
                            animations2 = lazyLayoutItemAnimationArr;
                            length2 = i12;
                        }
                        if (z3) {
                            for (LazyLayoutItemAnimation lazyLayoutItemAnimation3 : itemInfo2.getAnimations()) {
                                if (lazyLayoutItemAnimation3 != null) {
                                    if (lazyLayoutItemAnimation3.isDisappearanceAnimationInProgress()) {
                                        this.disappearingItems.remove(lazyLayoutItemAnimation3);
                                        DrawModifierNode drawModifierNode = this.displayingNode;
                                        if (drawModifierNode != null) {
                                            DrawModifierNodeKt.invalidateDraw(drawModifierNode);
                                            Unit unit3 = Unit.INSTANCE;
                                        }
                                    }
                                    lazyLayoutItemAnimation3.animateAppearance();
                                }
                            }
                        }
                        startPlacementAnimationsIfNeeded$default(this, t, false, 2, null);
                    }
                    Unit unit4 = Unit.INSTANCE;
                }
            } else {
                i3 = size2;
                i4 = i10;
                removeInfoForKey(t.getKey());
                Unit unit5 = Unit.INSTANCE;
            }
            i10 = i4 + 1;
            size2 = i3;
        }
        int[] iArr = new int[laneCount];
        if (z && lazyLayoutKeyIndexMap != null) {
            if (!this.movingInFromStartBound.isEmpty()) {
                List<T> list3 = this.movingInFromStartBound;
                if (list3.size() > 1) {
                    CollectionsKt.sortWith(list3, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$1
                        @Override // java.util.Comparator
                        public final int compare(T t2, T t3) {
                            return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyLayoutMeasuredItem) t3).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyLayoutMeasuredItem) t2).getKey())));
                        }
                    });
                }
                List<T> list4 = this.movingInFromStartBound;
                int size3 = list4.size();
                for (int i13 = 0; i13 < size3; i13++) {
                    T t2 = list4.get(i13);
                    initializeAnimation$default(this, t2, layoutMinOffset - updateAndReturnOffsetFor(iArr, t2), null, 4, null);
                    startPlacementAnimationsIfNeeded$default(this, t2, false, 2, null);
                }
                ArraysKt.fill$default(iArr, 0, 0, 0, 6, (Object) null);
            }
            if (!this.movingInFromEndBound.isEmpty()) {
                List<T> list5 = this.movingInFromEndBound;
                if (list5.size() > 1) {
                    CollectionsKt.sortWith(list5, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t3, T t4) {
                            return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyLayoutMeasuredItem) t3).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyLayoutMeasuredItem) t4).getKey())));
                        }
                    });
                }
                List<T> list6 = this.movingInFromEndBound;
                int size4 = list6.size();
                for (int i14 = 0; i14 < size4; i14++) {
                    T t3 = list6.get(i14);
                    initializeAnimation$default(this, t3, (layoutMaxOffset + updateAndReturnOffsetFor(iArr, t3)) - t3.getMainAxisSizeWithSpacings(), null, 4, null);
                    startPlacementAnimationsIfNeeded$default(this, t3, false, 2, null);
                }
                ArraysKt.fill$default(iArr, 0, 0, 0, 6, (Object) null);
            }
        }
        MutableScatterSet<Object> mutableScatterSet = this.movingAwayKeys;
        Object[] objArr5 = mutableScatterSet.elements;
        long[] jArr4 = mutableScatterSet.metadata;
        int length3 = jArr4.length - 2;
        if (length3 >= 0) {
            int i15 = 0;
            while (true) {
                long j3 = jArr4[i15];
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i16 = 8 - ((~(i15 - length3)) >>> 31);
                    int i17 = 0;
                    while (i17 < i16) {
                        if ((j3 & 255) >= 128 || (itemInfo = this.keyToItemInfoMap.get((obj = objArr5[(i15 << 3) + i17]))) == 0) {
                            jArr2 = jArr4;
                            objArr2 = objArr5;
                            i = i17;
                        } else {
                            jArr2 = jArr4;
                            int index2 = keyIndexMap.getIndex(obj);
                            objArr2 = objArr5;
                            itemInfo.setSpan(Math.min(laneCount, itemInfo.getSpan()));
                            i = i17;
                            itemInfo.setLane(Math.min(laneCount - itemInfo.getSpan(), itemInfo.getLane()));
                            if (index2 == -1) {
                                LazyLayoutItemAnimation[] animations3 = itemInfo.getAnimations();
                                int length4 = animations3.length;
                                int i18 = 0;
                                boolean z4 = false;
                                int i19 = 0;
                                while (i18 < length4) {
                                    LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr2 = animations3;
                                    LazyLayoutItemAnimation lazyLayoutItemAnimation4 = lazyLayoutItemAnimationArr2[i18];
                                    int i20 = i19 + 1;
                                    if (lazyLayoutItemAnimation4 != null) {
                                        if (lazyLayoutItemAnimation4.isDisappearanceAnimationInProgress()) {
                                            Unit unit6 = Unit.INSTANCE;
                                            i2 = i18;
                                            z4 = true;
                                        } else if (lazyLayoutItemAnimation4.isDisappearanceAnimationFinished()) {
                                            lazyLayoutItemAnimation4.release();
                                            itemInfo.getAnimations()[i19] = null;
                                            i2 = i18;
                                            this.disappearingItems.remove(lazyLayoutItemAnimation4);
                                            DrawModifierNode drawModifierNode2 = this.displayingNode;
                                            if (drawModifierNode2 != null) {
                                                DrawModifierNodeKt.invalidateDraw(drawModifierNode2);
                                                Unit unit7 = Unit.INSTANCE;
                                            }
                                        } else {
                                            i2 = i18;
                                            if (lazyLayoutItemAnimation4.getLayer() != null) {
                                                lazyLayoutItemAnimation4.animateDisappearance();
                                            }
                                            if (lazyLayoutItemAnimation4.isDisappearanceAnimationInProgress()) {
                                                this.disappearingItems.add(lazyLayoutItemAnimation4);
                                                DrawModifierNode drawModifierNode3 = this.displayingNode;
                                                if (drawModifierNode3 != null) {
                                                    DrawModifierNodeKt.invalidateDraw(drawModifierNode3);
                                                    Unit unit8 = Unit.INSTANCE;
                                                }
                                                z4 = true;
                                            } else {
                                                lazyLayoutItemAnimation4.release();
                                                itemInfo.getAnimations()[i19] = null;
                                            }
                                            Unit unit9 = Unit.INSTANCE;
                                        }
                                        i18 = i2 + 1;
                                        animations3 = lazyLayoutItemAnimationArr2;
                                        i19 = i20;
                                    } else {
                                        i2 = i18;
                                    }
                                    i18 = i2 + 1;
                                    animations3 = lazyLayoutItemAnimationArr2;
                                    i19 = i20;
                                }
                                if (!z4) {
                                    removeInfoForKey(obj);
                                }
                                Unit unit10 = Unit.INSTANCE;
                            } else {
                                Constraints constraints = itemInfo.getConstraints();
                                constraints.getClass();
                                LazyLayoutMeasuredItem lazyLayoutMeasuredItemMo1065getAndMeasurehBUhpc = itemProvider.mo1065getAndMeasurehBUhpc(index2, itemInfo.getLane(), itemInfo.getSpan(), constraints.unbox-impl());
                                lazyLayoutMeasuredItemMo1065getAndMeasurehBUhpc.setNonScrollableItem(true);
                                LazyLayoutItemAnimation[] animations4 = itemInfo.getAnimations();
                                int length5 = animations4.length;
                                int i21 = 0;
                                while (true) {
                                    if (i21 < length5) {
                                        LazyLayoutItemAnimation lazyLayoutItemAnimation5 = animations4[i21];
                                        int i22 = length5;
                                        if (lazyLayoutItemAnimation5 != null && lazyLayoutItemAnimation5.isPlacementAnimationInProgress()) {
                                        }
                                        i21++;
                                        length5 = i22;
                                    } else if (lazyLayoutKeyIndexMap != null && index2 == lazyLayoutKeyIndexMap.getIndex(obj)) {
                                        removeInfoForKey(obj);
                                        Unit unit11 = Unit.INSTANCE;
                                    }
                                    itemInfo.updateAnimation(lazyLayoutMeasuredItemMo1065getAndMeasurehBUhpc, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, itemInfo.getCrossAxisOffset());
                                    if (index2 < this.firstVisibleIndex) {
                                        this.movingAwayToStartBound.add((T) lazyLayoutMeasuredItemMo1065getAndMeasurehBUhpc);
                                    } else {
                                        this.movingAwayToEndBound.add((T) lazyLayoutMeasuredItemMo1065getAndMeasurehBUhpc);
                                    }
                                }
                            }
                        }
                        j3 >>= 8;
                        i17 = i + 1;
                        jArr4 = jArr2;
                        objArr5 = objArr2;
                    }
                    jArr = jArr4;
                    objArr = objArr5;
                    if (i16 != 8) {
                        break;
                    }
                } else {
                    jArr = jArr4;
                    objArr = objArr5;
                }
                if (i15 == length3) {
                    break;
                }
                i15++;
                jArr4 = jArr;
                objArr5 = objArr;
            }
        }
        if (!this.movingAwayToStartBound.isEmpty()) {
            List<T> list7 = this.movingAwayToStartBound;
            if (list7.size() > 1) {
                CollectionsKt.sortWith(list7, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$2
                    @Override // java.util.Comparator
                    public final int compare(T t4, T t5) {
                        return ComparisonsKt.compareValues(Integer.valueOf(keyIndexMap.getIndex(((LazyLayoutMeasuredItem) t5).getKey())), Integer.valueOf(keyIndexMap.getIndex(((LazyLayoutMeasuredItem) t4).getKey())));
                    }
                });
            }
            List<T> list8 = this.movingAwayToStartBound;
            int size5 = list8.size();
            for (int i23 = 0; i23 < size5; i23++) {
                T t4 = list8.get(i23);
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo4 = this.keyToItemInfoMap.get(t4.getKey());
                itemInfo4.getClass();
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo5 = itemInfo4;
                t4.position((isLookingAhead ? getMainAxisOffset((LazyLayoutMeasuredItem) CollectionsKt.first(positionedItems)) : itemInfo5.getLayoutMinOffset()) - updateAndReturnOffsetFor(iArr, t4), itemInfo5.getCrossAxisOffset(), layoutWidth, layoutHeight);
                if (z) {
                    startPlacementAnimationsIfNeeded(t4, true);
                }
            }
            ArraysKt.fill$default(iArr, 0, 0, 0, 6, (Object) null);
        }
        if (!this.movingAwayToEndBound.isEmpty()) {
            List<T> list9 = this.movingAwayToEndBound;
            if (list9.size() > 1) {
                CollectionsKt.sortWith(list9, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$2
                    @Override // java.util.Comparator
                    public final int compare(T t5, T t6) {
                        return ComparisonsKt.compareValues(Integer.valueOf(keyIndexMap.getIndex(((LazyLayoutMeasuredItem) t5).getKey())), Integer.valueOf(keyIndexMap.getIndex(((LazyLayoutMeasuredItem) t6).getKey())));
                    }
                });
            }
            List<T> list10 = this.movingAwayToEndBound;
            int size6 = list10.size();
            for (int i24 = 0; i24 < size6; i24++) {
                T t5 = list10.get(i24);
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo6 = this.keyToItemInfoMap.get(t5.getKey());
                itemInfo6.getClass();
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo7 = itemInfo6;
                t5.position((itemInfo7.getLayoutMaxOffset() - t5.getMainAxisSizeWithSpacings()) + updateAndReturnOffsetFor(iArr, t5), itemInfo7.getCrossAxisOffset(), layoutWidth, layoutHeight);
                if (z) {
                    startPlacementAnimationsIfNeeded(t5, true);
                }
            }
        }
        List<T> list11 = this.movingAwayToStartBound;
        CollectionsKt.reverse(list11);
        Unit unit12 = Unit.INSTANCE;
        positionedItems.addAll(0, list11);
        positionedItems.addAll(this.movingAwayToEndBound);
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }

    public final void reset() {
        releaseAnimations();
        this.keyIndexMap = null;
        this.firstVisibleIndex = -1;
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016J\f\u0010\u000b\u001a\u00020\t*\u00020\fH\u0016J\r\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u000e\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "create", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class DisplayingDisappearingItemsElement extends ModifierNodeElement<DisplayingDisappearingItemsNode> {
        private final LazyLayoutItemAnimator<?> animator;

        public DisplayingDisappearingItemsElement(LazyLayoutItemAnimator<?> lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        private final LazyLayoutItemAnimator<?> component1() {
            return this.animator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DisplayingDisappearingItemsElement copy$default(DisplayingDisappearingItemsElement displayingDisappearingItemsElement, LazyLayoutItemAnimator lazyLayoutItemAnimator, int i, Object obj) {
            if ((i & 1) != 0) {
                lazyLayoutItemAnimator = displayingDisappearingItemsElement.animator;
            }
            return displayingDisappearingItemsElement.copy(lazyLayoutItemAnimator);
        }

        public final DisplayingDisappearingItemsElement copy(LazyLayoutItemAnimator<?> animator) {
            return new DisplayingDisappearingItemsElement(animator);
        }

        /* JADX INFO: renamed from: create, reason: merged with bridge method [inline-methods] */
        public DisplayingDisappearingItemsNode m1124create() {
            return new DisplayingDisappearingItemsNode(this.animator);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DisplayingDisappearingItemsElement) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsElement) other).animator);
        }

        public int hashCode() {
            return this.animator.hashCode();
        }

        public void inspectableProperties(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("DisplayingDisappearingItemsElement");
        }

        public String toString() {
            return "DisplayingDisappearingItemsElement(animator=" + this.animator + ')';
        }

        public void update(DisplayingDisappearingItemsNode node) {
            node.setAnimator(this.animator);
        }
    }
}
