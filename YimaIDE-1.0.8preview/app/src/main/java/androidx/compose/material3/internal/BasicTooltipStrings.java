package androidx.compose.material3.internal;

import androidx.compose.foundation.R;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.res.StringResources_androidKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/compose/material3/internal/BasicTooltipStrings;", "", "<init>", "()V", "label", "", "(Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "description", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BasicTooltipStrings {
    public static final int $stable = 0;
    public static final BasicTooltipStrings INSTANCE = new BasicTooltipStrings();

    private BasicTooltipStrings() {
    }

    public final String description(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1282333990, i, -1, "androidx.compose.material3.internal.BasicTooltipStrings.description (BasicTooltip.android.kt:25)");
        }
        String strStringResource = StringResources_androidKt.stringResource(R.string.tooltip_description, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strStringResource;
    }

    public final String label(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1966841262, i, -1, "androidx.compose.material3.internal.BasicTooltipStrings.label (BasicTooltip.android.kt:23)");
        }
        String strStringResource = StringResources_androidKt.stringResource(R.string.tooltip_label, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strStringResource;
    }
}
