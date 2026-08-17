package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.FilledIconButtonTokens;
import androidx.compose.material3.tokens.FilledTonalIconButtonTokens;
import androidx.compose.material3.tokens.OutlinedIconButtonTokens;
import androidx.compose.material3.tokens.SmallIconButtonTokens;
import androidx.compose.material3.tokens.StandardIconButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001_B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J7\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\u0005*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J7\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0014\u0010\rJ\u0011\u0010\u0015\u001a\u00020\u0005*\u00020\u000fH\u0000¢\u0006\u0002\b\u0016J\r\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019JK\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001e\u001a\u00020\u0018*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019JK\u0010!\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\bH\u0007¢\u0006\u0004\b\"\u0010\u001dJ\u0011\u0010#\u001a\u00020\u0018*\u00020\u000fH\u0000¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J7\u0010%\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007¢\u0006\u0004\b&\u0010\rJ\r\u0010*\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019JK\u0010*\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\bH\u0007¢\u0006\u0004\b+\u0010\u001dJ\r\u0010/\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J7\u0010/\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007¢\u0006\u0004\b0\u0010\rJ\r\u00103\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019JK\u00103\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\bH\u0007¢\u0006\u0004\b4\u0010\u001dJ\r\u00107\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J7\u00107\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007¢\u0006\u0004\b8\u0010\rJ\u001b\u00109\u001a\u00020\u0005*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0000¢\u0006\u0004\b:\u0010\u0012J\r\u0010;\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J7\u0010;\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007¢\u0006\u0004\b<\u0010\rJ\u0011\u0010=\u001a\u00020\u0005*\u00020\u000fH\u0000¢\u0006\u0002\b>J\r\u0010?\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019JK\u0010?\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\bH\u0007¢\u0006\u0004\b@\u0010\u001dJ\u001b\u0010A\u001a\u00020\u0018*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0000¢\u0006\u0004\bB\u0010 J\r\u0010C\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019JK\u0010C\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\bH\u0007¢\u0006\u0004\bD\u0010\u001dJ\u0011\u0010E\u001a\u00020\u0018*\u00020\u000fH\u0000¢\u0006\u0002\bFJ\u001f\u0010G\u001a\u0004\u0018\u00010H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020JH\u0007¢\u0006\u0002\u0010LJ\u001f\u0010M\u001a\u0004\u0018\u00010H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020JH\u0007¢\u0006\u0002\u0010LJ\u0015\u0010N\u001a\u00020H2\u0006\u0010I\u001a\u00020JH\u0007¢\u0006\u0002\u0010OJ\u0015\u0010P\u001a\u00020H2\u0006\u0010I\u001a\u00020JH\u0007¢\u0006\u0002\u0010OJ\u0019\u0010Y\u001a\u00020Z2\b\b\u0002\u0010[\u001a\u00020\\H\u0000¢\u0006\u0004\b]\u0010^R\u0018\u0010'\u001a\u00020\u0005*\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0018\u0010,\u001a\u00020\u0018*\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0018\u00101\u001a\u00020\u0005*\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b2\u0010)R\u0018\u00105\u001a\u00020\u0018*\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b6\u0010.R\u0011\u0010Q\u001a\u00020R8G¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0011\u0010U\u001a\u00020R8G¢\u0006\u0006\u001a\u0004\bV\u0010TR\u0011\u0010W\u001a\u00020R8G¢\u0006\u0006\u001a\u0004\bX\u0010T¨\u0006`"}, d2 = {"Landroidx/compose/material3/IconButtonDefaults;", "", "<init>", "()V", "iconButtonColors", "Landroidx/compose/material3/IconButtonColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/IconButtonColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "iconButtonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/IconButtonColors;", "defaultIconButtonColors", "Landroidx/compose/material3/ColorScheme;", "localContentColor", "defaultIconButtonColors-4WTKRHQ$material3", "(Landroidx/compose/material3/ColorScheme;J)Landroidx/compose/material3/IconButtonColors;", "iconButtonVibrantColors", "iconButtonVibrantColors-ro_MJ88", "defaultIconButtonVibrantColors", "defaultIconButtonVibrantColors$material3", "iconToggleButtonColors", "Landroidx/compose/material3/IconToggleButtonColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/IconToggleButtonColors;", "checkedContainerColor", "checkedContentColor", "iconToggleButtonColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/IconToggleButtonColors;", "defaultIconToggleButtonColors", "defaultIconToggleButtonColors-4WTKRHQ$material3", "(Landroidx/compose/material3/ColorScheme;J)Landroidx/compose/material3/IconToggleButtonColors;", "iconToggleButtonVibrantColors", "iconToggleButtonVibrantColors-5tl4gsc", "defaultIconToggleButtonVibrantColors", "defaultIconToggleButtonVibrantColors$material3", "filledIconButtonColors", "filledIconButtonColors-ro_MJ88", "defaultFilledIconButtonColors", "getDefaultFilledIconButtonColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/IconButtonColors;", "filledIconToggleButtonColors", "filledIconToggleButtonColors-5tl4gsc", "defaultFilledIconToggleButtonColors", "getDefaultFilledIconToggleButtonColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/IconToggleButtonColors;", "filledTonalIconButtonColors", "filledTonalIconButtonColors-ro_MJ88", "defaultFilledTonalIconButtonColors", "getDefaultFilledTonalIconButtonColors$material3", "filledTonalIconToggleButtonColors", "filledTonalIconToggleButtonColors-5tl4gsc", "defaultFilledTonalIconToggleButtonColors", "getDefaultFilledTonalIconToggleButtonColors$material3", "outlinedIconButtonColors", "outlinedIconButtonColors-ro_MJ88", "defaultOutlinedIconButtonColors", "defaultOutlinedIconButtonColors-4WTKRHQ$material3", "outlinedIconButtonVibrantColors", "outlinedIconButtonVibrantColors-ro_MJ88", "defaultOutlinedIconButtonVibrantColors", "defaultOutlinedIconButtonVibrantColors$material3", "outlinedIconToggleButtonColors", "outlinedIconToggleButtonColors-5tl4gsc", "defaultOutlinedIconToggleButtonColors", "defaultOutlinedIconToggleButtonColors-4WTKRHQ$material3", "outlinedIconToggleButtonVibrantColors", "outlinedIconToggleButtonVibrantColors-5tl4gsc", "defaultOutlinedIconToggleButtonVibrantColors", "defaultOutlinedIconToggleButtonVibrantColors$material3", "outlinedIconToggleButtonBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "checked", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedIconToggleButtonVibrantBorder", "outlinedIconButtonBorder", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedIconButtonVibrantBorder", "standardShape", "Landroidx/compose/ui/graphics/Shape;", "getStandardShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "filledShape", "getFilledShape", "outlinedShape", "getOutlinedShape", "smallContainerSize", "Landroidx/compose/ui/unit/DpSize;", "widthOption", "Landroidx/compose/material3/IconButtonDefaults$IconButtonWidthOption;", "smallContainerSize-N-wlBFI$material3", "(I)J", "IconButtonWidthOption", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class IconButtonDefaults {
    public static final int $stable = 0;
    public static final IconButtonDefaults INSTANCE = new IconButtonDefaults();

    private IconButtonDefaults() {
    }

    /* JADX INFO: renamed from: smallContainerSize-N-wlBFI$material3$default, reason: not valid java name */
    public static /* synthetic */ long m509smallContainerSizeNwlBFI$material3$default(IconButtonDefaults iconButtonDefaults, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = IconButtonWidthOption.INSTANCE.m535getUniformrc6NtMs();
        }
        return iconButtonDefaults.m526smallContainerSizeNwlBFI$material3(i);
    }

    /* JADX INFO: renamed from: defaultIconButtonColors-4WTKRHQ$material3, reason: not valid java name */
    public final IconButtonColors m510defaultIconButtonColors4WTKRHQ$material3(ColorScheme colorScheme, long j) {
        IconButtonColors defaultIconButtonColorsCached = colorScheme.getDefaultIconButtonColorsCached();
        if (defaultIconButtonColorsCached != null) {
            return defaultIconButtonColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        IconButtonColors iconButtonColors = new IconButtonColors(companion.m3169getTransparent0d7_KjU(), j, companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(j, StandardIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultIconButtonColorsCached$material3(iconButtonColors);
        return iconButtonColors;
    }

    public final IconButtonColors defaultIconButtonVibrantColors$material3(ColorScheme colorScheme) {
        IconButtonColors defaultIconButtonVibrantColorsCached = colorScheme.getDefaultIconButtonVibrantColorsCached();
        if (defaultIconButtonVibrantColorsCached != null) {
            return defaultIconButtonVibrantColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3169getTransparent0d7_KjU = companion.m3169getTransparent0d7_KjU();
        StandardIconButtonTokens standardIconButtonTokens = StandardIconButtonTokens.INSTANCE;
        IconButtonColors iconButtonColors = new IconButtonColors(jM3169getTransparent0d7_KjU, ColorSchemeKt.fromToken(colorScheme, standardIconButtonTokens.getColor()), companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, standardIconButtonTokens.getDisabledColor()), standardIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultIconButtonVibrantColorsCached$material3(iconButtonColors);
        return iconButtonColors;
    }

    /* JADX INFO: renamed from: defaultIconToggleButtonColors-4WTKRHQ$material3, reason: not valid java name */
    public final IconToggleButtonColors m511defaultIconToggleButtonColors4WTKRHQ$material3(ColorScheme colorScheme, long j) {
        IconToggleButtonColors defaultIconToggleButtonColorsCached = colorScheme.getDefaultIconToggleButtonColorsCached();
        if (defaultIconToggleButtonColorsCached != null) {
            return defaultIconToggleButtonColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3169getTransparent0d7_KjU = companion.m3169getTransparent0d7_KjU();
        long jM3169getTransparent0d7_KjU2 = companion.m3169getTransparent0d7_KjU();
        StandardIconButtonTokens standardIconButtonTokens = StandardIconButtonTokens.INSTANCE;
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(jM3169getTransparent0d7_KjU, j, jM3169getTransparent0d7_KjU2, Color.m3133copywmQWz5c$default(j, standardIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), companion.m3169getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, standardIconButtonTokens.getSelectedColor()), null);
        colorScheme.setDefaultIconToggleButtonColorsCached$material3(iconToggleButtonColors);
        return iconToggleButtonColors;
    }

    public final IconToggleButtonColors defaultIconToggleButtonVibrantColors$material3(ColorScheme colorScheme) {
        IconToggleButtonColors defaultIconToggleButtonVibrantColorsCached = colorScheme.getDefaultIconToggleButtonVibrantColorsCached();
        if (defaultIconToggleButtonVibrantColorsCached != null) {
            return defaultIconToggleButtonVibrantColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3169getTransparent0d7_KjU = companion.m3169getTransparent0d7_KjU();
        StandardIconButtonTokens standardIconButtonTokens = StandardIconButtonTokens.INSTANCE;
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(jM3169getTransparent0d7_KjU, ColorSchemeKt.fromToken(colorScheme, standardIconButtonTokens.getUnselectedColor()), companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, standardIconButtonTokens.getDisabledColor()), standardIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), companion.m3169getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, standardIconButtonTokens.getSelectedColor()), null);
        colorScheme.setDefaultIconToggleButtonVibrantColorsCached$material3(iconToggleButtonColors);
        return iconToggleButtonColors;
    }

    /* JADX INFO: renamed from: defaultOutlinedIconButtonColors-4WTKRHQ$material3, reason: not valid java name */
    public final IconButtonColors m512defaultOutlinedIconButtonColors4WTKRHQ$material3(ColorScheme colorScheme, long j) {
        IconButtonColors defaultOutlinedIconButtonColorsCached = colorScheme.getDefaultOutlinedIconButtonColorsCached();
        if (defaultOutlinedIconButtonColorsCached != null) {
            return defaultOutlinedIconButtonColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        IconButtonColors iconButtonColors = new IconButtonColors(companion.m3169getTransparent0d7_KjU(), j, companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(j, OutlinedIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultOutlinedIconButtonColorsCached$material3(iconButtonColors);
        return iconButtonColors;
    }

    public final IconButtonColors defaultOutlinedIconButtonVibrantColors$material3(ColorScheme colorScheme) {
        IconButtonColors defaultOutlinedIconButtonVibrantColorsCached = colorScheme.getDefaultOutlinedIconButtonVibrantColorsCached();
        if (defaultOutlinedIconButtonVibrantColorsCached != null) {
            return defaultOutlinedIconButtonVibrantColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3169getTransparent0d7_KjU = companion.m3169getTransparent0d7_KjU();
        OutlinedIconButtonTokens outlinedIconButtonTokens = OutlinedIconButtonTokens.INSTANCE;
        IconButtonColors iconButtonColors = new IconButtonColors(jM3169getTransparent0d7_KjU, ColorSchemeKt.fromToken(colorScheme, outlinedIconButtonTokens.getColor()), companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedIconButtonTokens.getDisabledColor()), outlinedIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultOutlinedIconButtonVibrantColorsCached$material3(iconButtonColors);
        return iconButtonColors;
    }

    /* JADX INFO: renamed from: defaultOutlinedIconToggleButtonColors-4WTKRHQ$material3, reason: not valid java name */
    public final IconToggleButtonColors m513defaultOutlinedIconToggleButtonColors4WTKRHQ$material3(ColorScheme colorScheme, long j) {
        IconToggleButtonColors defaultIconToggleButtonColorsCached = colorScheme.getDefaultIconToggleButtonColorsCached();
        if (defaultIconToggleButtonColorsCached != null) {
            return defaultIconToggleButtonColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3169getTransparent0d7_KjU = companion.m3169getTransparent0d7_KjU();
        long jM3169getTransparent0d7_KjU2 = companion.m3169getTransparent0d7_KjU();
        OutlinedIconButtonTokens outlinedIconButtonTokens = OutlinedIconButtonTokens.INSTANCE;
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(jM3169getTransparent0d7_KjU, j, jM3169getTransparent0d7_KjU2, Color.m3133copywmQWz5c$default(j, outlinedIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedIconButtonTokens.getSelectedContainerColor()), ColorSchemeKt.m277contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, outlinedIconButtonTokens.getSelectedContainerColor())), null);
        colorScheme.setDefaultOutlinedIconToggleButtonColorsCached$material3(iconToggleButtonColors);
        return iconToggleButtonColors;
    }

    public final IconToggleButtonColors defaultOutlinedIconToggleButtonVibrantColors$material3(ColorScheme colorScheme) {
        IconToggleButtonColors defaultOutlinedIconToggleButtonVibrantColorsCached = colorScheme.getDefaultOutlinedIconToggleButtonVibrantColorsCached();
        if (defaultOutlinedIconToggleButtonVibrantColorsCached != null) {
            return defaultOutlinedIconToggleButtonVibrantColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3169getTransparent0d7_KjU = companion.m3169getTransparent0d7_KjU();
        OutlinedIconButtonTokens outlinedIconButtonTokens = OutlinedIconButtonTokens.INSTANCE;
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(jM3169getTransparent0d7_KjU, ColorSchemeKt.fromToken(colorScheme, outlinedIconButtonTokens.getUnselectedColor()), companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedIconButtonTokens.getDisabledColor()), outlinedIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedIconButtonTokens.getSelectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, outlinedIconButtonTokens.getSelectedColor()), null);
        colorScheme.setDefaultOutlinedIconToggleButtonColorsCached$material3(iconToggleButtonColors);
        return iconToggleButtonColors;
    }

    public final IconButtonColors filledIconButtonColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-958304265, i, -1, "androidx.compose.material3.IconButtonDefaults.filledIconButtonColors (IconButtonDefaults.kt:300)");
        }
        IconButtonColors defaultFilledIconButtonColors$material3 = getDefaultFilledIconButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultFilledIconButtonColors$material3;
    }

    /* JADX INFO: renamed from: filledIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m514filledIconButtonColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            j = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if ((i2 & 2) != 0) {
            j2 = ColorSchemeKt.m278contentColorForek8zF_U(j, composer, i & 14);
        }
        if ((i2 & 4) != 0) {
            j3 = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if ((i2 & 8) != 0) {
            j4 = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-245481051, i, -1, "androidx.compose.material3.IconButtonDefaults.filledIconButtonColors (IconButtonDefaults.kt:317)");
        }
        long j5 = j2;
        long j6 = j;
        IconButtonColors iconButtonColorsM504copyjRlVdoo = getDefaultFilledIconButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m504copyjRlVdoo(j6, j5, j3, j4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconButtonColorsM504copyjRlVdoo;
    }

    public final IconToggleButtonColors filledIconToggleButtonColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1455160689, i, -1, "androidx.compose.material3.IconButtonDefaults.filledIconToggleButtonColors (IconButtonDefaults.kt:346)");
        }
        IconToggleButtonColors defaultFilledIconToggleButtonColors$material3 = getDefaultFilledIconToggleButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultFilledIconToggleButtonColors$material3;
    }

    /* JADX INFO: renamed from: filledIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m515filledIconToggleButtonColors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM278contentColorForek8zF_U = (i2 & 32) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(jM3170getUnspecified0d7_KjU5, composer, (i >> 12) & 14) : j6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1473292947, i, -1, "androidx.compose.material3.IconButtonDefaults.filledIconToggleButtonColors (IconButtonDefaults.kt:370)");
        }
        IconToggleButtonColors iconToggleButtonColorsM542copytNS2XkQ = getDefaultFilledIconToggleButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m542copytNS2XkQ(jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM278contentColorForek8zF_U);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconToggleButtonColorsM542copytNS2XkQ;
    }

    public final IconButtonColors filledTonalIconButtonColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1145002745, i, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconButtonColors (IconButtonDefaults.kt:407)");
        }
        IconButtonColors defaultFilledTonalIconButtonColors$material3 = getDefaultFilledTonalIconButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultFilledTonalIconButtonColors$material3;
    }

    /* JADX INFO: renamed from: filledTonalIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m516filledTonalIconButtonColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            j = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if ((i2 & 2) != 0) {
            j2 = ColorSchemeKt.m278contentColorForek8zF_U(j, composer, i & 14);
        }
        if ((i2 & 4) != 0) {
            j3 = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if ((i2 & 8) != 0) {
            j4 = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(562762851, i, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconButtonColors (IconButtonDefaults.kt:425)");
        }
        long j5 = j2;
        long j6 = j;
        IconButtonColors iconButtonColorsM504copyjRlVdoo = getDefaultFilledTonalIconButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m504copyjRlVdoo(j6, j5, j3, j4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconButtonColorsM504copyjRlVdoo;
    }

    public final IconToggleButtonColors filledTonalIconToggleButtonColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(656374417, i, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconToggleButtonColors (IconButtonDefaults.kt:454)");
        }
        IconToggleButtonColors defaultFilledTonalIconToggleButtonColors$material3 = getDefaultFilledTonalIconToggleButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultFilledTonalIconToggleButtonColors$material3;
    }

    /* JADX INFO: renamed from: filledTonalIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m517filledTonalIconToggleButtonColors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM278contentColorForek8zF_U = (i2 & 2) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(jM3170getUnspecified0d7_KjU, composer, i & 14) : j2;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM278contentColorForek8zF_U2 = (i2 & 32) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(jM3170getUnspecified0d7_KjU4, composer, (i >> 12) & 14) : j6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2130748241, i, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconToggleButtonColors (IconButtonDefaults.kt:476)");
        }
        IconToggleButtonColors iconToggleButtonColorsM542copytNS2XkQ = getDefaultFilledTonalIconToggleButtonColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m542copytNS2XkQ(jM3170getUnspecified0d7_KjU, jM278contentColorForek8zF_U, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM278contentColorForek8zF_U2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconToggleButtonColorsM542copytNS2XkQ;
    }

    public final IconButtonColors getDefaultFilledIconButtonColors$material3(ColorScheme colorScheme) {
        IconButtonColors defaultFilledIconButtonColorsCached = colorScheme.getDefaultFilledIconButtonColorsCached();
        if (defaultFilledIconButtonColorsCached != null) {
            return defaultFilledIconButtonColorsCached;
        }
        FilledIconButtonTokens filledIconButtonTokens = FilledIconButtonTokens.INSTANCE;
        IconButtonColors iconButtonColors = new IconButtonColors(ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getDisabledContainerColor()), filledIconButtonTokens.getDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getDisabledColor()), filledIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultFilledIconButtonColorsCached$material3(iconButtonColors);
        return iconButtonColors;
    }

    public final IconToggleButtonColors getDefaultFilledIconToggleButtonColors$material3(ColorScheme colorScheme) {
        IconToggleButtonColors defaultFilledIconToggleButtonColorsCached = colorScheme.getDefaultFilledIconToggleButtonColorsCached();
        if (defaultFilledIconToggleButtonColorsCached != null) {
            return defaultFilledIconToggleButtonColorsCached;
        }
        FilledIconButtonTokens filledIconButtonTokens = FilledIconButtonTokens.INSTANCE;
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getUnselectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getUnselectedColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getDisabledContainerColor()), filledIconButtonTokens.getDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getDisabledColor()), filledIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getSelectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledIconButtonTokens.getSelectedColor()), null);
        colorScheme.setDefaultFilledIconToggleButtonColorsCached$material3(iconToggleButtonColors);
        return iconToggleButtonColors;
    }

    public final IconButtonColors getDefaultFilledTonalIconButtonColors$material3(ColorScheme colorScheme) {
        IconButtonColors defaultFilledTonalIconButtonColorsCached = colorScheme.getDefaultFilledTonalIconButtonColorsCached();
        if (defaultFilledTonalIconButtonColorsCached != null) {
            return defaultFilledTonalIconButtonColorsCached;
        }
        FilledTonalIconButtonTokens filledTonalIconButtonTokens = FilledTonalIconButtonTokens.INSTANCE;
        IconButtonColors iconButtonColors = new IconButtonColors(ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getDisabledContainerColor()), filledTonalIconButtonTokens.getDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getDisabledColor()), filledTonalIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultFilledTonalIconButtonColorsCached$material3(iconButtonColors);
        return iconButtonColors;
    }

    public final IconToggleButtonColors getDefaultFilledTonalIconToggleButtonColors$material3(ColorScheme colorScheme) {
        IconToggleButtonColors defaultFilledTonalIconToggleButtonColorsCached = colorScheme.getDefaultFilledTonalIconToggleButtonColorsCached();
        if (defaultFilledTonalIconToggleButtonColorsCached != null) {
            return defaultFilledTonalIconToggleButtonColorsCached;
        }
        FilledTonalIconButtonTokens filledTonalIconButtonTokens = FilledTonalIconButtonTokens.INSTANCE;
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getUnselectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getUnselectedColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getDisabledContainerColor()), filledTonalIconButtonTokens.getDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getDisabledColor()), filledTonalIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getSelectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTonalIconButtonTokens.getSelectedColor()), null);
        colorScheme.setDefaultFilledTonalIconToggleButtonColorsCached$material3(iconToggleButtonColors);
        return iconToggleButtonColors;
    }

    public final Shape getFilledShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1542796069, i, -1, "androidx.compose.material3.IconButtonDefaults.<get-filledShape> (IconButtonDefaults.kt:853)");
        }
        Shape value = ShapesKt.getValue(SmallIconButtonTokens.INSTANCE.getContainerShapeRound(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final Shape getOutlinedShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1706356635, i, -1, "androidx.compose.material3.IconButtonDefaults.<get-outlinedShape> (IconButtonDefaults.kt:857)");
        }
        Shape value = ShapesKt.getValue(SmallIconButtonTokens.INSTANCE.getContainerShapeRound(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final Shape getStandardShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-377108005, i, -1, "androidx.compose.material3.IconButtonDefaults.<get-standardShape> (IconButtonDefaults.kt:849)");
        }
        Shape value = ShapesKt.getValue(SmallIconButtonTokens.INSTANCE.getContainerShapeRound(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final IconButtonColors iconButtonColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1037266503, i, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonColors (IconButtonDefaults.kt:42)");
        }
        long jM3144unboximpl = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
        IconButtonColors iconButtonColorsM510defaultIconButtonColors4WTKRHQ$material3 = m510defaultIconButtonColors4WTKRHQ$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6), jM3144unboximpl);
        if (!Color.m3135equalsimpl0(iconButtonColorsM510defaultIconButtonColors4WTKRHQ$material3.getContentColor(), jM3144unboximpl)) {
            iconButtonColorsM510defaultIconButtonColors4WTKRHQ$material3 = IconButtonColors.m501copyjRlVdoo$default(iconButtonColorsM510defaultIconButtonColors4WTKRHQ$material3, 0L, jM3144unboximpl, 0L, Color.m3133copywmQWz5c$default(jM3144unboximpl, StandardIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), 5, null);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconButtonColorsM510defaultIconButtonColors4WTKRHQ$material3;
    }

    /* JADX INFO: renamed from: iconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m518iconButtonColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            j = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        long jM3144unboximpl = (i2 & 2) != 0 ? ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl() : j2;
        long jM3170getUnspecified0d7_KjU = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3133copywmQWz5c$default = (i2 & 8) != 0 ? Color.m3133copywmQWz5c$default(jM3144unboximpl, StandardIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j4;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1639168605, i, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonColors (IconButtonDefaults.kt:78)");
        }
        IconButtonColors iconButtonColorsM504copyjRlVdoo = m510defaultIconButtonColors4WTKRHQ$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6), ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()).m504copyjRlVdoo(j, jM3144unboximpl, jM3170getUnspecified0d7_KjU, jM3133copywmQWz5c$default);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconButtonColorsM504copyjRlVdoo;
    }

    public final IconButtonColors iconButtonVibrantColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(111454247, i, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonVibrantColors (IconButtonDefaults.kt:110)");
        }
        IconButtonColors iconButtonColorsDefaultIconButtonVibrantColors$material3 = defaultIconButtonVibrantColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconButtonColorsDefaultIconButtonVibrantColors$material3;
    }

    /* JADX INFO: renamed from: iconButtonVibrantColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m519iconButtonVibrantColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            j = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        long jM3170getUnspecified0d7_KjU = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3133copywmQWz5c$default = (i2 & 8) != 0 ? Color.m3133copywmQWz5c$default(jM3170getUnspecified0d7_KjU, StandardIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j4;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1036440437, i, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonVibrantColors (IconButtonDefaults.kt:132)");
        }
        IconButtonColors iconButtonColorsM504copyjRlVdoo = defaultIconButtonVibrantColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m504copyjRlVdoo(j, jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3133copywmQWz5c$default);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconButtonColorsM504copyjRlVdoo;
    }

    public final IconToggleButtonColors iconToggleButtonColors(Composer composer, int i) {
        composer.startReplaceGroup(-1355771567);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1355771567, i, -1, "androidx.compose.material3.IconButtonDefaults.iconToggleButtonColors (IconButtonDefaults.kt:164)");
        }
        long jM3144unboximpl = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
        IconToggleButtonColors iconToggleButtonColorsM511defaultIconToggleButtonColors4WTKRHQ$material3 = m511defaultIconToggleButtonColors4WTKRHQ$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6), jM3144unboximpl);
        if (Color.m3135equalsimpl0(iconToggleButtonColorsM511defaultIconToggleButtonColors4WTKRHQ$material3.getContentColor(), jM3144unboximpl)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return iconToggleButtonColorsM511defaultIconToggleButtonColors4WTKRHQ$material3;
        }
        IconToggleButtonColors iconToggleButtonColorsM541copytNS2XkQ$default = IconToggleButtonColors.m541copytNS2XkQ$default(iconToggleButtonColorsM511defaultIconToggleButtonColors4WTKRHQ$material3, 0L, jM3144unboximpl, 0L, Color.m3133copywmQWz5c$default(jM3144unboximpl, StandardIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), 0L, 0L, 53, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return iconToggleButtonColorsM541copytNS2XkQ$default;
    }

    /* JADX INFO: renamed from: iconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m520iconToggleButtonColors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer composer, int i, int i2) {
        long j7;
        long jM3133copywmQWz5c$default;
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3144unboximpl = (i2 & 2) != 0 ? ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl() : j2;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        if ((i2 & 8) != 0) {
            long j8 = jM3144unboximpl;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(j8, StandardIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null);
            j7 = j8;
        } else {
            j7 = jM3144unboximpl;
            jM3133copywmQWz5c$default = j4;
        }
        long jM3170getUnspecified0d7_KjU3 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1402082449, i, -1, "androidx.compose.material3.IconButtonDefaults.iconToggleButtonColors (IconButtonDefaults.kt:203)");
        }
        IconToggleButtonColors iconToggleButtonColorsM542copytNS2XkQ = m511defaultIconToggleButtonColors4WTKRHQ$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6), ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()).m542copytNS2XkQ(jM3170getUnspecified0d7_KjU, j7, jM3170getUnspecified0d7_KjU2, jM3133copywmQWz5c$default, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconToggleButtonColorsM542copytNS2XkQ;
    }

    public final IconToggleButtonColors iconToggleButtonVibrantColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1755001127, i, -1, "androidx.compose.material3.IconButtonDefaults.iconToggleButtonVibrantColors (IconButtonDefaults.kt:241)");
        }
        IconToggleButtonColors iconToggleButtonColorsDefaultIconToggleButtonVibrantColors$material3 = defaultIconToggleButtonVibrantColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconToggleButtonColorsDefaultIconToggleButtonVibrantColors$material3;
    }

    /* JADX INFO: renamed from: iconToggleButtonVibrantColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m521iconToggleButtonVibrantColors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer composer, int i, int i2) {
        long j7;
        long jM3133copywmQWz5c$default;
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        if ((i2 & 8) != 0) {
            long j8 = jM3170getUnspecified0d7_KjU2;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(j8, StandardIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null);
            j7 = j8;
        } else {
            j7 = jM3170getUnspecified0d7_KjU2;
            jM3133copywmQWz5c$default = j4;
        }
        long jM3170getUnspecified0d7_KjU4 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1027328773, i, -1, "androidx.compose.material3.IconButtonDefaults.iconToggleButtonVibrantColors (IconButtonDefaults.kt:267)");
        }
        IconToggleButtonColors iconToggleButtonColorsM542copytNS2XkQ = defaultIconToggleButtonVibrantColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m542copytNS2XkQ(jM3170getUnspecified0d7_KjU, j7, jM3170getUnspecified0d7_KjU3, jM3133copywmQWz5c$default, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconToggleButtonColorsM542copytNS2XkQ;
    }

    public final BorderStroke outlinedIconButtonBorder(boolean z, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1270640488, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonBorder (IconButtonDefaults.kt:818)");
        }
        long jM3144unboximpl = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
        if (!z) {
            jM3144unboximpl = Color.m3133copywmQWz5c$default(jM3144unboximpl, OutlinedIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null);
        }
        boolean zChanged = composer.changed(jM3144unboximpl);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = BorderStrokeKt.m12BorderStrokecXLIe8U(SmallIconButtonTokens.INSTANCE.m2122getOutlinedOutlineWidthD9Ej5fM(), jM3144unboximpl);
            composer.updateRememberedValue(objRememberedValue);
        }
        BorderStroke borderStroke = (BorderStroke) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return borderStroke;
    }

    public final IconButtonColors outlinedIconButtonColors(Composer composer, int i) {
        composer.startReplaceGroup(1591384183);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1591384183, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonColors (IconButtonDefaults.kt:513)");
        }
        long jM3144unboximpl = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
        IconButtonColors iconButtonColorsM512defaultOutlinedIconButtonColors4WTKRHQ$material3 = m512defaultOutlinedIconButtonColors4WTKRHQ$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6), jM3144unboximpl);
        if (Color.m3135equalsimpl0(iconButtonColorsM512defaultOutlinedIconButtonColors4WTKRHQ$material3.getContentColor(), jM3144unboximpl)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return iconButtonColorsM512defaultOutlinedIconButtonColors4WTKRHQ$material3;
        }
        IconButtonColors iconButtonColorsM501copyjRlVdoo$default = IconButtonColors.m501copyjRlVdoo$default(iconButtonColorsM512defaultOutlinedIconButtonColors4WTKRHQ$material3, 0L, jM3144unboximpl, 0L, Color.m3133copywmQWz5c$default(jM3144unboximpl, OutlinedIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), 5, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return iconButtonColorsM501copyjRlVdoo$default;
    }

    /* JADX INFO: renamed from: outlinedIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m522outlinedIconButtonColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            j = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        long jM3144unboximpl = (i2 & 2) != 0 ? ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl() : j2;
        long jM3170getUnspecified0d7_KjU = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3133copywmQWz5c$default = (i2 & 8) != 0 ? Color.m3133copywmQWz5c$default(jM3144unboximpl, OutlinedIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j4;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1335916251, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonColors (IconButtonDefaults.kt:547)");
        }
        IconButtonColors iconButtonColorsM504copyjRlVdoo = m512defaultOutlinedIconButtonColors4WTKRHQ$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6), ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()).m504copyjRlVdoo(j, jM3144unboximpl, jM3170getUnspecified0d7_KjU, jM3133copywmQWz5c$default);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconButtonColorsM504copyjRlVdoo;
    }

    public final BorderStroke outlinedIconButtonVibrantBorder(boolean z, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2139728858, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonVibrantBorder (IconButtonDefaults.kt:836)");
        }
        OutlinedIconButtonTokens outlinedIconButtonTokens = OutlinedIconButtonTokens.INSTANCE;
        long value = ColorSchemeKt.getValue(outlinedIconButtonTokens.getOutlineColor(), composer, 6);
        if (!z) {
            value = Color.m3133copywmQWz5c$default(value, outlinedIconButtonTokens.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null);
        }
        boolean zChanged = composer.changed(value);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = BorderStrokeKt.m12BorderStrokecXLIe8U(SmallIconButtonTokens.INSTANCE.m2122getOutlinedOutlineWidthD9Ej5fM(), value);
            composer.updateRememberedValue(objRememberedValue);
        }
        BorderStroke borderStroke = (BorderStroke) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return borderStroke;
    }

    public final IconButtonColors outlinedIconButtonVibrantColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-899469399, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonVibrantColors (IconButtonDefaults.kt:581)");
        }
        IconButtonColors iconButtonColorsDefaultOutlinedIconButtonVibrantColors$material3 = defaultOutlinedIconButtonVibrantColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconButtonColorsDefaultOutlinedIconButtonVibrantColors$material3;
    }

    /* JADX INFO: renamed from: outlinedIconButtonVibrantColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m523outlinedIconButtonVibrantColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            j = Color.INSTANCE.m3170getUnspecified0d7_KjU();
        }
        long jM3170getUnspecified0d7_KjU = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3133copywmQWz5c$default = (i2 & 8) != 0 ? Color.m3133copywmQWz5c$default(jM3170getUnspecified0d7_KjU, OutlinedIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j4;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-278201933, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonVibrantColors (IconButtonDefaults.kt:603)");
        }
        IconButtonColors iconButtonColorsM504copyjRlVdoo = defaultOutlinedIconButtonVibrantColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m504copyjRlVdoo(j, jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3133copywmQWz5c$default);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconButtonColorsM504copyjRlVdoo;
    }

    public final BorderStroke outlinedIconToggleButtonBorder(boolean z, boolean z2, Composer composer, int i) {
        composer.startReplaceGroup(1933433512);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1933433512, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonBorder (IconButtonDefaults.kt:786)");
        }
        if (z2) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return null;
        }
        BorderStroke borderStrokeOutlinedIconButtonBorder = outlinedIconButtonBorder(z, composer, (i & 14) | ((i >> 3) & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return borderStrokeOutlinedIconButtonBorder;
    }

    public final IconToggleButtonColors outlinedIconToggleButtonColors(Composer composer, int i) {
        composer.startReplaceGroup(-834376945);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-834376945, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonColors (IconButtonDefaults.kt:636)");
        }
        long jM3144unboximpl = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
        IconToggleButtonColors iconToggleButtonColorsM513defaultOutlinedIconToggleButtonColors4WTKRHQ$material3 = m513defaultOutlinedIconToggleButtonColors4WTKRHQ$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6), jM3144unboximpl);
        if (Color.m3135equalsimpl0(iconToggleButtonColorsM513defaultOutlinedIconToggleButtonColors4WTKRHQ$material3.getContentColor(), jM3144unboximpl)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return iconToggleButtonColorsM513defaultOutlinedIconToggleButtonColors4WTKRHQ$material3;
        }
        IconToggleButtonColors iconToggleButtonColorsM541copytNS2XkQ$default = IconToggleButtonColors.m541copytNS2XkQ$default(iconToggleButtonColorsM513defaultOutlinedIconToggleButtonColors4WTKRHQ$material3, 0L, jM3144unboximpl, 0L, Color.m3133copywmQWz5c$default(jM3144unboximpl, OutlinedIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null), 0L, 0L, 53, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return iconToggleButtonColorsM541copytNS2XkQ$default;
    }

    /* JADX INFO: renamed from: outlinedIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m524outlinedIconToggleButtonColors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer composer, int i, int i2) {
        long j7;
        long jM3133copywmQWz5c$default;
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3144unboximpl = (i2 & 2) != 0 ? ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl() : j2;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        if ((i2 & 8) != 0) {
            long j8 = jM3144unboximpl;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(j8, OutlinedIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null);
            j7 = j8;
        } else {
            j7 = jM3144unboximpl;
            jM3133copywmQWz5c$default = j4;
        }
        long jM3170getUnspecified0d7_KjU3 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM278contentColorForek8zF_U = (i2 & 32) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(jM3170getUnspecified0d7_KjU3, composer, (i >> 12) & 14) : j6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-514625005, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonColors (IconButtonDefaults.kt:675)");
        }
        IconToggleButtonColors iconToggleButtonColorsM542copytNS2XkQ = m513defaultOutlinedIconToggleButtonColors4WTKRHQ$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6), ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl()).m542copytNS2XkQ(jM3170getUnspecified0d7_KjU, j7, jM3170getUnspecified0d7_KjU2, jM3133copywmQWz5c$default, jM3170getUnspecified0d7_KjU3, jM278contentColorForek8zF_U);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconToggleButtonColorsM542copytNS2XkQ;
    }

    public final BorderStroke outlinedIconToggleButtonVibrantBorder(boolean z, boolean z2, Composer composer, int i) {
        composer.startReplaceGroup(394022990);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(394022990, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonVibrantBorder (IconButtonDefaults.kt:801)");
        }
        if (z2) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return null;
        }
        BorderStroke borderStrokeOutlinedIconButtonVibrantBorder = outlinedIconButtonVibrantBorder(z, composer, (i & 14) | ((i >> 3) & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return borderStrokeOutlinedIconButtonVibrantBorder;
    }

    public final IconToggleButtonColors outlinedIconToggleButtonVibrantColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1236236887, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonVibrantColors (IconButtonDefaults.kt:719)");
        }
        IconToggleButtonColors iconToggleButtonColorsDefaultOutlinedIconToggleButtonVibrantColors$material3 = defaultOutlinedIconToggleButtonVibrantColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconToggleButtonColorsDefaultOutlinedIconToggleButtonVibrantColors$material3;
    }

    /* JADX INFO: renamed from: outlinedIconToggleButtonVibrantColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m525outlinedIconToggleButtonVibrantColors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer composer, int i, int i2) {
        long j7;
        long jM3133copywmQWz5c$default;
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        if ((i2 & 8) != 0) {
            long j8 = jM3170getUnspecified0d7_KjU2;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(j8, OutlinedIconButtonTokens.INSTANCE.getDisabledOpacity(), 0.0f, 0.0f, 0.0f, 14, null);
            j7 = j8;
        } else {
            j7 = jM3170getUnspecified0d7_KjU2;
            jM3133copywmQWz5c$default = j4;
        }
        long jM3170getUnspecified0d7_KjU4 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM278contentColorForek8zF_U = (i2 & 32) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(jM3170getUnspecified0d7_KjU4, composer, (i >> 12) & 14) : j6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-142016199, i, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonVibrantColors (IconButtonDefaults.kt:745)");
        }
        IconToggleButtonColors iconToggleButtonColorsM542copytNS2XkQ = defaultOutlinedIconToggleButtonVibrantColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m542copytNS2XkQ(jM3170getUnspecified0d7_KjU, j7, jM3170getUnspecified0d7_KjU3, jM3133copywmQWz5c$default, jM3170getUnspecified0d7_KjU4, jM278contentColorForek8zF_U);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iconToggleButtonColorsM542copytNS2XkQ;
    }

    /* JADX INFO: renamed from: smallContainerSize-N-wlBFI$material3, reason: not valid java name */
    public final long m526smallContainerSizeNwlBFI$material3(int widthOption) {
        float fM6022constructorimpl;
        IconButtonWidthOption.Companion companion = IconButtonWidthOption.INSTANCE;
        if (IconButtonWidthOption.m530equalsimpl0(widthOption, companion.m534getNarrowrc6NtMs())) {
            SmallIconButtonTokens smallIconButtonTokens = SmallIconButtonTokens.INSTANCE;
            fM6022constructorimpl = Dp.m6022constructorimpl(smallIconButtonTokens.m2120getNarrowLeadingSpaceD9Ej5fM() + smallIconButtonTokens.m2121getNarrowTrailingSpaceD9Ej5fM());
        } else if (IconButtonWidthOption.m530equalsimpl0(widthOption, companion.m535getUniformrc6NtMs())) {
            SmallIconButtonTokens smallIconButtonTokens2 = SmallIconButtonTokens.INSTANCE;
            fM6022constructorimpl = Dp.m6022constructorimpl(smallIconButtonTokens2.m2117getDefaultLeadingSpaceD9Ej5fM() + smallIconButtonTokens2.m2117getDefaultLeadingSpaceD9Ej5fM());
        } else if (IconButtonWidthOption.m530equalsimpl0(widthOption, companion.m536getWiderc6NtMs())) {
            SmallIconButtonTokens smallIconButtonTokens3 = SmallIconButtonTokens.INSTANCE;
            fM6022constructorimpl = Dp.m6022constructorimpl(smallIconButtonTokens3.m2123getWideLeadingSpaceD9Ej5fM() + smallIconButtonTokens3.m2124getWideTrailingSpaceD9Ej5fM());
        } else {
            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
        }
        SmallIconButtonTokens smallIconButtonTokens4 = SmallIconButtonTokens.INSTANCE;
        return DpKt.m6044DpSizeYgX7TsA(Dp.m6022constructorimpl(smallIconButtonTokens4.m2119getIconSizeD9Ej5fM() + fM6022constructorimpl), smallIconButtonTokens4.m2116getContainerHeightD9Ej5fM());
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/material3/IconButtonDefaults$IconButtonWidthOption;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @JvmInline
    public static final class IconButtonWidthOption {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final int Narrow = m528constructorimpl(0);
        private static final int Uniform = m528constructorimpl(1);
        private static final int Wide = m528constructorimpl(2);
        private final int value;

        private /* synthetic */ IconButtonWidthOption(int i) {
            this.value = i;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ IconButtonWidthOption m527boximpl(int i) {
            return new IconButtonWidthOption(i);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        private static int m528constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m529equalsimpl(int i, Object obj) {
            return (obj instanceof IconButtonWidthOption) && i == ((IconButtonWidthOption) obj).getValue();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m530equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m531hashCodeimpl(int i) {
            return Integer.hashCode(i);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m532toStringimpl(int i) {
            if (m530equalsimpl0(i, Narrow)) {
                return "Narrow";
            }
            if (m530equalsimpl0(i, Uniform)) {
                return "Uniform";
            }
            return m530equalsimpl0(i, Wide) ? "Wide" : "Unknown";
        }

        public boolean equals(Object other) {
            return m529equalsimpl(this.value, other);
        }

        public int hashCode() {
            return m531hashCodeimpl(this.value);
        }

        public String toString() {
            return m532toStringimpl(this.value);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ int getValue() {
            return this.value;
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/material3/IconButtonDefaults$IconButtonWidthOption$Companion;", "", "<init>", "()V", "Narrow", "Landroidx/compose/material3/IconButtonDefaults$IconButtonWidthOption;", "getNarrow-rc6NtMs", "()I", "I", "Uniform", "getUniform-rc6NtMs", "Wide", "getWide-rc6NtMs", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX INFO: renamed from: getNarrow-rc6NtMs, reason: not valid java name */
            public final int m534getNarrowrc6NtMs() {
                return IconButtonWidthOption.Narrow;
            }

            /* JADX INFO: renamed from: getUniform-rc6NtMs, reason: not valid java name */
            public final int m535getUniformrc6NtMs() {
                return IconButtonWidthOption.Uniform;
            }

            /* JADX INFO: renamed from: getWide-rc6NtMs, reason: not valid java name */
            public final int m536getWiderc6NtMs() {
                return IconButtonWidthOption.Wide;
            }

            private Companion() {
            }
        }
    }
}
