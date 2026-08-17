package androidx.compose.ui.viewinterop;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012,\u0010\u0003\u001a(\u0012\u001a\u0012\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004j\u0004\u0018\u0001`\u0007\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\f\u0010\u0016\u001a\u00020\u0006*\u00020\u0017H\u0016R7\u0010\u0003\u001a(\u0012\u001a\u0012\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004j\u0004\u0018\u0001`\u0007\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/viewinterop/BringIntoViewElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/viewinterop/BringIntoViewNode;", "onRequesterReady", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Rect;", "", "Landroidx/compose/ui/viewinterop/BringIntoViewRequester;", "Landroidx/compose/ui/viewinterop/OnRequesterReady;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getOnRequesterReady", "()Lkotlin/jvm/functions/Function1;", "create", "update", "node", "hashCode", "", "equals", "", "other", "", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class BringIntoViewElement extends ModifierNodeElement<BringIntoViewNode> {
    private final Function1<Function1<? super Rect, Unit>, Unit> onRequesterReady;

    public BringIntoViewElement(Function1<? super Function1<? super Rect, Unit>, Unit> function1) {
        this.onRequesterReady = function1;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public BringIntoViewNode getNode() {
        return new BringIntoViewNode(this.onRequesterReady);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof BringIntoViewElement) && this.onRequesterReady == ((BringIntoViewElement) other).onRequesterReady;
        }
        return true;
    }

    public final Function1<Function1<? super Rect, Unit>, Unit> getOnRequesterReady() {
        return this.onRequesterReady;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.onRequesterReady.hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("requestRectangleBringIntoViewBridge");
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(BringIntoViewNode node) {
        node.update(this.onRequesterReady);
    }
}
