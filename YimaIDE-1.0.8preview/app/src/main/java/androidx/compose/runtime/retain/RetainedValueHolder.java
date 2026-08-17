package androidx.compose.runtime.retain;

import androidx.compose.runtime.RememberObserver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B)\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\u0013H\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u000e\u0010\rR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/retain/RetainedValueHolder;", "T", "Landroidx/compose/runtime/RememberObserver;", "key", "", "value", "owner", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "isNewlyRetained", "", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/retain/RetainedValuesStore;Z)V", "getKey", "()Ljava/lang/Object;", "getValue", "Ljava/lang/Object;", "getOwner", "()Landroidx/compose/runtime/retain/RetainedValuesStore;", "readoptUnder", "", "newStore", "readoptUnder$runtime_retain", "onRemembered", "onForgotten", "onAbandoned", "runtime-retain"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RetainedValueHolder<T> implements RememberObserver {
    public static final int $stable = 8;
    private boolean isNewlyRetained;
    private final Object key;
    private RetainedValuesStore owner;
    private final T value;

    public RetainedValueHolder(Object obj, T t, RetainedValuesStore retainedValuesStore, boolean z) {
        this.key = obj;
        this.value = t;
        this.isNewlyRetained = z;
        this.owner = retainedValuesStore;
        if (!(t instanceof RememberObserver) || (t instanceof RetainObserver)) {
            return;
        }
        kg9.a("Retained a value that implements RememberObserver but not RetainObserver. To receive the correct callbacks, the retained value '", t, "' must also implement RetainObserver.");
        throw null;
    }

    public final Object getKey() {
        return this.key;
    }

    public final RetainedValuesStore getOwner() {
        return this.owner;
    }

    public final T getValue() {
        return this.value;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        T t = this.value;
        if (t instanceof RetainObserver) {
            ((RetainObserver) t).onUnused();
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        T t = this.value;
        if (t instanceof RetainObserver) {
            ((RetainObserver) t).onExitedComposition();
        }
        this.owner.saveExitingValue(this.key, this.value);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        T t = this.value;
        if (t instanceof RetainObserver) {
            if (this.isNewlyRetained) {
                this.isNewlyRetained = false;
                ((RetainObserver) t).onRetained();
            }
            ((RetainObserver) this.value).onEnteredComposition();
        }
    }

    public final void readoptUnder$runtime_retain(RetainedValuesStore newStore) {
        this.owner = newStore;
    }
}
