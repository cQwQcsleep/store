package androidx.compose.material3.internal;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B \u0012\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b¢\u0006\u0004\b\t\u0010\nJ\f\u0010\u0017\u001a\u00020\u0007*\u00020\u0006H\u0016J\n\u0010\u0018\u001a\u00020\u0007*\u00020\u0006J\u0006\u0010\u0019\u001a\u00020\u0007R+\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\nR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Landroidx/compose/material3/internal/ParentSemanticsNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "properties", "Lkotlin/Function1;", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getProperties", "()Lkotlin/jvm/functions/Function1;", "setProperties", "semanticsConsumed", "", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "()Z", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "applySemantics", "obtainSemantics", "releaseSemantics", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ParentSemanticsNode extends Modifier.Node implements TraversableNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private Function1<? super SemanticsPropertyReceiver, Unit> properties;
    private boolean semanticsConsumed;
    private final Object traverseKey = ParentSemanticsNodeKey.INSTANCE;

    public ParentSemanticsNode(Function1<? super SemanticsPropertyReceiver, Unit> function1) {
        this.properties = function1;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (this.semanticsConsumed) {
            return;
        }
        this.properties.invoke(semanticsPropertyReceiver);
    }

    public final Function1<SemanticsPropertyReceiver, Unit> getProperties() {
        return this.properties;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return this.traverseKey;
    }

    public final void obtainSemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        this.semanticsConsumed = true;
        this.properties.invoke(semanticsPropertyReceiver);
        SemanticsModifierNodeKt.invalidateSemantics(this);
    }

    public final void releaseSemantics() {
        this.semanticsConsumed = false;
        SemanticsModifierNodeKt.invalidateSemantics(this);
    }

    public final void setProperties(Function1<? super SemanticsPropertyReceiver, Unit> function1) {
        this.properties = function1;
    }
}
