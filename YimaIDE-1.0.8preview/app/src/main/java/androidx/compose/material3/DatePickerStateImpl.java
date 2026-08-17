package androidx.compose.material3;

import androidx.compose.material3.DatePickerStateImpl;
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
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0003\u0018\u0000  2\u00020\u00012\u00020\u0002:\u0001 B?\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\u0010\f\u001a\u00060\rj\u0002`\u000e¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Landroidx/compose/material3/DatePickerStateImpl;", "Landroidx/compose/material3/BaseDatePickerStateImpl;", "Landroidx/compose/material3/DatePickerState;", "initialSelectedDateMillis", "", "initialDisplayedMonthMillis", "yearRange", "Lkotlin/ranges/IntRange;", "initialDisplayMode", "Landroidx/compose/material3/DisplayMode;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Ljava/util/Locale;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "_selectedDate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/material3/internal/CalendarDate;", "dateMillis", "selectedDateMillis", "getSelectedDateMillis", "()Ljava/lang/Long;", "setSelectedDateMillis", "(Ljava/lang/Long;)V", "_displayMode", "displayMode", "getDisplayMode-jFl-4v0", "()I", "setDisplayMode-vCnGnXg", "(I)V", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DatePickerStateImpl extends BaseDatePickerStateImpl implements DatePickerState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private MutableState<DisplayMode> _displayMode;
    private MutableState<CalendarDate> _selectedDate;

    /* JADX WARN: Code duplicated, block: B:7:0x001d  */
    private DatePickerStateImpl(Long l, Long l2, IntRange intRange, int i, SelectableDates selectableDates, Locale locale) {
        CalendarDate canonicalDate;
        super(l2, intRange, selectableDates, locale);
        if (l != null) {
            canonicalDate = getCalendarModel().getCanonicalDate(l.longValue());
            canonicalDate = intRange.contains(canonicalDate.getYear()) ? canonicalDate : null;
        }
        this._selectedDate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(canonicalDate, null, 2, null);
        this._displayMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(DisplayMode.m404boximpl(i), null, 2, null);
    }

    @Override // androidx.compose.material3.DatePickerState
    /* JADX INFO: renamed from: getDisplayMode-jFl-4v0 */
    public int mo372getDisplayModejFl4v0() {
        return this._displayMode.getValue().getValue();
    }

    @Override // androidx.compose.material3.DatePickerState
    public Long getSelectedDateMillis() {
        CalendarDate value = this._selectedDate.getValue();
        if (value != null) {
            return Long.valueOf(value.getUtcTimeMillis());
        }
        return null;
    }

    @Override // androidx.compose.material3.DatePickerState
    /* JADX INFO: renamed from: setDisplayMode-vCnGnXg */
    public void mo373setDisplayModevCnGnXg(int i) {
        Long selectedDateMillis = getSelectedDateMillis();
        if (selectedDateMillis != null) {
            setDisplayedMonthMillis(getCalendarModel().getMonth(selectedDateMillis.longValue()).getStartUtcTimeMillis());
        }
        this._displayMode.setValue(DisplayMode.m404boximpl(i));
    }

    @Override // androidx.compose.material3.DatePickerState
    public void setSelectedDateMillis(Long l) {
        if (l == null) {
            this._selectedDate.setValue(null);
        } else {
            CalendarDate canonicalDate = getCalendarModel().getCanonicalDate(l.longValue());
            this._selectedDate.setValue(getYearRange().contains(canonicalDate.getYear()) ? canonicalDate : null);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\nj\u0002`\u000b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/material3/DatePickerStateImpl$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/DatePickerStateImpl;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "(Landroidx/compose/material3/SelectableDates;Ljava/util/Locale;)Landroidx/compose/runtime/saveable/Saver;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static List a(SaverScope saverScope, DatePickerStateImpl datePickerStateImpl) {
            return CollectionsKt.listOf(new Object[]{datePickerStateImpl.getSelectedDateMillis(), Long.valueOf(datePickerStateImpl.getDisplayedMonthMillis()), Integer.valueOf(datePickerStateImpl.getYearRange().getFirst()), Integer.valueOf(datePickerStateImpl.getYearRange().getLast()), Integer.valueOf(datePickerStateImpl.mo372getDisplayModejFl4v0())});
        }

        public static DatePickerStateImpl b(SelectableDates selectableDates, Locale locale, List list) {
            Long l = (Long) list.get(0);
            Long l2 = (Long) list.get(1);
            Object obj = list.get(2);
            obj.getClass();
            int iIntValue = ((Integer) obj).intValue();
            Object obj2 = list.get(3);
            obj2.getClass();
            IntRange intRange = new IntRange(iIntValue, ((Integer) obj2).intValue());
            Object obj3 = list.get(4);
            obj3.getClass();
            return new DatePickerStateImpl(l, l2, intRange, DisplayMode.m405constructorimpl(((Integer) obj3).intValue()), selectableDates, locale, null);
        }

        public final Saver<DatePickerStateImpl, Object> Saver(final SelectableDates selectableDates, final Locale locale) {
            return ListSaverKt.listSaver(new Function2() { // from class: androidx.compose.material3.o0
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerStateImpl.Companion.a((SaverScope) obj, (DatePickerStateImpl) obj2);
                }
            }, new Function1() { // from class: ka3
                public final Object invoke(Object obj) {
                    return DatePickerStateImpl.Companion.b(selectableDates, locale, (List) obj);
                }
            });
        }

        private Companion() {
        }
    }

    public /* synthetic */ DatePickerStateImpl(Long l, Long l2, IntRange intRange, int i, SelectableDates selectableDates, Locale locale, DefaultConstructorMarker defaultConstructorMarker) {
        this(l, l2, intRange, i, selectableDates, locale);
    }
}
