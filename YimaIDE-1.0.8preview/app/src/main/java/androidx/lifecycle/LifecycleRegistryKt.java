package androidx.lifecycle;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import defpackage.yz8;
import defpackage.zz8;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¨\u0006\u0007"}, d2 = {"checkLifecycleStateTransition", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "current", "Landroidx/lifecycle/Lifecycle$State;", "next", "lifecycle-runtime_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LifecycleRegistryKt {
    public static final void checkLifecycleStateTransition(LifecycleOwner lifecycleOwner, Lifecycle.State state, Lifecycle.State state2) {
        state.getClass();
        state2.getClass();
        if (state == Lifecycle.State.INITIALIZED && state2 == Lifecycle.State.DESTROYED) {
            yz8.a("State must be at least '", Lifecycle.State.CREATED, "' to be moved to '", state2, "' in component ", lifecycleOwner);
            return;
        }
        Lifecycle.State state3 = Lifecycle.State.DESTROYED;
        if (state != state3 || state == state2) {
            return;
        }
        zz8.a("State is '", state3, "' and cannot be moved to `", state2, "` in component ", lifecycleOwner);
    }
}
