package androidx.compose.material3.internal;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\n\u001a\u00020\u000b*\u00028\u00002\u0006\u0010\f\u001a\u00020\u0007H\u0086\u0004¢\u0006\u0002\u0010\rR \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Landroidx/compose/material3/internal/DraggableAnchorsConfig;", "T", "", "<init>", "()V", "anchors", "", "", "getAnchors$material3", "()Ljava/util/Map;", "at", "", "position", "(Ljava/lang/Object;F)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DraggableAnchorsConfig<T> {
    public static final int $stable = 8;
    private final Map<T, Float> anchors = new LinkedHashMap();

    public final void at(T t, float f) {
        this.anchors.put(t, Float.valueOf(f));
    }

    public final Map<T, Float> getAnchors$material3() {
        return this.anchors;
    }
}
