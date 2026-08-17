package androidx.compose.material3;

import androidx.compose.material3.tokens.PlainTooltipTokens;
import androidx.compose.material3.tokens.RichTooltipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0017\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u0010\"J7\u0010 \u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020\t2\b\b\u0002\u0010$\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020\t2\b\b\u0002\u0010&\u001a\u00020\tH\u0007¢\u0006\u0004\b'\u0010(J\u0019\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020\u0016H\u0007¢\u0006\u0004\b0\u00101J\u0019\u00102\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020\u0016H\u0007¢\u0006\u0004\b3\u00101J\u0019\u00104\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020\u0016H\u0007¢\u0006\u0004\b5\u00101J!\u00104\u001a\u00020.2\u0006\u00106\u001a\u0002072\b\b\u0002\u0010/\u001a\u00020\u0016H\u0007¢\u0006\u0004\b8\u00109R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0007R\u0013\u0010\u0010\u001a\u00020\u0011¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0015\u001a\u00020\u0016¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u001a\u001a\u00020\u0016¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001b\u0010\u0018R\u0018\u0010)\u001a\u00020!*\u00020*8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010:\u001a\u00020\u001dX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<¨\u0006="}, d2 = {"Landroidx/compose/material3/TooltipDefaults;", "", "<init>", "()V", "plainTooltipContainerShape", "Landroidx/compose/ui/graphics/Shape;", "getPlainTooltipContainerShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "plainTooltipContainerColor", "Landroidx/compose/ui/graphics/Color;", "getPlainTooltipContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "plainTooltipContentColor", "getPlainTooltipContentColor", "richTooltipContainerShape", "getRichTooltipContainerShape", "caretSize", "Landroidx/compose/ui/unit/DpSize;", "getCaretSize-MYxV2XQ", "()J", "J", "plainTooltipMaxWidth", "Landroidx/compose/ui/unit/Dp;", "getPlainTooltipMaxWidth-D9Ej5fM", "()F", "F", "richTooltipMaxWidth", "getRichTooltipMaxWidth-D9Ej5fM", "caretShape", "Landroidx/compose/material3/DefaultTooltipCaretShape;", "caretShape-EaSLcWc", "(J)Landroidx/compose/ui/graphics/Shape;", "richTooltipColors", "Landroidx/compose/material3/RichTooltipColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/RichTooltipColors;", "containerColor", "contentColor", "titleContentColor", "actionContentColor", "richTooltipColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/RichTooltipColors;", "defaultRichTooltipColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultRichTooltipColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/RichTooltipColors;", "rememberPlainTooltipPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "spacingBetweenTooltipAndAnchor", "rememberPlainTooltipPositionProvider-kHDZbjc", "(FLandroidx/compose/runtime/Composer;II)Landroidx/compose/ui/window/PopupPositionProvider;", "rememberRichTooltipPositionProvider", "rememberRichTooltipPositionProvider-kHDZbjc", "rememberTooltipPositionProvider", "rememberTooltipPositionProvider-kHDZbjc", "positioning", "Landroidx/compose/material3/TooltipAnchorPosition;", "rememberTooltipPositionProvider-Hu5FAss", "(IFLandroidx/compose/runtime/Composer;II)Landroidx/compose/ui/window/PopupPositionProvider;", "DefaultCaretShape", "getDefaultCaretShape$material3", "()Landroidx/compose/material3/DefaultTooltipCaretShape;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TooltipDefaults {
    public static final int $stable = 0;
    private static final DefaultTooltipCaretShape DefaultCaretShape;
    public static final TooltipDefaults INSTANCE = new TooltipDefaults();
    private static final long caretSize;
    private static final float plainTooltipMaxWidth;
    private static final float richTooltipMaxWidth;

    static {
        long jM6044DpSizeYgX7TsA = DpKt.m6044DpSizeYgX7TsA(Dp.m6022constructorimpl(16.0f), Dp.m6022constructorimpl(8.0f));
        caretSize = jM6044DpSizeYgX7TsA;
        plainTooltipMaxWidth = Dp.m6022constructorimpl(200.0f);
        richTooltipMaxWidth = Dp.m6022constructorimpl(320.0f);
        DefaultCaretShape = new DefaultTooltipCaretShape(jM6044DpSizeYgX7TsA, null);
    }

    private TooltipDefaults() {
    }

    /* JADX INFO: renamed from: caretShape-EaSLcWc$default, reason: not valid java name */
    public static /* synthetic */ Shape m1269caretShapeEaSLcWc$default(TooltipDefaults tooltipDefaults, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = caretSize;
        }
        return tooltipDefaults.m1270caretShapeEaSLcWc(j);
    }

    public final DefaultTooltipCaretShape caretShape() {
        return DefaultCaretShape;
    }

    /* JADX INFO: renamed from: caretShape-EaSLcWc, reason: not valid java name */
    public final Shape m1270caretShapeEaSLcWc(long caretSize2) {
        return new DefaultTooltipCaretShape(caretSize2, null);
    }

    /* JADX INFO: renamed from: getCaretSize-MYxV2XQ, reason: not valid java name */
    public final long m1271getCaretSizeMYxV2XQ() {
        return caretSize;
    }

    public final DefaultTooltipCaretShape getDefaultCaretShape$material3() {
        return DefaultCaretShape;
    }

    public final RichTooltipColors getDefaultRichTooltipColors$material3(ColorScheme colorScheme) {
        RichTooltipColors defaultRichTooltipColorsCached = colorScheme.getDefaultRichTooltipColorsCached();
        if (defaultRichTooltipColorsCached != null) {
            return defaultRichTooltipColorsCached;
        }
        RichTooltipTokens richTooltipTokens = RichTooltipTokens.INSTANCE;
        RichTooltipColors richTooltipColors = new RichTooltipColors(ColorSchemeKt.fromToken(colorScheme, richTooltipTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, richTooltipTokens.getSupportingTextColor()), ColorSchemeKt.fromToken(colorScheme, richTooltipTokens.getSubheadColor()), ColorSchemeKt.fromToken(colorScheme, richTooltipTokens.getActionLabelTextColor()), null);
        colorScheme.setDefaultRichTooltipColorsCached$material3(richTooltipColors);
        return richTooltipColors;
    }

    public final long getPlainTooltipContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(102696215, i, -1, "androidx.compose.material3.TooltipDefaults.<get-plainTooltipContainerColor> (Tooltip.kt:626)");
        }
        long value = ColorSchemeKt.getValue(PlainTooltipTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final Shape getPlainTooltipContainerShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(49570325, i, -1, "androidx.compose.material3.TooltipDefaults.<get-plainTooltipContainerShape> (Tooltip.kt:622)");
        }
        Shape value = ShapesKt.getValue(PlainTooltipTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getPlainTooltipContentColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1982928937, i, -1, "androidx.compose.material3.TooltipDefaults.<get-plainTooltipContentColor> (Tooltip.kt:630)");
        }
        long value = ColorSchemeKt.getValue(PlainTooltipTokens.INSTANCE.getSupportingTextColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getPlainTooltipMaxWidth-D9Ej5fM, reason: not valid java name */
    public final float m1272getPlainTooltipMaxWidthD9Ej5fM() {
        return plainTooltipMaxWidth;
    }

    public final Shape getRichTooltipContainerShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1138709783, i, -1, "androidx.compose.material3.TooltipDefaults.<get-richTooltipContainerShape> (Tooltip.kt:634)");
        }
        Shape value = ShapesKt.getValue(RichTooltipTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getRichTooltipMaxWidth-D9Ej5fM, reason: not valid java name */
    public final float m1273getRichTooltipMaxWidthD9Ej5fM() {
        return richTooltipMaxWidth;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of rememberTooltipPositionProvider API.", replaceWith = @ReplaceWith(expression = "rememberTooltipPositionProvider(spacingBetweenTooltipAndAnchor)", imports = {}))
    /* JADX INFO: renamed from: rememberPlainTooltipPositionProvider-kHDZbjc, reason: not valid java name */
    public final PopupPositionProvider m1274rememberPlainTooltipPositionProviderkHDZbjc(float f, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = TooltipKt.getSpacingBetweenTooltipAndAnchor();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1047866909, i, -1, "androidx.compose.material3.TooltipDefaults.rememberPlainTooltipPositionProvider (Tooltip.kt:707)");
        }
        final int iMo4551roundToPx0680j_4 = ((Density) composer.consume(CompositionLocalsKt.getLocalDensity())).mo4551roundToPx0680j_4(f);
        boolean zChanged = composer.changed(iMo4551roundToPx0680j_4);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new PopupPositionProvider() { // from class: androidx.compose.material3.TooltipDefaults$rememberPlainTooltipPositionProvider$1$1
                @Override // androidx.compose.ui.window.PopupPositionProvider
                /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
                public long mo45calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
                    int left = anchorBounds.getLeft() + ((anchorBounds.getWidth() - ((int) (popupContentSize >> 32))) / 2);
                    int top = (anchorBounds.getTop() - ((int) (popupContentSize & 4294967295L))) - iMo4551roundToPx0680j_4;
                    if (top < 0) {
                        top = anchorBounds.getBottom() + iMo4551roundToPx0680j_4;
                    }
                    return IntOffset.m6144constructorimpl((((long) left) << 32) | (((long) top) & 4294967295L));
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        TooltipDefaults$rememberPlainTooltipPositionProvider$1$1 tooltipDefaults$rememberPlainTooltipPositionProvider$1$1 = (TooltipDefaults$rememberPlainTooltipPositionProvider$1$1) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return tooltipDefaults$rememberPlainTooltipPositionProvider$1$1;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of rememberTooltipPositionProvider API.", replaceWith = @ReplaceWith(expression = "rememberTooltipPositionProvider(spacingBetweenTooltipAndAnchor)", imports = {}))
    /* JADX INFO: renamed from: rememberRichTooltipPositionProvider-kHDZbjc, reason: not valid java name */
    public final PopupPositionProvider m1275rememberRichTooltipPositionProviderkHDZbjc(float f, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = TooltipKt.getSpacingBetweenTooltipAndAnchor();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1538806795, i, -1, "androidx.compose.material3.TooltipDefaults.rememberRichTooltipPositionProvider (Tooltip.kt:746)");
        }
        final int iMo4551roundToPx0680j_4 = ((Density) composer.consume(CompositionLocalsKt.getLocalDensity())).mo4551roundToPx0680j_4(f);
        boolean zChanged = composer.changed(iMo4551roundToPx0680j_4);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new PopupPositionProvider() { // from class: androidx.compose.material3.TooltipDefaults$rememberRichTooltipPositionProvider$1$1
                @Override // androidx.compose.ui.window.PopupPositionProvider
                /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
                public long mo45calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
                    int left = anchorBounds.getLeft();
                    int i3 = (int) (popupContentSize >> 32);
                    if (left + i3 > ((int) (windowSize >> 32)) && (left = anchorBounds.getRight() - i3) < 0) {
                        left = anchorBounds.getLeft() + ((anchorBounds.getWidth() - i3) / 2);
                    }
                    int top = (anchorBounds.getTop() - ((int) (popupContentSize & 4294967295L))) - iMo4551roundToPx0680j_4;
                    if (top < 0) {
                        top = anchorBounds.getBottom() + iMo4551roundToPx0680j_4;
                    }
                    return IntOffset.m6144constructorimpl((((long) left) << 32) | (((long) top) & 4294967295L));
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        TooltipDefaults$rememberRichTooltipPositionProvider$1$1 tooltipDefaults$rememberRichTooltipPositionProvider$1$1 = (TooltipDefaults$rememberRichTooltipPositionProvider$1$1) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return tooltipDefaults$rememberRichTooltipPositionProvider$1$1;
    }

    /* JADX INFO: renamed from: rememberTooltipPositionProvider-Hu5FAss, reason: not valid java name */
    public final PopupPositionProvider m1276rememberTooltipPositionProviderHu5FAss(int i, float f, Composer composer, int i2, int i3) {
        if ((i3 & 2) != 0) {
            f = TooltipKt.getSpacingBetweenTooltipAndAnchor();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-573803578, i2, -1, "androidx.compose.material3.TooltipDefaults.rememberTooltipPositionProvider (Tooltip.kt:849)");
        }
        int iMo4551roundToPx0680j_4 = ((Density) composer.consume(CompositionLocalsKt.getLocalDensity())).mo4551roundToPx0680j_4(f);
        boolean zChanged = composer.changed(iMo4551roundToPx0680j_4) | ((((i2 & 14) ^ 6) > 4 && composer.changed(i)) || (i2 & 6) == 4);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new TooltipPositionProviderImpl(i, iMo4551roundToPx0680j_4, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        TooltipPositionProviderImpl tooltipPositionProviderImpl = (TooltipPositionProviderImpl) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return tooltipPositionProviderImpl;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of rememberTooltipPositionProvider API that takes a preferred positioning. Please use rememberTooltipPositionProvider with TooltipAnchorPosition.Above if this same behavior is desired.", replaceWith = @ReplaceWith(expression = "rememberTooltipPositionProvider(TooltipAnchorPosition.ABOVE, spacingBetweenTooltipAndAnchor)", imports = {}))
    /* JADX INFO: renamed from: rememberTooltipPositionProvider-kHDZbjc, reason: not valid java name */
    public final PopupPositionProvider m1277rememberTooltipPositionProviderkHDZbjc(float f, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = TooltipKt.getSpacingBetweenTooltipAndAnchor();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1613894159, i, -1, "androidx.compose.material3.TooltipDefaults.rememberTooltipPositionProvider (Tooltip.kt:799)");
        }
        final int iMo4551roundToPx0680j_4 = ((Density) composer.consume(CompositionLocalsKt.getLocalDensity())).mo4551roundToPx0680j_4(f);
        boolean zChanged = composer.changed(iMo4551roundToPx0680j_4);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new PopupPositionProvider() { // from class: androidx.compose.material3.TooltipDefaults$rememberTooltipPositionProvider$1$1
                @Override // androidx.compose.ui.window.PopupPositionProvider
                /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
                public long mo45calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
                    int i3 = (int) (popupContentSize >> 32);
                    int left = anchorBounds.getLeft() + ((anchorBounds.getWidth() - i3) / 2);
                    if (left < 0) {
                        left = anchorBounds.getLeft();
                    } else if (left + i3 > ((int) (windowSize >> 32))) {
                        left = anchorBounds.getRight() - i3;
                    }
                    int top = (anchorBounds.getTop() - ((int) (popupContentSize & 4294967295L))) - iMo4551roundToPx0680j_4;
                    if (top < 0) {
                        top = anchorBounds.getBottom() + iMo4551roundToPx0680j_4;
                    }
                    return IntOffset.m6144constructorimpl((((long) left) << 32) | (((long) top) & 4294967295L));
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        TooltipDefaults$rememberTooltipPositionProvider$1$1 tooltipDefaults$rememberTooltipPositionProvider$1$1 = (TooltipDefaults$rememberTooltipPositionProvider$1$1) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return tooltipDefaults$rememberTooltipPositionProvider$1$1;
    }

    public final RichTooltipColors richTooltipColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1622312141, i, -1, "androidx.compose.material3.TooltipDefaults.richTooltipColors (Tooltip.kt:660)");
        }
        RichTooltipColors defaultRichTooltipColors$material3 = getDefaultRichTooltipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultRichTooltipColors$material3;
    }

    /* JADX INFO: renamed from: richTooltipColors-ro_MJ88, reason: not valid java name */
    public final RichTooltipColors m1278richTooltipColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            j = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if ((i2 & 2) != 0) {
            j2 = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if ((i2 & 4) != 0) {
            j3 = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if ((i2 & 8) != 0) {
            j4 = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1498555081, i, -1, "androidx.compose.material3.TooltipDefaults.richTooltipColors (Tooltip.kt:673)");
        }
        long j5 = j2;
        long j6 = j;
        RichTooltipColors richTooltipColorsM775copyjRlVdoo = getDefaultRichTooltipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m775copyjRlVdoo(j6, j5, j3, j4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return richTooltipColorsM775copyjRlVdoo;
    }
}
