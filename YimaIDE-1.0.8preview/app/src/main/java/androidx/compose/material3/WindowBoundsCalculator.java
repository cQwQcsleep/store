package androidx.compose.material3;

import android.view.View;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntRect;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/compose/material3/WindowBoundsCalculator;", "", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "getVisibleWindowBounds", "Landroidx/compose/ui/unit/IntRect;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowBoundsCalculator {
    public static final int $stable = 8;
    private final View view;

    public WindowBoundsCalculator(View view) {
        this.view = view;
    }

    public final IntRect getVisibleWindowBounds() {
        return ExposedDropdownMenu_androidKt.getWindowBounds(this.view);
    }
}
