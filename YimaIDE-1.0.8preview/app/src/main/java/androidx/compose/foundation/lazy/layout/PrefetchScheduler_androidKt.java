package androidx.compose.foundation.lazy.layout;

import android.os.Build;
import android.view.View;
import androidx.compose.foundation.R;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005*\u0001\u0004\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0002\"\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004¢\u0006\n\n\u0002\u0010\u0007\u0012\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"rememberDefaultPrefetchScheduler", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "RobolectricImpl", "androidx/compose/foundation/lazy/layout/PrefetchScheduler_androidKt$RobolectricImpl$1", "getRobolectricImpl$annotations", "()V", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler_androidKt$RobolectricImpl$1;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PrefetchScheduler_androidKt {
    private static final PrefetchScheduler_androidKt$RobolectricImpl$1 RobolectricImpl;

    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    /* JADX WARN: Multi-variable type inference failed */
    static {
        PrefetchScheduler_androidKt$RobolectricImpl$1 prefetchScheduler_androidKt$RobolectricImpl$1;
        String str = Build.FINGERPRINT;
        if (str != null) {
            String lowerCase = str.toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            if (Intrinsics.areEqual(lowerCase, "robolectric")) {
                prefetchScheduler_androidKt$RobolectricImpl$1 = new PrefetchScheduler() { // from class: androidx.compose.foundation.lazy.layout.PrefetchScheduler_androidKt$RobolectricImpl$1
                    @Override // androidx.compose.foundation.lazy.layout.PrefetchScheduler
                    public void schedulePrefetch(PrefetchRequest prefetchRequest) {
                    }
                };
            } else {
                prefetchScheduler_androidKt$RobolectricImpl$1 = 0;
            }
        } else {
            prefetchScheduler_androidKt$RobolectricImpl$1 = 0;
        }
        RobolectricImpl = prefetchScheduler_androidKt$RobolectricImpl$1;
    }

    private static /* synthetic */ void getRobolectricImpl$annotations() {
    }

    public static final PrefetchScheduler rememberDefaultPrefetchScheduler(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1141871251, i, -1, "androidx.compose.foundation.lazy.layout.rememberDefaultPrefetchScheduler (PrefetchScheduler.android.kt:36)");
        }
        PrefetchScheduler prefetchScheduler = RobolectricImpl;
        if (prefetchScheduler != null) {
            composer.startReplaceGroup(1345554384);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1345603457);
            View view = (View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView());
            boolean zChanged = composer.changed(view);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                Object tag = view.getTag(R.id.compose_prefetch_scheduler);
                PrefetchScheduler androidPrefetchScheduler = tag instanceof PrefetchScheduler ? (PrefetchScheduler) tag : null;
                if (androidPrefetchScheduler == null) {
                    androidPrefetchScheduler = new AndroidPrefetchScheduler(view);
                    view.setTag(R.id.compose_prefetch_scheduler, androidPrefetchScheduler);
                }
                objRememberedValue = androidPrefetchScheduler;
                composer.updateRememberedValue(objRememberedValue);
            }
            prefetchScheduler = (PrefetchScheduler) objRememberedValue;
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return prefetchScheduler;
    }
}
