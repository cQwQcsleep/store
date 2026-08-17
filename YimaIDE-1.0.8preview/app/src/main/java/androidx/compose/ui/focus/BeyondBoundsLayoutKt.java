package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\u0010\u0005\u001a\u0015\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0006¢\u0006\u0002\b\bH\u0000¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"searchBeyondBounds", "T", "Landroidx/compose/ui/focus/FocusTargetNode;", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "Lkotlin/ExtensionFunctionType;", "searchBeyondBounds--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BeyondBoundsLayoutKt {
    /* JADX INFO: renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final <T> T m2754searchBeyondBoundsOMvw8(FocusTargetNode focusTargetNode, int i, Function1<? super BeyondBoundsLayout.BeyondBoundsScope, ? extends T> function1) {
        Modifier.Node nodePop;
        BeyondBoundsLayout beyondBoundsLayoutParent;
        int iM4593getBeforehoxUOeE;
        NodeChain nodes;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
        if (!focusTargetNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = focusTargetNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        loop0: while (true) {
            if (layoutNodeRequireLayoutNode == null) {
                nodePop = null;
                break;
            }
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4949constructorimpl) != 0) {
                        nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                break loop0;
                            }
                            if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i2 = 0;
                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & iM4949constructorimpl) != 0) {
                                        i2++;
                                        if (i2 == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector.add(delegate);
                                        }
                                    }
                                }
                                if (i2 == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
        if ((focusTargetNode2 == null || !Intrinsics.areEqual(focusTargetNode2.getBeyondBoundsLayoutParent(), focusTargetNode.getBeyondBoundsLayoutParent())) && (beyondBoundsLayoutParent = focusTargetNode.getBeyondBoundsLayoutParent()) != null) {
            FocusDirection.Companion companion = FocusDirection.INSTANCE;
            if (FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s())) {
                iM4593getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4591getAbovehoxUOeE();
            } else if (FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
                iM4593getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4594getBelowhoxUOeE();
            } else if (FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s())) {
                iM4593getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4595getLefthoxUOeE();
            } else if (FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s())) {
                iM4593getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4596getRighthoxUOeE();
            } else if (FocusDirection.m2759equalsimpl0(i, companion.m2767getNextdhqQ8s())) {
                iM4593getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4592getAfterhoxUOeE();
            } else if (FocusDirection.m2759equalsimpl0(i, companion.m2768getPreviousdhqQ8s())) {
                iM4593getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4593getBeforehoxUOeE();
            } else {
                k2d.a("Unsupported direction for beyond bounds layout");
            }
            return (T) beyondBoundsLayoutParent.m4583layouto7g1Pn8(iM4593getBeforehoxUOeE, function1);
        }
        return null;
    }
}
