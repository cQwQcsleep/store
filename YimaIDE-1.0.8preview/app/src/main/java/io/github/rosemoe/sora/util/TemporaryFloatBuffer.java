package io.github.rosemoe.sora.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TemporaryFloatBuffer {
    private static final FloatArrayCache sCache = new FloatArrayCache();

    public static class FloatArrayCache {
        private float[] temp = null;

        public float[] obtain(int i) {
            float[] fArr;
            synchronized (this) {
                fArr = this.temp;
                this.temp = null;
            }
            return (fArr == null || fArr.length < i) ? new float[i] : fArr;
        }

        public void recycle(float[] fArr) {
            if (fArr.length > 1000) {
                return;
            }
            synchronized (this) {
                this.temp = fArr;
            }
        }
    }

    public static float[] obtain(int i) {
        return sCache.obtain(i);
    }

    public static void recycle(float[] fArr) {
        sCache.recycle(fArr);
    }
}
