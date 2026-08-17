package androidx.compose.material3.carousel;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.compose.foundation.pager.PagerScrollScopeKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.carousel.CarouselState$animateScrollToItem$2$1", f = "CarouselState.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
public final class CarouselState$animateScrollToItem$2$1 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AnimationSpec<Float> $animationSpec;
    final /* synthetic */ int $targetPage;
    final /* synthetic */ CarouselPagerState $this_with;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarouselState$animateScrollToItem$2$1(CarouselPagerState carouselPagerState, int i, AnimationSpec<Float> animationSpec, Continuation<? super CarouselState$animateScrollToItem$2$1> continuation) {
        super(2, continuation);
        this.$this_with = carouselPagerState;
        this.$targetPage = i;
        this.$animationSpec = animationSpec;
    }

    public static Unit b(CarouselPagerState carouselPagerState, ScrollScope scrollScope, int i) {
        carouselPagerState.updateTargetPage(scrollScope, i);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CarouselState$animateScrollToItem$2$1 carouselState$animateScrollToItem$2$1 = new CarouselState$animateScrollToItem$2$1(this.$this_with, this.$targetPage, this.$animationSpec, continuation);
        carouselState$animateScrollToItem$2$1.L$0 = obj;
        return carouselState$animateScrollToItem$2$1;
    }

    public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
        return create(scrollScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LazyLayoutScrollScope LazyLayoutScrollScope = PagerScrollScopeKt.LazyLayoutScrollScope(this.$this_with, (ScrollScope) this.L$0);
            final CarouselPagerState carouselPagerState = this.$this_with;
            int i2 = this.$targetPage;
            AnimationSpec<Float> animationSpec = this.$animationSpec;
            Function2 function2 = new Function2() { // from class: androidx.compose.material3.carousel.c
                public final Object invoke(Object obj2, Object obj3) {
                    return CarouselState$animateScrollToItem$2$1.b(carouselPagerState, (ScrollScope) obj2, ((Integer) obj3).intValue());
                }
            };
            this.label = 1;
            if (CarouselStateKt.animateScrollToPage(LazyLayoutScrollScope, carouselPagerState, i2, 0.0f, animationSpec, function2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
