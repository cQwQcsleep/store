package androidx.window.layout.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.WindowManager;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.core.Bounds;
import androidx.window.layout.WindowMetrics;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u0012"}, d2 = {"Landroidx/window/layout/util/WindowMetricsCompatHelperApi30Impl;", "Landroidx/window/layout/util/WindowMetricsCompatHelper;", "<init>", "()V", "translateWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "windowMetrics", "Landroid/view/WindowMetrics;", "density", "", "currentWindowMetrics", "context", "Landroid/content/Context;", "densityCompatHelper", "Landroidx/window/layout/util/DensityCompatHelper;", "activity", "Landroid/app/Activity;", "maximumWindowMetrics", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowMetricsCompatHelperApi30Impl implements WindowMetricsCompatHelper {
    public static final WindowMetricsCompatHelperApi30Impl INSTANCE = new WindowMetricsCompatHelperApi30Impl();

    private WindowMetricsCompatHelperApi30Impl() {
    }

    @Override // androidx.window.layout.util.WindowMetricsCompatHelper
    public WindowMetrics currentWindowMetrics(Context context, DensityCompatHelper densityCompatHelper) {
        context.getClass();
        densityCompatHelper.getClass();
        WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
        float f = context.getResources().getDisplayMetrics().density;
        Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();
        bounds.getClass();
        return new WindowMetrics(bounds, f);
    }

    @Override // androidx.window.layout.util.WindowMetricsCompatHelper
    public WindowMetrics maximumWindowMetrics(Context context, DensityCompatHelper densityCompatHelper) {
        context.getClass();
        densityCompatHelper.getClass();
        return new WindowMetrics(new Bounds(BoundsHelper.INSTANCE.getInstance().maximumWindowBounds(context)), densityCompatHelper.density(context));
    }

    @Override // androidx.window.layout.util.WindowMetricsCompatHelper
    public WindowMetrics translateWindowMetrics(android.view.WindowMetrics windowMetrics, float density) {
        windowMetrics.getClass();
        Rect bounds = windowMetrics.getBounds();
        bounds.getClass();
        return new WindowMetrics(bounds, density);
    }

    @Override // androidx.window.layout.util.WindowMetricsCompatHelper
    public WindowMetrics currentWindowMetrics(Activity activity, DensityCompatHelper densityCompatHelper) {
        activity.getClass();
        densityCompatHelper.getClass();
        return new WindowMetrics(new Bounds(BoundsHelper.INSTANCE.getInstance().currentWindowBounds(activity)), densityCompatHelper.density(activity));
    }
}
