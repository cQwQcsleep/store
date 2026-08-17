package androidx.compose.ui.focus;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0016\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/focus/CancelIndicatingFocusBoundaryScope;", "Landroidx/compose/ui/focus/FocusEnterExitScope;", "requestedFocusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "<init>", "(ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getRequestedFocusDirection-dhqQ-8s", "()I", "I", "value", "", "isCanceled", "()Z", "cancelFocusChange", "", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CancelIndicatingFocusBoundaryScope implements FocusEnterExitScope {
    public static final int $stable = 8;
    private boolean isCanceled;
    private final int requestedFocusDirection;

    private CancelIndicatingFocusBoundaryScope(int i) {
        this.requestedFocusDirection = i;
    }

    @Override // androidx.compose.ui.focus.FocusEnterExitScope
    public void cancelFocusChange() {
        this.isCanceled = true;
    }

    @Override // androidx.compose.ui.focus.FocusEnterExitScope
    /* JADX INFO: renamed from: getRequestedFocusDirection-dhqQ-8s, reason: not valid java name and from getter */
    public int getRequestedFocusDirection() {
        return this.requestedFocusDirection;
    }

    /* JADX INFO: renamed from: isCanceled, reason: from getter */
    public final boolean getIsCanceled() {
        return this.isCanceled;
    }

    public /* synthetic */ CancelIndicatingFocusBoundaryScope(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }
}
