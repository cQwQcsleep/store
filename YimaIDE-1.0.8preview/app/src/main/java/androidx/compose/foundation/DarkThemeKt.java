package androidx.compose.foundation;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"isSystemInDarkTheme", "", "(Landroidx/compose/runtime/Composer;I)Z", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DarkThemeKt {
    public static final boolean isSystemInDarkTheme(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1100791446, i, -1, "androidx.compose.foundation.isSystemInDarkTheme (DarkTheme.kt:36)");
        }
        boolean z_isSystemInDarkTheme = DarkTheme_androidKt._isSystemInDarkTheme(composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return z_isSystemInDarkTheme;
    }
}
