package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0007J\u0017\u0010\t\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0010\u001a\u00020\u000b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000b0\u0012H\u0000¢\u0006\u0002\b\u0014J\u0006\u0010\u0015\u001a\u00020\u000bJ\u0006\u0010\u0016\u001a\u00020\u000bJ\u0006\u0010\u0017\u001a\u00020\u000bJ\u0006\u0010\u0018\u001a\u00020\u000bJ\"\u0010\u0019\u001a\u00020\u000b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000b0\u0012H\u0080\b¢\u0006\u0002\b\u001aR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/focus/FocusRequester;", "", "<init>", "()V", "focusRequesterNodes", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "getFocusRequesterNodes$ui", "()Landroidx/compose/runtime/collection/MutableVector;", "requestFocus", "", "", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "requestFocus-3ESFkO8", "(I)Z", "findFocusTargetNode", "onFound", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusTargetNode;", "findFocusTargetNode$ui", "captureFocus", "freeFocus", "saveFocusedChild", "restoreFocusedChild", "findFocusTarget", "findFocusTarget$ui", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FocusRequester {
    public static final int $stable = 0;
    private final MutableVector<FocusRequesterModifierNode> focusRequesterNodes = new MutableVector<>(new FocusRequesterModifierNode[16], 0);

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FocusRequester Default = new FocusRequester();
    private static final FocusRequester Cancel = new FocusRequester();
    private static final FocusRequester Redirect = new FocusRequester();

    /* JADX INFO: renamed from: requestFocus-3ESFkO8$default, reason: not valid java name */
    public static /* synthetic */ boolean m2792requestFocus3ESFkO8$default(FocusRequester focusRequester, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = FocusDirection.INSTANCE.m2764getEnterdhqQ8s();
        }
        return focusRequester.m2793requestFocus3ESFkO8(i);
    }

    public final boolean captureFocus() {
        if (this.focusRequesterNodes.getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> mutableVector = this.focusRequesterNodes;
        FocusRequesterModifierNode[] focusRequesterModifierNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            if (FocusRequesterModifierNodeKt.captureFocus(focusRequesterModifierNodeArr[i])) {
                return true;
            }
        }
        return false;
    }

    public final boolean findFocusTarget$ui(Function1<? super FocusTargetNode, Boolean> onFound) {
        Companion companion = INSTANCE;
        if (this == companion.getDefault()) {
            k2d.a("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
            return false;
        }
        if (this == companion.getCancel()) {
            k2d.a("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
            return false;
        }
        if (getFocusRequesterNodes$ui().getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> focusRequesterNodes$ui = getFocusRequesterNodes$ui();
        FocusRequesterModifierNode[] focusRequesterModifierNodeArr = focusRequesterNodes$ui.content;
        int size = focusRequesterNodes$ui.getSize();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            FocusRequesterModifierNode focusRequesterModifierNode = focusRequesterModifierNodeArr[i];
            int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
            if (!focusRequesterModifierNode.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
            }
            MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
            Modifier.Node child = focusRequesterModifierNode.getNode().getChild();
            if (child == null) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusRequesterModifierNode.getNode(), false);
            } else {
                mutableVector.add(child);
            }
            while (mutableVector.getSize() != 0) {
                Modifier.Node nodePop = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
                if ((nodePop.getAggregateChildKindSet() & iM4949constructorimpl) == 0) {
                    DelegatableNodeKt.addLayoutNodeChildren(mutableVector, nodePop, false);
                } else {
                    while (nodePop != null) {
                        if ((nodePop.getKindSet() & iM4949constructorimpl) != 0) {
                            MutableVector mutableVector2 = null;
                            while (nodePop != null) {
                                if (nodePop instanceof FocusTargetNode) {
                                    if (((Boolean) onFound.invoke((FocusTargetNode) nodePop)).booleanValue()) {
                                        z = true;
                                        break;
                                    }
                                } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i2 = 0;
                                    for (Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate(); delegate$ui != null; delegate$ui = delegate$ui.getChild()) {
                                        if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                            i2++;
                                            if (i2 == 1) {
                                                nodePop = delegate$ui;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector2.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector2.add(delegate$ui);
                                            }
                                        }
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                            }
                            break;
                        }
                        nodePop = nodePop.getChild();
                    }
                }
            }
        }
        return z;
    }

    public final boolean findFocusTargetNode$ui(Function1<? super FocusTargetNode, Boolean> onFound) {
        Companion companion = INSTANCE;
        if (this == companion.getDefault()) {
            k2d.a("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
            return false;
        }
        if (this == companion.getCancel()) {
            k2d.a("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
            return false;
        }
        if (getFocusRequesterNodes$ui().getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> focusRequesterNodes$ui = getFocusRequesterNodes$ui();
        FocusRequesterModifierNode[] focusRequesterModifierNodeArr = focusRequesterNodes$ui.content;
        int size = focusRequesterNodes$ui.getSize();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            FocusRequesterModifierNode focusRequesterModifierNode = focusRequesterModifierNodeArr[i];
            int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
            if (!focusRequesterModifierNode.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
            }
            MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
            Modifier.Node child = focusRequesterModifierNode.getNode().getChild();
            if (child == null) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusRequesterModifierNode.getNode(), false);
            } else {
                mutableVector.add(child);
            }
            while (mutableVector.getSize() != 0) {
                Modifier.Node nodePop = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
                if ((nodePop.getAggregateChildKindSet() & iM4949constructorimpl) == 0) {
                    DelegatableNodeKt.addLayoutNodeChildren(mutableVector, nodePop, false);
                } else {
                    while (nodePop != null) {
                        if ((nodePop.getKindSet() & iM4949constructorimpl) != 0) {
                            MutableVector mutableVector2 = null;
                            while (nodePop != null) {
                                if (nodePop instanceof FocusTargetNode) {
                                    FocusTargetNode focusTargetNode = (FocusTargetNode) nodePop;
                                    if (focusTargetNode.fetchFocusProperties$ui().getCanFocus() ? ((Boolean) onFound.invoke(focusTargetNode)).booleanValue() : TwoDimensionalFocusSearchKt.m2835findChildCorrespondingToFocusEnterOMvw8(focusTargetNode, FocusDirection.INSTANCE.m2764getEnterdhqQ8s(), onFound)) {
                                        z = true;
                                        break;
                                    }
                                } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i2 = 0;
                                    for (Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate(); delegate$ui != null; delegate$ui = delegate$ui.getChild()) {
                                        if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                            i2++;
                                            if (i2 == 1) {
                                                nodePop = delegate$ui;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector2.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector2.add(delegate$ui);
                                            }
                                        }
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                            }
                            break;
                        }
                        nodePop = nodePop.getChild();
                    }
                }
            }
        }
        return z;
    }

    public final boolean freeFocus() {
        if (this.focusRequesterNodes.getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> mutableVector = this.focusRequesterNodes;
        FocusRequesterModifierNode[] focusRequesterModifierNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            if (FocusRequesterModifierNodeKt.freeFocus(focusRequesterModifierNodeArr[i])) {
                return true;
            }
        }
        return false;
    }

    public final MutableVector<FocusRequesterModifierNode> getFocusRequesterNodes$ui() {
        return this.focusRequesterNodes;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "use the version the has a FocusDirection", replaceWith = @ReplaceWith(expression = "this.requestFocus()", imports = {}))
    public final /* synthetic */ void requestFocus() {
        m2793requestFocus3ESFkO8(FocusDirection.INSTANCE.m2764getEnterdhqQ8s());
    }

    /* JADX INFO: renamed from: requestFocus-3ESFkO8, reason: not valid java name */
    public final boolean m2793requestFocus3ESFkO8(final int focusDirection) {
        if (!ComposeUiFlags.isRequestFocusOnNonFocusableFocusTargetEnabled) {
            return findFocusTargetNode$ui(new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusRequester.requestFocus.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public final Boolean invoke(FocusTargetNode focusTargetNode) {
                    return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(focusDirection));
                }
            });
        }
        Companion companion = INSTANCE;
        if (this == companion.getDefault()) {
            k2d.a("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
            return false;
        }
        if (this == companion.getCancel()) {
            k2d.a("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
            return false;
        }
        if (getFocusRequesterNodes$ui().getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> focusRequesterNodes$ui = getFocusRequesterNodes$ui();
        FocusRequesterModifierNode[] focusRequesterModifierNodeArr = focusRequesterNodes$ui.content;
        int size = focusRequesterNodes$ui.getSize();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            FocusRequesterModifierNode focusRequesterModifierNode = focusRequesterModifierNodeArr[i];
            int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
            if (!focusRequesterModifierNode.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
            }
            MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
            Modifier.Node child = focusRequesterModifierNode.getNode().getChild();
            if (child == null) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusRequesterModifierNode.getNode(), false);
            } else {
                mutableVector.add(child);
            }
            while (mutableVector.getSize() != 0) {
                Modifier.Node nodePop = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
                if ((nodePop.getAggregateChildKindSet() & iM4949constructorimpl) == 0) {
                    DelegatableNodeKt.addLayoutNodeChildren(mutableVector, nodePop, false);
                } else {
                    while (nodePop != null) {
                        if ((nodePop.getKindSet() & iM4949constructorimpl) != 0) {
                            MutableVector mutableVector2 = null;
                            while (nodePop != null) {
                                if (nodePop instanceof FocusTargetNode) {
                                    if (((FocusTargetNode) nodePop).mo2796requestFocus3ESFkO8(focusDirection)) {
                                        z = true;
                                        break;
                                    }
                                } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i2 = 0;
                                    for (Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate(); delegate$ui != null; delegate$ui = delegate$ui.getChild()) {
                                        if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                            i2++;
                                            if (i2 == 1) {
                                                nodePop = delegate$ui;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector2.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector2.add(delegate$ui);
                                            }
                                        }
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                            }
                            break;
                        }
                        nodePop = nodePop.getChild();
                    }
                }
            }
        }
        return z;
    }

    public final boolean restoreFocusedChild() {
        if (this.focusRequesterNodes.getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> mutableVector = this.focusRequesterNodes;
        FocusRequesterModifierNode[] focusRequesterModifierNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            z = FocusRequesterModifierNodeKt.restoreFocusedChild(focusRequesterModifierNodeArr[i]) || z;
        }
        return z;
    }

    public final boolean saveFocusedChild() {
        if (this.focusRequesterNodes.getSize() == 0) {
            System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
            return false;
        }
        MutableVector<FocusRequesterModifierNode> mutableVector = this.focusRequesterNodes;
        FocusRequesterModifierNode[] focusRequesterModifierNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            if (FocusRequesterModifierNodeKt.saveFocusedChild(focusRequesterModifierNodeArr[i])) {
                return true;
            }
        }
        return false;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/focus/FocusRequester$Companion;", "", "<init>", "()V", "Default", "Landroidx/compose/ui/focus/FocusRequester;", "getDefault", "()Landroidx/compose/ui/focus/FocusRequester;", "Cancel", "getCancel", "Redirect", "getRedirect$ui", "createRefs", "Landroidx/compose/ui/focus/FocusRequester$Companion$FocusRequesterFactory;", "FocusRequesterFactory", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\u0004\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0006\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0007\u001a\u00020\u0005H\u0086\u0002J\t\u0010\b\u001a\u00020\u0005H\u0086\u0002J\t\u0010\t\u001a\u00020\u0005H\u0086\u0002J\t\u0010\n\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u000b\u001a\u00020\u0005H\u0086\u0002J\t\u0010\f\u001a\u00020\u0005H\u0086\u0002J\t\u0010\r\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u000e\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u000f\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0010\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0011\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0012\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0013\u001a\u00020\u0005H\u0086\u0002J\t\u0010\u0014\u001a\u00020\u0005H\u0086\u0002¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/focus/FocusRequester$Companion$FocusRequesterFactory;", "", "<init>", "()V", "component1", "Landroidx/compose/ui/focus/FocusRequester;", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class FocusRequesterFactory {
            public static final int $stable = 0;
            public static final FocusRequesterFactory INSTANCE = new FocusRequesterFactory();

            private FocusRequesterFactory() {
            }

            public final FocusRequester component1() {
                return new FocusRequester();
            }

            public final FocusRequester component10() {
                return new FocusRequester();
            }

            public final FocusRequester component11() {
                return new FocusRequester();
            }

            public final FocusRequester component12() {
                return new FocusRequester();
            }

            public final FocusRequester component13() {
                return new FocusRequester();
            }

            public final FocusRequester component14() {
                return new FocusRequester();
            }

            public final FocusRequester component15() {
                return new FocusRequester();
            }

            public final FocusRequester component16() {
                return new FocusRequester();
            }

            public final FocusRequester component2() {
                return new FocusRequester();
            }

            public final FocusRequester component3() {
                return new FocusRequester();
            }

            public final FocusRequester component4() {
                return new FocusRequester();
            }

            public final FocusRequester component5() {
                return new FocusRequester();
            }

            public final FocusRequester component6() {
                return new FocusRequester();
            }

            public final FocusRequester component7() {
                return new FocusRequester();
            }

            public final FocusRequester component8() {
                return new FocusRequester();
            }

            public final FocusRequester component9() {
                return new FocusRequester();
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FocusRequesterFactory createRefs() {
            return FocusRequesterFactory.INSTANCE;
        }

        public final FocusRequester getCancel() {
            return FocusRequester.Cancel;
        }

        public final FocusRequester getDefault() {
            return FocusRequester.Default;
        }

        public final FocusRequester getRedirect$ui() {
            return FocusRequester.Redirect;
        }

        private Companion() {
        }
    }
}
