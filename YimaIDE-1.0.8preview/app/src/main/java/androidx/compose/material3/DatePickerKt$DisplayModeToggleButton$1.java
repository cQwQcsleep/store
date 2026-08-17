package androidx.compose.material3;

import androidx.compose.material3.internal.Icons;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DatePickerKt$DisplayModeToggleButton$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ int $displayMode;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Function1<DisplayMode, Unit> $onDisplayModeChange;

    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerKt$DisplayModeToggleButton$1(int i, Function1<? super DisplayMode, Unit> function1, Modifier modifier) {
        this.$displayMode = i;
        this.$onDisplayModeChange = function1;
        this.$modifier = modifier;
    }

    public static Unit a(Function1 function1) {
        function1.invoke(DisplayMode.m404boximpl(DisplayMode.INSTANCE.m411getInputjFl4v0()));
        return Unit.INSTANCE;
    }

    public static Unit b(Function1 function1) {
        function1.invoke(DisplayMode.m404boximpl(DisplayMode.INSTANCE.m412getPickerjFl4v0()));
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1734512197, i, -1, "androidx.compose.material3.DisplayModeToggleButton.<anonymous> (DatePicker.kt:1408)");
        }
        if (DisplayMode.m407equalsimpl0(this.$displayMode, DisplayMode.INSTANCE.m412getPickerjFl4v0())) {
            composer.startReplaceGroup(-101264927);
            ImageVector edit$material3 = Icons.Filled.INSTANCE.getEdit$material3();
            Strings.Companion companion = Strings.INSTANCE;
            String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_switch_to_input_mode), composer, 0);
            boolean zChanged = composer.changed(this.$onDisplayModeChange);
            final Function1<DisplayMode, Unit> function1 = this.$onDisplayModeChange;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: androidx.compose.material3.e0
                    public final Object invoke() {
                        return DatePickerKt$DisplayModeToggleButton$1.a(function1);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            DatePickerKt.IconButtonWithTooltip((Function0) objRememberedValue, edit$material3, strM1471getString2EP1pXo, this.$modifier, false, composer, 0, 16);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-100967048);
            ImageVector dateRange$material3 = Icons.Filled.INSTANCE.getDateRange$material3();
            Strings.Companion companion2 = Strings.INSTANCE;
            String strM1471getString2EP1pXo2 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_switch_to_calendar_mode), composer, 0);
            boolean zChanged2 = composer.changed(this.$onDisplayModeChange);
            final Function1<DisplayMode, Unit> function2 = this.$onDisplayModeChange;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: androidx.compose.material3.f0
                    public final Object invoke() {
                        return DatePickerKt$DisplayModeToggleButton$1.b(function2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            DatePickerKt.IconButtonWithTooltip((Function0) objRememberedValue2, dateRange$material3, strM1471getString2EP1pXo2, this.$modifier, false, composer, 0, 16);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
