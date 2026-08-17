package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/layout/UnionInsetsConsumingModifierNode;", "Landroidx/compose/foundation/layout/InsetsConsumingModifierNode;", "insets", "Landroidx/compose/foundation/layout/WindowInsets;", "<init>", "(Landroidx/compose/foundation/layout/WindowInsets;)V", "calculateInsets", "ancestorConsumedInsets", "update", "", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class UnionInsetsConsumingModifierNode extends InsetsConsumingModifierNode {
    private WindowInsets insets;

    public UnionInsetsConsumingModifierNode(WindowInsets windowInsets) {
        this.insets = windowInsets;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public WindowInsets calculateInsets(WindowInsets ancestorConsumedInsets) {
        return WindowInsetsKt.union(ancestorConsumedInsets, this.insets);
    }

    public final void update(WindowInsets insets) {
        if (Intrinsics.areEqual(insets, this.insets)) {
            return;
        }
        this.insets = insets;
        insetsInvalidated();
    }
}
