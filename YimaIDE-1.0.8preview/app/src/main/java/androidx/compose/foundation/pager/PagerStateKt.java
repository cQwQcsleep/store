package androidx.compose.foundation.pager;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.compose.foundation.pager.PagerStateKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000k\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0018\u001a/\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0007¢\u0006\u0002\u0010\b\u001a(\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0003\u0010\u000b\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u001a\u0012\u0010\f\u001a\u00020\r*\u00020\u0001H\u0080@¢\u0006\u0002\u0010\u000e\u001a\u0012\u0010\u000f\u001a\u00020\r*\u00020\u0001H\u0080@¢\u0006\u0002\u0010\u000e\u001a\u0017\u0010\u001e\u001a\u00020\r2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0007H\u0082\b\u001a\u0014\u0010!\u001a\u00020\"*\u00020#2\u0006\u0010\u0006\u001a\u00020\u0003H\u0000\u001a\u0014\u0010$\u001a\u00020\"*\u00020\u001b2\u0006\u0010\u0006\u001a\u00020\u0003H\u0002\u001aO\u0010%\u001a\u00020\r*\u00020&2\u0006\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u00052\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050*2\u001d\u0010+\u001a\u0019\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0,¢\u0006\u0002\b.H\u0082@¢\u0006\u0002\u0010/\"\u0016\u0010\u0010\u001a\u00020\u0011X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013\"\u000e\u0010\u0015\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019\"\u0014\u0010\u001a\u001a\u00020\u001bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u00060"}, d2 = {"rememberPagerState", "Landroidx/compose/foundation/pager/PagerState;", "initialPage", "", "initialPageOffsetFraction", "", "pageCount", "Lkotlin/Function0;", "(IFLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/pager/PagerState;", "PagerState", "currentPage", "currentPageOffsetFraction", "animateToNextPage", "", "(Landroidx/compose/foundation/pager/PagerState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateToPreviousPage", "DefaultPositionThreshold", "Landroidx/compose/ui/unit/Dp;", "getDefaultPositionThreshold", "()F", "F", "MaxPagesForAnimateScroll", "PagesToPrefetch", "UnitDensity", "androidx/compose/foundation/pager/PagerStateKt$UnitDensity$1", "Landroidx/compose/foundation/pager/PagerStateKt$UnitDensity$1;", "EmptyLayoutInfo", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "getEmptyLayoutInfo", "()Landroidx/compose/foundation/pager/PagerMeasureResult;", "debugLog", "generateMsg", "", "calculateNewMaxScrollOffset", "", "Landroidx/compose/foundation/pager/PagerLayoutInfo;", "calculateNewMinScrollOffset", "animateScrollToPage", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "targetPage", "targetPageOffsetToSnappedPosition", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "updateTargetPage", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;IFLandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PagerStateKt {
    private static final float DefaultPositionThreshold = Dp.constructor-impl(56.0f);
    private static final PagerMeasureResult EmptyLayoutInfo;
    private static final int MaxPagesForAnimateScroll = 3;
    public static final int PagesToPrefetch = 1;
    private static final PagerStateKt$UnitDensity$1 UnitDensity;

    /* JADX WARN: Type inference failed for: r22v0, types: [androidx.compose.foundation.pager.PagerStateKt$UnitDensity$1, androidx.compose.ui.unit.Density] */
    static {
        ?? r22 = new Density() { // from class: androidx.compose.foundation.pager.PagerStateKt$UnitDensity$1
            private final float density = 1.0f;
            private final float fontScale = 1.0f;

            public float getDensity() {
                return this.density;
            }

            public float getFontScale() {
                return this.fontScale;
            }
        };
        UnitDensity = r22;
        EmptyLayoutInfo = new PagerMeasureResult(CollectionsKt.emptyList(), 0, 0, 0, Orientation.Horizontal, 0, 0, false, 0, null, null, 0.0f, 0, false, SnapPosition.Start.INSTANCE, new MeasureResult() { // from class: androidx.compose.foundation.pager.PagerStateKt$EmptyLayoutInfo$1
            private final Map<AlignmentLine, Integer> alignmentLines = MapsKt.emptyMap();
            private final int height;
            private final int width;

            public static /* synthetic */ void getAlignmentLines$annotations() {
            }

            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return this.alignmentLines;
            }

            public int getHeight() {
                return this.height;
            }

            public int getWidth() {
                return this.width;
            }

            public void placeChildren() {
            }
        }, false, null, null, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), r22, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, (Object) null), 393216, null);
    }

    public static final PagerState PagerState(int i, float f, Function0<Integer> function0) {
        return new DefaultPagerState(i, f, function0);
    }

    public static /* synthetic */ PagerState PagerState$default(int i, float f, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        return PagerState(i, f, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object animateScrollToPage(final LazyLayoutScrollScope lazyLayoutScrollScope, int i, float f, AnimationSpec<Float> animationSpec, Function2<? super ScrollScope, ? super Integer, Unit> function2, Continuation<? super Unit> continuation) {
        function2.invoke(lazyLayoutScrollScope, Boxing.boxInt(i));
        boolean z = i > lazyLayoutScrollScope.getFirstVisibleItemIndex();
        int lastVisibleItemIndex = (lazyLayoutScrollScope.getLastVisibleItemIndex() - lazyLayoutScrollScope.getFirstVisibleItemIndex()) + 1;
        if (((z && i > lazyLayoutScrollScope.getLastVisibleItemIndex()) || (!z && i < lazyLayoutScrollScope.getFirstVisibleItemIndex())) && Math.abs(i - lazyLayoutScrollScope.getFirstVisibleItemIndex()) >= 3) {
            lazyLayoutScrollScope.snapToItem(z ? RangesKt.coerceAtLeast(i - lastVisibleItemIndex, lazyLayoutScrollScope.getFirstVisibleItemIndex()) : RangesKt.coerceAtMost(lastVisibleItemIndex + i, lazyLayoutScrollScope.getFirstVisibleItemIndex()), 0);
        }
        float fCalculateDistanceTo$default = LazyLayoutScrollScope.calculateDistanceTo$default(lazyLayoutScrollScope, i, 0, 2, null) + f;
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        Object objAnimate$default = SuspendAnimationKt.animate$default(0.0f, fCalculateDistanceTo$default, 0.0f, animationSpec, new Function2() { // from class: yxa
            public final Object invoke(Object obj, Object obj2) {
                return PagerStateKt.b(floatRef, lazyLayoutScrollScope, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            }
        }, continuation, 4, null);
        return objAnimate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate$default : Unit.INSTANCE;
    }

    public static final Object animateToNextPage(PagerState pagerState, Continuation<? super Unit> continuation) {
        Object objAnimateScrollToPage$default;
        return (pagerState.getCurrentPage() + 1 >= pagerState.getPageCount() || (objAnimateScrollToPage$default = PagerState.animateScrollToPage$default(pagerState, pagerState.getCurrentPage() + 1, 0.0f, null, continuation, 6, null)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objAnimateScrollToPage$default;
    }

    public static final Object animateToPreviousPage(PagerState pagerState, Continuation<? super Unit> continuation) {
        Object objAnimateScrollToPage$default;
        return (pagerState.getCurrentPage() + (-1) < 0 || (objAnimateScrollToPage$default = PagerState.animateScrollToPage$default(pagerState, pagerState.getCurrentPage() + (-1), 0.0f, null, continuation, 6, null)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objAnimateScrollToPage$default;
    }

    public static Unit b(Ref.FloatRef floatRef, LazyLayoutScrollScope lazyLayoutScrollScope, float f, float f2) {
        floatRef.element += lazyLayoutScrollScope.scrollBy(f - floatRef.element);
        return Unit.INSTANCE;
    }

    public static final long calculateNewMaxScrollOffset(PagerLayoutInfo pagerLayoutInfo, int i) {
        long pageSpacing = (((((long) i) * ((long) (pagerLayoutInfo.getPageSpacing() + pagerLayoutInfo.getPageSize()))) + ((long) pagerLayoutInfo.getBeforeContentPadding())) + ((long) pagerLayoutInfo.getAfterContentPadding())) - ((long) pagerLayoutInfo.getPageSpacing());
        int iMo1230getViewportSizeYbymL2g = (int) (pagerLayoutInfo.getOrientation() == Orientation.Horizontal ? pagerLayoutInfo.mo1230getViewportSizeYbymL2g() >> 32 : pagerLayoutInfo.mo1230getViewportSizeYbymL2g() & 4294967295L);
        return RangesKt.coerceAtLeast(pageSpacing - ((long) (iMo1230getViewportSizeYbymL2g - RangesKt.coerceIn(pagerLayoutInfo.getSnapPosition().position(iMo1230getViewportSizeYbymL2g, pagerLayoutInfo.getPageSize(), pagerLayoutInfo.getBeforeContentPadding(), pagerLayoutInfo.getAfterContentPadding(), i - 1, i), 0, iMo1230getViewportSizeYbymL2g))), 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long calculateNewMinScrollOffset(PagerMeasureResult pagerMeasureResult, int i) {
        int iMo1230getViewportSizeYbymL2g = (int) (pagerMeasureResult.getOrientation() == Orientation.Horizontal ? pagerMeasureResult.mo1230getViewportSizeYbymL2g() >> 32 : pagerMeasureResult.mo1230getViewportSizeYbymL2g() & 4294967295L);
        return RangesKt.coerceIn(pagerMeasureResult.getSnapPosition().position(iMo1230getViewportSizeYbymL2g, pagerMeasureResult.getPageSize(), pagerMeasureResult.getBeforeContentPadding(), pagerMeasureResult.getAfterContentPadding(), 0, i), 0, iMo1230getViewportSizeYbymL2g);
    }

    private static final void debugLog(Function0<String> function0) {
    }

    public static final float getDefaultPositionThreshold() {
        return DefaultPositionThreshold;
    }

    public static final PagerMeasureResult getEmptyLayoutInfo() {
        return EmptyLayoutInfo;
    }

    public static final PagerState rememberPagerState(final int i, final float f, final Function0<Integer> function0, Composer composer, int i2, int i3) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            f = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1210768637, i2, -1, "androidx.compose.foundation.pager.rememberPagerState (PagerState.kt:93)");
        }
        Object[] objArr = new Object[0];
        Saver<DefaultPagerState, ?> saver = DefaultPagerState.INSTANCE.getSaver();
        boolean z = true;
        boolean z2 = ((((i2 & 14) ^ 6) > 4 && composer.changed(i)) || (i2 & 6) == 4) | ((((i2 & 112) ^ 48) > 32 && composer.changed(f)) || (i2 & 48) == 32);
        if ((((i2 & 896) ^ 384) <= 256 || !composer.changed(function0)) && (i2 & 384) != 256) {
            z = false;
        }
        boolean z3 = z2 | z;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Function0() { // from class: xxa
                public final Object invoke() {
                    return PagerStateKt.rememberPagerState$lambda$0$0(i, f, function0);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        DefaultPagerState defaultPagerState = (DefaultPagerState) RememberSaveableKt.rememberSaveable(objArr, saver, (Function0) objRememberedValue, composer, 0);
        defaultPagerState.getPageCountState().setValue(function0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultPagerState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DefaultPagerState rememberPagerState$lambda$0$0(int i, float f, Function0 function0) {
        return new DefaultPagerState(i, f, function0);
    }
}
