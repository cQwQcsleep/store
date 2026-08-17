package androidx.compose.material3;

import android.content.Context;
import android.os.Build;
import androidx.compose.material3.internal.colorUtil.Cam;
import androidx.compose.material3.internal.colorUtil.CamUtils;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001d\u0010\u0007\u001a\u00020\b*\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¨\u0006\u0012"}, d2 = {"dynamicTonalPalette", "Landroidx/compose/material3/TonalPalette;", "context", "Landroid/content/Context;", "dynamicLightColorScheme", "Landroidx/compose/material3/ColorScheme;", "dynamicDarkColorScheme", "setLuminance", "Landroidx/compose/ui/graphics/Color;", "newLuminance", "", "setLuminance-DxMtmZc", "(JF)J", "dynamicLightColorScheme31", "tonalPalette", "dynamicLightColorScheme34", "dynamicDarkColorScheme31", "dynamicDarkColorScheme34", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DynamicTonalPaletteKt {
    public static final ColorScheme dynamicDarkColorScheme(Context context) {
        return Build.VERSION.SDK_INT >= 34 ? dynamicDarkColorScheme34(context) : dynamicDarkColorScheme31(dynamicTonalPalette(context));
    }

    public static final ColorScheme dynamicDarkColorScheme31(TonalPalette tonalPalette) {
        long primary80 = tonalPalette.getPrimary80();
        long primary20 = tonalPalette.getPrimary20();
        long primary30 = tonalPalette.getPrimary30();
        long primary90 = tonalPalette.getPrimary90();
        long primary40 = tonalPalette.getPrimary40();
        long secondary80 = tonalPalette.getSecondary80();
        long secondary20 = tonalPalette.getSecondary20();
        long secondary30 = tonalPalette.getSecondary30();
        long secondary90 = tonalPalette.getSecondary90();
        long tertiary80 = tonalPalette.getTertiary80();
        long tertiary20 = tonalPalette.getTertiary20();
        long tertiary30 = tonalPalette.getTertiary30();
        long tertiary90 = tonalPalette.getTertiary90();
        long neutralVariant6 = tonalPalette.getNeutralVariant6();
        long neutralVariant90 = tonalPalette.getNeutralVariant90();
        long neutralVariant7 = tonalPalette.getNeutralVariant6();
        long neutralVariant91 = tonalPalette.getNeutralVariant90();
        long neutralVariant30 = tonalPalette.getNeutralVariant30();
        long neutralVariant80 = tonalPalette.getNeutralVariant80();
        long neutralVariant92 = tonalPalette.getNeutralVariant90();
        long neutralVariant20 = tonalPalette.getNeutralVariant20();
        long neutralVariant60 = tonalPalette.getNeutralVariant60();
        long neutralVariant31 = tonalPalette.getNeutralVariant30();
        long neutralVariant0 = tonalPalette.getNeutralVariant0();
        long neutralVariant24 = tonalPalette.getNeutralVariant24();
        long neutralVariant8 = tonalPalette.getNeutralVariant6();
        return ColorSchemeKt.m284darkColorScheme_VG5OTI$default(primary80, primary20, primary30, primary90, primary40, secondary80, secondary20, secondary30, secondary90, tertiary80, tertiary20, tertiary30, tertiary90, neutralVariant6, neutralVariant90, neutralVariant7, neutralVariant91, neutralVariant30, neutralVariant80, tonalPalette.getPrimary80(), neutralVariant92, neutralVariant20, 0L, 0L, 0L, 0L, neutralVariant60, neutralVariant31, neutralVariant0, neutralVariant24, tonalPalette.getNeutralVariant12(), tonalPalette.getNeutralVariant17(), tonalPalette.getNeutralVariant22(), tonalPalette.getNeutralVariant10(), tonalPalette.getNeutralVariant4(), neutralVariant8, tonalPalette.getPrimary90(), tonalPalette.getPrimary80(), tonalPalette.getPrimary10(), tonalPalette.getPrimary30(), tonalPalette.getSecondary90(), tonalPalette.getSecondary80(), tonalPalette.getSecondary10(), tonalPalette.getSecondary30(), tonalPalette.getTertiary90(), tonalPalette.getTertiary80(), tonalPalette.getTertiary10(), tonalPalette.getTertiary30(), 62914560, 0, null);
    }

    public static final ColorScheme dynamicDarkColorScheme34(Context context) {
        ColorResourceHelper colorResourceHelper = ColorResourceHelper.INSTANCE;
        long jM221getColorWaAFU9c = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_dark);
        long jM221getColorWaAFU9c2 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_primary_dark);
        long jM221getColorWaAFU9c3 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_container_dark);
        long jM221getColorWaAFU9c4 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_primary_container_dark);
        long jM221getColorWaAFU9c5 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_light);
        long jM221getColorWaAFU9c6 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_secondary_dark);
        long jM221getColorWaAFU9c7 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_secondary_dark);
        long jM221getColorWaAFU9c8 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_secondary_container_dark);
        long jM221getColorWaAFU9c9 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_secondary_container_dark);
        long jM221getColorWaAFU9c10 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_tertiary_dark);
        long jM221getColorWaAFU9c11 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_tertiary_dark);
        long jM221getColorWaAFU9c12 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_tertiary_container_dark);
        long jM221getColorWaAFU9c13 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_tertiary_container_dark);
        long jM221getColorWaAFU9c14 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_background_dark);
        long jM221getColorWaAFU9c15 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_background_dark);
        long jM221getColorWaAFU9c16 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_dark);
        long jM221getColorWaAFU9c17 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_surface_dark);
        long jM221getColorWaAFU9c18 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_variant_dark);
        long jM221getColorWaAFU9c19 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_surface_variant_dark);
        long jM221getColorWaAFU9c20 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_light);
        long jM221getColorWaAFU9c21 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_surface_light);
        long jM221getColorWaAFU9c22 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_outline_dark);
        long jM221getColorWaAFU9c23 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_outline_variant_dark);
        long jM221getColorWaAFU9c24 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_bright_dark);
        long jM221getColorWaAFU9c25 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_dim_dark);
        return ColorSchemeKt.m284darkColorScheme_VG5OTI$default(jM221getColorWaAFU9c, jM221getColorWaAFU9c2, jM221getColorWaAFU9c3, jM221getColorWaAFU9c4, jM221getColorWaAFU9c5, jM221getColorWaAFU9c6, jM221getColorWaAFU9c7, jM221getColorWaAFU9c8, jM221getColorWaAFU9c9, jM221getColorWaAFU9c10, jM221getColorWaAFU9c11, jM221getColorWaAFU9c12, jM221getColorWaAFU9c13, jM221getColorWaAFU9c14, jM221getColorWaAFU9c15, jM221getColorWaAFU9c16, jM221getColorWaAFU9c17, jM221getColorWaAFU9c18, jM221getColorWaAFU9c19, colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_dark), jM221getColorWaAFU9c20, jM221getColorWaAFU9c21, 0L, 0L, 0L, 0L, jM221getColorWaAFU9c22, jM221getColorWaAFU9c23, 0L, jM221getColorWaAFU9c24, colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_dark), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_high_dark), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_highest_dark), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_low_dark), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_lowest_dark), jM221getColorWaAFU9c25, colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_fixed_dim), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_primary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_primary_fixed_variant), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_secondary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_secondary_fixed_dim), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_secondary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_secondary_fixed_variant), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_tertiary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_tertiary_fixed_dim), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_tertiary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_tertiary_fixed_variant), 331350016, 0, null);
    }

    public static final ColorScheme dynamicLightColorScheme(Context context) {
        return Build.VERSION.SDK_INT >= 34 ? dynamicLightColorScheme34(context) : dynamicLightColorScheme31(dynamicTonalPalette(context));
    }

    public static final ColorScheme dynamicLightColorScheme31(TonalPalette tonalPalette) {
        long primary40 = tonalPalette.getPrimary40();
        long primary100 = tonalPalette.getPrimary100();
        long primary90 = tonalPalette.getPrimary90();
        long primary10 = tonalPalette.getPrimary10();
        long primary80 = tonalPalette.getPrimary80();
        long secondary40 = tonalPalette.getSecondary40();
        long secondary100 = tonalPalette.getSecondary100();
        long secondary90 = tonalPalette.getSecondary90();
        long secondary10 = tonalPalette.getSecondary10();
        long tertiary40 = tonalPalette.getTertiary40();
        long tertiary100 = tonalPalette.getTertiary100();
        long tertiary90 = tonalPalette.getTertiary90();
        long tertiary10 = tonalPalette.getTertiary10();
        long neutralVariant98 = tonalPalette.getNeutralVariant98();
        long neutralVariant10 = tonalPalette.getNeutralVariant10();
        long neutralVariant99 = tonalPalette.getNeutralVariant98();
        long neutralVariant11 = tonalPalette.getNeutralVariant10();
        long neutralVariant90 = tonalPalette.getNeutralVariant90();
        long neutralVariant30 = tonalPalette.getNeutralVariant30();
        long neutralVariant20 = tonalPalette.getNeutralVariant20();
        long neutralVariant95 = tonalPalette.getNeutralVariant95();
        long neutralVariant50 = tonalPalette.getNeutralVariant50();
        long neutralVariant80 = tonalPalette.getNeutralVariant80();
        long neutralVariant0 = tonalPalette.getNeutralVariant0();
        long neutralVariant910 = tonalPalette.getNeutralVariant98();
        long neutralVariant87 = tonalPalette.getNeutralVariant87();
        return ColorSchemeKt.m290lightColorScheme_VG5OTI$default(primary40, primary100, primary90, primary10, primary80, secondary40, secondary100, secondary90, secondary10, tertiary40, tertiary100, tertiary90, tertiary10, neutralVariant98, neutralVariant10, neutralVariant99, neutralVariant11, neutralVariant90, neutralVariant30, tonalPalette.getPrimary40(), neutralVariant20, neutralVariant95, 0L, 0L, 0L, 0L, neutralVariant50, neutralVariant80, neutralVariant0, neutralVariant910, tonalPalette.getNeutralVariant94(), tonalPalette.getNeutralVariant92(), tonalPalette.getNeutralVariant90(), tonalPalette.getNeutralVariant96(), tonalPalette.getNeutralVariant100(), neutralVariant87, tonalPalette.getPrimary90(), tonalPalette.getPrimary80(), tonalPalette.getPrimary10(), tonalPalette.getPrimary30(), tonalPalette.getSecondary90(), tonalPalette.getSecondary80(), tonalPalette.getSecondary10(), tonalPalette.getSecondary30(), tonalPalette.getTertiary90(), tonalPalette.getTertiary80(), tonalPalette.getTertiary10(), tonalPalette.getTertiary30(), 62914560, 0, null);
    }

    public static final ColorScheme dynamicLightColorScheme34(Context context) {
        ColorResourceHelper colorResourceHelper = ColorResourceHelper.INSTANCE;
        long jM221getColorWaAFU9c = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_light);
        long jM221getColorWaAFU9c2 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_primary_light);
        long jM221getColorWaAFU9c3 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_container_light);
        long jM221getColorWaAFU9c4 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_primary_container_light);
        long jM221getColorWaAFU9c5 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_dark);
        long jM221getColorWaAFU9c6 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_secondary_light);
        long jM221getColorWaAFU9c7 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_secondary_light);
        long jM221getColorWaAFU9c8 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_secondary_container_light);
        long jM221getColorWaAFU9c9 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_secondary_container_light);
        long jM221getColorWaAFU9c10 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_tertiary_light);
        long jM221getColorWaAFU9c11 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_tertiary_light);
        long jM221getColorWaAFU9c12 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_tertiary_container_light);
        long jM221getColorWaAFU9c13 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_tertiary_container_light);
        long jM221getColorWaAFU9c14 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_background_light);
        long jM221getColorWaAFU9c15 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_background_light);
        long jM221getColorWaAFU9c16 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_light);
        long jM221getColorWaAFU9c17 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_surface_light);
        long jM221getColorWaAFU9c18 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_variant_light);
        long jM221getColorWaAFU9c19 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_surface_variant_light);
        long jM221getColorWaAFU9c20 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_dark);
        long jM221getColorWaAFU9c21 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_surface_dark);
        long jM221getColorWaAFU9c22 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_outline_light);
        long jM221getColorWaAFU9c23 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_outline_variant_light);
        long jM221getColorWaAFU9c24 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_bright_light);
        long jM221getColorWaAFU9c25 = colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_dim_light);
        return ColorSchemeKt.m290lightColorScheme_VG5OTI$default(jM221getColorWaAFU9c, jM221getColorWaAFU9c2, jM221getColorWaAFU9c3, jM221getColorWaAFU9c4, jM221getColorWaAFU9c5, jM221getColorWaAFU9c6, jM221getColorWaAFU9c7, jM221getColorWaAFU9c8, jM221getColorWaAFU9c9, jM221getColorWaAFU9c10, jM221getColorWaAFU9c11, jM221getColorWaAFU9c12, jM221getColorWaAFU9c13, jM221getColorWaAFU9c14, jM221getColorWaAFU9c15, jM221getColorWaAFU9c16, jM221getColorWaAFU9c17, jM221getColorWaAFU9c18, jM221getColorWaAFU9c19, colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_light), jM221getColorWaAFU9c20, jM221getColorWaAFU9c21, 0L, 0L, 0L, 0L, jM221getColorWaAFU9c22, jM221getColorWaAFU9c23, 0L, jM221getColorWaAFU9c24, colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_light), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_high_light), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_highest_light), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_low_light), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_surface_container_lowest_light), jM221getColorWaAFU9c25, colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_primary_fixed_dim), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_primary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_primary_fixed_variant), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_secondary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_secondary_fixed_dim), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_secondary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_secondary_fixed_variant), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_tertiary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_tertiary_fixed_dim), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_tertiary_fixed), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_on_tertiary_fixed_variant), 331350016, 0, null);
    }

    public static final TonalPalette dynamicTonalPalette(Context context) {
        ColorResourceHelper colorResourceHelper = ColorResourceHelper.INSTANCE;
        return new TonalPalette(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_0), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_10), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 98.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 96.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_50), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 94.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 92.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_100), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 87.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_200), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_300), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_400), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_500), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_700), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 24.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 22.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_800), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 17.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 12.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_900), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 6.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_600), 4.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral1_1000), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_0), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_10), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 98.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 96.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_50), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 94.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 92.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_100), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 87.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_200), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_300), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_400), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_500), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_700), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 24.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 22.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_800), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 17.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 12.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_900), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 6.0f), m427setLuminanceDxMtmZc(colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_600), 4.0f), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_neutral2_1000), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_0), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_10), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_50), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_100), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_200), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_300), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_400), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_500), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_600), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_700), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_800), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_900), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent1_1000), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_0), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_10), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_50), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_100), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_200), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_300), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_400), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_500), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_600), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_700), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_800), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_900), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent2_1000), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_0), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_10), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_50), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_100), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_200), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_300), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_400), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_500), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_600), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_700), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_800), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_900), colorResourceHelper.m221getColorWaAFU9c(context, android.R.color.system_accent3_1000), null);
    }

    /* JADX INFO: renamed from: setLuminance-DxMtmZc, reason: not valid java name */
    public static final long m427setLuminanceDxMtmZc(long j, float f) {
        double d = f;
        if ((d < 1.0E-4d) || (d > 99.9999d)) {
            return ColorKt.Color(CamUtils.INSTANCE.argbFromLstar(d));
        }
        Cam.Companion companion = Cam.INSTANCE;
        Cam camFromInt = companion.fromInt(ColorKt.m3188toArgb8_81llA(j));
        return ColorKt.Color(companion.getInt(camFromInt.getHue(), camFromInt.getChroma(), f));
    }
}
