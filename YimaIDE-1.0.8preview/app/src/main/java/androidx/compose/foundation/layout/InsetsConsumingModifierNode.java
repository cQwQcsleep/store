package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.InsetsConsumingModifierNode;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\b!\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0002R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\t¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/layout/InsetsConsumingModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/TraversableNode;", "<init>", "()V", "value", "Landroidx/compose/foundation/layout/WindowInsets;", "ancestorConsumedInsets", "getAncestorConsumedInsets", "()Landroidx/compose/foundation/layout/WindowInsets;", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "consumedInsets", "getConsumedInsets", "calculateInsets", "onAttach", "", "onDetach", "onReset", "setAncestorConsumedInsets", "insetsInvalidated", "invalidateChildConsumedInsets", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class InsetsConsumingModifierNode extends Modifier.Node implements TraversableNode {
    public static final int $stable = 8;
    private WindowInsets ancestorConsumedInsets = WindowInsetsKt.WindowInsets();
    private WindowInsets consumedInsets = WindowInsetsKt.WindowInsets();

    public static boolean a(InsetsConsumingModifierNode insetsConsumingModifierNode, TraversableNode traversableNode) {
        traversableNode.getClass();
        insetsConsumingModifierNode.ancestorConsumedInsets = ((InsetsConsumingModifierNode) traversableNode).consumedInsets;
        return false;
    }

    public static TraversableNode.Companion.TraverseDescendantsAction b(InsetsConsumingModifierNode insetsConsumingModifierNode, TraversableNode traversableNode) {
        traversableNode.getClass();
        ((InsetsConsumingModifierNode) traversableNode).setAncestorConsumedInsets(insetsConsumingModifierNode.consumedInsets);
        return TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
    }

    private final void invalidateChildConsumedInsets() {
        TraversableNodeKt.traverseDescendants(this, getTraverseKey(), new Function1() { // from class: sq6
            public final Object invoke(Object obj) {
                return InsetsConsumingModifierNode.b(this.b, (TraversableNode) obj);
            }
        });
    }

    private final void setAncestorConsumedInsets(WindowInsets ancestorConsumedInsets) {
        if (Intrinsics.areEqual(this.ancestorConsumedInsets, ancestorConsumedInsets)) {
            return;
        }
        this.ancestorConsumedInsets = ancestorConsumedInsets;
        insetsInvalidated();
    }

    public abstract WindowInsets calculateInsets(WindowInsets ancestorConsumedInsets);

    public final WindowInsets getAncestorConsumedInsets() {
        return this.ancestorConsumedInsets;
    }

    public final WindowInsets getConsumedInsets() {
        return this.consumedInsets;
    }

    public Object getTraverseKey() {
        return "androidx.compose.foundation.layout.ConsumedInsetsProvider";
    }

    public void insetsInvalidated() {
        this.consumedInsets = calculateInsets(this.ancestorConsumedInsets);
        invalidateChildConsumedInsets();
    }

    public void onAttach() {
        TraversableNodeKt.traverseAncestors(this, getTraverseKey(), new Function1() { // from class: tq6
            public final Object invoke(Object obj) {
                return Boolean.valueOf(InsetsConsumingModifierNode.a(this.b, (TraversableNode) obj));
            }
        });
        insetsInvalidated();
        super.onAttach();
    }

    public void onDetach() {
        this.consumedInsets = this.ancestorConsumedInsets;
        invalidateChildConsumedInsets();
        super.onDetach();
    }

    public void onReset() {
        super.onReset();
        this.ancestorConsumedInsets = WindowInsetsKt.WindowInsets();
    }
}
