package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuComponent;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuData;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u001a\u001e\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0003\u001aQ\u0010\u000e\u001a\u00020\u0005*\u00020\u000f2\u001e\u0010\u0010\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0003\u0012\u0004\u0012\u00020\u00050\u00032#\u0010\u0011\u001a\u001f\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u0012\u0004\u0012\u00020\u00050\u0003H\u0002\u001a\f\u0010\u0012\u001a\u00020\u0013*\u00020\u000fH\u0000\"\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"appendTextContextMenuComponents", "Landroidx/compose/ui/Modifier;", "builder", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;", "", "Lkotlin/ExtensionFunctionType;", "filterTextContextMenuComponents", "filter", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuComponent;", "", "continueTraversal", "wrongNodeTypeErrorMessage", "", "traverseTextContextMenuDataNodes", "Landroidx/compose/ui/node/DelegatableNode;", "filterBlock", "builderBlock", "collectTextContextMenuData", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuData;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextContextMenuModifierKt {
    private static final boolean continueTraversal = true;
    private static final String wrongNodeTypeErrorMessage = "TextContextMenuDataNode.TraverseKey key must only be attached to instances of TextContextMenuDataNode.";

    public static boolean a(Function1 function1, Function1 function2, TraversableNode traversableNode) {
        if (traversableNode instanceof AddTextContextMenuDataComponentsNode) {
            function1.invoke(((AddTextContextMenuDataComponentsNode) traversableNode).getBuilder());
            return continueTraversal;
        }
        if (traversableNode instanceof FilterTextContextMenuDataComponentsNode) {
            function2.invoke(((FilterTextContextMenuDataComponentsNode) traversableNode).getFilter());
            return continueTraversal;
        }
        k2d.a(wrongNodeTypeErrorMessage);
        return false;
    }

    public static final Modifier appendTextContextMenuComponents(Modifier modifier, Function1<? super TextContextMenuBuilderScope, Unit> function1) {
        return modifier.then(new AddTextContextMenuDataComponentsElement(function1));
    }

    public static final TextContextMenuData collectTextContextMenuData(DelegatableNode delegatableNode) {
        final TextContextMenuBuilderScope textContextMenuBuilderScope = new TextContextMenuBuilderScope();
        traverseTextContextMenuDataNodes(delegatableNode, new TextContextMenuModifierKt$collectTextContextMenuData$1$1(textContextMenuBuilderScope), new Function1() { // from class: a5e
            public final Object invoke(Object obj) {
                return TextContextMenuModifierKt.collectTextContextMenuData$lambda$0$0(textContextMenuBuilderScope, (Function1) obj);
            }
        });
        return textContextMenuBuilderScope.build$foundation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectTextContextMenuData$lambda$0$0(TextContextMenuBuilderScope textContextMenuBuilderScope, Function1 function1) {
        function1.invoke(textContextMenuBuilderScope);
        return Unit.INSTANCE;
    }

    public static final Modifier filterTextContextMenuComponents(Modifier modifier, Function1<? super TextContextMenuComponent, Boolean> function1) {
        return modifier.then(new FilterTextContextMenuDataComponentsElement(function1));
    }

    private static final void traverseTextContextMenuDataNodes(DelegatableNode delegatableNode, final Function1<? super Function1<? super TextContextMenuComponent, Boolean>, Unit> function1, final Function1<? super Function1<? super TextContextMenuBuilderScope, Unit>, Unit> function2) {
        TraversableNodeKt.traverseAncestors(delegatableNode, TextContextMenuDataTraverseKey.INSTANCE, new Function1() { // from class: z4e
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TextContextMenuModifierKt.a(function2, function1, (TraversableNode) obj));
            }
        });
    }
}
