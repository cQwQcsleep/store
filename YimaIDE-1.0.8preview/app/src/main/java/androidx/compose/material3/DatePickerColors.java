package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.material3.DatePickerColors;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001BÏ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\u0089\u0002\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b<\u0010=J!\u0010>\u001a\u00020\u001c*\u0004\u0018\u00010\u001c2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u001c0@H\u0000¢\u0006\u0002\bAJ5\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020D2\u0006\u0010G\u001a\u00020DH\u0001¢\u0006\u0004\bH\u0010IJ-\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00030B2\u0006\u0010E\u001a\u00020D2\u0006\u0010G\u001a\u00020D2\u0006\u0010K\u001a\u00020DH\u0001¢\u0006\u0004\bL\u0010MJ-\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030B2\u0006\u0010N\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010G\u001a\u00020DH\u0001¢\u0006\u0004\bO\u0010MJ%\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00030B2\u0006\u0010E\u001a\u00020D2\u0006\u0010G\u001a\u00020DH\u0001¢\u0006\u0004\bQ\u0010RJ\u0013\u0010S\u001a\u00020D2\b\u0010T\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010U\u001a\u00020VH\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\"\u0010 R\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b#\u0010 R\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b$\u0010 R\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b%\u0010 R\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b&\u0010 R\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b'\u0010 R\u0013\u0010\n\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b(\u0010 R\u0013\u0010\u000b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b)\u0010 R\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b*\u0010 R\u0013\u0010\r\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b+\u0010 R\u0013\u0010\u000e\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b,\u0010 R\u0013\u0010\u000f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b-\u0010 R\u0013\u0010\u0010\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b.\u0010 R\u0013\u0010\u0011\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b/\u0010 R\u0013\u0010\u0012\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b0\u0010 R\u0013\u0010\u0013\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b1\u0010 R\u0013\u0010\u0014\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b2\u0010 R\u0013\u0010\u0015\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b3\u0010 R\u0013\u0010\u0016\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b4\u0010 R\u0013\u0010\u0017\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b5\u0010 R\u0013\u0010\u0018\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b6\u0010 R\u0013\u0010\u0019\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b7\u0010 R\u0013\u0010\u001a\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010!\u001a\u0004\b8\u0010 R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:¨\u0006W"}, d2 = {"Landroidx/compose/material3/DatePickerColors;", "", "containerColor", "Landroidx/compose/ui/graphics/Color;", "titleContentColor", "headlineContentColor", "weekdayContentColor", "subheadContentColor", "navigationContentColor", "yearContentColor", "disabledYearContentColor", "currentYearContentColor", "selectedYearContentColor", "disabledSelectedYearContentColor", "selectedYearContainerColor", "disabledSelectedYearContainerColor", "dayContentColor", "disabledDayContentColor", "selectedDayContentColor", "disabledSelectedDayContentColor", "selectedDayContainerColor", "disabledSelectedDayContainerColor", "todayContentColor", "todayDateBorderColor", "dayInSelectionRangeContainerColor", "dayInSelectionRangeContentColor", "dividerColor", "dateTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "<init>", "(JJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/material3/TextFieldColors;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getContainerColor-0d7_KjU", "()J", "J", "getTitleContentColor-0d7_KjU", "getHeadlineContentColor-0d7_KjU", "getWeekdayContentColor-0d7_KjU", "getSubheadContentColor-0d7_KjU", "getNavigationContentColor-0d7_KjU", "getYearContentColor-0d7_KjU", "getDisabledYearContentColor-0d7_KjU", "getCurrentYearContentColor-0d7_KjU", "getSelectedYearContentColor-0d7_KjU", "getDisabledSelectedYearContentColor-0d7_KjU", "getSelectedYearContainerColor-0d7_KjU", "getDisabledSelectedYearContainerColor-0d7_KjU", "getDayContentColor-0d7_KjU", "getDisabledDayContentColor-0d7_KjU", "getSelectedDayContentColor-0d7_KjU", "getDisabledSelectedDayContentColor-0d7_KjU", "getSelectedDayContainerColor-0d7_KjU", "getDisabledSelectedDayContainerColor-0d7_KjU", "getTodayContentColor-0d7_KjU", "getTodayDateBorderColor-0d7_KjU", "getDayInSelectionRangeContainerColor-0d7_KjU", "getDayInSelectionRangeContentColor-0d7_KjU", "getDividerColor-0d7_KjU", "getDateTextFieldColors", "()Landroidx/compose/material3/TextFieldColors;", "copy", "copy-tNwlRmA", "(JJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/material3/TextFieldColors;)Landroidx/compose/material3/DatePickerColors;", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse$material3", "Landroidx/compose/runtime/State;", "isToday", "", "selected", "inRange", "enabled", "dayContentColor$material3", "(ZZZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "dayContainerColor", "animate", "dayContainerColor$material3", "(ZZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "currentYear", "yearContentColor$material3", "yearContainerColor", "yearContainerColor$material3", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "equals", "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DatePickerColors {
    public static final int $stable = 0;
    private final long containerColor;
    private final long currentYearContentColor;
    private final TextFieldColors dateTextFieldColors;
    private final long dayContentColor;
    private final long dayInSelectionRangeContainerColor;
    private final long dayInSelectionRangeContentColor;
    private final long disabledDayContentColor;
    private final long disabledSelectedDayContainerColor;
    private final long disabledSelectedDayContentColor;
    private final long disabledSelectedYearContainerColor;
    private final long disabledSelectedYearContentColor;
    private final long disabledYearContentColor;
    private final long dividerColor;
    private final long headlineContentColor;
    private final long navigationContentColor;
    private final long selectedDayContainerColor;
    private final long selectedDayContentColor;
    private final long selectedYearContainerColor;
    private final long selectedYearContentColor;
    private final long subheadContentColor;
    private final long titleContentColor;
    private final long todayContentColor;
    private final long todayDateBorderColor;
    private final long weekdayContentColor;
    private final long yearContentColor;

    private DatePickerColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, TextFieldColors textFieldColors) {
        this.containerColor = j;
        this.titleContentColor = j2;
        this.headlineContentColor = j3;
        this.weekdayContentColor = j4;
        this.subheadContentColor = j5;
        this.navigationContentColor = j6;
        this.yearContentColor = j7;
        this.disabledYearContentColor = j8;
        this.currentYearContentColor = j9;
        this.selectedYearContentColor = j10;
        this.disabledSelectedYearContentColor = j11;
        this.selectedYearContainerColor = j12;
        this.disabledSelectedYearContainerColor = j13;
        this.dayContentColor = j14;
        this.disabledDayContentColor = j15;
        this.selectedDayContentColor = j16;
        this.disabledSelectedDayContentColor = j17;
        this.selectedDayContainerColor = j18;
        this.disabledSelectedDayContainerColor = j19;
        this.todayContentColor = j20;
        this.todayDateBorderColor = j21;
        this.dayInSelectionRangeContainerColor = j22;
        this.dayInSelectionRangeContentColor = j23;
        this.dividerColor = j24;
        this.dateTextFieldColors = textFieldColors;
    }

    public static TextFieldColors a(DatePickerColors datePickerColors) {
        return datePickerColors.dateTextFieldColors;
    }

    /* JADX INFO: renamed from: copy-tNwlRmA, reason: not valid java name */
    public final DatePickerColors m333copytNwlRmA(long containerColor, long titleContentColor, long headlineContentColor, long weekdayContentColor, long subheadContentColor, long navigationContentColor, long yearContentColor, long disabledYearContentColor, long currentYearContentColor, long selectedYearContentColor, long disabledSelectedYearContentColor, long selectedYearContainerColor, long disabledSelectedYearContainerColor, long dayContentColor, long disabledDayContentColor, long selectedDayContentColor, long disabledSelectedDayContentColor, long selectedDayContainerColor, long disabledSelectedDayContainerColor, long todayContentColor, long todayDateBorderColor, long dayInSelectionRangeContainerColor, long dayInSelectionRangeContentColor, long dividerColor, TextFieldColors dateTextFieldColors) {
        return new DatePickerColors(containerColor != 16 ? containerColor : this.containerColor, titleContentColor != 16 ? titleContentColor : this.titleContentColor, headlineContentColor != 16 ? headlineContentColor : this.headlineContentColor, weekdayContentColor != 16 ? weekdayContentColor : this.weekdayContentColor, subheadContentColor != 16 ? subheadContentColor : this.subheadContentColor, navigationContentColor != 16 ? navigationContentColor : this.navigationContentColor, yearContentColor != 16 ? yearContentColor : this.yearContentColor, disabledYearContentColor != 16 ? disabledYearContentColor : this.disabledYearContentColor, currentYearContentColor != 16 ? currentYearContentColor : this.currentYearContentColor, selectedYearContentColor != 16 ? selectedYearContentColor : this.selectedYearContentColor, disabledSelectedYearContentColor != 16 ? disabledSelectedYearContentColor : this.disabledSelectedYearContentColor, selectedYearContainerColor != 16 ? selectedYearContainerColor : this.selectedYearContainerColor, disabledSelectedYearContainerColor != 16 ? disabledSelectedYearContainerColor : this.disabledSelectedYearContainerColor, dayContentColor != 16 ? dayContentColor : this.dayContentColor, disabledDayContentColor != 16 ? disabledDayContentColor : this.disabledDayContentColor, selectedDayContentColor != 16 ? selectedDayContentColor : this.selectedDayContentColor, disabledSelectedDayContentColor != 16 ? disabledSelectedDayContentColor : this.disabledSelectedDayContentColor, selectedDayContainerColor != 16 ? selectedDayContainerColor : this.selectedDayContainerColor, disabledSelectedDayContainerColor != 16 ? disabledSelectedDayContainerColor : this.disabledSelectedDayContainerColor, todayContentColor != 16 ? todayContentColor : this.todayContentColor, todayDateBorderColor != 16 ? todayDateBorderColor : this.todayDateBorderColor, dayInSelectionRangeContainerColor != 16 ? dayInSelectionRangeContainerColor : this.dayInSelectionRangeContainerColor, dayInSelectionRangeContentColor != 16 ? dayInSelectionRangeContentColor : this.dayInSelectionRangeContentColor, dividerColor != 16 ? dividerColor : this.dividerColor, takeOrElse$material3(dateTextFieldColors, new Function0() { // from class: x83
            public final Object invoke() {
                return DatePickerColors.a(this.b);
            }
        }), null);
    }

    public final State<Color> dayContainerColor$material3(boolean z, boolean z2, boolean z3, Composer composer, int i) {
        long jM3169getTransparent0d7_KjU;
        State<Color> stateRememberUpdatedState;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1240482658, i, -1, "androidx.compose.material3.DatePickerColors.dayContainerColor (DatePicker.kt:976)");
        }
        if (z) {
            jM3169getTransparent0d7_KjU = z2 ? this.selectedDayContainerColor : this.disabledSelectedDayContainerColor;
        } else {
            jM3169getTransparent0d7_KjU = Color.INSTANCE.m3169getTransparent0d7_KjU();
        }
        long j = jM3169getTransparent0d7_KjU;
        if (z3) {
            composer.startReplaceGroup(-1319856736);
            stateRememberUpdatedState = SingleValueAnimationKt.animateColorAsState-euL9pac(j, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer, 6), (String) null, (Function1) null, composer, 0, 12);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1319630064);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3124boximpl(j), composer, 0);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateRememberUpdatedState;
    }

    public final State<Color> dayContentColor$material3(boolean z, boolean z2, boolean z3, boolean z4, Composer composer, int i) {
        long j;
        State<Color> stateRememberUpdatedState;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1233694918, i, -1, "androidx.compose.material3.DatePickerColors.dayContentColor (DatePicker.kt:940)");
        }
        if (z2 && z4) {
            j = this.selectedDayContentColor;
        } else if (z2 && !z4) {
            j = this.disabledSelectedDayContentColor;
        } else if (z3 && z4) {
            j = this.dayInSelectionRangeContentColor;
        } else if (z3 && !z4) {
            j = this.disabledDayContentColor;
        } else if (z && z4) {
            j = this.todayContentColor;
        } else {
            j = z4 ? this.dayContentColor : this.disabledDayContentColor;
        }
        long j2 = j;
        if (z3) {
            composer.startReplaceGroup(-969483020);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3124boximpl(j2), composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-969417610);
            stateRememberUpdatedState = SingleValueAnimationKt.animateColorAsState-euL9pac(j2, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer, 6), (String) null, (Function1) null, composer, 0, 12);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateRememberUpdatedState;
    }

    public boolean equals(Object other) {
        if (!(other instanceof DatePickerColors)) {
            return false;
        }
        DatePickerColors datePickerColors = (DatePickerColors) other;
        return Color.m3135equalsimpl0(this.containerColor, datePickerColors.containerColor) && Color.m3135equalsimpl0(this.titleContentColor, datePickerColors.titleContentColor) && Color.m3135equalsimpl0(this.headlineContentColor, datePickerColors.headlineContentColor) && Color.m3135equalsimpl0(this.weekdayContentColor, datePickerColors.weekdayContentColor) && Color.m3135equalsimpl0(this.subheadContentColor, datePickerColors.subheadContentColor) && Color.m3135equalsimpl0(this.yearContentColor, datePickerColors.yearContentColor) && Color.m3135equalsimpl0(this.disabledYearContentColor, datePickerColors.disabledYearContentColor) && Color.m3135equalsimpl0(this.currentYearContentColor, datePickerColors.currentYearContentColor) && Color.m3135equalsimpl0(this.selectedYearContentColor, datePickerColors.selectedYearContentColor) && Color.m3135equalsimpl0(this.disabledSelectedYearContentColor, datePickerColors.disabledSelectedYearContentColor) && Color.m3135equalsimpl0(this.selectedYearContainerColor, datePickerColors.selectedYearContainerColor) && Color.m3135equalsimpl0(this.disabledSelectedYearContainerColor, datePickerColors.disabledSelectedYearContainerColor) && Color.m3135equalsimpl0(this.dayContentColor, datePickerColors.dayContentColor) && Color.m3135equalsimpl0(this.disabledDayContentColor, datePickerColors.disabledDayContentColor) && Color.m3135equalsimpl0(this.selectedDayContentColor, datePickerColors.selectedDayContentColor) && Color.m3135equalsimpl0(this.disabledSelectedDayContentColor, datePickerColors.disabledSelectedDayContentColor) && Color.m3135equalsimpl0(this.selectedDayContainerColor, datePickerColors.selectedDayContainerColor) && Color.m3135equalsimpl0(this.disabledSelectedDayContainerColor, datePickerColors.disabledSelectedDayContainerColor) && Color.m3135equalsimpl0(this.todayContentColor, datePickerColors.todayContentColor) && Color.m3135equalsimpl0(this.todayDateBorderColor, datePickerColors.todayDateBorderColor) && Color.m3135equalsimpl0(this.dayInSelectionRangeContainerColor, datePickerColors.dayInSelectionRangeContainerColor) && Color.m3135equalsimpl0(this.dayInSelectionRangeContentColor, datePickerColors.dayInSelectionRangeContentColor);
    }

    /* JADX INFO: renamed from: getContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContainerColor() {
        return this.containerColor;
    }

    /* JADX INFO: renamed from: getCurrentYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCurrentYearContentColor() {
        return this.currentYearContentColor;
    }

    public final TextFieldColors getDateTextFieldColors() {
        return this.dateTextFieldColors;
    }

    /* JADX INFO: renamed from: getDayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDayContentColor() {
        return this.dayContentColor;
    }

    /* JADX INFO: renamed from: getDayInSelectionRangeContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDayInSelectionRangeContainerColor() {
        return this.dayInSelectionRangeContainerColor;
    }

    /* JADX INFO: renamed from: getDayInSelectionRangeContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDayInSelectionRangeContentColor() {
        return this.dayInSelectionRangeContentColor;
    }

    /* JADX INFO: renamed from: getDisabledDayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledDayContentColor() {
        return this.disabledDayContentColor;
    }

    /* JADX INFO: renamed from: getDisabledSelectedDayContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSelectedDayContainerColor() {
        return this.disabledSelectedDayContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledSelectedDayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSelectedDayContentColor() {
        return this.disabledSelectedDayContentColor;
    }

    /* JADX INFO: renamed from: getDisabledSelectedYearContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSelectedYearContainerColor() {
        return this.disabledSelectedYearContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledSelectedYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSelectedYearContentColor() {
        return this.disabledSelectedYearContentColor;
    }

    /* JADX INFO: renamed from: getDisabledYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledYearContentColor() {
        return this.disabledYearContentColor;
    }

    /* JADX INFO: renamed from: getDividerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDividerColor() {
        return this.dividerColor;
    }

    /* JADX INFO: renamed from: getHeadlineContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getHeadlineContentColor() {
        return this.headlineContentColor;
    }

    /* JADX INFO: renamed from: getNavigationContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getNavigationContentColor() {
        return this.navigationContentColor;
    }

    /* JADX INFO: renamed from: getSelectedDayContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedDayContainerColor() {
        return this.selectedDayContainerColor;
    }

    /* JADX INFO: renamed from: getSelectedDayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedDayContentColor() {
        return this.selectedDayContentColor;
    }

    /* JADX INFO: renamed from: getSelectedYearContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedYearContainerColor() {
        return this.selectedYearContainerColor;
    }

    /* JADX INFO: renamed from: getSelectedYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedYearContentColor() {
        return this.selectedYearContentColor;
    }

    /* JADX INFO: renamed from: getSubheadContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSubheadContentColor() {
        return this.subheadContentColor;
    }

    /* JADX INFO: renamed from: getTitleContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTitleContentColor() {
        return this.titleContentColor;
    }

    /* JADX INFO: renamed from: getTodayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTodayContentColor() {
        return this.todayContentColor;
    }

    /* JADX INFO: renamed from: getTodayDateBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTodayDateBorderColor() {
        return this.todayDateBorderColor;
    }

    /* JADX INFO: renamed from: getWeekdayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getWeekdayContentColor() {
        return this.weekdayContentColor;
    }

    /* JADX INFO: renamed from: getYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getYearContentColor() {
        return this.yearContentColor;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((Color.m3141hashCodeimpl(this.containerColor) * 31) + Color.m3141hashCodeimpl(this.titleContentColor)) * 31) + Color.m3141hashCodeimpl(this.headlineContentColor)) * 31) + Color.m3141hashCodeimpl(this.weekdayContentColor)) * 31) + Color.m3141hashCodeimpl(this.subheadContentColor)) * 31) + Color.m3141hashCodeimpl(this.yearContentColor)) * 31) + Color.m3141hashCodeimpl(this.disabledYearContentColor)) * 31) + Color.m3141hashCodeimpl(this.currentYearContentColor)) * 31) + Color.m3141hashCodeimpl(this.selectedYearContentColor)) * 31) + Color.m3141hashCodeimpl(this.disabledSelectedYearContentColor)) * 31) + Color.m3141hashCodeimpl(this.selectedYearContainerColor)) * 31) + Color.m3141hashCodeimpl(this.disabledSelectedYearContainerColor)) * 31) + Color.m3141hashCodeimpl(this.dayContentColor)) * 31) + Color.m3141hashCodeimpl(this.disabledDayContentColor)) * 31) + Color.m3141hashCodeimpl(this.selectedDayContentColor)) * 31) + Color.m3141hashCodeimpl(this.disabledSelectedDayContentColor)) * 31) + Color.m3141hashCodeimpl(this.selectedDayContainerColor)) * 31) + Color.m3141hashCodeimpl(this.disabledSelectedDayContainerColor)) * 31) + Color.m3141hashCodeimpl(this.todayContentColor)) * 31) + Color.m3141hashCodeimpl(this.todayDateBorderColor)) * 31) + Color.m3141hashCodeimpl(this.dayInSelectionRangeContainerColor)) * 31) + Color.m3141hashCodeimpl(this.dayInSelectionRangeContentColor);
    }

    public final TextFieldColors takeOrElse$material3(TextFieldColors textFieldColors, Function0<TextFieldColors> function0) {
        return textFieldColors == null ? (TextFieldColors) function0.invoke() : textFieldColors;
    }

    public final State<Color> yearContainerColor$material3(boolean z, boolean z2, Composer composer, int i) {
        long jM3169getTransparent0d7_KjU;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1306331107, i, -1, "androidx.compose.material3.DatePickerColors.yearContainerColor (DatePicker.kt:1030)");
        }
        if (z) {
            jM3169getTransparent0d7_KjU = z2 ? this.selectedYearContainerColor : this.disabledSelectedYearContainerColor;
        } else {
            jM3169getTransparent0d7_KjU = Color.INSTANCE.m3169getTransparent0d7_KjU();
        }
        State<Color> state = SingleValueAnimationKt.animateColorAsState-euL9pac(jM3169getTransparent0d7_KjU, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer, 6), (String) null, (Function1) null, composer, 0, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return state;
    }

    public final State<Color> yearContentColor$material3(boolean z, boolean z2, boolean z3, Composer composer, int i) {
        long j;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(874111097, i, -1, "androidx.compose.material3.DatePickerColors.yearContentColor (DatePicker.kt:1006)");
        }
        if (z2 && z3) {
            j = this.selectedYearContentColor;
        } else if (z2 && !z3) {
            j = this.disabledSelectedYearContentColor;
        } else if (z && z3) {
            j = this.currentYearContentColor;
        } else {
            j = z3 ? this.yearContentColor : this.disabledYearContentColor;
        }
        State<Color> state = SingleValueAnimationKt.animateColorAsState-euL9pac(j, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer, 6), (String) null, (Function1) null, composer, 0, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return state;
    }

    public /* synthetic */ DatePickerColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, TextFieldColors textFieldColors, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, textFieldColors);
    }
}
