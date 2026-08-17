package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B<\u00123\u0010\u0003\u001a/\b\u0001\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0004¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0002H\u0016J\f\u0010\u0012\u001a\u00020\n*\u00020\u0013H\u0016J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R=\u0010\u0003\u001a/\b\u0001\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u0019"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuGestureElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuGestureNode;", "onPreShowContextMenu", "Lkotlin/Function2;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", "name", "clickLocation", "Lkotlin/coroutines/Continuation;", "", "", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "create", "update", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "equals", "", "other", "hashCode", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class TextContextMenuGestureElement extends ModifierNodeElement<TextContextMenuGestureNode> {
    private final Function2<Offset, Continuation<? super Unit>, Object> onPreShowContextMenu;

    public TextContextMenuGestureElement(Function2<? super Offset, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.onPreShowContextMenu = function2;
    }

    /* JADX INFO: renamed from: create, reason: merged with bridge method [inline-methods] */
    public TextContextMenuGestureNode m1462create() {
        return new TextContextMenuGestureNode(this.onPreShowContextMenu);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TextContextMenuGestureElement) && this.onPreShowContextMenu == ((TextContextMenuGestureElement) other).onPreShowContextMenu;
    }

    public int hashCode() {
        Function2<Offset, Continuation<? super Unit>, Object> function2 = this.onPreShowContextMenu;
        if (function2 != null) {
            return function2.hashCode();
        }
        return 0;
    }

    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("TextContextMenuGestures");
        inspectorInfo.getProperties().set("onPreShowContextMenu", this.onPreShowContextMenu);
    }

    public void update(TextContextMenuGestureNode node) {
        node.update(this.onPreShowContextMenu);
    }
}
