package androidx.compose.material3.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposableSingletons$BasicEdgeToEdgeDialog_androidKt {
    public static final ComposableSingletons$BasicEdgeToEdgeDialog_androidKt INSTANCE = new ComposableSingletons$BasicEdgeToEdgeDialog_androidKt();
    private static Function2<Composer, Integer, Unit> lambda$453789431 = ComposableLambdaKt.composableLambdaInstance(453789431, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.ComposableSingletons$BasicEdgeToEdgeDialog_androidKt$lambda$453789431$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(453789431, i, -1, "androidx.compose.material3.internal.ComposableSingletons$BasicEdgeToEdgeDialog_androidKt.lambda$453789431.<anonymous> (BasicEdgeToEdgeDialog.android.kt:316)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    });

    public final Function2<Composer, Integer, Unit> getLambda$453789431$material3() {
        return lambda$453789431;
    }
}
