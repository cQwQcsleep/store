package io.github.rosemoe.sora.graphics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class GraphemeBoundsBreaker {
    public static int findGraphemeBreakPoint(float[] fArr, int i, int i2, int i3) {
        float f = 0.0f;
        while (i3 < i) {
            float f2 = fArr[i3];
            if (f2 != 0.0f) {
                if (f + f2 > i2) {
                    break;
                }
                f += f2;
            }
            i3++;
        }
        return i3;
    }
}
