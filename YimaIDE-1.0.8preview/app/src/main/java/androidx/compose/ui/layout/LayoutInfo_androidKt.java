package androidx.compose.ui.layout;

import android.view.View;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"view", "Landroid/view/View;", "Landroidx/compose/ui/layout/LayoutInfo;", "getView", "(Landroidx/compose/ui/layout/LayoutInfo;)Landroid/view/View;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LayoutInfo_androidKt {
    public static final View getView(LayoutInfo layoutInfo) {
        layoutInfo.getClass();
        Object owner = ((LayoutNode) layoutInfo).getOwner();
        if (owner instanceof View) {
            return (View) owner;
        }
        return null;
    }
}
