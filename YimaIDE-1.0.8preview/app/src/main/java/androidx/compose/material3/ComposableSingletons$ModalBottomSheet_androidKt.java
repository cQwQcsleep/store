package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposableSingletons$ModalBottomSheet_androidKt {
    public static final ComposableSingletons$ModalBottomSheet_androidKt INSTANCE = new ComposableSingletons$ModalBottomSheet_androidKt();

    /* JADX INFO: renamed from: lambda$-1294623166, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f24lambda$1294623166 = ComposableLambdaKt.composableLambdaInstance(-1294623166, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$ModalBottomSheet_androidKt$lambda$-1294623166$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1294623166, i, -1, "androidx.compose.material3.ComposableSingletons$ModalBottomSheet_androidKt.lambda$-1294623166.<anonymous> (ModalBottomSheet.android.kt:338)");
            }
            BottomSheetDefaults.INSTANCE.m118DragHandlelgZ2HuY(null, 0.0f, 0.0f, null, 0L, composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    });

    /* JADX INFO: renamed from: lambda$-91331245, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f25lambda$91331245 = ComposableLambdaKt.composableLambdaInstance(-91331245, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$ModalBottomSheet_androidKt$lambda$-91331245$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-91331245, i, -1, "androidx.compose.material3.ComposableSingletons$ModalBottomSheet_androidKt.lambda$-91331245.<anonymous> (ModalBottomSheet.android.kt:422)");
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

    /* JADX INFO: renamed from: getLambda$-1294623166$material3, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m316getLambda$1294623166$material3() {
        return f24lambda$1294623166;
    }

    /* JADX INFO: renamed from: getLambda$-91331245$material3, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m317getLambda$91331245$material3() {
        return f25lambda$91331245;
    }
}
