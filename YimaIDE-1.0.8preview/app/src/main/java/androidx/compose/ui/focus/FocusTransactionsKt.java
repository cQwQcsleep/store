package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u001e\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a \u0010\t\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0001H\u0002\u001a)\u0010\n\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\b\u000f\u001a\f\u0010\u0010\u001a\u00020\u0002*\u00020\u0002H\u0002\u001a\u001b\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001b\u0010\u0015\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0016\u0010\u0014\u001a\u001b\u0010\u0017\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0018\u0010\u0014\u001a\u001b\u0010\u0019\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\u001a\u0010\u0014¨\u0006\u001b"}, d2 = {"performRequestFocus", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "captureFocus", "freeFocus", "clearFocus", "forced", "refreshFocusEvents", "grantFocus", "clearChildFocus", "requestOwnerFocus", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "requestOwnerFocus-Etdf9zw", "requireActiveChild", "performCustomRequestFocus", "Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomRequestFocus-Mxy_nc0", "(Landroidx/compose/ui/focus/FocusTargetNode;I)Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomClearFocus", "performCustomClearFocus-Mxy_nc0", "performCustomEnter", "performCustomEnter-Mxy_nc0", "performCustomExit", "performCustomExit-Mxy_nc0", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FocusTransactionsKt {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final boolean captureFocus(FocusTargetNode focusTargetNode) {
        int i = WhenMappings.$EnumSwitchMapping$0[focusTargetNode.getFocusState().ordinal()];
        boolean z = true;
        if (i == 1) {
            DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().setFocusCaptured(true);
            focusTargetNode.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Captured);
            return true;
        }
        if (i != 2) {
            z = false;
            if (i != 3 && i != 4) {
                bu8.a();
            }
        }
        return z;
    }

    private static final boolean clearChildFocus(FocusTargetNode focusTargetNode, boolean z, boolean z2) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild != null) {
            return clearFocus(activeChild, z, z2);
        }
        return true;
    }

    public static /* synthetic */ boolean clearChildFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return clearChildFocus(focusTargetNode, z, z2);
    }

    public static final boolean clearFocus(FocusTargetNode focusTargetNode, boolean z, boolean z2) {
        int i = WhenMappings.$EnumSwitchMapping$0[focusTargetNode.getFocusState().ordinal()];
        if (i == 1) {
            if (!ComposeUiFlags.isOptimizedFocusEventDispatchEnabled) {
                DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().setActiveFocusTargetNode(null);
                if (z2) {
                    focusTargetNode.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
                }
            }
            return true;
        }
        if (i == 2) {
            if (z && !ComposeUiFlags.isOptimizedFocusEventDispatchEnabled) {
                DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().setActiveFocusTargetNode(null);
                if (z2) {
                    focusTargetNode.dispatchFocusCallbacks$ui(FocusStateImpl.Captured, FocusStateImpl.Inactive);
                }
            }
            return z;
        }
        if (i != 3) {
            if (i == 4) {
                return true;
            }
            bu8.a();
            return false;
        }
        if (!clearChildFocus(focusTargetNode, z, z2)) {
            return false;
        }
        if (z2) {
            focusTargetNode.dispatchFocusCallbacks$ui(FocusStateImpl.ActiveParent, FocusStateImpl.Inactive);
        }
        return true;
    }

    public static /* synthetic */ boolean clearFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return clearFocus(focusTargetNode, z, z2);
    }

    public static final boolean freeFocus(FocusTargetNode focusTargetNode) {
        int i = WhenMappings.$EnumSwitchMapping$0[focusTargetNode.getFocusState().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3 && i != 4) {
                    bu8.a();
                }
                return false;
            }
            DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().setFocusCaptured(false);
            focusTargetNode.dispatchFocusCallbacks$ui(FocusStateImpl.Captured, FocusStateImpl.Active);
        }
        return true;
    }

    private static final boolean grantFocus(final FocusTargetNode focusTargetNode) {
        ObserverModifierNodeKt.observeReads(focusTargetNode, new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTransactionsKt.grantFocus.1
            {
                super(0);
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m2812invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m2812invoke() {
                focusTargetNode.fetchFocusProperties$ui();
            }
        });
        int i = WhenMappings.$EnumSwitchMapping$0[focusTargetNode.getFocusState().ordinal()];
        if (i != 1 && i != 2) {
            if (i != 3 && i != 4) {
                bu8.a();
                return false;
            }
            DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().setActiveFocusTargetNode(focusTargetNode);
        }
        return true;
    }

    /* JADX INFO: renamed from: performCustomClearFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m2806performCustomClearFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        int i2 = WhenMappings.$EnumSwitchMapping$0[focusTargetNode.getFocusState().ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return CustomDestinationResult.Cancelled;
            }
            if (i2 == 3) {
                CustomDestinationResult customDestinationResultM2806performCustomClearFocusMxy_nc0 = m2806performCustomClearFocusMxy_nc0(requireActiveChild(focusTargetNode), i);
                CustomDestinationResult customDestinationResult = customDestinationResultM2806performCustomClearFocusMxy_nc0 != CustomDestinationResult.None ? customDestinationResultM2806performCustomClearFocusMxy_nc0 : null;
                return customDestinationResult == null ? m2808performCustomExitMxy_nc0(focusTargetNode, i) : customDestinationResult;
            }
            if (i2 != 4) {
                bu8.a();
                return null;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomEnter-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m2807performCustomEnterMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        if (!focusTargetNode.isProcessingCustomEnter) {
            focusTargetNode.isProcessingCustomEnter = true;
            try {
                FocusProperties focusPropertiesFetchFocusProperties$ui = focusTargetNode.fetchFocusProperties$ui();
                CancelIndicatingFocusBoundaryScope cancelIndicatingFocusBoundaryScope = new CancelIndicatingFocusBoundaryScope(i, null);
                FocusOwner focusOwner = DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner();
                FocusTargetNode activeFocusTargetNode = focusOwner.getActiveFocusTargetNode();
                focusPropertiesFetchFocusProperties$ui.getOnEnter().invoke(cancelIndicatingFocusBoundaryScope);
                FocusTargetNode activeFocusTargetNode2 = focusOwner.getActiveFocusTargetNode();
                if (cancelIndicatingFocusBoundaryScope.getIsCanceled()) {
                    FocusRequester.Companion companion = FocusRequester.INSTANCE;
                    FocusRequester cancel = companion.getCancel();
                    if (cancel == companion.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    if (cancel == companion.getRedirect$ui()) {
                        return CustomDestinationResult.Redirected;
                    }
                    return FocusRequester.m2792requestFocus3ESFkO8$default(cancel, 0, 1, null) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
                if (activeFocusTargetNode != activeFocusTargetNode2 && activeFocusTargetNode2 != null) {
                    FocusRequester.Companion companion2 = FocusRequester.INSTANCE;
                    FocusRequester redirect$ui = companion2.getRedirect$ui();
                    if (redirect$ui == companion2.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    if (redirect$ui == companion2.getRedirect$ui()) {
                        return CustomDestinationResult.Redirected;
                    }
                    return FocusRequester.m2792requestFocus3ESFkO8$default(redirect$ui, 0, 1, null) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
            } finally {
                focusTargetNode.isProcessingCustomEnter = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomExit-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m2808performCustomExitMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        if (!focusTargetNode.isProcessingCustomExit) {
            focusTargetNode.isProcessingCustomExit = true;
            try {
                FocusProperties focusPropertiesFetchFocusProperties$ui = focusTargetNode.fetchFocusProperties$ui();
                CancelIndicatingFocusBoundaryScope cancelIndicatingFocusBoundaryScope = new CancelIndicatingFocusBoundaryScope(i, null);
                FocusOwner focusOwner = DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner();
                FocusTargetNode activeFocusTargetNode = focusOwner.getActiveFocusTargetNode();
                focusPropertiesFetchFocusProperties$ui.getOnExit().invoke(cancelIndicatingFocusBoundaryScope);
                FocusTargetNode activeFocusTargetNode2 = focusOwner.getActiveFocusTargetNode();
                if (cancelIndicatingFocusBoundaryScope.getIsCanceled()) {
                    FocusRequester.Companion companion = FocusRequester.INSTANCE;
                    FocusRequester cancel = companion.getCancel();
                    if (cancel == companion.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    if (cancel == companion.getRedirect$ui()) {
                        return CustomDestinationResult.Redirected;
                    }
                    return FocusRequester.m2792requestFocus3ESFkO8$default(cancel, 0, 1, null) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
                if (activeFocusTargetNode != activeFocusTargetNode2 && activeFocusTargetNode2 != null) {
                    FocusRequester.Companion companion2 = FocusRequester.INSTANCE;
                    FocusRequester redirect$ui = companion2.getRedirect$ui();
                    if (redirect$ui == companion2.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    if (redirect$ui == companion2.getRedirect$ui()) {
                        return CustomDestinationResult.Redirected;
                    }
                    return FocusRequester.m2792requestFocus3ESFkO8$default(redirect$ui, 0, 1, null) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
            } finally {
                focusTargetNode.isProcessingCustomExit = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomRequestFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m2809performCustomRequestFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        Modifier.Node nodePop;
        NodeChain nodes;
        int i2 = WhenMappings.$EnumSwitchMapping$0[focusTargetNode.getFocusState().ordinal()];
        if (i2 == 1 || i2 == 2) {
            return CustomDestinationResult.None;
        }
        if (i2 == 3) {
            return m2806performCustomClearFocusMxy_nc0(requireActiveChild(focusTargetNode), i);
        }
        if (i2 != 4) {
            bu8.a();
            return null;
        }
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
                        MutableVector mutableVector = null;
                        nodePop = parent;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                break loop0;
                            }
                            if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i3 = 0;
                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & iM4949constructorimpl) != 0) {
                                        i3++;
                                        if (i3 == 1) {
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
                                if (i3 == 1) {
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
        if (focusTargetNode2 == null) {
            return CustomDestinationResult.None;
        }
        int i4 = WhenMappings.$EnumSwitchMapping$0[focusTargetNode2.getFocusState().ordinal()];
        if (i4 == 1) {
            return m2807performCustomEnterMxy_nc0(focusTargetNode2, i);
        }
        if (i4 == 2) {
            return CustomDestinationResult.Cancelled;
        }
        if (i4 == 3) {
            return m2809performCustomRequestFocusMxy_nc0(focusTargetNode2, i);
        }
        if (i4 != 4) {
            bu8.a();
            return null;
        }
        CustomDestinationResult customDestinationResultM2809performCustomRequestFocusMxy_nc0 = m2809performCustomRequestFocusMxy_nc0(focusTargetNode2, i);
        CustomDestinationResult customDestinationResult = customDestinationResultM2809performCustomRequestFocusMxy_nc0 != CustomDestinationResult.None ? customDestinationResultM2809performCustomRequestFocusMxy_nc0 : null;
        return customDestinationResult == null ? m2807performCustomEnterMxy_nc0(focusTargetNode2, i) : customDestinationResult;
    }

    public static final boolean performRequestFocus(FocusTargetNode focusTargetNode) {
        MutableVector mutableVector;
        NodeChain nodes;
        NodeChain nodes2;
        FocusOwner focusOwner = DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner();
        FocusTargetNode activeFocusTargetNode = focusOwner.getActiveFocusTargetNode();
        FocusStateImpl focusState = focusTargetNode.getFocusState();
        int i = 1;
        if (activeFocusTargetNode == focusTargetNode) {
            focusTargetNode.dispatchFocusCallbacks$ui(focusState, focusState);
            return true;
        }
        int i2 = 0;
        MutableVector mutableVector2 = null;
        if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
            if ((activeFocusTargetNode == null || activeFocusTargetNode.getIsInteropViewHost()) && !focusTargetNode.getIsInteropViewHost() && !m2811requestOwnerFocusEtdf9zw$default(focusTargetNode, null, null, 3, null)) {
                return false;
            }
        } else if (activeFocusTargetNode == null && !m2811requestOwnerFocusEtdf9zw$default(focusTargetNode, null, null, 3, null)) {
            return false;
        }
        int i3 = 1024;
        int i4 = 16;
        if (activeFocusTargetNode != null) {
            mutableVector = new MutableVector(new FocusTargetNode[16], 0);
            int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
            if (!activeFocusTargetNode.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node parent = activeFocusTargetNode.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(activeFocusTargetNode);
            while (layoutNodeRequireLayoutNode != null) {
                if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                    while (parent != null) {
                        if ((parent.getKindSet() & iM4949constructorimpl) != 0) {
                            MutableVector mutableVector3 = mutableVector2;
                            Modifier.Node nodePop = parent;
                            while (nodePop != null) {
                                i3 = i3;
                                if (nodePop instanceof FocusTargetNode) {
                                    mutableVector.add((FocusTargetNode) nodePop);
                                } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                    int i5 = 0;
                                    while (delegate != null) {
                                        if ((delegate.getKindSet() & iM4949constructorimpl) != 0) {
                                            i5++;
                                            if (i5 == i) {
                                                nodePop = delegate;
                                            } else {
                                                if (mutableVector3 == null) {
                                                    mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector3.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector3.add(delegate);
                                            }
                                        }
                                        delegate = delegate.getChild();
                                        i = 1;
                                    }
                                    if (i5 == i) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector3);
                                i = 1;
                            }
                        }
                        parent = parent.getParent();
                        i3 = i3;
                        i = 1;
                        mutableVector2 = null;
                    }
                }
                int i6 = i3;
                layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
                parent = (layoutNodeRequireLayoutNode == null || (nodes2 = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes2.getTail();
                i3 = i6;
                i = 1;
                mutableVector2 = null;
            }
        } else {
            mutableVector = null;
        }
        int i7 = i3;
        MutableVector mutableVector4 = new MutableVector(new FocusTargetNode[16], 0);
        int iM4949constructorimpl2 = NodeKind.m4949constructorimpl(i7);
        if (!focusTargetNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent2 = focusTargetNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        int i8 = 1;
        while (layoutNodeRequireLayoutNode2 != null) {
            if ((layoutNodeRequireLayoutNode2.getNodes().getHead().getAggregateChildKindSet() & iM4949constructorimpl2) != 0) {
                while (parent2 != null) {
                    if ((parent2.getKindSet() & iM4949constructorimpl2) != 0) {
                        Modifier.Node nodePop2 = parent2;
                        MutableVector mutableVector5 = null;
                        while (nodePop2 != null) {
                            if (nodePop2 instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop2;
                                Boolean boolValueOf = mutableVector != null ? Boolean.valueOf(mutableVector.remove(focusTargetNode2)) : null;
                                if (boolValueOf == null || !boolValueOf.booleanValue()) {
                                    mutableVector4.add(focusTargetNode2);
                                }
                                if (focusTargetNode2 == activeFocusTargetNode) {
                                    i8 = i2;
                                }
                            } else {
                                if ((nodePop2.getKindSet() & iM4949constructorimpl2) != 0 && (nodePop2 instanceof DelegatingNode)) {
                                    Modifier.Node delegate2 = ((DelegatingNode) nodePop2).getDelegate();
                                    int i9 = i2;
                                    while (delegate2 != null) {
                                        if ((delegate2.getKindSet() & iM4949constructorimpl2) != 0) {
                                            i9++;
                                            if (i9 == 1) {
                                                nodePop2 = delegate2;
                                            } else {
                                                if (mutableVector5 == null) {
                                                    mutableVector5 = new MutableVector(new Modifier.Node[i4], 0);
                                                }
                                                if (nodePop2 != null) {
                                                    mutableVector5.add(nodePop2);
                                                    nodePop2 = null;
                                                }
                                                mutableVector5.add(delegate2);
                                            }
                                        }
                                        delegate2 = delegate2.getChild();
                                        i4 = 16;
                                    }
                                    if (i9 == 1) {
                                    }
                                }
                                i2 = 0;
                                i4 = 16;
                            }
                            nodePop2 = DelegatableNodeKt.pop(mutableVector5);
                            i2 = 0;
                            i4 = 16;
                        }
                    }
                    parent2 = parent2.getParent();
                    i2 = 0;
                    i4 = 16;
                }
            }
            layoutNodeRequireLayoutNode2 = layoutNodeRequireLayoutNode2.getParent$ui();
            parent2 = (layoutNodeRequireLayoutNode2 == null || (nodes = layoutNodeRequireLayoutNode2.getNodes()) == null) ? null : nodes.getTail();
            i2 = 0;
            i4 = 16;
        }
        if (i8 != 0 && activeFocusTargetNode != null && !clearFocus$default(activeFocusTargetNode, false, true, 1, null)) {
            return false;
        }
        grantFocus(focusTargetNode);
        if (ComposeUiFlags.isOptimizedFocusEventDispatchEnabled && i8 != 0 && activeFocusTargetNode != null) {
            activeFocusTargetNode.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
        }
        if (mutableVector != null) {
            int size = mutableVector.getSize() - 1;
            Object[] objArr = mutableVector.content;
            if (size < objArr.length) {
                while (size >= 0) {
                    FocusTargetNode focusTargetNode3 = (FocusTargetNode) objArr[size];
                    if (focusOwner.getActiveFocusTargetNode() != focusTargetNode) {
                        return false;
                    }
                    focusTargetNode3.dispatchFocusCallbacks$ui(FocusStateImpl.ActiveParent, FocusStateImpl.Inactive);
                    size--;
                }
            }
        }
        int size2 = mutableVector4.getSize() - 1;
        Object[] objArr2 = mutableVector4.content;
        if (size2 < objArr2.length) {
            while (size2 >= 0) {
                FocusTargetNode focusTargetNode4 = (FocusTargetNode) objArr2[size2];
                if (focusOwner.getActiveFocusTargetNode() != focusTargetNode) {
                    return false;
                }
                focusTargetNode4.dispatchFocusCallbacks$ui(focusTargetNode4 == activeFocusTargetNode ? FocusStateImpl.Active : FocusStateImpl.Inactive, FocusStateImpl.ActiveParent);
                size2--;
            }
        }
        if (focusOwner.getActiveFocusTargetNode() != focusTargetNode) {
            return false;
        }
        focusTargetNode.dispatchFocusCallbacks$ui(focusState, FocusStateImpl.Active);
        if (focusOwner.getActiveFocusTargetNode() != focusTargetNode) {
            return false;
        }
        if (!ComposeUiFlags.isViewFocusFixEnabled || DelegatableNodeKt.requireLayoutNode(focusTargetNode).getInteropView() != null) {
            return true;
        }
        m2810requestOwnerFocusEtdf9zw(focusTargetNode, FocusDirection.m2756boximpl(FocusDirection.INSTANCE.m2767getNextdhqQ8s()), null);
        return true;
    }

    /* JADX INFO: renamed from: requestOwnerFocus-Etdf9zw, reason: not valid java name */
    private static final boolean m2810requestOwnerFocusEtdf9zw(FocusTargetNode focusTargetNode, FocusDirection focusDirection, Rect rect) {
        return DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().mo2781requestOwnerFocus7o62pno(focusDirection, rect);
    }

    /* JADX INFO: renamed from: requestOwnerFocus-Etdf9zw$default, reason: not valid java name */
    public static /* synthetic */ boolean m2811requestOwnerFocusEtdf9zw$default(FocusTargetNode focusTargetNode, FocusDirection focusDirection, Rect rect, int i, Object obj) {
        if ((i & 1) != 0) {
            focusDirection = null;
        }
        if ((i & 2) != 0) {
            rect = null;
        }
        return m2810requestOwnerFocusEtdf9zw(focusTargetNode, focusDirection, rect);
    }

    private static final FocusTargetNode requireActiveChild(FocusTargetNode focusTargetNode) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild != null) {
            return activeChild;
        }
        w01.a("ActiveParent with no focused child");
        return null;
    }
}
