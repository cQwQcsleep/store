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
public final class ComposableSingletons$ModalBottomSheetKt {
    public static final ComposableSingletons$ModalBottomSheetKt INSTANCE = new ComposableSingletons$ModalBottomSheetKt();
    private static Function2<Composer, Integer, Unit> lambda$1121996006 = ComposableLambdaKt.composableLambdaInstance(1121996006, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$ModalBottomSheetKt$lambda$1121996006$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1121996006, i, -1, "androidx.compose.material3.ComposableSingletons$ModalBottomSheetKt.lambda$1121996006.<anonymous> (ModalBottomSheet.kt:133)");
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

    /* JADX INFO: renamed from: lambda$-655173438, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f23lambda$655173438 = ComposableLambdaKt.composableLambdaInstance(-655173438, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$ModalBottomSheetKt$lambda$-655173438$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-655173438, i, -1, "androidx.compose.material3.ComposableSingletons$ModalBottomSheetKt.lambda$-655173438.<anonymous> (ModalBottomSheet.kt:232)");
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
    private static Function2<Composer, Integer, Unit> lambda$1716959002 = ComposableLambdaKt.composableLambdaInstance(1716959002, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$ModalBottomSheetKt$lambda$1716959002$1
        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1716959002, i, -1, "androidx.compose.material3.ComposableSingletons$ModalBottomSheetKt.lambda$1716959002.<anonymous> (ModalBottomSheet.kt:269)");
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

    /* JADX INFO: renamed from: getLambda$-655173438$material3, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m315getLambda$655173438$material3() {
        return f23lambda$655173438;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1121996006$material3() {
        return lambda$1121996006;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1716959002$material3() {
        return lambda$1716959002;
    }
}
