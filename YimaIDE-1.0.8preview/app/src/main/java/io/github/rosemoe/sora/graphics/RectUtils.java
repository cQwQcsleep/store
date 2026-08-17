package io.github.rosemoe.sora.graphics;

import android.graphics.RectF;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RectUtils {
    public static boolean almostContains(RectF rectF, float f, float f2, float f3) {
        return f >= rectF.left - f3 && f <= rectF.right + f3 && f2 >= rectF.top - f3 && f2 <= rectF.bottom + f3;
    }

    public static boolean contains(RectF rectF, float f, float f2, float f3) {
        return f >= rectF.left - f3 && f <= rectF.right + f3 && f2 >= rectF.top && f2 <= rectF.bottom;
    }
}
