package androidx.compose.foundation.text;

import android.content.res.Resources;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"getString", "", "string", "Landroidx/compose/foundation/text/ContextMenuStrings;", "getString-tk4Tqcs", "(ILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ContextMenuStrings_androidKt {
    /* JADX INFO: renamed from: getString-tk4Tqcs, reason: not valid java name */
    public static final String m1347getStringtk4Tqcs(int i, Composer composer, int i2) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2083411200, i2, -1, "androidx.compose.foundation.text.getString (ContextMenuStrings.android.kt:55)");
        }
        String string = ((Resources) composer.consume(AndroidCompositionLocals_androidKt.getLocalResources())).getString(i);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return string;
    }
}
