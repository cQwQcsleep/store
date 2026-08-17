package androidx.compose.material3;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.grid.LazyGridItemScope;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DatePickerKt$YearPicker$1$1$1$1 implements Function4<LazyGridItemScope, Integer, Composer, Integer, Unit> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ int $currentYear;
    final /* synthetic */ int $displayedYear;
    final /* synthetic */ Function1<Integer, Unit> $onYearSelected;
    final /* synthetic */ SelectableDates $selectableDates;
    final /* synthetic */ IntRange $yearRange;

    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerKt$YearPicker$1$1$1$1(IntRange intRange, CalendarModel calendarModel, int i, int i2, Function1<? super Integer, Unit> function1, SelectableDates selectableDates, DatePickerColors datePickerColors) {
        this.$yearRange = intRange;
        this.$calendarModel = calendarModel;
        this.$displayedYear = i;
        this.$currentYear = i2;
        this.$onYearSelected = function1;
        this.$selectableDates = selectableDates;
        this.$colors = datePickerColors;
    }

    public static Unit a(Function1 function1, int i) {
        function1.invoke(Integer.valueOf(i));
        return Unit.INSTANCE;
    }

    public final void invoke(LazyGridItemScope lazyGridItemScope, int i, Composer composer, int i2) {
        int i3;
        if ((i2 & 48) == 0) {
            i3 = i2 | (composer.changed(i) ? 32 : 16);
        } else {
            i3 = i2;
        }
        if (!composer.shouldExecute((i3 & 145) != 144, i3 & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(674613074, i3, -1, "androidx.compose.material3.YearPicker.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:2088)");
        }
        final int first = i + this.$yearRange.getFirst();
        String localString$default = CalendarLocale_jvmKt.toLocalString$default(first, 0, 0, false, this.$calendarModel.getLocale(), 7, null);
        Modifier.Companion companion = Modifier.INSTANCE;
        DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
        Modifier modifier = SizeKt.requiredSize-VpY3zN4(companion, datePickerModalTokens.m1709getSelectionYearContainerWidthD9Ej5fM(), datePickerModalTokens.m1708getSelectionYearContainerHeightD9Ej5fM());
        boolean z = first == this.$displayedYear;
        boolean z2 = first == this.$currentYear;
        boolean zChanged = composer.changed(this.$onYearSelected) | composer.changed(first);
        final Function1<Integer, Unit> function1 = this.$onYearSelected;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: androidx.compose.material3.n0
                public final Object invoke() {
                    return DatePickerKt$YearPicker$1$1$1$1.a(function1, first);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        boolean zIsSelectableYear = this.$selectableDates.isSelectableYear(first);
        Strings.Companion companion2 = Strings.INSTANCE;
        DatePickerKt.Year(localString$default, modifier, z, z2, (Function0) objRememberedValue, zIsSelectableYear, String.format(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_navigate_to_year_description), composer, 0), Arrays.copyOf(new Object[]{localString$default}, 1)), this.$colors, composer, 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyGridItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return Unit.INSTANCE;
    }
}
