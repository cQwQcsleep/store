package io.github.rosemoe.sora.util;

import android.R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ThemeUtils {
    public static int getColorPrimary(ContextThemeWrapper contextThemeWrapper) {
        TypedValue typedValue = new TypedValue();
        contextThemeWrapper.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
}
