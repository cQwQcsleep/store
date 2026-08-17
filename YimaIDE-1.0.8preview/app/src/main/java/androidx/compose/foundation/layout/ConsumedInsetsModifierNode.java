package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0016J\u001a\u0010\n\u001a\u00020\u00052\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/layout/ConsumedInsetsModifierNode;", "Landroidx/compose/foundation/layout/InsetsConsumingModifierNode;", "block", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/WindowInsets;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "calculateInsets", "ancestorConsumedInsets", "update", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ConsumedInsetsModifierNode extends InsetsConsumingModifierNode {
    private Function1<? super WindowInsets, Unit> block;

    public ConsumedInsetsModifierNode(Function1<? super WindowInsets, Unit> function1) {
        this.block = function1;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public WindowInsets calculateInsets(WindowInsets ancestorConsumedInsets) {
        this.block.invoke(ancestorConsumedInsets);
        return ancestorConsumedInsets;
    }

    public final void update(Function1<? super WindowInsets, Unit> block) {
        if (block != this.block) {
            this.block = block;
        }
    }
}
