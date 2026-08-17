package androidx.compose.runtime.retain;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\b\u0010\r\u001a\u00020\tH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/retain/RetainedValuesStoreRegistryWrapper;", "Landroidx/compose/runtime/retain/RetainObserver;", "<init>", "()V", "retainedValuesStoreRegistry", "Landroidx/compose/runtime/retain/RetainedValuesStoreRegistry;", "getRetainedValuesStoreRegistry", "()Landroidx/compose/runtime/retain/RetainedValuesStoreRegistry;", "onRetained", "", "onEnteredComposition", "onExitedComposition", "onRetired", "onUnused", "runtime-retain"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RetainedValuesStoreRegistryWrapper implements RetainObserver {
    private final RetainedValuesStoreRegistry retainedValuesStoreRegistry = new RetainedValuesStoreRegistry();

    public final RetainedValuesStoreRegistry getRetainedValuesStoreRegistry() {
        return this.retainedValuesStoreRegistry;
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onEnteredComposition() {
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onExitedComposition() {
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onRetained() {
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onRetired() {
        this.retainedValuesStoreRegistry.dispose();
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onUnused() {
        this.retainedValuesStoreRegistry.dispose();
    }
}
