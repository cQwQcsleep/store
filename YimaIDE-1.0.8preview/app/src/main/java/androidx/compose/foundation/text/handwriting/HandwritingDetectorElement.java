package androidx.compose.foundation.text.handwriting;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\u0002H\u0016J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\f\u0010\u0011\u001a\u00020\u0005*\u00020\u0012H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/text/handwriting/HandwritingDetectorElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/text/handwriting/HandwritingDetectorNode;", "callback", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "create", "update", "node", "hashCode", "", "equals", "", "other", "", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class HandwritingDetectorElement extends ModifierNodeElement<HandwritingDetectorNode> {
    private final Function0<Unit> callback;

    public HandwritingDetectorElement(Function0<Unit> function0) {
        this.callback = function0;
    }

    /* JADX INFO: renamed from: create, reason: merged with bridge method [inline-methods] */
    public HandwritingDetectorNode m1468create() {
        return new HandwritingDetectorNode(this.callback);
    }

    public boolean equals(Object other) {
        boolean z = false;
        boolean z2 = this == other;
        if ((other instanceof HandwritingDetectorElement) && this.callback == ((HandwritingDetectorElement) other).callback) {
            z = true;
        }
        return z2 | z;
    }

    public int hashCode() {
        return this.callback.hashCode() * 31;
    }

    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("handwritingDetector");
        inspectorInfo.getProperties().set("callback", this.callback);
    }

    public void update(HandwritingDetectorNode node) {
        node.setCallback(this.callback);
    }
}
