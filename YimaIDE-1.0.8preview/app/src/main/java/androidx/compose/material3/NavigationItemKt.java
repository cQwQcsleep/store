package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.NavigationItemKt;
import androidx.compose.material3.internal.MappedInteractionSource;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
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
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\u001aµ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001aÕ\u0001\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010)\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0004\b*\u0010+\u001a\u008d\u0001\u0010,\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000b2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0003¢\u0006\u0004\b2\u00103\u001a³\u0001\u00104\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000b2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00105\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010)\u001a\u00020\rH\u0003¢\u0006\u0004\b6\u00107\u001a3\u00108\u001a\u000209*\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020@H\u0002¢\u0006\u0004\bA\u0010B\u001aS\u0010C\u001a\u000209*\u00020:2\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020@2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0002¢\u0006\u0004\bE\u0010F\u001aC\u0010G\u001a\u000209*\u00020:2\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020@2\u0006\u0010\u0011\u001a\u00020\rH\u0002¢\u0006\u0004\bH\u0010I\u001a\u0091\u0001\u0010J\u001a\u000209*\u00020:2\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00105\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010 \u001a\u00020K2\u0006\u0010?\u001a\u00020@2\u0006\u0010%\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010#\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010)\u001a\u00020\rH\u0002¢\u0006\u0004\bL\u0010M\u001a@\u0010N\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00032\u0011\u0010O\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0007H\u0003¢\u0006\u0002\u0010P\u001a\u001b\u0010Q\u001a\b\u0012\u0004\u0012\u0002010R2\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010S\u001a\u001d\u0010T\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020-2\u0006\u0010\n\u001a\u00020\u000bH\u0003¢\u0006\u0002\u0010U\u001a-\u0010V\u001a\u00020\u00012\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000b2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0005H\u0003¢\u0006\u0004\bW\u0010X\"\u000e\u0010Y\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010[\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\\\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010]\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010^\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010_¨\u0006`²\u0006\n\u0010a\u001a\u00020KX\u008a\u008e\u0002²\u0006\n\u0010a\u001a\u00020KX\u008a\u008e\u0002²\u0006\n\u00105\u001a\u000201X\u008a\u0084\u0002²\u0006\n\u0010b\u001a\u00020\tX\u008a\u0084\u0002"}, d2 = {"NavigationItem", "", "selected", "", "onClick", "Lkotlin/Function0;", NavigationItemKt.IconLayoutIdTag, "Landroidx/compose/runtime/Composable;", "labelTextStyle", "Landroidx/compose/ui/text/TextStyle;", "indicatorShape", "Landroidx/compose/ui/graphics/Shape;", "indicatorWidth", "Landroidx/compose/ui/unit/Dp;", "indicatorHorizontalPadding", "indicatorVerticalPadding", "indicatorToLabelVerticalPadding", "startIconToLabelHorizontalPadding", "topIconItemVerticalPadding", "colors", "Landroidx/compose/material3/NavigationItemColors;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", NavigationItemKt.LabelLayoutIdTag, "iconPosition", "Landroidx/compose/material3/NavigationItemIconPosition;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "NavigationItem-8Df7sds", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/graphics/Shape;FFFFFFLandroidx/compose/material3/NavigationItemColors;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "AnimatedNavigationItem", "topIconIndicatorWidth", "topIconLabelTextStyle", "startIconLabelTextStyle", "topIconIndicatorHorizontalPadding", "topIconIndicatorVerticalPadding", "topIconIndicatorToLabelVerticalPadding", "startIconIndicatorHorizontalPadding", "startIconIndicatorVerticalPadding", "noLabelIndicatorPadding", "itemHorizontalPadding", "AnimatedNavigationItem-DQd_Gtc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;FLandroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;FFFFFFFFLandroidx/compose/material3/NavigationItemColors;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "NavigationItemLayout", "Landroidx/compose/foundation/interaction/InteractionSource;", "indicatorColor", "Landroidx/compose/ui/graphics/Color;", "indicatorAnimationProgress", "", "NavigationItemLayout-KmRX-Dg", "(Landroidx/compose/foundation/interaction/InteractionSource;JLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function2;ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;FFFFFLandroidx/compose/runtime/Composer;II)V", "AnimatedNavigationItemLayout", "iconPositionProgress", "AnimatedNavigationItemLayout-he0WsC4", "(Landroidx/compose/foundation/interaction/InteractionSource;JLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;ILkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;FFFFFFFFLandroidx/compose/runtime/Composer;II)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndTopIcon", "labelPlaceable", "placeLabelAndTopIcon-qoqLrGI", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JFFF)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndStartIcon", "placeLabelAndStartIcon-nru01g4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JF)Landroidx/compose/ui/layout/MeasureResult;", "placeAnimatedLabelAndIcon", "", "placeAnimatedLabelAndIcon-2QYhCQ8", "(Landroidx/compose/ui/layout/MeasureScope;ILkotlin/jvm/functions/Function0;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;IJFFFFFFF)Landroidx/compose/ui/layout/MeasureResult;", "StyledLabel", "content", "(ZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/material3/NavigationItemColors;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "animateIndicatorProgressAsState", "Landroidx/compose/runtime/State;", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "IndicatorRipple", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "Indicator", "Indicator-3J-VO9M", "(JLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "IndicatorRippleLayoutIdTag", "", "IndicatorLayoutIdTag", "IconLayoutIdTag", "LabelLayoutIdTag", "IndicatorVerticalOffset", "F", "material3", "itemWidth", "textStyle"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationItemKt {
    private static final String IconLayoutIdTag = "icon";
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalOffset = Dp.m6022constructorimpl(12.0f);
    private static final String LabelLayoutIdTag = "label";

    /* JADX INFO: renamed from: AnimatedNavigationItem-DQd_Gtc, reason: not valid java name */
    public static final void m691AnimatedNavigationItemDQd_Gtc(final boolean z, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Shape shape, final float f, final TextStyle textStyle, final TextStyle textStyle2, final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8, final float f9, final NavigationItemColors navigationItemColors, final Modifier modifier, final boolean z2, final Function2<? super Composer, ? super Integer, Unit> function3, final int i, MutableInteractionSource mutableInteractionSource, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        int i6;
        int i7;
        MutableInteractionSource mutableInteractionSource2;
        Composer composer2;
        final State<Float> state;
        boolean z3;
        boolean z4;
        Function2 function4;
        MutableInteractionSource mutableInteractionSource3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1114086313);
        if ((i2 & 6) == 0) {
            i5 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        if ((i2 & 48) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i5 |= composerStartRestartGroup.changed(shape) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i5 |= composerStartRestartGroup.changed(f) ? 16384 : 8192;
        }
        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i5 |= composerStartRestartGroup.changed(textStyle) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i5 |= composerStartRestartGroup.changed(textStyle2) ? 1048576 : 524288;
        }
        if ((i2 & 12582912) == 0) {
            i5 |= composerStartRestartGroup.changed(f2) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            i5 |= composerStartRestartGroup.changed(f3) ? 67108864 : 33554432;
        }
        if ((i2 & 805306368) == 0) {
            i5 |= composerStartRestartGroup.changed(f4) ? 536870912 : 268435456;
        }
        if ((i3 & 6) == 0) {
            i6 = i3 | (composerStartRestartGroup.changed(f5) ? 4 : 2);
        } else {
            i6 = i3;
        }
        if ((i3 & 48) == 0) {
            i6 |= composerStartRestartGroup.changed(f6) ? 32 : 16;
        }
        if ((i3 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i6 |= composerStartRestartGroup.changed(f7) ? 256 : 128;
        }
        if ((i3 & 3072) == 0) {
            i6 |= composerStartRestartGroup.changed(f8) ? 2048 : 1024;
        }
        if ((i3 & 24576) == 0) {
            i6 |= composerStartRestartGroup.changed(f9) ? 16384 : 8192;
        }
        if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i6 |= composerStartRestartGroup.changed(navigationItemColors) ? 131072 : 65536;
        }
        if ((i3 & 1572864) == 0) {
            i6 |= composerStartRestartGroup.changed(modifier) ? 1048576 : 524288;
        }
        if ((i3 & 12582912) == 0) {
            i6 |= composerStartRestartGroup.changed(z2) ? 8388608 : 4194304;
        }
        if ((i3 & 100663296) == 0) {
            i6 |= composerStartRestartGroup.changedInstance(function3) ? 67108864 : 33554432;
        }
        if ((i3 & 805306368) == 0) {
            i6 |= composerStartRestartGroup.changed(i) ? 536870912 : 268435456;
        }
        int i8 = i6;
        if ((i4 & 6) == 0) {
            i7 = i4 | (composerStartRestartGroup.changed(mutableInteractionSource) ? 4 : 2);
        } else {
            i7 = i4;
        }
        if (composerStartRestartGroup.shouldExecute(((i5 & 306783379) == 306783378 && (i8 & 306783379) == 306783378 && (i7 & 3) == 2) ? false : true, i5 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1114086313, i5, i8, "androidx.compose.material3.AnimatedNavigationItem (NavigationItem.kt:343)");
            }
            final long jM680iconColorWaAFU9c = navigationItemColors.m680iconColorWaAFU9c(z, z2);
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(851124593, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$AnimatedNavigationItem$styledIcon$1
                public final void invoke(Composer composer3, int i9) {
                    if (!composer3.shouldExecute((i9 & 3) != 2, i9 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(851124593, i9, -1, "androidx.compose.material3.AnimatedNavigationItem.<anonymous> (NavigationItem.kt:346)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM680iconColorWaAFU9c)), function2, composer3, ProvidedValue.$stable);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableIntState mutableIntState = (MutableIntState) objRememberedValue;
            int i9 = i5;
            mutableInteractionSource2 = mutableInteractionSource;
            Modifier modifier2 = SizeKt.defaultMinSize-VpY3zN4(SelectableKt.selectable-O2vRcR0(modifier, z, mutableInteractionSource, (Indication) null, z2, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), ((Dp) composerStartRestartGroup.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl(), ((Dp) composerStartRestartGroup.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl());
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: iea
                    public final Object invoke(Object obj) {
                        return NavigationItemKt.n(mutableIntState, (IntSize) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierOnSizeChanged = OnRemeasuredModifierKt.onSizeChanged(modifier2, (Function1) objRememberedValue2);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged);
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            final boolean zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(i, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
            State<Float> stateAnimateIndicatorProgressAsState = animateIndicatorProgressAsState(z, composerStartRestartGroup, i9 & 14);
            final State stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(zM685equalsimpl0 ? 0.0f : 1.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: jea
                    public final Object invoke() {
                        return NavigationItemKt.p(zM685equalsimpl0, textStyle, textStyle2, stateAnimateFloatAsState);
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final State state2 = (State) objRememberedValue3;
            if (function3 != null) {
                composerStartRestartGroup.startReplaceGroup(680212713);
                state = stateAnimateIndicatorProgressAsState;
                z4 = false;
                composer2 = composerStartRestartGroup;
                z3 = true;
                Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(22475789, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$AnimatedNavigationItem$2$styledLabel$1
                    public final void invoke(Composer composer3, int i10) {
                        if (!composer3.shouldExecute((i10 & 3) != 2, i10 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(22475789, i10, -1, "androidx.compose.material3.AnimatedNavigationItem.<anonymous>.<anonymous> (NavigationItem.kt:387)");
                        }
                        NavigationItemKt.StyledLabel(z, NavigationItemKt.AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$19(state2), navigationItemColors, z2, function3, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composer2, 54);
                composer2.endReplaceGroup();
                function4 = function2RememberComposableLambda;
            } else {
                state = stateAnimateIndicatorProgressAsState;
                z3 = true;
                z4 = false;
                composer2 = composerStartRestartGroup;
                composer2.startReplaceGroup(680534989);
                composer2.endReplaceGroup();
                function4 = null;
            }
            if (zM685equalsimpl0) {
                composer2.startReplaceGroup(680694081);
                Density density = (Density) composer2.consume(CompositionLocalsKt.getLocalDensity());
                long jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density.mo4551roundToPx0680j_4(f)) / 2.0f)) << 32) | (((long) Float.floatToRawIntBits(density.mo4557toPx0680j_4(IndicatorVerticalOffset))) & 4294967295L));
                Unit unit = Unit.INSTANCE;
                if ((i7 & 14) == 4) {
                    z4 = z3;
                }
                boolean zChanged = z4 | composer2.changed(jM2881constructorimpl);
                Object objRememberedValue4 = composer2.rememberedValue();
                if (zChanged || objRememberedValue4 == companion.getEmpty()) {
                    objRememberedValue4 = new MappedInteractionSource(mutableInteractionSource2, jM2881constructorimpl, null);
                    composer2.updateRememberedValue(objRememberedValue4);
                }
                composer2.endReplaceGroup();
                mutableInteractionSource3 = (MappedInteractionSource) objRememberedValue4;
            } else {
                mutableInteractionSource3 = null;
                composer2.startReplaceGroup(681416753);
                composer2.endReplaceGroup();
            }
            MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource3 != null ? mutableInteractionSource3 : mutableInteractionSource2;
            long selectedIndicatorColor = navigationItemColors.getSelectedIndicatorColor();
            boolean zChanged2 = composer2.changed(state);
            Object objRememberedValue5 = composer2.rememberedValue();
            if (zChanged2 || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: kea
                    public final Object invoke() {
                        return Float.valueOf(NavigationItemKt.k(state));
                    }
                };
                composer2.updateRememberedValue(objRememberedValue5);
            }
            Function0 function1 = (Function0) objRememberedValue5;
            boolean zChanged3 = composer2.changed(stateAnimateFloatAsState);
            Object objRememberedValue6 = composer2.rememberedValue();
            if (zChanged3 || objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = new Function0() { // from class: lea
                    public final Object invoke() {
                        return Float.valueOf(NavigationItemKt.e(stateAnimateFloatAsState));
                    }
                };
                composer2.updateRememberedValue(objRememberedValue6);
            }
            Function0 function5 = (Function0) objRememberedValue6;
            int i10 = i9 << 3;
            int i11 = i8 << 3;
            m692AnimatedNavigationItemLayouthe0WsC4(mutableInteractionSource4, selectedIndicatorColor, shape, function1, composableLambdaRememberComposableLambda, i, function5, function4, f2, f3, f4, f5, f6, f7, f8, f9, composer2, ((i9 >> 3) & 896) | 24576 | ((i8 >> 12) & 458752) | (234881024 & i10) | (i10 & 1879048192), ((i9 >> 27) & 14) | (i11 & 112) | (i11 & 896) | (i11 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11) | (458752 & i11));
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: mea
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.q(z, function0, function2, shape, f, textStyle, textStyle2, f2, f3, f4, f5, f6, f7, f8, f9, navigationItemColors, modifier, z2, function3, i, mutableInteractionSource5, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: AnimatedNavigationItemLayout-he0WsC4, reason: not valid java name */
    private static final void m692AnimatedNavigationItemLayouthe0WsC4(final InteractionSource interactionSource, final long j, final Shape shape, final Function0<Float> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final int i, final Function0<Float> function1, final Function2<? super Composer, ? super Integer, Unit> function3, final float f, final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8, Composer composer, final int i2, final int i3) {
        int i4;
        Function0<Float> function4;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(94433406);
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(interactionSource) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= composerStartRestartGroup.changed(shape) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            function4 = function0;
            i4 |= composerStartRestartGroup.changedInstance(function4) ? 2048 : 1024;
        } else {
            function4 = function0;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function1) ? 1048576 : 524288;
        }
        if ((i2 & 12582912) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function3) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            i4 |= composerStartRestartGroup.changed(f) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i2 & 805306368) == 0) {
            i4 |= composerStartRestartGroup.changed(f2) ? 536870912 : 268435456;
        }
        if ((i3 & 6) == 0) {
            i5 = i3 | (composerStartRestartGroup.changed(f3) ? 4 : 2);
        } else {
            i5 = i3;
        }
        if ((i3 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(f4) ? 32 : 16;
        }
        if ((i3 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i5 |= composerStartRestartGroup.changed(f5) ? 256 : 128;
        }
        if ((i3 & 3072) == 0) {
            i5 |= composerStartRestartGroup.changed(f6) ? 2048 : 1024;
        }
        if ((i3 & 24576) == 0) {
            i5 |= composerStartRestartGroup.changed(f7) ? 16384 : 8192;
        }
        if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i5 |= composerStartRestartGroup.changed(f8) ? 131072 : 65536;
        }
        int i6 = i5;
        if (composerStartRestartGroup.shouldExecute(((i4 & 306783379) == 306783378 && (74899 & i6) == 74898) ? false : true, i4 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(94433406, i4, i6, "androidx.compose.material3.AnimatedNavigationItemLayout (NavigationItem.kt:508)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierBadgeBounds = BadgeKt.badgeBounds(companion);
            Object animatedMeasurePolicy = function3 != null ? new AnimatedMeasurePolicy(i, function1, function4, f, f2, f3, f4, f5, f7, f8, null) : new TopIconOrIconOnlyMeasurePolicy(false, function0, f6, f6, Dp.m6022constructorimpl(0.0f), Dp.m6022constructorimpl(0.0f), null);
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
            Updater.m2396setimpl(composerM2388constructorimpl, animatedMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            int i7 = i4 >> 3;
            IndicatorRipple(interactionSource, shape, composerStartRestartGroup, (i4 & 14) | (i7 & 112));
            int i8 = i4;
            m693Indicator3JVO9M(j, shape, function0, composerStartRestartGroup, i7 & 1022);
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
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i8 >> 12) & 14));
            composerStartRestartGroup.endNode();
            if (function3 != null) {
                composerStartRestartGroup.startReplaceGroup(-987944825);
                Modifier modifierLayoutId2 = LayoutIdKt.layoutId(companion, LabelLayoutIdTag);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId2);
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
                function3.invoke(composerStartRestartGroup, Integer.valueOf((i8 >> 21) & 14));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-987864101);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tea
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.r(interactionSource, j, shape, function0, function2, i, function1, function3, f, f2, f3, f4, f5, f6, f7, f8, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$16(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextStyle AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$19(State<TextStyle> state) {
        return state.getValue();
    }

    /* JADX INFO: renamed from: Indicator-3J-VO9M, reason: not valid java name */
    private static final void m693Indicator3JVO9M(final long j, final Shape shape, final Function0<Float> function0, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-273382589);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(shape) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-273382589, i2, -1, "androidx.compose.material3.Indicator (NavigationItem.kt:1114)");
            }
            Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, IndicatorLayoutIdTag);
            boolean z = (i2 & 896) == 256;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: rea
                    public final Object invoke(Object obj) {
                        return NavigationItemKt.d(function0, (GraphicsLayerScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            BoxKt.Box(BackgroundKt.background-bw27NRU(GraphicsLayerModifierKt.graphicsLayer(modifierLayoutId, (Function1) objRememberedValue), j, shape), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: sea
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.l(j, shape, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void IndicatorRipple(final InteractionSource interactionSource, final Shape shape, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-629069867);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(interactionSource) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(shape) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-629069867, i2, -1, "androidx.compose.material3.IndicatorRipple (NavigationItem.kt:1101)");
            }
            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, IndicatorRippleLayoutIdTag), shape), interactionSource, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vea
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.f(interactionSource, shape, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: NavigationItem-8Df7sds, reason: not valid java name */
    public static final void m694NavigationItem8Df7sds(boolean z, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle textStyle, final Shape shape, final float f, final float f2, final float f3, final float f4, final float f5, final float f6, final NavigationItemColors navigationItemColors, final Modifier modifier, final boolean z2, final Function2<? super Composer, ? super Integer, Unit> function3, final int i, MutableInteractionSource mutableInteractionSource, Composer composer, final int i2, final int i3) {
        int i4;
        Function0<Unit> function1;
        int i5;
        MutableInteractionSource mutableInteractionSource2;
        Composer composer2;
        int i6;
        Function2 function4;
        MutableInteractionSource mutableInteractionSource3;
        final boolean z3 = z;
        Composer composerStartRestartGroup = composer.startRestartGroup(2075155418);
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(z3) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            function1 = function0;
            i4 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changed(textStyle) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changed(shape) ? 16384 : 8192;
        }
        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i4 |= composerStartRestartGroup.changed(f) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i4 |= composerStartRestartGroup.changed(f2) ? 1048576 : 524288;
        }
        if ((i2 & 12582912) == 0) {
            i4 |= composerStartRestartGroup.changed(f3) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            i4 |= composerStartRestartGroup.changed(f4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i2 & 805306368) == 0) {
            i4 |= composerStartRestartGroup.changed(f5) ? 536870912 : 268435456;
        }
        if ((i3 & 6) == 0) {
            i5 = i3 | (composerStartRestartGroup.changed(f6) ? 4 : 2);
        } else {
            i5 = i3;
        }
        if ((i3 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(navigationItemColors) ? 32 : 16;
        }
        if ((i3 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i5 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i3 & 3072) == 0) {
            i5 |= composerStartRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if ((i3 & 24576) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function3) ? 16384 : 8192;
        }
        if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i5 |= composerStartRestartGroup.changed(i) ? 131072 : 65536;
        }
        if ((i3 & 1572864) == 0) {
            i5 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 1048576 : 524288;
        }
        int i7 = i5;
        if (composerStartRestartGroup.shouldExecute(((i4 & 306783379) == 306783378 && (599187 & i7) == 599186) ? false : true, i4 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2075155418, i4, i7, "androidx.compose.material3.NavigationItem (NavigationItem.kt:245)");
            }
            final long jM680iconColorWaAFU9c = navigationItemColors.m680iconColorWaAFU9c(z3, z2);
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1119868672, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$NavigationItem$styledIcon$1
                public final void invoke(Composer composer3, int i8) {
                    if (!composer3.shouldExecute((i8 & 3) != 2, i8 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1119868672, i8, -1, "androidx.compose.material3.NavigationItem.<anonymous> (NavigationItem.kt:248)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM680iconColorWaAFU9c)), function2, composer3, ProvidedValue.$stable);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            if (function3 == null) {
                composerStartRestartGroup.startReplaceGroup(-803302356);
                composerStartRestartGroup.endReplaceGroup();
                function4 = null;
                i6 = 1048576;
            } else {
                composerStartRestartGroup.startReplaceGroup(-803266737);
                i6 = 1048576;
                Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1062206119, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$NavigationItem$styledLabel$1
                    public final void invoke(Composer composer3, int i8) {
                        if (!composer3.shouldExecute((i8 & 3) != 2, i8 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1062206119, i8, -1, "androidx.compose.material3.NavigationItem.<anonymous> (NavigationItem.kt:254)");
                        }
                        NavigationItemKt.StyledLabel(z3, textStyle, navigationItemColors, z2, function3, composer3, 0);
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
                function4 = function2RememberComposableLambda;
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableIntState mutableIntState = (MutableIntState) objRememberedValue;
            z3 = z;
            mutableInteractionSource2 = mutableInteractionSource;
            Modifier modifier2 = SizeKt.defaultMinSize-VpY3zN4(SelectableKt.selectable-O2vRcR0(modifier, z3, mutableInteractionSource2, (Indication) null, z2, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), ((Dp) composerStartRestartGroup.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl(), ((Dp) composerStartRestartGroup.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl());
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: fea
                    public final Object invoke(Object obj) {
                        return NavigationItemKt.i(mutableIntState, (IntSize) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierOnSizeChanged = OnRemeasuredModifierKt.onSizeChanged(modifier2, (Function1) objRememberedValue2);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), true);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged);
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            final State<Float> stateAnimateIndicatorProgressAsState = animateIndicatorProgressAsState(z3, composerStartRestartGroup, i4 & 14);
            if (NavigationItemIconPosition.m685equalsimpl0(i, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg())) {
                composerStartRestartGroup.startReplaceGroup(484755993);
                Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                long jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(density.mo4557toPx0680j_4(IndicatorVerticalOffset))) & 4294967295L) | (((long) Float.floatToRawIntBits((mutableIntState.getIntValue() - density.mo4551roundToPx0680j_4(f)) / 2.0f)) << 32));
                Unit unit = Unit.INSTANCE;
                boolean zChanged = ((i7 & 3670016) == i6) | composerStartRestartGroup.changed(jM2881constructorimpl);
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                    objRememberedValue3 = new MappedInteractionSource(mutableInteractionSource2, jM2881constructorimpl, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                mutableInteractionSource3 = (MappedInteractionSource) objRememberedValue3;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(485471938);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource3 = null;
            }
            if (mutableInteractionSource3 == null) {
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            long selectedIndicatorColor = navigationItemColors.getSelectedIndicatorColor();
            boolean zChanged2 = composerStartRestartGroup.changed(stateAnimateIndicatorProgressAsState);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: pea
                    public final Object invoke() {
                        return Float.valueOf(NavigationItemKt.m(stateAnimateIndicatorProgressAsState));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            int i8 = i4 << 3;
            composer2 = composerStartRestartGroup;
            m695NavigationItemLayoutKmRXDg(mutableInteractionSource3, selectedIndicatorColor, shape, composableLambdaRememberComposableLambda, i, function4, (Function0) objRememberedValue4, f2, f3, f4, f5, f6, composer2, ((i4 >> 6) & 896) | 3072 | ((i7 >> 3) & 57344) | (29360128 & i8) | (234881024 & i8) | (i8 & 1879048192), ((i4 >> 27) & 14) | ((i7 << 3) & 112));
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qea
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.s(z3, function0, function2, textStyle, shape, f, f2, f3, f4, f5, f6, navigationItemColors, modifier, z2, function3, i, mutableInteractionSource4, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: NavigationItemLayout-KmRX-Dg, reason: not valid java name */
    private static final void m695NavigationItemLayoutKmRXDg(final InteractionSource interactionSource, final long j, final Shape shape, final Function2<? super Composer, ? super Integer, Unit> function2, final int i, final Function2<? super Composer, ? super Integer, Unit> function3, final Function0<Float> function0, final float f, final float f2, final float f3, final float f4, final float f5, Composer composer, final int i2, final int i3) {
        int i4;
        Function0<Float> function1;
        int i5;
        Object topIconOrIconOnlyMeasurePolicy;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1473868071);
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(interactionSource) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= composerStartRestartGroup.changed(shape) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function3) ? 131072 : 65536;
        }
        if ((1572864 & i2) == 0) {
            function1 = function0;
            i4 |= composerStartRestartGroup.changedInstance(function1) ? 1048576 : 524288;
        } else {
            function1 = function0;
        }
        if ((12582912 & i2) == 0) {
            i4 |= composerStartRestartGroup.changed(f) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            i4 |= composerStartRestartGroup.changed(f2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i2 & 805306368) == 0) {
            i4 |= composerStartRestartGroup.changed(f3) ? 536870912 : 268435456;
        }
        if ((i3 & 6) == 0) {
            i5 = i3 | (composerStartRestartGroup.changed(f4) ? 4 : 2);
        } else {
            i5 = i3;
        }
        if ((i3 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(f5) ? 32 : 16;
        }
        int i6 = i5;
        if (composerStartRestartGroup.shouldExecute(((i4 & 306783379) == 306783378 && (i6 & 19) == 18) ? false : true, i4 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1473868071, i4, i6, "androidx.compose.material3.NavigationItemLayout (NavigationItem.kt:453)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierBadgeBounds = BadgeKt.badgeBounds(companion);
            if (function3 == null || NavigationItemIconPosition.m685equalsimpl0(i, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg())) {
                topIconOrIconOnlyMeasurePolicy = new TopIconOrIconOnlyMeasurePolicy(function3 != null, function0, f, f2, f3, f5, null);
            } else {
                topIconOrIconOnlyMeasurePolicy = new StartIconMeasurePolicy(function1, f, f2, f4, null);
            }
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
            Updater.m2396setimpl(composerM2388constructorimpl, topIconOrIconOnlyMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            int i7 = i4 >> 3;
            IndicatorRipple(interactionSource, shape, composerStartRestartGroup, (i4 & 14) | (i7 & 112));
            int i8 = i4;
            m693Indicator3JVO9M(j, shape, function0, composerStartRestartGroup, (i7 & 126) | ((i4 >> 12) & 896));
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
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i8 >> 9) & 14));
            composerStartRestartGroup.endNode();
            if (function3 != null) {
                composerStartRestartGroup.startReplaceGroup(-2087200706);
                Modifier modifierLayoutId2 = LayoutIdKt.layoutId(companion, LabelLayoutIdTag);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId2);
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
                function3.invoke(composerStartRestartGroup, Integer.valueOf((i8 >> 15) & 14));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-2087119982);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: wea
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.b(interactionSource, j, shape, function2, i, function3, function0, f, f2, f3, f4, f5, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void StyledLabel(final boolean z, TextStyle textStyle, final NavigationItemColors navigationItemColors, final boolean z2, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        TextStyle textStyle2;
        final Function2<? super Composer, ? super Integer, Unit> function3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2136267443);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(textStyle) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(navigationItemColors) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2136267443, i2, -1, "androidx.compose.material3.StyledLabel (NavigationItem.kt:1083)");
            }
            textStyle2 = textStyle;
            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(navigationItemColors.m681textColorWaAFU9c(z, z2), textStyle2, function2, composerStartRestartGroup, (i2 & 112) | ((i2 >> 6) & 896));
            function3 = function2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            textStyle2 = textStyle;
            function3 = function2;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final TextStyle textStyle3 = textStyle2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oea
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.o(z, textStyle3, navigationItemColors, z2, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(float f, GraphicsLayerScope graphicsLayerScope) {
        float f2 = f - 0.5f;
        graphicsLayerScope.setAlpha(4.0f * f2 * f2);
        return Unit.INSTANCE;
    }

    private static final State<Float> animateIndicatorProgressAsState(boolean z, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1105658511, i, -1, "androidx.compose.material3.animateIndicatorProgressAsState (NavigationItem.kt:1094)");
        }
        State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, composer, 6), 0.0f, (String) null, (Function1) null, composer, 0, 28);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateAnimateFloatAsState;
    }

    public static Unit b(InteractionSource interactionSource, long j, Shape shape, Function2 function2, int i, Function2 function3, Function0 function0, float f, float f2, float f3, float f4, float f5, int i2, int i3, Composer composer, int i4) {
        m695NavigationItemLayoutKmRXDg(interactionSource, j, shape, function2, i, function3, function0, f, f2, f3, f4, f5, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    public static Unit c(Placeable placeable, int i, int i2, Placeable placeable2, int i3, int i4, Placeable placeable3, int i5, int i6, Placeable placeable4, int i7, int i8, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i, i2, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i3, i4, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i5, i6, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, i7, i8, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit d(Function0 function0, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(((Number) function0.invoke()).floatValue());
        return Unit.INSTANCE;
    }

    public static float e(State state) {
        return RangesKt.coerceAtLeast(AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$16(state), 0.0f);
    }

    public static Unit f(InteractionSource interactionSource, Shape shape, int i, Composer composer, int i2) {
        IndicatorRipple(interactionSource, shape, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit g(Placeable placeable, int i, int i2, Placeable placeable2, int i3, int i4, Placeable placeable3, int i5, int i6, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i, i2, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i3, i4, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i5, i6, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit h(Placeable placeable, int i, Placeable placeable2, int i2, int i3, Placeable placeable3, Object obj, int i4, Placeable placeable4, int i5, final float f, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, placeable, i, 0, 0.0f, (Function1) null, 12, (Object) null);
        Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, placeable2, i2, i3, 0.0f, (Function1) null, 12, (Object) null);
        Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, placeable3, ((Number) obj).intValue(), i4, 0.0f, new Function1() { // from class: uea
            public final Object invoke(Object obj2) {
                return NavigationItemKt.a(f, (GraphicsLayerScope) obj2);
            }
        }, 4, (Object) null);
        Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, placeable4, i5, 0, 0.0f, (Function1) null, 12, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit i(MutableIntState mutableIntState, IntSize intSize) {
        mutableIntState.setIntValue((int) (intSize.m6197unboximpl() >> 32));
        return Unit.INSTANCE;
    }

    public static Unit j(Placeable placeable, int i, int i2, Placeable placeable2, int i3, int i4, Placeable placeable3, int i5, int i6, Placeable placeable4, int i7, int i8, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i, i2, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i3, i4, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i5, i6, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, i7, i8, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static float k(State state) {
        return RangesKt.coerceAtLeast(((Number) state.getValue()).floatValue(), 0.0f);
    }

    public static Unit l(long j, Shape shape, Function0 function0, int i, Composer composer, int i2) {
        m693Indicator3JVO9M(j, shape, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static float m(State state) {
        return RangesKt.coerceAtLeast(((Number) state.getValue()).floatValue(), 0.0f);
    }

    public static Unit n(MutableIntState mutableIntState, IntSize intSize) {
        mutableIntState.setIntValue((int) (intSize.m6197unboximpl() >> 32));
        return Unit.INSTANCE;
    }

    public static Unit o(boolean z, TextStyle textStyle, NavigationItemColors navigationItemColors, boolean z2, Function2 function2, int i, Composer composer, int i2) {
        StyledLabel(z, textStyle, navigationItemColors, z2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static TextStyle p(boolean z, TextStyle textStyle, TextStyle textStyle2, State state) {
        return (!z || AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$16(state) >= 0.5f) ? textStyle2 : textStyle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeAnimatedLabelAndIcon-2QYhCQ8, reason: not valid java name */
    public static final MeasureResult m700placeAnimatedLabelAndIcon2QYhCQ8(MeasureScope measureScope, int i, Function0<Float> function0, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final Placeable placeable4, int i2, long j, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        final float fFloatValue = ((Number) function0.invoke()).floatValue();
        boolean zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(i, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
        float f8 = f7 * 2.0f;
        int iM5992constrainWidthK40F9xA = ConstraintsKt.m5992constrainWidthK40F9xA(j, Math.max(placeable.getWidth(), i2 + measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(f8))));
        float fM5992constrainWidthK40F9xA = iM5992constrainWidthK40F9xA + ((ConstraintsKt.m5992constrainWidthK40F9xA(j, placeable3.getWidth() + measureScope.mo4551roundToPx0680j_4(f7)) - iM5992constrainWidthK40F9xA) * fFloatValue);
        int iLerp = MathHelpersKt.lerp(ConstraintsKt.m5991constrainHeightK40F9xA(j, MathKt.roundToInt(placeable3.getHeight() + measureScope.mo4557toPx0680j_4(f) + placeable.getHeight())), ConstraintsKt.m5991constrainHeightK40F9xA(j, placeable3.getHeight()), fFloatValue);
        final int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(f7);
        int iLerp2 = (fFloatValue == 0.0f || fFloatValue == 1.0f) ? MathHelpersKt.lerp(MathKt.roundToInt((fM5992constrainWidthK40F9xA - placeable4.getWidth()) / 2.0f), MathKt.roundToInt(((iMo4551roundToPx0680j_4 + fM5992constrainWidthK40F9xA) - placeable4.getWidth()) / 2.0f), fFloatValue) : measureScope.mo4551roundToPx0680j_4(f7);
        int iMo4551roundToPx0680j_5 = measureScope.mo4551roundToPx0680j_4(f3) + iMo4551roundToPx0680j_4;
        int iMo4551roundToPx0680j_6 = measureScope.mo4551roundToPx0680j_4(f4) + iMo4551roundToPx0680j_4;
        int iMo4551roundToPx0680j_7 = measureScope.mo4551roundToPx0680j_4(f2);
        int iMo4551roundToPx0680j_8 = measureScope.mo4551roundToPx0680j_4(f5);
        final int iLerp3 = MathHelpersKt.lerp(iMo4551roundToPx0680j_5, iMo4551roundToPx0680j_6, fFloatValue);
        final int iLerp4 = MathHelpersKt.lerp(iMo4551roundToPx0680j_7, iMo4551roundToPx0680j_8, fFloatValue);
        int width = ((placeable2.getWidth() + measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(Dp.m6022constructorimpl(f3 * 2.0f) + Dp.m6022constructorimpl(f8)))) - placeable.getWidth()) / 2;
        int height = placeable2.getHeight() + iLerp4 + measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(f + f));
        float width2 = ((placeable2.getWidth() + iLerp3) + measureScope.mo4551roundToPx0680j_4(f6)) - ((!zM685equalsimpl0 || fFloatValue <= 0.0f) ? measureScope.mo4551roundToPx0680j_4(f7) * (1.0f - fFloatValue) : 0.0f);
        int height2 = (iLerp - placeable.getHeight()) / 2;
        final Object objValueOf = fFloatValue < 0.5f ? Integer.valueOf(width) : Float.valueOf(width2 * fFloatValue);
        final int i3 = fFloatValue < 0.5f ? height : height2;
        final int i4 = iLerp2;
        return MeasureScope.layout$default(measureScope, MathKt.roundToInt(fM5992constrainWidthK40F9xA), iLerp, null, new Function1() { // from class: xea
            public final Object invoke(Object obj) {
                return NavigationItemKt.h(placeable4, i4, placeable2, iLerp3, iLerp4, placeable, objValueOf, i3, placeable3, iMo4551roundToPx0680j_4, fFloatValue, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m701placeIconX9ElhV4(MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, long j) {
        int iM5992constrainWidthK40F9xA = ConstraintsKt.m5992constrainWidthK40F9xA(j, placeable2.getWidth());
        int iM5991constrainHeightK40F9xA = ConstraintsKt.m5991constrainHeightK40F9xA(j, placeable2.getHeight());
        final int width = (iM5992constrainWidthK40F9xA - placeable3.getWidth()) / 2;
        final int height = (iM5991constrainHeightK40F9xA - placeable3.getHeight()) / 2;
        final int width2 = (iM5992constrainWidthK40F9xA - placeable.getWidth()) / 2;
        final int height2 = (iM5991constrainHeightK40F9xA - placeable.getHeight()) / 2;
        final int width3 = (iM5992constrainWidthK40F9xA - placeable2.getWidth()) / 2;
        final int height3 = (iM5991constrainHeightK40F9xA - placeable2.getHeight()) / 2;
        return MeasureScope.layout$default(measureScope, iM5992constrainWidthK40F9xA, iM5991constrainHeightK40F9xA, null, new Function1() { // from class: gea
            public final Object invoke(Object obj) {
                return NavigationItemKt.g(placeable3, width, height, placeable, width2, height2, placeable2, width3, height3, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeLabelAndStartIcon-nru01g4, reason: not valid java name */
    public static final MeasureResult m702placeLabelAndStartIconnru01g4(MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final Placeable placeable4, long j, float f) {
        int iM5992constrainWidthK40F9xA = ConstraintsKt.m5992constrainWidthK40F9xA(j, placeable3.getWidth());
        int iM5991constrainHeightK40F9xA = ConstraintsKt.m5991constrainHeightK40F9xA(j, placeable3.getHeight());
        final int width = (iM5992constrainWidthK40F9xA - placeable4.getWidth()) / 2;
        final int height = (iM5991constrainHeightK40F9xA - placeable4.getHeight()) / 2;
        final int height2 = (iM5991constrainHeightK40F9xA - placeable2.getHeight()) / 2;
        final int height3 = (iM5991constrainHeightK40F9xA - placeable.getHeight()) / 2;
        final int width2 = (iM5992constrainWidthK40F9xA - ((placeable2.getWidth() + measureScope.mo4551roundToPx0680j_4(f)) + placeable.getWidth())) / 2;
        final int width3 = placeable2.getWidth() + width2 + measureScope.mo4551roundToPx0680j_4(f);
        final int width4 = (iM5992constrainWidthK40F9xA - placeable3.getWidth()) / 2;
        final int height4 = (iM5991constrainHeightK40F9xA - placeable3.getHeight()) / 2;
        return MeasureScope.layout$default(measureScope, iM5992constrainWidthK40F9xA, iM5991constrainHeightK40F9xA, null, new Function1() { // from class: nea
            public final Object invoke(Object obj) {
                return NavigationItemKt.j(placeable4, width, height, placeable, width3, height3, placeable2, width2, height2, placeable3, width4, height4, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeLabelAndTopIcon-qoqLrGI, reason: not valid java name */
    public static final MeasureResult m703placeLabelAndTopIconqoqLrGI(MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final Placeable placeable4, long j, float f, float f2, float f3) {
        int iM5992constrainWidthK40F9xA = ConstraintsKt.m5992constrainWidthK40F9xA(j, Math.max(placeable.getWidth(), placeable3.getWidth()));
        int iM5991constrainHeightK40F9xA = ConstraintsKt.m5991constrainHeightK40F9xA(j, MathKt.roundToInt(placeable3.getHeight() + measureScope.mo4557toPx0680j_4(f) + placeable.getHeight() + (measureScope.mo4557toPx0680j_4(f3) * 2.0f)));
        final int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(f3 + f2));
        final int width = (iM5992constrainWidthK40F9xA - placeable2.getWidth()) / 2;
        final int width2 = (iM5992constrainWidthK40F9xA - placeable4.getWidth()) / 2;
        final int iMo4551roundToPx0680j_5 = iMo4551roundToPx0680j_4 - measureScope.mo4551roundToPx0680j_4(f2);
        final int width3 = (iM5992constrainWidthK40F9xA - placeable.getWidth()) / 2;
        final int height = iMo4551roundToPx0680j_4 + placeable2.getHeight() + measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(f + f2));
        final int width4 = (iM5992constrainWidthK40F9xA - placeable3.getWidth()) / 2;
        return MeasureScope.layout$default(measureScope, iM5992constrainWidthK40F9xA, iM5991constrainHeightK40F9xA, null, new Function1() { // from class: hea
            public final Object invoke(Object obj) {
                return NavigationItemKt.c(placeable4, width2, iMo4551roundToPx0680j_5, placeable, width3, height, placeable2, width, iMo4551roundToPx0680j_4, placeable3, width4, iMo4551roundToPx0680j_5, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public static Unit q(boolean z, Function0 function0, Function2 function2, Shape shape, float f, TextStyle textStyle, TextStyle textStyle2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, NavigationItemColors navigationItemColors, Modifier modifier, boolean z2, Function2 function3, int i, MutableInteractionSource mutableInteractionSource, int i2, int i3, int i4, Composer composer, int i5) {
        m691AnimatedNavigationItemDQd_Gtc(z, function0, function2, shape, f, textStyle, textStyle2, f2, f3, f4, f5, f6, f7, f8, f9, navigationItemColors, modifier, z2, function3, i, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), RecomposeScopeImplKt.updateChangedFlags(i4));
        return Unit.INSTANCE;
    }

    public static Unit r(InteractionSource interactionSource, long j, Shape shape, Function0 function0, Function2 function2, int i, Function0 function1, Function2 function3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i2, int i3, Composer composer, int i4) {
        m692AnimatedNavigationItemLayouthe0WsC4(interactionSource, j, shape, function0, function2, i, function1, function3, f, f2, f3, f4, f5, f6, f7, f8, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    public static Unit s(boolean z, Function0 function0, Function2 function2, TextStyle textStyle, Shape shape, float f, float f2, float f3, float f4, float f5, float f6, NavigationItemColors navigationItemColors, Modifier modifier, boolean z2, Function2 function3, int i, MutableInteractionSource mutableInteractionSource, int i2, int i3, Composer composer, int i4) {
        m694NavigationItem8Df7sds(z, function0, function2, textStyle, shape, f, f2, f3, f4, f5, f6, navigationItemColors, modifier, z2, function3, i, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }
}
