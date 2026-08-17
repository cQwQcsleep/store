package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/layout/PaddingValuesConsumingModifierNode;", "Landroidx/compose/foundation/layout/InsetsConsumingModifierNode;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "<init>", "(Landroidx/compose/foundation/layout/PaddingValues;)V", "update", "", "calculateInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "ancestorConsumedInsets", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class PaddingValuesConsumingModifierNode extends InsetsConsumingModifierNode {
    private PaddingValues paddingValues;

    public PaddingValuesConsumingModifierNode(PaddingValues paddingValues) {
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public WindowInsets calculateInsets(WindowInsets ancestorConsumedInsets) {
        return WindowInsetsKt.add(ancestorConsumedInsets, WindowInsetsKt.asInsets(this.paddingValues));
    }

    public final void update(PaddingValues paddingValues) {
        if (Intrinsics.areEqual(paddingValues, this.paddingValues)) {
            return;
        }
        this.paddingValues = paddingValues;
        insetsInvalidated();
    }
}
