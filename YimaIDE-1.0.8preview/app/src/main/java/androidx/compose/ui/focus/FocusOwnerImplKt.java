package androidx.compose.ui.focus;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"is1dFocusSearch", "", "Landroidx/compose/ui/focus/FocusDirection;", "is1dFocusSearch-3ESFkO8", "(I)Z", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FocusOwnerImplKt {
    /* JADX INFO: renamed from: is1dFocusSearch-3ESFkO8, reason: not valid java name */
    public static final boolean m2789is1dFocusSearch3ESFkO8(int i) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        return FocusDirection.m2759equalsimpl0(i, companion.m2767getNextdhqQ8s()) || FocusDirection.m2759equalsimpl0(i, companion.m2768getPreviousdhqQ8s());
    }
}
