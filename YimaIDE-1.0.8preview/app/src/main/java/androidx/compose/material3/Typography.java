package androidx.compose.material3;

import androidx.compose.material3.tokens.TypographyTokens;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\bB\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001Bµ\u0002\b\u0000\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0003\u0012\b\b\u0002\u0010 \u001a\u00020\u0003¢\u0006\u0004\b!\u0010\"B\u009f\u0001\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003¢\u0006\u0004\b!\u0010#J¹\u0002\u0010C\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u0003H\u0000¢\u0006\u0002\bDJ\u009c\u0001\u0010C\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0003J\u0013\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010H\u001a\u00020IH\u0016J\b\u0010J\u001a\u00020KH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010%R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010%R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010%R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010%R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010%R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010%R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010%R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010%R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010%R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010%R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010%R\u0014\u0010\u0012\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010%R\u0014\u0010\u0013\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010%R\u0014\u0010\u0014\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010%R\u0014\u0010\u0015\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u0010%R\u0014\u0010\u0016\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010%R\u0014\u0010\u0017\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010%R\u0014\u0010\u0018\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010%R\u0014\u0010\u0019\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010%R\u0014\u0010\u001a\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010%R\u0014\u0010\u001b\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010%R\u0014\u0010\u001c\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010%R\u0014\u0010\u001d\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010%R\u0014\u0010\u001e\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010%R\u0014\u0010\u001f\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010%R\u0014\u0010 \u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010%¨\u0006L"}, d2 = {"Landroidx/compose/material3/Typography;", "", "displayLarge", "Landroidx/compose/ui/text/TextStyle;", "displayMedium", "displaySmall", "headlineLarge", "headlineMedium", "headlineSmall", "titleLarge", "titleMedium", "titleSmall", "bodyLarge", "bodyMedium", "bodySmall", "labelLarge", "labelMedium", "labelSmall", "displayLargeEmphasized", "displayMediumEmphasized", "displaySmallEmphasized", "headlineLargeEmphasized", "headlineMediumEmphasized", "headlineSmallEmphasized", "titleLargeEmphasized", "titleMediumEmphasized", "titleSmallEmphasized", "bodyLargeEmphasized", "bodyMediumEmphasized", "bodySmallEmphasized", "labelLargeEmphasized", "labelMediumEmphasized", "labelSmallEmphasized", "<init>", "(Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;)V", "(Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;)V", "getDisplayLarge", "()Landroidx/compose/ui/text/TextStyle;", "getDisplayMedium", "getDisplaySmall", "getHeadlineLarge", "getHeadlineMedium", "getHeadlineSmall", "getTitleLarge", "getTitleMedium", "getTitleSmall", "getBodyLarge", "getBodyMedium", "getBodySmall", "getLabelLarge", "getLabelMedium", "getLabelSmall", "getDisplayLargeEmphasized$material3", "getDisplayMediumEmphasized$material3", "getDisplaySmallEmphasized$material3", "getHeadlineLargeEmphasized$material3", "getHeadlineMediumEmphasized$material3", "getHeadlineSmallEmphasized$material3", "getTitleLargeEmphasized$material3", "getTitleMediumEmphasized$material3", "getTitleSmallEmphasized$material3", "getBodyLargeEmphasized$material3", "getBodyMediumEmphasized$material3", "getBodySmallEmphasized$material3", "getLabelLargeEmphasized$material3", "getLabelMediumEmphasized$material3", "getLabelSmallEmphasized$material3", "copy", "copy$material3", "equals", "", "other", "hashCode", "", "toString", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Typography {
    public static final int $stable = 0;
    private final TextStyle bodyLarge;
    private final TextStyle bodyLargeEmphasized;
    private final TextStyle bodyMedium;
    private final TextStyle bodyMediumEmphasized;
    private final TextStyle bodySmall;
    private final TextStyle bodySmallEmphasized;
    private final TextStyle displayLarge;
    private final TextStyle displayLargeEmphasized;
    private final TextStyle displayMedium;
    private final TextStyle displayMediumEmphasized;
    private final TextStyle displaySmall;
    private final TextStyle displaySmallEmphasized;
    private final TextStyle headlineLarge;
    private final TextStyle headlineLargeEmphasized;
    private final TextStyle headlineMedium;
    private final TextStyle headlineMediumEmphasized;
    private final TextStyle headlineSmall;
    private final TextStyle headlineSmallEmphasized;
    private final TextStyle labelLarge;
    private final TextStyle labelLargeEmphasized;
    private final TextStyle labelMedium;
    private final TextStyle labelMediumEmphasized;
    private final TextStyle labelSmall;
    private final TextStyle labelSmallEmphasized;
    private final TextStyle titleLarge;
    private final TextStyle titleLargeEmphasized;
    private final TextStyle titleMedium;
    private final TextStyle titleMediumEmphasized;
    private final TextStyle titleSmall;
    private final TextStyle titleSmallEmphasized;

    public /* synthetic */ Typography(TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13, TextStyle textStyle14, TextStyle textStyle15, TextStyle textStyle16, TextStyle textStyle17, TextStyle textStyle18, TextStyle textStyle19, TextStyle textStyle20, TextStyle textStyle21, TextStyle textStyle22, TextStyle textStyle23, TextStyle textStyle24, TextStyle textStyle25, TextStyle textStyle26, TextStyle textStyle27, TextStyle textStyle28, TextStyle textStyle29, TextStyle textStyle30, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? TypographyTokens.INSTANCE.getDisplayLarge() : textStyle, (i & 2) != 0 ? TypographyTokens.INSTANCE.getDisplayMedium() : textStyle2, (i & 4) != 0 ? TypographyTokens.INSTANCE.getDisplaySmall() : textStyle3, (i & 8) != 0 ? TypographyTokens.INSTANCE.getHeadlineLarge() : textStyle4, (i & 16) != 0 ? TypographyTokens.INSTANCE.getHeadlineMedium() : textStyle5, (i & 32) != 0 ? TypographyTokens.INSTANCE.getHeadlineSmall() : textStyle6, (i & 64) != 0 ? TypographyTokens.INSTANCE.getTitleLarge() : textStyle7, (i & 128) != 0 ? TypographyTokens.INSTANCE.getTitleMedium() : textStyle8, (i & 256) != 0 ? TypographyTokens.INSTANCE.getTitleSmall() : textStyle9, (i & 512) != 0 ? TypographyTokens.INSTANCE.getBodyLarge() : textStyle10, (i & 1024) != 0 ? TypographyTokens.INSTANCE.getBodyMedium() : textStyle11, (i & 2048) != 0 ? TypographyTokens.INSTANCE.getBodySmall() : textStyle12, (i & 4096) != 0 ? TypographyTokens.INSTANCE.getLabelLarge() : textStyle13, (i & 8192) != 0 ? TypographyTokens.INSTANCE.getLabelMedium() : textStyle14, (i & 16384) != 0 ? TypographyTokens.INSTANCE.getLabelSmall() : textStyle15, (i & 32768) != 0 ? TypographyTokens.INSTANCE.getDisplayLargeEmphasized() : textStyle16, (i & 65536) != 0 ? TypographyTokens.INSTANCE.getDisplayMediumEmphasized() : textStyle17, (i & 131072) != 0 ? TypographyTokens.INSTANCE.getDisplaySmallEmphasized() : textStyle18, (i & 262144) != 0 ? TypographyTokens.INSTANCE.getHeadlineLargeEmphasized() : textStyle19, (i & 524288) != 0 ? TypographyTokens.INSTANCE.getHeadlineMediumEmphasized() : textStyle20, (i & 1048576) != 0 ? TypographyTokens.INSTANCE.getHeadlineSmallEmphasized() : textStyle21, (i & 2097152) != 0 ? TypographyTokens.INSTANCE.getTitleLargeEmphasized() : textStyle22, (i & 4194304) != 0 ? TypographyTokens.INSTANCE.getTitleMediumEmphasized() : textStyle23, (i & 8388608) != 0 ? TypographyTokens.INSTANCE.getTitleSmallEmphasized() : textStyle24, (i & 16777216) != 0 ? TypographyTokens.INSTANCE.getBodyLargeEmphasized() : textStyle25, (i & 33554432) != 0 ? TypographyTokens.INSTANCE.getBodyMediumEmphasized() : textStyle26, (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? TypographyTokens.INSTANCE.getBodySmallEmphasized() : textStyle27, (i & 134217728) != 0 ? TypographyTokens.INSTANCE.getLabelLargeEmphasized() : textStyle28, (i & 268435456) != 0 ? TypographyTokens.INSTANCE.getLabelMediumEmphasized() : textStyle29, (i & 536870912) != 0 ? TypographyTokens.INSTANCE.getLabelSmallEmphasized() : textStyle30);
    }

    public static /* synthetic */ Typography copy$material3$default(Typography typography, TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13, TextStyle textStyle14, TextStyle textStyle15, TextStyle textStyle16, TextStyle textStyle17, TextStyle textStyle18, TextStyle textStyle19, TextStyle textStyle20, TextStyle textStyle21, TextStyle textStyle22, TextStyle textStyle23, TextStyle textStyle24, TextStyle textStyle25, TextStyle textStyle26, TextStyle textStyle27, TextStyle textStyle28, TextStyle textStyle29, TextStyle textStyle30, int i, Object obj) {
        TextStyle textStyle31;
        TextStyle textStyle32;
        TextStyle textStyle33 = (i & 1) != 0 ? typography.displayLarge : textStyle;
        TextStyle textStyle34 = (i & 2) != 0 ? typography.displayMedium : textStyle2;
        TextStyle textStyle35 = (i & 4) != 0 ? typography.displaySmall : textStyle3;
        TextStyle textStyle36 = (i & 8) != 0 ? typography.headlineLarge : textStyle4;
        TextStyle textStyle37 = (i & 16) != 0 ? typography.headlineMedium : textStyle5;
        TextStyle textStyle38 = (i & 32) != 0 ? typography.headlineSmall : textStyle6;
        TextStyle textStyle39 = (i & 64) != 0 ? typography.titleLarge : textStyle7;
        TextStyle textStyle40 = (i & 128) != 0 ? typography.titleMedium : textStyle8;
        TextStyle textStyle41 = (i & 256) != 0 ? typography.titleSmall : textStyle9;
        TextStyle textStyle42 = (i & 512) != 0 ? typography.bodyLarge : textStyle10;
        TextStyle textStyle43 = (i & 1024) != 0 ? typography.bodyMedium : textStyle11;
        TextStyle textStyle44 = (i & 2048) != 0 ? typography.bodySmall : textStyle12;
        TextStyle textStyle45 = (i & 4096) != 0 ? typography.labelLarge : textStyle13;
        TextStyle textStyle46 = (i & 8192) != 0 ? typography.labelMedium : textStyle14;
        TextStyle textStyle47 = textStyle33;
        TextStyle textStyle48 = (i & 16384) != 0 ? typography.labelSmall : textStyle15;
        TextStyle textStyle49 = (i & 32768) != 0 ? typography.displayLargeEmphasized : textStyle16;
        TextStyle textStyle50 = (i & 65536) != 0 ? typography.displayMediumEmphasized : textStyle17;
        TextStyle textStyle51 = (i & 131072) != 0 ? typography.displaySmallEmphasized : textStyle18;
        TextStyle textStyle52 = (i & 262144) != 0 ? typography.headlineLargeEmphasized : textStyle19;
        TextStyle textStyle53 = (i & 524288) != 0 ? typography.headlineMediumEmphasized : textStyle20;
        TextStyle textStyle54 = (i & 1048576) != 0 ? typography.headlineSmallEmphasized : textStyle21;
        TextStyle textStyle55 = (i & 2097152) != 0 ? typography.titleLargeEmphasized : textStyle22;
        TextStyle textStyle56 = (i & 4194304) != 0 ? typography.titleMediumEmphasized : textStyle23;
        TextStyle textStyle57 = (i & 8388608) != 0 ? typography.titleSmallEmphasized : textStyle24;
        TextStyle textStyle58 = (i & 16777216) != 0 ? typography.bodyLargeEmphasized : textStyle25;
        TextStyle textStyle59 = (i & 33554432) != 0 ? typography.bodyMediumEmphasized : textStyle26;
        TextStyle textStyle60 = (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? typography.bodySmallEmphasized : textStyle27;
        TextStyle textStyle61 = (i & 134217728) != 0 ? typography.labelLargeEmphasized : textStyle28;
        TextStyle textStyle62 = (i & 268435456) != 0 ? typography.labelMediumEmphasized : textStyle29;
        if ((i & 536870912) != 0) {
            textStyle32 = textStyle62;
            textStyle31 = typography.labelSmallEmphasized;
        } else {
            textStyle31 = textStyle30;
            textStyle32 = textStyle62;
        }
        return typography.copy$material3(textStyle47, textStyle34, textStyle35, textStyle36, textStyle37, textStyle38, textStyle39, textStyle40, textStyle41, textStyle42, textStyle43, textStyle44, textStyle45, textStyle46, textStyle48, textStyle49, textStyle50, textStyle51, textStyle52, textStyle53, textStyle54, textStyle55, textStyle56, textStyle57, textStyle58, textStyle59, textStyle60, textStyle61, textStyle32, textStyle31);
    }

    public final Typography copy(TextStyle displayLarge, TextStyle displayMedium, TextStyle displaySmall, TextStyle headlineLarge, TextStyle headlineMedium, TextStyle headlineSmall, TextStyle titleLarge, TextStyle titleMedium, TextStyle titleSmall, TextStyle bodyLarge, TextStyle bodyMedium, TextStyle bodySmall, TextStyle labelLarge, TextStyle labelMedium, TextStyle labelSmall) {
        return copy$material3(displayLarge, displayMedium, displaySmall, headlineLarge, headlineMedium, headlineSmall, titleLarge, titleMedium, titleSmall, bodyLarge, bodyMedium, bodySmall, labelLarge, labelMedium, labelSmall, this.displayLargeEmphasized, this.displayMediumEmphasized, this.displaySmallEmphasized, this.headlineLargeEmphasized, this.headlineMediumEmphasized, this.headlineSmallEmphasized, this.titleLargeEmphasized, this.titleMediumEmphasized, this.titleSmallEmphasized, this.bodyLargeEmphasized, this.bodyMediumEmphasized, this.bodySmallEmphasized, this.labelLargeEmphasized, this.labelMediumEmphasized, this.labelSmallEmphasized);
    }

    public final Typography copy$material3(TextStyle displayLarge, TextStyle displayMedium, TextStyle displaySmall, TextStyle headlineLarge, TextStyle headlineMedium, TextStyle headlineSmall, TextStyle titleLarge, TextStyle titleMedium, TextStyle titleSmall, TextStyle bodyLarge, TextStyle bodyMedium, TextStyle bodySmall, TextStyle labelLarge, TextStyle labelMedium, TextStyle labelSmall, TextStyle displayLargeEmphasized, TextStyle displayMediumEmphasized, TextStyle displaySmallEmphasized, TextStyle headlineLargeEmphasized, TextStyle headlineMediumEmphasized, TextStyle headlineSmallEmphasized, TextStyle titleLargeEmphasized, TextStyle titleMediumEmphasized, TextStyle titleSmallEmphasized, TextStyle bodyLargeEmphasized, TextStyle bodyMediumEmphasized, TextStyle bodySmallEmphasized, TextStyle labelLargeEmphasized, TextStyle labelMediumEmphasized, TextStyle labelSmallEmphasized) {
        return new Typography(displayLarge, displayMedium, displaySmall, headlineLarge, headlineMedium, headlineSmall, titleLarge, titleMedium, titleSmall, bodyLarge, bodyMedium, bodySmall, labelLarge, labelMedium, labelSmall, displayLargeEmphasized, displayMediumEmphasized, displaySmallEmphasized, headlineLargeEmphasized, headlineMediumEmphasized, headlineSmallEmphasized, titleLargeEmphasized, titleMediumEmphasized, titleSmallEmphasized, bodyLargeEmphasized, bodyMediumEmphasized, bodySmallEmphasized, labelLargeEmphasized, labelMediumEmphasized, labelSmallEmphasized);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Typography)) {
            return false;
        }
        Typography typography = (Typography) other;
        return Intrinsics.areEqual(this.displayLarge, typography.displayLarge) && Intrinsics.areEqual(this.displayMedium, typography.displayMedium) && Intrinsics.areEqual(this.displaySmall, typography.displaySmall) && Intrinsics.areEqual(this.headlineLarge, typography.headlineLarge) && Intrinsics.areEqual(this.headlineMedium, typography.headlineMedium) && Intrinsics.areEqual(this.headlineSmall, typography.headlineSmall) && Intrinsics.areEqual(this.titleLarge, typography.titleLarge) && Intrinsics.areEqual(this.titleMedium, typography.titleMedium) && Intrinsics.areEqual(this.titleSmall, typography.titleSmall) && Intrinsics.areEqual(this.bodyLarge, typography.bodyLarge) && Intrinsics.areEqual(this.bodyMedium, typography.bodyMedium) && Intrinsics.areEqual(this.bodySmall, typography.bodySmall) && Intrinsics.areEqual(this.labelLarge, typography.labelLarge) && Intrinsics.areEqual(this.labelMedium, typography.labelMedium) && Intrinsics.areEqual(this.labelSmall, typography.labelSmall) && Intrinsics.areEqual(this.displayLargeEmphasized, typography.displayLargeEmphasized) && Intrinsics.areEqual(this.displayMediumEmphasized, typography.displayMediumEmphasized) && Intrinsics.areEqual(this.displaySmallEmphasized, typography.displaySmallEmphasized) && Intrinsics.areEqual(this.headlineLargeEmphasized, typography.headlineLargeEmphasized) && Intrinsics.areEqual(this.headlineMediumEmphasized, typography.headlineMediumEmphasized) && Intrinsics.areEqual(this.headlineSmallEmphasized, typography.headlineSmallEmphasized) && Intrinsics.areEqual(this.titleLargeEmphasized, typography.titleLargeEmphasized) && Intrinsics.areEqual(this.titleMediumEmphasized, typography.titleMediumEmphasized) && Intrinsics.areEqual(this.titleSmallEmphasized, typography.titleSmallEmphasized) && Intrinsics.areEqual(this.bodyLargeEmphasized, typography.bodyLargeEmphasized) && Intrinsics.areEqual(this.bodyMediumEmphasized, typography.bodyMediumEmphasized) && Intrinsics.areEqual(this.bodySmallEmphasized, typography.bodySmallEmphasized) && Intrinsics.areEqual(this.labelLargeEmphasized, typography.labelLargeEmphasized) && Intrinsics.areEqual(this.labelMediumEmphasized, typography.labelMediumEmphasized) && Intrinsics.areEqual(this.labelSmallEmphasized, typography.labelSmallEmphasized);
    }

    public final TextStyle getBodyLarge() {
        return this.bodyLarge;
    }

    /* JADX INFO: renamed from: getBodyLargeEmphasized$material3, reason: from getter */
    public final TextStyle getBodyLargeEmphasized() {
        return this.bodyLargeEmphasized;
    }

    public final TextStyle getBodyMedium() {
        return this.bodyMedium;
    }

    /* JADX INFO: renamed from: getBodyMediumEmphasized$material3, reason: from getter */
    public final TextStyle getBodyMediumEmphasized() {
        return this.bodyMediumEmphasized;
    }

    public final TextStyle getBodySmall() {
        return this.bodySmall;
    }

    /* JADX INFO: renamed from: getBodySmallEmphasized$material3, reason: from getter */
    public final TextStyle getBodySmallEmphasized() {
        return this.bodySmallEmphasized;
    }

    public final TextStyle getDisplayLarge() {
        return this.displayLarge;
    }

    /* JADX INFO: renamed from: getDisplayLargeEmphasized$material3, reason: from getter */
    public final TextStyle getDisplayLargeEmphasized() {
        return this.displayLargeEmphasized;
    }

    public final TextStyle getDisplayMedium() {
        return this.displayMedium;
    }

    /* JADX INFO: renamed from: getDisplayMediumEmphasized$material3, reason: from getter */
    public final TextStyle getDisplayMediumEmphasized() {
        return this.displayMediumEmphasized;
    }

    public final TextStyle getDisplaySmall() {
        return this.displaySmall;
    }

    /* JADX INFO: renamed from: getDisplaySmallEmphasized$material3, reason: from getter */
    public final TextStyle getDisplaySmallEmphasized() {
        return this.displaySmallEmphasized;
    }

    public final TextStyle getHeadlineLarge() {
        return this.headlineLarge;
    }

    /* JADX INFO: renamed from: getHeadlineLargeEmphasized$material3, reason: from getter */
    public final TextStyle getHeadlineLargeEmphasized() {
        return this.headlineLargeEmphasized;
    }

    public final TextStyle getHeadlineMedium() {
        return this.headlineMedium;
    }

    /* JADX INFO: renamed from: getHeadlineMediumEmphasized$material3, reason: from getter */
    public final TextStyle getHeadlineMediumEmphasized() {
        return this.headlineMediumEmphasized;
    }

    public final TextStyle getHeadlineSmall() {
        return this.headlineSmall;
    }

    /* JADX INFO: renamed from: getHeadlineSmallEmphasized$material3, reason: from getter */
    public final TextStyle getHeadlineSmallEmphasized() {
        return this.headlineSmallEmphasized;
    }

    public final TextStyle getLabelLarge() {
        return this.labelLarge;
    }

    /* JADX INFO: renamed from: getLabelLargeEmphasized$material3, reason: from getter */
    public final TextStyle getLabelLargeEmphasized() {
        return this.labelLargeEmphasized;
    }

    public final TextStyle getLabelMedium() {
        return this.labelMedium;
    }

    /* JADX INFO: renamed from: getLabelMediumEmphasized$material3, reason: from getter */
    public final TextStyle getLabelMediumEmphasized() {
        return this.labelMediumEmphasized;
    }

    public final TextStyle getLabelSmall() {
        return this.labelSmall;
    }

    /* JADX INFO: renamed from: getLabelSmallEmphasized$material3, reason: from getter */
    public final TextStyle getLabelSmallEmphasized() {
        return this.labelSmallEmphasized;
    }

    public final TextStyle getTitleLarge() {
        return this.titleLarge;
    }

    /* JADX INFO: renamed from: getTitleLargeEmphasized$material3, reason: from getter */
    public final TextStyle getTitleLargeEmphasized() {
        return this.titleLargeEmphasized;
    }

    public final TextStyle getTitleMedium() {
        return this.titleMedium;
    }

    /* JADX INFO: renamed from: getTitleMediumEmphasized$material3, reason: from getter */
    public final TextStyle getTitleMediumEmphasized() {
        return this.titleMediumEmphasized;
    }

    public final TextStyle getTitleSmall() {
        return this.titleSmall;
    }

    /* JADX INFO: renamed from: getTitleSmallEmphasized$material3, reason: from getter */
    public final TextStyle getTitleSmallEmphasized() {
        return this.titleSmallEmphasized;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.displayLarge.hashCode() * 31) + this.displayMedium.hashCode()) * 31) + this.displaySmall.hashCode()) * 31) + this.headlineLarge.hashCode()) * 31) + this.headlineMedium.hashCode()) * 31) + this.headlineSmall.hashCode()) * 31) + this.titleLarge.hashCode()) * 31) + this.titleMedium.hashCode()) * 31) + this.titleSmall.hashCode()) * 31) + this.bodyLarge.hashCode()) * 31) + this.bodyMedium.hashCode()) * 31) + this.bodySmall.hashCode()) * 31) + this.labelLarge.hashCode()) * 31) + this.labelMedium.hashCode()) * 31) + this.labelSmall.hashCode()) * 31) + this.displayLargeEmphasized.hashCode()) * 31) + this.displayMediumEmphasized.hashCode()) * 31) + this.displaySmallEmphasized.hashCode()) * 31) + this.headlineLargeEmphasized.hashCode()) * 31) + this.headlineMediumEmphasized.hashCode()) * 31) + this.headlineSmallEmphasized.hashCode()) * 31) + this.titleLargeEmphasized.hashCode()) * 31) + this.titleMediumEmphasized.hashCode()) * 31) + this.titleSmallEmphasized.hashCode()) * 31) + this.bodyLargeEmphasized.hashCode()) * 31) + this.bodyMediumEmphasized.hashCode()) * 31) + this.bodySmallEmphasized.hashCode()) * 31) + this.labelLargeEmphasized.hashCode()) * 31) + this.labelMediumEmphasized.hashCode()) * 31) + this.labelSmallEmphasized.hashCode();
    }

    public String toString() {
        return "Typography(displayLarge=" + this.displayLarge + ", displayMedium=" + this.displayMedium + ",displaySmall=" + this.displaySmall + ", headlineLarge=" + this.headlineLarge + ", headlineMedium=" + this.headlineMedium + ", headlineSmall=" + this.headlineSmall + ", titleLarge=" + this.titleLarge + ", titleMedium=" + this.titleMedium + ", titleSmall=" + this.titleSmall + ", bodyLarge=" + this.bodyLarge + ", bodyMedium=" + this.bodyMedium + ", bodySmall=" + this.bodySmall + ", labelLarge=" + this.labelLarge + ", labelMedium=" + this.labelMedium + ", labelSmall=" + this.labelSmall + ", displayLargeEmphasized=" + this.displayLargeEmphasized + ", displayMediumEmphasized=" + this.displayMediumEmphasized + ", displaySmallEmphasized=" + this.displaySmallEmphasized + ", headlineLargeEmphasized=" + this.headlineLargeEmphasized + ", headlineMediumEmphasized=" + this.headlineMediumEmphasized + ", headlineSmallEmphasized=" + this.headlineSmallEmphasized + ", titleLargeEmphasized=" + this.titleLargeEmphasized + ", titleMediumEmphasized=" + this.titleMediumEmphasized + ", titleSmallEmphasized=" + this.titleSmallEmphasized + ", bodyLargeEmphasized=" + this.bodyLargeEmphasized + ", bodyMediumEmphasized=" + this.bodyMediumEmphasized + ", bodySmallEmphasized=" + this.bodySmallEmphasized + ", labelLargeEmphasized=" + this.labelLargeEmphasized + ", labelMediumEmphasized=" + this.labelMediumEmphasized + ", labelSmallEmphasized=" + this.labelSmallEmphasized + ')';
    }

    public Typography(TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13, TextStyle textStyle14, TextStyle textStyle15, TextStyle textStyle16, TextStyle textStyle17, TextStyle textStyle18, TextStyle textStyle19, TextStyle textStyle20, TextStyle textStyle21, TextStyle textStyle22, TextStyle textStyle23, TextStyle textStyle24, TextStyle textStyle25, TextStyle textStyle26, TextStyle textStyle27, TextStyle textStyle28, TextStyle textStyle29, TextStyle textStyle30) {
        this.displayLarge = textStyle;
        this.displayMedium = textStyle2;
        this.displaySmall = textStyle3;
        this.headlineLarge = textStyle4;
        this.headlineMedium = textStyle5;
        this.headlineSmall = textStyle6;
        this.titleLarge = textStyle7;
        this.titleMedium = textStyle8;
        this.titleSmall = textStyle9;
        this.bodyLarge = textStyle10;
        this.bodyMedium = textStyle11;
        this.bodySmall = textStyle12;
        this.labelLarge = textStyle13;
        this.labelMedium = textStyle14;
        this.labelSmall = textStyle15;
        this.displayLargeEmphasized = textStyle16;
        this.displayMediumEmphasized = textStyle17;
        this.displaySmallEmphasized = textStyle18;
        this.headlineLargeEmphasized = textStyle19;
        this.headlineMediumEmphasized = textStyle20;
        this.headlineSmallEmphasized = textStyle21;
        this.titleLargeEmphasized = textStyle22;
        this.titleMediumEmphasized = textStyle23;
        this.titleSmallEmphasized = textStyle24;
        this.bodyLargeEmphasized = textStyle25;
        this.bodyMediumEmphasized = textStyle26;
        this.bodySmallEmphasized = textStyle27;
        this.labelLargeEmphasized = textStyle28;
        this.labelMediumEmphasized = textStyle29;
        this.labelSmallEmphasized = textStyle30;
    }

    public Typography() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1073741823, null);
    }

    public /* synthetic */ Typography(TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13, TextStyle textStyle14, TextStyle textStyle15, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? TypographyTokens.INSTANCE.getDisplayLarge() : textStyle, (i & 2) != 0 ? TypographyTokens.INSTANCE.getDisplayMedium() : textStyle2, (i & 4) != 0 ? TypographyTokens.INSTANCE.getDisplaySmall() : textStyle3, (i & 8) != 0 ? TypographyTokens.INSTANCE.getHeadlineLarge() : textStyle4, (i & 16) != 0 ? TypographyTokens.INSTANCE.getHeadlineMedium() : textStyle5, (i & 32) != 0 ? TypographyTokens.INSTANCE.getHeadlineSmall() : textStyle6, (i & 64) != 0 ? TypographyTokens.INSTANCE.getTitleLarge() : textStyle7, (i & 128) != 0 ? TypographyTokens.INSTANCE.getTitleMedium() : textStyle8, (i & 256) != 0 ? TypographyTokens.INSTANCE.getTitleSmall() : textStyle9, (i & 512) != 0 ? TypographyTokens.INSTANCE.getBodyLarge() : textStyle10, (i & 1024) != 0 ? TypographyTokens.INSTANCE.getBodyMedium() : textStyle11, (i & 2048) != 0 ? TypographyTokens.INSTANCE.getBodySmall() : textStyle12, (i & 4096) != 0 ? TypographyTokens.INSTANCE.getLabelLarge() : textStyle13, (i & 8192) != 0 ? TypographyTokens.INSTANCE.getLabelMedium() : textStyle14, (i & 16384) != 0 ? TypographyTokens.INSTANCE.getLabelSmall() : textStyle15);
    }

    public Typography(TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13, TextStyle textStyle14, TextStyle textStyle15) {
        this(textStyle, textStyle2, textStyle3, textStyle4, textStyle5, textStyle6, textStyle7, textStyle8, textStyle9, textStyle10, textStyle11, textStyle12, textStyle13, textStyle14, textStyle15, textStyle, textStyle2, textStyle3, textStyle4, textStyle5, textStyle6, textStyle7, textStyle8, textStyle9, textStyle10, textStyle11, textStyle12, textStyle13, textStyle14, textStyle15);
    }
}
