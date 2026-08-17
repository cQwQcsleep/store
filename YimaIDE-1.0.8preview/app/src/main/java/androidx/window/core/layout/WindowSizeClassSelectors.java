package androidx.window.core.layout;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a \u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006\u001a \u0010\u0007\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"computeWindowSizeClass", "Landroidx/window/core/layout/WindowSizeClass;", "", "widthDp", "", "heightDp", "", "computeWindowSizeClassPreferHeight", "window-core"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowSizeClassSelectors {
    public static final WindowSizeClass computeWindowSizeClass(Set<WindowSizeClass> set, int i, int i2) {
        set.getClass();
        Set<WindowSizeClass> set2 = set;
        int minWidthDp = 0;
        for (WindowSizeClass windowSizeClass : set2) {
            if (windowSizeClass.getMinWidthDp() <= i && windowSizeClass.getMinWidthDp() > minWidthDp) {
                minWidthDp = windowSizeClass.getMinWidthDp();
            }
        }
        WindowSizeClass windowSizeClass2 = new WindowSizeClass(0, 0);
        for (WindowSizeClass windowSizeClass3 : set2) {
            if (windowSizeClass3.getMinWidthDp() == minWidthDp && windowSizeClass3.getMinHeightDp() <= i2 && windowSizeClass2.getMinHeightDp() <= windowSizeClass3.getMinHeightDp()) {
                windowSizeClass2 = windowSizeClass3;
            }
        }
        return windowSizeClass2;
    }

    public static final WindowSizeClass computeWindowSizeClassPreferHeight(Set<WindowSizeClass> set, int i, int i2) {
        set.getClass();
        Set<WindowSizeClass> set2 = set;
        int minHeightDp = 0;
        for (WindowSizeClass windowSizeClass : set2) {
            if (windowSizeClass.getMinHeightDp() <= i2 && windowSizeClass.getMinHeightDp() > minHeightDp) {
                minHeightDp = windowSizeClass.getMinHeightDp();
            }
        }
        WindowSizeClass windowSizeClass2 = new WindowSizeClass(0, 0);
        for (WindowSizeClass windowSizeClass3 : set2) {
            if (windowSizeClass3.getMinHeightDp() == minHeightDp && windowSizeClass3.getMinWidthDp() <= i && windowSizeClass2.getMinWidthDp() <= windowSizeClass3.getMinWidthDp()) {
                windowSizeClass2 = windowSizeClass3;
            }
        }
        return windowSizeClass2;
    }

    public static final WindowSizeClass computeWindowSizeClass(Set<WindowSizeClass> set, float f, float f2) {
        set.getClass();
        return computeWindowSizeClass(set, (int) f, (int) f2);
    }
}
