package io.github.rosemoe.sora.graphics;

import android.graphics.Canvas;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class GraphicsCompat {
    public static void drawTextRun(Canvas canvas, char[] cArr, int i, int i2, int i3, int i4, float f, float f2, boolean z, android.graphics.Paint paint) {
        canvas.drawTextRun(cArr, i, i2, i3, i4, f, f2, z, paint);
    }

    public static float getRunAdvance(Paint paint, char[] cArr, int i, int i2, int i3, int i4, boolean z, int i5) {
        return paint.getRunAdvance(cArr, i, i2, i3, i4, z, i5);
    }
}
