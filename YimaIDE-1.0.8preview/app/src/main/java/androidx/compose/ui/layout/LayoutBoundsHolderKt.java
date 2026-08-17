package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"layoutBounds", "Landroidx/compose/ui/Modifier;", "holder", "Landroidx/compose/ui/layout/LayoutBoundsHolder;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LayoutBoundsHolderKt {
    public static final Modifier layoutBounds(Modifier modifier, LayoutBoundsHolder layoutBoundsHolder) {
        return modifier.then(new LayoutBoundsElement(layoutBoundsHolder));
    }
}
