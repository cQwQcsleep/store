package androidx.compose.material3;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.material3.internal.SystemBarsDefaultInsets_androidKt;
import androidx.compose.material3.tokens.AppBarLargeFlexibleTokens;
import androidx.compose.material3.tokens.AppBarLargeTokens;
import androidx.compose.material3.tokens.AppBarMediumFlexibleTokens;
import androidx.compose.material3.tokens.AppBarMediumTokens;
import androidx.compose.material3.tokens.AppBarSmallTokens;
import androidx.compose.material3.tokens.AppBarTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JK\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJA\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u001a\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JA\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001b\u0010\u0011J\r\u0010\u001c\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JA\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001d\u0010\u0011J\r\u0010\u001e\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JA\u0010\u001e\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001f\u0010\u0011J'\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%H\u0007¢\u0006\u0002\u0010'JK\u0010(\u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*2\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010-H\u0007¢\u0006\u0002\u0010.JU\u0010(\u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*2\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010-2\b\b\u0002\u0010/\u001a\u00020&H\u0007¢\u0006\u0002\u00100JK\u00101\u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*2\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010-H\u0007¢\u0006\u0002\u0010.R\u0018\u0010\u0012\u001a\u00020\u0005*\u00020\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00178G¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0013\u00102\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b4\u00105R\u0013\u00107\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b8\u00105R\u0013\u00109\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b:\u00105R\u0013\u0010;\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b<\u00105R\u0013\u0010=\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b>\u00105R\u0013\u0010?\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\b@\u00105R\u0013\u0010A\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\bB\u00105R\u0013\u0010C\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\bD\u00105R\u0013\u0010E\u001a\u000203¢\u0006\n\n\u0002\u00106\u001a\u0004\bF\u00105¨\u0006G"}, d2 = {"Landroidx/compose/material3/TopAppBarDefaults;", "", "<init>", "()V", "topAppBarColors", "Landroidx/compose/material3/TopAppBarColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TopAppBarColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "scrolledContainerColor", "navigationIconContentColor", "titleContentColor", "actionIconContentColor", "subtitleContentColor", "topAppBarColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarColors;", "topAppBarColors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarColors;", "defaultTopAppBarColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultTopAppBarColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/TopAppBarColors;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "centerAlignedTopAppBarColors", "centerAlignedTopAppBarColors-zjMxDiM", "mediumTopAppBarColors", "mediumTopAppBarColors-zjMxDiM", "largeTopAppBarColors", "largeTopAppBarColors-zjMxDiM", "pinnedScrollBehavior", "Landroidx/compose/material3/TopAppBarScrollBehavior;", "state", "Landroidx/compose/material3/TopAppBarState;", "canScroll", "Lkotlin/Function0;", "", "(Landroidx/compose/material3/TopAppBarState;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "enterAlwaysScrollBehavior", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "(Landroidx/compose/material3/TopAppBarState;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "reverseLayout", "(Landroidx/compose/material3/TopAppBarState;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "exitUntilCollapsedScrollBehavior", "TopAppBarExpandedHeight", "Landroidx/compose/ui/unit/Dp;", "getTopAppBarExpandedHeight-D9Ej5fM", "()F", "F", "MediumAppBarCollapsedHeight", "getMediumAppBarCollapsedHeight-D9Ej5fM", "MediumAppBarExpandedHeight", "getMediumAppBarExpandedHeight-D9Ej5fM", "MediumFlexibleAppBarWithoutSubtitleExpandedHeight", "getMediumFlexibleAppBarWithoutSubtitleExpandedHeight-D9Ej5fM", "MediumFlexibleAppBarWithSubtitleExpandedHeight", "getMediumFlexibleAppBarWithSubtitleExpandedHeight-D9Ej5fM", "LargeAppBarCollapsedHeight", "getLargeAppBarCollapsedHeight-D9Ej5fM", "LargeAppBarExpandedHeight", "getLargeAppBarExpandedHeight-D9Ej5fM", "LargeFlexibleAppBarWithoutSubtitleExpandedHeight", "getLargeFlexibleAppBarWithoutSubtitleExpandedHeight-D9Ej5fM", "LargeFlexibleAppBarWithSubtitleExpandedHeight", "getLargeFlexibleAppBarWithSubtitleExpandedHeight-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TopAppBarDefaults {
    public static final int $stable = 0;
    public static final TopAppBarDefaults INSTANCE = new TopAppBarDefaults();
    private static final float LargeAppBarCollapsedHeight;
    private static final float LargeAppBarExpandedHeight;
    private static final float LargeFlexibleAppBarWithSubtitleExpandedHeight;
    private static final float LargeFlexibleAppBarWithoutSubtitleExpandedHeight;
    private static final float MediumAppBarCollapsedHeight;
    private static final float MediumAppBarExpandedHeight;
    private static final float MediumFlexibleAppBarWithSubtitleExpandedHeight;
    private static final float MediumFlexibleAppBarWithoutSubtitleExpandedHeight;
    private static final float TopAppBarExpandedHeight;

    static {
        AppBarSmallTokens appBarSmallTokens = AppBarSmallTokens.INSTANCE;
        TopAppBarExpandedHeight = appBarSmallTokens.m1501getContainerHeightD9Ej5fM();
        MediumAppBarCollapsedHeight = appBarSmallTokens.m1501getContainerHeightD9Ej5fM();
        MediumAppBarExpandedHeight = AppBarMediumTokens.INSTANCE.m1500getContainerHeightD9Ej5fM();
        AppBarMediumFlexibleTokens appBarMediumFlexibleTokens = AppBarMediumFlexibleTokens.INSTANCE;
        MediumFlexibleAppBarWithoutSubtitleExpandedHeight = appBarMediumFlexibleTokens.m1498getContainerHeightD9Ej5fM();
        MediumFlexibleAppBarWithSubtitleExpandedHeight = appBarMediumFlexibleTokens.m1499getLargeContainerHeightD9Ej5fM();
        LargeAppBarCollapsedHeight = appBarSmallTokens.m1501getContainerHeightD9Ej5fM();
        LargeAppBarExpandedHeight = AppBarLargeTokens.INSTANCE.m1497getContainerHeightD9Ej5fM();
        AppBarLargeFlexibleTokens appBarLargeFlexibleTokens = AppBarLargeFlexibleTokens.INSTANCE;
        LargeFlexibleAppBarWithoutSubtitleExpandedHeight = appBarLargeFlexibleTokens.m1495getContainerHeightD9Ej5fM();
        LargeFlexibleAppBarWithSubtitleExpandedHeight = appBarLargeFlexibleTokens.m1496getLargeContainerHeightD9Ej5fM();
    }

    private TopAppBarDefaults() {
    }

    public static boolean a() {
        return true;
    }

    public static boolean b() {
        return true;
    }

    public static boolean c() {
        return true;
    }

    public static boolean d() {
        return true;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors()", imports = {}))
    public final TopAppBarColors centerAlignedTopAppBarColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(513940029, i, -1, "androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors (AppBar.kt:1540)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTopAppBarColors$material3;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors(containerColor, scrolledContainerColor, navigationIconContentColor, titleContentColor, actionIconContentColor)", imports = {}))
    /* JADX INFO: renamed from: centerAlignedTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m1300centerAlignedTopAppBarColorszjMxDiM(long j, long j2, long j3, long j4, long j5, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896017784, i, -1, "androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors (AppBar.kt:1570)");
        }
        TopAppBarColors topAppBarColorsM1291copytNS2XkQ$default = TopAppBarColors.m1291copytNS2XkQ$default(getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)), jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, 0L, 32, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return topAppBarColorsM1291copytNS2XkQ$default;
    }

    public final TopAppBarScrollBehavior enterAlwaysScrollBehavior(TopAppBarState topAppBarState, Function0<Boolean> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, boolean z, Composer composer, int i, int i2) {
        Composer composer2;
        if ((i2 & 1) != 0) {
            composer2 = composer;
            topAppBarState = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i2 & 2) != 0) {
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: lhe
                    public final Object invoke() {
                        return Boolean.valueOf(TopAppBarDefaults.a());
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            function0 = (Function0) objRememberedValue;
        }
        Function0<Boolean> function1 = function0;
        if ((i2 & 4) != 0) {
            animationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer2, 6);
        }
        if ((i2 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer2, 0);
        }
        if ((i2 & 16) != 0) {
            z = false;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(53729710, i, -1, "androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior (AppBar.kt:1762)");
        }
        boolean zChanged = ((((i & 14) ^ 6) > 4 && composer2.changed(topAppBarState)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer2.changed(function1)) || (i & 48) == 32) | composer2.changed(animationSpec) | composer2.changed(decayAnimationSpec) | ((((57344 & i) ^ 24576) > 16384 && composer2.changed(z)) || (i & 24576) == 16384);
        Object objRememberedValue2 = composer2.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new EnterAlwaysScrollBehavior(topAppBarState, animationSpec, decayAnimationSpec, function1, z);
            composer2.updateRememberedValue(objRememberedValue2);
        }
        EnterAlwaysScrollBehavior enterAlwaysScrollBehavior = (EnterAlwaysScrollBehavior) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return enterAlwaysScrollBehavior;
    }

    public final TopAppBarScrollBehavior exitUntilCollapsedScrollBehavior(TopAppBarState topAppBarState, Function0<Boolean> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Composer composer, int i, int i2) {
        Composer composer2;
        if ((i2 & 1) != 0) {
            composer2 = composer;
            topAppBarState = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i2 & 2) != 0) {
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: khe
                    public final Object invoke() {
                        return Boolean.valueOf(TopAppBarDefaults.d());
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            function0 = (Function0) objRememberedValue;
        }
        if ((i2 & 4) != 0) {
            animationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer2, 6);
        }
        if ((i2 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer2, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1757023234, i, -1, "androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior (AppBar.kt:1801)");
        }
        boolean zChanged = ((((i & 14) ^ 6) > 4 && composer2.changed(topAppBarState)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer2.changed(function0)) || (i & 48) == 32) | composer2.changed(animationSpec) | composer2.changed(decayAnimationSpec);
        Object objRememberedValue2 = composer2.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new ExitUntilCollapsedScrollBehavior(topAppBarState, animationSpec, decayAnimationSpec, function0);
            composer2.updateRememberedValue(objRememberedValue2);
        }
        ExitUntilCollapsedScrollBehavior exitUntilCollapsedScrollBehavior = (ExitUntilCollapsedScrollBehavior) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return exitUntilCollapsedScrollBehavior;
    }

    public final TopAppBarColors getDefaultTopAppBarColors$material3(ColorScheme colorScheme) {
        TopAppBarColors defaultTopAppBarColorsCached = colorScheme.getDefaultTopAppBarColorsCached();
        if (defaultTopAppBarColorsCached != null) {
            return defaultTopAppBarColorsCached;
        }
        AppBarTokens appBarTokens = AppBarTokens.INSTANCE;
        TopAppBarColors topAppBarColors = new TopAppBarColors(ColorSchemeKt.fromToken(colorScheme, appBarTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, appBarTokens.getOnScrollContainerColor()), ColorSchemeKt.fromToken(colorScheme, appBarTokens.getLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, appBarTokens.getTitleColor()), ColorSchemeKt.fromToken(colorScheme, appBarTokens.getTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, appBarTokens.getSubtitleColor()), null);
        colorScheme.setDefaultTopAppBarColorsCached$material3(topAppBarColors);
        return topAppBarColors;
    }

    /* JADX INFO: renamed from: getLargeAppBarCollapsedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1301getLargeAppBarCollapsedHeightD9Ej5fM() {
        return LargeAppBarCollapsedHeight;
    }

    /* JADX INFO: renamed from: getLargeAppBarExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1302getLargeAppBarExpandedHeightD9Ej5fM() {
        return LargeAppBarExpandedHeight;
    }

    /* JADX INFO: renamed from: getLargeFlexibleAppBarWithSubtitleExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1303getLargeFlexibleAppBarWithSubtitleExpandedHeightD9Ej5fM() {
        return LargeFlexibleAppBarWithSubtitleExpandedHeight;
    }

    /* JADX INFO: renamed from: getLargeFlexibleAppBarWithoutSubtitleExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1304getLargeFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM() {
        return LargeFlexibleAppBarWithoutSubtitleExpandedHeight;
    }

    /* JADX INFO: renamed from: getMediumAppBarCollapsedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1305getMediumAppBarCollapsedHeightD9Ej5fM() {
        return MediumAppBarCollapsedHeight;
    }

    /* JADX INFO: renamed from: getMediumAppBarExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1306getMediumAppBarExpandedHeightD9Ej5fM() {
        return MediumAppBarExpandedHeight;
    }

    /* JADX INFO: renamed from: getMediumFlexibleAppBarWithSubtitleExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1307getMediumFlexibleAppBarWithSubtitleExpandedHeightD9Ej5fM() {
        return MediumFlexibleAppBarWithSubtitleExpandedHeight;
    }

    /* JADX INFO: renamed from: getMediumFlexibleAppBarWithoutSubtitleExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1308getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM() {
        return MediumFlexibleAppBarWithoutSubtitleExpandedHeight;
    }

    /* JADX INFO: renamed from: getTopAppBarExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1309getTopAppBarExpandedHeightD9Ej5fM() {
        return TopAppBarExpandedHeight;
    }

    public final WindowInsets getWindowInsets(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2143182847, i, -1, "androidx.compose.material3.TopAppBarDefaults.<get-windowInsets> (AppBar.kt:1526)");
        }
        WindowInsets systemBarsForVisualComponents = SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.Companion, composer, 6);
        WindowInsetsSides.Companion companion = WindowInsetsSides.Companion;
        WindowInsets windowInsets = WindowInsetsKt.only-bOOhFvg(systemBarsForVisualComponents, WindowInsetsSides.plus-gK_yJZ4(companion.getHorizontal-JoeWqyM(), companion.getTop-JoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return windowInsets;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors()", imports = {}))
    public final TopAppBarColors largeTopAppBarColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1744932393, i, -1, "androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors (AppBar.kt:1639)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTopAppBarColors$material3;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors(containerColor, scrolledContainerColor, navigationIconContentColor, titleContentColor, actionIconContentColor)", imports = {}))
    /* JADX INFO: renamed from: largeTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m1310largeTopAppBarColorszjMxDiM(long j, long j2, long j3, long j4, long j5, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1471507700, i, -1, "androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors (AppBar.kt:1670)");
        }
        TopAppBarColors topAppBarColorsM1291copytNS2XkQ$default = TopAppBarColors.m1291copytNS2XkQ$default(getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)), jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, 0L, 32, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return topAppBarColorsM1291copytNS2XkQ$default;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors()", imports = {}))
    public final TopAppBarColors mediumTopAppBarColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1268886463, i, -1, "androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors (AppBar.kt:1589)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTopAppBarColors$material3;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use topAppBarColors instead", replaceWith = @ReplaceWith(expression = "topAppBarColors(containerColor, scrolledContainerColor, navigationIconContentColor, titleContentColor, actionIconContentColor)", imports = {}))
    /* JADX INFO: renamed from: mediumTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m1311mediumTopAppBarColorszjMxDiM(long j, long j2, long j3, long j4, long j5, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-582474442, i, -1, "androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors (AppBar.kt:1620)");
        }
        TopAppBarColors topAppBarColorsM1291copytNS2XkQ$default = TopAppBarColors.m1291copytNS2XkQ$default(getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)), jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, 0L, 32, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return topAppBarColorsM1291copytNS2XkQ$default;
    }

    public final TopAppBarScrollBehavior pinnedScrollBehavior(TopAppBarState topAppBarState, Function0<Boolean> function0, Composer composer, int i, int i2) {
        Composer composer2;
        if ((i2 & 1) != 0) {
            composer2 = composer;
            topAppBarState = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i2 & 2) != 0) {
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: mhe
                    public final Object invoke() {
                        return Boolean.valueOf(TopAppBarDefaults.c());
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            function0 = (Function0) objRememberedValue;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(286497075, i, -1, "androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior (AppBar.kt:1695)");
        }
        boolean z = ((((i & 14) ^ 6) > 4 && composer2.changed(topAppBarState)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer2.changed(function0)) || (i & 48) == 32);
        Object objRememberedValue2 = composer2.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new PinnedScrollBehavior(topAppBarState, function0);
            composer2.updateRememberedValue(objRememberedValue2);
        }
        PinnedScrollBehavior pinnedScrollBehavior = (PinnedScrollBehavior) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return pinnedScrollBehavior;
    }

    public final TopAppBarColors topAppBarColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1388520854, i, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:1444)");
        }
        TopAppBarColors defaultTopAppBarColors$material3 = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTopAppBarColors$material3;
    }

    /* JADX INFO: renamed from: topAppBarColors-5tl4gsc, reason: not valid java name */
    public final TopAppBarColors m1312topAppBarColors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i2 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1325733438, i, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:1467)");
        }
        TopAppBarColors topAppBarColorsM1293copytNS2XkQ = getDefaultTopAppBarColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1293copytNS2XkQ(jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return topAppBarColorsM1293copytNS2XkQ;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility in favor of topAppBarColors with subtitleContentColor")
    /* JADX INFO: renamed from: topAppBarColors-zjMxDiM, reason: not valid java name */
    public final /* synthetic */ TopAppBarColors m1313topAppBarColorszjMxDiM(long j, long j2, long j3, long j4, long j5, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2142919275, i, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:1499)");
        }
        TopAppBarColors topAppBarColorsM1312topAppBarColors5tl4gsc = m1312topAppBarColors5tl4gsc(jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU4, composer, (65534 & i) | ((i << 6) & 458752) | ((i << 3) & 3670016), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return topAppBarColorsM1312topAppBarColors5tl4gsc;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public final /* synthetic */ TopAppBarScrollBehavior enterAlwaysScrollBehavior(TopAppBarState topAppBarState, Function0 function0, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            topAppBarState = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, composer, 0, 7);
        }
        TopAppBarState topAppBarState2 = topAppBarState;
        if ((i2 & 2) != 0) {
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: nhe
                    public final Object invoke() {
                        return Boolean.valueOf(TopAppBarDefaults.b());
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            function0 = (Function0) objRememberedValue;
        }
        Function0 function1 = function0;
        if ((i2 & 4) != 0) {
            animationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer, 6);
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i2 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(959086674, i, -1, "androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior (AppBar.kt:1723)");
        }
        TopAppBarScrollBehavior topAppBarScrollBehaviorEnterAlwaysScrollBehavior = enterAlwaysScrollBehavior(topAppBarState2, function1, animationSpec2, decayAnimationSpec, false, composer, (i & 14) | 24576 | (i & 112) | (i & 896) | (i & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i << 3) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return topAppBarScrollBehaviorEnterAlwaysScrollBehavior;
    }
}
