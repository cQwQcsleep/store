package androidx.compose.ui.draganddrop;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.core.app.NotificationCompat;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u0000 E2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001EBD\u0012!\b\u0002\u0010\u0007\u001a\u001b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\b\f\u0012\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010&\u001a\u00020\u000bH\u0016J\u0017\u0010'\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 H\u0016¢\u0006\u0004\b(\u0010$J\u0017\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\nH\u0016¢\u0006\u0004\b,\u0010$J'\u0010-\u001a\u00020\u000b*\u00020\t2\u0006\u0010+\u001a\u00020\n2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001d0/¢\u0006\u0004\b0\u00101J8\u00102\u001a\u00020\u000b2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0017\u00107\u001a\u0013\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u000b0\u000e¢\u0006\u0002\b\fH\u0017¢\u0006\u0004\b9\u0010:J\u0010\u0010;\u001a\u00020\u001d2\u0006\u0010<\u001a\u00020\u000fH\u0016J\u0010\u0010=\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u000fH\u0016J\u0010\u0010?\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u000fH\u0016J\u0010\u0010@\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u000fH\u0016J\u0010\u0010A\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u000fH\u0016J\u0010\u0010B\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u000fH\u0016J\u0010\u0010C\u001a\u00020\u001d2\u0006\u0010>\u001a\u00020\u000fH\u0016J\u0010\u0010D\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u000fH\u0016R'\u0010\u0007\u001a\u001b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\b\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d8G¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001eR\u001c\u0010\u001f\u001a\u00020 X\u0080\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0014\u0010)\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u001e¨\u0006F"}, d2 = {"Landroidx/compose/ui/draganddrop/DragAndDropNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "Landroidx/compose/ui/draganddrop/DragAndDropSourceModifierNode;", "Landroidx/compose/ui/draganddrop/DragAndDropTargetModifierNode;", "Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "onStartTransfer", "Lkotlin/Function2;", "Landroidx/compose/ui/draganddrop/DragAndDropStartTransferScope;", "Landroidx/compose/ui/geometry/Offset;", "", "Lkotlin/ExtensionFunctionType;", "onDropTargetValidate", "Lkotlin/Function1;", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "<init>", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "dragAndDropManager", "Landroidx/compose/ui/draganddrop/DragAndDropManager;", "getDragAndDropManager", "()Landroidx/compose/ui/draganddrop/DragAndDropManager;", "lastChildDragAndDropModifierNode", "thisDragAndDropTarget", "hasEligibleDropTarget", "", "()Z", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g$ui", "()J", "setSize-ozmzZPI$ui", "(J)V", "J", "onDetach", "onRemeasured", "onRemeasured-ozmzZPI", "isRequestDragAndDropTransferRequired", "requestDragAndDropTransfer", "offset", "requestDragAndDropTransfer-k-4lQ0M", "startDragAndDropTransfer", "isTransferStarted", "Lkotlin/Function0;", "startDragAndDropTransfer-d-4ec7I", "(Landroidx/compose/ui/draganddrop/DragAndDropStartTransferScope;JLkotlin/jvm/functions/Function0;)V", "drag", "transferData", "Landroidx/compose/ui/draganddrop/DragAndDropTransferData;", "decorationSize", "Landroidx/compose/ui/geometry/Size;", "drawDragDecoration", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drag-12SF9DM", "(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;JLkotlin/jvm/functions/Function1;)V", "acceptDragAndDropTransfer", "startEvent", "onStarted", NotificationCompat.CATEGORY_EVENT, "onEntered", "onMoved", "onChanged", "onExited", "onDrop", "onEnded", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DragAndDropNode extends Modifier.Node implements TraversableNode, DragAndDropModifierNode, DragAndDropSourceModifierNode, DragAndDropTargetModifierNode, DragAndDropTarget {
    private DragAndDropNode lastChildDragAndDropModifierNode;
    private final Function1<DragAndDropEvent, DragAndDropTarget> onDropTargetValidate;
    private Function2<? super DragAndDropStartTransferScope, ? super Offset, Unit> onStartTransfer;
    private long size;
    private DragAndDropTarget thisDragAndDropTarget;
    private final Object traverseKey;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public DragAndDropNode(Function2<? super DragAndDropStartTransferScope, ? super Offset, Unit> function2, Function1<? super DragAndDropEvent, ? extends DragAndDropTarget> function1) {
        this.onStartTransfer = function2;
        this.onDropTargetValidate = function1;
        this.traverseKey = Companion.DragAndDropTraversableKey.INSTANCE;
        this.size = IntSize.INSTANCE.m6198getZeroYbymL2g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DragAndDropManager getDragAndDropManager() {
        return DelegatableNodeKt.requireOwner(this).getDragAndDropManager();
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropModifierNode
    public boolean acceptDragAndDropTransfer(final DragAndDropEvent startEvent) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        DragAndDropNodeKt.traverseSelfAndDescendants(this, new Function1<DragAndDropNode, TraversableNode.Companion.TraverseDescendantsAction>() { // from class: androidx.compose.ui.draganddrop.DragAndDropNode.acceptDragAndDropTransfer.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final TraversableNode.Companion.TraverseDescendantsAction invoke(DragAndDropNode dragAndDropNode) {
                if (!dragAndDropNode.getIsAttached()) {
                    return TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
                }
                if (!(dragAndDropNode.thisDragAndDropTarget == null)) {
                    InlineClassHelperKt.throwIllegalStateException("DragAndDropTarget self reference must be null at the start of a drag and drop session");
                }
                Function1 function1 = dragAndDropNode.onDropTargetValidate;
                dragAndDropNode.thisDragAndDropTarget = function1 != null ? (DragAndDropTarget) function1.invoke(startEvent) : null;
                boolean z = dragAndDropNode.thisDragAndDropTarget != null;
                if (z) {
                    this.getDragAndDropManager().registerTargetInterest(dragAndDropNode);
                }
                Ref.BooleanRef booleanRef2 = booleanRef;
                booleanRef2.element = booleanRef2.element || z;
                return TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
            }
        });
        return booleanRef.element;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropModifierNode
    @Deprecated(message = "Use DragAndDropSourceModifierNode.requestDragAndDropTransfer instead")
    /* JADX INFO: renamed from: drag-12SF9DM */
    public void mo2630drag12SF9DM(final DragAndDropTransferData transferData, final long decorationSize, final Function1<? super DrawScope, Unit> drawDragDecoration) {
        if (!(this.onStartTransfer == null)) {
            InlineClassHelperKt.throwIllegalStateException("Check failed.");
        }
        this.onStartTransfer = new Function2<DragAndDropStartTransferScope, Offset, Unit>() { // from class: androidx.compose.ui.draganddrop.DragAndDropNode$drag$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                m2636invokeUv8p0NA((DragAndDropStartTransferScope) obj, ((Offset) obj2).m2899unboximpl());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
            public final void m2636invokeUv8p0NA(DragAndDropStartTransferScope dragAndDropStartTransferScope, long j) {
                dragAndDropStartTransferScope.mo2629startDragAndDropTransfer12SF9DM(transferData, decorationSize, drawDragDecoration);
            }
        };
        getDragAndDropManager().mo2627requestDragAndDropTransferUv8p0NA(this, Offset.INSTANCE.m2904getUnspecifiedF1C5BW0());
        this.onStartTransfer = null;
    }

    /* JADX INFO: renamed from: getSize-YbymL2g$ui, reason: not valid java name and from getter */
    public final long getSize() {
        return this.size;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return this.traverseKey;
    }

    public final boolean hasEligibleDropTarget() {
        return (this.lastChildDragAndDropModifierNode == null && this.thisDragAndDropTarget == null) ? false : true;
    }

    public boolean isRequestDragAndDropTransferRequired() {
        return getDragAndDropManager().isRequestDragAndDropTransferRequired();
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onChanged(DragAndDropEvent event) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onChanged(event);
            return;
        }
        DragAndDropNode dragAndDropNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode != null) {
            dragAndDropNode.onChanged(event);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.thisDragAndDropTarget = null;
        this.lastChildDragAndDropModifierNode = null;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public boolean onDrop(DragAndDropEvent event) {
        DragAndDropNode dragAndDropNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode != null) {
            return dragAndDropNode.onDrop(event);
        }
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            return dragAndDropTarget.onDrop(event);
        }
        return false;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onEnded(final DragAndDropEvent event) {
        DragAndDropNodeKt.traverseSelfAndDescendants(this, new Function1<DragAndDropNode, TraversableNode.Companion.TraverseDescendantsAction>() { // from class: androidx.compose.ui.draganddrop.DragAndDropNode.onEnded.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final TraversableNode.Companion.TraverseDescendantsAction invoke(DragAndDropNode dragAndDropNode) {
                if (!dragAndDropNode.getNode().getIsAttached()) {
                    return TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
                }
                DragAndDropTarget dragAndDropTarget = dragAndDropNode.thisDragAndDropTarget;
                if (dragAndDropTarget != null) {
                    dragAndDropTarget.onEnded(event);
                }
                dragAndDropNode.thisDragAndDropTarget = null;
                dragAndDropNode.lastChildDragAndDropModifierNode = null;
                return TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
            }
        });
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onEntered(DragAndDropEvent event) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onEntered(event);
            return;
        }
        DragAndDropNode dragAndDropNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode != null) {
            dragAndDropNode.onEntered(event);
        }
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onExited(DragAndDropEvent event) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onExited(event);
        }
        DragAndDropNode dragAndDropNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode != null) {
            dragAndDropNode.onExited(event);
        }
        this.lastChildDragAndDropModifierNode = null;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onMoved(final DragAndDropEvent event) {
        TraversableNode traversableNode;
        DragAndDropNode dragAndDropNode;
        DragAndDropNode dragAndDropNode2 = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode2 == null || !DragAndDropNodeKt.m2638containsUv8p0NA(dragAndDropNode2, DragAndDrop_androidKt.getPositionInRoot(event))) {
            if (getNode().getIsAttached()) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                TraversableNodeKt.traverseDescendants(this, new Function1<DragAndDropNode, TraversableNode.Companion.TraverseDescendantsAction>() { // from class: androidx.compose.ui.draganddrop.DragAndDropNode$onMoved$$inlined$firstDescendantOrNull$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final TraversableNode.Companion.TraverseDescendantsAction invoke(DragAndDropNode dragAndDropNode3) {
                        DragAndDropNode dragAndDropNode4 = dragAndDropNode3;
                        if (!this.getDragAndDropManager().isInterestedTarget(dragAndDropNode4) || !DragAndDropNodeKt.m2638containsUv8p0NA(dragAndDropNode4, DragAndDrop_androidKt.getPositionInRoot(event))) {
                            return TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
                        }
                        objectRef.element = dragAndDropNode3;
                        return TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal;
                    }
                });
                traversableNode = (TraversableNode) objectRef.element;
            } else {
                traversableNode = null;
            }
            dragAndDropNode = (DragAndDropNode) traversableNode;
        } else {
            dragAndDropNode = dragAndDropNode2;
        }
        if (dragAndDropNode != null && dragAndDropNode2 == null) {
            DragAndDropNodeKt.dispatchEntered(dragAndDropNode, event);
            DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
            if (dragAndDropTarget != null) {
                dragAndDropTarget.onExited(event);
            }
        } else if (dragAndDropNode == null && dragAndDropNode2 != null) {
            DragAndDropTarget dragAndDropTarget2 = this.thisDragAndDropTarget;
            if (dragAndDropTarget2 != null) {
                DragAndDropNodeKt.dispatchEntered(dragAndDropTarget2, event);
            }
            dragAndDropNode2.onExited(event);
        } else if (!Intrinsics.areEqual(dragAndDropNode, dragAndDropNode2)) {
            if (dragAndDropNode != null) {
                DragAndDropNodeKt.dispatchEntered(dragAndDropNode, event);
            }
            if (dragAndDropNode2 != null) {
                dragAndDropNode2.onExited(event);
            }
        } else if (dragAndDropNode != null) {
            dragAndDropNode.onMoved(event);
        } else {
            DragAndDropTarget dragAndDropTarget3 = this.thisDragAndDropTarget;
            if (dragAndDropTarget3 != null) {
                dragAndDropTarget3.onMoved(event);
            }
        }
        this.lastChildDragAndDropModifierNode = dragAndDropNode;
    }

    /* JADX INFO: renamed from: onRemeasured-ozmzZPI, reason: not valid java name */
    public void m2632onRemeasuredozmzZPI(long size) {
        this.size = size;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onStarted(DragAndDropEvent event) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onStarted(event);
            return;
        }
        DragAndDropNode dragAndDropNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode != null) {
            dragAndDropNode.onStarted(event);
        }
    }

    /* JADX INFO: renamed from: requestDragAndDropTransfer-k-4lQ0M, reason: not valid java name */
    public void m2633requestDragAndDropTransferk4lQ0M(long offset) {
        if (!(this.onStartTransfer != null)) {
            InlineClassHelperKt.throwIllegalStateException("Check failed.");
        }
        getDragAndDropManager().mo2627requestDragAndDropTransferUv8p0NA(this, offset);
    }

    /* JADX INFO: renamed from: setSize-ozmzZPI$ui, reason: not valid java name */
    public final void m2634setSizeozmzZPI$ui(long j) {
        this.size = j;
    }

    /* JADX INFO: renamed from: startDragAndDropTransfer-d-4ec7I, reason: not valid java name */
    public final void m2635startDragAndDropTransferd4ec7I(final DragAndDropStartTransferScope dragAndDropStartTransferScope, final long j, final Function0<Boolean> function0) {
        final LayoutCoordinates coordinates = DelegatableNodeKt.requireLayoutNode(this).getCoordinates();
        DragAndDropNodeKt.traverseSelfAndDescendants(this, new Function1<DragAndDropNode, TraversableNode.Companion.TraverseDescendantsAction>() { // from class: androidx.compose.ui.draganddrop.DragAndDropNode$startDragAndDropTransfer$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final TraversableNode.Companion.TraverseDescendantsAction invoke(DragAndDropNode dragAndDropNode) {
                if (!dragAndDropNode.getIsAttached()) {
                    return TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
                }
                Function2 function2 = dragAndDropNode.onStartTransfer;
                if (function2 == null) {
                    return TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
                }
                long j2 = j;
                Offset.Companion companion = Offset.INSTANCE;
                if (Offset.m2886equalsimpl0(j2, companion.m2904getUnspecifiedF1C5BW0())) {
                    function2.invoke(dragAndDropStartTransferScope, Offset.m2878boximpl(companion.m2904getUnspecifiedF1C5BW0()));
                } else {
                    long jMo4614localPositionOfR5De75A = DelegatableNodeKt.requireLayoutNode(dragAndDropNode).getCoordinates().mo4614localPositionOfR5De75A(coordinates, j);
                    if (!SizeKt.m2979toRectuvyYCjk(IntSizeKt.m6205toSizeozmzZPI(dragAndDropNode.getSize())).m2915containsk4lQ0M(jMo4614localPositionOfR5De75A)) {
                        return TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
                    }
                    function2.invoke(dragAndDropStartTransferScope, Offset.m2878boximpl(jMo4614localPositionOfR5De75A));
                }
                return ((Boolean) function0.invoke()).booleanValue() ? TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal : TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
            }
        });
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/compose/ui/draganddrop/DragAndDropNode$Companion;", "", "<init>", "()V", "DragAndDropTraversableKey", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/draganddrop/DragAndDropNode$Companion$DragAndDropTraversableKey;", "", "<init>", "()V", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class DragAndDropTraversableKey {
            public static final DragAndDropTraversableKey INSTANCE = new DragAndDropTraversableKey();

            private DragAndDropTraversableKey() {
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DragAndDropNode() {
        Function2 function2 = null;
        this(function2, function2, 3, function2);
    }

    public /* synthetic */ DragAndDropNode(Function2 function2, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : function2, (i & 2) != 0 ? null : function1);
    }
}
