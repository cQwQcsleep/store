package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.ColorFilter;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class BlendModeColorFilterCompat {

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static ColorFilter createBlendModeColorFilter(int i, Object obj) {
            return new BlendModeColorFilter(i, (BlendMode) obj);
        }
    }

    private BlendModeColorFilterCompat() {
    }

    public static ColorFilter createBlendModeColorFilterCompat(int i, BlendModeCompat blendModeCompat) {
        Object objObtainBlendModeFromCompat = BlendModeUtils.Api29Impl.obtainBlendModeFromCompat(blendModeCompat);
        if (objObtainBlendModeFromCompat != null) {
            return Api29Impl.createBlendModeColorFilter(i, objObtainBlendModeFromCompat);
        }
        return null;
    }
}
