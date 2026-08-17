package androidx.compose.runtime.retain;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u00052\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086\b¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/retain/RetainedEffectScope;", "", "<init>", "()V", "onRetire", "Landroidx/compose/runtime/retain/RetainedEffectResult;", "onRetiredEffect", "Lkotlin/Function0;", "", "runtime-retain"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RetainedEffectScope {
    public static final int $stable = 0;

    public final RetainedEffectResult onRetire(final Function0<Unit> onRetiredEffect) {
        return new RetainedEffectResult() { // from class: androidx.compose.runtime.retain.RetainedEffectScope.onRetire.1
            @Override // androidx.compose.runtime.retain.RetainedEffectResult
            public void retire() {
                onRetiredEffect.invoke();
            }
        };
    }
}
