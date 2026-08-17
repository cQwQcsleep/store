package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0080\b\u001a\u0019\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\b\u001aC\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001aO\u0010\u0014\u001a\u00020\u00072\n\u0010\u0015\u001a\u00060\u0016j\u0002`\u00172\b\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001aM\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001aY\u0010 \u001a\u00020\u001b2\n\u0010\u0015\u001a\u00060\u0016j\u0002`\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0004\b!\u0010\"\u001a\u0016\u0010#\u001a\u00020$*\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010\tH\u0007\u001a\u000e\u0010&\u001a\u0004\u0018\u00010\t*\u00020\u0007H\u0007\u001a\u0014\u0010'\u001a\u00020$*\u00020\u00072\u0006\u0010(\u001a\u00020\u000bH\u0007\u001a\f\u0010)\u001a\u00020\u000b*\u00020\u0007H\u0007\u001a \u0010*\u001a\u00020$*\u00020\u001b2\b\u0010+\u001a\u0004\u0018\u00010\t2\b\u0010,\u001a\u0004\u0018\u00010\tH\u0007\u001a\u000e\u0010-\u001a\u0004\u0018\u00010\t*\u00020\u001bH\u0007\u001a\u000e\u0010.\u001a\u0004\u0018\u00010\t*\u00020\u001bH\u0007\u001a\u0014\u0010'\u001a\u00020$*\u00020\u001b2\u0006\u0010(\u001a\u00020\u000bH\u0007\u001a\f\u0010)\u001a\u00020\u000b*\u00020\u001bH\u0007\u001a\u0010\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u000201H\u0003\u001a\u0010\u00102\u001a\u0002012\u0006\u0010(\u001a\u00020\u000bH\u0003\u001a\u0019\u00103\u001a\u0004\u0018\u00010\t2\b\u00104\u001a\u0004\u0018\u000101H\u0003¢\u0006\u0002\u00105\u001a\u0019\u00106\u001a\u0004\u0018\u0001012\b\u0010%\u001a\u0004\u0018\u00010\tH\u0003¢\u0006\u0002\u00107¨\u00068"}, d2 = {"formatDatePickerNavigateToYearString", "", "template", "localizedYear", "formatHeadlineDescription", "verboseDateDescription", "rememberDatePickerState", "Landroidx/compose/material3/DatePickerState;", "initialSelectedDate", "Ljava/time/LocalDate;", "initialDisplayedMonth", "Ljava/time/YearMonth;", "yearRange", "Lkotlin/ranges/IntRange;", "initialDisplayMode", "Landroidx/compose/material3/DisplayMode;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "rememberDatePickerState-EU0dCGE", "(Ljava/time/LocalDate;Ljava/time/YearMonth;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DatePickerState;", "DatePickerState", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DatePickerState-sHin3Bw", "(Ljava/util/Locale;Ljava/time/LocalDate;Ljava/time/YearMonth;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DatePickerState;", "rememberDateRangePickerState", "Landroidx/compose/material3/DateRangePickerState;", "initialSelectedStartDate", "initialSelectedEndDate", "rememberDateRangePickerState-IlFM19s", "(Ljava/time/LocalDate;Ljava/time/LocalDate;Ljava/time/YearMonth;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DateRangePickerState;", "DateRangePickerState", "DateRangePickerState-HVP43zI", "(Ljava/util/Locale;Ljava/time/LocalDate;Ljava/time/LocalDate;Ljava/time/YearMonth;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DateRangePickerState;", "setSelectedDate", "", "date", "getSelectedDate", "setDisplayedMonth", "yearMonth", "getDisplayedMonth", "setSelection", "startDate", "endDate", "getSelectedStartDate", "getSelectedEndDate", "getYearMonth", "millisUtcFirstOfMonth", "", "getYearMonthMillisUtc", "getLocalDate", "millisUtc", "(Ljava/lang/Long;)Ljava/time/LocalDate;", "getLocalDateMillisUtc", "(Ljava/time/LocalDate;)Ljava/lang/Long;", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DatePicker_jvmKt {
    /* JADX INFO: renamed from: DatePickerState-sHin3Bw, reason: not valid java name */
    public static final DatePickerState m374DatePickerStatesHin3Bw(Locale locale, LocalDate localDate, YearMonth yearMonth, IntRange intRange, int i, SelectableDates selectableDates) {
        return DatePickerKt.m365DatePickerStatesHin3Bw(locale, getLocalDateMillisUtc(localDate), yearMonth != null ? Long.valueOf(getYearMonthMillisUtc(yearMonth)) : null, intRange, i, selectableDates);
    }

    /* JADX INFO: renamed from: DatePickerState-sHin3Bw$default, reason: not valid java name */
    public static /* synthetic */ DatePickerState m375DatePickerStatesHin3Bw$default(Locale locale, LocalDate localDate, YearMonth yearMonth, IntRange intRange, int i, SelectableDates selectableDates, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            yearMonth = localDate != null ? YearMonth.from(localDate) : null;
        }
        YearMonth yearMonth2 = yearMonth;
        if ((i2 & 8) != 0) {
            intRange = DatePickerDefaults.INSTANCE.getYearRange();
        }
        IntRange intRange2 = intRange;
        if ((i2 & 16) != 0) {
            i = DisplayMode.INSTANCE.m412getPickerjFl4v0();
        }
        int i3 = i;
        if ((i2 & 32) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        return m374DatePickerStatesHin3Bw(locale, localDate, yearMonth2, intRange2, i3, selectableDates);
    }

    /* JADX INFO: renamed from: DateRangePickerState-HVP43zI, reason: not valid java name */
    public static final DateRangePickerState m376DateRangePickerStateHVP43zI(Locale locale, LocalDate localDate, LocalDate localDate2, YearMonth yearMonth, IntRange intRange, int i, SelectableDates selectableDates) {
        return DateRangePickerKt.m383DateRangePickerStateHVP43zI(locale, getLocalDateMillisUtc(localDate), getLocalDateMillisUtc(localDate2), yearMonth != null ? Long.valueOf(getYearMonthMillisUtc(yearMonth)) : null, intRange, i, selectableDates);
    }

    /* JADX INFO: renamed from: DateRangePickerState-HVP43zI$default, reason: not valid java name */
    public static /* synthetic */ DateRangePickerState m377DateRangePickerStateHVP43zI$default(Locale locale, LocalDate localDate, LocalDate localDate2, YearMonth yearMonth, IntRange intRange, int i, SelectableDates selectableDates, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            yearMonth = localDate != null ? YearMonth.from(localDate) : null;
        }
        YearMonth yearMonth2 = yearMonth;
        if ((i2 & 16) != 0) {
            intRange = DatePickerDefaults.INSTANCE.getYearRange();
        }
        IntRange intRange2 = intRange;
        if ((i2 & 32) != 0) {
            i = DisplayMode.INSTANCE.m412getPickerjFl4v0();
        }
        int i3 = i;
        if ((i2 & 64) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        return m376DateRangePickerStateHVP43zI(locale, localDate, localDate2, yearMonth2, intRange2, i3, selectableDates);
    }

    public static final String formatDatePickerNavigateToYearString(String str, String str2) {
        return String.format(str, Arrays.copyOf(new Object[]{str2}, 1));
    }

    public static final String formatHeadlineDescription(String str, String str2) {
        return String.format(str, Arrays.copyOf(new Object[]{str2}, 1));
    }

    public static final YearMonth getDisplayedMonth(DatePickerState datePickerState) {
        return getYearMonth(datePickerState.getDisplayedMonthMillis());
    }

    private static final LocalDate getLocalDate(Long l) {
        if (l == null) {
            return null;
        }
        return Instant.ofEpochMilli(l.longValue()).atZone(ZoneOffset.UTC).toLocalDate();
    }

    private static final Long getLocalDateMillisUtc(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Long.valueOf(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
    }

    public static final LocalDate getSelectedDate(DatePickerState datePickerState) {
        return getLocalDate(datePickerState.getSelectedDateMillis());
    }

    public static final LocalDate getSelectedEndDate(DateRangePickerState dateRangePickerState) {
        return getLocalDate(dateRangePickerState.getSelectedEndDateMillis());
    }

    public static final LocalDate getSelectedStartDate(DateRangePickerState dateRangePickerState) {
        return getLocalDate(dateRangePickerState.getSelectedStartDateMillis());
    }

    private static final YearMonth getYearMonth(long j) {
        return YearMonth.from(Instant.ofEpochMilli(j).atZone(ZoneOffset.UTC));
    }

    private static final long getYearMonthMillisUtc(YearMonth yearMonth) {
        return yearMonth.atDay(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    /* JADX INFO: renamed from: rememberDatePickerState-EU0dCGE, reason: not valid java name */
    public static final DatePickerState m378rememberDatePickerStateEU0dCGE(LocalDate localDate, YearMonth yearMonth, IntRange intRange, int i, SelectableDates selectableDates, Composer composer, int i2, int i3) {
        if ((i3 & 2) != 0) {
            yearMonth = localDate != null ? YearMonth.from(localDate) : null;
        }
        if ((i3 & 4) != 0) {
            intRange = DatePickerDefaults.INSTANCE.getYearRange();
        }
        IntRange intRange2 = intRange;
        if ((i3 & 8) != 0) {
            i = DisplayMode.INSTANCE.m412getPickerjFl4v0();
        }
        int i4 = i;
        SelectableDates allDates = (i3 & 16) != 0 ? DatePickerDefaults.INSTANCE.getAllDates() : selectableDates;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1802387829, i2, -1, "androidx.compose.material3.rememberDatePickerState (DatePicker.jvm.kt:70)");
        }
        DatePickerState datePickerStateM370rememberDatePickerStateEU0dCGE = DatePickerKt.m370rememberDatePickerStateEU0dCGE(localDate != null ? getLocalDateMillisUtc(localDate) : null, yearMonth != null ? Long.valueOf(getYearMonthMillisUtc(yearMonth)) : null, intRange2, i4, allDates, composer, i2 & 65408, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return datePickerStateM370rememberDatePickerStateEU0dCGE;
    }

    /* JADX INFO: renamed from: rememberDateRangePickerState-IlFM19s, reason: not valid java name */
    public static final DateRangePickerState m379rememberDateRangePickerStateIlFM19s(LocalDate localDate, LocalDate localDate2, YearMonth yearMonth, IntRange intRange, int i, SelectableDates selectableDates, Composer composer, int i2, int i3) {
        if ((i3 & 4) != 0) {
            yearMonth = localDate != null ? YearMonth.from(localDate) : null;
        }
        IntRange yearRange = (i3 & 8) != 0 ? DatePickerDefaults.INSTANCE.getYearRange() : intRange;
        int iM412getPickerjFl4v0 = (i3 & 16) != 0 ? DisplayMode.INSTANCE.m412getPickerjFl4v0() : i;
        SelectableDates allDates = (i3 & 32) != 0 ? DatePickerDefaults.INSTANCE.getAllDates() : selectableDates;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1580705706, i2, -1, "androidx.compose.material3.rememberDateRangePickerState (DatePicker.jvm.kt:175)");
        }
        DateRangePickerState dateRangePickerStateM388rememberDateRangePickerStateIlFM19s = DateRangePickerKt.m388rememberDateRangePickerStateIlFM19s(localDate != null ? getLocalDateMillisUtc(localDate) : null, localDate2 != null ? getLocalDateMillisUtc(localDate2) : null, yearMonth != null ? Long.valueOf(getYearMonthMillisUtc(yearMonth)) : null, yearRange, iM412getPickerjFl4v0, allDates, composer, i2 & 523264, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return dateRangePickerStateM388rememberDateRangePickerStateIlFM19s;
    }

    public static final void setDisplayedMonth(DatePickerState datePickerState, YearMonth yearMonth) {
        datePickerState.setDisplayedMonthMillis(getYearMonthMillisUtc(yearMonth));
    }

    public static final void setSelectedDate(DatePickerState datePickerState, LocalDate localDate) {
        datePickerState.setSelectedDateMillis(getLocalDateMillisUtc(localDate));
    }

    public static final void setSelection(DateRangePickerState dateRangePickerState, LocalDate localDate, LocalDate localDate2) {
        dateRangePickerState.setSelection(getLocalDateMillisUtc(localDate), getLocalDateMillisUtc(localDate2));
    }

    public static final void setDisplayedMonth(DateRangePickerState dateRangePickerState, YearMonth yearMonth) {
        dateRangePickerState.setDisplayedMonthMillis(getYearMonthMillisUtc(yearMonth));
    }

    public static final YearMonth getDisplayedMonth(DateRangePickerState dateRangePickerState) {
        return getYearMonth(dateRangePickerState.getDisplayedMonthMillis());
    }
}
