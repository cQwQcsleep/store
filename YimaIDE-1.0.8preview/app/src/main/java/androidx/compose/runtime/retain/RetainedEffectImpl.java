package androidx.compose.runtime.retain;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B \u0012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\u000bH\u0016R\u001f\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/compose/runtime/retain/RetainedEffectImpl;", "Landroidx/compose/runtime/retain/RetainObserver;", "effect", "Lkotlin/Function1;", "Landroidx/compose/runtime/retain/RetainedEffectScope;", "Landroidx/compose/runtime/retain/RetainedEffectResult;", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "onRetire", "onRetained", "", "onRetired", "onEnteredComposition", "onExitedComposition", "onUnused", "runtime-retain"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RetainedEffectImpl implements RetainObserver {
    private final Function1<RetainedEffectScope, RetainedEffectResult> effect;
    private RetainedEffectResult onRetire;

    /* JADX WARN: Multi-variable type inference failed */
    public RetainedEffectImpl(Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1) {
        this.effect = function1;
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onEnteredComposition() {
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onExitedComposition() {
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onRetained() {
        this.onRetire = (RetainedEffectResult) this.effect.invoke(RetainedEffectKt.InternalRetainedEffectScope);
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onRetired() {
        RetainedEffectResult retainedEffectResult = this.onRetire;
        if (retainedEffectResult != null) {
            retainedEffectResult.retire();
        }
        this.onRetire = null;
    }

    @Override // androidx.compose.runtime.retain.RetainObserver
    public void onUnused() {
    }
}
