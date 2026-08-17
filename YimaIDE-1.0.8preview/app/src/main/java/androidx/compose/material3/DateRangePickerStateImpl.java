package androidx.compose.material3;

import androidx.compose.material3.DateRangePickerStateImpl;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\b\b\u0003\u0018\u0000 )2\u00020\u00012\u00020\u0002:\u0001)BI\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u00042\b\u0010$\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010%J\u0019\u0010&\u001a\u0004\u0018\u00010\u00142\b\u0010'\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0002\u0010(R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006*"}, d2 = {"Landroidx/compose/material3/DateRangePickerStateImpl;", "Landroidx/compose/material3/BaseDatePickerStateImpl;", "Landroidx/compose/material3/DateRangePickerState;", "initialSelectedStartDateMillis", "", "initialSelectedEndDateMillis", "initialDisplayedMonthMillis", "yearRange", "Lkotlin/ranges/IntRange;", "initialDisplayMode", "Landroidx/compose/material3/DisplayMode;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Ljava/util/Locale;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "_selectedStartDate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/material3/internal/CalendarDate;", "_selectedEndDate", "selectedStartDateMillis", "getSelectedStartDateMillis", "()Ljava/lang/Long;", "selectedEndDateMillis", "getSelectedEndDateMillis", "_displayMode", "displayMode", "getDisplayMode-jFl-4v0", "()I", "setDisplayMode-vCnGnXg", "(I)V", "setSelection", "", "startDateMillis", "endDateMillis", "(Ljava/lang/Long;Ljava/lang/Long;)V", "getDate", "dateMillis", "(Ljava/lang/Long;)Landroidx/compose/material3/internal/CalendarDate;", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DateRangePickerStateImpl extends BaseDatePickerStateImpl implements DateRangePickerState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private MutableState<DisplayMode> _displayMode;
    private MutableState<CalendarDate> _selectedEndDate;
    private MutableState<CalendarDate> _selectedStartDate;

    private DateRangePickerStateImpl(Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates, Locale locale) {
        super(l3, intRange, selectableDates, locale);
        this._selectedStartDate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this._selectedEndDate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        setSelection(l, l2);
        this._displayMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(DisplayMode.m404boximpl(i), null, 2, null);
    }

    private final CalendarDate getDate(Long dateMillis) {
        if (dateMillis != null) {
            CalendarDate canonicalDate = getCalendarModel().getCanonicalDate(dateMillis.longValue());
            if (getYearRange().contains(canonicalDate.getYear())) {
                return canonicalDate;
            }
        }
        return null;
    }

    @Override // androidx.compose.material3.DateRangePickerState
    /* JADX INFO: renamed from: getDisplayMode-jFl-4v0 */
    public int mo390getDisplayModejFl4v0() {
        return this._displayMode.getValue().getValue();
    }

    @Override // androidx.compose.material3.DateRangePickerState
    public Long getSelectedEndDateMillis() {
        CalendarDate value = this._selectedEndDate.getValue();
        if (value != null) {
            return Long.valueOf(value.getUtcTimeMillis());
        }
        return null;
    }

    @Override // androidx.compose.material3.DateRangePickerState
    public Long getSelectedStartDateMillis() {
        CalendarDate value = this._selectedStartDate.getValue();
        if (value != null) {
            return Long.valueOf(value.getUtcTimeMillis());
        }
        return null;
    }

    @Override // androidx.compose.material3.DateRangePickerState
    /* JADX INFO: renamed from: setDisplayMode-vCnGnXg */
    public void mo391setDisplayModevCnGnXg(int i) {
        Long selectedStartDateMillis = getSelectedStartDateMillis();
        if (selectedStartDateMillis != null) {
            setDisplayedMonthMillis(getCalendarModel().getMonth(selectedStartDateMillis.longValue()).getStartUtcTimeMillis());
        }
        this._displayMode.setValue(DisplayMode.m404boximpl(i));
    }

    @Override // androidx.compose.material3.DateRangePickerState
    public void setSelection(Long startDateMillis, Long endDateMillis) {
        CalendarDate date = getDate(startDateMillis);
        CalendarDate date2 = getDate(endDateMillis);
        if (date == null || (date2 != null && date.getUtcTimeMillis() > date2.getUtcTimeMillis())) {
            this._selectedStartDate.setValue(null);
            this._selectedEndDate.setValue(null);
        } else {
            this._selectedStartDate.setValue(date);
            this._selectedEndDate.setValue(date2);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\nj\u0002`\u000b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/material3/DateRangePickerStateImpl$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/DateRangePickerStateImpl;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "(Landroidx/compose/material3/SelectableDates;Ljava/util/Locale;)Landroidx/compose/runtime/saveable/Saver;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static List a(SaverScope saverScope, DateRangePickerStateImpl dateRangePickerStateImpl) {
            return CollectionsKt.listOf(new Object[]{dateRangePickerStateImpl.getSelectedStartDateMillis(), dateRangePickerStateImpl.getSelectedEndDateMillis(), Long.valueOf(dateRangePickerStateImpl.getDisplayedMonthMillis()), Integer.valueOf(dateRangePickerStateImpl.getYearRange().getFirst()), Integer.valueOf(dateRangePickerStateImpl.getYearRange().getLast()), Integer.valueOf(dateRangePickerStateImpl.mo390getDisplayModejFl4v0())});
        }

        public static DateRangePickerStateImpl b(SelectableDates selectableDates, Locale locale, List list) {
            Long l = (Long) list.get(0);
            Long l2 = (Long) list.get(1);
            Long l3 = (Long) list.get(2);
            Object obj = list.get(3);
            obj.getClass();
            int iIntValue = ((Integer) obj).intValue();
            Object obj2 = list.get(4);
            obj2.getClass();
            IntRange intRange = new IntRange(iIntValue, ((Integer) obj2).intValue());
            Object obj3 = list.get(5);
            obj3.getClass();
            return new DateRangePickerStateImpl(l, l2, l3, intRange, DisplayMode.m405constructorimpl(((Integer) obj3).intValue()), selectableDates, locale, null);
        }

        public final Saver<DateRangePickerStateImpl, Object> Saver(final SelectableDates selectableDates, final Locale locale) {
            return ListSaverKt.listSaver(new Function2() { // from class: androidx.compose.material3.c1
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerStateImpl.Companion.a((SaverScope) obj, (DateRangePickerStateImpl) obj2);
                }
            }, new Function1() { // from class: ab3
                public final Object invoke(Object obj) {
                    return DateRangePickerStateImpl.Companion.b(selectableDates, locale, (List) obj);
                }
            });
        }

        private Companion() {
        }
    }

    public /* synthetic */ DateRangePickerStateImpl(Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates, Locale locale, DefaultConstructorMarker defaultConstructorMarker) {
        this(l, l2, l3, intRange, i, selectableDates, locale);
    }
}
