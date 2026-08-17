package androidx.compose.runtime;

import androidx.compose.runtime.internal.AtomicBoolean;
import androidx.compose.runtime.internal.AtomicInt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u0004H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u000b"}, d2 = {"Landroidx/compose/runtime/OneShotCancellationHandle;", "Landroidx/compose/runtime/CancellationHandle;", "action", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "didFireCancellation", "Landroidx/compose/runtime/internal/AtomicBoolean;", "Landroidx/compose/runtime/internal/AtomicInt;", "cancel", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class OneShotCancellationHandle implements CancellationHandle {
    public static final int $stable = 8;
    private final Function0<Unit> action;
    private final AtomicInt didFireCancellation = AtomicBoolean.m2531constructorimpl(false);

    public OneShotCancellationHandle(Function0<Unit> function0) {
        this.action = function0;
    }

    @Override // androidx.compose.runtime.CancellationHandle
    public void cancel() {
        if (AtomicBoolean.m2536getAndSetimpl(this.didFireCancellation, true)) {
            return;
        }
        this.action.invoke();
    }
}
