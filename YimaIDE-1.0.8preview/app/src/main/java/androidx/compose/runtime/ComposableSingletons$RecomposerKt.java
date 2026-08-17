package androidx.compose.runtime;

import androidx.compose.runtime.ComposableSingletons$RecomposerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposableSingletons$RecomposerKt {
    public static final ComposableSingletons$RecomposerKt INSTANCE = new ComposableSingletons$RecomposerKt();

    /* JADX INFO: renamed from: lambda$-1091980426, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f37lambda$1091980426 = ComposableLambdaKt.composableLambdaInstance(-1091980426, false, new Function2() { // from class: hi2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$RecomposerKt.a((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public static Unit a(Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1091980426, i, -1, "androidx.compose.runtime.ComposableSingletons$RecomposerKt.lambda$-1091980426.<anonymous> (Recomposer.kt:434)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: getLambda$-1091980426$runtime, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m2323getLambda$1091980426$runtime() {
        return f37lambda$1091980426;
    }
}
