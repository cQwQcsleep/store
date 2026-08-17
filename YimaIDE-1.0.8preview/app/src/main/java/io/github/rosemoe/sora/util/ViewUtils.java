package io.github.rosemoe.sora.util;

import android.content.Context;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ViewUtils {
    public static final float DEFAULT_SCROLL_FACTOR = 32.0f;
    public static final int HOVER_TAP_SLOP = 20;
    public static final long HOVER_TOOLTIP_SHOW_TIMEOUT = 1000;
    private static final String LOG_TAG = "ViewUtils";

    public static float getVerticalScrollFactor(Context context) {
        return ViewConfiguration.get(context).getScaledVerticalScrollFactor();
    }
}
