package androidx.compose.ui.focus;

import android.os.Trace;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001VBo\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012:\b\u0002\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\f\u0012\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010!\u001a\u00020\nH\u0017J\u0017\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b'\u0010%J\b\u00106\u001a\u00020\u0012H\u0016J\b\u00107\u001a\u00020\u0012H\u0016J\b\u00108\u001a\u00020\u0012H\u0016J\u0010\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u00020;H\u0016J\r\u0010<\u001a\u00020=H\u0000¢\u0006\u0002\b>J\u0019\u0010?\u001a\u00020@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010;H\u0000¢\u0006\u0002\bBJK\u0010C\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020\u00120\u00142\u001d\u0010F\u001a\u0019\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\u00120\f¢\u0006\u0002\bHH\u0082\b¢\u0006\u0004\bI\u0010JJ,\u0010K\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020\u00120\u0014H\u0080\b¢\u0006\u0004\bL\u0010MJ,\u0010N\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020\u00120\u0014H\u0080\b¢\u0006\u0004\bO\u0010MJ\r\u0010P\u001a\u00020\u0012H\u0000¢\u0006\u0002\bQJ\u001d\u0010R\u001a\u00020\u00122\u0006\u0010S\u001a\u00020\r2\u0006\u0010T\u001a\u00020\rH\u0000¢\u0006\u0002\bUR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0017R@\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R&\u0010\u0007\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010.\u001a\u00020/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010*\"\u0004\b1\u0010,R\u0013\u00102\u001a\u0004\u0018\u0001038F¢\u0006\u0006\u001a\u0004\b4\u00105¨\u0006W"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "focusability", "Landroidx/compose/ui/focus/Focusability;", "isInteropViewHost", "", "onFocusChange", "Lkotlin/Function2;", "Landroidx/compose/ui/focus/FocusState;", "Lkotlin/ParameterName;", "name", "previous", "current", "", "onDispatchEventsCompleted", "Lkotlin/Function1;", "<init>", "(IZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "()Z", "isProcessingCustomExit", "isProcessingCustomEnter", "committedFocusState", "Landroidx/compose/ui/focus/FocusStateImpl;", "shouldAutoInvalidate", "getShouldAutoInvalidate", "focusState", "getFocusState", "()Landroidx/compose/ui/focus/FocusStateImpl;", "requestFocus", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "requestFocus-3ESFkO8", "(I)Z", "assignFocus", "assignFocus-3ESFkO8", "value", "getFocusability-LCbbffg", "()I", "setFocusability-josRg5g", "(I)V", "I", "previouslyFocusedChildHash", "", "getPreviouslyFocusedChildHash", "setPreviouslyFocusedChildHash", "beyondBoundsLayoutParent", "Landroidx/compose/ui/layout/BeyondBoundsLayout;", "getBeyondBoundsLayoutParent", "()Landroidx/compose/ui/layout/BeyondBoundsLayout;", "onObservedReadsChanged", "onReset", "onDetach", "onPlaced", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "fetchFocusProperties", "Landroidx/compose/ui/focus/FocusProperties;", "fetchFocusProperties$ui", "fetchFocusRect", "Landroidx/compose/ui/geometry/Rect;", "relativeCoordinates", "fetchFocusRect$ui", "fetchCustomEnterOrExit", "block", "Landroidx/compose/ui/focus/FocusRequester;", "enterOrExit", "Landroidx/compose/ui/focus/FocusEnterExitScope;", "Lkotlin/ExtensionFunctionType;", "fetchCustomEnterOrExit-ULY8qGw", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "fetchCustomEnter", "fetchCustomEnter-aToIllA$ui", "(ILkotlin/jvm/functions/Function1;)V", "fetchCustomExit", "fetchCustomExit-aToIllA$ui", "invalidateFocus", "invalidateFocus$ui", "dispatchFocusCallbacks", "previousState", "newState", "dispatchFocusCallbacks$ui", "FocusTargetElement", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FocusTargetNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, LayoutAwareModifierNode, FocusTargetModifierNode, ObserverModifierNode, ModifierLocalModifierNode {
    public static final int $stable = 8;
    private FocusStateImpl committedFocusState;
    private int focusability;
    private final boolean isInteropViewHost;
    private boolean isProcessingCustomEnter;
    private boolean isProcessingCustomExit;
    private final Function1<FocusTargetNode, Unit> onDispatchEventsCompleted;
    private final Function2<FocusState, FocusState, Unit> onFocusChange;
    private int previouslyFocusedChildHash;
    private final boolean shouldAutoInvalidate;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public /* synthetic */ FocusTargetNode(int i, boolean z, Function2 function2, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? Focusability.INSTANCE.m2823getAlwaysLCbbffg() : i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? null : function2, (i2 & 8) != 0 ? null : function1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: assignFocus-3ESFkO8, reason: not valid java name */
    public final boolean m2801assignFocus3ESFkO8(int focusDirection) {
        int i = WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m2809performCustomRequestFocusMxy_nc0(this, focusDirection).ordinal()];
        if (i == 1) {
            return FocusTransactionsKt.performRequestFocus(this);
        }
        if (i == 2) {
            return true;
        }
        if (i == 3 || i == 4) {
            return false;
        }
        bu8.a();
        return false;
    }

    /* JADX INFO: renamed from: fetchCustomEnterOrExit-ULY8qGw, reason: not valid java name */
    private final void m2802fetchCustomEnterOrExitULY8qGw(int focusDirection, Function1<? super FocusRequester, Unit> block, Function2<? super FocusProperties, ? super FocusEnterExitScope, Unit> enterOrExit) {
        FocusProperties focusPropertiesFetchFocusProperties$ui = fetchFocusProperties$ui();
        CancelIndicatingFocusBoundaryScope cancelIndicatingFocusBoundaryScope = new CancelIndicatingFocusBoundaryScope(focusDirection, null);
        FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
        FocusTargetNode activeFocusTargetNode = focusOwner.getActiveFocusTargetNode();
        enterOrExit.invoke(focusPropertiesFetchFocusProperties$ui, cancelIndicatingFocusBoundaryScope);
        FocusTargetNode activeFocusTargetNode2 = focusOwner.getActiveFocusTargetNode();
        if (cancelIndicatingFocusBoundaryScope.getIsCanceled()) {
            block.invoke(FocusRequester.INSTANCE.getCancel());
        } else {
            if (activeFocusTargetNode == activeFocusTargetNode2 || activeFocusTargetNode2 == null) {
                return;
            }
            block.invoke(FocusRequester.INSTANCE.getRedirect$ui());
        }
    }

    public static /* synthetic */ Rect fetchFocusRect$ui$default(FocusTargetNode focusTargetNode, LayoutCoordinates layoutCoordinates, int i, Object obj) {
        if ((i & 1) != 0) {
            layoutCoordinates = null;
        }
        return focusTargetNode.fetchFocusRect$ui(layoutCoordinates);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v14 */
    public final void dispatchFocusCallbacks$ui(FocusState previousState, FocusState newState) {
        NodeChain nodes;
        Function2<FocusState, FocusState, Unit> function2;
        FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
        FocusTargetNode activeFocusTargetNode = focusOwner.getActiveFocusTargetNode();
        if (!Intrinsics.areEqual(previousState, newState) && (function2 = this.onFocusChange) != null) {
            function2.invoke(previousState, newState);
        }
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(4096);
        int iM4949constructorimpl2 = NodeKind.m4949constructorimpl(1024);
        Modifier.Node node = getNode();
        int i = iM4949constructorimpl | iM4949constructorimpl2;
        if (!getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
        loop0: while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & i) != 0) {
                        if (node2 != node && (node2.getKindSet() & iM4949constructorimpl2) != 0) {
                            break loop0;
                        }
                        if ((node2.getKindSet() & iM4949constructorimpl) != 0) {
                            Modifier.Node nodePop = node2;
                            MutableVector mutableVector = null;
                            while (nodePop != 0) {
                                if (nodePop instanceof FocusEventModifierNode) {
                                    FocusEventModifierNode focusEventModifierNode = (FocusEventModifierNode) nodePop;
                                    if (activeFocusTargetNode == focusOwner.getActiveFocusTargetNode()) {
                                        focusEventModifierNode.onFocusEvent(newState);
                                    }
                                } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                                    int i2 = 0;
                                    nodePop = nodePop;
                                    while (delegate$ui != null) {
                                        if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                            i2++;
                                            if (i2 == 1) {
                                                nodePop = delegate$ui;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != 0) {
                                                    mutableVector.add(nodePop);
                                                    nodePop = 0;
                                                }
                                                mutableVector.add(delegate$ui);
                                            }
                                        }
                                        delegate$ui = delegate$ui.getChild();
                                        nodePop = nodePop;
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                    }
                    node2 = node2.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node2 = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        Function1<FocusTargetNode, Unit> function1 = this.onDispatchEventsCompleted;
        if (function1 != null) {
            function1.invoke(this);
        }
    }

    /* JADX INFO: renamed from: fetchCustomEnter-aToIllA$ui, reason: not valid java name */
    public final void m2803fetchCustomEnteraToIllA$ui(int focusDirection, Function1<? super FocusRequester, Unit> block) {
        if (this.isProcessingCustomEnter) {
            return;
        }
        this.isProcessingCustomEnter = true;
        try {
            FocusProperties focusPropertiesFetchFocusProperties$ui = fetchFocusProperties$ui();
            CancelIndicatingFocusBoundaryScope cancelIndicatingFocusBoundaryScope = new CancelIndicatingFocusBoundaryScope(focusDirection, null);
            FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
            FocusTargetNode activeFocusTargetNode = focusOwner.getActiveFocusTargetNode();
            focusPropertiesFetchFocusProperties$ui.getOnEnter().invoke(cancelIndicatingFocusBoundaryScope);
            FocusTargetNode activeFocusTargetNode2 = focusOwner.getActiveFocusTargetNode();
            if (cancelIndicatingFocusBoundaryScope.getIsCanceled()) {
                block.invoke(FocusRequester.INSTANCE.getCancel());
            } else if (activeFocusTargetNode != activeFocusTargetNode2 && activeFocusTargetNode2 != null) {
                block.invoke(FocusRequester.INSTANCE.getRedirect$ui());
            }
        } finally {
            InlineMarker.finallyStart(1);
            this.isProcessingCustomEnter = false;
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: renamed from: fetchCustomExit-aToIllA$ui, reason: not valid java name */
    public final void m2804fetchCustomExitaToIllA$ui(int focusDirection, Function1<? super FocusRequester, Unit> block) {
        if (this.isProcessingCustomExit) {
            return;
        }
        this.isProcessingCustomExit = true;
        try {
            FocusProperties focusPropertiesFetchFocusProperties$ui = fetchFocusProperties$ui();
            CancelIndicatingFocusBoundaryScope cancelIndicatingFocusBoundaryScope = new CancelIndicatingFocusBoundaryScope(focusDirection, null);
            FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
            FocusTargetNode activeFocusTargetNode = focusOwner.getActiveFocusTargetNode();
            focusPropertiesFetchFocusProperties$ui.getOnExit().invoke(cancelIndicatingFocusBoundaryScope);
            FocusTargetNode activeFocusTargetNode2 = focusOwner.getActiveFocusTargetNode();
            if (cancelIndicatingFocusBoundaryScope.getIsCanceled()) {
                block.invoke(FocusRequester.INSTANCE.getCancel());
            } else if (activeFocusTargetNode != activeFocusTargetNode2 && activeFocusTargetNode2 != null) {
                block.invoke(FocusRequester.INSTANCE.getRedirect$ui());
            }
        } finally {
            InlineMarker.finallyStart(1);
            this.isProcessingCustomExit = false;
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v14 */
    public final FocusProperties fetchFocusProperties$ui() {
        NodeChain nodes;
        FocusPropertiesImpl focusPropertiesImpl = new FocusPropertiesImpl();
        focusPropertiesImpl.setCanFocus(Focusability.m2816canFocusimpl$ui(getFocusability(), this));
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(2048);
        int iM4949constructorimpl2 = NodeKind.m4949constructorimpl(1024);
        Modifier.Node node = getNode();
        int i = iM4949constructorimpl | iM4949constructorimpl2;
        if (!getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & i) != 0) {
                        if (node2 != node && (node2.getKindSet() & iM4949constructorimpl2) != 0) {
                            return focusPropertiesImpl;
                        }
                        if ((node2.getKindSet() & iM4949constructorimpl) != 0) {
                            Modifier.Node nodePop = node2;
                            MutableVector mutableVector = null;
                            while (nodePop != 0) {
                                if (nodePop instanceof FocusPropertiesModifierNode) {
                                    ((FocusPropertiesModifierNode) nodePop).applyFocusProperties(focusPropertiesImpl);
                                } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                                    int i2 = 0;
                                    nodePop = nodePop;
                                    while (delegate$ui != null) {
                                        if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                            i2++;
                                            if (i2 == 1) {
                                                nodePop = delegate$ui;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != 0) {
                                                    mutableVector.add(nodePop);
                                                    nodePop = 0;
                                                }
                                                mutableVector.add(delegate$ui);
                                            }
                                        }
                                        delegate$ui = delegate$ui.getChild();
                                        nodePop = nodePop;
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                    }
                    node2 = node2.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node2 = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return focusPropertiesImpl;
    }

    public final Rect fetchFocusRect$ui(LayoutCoordinates relativeCoordinates) {
        Rect rectLocalBoundingBoxOf;
        Rect focusRect = fetchFocusProperties$ui().getFocusRect();
        if (focusRect != FocusProperties.INSTANCE.getUnsetFocusRect()) {
            return relativeCoordinates == null ? focusRect : focusRect.m2926translatek4lQ0M(LayoutCoordinates.m4612localPositionOfS_NoaFU$default(relativeCoordinates, DelegatableNodeKt.requireLayoutCoordinates(this), 0L, false, 6, null));
        }
        return (relativeCoordinates == null || (rectLocalBoundingBoxOf = relativeCoordinates.localBoundingBoxOf(DelegatableNodeKt.requireLayoutCoordinates(this), false)) == null) ? RectKt.m2929Recttz77jQw(Offset.INSTANCE.m2905getZeroF1C5BW0(), IntSizeKt.m6205toSizeozmzZPI(DelegatableNodeKt.requireLayoutCoordinates(this).mo4613getSizeYbymL2g())) : rectLocalBoundingBoxOf;
    }

    public final BeyondBoundsLayout getBeyondBoundsLayoutParent() {
        return DelegatableNodeKt.findNearestBeyondBoundsLayoutAncestor(this);
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    public FocusStateImpl getFocusState() {
        FocusOwner focusOwner;
        FocusTargetNode activeFocusTargetNode;
        NodeChain nodes;
        if (getIsAttached() && (activeFocusTargetNode = (focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner()).getActiveFocusTargetNode()) != null) {
            if (this == activeFocusTargetNode) {
                return focusOwner.isFocusCaptured() ? FocusStateImpl.Captured : FocusStateImpl.Active;
            }
            if (activeFocusTargetNode.getIsAttached()) {
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
                                        if (this == ((FocusTargetNode) nodePop)) {
                                            return FocusStateImpl.ActiveParent;
                                        }
                                    } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                        int i = 0;
                                        for (Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate(); delegate$ui != null; delegate$ui = delegate$ui.getChild()) {
                                            if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                                i++;
                                                if (i == 1) {
                                                    nodePop = delegate$ui;
                                                } else {
                                                    if (mutableVector == null) {
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    if (nodePop != null) {
                                                        mutableVector.add(nodePop);
                                                        nodePop = null;
                                                    }
                                                    mutableVector.add(delegate$ui);
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
            return FocusStateImpl.Inactive;
        }
        return FocusStateImpl.Inactive;
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    /* JADX INFO: renamed from: getFocusability-LCbbffg, reason: from getter */
    public int getFocusability() {
        return this.focusability;
    }

    public final int getPreviouslyFocusedChildHash() {
        return this.previouslyFocusedChildHash;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    public final void invalidateFocus$ui() {
        FocusProperties focusProperties;
        int i = WhenMappings.$EnumSwitchMapping$1[getFocusState().ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3 || i == 4) {
                return;
            }
            bu8.a();
            return;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTargetNode$invalidateFocus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m2805invoke() {
                objectRef.element = this.fetchFocusProperties$ui();
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m2805invoke();
                return Unit.INSTANCE;
            }
        });
        Object obj = objectRef.element;
        if (obj == null) {
            Intrinsics.throwUninitializedPropertyAccessException("focusProperties");
            focusProperties = null;
        } else {
            focusProperties = (FocusProperties) obj;
        }
        if (focusProperties.getCanFocus()) {
            return;
        }
        DelegatableNodeKt.requireOwner(this).getFocusOwner().clearFocus(true);
    }

    /* JADX INFO: renamed from: isInteropViewHost, reason: from getter */
    public final boolean getIsInteropViewHost() {
        return this.isInteropViewHost;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        int i = WhenMappings.$EnumSwitchMapping$1[getFocusState().ordinal()];
        if (i == 1 || i == 2) {
            FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
            focusOwner.mo2776clearFocusI7lrPNg(true, true, false, FocusDirection.INSTANCE.m2765getExitdhqQ8s());
            if (this.isInteropViewHost) {
                focusOwner.mo2781requestOwnerFocus7o62pno(null, null);
            }
            focusOwner.scheduleInvalidationForOwner();
        } else if (i == 3) {
            FocusOwner focusOwner2 = DelegatableNodeKt.requireOwner(this).getFocusOwner();
            FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this);
            if (focusTargetNodeFindActiveFocusNode != null && focusTargetNodeFindActiveFocusNode.isInteropViewHost) {
                focusOwner2.mo2781requestOwnerFocus7o62pno(null, null);
                focusOwner2.scheduleInvalidationForOwner();
            }
        } else if (i != 4) {
            bu8.a();
            return;
        }
        this.committedFocusState = null;
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        invalidateFocus$ui();
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public void onPlaced(LayoutCoordinates coordinates) {
        if (ComposeUiFlags.isInitialFocusOnFocusableAvailable) {
            DelegatableNodeKt.requireOwner(getNode()).getFocusOwner().focusTargetAvailable();
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        if (getFocusState().isFocused()) {
            DelegatableNodeKt.requireOwner(this).getFocusOwner().mo2776clearFocusI7lrPNg(true, true, true, FocusDirection.INSTANCE.m2765getExitdhqQ8s());
        }
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the version accepting FocusDirection", replaceWith = @ReplaceWith(expression = "this.requestFocus()", imports = {}))
    public /* synthetic */ boolean requestFocus() {
        return mo2796requestFocus3ESFkO8(FocusDirection.INSTANCE.m2764getEnterdhqQ8s());
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    /* JADX INFO: renamed from: requestFocus-3ESFkO8 */
    public boolean mo2796requestFocus3ESFkO8(final int focusDirection) {
        boolean zM2801assignFocus3ESFkO8;
        Trace.beginSection("FocusTransactions:requestFocus");
        try {
            if (ComposeUiFlags.isRequestFocusOnNonFocusableFocusTargetEnabled) {
                zM2801assignFocus3ESFkO8 = fetchFocusProperties$ui().getCanFocus() ? m2801assignFocus3ESFkO8(focusDirection) : TwoDimensionalFocusSearchKt.m2835findChildCorrespondingToFocusEnterOMvw8(this, focusDirection, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusTargetNode$requestFocus$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final Boolean invoke(FocusTargetNode focusTargetNode) {
                        return Boolean.valueOf(focusTargetNode.m2801assignFocus3ESFkO8(focusDirection));
                    }
                });
            } else {
                zM2801assignFocus3ESFkO8 = fetchFocusProperties$ui().getCanFocus() && m2801assignFocus3ESFkO8(focusDirection);
            }
            return zM2801assignFocus3ESFkO8;
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    /* JADX INFO: renamed from: setFocusability-josRg5g */
    public void mo2797setFocusabilityjosRg5g(int i) {
        if (Focusability.m2819equalsimpl0(this.focusability, i)) {
            return;
        }
        this.focusability = i;
        if (getIsAttached() && this == DelegatableNodeKt.requireOwner(this).getFocusOwner().getActiveFocusTargetNode() && !Focusability.m2816canFocusimpl$ui(this.focusability, this)) {
            if (!ComposeUiFlags.isOptimizedFocusEventDispatchEnabled) {
                FocusTransactionsKt.clearFocus(this, true, true);
                return;
            }
            if (FocusTransactionsKt.clearFocus(this, true, true)) {
                FocusTargetNode activeFocusTargetNode = DelegatableNodeKt.requireOwner(this).getFocusOwner().getActiveFocusTargetNode();
                DelegatableNodeKt.requireOwner(this).getFocusOwner().setActiveFocusTargetNode(null);
                if (activeFocusTargetNode != null) {
                    activeFocusTargetNode.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
                }
            }
        }
    }

    public final void setPreviouslyFocusedChildHash(int i) {
        this.previouslyFocusedChildHash = i;
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\bÁ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016J\f\u0010\t\u001a\u00020\u0007*\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode$FocusTargetElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/focus/FocusTargetNode;", "<init>", "()V", "create", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "hashCode", "", "equals", "", "other", "", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class FocusTargetElement extends ModifierNodeElement<FocusTargetNode> {
        public static final int $stable = 0;
        public static final FocusTargetElement INSTANCE = new FocusTargetElement();

        private FocusTargetElement() {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public FocusTargetNode getNode() {
            return new FocusTargetNode(0, false, null, null, 15, null);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return 1739042953;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("focusTarget");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(FocusTargetNode node) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FocusTargetNode(int i, boolean z, Function2<? super FocusState, ? super FocusState, Unit> function2, Function1<? super FocusTargetNode, Unit> function1) {
        this.isInteropViewHost = z;
        this.onFocusChange = function2;
        this.onDispatchEventsCompleted = function1;
        this.focusability = i;
    }

    public /* synthetic */ FocusTargetNode(int i, boolean z, Function2 function2, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, z, function2, function1);
    }
}
