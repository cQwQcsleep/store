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
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.NavigationRailKt;
import androidx.compose.material3.internal.MappedInteractionSource;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.NavigationRailBaselineItemTokens;
import androidx.compose.material3.tokens.NavigationRailCollapsedTokens;
import androidx.compose.material3.tokens.NavigationRailVerticalItemTokens;
import androidx.compose.material3.tokens.ShapeKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
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
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
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
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aw\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052 \b\u0002\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\u0002\b\n¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0081\u0001\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\n2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00132\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015¢\u0006\u0002\b\n2\b\b\u0002\u0010\u0019\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007¢\u0006\u0002\u0010\u001e\u001a\u007f\u0010\u001f\u001a\u00020\u00012\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\n2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\n2\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\n2\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015¢\u0006\u0002\b\n2\u0006\u0010\u0019\u001a\u00020\u00132\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00152\f\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\u0015H\u0003¢\u0006\u0002\u0010%\u001a5\u0010&\u001a\u00020'*\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\b\u0010,\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020.H\u0002¢\u0006\u0004\b/\u00100\u001aM\u00101\u001a\u00020'*\u00020(2\u0006\u00102\u001a\u00020*2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\b\u0010,\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u00103\u001a\u00020#H\u0002¢\u0006\u0004\b4\u00105\"\u000e\u00106\u001a\u000207X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00108\u001a\u000207X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00109\u001a\u000207X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010:\u001a\u000207X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010;\u001a\u00020<X\u0080\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\b=\u0010>\"\u0010\u0010@\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0016\u0010A\u001a\u00020<X\u0080\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bB\u0010>\"\u0016\u0010C\u001a\u00020<X\u0080\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bD\u0010>\"\u0016\u0010E\u001a\u00020<X\u0080\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bF\u0010>\"\u0010\u0010G\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0010\u0010H\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0010\u0010I\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020L0KX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010N¨\u0006O²\u0006\n\u0010P\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010Q\u001a\u00020\u0005X\u008a\u0084\u0002"}, d2 = {"NavigationRail", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "header", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "NavigationRail-qi6gXK8", "(Landroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItem", "selected", "", "onClick", "Lkotlin/Function0;", NavigationRailKt.IconLayoutIdTag, "enabled", NavigationRailKt.LabelLayoutIdTag, "alwaysShowLabel", "colors", "Landroidx/compose/material3/NavigationRailItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/NavigationRailItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItemLayout", NavigationRailKt.IndicatorRippleLayoutIdTag, NavigationRailKt.IndicatorLayoutIdTag, "alphaAnimationProgress", "", "sizeAnimationProgress", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "animationProgress", "placeLabelAndIcon-zUg2_y0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JZF)Landroidx/compose/ui/layout/MeasureResult;", "IndicatorRippleLayoutIdTag", "", "IndicatorLayoutIdTag", "IconLayoutIdTag", "LabelLayoutIdTag", "NavigationRailVerticalPadding", "Landroidx/compose/ui/unit/Dp;", "getNavigationRailVerticalPadding", "()F", "F", "NavigationRailHeaderPadding", "NavigationRailItemWidth", "getNavigationRailItemWidth", "NavigationRailItemHeight", "getNavigationRailItemHeight", "NavigationRailItemVerticalPadding", "getNavigationRailItemVerticalPadding", "IndicatorHorizontalPadding", "IndicatorVerticalPaddingWithLabel", "IndicatorVerticalPaddingNoLabel", "LocalNavigationRailOverride", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/NavigationRailOverride;", "getLocalNavigationRailOverride", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "material3", "iconColor", "textColor"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationRailKt {
    private static final String IconLayoutIdTag = "icon";
    private static final float IndicatorHorizontalPadding;
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalPaddingNoLabel;
    private static final float IndicatorVerticalPaddingWithLabel;
    private static final String LabelLayoutIdTag = "label";
    private static final ProvidableCompositionLocal<NavigationRailOverride> LocalNavigationRailOverride;
    private static final float NavigationRailItemHeight;
    private static final float NavigationRailItemVerticalPadding;
    private static final float NavigationRailVerticalPadding = Dp.m6022constructorimpl(4.0f);
    private static final float NavigationRailHeaderPadding = Dp.m6022constructorimpl(8.0f);
    private static final float NavigationRailItemWidth = NavigationRailCollapsedTokens.INSTANCE.m1945getNarrowContainerWidthD9Ej5fM();

    static {
        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens = NavigationRailVerticalItemTokens.INSTANCE;
        NavigationRailItemHeight = navigationRailVerticalItemTokens.m1958getActiveIndicatorWidthD9Ej5fM();
        NavigationRailItemVerticalPadding = Dp.m6022constructorimpl(4.0f);
        float fM1958getActiveIndicatorWidthD9Ej5fM = navigationRailVerticalItemTokens.m1958getActiveIndicatorWidthD9Ej5fM();
        NavigationRailBaselineItemTokens navigationRailBaselineItemTokens = NavigationRailBaselineItemTokens.INSTANCE;
        IndicatorHorizontalPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(fM1958getActiveIndicatorWidthD9Ej5fM - navigationRailBaselineItemTokens.m1941getIconSizeD9Ej5fM()) / 2.0f);
        IndicatorVerticalPaddingWithLabel = Dp.m6022constructorimpl(Dp.m6022constructorimpl(navigationRailVerticalItemTokens.m1957getActiveIndicatorHeightD9Ej5fM() - navigationRailBaselineItemTokens.m1941getIconSizeD9Ej5fM()) / 2.0f);
        IndicatorVerticalPaddingNoLabel = Dp.m6022constructorimpl(Dp.m6022constructorimpl(navigationRailVerticalItemTokens.m1958getActiveIndicatorWidthD9Ej5fM() - navigationRailBaselineItemTokens.m1941getIconSizeD9Ej5fM()) / 2.0f);
        LocalNavigationRailOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: cfa
            public final Object invoke() {
                return NavigationRailKt.d();
            }
        }, 1, null);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011c  */
    /* JADX WARN: Code duplicated, block: B:104:0x0122  */
    /* JADX WARN: Code duplicated, block: B:105:0x0133  */
    /* JADX WARN: Code duplicated, block: B:108:0x013e  */
    /* JADX WARN: Code duplicated, block: B:111:0x015e  */
    /* JADX WARN: Code duplicated, block: B:113:0x016a  */
    /* JADX WARN: Code duplicated, block: B:116:0x0178  */
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
    /* JADX INFO: renamed from: NavigationRail-qi6gXK8, reason: not valid java name */
    public static final void m718NavigationRailqi6gXK8(Modifier modifier, long j, long j2, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long containerColor;
        long jM278contentColorForek8zF_U;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function5;
        WindowInsets windowInsets2;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function6;
        int i4;
        boolean z;
        Modifier modifier3;
        final long j3;
        final long j4;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function7;
        final WindowInsets windowInsets3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        WindowInsets windowInsets4;
        long j5;
        long j6;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function8;
        Composer composerStartRestartGroup = composer.startRestartGroup(331386280);
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
                jM278contentColorForek8zF_U = j2;
                int i7 = composerStartRestartGroup.changed(jM278contentColorForek8zF_U) ? 256 : 128;
                i3 |= i7;
            } else {
                jM278contentColorForek8zF_U = j2;
            }
            i3 |= i7;
        } else {
            jM278contentColorForek8zF_U = j2;
        }
        int i8 = i2 & 8;
        if (i8 == 0) {
            if ((i & 3072) == 0) {
                function5 = function3;
                i3 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
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
                    function6 = function4;
                    if (composerStartRestartGroup.changedInstance(function6)) {
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
                            containerColor = NavigationRailDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -113;
                        }
                        if ((i2 & 4) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 3) & 14);
                            i3 &= -897;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            modifier3 = modifier4;
                            windowInsets4 = NavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            j5 = containerColor;
                            j6 = jM278contentColorForek8zF_U;
                            function8 = function5;
                        } else {
                            modifier3 = modifier4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(331386280, i3, -1, "androidx.compose.material3.NavigationRail (NavigationRail.kt:126)");
                        }
                        ((NavigationRailOverride) composerStartRestartGroup.consume(LocalNavigationRailOverride)).NavigationRail(new NavigationRailOverrideScope(modifier3, j5, j6, function8, windowInsets4, function6, null), composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j3 = j5;
                        j4 = j6;
                        function7 = function8;
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
                    j6 = jM278contentColorForek8zF_U;
                    function8 = function5;
                    windowInsets4 = windowInsets2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(331386280, i3, -1, "androidx.compose.material3.NavigationRail (NavigationRail.kt:126)");
                    }
                    ((NavigationRailOverride) composerStartRestartGroup.consume(LocalNavigationRailOverride)).NavigationRail(new NavigationRailOverrideScope(modifier3, j5, j6, function8, windowInsets4, function6, null), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j5;
                    j4 = j6;
                    function7 = function8;
                    windowInsets3 = windowInsets4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j3 = containerColor;
                    j4 = jM278contentColorForek8zF_U;
                    function7 = function5;
                    windowInsets3 = windowInsets2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier3;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: efa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.g(modifier5, j3, j4, function7, windowInsets3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function6 = function4;
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
                        containerColor = NavigationRailDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 3) & 14);
                        i3 &= -897;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        modifier3 = modifier4;
                        windowInsets4 = NavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        j5 = containerColor;
                        j6 = jM278contentColorForek8zF_U;
                        function8 = function5;
                    } else {
                        modifier3 = modifier4;
                        j5 = containerColor;
                        j6 = jM278contentColorForek8zF_U;
                        function8 = function5;
                        windowInsets4 = windowInsets2;
                    }
                } else {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        containerColor = NavigationRailDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 3) & 14);
                        i3 &= -897;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        modifier3 = modifier4;
                        windowInsets4 = NavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        j5 = containerColor;
                        j6 = jM278contentColorForek8zF_U;
                        function8 = function5;
                    } else {
                        modifier3 = modifier4;
                        j5 = containerColor;
                        j6 = jM278contentColorForek8zF_U;
                        function8 = function5;
                        windowInsets4 = windowInsets2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(331386280, i3, -1, "androidx.compose.material3.NavigationRail (NavigationRail.kt:126)");
                }
                ((NavigationRailOverride) composerStartRestartGroup.consume(LocalNavigationRailOverride)).NavigationRail(new NavigationRailOverrideScope(modifier3, j5, j6, function8, windowInsets4, function6, null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j5;
                j4 = j6;
                function7 = function8;
                windowInsets3 = windowInsets4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = containerColor;
                j4 = jM278contentColorForek8zF_U;
                function7 = function5;
                windowInsets3 = windowInsets2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: efa
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationRailKt.g(modifier6, j3, j4, function7, windowInsets3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        function5 = function3;
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
                function6 = function4;
                if (composerStartRestartGroup.changedInstance(function6)) {
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
                        containerColor = NavigationRailDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 3) & 14);
                        i3 &= -897;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        modifier3 = modifier4;
                        windowInsets4 = NavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        j5 = containerColor;
                        j6 = jM278contentColorForek8zF_U;
                        function8 = function5;
                    } else {
                        modifier3 = modifier4;
                        j5 = containerColor;
                        j6 = jM278contentColorForek8zF_U;
                        function8 = function5;
                        windowInsets4 = windowInsets2;
                    }
                } else {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        containerColor = NavigationRailDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 3) & 14);
                        i3 &= -897;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        modifier3 = modifier4;
                        windowInsets4 = NavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        j5 = containerColor;
                        j6 = jM278contentColorForek8zF_U;
                        function8 = function5;
                    } else {
                        modifier3 = modifier4;
                        j5 = containerColor;
                        j6 = jM278contentColorForek8zF_U;
                        function8 = function5;
                        windowInsets4 = windowInsets2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(331386280, i3, -1, "androidx.compose.material3.NavigationRail (NavigationRail.kt:126)");
                }
                ((NavigationRailOverride) composerStartRestartGroup.consume(LocalNavigationRailOverride)).NavigationRail(new NavigationRailOverrideScope(modifier3, j5, j6, function8, windowInsets4, function6, null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j5;
                j4 = j6;
                function7 = function8;
                windowInsets3 = windowInsets4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = containerColor;
                j4 = jM278contentColorForek8zF_U;
                function7 = function5;
                windowInsets3 = windowInsets2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier7 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: efa
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationRailKt.g(modifier7, j3, j4, function7, windowInsets3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        function6 = function4;
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
                    containerColor = NavigationRailDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 3) & 14);
                    i3 &= -897;
                }
                if (i8 != 0) {
                    function5 = null;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    modifier3 = modifier4;
                    windowInsets4 = NavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    j5 = containerColor;
                    j6 = jM278contentColorForek8zF_U;
                    function8 = function5;
                } else {
                    modifier3 = modifier4;
                    j5 = containerColor;
                    j6 = jM278contentColorForek8zF_U;
                    function8 = function5;
                    windowInsets4 = windowInsets2;
                }
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    containerColor = NavigationRailDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 3) & 14);
                    i3 &= -897;
                }
                if (i8 != 0) {
                    function5 = null;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    modifier3 = modifier4;
                    windowInsets4 = NavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    j5 = containerColor;
                    j6 = jM278contentColorForek8zF_U;
                    function8 = function5;
                } else {
                    modifier3 = modifier4;
                    j5 = containerColor;
                    j6 = jM278contentColorForek8zF_U;
                    function8 = function5;
                    windowInsets4 = windowInsets2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(331386280, i3, -1, "androidx.compose.material3.NavigationRail (NavigationRail.kt:126)");
            }
            ((NavigationRailOverride) composerStartRestartGroup.consume(LocalNavigationRailOverride)).NavigationRail(new NavigationRailOverrideScope(modifier3, j5, j6, function8, windowInsets4, function6, null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = j5;
            j4 = j6;
            function7 = function8;
            windowInsets3 = windowInsets4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = containerColor;
            j4 = jM278contentColorForek8zF_U;
            function7 = function5;
            windowInsets3 = windowInsets2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier8 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: efa
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.g(modifier8, j3, j4, function7, windowInsets3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011a  */
    /* JADX WARN: Code duplicated, block: B:102:0x011c  */
    /* JADX WARN: Code duplicated, block: B:105:0x0125  */
    /* JADX WARN: Code duplicated, block: B:107:0x0130  */
    /* JADX WARN: Code duplicated, block: B:116:0x0149 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:117:0x014b  */
    /* JADX WARN: Code duplicated, block: B:119:0x0150  */
    /* JADX WARN: Code duplicated, block: B:121:0x0153  */
    /* JADX WARN: Code duplicated, block: B:123:0x0156  */
    /* JADX WARN: Code duplicated, block: B:126:0x015b  */
    /* JADX WARN: Code duplicated, block: B:127:0x0163  */
    /* JADX WARN: Code duplicated, block: B:129:0x0167  */
    /* JADX WARN: Code duplicated, block: B:130:0x016d  */
    /* JADX WARN: Code duplicated, block: B:133:0x017a  */
    /* JADX WARN: Code duplicated, block: B:135:0x0185  */
    /* JADX WARN: Code duplicated, block: B:137:0x0197  */
    /* JADX WARN: Code duplicated, block: B:139:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:142:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:143:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:146:0x0256  */
    /* JADX WARN: Code duplicated, block: B:149:0x0262  */
    /* JADX WARN: Code duplicated, block: B:150:0x0266  */
    /* JADX WARN: Code duplicated, block: B:153:0x0287  */
    /* JADX WARN: Code duplicated, block: B:155:0x0295  */
    /* JADX WARN: Code duplicated, block: B:158:0x02ae  */
    /* JADX WARN: Code duplicated, block: B:160:0x02b2  */
    /* JADX WARN: Code duplicated, block: B:163:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:164:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:167:0x0330  */
    /* JADX WARN: Code duplicated, block: B:169:0x0338  */
    /* JADX WARN: Code duplicated, block: B:172:0x0345  */
    /* JADX WARN: Code duplicated, block: B:173:0x035a  */
    /* JADX WARN: Code duplicated, block: B:176:0x038f  */
    /* JADX WARN: Code duplicated, block: B:178:0x0397  */
    /* JADX WARN: Code duplicated, block: B:181:0x03ab  */
    /* JADX WARN: Code duplicated, block: B:183:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:186:0x03dc  */
    /* JADX WARN: Code duplicated, block: B:188:0x03e9  */
    /* JADX WARN: Code duplicated, block: B:191:0x03fa  */
    /* JADX WARN: Code duplicated, block: B:193:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x007b  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:57:0x0098  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:61:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:64:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:81:0x00da  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:87:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:90:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:94:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:96:0x0109  */
    /* JADX WARN: Code duplicated, block: B:97:0x010c  */
    public static final void NavigationRailItem(final boolean z, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean z2, Function2<? super Composer, ? super Integer, Unit> function3, boolean z3, NavigationRailItemColors navigationRailItemColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier2;
        int i4;
        boolean z4;
        int i5;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function5;
        int i7;
        int i8;
        boolean z5;
        char c;
        int i9;
        int i10;
        int i11;
        boolean z6;
        final MutableInteractionSource mutableInteractionSource2;
        final boolean z7;
        Composer composer2;
        final boolean z8;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final NavigationRailItemColors navigationRailItemColors2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        NavigationRailItemColors navigationRailItemColorsColors;
        NavigationRailItemColors navigationRailItemColors3;
        boolean z9;
        boolean z10;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int i12;
        MutableInteractionSource mutableInteractionSource3;
        final FiniteAnimationSpec finiteAnimationSpecValue;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function2 function9;
        MutableInteractionSource mutableInteractionSource4;
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
        Object objRememberedValue;
        final Shape value;
        boolean zChanged2;
        Object objRememberedValue2;
        boolean zChanged3;
        Object objRememberedValue3;
        Object objRememberedValue4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1620317701);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
            }
        }
        if ((i2 & 4) != 0) {
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function4 = function2;
        } else {
            function4 = function2;
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function4) ? 256 : 128;
            }
        }
        int i13 = i2 & 8;
        if (i13 == 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 2048 : 1024;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        function5 = function3;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        z5 = z3;
                        if ((i & 1572864) == 0) {
                            c = ' ';
                            if (composerStartRestartGroup.changed(z5)) {
                                i9 = 1048576;
                            } else {
                                i9 = 524288;
                            }
                            i3 |= i9;
                        }
                        if ((i & 12582912) != 0) {
                            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                        }
                        i10 = i2 & 256;
                        if (i10 != 0) {
                            if ((i & 100663296) == 0) {
                                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                    i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                } else {
                                    i11 = 33554432;
                                }
                                i3 |= i11;
                            }
                            if ((i3 & 38347923) != 38347922) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i13 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i4 != 0) {
                                        z4 = true;
                                    }
                                    if (i6 != 0) {
                                        function5 = null;
                                    }
                                    if (i8 != 0) {
                                        z5 = true;
                                    }
                                    if ((i2 & 128) != 0) {
                                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                        i3 &= -29360129;
                                    } else {
                                        navigationRailItemColorsColors = navigationRailItemColors;
                                    }
                                    if (i10 != 0) {
                                        navigationRailItemColors3 = navigationRailItemColorsColors;
                                        z9 = z4;
                                        z10 = z5;
                                        function7 = function5;
                                        mutableInteractionSource = null;
                                    } else {
                                        navigationRailItemColors3 = navigationRailItemColorsColors;
                                    }
                                    i12 = i3;
                                    Modifier modifier3 = modifier2;
                                    composerStartRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                                    }
                                    if (mutableInteractionSource == null) {
                                        composerStartRestartGroup.startReplaceGroup(253288608);
                                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                        }
                                        composerStartRestartGroup.endReplaceGroup();
                                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                                    } else {
                                        composerStartRestartGroup.startReplaceGroup(1947832599);
                                        composerStartRestartGroup.endReplaceGroup();
                                        mutableInteractionSource3 = mutableInteractionSource;
                                    }
                                    MotionSchemeKeyTokens motionSchemeKeyTokens = MotionSchemeKeyTokens.DefaultEffects;
                                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
                                    boolean z11 = z10;
                                    ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                                    if (function7 == null) {
                                        composerStartRestartGroup.startReplaceGroup(254215848);
                                        composerStartRestartGroup.endReplaceGroup();
                                        function8 = function7;
                                        function9 = null;
                                    } else {
                                        composerStartRestartGroup.startReplaceGroup(254215849);
                                        final NavigationRailItemColors navigationRailItemColors4 = navigationRailItemColors3;
                                        final boolean z12 = z9;
                                        final Function2<? super Composer, ? super Integer, Unit> function10 = function7;
                                        function8 = function10;
                                        Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                            private static final long invoke$lambda$0(State<Color> state) {
                                                return state.getValue().m3144unboximpl();
                                            }

                                            public final void invoke(Composer composer3, int i14) {
                                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                                    composer3.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                                }
                                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors4.m715textColorWaAFU9c$material3(z, z12), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function10, composer3, 0);
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
                                        function9 = function2RememberComposableLambda;
                                    }
                                    boolean z13 = z9;
                                    mutableInteractionSource4 = mutableInteractionSource3;
                                    Modifier modifier4 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier3, z, mutableInteractionSource4, (Indication) null, z13, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                                    float f3 = NavigationRailItemWidth;
                                    Modifier modifier5 = SizeKt.widthIn-VpY3zN4$default(modifier4, f3, 0.0f, 2, (Object) null);
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier5);
                                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                    constructor = companion.getConstructor();
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
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                    setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    if (z) {
                                        f = 1.0f;
                                    } else {
                                        f = 0.0f;
                                    }
                                    NavigationRailItemColors navigationRailItemColors5 = navigationRailItemColors3;
                                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                                    if (z) {
                                        f2 = 1.0f;
                                    } else {
                                        f2 = 0.0f;
                                    }
                                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                                    Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density.mo4551roundToPx0680j_4(f3) - density.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                                    Unit unit = Unit.INSTANCE;
                                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                    }
                                    final MappedInteractionSource mappedInteractionSource = (MappedInteractionSource) objRememberedValue;
                                    if (function8 != null) {
                                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                                        composerStartRestartGroup.endReplaceGroup();
                                    } else {
                                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                                        composerStartRestartGroup.endReplaceGroup();
                                    }
                                    ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                        public final void invoke(Composer composer3, int i14) {
                                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                            }
                                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composerStartRestartGroup, 54);
                                    ComposableLambda composableLambdaRememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors5, value), composerStartRestartGroup, 54);
                                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                    if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue2 = new Function0() { // from class: zea
                                            public final Object invoke() {
                                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                            }
                                        };
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                    }
                                    Function0 function11 = (Function0) objRememberedValue2;
                                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                                    if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = new Function0() { // from class: afa
                                            public final Object invoke() {
                                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                            }
                                        };
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                                    }
                                    NavigationRailItemLayout(composableLambdaRememberComposableLambda2, composableLambdaRememberComposableLambda3, composableLambdaRememberComposableLambda, function9, z11, function11, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                                    composerStartRestartGroup.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    navigationRailItemColors2 = navigationRailItemColors5;
                                    composer2 = composerStartRestartGroup;
                                    z8 = z11;
                                    z7 = z13;
                                    modifier2 = modifier3;
                                    mutableInteractionSource2 = mutableInteractionSource;
                                    function6 = function8;
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    if ((i2 & 128) != 0) {
                                        i3 &= -29360129;
                                    }
                                    navigationRailItemColors3 = navigationRailItemColors;
                                }
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                i12 = i3;
                                Modifier modifier6 = modifier2;
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                                }
                                if (mutableInteractionSource == null) {
                                    composerStartRestartGroup.startReplaceGroup(253288608);
                                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                    }
                                    composerStartRestartGroup.endReplaceGroup();
                                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(1947832599);
                                    composerStartRestartGroup.endReplaceGroup();
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                MotionSchemeKeyTokens motionSchemeKeyTokens2 = MotionSchemeKeyTokens.DefaultEffects;
                                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens2, composerStartRestartGroup, 6);
                                boolean z14 = z10;
                                ComposableLambda composableLambdaRememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                                if (function7 == null) {
                                    composerStartRestartGroup.startReplaceGroup(254215848);
                                    composerStartRestartGroup.endReplaceGroup();
                                    function8 = function7;
                                    function9 = null;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(254215849);
                                    final NavigationRailItemColors navigationRailItemColors6 = navigationRailItemColors3;
                                    final boolean z15 = z9;
                                    final Function2<? super Composer, ? super Integer, Unit> function12 = function7;
                                    function8 = function12;
                                    Function2 function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                        private static final long invoke$lambda$0(State<Color> state) {
                                            return state.getValue().m3144unboximpl();
                                        }

                                        public final void invoke(Composer composer3, int i14) {
                                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                            }
                                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors6.m715textColorWaAFU9c$material3(z, z15), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function12, composer3, 0);
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
                                    function9 = function2RememberComposableLambda2;
                                }
                                boolean z16 = z9;
                                mutableInteractionSource4 = mutableInteractionSource3;
                                Modifier modifier7 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier6, z, mutableInteractionSource4, (Indication) null, z16, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                                float f4 = NavigationRailItemWidth;
                                Modifier modifier8 = SizeKt.widthIn-VpY3zN4$default(modifier7, f4, 0.0f, 2, (Object) null);
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier8);
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
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                                setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting()) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                } else {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier2, companion2.getSetModifier());
                                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                if (z) {
                                    f = 1.0f;
                                } else {
                                    f = 0.0f;
                                }
                                NavigationRailItemColors navigationRailItemColors7 = navigationRailItemColors3;
                                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens2, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                                if (z) {
                                    f2 = 1.0f;
                                } else {
                                    f2 = 0.0f;
                                }
                                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                                Density density2 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density2.mo4551roundToPx0680j_4(f4) - density2.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                                Unit unit2 = Unit.INSTANCE;
                                zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (zChanged) {
                                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                } else {
                                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                final MappedInteractionSource mappedInteractionSource2 = (MappedInteractionSource) objRememberedValue;
                                if (function8 != null) {
                                    composerStartRestartGroup.startReplaceGroup(-1825624334);
                                    value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                                    composerStartRestartGroup.endReplaceGroup();
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(-1825528978);
                                    value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                                    composerStartRestartGroup.endReplaceGroup();
                                }
                                ComposableLambda composableLambdaRememberComposableLambda5 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                    public final void invoke(Composer composer3, int i14) {
                                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                        }
                                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource2, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                ComposableLambda composableLambdaRememberComposableLambda6 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors7, value), composerStartRestartGroup, 54);
                                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (zChanged2) {
                                    objRememberedValue2 = new Function0() { // from class: zea
                                        public final Object invoke() {
                                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function0() { // from class: zea
                                        public final Object invoke() {
                                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                Function0 function13 = (Function0) objRememberedValue2;
                                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                                if (zChanged3) {
                                    objRememberedValue3 = new Function0() { // from class: afa
                                        public final Object invoke() {
                                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                                } else {
                                    objRememberedValue3 = new Function0() { // from class: afa
                                        public final Object invoke() {
                                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                                }
                                NavigationRailItemLayout(composableLambdaRememberComposableLambda5, composableLambdaRememberComposableLambda6, composableLambdaRememberComposableLambda4, function9, z14, function13, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                                composerStartRestartGroup.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                navigationRailItemColors2 = navigationRailItemColors7;
                                composer2 = composerStartRestartGroup;
                                z8 = z14;
                                z7 = z16;
                                modifier2 = modifier6;
                                mutableInteractionSource2 = mutableInteractionSource;
                                function6 = function8;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                mutableInteractionSource2 = mutableInteractionSource;
                                z7 = z4;
                                composer2 = composerStartRestartGroup;
                                z8 = z5;
                                function6 = function5;
                                navigationRailItemColors2 = navigationRailItemColors;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                                    public final Object invoke(Object obj, Object obj2) {
                                        return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i3 |= 100663296;
                        if ((i3 & 38347923) != 38347922) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            } else {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            }
                            i12 = i3;
                            Modifier modifier9 = modifier2;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                            }
                            if (mutableInteractionSource == null) {
                                composerStartRestartGroup.startReplaceGroup(253288608);
                                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1947832599);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens3 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens3, composerStartRestartGroup, 6);
                            boolean z17 = z10;
                            ComposableLambda composableLambdaRememberComposableLambda7 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                            if (function7 == null) {
                                composerStartRestartGroup.startReplaceGroup(254215848);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function7;
                                function9 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(254215849);
                                final NavigationRailItemColors navigationRailItemColors8 = navigationRailItemColors3;
                                final boolean z18 = z9;
                                final Function2<? super Composer, ? super Integer, Unit> function14 = function7;
                                function8 = function14;
                                Function2 function2RememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i14) {
                                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors8.m715textColorWaAFU9c$material3(z, z18), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function14, composer3, 0);
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
                                function9 = function2RememberComposableLambda3;
                            }
                            boolean z19 = z9;
                            mutableInteractionSource4 = mutableInteractionSource3;
                            Modifier modifier10 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier9, z, mutableInteractionSource4, (Indication) null, z19, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                            float f5 = NavigationRailItemWidth;
                            Modifier modifier11 = SizeKt.widthIn-VpY3zN4$default(modifier10, f5, 0.0f, 2, (Object) null);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11);
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
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, companion3.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier3, companion3.getSetModifier());
                            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            NavigationRailItemColors navigationRailItemColors9 = navigationRailItemColors3;
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens3, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density3 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density3.mo4551roundToPx0680j_4(f5) - density3.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                            Unit unit3 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            final MappedInteractionSource mappedInteractionSource3 = (MappedInteractionSource) objRememberedValue;
                            if (function8 != null) {
                                composerStartRestartGroup.startReplaceGroup(-1825624334);
                                value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1825528978);
                                value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            ComposableLambda composableLambdaRememberComposableLambda8 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource3, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda9 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors9, value), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Function0 function15 = (Function0) objRememberedValue2;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            NavigationRailItemLayout(composableLambdaRememberComposableLambda8, composableLambdaRememberComposableLambda9, composableLambdaRememberComposableLambda7, function9, z17, function15, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationRailItemColors2 = navigationRailItemColors9;
                            composer2 = composerStartRestartGroup;
                            z8 = z17;
                            z7 = z19;
                            modifier2 = modifier9;
                            mutableInteractionSource2 = mutableInteractionSource;
                            function6 = function8;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            z7 = z4;
                            composer2 = composerStartRestartGroup;
                            z8 = z5;
                            function6 = function5;
                            navigationRailItemColors2 = navigationRailItemColors;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    z5 = z3;
                    c = ' ';
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 38347923) != 38347922) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            } else {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            }
                            i12 = i3;
                            Modifier modifier12 = modifier2;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                            }
                            if (mutableInteractionSource == null) {
                                composerStartRestartGroup.startReplaceGroup(253288608);
                                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1947832599);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens4 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens4, composerStartRestartGroup, 6);
                            boolean z110 = z10;
                            ComposableLambda composableLambdaRememberComposableLambda10 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                            if (function7 == null) {
                                composerStartRestartGroup.startReplaceGroup(254215848);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function7;
                                function9 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(254215849);
                                final NavigationRailItemColors navigationRailItemColors10 = navigationRailItemColors3;
                                final boolean z111 = z9;
                                final Function2<? super Composer, ? super Integer, Unit> function16 = function7;
                                function8 = function16;
                                Function2 function2RememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i14) {
                                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors10.m715textColorWaAFU9c$material3(z, z111), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function16, composer3, 0);
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
                                function9 = function2RememberComposableLambda4;
                            }
                            boolean z112 = z9;
                            mutableInteractionSource4 = mutableInteractionSource3;
                            Modifier modifier13 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier12, z, mutableInteractionSource4, (Indication) null, z112, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                            float f6 = NavigationRailItemWidth;
                            Modifier modifier14 = SizeKt.widthIn-VpY3zN4$default(modifier13, f6, 0.0f, 2, (Object) null);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier14);
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
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, companion4.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion4.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion4.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion4.getSetModifier());
                            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            NavigationRailItemColors navigationRailItemColors11 = navigationRailItemColors3;
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens4, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density4 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density4.mo4551roundToPx0680j_4(f6) - density4.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                            Unit unit4 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            final MappedInteractionSource mappedInteractionSource4 = (MappedInteractionSource) objRememberedValue;
                            if (function8 != null) {
                                composerStartRestartGroup.startReplaceGroup(-1825624334);
                                value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1825528978);
                                value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            ComposableLambda composableLambdaRememberComposableLambda11 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda12 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors11, value), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Function0 function17 = (Function0) objRememberedValue2;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            NavigationRailItemLayout(composableLambdaRememberComposableLambda11, composableLambdaRememberComposableLambda12, composableLambdaRememberComposableLambda10, function9, z110, function17, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationRailItemColors2 = navigationRailItemColors11;
                            composer2 = composerStartRestartGroup;
                            z8 = z110;
                            z7 = z112;
                            modifier2 = modifier12;
                            mutableInteractionSource2 = mutableInteractionSource;
                            function6 = function8;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            z7 = z4;
                            composer2 = composerStartRestartGroup;
                            z8 = z5;
                            function6 = function5;
                            navigationRailItemColors2 = navigationRailItemColors;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier15 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens5 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens5, composerStartRestartGroup, 6);
                        boolean z113 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda13 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors12 = navigationRailItemColors3;
                            final boolean z114 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function18 = function7;
                            function8 = function18;
                            Function2 function2RememberComposableLambda5 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors12.m715textColorWaAFU9c$material3(z, z114), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function18, composer3, 0);
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
                            function9 = function2RememberComposableLambda5;
                        }
                        boolean z115 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier16 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier15, z, mutableInteractionSource4, (Indication) null, z115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f7 = NavigationRailItemWidth;
                        Modifier modifier17 = SizeKt.widthIn-VpY3zN4$default(modifier16, f7, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier17);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy5, companion5.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap5, companion5.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion5.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier5, companion5.getSetModifier());
                        BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors13 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens5, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density5 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density5.mo4551roundToPx0680j_4(f7) - density5.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit5 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource5 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda14 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource5, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda15 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors13, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function19 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda14, composableLambdaRememberComposableLambda15, composableLambdaRememberComposableLambda13, function9, z113, function19, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors13;
                        composer2 = composerStartRestartGroup;
                        z8 = z113;
                        z7 = z115;
                        modifier2 = modifier15;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function5 = function3;
                i8 = i2 & 64;
                if (i8 != 0) {
                    z5 = z3;
                    if ((i & 1572864) == 0) {
                        c = ' ';
                        if (composerStartRestartGroup.changed(z5)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 38347923) != 38347922) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            } else {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            }
                            i12 = i3;
                            Modifier modifier18 = modifier2;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                            }
                            if (mutableInteractionSource == null) {
                                composerStartRestartGroup.startReplaceGroup(253288608);
                                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1947832599);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens6 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens6, composerStartRestartGroup, 6);
                            boolean z116 = z10;
                            ComposableLambda composableLambdaRememberComposableLambda16 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                            if (function7 == null) {
                                composerStartRestartGroup.startReplaceGroup(254215848);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function7;
                                function9 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(254215849);
                                final NavigationRailItemColors navigationRailItemColors14 = navigationRailItemColors3;
                                final boolean z117 = z9;
                                final Function2<? super Composer, ? super Integer, Unit> function110 = function7;
                                function8 = function110;
                                Function2 function2RememberComposableLambda6 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i14) {
                                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors14.m715textColorWaAFU9c$material3(z, z117), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function110, composer3, 0);
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
                                function9 = function2RememberComposableLambda6;
                            }
                            boolean z118 = z9;
                            mutableInteractionSource4 = mutableInteractionSource3;
                            Modifier modifier19 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier18, z, mutableInteractionSource4, (Indication) null, z118, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                            float f8 = NavigationRailItemWidth;
                            Modifier modifier110 = SizeKt.widthIn-VpY3zN4$default(modifier19, f8, 0.0f, 2, (Object) null);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier110);
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
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy6, companion6.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap6, companion6.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion6.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier6, companion6.getSetModifier());
                            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            NavigationRailItemColors navigationRailItemColors15 = navigationRailItemColors3;
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens6, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density6 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density6.mo4551roundToPx0680j_4(f8) - density6.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                            Unit unit6 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            final MappedInteractionSource mappedInteractionSource6 = (MappedInteractionSource) objRememberedValue;
                            if (function8 != null) {
                                composerStartRestartGroup.startReplaceGroup(-1825624334);
                                value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1825528978);
                                value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            ComposableLambda composableLambdaRememberComposableLambda17 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource6, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda18 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors15, value), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Function0 function111 = (Function0) objRememberedValue2;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            NavigationRailItemLayout(composableLambdaRememberComposableLambda17, composableLambdaRememberComposableLambda18, composableLambdaRememberComposableLambda16, function9, z116, function111, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationRailItemColors2 = navigationRailItemColors15;
                            composer2 = composerStartRestartGroup;
                            z8 = z116;
                            z7 = z118;
                            modifier2 = modifier18;
                            mutableInteractionSource2 = mutableInteractionSource;
                            function6 = function8;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            z7 = z4;
                            composer2 = composerStartRestartGroup;
                            z8 = z5;
                            function6 = function5;
                            navigationRailItemColors2 = navigationRailItemColors;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier111 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens7 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens7, composerStartRestartGroup, 6);
                        boolean z119 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda19 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors16 = navigationRailItemColors3;
                            final boolean z1110 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function112 = function7;
                            function8 = function112;
                            Function2 function2RememberComposableLambda7 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors16.m715textColorWaAFU9c$material3(z, z1110), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function112, composer3, 0);
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
                            function9 = function2RememberComposableLambda7;
                        }
                        boolean z1111 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier112 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier111, z, mutableInteractionSource4, (Indication) null, z1111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f9 = NavigationRailItemWidth;
                        Modifier modifier113 = SizeKt.widthIn-VpY3zN4$default(modifier112, f9, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier113);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, companion7.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap7, companion7.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion7.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier7, companion7.getSetModifier());
                        BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors17 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens7, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density7 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density7.mo4551roundToPx0680j_4(f9) - density7.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit7 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource7 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda110 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource7, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda111 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors17, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function113 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda110, composableLambdaRememberComposableLambda111, composableLambdaRememberComposableLambda19, function9, z119, function113, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors17;
                        composer2 = composerStartRestartGroup;
                        z8 = z119;
                        z7 = z1111;
                        modifier2 = modifier111;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                z5 = z3;
                c = ' ';
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier114 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens8 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens8, composerStartRestartGroup, 6);
                        boolean z1112 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda112 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors18 = navigationRailItemColors3;
                            final boolean z1113 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function114 = function7;
                            function8 = function114;
                            Function2 function2RememberComposableLambda8 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors18.m715textColorWaAFU9c$material3(z, z1113), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function114, composer3, 0);
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
                            function9 = function2RememberComposableLambda8;
                        }
                        boolean z1114 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier115 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier114, z, mutableInteractionSource4, (Indication) null, z1114, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f10 = NavigationRailItemWidth;
                        Modifier modifier116 = SizeKt.widthIn-VpY3zN4$default(modifier115, f10, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier116);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy8, companion8.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap8, companion8.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion8.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier8, companion8.getSetModifier());
                        BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors19 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens8, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density8 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density8.mo4551roundToPx0680j_4(f10) - density8.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit8 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource8 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda113 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource8, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda114 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors19, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function115 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda113, composableLambdaRememberComposableLambda114, composableLambdaRememberComposableLambda112, function9, z1112, function115, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors19;
                        composer2 = composerStartRestartGroup;
                        z8 = z1112;
                        z7 = z1114;
                        modifier2 = modifier114;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier117 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens9 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens9, composerStartRestartGroup, 6);
                    boolean z1115 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda115 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors110 = navigationRailItemColors3;
                        final boolean z1116 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function116 = function7;
                        function8 = function116;
                        Function2 function2RememberComposableLambda9 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors110.m715textColorWaAFU9c$material3(z, z1116), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function116, composer3, 0);
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
                        function9 = function2RememberComposableLambda9;
                    }
                    boolean z1117 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier118 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier117, z, mutableInteractionSource4, (Indication) null, z1117, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f11 = NavigationRailItemWidth;
                    Modifier modifier119 = SizeKt.widthIn-VpY3zN4$default(modifier118, f11, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy9 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier119);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy9, companion9.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap9, companion9.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion9.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier9, companion9.getSetModifier());
                    BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors111 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens9, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density9 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density9.mo4551roundToPx0680j_4(f11) - density9.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit9 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource9 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda116 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource9, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda117 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors111, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function117 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda116, composableLambdaRememberComposableLambda117, composableLambdaRememberComposableLambda115, function9, z1115, function117, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors111;
                    composer2 = composerStartRestartGroup;
                    z8 = z1115;
                    z7 = z1117;
                    modifier2 = modifier117;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z4 = z2;
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    z5 = z3;
                    if ((i & 1572864) == 0) {
                        c = ' ';
                        if (composerStartRestartGroup.changed(z5)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 38347923) != 38347922) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            } else {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            }
                            i12 = i3;
                            Modifier modifier1110 = modifier2;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                            }
                            if (mutableInteractionSource == null) {
                                composerStartRestartGroup.startReplaceGroup(253288608);
                                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1947832599);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens10 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens10, composerStartRestartGroup, 6);
                            boolean z1118 = z10;
                            ComposableLambda composableLambdaRememberComposableLambda118 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                            if (function7 == null) {
                                composerStartRestartGroup.startReplaceGroup(254215848);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function7;
                                function9 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(254215849);
                                final NavigationRailItemColors navigationRailItemColors112 = navigationRailItemColors3;
                                final boolean z1119 = z9;
                                final Function2<? super Composer, ? super Integer, Unit> function118 = function7;
                                function8 = function118;
                                Function2 function2RememberComposableLambda10 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i14) {
                                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors112.m715textColorWaAFU9c$material3(z, z1119), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function118, composer3, 0);
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
                                function9 = function2RememberComposableLambda10;
                            }
                            boolean z11110 = z9;
                            mutableInteractionSource4 = mutableInteractionSource3;
                            Modifier modifier1111 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1110, z, mutableInteractionSource4, (Indication) null, z11110, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                            float f12 = NavigationRailItemWidth;
                            Modifier modifier1112 = SizeKt.widthIn-VpY3zN4$default(modifier1111, f12, 0.0f, 2, (Object) null);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy10 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1112);
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
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy10, companion10.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap10, companion10.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion10.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier10, companion10.getSetModifier());
                            BoxScopeInstance boxScopeInstance10 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            NavigationRailItemColors navigationRailItemColors113 = navigationRailItemColors3;
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens10, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density10 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density10.mo4551roundToPx0680j_4(f12) - density10.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                            Unit unit10 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            final MappedInteractionSource mappedInteractionSource10 = (MappedInteractionSource) objRememberedValue;
                            if (function8 != null) {
                                composerStartRestartGroup.startReplaceGroup(-1825624334);
                                value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1825528978);
                                value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            ComposableLambda composableLambdaRememberComposableLambda119 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource10, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda1110 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors113, value), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Function0 function119 = (Function0) objRememberedValue2;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            NavigationRailItemLayout(composableLambdaRememberComposableLambda119, composableLambdaRememberComposableLambda1110, composableLambdaRememberComposableLambda118, function9, z1118, function119, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationRailItemColors2 = navigationRailItemColors113;
                            composer2 = composerStartRestartGroup;
                            z8 = z1118;
                            z7 = z11110;
                            modifier2 = modifier1110;
                            mutableInteractionSource2 = mutableInteractionSource;
                            function6 = function8;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            z7 = z4;
                            composer2 = composerStartRestartGroup;
                            z8 = z5;
                            function6 = function5;
                            navigationRailItemColors2 = navigationRailItemColors;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier1113 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens11 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens11, composerStartRestartGroup, 6);
                        boolean z11111 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda1111 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors114 = navigationRailItemColors3;
                            final boolean z11112 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function1110 = function7;
                            function8 = function1110;
                            Function2 function2RememberComposableLambda11 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors114.m715textColorWaAFU9c$material3(z, z11112), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1110, composer3, 0);
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
                            function9 = function2RememberComposableLambda11;
                        }
                        boolean z11113 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier1114 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1113, z, mutableInteractionSource4, (Indication) null, z11113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f13 = NavigationRailItemWidth;
                        Modifier modifier1115 = SizeKt.widthIn-VpY3zN4$default(modifier1114, f13, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy11 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1115);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy11, companion11.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap11, companion11.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion11.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier11, companion11.getSetModifier());
                        BoxScopeInstance boxScopeInstance11 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors115 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens11, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density11 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density11.mo4551roundToPx0680j_4(f13) - density11.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit11 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource11 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda1112 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource11, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda1113 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors115, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function1111 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda1112, composableLambdaRememberComposableLambda1113, composableLambdaRememberComposableLambda1111, function9, z11111, function1111, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors115;
                        composer2 = composerStartRestartGroup;
                        z8 = z11111;
                        z7 = z11113;
                        modifier2 = modifier1113;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                z5 = z3;
                c = ' ';
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier1116 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens12 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens12, composerStartRestartGroup, 6);
                        boolean z11114 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda1114 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors116 = navigationRailItemColors3;
                            final boolean z11115 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function1112 = function7;
                            function8 = function1112;
                            Function2 function2RememberComposableLambda12 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors116.m715textColorWaAFU9c$material3(z, z11115), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1112, composer3, 0);
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
                            function9 = function2RememberComposableLambda12;
                        }
                        boolean z11116 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier1117 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1116, z, mutableInteractionSource4, (Indication) null, z11116, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f14 = NavigationRailItemWidth;
                        Modifier modifier1118 = SizeKt.widthIn-VpY3zN4$default(modifier1117, f14, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy12 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1118);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy12, companion12.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap12, companion12.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion12.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier12, companion12.getSetModifier());
                        BoxScopeInstance boxScopeInstance12 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors117 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens12, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density12 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density12.mo4551roundToPx0680j_4(f14) - density12.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit12 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource12 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda1115 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource12, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda1116 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors117, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function1113 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda1115, composableLambdaRememberComposableLambda1116, composableLambdaRememberComposableLambda1114, function9, z11114, function1113, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors117;
                        composer2 = composerStartRestartGroup;
                        z8 = z11114;
                        z7 = z11116;
                        modifier2 = modifier1116;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier1119 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens13 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens13, composerStartRestartGroup, 6);
                    boolean z11117 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda1117 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors118 = navigationRailItemColors3;
                        final boolean z11118 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function1114 = function7;
                        function8 = function1114;
                        Function2 function2RememberComposableLambda13 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors118.m715textColorWaAFU9c$material3(z, z11118), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1114, composer3, 0);
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
                        function9 = function2RememberComposableLambda13;
                    }
                    boolean z11119 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier11110 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1119, z, mutableInteractionSource4, (Indication) null, z11119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f15 = NavigationRailItemWidth;
                    Modifier modifier11111 = SizeKt.widthIn-VpY3zN4$default(modifier11110, f15, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy13 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11111);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy13, companion13.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap13, companion13.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion13.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier13, companion13.getSetModifier());
                    BoxScopeInstance boxScopeInstance13 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors119 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens13, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density13 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density13.mo4551roundToPx0680j_4(f15) - density13.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit13 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource13 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda1118 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource13, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1119 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors119, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function1115 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda1118, composableLambdaRememberComposableLambda1119, composableLambdaRememberComposableLambda1117, function9, z11117, function1115, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors119;
                    composer2 = composerStartRestartGroup;
                    z8 = z11117;
                    z7 = z11119;
                    modifier2 = modifier1119;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
            i8 = i2 & 64;
            if (i8 != 0) {
                z5 = z3;
                if ((i & 1572864) == 0) {
                    c = ' ';
                    if (composerStartRestartGroup.changed(z5)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier11112 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens14 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens14, composerStartRestartGroup, 6);
                        boolean z111110 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda11110 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors1110 = navigationRailItemColors3;
                            final boolean z111111 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function1116 = function7;
                            function8 = function1116;
                            Function2 function2RememberComposableLambda14 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1110.m715textColorWaAFU9c$material3(z, z111111), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1116, composer3, 0);
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
                            function9 = function2RememberComposableLambda14;
                        }
                        boolean z111112 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier11113 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier11112, z, mutableInteractionSource4, (Indication) null, z111112, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f16 = NavigationRailItemWidth;
                        Modifier modifier11114 = SizeKt.widthIn-VpY3zN4$default(modifier11113, f16, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy14 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11114);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy14, companion14.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap14, companion14.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion14.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier14, companion14.getSetModifier());
                        BoxScopeInstance boxScopeInstance14 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors1111 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens14, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density14 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density14.mo4551roundToPx0680j_4(f16) - density14.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit14 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource14 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda11111 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource14, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda11112 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1111, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function1117 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda11111, composableLambdaRememberComposableLambda11112, composableLambdaRememberComposableLambda11110, function9, z111110, function1117, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors1111;
                        composer2 = composerStartRestartGroup;
                        z8 = z111110;
                        z7 = z111112;
                        modifier2 = modifier11112;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier11115 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens15 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens15, composerStartRestartGroup, 6);
                    boolean z111113 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda11113 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors1112 = navigationRailItemColors3;
                        final boolean z111114 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function1118 = function7;
                        function8 = function1118;
                        Function2 function2RememberComposableLambda15 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1112.m715textColorWaAFU9c$material3(z, z111114), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1118, composer3, 0);
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
                        function9 = function2RememberComposableLambda15;
                    }
                    boolean z111115 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier11116 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier11115, z, mutableInteractionSource4, (Indication) null, z111115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f17 = NavigationRailItemWidth;
                    Modifier modifier11117 = SizeKt.widthIn-VpY3zN4$default(modifier11116, f17, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy15 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11117);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy15, companion15.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap15, companion15.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion15.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier15, companion15.getSetModifier());
                    BoxScopeInstance boxScopeInstance15 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors1113 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens15, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density15 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density15.mo4551roundToPx0680j_4(f17) - density15.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit15 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource15 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda11114 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource15, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda11115 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1113, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function1119 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda11114, composableLambdaRememberComposableLambda11115, composableLambdaRememberComposableLambda11113, function9, z111113, function1119, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors1113;
                    composer2 = composerStartRestartGroup;
                    z8 = z111113;
                    z7 = z111115;
                    modifier2 = modifier11115;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            z5 = z3;
            c = ' ';
            if ((i & 12582912) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier11118 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens16 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens16, composerStartRestartGroup, 6);
                    boolean z111116 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda11116 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors1114 = navigationRailItemColors3;
                        final boolean z111117 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function11110 = function7;
                        function8 = function11110;
                        Function2 function2RememberComposableLambda16 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1114.m715textColorWaAFU9c$material3(z, z111117), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11110, composer3, 0);
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
                        function9 = function2RememberComposableLambda16;
                    }
                    boolean z111118 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier11119 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier11118, z, mutableInteractionSource4, (Indication) null, z111118, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f18 = NavigationRailItemWidth;
                    Modifier modifier111110 = SizeKt.widthIn-VpY3zN4$default(modifier11119, f18, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy16 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier111110);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy16, companion16.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap16, companion16.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion16.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier16, companion16.getSetModifier());
                    BoxScopeInstance boxScopeInstance16 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors1115 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens16, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density16 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density16.mo4551roundToPx0680j_4(f18) - density16.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit16 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource16 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda11117 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource16, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda11118 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1115, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function11111 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda11117, composableLambdaRememberComposableLambda11118, composableLambdaRememberComposableLambda11116, function9, z111116, function11111, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors1115;
                    composer2 = composerStartRestartGroup;
                    z8 = z111116;
                    z7 = z111118;
                    modifier2 = modifier11118;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) != 38347922) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                }
                i12 = i3;
                Modifier modifier111111 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                }
                if (mutableInteractionSource == null) {
                    composerStartRestartGroup.startReplaceGroup(253288608);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1947832599);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens17 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens17, composerStartRestartGroup, 6);
                boolean z111119 = z10;
                ComposableLambda composableLambdaRememberComposableLambda11119 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                if (function7 == null) {
                    composerStartRestartGroup.startReplaceGroup(254215848);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function7;
                    function9 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(254215849);
                    final NavigationRailItemColors navigationRailItemColors1116 = navigationRailItemColors3;
                    final boolean z1111110 = z9;
                    final Function2<? super Composer, ? super Integer, Unit> function11112 = function7;
                    function8 = function11112;
                    Function2 function2RememberComposableLambda17 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1116.m715textColorWaAFU9c$material3(z, z1111110), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11112, composer3, 0);
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
                    function9 = function2RememberComposableLambda17;
                }
                boolean z1111111 = z9;
                mutableInteractionSource4 = mutableInteractionSource3;
                Modifier modifier111112 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier111111, z, mutableInteractionSource4, (Indication) null, z1111111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                float f19 = NavigationRailItemWidth;
                Modifier modifier111113 = SizeKt.widthIn-VpY3zN4$default(modifier111112, f19, 0.0f, 2, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy17 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap17 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier17 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier111113);
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
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy17, companion17.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap17, companion17.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion17.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier17, companion17.getSetModifier());
                BoxScopeInstance boxScopeInstance17 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                NavigationRailItemColors navigationRailItemColors1117 = navigationRailItemColors3;
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens17, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density17 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density17.mo4551roundToPx0680j_4(f19) - density17.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                Unit unit17 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final MappedInteractionSource mappedInteractionSource17 = (MappedInteractionSource) objRememberedValue;
                if (function8 != null) {
                    composerStartRestartGroup.startReplaceGroup(-1825624334);
                    value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1825528978);
                    value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposableLambda composableLambdaRememberComposableLambda111110 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                    public final void invoke(Composer composer3, int i14) {
                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource17, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda111111 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1117, value), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function0 function11113 = (Function0) objRememberedValue2;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                NavigationRailItemLayout(composableLambdaRememberComposableLambda111110, composableLambdaRememberComposableLambda111111, composableLambdaRememberComposableLambda11119, function9, z111119, function11113, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationRailItemColors2 = navigationRailItemColors1117;
                composer2 = composerStartRestartGroup;
                z8 = z111119;
                z7 = z1111111;
                modifier2 = modifier111111;
                mutableInteractionSource2 = mutableInteractionSource;
                function6 = function8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                z7 = z4;
                composer2 = composerStartRestartGroup;
                z8 = z5;
                function6 = function5;
                navigationRailItemColors2 = navigationRailItemColors;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    z5 = z3;
                    if ((i & 1572864) == 0) {
                        c = ' ';
                        if (composerStartRestartGroup.changed(z5)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i & 12582912) != 0) {
                        i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 38347923) != 38347922) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            } else {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function5 = null;
                                }
                                if (i8 != 0) {
                                    z5 = true;
                                }
                                if ((i2 & 128) != 0) {
                                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -29360129;
                                } else {
                                    navigationRailItemColorsColors = navigationRailItemColors;
                                }
                                if (i10 != 0) {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                    mutableInteractionSource = null;
                                } else {
                                    navigationRailItemColors3 = navigationRailItemColorsColors;
                                    z9 = z4;
                                    z10 = z5;
                                    function7 = function5;
                                }
                            }
                            i12 = i3;
                            Modifier modifier111114 = modifier2;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                            }
                            if (mutableInteractionSource == null) {
                                composerStartRestartGroup.startReplaceGroup(253288608);
                                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1947832599);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            MotionSchemeKeyTokens motionSchemeKeyTokens18 = MotionSchemeKeyTokens.DefaultEffects;
                            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens18, composerStartRestartGroup, 6);
                            boolean z1111112 = z10;
                            ComposableLambda composableLambdaRememberComposableLambda111112 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                            if (function7 == null) {
                                composerStartRestartGroup.startReplaceGroup(254215848);
                                composerStartRestartGroup.endReplaceGroup();
                                function8 = function7;
                                function9 = null;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(254215849);
                                final NavigationRailItemColors navigationRailItemColors1118 = navigationRailItemColors3;
                                final boolean z1111113 = z9;
                                final Function2<? super Composer, ? super Integer, Unit> function11114 = function7;
                                function8 = function11114;
                                Function2 function2RememberComposableLambda18 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                    private static final long invoke$lambda$0(State<Color> state) {
                                        return state.getValue().m3144unboximpl();
                                    }

                                    public final void invoke(Composer composer3, int i14) {
                                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                        }
                                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1118.m715textColorWaAFU9c$material3(z, z1111113), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11114, composer3, 0);
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
                                function9 = function2RememberComposableLambda18;
                            }
                            boolean z1111114 = z9;
                            mutableInteractionSource4 = mutableInteractionSource3;
                            Modifier modifier111115 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier111114, z, mutableInteractionSource4, (Indication) null, z1111114, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                            float f110 = NavigationRailItemWidth;
                            Modifier modifier111116 = SizeKt.widthIn-VpY3zN4$default(modifier111115, f110, 0.0f, 2, (Object) null);
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy18 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap18 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier18 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier111116);
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
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy18, companion18.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap18, companion18.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion18.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting()) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            } else {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier18, companion18.getSetModifier());
                            BoxScopeInstance boxScopeInstance18 = BoxScopeInstance.INSTANCE;
                            if (z) {
                                f = 1.0f;
                            } else {
                                f = 0.0f;
                            }
                            NavigationRailItemColors navigationRailItemColors1119 = navigationRailItemColors3;
                            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens18, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            if (z) {
                                f2 = 1.0f;
                            } else {
                                f2 = 0.0f;
                            }
                            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                            Density density18 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density18.mo4551roundToPx0680j_4(f110) - density18.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                            Unit unit18 = Unit.INSTANCE;
                            zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            final MappedInteractionSource mappedInteractionSource18 = (MappedInteractionSource) objRememberedValue;
                            if (function8 != null) {
                                composerStartRestartGroup.startReplaceGroup(-1825624334);
                                value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-1825528978);
                                value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            ComposableLambda composableLambdaRememberComposableLambda111113 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource18, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda111114 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1119, value), composerStartRestartGroup, 54);
                            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged2) {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function0() { // from class: zea
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Function0 function11115 = (Function0) objRememberedValue2;
                            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged3) {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function0() { // from class: afa
                                    public final Object invoke() {
                                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            NavigationRailItemLayout(composableLambdaRememberComposableLambda111113, composableLambdaRememberComposableLambda111114, composableLambdaRememberComposableLambda111112, function9, z1111112, function11115, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            navigationRailItemColors2 = navigationRailItemColors1119;
                            composer2 = composerStartRestartGroup;
                            z8 = z1111112;
                            z7 = z1111114;
                            modifier2 = modifier111114;
                            mutableInteractionSource2 = mutableInteractionSource;
                            function6 = function8;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            z7 = z4;
                            composer2 = composerStartRestartGroup;
                            z8 = z5;
                            function6 = function5;
                            navigationRailItemColors2 = navigationRailItemColors;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                                public final Object invoke(Object obj, Object obj2) {
                                    return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier111117 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens19 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens19, composerStartRestartGroup, 6);
                        boolean z1111115 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda111115 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors11110 = navigationRailItemColors3;
                            final boolean z1111116 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function11116 = function7;
                            function8 = function11116;
                            Function2 function2RememberComposableLambda19 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors11110.m715textColorWaAFU9c$material3(z, z1111116), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11116, composer3, 0);
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
                            function9 = function2RememberComposableLambda19;
                        }
                        boolean z1111117 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier111118 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier111117, z, mutableInteractionSource4, (Indication) null, z1111117, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f111 = NavigationRailItemWidth;
                        Modifier modifier111119 = SizeKt.widthIn-VpY3zN4$default(modifier111118, f111, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy19 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap19 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier19 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier111119);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy19, companion19.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap19, companion19.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion19.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier19, companion19.getSetModifier());
                        BoxScopeInstance boxScopeInstance19 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors11111 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens19, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density19 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density19.mo4551roundToPx0680j_4(f111) - density19.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit19 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource19 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda111116 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource19, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda111117 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors11111, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function11117 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda111116, composableLambdaRememberComposableLambda111117, composableLambdaRememberComposableLambda111115, function9, z1111115, function11117, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors11111;
                        composer2 = composerStartRestartGroup;
                        z8 = z1111115;
                        z7 = z1111117;
                        modifier2 = modifier111117;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                z5 = z3;
                c = ' ';
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier1111110 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens110 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens110, composerStartRestartGroup, 6);
                        boolean z1111118 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda111118 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors11112 = navigationRailItemColors3;
                            final boolean z1111119 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function11118 = function7;
                            function8 = function11118;
                            Function2 function2RememberComposableLambda110 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors11112.m715textColorWaAFU9c$material3(z, z1111119), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11118, composer3, 0);
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
                            function9 = function2RememberComposableLambda110;
                        }
                        boolean z11111110 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier1111111 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1111110, z, mutableInteractionSource4, (Indication) null, z11111110, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f112 = NavigationRailItemWidth;
                        Modifier modifier1111112 = SizeKt.widthIn-VpY3zN4$default(modifier1111111, f112, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy110 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1111112);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy110, companion110.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap110, companion110.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion110.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier110, companion110.getSetModifier());
                        BoxScopeInstance boxScopeInstance110 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors11113 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens110, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density110 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density110.mo4551roundToPx0680j_4(f112) - density110.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit110 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource110 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda111119 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource110, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda1111110 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors11113, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function11119 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda111119, composableLambdaRememberComposableLambda1111110, composableLambdaRememberComposableLambda111118, function9, z1111118, function11119, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors11113;
                        composer2 = composerStartRestartGroup;
                        z8 = z1111118;
                        z7 = z11111110;
                        modifier2 = modifier1111110;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier1111113 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens111 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens111, composerStartRestartGroup, 6);
                    boolean z11111111 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda1111111 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors11114 = navigationRailItemColors3;
                        final boolean z11111112 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function111110 = function7;
                        function8 = function111110;
                        Function2 function2RememberComposableLambda111 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors11114.m715textColorWaAFU9c$material3(z, z11111112), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111110, composer3, 0);
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
                        function9 = function2RememberComposableLambda111;
                    }
                    boolean z11111113 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier1111114 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1111113, z, mutableInteractionSource4, (Indication) null, z11111113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f113 = NavigationRailItemWidth;
                    Modifier modifier1111115 = SizeKt.widthIn-VpY3zN4$default(modifier1111114, f113, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy111 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1111115);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy111, companion111.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap111, companion111.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion111.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier111, companion111.getSetModifier());
                    BoxScopeInstance boxScopeInstance111 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors11115 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens111, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density111 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density111.mo4551roundToPx0680j_4(f113) - density111.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit111 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource111 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda1111112 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource111, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1111113 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors11115, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function111111 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda1111112, composableLambdaRememberComposableLambda1111113, composableLambdaRememberComposableLambda1111111, function9, z11111111, function111111, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors11115;
                    composer2 = composerStartRestartGroup;
                    z8 = z11111111;
                    z7 = z11111113;
                    modifier2 = modifier1111113;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
            i8 = i2 & 64;
            if (i8 != 0) {
                z5 = z3;
                if ((i & 1572864) == 0) {
                    c = ' ';
                    if (composerStartRestartGroup.changed(z5)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier1111116 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens112 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens112, composerStartRestartGroup, 6);
                        boolean z11111114 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda1111114 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors11116 = navigationRailItemColors3;
                            final boolean z11111115 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function111112 = function7;
                            function8 = function111112;
                            Function2 function2RememberComposableLambda112 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors11116.m715textColorWaAFU9c$material3(z, z11111115), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111112, composer3, 0);
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
                            function9 = function2RememberComposableLambda112;
                        }
                        boolean z11111116 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier1111117 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1111116, z, mutableInteractionSource4, (Indication) null, z11111116, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f114 = NavigationRailItemWidth;
                        Modifier modifier1111118 = SizeKt.widthIn-VpY3zN4$default(modifier1111117, f114, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy112 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap112 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier112 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1111118);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy112, companion112.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap112, companion112.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion112.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier112, companion112.getSetModifier());
                        BoxScopeInstance boxScopeInstance112 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors11117 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens112, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density112 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density112.mo4551roundToPx0680j_4(f114) - density112.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit112 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource112 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda1111115 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource112, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda1111116 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors11117, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function111113 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda1111115, composableLambdaRememberComposableLambda1111116, composableLambdaRememberComposableLambda1111114, function9, z11111114, function111113, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors11117;
                        composer2 = composerStartRestartGroup;
                        z8 = z11111114;
                        z7 = z11111116;
                        modifier2 = modifier1111116;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier1111119 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens113 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens113, composerStartRestartGroup, 6);
                    boolean z11111117 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda1111117 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors11118 = navigationRailItemColors3;
                        final boolean z11111118 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function111114 = function7;
                        function8 = function111114;
                        Function2 function2RememberComposableLambda113 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors11118.m715textColorWaAFU9c$material3(z, z11111118), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111114, composer3, 0);
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
                        function9 = function2RememberComposableLambda113;
                    }
                    boolean z11111119 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier11111110 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1111119, z, mutableInteractionSource4, (Indication) null, z11111119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f115 = NavigationRailItemWidth;
                    Modifier modifier11111111 = SizeKt.widthIn-VpY3zN4$default(modifier11111110, f115, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy113 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap113 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier113 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11111111);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy113, companion113.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap113, companion113.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion113.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier113, companion113.getSetModifier());
                    BoxScopeInstance boxScopeInstance113 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors11119 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens113, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density113 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density113.mo4551roundToPx0680j_4(f115) - density113.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit113 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource113 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda1111118 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource113, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1111119 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors11119, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function111115 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda1111118, composableLambdaRememberComposableLambda1111119, composableLambdaRememberComposableLambda1111117, function9, z11111117, function111115, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors11119;
                    composer2 = composerStartRestartGroup;
                    z8 = z11111117;
                    z7 = z11111119;
                    modifier2 = modifier1111119;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            z5 = z3;
            c = ' ';
            if ((i & 12582912) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier11111112 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens114 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens114, composerStartRestartGroup, 6);
                    boolean z111111110 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda11111110 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors111110 = navigationRailItemColors3;
                        final boolean z111111111 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function111116 = function7;
                        function8 = function111116;
                        Function2 function2RememberComposableLambda114 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors111110.m715textColorWaAFU9c$material3(z, z111111111), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111116, composer3, 0);
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
                        function9 = function2RememberComposableLambda114;
                    }
                    boolean z111111112 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier11111113 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier11111112, z, mutableInteractionSource4, (Indication) null, z111111112, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f116 = NavigationRailItemWidth;
                    Modifier modifier11111114 = SizeKt.widthIn-VpY3zN4$default(modifier11111113, f116, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy114 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap114 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier114 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11111114);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy114, companion114.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap114, companion114.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion114.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier114, companion114.getSetModifier());
                    BoxScopeInstance boxScopeInstance114 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors111111 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens114, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density114 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density114.mo4551roundToPx0680j_4(f116) - density114.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit114 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource114 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda11111111 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource114, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda11111112 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors111111, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function111117 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda11111111, composableLambdaRememberComposableLambda11111112, composableLambdaRememberComposableLambda11111110, function9, z111111110, function111117, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors111111;
                    composer2 = composerStartRestartGroup;
                    z8 = z111111110;
                    z7 = z111111112;
                    modifier2 = modifier11111112;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) != 38347922) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                }
                i12 = i3;
                Modifier modifier11111115 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                }
                if (mutableInteractionSource == null) {
                    composerStartRestartGroup.startReplaceGroup(253288608);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1947832599);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens115 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens115, composerStartRestartGroup, 6);
                boolean z111111113 = z10;
                ComposableLambda composableLambdaRememberComposableLambda11111113 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                if (function7 == null) {
                    composerStartRestartGroup.startReplaceGroup(254215848);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function7;
                    function9 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(254215849);
                    final NavigationRailItemColors navigationRailItemColors111112 = navigationRailItemColors3;
                    final boolean z111111114 = z9;
                    final Function2<? super Composer, ? super Integer, Unit> function111118 = function7;
                    function8 = function111118;
                    Function2 function2RememberComposableLambda115 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors111112.m715textColorWaAFU9c$material3(z, z111111114), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function111118, composer3, 0);
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
                    function9 = function2RememberComposableLambda115;
                }
                boolean z111111115 = z9;
                mutableInteractionSource4 = mutableInteractionSource3;
                Modifier modifier11111116 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier11111115, z, mutableInteractionSource4, (Indication) null, z111111115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                float f117 = NavigationRailItemWidth;
                Modifier modifier11111117 = SizeKt.widthIn-VpY3zN4$default(modifier11111116, f117, 0.0f, 2, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy115 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap115 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier115 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11111117);
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
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy115, companion115.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap115, companion115.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion115.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier115, companion115.getSetModifier());
                BoxScopeInstance boxScopeInstance115 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                NavigationRailItemColors navigationRailItemColors111113 = navigationRailItemColors3;
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens115, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density115 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density115.mo4551roundToPx0680j_4(f117) - density115.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                Unit unit115 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final MappedInteractionSource mappedInteractionSource115 = (MappedInteractionSource) objRememberedValue;
                if (function8 != null) {
                    composerStartRestartGroup.startReplaceGroup(-1825624334);
                    value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1825528978);
                    value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposableLambda composableLambdaRememberComposableLambda11111114 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                    public final void invoke(Composer composer3, int i14) {
                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource115, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda11111115 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors111113, value), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function0 function111119 = (Function0) objRememberedValue2;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                NavigationRailItemLayout(composableLambdaRememberComposableLambda11111114, composableLambdaRememberComposableLambda11111115, composableLambdaRememberComposableLambda11111113, function9, z111111113, function111119, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationRailItemColors2 = navigationRailItemColors111113;
                composer2 = composerStartRestartGroup;
                z8 = z111111113;
                z7 = z111111115;
                modifier2 = modifier11111115;
                mutableInteractionSource2 = mutableInteractionSource;
                function6 = function8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                z7 = z4;
                composer2 = composerStartRestartGroup;
                z8 = z5;
                function6 = function5;
                navigationRailItemColors2 = navigationRailItemColors;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        z4 = z2;
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                function5 = function3;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                z5 = z3;
                if ((i & 1572864) == 0) {
                    c = ' ';
                    if (composerStartRestartGroup.changed(z5)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i & 12582912) != 0) {
                    i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function5 = null;
                            }
                            if (i8 != 0) {
                                z5 = true;
                            }
                            if ((i2 & 128) != 0) {
                                navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -29360129;
                            } else {
                                navigationRailItemColorsColors = navigationRailItemColors;
                            }
                            if (i10 != 0) {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                                mutableInteractionSource = null;
                            } else {
                                navigationRailItemColors3 = navigationRailItemColorsColors;
                                z9 = z4;
                                z10 = z5;
                                function7 = function5;
                            }
                        }
                        i12 = i3;
                        Modifier modifier11111118 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                        }
                        if (mutableInteractionSource == null) {
                            composerStartRestartGroup.startReplaceGroup(253288608);
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1947832599);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        MotionSchemeKeyTokens motionSchemeKeyTokens116 = MotionSchemeKeyTokens.DefaultEffects;
                        finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens116, composerStartRestartGroup, 6);
                        boolean z111111116 = z10;
                        ComposableLambda composableLambdaRememberComposableLambda11111116 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                        if (function7 == null) {
                            composerStartRestartGroup.startReplaceGroup(254215848);
                            composerStartRestartGroup.endReplaceGroup();
                            function8 = function7;
                            function9 = null;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(254215849);
                            final NavigationRailItemColors navigationRailItemColors111114 = navigationRailItemColors3;
                            final boolean z111111117 = z9;
                            final Function2<? super Composer, ? super Integer, Unit> function1111110 = function7;
                            function8 = function1111110;
                            Function2 function2RememberComposableLambda116 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m3144unboximpl();
                                }

                                public final void invoke(Composer composer3, int i14) {
                                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                    }
                                    ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors111114.m715textColorWaAFU9c$material3(z, z111111117), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111110, composer3, 0);
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
                            function9 = function2RememberComposableLambda116;
                        }
                        boolean z111111118 = z9;
                        mutableInteractionSource4 = mutableInteractionSource3;
                        Modifier modifier11111119 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier11111118, z, mutableInteractionSource4, (Indication) null, z111111118, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                        float f118 = NavigationRailItemWidth;
                        Modifier modifier111111110 = SizeKt.widthIn-VpY3zN4$default(modifier11111119, f118, 0.0f, 2, (Object) null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy116 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap116 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier116 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier111111110);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy116, companion116.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap116, companion116.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion116.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier116, companion116.getSetModifier());
                        BoxScopeInstance boxScopeInstance116 = BoxScopeInstance.INSTANCE;
                        if (z) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        NavigationRailItemColors navigationRailItemColors111115 = navigationRailItemColors3;
                        stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens116, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        if (z) {
                            f2 = 1.0f;
                        } else {
                            f2 = 0.0f;
                        }
                        stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                        Density density116 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density116.mo4551roundToPx0680j_4(f118) - density116.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                        Unit unit116 = Unit.INSTANCE;
                        zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        final MappedInteractionSource mappedInteractionSource116 = (MappedInteractionSource) objRememberedValue;
                        if (function8 != null) {
                            composerStartRestartGroup.startReplaceGroup(-1825624334);
                            value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1825528978);
                            value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        ComposableLambda composableLambdaRememberComposableLambda11111117 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource116, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda11111118 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors111115, value), composerStartRestartGroup, 54);
                        zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged2) {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function0() { // from class: zea
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function1111111 = (Function0) objRememberedValue2;
                        zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged3) {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function0() { // from class: afa
                                public final Object invoke() {
                                    return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        NavigationRailItemLayout(composableLambdaRememberComposableLambda11111117, composableLambdaRememberComposableLambda11111118, composableLambdaRememberComposableLambda11111116, function9, z111111116, function1111111, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        navigationRailItemColors2 = navigationRailItemColors111115;
                        composer2 = composerStartRestartGroup;
                        z8 = z111111116;
                        z7 = z111111118;
                        modifier2 = modifier11111118;
                        mutableInteractionSource2 = mutableInteractionSource;
                        function6 = function8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        z7 = z4;
                        composer2 = composerStartRestartGroup;
                        z8 = z5;
                        function6 = function5;
                        navigationRailItemColors2 = navigationRailItemColors;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier111111111 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens117 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens117, composerStartRestartGroup, 6);
                    boolean z111111119 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda11111119 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors111116 = navigationRailItemColors3;
                        final boolean z1111111110 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function1111112 = function7;
                        function8 = function1111112;
                        Function2 function2RememberComposableLambda117 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors111116.m715textColorWaAFU9c$material3(z, z1111111110), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111112, composer3, 0);
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
                        function9 = function2RememberComposableLambda117;
                    }
                    boolean z1111111111 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier111111112 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier111111111, z, mutableInteractionSource4, (Indication) null, z1111111111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f119 = NavigationRailItemWidth;
                    Modifier modifier111111113 = SizeKt.widthIn-VpY3zN4$default(modifier111111112, f119, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy117 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap117 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier117 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier111111113);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy117, companion117.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap117, companion117.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion117.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier117, companion117.getSetModifier());
                    BoxScopeInstance boxScopeInstance117 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors111117 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens117, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density117 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density117.mo4551roundToPx0680j_4(f119) - density117.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit117 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource117 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda111111110 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource117, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda111111111 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors111117, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function1111113 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda111111110, composableLambdaRememberComposableLambda111111111, composableLambdaRememberComposableLambda11111119, function9, z111111119, function1111113, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors111117;
                    composer2 = composerStartRestartGroup;
                    z8 = z111111119;
                    z7 = z1111111111;
                    modifier2 = modifier111111111;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            z5 = z3;
            c = ' ';
            if ((i & 12582912) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier111111114 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens118 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens118, composerStartRestartGroup, 6);
                    boolean z1111111112 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda111111112 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors111118 = navigationRailItemColors3;
                        final boolean z1111111113 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function1111114 = function7;
                        function8 = function1111114;
                        Function2 function2RememberComposableLambda118 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors111118.m715textColorWaAFU9c$material3(z, z1111111113), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111114, composer3, 0);
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
                        function9 = function2RememberComposableLambda118;
                    }
                    boolean z1111111114 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier111111115 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier111111114, z, mutableInteractionSource4, (Indication) null, z1111111114, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f1110 = NavigationRailItemWidth;
                    Modifier modifier111111116 = SizeKt.widthIn-VpY3zN4$default(modifier111111115, f1110, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy118 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap118 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier118 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier111111116);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy118, companion118.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap118, companion118.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion118.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier118, companion118.getSetModifier());
                    BoxScopeInstance boxScopeInstance118 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors111119 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens118, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density118 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density118.mo4551roundToPx0680j_4(f1110) - density118.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit118 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource118 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda111111113 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource118, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda111111114 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors111119, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function1111115 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda111111113, composableLambdaRememberComposableLambda111111114, composableLambdaRememberComposableLambda111111112, function9, z1111111112, function1111115, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors111119;
                    composer2 = composerStartRestartGroup;
                    z8 = z1111111112;
                    z7 = z1111111114;
                    modifier2 = modifier111111114;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) != 38347922) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                }
                i12 = i3;
                Modifier modifier111111117 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                }
                if (mutableInteractionSource == null) {
                    composerStartRestartGroup.startReplaceGroup(253288608);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1947832599);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens119 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens119, composerStartRestartGroup, 6);
                boolean z1111111115 = z10;
                ComposableLambda composableLambdaRememberComposableLambda111111115 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                if (function7 == null) {
                    composerStartRestartGroup.startReplaceGroup(254215848);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function7;
                    function9 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(254215849);
                    final NavigationRailItemColors navigationRailItemColors1111110 = navigationRailItemColors3;
                    final boolean z1111111116 = z9;
                    final Function2<? super Composer, ? super Integer, Unit> function1111116 = function7;
                    function8 = function1111116;
                    Function2 function2RememberComposableLambda119 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1111110.m715textColorWaAFU9c$material3(z, z1111111116), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111116, composer3, 0);
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
                    function9 = function2RememberComposableLambda119;
                }
                boolean z1111111117 = z9;
                mutableInteractionSource4 = mutableInteractionSource3;
                Modifier modifier111111118 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier111111117, z, mutableInteractionSource4, (Indication) null, z1111111117, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                float f1111 = NavigationRailItemWidth;
                Modifier modifier111111119 = SizeKt.widthIn-VpY3zN4$default(modifier111111118, f1111, 0.0f, 2, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy119 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap119 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier119 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier111111119);
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
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy119, companion119.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap119, companion119.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion119.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier119, companion119.getSetModifier());
                BoxScopeInstance boxScopeInstance119 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                NavigationRailItemColors navigationRailItemColors1111111 = navigationRailItemColors3;
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens119, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density119 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density119.mo4551roundToPx0680j_4(f1111) - density119.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                Unit unit119 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final MappedInteractionSource mappedInteractionSource119 = (MappedInteractionSource) objRememberedValue;
                if (function8 != null) {
                    composerStartRestartGroup.startReplaceGroup(-1825624334);
                    value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1825528978);
                    value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposableLambda composableLambdaRememberComposableLambda111111116 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                    public final void invoke(Composer composer3, int i14) {
                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource119, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda111111117 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1111111, value), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function0 function1111117 = (Function0) objRememberedValue2;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                NavigationRailItemLayout(composableLambdaRememberComposableLambda111111116, composableLambdaRememberComposableLambda111111117, composableLambdaRememberComposableLambda111111115, function9, z1111111115, function1111117, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationRailItemColors2 = navigationRailItemColors1111111;
                composer2 = composerStartRestartGroup;
                z8 = z1111111115;
                z7 = z1111111117;
                modifier2 = modifier111111117;
                mutableInteractionSource2 = mutableInteractionSource;
                function6 = function8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                z7 = z4;
                composer2 = composerStartRestartGroup;
                z8 = z5;
                function6 = function5;
                navigationRailItemColors2 = navigationRailItemColors;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        function5 = function3;
        i8 = i2 & 64;
        if (i8 != 0) {
            z5 = z3;
            if ((i & 1572864) == 0) {
                c = ' ';
                if (composerStartRestartGroup.changed(z5)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            if ((i & 12582912) != 0) {
                i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i3 & 38347923) != 38347922) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function5 = null;
                        }
                        if (i8 != 0) {
                            z5 = true;
                        }
                        if ((i2 & 128) != 0) {
                            navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -29360129;
                        } else {
                            navigationRailItemColorsColors = navigationRailItemColors;
                        }
                        if (i10 != 0) {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                            mutableInteractionSource = null;
                        } else {
                            navigationRailItemColors3 = navigationRailItemColorsColors;
                            z9 = z4;
                            z10 = z5;
                            function7 = function5;
                        }
                    }
                    i12 = i3;
                    Modifier modifier1111111110 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                    }
                    if (mutableInteractionSource == null) {
                        composerStartRestartGroup.startReplaceGroup(253288608);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1947832599);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    MotionSchemeKeyTokens motionSchemeKeyTokens1110 = MotionSchemeKeyTokens.DefaultEffects;
                    finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens1110, composerStartRestartGroup, 6);
                    boolean z1111111118 = z10;
                    ComposableLambda composableLambdaRememberComposableLambda111111118 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                    if (function7 == null) {
                        composerStartRestartGroup.startReplaceGroup(254215848);
                        composerStartRestartGroup.endReplaceGroup();
                        function8 = function7;
                        function9 = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(254215849);
                        final NavigationRailItemColors navigationRailItemColors1111112 = navigationRailItemColors3;
                        final boolean z1111111119 = z9;
                        final Function2<? super Composer, ? super Integer, Unit> function1111118 = function7;
                        function8 = function1111118;
                        Function2 function2RememberComposableLambda1110 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m3144unboximpl();
                            }

                            public final void invoke(Composer composer3, int i14) {
                                if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                                }
                                ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1111112.m715textColorWaAFU9c$material3(z, z1111111119), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function1111118, composer3, 0);
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
                        function9 = function2RememberComposableLambda1110;
                    }
                    boolean z11111111110 = z9;
                    mutableInteractionSource4 = mutableInteractionSource3;
                    Modifier modifier1111111111 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1111111110, z, mutableInteractionSource4, (Indication) null, z11111111110, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                    float f1112 = NavigationRailItemWidth;
                    Modifier modifier1111111112 = SizeKt.widthIn-VpY3zN4$default(modifier1111111111, f1112, 0.0f, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy1110 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap1110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier1110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1111111112);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy1110, companion1110.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap1110, companion1110.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion1110.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier1110, companion1110.getSetModifier());
                    BoxScopeInstance boxScopeInstance1110 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    NavigationRailItemColors navigationRailItemColors1111113 = navigationRailItemColors3;
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens1110, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    if (z) {
                        f2 = 1.0f;
                    } else {
                        f2 = 0.0f;
                    }
                    stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                    Density density1110 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density1110.mo4551roundToPx0680j_4(f1112) - density1110.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                    Unit unit1110 = Unit.INSTANCE;
                    zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final MappedInteractionSource mappedInteractionSource1110 = (MappedInteractionSource) objRememberedValue;
                    if (function8 != null) {
                        composerStartRestartGroup.startReplaceGroup(-1825624334);
                        value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1825528978);
                        value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    ComposableLambda composableLambdaRememberComposableLambda111111119 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource1110, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1111111110 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1111113, value), composerStartRestartGroup, 54);
                    zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2) {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function0() { // from class: zea
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function1111119 = (Function0) objRememberedValue2;
                    zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: afa
                            public final Object invoke() {
                                return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    NavigationRailItemLayout(composableLambdaRememberComposableLambda111111119, composableLambdaRememberComposableLambda1111111110, composableLambdaRememberComposableLambda111111118, function9, z1111111118, function1111119, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    navigationRailItemColors2 = navigationRailItemColors1111113;
                    composer2 = composerStartRestartGroup;
                    z8 = z1111111118;
                    z7 = z11111111110;
                    modifier2 = modifier1111111110;
                    mutableInteractionSource2 = mutableInteractionSource;
                    function6 = function8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    z7 = z4;
                    composer2 = composerStartRestartGroup;
                    z8 = z5;
                    function6 = function5;
                    navigationRailItemColors2 = navigationRailItemColors;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) != 38347922) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                }
                i12 = i3;
                Modifier modifier1111111113 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                }
                if (mutableInteractionSource == null) {
                    composerStartRestartGroup.startReplaceGroup(253288608);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1947832599);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens1111 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens1111, composerStartRestartGroup, 6);
                boolean z11111111111 = z10;
                ComposableLambda composableLambdaRememberComposableLambda1111111111 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                if (function7 == null) {
                    composerStartRestartGroup.startReplaceGroup(254215848);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function7;
                    function9 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(254215849);
                    final NavigationRailItemColors navigationRailItemColors1111114 = navigationRailItemColors3;
                    final boolean z11111111112 = z9;
                    final Function2<? super Composer, ? super Integer, Unit> function11111110 = function7;
                    function8 = function11111110;
                    Function2 function2RememberComposableLambda1111 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1111114.m715textColorWaAFU9c$material3(z, z11111111112), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11111110, composer3, 0);
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
                    function9 = function2RememberComposableLambda1111;
                }
                boolean z11111111113 = z9;
                mutableInteractionSource4 = mutableInteractionSource3;
                Modifier modifier1111111114 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1111111113, z, mutableInteractionSource4, (Indication) null, z11111111113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                float f1113 = NavigationRailItemWidth;
                Modifier modifier1111111115 = SizeKt.widthIn-VpY3zN4$default(modifier1111111114, f1113, 0.0f, 2, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy1111 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap1111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier1111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1111111115);
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
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy1111, companion1111.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap1111, companion1111.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion1111.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier1111, companion1111.getSetModifier());
                BoxScopeInstance boxScopeInstance1111 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                NavigationRailItemColors navigationRailItemColors1111115 = navigationRailItemColors3;
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens1111, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density1111 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density1111.mo4551roundToPx0680j_4(f1113) - density1111.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                Unit unit1111 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final MappedInteractionSource mappedInteractionSource1111 = (MappedInteractionSource) objRememberedValue;
                if (function8 != null) {
                    composerStartRestartGroup.startReplaceGroup(-1825624334);
                    value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1825528978);
                    value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposableLambda composableLambdaRememberComposableLambda1111111112 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                    public final void invoke(Composer composer3, int i14) {
                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource1111, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda1111111113 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1111115, value), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function0 function11111111 = (Function0) objRememberedValue2;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                NavigationRailItemLayout(composableLambdaRememberComposableLambda1111111112, composableLambdaRememberComposableLambda1111111113, composableLambdaRememberComposableLambda1111111111, function9, z11111111111, function11111111, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationRailItemColors2 = navigationRailItemColors1111115;
                composer2 = composerStartRestartGroup;
                z8 = z11111111111;
                z7 = z11111111113;
                modifier2 = modifier1111111113;
                mutableInteractionSource2 = mutableInteractionSource;
                function6 = function8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                z7 = z4;
                composer2 = composerStartRestartGroup;
                z8 = z5;
                function6 = function5;
                navigationRailItemColors2 = navigationRailItemColors;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 1572864;
        z5 = z3;
        c = ' ';
        if ((i & 12582912) != 0) {
            i3 |= ((i2 & 128) == 0 || !composerStartRestartGroup.changed(navigationRailItemColors)) ? 4194304 : 8388608;
        }
        i10 = i2 & 256;
        if (i10 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i3 & 38347923) != 38347922) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function5 = null;
                    }
                    if (i8 != 0) {
                        z5 = true;
                    }
                    if ((i2 & 128) != 0) {
                        navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -29360129;
                    } else {
                        navigationRailItemColorsColors = navigationRailItemColors;
                    }
                    if (i10 != 0) {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                        mutableInteractionSource = null;
                    } else {
                        navigationRailItemColors3 = navigationRailItemColorsColors;
                        z9 = z4;
                        z10 = z5;
                        function7 = function5;
                    }
                }
                i12 = i3;
                Modifier modifier1111111116 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
                }
                if (mutableInteractionSource == null) {
                    composerStartRestartGroup.startReplaceGroup(253288608);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1947832599);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                MotionSchemeKeyTokens motionSchemeKeyTokens1112 = MotionSchemeKeyTokens.DefaultEffects;
                finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens1112, composerStartRestartGroup, 6);
                boolean z11111111114 = z10;
                ComposableLambda composableLambdaRememberComposableLambda1111111114 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
                if (function7 == null) {
                    composerStartRestartGroup.startReplaceGroup(254215848);
                    composerStartRestartGroup.endReplaceGroup();
                    function8 = function7;
                    function9 = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(254215849);
                    final NavigationRailItemColors navigationRailItemColors1111116 = navigationRailItemColors3;
                    final boolean z11111111115 = z9;
                    final Function2<? super Composer, ? super Integer, Unit> function11111112 = function7;
                    function8 = function11111112;
                    Function2 function2RememberComposableLambda1112 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m3144unboximpl();
                        }

                        public final void invoke(Composer composer3, int i14) {
                            if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                            }
                            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1111116.m715textColorWaAFU9c$material3(z, z11111111115), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11111112, composer3, 0);
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
                    function9 = function2RememberComposableLambda1112;
                }
                boolean z11111111116 = z9;
                mutableInteractionSource4 = mutableInteractionSource3;
                Modifier modifier1111111117 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1111111116, z, mutableInteractionSource4, (Indication) null, z11111111116, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
                float f1114 = NavigationRailItemWidth;
                Modifier modifier1111111118 = SizeKt.widthIn-VpY3zN4$default(modifier1111111117, f1114, 0.0f, 2, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy1112 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap1112 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier1112 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1111111118);
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
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy1112, companion1112.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap1112, companion1112.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion1112.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier1112, companion1112.getSetModifier());
                BoxScopeInstance boxScopeInstance1112 = BoxScopeInstance.INSTANCE;
                if (z) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                NavigationRailItemColors navigationRailItemColors1111117 = navigationRailItemColors3;
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens1112, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                if (z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Density density1112 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density1112.mo4551roundToPx0680j_4(f1114) - density1112.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
                Unit unit1112 = Unit.INSTANCE;
                zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final MappedInteractionSource mappedInteractionSource1112 = (MappedInteractionSource) objRememberedValue;
                if (function8 != null) {
                    composerStartRestartGroup.startReplaceGroup(-1825624334);
                    value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1825528978);
                    value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposableLambda composableLambdaRememberComposableLambda1111111115 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                    public final void invoke(Composer composer3, int i14) {
                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource1112, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda1111111116 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1111117, value), composerStartRestartGroup, 54);
                zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged2) {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function0() { // from class: zea
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function0 function11111113 = (Function0) objRememberedValue2;
                zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function0() { // from class: afa
                        public final Object invoke() {
                            return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                NavigationRailItemLayout(composableLambdaRememberComposableLambda1111111115, composableLambdaRememberComposableLambda1111111116, composableLambdaRememberComposableLambda1111111114, function9, z11111111114, function11111113, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                navigationRailItemColors2 = navigationRailItemColors1111117;
                composer2 = composerStartRestartGroup;
                z8 = z11111111114;
                z7 = z11111111116;
                modifier2 = modifier1111111116;
                mutableInteractionSource2 = mutableInteractionSource;
                function6 = function8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                z7 = z4;
                composer2 = composerStartRestartGroup;
                z8 = z5;
                function6 = function5;
                navigationRailItemColors2 = navigationRailItemColors;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 100663296;
        if ((i3 & 38347923) != 38347922) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i13 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z4 = true;
                }
                if (i6 != 0) {
                    function5 = null;
                }
                if (i8 != 0) {
                    z5 = true;
                }
                if ((i2 & 128) != 0) {
                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i3 &= -29360129;
                } else {
                    navigationRailItemColorsColors = navigationRailItemColors;
                }
                if (i10 != 0) {
                    navigationRailItemColors3 = navigationRailItemColorsColors;
                    z9 = z4;
                    z10 = z5;
                    function7 = function5;
                    mutableInteractionSource = null;
                } else {
                    navigationRailItemColors3 = navigationRailItemColorsColors;
                    z9 = z4;
                    z10 = z5;
                    function7 = function5;
                }
            } else {
                if (i13 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z4 = true;
                }
                if (i6 != 0) {
                    function5 = null;
                }
                if (i8 != 0) {
                    z5 = true;
                }
                if ((i2 & 128) != 0) {
                    navigationRailItemColorsColors = NavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i3 &= -29360129;
                } else {
                    navigationRailItemColorsColors = navigationRailItemColors;
                }
                if (i10 != 0) {
                    navigationRailItemColors3 = navigationRailItemColorsColors;
                    z9 = z4;
                    z10 = z5;
                    function7 = function5;
                    mutableInteractionSource = null;
                } else {
                    navigationRailItemColors3 = navigationRailItemColorsColors;
                    z9 = z4;
                    z10 = z5;
                    function7 = function5;
                }
            }
            i12 = i3;
            Modifier modifier1111111119 = modifier2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1620317701, i12, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
            }
            if (mutableInteractionSource == null) {
                composerStartRestartGroup.startReplaceGroup(253288608);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue4;
            } else {
                composerStartRestartGroup.startReplaceGroup(1947832599);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource3 = mutableInteractionSource;
            }
            MotionSchemeKeyTokens motionSchemeKeyTokens1113 = MotionSchemeKeyTokens.DefaultEffects;
            finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens1113, composerStartRestartGroup, 6);
            boolean z11111111117 = z10;
            ComposableLambda composableLambdaRememberComposableLambda1111111117 = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(navigationRailItemColors3, z, z9, finiteAnimationSpecValue, function7, z10, function4), composerStartRestartGroup, 54);
            if (function7 == null) {
                composerStartRestartGroup.startReplaceGroup(254215848);
                composerStartRestartGroup.endReplaceGroup();
                function8 = function7;
                function9 = null;
            } else {
                composerStartRestartGroup.startReplaceGroup(254215849);
                final NavigationRailItemColors navigationRailItemColors1111118 = navigationRailItemColors3;
                final boolean z11111111118 = z9;
                final Function2<? super Composer, ? super Integer, Unit> function11111114 = function7;
                function8 = function11111114;
                Function2 function2RememberComposableLambda1113 = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                    private static final long invoke$lambda$0(State<Color> state) {
                        return state.getValue().m3144unboximpl();
                    }

                    public final void invoke(Composer composer3, int i14) {
                        if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2056532825, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                        }
                        ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.animateColorAsState-euL9pac(navigationRailItemColors1111118.m715textColorWaAFU9c$material3(z, z11111111118), finiteAnimationSpecValue, (String) null, (Function1) null, composer3, 0, 12)), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), composer3, 6), function11111114, composer3, 0);
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
                function9 = function2RememberComposableLambda1113;
            }
            boolean z11111111119 = z9;
            mutableInteractionSource4 = mutableInteractionSource3;
            Modifier modifier11111111110 = SizeKt.defaultMinSize-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(modifier1111111119, z, mutableInteractionSource4, (Indication) null, z11111111119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), 0.0f, NavigationRailItemHeight, 1, (Object) null);
            float f1115 = NavigationRailItemWidth;
            Modifier modifier11111111111 = SizeKt.widthIn-VpY3zN4$default(modifier11111111110, f1115, 0.0f, 2, (Object) null);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy1113 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap1113 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier1113 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11111111111);
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy1113, companion1113.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap1113, companion1113.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion1113.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier1113, companion1113.getSetModifier());
            BoxScopeInstance boxScopeInstance1113 = BoxScopeInstance.INSTANCE;
            if (z) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            NavigationRailItemColors navigationRailItemColors1111119 = navigationRailItemColors3;
            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, MotionSchemeKt.value(motionSchemeKeyTokens1113, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
            if (z) {
                f2 = 1.0f;
            } else {
                f2 = 0.0f;
            }
            stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
            Density density1113 = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((density1113.mo4551roundToPx0680j_4(f1115) - density1113.mo4551roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m1958getActiveIndicatorWidthD9Ej5fM())) / 2.0f)) << c) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
            Unit unit1113 = Unit.INSTANCE;
            zChanged = composerStartRestartGroup.changed(mutableInteractionSource4) | composerStartRestartGroup.changed(jM2881constructorimpl);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new MappedInteractionSource(mutableInteractionSource4, jM2881constructorimpl, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MappedInteractionSource mappedInteractionSource1113 = (MappedInteractionSource) objRememberedValue;
            if (function8 != null) {
                composerStartRestartGroup.startReplaceGroup(-1825624334);
                value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1825528978);
                value = ShapesKt.getValue(ShapeKeyTokens.CornerFull, composerStartRestartGroup, 6);
                composerStartRestartGroup.endReplaceGroup();
            }
            ComposableLambda composableLambdaRememberComposableLambda1111111118 = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                public final void invoke(Composer composer3, int i14) {
                    if (!composer3.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(455696046, i14, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                    }
                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), value), mappedInteractionSource1113, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            ComposableLambda composableLambdaRememberComposableLambda1111111119 = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, navigationRailItemColors1111119, value), composerStartRestartGroup, 54);
            zChanged2 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2) {
                objRememberedValue2 = new Function0() { // from class: zea
                    public final Object invoke() {
                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new Function0() { // from class: zea
                    public final Object invoke() {
                        return Float.valueOf(NavigationRailKt.e(stateAnimateFloatAsState));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function0 function11111115 = (Function0) objRememberedValue2;
            zChanged3 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged3) {
                objRememberedValue3 = new Function0() { // from class: afa
                    public final Object invoke() {
                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new Function0() { // from class: afa
                    public final Object invoke() {
                        return Float.valueOf(NavigationRailKt.h(stateAnimateFloatAsState2));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            NavigationRailItemLayout(composableLambdaRememberComposableLambda1111111118, composableLambdaRememberComposableLambda1111111119, composableLambdaRememberComposableLambda1111111117, function9, z11111111117, function11111115, (Function0) objRememberedValue3, composerStartRestartGroup, (57344 & (i12 >> 6)) | 438);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            navigationRailItemColors2 = navigationRailItemColors1111119;
            composer2 = composerStartRestartGroup;
            z8 = z11111111117;
            z7 = z11111111119;
            modifier2 = modifier1111111119;
            mutableInteractionSource2 = mutableInteractionSource;
            function6 = function8;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            mutableInteractionSource2 = mutableInteractionSource;
            z7 = z4;
            composer2 = composerStartRestartGroup;
            z8 = z5;
            function6 = function5;
            navigationRailItemColors2 = navigationRailItemColors;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bfa
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.a(z, function0, function2, modifier2, z7, function6, z8, navigationRailItemColors2, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void NavigationRailItemLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final boolean z, final Function0<Float> function0, final Function0<Float> function1, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-759267492);
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
                ComposerKt.traceEventStart(-759267492, i2, -1, "androidx.compose.material3.NavigationRailItemLayout (NavigationRail.kt:557)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierBadgeBounds = BadgeKt.badgeBounds(companion);
            int i3 = 57344 & i2;
            boolean z2 = ((i2 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) | ((3670016 & i2) == 1048576) | (i3 == 16384);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItemLayout$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        Measurable measurable;
                        float fCoerceAtLeast = RangesKt.coerceAtLeast(((Number) function1.invoke()).floatValue(), 0.0f);
                        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        int size = list.size();
                        for (int i4 = 0; i4 < size; i4++) {
                            Measurable measurable2 = list.get(i4);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "icon")) {
                                Placeable placeableMo4605measureBRTryo0 = measurable2.mo4605measureBRTryo0(jM5965copyZbe2FdA$default);
                                int width = placeableMo4605measureBRTryo0.getWidth() + measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(NavigationRailKt.IndicatorHorizontalPadding * 2.0f));
                                int iRoundToInt = MathKt.roundToInt(width * fCoerceAtLeast);
                                int height = placeableMo4605measureBRTryo0.getHeight() + measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl((function5 == null ? NavigationRailKt.IndicatorVerticalPaddingNoLabel : NavigationRailKt.IndicatorVerticalPaddingWithLabel) * 2.0f));
                                List<? extends Measurable> list2 = list;
                                int size2 = list2.size();
                                for (int i5 = 0; i5 < size2; i5++) {
                                    Measurable measurable3 = list.get(i5);
                                    Placeable placeableMo4605measureBRTryo1 = null;
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
                                                    placeableMo4605measureBRTryo1 = measurable5.mo4605measureBRTryo0(jM5965copyZbe2FdA$default);
                                                    break;
                                                }
                                                i7++;
                                            }
                                        }
                                        if (function5 == null) {
                                            return NavigationRailKt.m721placeIconX9ElhV4(measureScope, placeableMo4605measureBRTryo0, placeableMo4605measureBRTryo2, placeableMo4605measureBRTryo3, j);
                                        }
                                        placeableMo4605measureBRTryo1.getClass();
                                        return NavigationRailKt.m722placeLabelAndIconzUg2_y0(measureScope, placeableMo4605measureBRTryo1, placeableMo4605measureBRTryo0, placeableMo4605measureBRTryo2, placeableMo4605measureBRTryo3, j, z, fCoerceAtLeast);
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
                composerStartRestartGroup.startReplaceGroup(773116085);
                Modifier modifierLayoutId2 = LayoutIdKt.layoutId(companion, LabelLayoutIdTag);
                boolean z3 = (i3 == 16384) | ((458752 & i2) == 131072);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: ffa
                        public final Object invoke(Object obj) {
                            return NavigationRailKt.b(z, function0, (GraphicsLayerScope) obj);
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
                composerStartRestartGroup.startReplaceGroup(773387087);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfa
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.i(function2, function3, function4, function5, z, function0, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function3, boolean z3, NavigationRailItemColors navigationRailItemColors, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        NavigationRailItem(z, function0, function2, modifier, z2, function3, z3, navigationRailItemColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(boolean z, Function0 function0, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(z ? 1.0f : ((Number) function0.invoke()).floatValue());
        return Unit.INSTANCE;
    }

    public static Unit c(Placeable placeable, boolean z, float f, Placeable placeable2, int i, float f2, float f3, Placeable placeable3, int i2, float f4, Placeable placeable4, int i3, float f5, int i4, MeasureScope measureScope, Placeable.PlacementScope placementScope) {
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, (i4 - placeable.getWidth()) / 2, MathKt.roundToInt((f4 - measureScope.mo4557toPx0680j_4(IndicatorVerticalPaddingWithLabel)) + f3), 0.0f, 4, null);
        }
        if (z || f != 0.0f) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i, MathKt.roundToInt(f2 + f3), 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i2, MathKt.roundToInt(f4 + f3), 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, i3, MathKt.roundToInt(f5 + f3), 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static NavigationRailOverride d() {
        return DefaultNavigationRailOverride.INSTANCE;
    }

    public static float e(State state) {
        return ((Number) state.getValue()).floatValue();
    }

    public static Unit f(Placeable placeable, Placeable placeable2, int i, int i2, Placeable placeable3, int i3, int i4, int i5, int i6, Placeable.PlacementScope placementScope) {
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, (i5 - placeable.getWidth()) / 2, (i6 - placeable.getHeight()) / 2, 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i, i2, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i3, i4, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit g(Modifier modifier, long j, long j2, Function3 function3, WindowInsets windowInsets, Function3 function4, int i, int i2, Composer composer, int i3) {
        m718NavigationRailqi6gXK8(modifier, j, j2, function3, windowInsets, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final ProvidableCompositionLocal<NavigationRailOverride> getLocalNavigationRailOverride() {
        return LocalNavigationRailOverride;
    }

    public static final float getNavigationRailItemHeight() {
        return NavigationRailItemHeight;
    }

    public static final float getNavigationRailItemVerticalPadding() {
        return NavigationRailItemVerticalPadding;
    }

    public static final float getNavigationRailItemWidth() {
        return NavigationRailItemWidth;
    }

    public static final float getNavigationRailVerticalPadding() {
        return NavigationRailVerticalPadding;
    }

    public static float h(State state) {
        return ((Number) state.getValue()).floatValue();
    }

    public static Unit i(Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z, Function0 function0, Function0 function1, int i, Composer composer, int i2) {
        NavigationRailItemLayout(function2, function3, function4, function5, z, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m721placeIconX9ElhV4(MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, long j) {
        final int iM5992constrainWidthK40F9xA = ConstraintsKt.m5992constrainWidthK40F9xA(j, Math.max(placeable.getWidth(), Math.max(placeable2.getWidth(), placeable3 != null ? placeable3.getWidth() : 0)));
        final int iM5991constrainHeightK40F9xA = ConstraintsKt.m5991constrainHeightK40F9xA(j, measureScope.mo4551roundToPx0680j_4(NavigationRailItemHeight));
        final int width = (iM5992constrainWidthK40F9xA - placeable.getWidth()) / 2;
        final int height = (iM5991constrainHeightK40F9xA - placeable.getHeight()) / 2;
        final int width2 = (iM5992constrainWidthK40F9xA - placeable2.getWidth()) / 2;
        final int height2 = (iM5991constrainHeightK40F9xA - placeable2.getHeight()) / 2;
        return MeasureScope.layout$default(measureScope, iM5992constrainWidthK40F9xA, iM5991constrainHeightK40F9xA, null, new Function1() { // from class: dfa
            public final Object invoke(Object obj) {
                return NavigationRailKt.f(placeable3, placeable, width, height, placeable2, width2, height2, iM5992constrainWidthK40F9xA, iM5991constrainHeightK40F9xA, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeLabelAndIcon-zUg2_y0, reason: not valid java name */
    public static final MeasureResult m722placeLabelAndIconzUg2_y0(final MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final Placeable placeable4, long j, final boolean z, final float f) {
        float height = placeable2.getHeight();
        float f2 = IndicatorVerticalPaddingWithLabel;
        float fMo4557toPx0680j_4 = height + measureScope.mo4557toPx0680j_4(f2);
        float f3 = NavigationRailItemVerticalPadding;
        float fMo4557toPx0680j_5 = fMo4557toPx0680j_4 + measureScope.mo4557toPx0680j_4(f3) + placeable.getHeight();
        final float fCoerceAtLeast = RangesKt.coerceAtLeast((Constraints.m5976getMinHeightimpl(j) - fMo4557toPx0680j_5) / 2.0f, measureScope.mo4557toPx0680j_4(f2));
        float f4 = fMo4557toPx0680j_5 + (fCoerceAtLeast * 2.0f);
        final float height2 = ((z ? fCoerceAtLeast : (f4 - placeable2.getHeight()) / 2.0f) - fCoerceAtLeast) * (1.0f - f);
        final float height3 = placeable2.getHeight() + fCoerceAtLeast + measureScope.mo4557toPx0680j_4(f2) + measureScope.mo4557toPx0680j_4(f3);
        final int iM5992constrainWidthK40F9xA = ConstraintsKt.m5992constrainWidthK40F9xA(j, Math.max(placeable2.getWidth(), Math.max(placeable.getWidth(), placeable4 != null ? placeable4.getWidth() : 0)));
        final int width = (iM5992constrainWidthK40F9xA - placeable.getWidth()) / 2;
        final int width2 = (iM5992constrainWidthK40F9xA - placeable2.getWidth()) / 2;
        final int width3 = (iM5992constrainWidthK40F9xA - placeable3.getWidth()) / 2;
        final float fMo4557toPx0680j_6 = fCoerceAtLeast - measureScope.mo4557toPx0680j_4(f2);
        return MeasureScope.layout$default(measureScope, iM5992constrainWidthK40F9xA, MathKt.roundToInt(f4), null, new Function1() { // from class: yea
            public final Object invoke(Object obj) {
                return NavigationRailKt.c(placeable4, z, f, placeable, width, height3, height2, placeable2, width2, fCoerceAtLeast, placeable3, width3, fMo4557toPx0680j_6, iM5992constrainWidthK40F9xA, measureScope, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
