package androidx.compose.foundation;

import androidx.compose.ui.focus.FocusTargetModifierNodeKt;
import androidx.compose.ui.focus.Focusability;
import androidx.compose.ui.node.DelegatingNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/foundation/FocusGroupNode;", "Landroidx/compose/ui/node/DelegatingNode;", "<init>", "()V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class FocusGroupNode extends DelegatingNode {
    public FocusGroupNode() {
        delegate(FocusTargetModifierNodeKt.FocusTargetModifierNode-PYyLHbc$default(Focusability.Companion.getNever-LCbbffg(), (Function2) null, 2, (Object) null));
    }
}
