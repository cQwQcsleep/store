package androidx.window.layout.util;

import android.content.Context;
import android.content.res.Configuration;
import android.view.WindowMetrics;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Landroidx/window/layout/util/DensityCompatHelperBaseImpl;", "Landroidx/window/layout/util/DensityCompatHelper;", "<init>", "()V", "density", "", "context", "Landroid/content/Context;", "configuration", "Landroid/content/res/Configuration;", "windowMetrics", "Landroid/view/WindowMetrics;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class DensityCompatHelperBaseImpl implements DensityCompatHelper {
    public static final DensityCompatHelperBaseImpl INSTANCE = new DensityCompatHelperBaseImpl();

    private DensityCompatHelperBaseImpl() {
    }

    @Override // androidx.window.layout.util.DensityCompatHelper
    public float density(Context context) {
        context.getClass();
        return context.getResources().getDisplayMetrics().density;
    }

    @Override // androidx.window.layout.util.DensityCompatHelper
    public float density(Configuration configuration, WindowMetrics windowMetrics) {
        configuration.getClass();
        windowMetrics.getClass();
        return configuration.densityDpi / 160.0f;
    }
}
