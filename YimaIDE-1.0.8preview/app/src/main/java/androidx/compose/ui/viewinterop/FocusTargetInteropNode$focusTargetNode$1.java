package androidx.compose.ui.viewinterop;

import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final /* synthetic */ class FocusTargetInteropNode$focusTargetNode$1 extends FunctionReferenceImpl implements Function2<FocusState, FocusState, Unit> {
    public FocusTargetInteropNode$focusTargetNode$1(Object obj) {
        super(2, obj, FocusTargetInteropNode.class, "onFocusStateChange", "onFocusStateChange(Landroidx/compose/ui/focus/FocusState;Landroidx/compose/ui/focus/FocusState;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((FocusState) obj, (FocusState) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(FocusState focusState, FocusState focusState2) {
        ((FocusTargetInteropNode) ((CallableReference) this).receiver).onFocusStateChange(focusState, focusState2);
    }
}
