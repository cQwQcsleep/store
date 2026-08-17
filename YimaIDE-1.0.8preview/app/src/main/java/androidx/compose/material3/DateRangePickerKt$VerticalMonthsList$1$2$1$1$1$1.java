package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.CalendarMonth;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ List<CustomAccessibilityAction> $customAccessibilityAction;
    final /* synthetic */ DatePickerFormatter $dateFormatter;
    final /* synthetic */ CalendarMonth $month;

    public DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1(DatePickerFormatter datePickerFormatter, CalendarMonth calendarMonth, CalendarModel calendarModel, List<CustomAccessibilityAction> list, DatePickerColors datePickerColors) {
        this.$dateFormatter = datePickerFormatter;
        this.$month = calendarMonth;
        this.$calendarModel = calendarModel;
        this.$customAccessibilityAction = list;
        this.$colors = datePickerColors;
    }

    public static Unit a(List list, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setCustomActions(semanticsPropertyReceiver, list);
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-577031469, i, -1, "androidx.compose.material3.VerticalMonthsList.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DateRangePicker.kt:858)");
        }
        String monthYear = this.$dateFormatter.formatMonthYear(Long.valueOf(this.$month.getStartUtcTimeMillis()), this.$calendarModel.getLocale());
        if (monthYear == null) {
            monthYear = "-";
        }
        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.getCalendarMonthSubheadPadding());
        boolean zChangedInstance = composer.changedInstance(this.$customAccessibilityAction);
        final List<CustomAccessibilityAction> list = this.$customAccessibilityAction;
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function1() { // from class: androidx.compose.material3.b1
                public final Object invoke(Object obj) {
                    return DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1.a(list, (SemanticsPropertyReceiver) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        TextKt.m1097TextNvy7gAk(monthYear, SemanticsModifierKt.semantics$default(modifierPadding, false, (Function1) objRememberedValue, 1, null), this.$colors.getSubheadContentColor(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262136);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
