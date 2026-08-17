package androidx.compose.foundation.layout;

import android.view.View;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import androidx.compose.ui.node.LayoutModifierNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B \u0012\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u001a\u0010\u0012\u001a\u00020\u00102\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004R\u001f\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/layout/SystemInsetsPaddingModifierNode;", "Landroidx/compose/foundation/layout/InsetsPaddingModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "insetsGetter", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/WindowInsetsHolder;", "Landroidx/compose/foundation/layout/WindowInsets;", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "windowInsetsHolder", "getWindowInsetsHolder", "()Landroidx/compose/foundation/layout/WindowInsetsHolder;", "setWindowInsetsHolder", "(Landroidx/compose/foundation/layout/WindowInsetsHolder;)V", "onAttach", "", "onDetach", "update", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SystemInsetsPaddingModifierNode extends InsetsPaddingModifierNode implements LayoutModifierNode {
    private Function1<? super WindowInsetsHolder, ? extends WindowInsets> insetsGetter;
    private WindowInsetsHolder windowInsetsHolder;

    public SystemInsetsPaddingModifierNode(Function1<? super WindowInsetsHolder, ? extends WindowInsets> function1) {
        super(WindowInsetsKt.WindowInsets());
        this.insetsGetter = function1;
    }

    public final WindowInsetsHolder getWindowInsetsHolder() {
        return this.windowInsetsHolder;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public void onAttach() {
        View viewRequireView = DelegatableNode_androidKt.requireView(this);
        WindowInsetsHolder orCreateFor = WindowInsetsHolder.INSTANCE.getOrCreateFor(viewRequireView);
        orCreateFor.incrementAccessors(viewRequireView);
        update((WindowInsets) this.insetsGetter.invoke(orCreateFor));
        this.windowInsetsHolder = orCreateFor;
        super.onAttach();
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public void onDetach() {
        View viewRequireView = DelegatableNode_androidKt.requireView(this);
        WindowInsetsHolder windowInsetsHolder = this.windowInsetsHolder;
        if (windowInsetsHolder != null) {
            windowInsetsHolder.decrementAccessors(viewRequireView);
        }
        super.onDetach();
    }

    public final void setWindowInsetsHolder(WindowInsetsHolder windowInsetsHolder) {
        this.windowInsetsHolder = windowInsetsHolder;
    }

    public final void update(Function1<? super WindowInsetsHolder, ? extends WindowInsets> insetsGetter) {
        if (this.insetsGetter != insetsGetter) {
            this.insetsGetter = insetsGetter;
            WindowInsetsHolder windowInsetsHolder = this.windowInsetsHolder;
            if (windowInsetsHolder != null) {
                update((WindowInsets) insetsGetter.invoke(windowInsetsHolder));
            }
        }
    }
}
