package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.AbstractApplier;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0016J\b\u0010\u000e\u001a\u00020\u0007H\u0014J \u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0016J\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0002H\u0002¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorApplier;", "Landroidx/compose/runtime/AbstractApplier;", "Landroidx/compose/ui/graphics/vector/VNode;", "root", "<init>", "(Landroidx/compose/ui/graphics/vector/VNode;)V", "insertTopDown", "", "index", "", "instance", "insertBottomUp", "remove", "count", "onClear", "move", "from", "to", "asGroup", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class VectorApplier extends AbstractApplier<VNode> {
    public static final int $stable = AbstractApplier.$stable;

    public VectorApplier(VNode vNode) {
        super(vNode);
    }

    private final GroupComponent asGroup(VNode vNode) {
        if (vNode instanceof GroupComponent) {
            return (GroupComponent) vNode;
        }
        k2d.a("Cannot only insert VNode into Group");
        return null;
    }

    @Override // androidx.compose.runtime.Applier
    public void insertBottomUp(int index, VNode instance) {
        asGroup(getCurrent()).insertAt(index, instance);
    }

    @Override // androidx.compose.runtime.Applier
    public void move(int from, int to, int count) {
        asGroup(getCurrent()).move(from, to, count);
    }

    @Override // androidx.compose.runtime.AbstractApplier
    public void onClear() {
        GroupComponent groupComponentAsGroup = asGroup(getRoot());
        groupComponentAsGroup.remove(0, groupComponentAsGroup.getNumChildren());
    }

    @Override // androidx.compose.runtime.Applier
    public void remove(int index, int count) {
        asGroup(getCurrent()).remove(index, count);
    }

    @Override // androidx.compose.runtime.Applier
    public void insertTopDown(int index, VNode instance) {
    }
}
