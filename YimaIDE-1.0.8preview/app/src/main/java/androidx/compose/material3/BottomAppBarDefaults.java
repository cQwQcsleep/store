package androidx.compose.material3;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.BottomAppBarDefaults;
import androidx.compose.material3.internal.SystemBarsDefaultInsets_androidKt;
import androidx.compose.material3.tokens.BottomAppBarTokens;
import androidx.compose.material3.tokens.DockedToolbarTokens;
import androidx.compose.material3.tokens.FabSecondaryContainerTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JK\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&2\u0010\b\u0002\u0010(\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010)2\u0010\b\u0002\u0010+\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010,H\u0007¢\u0006\u0002\u0010-R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0007R\u0014\u0010\u0017\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0016\u0010\u0019\u001a\u00020\tX\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u001a\u0010\u000bR\u0014\u0010\u001b\u001a\u00020\u001cX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u001cX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001e¨\u0006."}, d2 = {"Landroidx/compose/material3/BottomAppBarDefaults;", "", "<init>", "()V", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "ContainerElevation", "Landroidx/compose/ui/unit/Dp;", "getContainerElevation-D9Ej5fM", "()F", "F", "ContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "bottomAppBarFabColor", "getBottomAppBarFabColor", "FlexibleContentPadding", "getFlexibleContentPadding$material3", "FlexibleBottomAppBarHeight", "getFlexibleBottomAppBarHeight-D9Ej5fM$material3", "FlexibleHorizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "getFlexibleHorizontalArrangement$material3", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "FlexibleFixedHorizontalArrangement", "getFlexibleFixedHorizontalArrangement$material3", "exitAlwaysScrollBehavior", "Landroidx/compose/material3/BottomAppBarScrollBehavior;", "state", "Landroidx/compose/material3/BottomAppBarState;", "canScroll", "Lkotlin/Function0;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "(Landroidx/compose/material3/BottomAppBarState;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomAppBarScrollBehavior;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BottomAppBarDefaults {
    public static final int $stable = 0;
    private static final float FlexibleBottomAppBarHeight;
    private static final PaddingValues FlexibleContentPadding;
    private static final Arrangement.Horizontal FlexibleFixedHorizontalArrangement;
    private static final Arrangement.Horizontal FlexibleHorizontalArrangement;
    public static final BottomAppBarDefaults INSTANCE = new BottomAppBarDefaults();
    private static final float ContainerElevation = Dp.m6022constructorimpl(0.0f);
    private static final PaddingValues ContentPadding = PaddingKt.PaddingValues-a9UjIt4$default(AppBarKt.BottomAppBarHorizontalPadding, AppBarKt.getBottomAppBarVerticalPadding(), AppBarKt.BottomAppBarHorizontalPadding, 0.0f, 8, (Object) null);

    static {
        DockedToolbarTokens dockedToolbarTokens = DockedToolbarTokens.INSTANCE;
        FlexibleContentPadding = PaddingKt.PaddingValues-a9UjIt4$default(dockedToolbarTokens.m1716getContainerLeadingSpaceD9Ej5fM(), 0.0f, dockedToolbarTokens.m1719getContainerTrailingSpaceD9Ej5fM(), 0.0f, 10, (Object) null);
        FlexibleBottomAppBarHeight = dockedToolbarTokens.m1715getContainerHeightD9Ej5fM();
        Arrangement arrangement = Arrangement.INSTANCE;
        FlexibleHorizontalArrangement = arrangement.getSpaceBetween();
        FlexibleFixedHorizontalArrangement = arrangement.spacedBy-D5KLDUw(dockedToolbarTokens.m1717getContainerMaxSpacingD9Ej5fM(), Alignment.INSTANCE.getCenterHorizontally());
    }

    private BottomAppBarDefaults() {
    }

    public static boolean a() {
        return true;
    }

    public final BottomAppBarScrollBehavior exitAlwaysScrollBehavior(BottomAppBarState bottomAppBarState, Function0<Boolean> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Composer composer, int i, int i2) {
        Composer composer2;
        if ((i2 & 1) != 0) {
            composer2 = composer;
            bottomAppBarState = AppBarKt.rememberBottomAppBarState(0.0f, 0.0f, 0.0f, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i2 & 2) != 0) {
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: yy0
                    public final Object invoke() {
                        return Boolean.valueOf(BottomAppBarDefaults.a());
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            function0 = (Function0) objRememberedValue;
        }
        if ((i2 & 4) != 0) {
            animationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composer2, 6);
        }
        if ((i2 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer2, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(457144034, i, -1, "androidx.compose.material3.BottomAppBarDefaults.exitAlwaysScrollBehavior (AppBar.kt:2208)");
        }
        boolean zChanged = ((((i & 14) ^ 6) > 4 && composer2.changed(bottomAppBarState)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer2.changed(function0)) || (i & 48) == 32) | composer2.changed(animationSpec) | composer2.changed(decayAnimationSpec);
        Object objRememberedValue2 = composer2.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new ExitAlwaysScrollBehavior(bottomAppBarState, animationSpec, decayAnimationSpec, function0);
            composer2.updateRememberedValue(objRememberedValue2);
        }
        ExitAlwaysScrollBehavior exitAlwaysScrollBehavior = (ExitAlwaysScrollBehavior) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return exitAlwaysScrollBehavior;
    }

    public final long getBottomAppBarFabColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1464561486, i, -1, "androidx.compose.material3.BottomAppBarDefaults.<get-bottomAppBarFabColor> (AppBar.kt:2155)");
        }
        long value = ColorSchemeKt.getValue(FabSecondaryContainerTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-368340078, i, -1, "androidx.compose.material3.BottomAppBarDefaults.<get-containerColor> (AppBar.kt:2128)");
        }
        long value = ColorSchemeKt.getValue(BottomAppBarTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m116getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final PaddingValues getContentPadding() {
        return ContentPadding;
    }

    /* JADX INFO: renamed from: getFlexibleBottomAppBarHeight-D9Ej5fM$material3, reason: not valid java name */
    public final float m117getFlexibleBottomAppBarHeightD9Ej5fM$material3() {
        return FlexibleBottomAppBarHeight;
    }

    public final PaddingValues getFlexibleContentPadding$material3() {
        return FlexibleContentPadding;
    }

    public final Arrangement.Horizontal getFlexibleFixedHorizontalArrangement$material3() {
        return FlexibleFixedHorizontalArrangement;
    }

    public final Arrangement.Horizontal getFlexibleHorizontalArrangement$material3() {
        return FlexibleHorizontalArrangement;
    }

    public final WindowInsets getWindowInsets(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(688896409, i, -1, "androidx.compose.material3.BottomAppBarDefaults.<get-windowInsets> (AppBar.kt:2147)");
        }
        WindowInsets systemBarsForVisualComponents = SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.Companion, composer, 6);
        WindowInsetsSides.Companion companion = WindowInsetsSides.Companion;
        WindowInsets windowInsets = WindowInsetsKt.only-bOOhFvg(systemBarsForVisualComponents, WindowInsetsSides.plus-gK_yJZ4(companion.getHorizontal-JoeWqyM(), companion.getBottom-JoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return windowInsets;
    }
}
