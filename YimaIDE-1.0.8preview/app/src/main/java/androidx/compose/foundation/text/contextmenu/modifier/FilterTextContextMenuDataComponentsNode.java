package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.foundation.text.contextmenu.data.TextContextMenuComponent;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.TraversableNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bR&\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/modifier/FilterTextContextMenuDataComponentsNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/TraversableNode;", "filter", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuComponent;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getFilter", "()Lkotlin/jvm/functions/Function1;", "setFilter", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class FilterTextContextMenuDataComponentsNode extends Modifier.Node implements TraversableNode {
    private Function1<? super TextContextMenuComponent, Boolean> filter;

    public FilterTextContextMenuDataComponentsNode(Function1<? super TextContextMenuComponent, Boolean> function1) {
        this.filter = function1;
    }

    public final Function1<TextContextMenuComponent, Boolean> getFilter() {
        return this.filter;
    }

    public Object getTraverseKey() {
        return TextContextMenuDataTraverseKey.INSTANCE;
    }

    public final void setFilter(Function1<? super TextContextMenuComponent, Boolean> function1) {
        this.filter = function1;
    }
}
