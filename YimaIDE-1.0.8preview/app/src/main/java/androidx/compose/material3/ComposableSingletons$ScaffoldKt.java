package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposableSingletons$ScaffoldKt {
    public static final ComposableSingletons$ScaffoldKt INSTANCE = new ComposableSingletons$ScaffoldKt();

    /* JADX INFO: renamed from: lambda$-39202156, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f27lambda$39202156 = ComposableLambdaKt.composableLambdaInstance(-39202156, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$ScaffoldKt$lambda$-39202156$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-39202156, i, -1, "androidx.compose.material3.ComposableSingletons$ScaffoldKt.lambda$-39202156.<anonymous> (Scaffold.kt:84)");
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
    private static Function2<Composer, Integer, Unit> lambda$1582488484 = ComposableLambdaKt.composableLambdaInstance(1582488484, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$ScaffoldKt$lambda$1582488484$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1582488484, i, -1, "androidx.compose.material3.ComposableSingletons$ScaffoldKt.lambda$1582488484.<anonymous> (Scaffold.kt:85)");
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
    private static Function2<Composer, Integer, Unit> lambda$414328099 = ComposableLambdaKt.composableLambdaInstance(414328099, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$ScaffoldKt$lambda$414328099$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(414328099, i, -1, "androidx.compose.material3.ComposableSingletons$ScaffoldKt.lambda$414328099.<anonymous> (Scaffold.kt:86)");
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

    /* JADX INFO: renamed from: lambda$-1514016380, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f26lambda$1514016380 = ComposableLambdaKt.composableLambdaInstance(-1514016380, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$ScaffoldKt$lambda$-1514016380$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1514016380, i, -1, "androidx.compose.material3.ComposableSingletons$ScaffoldKt.lambda$-1514016380.<anonymous> (Scaffold.kt:87)");
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

    /* JADX INFO: renamed from: getLambda$-1514016380$material3, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m318getLambda$1514016380$material3() {
        return f26lambda$1514016380;
    }

    /* JADX INFO: renamed from: getLambda$-39202156$material3, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m319getLambda$39202156$material3() {
        return f27lambda$39202156;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1582488484$material3() {
        return lambda$1582488484;
    }

    public final Function2<Composer, Integer, Unit> getLambda$414328099$material3() {
        return lambda$414328099;
    }
}
