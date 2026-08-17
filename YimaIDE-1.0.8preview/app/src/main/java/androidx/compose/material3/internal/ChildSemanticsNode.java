package androidx.compose.material3.internal;

import androidx.compose.material3.internal.ChildSemanticsNode;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B \u0012\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\b\u0010\tJ\f\u0010\r\u001a\u00020\u0006*\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0016R+\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\t¨\u0006\u000f"}, d2 = {"Landroidx/compose/material3/internal/ChildSemanticsNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "properties", "Lkotlin/Function1;", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getProperties", "()Lkotlin/jvm/functions/Function1;", "setProperties", "applySemantics", "onDetach", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ChildSemanticsNode extends Modifier.Node implements SemanticsModifierNode {
    public static final int $stable = 8;
    private Function1<? super SemanticsPropertyReceiver, Unit> properties;

    public ChildSemanticsNode(Function1<? super SemanticsPropertyReceiver, Unit> function1) {
        this.properties = function1;
    }

    public static boolean a(SemanticsPropertyReceiver semanticsPropertyReceiver, TraversableNode traversableNode) {
        traversableNode.getClass();
        ((ParentSemanticsNode) traversableNode).obtainSemantics(semanticsPropertyReceiver);
        return false;
    }

    public static boolean b(TraversableNode traversableNode) {
        traversableNode.getClass();
        ((ParentSemanticsNode) traversableNode).releaseSemantics();
        return false;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(final SemanticsPropertyReceiver semanticsPropertyReceiver) {
        TraversableNodeKt.traverseAncestors(this, ParentSemanticsNodeKey.INSTANCE, new Function1() { // from class: ks1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ChildSemanticsNode.a(semanticsPropertyReceiver, (TraversableNode) obj));
            }
        });
        this.properties.invoke(semanticsPropertyReceiver);
    }

    public final Function1<SemanticsPropertyReceiver, Unit> getProperties() {
        return this.properties;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        super.onDetach();
        TraversableNodeKt.traverseAncestors(this, ParentSemanticsNodeKey.INSTANCE, new Function1() { // from class: ls1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ChildSemanticsNode.b((TraversableNode) obj));
            }
        });
    }

    public final void setProperties(Function1<? super SemanticsPropertyReceiver, Unit> function1) {
        this.properties = function1;
    }
}
