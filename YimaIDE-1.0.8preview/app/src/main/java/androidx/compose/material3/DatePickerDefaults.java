package androidx.compose.material3;

import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.LazyListSnapLayoutInfoProviderKt;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt;
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.material3.DatePickerDefaults;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.material3.tokens.DividerTokens;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.view.PointerIconCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J\u008b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\b2\b\b\u0002\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\b2\b\b\u0002\u0010\u001c\u001a\u00020\b2\b\b\u0002\u0010\u001d\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\b2\b\b\u0002\u0010\u001f\u001a\u00020\b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!H\u0007¢\u0006\u0004\b\"\u0010#J$\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+J+\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u00020\bH\u0007¢\u0006\u0004\b5\u00106J=\u00107\u001a\u00020/2\b\u00108\u001a\u0004\u0018\u0001092\u0006\u00100\u001a\u0002012\u0006\u0010(\u001a\u00020)2\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u00020\bH\u0007¢\u0006\u0004\b:\u0010;J'\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u000e\b\u0002\u0010@\u001a\b\u0012\u0004\u0012\u00020B0AH\u0001¢\u0006\u0004\bC\u0010DR\u0018\u0010$\u001a\u00020\u0005*\u00020%8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0011\u0010E\u001a\u00020F¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0013\u0010I\u001a\u00020J¢\u0006\n\n\u0002\u0010M\u001a\u0004\bK\u0010LR\u0011\u0010N\u001a\u00020O8G¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0011\u0010R\u001a\u00020S¢\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u000e\u0010V\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"Landroidx/compose/material3/DatePickerDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/DatePickerColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/DatePickerColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "titleContentColor", "headlineContentColor", "weekdayContentColor", "subheadContentColor", "navigationContentColor", "yearContentColor", "disabledYearContentColor", "currentYearContentColor", "selectedYearContentColor", "disabledSelectedYearContentColor", "selectedYearContainerColor", "disabledSelectedYearContainerColor", "dayContentColor", "disabledDayContentColor", "selectedDayContentColor", "disabledSelectedDayContentColor", "selectedDayContainerColor", "disabledSelectedDayContainerColor", "todayContentColor", "todayDateBorderColor", "dayInSelectionRangeContentColor", "dayInSelectionRangeContainerColor", "dividerColor", "dateTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "colors-bSRYm20", "(JJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)Landroidx/compose/material3/DatePickerColors;", "defaultDatePickerColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultDatePickerColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/DatePickerColors;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "yearSelectionSkeleton", "", "selectedDateSkeleton", "selectedDateDescriptionSkeleton", "DatePickerTitle", "", "displayMode", "Landroidx/compose/material3/DisplayMode;", "modifier", "Landroidx/compose/ui/Modifier;", "contentColor", "DatePickerTitle-FNtVw6o", "(ILandroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "DatePickerHeadline", "selectedDateMillis", "", "DatePickerHeadline-ISIPfiY", "(Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "rememberSnapFlingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "rememberSnapFlingBehavior$material3", "(Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/FlingBehavior;", "YearRange", "Lkotlin/ranges/IntRange;", "getYearRange", "()Lkotlin/ranges/IntRange;", "TonalElevation", "Landroidx/compose/ui/unit/Dp;", "getTonalElevation-D9Ej5fM", "()F", "F", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "AllDates", "Landroidx/compose/material3/SelectableDates;", "getAllDates", "()Landroidx/compose/material3/SelectableDates;", "YearMonthSkeleton", "YearAbbrMonthDaySkeleton", "YearMonthWeekdayDaySkeleton", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DatePickerDefaults {
    public static final int $stable = 0;
    public static final String YearAbbrMonthDaySkeleton = "yMMMd";
    public static final String YearMonthSkeleton = "yMMMM";
    public static final String YearMonthWeekdayDaySkeleton = "yMMMMEEEEd";
    public static final DatePickerDefaults INSTANCE = new DatePickerDefaults();
    private static final IntRange YearRange = new IntRange(1900, 2100);
    private static final float TonalElevation = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
    private static final SelectableDates AllDates = new SelectableDates() { // from class: androidx.compose.material3.DatePickerDefaults$AllDates$1
    };

    private DatePickerDefaults() {
    }

    public static Unit a(DatePickerDefaults datePickerDefaults, Long l, int i, DatePickerFormatter datePickerFormatter, Modifier modifier, long j, int i2, int i3, Composer composer, int i4) {
        datePickerDefaults.m358DatePickerHeadlineISIPfiY(l, i, datePickerFormatter, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit b(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.m5263setLiveRegionhR3wRGc(semanticsPropertyReceiver, LiveRegionMode.INSTANCE.m5237getPolite0phEisY());
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    public static Unit c(DatePickerDefaults datePickerDefaults, int i, Modifier modifier, long j, int i2, int i3, Composer composer, int i4) {
        datePickerDefaults.m359DatePickerTitleFNtVw6o(i, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ DatePickerFormatter dateFormatter$default(DatePickerDefaults datePickerDefaults, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = YearMonthSkeleton;
        }
        if ((i & 2) != 0) {
            str2 = YearAbbrMonthDaySkeleton;
        }
        if ((i & 4) != 0) {
            str3 = YearMonthWeekdayDaySkeleton;
        }
        return datePickerDefaults.dateFormatter(str, str2, str3);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0159  */
    /* JADX WARN: Code duplicated, block: B:101:0x016f  */
    /* JADX WARN: Code duplicated, block: B:103:0x017d  */
    /* JADX WARN: Code duplicated, block: B:105:0x0188  */
    /* JADX WARN: Code duplicated, block: B:107:0x019a  */
    /* JADX WARN: Code duplicated, block: B:108:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:110:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:111:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:113:0x01de  */
    /* JADX WARN: Code duplicated, block: B:116:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:117:0x0209  */
    /* JADX WARN: Code duplicated, block: B:119:0x0213  */
    /* JADX WARN: Code duplicated, block: B:120:0x0229  */
    /* JADX WARN: Code duplicated, block: B:125:0x0251  */
    /* JADX WARN: Code duplicated, block: B:128:0x0297  */
    /* JADX WARN: Code duplicated, block: B:130:0x029d  */
    /* JADX WARN: Code duplicated, block: B:133:0x02aa  */
    /* JADX WARN: Code duplicated, block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x0080  */
    /* JADX WARN: Code duplicated, block: B:51:0x0084  */
    /* JADX WARN: Code duplicated, block: B:53:0x008c  */
    /* JADX WARN: Code duplicated, block: B:54:0x008f  */
    /* JADX WARN: Code duplicated, block: B:57:0x0095  */
    /* JADX WARN: Code duplicated, block: B:60:0x009d  */
    /* JADX WARN: Code duplicated, block: B:61:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:65:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:84:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:92:0x0105  */
    /* JADX WARN: Code duplicated, block: B:95:0x0127  */
    /* JADX WARN: Code duplicated, block: B:97:0x0139  */
    /* JADX WARN: Code duplicated, block: B:98:0x014f  */
    /* JADX INFO: renamed from: DatePickerHeadline-ISIPfiY, reason: not valid java name */
    public final void m358DatePickerHeadlineISIPfiY(Long l, final int i, DatePickerFormatter datePickerFormatter, Modifier modifier, long j, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long headlineContentColor;
        int i5;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        final long j2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int i6;
        long j3;
        Modifier modifier5;
        String date$default;
        String date;
        String strM1471getString2EP1pXo;
        DisplayMode.Companion companion;
        final String str;
        boolean zChanged;
        Object objRememberedValue;
        DisplayMode.Companion companion2;
        DisplayMode.Companion companion3;
        final Long l2 = l;
        final DatePickerFormatter datePickerFormatter2 = datePickerFormatter;
        Composer composerStartRestartGroup = composer.startRestartGroup(1913724796);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(l2) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 32 : 16;
        }
        if ((i3 & 4) != 0) {
            i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        } else if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= (i2 & 512) == 0 ? composerStartRestartGroup.changed(datePickerFormatter2) : composerStartRestartGroup.changedInstance(datePickerFormatter2) ? 256 : 128;
        }
        int i7 = i3 & 8;
        if (i7 == 0) {
            if ((i2 & 3072) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 2048 : 1024;
            }
            if ((i2 & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    headlineContentColor = j;
                    int i8 = composerStartRestartGroup.changed(headlineContentColor) ? 16384 : 8192;
                    i4 |= i8;
                } else {
                    headlineContentColor = j;
                }
                i4 |= i8;
            } else {
                headlineContentColor = j;
            }
            if ((i3 & 32) != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i5 = 131072;
                } else {
                    i5 = 65536;
                }
                i4 |= i5;
            }
            if ((74899 & i4) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i7 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 16) != 0) {
                        headlineContentColor = colors(composerStartRestartGroup, (i4 >> 15) & 14).getHeadlineContentColor();
                        i4 &= -57345;
                    }
                    i6 = i4;
                    j3 = headlineContentColor;
                    modifier5 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                    }
                    i6 = i4;
                    j3 = headlineContentColor;
                    modifier5 = modifier2;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1913724796, i6, -1, "androidx.compose.material3.DatePickerDefaults.DatePickerHeadline (DatePicker.kt:684)");
                }
                Locale localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                date$default = DatePickerFormatter.formatDate$default(datePickerFormatter, l2, localeDefaultLocale, false, 4, null);
                datePickerFormatter2 = datePickerFormatter;
                l2 = l2;
                date = datePickerFormatter2.formatDate(l2, localeDefaultLocale, true);
                strM1471getString2EP1pXo = "";
                if (date == null) {
                    composerStartRestartGroup.startReplaceGroup(380185931);
                    companion3 = DisplayMode.INSTANCE;
                    if (DisplayMode.m407equalsimpl0(i, companion3.m412getPickerjFl4v0())) {
                        composerStartRestartGroup.startReplaceGroup(843549871);
                        Strings.Companion companion4 = Strings.INSTANCE;
                        date = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_no_selection_description), composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    } else if (DisplayMode.m407equalsimpl0(i, companion3.m411getInputjFl4v0())) {
                        composerStartRestartGroup.startReplaceGroup(843552842);
                        Strings.Companion companion5 = Strings.INSTANCE;
                        date = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_no_input_description), composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(380407362);
                        composerStartRestartGroup.endReplaceGroup();
                        date = "";
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(843542258);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (date$default == null) {
                    composerStartRestartGroup.startReplaceGroup(380507587);
                    companion2 = DisplayMode.INSTANCE;
                    if (DisplayMode.m407equalsimpl0(i, companion2.m412getPickerjFl4v0())) {
                        composerStartRestartGroup.startReplaceGroup(843560257);
                        Strings.Companion companion6 = Strings.INSTANCE;
                        date$default = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_headline), composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    } else if (DisplayMode.m407equalsimpl0(i, companion2.m411getInputjFl4v0())) {
                        composerStartRestartGroup.startReplaceGroup(843562784);
                        Strings.Companion companion7 = Strings.INSTANCE;
                        date$default = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_headline), composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(380705954);
                        composerStartRestartGroup.endReplaceGroup();
                        date$default = "";
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(843557408);
                    composerStartRestartGroup.endReplaceGroup();
                }
                companion = DisplayMode.INSTANCE;
                if (DisplayMode.m407equalsimpl0(i, companion.m412getPickerjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(843570444);
                    Strings.Companion companion8 = Strings.INSTANCE;
                    strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_headline_description), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (DisplayMode.m407equalsimpl0(i, companion.m411getInputjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(843573323);
                    Strings.Companion companion9 = Strings.INSTANCE;
                    strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_headline_description), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(381043234);
                    composerStartRestartGroup.endReplaceGroup();
                }
                str = String.format(strM1471getString2EP1pXo, Arrays.copyOf(new Object[]{date}, 1));
                zChanged = composerStartRestartGroup.changed(str);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: y83
                        public final Object invoke(Object obj) {
                            return DatePickerDefaults.b(str, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                Modifier modifier6 = modifier5;
                TextKt.m1097TextNvy7gAk(date$default, SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null), j3, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, null, composer2, (i6 >> 6) & 896, 24576, 245752);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier6;
                j2 = j3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                j2 = headlineContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z83
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerDefaults.a(this.b, l2, i, datePickerFormatter2, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        modifier2 = modifier;
        if ((i2 & 24576) == 0) {
            if ((i3 & 16) == 0) {
                headlineContentColor = j;
                if (composerStartRestartGroup.changed(headlineContentColor)) {
                }
                i4 |= i8;
            } else {
                headlineContentColor = j;
            }
            i4 |= i8;
        } else {
            headlineContentColor = j;
        }
        if ((i3 & 32) != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i5 = 131072;
            } else {
                i5 = 65536;
            }
            i4 |= i5;
        }
        if ((74899 & i4) != 74898) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i7 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 16) != 0) {
                    headlineContentColor = colors(composerStartRestartGroup, (i4 >> 15) & 14).getHeadlineContentColor();
                    i4 &= -57345;
                }
                i6 = i4;
                j3 = headlineContentColor;
                modifier5 = modifier4;
            } else {
                if (i7 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 16) != 0) {
                    headlineContentColor = colors(composerStartRestartGroup, (i4 >> 15) & 14).getHeadlineContentColor();
                    i4 &= -57345;
                }
                i6 = i4;
                j3 = headlineContentColor;
                modifier5 = modifier4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1913724796, i6, -1, "androidx.compose.material3.DatePickerDefaults.DatePickerHeadline (DatePicker.kt:684)");
            }
            Locale localeDefaultLocale2 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
            date$default = DatePickerFormatter.formatDate$default(datePickerFormatter, l2, localeDefaultLocale2, false, 4, null);
            datePickerFormatter2 = datePickerFormatter;
            l2 = l2;
            date = datePickerFormatter2.formatDate(l2, localeDefaultLocale2, true);
            strM1471getString2EP1pXo = "";
            if (date == null) {
                composerStartRestartGroup.startReplaceGroup(380185931);
                companion3 = DisplayMode.INSTANCE;
                if (DisplayMode.m407equalsimpl0(i, companion3.m412getPickerjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(843549871);
                    Strings.Companion companion10 = Strings.INSTANCE;
                    date = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_no_selection_description), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (DisplayMode.m407equalsimpl0(i, companion3.m411getInputjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(843552842);
                    Strings.Companion companion11 = Strings.INSTANCE;
                    date = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_no_input_description), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(380407362);
                    composerStartRestartGroup.endReplaceGroup();
                    date = "";
                }
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(843542258);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (date$default == null) {
                composerStartRestartGroup.startReplaceGroup(380507587);
                companion2 = DisplayMode.INSTANCE;
                if (DisplayMode.m407equalsimpl0(i, companion2.m412getPickerjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(843560257);
                    Strings.Companion companion12 = Strings.INSTANCE;
                    date$default = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_headline), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (DisplayMode.m407equalsimpl0(i, companion2.m411getInputjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(843562784);
                    Strings.Companion companion13 = Strings.INSTANCE;
                    date$default = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_headline), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(380705954);
                    composerStartRestartGroup.endReplaceGroup();
                    date$default = "";
                }
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(843557408);
                composerStartRestartGroup.endReplaceGroup();
            }
            companion = DisplayMode.INSTANCE;
            if (DisplayMode.m407equalsimpl0(i, companion.m412getPickerjFl4v0())) {
                composerStartRestartGroup.startReplaceGroup(843570444);
                Strings.Companion companion14 = Strings.INSTANCE;
                strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_headline_description), composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else if (DisplayMode.m407equalsimpl0(i, companion.m411getInputjFl4v0())) {
                composerStartRestartGroup.startReplaceGroup(843573323);
                Strings.Companion companion15 = Strings.INSTANCE;
                strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_headline_description), composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(381043234);
                composerStartRestartGroup.endReplaceGroup();
            }
            str = String.format(strM1471getString2EP1pXo, Arrays.copyOf(new Object[]{date}, 1));
            zChanged = composerStartRestartGroup.changed(str);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue = new Function1() { // from class: y83
                    public final Object invoke(Object obj) {
                        return DatePickerDefaults.b(str, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function1() { // from class: y83
                    public final Object invoke(Object obj) {
                        return DatePickerDefaults.b(str, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composer2 = composerStartRestartGroup;
            Modifier modifier7 = modifier5;
            TextKt.m1097TextNvy7gAk(date$default, SemanticsModifierKt.semantics$default(modifier5, false, (Function1) objRememberedValue, 1, null), j3, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, null, composer2, (i6 >> 6) & 896, 24576, 245752);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier7;
            j2 = j3;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j2 = headlineContentColor;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z83
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerDefaults.a(this.b, l2, i, datePickerFormatter2, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x0049  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:34:0x005a  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:38:0x0063  */
    /* JADX WARN: Code duplicated, block: B:40:0x0067  */
    /* JADX WARN: Code duplicated, block: B:42:0x006d  */
    /* JADX WARN: Code duplicated, block: B:43:0x0070  */
    /* JADX WARN: Code duplicated, block: B:47:0x007a  */
    /* JADX WARN: Code duplicated, block: B:48:0x007c  */
    /* JADX WARN: Code duplicated, block: B:51:0x0085  */
    /* JADX WARN: Code duplicated, block: B:60:0x009f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:73:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:74:0x0116  */
    /* JADX WARN: Code duplicated, block: B:76:0x0120  */
    /* JADX WARN: Code duplicated, block: B:77:0x015f  */
    /* JADX WARN: Code duplicated, block: B:80:0x016e  */
    /* JADX WARN: Code duplicated, block: B:82:0x0173  */
    /* JADX WARN: Code duplicated, block: B:85:0x017e  */
    /* JADX WARN: Code duplicated, block: B:87:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DatePickerTitle-FNtVw6o, reason: not valid java name */
    public final void m359DatePickerTitleFNtVw6o(final int i, Modifier modifier, long j, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long j2;
        int i5;
        boolean z;
        final long j3;
        Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        long j4;
        DisplayMode.Companion companion;
        Composer composerStartRestartGroup = composer.startRestartGroup(-390880814);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i6 = i3 & 2;
        if (i6 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i3 & 4) == 0) {
                    j2 = j;
                    int i7 = composerStartRestartGroup.changed(j2) ? 256 : 128;
                    i4 |= i7;
                } else {
                    j2 = j;
                }
                i4 |= i7;
            } else {
                j2 = j;
            }
            if ((i3 & 8) != 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i4 |= i5;
            }
            if ((i4 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        long titleContentColor = colors(composerStartRestartGroup, (i4 >> 9) & 14).getTitleContentColor();
                        i4 &= -897;
                        j4 = titleContentColor;
                    } else {
                        j4 = j2;
                    }
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                    }
                    j4 = j2;
                    modifier3 = modifier2;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-390880814, i4, -1, "androidx.compose.material3.DatePickerDefaults.DatePickerTitle (DatePicker.kt:649)");
                }
                companion = DisplayMode.INSTANCE;
                if (DisplayMode.m407equalsimpl0(i, companion.m412getPickerjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(-1974299164);
                    Strings.Companion companion2 = Strings.INSTANCE;
                    TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_title), composerStartRestartGroup, 0), modifier3, j4, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, i4 & PointerIconCompat.TYPE_TEXT, 0, 262136);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (DisplayMode.m407equalsimpl0(i, companion.m411getInputjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(-1974291869);
                    Strings.Companion companion3 = Strings.INSTANCE;
                    TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_title), composerStartRestartGroup, 0), modifier3, j4, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, i4 & PointerIconCompat.TYPE_TEXT, 0, 262136);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1073325776);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                j3 = j2;
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier5 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a93
                    public final Object invoke(Object obj, Object obj2) {
                        return DatePickerDefaults.c(this.b, i, modifier5, j3, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i3 & 4) == 0) {
                j2 = j;
                if (composerStartRestartGroup.changed(j2)) {
                }
                i4 |= i7;
            } else {
                j2 = j;
            }
            i4 |= i7;
        } else {
            j2 = j;
        }
        if ((i3 & 8) != 0) {
            i4 |= 3072;
        } else if ((i2 & 3072) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i5 = 2048;
            } else {
                i5 = 1024;
            }
            i4 |= i5;
        }
        if ((i4 & 1171) != 1170) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i6 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    long titleContentColor2 = colors(composerStartRestartGroup, (i4 >> 9) & 14).getTitleContentColor();
                    i4 &= -897;
                    j4 = titleContentColor2;
                } else {
                    j4 = j2;
                }
                modifier3 = modifier4;
            } else {
                if (i6 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    long titleContentColor3 = colors(composerStartRestartGroup, (i4 >> 9) & 14).getTitleContentColor();
                    i4 &= -897;
                    j4 = titleContentColor3;
                } else {
                    j4 = j2;
                }
                modifier3 = modifier4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-390880814, i4, -1, "androidx.compose.material3.DatePickerDefaults.DatePickerTitle (DatePicker.kt:649)");
            }
            companion = DisplayMode.INSTANCE;
            if (DisplayMode.m407equalsimpl0(i, companion.m412getPickerjFl4v0())) {
                composerStartRestartGroup.startReplaceGroup(-1974299164);
                Strings.Companion companion4 = Strings.INSTANCE;
                TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_title), composerStartRestartGroup, 0), modifier3, j4, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, i4 & PointerIconCompat.TYPE_TEXT, 0, 262136);
                composerStartRestartGroup.endReplaceGroup();
            } else if (DisplayMode.m407equalsimpl0(i, companion.m411getInputjFl4v0())) {
                composerStartRestartGroup.startReplaceGroup(-1974291869);
                Strings.Companion companion5 = Strings.INSTANCE;
                TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_title), composerStartRestartGroup, 0), modifier3, j4, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, i4 & PointerIconCompat.TYPE_TEXT, 0, 262136);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1073325776);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = j4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            j3 = j2;
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a93
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerDefaults.c(this.b, i, modifier6, j3, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final DatePickerColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-275219611, i, -1, "androidx.compose.material3.DatePickerDefaults.colors (DatePicker.kt:447)");
        }
        DatePickerColors defaultDatePickerColors = getDefaultDatePickerColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultDatePickerColors;
    }

    /* JADX INFO: renamed from: colors-bSRYm20, reason: not valid java name */
    public final DatePickerColors m360colorsbSRYm20(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, TextFieldColors textFieldColors, Composer composer, int i, int i2, int i3, int i4) {
        long jM3170getUnspecified0d7_KjU = (i4 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i4 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i4 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i4 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i4 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i4 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        long jM3170getUnspecified0d7_KjU7 = (i4 & 64) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j7;
        long j25 = jM3170getUnspecified0d7_KjU;
        long jM3170getUnspecified0d7_KjU8 = (i4 & 128) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j8;
        long jM3170getUnspecified0d7_KjU9 = (i4 & 256) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j9;
        long jM3170getUnspecified0d7_KjU10 = (i4 & 512) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j10;
        long jM3170getUnspecified0d7_KjU11 = (i4 & 1024) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j11;
        long jM3170getUnspecified0d7_KjU12 = (i4 & 2048) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j12;
        long jM3170getUnspecified0d7_KjU13 = (i4 & 4096) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j13;
        long jM3170getUnspecified0d7_KjU14 = (i4 & 8192) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j14;
        long jM3170getUnspecified0d7_KjU15 = (i4 & 16384) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j15;
        long jM3170getUnspecified0d7_KjU16 = (i4 & 32768) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j16;
        long jM3170getUnspecified0d7_KjU17 = (i4 & 65536) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j17;
        long jM3170getUnspecified0d7_KjU18 = (i4 & 131072) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j18;
        long jM3170getUnspecified0d7_KjU19 = (i4 & 262144) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j19;
        long jM3170getUnspecified0d7_KjU20 = (i4 & 524288) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j20;
        long jM3170getUnspecified0d7_KjU21 = (i4 & 1048576) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j21;
        long jM3170getUnspecified0d7_KjU22 = (i4 & 2097152) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j22;
        long jM3170getUnspecified0d7_KjU23 = (i4 & 4194304) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j23;
        long jM3170getUnspecified0d7_KjU24 = (i4 & 8388608) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j24;
        TextFieldColors textFieldColors2 = (i4 & 16777216) != 0 ? null : textFieldColors;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1991626358, i, i2, "androidx.compose.material3.DatePickerDefaults.colors (DatePicker.kt:516)");
        }
        DatePickerColors datePickerColorsM333copytNwlRmA = getDefaultDatePickerColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i3 >> 12) & 112).m333copytNwlRmA(j25, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6, jM3170getUnspecified0d7_KjU7, jM3170getUnspecified0d7_KjU8, jM3170getUnspecified0d7_KjU9, jM3170getUnspecified0d7_KjU10, jM3170getUnspecified0d7_KjU11, jM3170getUnspecified0d7_KjU12, jM3170getUnspecified0d7_KjU13, jM3170getUnspecified0d7_KjU14, jM3170getUnspecified0d7_KjU15, jM3170getUnspecified0d7_KjU16, jM3170getUnspecified0d7_KjU17, jM3170getUnspecified0d7_KjU18, jM3170getUnspecified0d7_KjU19, jM3170getUnspecified0d7_KjU20, jM3170getUnspecified0d7_KjU21, jM3170getUnspecified0d7_KjU23, jM3170getUnspecified0d7_KjU22, jM3170getUnspecified0d7_KjU24, textFieldColors2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return datePickerColorsM333copytNwlRmA;
    }

    public final DatePickerFormatter dateFormatter(String yearSelectionSkeleton, String selectedDateSkeleton, String selectedDateDescriptionSkeleton) {
        return new DatePickerFormatterImpl(yearSelectionSkeleton, selectedDateSkeleton, selectedDateDescriptionSkeleton);
    }

    public final SelectableDates getAllDates() {
        return AllDates;
    }

    public final DatePickerColors getDefaultDatePickerColors(ColorScheme colorScheme, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1180555308, i, -1, "androidx.compose.material3.DatePickerDefaults.<get-defaultDatePickerColors> (DatePicker.kt:546)");
        }
        DatePickerColors defaultDatePickerColorsCached = colorScheme.getDefaultDatePickerColorsCached();
        if (defaultDatePickerColorsCached == null) {
            composer.startReplaceGroup(642416503);
            DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
            DatePickerColors datePickerColors = new DatePickerColors(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getHeaderSupportingTextColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getHeaderHeadlineColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getWeekdaysLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getRangeSelectionMonthSubheadColor()), colorScheme.getOnSurfaceVariant(), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearUnselectedLabelTextColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearUnselectedLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateTodayLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearSelectedLabelTextColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearSelectedLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearSelectedContainerColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearSelectedContainerColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateUnselectedLabelTextColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateUnselectedLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateSelectedLabelTextColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateSelectedLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateSelectedContainerColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateSelectedContainerColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateTodayLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateTodayContainerOutlineColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getRangeSelectionActiveIndicatorContainerColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionDateInRangeLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, DividerTokens.INSTANCE.getColor()), OutlinedTextFieldDefaults.INSTANCE.getDefaultOutlinedTextFieldColors(colorScheme, composer, (i & 14) | 48), null);
            colorScheme.setDefaultDatePickerColorsCached$material3(datePickerColors);
            composer.endReplaceGroup();
            defaultDatePickerColorsCached = datePickerColors;
        } else {
            composer.startReplaceGroup(642290457);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultDatePickerColorsCached;
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(700927667, i, -1, "androidx.compose.material3.DatePickerDefaults.<get-shape> (DatePicker.kt:770)");
        }
        Shape value = ShapesKt.getValue(DatePickerModalTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getTonalElevation-D9Ej5fM, reason: not valid java name */
    public final float m361getTonalElevationD9Ej5fM() {
        return TonalElevation;
    }

    public final IntRange getYearRange() {
        return YearRange;
    }

    public final FlingBehavior rememberSnapFlingBehavior$material3(LazyListState lazyListState, DecayAnimationSpec<Float> decayAnimationSpec, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            decayAnimationSpec = DecayAnimationSpecKt.exponentialDecay$default(0.0f, 0.0f, 3, (Object) null);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2036003494, i, -1, "androidx.compose.material3.DatePickerDefaults.rememberSnapFlingBehavior (DatePicker.kt:741)");
        }
        FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer, 6);
        boolean zChanged = ((((i & 14) ^ 6) > 4 && composer.changed(lazyListState)) || (i & 6) == 4) | composer.changed(decayAnimationSpec);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final SnapLayoutInfoProvider snapLayoutInfoProviderSnapLayoutInfoProvider$default = LazyListSnapLayoutInfoProviderKt.SnapLayoutInfoProvider$default(lazyListState, (SnapPosition) null, 2, (Object) null);
            objRememberedValue = SnapFlingBehaviorKt.snapFlingBehavior(new SnapLayoutInfoProvider() { // from class: androidx.compose.material3.DatePickerDefaults$rememberSnapFlingBehavior$1$snapLayoutInfoProvider$1
                public float calculateApproachOffset(float velocity, float decayOffset) {
                    return 0.0f;
                }

                public float calculateSnapOffset(float velocity) {
                    return snapLayoutInfoProviderSnapLayoutInfoProvider$default.calculateSnapOffset(velocity);
                }
            }, decayAnimationSpec, finiteAnimationSpecValue);
            composer.updateRememberedValue(objRememberedValue);
        }
        TargetedFlingBehavior targetedFlingBehavior = (TargetedFlingBehavior) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return targetedFlingBehavior;
    }
}
