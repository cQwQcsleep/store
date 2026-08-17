package androidx.window.layout.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.WindowManager;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Landroidx/window/layout/util/BoundsHelperApi16Impl;", "Landroidx/window/layout/util/BoundsHelper;", "<init>", "()V", "currentWindowBounds", "Landroid/graphics/Rect;", "activity", "Landroid/app/Activity;", "maximumWindowBounds", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class BoundsHelperApi16Impl implements BoundsHelper {
    public static final BoundsHelperApi16Impl INSTANCE = new BoundsHelperApi16Impl();

    private BoundsHelperApi16Impl() {
    }

    @Override // androidx.window.layout.util.BoundsHelper
    public Rect currentWindowBounds(Activity activity) {
        int i;
        activity.getClass();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayHelper displayHelper = DisplayHelper.INSTANCE;
        defaultDisplay.getClass();
        Point realSizeForDisplay = displayHelper.getRealSizeForDisplay(defaultDisplay);
        Rect rect = new Rect();
        int i2 = realSizeForDisplay.x;
        if (i2 == 0 || (i = realSizeForDisplay.y) == 0) {
            defaultDisplay.getRectSize(rect);
            return rect;
        }
        rect.right = i2;
        rect.bottom = i;
        return rect;
    }

    @Override // androidx.window.layout.util.BoundsHelper
    public Rect maximumWindowBounds(Context context) {
        context.getClass();
        Object systemService = context.getSystemService("window");
        systemService.getClass();
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        DisplayHelper displayHelper = DisplayHelper.INSTANCE;
        defaultDisplay.getClass();
        Point realSizeForDisplay = displayHelper.getRealSizeForDisplay(defaultDisplay);
        return new Rect(0, 0, realSizeForDisplay.x, realSizeForDisplay.y);
    }
}
