package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.Paint;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class PaintCompat {

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static void setBlendMode(Paint paint, Object obj) {
            paint.setBlendMode((BlendMode) obj);
        }
    }

    private PaintCompat() {
    }

    public static boolean hasGlyph(Paint paint, String str) {
        return paint.hasGlyph(str);
    }

    public static boolean setBlendMode(Paint paint, BlendModeCompat blendModeCompat) {
        Api29Impl.setBlendMode(paint, blendModeCompat != null ? BlendModeUtils.Api29Impl.obtainBlendModeFromCompat(blendModeCompat) : null);
        return true;
    }
}
