package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.NavigationBarKt;
import androidx.compose.material3.internal.MappedInteractionSource;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.material3.tokens.NavigationBarVerticalItemTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\u001a_\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0085\u0001\u0010\u0012\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00142\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u00142\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007¢\u0006\u0002\u0010\u001f\u001a\u007f\u0010 \u001a\u00020\u00012\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000e2\u0011\u0010\"\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000e2\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000e2\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u000e2\u0006\u0010\u001a\u001a\u00020\u00142\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00162\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u0016H\u0003¢\u0006\u0002\u0010&\u001a5\u0010'\u001a\u00020(*\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\b\u0010-\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020/H\u0002¢\u0006\u0004\b0\u00101\u001aM\u00102\u001a\u00020(*\u00020)2\u0006\u00103\u001a\u00020+2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\b\u0010-\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u00104\u001a\u00020$H\u0002¢\u0006\u0004\b5\u00106\"\u000e\u00107\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00109\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010:\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010;\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010<\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010=\"\u0016\u0010>\u001a\u00020\bX\u0080\u0004¢\u0006\n\n\u0002\u0010=\u001a\u0004\b?\u0010@\"\u0016\u0010A\u001a\u00020\bX\u0080\u0004¢\u0006\n\n\u0002\u0010=\u001a\u0004\bB\u0010@\"\u0010\u0010C\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010=\"\u0016\u0010D\u001a\u00020\bX\u0080\u0004¢\u0006\n\n\u0002\u0010=\u001a\u0004\bE\u0010@\"\u0010\u0010F\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010=\"\u0016\u0010G\u001a\u00020\bX\u0080\u0004¢\u0006\n\n\u0002\u0010=\u001a\u0004\bH\u0010@\"\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020K0JX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010M¨\u0006N²\u0006\n\u0010O\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010P\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010Q\u001a\u00020RX\u008a\u008e\u0002"}, d2 = {"NavigationBar", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "NavigationBar-HsRjFd4", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationBarItem", "selected", "", "onClick", "Lkotlin/Function0;", NavigationBarKt.IconLayoutIdTag, "enabled", NavigationBarKt.LabelLayoutIdTag, "alwaysShowLabel", "colors", "Landroidx/compose/material3/NavigationBarItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/layout/RowScope;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/NavigationBarItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "NavigationBarItemLayout", NavigationBarKt.IndicatorRippleLayoutIdTag, NavigationBarKt.IndicatorLayoutIdTag, "alphaAnimationProgress", "", "sizeAnimationProgress", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "animationProgress", "placeLabelAndIcon-zUg2_y0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JZF)Landroidx/compose/ui/layout/MeasureResult;", "IndicatorRippleLayoutIdTag", "", "IndicatorLayoutIdTag", "IconLayoutIdTag", "LabelLayoutIdTag", "NavigationBarHeight", "F", "NavigationBarItemHorizontalPadding", "getNavigationBarItemHorizontalPadding", "()F", "NavigationBarIndicatorToLabelPadding", "getNavigationBarIndicatorToLabelPadding", "IndicatorHorizontalPadding", "IndicatorVerticalPadding", "getIndicatorVerticalPadding", "IndicatorVerticalOffset", "NavigationBarItemToIconMinimumPadding", "getNavigationBarItemToIconMinimumPadding", "LocalNavigationBarOverride", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/NavigationBarOverride;", "getLocalNavigationBarOverride", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "material3", "iconColor", "textColor", "itemWidth", ""}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationBarKt {
    private static final String IconLayoutIdTag = "icon";
    private static final float IndicatorHorizontalPadding;
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalOffset;
    private static final float IndicatorVerticalPadding;
    private static final String LabelLayoutIdTag = "label";
    private static final ProvidableCompositionLocal<NavigationBarOverride> LocalNavigationBarOverride;
    private static final float NavigationBarItemToIconMinimumPadding;
    private static final float NavigationBarHeight = NavigationBarTokens.INSTANCE.m1924getTallContainerHeightD9Ej5fM();
    private static final float NavigationBarItemHorizontalPadding = Dp.m6022constructorimpl(8.0f);
    private static final float NavigationBarIndicatorToLabelPadding = Dp.m6022constructorimpl(4.0f);

    static {
        NavigationBarVerticalItemTokens navigationBarVerticalItemTokens = NavigationBarVerticalItemTokens.INSTANCE;
        IndicatorHorizontalPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(navigationBarVerticalItemTokens.m1926getActiveIndicatorWidthD9Ej5fM() - navigationBarVerticalItemTokens.m1928getIconSizeD9Ej5fM()) / 2.0f);
        IndicatorVerticalPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(navigationBarVerticalItemTokens.m1925getActiveIndicatorHeightD9Ej5fM() - navigationBarVerticalItemTokens.m1928getIconSizeD9Ej5fM()) / 2.0f);
        IndicatorVerticalOffset = Dp.m6022constructorimpl(12.0f);
        NavigationBarItemToIconMinimumPadding = Dp.m6022constructorimpl(44.0f);
        LocalNavigationBarOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: oca
            public final Object invoke() {
                return NavigationBarKt.j();
            }
        }, 1, null);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0129  */
    /* JADX WARN: Code duplicated, block: B:105:0x013a  */
    /* JADX WARN: Code duplicated, block: B:108:0x0145  */
    /* JADX WARN: Code duplicated, block: B:111:0x0165  */
    /* JADX WARN: Code duplicated, block: B:113:0x0171  */
    /* JADX WARN: Code duplicated, block: B:116:0x017f  */
    /* JADX WARN: Code duplicated, block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0082  */
    /* JADX WARN: Code duplicated, block: B:52:0x008a  */
    /* JADX WARN: Code duplicated, block: B:53:0x008d  */
    /* JADX WARN: Code duplicated, block: B:56:0x0093  */
    /* JADX WARN: Code duplicated, block: B:59:0x009b  */
    /* JADX WARN: Code duplicated, block: B:61:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:96:0x0103  */
    /* JADX WARN: Code duplicated, block: B:99:0x010f  */
    /* JADX INFO: renamed from: NavigationBar-HsRjFd4, reason: not valid java name */
    public static final void m654NavigationBarHsRjFd4(Modifier modifier, long j, long j2, float f, WindowInsets windowInsets, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long containerColor;
        long jM277contentColorFor4WTKRHQ;
        float fM639getElevationD9Ej5fM;
        WindowInsets windowInsets2;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4;
        int i4;
        boolean z;
        Modifier modifier3;
        final long j3;
        final long j4;
        final float f2;
        final WindowInsets windowInsets3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        WindowInsets windowInsets4;
        long j5;
        long j6;
        float f3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1054099326);
        int i5 = i2 & 1;
        if (i5 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                containerColor = j;
                int i6 = composerStartRestartGroup.changed(containerColor) ? 32 : 16;
                i3 |= i6;
            } else {
                containerColor = j;
            }
            i3 |= i6;
        } else {
            containerColor = j;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                jM277contentColorFor4WTKRHQ = j2;
                int i7 = composerStartRestartGroup.changed(jM277contentColorFor4WTKRHQ) ? 256 : 128;
                i3 |= i7;
            } else {
                jM277contentColorFor4WTKRHQ = j2;
            }
            i3 |= i7;
        } else {
            jM277contentColorFor4WTKRHQ = j2;
        }
        int i8 = i2 & 8;
        if (i8 == 0) {
            if ((i & 3072) == 0) {
                fM639getElevationD9Ej5fM = f;
                i3 |= composerStartRestartGroup.changed(fM639getElevationD9Ej5fM) ? 2048 : 1024;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    windowInsets2 = windowInsets;
                    int i9 = composerStartRestartGroup.changed(windowInsets2) ? 16384 : 8192;
                    i3 |= i9;
                } else {
                    windowInsets2 = windowInsets;
                }
                i3 |= i9;
            } else {
                windowInsets2 = windowInsets;
            }
            if ((i2 & 32) != 0) {
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    function4 = function3;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i4 = 131072;
                    } else {
                        i4 = 65536;
                    }
                    i3 |= i4;
                }
                if ((74899 & i3) != 74898) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i5 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 2) != 0) {
                            containerColor = NavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -113;
                        }
                        if ((i2 & 4) != 0) {
                            jM277contentColorFor4WTKRHQ = ColorSchemeKt.m277contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), containerColor);
                            i3 &= -897;
                        }
                        if (i8 != 0) {
                            fM639getElevationD9Ej5fM = NavigationBarDefaults.INSTANCE.m639getElevationD9Ej5fM();
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            modifier3 = modifier4;
                            windowInsets4 = NavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            j5 = containerColor;
                            j6 = jM277contentColorFor4WTKRHQ;
                            f3 = fM639getElevationD9Ej5fM;
                        } else {
                            modifier3 = modifier4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1054099326, i3, -1, "androidx.compose.material3.NavigationBar (NavigationBar.kt:118)");
                        }
                        ((NavigationBarOverride) composerStartRestartGroup.consume(LocalNavigationBarOverride)).NavigationBar(new NavigationBarOverrideScope(modifier3, j5, j6, f3, windowInsets4, function4, null), composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j3 = j5;
                        j4 = j6;
                        f2 = f3;
                        windowInsets3 = windowInsets4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 2) != 0) {
                            i3 &= -113;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                        }
                        modifier3 = modifier2;
                    }
                    j5 = containerColor;
                    j6 = jM277contentColorFor4WTKRHQ;
                    f3 = fM639getElevationD9Ej5fM;
                    windowInsets4 = windowInsets2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1054099326, i3, -1, "androidx.compose.material3.NavigationBar (NavigationBar.kt:118)");
                    }
                    ((NavigationBarOverride) composerStartRestartGroup.consume(LocalNavigationBarOverride)).NavigationBar(new NavigationBarOverrideScope(modifier3, j5, j6, f3, windowInsets4, function4, null), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j5;
                    j4 = j6;
                    f2 = f3;
                    windowInsets3 = windowInsets4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = containerColor;
                    j4 = jM277contentColorFor4WTKRHQ;
                    f2 = fM639getElevationD9Ej5fM;
                    windowInsets3 = windowInsets2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier3;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.g(modifier5, j3, j4, f2, windowInsets3, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function3;
            if ((74899 & i3) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        containerColor = NavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        jM277contentColorFor4WTKRHQ = ColorSchemeKt.m277contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), containerColor);
                        i3 &= -897;
                    }
                    if (i8 != 0) {
                        fM639getElevationD9Ej5fM = NavigationBarDefaults.INSTANCE.m639getElevationD9Ej5fM();
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        modifier3 = modifier4;
                        windowInsets4 = NavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        j5 = containerColor;
                        j6 = jM277contentColorFor4WTKRHQ;
                        f3 = fM639getElevationD9Ej5fM;
                    } else {
                        modifier3 = modifier4;
                        j5 = containerColor;
                        j6 = jM277contentColorFor4WTKRHQ;
                        f3 = fM639getElevationD9Ej5fM;
                        windowInsets4 = windowInsets2;
                    }
                } else {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        containerColor = NavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        jM277contentColorFor4WTKRHQ = ColorSchemeKt.m277contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), containerColor);
                        i3 &= -897;
                    }
                    if (i8 != 0) {
                        fM639getElevationD9Ej5fM = NavigationBarDefaults.INSTANCE.m639getElevationD9Ej5fM();
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        modifier3 = modifier4;
                        windowInsets4 = NavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        j5 = containerColor;
                        j6 = jM277contentColorFor4WTKRHQ;
                        f3 = fM639getElevationD9Ej5fM;
                    } else {
                        modifier3 = modifier4;
                        j5 = containerColor;
                        j6 = jM277contentColorFor4WTKRHQ;
                        f3 = fM639getElevationD9Ej5fM;
                        windowInsets4 = windowInsets2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1054099326, i3, -1, "androidx.compose.material3.NavigationBar (NavigationBar.kt:118)");
                }
                ((NavigationBarOverride) composerStartRestartGroup.consume(LocalNavigationBarOverride)).NavigationBar(new NavigationBarOverrideScope(modifier3, j5, j6, f3, windowInsets4, function4, null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j5;
                j4 = j6;
                f2 = f3;
                windowInsets3 = windowInsets4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = containerColor;
                j4 = jM277contentColorFor4WTKRHQ;
                f2 = fM639getElevationD9Ej5fM;
                windowInsets3 = windowInsets2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qca
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationBarKt.g(modifier6, j3, j4, f2, windowInsets3, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        fM639getElevationD9Ej5fM = f;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                windowInsets2 = windowInsets;
                if (composerStartRestartGroup.changed(windowInsets2)) {
                }
                i3 |= i9;
            } else {
                windowInsets2 = windowInsets;
            }
            i3 |= i9;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i2 & 32) != 0) {
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                function4 = function3;
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i4 = 131072;
                } else {
                    i4 = 65536;
                }
                i3 |= i4;
            }
            if ((74899 & i3) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        containerColor = NavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        jM277contentColorFor4WTKRHQ = ColorSchemeKt.m277contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), containerColor);
                        i3 &= -897;
                    }
                    if (i8 != 0) {
                        fM639getElevationD9Ej5fM = NavigationBarDefaults.INSTANCE.m639getElevationD9Ej5fM();
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        modifier3 = modifier4;
                        windowInsets4 = NavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        j5 = containerColor;
                        j6 = jM277contentColorFor4WTKRHQ;
                        f3 = fM639getElevationD9Ej5fM;
                    } else {
                        modifier3 = modifier4;
                        j5 = containerColor;
                        j6 = jM277contentColorFor4WTKRHQ;
                        f3 = fM639getElevationD9Ej5fM;
                        windowInsets4 = windowInsets2;
                    }
                } else {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        containerColor = NavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        jM277contentColorFor4WTKRHQ = ColorSchemeKt.m277contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), containerColor);
                        i3 &= -897;
                    }
                    if (i8 != 0) {
                        fM639getElevationD9Ej5fM = NavigationBarDefaults.INSTANCE.m639getElevationD9Ej5fM();
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        modifier3 = modifier4;
                        windowInsets4 = NavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        j5 = containerColor;
                        j6 = jM277contentColorFor4WTKRHQ;
                        f3 = fM639getElevationD9Ej5fM;
                    } else {
                        modifier3 = modifier4;
                        j5 = containerColor;
                        j6 = jM277contentColorFor4WTKRHQ;
                        f3 = fM639getElevationD9Ej5fM;
                        windowInsets4 = windowInsets2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1054099326, i3, -1, "androidx.compose.material3.NavigationBar (NavigationBar.kt:118)");
                }
                ((NavigationBarOverride) composerStartRestartGroup.consume(LocalNavigationBarOverride)).NavigationBar(new NavigationBarOverrideScope(modifier3, j5, j6, f3, windowInsets4, function4, null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j5;
                j4 = j6;
                f2 = f3;
                windowInsets3 = windowInsets4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = containerColor;
                j4 = jM277contentColorFor4WTKRHQ;
                f2 = fM639getElevationD9Ej5fM;
                windowInsets3 = windowInsets2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier7 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qca
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationBarKt.g(modifier7, j3, j4, f2, windowInsets3, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        function4 = function3;
        if ((74899 & i3) != 74898) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    containerColor = NavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    jM277contentColorFor4WTKRHQ = ColorSchemeKt.m277contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), containerColor);
                    i3 &= -897;
                }
                if (i8 != 0) {
                    fM639getElevationD9Ej5fM = NavigationBarDefaults.INSTANCE.m639getElevationD9Ej5fM();
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    modifier3 = modifier4;
                    windowInsets4 = NavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    j5 = containerColor;
                    j6 = jM277contentColorFor4WTKRHQ;
                    f3 = fM639getElevationD9Ej5fM;
                } else {
                    modifier3 = modifier4;
                    j5 = containerColor;
                    j6 = jM277contentColorFor4WTKRHQ;
                    f3 = fM639getElevationD9Ej5fM;
                    windowInsets4 = windowInsets2;
                }
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    containerColor = NavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    jM277contentColorFor4WTKRHQ = ColorSchemeKt.m277contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), containerColor);
                    i3 &= -897;
                }
                if (i8 != 0) {
                    fM639getElevationD9Ej5fM = NavigationBarDefaults.INSTANCE.m639getElevationD9Ej5fM();
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    modifier3 = modifier4;
                    windowInsets4 = NavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    j5 = containerColor;
                    j6 = jM277contentColorFor4WTKRHQ;
                    f3 = fM639getElevationD9Ej5fM;
                } else {
                    modifier3 = modifier4;
                    j5 = containerColor;
                    j6 = jM277contentColorFor4WTKRHQ;
                    f3 = fM639getElevationD9Ej5fM;
                    windowInsets4 = windowInsets2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1054099326, i3, -1, "androidx.compose.material3.NavigationBar (NavigationBar.kt:118)");
            }
            ((NavigationBarOverride) composerStartRestartGroup.consume(LocalNavigationBarOverride)).NavigationBar(new NavigationBarOverrideScope(modifier3, j5, j6, f3, windowInsets4, function4, null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = j5;
            j4 = j6;
            f2 = f3;
            windowInsets3 = windowInsets4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = containerColor;
            j4 = jM277contentColorFor4WTKRHQ;
            f2 = fM639getElevationD9Ej5fM;
            windowInsets3 = windowInsets2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier8 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qca
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationBarKt.g(modifier8, j3, j4, f2, windowInsets3, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0110  */
    /* JADX WARN: Code duplicated, block: B:102:0x0117  */
    /* JADX WARN: Code duplicated, block: B:104:0x011b  */
    /* JADX WARN: Code duplicated, block: B:106:0x0125  */
    /* JADX WARN: Code duplicated, block: B:107:0x0128  */
    /* JADX WARN: Code duplicated, block: B:111:0x013a  */
    /* JADX WARN: Code duplicated, block: B:112:0x013c  */
    /* JADX WARN: Code duplicated, block: B:115:0x0146  */
    /* JADX WARN: Code duplicated, block: B:117:0x0151  */
    /* JADX WARN: Code duplicated, block: B:124:0x0176 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:125:0x0178  */
    /* JADX WARN: Code duplicated, block: B:127:0x017d  */
    /* JADX WARN: Code duplicated, block: B:129:0x0180  */
    /* JADX WARN: Code duplicated, block: B:131:0x0183  */
    /* JADX WARN: Code duplicated, block: B:134:0x0188  */
    /* JADX WARN: Code duplicated, block: B:135:0x0191  */
    /* JADX WARN: Code duplicated, block: B:137:0x0197  */
    /* JADX WARN: Code duplicated, block: B:139:0x019f  */
    /* JADX WARN: Code duplicated, block: B:142:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:144:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:146:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:148:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:151:0x0204  */
    /* JADX WARN: Code duplicated, block: B:152:0x0211  */
    /* JADX WARN: Code duplicated, block: B:155:0x0241  */
    /* JADX WARN: Code duplicated, block: B:158:0x0298  */
    /* JADX WARN: Code duplicated, block: B:161:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:164:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:165:0x02db  */
    /* JADX WARN: Code duplicated, block: B:168:0x02fc  */
    /* JADX WARN: Code duplicated, block: B:170:0x030a  */
    /* JADX WARN: Code duplicated, block: B:173:0x0323  */
    /* JADX WARN: Code duplicated, block: B:175:0x0327  */
    /* JADX WARN: Code duplicated, block: B:178:0x0346  */
    /* JADX WARN: Code duplicated, block: B:179:0x0349  */
    /* JADX WARN: Code duplicated, block: B:182:0x03aa  */
    /* JADX WARN: Code duplicated, block: B:184:0x03b0  */
    /* JADX WARN: Code duplicated, block: B:187:0x03e0  */
    /* JADX WARN: Code duplicated, block: B:189:0x03e6  */
    /* JADX WARN: Code duplicated, block: B:192:0x03fa  */
    /* JADX WARN: Code duplicated, block: B:194:0x0400  */
    /* JADX WARN: Code duplicated, block: B:197:0x0429  */
    /* JADX WARN: Code duplicated, block: B:199:0x0436  */
    /* JADX WARN: Code duplicated, block: B:202:0x0448  */
    /* JADX WARN: Code duplicated, block: B:204:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:0x0099  */
    /* JADX WARN: Code duplicated, block: B:58:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:82:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:94:0x0101 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:97:0x0108  */
    public static final void NavigationBarItem(final RowScope rowScope, final boolean z, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean z2, Function2<? super Composer, ? super Integer, Unit> function3, boolean z3, NavigationBarItemColors navigationBarItemColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        Function0<Unit> function1;
        final Modifier modifier2;
        int i4;
        boolean z4;
        int i5;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function4;
        int i7;
        int i8;
        boolean z5;
        char c;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z6;
        final NavigationBarItemColors navigationBarItemColors2;
        Composer composer2;
        final boolean z7;
        final boolean z8;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final MutableInteractionSource mutableInteractionSource2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        NavigationBarItemColors navigationBarItemColorsColors;
        int i13;
        MutableInteractionSource mutableInteractionSource3;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function6;
        int i14;
        NavigationBarItemColors navigationBarItemColors3;
        MutableInteractionSource mutableInteractionSource4;
        final FiniteAnimationSpec finiteAnimationSpecValue;
        MutableInteractionSource mutableInteractionSource5;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2 function8;
        Object objRememberedValue;
        Composer.Companion companion;
        final MutableIntState mutableIntState;
        Object objRememberedValue2;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        float f;
        final State stateAnimateFloatAsState;
        float f2;
        final State stateAnimateFloatAsState2;
        long jM2881constructorimpl;
        boolean zChanged;
        Object objRememberedValue3;
        boolean zChanged2;
        Object objRememberedValue4;
        boolean zChanged3;
        Object objRememberedValue5;
        Object objRememberedValue6;
        Composer composerStartRestartGroup = composer.startRestartGroup(974293026);
        if ((Integer.MIN_VALUE & i2) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(rowScope) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 1) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i2 & 2) != 0) {
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
            }
        }
        if ((i2 & 4) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        int i15 = i2 & 8;
        if (i15 == 0) {
            if ((i & 24576) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 16384 : 8192;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((196608 & i) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i5 = 131072;
                    } else {
                        i5 = 65536;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((1572864 & i) == 0) {
                        function4 = function3;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i7 = 1048576;
                        } else {
                            i7 = 524288;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        z5 = z3;
                        if ((i & 12582912) == 0) {
                            c = ' ';
                            if (composerStartRestartGroup.changed(z5)) {
                                i9 = 8388608;
                            } else {
                                i9 = 4194304;
                            }
                            i3 |= i9;
                        }
                        if ((i & 100663296) != 0) {
                            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        }
                        i10 = i2 & 256;
                        if (i10 != 0) {
                            if ((i & 805306368) == 0) {
                                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                    i11 = 536870912;
                                } else {
                                    i11 = 268435456;
                                }
                                i3 |= i11;
                            }
                            i12 = i3;
                            if ((i3 & 306783379) != 306783378) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i15 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i4 != 0) {
                                        z4 = true;
                                    }
                                    if (i6 != 0) {
                                        function4 = null;
                                    }
                                    if (i8 != 0) {
                                        z5 = true;
                                    }
                                    if ((i2 & 128) != 0) {
                                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                        i13 = i12 & (-234881025);
                                    } else {
                                        navigationBarItemColorsColors = navigationBarItemColors;
                                        i13 = i12;
                                    }
                                    if (i10 != 0) {
                                        mutableInteractionSource3 = null;
                                    } else {
                                        mutableInteractionSource3 = mutableInteractionSource;
                                    }
                                    modifier3 = modifier2;
                                    function6 = function4;
                                    i14 = i13;
                                    navigationBarItemColors3 = navigationBarItemColorsColors;
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    if ((i2 & 128) != 0) {
                                        mutableInteractionSource3 = mutableInteractionSource;
                                        z4 = z4;
                                        z5 = z5;
                                        modifier3 = modifier2;
                                        function6 = function4;
                                        i14 = i12 & (-234881025);
                                        navigationBarItemColors3 = navigationBarItemColors;
                                    } else {
                                        navigationBarItemColors3 = navigationBarItemColors;
                                        mutableInteractionSource3 = mutableInteractionSource;
                                        z4 = z4;
                                        z5 = z5;
                                        modifier3 = modifier2;
                                        function6 = function4;
                                        i14 = i12;
                                    }
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                                }
                                if (mutableInteractionSource3 == null) {
                                    composerStartRestartGroup.startReplaceGroup(-224963495);
                                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                                    }
                                    composerStartRestartGroup.endReplaceGroup();
                                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(-7257538);
                                    composerStartRestartGroup.endReplaceGroup();
                                    mutableInteractionSource4 = mutableInteractionSource3;
                                }
                                MotionSchemeKeyTokens motionSchemeKeyTokens = MotionSchemeKeyTokens.DefaultEffects;
                                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
                                mutableInteractionSource5 = mutableInteractionSource4;
                                boolean z9 = z5;
                                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                                if (function6 == null) {
                                    composerStartRestartGroup.startReplaceGroup(-224036658);
                                    composerStartRestartGroup.endReplaceGroup();
                                    function7 = function6;
                                    function8 = null;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(-224036657);
                                    final NavigationBarItemColors navigationBarItemColors4 = navigationBarItemColors3;
                                    final boolean z10 = z4;
                                    final Function2<? super Composer, ? super Integer, Unit> function9 = function6;
                                    function7 = function9;
                                    Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                        private static final long invoke$lambda$0(State<Color> state) {
                                            return state.getValue().m3144unboximpl();
                                        }

                                        public final void invoke(Composer composer3, int i16) {
                                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                            }
                                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors4.m651textColorWaAFU9c$material3(z, z10), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function9, composer3, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composerStartRestartGroup, 54);
                                    composerStartRestartGroup.endReplaceGroup();
                                    function8 = function2RememberComposableLambda;
                                }
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                companion = Composer.INSTANCE;
                                if (objRememberedValue == companion.getEmpty()) {
                                    objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                mutableIntState = (MutableIntState) objRememberedValue;
                                boolean z11 = z4;
                                Modifier modifier4 = modifier3;
                                Function2 function10 = function8;
                                Modifier modifierWeight$default = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier4, z, mutableInteractionSource5, (Indication) null, z11, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == companion.getEmpty()) {
                                    objRememberedValue2 = new Function1() { // from class: rca
                                        public final Object invoke(Object obj) {
                                            return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                Modifier modifierOnSizeChanged = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default, (Function1) objRememberedValue2);
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged);
                                ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                                constructor = companion2.getConstructor();
                                if (composerStartRestartGroup.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composerStartRestartGroup.startReusableNode();
                                if (composerStartRestartGroup.getInserting()) {
                                    composerStartRestartGroup.createNode(constructor);
                                } else {
                                    composerStartRestartGroup.useNode();
                                }
                                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                                NavigationBarItemColors navigationBarItemColors5 = navigationBarItemColors3;
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                                setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                if (z) {
                                    f = 1.0f;
                                } else {
                                    f = 0.0f;
                                }
                                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                                if (z) {
                                    f2 = 1.0f;
                                } else {
                                    f2 = 0.0f;
                                }
                                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                                Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                                Unit unit = Unit.INSTANCE;
                                zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                                if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                                }
                                final MappedInteractionSource mappedInteractionSource = (MappedInteractionSource) objRememberedValue3;
                                ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                    public final void invoke(Composer composer3, int i16) {
                                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                        }
                                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                ComposableLambda composableLambdaRememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors5), composerStartRestartGroup, 54);
                                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                if (zChanged2 || objRememberedValue4 == companion.getEmpty()) {
                                    objRememberedValue4 = new Function0() { // from class: sca
                                        public final Object invoke() {
                                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                }
                                Function0 function11 = (Function0) objRememberedValue4;
                                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                                if (zChanged3 || objRememberedValue5 == companion.getEmpty()) {
                                    objRememberedValue5 = new Function0() { // from class: tca
                                        public final Object invoke() {
                                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                                }
                                NavigationBarItemLayout(composableLambdaRememberComposableLambda2, composableLambdaRememberComposableLambda3, composableLambdaRememberComposableLambda, function10, z9, function11, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                                composerStartRestartGroup.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                navigationBarItemColors2 = navigationBarItemColors5;
                                z8 = z9;
                                composer2 = composerStartRestartGroup;
                                modifier2 = modifier4;
                                mutableInteractionSource2 = mutableInteractionSource3;
                                function5 = function7;
                                z7 = z11;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                navigationBarItemColors2 = navigationBarItemColors;
                                composer2 = composerStartRestartGroup;
                                z7 = z4;
                                z8 = z5;
                                function5 = function4;
                                mutableInteractionSource2 = mutableInteractionSource;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                                    public final Object invoke(Object obj, Object obj2) {
                                        return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i3 |= 805306368;
                        i12 = i3;
                        if ((i3 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            } else {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224963495);
                                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-7257538);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens2 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens2, composerStartRestartGroup, 6);
                            mutableInteractionSource5 = mutableInteractionSource4;
                            boolean z12 = z5;
                            ComposableLambda composableLambdaRememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                            if (function6 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224036658);
                                composerStartRestartGroup.endReplaceGroup();
                                function7 = function6;
                                function8 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-224036657);
                                final NavigationBarItemColors navigationBarItemColors6 = navigationBarItemColors3;
                                final boolean z13 = z4;
                                final Function2<? super Composer, ? super Integer, Unit> function12 = function6;
                                function7 = function12;
                                Function2 function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i16) {
                                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors6.m651textColorWaAFU9c$material3(z, z13), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function12, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function2RememberComposableLambda2;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableIntState = (MutableIntState) objRememberedValue;
                            boolean z14 = z4;
                            Modifier modifier5 = modifier3;
                            Function2 function13 = function8;
                            Modifier modifierWeight$default2 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier5, z, mutableInteractionSource5, (Indication) null, z14, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == companion.getEmpty()) {
                                objRememberedValue2 = new Function1() { // from class: rca
                                    public final Object invoke(Object obj) {
                                        return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Modifier modifierOnSizeChanged2 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default2, (Function1) objRememberedValue2);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged2);
                            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                            constructor = companion3.getConstructor();
                            if (composerStartRestartGroup.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(constructor);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                            NavigationBarItemColors navigationBarItemColors7 = navigationBarItemColors3;
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, companion3.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier2, companion3.getSetModifier());
                            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens2, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density2 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density2.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density2.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                            Unit unit2 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            final MappedInteractionSource mappedInteractionSource2 = (MappedInteractionSource) objRememberedValue3;
                            ComposableLambda composableLambdaRememberComposableLambda5 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource2, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda6 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors7), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            } else {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            Function0 function14 = (Function0) objRememberedValue4;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            } else {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            }
                            NavigationBarItemLayout(composableLambdaRememberComposableLambda5, composableLambdaRememberComposableLambda6, composableLambdaRememberComposableLambda4, function13, z12, function14, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationBarItemColors2 = navigationBarItemColors7;
                            z8 = z12;
                            composer2 = composerStartRestartGroup;
                            modifier2 = modifier5;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            function5 = function7;
                            z7 = z14;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            navigationBarItemColors2 = navigationBarItemColors;
                            composer2 = composerStartRestartGroup;
                            z7 = z4;
                            z8 = z5;
                            function5 = function4;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    z5 = z3;
                    c = ' ';
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = 536870912;
                            } else {
                                i11 = 268435456;
                            }
                            i3 |= i11;
                        }
                        i12 = i3;
                        if ((i3 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            } else {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224963495);
                                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-7257538);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens3 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens3, composerStartRestartGroup, 6);
                            mutableInteractionSource5 = mutableInteractionSource4;
                            boolean z15 = z5;
                            ComposableLambda composableLambdaRememberComposableLambda7 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                            if (function6 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224036658);
                                composerStartRestartGroup.endReplaceGroup();
                                function7 = function6;
                                function8 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-224036657);
                                final NavigationBarItemColors navigationBarItemColors8 = navigationBarItemColors3;
                                final boolean z16 = z4;
                                final Function2<? super Composer, ? super Integer, Unit> function15 = function6;
                                function7 = function15;
                                Function2 function2RememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i16) {
                                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors8.m651textColorWaAFU9c$material3(z, z16), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function15, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function2RememberComposableLambda3;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableIntState = (MutableIntState) objRememberedValue;
                            boolean z17 = z4;
                            Modifier modifier6 = modifier3;
                            Function2 function16 = function8;
                            Modifier modifierWeight$default3 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier6, z, mutableInteractionSource5, (Indication) null, z17, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == companion.getEmpty()) {
                                objRememberedValue2 = new Function1() { // from class: rca
                                    public final Object invoke(Object obj) {
                                        return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Modifier modifierOnSizeChanged3 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default3, (Function1) objRememberedValue2);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged3);
                            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                            constructor = companion4.getConstructor();
                            if (composerStartRestartGroup.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(constructor);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                            NavigationBarItemColors navigationBarItemColors9 = navigationBarItemColors3;
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, companion4.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap3, companion4.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion4.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier3, companion4.getSetModifier());
                            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens3, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density3 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density3.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density3.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                            Unit unit3 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            final MappedInteractionSource mappedInteractionSource3 = (MappedInteractionSource) objRememberedValue3;
                            ComposableLambda composableLambdaRememberComposableLambda8 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource3, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda9 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors9), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            } else {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            Function0 function17 = (Function0) objRememberedValue4;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            } else {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            }
                            NavigationBarItemLayout(composableLambdaRememberComposableLambda8, composableLambdaRememberComposableLambda9, composableLambdaRememberComposableLambda7, function16, z15, function17, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationBarItemColors2 = navigationBarItemColors9;
                            z8 = z15;
                            composer2 = composerStartRestartGroup;
                            modifier2 = modifier6;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            function5 = function7;
                            z7 = z17;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            navigationBarItemColors2 = navigationBarItemColors;
                            composer2 = composerStartRestartGroup;
                            z7 = z4;
                            z8 = z5;
                            function5 = function4;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 805306368;
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens4 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens4, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z18 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda10 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors10 = navigationBarItemColors3;
                            final boolean z19 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function18 = function6;
                            function7 = function18;
                            Function2 function2RememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors10.m651textColorWaAFU9c$material3(z, z19), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function18, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda4;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z110 = z4;
                        Modifier modifier7 = modifier3;
                        Function2 function19 = function8;
                        Modifier modifierWeight$default4 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier7, z, mutableInteractionSource5, (Indication) null, z110, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged4 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default4, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged4);
                        ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
                        constructor = companion5.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors11 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, companion5.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion5.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion5.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion5.getSetModifier());
                        BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens4, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density4 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density4.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density4.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit4 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource4 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda11 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda12 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors11), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function110 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda11, composableLambdaRememberComposableLambda12, composableLambdaRememberComposableLambda10, function19, z18, function110, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors11;
                        z8 = z18;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z110;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                function4 = function3;
                i8 = i2 & 64;
                if (i8 != 0) {
                    z5 = z3;
                    if ((i & 12582912) == 0) {
                        c = ' ';
                        if (composerStartRestartGroup.changed(z5)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = 536870912;
                            } else {
                                i11 = 268435456;
                            }
                            i3 |= i11;
                        }
                        i12 = i3;
                        if ((i3 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            } else {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224963495);
                                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-7257538);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens5 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens5, composerStartRestartGroup, 6);
                            mutableInteractionSource5 = mutableInteractionSource4;
                            boolean z111 = z5;
                            ComposableLambda composableLambdaRememberComposableLambda13 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                            if (function6 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224036658);
                                composerStartRestartGroup.endReplaceGroup();
                                function7 = function6;
                                function8 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-224036657);
                                final NavigationBarItemColors navigationBarItemColors12 = navigationBarItemColors3;
                                final boolean z112 = z4;
                                final Function2<? super Composer, ? super Integer, Unit> function111 = function6;
                                function7 = function111;
                                Function2 function2RememberComposableLambda5 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i16) {
                                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors12.m651textColorWaAFU9c$material3(z, z112), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function2RememberComposableLambda5;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableIntState = (MutableIntState) objRememberedValue;
                            boolean z113 = z4;
                            Modifier modifier8 = modifier3;
                            Function2 function112 = function8;
                            Modifier modifierWeight$default5 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier8, z, mutableInteractionSource5, (Indication) null, z113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == companion.getEmpty()) {
                                objRememberedValue2 = new Function1() { // from class: rca
                                    public final Object invoke(Object obj) {
                                        return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Modifier modifierOnSizeChanged5 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default5, (Function1) objRememberedValue2);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged5);
                            ComposeUiNode.Companion companion6 = ComposeUiNode.INSTANCE;
                            constructor = companion6.getConstructor();
                            if (composerStartRestartGroup.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(constructor);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                            NavigationBarItemColors navigationBarItemColors13 = navigationBarItemColors3;
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy5, companion6.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap5, companion6.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion6.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier5, companion6.getSetModifier());
                            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens5, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density5 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density5.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density5.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                            Unit unit5 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            final MappedInteractionSource mappedInteractionSource5 = (MappedInteractionSource) objRememberedValue3;
                            ComposableLambda composableLambdaRememberComposableLambda14 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource5, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda15 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors13), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            } else {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            Function0 function113 = (Function0) objRememberedValue4;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            } else {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            }
                            NavigationBarItemLayout(composableLambdaRememberComposableLambda14, composableLambdaRememberComposableLambda15, composableLambdaRememberComposableLambda13, function112, z111, function113, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationBarItemColors2 = navigationBarItemColors13;
                            z8 = z111;
                            composer2 = composerStartRestartGroup;
                            modifier2 = modifier8;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            function5 = function7;
                            z7 = z113;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            navigationBarItemColors2 = navigationBarItemColors;
                            composer2 = composerStartRestartGroup;
                            z7 = z4;
                            z8 = z5;
                            function5 = function4;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 805306368;
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens6 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens6, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z114 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda16 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors14 = navigationBarItemColors3;
                            final boolean z115 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function114 = function6;
                            function7 = function114;
                            Function2 function2RememberComposableLambda6 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors14.m651textColorWaAFU9c$material3(z, z115), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function114, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda6;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z116 = z4;
                        Modifier modifier9 = modifier3;
                        Function2 function115 = function8;
                        Modifier modifierWeight$default6 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier9, z, mutableInteractionSource5, (Indication) null, z116, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged6 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default6, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged6);
                        ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
                        constructor = companion7.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors15 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy6, companion7.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap6, companion7.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion7.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier6, companion7.getSetModifier());
                        BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens6, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density6 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density6.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density6.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit6 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource6 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda17 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource6, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda18 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors15), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function116 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda17, composableLambdaRememberComposableLambda18, composableLambdaRememberComposableLambda16, function115, z114, function116, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors15;
                        z8 = z114;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier9;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z116;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                z5 = z3;
                c = ' ';
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens7 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens7, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z117 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda19 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors16 = navigationBarItemColors3;
                            final boolean z118 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function117 = function6;
                            function7 = function117;
                            Function2 function2RememberComposableLambda7 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors16.m651textColorWaAFU9c$material3(z, z118), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function117, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda7;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z119 = z4;
                        Modifier modifier10 = modifier3;
                        Function2 function118 = function8;
                        Modifier modifierWeight$default7 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier10, z, mutableInteractionSource5, (Indication) null, z119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged7 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default7, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged7);
                        ComposeUiNode.Companion companion8 = ComposeUiNode.INSTANCE;
                        constructor = companion8.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors17 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, companion8.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap7, companion8.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion8.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier7, companion8.getSetModifier());
                        BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens7, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density7 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density7.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density7.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit7 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource7 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda110 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource7, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda111 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors17), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function119 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda110, composableLambdaRememberComposableLambda111, composableLambdaRememberComposableLambda19, function118, z117, function119, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors17;
                        z8 = z117;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier10;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z119;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens8 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens8, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z1110 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda112 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors18 = navigationBarItemColors3;
                        final boolean z1111 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function1110 = function6;
                        function7 = function1110;
                        Function2 function2RememberComposableLambda8 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors18.m651textColorWaAFU9c$material3(z, z1111), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1110, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda8;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z1112 = z4;
                    Modifier modifier11 = modifier3;
                    Function2 function1111 = function8;
                    Modifier modifierWeight$default8 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier11, z, mutableInteractionSource5, (Indication) null, z1112, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged8 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default8, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged8);
                    ComposeUiNode.Companion companion9 = ComposeUiNode.INSTANCE;
                    constructor = companion9.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors19 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy8, companion9.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap8, companion9.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion9.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier8, companion9.getSetModifier());
                    BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens8, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density8 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density8.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density8.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit8 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource8 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda113 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource8, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda114 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors19), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function1112 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda113, composableLambdaRememberComposableLambda114, composableLambdaRememberComposableLambda112, function1111, z1110, function1112, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors19;
                    z8 = z1110;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier11;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z1112;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z4 = z2;
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((1572864 & i) == 0) {
                    function4 = function3;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i7 = 1048576;
                    } else {
                        i7 = 524288;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    z5 = z3;
                    if ((i & 12582912) == 0) {
                        c = ' ';
                        if (composerStartRestartGroup.changed(z5)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = 536870912;
                            } else {
                                i11 = 268435456;
                            }
                            i3 |= i11;
                        }
                        i12 = i3;
                        if ((i3 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            } else {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224963495);
                                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-7257538);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens9 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens9, composerStartRestartGroup, 6);
                            mutableInteractionSource5 = mutableInteractionSource4;
                            boolean z1113 = z5;
                            ComposableLambda composableLambdaRememberComposableLambda115 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                            if (function6 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224036658);
                                composerStartRestartGroup.endReplaceGroup();
                                function7 = function6;
                                function8 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-224036657);
                                final NavigationBarItemColors navigationBarItemColors110 = navigationBarItemColors3;
                                final boolean z1114 = z4;
                                final Function2<? super Composer, ? super Integer, Unit> function1113 = function6;
                                function7 = function1113;
                                Function2 function2RememberComposableLambda9 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i16) {
                                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors110.m651textColorWaAFU9c$material3(z, z1114), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1113, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function2RememberComposableLambda9;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableIntState = (MutableIntState) objRememberedValue;
                            boolean z1115 = z4;
                            Modifier modifier12 = modifier3;
                            Function2 function1114 = function8;
                            Modifier modifierWeight$default9 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier12, z, mutableInteractionSource5, (Indication) null, z1115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == companion.getEmpty()) {
                                objRememberedValue2 = new Function1() { // from class: rca
                                    public final Object invoke(Object obj) {
                                        return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Modifier modifierOnSizeChanged9 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default9, (Function1) objRememberedValue2);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy9 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged9);
                            ComposeUiNode.Companion companion10 = ComposeUiNode.INSTANCE;
                            constructor = companion10.getConstructor();
                            if (composerStartRestartGroup.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(constructor);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                            NavigationBarItemColors navigationBarItemColors111 = navigationBarItemColors3;
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy9, companion10.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap9, companion10.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion10.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier9, companion10.getSetModifier());
                            BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens9, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density9 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density9.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density9.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                            Unit unit9 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            final MappedInteractionSource mappedInteractionSource9 = (MappedInteractionSource) objRememberedValue3;
                            ComposableLambda composableLambdaRememberComposableLambda116 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource9, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda117 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors111), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            } else {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            Function0 function1115 = (Function0) objRememberedValue4;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            } else {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            }
                            NavigationBarItemLayout(composableLambdaRememberComposableLambda116, composableLambdaRememberComposableLambda117, composableLambdaRememberComposableLambda115, function1114, z1113, function1115, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationBarItemColors2 = navigationBarItemColors111;
                            z8 = z1113;
                            composer2 = composerStartRestartGroup;
                            modifier2 = modifier12;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            function5 = function7;
                            z7 = z1115;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            navigationBarItemColors2 = navigationBarItemColors;
                            composer2 = composerStartRestartGroup;
                            z7 = z4;
                            z8 = z5;
                            function5 = function4;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 805306368;
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens10 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens10, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z1116 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda118 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors112 = navigationBarItemColors3;
                            final boolean z1117 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function1116 = function6;
                            function7 = function1116;
                            Function2 function2RememberComposableLambda10 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors112.m651textColorWaAFU9c$material3(z, z1117), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1116, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda10;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z1118 = z4;
                        Modifier modifier13 = modifier3;
                        Function2 function1117 = function8;
                        Modifier modifierWeight$default10 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier13, z, mutableInteractionSource5, (Indication) null, z1118, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged10 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default10, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy10 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged10);
                        ComposeUiNode.Companion companion11 = ComposeUiNode.INSTANCE;
                        constructor = companion11.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors113 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy10, companion11.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap10, companion11.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion11.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier10, companion11.getSetModifier());
                        BoxScopeInstance boxScopeInstance10 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens10, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density10 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density10.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density10.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit10 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource10 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda119 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource10, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda1110 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors113), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function1118 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda119, composableLambdaRememberComposableLambda1110, composableLambdaRememberComposableLambda118, function1117, z1116, function1118, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors113;
                        z8 = z1116;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier13;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z1118;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                z5 = z3;
                c = ' ';
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens11 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens11, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z1119 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda1111 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors114 = navigationBarItemColors3;
                            final boolean z11110 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function1119 = function6;
                            function7 = function1119;
                            Function2 function2RememberComposableLambda11 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors114.m651textColorWaAFU9c$material3(z, z11110), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1119, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda11;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z11111 = z4;
                        Modifier modifier14 = modifier3;
                        Function2 function11110 = function8;
                        Modifier modifierWeight$default11 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier14, z, mutableInteractionSource5, (Indication) null, z11111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged11 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default11, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy11 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged11);
                        ComposeUiNode.Companion companion12 = ComposeUiNode.INSTANCE;
                        constructor = companion12.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors115 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy11, companion12.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap11, companion12.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion12.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier11, companion12.getSetModifier());
                        BoxScopeInstance boxScopeInstance11 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens11, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density11 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density11.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density11.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit11 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource11 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda1112 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource11, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda1113 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors115), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function11111 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda1112, composableLambdaRememberComposableLambda1113, composableLambdaRememberComposableLambda1111, function11110, z1119, function11111, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors115;
                        z8 = z1119;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier14;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z11111;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens12 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens12, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z11112 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda1114 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors116 = navigationBarItemColors3;
                        final boolean z11113 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function11112 = function6;
                        function7 = function11112;
                        Function2 function2RememberComposableLambda12 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors116.m651textColorWaAFU9c$material3(z, z11113), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11112, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda12;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z11114 = z4;
                    Modifier modifier15 = modifier3;
                    Function2 function11113 = function8;
                    Modifier modifierWeight$default12 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier15, z, mutableInteractionSource5, (Indication) null, z11114, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged12 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default12, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy12 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged12);
                    ComposeUiNode.Companion companion13 = ComposeUiNode.INSTANCE;
                    constructor = companion13.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors117 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy12, companion13.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap12, companion13.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion13.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier12, companion13.getSetModifier());
                    BoxScopeInstance boxScopeInstance12 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens12, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density12 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density12.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density12.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit12 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource12 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda1115 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource12, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1116 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors117), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function11114 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda1115, composableLambdaRememberComposableLambda1116, composableLambdaRememberComposableLambda1114, function11113, z11112, function11114, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors117;
                    z8 = z11112;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier15;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z11114;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            function4 = function3;
            i8 = i2 & 64;
            if (i8 != 0) {
                z5 = z3;
                if ((i & 12582912) == 0) {
                    c = ' ';
                    if (composerStartRestartGroup.changed(z5)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens13 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens13, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z11115 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda1117 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors118 = navigationBarItemColors3;
                            final boolean z11116 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function11115 = function6;
                            function7 = function11115;
                            Function2 function2RememberComposableLambda13 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors118.m651textColorWaAFU9c$material3(z, z11116), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11115, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda13;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z11117 = z4;
                        Modifier modifier16 = modifier3;
                        Function2 function11116 = function8;
                        Modifier modifierWeight$default13 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier16, z, mutableInteractionSource5, (Indication) null, z11117, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged13 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default13, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy13 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged13);
                        ComposeUiNode.Companion companion14 = ComposeUiNode.INSTANCE;
                        constructor = companion14.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors119 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy13, companion14.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap13, companion14.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion14.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier13, companion14.getSetModifier());
                        BoxScopeInstance boxScopeInstance13 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens13, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density13 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density13.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density13.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit13 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource13 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda1118 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource13, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda1119 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors119), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function11117 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda1118, composableLambdaRememberComposableLambda1119, composableLambdaRememberComposableLambda1117, function11116, z11115, function11117, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors119;
                        z8 = z11115;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier16;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z11117;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens14 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens14, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z11118 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda11110 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors1110 = navigationBarItemColors3;
                        final boolean z11119 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function11118 = function6;
                        function7 = function11118;
                        Function2 function2RememberComposableLambda14 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors1110.m651textColorWaAFU9c$material3(z, z11119), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11118, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda14;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z111110 = z4;
                    Modifier modifier17 = modifier3;
                    Function2 function11119 = function8;
                    Modifier modifierWeight$default14 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier17, z, mutableInteractionSource5, (Indication) null, z111110, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged14 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default14, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy14 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged14);
                    ComposeUiNode.Companion companion15 = ComposeUiNode.INSTANCE;
                    constructor = companion15.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors1111 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy14, companion15.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap14, companion15.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion15.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier14, companion15.getSetModifier());
                    BoxScopeInstance boxScopeInstance14 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens14, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density14 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density14.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density14.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit14 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource14 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda11111 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource14, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda11112 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors1111), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function111110 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda11111, composableLambdaRememberComposableLambda11112, composableLambdaRememberComposableLambda11110, function11119, z11118, function111110, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors1111;
                    z8 = z11118;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier17;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z111110;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            z5 = z3;
            c = ' ';
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens15 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens15, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z111111 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda11113 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors1112 = navigationBarItemColors3;
                        final boolean z111112 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function111111 = function6;
                        function7 = function111111;
                        Function2 function2RememberComposableLambda15 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors1112.m651textColorWaAFU9c$material3(z, z111112), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111111, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda15;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z111113 = z4;
                    Modifier modifier18 = modifier3;
                    Function2 function111112 = function8;
                    Modifier modifierWeight$default15 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier18, z, mutableInteractionSource5, (Indication) null, z111113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged15 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default15, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy15 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged15);
                    ComposeUiNode.Companion companion16 = ComposeUiNode.INSTANCE;
                    constructor = companion16.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors1113 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy15, companion16.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap15, companion16.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion16.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier15, companion16.getSetModifier());
                    BoxScopeInstance boxScopeInstance15 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens15, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density15 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density15.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density15.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit15 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource15 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda11114 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource15, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda11115 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors1113), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function111113 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda11114, composableLambdaRememberComposableLambda11115, composableLambdaRememberComposableLambda11113, function111112, z111111, function111113, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors1113;
                    z8 = z111111;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier18;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z111113;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            i12 = i3;
            if ((i3 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224963495);
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-7257538);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens16 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens16, composerStartRestartGroup, 6);
                mutableInteractionSource5 = mutableInteractionSource4;
                boolean z111114 = z5;
                ComposableLambda composableLambdaRememberComposableLambda11116 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                if (function6 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224036658);
                    composerStartRestartGroup.endReplaceGroup();
                    function7 = function6;
                    function8 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-224036657);
                    final NavigationBarItemColors navigationBarItemColors1114 = navigationBarItemColors3;
                    final boolean z111115 = z4;
                    final Function2<? super Composer, ? super Integer, Unit> function111114 = function6;
                    function7 = function111114;
                    Function2 function2RememberComposableLambda16 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors1114.m651textColorWaAFU9c$material3(z, z111115), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111114, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function2RememberComposableLambda16;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableIntState = (MutableIntState) objRememberedValue;
                boolean z111116 = z4;
                Modifier modifier19 = modifier3;
                Function2 function111115 = function8;
                Modifier modifierWeight$default16 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier19, z, mutableInteractionSource5, (Indication) null, z111116, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: rca
                        public final Object invoke(Object obj) {
                            return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierOnSizeChanged16 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default16, (Function1) objRememberedValue2);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy16 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged16);
                ComposeUiNode.Companion companion17 = ComposeUiNode.INSTANCE;
                constructor = companion17.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                NavigationBarItemColors navigationBarItemColors1115 = navigationBarItemColors3;
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy16, companion17.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap16, companion17.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion17.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier16, companion17.getSetModifier());
                BoxScopeInstance boxScopeInstance16 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens16, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density16 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density16.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density16.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                Unit unit16 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final MappedInteractionSource mappedInteractionSource16 = (MappedInteractionSource) objRememberedValue3;
                ComposableLambda composableLambdaRememberComposableLambda11117 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource16, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda11118 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors1115), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                Function0 function111116 = (Function0) objRememberedValue4;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                NavigationBarItemLayout(composableLambdaRememberComposableLambda11117, composableLambdaRememberComposableLambda11118, composableLambdaRememberComposableLambda11116, function111115, z111114, function111116, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationBarItemColors2 = navigationBarItemColors1115;
                z8 = z111114;
                composer2 = composerStartRestartGroup;
                modifier2 = modifier19;
                mutableInteractionSource2 = mutableInteractionSource3;
                function5 = function7;
                z7 = z111116;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                navigationBarItemColors2 = navigationBarItemColors;
                composer2 = composerStartRestartGroup;
                z7 = z4;
                z8 = z5;
                function5 = function4;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        modifier2 = modifier;
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((196608 & i) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i5 = 131072;
                } else {
                    i5 = 65536;
                }
                i3 |= i5;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((1572864 & i) == 0) {
                    function4 = function3;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i7 = 1048576;
                    } else {
                        i7 = 524288;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    z5 = z3;
                    if ((i & 12582912) == 0) {
                        c = ' ';
                        if (composerStartRestartGroup.changed(z5)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i & 100663296) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = 536870912;
                            } else {
                                i11 = 268435456;
                            }
                            i3 |= i11;
                        }
                        i12 = i3;
                        if ((i3 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            } else {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i13 = i12 & (-234881025);
                                } else {
                                    navigationBarItemColorsColors = navigationBarItemColors;
                                    i13 = i12;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                i14 = i13;
                                navigationBarItemColors3 = navigationBarItemColorsColors;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224963495);
                                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-7257538);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens17 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens17, composerStartRestartGroup, 6);
                            mutableInteractionSource5 = mutableInteractionSource4;
                            boolean z111117 = z5;
                            ComposableLambda composableLambdaRememberComposableLambda11119 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                            if (function6 == null) {
                                composerStartRestartGroup.startReplaceGroup(-224036658);
                                composerStartRestartGroup.endReplaceGroup();
                                function7 = function6;
                                function8 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-224036657);
                                final NavigationBarItemColors navigationBarItemColors1116 = navigationBarItemColors3;
                                final boolean z111118 = z4;
                                final Function2<? super Composer, ? super Integer, Unit> function111117 = function6;
                                function7 = function111117;
                                Function2 function2RememberComposableLambda17 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i16) {
                                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors1116.m651textColorWaAFU9c$material3(z, z111118), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111117, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function2RememberComposableLambda17;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableIntState = (MutableIntState) objRememberedValue;
                            boolean z111119 = z4;
                            Modifier modifier110 = modifier3;
                            Function2 function111118 = function8;
                            Modifier modifierWeight$default17 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier110, z, mutableInteractionSource5, (Indication) null, z111119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == companion.getEmpty()) {
                                objRememberedValue2 = new Function1() { // from class: rca
                                    public final Object invoke(Object obj) {
                                        return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Modifier modifierOnSizeChanged17 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default17, (Function1) objRememberedValue2);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy17 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap17 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier17 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged17);
                            ComposeUiNode.Companion companion18 = ComposeUiNode.INSTANCE;
                            constructor = companion18.getConstructor();
                            if (composerStartRestartGroup.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(constructor);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                            NavigationBarItemColors navigationBarItemColors1117 = navigationBarItemColors3;
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy17, companion18.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap17, companion18.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion18.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier17, companion18.getSetModifier());
                            BoxScopeInstance boxScopeInstance17 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens17, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density17 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density17.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density17.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                            Unit unit17 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            final MappedInteractionSource mappedInteractionSource17 = (MappedInteractionSource) objRememberedValue3;
                            ComposableLambda composableLambdaRememberComposableLambda111110 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource17, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda111111 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors1117), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            } else {
                                objRememberedValue4 = new Function0() { // from class: sca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            Function0 function111119 = (Function0) objRememberedValue4;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            } else {
                                objRememberedValue5 = new Function0() { // from class: tca
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            }
                            NavigationBarItemLayout(composableLambdaRememberComposableLambda111110, composableLambdaRememberComposableLambda111111, composableLambdaRememberComposableLambda11119, function111118, z111117, function111119, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationBarItemColors2 = navigationBarItemColors1117;
                            z8 = z111117;
                            composer2 = composerStartRestartGroup;
                            modifier2 = modifier110;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            function5 = function7;
                            z7 = z111119;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            navigationBarItemColors2 = navigationBarItemColors;
                            composer2 = composerStartRestartGroup;
                            z7 = z4;
                            z8 = z5;
                            function5 = function4;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 805306368;
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens18 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens18, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z1111110 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda111112 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors1118 = navigationBarItemColors3;
                            final boolean z1111111 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function1111110 = function6;
                            function7 = function1111110;
                            Function2 function2RememberComposableLambda18 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors1118.m651textColorWaAFU9c$material3(z, z1111111), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111110, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda18;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z1111112 = z4;
                        Modifier modifier111 = modifier3;
                        Function2 function1111111 = function8;
                        Modifier modifierWeight$default18 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier111, z, mutableInteractionSource5, (Indication) null, z1111112, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged18 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default18, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy18 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap18 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier18 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged18);
                        ComposeUiNode.Companion companion19 = ComposeUiNode.INSTANCE;
                        constructor = companion19.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors1119 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy18, companion19.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap18, companion19.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion19.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier18, companion19.getSetModifier());
                        BoxScopeInstance boxScopeInstance18 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens18, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density18 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density18.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density18.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit18 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource18 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda111113 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource18, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda111114 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors1119), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function1111112 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda111113, composableLambdaRememberComposableLambda111114, composableLambdaRememberComposableLambda111112, function1111111, z1111110, function1111112, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors1119;
                        z8 = z1111110;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier111;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z1111112;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                z5 = z3;
                c = ' ';
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens19 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens19, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z1111113 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda111115 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors11110 = navigationBarItemColors3;
                            final boolean z1111114 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function1111113 = function6;
                            function7 = function1111113;
                            Function2 function2RememberComposableLambda19 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors11110.m651textColorWaAFU9c$material3(z, z1111114), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111113, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda19;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z1111115 = z4;
                        Modifier modifier112 = modifier3;
                        Function2 function1111114 = function8;
                        Modifier modifierWeight$default19 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier112, z, mutableInteractionSource5, (Indication) null, z1111115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged19 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default19, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy19 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap19 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier19 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged19);
                        ComposeUiNode.Companion companion110 = ComposeUiNode.INSTANCE;
                        constructor = companion110.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors11111 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy19, companion110.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap19, companion110.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion110.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier19, companion110.getSetModifier());
                        BoxScopeInstance boxScopeInstance19 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens19, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density19 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density19.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density19.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit19 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource19 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda111116 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource19, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda111117 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors11111), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function1111115 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda111116, composableLambdaRememberComposableLambda111117, composableLambdaRememberComposableLambda111115, function1111114, z1111113, function1111115, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors11111;
                        z8 = z1111113;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier112;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z1111115;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens110 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens110, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z1111116 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda111118 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors11112 = navigationBarItemColors3;
                        final boolean z1111117 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function1111116 = function6;
                        function7 = function1111116;
                        Function2 function2RememberComposableLambda110 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors11112.m651textColorWaAFU9c$material3(z, z1111117), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111116, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda110;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z1111118 = z4;
                    Modifier modifier113 = modifier3;
                    Function2 function1111117 = function8;
                    Modifier modifierWeight$default110 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier113, z, mutableInteractionSource5, (Indication) null, z1111118, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged110 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default110, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy110 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged110);
                    ComposeUiNode.Companion companion111 = ComposeUiNode.INSTANCE;
                    constructor = companion111.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors11113 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy110, companion111.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap110, companion111.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion111.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier110, companion111.getSetModifier());
                    BoxScopeInstance boxScopeInstance110 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens110, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density110 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density110.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density110.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit110 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource110 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda111119 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource110, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1111110 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors11113), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function1111118 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda111119, composableLambdaRememberComposableLambda1111110, composableLambdaRememberComposableLambda111118, function1111117, z1111116, function1111118, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors11113;
                    z8 = z1111116;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier113;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z1111118;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            function4 = function3;
            i8 = i2 & 64;
            if (i8 != 0) {
                z5 = z3;
                if ((i & 12582912) == 0) {
                    c = ' ';
                    if (composerStartRestartGroup.changed(z5)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens111 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens111, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z1111119 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda1111111 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors11114 = navigationBarItemColors3;
                            final boolean z11111110 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function1111119 = function6;
                            function7 = function1111119;
                            Function2 function2RememberComposableLambda111 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors11114.m651textColorWaAFU9c$material3(z, z11111110), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111119, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda111;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z11111111 = z4;
                        Modifier modifier114 = modifier3;
                        Function2 function11111110 = function8;
                        Modifier modifierWeight$default111 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier114, z, mutableInteractionSource5, (Indication) null, z11111111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged111 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default111, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy111 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged111);
                        ComposeUiNode.Companion companion112 = ComposeUiNode.INSTANCE;
                        constructor = companion112.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors11115 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy111, companion112.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap111, companion112.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion112.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier111, companion112.getSetModifier());
                        BoxScopeInstance boxScopeInstance111 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens111, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density111 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density111.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density111.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit111 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource111 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda1111112 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource111, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda1111113 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors11115), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function11111111 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda1111112, composableLambdaRememberComposableLambda1111113, composableLambdaRememberComposableLambda1111111, function11111110, z1111119, function11111111, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors11115;
                        z8 = z1111119;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier114;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z11111111;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens112 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens112, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z11111112 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda1111114 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors11116 = navigationBarItemColors3;
                        final boolean z11111113 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function11111112 = function6;
                        function7 = function11111112;
                        Function2 function2RememberComposableLambda112 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors11116.m651textColorWaAFU9c$material3(z, z11111113), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11111112, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda112;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z11111114 = z4;
                    Modifier modifier115 = modifier3;
                    Function2 function11111113 = function8;
                    Modifier modifierWeight$default112 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier115, z, mutableInteractionSource5, (Indication) null, z11111114, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged112 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default112, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy112 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap112 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier112 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged112);
                    ComposeUiNode.Companion companion113 = ComposeUiNode.INSTANCE;
                    constructor = companion113.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors11117 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy112, companion113.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap112, companion113.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion113.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier112, companion113.getSetModifier());
                    BoxScopeInstance boxScopeInstance112 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens112, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density112 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density112.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density112.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit112 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource112 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda1111115 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource112, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1111116 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors11117), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function11111114 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda1111115, composableLambdaRememberComposableLambda1111116, composableLambdaRememberComposableLambda1111114, function11111113, z11111112, function11111114, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors11117;
                    z8 = z11111112;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier115;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z11111114;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            z5 = z3;
            c = ' ';
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens113 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens113, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z11111115 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda1111117 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors11118 = navigationBarItemColors3;
                        final boolean z11111116 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function11111115 = function6;
                        function7 = function11111115;
                        Function2 function2RememberComposableLambda113 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors11118.m651textColorWaAFU9c$material3(z, z11111116), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11111115, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda113;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z11111117 = z4;
                    Modifier modifier116 = modifier3;
                    Function2 function11111116 = function8;
                    Modifier modifierWeight$default113 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier116, z, mutableInteractionSource5, (Indication) null, z11111117, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged113 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default113, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy113 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap113 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier113 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged113);
                    ComposeUiNode.Companion companion114 = ComposeUiNode.INSTANCE;
                    constructor = companion114.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors11119 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy113, companion114.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap113, companion114.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion114.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier113, companion114.getSetModifier());
                    BoxScopeInstance boxScopeInstance113 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens113, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density113 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density113.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density113.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit113 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource113 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda1111118 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource113, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1111119 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors11119), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function11111117 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda1111118, composableLambdaRememberComposableLambda1111119, composableLambdaRememberComposableLambda1111117, function11111116, z11111115, function11111117, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors11119;
                    z8 = z11111115;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier116;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z11111117;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            i12 = i3;
            if ((i3 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224963495);
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-7257538);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens114 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens114, composerStartRestartGroup, 6);
                mutableInteractionSource5 = mutableInteractionSource4;
                boolean z11111118 = z5;
                ComposableLambda composableLambdaRememberComposableLambda11111110 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                if (function6 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224036658);
                    composerStartRestartGroup.endReplaceGroup();
                    function7 = function6;
                    function8 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-224036657);
                    final NavigationBarItemColors navigationBarItemColors111110 = navigationBarItemColors3;
                    final boolean z11111119 = z4;
                    final Function2<? super Composer, ? super Integer, Unit> function11111118 = function6;
                    function7 = function11111118;
                    Function2 function2RememberComposableLambda114 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors111110.m651textColorWaAFU9c$material3(z, z11111119), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11111118, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function2RememberComposableLambda114;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableIntState = (MutableIntState) objRememberedValue;
                boolean z111111110 = z4;
                Modifier modifier117 = modifier3;
                Function2 function11111119 = function8;
                Modifier modifierWeight$default114 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier117, z, mutableInteractionSource5, (Indication) null, z111111110, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: rca
                        public final Object invoke(Object obj) {
                            return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierOnSizeChanged114 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default114, (Function1) objRememberedValue2);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy114 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap114 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier114 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged114);
                ComposeUiNode.Companion companion115 = ComposeUiNode.INSTANCE;
                constructor = companion115.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                NavigationBarItemColors navigationBarItemColors111111 = navigationBarItemColors3;
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy114, companion115.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap114, companion115.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion115.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier114, companion115.getSetModifier());
                BoxScopeInstance boxScopeInstance114 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens114, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density114 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density114.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density114.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                Unit unit114 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final MappedInteractionSource mappedInteractionSource114 = (MappedInteractionSource) objRememberedValue3;
                ComposableLambda composableLambdaRememberComposableLambda11111111 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource114, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda11111112 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors111111), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                Function0 function111111110 = (Function0) objRememberedValue4;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                NavigationBarItemLayout(composableLambdaRememberComposableLambda11111111, composableLambdaRememberComposableLambda11111112, composableLambdaRememberComposableLambda11111110, function11111119, z11111118, function111111110, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationBarItemColors2 = navigationBarItemColors111111;
                z8 = z11111118;
                composer2 = composerStartRestartGroup;
                modifier2 = modifier117;
                mutableInteractionSource2 = mutableInteractionSource3;
                function5 = function7;
                z7 = z111111110;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                navigationBarItemColors2 = navigationBarItemColors;
                composer2 = composerStartRestartGroup;
                z7 = z4;
                z8 = z5;
                function5 = function4;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        z4 = z2;
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((1572864 & i) == 0) {
                function4 = function3;
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i7 = 1048576;
                } else {
                    i7 = 524288;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                z5 = z3;
                if ((i & 12582912) == 0) {
                    c = ' ';
                    if (composerStartRestartGroup.changed(z5)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i & 100663296) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i3 |= i11;
                    }
                    i12 = i3;
                    if ((i3 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i13 = i12 & (-234881025);
                            } else {
                                navigationBarItemColorsColors = navigationBarItemColors;
                                i13 = i12;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            i14 = i13;
                            navigationBarItemColors3 = navigationBarItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224963495);
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-7257538);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens115 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens115, composerStartRestartGroup, 6);
                        mutableInteractionSource5 = mutableInteractionSource4;
                        boolean z111111111 = z5;
                        ComposableLambda composableLambdaRememberComposableLambda11111113 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                        if (function6 == null) {
                            composerStartRestartGroup.startReplaceGroup(-224036658);
                            composerStartRestartGroup.endReplaceGroup();
                            function7 = function6;
                            function8 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-224036657);
                            final NavigationBarItemColors navigationBarItemColors111112 = navigationBarItemColors3;
                            final boolean z111111112 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function111111111 = function6;
                            function7 = function111111111;
                            Function2 function2RememberComposableLambda115 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors111112.m651textColorWaAFU9c$material3(z, z111111112), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111111111, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function2RememberComposableLambda115;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableIntState = (MutableIntState) objRememberedValue;
                        boolean z111111113 = z4;
                        Modifier modifier118 = modifier3;
                        Function2 function111111112 = function8;
                        Modifier modifierWeight$default115 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier118, z, mutableInteractionSource5, (Indication) null, z111111113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: rca
                                public final Object invoke(Object obj) {
                                    return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierOnSizeChanged115 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default115, (Function1) objRememberedValue2);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy115 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap115 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier115 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged115);
                        ComposeUiNode.Companion companion116 = ComposeUiNode.INSTANCE;
                        constructor = companion116.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        NavigationBarItemColors navigationBarItemColors111113 = navigationBarItemColors3;
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy115, companion116.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap115, companion116.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion116.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier115, companion116.getSetModifier());
                        BoxScopeInstance boxScopeInstance115 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens115, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density115 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density115.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density115.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                        Unit unit115 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        final MappedInteractionSource mappedInteractionSource115 = (MappedInteractionSource) objRememberedValue3;
                        ComposableLambda composableLambdaRememberComposableLambda11111114 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource115, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda11111115 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors111113), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        } else {
                            objRememberedValue4 = new Function0() { // from class: sca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function0 function111111113 = (Function0) objRememberedValue4;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        } else {
                            objRememberedValue5 = new Function0() { // from class: tca
                                public final Object invoke() {
                                    return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        NavigationBarItemLayout(composableLambdaRememberComposableLambda11111114, composableLambdaRememberComposableLambda11111115, composableLambdaRememberComposableLambda11111113, function111111112, z111111111, function111111113, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationBarItemColors2 = navigationBarItemColors111113;
                        z8 = z111111111;
                        composer2 = composerStartRestartGroup;
                        modifier2 = modifier118;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        function5 = function7;
                        z7 = z111111113;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        navigationBarItemColors2 = navigationBarItemColors;
                        composer2 = composerStartRestartGroup;
                        z7 = z4;
                        z8 = z5;
                        function5 = function4;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens116 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens116, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z111111114 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda11111116 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors111114 = navigationBarItemColors3;
                        final boolean z111111115 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function111111114 = function6;
                        function7 = function111111114;
                        Function2 function2RememberComposableLambda116 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors111114.m651textColorWaAFU9c$material3(z, z111111115), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111111114, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda116;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z111111116 = z4;
                    Modifier modifier119 = modifier3;
                    Function2 function111111115 = function8;
                    Modifier modifierWeight$default116 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier119, z, mutableInteractionSource5, (Indication) null, z111111116, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged116 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default116, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy116 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap116 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier116 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged116);
                    ComposeUiNode.Companion companion117 = ComposeUiNode.INSTANCE;
                    constructor = companion117.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors111115 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy116, companion117.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap116, companion117.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion117.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier116, companion117.getSetModifier());
                    BoxScopeInstance boxScopeInstance116 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens116, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density116 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density116.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density116.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit116 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource116 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda11111117 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource116, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda11111118 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors111115), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function111111116 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda11111117, composableLambdaRememberComposableLambda11111118, composableLambdaRememberComposableLambda11111116, function111111115, z111111114, function111111116, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors111115;
                    z8 = z111111114;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier119;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z111111116;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            z5 = z3;
            c = ' ';
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens117 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens117, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z111111117 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda11111119 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors111116 = navigationBarItemColors3;
                        final boolean z111111118 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function111111117 = function6;
                        function7 = function111111117;
                        Function2 function2RememberComposableLambda117 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors111116.m651textColorWaAFU9c$material3(z, z111111118), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111111117, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda117;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z111111119 = z4;
                    Modifier modifier1110 = modifier3;
                    Function2 function111111118 = function8;
                    Modifier modifierWeight$default117 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1110, z, mutableInteractionSource5, (Indication) null, z111111119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged117 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default117, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy117 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap117 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier117 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged117);
                    ComposeUiNode.Companion companion118 = ComposeUiNode.INSTANCE;
                    constructor = companion118.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors111117 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy117, companion118.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap117, companion118.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion118.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier117, companion118.getSetModifier());
                    BoxScopeInstance boxScopeInstance117 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens117, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density117 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density117.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density117.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit117 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource117 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda111111110 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource117, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda111111111 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors111117), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function111111119 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda111111110, composableLambdaRememberComposableLambda111111111, composableLambdaRememberComposableLambda11111119, function111111118, z111111117, function111111119, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors111117;
                    z8 = z111111117;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier1110;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z111111119;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            i12 = i3;
            if ((i3 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224963495);
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-7257538);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens118 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens118, composerStartRestartGroup, 6);
                mutableInteractionSource5 = mutableInteractionSource4;
                boolean z1111111110 = z5;
                ComposableLambda composableLambdaRememberComposableLambda111111112 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                if (function6 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224036658);
                    composerStartRestartGroup.endReplaceGroup();
                    function7 = function6;
                    function8 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-224036657);
                    final NavigationBarItemColors navigationBarItemColors111118 = navigationBarItemColors3;
                    final boolean z1111111111 = z4;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111110 = function6;
                    function7 = function1111111110;
                    Function2 function2RememberComposableLambda118 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors111118.m651textColorWaAFU9c$material3(z, z1111111111), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111111110, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function2RememberComposableLambda118;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableIntState = (MutableIntState) objRememberedValue;
                boolean z1111111112 = z4;
                Modifier modifier1111 = modifier3;
                Function2 function1111111111 = function8;
                Modifier modifierWeight$default118 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1111, z, mutableInteractionSource5, (Indication) null, z1111111112, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: rca
                        public final Object invoke(Object obj) {
                            return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierOnSizeChanged118 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default118, (Function1) objRememberedValue2);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy118 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap118 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier118 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged118);
                ComposeUiNode.Companion companion119 = ComposeUiNode.INSTANCE;
                constructor = companion119.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                NavigationBarItemColors navigationBarItemColors111119 = navigationBarItemColors3;
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy118, companion119.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap118, companion119.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion119.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier118, companion119.getSetModifier());
                BoxScopeInstance boxScopeInstance118 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens118, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density118 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density118.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density118.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                Unit unit118 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final MappedInteractionSource mappedInteractionSource118 = (MappedInteractionSource) objRememberedValue3;
                ComposableLambda composableLambdaRememberComposableLambda111111113 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource118, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda111111114 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors111119), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                Function0 function1111111112 = (Function0) objRememberedValue4;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                NavigationBarItemLayout(composableLambdaRememberComposableLambda111111113, composableLambdaRememberComposableLambda111111114, composableLambdaRememberComposableLambda111111112, function1111111111, z1111111110, function1111111112, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationBarItemColors2 = navigationBarItemColors111119;
                z8 = z1111111110;
                composer2 = composerStartRestartGroup;
                modifier2 = modifier1111;
                mutableInteractionSource2 = mutableInteractionSource3;
                function5 = function7;
                z7 = z1111111112;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                navigationBarItemColors2 = navigationBarItemColors;
                composer2 = composerStartRestartGroup;
                z7 = z4;
                z8 = z5;
                function5 = function4;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 1572864;
        function4 = function3;
        i8 = i2 & 64;
        if (i8 != 0) {
            z5 = z3;
            if ((i & 12582912) == 0) {
                c = ' ';
                if (composerStartRestartGroup.changed(z5)) {
                    i9 = 8388608;
                } else {
                    i9 = 4194304;
                }
                i3 |= i9;
            }
            if ((i & 100663296) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i3 |= i11;
                }
                i12 = i3;
                if ((i3 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i13 = i12 & (-234881025);
                        } else {
                            navigationBarItemColorsColors = navigationBarItemColors;
                            i13 = i12;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        i14 = i13;
                        navigationBarItemColors3 = navigationBarItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224963495);
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-7257538);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens119 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens119, composerStartRestartGroup, 6);
                    mutableInteractionSource5 = mutableInteractionSource4;
                    boolean z1111111113 = z5;
                    ComposableLambda composableLambdaRememberComposableLambda111111115 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                    if (function6 == null) {
                        composerStartRestartGroup.startReplaceGroup(-224036658);
                        composerStartRestartGroup.endReplaceGroup();
                        function7 = function6;
                        function8 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-224036657);
                        final NavigationBarItemColors navigationBarItemColors1111110 = navigationBarItemColors3;
                        final boolean z1111111114 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function1111111113 = function6;
                        function7 = function1111111113;
                        Function2 function2RememberComposableLambda119 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i16) {
                                if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors1111110.m651textColorWaAFU9c$material3(z, z1111111114), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111111113, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function2RememberComposableLambda119;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableIntState = (MutableIntState) objRememberedValue;
                    boolean z1111111115 = z4;
                    Modifier modifier1112 = modifier3;
                    Function2 function1111111114 = function8;
                    Modifier modifierWeight$default119 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1112, z, mutableInteractionSource5, (Indication) null, z1111111115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: rca
                            public final Object invoke(Object obj) {
                                return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierOnSizeChanged119 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default119, (Function1) objRememberedValue2);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy119 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap119 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier119 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged119);
                    ComposeUiNode.Companion companion1110 = ComposeUiNode.INSTANCE;
                    constructor = companion1110.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    NavigationBarItemColors navigationBarItemColors1111111 = navigationBarItemColors3;
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy119, companion1110.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap119, companion1110.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion1110.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier119, companion1110.getSetModifier());
                    BoxScopeInstance boxScopeInstance119 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens119, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density119 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density119.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density119.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                    Unit unit119 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    final MappedInteractionSource mappedInteractionSource119 = (MappedInteractionSource) objRememberedValue3;
                    ComposableLambda composableLambdaRememberComposableLambda111111116 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource119, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda111111117 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors1111111), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = new Function0() { // from class: sca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    Function0 function1111111115 = (Function0) objRememberedValue4;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = new Function0() { // from class: tca
                            public final Object invoke() {
                                return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    NavigationBarItemLayout(composableLambdaRememberComposableLambda111111116, composableLambdaRememberComposableLambda111111117, composableLambdaRememberComposableLambda111111115, function1111111114, z1111111113, function1111111115, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationBarItemColors2 = navigationBarItemColors1111111;
                    z8 = z1111111113;
                    composer2 = composerStartRestartGroup;
                    modifier2 = modifier1112;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    function5 = function7;
                    z7 = z1111111115;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    navigationBarItemColors2 = navigationBarItemColors;
                    composer2 = composerStartRestartGroup;
                    z7 = z4;
                    z8 = z5;
                    function5 = function4;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            i12 = i3;
            if ((i3 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224963495);
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-7257538);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens1110 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens1110, composerStartRestartGroup, 6);
                mutableInteractionSource5 = mutableInteractionSource4;
                boolean z1111111116 = z5;
                ComposableLambda composableLambdaRememberComposableLambda111111118 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                if (function6 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224036658);
                    composerStartRestartGroup.endReplaceGroup();
                    function7 = function6;
                    function8 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-224036657);
                    final NavigationBarItemColors navigationBarItemColors1111112 = navigationBarItemColors3;
                    final boolean z1111111117 = z4;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111116 = function6;
                    function7 = function1111111116;
                    Function2 function2RememberComposableLambda1110 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors1111112.m651textColorWaAFU9c$material3(z, z1111111117), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111111116, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function2RememberComposableLambda1110;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableIntState = (MutableIntState) objRememberedValue;
                boolean z1111111118 = z4;
                Modifier modifier1113 = modifier3;
                Function2 function1111111117 = function8;
                Modifier modifierWeight$default1110 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1113, z, mutableInteractionSource5, (Indication) null, z1111111118, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: rca
                        public final Object invoke(Object obj) {
                            return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierOnSizeChanged1110 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default1110, (Function1) objRememberedValue2);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy1110 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap1110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier1110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged1110);
                ComposeUiNode.Companion companion1111 = ComposeUiNode.INSTANCE;
                constructor = companion1111.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                NavigationBarItemColors navigationBarItemColors1111113 = navigationBarItemColors3;
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy1110, companion1111.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap1110, companion1111.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion1111.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier1110, companion1111.getSetModifier());
                BoxScopeInstance boxScopeInstance1110 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens1110, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density1110 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density1110.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density1110.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                Unit unit1110 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final MappedInteractionSource mappedInteractionSource1110 = (MappedInteractionSource) objRememberedValue3;
                ComposableLambda composableLambdaRememberComposableLambda111111119 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource1110, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda1111111110 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors1111113), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                Function0 function1111111118 = (Function0) objRememberedValue4;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                NavigationBarItemLayout(composableLambdaRememberComposableLambda111111119, composableLambdaRememberComposableLambda1111111110, composableLambdaRememberComposableLambda111111118, function1111111117, z1111111116, function1111111118, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationBarItemColors2 = navigationBarItemColors1111113;
                z8 = z1111111116;
                composer2 = composerStartRestartGroup;
                modifier2 = modifier1113;
                mutableInteractionSource2 = mutableInteractionSource3;
                function5 = function7;
                z7 = z1111111118;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                navigationBarItemColors2 = navigationBarItemColors;
                composer2 = composerStartRestartGroup;
                z7 = z4;
                z8 = z5;
                function5 = function4;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 12582912;
        z5 = z3;
        c = ' ';
        if ((i & 100663296) != 0) {
            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationBarItemColors)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
        }
        i10 = i2 & 256;
        if (i10 != 0) {
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i3 |= i11;
            }
            i12 = i3;
            if ((i3 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i13 = i12 & (-234881025);
                    } else {
                        navigationBarItemColorsColors = navigationBarItemColors;
                        i13 = i12;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    i14 = i13;
                    navigationBarItemColors3 = navigationBarItemColorsColors;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224963495);
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-7257538);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens1111 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens1111, composerStartRestartGroup, 6);
                mutableInteractionSource5 = mutableInteractionSource4;
                boolean z1111111119 = z5;
                ComposableLambda composableLambdaRememberComposableLambda1111111111 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
                if (function6 == null) {
                    composerStartRestartGroup.startReplaceGroup(-224036658);
                    composerStartRestartGroup.endReplaceGroup();
                    function7 = function6;
                    function8 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(-224036657);
                    final NavigationBarItemColors navigationBarItemColors1111114 = navigationBarItemColors3;
                    final boolean z11111111110 = z4;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111119 = function6;
                    function7 = function1111111119;
                    Function2 function2RememberComposableLambda1111 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i16) {
                            if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors1111114.m651textColorWaAFU9c$material3(z, z11111111110), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111111119, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function2RememberComposableLambda1111;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableIntState = (MutableIntState) objRememberedValue;
                boolean z11111111111 = z4;
                Modifier modifier1114 = modifier3;
                Function2 function11111111110 = function8;
                Modifier modifierWeight$default1111 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1114, z, mutableInteractionSource5, (Indication) null, z11111111111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: rca
                        public final Object invoke(Object obj) {
                            return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierOnSizeChanged1111 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default1111, (Function1) objRememberedValue2);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy1111 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap1111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier1111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged1111);
                ComposeUiNode.Companion companion1112 = ComposeUiNode.INSTANCE;
                constructor = companion1112.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                NavigationBarItemColors navigationBarItemColors1111115 = navigationBarItemColors3;
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy1111, companion1112.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap1111, companion1112.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion1112.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier1111, companion1112.getSetModifier());
                BoxScopeInstance boxScopeInstance1111 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens1111, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density1111 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density1111.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density1111.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
                Unit unit1111 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final MappedInteractionSource mappedInteractionSource1111 = (MappedInteractionSource) objRememberedValue3;
                ComposableLambda composableLambdaRememberComposableLambda1111111112 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource1111, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda1111111113 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors1111115), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = new Function0() { // from class: sca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                Function0 function11111111111 = (Function0) objRememberedValue4;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = new Function0() { // from class: tca
                        public final Object invoke() {
                            return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                NavigationBarItemLayout(composableLambdaRememberComposableLambda1111111112, composableLambdaRememberComposableLambda1111111113, composableLambdaRememberComposableLambda1111111111, function11111111110, z1111111119, function11111111111, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationBarItemColors2 = navigationBarItemColors1111115;
                z8 = z1111111119;
                composer2 = composerStartRestartGroup;
                modifier2 = modifier1114;
                mutableInteractionSource2 = mutableInteractionSource3;
                function5 = function7;
                z7 = z11111111111;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                navigationBarItemColors2 = navigationBarItemColors;
                composer2 = composerStartRestartGroup;
                z7 = z4;
                z8 = z5;
                function5 = function4;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 805306368;
        i12 = i3;
        if ((i3 & 306783379) != 306783378) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z6, i12 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i15 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z4 = true;
                }
                if (i6 != 0) {
                    function4 = null;
                }
                if (i8 != 0) {
                    z5 = true;
                }
                if ((i2 & 128) != 0) {
                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i13 = i12 & (-234881025);
                } else {
                    navigationBarItemColorsColors = navigationBarItemColors;
                    i13 = i12;
                }
                if (i10 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                modifier3 = modifier2;
                function6 = function4;
                i14 = i13;
                navigationBarItemColors3 = navigationBarItemColorsColors;
            } else {
                if (i15 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z4 = true;
                }
                if (i6 != 0) {
                    function4 = null;
                }
                if (i8 != 0) {
                    z5 = true;
                }
                if ((i2 & 128) != 0) {
                    navigationBarItemColorsColors = NavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i13 = i12 & (-234881025);
                } else {
                    navigationBarItemColorsColors = navigationBarItemColors;
                    i13 = i12;
                }
                if (i10 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                modifier3 = modifier2;
                function6 = function4;
                i14 = i13;
                navigationBarItemColors3 = navigationBarItemColorsColors;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(974293026, i14, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
            }
            if (mutableInteractionSource3 == null) {
                composerStartRestartGroup.startReplaceGroup(-224963495);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue6;
            } else {
                composerStartRestartGroup.startReplaceGroup(-7257538);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            MotionSchemeKeyTokens motionSchemeKeyTokens1112 = MotionSchemeKeyTokens.DefaultEffects;
            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens1112, composerStartRestartGroup, 6);
            mutableInteractionSource5 = mutableInteractionSource4;
            boolean z11111111112 = z5;
            ComposableLambda composableLambdaRememberComposableLambda1111111114 = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(navigationBarItemColors3, z, z4, finiteAnimationSpecValue, function6, z5, function2), composerStartRestartGroup, 54);
            if (function6 == null) {
                composerStartRestartGroup.startReplaceGroup(-224036658);
                composerStartRestartGroup.endReplaceGroup();
                function7 = function6;
                function8 = null;
            } else {
                composerStartRestartGroup.startReplaceGroup(-224036657);
                final NavigationBarItemColors navigationBarItemColors1111116 = navigationBarItemColors3;
                final boolean z11111111113 = z4;
                final Function2<? super Composer, ? super Integer, Unit> function11111111112 = function6;
                function7 = function11111111112;
                Function2 function2RememberComposableLambda1112 = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                    private static final long invoke$lambda$0(State<Color> state) {
                        return state.getValue().m3144unboximpl();
                    }

                    public final void invoke(Composer composer3, int i16) {
                        if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(802208206, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                        }
                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationBarItemColors1111116.m651textColorWaAFU9c$material3(z, z11111111113), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11111111112, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                composerStartRestartGroup.endReplaceGroup();
                function8 = function2RememberComposableLambda1112;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableIntState = (MutableIntState) objRememberedValue;
            boolean z11111111114 = z4;
            Modifier modifier1115 = modifier3;
            Function2 function11111111113 = function8;
            Modifier modifierWeight$default1112 = RowScope.weight$default(rowScope, SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1115, z, mutableInteractionSource5, (Indication) null, z11111111114, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationBarHeight, 1, (Object) null), 1.0f, false, 2, (Object) null);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: rca
                    public final Object invoke(Object obj) {
                        return NavigationBarKt.a(mutableIntState, (IntSize) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierOnSizeChanged1112 = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default1112, (Function1) objRememberedValue2);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy1112 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap1112 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier1112 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged1112);
            ComposeUiNode.Companion companion1113 = ComposeUiNode.INSTANCE;
            constructor = companion1113.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            NavigationBarItemColors navigationBarItemColors1111117 = navigationBarItemColors3;
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy1112, companion1113.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap1112, companion1113.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion1113.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier1112, companion1113.getSetModifier());
            BoxScopeInstance boxScopeInstance1112 = BoxScopeInstance.INSTANCE;
            if (z) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens1112, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
            if (z) {
                f2 = 1.0f;
            } else {
                f2 = 0.0f;
            }
            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
            Density density1112 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density1112.mo4551roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (4294967295L & ((long) Float.floatToRawIntBits(density1112.mo4557toPx0680j_4(IndicatorVerticalOffset)))));
            Unit unit1112 = Unit.INSTANCE;
            zChanged = composerStartRestartGroup.changed(mutableInteractionSource5) | composerStartRestartGroup.changed(jM2881constructorimpl);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, jM2881constructorimpl, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MappedInteractionSource mappedInteractionSource1112 = (MappedInteractionSource) objRememberedValue3;
            ComposableLambda composableLambdaRememberComposableLambda1111111115 = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                public final void invoke(Composer composer3, int i16) {
                    if (!composer3.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2082182507, i16, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                    }
                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer3, 6)), mappedInteractionSource1112, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            ComposableLambda composableLambdaRememberComposableLambda1111111116 = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, navigationBarItemColors1111117), composerStartRestartGroup, 54);
            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged2) {
                objRememberedValue4 = new Function0() { // from class: sca
                    public final Object invoke() {
                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                objRememberedValue4 = new Function0() { // from class: sca
                    public final Object invoke() {
                        return Float.valueOf(NavigationBarKt.b(stateAnimateFloatAsState));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            Function0 function11111111114 = (Function0) objRememberedValue4;
            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged3) {
                objRememberedValue5 = new Function0() { // from class: tca
                    public final Object invoke() {
                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                objRememberedValue5 = new Function0() { // from class: tca
                    public final Object invoke() {
                        return Float.valueOf(NavigationBarKt.i(stateAnimateFloatAsState2));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            NavigationBarItemLayout(composableLambdaRememberComposableLambda1111111115, composableLambdaRememberComposableLambda1111111116, composableLambdaRememberComposableLambda1111111114, function11111111113, z11111111112, function11111111114, (Function0) objRememberedValue5, composerStartRestartGroup, ((i14 >> 9) & 57344) | 438);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            navigationBarItemColors2 = navigationBarItemColors1111117;
            z8 = z11111111112;
            composer2 = composerStartRestartGroup;
            modifier2 = modifier1115;
            mutableInteractionSource2 = mutableInteractionSource3;
            function5 = function7;
            z7 = z11111111114;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            navigationBarItemColors2 = navigationBarItemColors;
            composer2 = composerStartRestartGroup;
            z7 = z4;
            z8 = z5;
            function5 = function4;
            mutableInteractionSource2 = mutableInteractionSource;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uca
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationBarKt.c(rowScope, z, function0, function2, modifier2, z7, function5, z8, navigationBarItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void NavigationBarItemLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final boolean z, final Function0<Float> function0, final Function0<Float> function1, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1019541078);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function4) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 1048576 : 524288;
        }
        if (composerStartRestartGroup.shouldExecute((599187 & i2) != 599186, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1019541078, i2, -1, "androidx.compose.material3.NavigationBarItemLayout (NavigationBar.kt:553)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierBadgeBounds = BadgeKt.badgeBounds(companion);
            int i3 = 57344 & i2;
            boolean z2 = ((i2 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) | ((3670016 & i2) == 1048576) | (i3 == 16384);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItemLayout$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        Measurable measurable;
                        Placeable placeableMo4605measureBRTryo0;
                        float fCoerceAtLeast = RangesKt.coerceAtLeast(((Number) function1.invoke()).floatValue(), 0.0f);
                        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        int size = list.size();
                        for (int i4 = 0; i4 < size; i4++) {
                            Measurable measurable2 = list.get(i4);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "icon")) {
                                Placeable placeableMo4605measureBRTryo1 = measurable2.mo4605measureBRTryo0(jM5965copyZbe2FdA$default);
                                int width = placeableMo4605measureBRTryo1.getWidth() + measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(NavigationBarKt.IndicatorHorizontalPadding * 2.0f));
                                int iRoundToInt = MathKt.roundToInt(width * fCoerceAtLeast);
                                int height = placeableMo4605measureBRTryo1.getHeight() + measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(NavigationBarKt.getIndicatorVerticalPadding() * 2.0f));
                                List<? extends Measurable> list2 = list;
                                int size2 = list2.size();
                                for (int i5 = 0; i5 < size2; i5++) {
                                    Measurable measurable3 = list.get(i5);
                                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "indicatorRipple")) {
                                        Placeable placeableMo4605measureBRTryo2 = measurable3.mo4605measureBRTryo0(Constraints.INSTANCE.m5985fixedJhjzzOo(width, height));
                                        int size3 = list2.size();
                                        int i6 = 0;
                                        while (true) {
                                            if (i6 >= size3) {
                                                measurable = null;
                                                break;
                                            }
                                            measurable = list.get(i6);
                                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "indicator")) {
                                                break;
                                            }
                                            i6++;
                                        }
                                        Measurable measurable4 = measurable;
                                        Placeable placeableMo4605measureBRTryo3 = measurable4 != null ? measurable4.mo4605measureBRTryo0(Constraints.INSTANCE.m5985fixedJhjzzOo(iRoundToInt, height)) : null;
                                        if (function5 != null) {
                                            int size4 = list2.size();
                                            int i7 = 0;
                                            while (true) {
                                                if (i7 >= size4) {
                                                    ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                                                    wq6.a();
                                                    return null;
                                                }
                                                Measurable measurable5 = list.get(i7);
                                                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), "label")) {
                                                    placeableMo4605measureBRTryo0 = measurable5.mo4605measureBRTryo0(jM5965copyZbe2FdA$default);
                                                    break;
                                                }
                                                i7++;
                                            }
                                        } else {
                                            placeableMo4605measureBRTryo0 = null;
                                        }
                                        if (function5 == null) {
                                            return NavigationBarKt.m657placeIconX9ElhV4(measureScope, placeableMo4605measureBRTryo1, placeableMo4605measureBRTryo2, placeableMo4605measureBRTryo3, j);
                                        }
                                        placeableMo4605measureBRTryo0.getClass();
                                        return NavigationBarKt.m658placeLabelAndIconzUg2_y0(measureScope, placeableMo4605measureBRTryo0, placeableMo4605measureBRTryo1, placeableMo4605measureBRTryo2, placeableMo4605measureBRTryo3, j, z, fCoerceAtLeast);
                                    }
                                }
                                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                                wq6.a();
                                return null;
                            }
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        wq6.a();
                        return null;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierBadgeBounds);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            function2.invoke(composerStartRestartGroup, Integer.valueOf(i2 & 14));
            function3.invoke(composerStartRestartGroup, Integer.valueOf((i2 >> 3) & 14));
            Modifier modifierLayoutId = LayoutIdKt.layoutId(companion, IconLayoutIdTag);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId);
            Function0<ComposeUiNode> constructor2 = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            function4.invoke(composerStartRestartGroup, Integer.valueOf((i2 >> 6) & 14));
            composerStartRestartGroup.endNode();
            if (function5 != null) {
                composerStartRestartGroup.startReplaceGroup(-660471321);
                Modifier modifierLayoutId2 = LayoutIdKt.layoutId(companion, LabelLayoutIdTag);
                boolean z3 = (i3 == 16384) | ((458752 & i2) == 131072);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: wca
                        public final Object invoke(Object obj) {
                            return NavigationBarKt.e(z, function0, (GraphicsLayerScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierLayoutId2, (Function1) objRememberedValue2);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierGraphicsLayer);
                Function0<ComposeUiNode> constructor3 = companion2.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion2.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = companion2.getSetCompositeKeyHash();
                if (composerM2388constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion2.getSetModifier());
                function5.invoke(composerStartRestartGroup, Integer.valueOf((i2 >> 9) & 14));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-660200319);
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xca
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationBarKt.f(function2, function3, function4, function5, z, function0, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(MutableIntState mutableIntState, IntSize intSize) {
        mutableIntState.setIntValue((int) (intSize.m6197unboximpl() >> 32));
        return Unit.INSTANCE;
    }

    public static float b(State state) {
        return ((Number) state.getValue()).floatValue();
    }

    public static Unit c(RowScope rowScope, boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function3, boolean z3, NavigationBarItemColors navigationBarItemColors, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        NavigationBarItem(rowScope, z, function0, function2, modifier, z2, function3, z3, navigationBarItemColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit d(Placeable placeable, boolean z, float f, Placeable placeable2, int i, float f2, float f3, Placeable placeable3, int i2, float f4, Placeable placeable4, int i3, float f5, int i4, MeasureScope measureScope, Placeable.PlacementScope placementScope) {
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, (i4 - placeable.getWidth()) / 2, MathKt.roundToInt((f4 - measureScope.mo4551roundToPx0680j_4(IndicatorVerticalPadding)) + f3), 0.0f, 4, null);
        }
        if (z || f != 0.0f) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i, MathKt.roundToInt(f2 + f3), 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i2, MathKt.roundToInt(f4 + f3), 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, i3, MathKt.roundToInt(f5 + f3), 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit e(boolean z, Function0 function0, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(z ? 1.0f : ((Number) function0.invoke()).floatValue());
        return Unit.INSTANCE;
    }

    public static Unit f(Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z, Function0 function0, Function0 function1, int i, Composer composer, int i2) {
        NavigationBarItemLayout(function2, function3, function4, function5, z, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit g(Modifier modifier, long j, long j2, float f, WindowInsets windowInsets, Function3 function3, int i, int i2, Composer composer, int i3) {
        m654NavigationBarHsRjFd4(modifier, j, j2, f, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final float getIndicatorVerticalPadding() {
        return IndicatorVerticalPadding;
    }

    public static final ProvidableCompositionLocal<NavigationBarOverride> getLocalNavigationBarOverride() {
        return LocalNavigationBarOverride;
    }

    public static final float getNavigationBarIndicatorToLabelPadding() {
        return NavigationBarIndicatorToLabelPadding;
    }

    public static final float getNavigationBarItemHorizontalPadding() {
        return NavigationBarItemHorizontalPadding;
    }

    public static final float getNavigationBarItemToIconMinimumPadding() {
        return NavigationBarItemToIconMinimumPadding;
    }

    public static Unit h(Placeable placeable, Placeable placeable2, int i, int i2, Placeable placeable3, int i3, int i4, int i5, int i6, Placeable.PlacementScope placementScope) {
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, (i5 - placeable.getWidth()) / 2, (i6 - placeable.getHeight()) / 2, 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i, i2, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i3, i4, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static float i(State state) {
        return ((Number) state.getValue()).floatValue();
    }

    public static NavigationBarOverride j() {
        return DefaultNavigationBarOverride.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m657placeIconX9ElhV4(MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, long j) {
        final int width = Constraints.m5975getMaxWidthimpl(j) == Integer.MAX_VALUE ? placeable.getWidth() + (measureScope.mo4551roundToPx0680j_4(NavigationBarItemToIconMinimumPadding) * 2) : Constraints.m5975getMaxWidthimpl(j);
        final int iM5991constrainHeightK40F9xA = ConstraintsKt.m5991constrainHeightK40F9xA(j, measureScope.mo4551roundToPx0680j_4(NavigationBarHeight));
        final int width2 = (width - placeable.getWidth()) / 2;
        final int height = (iM5991constrainHeightK40F9xA - placeable.getHeight()) / 2;
        final int width3 = (width - placeable2.getWidth()) / 2;
        final int height2 = (iM5991constrainHeightK40F9xA - placeable2.getHeight()) / 2;
        return MeasureScope.layout$default(measureScope, width, iM5991constrainHeightK40F9xA, null, new Function1() { // from class: vca
            public final Object invoke(Object obj) {
                return NavigationBarKt.h(placeable3, placeable, width2, height, placeable2, width3, height2, width, iM5991constrainHeightK40F9xA, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeLabelAndIcon-zUg2_y0, reason: not valid java name */
    public static final MeasureResult m658placeLabelAndIconzUg2_y0(final MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final Placeable placeable4, long j, final boolean z, final float f) {
        float height = placeable2.getHeight();
        float f2 = IndicatorVerticalPadding;
        float fMo4557toPx0680j_4 = height + measureScope.mo4557toPx0680j_4(f2);
        float f3 = NavigationBarIndicatorToLabelPadding;
        float fMo4557toPx0680j_5 = fMo4557toPx0680j_4 + measureScope.mo4557toPx0680j_4(f3) + placeable.getHeight();
        final float fCoerceAtLeast = RangesKt.coerceAtLeast((Constraints.m5976getMinHeightimpl(j) - fMo4557toPx0680j_5) / 2.0f, measureScope.mo4557toPx0680j_4(f2));
        float f4 = fMo4557toPx0680j_5 + (fCoerceAtLeast * 2.0f);
        final float height2 = ((z ? fCoerceAtLeast : (f4 - placeable2.getHeight()) / 2.0f) - fCoerceAtLeast) * (1.0f - f);
        final float height3 = placeable2.getHeight() + fCoerceAtLeast + measureScope.mo4557toPx0680j_4(f2) + measureScope.mo4557toPx0680j_4(f3);
        final int width = Constraints.m5975getMaxWidthimpl(j) == Integer.MAX_VALUE ? placeable2.getWidth() + (measureScope.mo4551roundToPx0680j_4(NavigationBarItemToIconMinimumPadding) * 2) : Constraints.m5975getMaxWidthimpl(j);
        final int width2 = (width - placeable.getWidth()) / 2;
        final int width3 = (width - placeable2.getWidth()) / 2;
        final int width4 = (width - placeable3.getWidth()) / 2;
        final float fMo4557toPx0680j_6 = fCoerceAtLeast - measureScope.mo4557toPx0680j_4(f2);
        return MeasureScope.layout$default(measureScope, width, MathKt.roundToInt(f4), null, new Function1() { // from class: pca
            public final Object invoke(Object obj) {
                return NavigationBarKt.d(placeable4, z, f, placeable, width2, height3, height2, placeable2, width3, fCoerceAtLeast, placeable3, width4, fMo4557toPx0680j_6, width, measureScope, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
