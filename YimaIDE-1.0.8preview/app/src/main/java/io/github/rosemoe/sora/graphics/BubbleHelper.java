package io.github.rosemoe.sora.graphics;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class BubbleHelper {
    private static final Matrix tempMatrix = new Matrix();

    public static void buildBubblePath(Path path, RectF rectF) {
        path.reset();
        float fWidth = rectF.width();
        float fHeight = rectF.height() / 2.0f;
        float fSqrt = (float) Math.sqrt(2.0d);
        float f = fSqrt * fHeight;
        float fMax = Math.max(fHeight + f, fWidth);
        pathArcTo(path, fHeight, fHeight, fHeight, 90.0f, 180.0f);
        float f2 = fMax - f;
        pathArcTo(path, f2, fHeight, fHeight, -90.0f, 45.0f);
        float f3 = fHeight / 5.0f;
        pathArcTo(path, fMax - (fSqrt * f3), fHeight, f3, -45.0f, 90.0f);
        pathArcTo(path, f2, fHeight, fHeight, 45.0f, 45.0f);
        path.close();
        Matrix matrix = tempMatrix;
        matrix.reset();
        matrix.postTranslate(rectF.left, rectF.top);
        path.transform(matrix);
    }

    private static void pathArcTo(Path path, float f, float f2, float f3, float f4, float f5) {
        path.arcTo(f - f3, f2 - f3, f + f3, f2 + f3, f4, f5, false);
    }
}
