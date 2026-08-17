package androidx.compose.material3.carousel;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt;
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider;
import androidx.compose.foundation.pager.PagerDefaults;
import androidx.compose.foundation.pager.PagerSnapDistance;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\u0010\u000bJ5\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0011R\u0013\u0010\u0012\u001a\u00020\u0013¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0017\u001a\u00020\u0013¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0018\u0010\u0015R\u0016\u0010\u0019\u001a\u00020\u0013X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u001a\u0010\u0015R\u000e\u0010\u001b\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/compose/material3/carousel/CarouselDefaults;", "", "<init>", "()V", "singleAdvanceFlingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "state", "Landroidx/compose/material3/carousel/CarouselState;", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "(Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "multiBrowseFlingBehavior", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "(Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "noSnapFlingBehavior", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "MinSmallItemSize", "Landroidx/compose/ui/unit/Dp;", "getMinSmallItemSize-D9Ej5fM", "()F", "F", "MaxSmallItemSize", "getMaxSmallItemSize-D9Ej5fM", "AnchorSize", "getAnchorSize-D9Ej5fM$material3", "MediumLargeItemDiffThreshold", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CarouselDefaults {
    public static final int $stable = 0;
    public static final float MediumLargeItemDiffThreshold = 0.85f;
    public static final CarouselDefaults INSTANCE = new CarouselDefaults();
    private static final float MinSmallItemSize = Dp.m6022constructorimpl(40.0f);
    private static final float MaxSmallItemSize = Dp.m6022constructorimpl(56.0f);
    private static final float AnchorSize = Dp.m6022constructorimpl(10.0f);

    private CarouselDefaults() {
    }

    /* JADX INFO: renamed from: getAnchorSize-D9Ej5fM$material3, reason: not valid java name */
    public final float m1352getAnchorSizeD9Ej5fM$material3() {
        return AnchorSize;
    }

    /* JADX INFO: renamed from: getMaxSmallItemSize-D9Ej5fM, reason: not valid java name */
    public final float m1353getMaxSmallItemSizeD9Ej5fM() {
        return MaxSmallItemSize;
    }

    /* JADX INFO: renamed from: getMinSmallItemSize-D9Ej5fM, reason: not valid java name */
    public final float m1354getMinSmallItemSizeD9Ej5fM() {
        return MinSmallItemSize;
    }

    public final TargetedFlingBehavior multiBrowseFlingBehavior(CarouselState carouselState, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        }
        DecayAnimationSpec<Float> decayAnimationSpec2 = decayAnimationSpec;
        if ((i2 & 4) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, (Object) null, 5, (Object) null);
        }
        AnimationSpec<Float> animationSpec2 = animationSpec;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1105043293, i, -1, "androidx.compose.material3.carousel.CarouselDefaults.multiBrowseFlingBehavior (Carousel.kt:816)");
        }
        TargetedFlingBehavior targetedFlingBehaviorFlingBehavior = PagerDefaults.INSTANCE.flingBehavior(carouselState.getPagerState(), new PagerSnapDistance() { // from class: androidx.compose.material3.carousel.CarouselDefaults$multiBrowseFlingBehavior$pagerSnapDistance$1
            public int calculateTargetPage(int startPage, int suggestedTargetPage, float velocity, int pageSize, int pageSpacing) {
                return suggestedTargetPage;
            }
        }, decayAnimationSpec2, animationSpec2, 0.0f, composer, ((i << 3) & 8064) | (PagerDefaults.$stable << 15), 16);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return targetedFlingBehaviorFlingBehavior;
    }

    public final TargetedFlingBehavior noSnapFlingBehavior(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-418415756, i, -1, "androidx.compose.material3.carousel.CarouselDefaults.noSnapFlingBehavior (Carousel.kt:845)");
        }
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SnapLayoutInfoProvider() { // from class: androidx.compose.material3.carousel.CarouselDefaults$noSnapFlingBehavior$decayLayoutInfoProvider$1$1
                public float calculateSnapOffset(float velocity) {
                    return 0.0f;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        TargetedFlingBehavior targetedFlingBehaviorRememberSnapFlingBehavior = SnapFlingBehaviorKt.rememberSnapFlingBehavior((CarouselDefaults$noSnapFlingBehavior$decayLayoutInfoProvider$1$1) objRememberedValue, composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return targetedFlingBehaviorRememberSnapFlingBehavior;
    }

    public final TargetedFlingBehavior singleAdvanceFlingBehavior(CarouselState carouselState, AnimationSpec<Float> animationSpec, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, (Object) null, 5, (Object) null);
        }
        AnimationSpec<Float> animationSpec2 = animationSpec;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1701587199, i, -1, "androidx.compose.material3.carousel.CarouselDefaults.singleAdvanceFlingBehavior (Carousel.kt:783)");
        }
        TargetedFlingBehavior targetedFlingBehaviorFlingBehavior = PagerDefaults.INSTANCE.flingBehavior(carouselState.getPagerState(), PagerSnapDistance.Companion.atMost(1), (DecayAnimationSpec) null, animationSpec2, 0.0f, composer, ((i << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | (PagerDefaults.$stable << 15), 20);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return targetedFlingBehaviorFlingBehavior;
    }
}
