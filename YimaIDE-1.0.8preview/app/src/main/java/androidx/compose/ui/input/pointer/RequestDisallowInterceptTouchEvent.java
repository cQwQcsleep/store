package androidx.compose.ui.input.pointer;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0096\u0002R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/input/pointer/RequestDisallowInterceptTouchEvent;", "Lkotlin/Function1;", "", "", "<init>", "()V", "pointerInteropFilter", "Landroidx/compose/ui/input/pointer/PointerInteropFilter;", "getPointerInteropFilter$ui", "()Landroidx/compose/ui/input/pointer/PointerInteropFilter;", "setPointerInteropFilter$ui", "(Landroidx/compose/ui/input/pointer/PointerInteropFilter;)V", "invoke", "disallowIntercept", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RequestDisallowInterceptTouchEvent implements Function1<Boolean, Unit> {
    public static final int $stable = 8;
    private PointerInteropFilter pointerInteropFilter;

    /* JADX INFO: renamed from: getPointerInteropFilter$ui, reason: from getter */
    public final PointerInteropFilter getPointerInteropFilter() {
        return this.pointerInteropFilter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Boolean) obj).booleanValue());
        return Unit.INSTANCE;
    }

    public final void setPointerInteropFilter$ui(PointerInteropFilter pointerInteropFilter) {
        this.pointerInteropFilter = pointerInteropFilter;
    }

    public void invoke(boolean disallowIntercept) {
        PointerInteropFilter pointerInteropFilter = this.pointerInteropFilter;
        if (pointerInteropFilter != null) {
            pointerInteropFilter.setDisallowIntercept$ui(disallowIntercept);
        }
    }
}
