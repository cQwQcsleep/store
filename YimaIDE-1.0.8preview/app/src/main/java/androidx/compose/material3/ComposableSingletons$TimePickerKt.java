package androidx.compose.material3;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposableSingletons$TimePickerKt {
    public static final ComposableSingletons$TimePickerKt INSTANCE = new ComposableSingletons$TimePickerKt();
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1425358052 = ComposableLambdaKt.composableLambdaInstance(1425358052, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$TimePickerKt$lambda$1425358052$1
        public final void invoke(RowScope rowScope, Composer composer, int i) {
            if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1425358052, i, -1, "androidx.compose.material3.ComposableSingletons$TimePickerKt.lambda$1425358052.<anonymous> (TimePicker.kt:1328)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_time_picker_am), composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }
    });

    /* JADX INFO: renamed from: lambda$-1179219109, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f36lambda$1179219109 = ComposableLambdaKt.composableLambdaInstance(-1179219109, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$TimePickerKt$lambda$-1179219109$1
        public final void invoke(RowScope rowScope, Composer composer, int i) {
            if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1179219109, i, -1, "androidx.compose.material3.ComposableSingletons$TimePickerKt.lambda$-1179219109.<anonymous> (TimePicker.kt:1346)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_time_picker_pm), composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }
    });

    /* JADX INFO: renamed from: getLambda$-1179219109$material3, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m328getLambda$1179219109$material3() {
        return f36lambda$1179219109;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1425358052$material3() {
        return lambda$1425358052;
    }
}
