package androidx.compose.ui.focus;

import android.view.KeyEvent;
import androidx.collection.MutableLongSet;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\b\u001aJ!\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020!H\u0016J\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0015H\u0016J/\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b)\u0010*J\u001a\u0010#\u001a\u00020\u00152\b\b\u0002\u0010+\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0015H\u0002J\u0017\u0010,\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b-\u0010*J\u001f\u0010,\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u0015H\u0016¢\u0006\u0004\b/\u00100J7\u00101\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u00102\u001a\u0004\u0018\u00010\u00192\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001504H\u0016¢\u0006\u0004\b5\u00106J%\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00150;H\u0016¢\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\u00020\u00152\u0006\u00108\u001a\u000209H\u0016¢\u0006\u0004\b?\u0010@J\u001e\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020C2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00150;H\u0016J\u0010\u0010D\u001a\u00020\u00152\u0006\u0010B\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020!H\u0016J\b\u0010G\u001a\u00020!H\u0016J\u0010\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020\tH\u0016J\u0010\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020JH\u0016J\b\u0010K\u001a\u00020!H\u0016Jd\u0010L\u001a\u00020!\"\n\b\u0000\u0010M\u0018\u0001*\u00020N*\u00020N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002HM0P2\u0012\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002HM\u0012\u0004\u0012\u00020!042\f\u0010R\u001a\b\u0012\u0004\u0012\u00020!0;2\u0012\u0010S\u001a\u000e\u0012\u0004\u0012\u0002HM\u0012\u0004\u0012\u00020!04H\u0082\b¢\u0006\u0004\bT\u0010UJ0\u0010V\u001a\u0004\u0018\u0001HM\"\n\b\u0000\u0010M\u0018\u0001*\u00020W*\u00020N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002HM0PH\u0082\b¢\u0006\u0004\bX\u0010YJ\n\u0010Z\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010[\u001a\u00020\u0015H\u0016J\b\u0010\\\u001a\u00020\u0015H\u0016J\n\u0010]\u001a\u0004\u0018\u00010\tH\u0002J\u000e\u0010o\u001a\u0004\u0018\u00010p*\u00020NH\u0002J\u0017\u0010q\u001a\u00020\u00152\u0006\u00108\u001a\u000209H\u0002¢\u0006\u0004\br\u0010@R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010^\u001a\u00020_8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u001a\u0010b\u001a\b\u0012\u0004\u0012\u00020d0cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\be\u0010fR*\u0010h\u001a\u0004\u0018\u00010\t2\b\u0010g\u001a\u0004\u0018\u00010\t8V@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u000b\"\u0004\bj\u0010\rR$\u0010k\u001a\u00020\u00152\u0006\u0010g\u001a\u00020\u0015@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010n¨\u0006s"}, d2 = {"Landroidx/compose/ui/focus/FocusOwnerImpl;", "Landroidx/compose/ui/focus/FocusOwner;", "platformFocusOwner", "Landroidx/compose/ui/focus/PlatformFocusOwner;", "owner", "Landroidx/compose/ui/node/Owner;", "<init>", "(Landroidx/compose/ui/focus/PlatformFocusOwner;Landroidx/compose/ui/node/Owner;)V", "rootFocusNode", "Landroidx/compose/ui/focus/FocusTargetNode;", "getRootFocusNode$ui", "()Landroidx/compose/ui/focus/FocusTargetNode;", "setRootFocusNode$ui", "(Landroidx/compose/ui/focus/FocusTargetNode;)V", "focusInvalidationManager", "Landroidx/compose/ui/focus/FocusInvalidationManager;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "requestOwnerFocus", "", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "requestOwnerFocus-7o62pno", "keysCurrentlyDown", "Landroidx/collection/MutableLongSet;", "takeFocus", "takeFocus-aToIllA", "(ILandroidx/compose/ui/geometry/Rect;)Z", "releaseFocus", "", "clearOwnerFocus", "clearFocus", "force", "refreshFocusEvents", "clearFocus-I7lrPNg", "(ZZZI)Z", "resetFocus", "resetFocus-3ESFkO8", "(I)Z", "forced", "moveFocus", "moveFocus-3ESFkO8", "wrapAroundForOneDimensionalFocus", "moveFocus-aToIllA", "(IZ)Z", "focusSearch", "focusedRect", "onFound", "Lkotlin/Function1;", "focusSearch-ULY8qGw", "(ILandroidx/compose/ui/geometry/Rect;Lkotlin/jvm/functions/Function1;)Ljava/lang/Boolean;", "dispatchKeyEvent", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onFocusedItem", "Lkotlin/Function0;", "dispatchKeyEvent-YhN2O0w", "(Landroid/view/KeyEvent;Lkotlin/jvm/functions/Function0;)Z", "dispatchInterceptedSoftKeyboardEvent", "dispatchInterceptedSoftKeyboardEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "dispatchRotaryEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/rotary/RotaryScrollEvent;", "dispatchIndirectPointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "dispatchIndirectPointerCancel", "focusTargetAvailable", "scheduleInvalidation", "node", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "scheduleInvalidationForOwner", "traverseAncestorsIncludingSelf", "T", "Landroidx/compose/ui/node/DelegatableNode;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/node/NodeKind;", "onPreVisit", "onVisit", "onPostVisit", "traverseAncestorsIncludingSelf-QFhIj7k", "(Landroidx/compose/ui/node/DelegatableNode;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "nearestAncestorIncludingSelf", "", "nearestAncestorIncludingSelf-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/lang/Object;", "getFocusRect", "hasFocusableContent", "hasNonInteropFocusableContent", "findFocusTargetNode", "rootState", "Landroidx/compose/ui/focus/FocusState;", "getRootState", "()Landroidx/compose/ui/focus/FocusState;", "listeners", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/focus/FocusListener;", "getListeners", "()Landroidx/collection/MutableObjectList;", "value", "activeFocusTargetNode", "getActiveFocusTargetNode", "setActiveFocusTargetNode", "isFocusCaptured", "()Z", "setFocusCaptured", "(Z)V", "lastLocalKeyInputNode", "Landroidx/compose/ui/Modifier$Node;", "validateKeyEvent", "validateKeyEvent-ZmokQxo", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FocusOwnerImpl implements FocusOwner {
    public static final int $stable = 8;
    private FocusTargetNode activeFocusTargetNode;
    private final FocusInvalidationManager focusInvalidationManager;
    private boolean isFocusCaptured;
    private MutableLongSet keysCurrentlyDown;
    private final Owner owner;
    private final PlatformFocusOwner platformFocusOwner;
    private FocusTargetNode rootFocusNode = new FocusTargetNode(Focusability.INSTANCE.m2824getNeverLCbbffg(), false, null, null, 14, null);
    private final Modifier modifier = new ModifierNodeElement<FocusTargetNode>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$1
        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public FocusTargetNode getNode() {
            return this.this$0.getRootFocusNode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return this.this$0.getRootFocusNode().hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("RootFocusTarget");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(FocusTargetNode node) {
        }
    };
    private final MutableObjectList<FocusListener> listeners = new MutableObjectList<>(1);

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FocusOwnerImpl(PlatformFocusOwner platformFocusOwner, Owner owner) {
        this.platformFocusOwner = platformFocusOwner;
        this.owner = owner;
        this.focusInvalidationManager = new FocusInvalidationManager(this, owner);
    }

    private final boolean clearFocus(boolean forced, boolean refreshFocusEvents) {
        NodeChain nodes;
        if (getActiveFocusTargetNode() == null) {
            return true;
        }
        if (getIsFocusCaptured() && !forced) {
            return false;
        }
        FocusTargetNode activeFocusTargetNode = getActiveFocusTargetNode();
        setActiveFocusTargetNode(null);
        if (refreshFocusEvents && activeFocusTargetNode != null) {
            activeFocusTargetNode.dispatchFocusCallbacks$ui(getIsFocusCaptured() ? FocusStateImpl.Captured : FocusStateImpl.Active, FocusStateImpl.Inactive);
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
                            Modifier.Node nodePop = parent;
                            MutableVector mutableVector = null;
                            while (nodePop != null) {
                                if (nodePop instanceof FocusTargetNode) {
                                    ((FocusTargetNode) nodePop).dispatchFocusCallbacks$ui(FocusStateImpl.ActiveParent, FocusStateImpl.Inactive);
                                } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i = 0;
                                    for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                        if ((delegate.getKindSet() & iM4949constructorimpl) != 0) {
                                            i++;
                                            if (i == 1) {
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
                                    if (i == 1) {
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
        }
        return true;
    }

    public static /* synthetic */ boolean clearFocus$default(FocusOwnerImpl focusOwnerImpl, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return focusOwnerImpl.clearFocus(z, z2);
    }

    private final FocusTargetNode findFocusTargetNode() {
        return FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
    }

    private final Modifier.Node lastLocalKeyInputNode(DelegatableNode delegatableNode) {
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024) | NodeKind.m4949constructorimpl(8192);
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        Modifier.Node node2 = null;
        if ((node.getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
            for (Modifier.Node child = node.getChild(); child != null; child = child.getChild()) {
                if ((child.getKindSet() & iM4949constructorimpl) != 0) {
                    if ((NodeKind.m4949constructorimpl(1024) & child.getKindSet()) != 0) {
                        return node2;
                    }
                    node2 = child;
                }
            }
        }
        return node2;
    }

    /* JADX INFO: renamed from: nearestAncestorIncludingSelf-64DMado, reason: not valid java name */
    private final /* synthetic */ <T> T m2786nearestAncestorIncludingSelf64DMado(DelegatableNode delegatableNode, int i) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Object node = delegatableNode.getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((((Modifier.Node) node).getKindSet() & i) != 0) {
                        Intrinsics.reifiedOperationMarker(3, "T");
                        return (T) node;
                    }
                    node = (T) ((Modifier.Node) node).getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? (T) null : (T) nodes.getTail();
        }
        return null;
    }

    /* JADX INFO: renamed from: traverseAncestorsIncludingSelf-QFhIj7k, reason: not valid java name */
    private final /* synthetic */ <T extends DelegatableNode> void m2787traverseAncestorsIncludingSelfQFhIj7k(DelegatableNode delegatableNode, int i, Function1<? super T, Unit> function1, Function0<Unit> function0, Function1<? super T, Unit> function2) {
        int size;
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        ArrayList arrayList = null;
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & i) != 0) {
                        for (Modifier.Node nodePop = parent; nodePop != null; nodePop = DelegatableNodeKt.pop(null)) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(nodePop);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        if (arrayList != null && (size = arrayList.size() - 1) >= 0) {
            while (true) {
                int i2 = size - 1;
                function1.invoke(arrayList.get(size));
                if (i2 < 0) {
                    break;
                } else {
                    size = i2;
                }
            }
        }
        for (Modifier.Node node = delegatableNode.getNode(); node != null; node = DelegatableNodeKt.pop(null)) {
            Intrinsics.reifiedOperationMarker(3, "T");
            function1.invoke(node);
        }
        function0.invoke();
        for (Modifier.Node node2 = delegatableNode.getNode(); node2 != null; node2 = DelegatableNodeKt.pop(null)) {
            Intrinsics.reifiedOperationMarker(3, "T");
            function2.invoke(node2);
        }
        if (arrayList != null) {
            int size2 = arrayList.size();
            for (int i3 = 0; i3 < size2; i3++) {
                function2.invoke(arrayList.get(i3));
            }
        }
    }

    /* JADX INFO: renamed from: validateKeyEvent-ZmokQxo, reason: not valid java name */
    private final boolean m2788validateKeyEventZmokQxo(KeyEvent keyEvent) {
        long jM4295getKeyZmokQxo = KeyEvent_androidKt.m4295getKeyZmokQxo(keyEvent);
        int iM4296getTypeZmokQxo = KeyEvent_androidKt.m4296getTypeZmokQxo(keyEvent);
        KeyEventType.Companion companion = KeyEventType.INSTANCE;
        if (KeyEventType.m4288equalsimpl0(iM4296getTypeZmokQxo, companion.m4292getKeyDownCS__XNY())) {
            MutableLongSet mutableLongSet = this.keysCurrentlyDown;
            if (mutableLongSet == null) {
                mutableLongSet = new MutableLongSet(3);
                this.keysCurrentlyDown = mutableLongSet;
            }
            mutableLongSet.plusAssign(jM4295getKeyZmokQxo);
        } else if (KeyEventType.m4288equalsimpl0(iM4296getTypeZmokQxo, companion.m4293getKeyUpCS__XNY())) {
            MutableLongSet mutableLongSet2 = this.keysCurrentlyDown;
            if (mutableLongSet2 == null || !mutableLongSet2.contains(jM4295getKeyZmokQxo)) {
                return false;
            }
            MutableLongSet mutableLongSet3 = this.keysCurrentlyDown;
            if (mutableLongSet3 != null) {
                mutableLongSet3.remove(jM4295getKeyZmokQxo);
            }
        }
        return true;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: clearFocus-I7lrPNg */
    public boolean mo2776clearFocusI7lrPNg(boolean force, boolean refreshFocusEvents, boolean clearOwnerFocus, int focusDirection) {
        boolean zClearFocus;
        if (force) {
            zClearFocus = clearFocus(force, refreshFocusEvents);
        } else {
            int i = WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m2806performCustomClearFocusMxy_nc0(this.rootFocusNode, focusDirection).ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                zClearFocus = false;
            } else {
                if (i != 4) {
                    bu8.a();
                    return false;
                }
                zClearFocus = clearFocus(force, refreshFocusEvents);
            }
        }
        if (zClearFocus && clearOwnerFocus) {
            clearOwnerFocus();
        }
        return zClearFocus;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void clearOwnerFocus() {
        this.platformFocusOwner.clearOwnerFocus();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    @Override // androidx.compose.ui.focus.FocusOwner
    public void dispatchIndirectPointerCancel() {
        /*
            Method dump skipped, instruction units count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.dispatchIndirectPointerCancel():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchIndirectPointerEvent(androidx.compose.ui.input.indirect.IndirectPointerEvent r14) {
        /*
            Method dump skipped, instruction units count: 479
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.dispatchIndirectPointerEvent(androidx.compose.ui.input.indirect.IndirectPointerEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v26, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v46 */
    /* JADX WARN: Type inference failed for: r0v47 */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v9, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v19, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchInterceptedSoftKeyboardEvent-ZmokQxo */
    public boolean mo2777dispatchInterceptedSoftKeyboardEventZmokQxo(android.view.KeyEvent r14) {
        /*
            Method dump skipped, instruction units count: 582
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.mo2777dispatchInterceptedSoftKeyboardEventZmokQxo(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Code duplicated, block: B:109:0x0184 A[Catch: all -> 0x033f, TryCatch #0 {all -> 0x033f, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:112:0x018c, B:114:0x019a, B:115:0x019d, B:117:0x01ac, B:120:0x01bd, B:124:0x01c8, B:127:0x01ce, B:128:0x01d3, B:148:0x0215, B:129:0x01d7, B:131:0x01de, B:133:0x01e2, B:135:0x01ec, B:137:0x01f3, B:139:0x01f7, B:141:0x01fd, B:143:0x0206, B:144:0x020a, B:145:0x020d, B:149:0x021a, B:150:0x021f, B:152:0x0225, B:154:0x022b, B:157:0x0236, B:159:0x023e, B:166:0x0255, B:167:0x0257, B:169:0x025e, B:171:0x0262, B:194:0x02ac, B:175:0x026e, B:177:0x0275, B:179:0x0279, B:181:0x0283, B:183:0x028a, B:185:0x028e, B:187:0x0294, B:189:0x029d, B:190:0x02a1, B:191:0x02a4, B:195:0x02b1, B:199:0x02c1, B:201:0x02c8, B:203:0x02cc, B:226:0x0316, B:207:0x02d8, B:209:0x02df, B:211:0x02e3, B:213:0x02ed, B:215:0x02f4, B:217:0x02f8, B:219:0x02fe, B:221:0x0307, B:222:0x030b, B:223:0x030e, B:228:0x031d, B:230:0x0324, B:235:0x0337, B:236:0x0339, B:18:0x003e, B:20:0x004c, B:21:0x004f, B:23:0x0059, B:26:0x006a, B:30:0x0075, B:61:0x00d7, B:63:0x00db, B:33:0x007b, B:35:0x0082, B:37:0x0086, B:39:0x0090, B:41:0x0097, B:43:0x009b, B:45:0x00a1, B:47:0x00aa, B:48:0x00ae, B:49:0x00b1, B:52:0x00b9, B:53:0x00be, B:54:0x00c3, B:56:0x00c9, B:58:0x00cf, B:64:0x00e1, B:66:0x00f1, B:67:0x00f4, B:69:0x0102, B:72:0x0113, B:76:0x011e, B:107:0x0180, B:109:0x0184, B:79:0x0124, B:81:0x012b, B:83:0x012f, B:85:0x0139, B:87:0x0140, B:89:0x0144, B:91:0x014a, B:93:0x0153, B:94:0x0157, B:95:0x015a, B:98:0x0162, B:99:0x0167, B:100:0x016c, B:102:0x0172, B:104:0x0178), top: B:242:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:110:0x0189  */
    /* JADX WARN: Code duplicated, block: B:319:0x017f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:320:0x0122 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:328:0x0167 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x00e1 A[Catch: all -> 0x033f, TryCatch #0 {all -> 0x033f, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:112:0x018c, B:114:0x019a, B:115:0x019d, B:117:0x01ac, B:120:0x01bd, B:124:0x01c8, B:127:0x01ce, B:128:0x01d3, B:148:0x0215, B:129:0x01d7, B:131:0x01de, B:133:0x01e2, B:135:0x01ec, B:137:0x01f3, B:139:0x01f7, B:141:0x01fd, B:143:0x0206, B:144:0x020a, B:145:0x020d, B:149:0x021a, B:150:0x021f, B:152:0x0225, B:154:0x022b, B:157:0x0236, B:159:0x023e, B:166:0x0255, B:167:0x0257, B:169:0x025e, B:171:0x0262, B:194:0x02ac, B:175:0x026e, B:177:0x0275, B:179:0x0279, B:181:0x0283, B:183:0x028a, B:185:0x028e, B:187:0x0294, B:189:0x029d, B:190:0x02a1, B:191:0x02a4, B:195:0x02b1, B:199:0x02c1, B:201:0x02c8, B:203:0x02cc, B:226:0x0316, B:207:0x02d8, B:209:0x02df, B:211:0x02e3, B:213:0x02ed, B:215:0x02f4, B:217:0x02f8, B:219:0x02fe, B:221:0x0307, B:222:0x030b, B:223:0x030e, B:228:0x031d, B:230:0x0324, B:235:0x0337, B:236:0x0339, B:18:0x003e, B:20:0x004c, B:21:0x004f, B:23:0x0059, B:26:0x006a, B:30:0x0075, B:61:0x00d7, B:63:0x00db, B:33:0x007b, B:35:0x0082, B:37:0x0086, B:39:0x0090, B:41:0x0097, B:43:0x009b, B:45:0x00a1, B:47:0x00aa, B:48:0x00ae, B:49:0x00b1, B:52:0x00b9, B:53:0x00be, B:54:0x00c3, B:56:0x00c9, B:58:0x00cf, B:64:0x00e1, B:66:0x00f1, B:67:0x00f4, B:69:0x0102, B:72:0x0113, B:76:0x011e, B:107:0x0180, B:109:0x0184, B:79:0x0124, B:81:0x012b, B:83:0x012f, B:85:0x0139, B:87:0x0140, B:89:0x0144, B:91:0x014a, B:93:0x0153, B:94:0x0157, B:95:0x015a, B:98:0x0162, B:99:0x0167, B:100:0x016c, B:102:0x0172, B:104:0x0178), top: B:242:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x00f1 A[Catch: all -> 0x033f, TryCatch #0 {all -> 0x033f, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:112:0x018c, B:114:0x019a, B:115:0x019d, B:117:0x01ac, B:120:0x01bd, B:124:0x01c8, B:127:0x01ce, B:128:0x01d3, B:148:0x0215, B:129:0x01d7, B:131:0x01de, B:133:0x01e2, B:135:0x01ec, B:137:0x01f3, B:139:0x01f7, B:141:0x01fd, B:143:0x0206, B:144:0x020a, B:145:0x020d, B:149:0x021a, B:150:0x021f, B:152:0x0225, B:154:0x022b, B:157:0x0236, B:159:0x023e, B:166:0x0255, B:167:0x0257, B:169:0x025e, B:171:0x0262, B:194:0x02ac, B:175:0x026e, B:177:0x0275, B:179:0x0279, B:181:0x0283, B:183:0x028a, B:185:0x028e, B:187:0x0294, B:189:0x029d, B:190:0x02a1, B:191:0x02a4, B:195:0x02b1, B:199:0x02c1, B:201:0x02c8, B:203:0x02cc, B:226:0x0316, B:207:0x02d8, B:209:0x02df, B:211:0x02e3, B:213:0x02ed, B:215:0x02f4, B:217:0x02f8, B:219:0x02fe, B:221:0x0307, B:222:0x030b, B:223:0x030e, B:228:0x031d, B:230:0x0324, B:235:0x0337, B:236:0x0339, B:18:0x003e, B:20:0x004c, B:21:0x004f, B:23:0x0059, B:26:0x006a, B:30:0x0075, B:61:0x00d7, B:63:0x00db, B:33:0x007b, B:35:0x0082, B:37:0x0086, B:39:0x0090, B:41:0x0097, B:43:0x009b, B:45:0x00a1, B:47:0x00aa, B:48:0x00ae, B:49:0x00b1, B:52:0x00b9, B:53:0x00be, B:54:0x00c3, B:56:0x00c9, B:58:0x00cf, B:64:0x00e1, B:66:0x00f1, B:67:0x00f4, B:69:0x0102, B:72:0x0113, B:76:0x011e, B:107:0x0180, B:109:0x0184, B:79:0x0124, B:81:0x012b, B:83:0x012f, B:85:0x0139, B:87:0x0140, B:89:0x0144, B:91:0x014a, B:93:0x0153, B:94:0x0157, B:95:0x015a, B:98:0x0162, B:99:0x0167, B:100:0x016c, B:102:0x0172, B:104:0x0178), top: B:242:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x0102 A[Catch: all -> 0x033f, TryCatch #0 {all -> 0x033f, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:112:0x018c, B:114:0x019a, B:115:0x019d, B:117:0x01ac, B:120:0x01bd, B:124:0x01c8, B:127:0x01ce, B:128:0x01d3, B:148:0x0215, B:129:0x01d7, B:131:0x01de, B:133:0x01e2, B:135:0x01ec, B:137:0x01f3, B:139:0x01f7, B:141:0x01fd, B:143:0x0206, B:144:0x020a, B:145:0x020d, B:149:0x021a, B:150:0x021f, B:152:0x0225, B:154:0x022b, B:157:0x0236, B:159:0x023e, B:166:0x0255, B:167:0x0257, B:169:0x025e, B:171:0x0262, B:194:0x02ac, B:175:0x026e, B:177:0x0275, B:179:0x0279, B:181:0x0283, B:183:0x028a, B:185:0x028e, B:187:0x0294, B:189:0x029d, B:190:0x02a1, B:191:0x02a4, B:195:0x02b1, B:199:0x02c1, B:201:0x02c8, B:203:0x02cc, B:226:0x0316, B:207:0x02d8, B:209:0x02df, B:211:0x02e3, B:213:0x02ed, B:215:0x02f4, B:217:0x02f8, B:219:0x02fe, B:221:0x0307, B:222:0x030b, B:223:0x030e, B:228:0x031d, B:230:0x0324, B:235:0x0337, B:236:0x0339, B:18:0x003e, B:20:0x004c, B:21:0x004f, B:23:0x0059, B:26:0x006a, B:30:0x0075, B:61:0x00d7, B:63:0x00db, B:33:0x007b, B:35:0x0082, B:37:0x0086, B:39:0x0090, B:41:0x0097, B:43:0x009b, B:45:0x00a1, B:47:0x00aa, B:48:0x00ae, B:49:0x00b1, B:52:0x00b9, B:53:0x00be, B:54:0x00c3, B:56:0x00c9, B:58:0x00cf, B:64:0x00e1, B:66:0x00f1, B:67:0x00f4, B:69:0x0102, B:72:0x0113, B:76:0x011e, B:107:0x0180, B:109:0x0184, B:79:0x0124, B:81:0x012b, B:83:0x012f, B:85:0x0139, B:87:0x0140, B:89:0x0144, B:91:0x014a, B:93:0x0153, B:94:0x0157, B:95:0x015a, B:98:0x0162, B:99:0x0167, B:100:0x016c, B:102:0x0172, B:104:0x0178), top: B:242:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0111 A[ADDED_TO_REGION, LOOP:15: B:71:0x0111->B:99:0x0167, LOOP_START, PHI: r9
      0x0111: PHI (r9v11 androidx.compose.ui.Modifier$Node) = (r9v4 androidx.compose.ui.Modifier$Node), (r9v12 androidx.compose.ui.Modifier$Node) binds: [B:70:0x010f, B:99:0x0167] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:72:0x0113 A[Catch: all -> 0x033f, TryCatch #0 {all -> 0x033f, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:112:0x018c, B:114:0x019a, B:115:0x019d, B:117:0x01ac, B:120:0x01bd, B:124:0x01c8, B:127:0x01ce, B:128:0x01d3, B:148:0x0215, B:129:0x01d7, B:131:0x01de, B:133:0x01e2, B:135:0x01ec, B:137:0x01f3, B:139:0x01f7, B:141:0x01fd, B:143:0x0206, B:144:0x020a, B:145:0x020d, B:149:0x021a, B:150:0x021f, B:152:0x0225, B:154:0x022b, B:157:0x0236, B:159:0x023e, B:166:0x0255, B:167:0x0257, B:169:0x025e, B:171:0x0262, B:194:0x02ac, B:175:0x026e, B:177:0x0275, B:179:0x0279, B:181:0x0283, B:183:0x028a, B:185:0x028e, B:187:0x0294, B:189:0x029d, B:190:0x02a1, B:191:0x02a4, B:195:0x02b1, B:199:0x02c1, B:201:0x02c8, B:203:0x02cc, B:226:0x0316, B:207:0x02d8, B:209:0x02df, B:211:0x02e3, B:213:0x02ed, B:215:0x02f4, B:217:0x02f8, B:219:0x02fe, B:221:0x0307, B:222:0x030b, B:223:0x030e, B:228:0x031d, B:230:0x0324, B:235:0x0337, B:236:0x0339, B:18:0x003e, B:20:0x004c, B:21:0x004f, B:23:0x0059, B:26:0x006a, B:30:0x0075, B:61:0x00d7, B:63:0x00db, B:33:0x007b, B:35:0x0082, B:37:0x0086, B:39:0x0090, B:41:0x0097, B:43:0x009b, B:45:0x00a1, B:47:0x00aa, B:48:0x00ae, B:49:0x00b1, B:52:0x00b9, B:53:0x00be, B:54:0x00c3, B:56:0x00c9, B:58:0x00cf, B:64:0x00e1, B:66:0x00f1, B:67:0x00f4, B:69:0x0102, B:72:0x0113, B:76:0x011e, B:107:0x0180, B:109:0x0184, B:79:0x0124, B:81:0x012b, B:83:0x012f, B:85:0x0139, B:87:0x0140, B:89:0x0144, B:91:0x014a, B:93:0x0153, B:94:0x0157, B:95:0x015a, B:98:0x0162, B:99:0x0167, B:100:0x016c, B:102:0x0172, B:104:0x0178), top: B:242:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x011a  */
    /* JADX WARN: Code duplicated, block: B:76:0x011e A[Catch: all -> 0x033f, TryCatch #0 {all -> 0x033f, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:112:0x018c, B:114:0x019a, B:115:0x019d, B:117:0x01ac, B:120:0x01bd, B:124:0x01c8, B:127:0x01ce, B:128:0x01d3, B:148:0x0215, B:129:0x01d7, B:131:0x01de, B:133:0x01e2, B:135:0x01ec, B:137:0x01f3, B:139:0x01f7, B:141:0x01fd, B:143:0x0206, B:144:0x020a, B:145:0x020d, B:149:0x021a, B:150:0x021f, B:152:0x0225, B:154:0x022b, B:157:0x0236, B:159:0x023e, B:166:0x0255, B:167:0x0257, B:169:0x025e, B:171:0x0262, B:194:0x02ac, B:175:0x026e, B:177:0x0275, B:179:0x0279, B:181:0x0283, B:183:0x028a, B:185:0x028e, B:187:0x0294, B:189:0x029d, B:190:0x02a1, B:191:0x02a4, B:195:0x02b1, B:199:0x02c1, B:201:0x02c8, B:203:0x02cc, B:226:0x0316, B:207:0x02d8, B:209:0x02df, B:211:0x02e3, B:213:0x02ed, B:215:0x02f4, B:217:0x02f8, B:219:0x02fe, B:221:0x0307, B:222:0x030b, B:223:0x030e, B:228:0x031d, B:230:0x0324, B:235:0x0337, B:236:0x0339, B:18:0x003e, B:20:0x004c, B:21:0x004f, B:23:0x0059, B:26:0x006a, B:30:0x0075, B:61:0x00d7, B:63:0x00db, B:33:0x007b, B:35:0x0082, B:37:0x0086, B:39:0x0090, B:41:0x0097, B:43:0x009b, B:45:0x00a1, B:47:0x00aa, B:48:0x00ae, B:49:0x00b1, B:52:0x00b9, B:53:0x00be, B:54:0x00c3, B:56:0x00c9, B:58:0x00cf, B:64:0x00e1, B:66:0x00f1, B:67:0x00f4, B:69:0x0102, B:72:0x0113, B:76:0x011e, B:107:0x0180, B:109:0x0184, B:79:0x0124, B:81:0x012b, B:83:0x012f, B:85:0x0139, B:87:0x0140, B:89:0x0144, B:91:0x014a, B:93:0x0153, B:94:0x0157, B:95:0x015a, B:98:0x0162, B:99:0x0167, B:100:0x016c, B:102:0x0172, B:104:0x0178), top: B:242:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x0124 A[Catch: all -> 0x033f, TryCatch #0 {all -> 0x033f, blocks: (B:3:0x0009, B:5:0x0012, B:8:0x001d, B:12:0x0027, B:15:0x0036, B:112:0x018c, B:114:0x019a, B:115:0x019d, B:117:0x01ac, B:120:0x01bd, B:124:0x01c8, B:127:0x01ce, B:128:0x01d3, B:148:0x0215, B:129:0x01d7, B:131:0x01de, B:133:0x01e2, B:135:0x01ec, B:137:0x01f3, B:139:0x01f7, B:141:0x01fd, B:143:0x0206, B:144:0x020a, B:145:0x020d, B:149:0x021a, B:150:0x021f, B:152:0x0225, B:154:0x022b, B:157:0x0236, B:159:0x023e, B:166:0x0255, B:167:0x0257, B:169:0x025e, B:171:0x0262, B:194:0x02ac, B:175:0x026e, B:177:0x0275, B:179:0x0279, B:181:0x0283, B:183:0x028a, B:185:0x028e, B:187:0x0294, B:189:0x029d, B:190:0x02a1, B:191:0x02a4, B:195:0x02b1, B:199:0x02c1, B:201:0x02c8, B:203:0x02cc, B:226:0x0316, B:207:0x02d8, B:209:0x02df, B:211:0x02e3, B:213:0x02ed, B:215:0x02f4, B:217:0x02f8, B:219:0x02fe, B:221:0x0307, B:222:0x030b, B:223:0x030e, B:228:0x031d, B:230:0x0324, B:235:0x0337, B:236:0x0339, B:18:0x003e, B:20:0x004c, B:21:0x004f, B:23:0x0059, B:26:0x006a, B:30:0x0075, B:61:0x00d7, B:63:0x00db, B:33:0x007b, B:35:0x0082, B:37:0x0086, B:39:0x0090, B:41:0x0097, B:43:0x009b, B:45:0x00a1, B:47:0x00aa, B:48:0x00ae, B:49:0x00b1, B:52:0x00b9, B:53:0x00be, B:54:0x00c3, B:56:0x00c9, B:58:0x00cf, B:64:0x00e1, B:66:0x00f1, B:67:0x00f4, B:69:0x0102, B:72:0x0113, B:76:0x011e, B:107:0x0180, B:109:0x0184, B:79:0x0124, B:81:0x012b, B:83:0x012f, B:85:0x0139, B:87:0x0140, B:89:0x0144, B:91:0x014a, B:93:0x0153, B:94:0x0157, B:95:0x015a, B:98:0x0162, B:99:0x0167, B:100:0x016c, B:102:0x0172, B:104:0x0178), top: B:242:0x0009 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchKeyEvent-YhN2O0w */
    public boolean mo2778dispatchKeyEventYhN2O0w(android.view.KeyEvent r17, kotlin.jvm.functions.Function0<java.lang.Boolean> r18) {
        /*
            Method dump skipped, instruction units count: 836
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.mo2778dispatchKeyEventYhN2O0w(android.view.KeyEvent, kotlin.jvm.functions.Function0):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v42 */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r15v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v17 */
    /* JADX WARN: Type inference failed for: r15v18 */
    /* JADX WARN: Type inference failed for: r15v19 */
    /* JADX WARN: Type inference failed for: r15v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r15v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v18, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchRotaryEvent(androidx.compose.ui.input.rotary.RotaryScrollEvent r14, kotlin.jvm.functions.Function0<java.lang.Boolean> r15) {
        /*
            Method dump skipped, instruction units count: 593
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.dispatchRotaryEvent(androidx.compose.ui.input.rotary.RotaryScrollEvent, kotlin.jvm.functions.Function0):boolean");
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: focusSearch-ULY8qGw */
    public Boolean mo2779focusSearchULY8qGw(int focusDirection, Rect focusedRect, final Function1<? super FocusTargetNode, Boolean> onFound) {
        final FocusTargetNode focusTargetNodeFindFocusTargetNode = findFocusTargetNode();
        if (focusTargetNodeFindFocusTargetNode != null) {
            FocusRequester focusRequesterM2813customFocusSearchOMvw8 = FocusTraversalKt.m2813customFocusSearchOMvw8(focusTargetNodeFindFocusTargetNode, focusDirection, this.owner.getLayoutDirection());
            FocusRequester.Companion companion = FocusRequester.INSTANCE;
            if (Intrinsics.areEqual(focusRequesterM2813customFocusSearchOMvw8, companion.getCancel())) {
                return null;
            }
            if (Intrinsics.areEqual(focusRequesterM2813customFocusSearchOMvw8, companion.getRedirect$ui())) {
                FocusTargetNode focusTargetNodeFindFocusTargetNode2 = findFocusTargetNode();
                if (focusTargetNodeFindFocusTargetNode2 != null) {
                    return (Boolean) onFound.invoke(focusTargetNodeFindFocusTargetNode2);
                }
                return null;
            }
            if (!Intrinsics.areEqual(focusRequesterM2813customFocusSearchOMvw8, companion.getDefault())) {
                if (!ComposeUiFlags.isRequestFocusOnNonFocusableFocusTargetEnabled) {
                    return Boolean.valueOf(focusRequesterM2813customFocusSearchOMvw8.findFocusTargetNode$ui(onFound));
                }
                if (focusRequesterM2813customFocusSearchOMvw8 == companion.getDefault()) {
                    k2d.a("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
                    return null;
                }
                if (focusRequesterM2813customFocusSearchOMvw8 == companion.getCancel()) {
                    k2d.a("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
                    return null;
                }
                boolean z = false;
                if (focusRequesterM2813customFocusSearchOMvw8.getFocusRequesterNodes$ui().getSize() == 0) {
                    System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
                } else {
                    MutableVector<FocusRequesterModifierNode> focusRequesterNodes$ui = focusRequesterM2813customFocusSearchOMvw8.getFocusRequesterNodes$ui();
                    FocusRequesterModifierNode[] focusRequesterModifierNodeArr = focusRequesterNodes$ui.content;
                    int size = focusRequesterNodes$ui.getSize();
                    boolean z2 = false;
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
                                                    z2 = true;
                                                    break;
                                                }
                                            } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                                int i2 = 0;
                                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                                    if ((delegate.getKindSet() & iM4949constructorimpl) != 0) {
                                                        i2++;
                                                        if (i2 == 1) {
                                                            nodePop = delegate;
                                                        } else {
                                                            if (mutableVector2 == null) {
                                                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                            }
                                                            if (nodePop != null) {
                                                                mutableVector2.add(nodePop);
                                                                nodePop = null;
                                                            }
                                                            mutableVector2.add(delegate);
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
                    z = z2;
                }
                return Boolean.valueOf(z);
            }
        } else {
            focusTargetNodeFindFocusTargetNode = null;
        }
        return FocusTraversalKt.m2814focusSearch0X8WOeE(this.rootFocusNode, focusDirection, this.owner.getLayoutDirection(), focusedRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$focusSearch$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                boolean zBooleanValue;
                if (Intrinsics.areEqual(focusTargetNode, focusTargetNodeFindFocusTargetNode)) {
                    zBooleanValue = false;
                } else {
                    if (Intrinsics.areEqual(focusTargetNode, this.getRootFocusNode())) {
                        k2d.a("Focus search landed at the root.");
                        return null;
                    }
                    zBooleanValue = ((Boolean) onFound.invoke(focusTargetNode)).booleanValue();
                }
                return Boolean.valueOf(zBooleanValue);
            }
        });
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void focusTargetAvailable() {
        this.platformFocusOwner.focusTargetAvailable();
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public FocusTargetNode getActiveFocusTargetNode() {
        FocusTargetNode focusTargetNode = this.activeFocusTargetNode;
        if (focusTargetNode == null || !focusTargetNode.getIsAttached()) {
            return null;
        }
        return this.activeFocusTargetNode;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Rect getFocusRect() {
        FocusTargetNode focusTargetNodeFindFocusTargetNode = findFocusTargetNode();
        if (focusTargetNodeFindFocusTargetNode != null) {
            return FocusTraversalKt.focusRect(focusTargetNodeFindFocusTargetNode);
        }
        return null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public MutableObjectList<FocusListener> getListeners() {
        return this.listeners;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Modifier getModifier() {
        return this.modifier;
    }

    /* JADX INFO: renamed from: getRootFocusNode$ui, reason: from getter */
    public final FocusTargetNode getRootFocusNode() {
        return this.rootFocusNode;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public FocusState getRootState() {
        return this.rootFocusNode.getFocusState();
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean hasFocusableContent() {
        if (!this.rootFocusNode.getIsAttached()) {
            return false;
        }
        FocusTargetNode focusTargetNode = this.rootFocusNode;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
        if (!focusTargetNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = focusTargetNode.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusTargetNode.getNode(), false);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node node = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node.getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                for (Modifier.Node child2 = node; child2 != null && child2.getIsAttached(); child2 = child2.getChild()) {
                    if ((child2.getKindSet() & iM4949constructorimpl) != 0) {
                        Modifier.Node nodePop = child2;
                        MutableVector mutableVector2 = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
                                if (focusTargetNode2.getIsAttached() && focusTargetNode2.fetchFocusProperties$ui().getCanFocus()) {
                                    return true;
                                }
                            } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i = 0;
                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & iM4949constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector2 == null) {
                                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector2.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector2.add(delegate);
                                        }
                                    }
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                        }
                    }
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, node, false);
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean hasNonInteropFocusableContent() {
        if (!this.rootFocusNode.getIsAttached()) {
            return false;
        }
        FocusTargetNode focusTargetNode = this.rootFocusNode;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
        if (!focusTargetNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = focusTargetNode.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusTargetNode.getNode(), false);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node node = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node.getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                for (Modifier.Node child2 = node; child2 != null && child2.getIsAttached(); child2 = child2.getChild()) {
                    if ((child2.getKindSet() & iM4949constructorimpl) != 0) {
                        Modifier.Node nodePop = child2;
                        MutableVector mutableVector2 = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
                                if (focusTargetNode2.getIsAttached()) {
                                    FocusProperties focusPropertiesFetchFocusProperties$ui = focusTargetNode2.fetchFocusProperties$ui();
                                    if (focusTargetNode2.getIsAttached() && !focusTargetNode2.getIsInteropViewHost() && focusPropertiesFetchFocusProperties$ui.getCanFocus()) {
                                        return true;
                                    }
                                }
                            } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i = 0;
                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & iM4949constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector2 == null) {
                                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector2.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector2.add(delegate);
                                        }
                                    }
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                        }
                    }
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, node, false);
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: isFocusCaptured, reason: from getter */
    public boolean getIsFocusCaptured() {
        return this.isFocusCaptured;
    }

    @Override // androidx.compose.ui.focus.FocusManager
    /* JADX INFO: renamed from: moveFocus-3ESFkO8 */
    public boolean mo2774moveFocus3ESFkO8(int focusDirection) {
        return mo2780moveFocusaToIllA(focusDirection, true);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: moveFocus-aToIllA */
    public boolean mo2780moveFocusaToIllA(final int focusDirection, boolean wrapAroundForOneDimensionalFocus) {
        FocusTargetNode activeFocusTargetNode;
        if ((ComposeUiFlags.isViewFocusFixEnabled || (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled && (activeFocusTargetNode = getActiveFocusTargetNode()) != null && activeFocusTargetNode.getIsInteropViewHost())) && this.platformFocusOwner.mo2830moveFocusInChildren3ESFkO8(focusDirection)) {
            return true;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = Boolean.FALSE;
        FocusTargetNode activeFocusTargetNode2 = getActiveFocusTargetNode();
        Boolean boolMo2779focusSearchULY8qGw = mo2779focusSearchULY8qGw(focusDirection, this.platformFocusOwner.getEmbeddedViewFocusRect(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$moveFocus$focusSearchSuccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                objectRef.element = Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(focusDirection));
                return (Boolean) objectRef.element;
            }
        });
        if (Intrinsics.areEqual(boolMo2779focusSearchULY8qGw, Boolean.TRUE) && activeFocusTargetNode2 != getActiveFocusTargetNode()) {
            return true;
        }
        if (boolMo2779focusSearchULY8qGw != null && objectRef.element != null) {
            if (boolMo2779focusSearchULY8qGw.booleanValue() && ((Boolean) objectRef.element).booleanValue()) {
                return true;
            }
            if (FocusOwnerImplKt.m2789is1dFocusSearch3ESFkO8(focusDirection) && wrapAroundForOneDimensionalFocus) {
                return mo2776clearFocusI7lrPNg(false, true, false, focusDirection) && mo2783takeFocusaToIllA(focusDirection, null);
            }
            if (!ComposeUiFlags.isViewFocusFixEnabled && !ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
                return this.platformFocusOwner.mo2830moveFocusInChildren3ESFkO8(focusDirection);
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void releaseFocus() {
        FocusTransactionsKt.clearFocus(this.rootFocusNode, true, true);
        if (!ComposeUiFlags.isOptimizedFocusEventDispatchEnabled || getActiveFocusTargetNode() == null) {
            return;
        }
        FocusTargetNode activeFocusTargetNode = getActiveFocusTargetNode();
        setActiveFocusTargetNode(null);
        if (activeFocusTargetNode != null) {
            activeFocusTargetNode.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: requestOwnerFocus-7o62pno */
    public boolean mo2781requestOwnerFocus7o62pno(FocusDirection focusDirection, Rect previouslyFocusedRect) {
        return this.platformFocusOwner.mo2831requestOwnerFocus7o62pno(focusDirection, previouslyFocusedRect);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: resetFocus-3ESFkO8 */
    public boolean mo2782resetFocus3ESFkO8(final int focusDirection) {
        if (!mo2776clearFocusI7lrPNg(false, true, false, focusDirection)) {
            return false;
        }
        Boolean boolMo2779focusSearchULY8qGw = mo2779focusSearchULY8qGw(focusDirection, null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$resetFocus$successfulReset$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(focusDirection));
            }
        });
        boolean zBooleanValue = boolMo2779focusSearchULY8qGw != null ? boolMo2779focusSearchULY8qGw.booleanValue() : false;
        if (!zBooleanValue) {
            clearOwnerFocus();
        }
        return zBooleanValue;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusTargetNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidationForOwner() {
        this.focusInvalidationManager.scheduleInvalidation();
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setActiveFocusTargetNode(FocusTargetNode focusTargetNode) {
        FocusTargetNode focusTargetNode2 = this.activeFocusTargetNode;
        this.activeFocusTargetNode = focusTargetNode;
        if (focusTargetNode == null || focusTargetNode2 != focusTargetNode) {
            setFocusCaptured(false);
        }
        if (ComposeUiFlags.isSemanticAutofillEnabled) {
            MutableObjectList<FocusListener> listeners = getListeners();
            Object[] objArr = ((ObjectList) listeners).content;
            int i = ((ObjectList) listeners)._size;
            for (int i2 = 0; i2 < i; i2++) {
                ((FocusListener) objArr[i2]).onFocusChanged(focusTargetNode2, focusTargetNode);
            }
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setFocusCaptured(boolean z) {
        if (!((z && getActiveFocusTargetNode() == null) ? false : true)) {
            InlineClassHelperKt.throwIllegalArgumentException("Cannot capture focus when the active focus target node is unset");
        }
        this.isFocusCaptured = z;
    }

    public final void setRootFocusNode$ui(FocusTargetNode focusTargetNode) {
        this.rootFocusNode = focusTargetNode;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: takeFocus-aToIllA */
    public boolean mo2783takeFocusaToIllA(final int focusDirection, Rect previouslyFocusedRect) {
        Boolean boolMo2779focusSearchULY8qGw = mo2779focusSearchULY8qGw(focusDirection, previouslyFocusedRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$takeFocus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                return Boolean.valueOf(focusTargetNode.mo2796requestFocus3ESFkO8(focusDirection));
            }
        });
        if (boolMo2779focusSearchULY8qGw != null) {
            return boolMo2779focusSearchULY8qGw.booleanValue();
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusEventModifierNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusManager
    public void clearFocus(boolean force) {
        mo2776clearFocusI7lrPNg(force, true, true, FocusDirection.INSTANCE.m2765getExitdhqQ8s());
    }
}
