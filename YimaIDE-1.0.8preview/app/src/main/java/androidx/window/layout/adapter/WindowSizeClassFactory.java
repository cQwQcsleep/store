package androidx.window.layout.adapter;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.core.layout.WindowSizeClass;
import androidx.window.core.layout.WindowSizeClassSelectors;
import androidx.window.layout.WindowMetrics;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"computeWindowSizeClass", "Landroidx/window/core/layout/WindowSizeClass;", "", "windowMetrics", "Landroidx/window/layout/WindowMetrics;", "window_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowSizeClassFactory {
    public static final WindowSizeClass computeWindowSizeClass(Set<WindowSizeClass> set, WindowMetrics windowMetrics) {
        set.getClass();
        windowMetrics.getClass();
        return WindowSizeClassSelectors.computeWindowSizeClass(set, windowMetrics.getWidthDp(), windowMetrics.getHeightDp());
    }
}
