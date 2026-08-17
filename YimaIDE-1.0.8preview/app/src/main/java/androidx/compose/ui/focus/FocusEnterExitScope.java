package androidx.compose.ui.focus;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H\u0017R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0001\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/focus/FocusEnterExitScope;", "", "requestedFocusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "getRequestedFocusDirection-dhqQ-8s", "()I", "cancelFocusChange", "", "cancelFocus", "Landroidx/compose/ui/focus/CancelIndicatingFocusBoundaryScope;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface FocusEnterExitScope {
    @Deprecated(message = "Use cancelFocusChange instead", replaceWith = @ReplaceWith(expression = "cancelFocusChange", imports = {}))
    default void cancelFocus() {
        cancelFocusChange();
    }

    void cancelFocusChange();

    /* JADX INFO: renamed from: getRequestedFocusDirection-dhqQ-8s */
    int mo2755getRequestedFocusDirectiondhqQ8s();
}
