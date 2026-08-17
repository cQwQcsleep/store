package androidx.compose.material3.tokens;

import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b=\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007R\u0011\u0010*\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0007R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0011\u00100\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0007R\u0011\u00102\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R\u0011\u00104\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0007R\u0011\u00106\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0007R\u0011\u00108\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0007R\u0011\u0010:\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0007R\u0011\u0010<\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0007R\u0011\u0010>\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0007R\u0011\u0010@\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0007¨\u0006B"}, d2 = {"Landroidx/compose/material3/tokens/TypographyTokens;", "", "<init>", "()V", "BodyLarge", "Landroidx/compose/ui/text/TextStyle;", "getBodyLarge", "()Landroidx/compose/ui/text/TextStyle;", "BodyMedium", "getBodyMedium", "BodySmall", "getBodySmall", "DisplayLarge", "getDisplayLarge", "DisplayMedium", "getDisplayMedium", "DisplaySmall", "getDisplaySmall", "HeadlineLarge", "getHeadlineLarge", "HeadlineMedium", "getHeadlineMedium", "HeadlineSmall", "getHeadlineSmall", "LabelLarge", "getLabelLarge", "LabelMedium", "getLabelMedium", "LabelSmall", "getLabelSmall", "TitleLarge", "getTitleLarge", "TitleMedium", "getTitleMedium", "TitleSmall", "getTitleSmall", "BodyLargeEmphasized", "getBodyLargeEmphasized", "BodyMediumEmphasized", "getBodyMediumEmphasized", "BodySmallEmphasized", "getBodySmallEmphasized", "DisplayLargeEmphasized", "getDisplayLargeEmphasized", "DisplayMediumEmphasized", "getDisplayMediumEmphasized", "DisplaySmallEmphasized", "getDisplaySmallEmphasized", "HeadlineLargeEmphasized", "getHeadlineLargeEmphasized", "HeadlineMediumEmphasized", "getHeadlineMediumEmphasized", "HeadlineSmallEmphasized", "getHeadlineSmallEmphasized", "LabelLargeEmphasized", "getLabelLargeEmphasized", "LabelMediumEmphasized", "getLabelMediumEmphasized", "LabelSmallEmphasized", "getLabelSmallEmphasized", "TitleLargeEmphasized", "getTitleLargeEmphasized", "TitleMediumEmphasized", "getTitleMediumEmphasized", "TitleSmallEmphasized", "getTitleSmallEmphasized", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TypographyTokens {
    public static final int $stable = 0;
    private static final TextStyle BodyLarge;
    private static final TextStyle BodyLargeEmphasized;
    private static final TextStyle BodyMedium;
    private static final TextStyle BodyMediumEmphasized;
    private static final TextStyle BodySmall;
    private static final TextStyle BodySmallEmphasized;
    private static final TextStyle DisplayLarge;
    private static final TextStyle DisplayLargeEmphasized;
    private static final TextStyle DisplayMedium;
    private static final TextStyle DisplayMediumEmphasized;
    private static final TextStyle DisplaySmall;
    private static final TextStyle DisplaySmallEmphasized;
    private static final TextStyle HeadlineLarge;
    private static final TextStyle HeadlineLargeEmphasized;
    private static final TextStyle HeadlineMedium;
    private static final TextStyle HeadlineMediumEmphasized;
    private static final TextStyle HeadlineSmall;
    private static final TextStyle HeadlineSmallEmphasized;
    public static final TypographyTokens INSTANCE = new TypographyTokens();
    private static final TextStyle LabelLarge;
    private static final TextStyle LabelLargeEmphasized;
    private static final TextStyle LabelMedium;
    private static final TextStyle LabelMediumEmphasized;
    private static final TextStyle LabelSmall;
    private static final TextStyle LabelSmallEmphasized;
    private static final TextStyle TitleLarge;
    private static final TextStyle TitleLargeEmphasized;
    private static final TextStyle TitleMedium;
    private static final TextStyle TitleMediumEmphasized;
    private static final TextStyle TitleSmall;
    private static final TextStyle TitleSmallEmphasized;

    static {
        TextStyle defaultTextStyle = TypographyTokensKt.getDefaultTextStyle();
        TypeScaleTokens typeScaleTokens = TypeScaleTokens.INSTANCE;
        GenericFontFamily bodyLargeFont = typeScaleTokens.getBodyLargeFont();
        BodyLarge = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle, 0L, typeScaleTokens.m2217getBodyLargeSizeXSAIIZE(), typeScaleTokens.getBodyLargeWeight(), null, null, bodyLargeFont, null, typeScaleTokens.m2218getBodyLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2216getBodyLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle2 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodyMediumFont = typeScaleTokens.getBodyMediumFont();
        BodyMedium = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle2, 0L, typeScaleTokens.m2223getBodyMediumSizeXSAIIZE(), typeScaleTokens.getBodyMediumWeight(), null, null, bodyMediumFont, null, typeScaleTokens.m2224getBodyMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2222getBodyMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle3 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodySmallFont = typeScaleTokens.getBodySmallFont();
        BodySmall = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle3, 0L, typeScaleTokens.m2229getBodySmallSizeXSAIIZE(), typeScaleTokens.getBodySmallWeight(), null, null, bodySmallFont, null, typeScaleTokens.m2230getBodySmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2228getBodySmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle4 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayLargeFont = typeScaleTokens.getDisplayLargeFont();
        DisplayLarge = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle4, 0L, typeScaleTokens.m2235getDisplayLargeSizeXSAIIZE(), typeScaleTokens.getDisplayLargeWeight(), null, null, displayLargeFont, null, typeScaleTokens.m2236getDisplayLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2234getDisplayLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle5 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayMediumFont = typeScaleTokens.getDisplayMediumFont();
        DisplayMedium = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle5, 0L, typeScaleTokens.m2241getDisplayMediumSizeXSAIIZE(), typeScaleTokens.getDisplayMediumWeight(), null, null, displayMediumFont, null, typeScaleTokens.m2242getDisplayMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2240getDisplayMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle6 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displaySmallFont = typeScaleTokens.getDisplaySmallFont();
        DisplaySmall = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle6, 0L, typeScaleTokens.m2247getDisplaySmallSizeXSAIIZE(), typeScaleTokens.getDisplaySmallWeight(), null, null, displaySmallFont, null, typeScaleTokens.m2248getDisplaySmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2246getDisplaySmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle7 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineLargeFont = typeScaleTokens.getHeadlineLargeFont();
        HeadlineLarge = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle7, 0L, typeScaleTokens.m2253getHeadlineLargeSizeXSAIIZE(), typeScaleTokens.getHeadlineLargeWeight(), null, null, headlineLargeFont, null, typeScaleTokens.m2254getHeadlineLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2252getHeadlineLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle8 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineMediumFont = typeScaleTokens.getHeadlineMediumFont();
        HeadlineMedium = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle8, 0L, typeScaleTokens.m2259getHeadlineMediumSizeXSAIIZE(), typeScaleTokens.getHeadlineMediumWeight(), null, null, headlineMediumFont, null, typeScaleTokens.m2260getHeadlineMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2258getHeadlineMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle9 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineSmallFont = typeScaleTokens.getHeadlineSmallFont();
        HeadlineSmall = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle9, 0L, typeScaleTokens.m2265getHeadlineSmallSizeXSAIIZE(), typeScaleTokens.getHeadlineSmallWeight(), null, null, headlineSmallFont, null, typeScaleTokens.m2266getHeadlineSmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2264getHeadlineSmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle10 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelLargeFont = typeScaleTokens.getLabelLargeFont();
        LabelLarge = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle10, 0L, typeScaleTokens.m2271getLabelLargeSizeXSAIIZE(), typeScaleTokens.getLabelLargeWeight(), null, null, labelLargeFont, null, typeScaleTokens.m2272getLabelLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2270getLabelLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle11 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelMediumFont = typeScaleTokens.getLabelMediumFont();
        LabelMedium = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle11, 0L, typeScaleTokens.m2277getLabelMediumSizeXSAIIZE(), typeScaleTokens.getLabelMediumWeight(), null, null, labelMediumFont, null, typeScaleTokens.m2278getLabelMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2276getLabelMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle12 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelSmallFont = typeScaleTokens.getLabelSmallFont();
        LabelSmall = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle12, 0L, typeScaleTokens.m2283getLabelSmallSizeXSAIIZE(), typeScaleTokens.getLabelSmallWeight(), null, null, labelSmallFont, null, typeScaleTokens.m2284getLabelSmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2282getLabelSmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle13 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleLargeFont = typeScaleTokens.getTitleLargeFont();
        TitleLarge = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle13, 0L, typeScaleTokens.m2289getTitleLargeSizeXSAIIZE(), typeScaleTokens.getTitleLargeWeight(), null, null, titleLargeFont, null, typeScaleTokens.m2290getTitleLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2288getTitleLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle14 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleMediumFont = typeScaleTokens.getTitleMediumFont();
        TitleMedium = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle14, 0L, typeScaleTokens.m2295getTitleMediumSizeXSAIIZE(), typeScaleTokens.getTitleMediumWeight(), null, null, titleMediumFont, null, typeScaleTokens.m2296getTitleMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2294getTitleMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle15 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleSmallFont = typeScaleTokens.getTitleSmallFont();
        TitleSmall = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle15, 0L, typeScaleTokens.m2301getTitleSmallSizeXSAIIZE(), typeScaleTokens.getTitleSmallWeight(), null, null, titleSmallFont, null, typeScaleTokens.m2302getTitleSmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2300getTitleSmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle16 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodyLargeEmphasizedFont = typeScaleTokens.getBodyLargeEmphasizedFont();
        BodyLargeEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle16, 0L, typeScaleTokens.m2214getBodyLargeEmphasizedSizeXSAIIZE(), typeScaleTokens.getBodyLargeEmphasizedWeight(), null, null, bodyLargeEmphasizedFont, null, typeScaleTokens.m2215getBodyLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2213getBodyLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle17 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodyMediumEmphasizedFont = typeScaleTokens.getBodyMediumEmphasizedFont();
        BodyMediumEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle17, 0L, typeScaleTokens.m2220getBodyMediumEmphasizedSizeXSAIIZE(), typeScaleTokens.getBodyMediumEmphasizedWeight(), null, null, bodyMediumEmphasizedFont, null, typeScaleTokens.m2221getBodyMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2219getBodyMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle18 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodySmallEmphasizedFont = typeScaleTokens.getBodySmallEmphasizedFont();
        BodySmallEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle18, 0L, typeScaleTokens.m2226getBodySmallEmphasizedSizeXSAIIZE(), typeScaleTokens.getBodySmallEmphasizedWeight(), null, null, bodySmallEmphasizedFont, null, typeScaleTokens.m2227getBodySmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2225getBodySmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle19 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayLargeEmphasizedFont = typeScaleTokens.getDisplayLargeEmphasizedFont();
        DisplayLargeEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle19, 0L, typeScaleTokens.m2232getDisplayLargeEmphasizedSizeXSAIIZE(), typeScaleTokens.getDisplayLargeEmphasizedWeight(), null, null, displayLargeEmphasizedFont, null, typeScaleTokens.m2233getDisplayLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2231getDisplayLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle20 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayMediumEmphasizedFont = typeScaleTokens.getDisplayMediumEmphasizedFont();
        DisplayMediumEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle20, 0L, typeScaleTokens.m2238getDisplayMediumEmphasizedSizeXSAIIZE(), typeScaleTokens.getDisplayMediumEmphasizedWeight(), null, null, displayMediumEmphasizedFont, null, typeScaleTokens.m2239getDisplayMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2237getDisplayMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle21 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displaySmallEmphasizedFont = typeScaleTokens.getDisplaySmallEmphasizedFont();
        DisplaySmallEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle21, 0L, typeScaleTokens.m2244getDisplaySmallEmphasizedSizeXSAIIZE(), typeScaleTokens.getDisplaySmallEmphasizedWeight(), null, null, displaySmallEmphasizedFont, null, typeScaleTokens.m2245getDisplaySmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2243getDisplaySmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle22 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineLargeEmphasizedFont = typeScaleTokens.getHeadlineLargeEmphasizedFont();
        HeadlineLargeEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle22, 0L, typeScaleTokens.m2250getHeadlineLargeEmphasizedSizeXSAIIZE(), typeScaleTokens.getHeadlineLargeEmphasizedWeight(), null, null, headlineLargeEmphasizedFont, null, typeScaleTokens.m2251getHeadlineLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2249getHeadlineLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle23 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineMediumEmphasizedFont = typeScaleTokens.getHeadlineMediumEmphasizedFont();
        HeadlineMediumEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle23, 0L, typeScaleTokens.m2256getHeadlineMediumEmphasizedSizeXSAIIZE(), typeScaleTokens.getHeadlineMediumEmphasizedWeight(), null, null, headlineMediumEmphasizedFont, null, typeScaleTokens.m2257getHeadlineMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2255getHeadlineMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle24 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineSmallEmphasizedFont = typeScaleTokens.getHeadlineSmallEmphasizedFont();
        HeadlineSmallEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle24, 0L, typeScaleTokens.m2262getHeadlineSmallEmphasizedSizeXSAIIZE(), typeScaleTokens.getHeadlineSmallEmphasizedWeight(), null, null, headlineSmallEmphasizedFont, null, typeScaleTokens.m2263getHeadlineSmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2261getHeadlineSmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle25 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelLargeEmphasizedFont = typeScaleTokens.getLabelLargeEmphasizedFont();
        LabelLargeEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle25, 0L, typeScaleTokens.m2268getLabelLargeEmphasizedSizeXSAIIZE(), typeScaleTokens.getLabelLargeEmphasizedWeight(), null, null, labelLargeEmphasizedFont, null, typeScaleTokens.m2269getLabelLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2267getLabelLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle26 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelMediumEmphasizedFont = typeScaleTokens.getLabelMediumEmphasizedFont();
        LabelMediumEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle26, 0L, typeScaleTokens.m2274getLabelMediumEmphasizedSizeXSAIIZE(), typeScaleTokens.getLabelMediumEmphasizedWeight(), null, null, labelMediumEmphasizedFont, null, typeScaleTokens.m2275getLabelMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2273getLabelMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle27 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelSmallEmphasizedFont = typeScaleTokens.getLabelSmallEmphasizedFont();
        LabelSmallEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle27, 0L, typeScaleTokens.m2280getLabelSmallEmphasizedSizeXSAIIZE(), typeScaleTokens.getLabelSmallEmphasizedWeight(), null, null, labelSmallEmphasizedFont, null, typeScaleTokens.m2281getLabelSmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2279getLabelSmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle28 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleLargeEmphasizedFont = typeScaleTokens.getTitleLargeEmphasizedFont();
        TitleLargeEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle28, 0L, typeScaleTokens.m2286getTitleLargeEmphasizedSizeXSAIIZE(), typeScaleTokens.getTitleLargeEmphasizedWeight(), null, null, titleLargeEmphasizedFont, null, typeScaleTokens.m2287getTitleLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2285getTitleLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle29 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleMediumEmphasizedFont = typeScaleTokens.getTitleMediumEmphasizedFont();
        TitleMediumEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle29, 0L, typeScaleTokens.m2292getTitleMediumEmphasizedSizeXSAIIZE(), typeScaleTokens.getTitleMediumEmphasizedWeight(), null, null, titleMediumEmphasizedFont, null, typeScaleTokens.m2293getTitleMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2291getTitleMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle30 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleSmallEmphasizedFont = typeScaleTokens.getTitleSmallEmphasizedFont();
        TitleSmallEmphasized = TextStyle.m5492copyp1EtxEg$default(defaultTextStyle30, 0L, typeScaleTokens.m2298getTitleSmallEmphasizedSizeXSAIIZE(), typeScaleTokens.getTitleSmallEmphasizedWeight(), null, null, titleSmallEmphasizedFont, null, typeScaleTokens.m2299getTitleSmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, typeScaleTokens.m2297getTitleSmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
    }

    private TypographyTokens() {
    }

    public final TextStyle getBodyLarge() {
        return BodyLarge;
    }

    public final TextStyle getBodyLargeEmphasized() {
        return BodyLargeEmphasized;
    }

    public final TextStyle getBodyMedium() {
        return BodyMedium;
    }

    public final TextStyle getBodyMediumEmphasized() {
        return BodyMediumEmphasized;
    }

    public final TextStyle getBodySmall() {
        return BodySmall;
    }

    public final TextStyle getBodySmallEmphasized() {
        return BodySmallEmphasized;
    }

    public final TextStyle getDisplayLarge() {
        return DisplayLarge;
    }

    public final TextStyle getDisplayLargeEmphasized() {
        return DisplayLargeEmphasized;
    }

    public final TextStyle getDisplayMedium() {
        return DisplayMedium;
    }

    public final TextStyle getDisplayMediumEmphasized() {
        return DisplayMediumEmphasized;
    }

    public final TextStyle getDisplaySmall() {
        return DisplaySmall;
    }

    public final TextStyle getDisplaySmallEmphasized() {
        return DisplaySmallEmphasized;
    }

    public final TextStyle getHeadlineLarge() {
        return HeadlineLarge;
    }

    public final TextStyle getHeadlineLargeEmphasized() {
        return HeadlineLargeEmphasized;
    }

    public final TextStyle getHeadlineMedium() {
        return HeadlineMedium;
    }

    public final TextStyle getHeadlineMediumEmphasized() {
        return HeadlineMediumEmphasized;
    }

    public final TextStyle getHeadlineSmall() {
        return HeadlineSmall;
    }

    public final TextStyle getHeadlineSmallEmphasized() {
        return HeadlineSmallEmphasized;
    }

    public final TextStyle getLabelLarge() {
        return LabelLarge;
    }

    public final TextStyle getLabelLargeEmphasized() {
        return LabelLargeEmphasized;
    }

    public final TextStyle getLabelMedium() {
        return LabelMedium;
    }

    public final TextStyle getLabelMediumEmphasized() {
        return LabelMediumEmphasized;
    }

    public final TextStyle getLabelSmall() {
        return LabelSmall;
    }

    public final TextStyle getLabelSmallEmphasized() {
        return LabelSmallEmphasized;
    }

    public final TextStyle getTitleLarge() {
        return TitleLarge;
    }

    public final TextStyle getTitleLargeEmphasized() {
        return TitleLargeEmphasized;
    }

    public final TextStyle getTitleMedium() {
        return TitleMedium;
    }

    public final TextStyle getTitleMediumEmphasized() {
        return TitleMediumEmphasized;
    }

    public final TextStyle getTitleSmall() {
        return TitleSmall;
    }

    public final TextStyle getTitleSmallEmphasized() {
        return TitleSmallEmphasized;
    }
}
