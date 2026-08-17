package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"invalidateDraw", "", "Landroidx/compose/ui/node/DrawModifierNode;", "dispatchDraw", "Landroidx/compose/ui/node/DelegatableNode;", "scope", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DrawModifierNodeKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v7 */
    public static final void dispatchDraw(DelegatableNode delegatableNode, ContentDrawScope contentDrawScope) {
        Modifier.Node node = delegatableNode.getNode();
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(4);
        MutableVector mutableVector = null;
        while (node != 0) {
            if (node instanceof DrawModifierNode) {
                ((DrawModifierNode) node).draw(contentDrawScope);
            } else if ((node.getKindSet() & iM4949constructorimpl) != 0 && (node instanceof DelegatingNode)) {
                Modifier.Node delegate = ((DelegatingNode) node).getDelegate();
                int i = 0;
                node = node;
                while (delegate != null) {
                    if ((delegate.getKindSet() & iM4949constructorimpl) != 0) {
                        i++;
                        if (i == 1) {
                            node = delegate;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (node != 0) {
                                mutableVector.add(node);
                                node = 0;
                            }
                            mutableVector.add(delegate);
                        }
                    }
                    delegate = delegate.getChild();
                    node = node;
                }
                if (i == 1) {
                }
            }
            node = DelegatableNodeKt.pop(mutableVector);
        }
    }

    public static final void invalidateDraw(DrawModifierNode drawModifierNode) {
        if (drawModifierNode.getNode().getIsAttached()) {
            DelegatableNodeKt.m4787requireCoordinator64DMado(drawModifierNode, NodeKind.m4949constructorimpl(1)).invalidateLayer();
        }
    }
}
