package androidx.compose.material3.carousel;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.material3.carousel.CarouselState;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J?\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2'\u0010\u001b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001c¢\u0006\u0002\b H\u0096@¢\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0003H\u0086@¢\u0006\u0002\u0010$J&\u0010%\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00032\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050'H\u0086@¢\u0006\u0002\u0010(R\u001a\u0010\n\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006*"}, d2 = {"Landroidx/compose/material3/carousel/CarouselState;", "Landroidx/compose/foundation/gestures/ScrollableState;", "currentItem", "", "currentItemOffsetFraction", "", "itemCount", "Lkotlin/Function0;", "<init>", "(IFLkotlin/jvm/functions/Function0;)V", "pagerState", "Landroidx/compose/material3/carousel/CarouselPagerState;", "getPagerState$material3", "()Landroidx/compose/material3/carousel/CarouselPagerState;", "setPagerState$material3", "(Landroidx/compose/material3/carousel/CarouselPagerState;)V", "isScrollInProgress", "", "()Z", "getCurrentItem", "()I", "dispatchRawDelta", "delta", "scroll", "", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollToItem", "item", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateScrollToItem", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(ILandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CarouselState implements ScrollableState {
    private CarouselPagerState pagerState;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Saver<CarouselState, ?> Saver = ListSaverKt.listSaver(new Function2() { // from class: nd1
        public final Object invoke(Object obj, Object obj2) {
            return CarouselState.c((SaverScope) obj, (CarouselState) obj2);
        }
    }, new Function1() { // from class: od1
        public final Object invoke(Object obj) {
            return CarouselState.a((List) obj);
        }
    });

    public /* synthetic */ CarouselState(int i, float f, Function0 function0, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0.0f : f, function0);
    }

    public static CarouselState a(final List list) {
        Object obj = list.get(0);
        obj.getClass();
        int iIntValue = ((Integer) obj).intValue();
        Object obj2 = list.get(1);
        obj2.getClass();
        return new CarouselState(iIntValue, ((Float) obj2).floatValue(), new Function0() { // from class: md1
            public final Object invoke() {
                return Integer.valueOf(CarouselState.b(list));
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateScrollToItem$default(CarouselState carouselState, int i, AnimationSpec animationSpec, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, (Object) null, 7, (Object) null);
        }
        return carouselState.animateScrollToItem(i, animationSpec, continuation);
    }

    public static int b(List list) {
        Object obj = list.get(2);
        obj.getClass();
        return ((Integer) obj).intValue();
    }

    public static List c(SaverScope saverScope, CarouselState carouselState) {
        return CollectionsKt.listOf(new Object[]{Integer.valueOf(carouselState.pagerState.getCurrentPage()), Float.valueOf(carouselState.pagerState.getCurrentPageOffsetFraction()), Integer.valueOf(carouselState.pagerState.getPageCount())});
    }

    public final Object animateScrollToItem(int i, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        CarouselPagerState carouselPagerState = this.pagerState;
        if ((i == carouselPagerState.getCurrentPage() && carouselPagerState.getCurrentPageOffsetFraction() == 0.0f) || carouselPagerState.getPageCount() == 0) {
            return Unit.INSTANCE;
        }
        Object objScroll$default = ScrollableState.scroll$default(carouselPagerState, (MutatePriority) null, new CarouselState$animateScrollToItem$2$1(carouselPagerState, carouselPagerState.getPageCount() > 0 ? RangesKt.coerceIn(i, 0, carouselPagerState.getPageCount() - 1) : 0, animationSpec, null), continuation, 1, (Object) null);
        return objScroll$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll$default : Unit.INSTANCE;
    }

    public float dispatchRawDelta(float delta) {
        return this.pagerState.dispatchRawDelta(delta);
    }

    public final int getCurrentItem() {
        return this.pagerState.getCurrentPage();
    }

    /* JADX INFO: renamed from: getPagerState$material3, reason: from getter */
    public final CarouselPagerState getPagerState() {
        return this.pagerState;
    }

    public boolean isScrollInProgress() {
        return this.pagerState.isScrollInProgress();
    }

    public Object scroll(MutatePriority mutatePriority, Function2<? super ScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objScroll = this.pagerState.scroll(mutatePriority, function2, continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    public final Object scrollToItem(int i, Continuation<? super Unit> continuation) {
        Object objScrollToPage = this.pagerState.scrollToPage(i, 0.0f, continuation);
        return objScrollToPage == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScrollToPage : Unit.INSTANCE;
    }

    public final void setPagerState$material3(CarouselPagerState carouselPagerState) {
        this.pagerState = carouselPagerState;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/material3/carousel/CarouselState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/carousel/CarouselState;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Saver<CarouselState, ?> getSaver() {
            return CarouselState.Saver;
        }

        private Companion() {
        }
    }

    public CarouselState(int i, float f, Function0<Integer> function0) {
        this.pagerState = new CarouselPagerState(i, f, function0);
    }
}
