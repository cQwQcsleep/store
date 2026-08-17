package androidx.compose.ui.graphics;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0002"}, d2 = {"copy", "Landroidx/compose/ui/graphics/Path;", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PathKt {
    public static final Path copy(Path path) {
        Path Path = AndroidPath_androidKt.Path();
        Path.m3418addPathUv8p0NA$default(Path, path, 0L, 2, null);
        return Path;
    }
}
