package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.ElevatedCardTokens;
import androidx.compose.material3.tokens.FilledCardTokens;
import androidx.compose.material3.tokens.OutlinedCardTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JK\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0015\u0010\u0016JK\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0018\u0010\u0016JK\u0010\u0019\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u001a\u0010\u0016J\r\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001dJ7\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u001fH\u0007¢\u0006\u0004\b#\u0010$J\r\u0010)\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001dJ7\u0010)\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u001fH\u0007¢\u0006\u0004\b*\u0010$J\r\u0010-\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001dJ7\u0010-\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u001fH\u0007¢\u0006\u0004\b.\u0010$J\u0017\u00101\u001a\u0002022\b\b\u0002\u00103\u001a\u000204H\u0007¢\u0006\u0002\u00105R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0018\u0010%\u001a\u00020\u001c*\u00020&8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0018\u0010+\u001a\u00020\u001c*\u00020&8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b,\u0010(R\u0018\u0010/\u001a\u00020\u001c*\u00020&8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b0\u0010(¨\u00066"}, d2 = {"Landroidx/compose/material3/CardDefaults;", "", "<init>", "()V", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "elevatedShape", "getElevatedShape", "outlinedShape", "getOutlinedShape", "cardElevation", "Landroidx/compose/material3/CardElevation;", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "cardElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CardElevation;", "elevatedCardElevation", "elevatedCardElevation-aqJV_2Y", "outlinedCardElevation", "outlinedCardElevation-aqJV_2Y", "cardColors", "Landroidx/compose/material3/CardColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/CardColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "cardColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CardColors;", "defaultCardColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultCardColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/CardColors;", "elevatedCardColors", "elevatedCardColors-ro_MJ88", "defaultElevatedCardColors", "getDefaultElevatedCardColors$material3", "outlinedCardColors", "outlinedCardColors-ro_MJ88", "defaultOutlinedCardColors", "getDefaultOutlinedCardColors$material3", "outlinedCardBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "(ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CardDefaults {
    public static final int $stable = 0;
    public static final CardDefaults INSTANCE = new CardDefaults();

    private CardDefaults() {
    }

    public final CardColors cardColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1876034303, i, -1, "androidx.compose.material3.CardDefaults.cardColors (Card.kt:472)");
        }
        CardColors defaultCardColors$material3 = getDefaultCardColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultCardColors$material3;
    }

    /* JADX INFO: renamed from: cardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m156cardColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        long j5;
        long jM3133copywmQWz5c$default;
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM278contentColorForek8zF_U = (i2 & 2) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(jM3170getUnspecified0d7_KjU, composer, i & 14) : j2;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        if ((i2 & 8) != 0) {
            long j6 = jM278contentColorForek8zF_U;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(j6, 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
            j5 = j6;
        } else {
            j5 = jM278contentColorForek8zF_U;
            jM3133copywmQWz5c$default = j4;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1589582123, i, -1, "androidx.compose.material3.CardDefaults.cardColors (Card.kt:490)");
        }
        CardColors cardColorsM151copyjRlVdoo = getDefaultCardColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m151copyjRlVdoo(jM3170getUnspecified0d7_KjU, j5, jM3170getUnspecified0d7_KjU2, jM3133copywmQWz5c$default);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardColorsM151copyjRlVdoo;
    }

    /* JADX INFO: renamed from: cardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m157cardElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = FilledCardTokens.INSTANCE.m1817getContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = FilledCardTokens.INSTANCE.m1823getPressedContainerElevationD9Ej5fM();
        }
        if ((i2 & 4) != 0) {
            f3 = FilledCardTokens.INSTANCE.m1820getFocusContainerElevationD9Ej5fM();
        }
        if ((i2 & 8) != 0) {
            f4 = FilledCardTokens.INSTANCE.m1821getHoverContainerElevationD9Ej5fM();
        }
        if ((i2 & 16) != 0) {
            f5 = FilledCardTokens.INSTANCE.m1819getDraggedContainerElevationD9Ej5fM();
        }
        float f7 = f5;
        if ((i2 & 32) != 0) {
            f6 = FilledCardTokens.INSTANCE.m1818getDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-574898487, i, -1, "androidx.compose.material3.CardDefaults.cardElevation (Card.kt:400)");
        }
        float f8 = f6;
        float f9 = f3;
        float f10 = f;
        CardElevation cardElevation = new CardElevation(f10, f2, f9, f4, f7, f8, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardElevation;
    }

    public final CardColors elevatedCardColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1610137975, i, -1, "androidx.compose.material3.CardDefaults.elevatedCardColors (Card.kt:518)");
        }
        CardColors defaultElevatedCardColors$material3 = getDefaultElevatedCardColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultElevatedCardColors$material3;
    }

    /* JADX INFO: renamed from: elevatedCardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m158elevatedCardColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        long j5;
        long jM3133copywmQWz5c$default;
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM278contentColorForek8zF_U = (i2 & 2) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(jM3170getUnspecified0d7_KjU, composer, i & 14) : j2;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        if ((i2 & 8) != 0) {
            long j6 = jM278contentColorForek8zF_U;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(j6, 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
            j5 = j6;
        } else {
            j5 = jM278contentColorForek8zF_U;
            jM3133copywmQWz5c$default = j4;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(139558303, i, -1, "androidx.compose.material3.CardDefaults.elevatedCardColors (Card.kt:536)");
        }
        CardColors cardColorsM151copyjRlVdoo = getDefaultElevatedCardColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m151copyjRlVdoo(jM3170getUnspecified0d7_KjU, j5, jM3170getUnspecified0d7_KjU2, jM3133copywmQWz5c$default);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardColorsM151copyjRlVdoo;
    }

    /* JADX INFO: renamed from: elevatedCardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m159elevatedCardElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = ElevatedCardTokens.INSTANCE.m1735getContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = ElevatedCardTokens.INSTANCE.m1741getPressedContainerElevationD9Ej5fM();
        }
        if ((i2 & 4) != 0) {
            f3 = ElevatedCardTokens.INSTANCE.m1738getFocusContainerElevationD9Ej5fM();
        }
        if ((i2 & 8) != 0) {
            f4 = ElevatedCardTokens.INSTANCE.m1739getHoverContainerElevationD9Ej5fM();
        }
        if ((i2 & 16) != 0) {
            f5 = ElevatedCardTokens.INSTANCE.m1737getDraggedContainerElevationD9Ej5fM();
        }
        float f7 = f5;
        if ((i2 & 32) != 0) {
            f6 = ElevatedCardTokens.INSTANCE.m1736getDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1154241939, i, -1, "androidx.compose.material3.CardDefaults.elevatedCardElevation (Card.kt:430)");
        }
        float f8 = f6;
        float f9 = f3;
        float f10 = f;
        CardElevation cardElevation = new CardElevation(f10, f2, f9, f4, f7, f8, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardElevation;
    }

    public final CardColors getDefaultCardColors$material3(ColorScheme colorScheme) {
        CardColors defaultCardColorsCached = colorScheme.getDefaultCardColorsCached();
        if (defaultCardColorsCached != null) {
            return defaultCardColorsCached;
        }
        FilledCardTokens filledCardTokens = FilledCardTokens.INSTANCE;
        CardColors cardColors = new CardColors(ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getContainerColor()), ColorSchemeKt.m277contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getContainerColor())), ColorKt.m3179compositeOverOWjLjI(Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getDisabledContainerColor()), filledCardTokens.getDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getContainerColor())), Color.m3133copywmQWz5c$default(ColorSchemeKt.m277contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getContainerColor())), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultCardColorsCached$material3(cardColors);
        return cardColors;
    }

    public final CardColors getDefaultElevatedCardColors$material3(ColorScheme colorScheme) {
        CardColors defaultElevatedCardColorsCached = colorScheme.getDefaultElevatedCardColorsCached();
        if (defaultElevatedCardColorsCached != null) {
            return defaultElevatedCardColorsCached;
        }
        ElevatedCardTokens elevatedCardTokens = ElevatedCardTokens.INSTANCE;
        CardColors cardColors = new CardColors(ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getContainerColor()), ColorSchemeKt.m277contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getContainerColor())), ColorKt.m3179compositeOverOWjLjI(Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getDisabledContainerColor()), elevatedCardTokens.getDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getDisabledContainerColor())), Color.m3133copywmQWz5c$default(ColorSchemeKt.m277contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getContainerColor())), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultElevatedCardColorsCached$material3(cardColors);
        return cardColors;
    }

    public final CardColors getDefaultOutlinedCardColors$material3(ColorScheme colorScheme) {
        CardColors defaultOutlinedCardColorsCached = colorScheme.getDefaultOutlinedCardColorsCached();
        if (defaultOutlinedCardColorsCached != null) {
            return defaultOutlinedCardColorsCached;
        }
        OutlinedCardTokens outlinedCardTokens = OutlinedCardTokens.INSTANCE;
        CardColors cardColors = new CardColors(ColorSchemeKt.fromToken(colorScheme, outlinedCardTokens.getContainerColor()), ColorSchemeKt.m277contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, outlinedCardTokens.getContainerColor())), ColorSchemeKt.fromToken(colorScheme, outlinedCardTokens.getContainerColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.m277contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, outlinedCardTokens.getContainerColor())), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultOutlinedCardColorsCached$material3(cardColors);
        return cardColors;
    }

    public final Shape getElevatedShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-133496185, i, -1, "androidx.compose.material3.CardDefaults.<get-elevatedShape> (Card.kt:374)");
        }
        Shape value = ShapesKt.getValue(ElevatedCardTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final Shape getOutlinedShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1095404023, i, -1, "androidx.compose.material3.CardDefaults.<get-outlinedShape> (Card.kt:378)");
        }
        Shape value = ShapesKt.getValue(OutlinedCardTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1266660211, i, -1, "androidx.compose.material3.CardDefaults.<get-shape> (Card.kt:370)");
        }
        Shape value = ShapesKt.getValue(FilledCardTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final BorderStroke outlinedCardBorder(boolean z, Composer composer, int i, int i2) {
        long jM3179compositeOverOWjLjI;
        if ((i2 & 1) != 0) {
            z = true;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-392936593, i, -1, "androidx.compose.material3.CardDefaults.outlinedCardBorder (Card.kt:613)");
        }
        if (z) {
            composer.startReplaceGroup(2106932974);
            jM3179compositeOverOWjLjI = ColorSchemeKt.getValue(OutlinedCardTokens.INSTANCE.getOutlineColor(), composer, 6);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(2107012365);
            jM3179compositeOverOWjLjI = ColorKt.m3179compositeOverOWjLjI(Color.m3133copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedCardTokens.INSTANCE.getDisabledOutlineColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.getValue(ElevatedCardTokens.INSTANCE.getContainerColor(), composer, 6));
            composer.endReplaceGroup();
        }
        boolean zChanged = composer.changed(jM3179compositeOverOWjLjI);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = BorderStrokeKt.m12BorderStrokecXLIe8U(OutlinedCardTokens.INSTANCE.m1975getOutlineWidthD9Ej5fM(), jM3179compositeOverOWjLjI);
            composer.updateRememberedValue(objRememberedValue);
        }
        BorderStroke borderStroke = (BorderStroke) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return borderStroke;
    }

    public final CardColors outlinedCardColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1204388929, i, -1, "androidx.compose.material3.CardDefaults.outlinedCardColors (Card.kt:567)");
        }
        CardColors defaultOutlinedCardColors$material3 = getDefaultOutlinedCardColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultOutlinedCardColors$material3;
    }

    /* JADX INFO: renamed from: outlinedCardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m160outlinedCardColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM278contentColorForek8zF_U = (i2 & 2) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(jM3170getUnspecified0d7_KjU, composer, i & 14) : j2;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3133copywmQWz5c$default = (i2 & 8) != 0 ? Color.m3133copywmQWz5c$default(ColorSchemeKt.m278contentColorForek8zF_U(jM3170getUnspecified0d7_KjU, composer, i & 14), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j4;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1112362409, i, -1, "androidx.compose.material3.CardDefaults.outlinedCardColors (Card.kt:585)");
        }
        CardColors cardColorsM151copyjRlVdoo = getDefaultOutlinedCardColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m151copyjRlVdoo(jM3170getUnspecified0d7_KjU, jM278contentColorForek8zF_U, jM3170getUnspecified0d7_KjU2, jM3133copywmQWz5c$default);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardColorsM151copyjRlVdoo;
    }

    /* JADX INFO: renamed from: outlinedCardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m161outlinedCardElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = OutlinedCardTokens.INSTANCE.m1969getContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = f;
        }
        if ((i2 & 4) != 0) {
            f3 = f;
        }
        if ((i2 & 8) != 0) {
            f4 = f;
        }
        if ((i2 & 16) != 0) {
            f5 = OutlinedCardTokens.INSTANCE.m1971getDraggedContainerElevationD9Ej5fM();
        }
        float f7 = f5;
        if ((i2 & 32) != 0) {
            f6 = OutlinedCardTokens.INSTANCE.m1970getDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-97678773, i, -1, "androidx.compose.material3.CardDefaults.outlinedCardElevation (Card.kt:459)");
        }
        float f8 = f6;
        float f9 = f3;
        float f10 = f;
        CardElevation cardElevation = new CardElevation(f10, f2, f9, f4, f7, f8, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardElevation;
    }
}
