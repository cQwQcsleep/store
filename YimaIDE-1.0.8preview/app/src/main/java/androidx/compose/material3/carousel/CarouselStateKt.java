package androidx.compose.material3.carousel;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.material3.carousel.CarouselStateKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a%\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001aW\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00132\u001d\u0010\u0014\u001a\u0019\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u0015¢\u0006\u0002\b\u0017H\u0082@¢\u0006\u0002\u0010\u0018\u001a\u001c\u0010\u0019\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0002\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"rememberCarouselState", "Landroidx/compose/material3/carousel/CarouselState;", "initialItem", "", "itemCount", "Lkotlin/Function0;", "(ILkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/carousel/CarouselState;", "MinPageOffset", "", "MaxPageOffset", "MaxPagesForAnimateScroll", "animateScrollToPage", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "pagerState", "Landroidx/compose/foundation/pager/PagerState;", "targetPage", "targetPageOffsetToSnappedPosition", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "updateTargetPage", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;Landroidx/compose/foundation/pager/PagerState;IFLandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateScrollDistanceTo", "currentPage", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CarouselStateKt {
    public static final float MaxPageOffset = 0.5f;
    private static final int MaxPagesForAnimateScroll = 3;
    public static final float MinPageOffset = -0.5f;

    public static Unit a(Ref.FloatRef floatRef, LazyLayoutScrollScope lazyLayoutScrollScope, float f, float f2) {
        floatRef.element += lazyLayoutScrollScope.scrollBy(f - floatRef.element);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object animateScrollToPage(final LazyLayoutScrollScope lazyLayoutScrollScope, PagerState pagerState, int i, float f, AnimationSpec<Float> animationSpec, Function2<? super ScrollScope, ? super Integer, Unit> function2, Continuation<? super Unit> continuation) {
        function2.invoke(lazyLayoutScrollScope, Boxing.boxInt(i));
        boolean z = i > lazyLayoutScrollScope.getFirstVisibleItemIndex();
        int lastVisibleItemIndex = (lazyLayoutScrollScope.getLastVisibleItemIndex() - lazyLayoutScrollScope.getFirstVisibleItemIndex()) + 1;
        if (((z && i > lazyLayoutScrollScope.getLastVisibleItemIndex()) || (!z && i < lazyLayoutScrollScope.getFirstVisibleItemIndex())) && Math.abs(i - lazyLayoutScrollScope.getFirstVisibleItemIndex()) >= 3) {
            lazyLayoutScrollScope.snapToItem(z ? RangesKt.coerceAtLeast(i - lastVisibleItemIndex, lazyLayoutScrollScope.getFirstVisibleItemIndex()) : RangesKt.coerceAtMost(lastVisibleItemIndex + i, lazyLayoutScrollScope.getFirstVisibleItemIndex()), 0);
        }
        float fCalculateScrollDistanceTo = calculateScrollDistanceTo(pagerState, pagerState.getCurrentPage(), i) + f;
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        Object objAnimate$default = SuspendAnimationKt.animate$default(0.0f, fCalculateScrollDistanceTo, 0.0f, animationSpec, new Function2() { // from class: qd1
            public final Object invoke(Object obj, Object obj2) {
                return CarouselStateKt.a(floatRef, lazyLayoutScrollScope, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            }
        }, continuation, 4, (Object) null);
        return objAnimate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate$default : Unit.INSTANCE;
    }

    public static CarouselState b(int i, Function0 function0) {
        return new CarouselState(i, 0.0f, function0);
    }

    private static final float calculateScrollDistanceTo(PagerState pagerState, int i, int i2) {
        int i3 = (int) (pagerState.getLayoutInfo().getOrientation() == Orientation.Horizontal ? pagerState.getLayoutInfo().getViewportSize-YbymL2g() >> 32 : pagerState.getLayoutInfo().getViewportSize-YbymL2g() & 4294967295L);
        return ((i2 - i) * (pagerState.getLayoutInfo().getPageSize() + pagerState.getLayoutInfo().getPageSpacing())) + (pagerState.getLayoutInfo().getSnapPosition().position(i3, pagerState.getLayoutInfo().getPageSize(), pagerState.getLayoutInfo().getBeforeContentPadding(), pagerState.getLayoutInfo().getAfterContentPadding(), i, pagerState.getPageCount()) - pagerState.getLayoutInfo().getSnapPosition().position(i3, pagerState.getLayoutInfo().getPageSize(), pagerState.getLayoutInfo().getBeforeContentPadding(), pagerState.getLayoutInfo().getAfterContentPadding(), i2, pagerState.getPageCount()));
    }

    public static final CarouselState rememberCarouselState(final int i, final Function0<Integer> function0, Composer composer, int i2, int i3) {
        boolean z = true;
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-217285684, i2, -1, "androidx.compose.material3.carousel.rememberCarouselState (CarouselState.kt:151)");
        }
        Object[] objArr = new Object[0];
        Saver<CarouselState, ?> saver = CarouselState.INSTANCE.getSaver();
        boolean z2 = (((i2 & 14) ^ 6) > 4 && composer.changed(i)) || (i2 & 6) == 4;
        if ((((i2 & 112) ^ 48) <= 32 || !composer.changed(function0)) && (i2 & 48) != 32) {
            z = false;
        }
        boolean z3 = z2 | z;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: pd1
                public final Object invoke() {
                    return CarouselStateKt.b(i, function0);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        CarouselState carouselState = (CarouselState) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) saver, (Function0) objRememberedValue, composer, 0);
        carouselState.getPagerState().getPageCountState().setValue(function0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return carouselState;
    }
}
