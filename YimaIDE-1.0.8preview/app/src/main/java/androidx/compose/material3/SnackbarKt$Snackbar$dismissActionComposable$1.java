package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SnackbarKt$Snackbar$dismissActionComposable$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ SnackbarData $snackbarData;

    public SnackbarKt$Snackbar$dismissActionComposable$1(SnackbarData snackbarData) {
        this.$snackbarData = snackbarData;
    }

    public static Unit a(SnackbarData snackbarData) {
        snackbarData.dismiss();
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1812633777, i, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:223)");
        }
        boolean zChanged = composer.changed(this.$snackbarData);
        final SnackbarData snackbarData = this.$snackbarData;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: androidx.compose.material3.g4
                public final Object invoke() {
                    return SnackbarKt$Snackbar$dismissActionComposable$1.a(snackbarData);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$SnackbarKt.INSTANCE.getLambda$984817901$material3(), composer, 1572864, 62);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
