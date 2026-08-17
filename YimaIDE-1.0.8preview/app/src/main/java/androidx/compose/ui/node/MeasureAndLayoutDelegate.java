package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b%\b\u0001\u0018\u00002\u00020\u0001:\u0001_B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020 ¢\u0006\u0004\b*\u0010+J\u0018\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\tJ\u0018\u00101\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\tJ\u0018\u00102\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\tJ\u0018\u00103\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\tJ\u000e\u00104\u001a\u00020(2\u0006\u0010/\u001a\u00020\u0003J\u001f\u00105\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010 H\u0002¢\u0006\u0002\b6J\u001f\u00107\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010 H\u0002¢\u0006\u0002\b8J\u0018\u00109\u001a\u00020\t2\u0010\b\u0002\u0010:\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010;J\u0006\u0010<\u001a\u00020(J\u0010\u0010=\u001a\u00020(2\u0006\u0010/\u001a\u00020\u0003H\u0002J\u001d\u00109\u001a\u00020(2\u0006\u0010/\u001a\u00020\u00032\u0006\u0010)\u001a\u00020 ¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020(2\u0006\u0010/\u001a\u00020\u0003H\u0002J\u001f\u0010A\u001a\u00020(2\u0006\u0010B\u001a\u00020\t2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020(0;H\u0082\bJ\u000e\u0010D\u001a\u00020(2\u0006\u0010E\u001a\u00020\u0017J\b\u0010F\u001a\u00020(H\u0002J$\u0010G\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u0010H\u001a\u00020\t2\b\b\u0002\u0010I\u001a\u00020\tH\u0002J\b\u0010J\u001a\u00020(H\u0002J\u0018\u0010K\u001a\u00020(2\u0006\u0010/\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\tH\u0002J\u0016\u0010L\u001a\u00020(2\u0006\u0010/\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\tJ\u0018\u0010M\u001a\u00020(2\u0006\u0010N\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\tH\u0002J\u0018\u0010O\u001a\u00020(2\u0006\u0010/\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\tH\u0002J\u0010\u0010P\u001a\u00020(2\b\b\u0002\u0010Q\u001a\u00020\tJ\u000e\u0010R\u001a\u00020(2\u0006\u0010N\u001a\u00020\u0003J\u0014\u0010^\u001a\u00020\t*\u00020\u00032\u0006\u0010H\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u000e\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00198F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010S\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0018\u0010V\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bW\u0010UR\u0018\u0010X\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bY\u0010UR\u0018\u0010Z\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b[\u0010UR\u0018\u0010\\\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b]\u0010U¨\u0006`"}, d2 = {"Landroidx/compose/ui/node/MeasureAndLayoutDelegate;", "", "root", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "relayoutNodes", "Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;", "hasPendingMeasureOrLayout", "", "getHasPendingMeasureOrLayout", "()Z", "hasPendingOnPositionedCallbacks", "getHasPendingOnPositionedCallbacks", "duringMeasureLayout", "getDuringMeasureLayout$ui", "setDuringMeasureLayout$ui", "(Z)V", "duringFullMeasureLayoutPass", "onPositionedDispatcher", "Landroidx/compose/ui/node/OnPositionedDispatcher;", "onLayoutCompletedListeners", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "value", "", "measureIteration", "getMeasureIteration", "()J", "postponedMeasureRequests", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "rootConstraints", "Landroidx/compose/ui/unit/Constraints;", "uncaughtExceptionHandler", "Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;", "getUncaughtExceptionHandler$ui", "()Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;", "setUncaughtExceptionHandler$ui", "(Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;)V", "updateRootConstraints", "", "constraints", "updateRootConstraints-BRTryo0", "(J)V", "consistencyChecker", "Landroidx/compose/ui/node/LayoutTreeConsistencyChecker;", "requestLookaheadRemeasure", "layoutNode", "forced", "requestRemeasure", "requestLookaheadRelayout", "requestRelayout", "requestOnPositionedCallback", "doLookaheadRemeasure", "doLookaheadRemeasure-sdFAvZA", "doRemeasure", "doRemeasure-sdFAvZA", "measureAndLayout", "onLayout", "Lkotlin/Function0;", "measureOnly", "remeasureLookaheadRootsInSubtree", "measureAndLayout-0kLqBqw", "(Landroidx/compose/ui/node/LayoutNode;J)V", "ensureSubtreeLookaheadReplaced", "performMeasureAndLayout", "fullPass", "block", "registerOnLayoutCompletedListener", "listener", "callOnLayoutCompletedListeners", "remeasureAndRelayoutIfNeeded", "affectsLookahead", "relayoutNeeded", "drainPostponedMeasureRequests", "remeasureOnly", "forceMeasureTheSubtree", "onlyRemeasureIfPending", "node", "forceMeasureTheSubtreeInternal", "dispatchOnPositionedCallbacks", "forceDispatch", "onNodeDetached", "remeasureCanAffectParentSize", "getRemeasureCanAffectParentSize", "(Landroidx/compose/ui/node/LayoutNode;)Z", "measuredByPlacedParent", "getMeasuredByPlacedParent", "canAffectPlacedParent", "getCanAffectPlacedParent", "canAffectParentInLookahead", "getCanAffectParentInLookahead", "lookaheadRemeasureCanAffectParentSize", "getLookaheadRemeasureCanAffectParentSize", "measurePending", "PostponedRequest", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class MeasureAndLayoutDelegate {
    public static final int $stable = 8;
    private final LayoutTreeConsistencyChecker consistencyChecker;
    private boolean duringFullMeasureLayoutPass;
    private boolean duringMeasureLayout;
    private long measureIteration;
    private final MutableVector<Owner.OnLayoutCompletedListener> onLayoutCompletedListeners;
    private final OnPositionedDispatcher onPositionedDispatcher;
    private final MutableVector<PostponedRequest> postponedMeasureRequests;
    private final DepthSortedSetsForDifferentPasses relayoutNodes;
    private final LayoutNode root;
    private Constraints rootConstraints;
    private RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "", "node", "Landroidx/compose/ui/node/LayoutNode;", "isLookahead", "", "isForced", "<init>", "(Landroidx/compose/ui/node/LayoutNode;ZZ)V", "getNode", "()Landroidx/compose/ui/node/LayoutNode;", "()Z", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class PostponedRequest {
        public static final int $stable = 8;
        private final boolean isForced;
        private final boolean isLookahead;
        private final LayoutNode node;

        public PostponedRequest(LayoutNode layoutNode, boolean z, boolean z2) {
            this.node = layoutNode;
            this.isLookahead = z;
            this.isForced = z2;
        }

        public final LayoutNode getNode() {
            return this.node;
        }

        /* JADX INFO: renamed from: isForced, reason: from getter */
        public final boolean getIsForced() {
            return this.isForced;
        }

        /* JADX INFO: renamed from: isLookahead, reason: from getter */
        public final boolean getIsLookahead() {
            return this.isLookahead;
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutNode.LayoutState.values().length];
            try {
                iArr[LayoutNode.LayoutState.LookaheadMeasuring.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutNode.LayoutState.Measuring.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LayoutNode.LayoutState.LookaheadLayingOut.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LayoutNode.LayoutState.LayingOut.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[LayoutNode.LayoutState.Idle.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MeasureAndLayoutDelegate(LayoutNode layoutNode) {
        this.root = layoutNode;
        Owner.Companion companion = Owner.INSTANCE;
        DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = new DepthSortedSetsForDifferentPasses(companion.getEnableExtraAssertions());
        this.relayoutNodes = depthSortedSetsForDifferentPasses;
        this.onPositionedDispatcher = new OnPositionedDispatcher();
        this.onLayoutCompletedListeners = new MutableVector<>(new Owner.OnLayoutCompletedListener[16], 0);
        this.measureIteration = 1L;
        MutableVector<PostponedRequest> mutableVector = new MutableVector<>(new PostponedRequest[16], 0);
        this.postponedMeasureRequests = mutableVector;
        this.consistencyChecker = companion.getEnableExtraAssertions() ? new LayoutTreeConsistencyChecker(layoutNode, depthSortedSetsForDifferentPasses, mutableVector.asMutableList()) : null;
    }

    private final void callOnLayoutCompletedListeners() {
        MutableVector<Owner.OnLayoutCompletedListener> mutableVector = this.onLayoutCompletedListeners;
        Owner.OnLayoutCompletedListener[] onLayoutCompletedListenerArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            onLayoutCompletedListenerArr[i].onLayoutComplete();
        }
        this.onLayoutCompletedListeners.clear();
    }

    public static /* synthetic */ void dispatchOnPositionedCallbacks$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    /* JADX INFO: renamed from: doLookaheadRemeasure-sdFAvZA, reason: not valid java name */
    private final boolean m4888doLookaheadRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        if (layoutNode.getLookaheadRoot() == null) {
            return false;
        }
        boolean zM4846lookaheadRemeasure_Sx5XlM$ui = constraints != null ? layoutNode.m4846lookaheadRemeasure_Sx5XlM$ui(constraints) : LayoutNode.m4839lookaheadRemeasure_Sx5XlM$ui$default(layoutNode, null, 1, null);
        LayoutNode parent$ui = layoutNode.getParent$ui();
        if (zM4846lookaheadRemeasure_Sx5XlM$ui && parent$ui != null) {
            if (parent$ui.getLookaheadRoot() == null) {
                LayoutNode.requestRemeasure$ui$default(parent$ui, false, false, false, 3, null);
                return zM4846lookaheadRemeasure_Sx5XlM$ui;
            }
            if (layoutNode.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.InMeasureBlock) {
                LayoutNode.requestLookaheadRemeasure$ui$default(parent$ui, false, false, false, 3, null);
                return zM4846lookaheadRemeasure_Sx5XlM$ui;
            }
            if (layoutNode.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.InLayoutBlock) {
                LayoutNode.requestLookaheadRelayout$ui$default(parent$ui, false, 1, null);
            }
        }
        return zM4846lookaheadRemeasure_Sx5XlM$ui;
    }

    /* JADX INFO: renamed from: doRemeasure-sdFAvZA, reason: not valid java name */
    private final boolean m4889doRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean zM4847remeasure_Sx5XlM$ui = constraints != null ? layoutNode.m4847remeasure_Sx5XlM$ui(constraints) : LayoutNode.m4840remeasure_Sx5XlM$ui$default(layoutNode, null, 1, null);
        LayoutNode parent$ui = layoutNode.getParent$ui();
        if (zM4847remeasure_Sx5XlM$ui && parent$ui != null) {
            if (layoutNode.getMeasuredByParent$ui() == LayoutNode.UsageByParent.InMeasureBlock) {
                LayoutNode.requestRemeasure$ui$default(parent$ui, false, false, false, 3, null);
                return zM4847remeasure_Sx5XlM$ui;
            }
            if (layoutNode.getMeasuredByParent$ui() == LayoutNode.UsageByParent.InLayoutBlock) {
                LayoutNode.requestRelayout$ui$default(parent$ui, false, 1, null);
            }
        }
        return zM4847remeasure_Sx5XlM$ui;
    }

    private final void drainPostponedMeasureRequests() {
        if (this.postponedMeasureRequests.getSize() != 0) {
            MutableVector<PostponedRequest> mutableVector = this.postponedMeasureRequests;
            PostponedRequest[] postponedRequestArr = mutableVector.content;
            int size = mutableVector.getSize();
            for (int i = 0; i < size; i++) {
                PostponedRequest postponedRequest = postponedRequestArr[i];
                if (postponedRequest.getNode().isAttached()) {
                    if (postponedRequest.getIsLookahead()) {
                        LayoutNode.requestLookaheadRemeasure$ui$default(postponedRequest.getNode(), postponedRequest.getIsForced(), false, false, 2, null);
                    } else {
                        LayoutNode.requestRemeasure$ui$default(postponedRequest.getNode(), postponedRequest.getIsForced(), false, false, 2, null);
                    }
                }
            }
            this.postponedMeasureRequests.clear();
        }
    }

    private final void ensureSubtreeLookaheadReplaced(LayoutNode layoutNode) {
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode2 = layoutNodeArr[i];
            if (Intrinsics.areEqual(layoutNode2.isPlacedInLookahead(), Boolean.TRUE) && !layoutNode2.getIsDeactivated()) {
                if (this.relayoutNodes.contains(layoutNode2, true)) {
                    layoutNode2.lookaheadReplace$ui();
                }
                ensureSubtreeLookaheadReplaced(layoutNode2);
            }
        }
    }

    private final void forceMeasureTheSubtreeInternal(LayoutNode layoutNode, boolean affectsLookahead) {
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode2 = layoutNodeArr[i];
            if ((!affectsLookahead && getRemeasureCanAffectParentSize(layoutNode2)) || (affectsLookahead && getLookaheadRemeasureCanAffectParentSize(layoutNode2))) {
                if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNode2) && !affectsLookahead) {
                    if (layoutNode2.getLookaheadMeasurePending$ui() && this.relayoutNodes.contains(layoutNode2, true)) {
                        remeasureAndRelayoutIfNeeded(layoutNode2, true, false);
                    } else {
                        forceMeasureTheSubtree(layoutNode2, true);
                    }
                }
                onlyRemeasureIfPending(layoutNode2, affectsLookahead);
                if (!measurePending(layoutNode2, affectsLookahead)) {
                    forceMeasureTheSubtreeInternal(layoutNode2, affectsLookahead);
                }
            }
        }
        onlyRemeasureIfPending(layoutNode, affectsLookahead);
    }

    private final boolean getCanAffectParentInLookahead(LayoutNode layoutNode) {
        AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui;
        AlignmentLines alignmentLines;
        if (layoutNode.getLookaheadMeasurePending$ui()) {
            return (layoutNode.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.NotUsed && ((lookaheadAlignmentLinesOwner$ui = layoutNode.getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui()) == null || (alignmentLines = lookaheadAlignmentLinesOwner$ui.getAlignmentLines()) == null || !alignmentLines.getRequired$ui())) ? false : true;
        }
        return false;
    }

    private final boolean getCanAffectPlacedParent(LayoutNode layoutNode) {
        return layoutNode.getMeasurePending$ui() && getMeasuredByPlacedParent(layoutNode);
    }

    private final boolean getLookaheadRemeasureCanAffectParentSize(LayoutNode layoutNode) {
        AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui;
        AlignmentLines alignmentLines;
        return layoutNode.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.InMeasureBlock || !((lookaheadAlignmentLinesOwner$ui = layoutNode.getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui()) == null || (alignmentLines = lookaheadAlignmentLinesOwner$ui.getAlignmentLines()) == null || !alignmentLines.getRequired$ui());
    }

    private final boolean getMeasuredByPlacedParent(LayoutNode layoutNode) {
        do {
            if (layoutNode.getMeasuredByParent$ui() == LayoutNode.UsageByParent.NotUsed && !layoutNode.getLayoutDelegate().getAlignmentLinesOwner$ui().getAlignmentLines().getRequired$ui()) {
                LayoutNode parent$ui = layoutNode.getParent$ui();
                if ((parent$ui != null ? parent$ui.getLayoutState$ui() : null) != LayoutNode.LayoutState.Measuring) {
                    return false;
                }
            }
            layoutNode = layoutNode.getParent$ui();
            if (layoutNode == null) {
                return false;
            }
        } while (!layoutNode.isPlaced());
        return true;
    }

    private final boolean getRemeasureCanAffectParentSize(LayoutNode layoutNode) {
        return layoutNode.getMeasuredByParent$ui() == LayoutNode.UsageByParent.InMeasureBlock || layoutNode.getLayoutDelegate().getAlignmentLinesOwner$ui().getAlignmentLines().getRequired$ui();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean measureAndLayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        return measureAndLayoutDelegate.measureAndLayout(function0);
    }

    private final boolean measurePending(LayoutNode layoutNode, boolean z) {
        return z ? layoutNode.getLookaheadMeasurePending$ui() : layoutNode.getMeasurePending$ui();
    }

    private final void onlyRemeasureIfPending(LayoutNode node, boolean affectsLookahead) {
        if (measurePending(node, affectsLookahead)) {
            remeasureAndRelayoutIfNeeded(node, affectsLookahead, false);
        }
    }

    private final void performMeasureAndLayout(boolean fullPass, Function0<Unit> block) {
        if (!this.root.isAttached()) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        if (!this.root.isPlaced()) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        if (this.duringMeasureLayout) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            this.duringFullMeasureLayoutPass = fullPass;
            try {
                block.invoke();
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                try {
                    RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
                    if (uncaughtExceptionHandler == null) {
                        throw th;
                    }
                    uncaughtExceptionHandler.onUncaughtException(th);
                    InlineMarker.finallyStart(1);
                } catch (Throwable th2) {
                    InlineMarker.finallyStart(1);
                    this.duringMeasureLayout = false;
                    this.duringFullMeasureLayoutPass = false;
                    InlineMarker.finallyEnd(1);
                    throw th2;
                }
            }
            this.duringMeasureLayout = false;
            this.duringFullMeasureLayoutPass = false;
            InlineMarker.finallyEnd(1);
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean remeasureAndRelayoutIfNeeded(LayoutNode layoutNode, boolean affectsLookahead, boolean relayoutNeeded) {
        Constraints constraints;
        boolean zM4888doLookaheadRemeasuresdFAvZA;
        LayoutNode parent$ui;
        if (layoutNode.getIsDeactivated()) {
            return false;
        }
        if (!layoutNode.isPlaced() && !layoutNode.isPlacedByParent() && !getCanAffectPlacedParent(layoutNode) && !Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE) && !getCanAffectParentInLookahead(layoutNode) && !layoutNode.getAlignmentLinesRequired$ui()) {
            return false;
        }
        if (layoutNode == this.root) {
            constraints = this.rootConstraints;
            constraints.getClass();
        } else {
            constraints = null;
        }
        if (affectsLookahead) {
            zM4888doLookaheadRemeasuresdFAvZA = layoutNode.getLookaheadMeasurePending$ui() ? m4888doLookaheadRemeasuresdFAvZA(layoutNode, constraints) : false;
            if (relayoutNeeded && ((zM4888doLookaheadRemeasuresdFAvZA || layoutNode.getLookaheadLayoutPending$ui()) && Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE))) {
                layoutNode.lookaheadReplace$ui();
            }
        } else {
            boolean zM4889doRemeasuresdFAvZA = layoutNode.getMeasurePending$ui() ? m4889doRemeasuresdFAvZA(layoutNode, constraints) : false;
            if (relayoutNeeded && layoutNode.getLayoutPending$ui() && (layoutNode == this.root || ((parent$ui = layoutNode.getParent$ui()) != null && parent$ui.isPlaced() && layoutNode.isPlacedByParent()))) {
                if (layoutNode == this.root) {
                    layoutNode.place$ui(0, 0);
                } else {
                    layoutNode.replace$ui();
                }
                this.onPositionedDispatcher.onNodePositioned(layoutNode);
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
            }
            zM4888doLookaheadRemeasuresdFAvZA = zM4889doRemeasuresdFAvZA;
        }
        drainPostponedMeasureRequests();
        return zM4888doLookaheadRemeasuresdFAvZA;
    }

    public static /* synthetic */ boolean remeasureAndRelayoutIfNeeded$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return measureAndLayoutDelegate.remeasureAndRelayoutIfNeeded(layoutNode, z, z2);
    }

    private final void remeasureLookaheadRootsInSubtree(LayoutNode layoutNode) {
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode2 = layoutNodeArr[i];
            if (getRemeasureCanAffectParentSize(layoutNode2)) {
                if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNode2)) {
                    remeasureOnly(layoutNode2, true);
                } else {
                    remeasureLookaheadRootsInSubtree(layoutNode2);
                }
            }
        }
    }

    private final void remeasureOnly(LayoutNode layoutNode, boolean affectsLookahead) {
        Constraints constraints;
        if (layoutNode.getIsDeactivated()) {
            return;
        }
        if (layoutNode == this.root) {
            constraints = this.rootConstraints;
            constraints.getClass();
        } else {
            constraints = null;
        }
        if (affectsLookahead) {
            m4888doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
        } else {
            m4889doRemeasuresdFAvZA(layoutNode, constraints);
        }
    }

    public static /* synthetic */ boolean requestLookaheadRelayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, z);
    }

    public static /* synthetic */ boolean requestLookaheadRemeasure$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, z);
    }

    public static /* synthetic */ boolean requestRelayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestRelayout(layoutNode, z);
    }

    public static /* synthetic */ boolean requestRemeasure$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestRemeasure(layoutNode, z);
    }

    public final void dispatchOnPositionedCallbacks(boolean forceDispatch) {
        if (forceDispatch) {
            this.onPositionedDispatcher.onRootNodePositioned(this.root);
        }
        if (this.onPositionedDispatcher.isNotEmpty()) {
            this.onPositionedDispatcher.dispatch();
        }
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode, boolean affectsLookahead) {
        if (!this.duringMeasureLayout) {
            InlineClassHelperKt.throwIllegalStateException("forceMeasureTheSubtree should be executed during the measureAndLayout pass");
        }
        if (measurePending(layoutNode, affectsLookahead)) {
            InlineClassHelperKt.throwIllegalArgumentException("node not yet measured");
        }
        forceMeasureTheSubtreeInternal(layoutNode, affectsLookahead);
    }

    /* JADX INFO: renamed from: getDuringMeasureLayout$ui, reason: from getter */
    public final boolean getDuringMeasureLayout() {
        return this.duringMeasureLayout;
    }

    public final boolean getHasPendingMeasureOrLayout() {
        return this.relayoutNodes.isNotEmpty();
    }

    public final boolean getHasPendingOnPositionedCallbacks() {
        return this.onPositionedDispatcher.isNotEmpty();
    }

    public final long getMeasureIteration() {
        if (!this.duringMeasureLayout) {
            InlineClassHelperKt.throwIllegalArgumentException("measureIteration should be only used during the measure/layout pass");
        }
        return this.measureIteration;
    }

    /* JADX INFO: renamed from: getUncaughtExceptionHandler$ui, reason: from getter */
    public final RootForTest.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.uncaughtExceptionHandler;
    }

    public final boolean measureAndLayout(Function0<Unit> onLayout) {
        boolean z;
        LayoutNode layoutNodePop;
        boolean z2;
        boolean z3;
        if (!this.root.isAttached()) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        if (!this.root.isPlaced()) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        if (this.duringMeasureLayout) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        boolean z4 = false;
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            this.duringFullMeasureLayoutPass = true;
            try {
                if (this.relayoutNodes.isNotEmpty()) {
                    DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = this.relayoutNodes;
                    z = false;
                    while (true) {
                        try {
                            if (!depthSortedSetsForDifferentPasses.lookaheadAndAncestorMeasureSet.isEmpty()) {
                                layoutNodePop = depthSortedSetsForDifferentPasses.lookaheadAndAncestorMeasureSet.pop();
                                z3 = layoutNodePop.getLookaheadRoot() != null;
                                z2 = false;
                            } else if (!depthSortedSetsForDifferentPasses.lookaheadAndAncestorPlaceSet.isEmpty()) {
                                layoutNodePop = depthSortedSetsForDifferentPasses.lookaheadAndAncestorPlaceSet.pop();
                                z3 = layoutNodePop.getLookaheadRoot() != null;
                                z2 = true;
                            } else {
                                if (depthSortedSetsForDifferentPasses.approachSet.isEmpty()) {
                                    break;
                                }
                                layoutNodePop = depthSortedSetsForDifferentPasses.approachSet.pop();
                                z2 = true;
                                z3 = false;
                            }
                            boolean zRemeasureAndRelayoutIfNeeded = remeasureAndRelayoutIfNeeded(layoutNodePop, z3, z2);
                            if (!z2) {
                                if (layoutNodePop.getLookaheadLayoutPending$ui()) {
                                    this.relayoutNodes.add(layoutNodePop, Invalidation.LookaheadPlacement);
                                }
                                if (layoutNodePop.getLayoutPending$ui()) {
                                    this.relayoutNodes.add(layoutNodePop, Invalidation.Placement);
                                }
                            }
                            if (layoutNodePop == this.root && zRemeasureAndRelayoutIfNeeded) {
                                z = true;
                            }
                        } catch (Throwable th) {
                            th = th;
                            try {
                                RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
                                if (uncaughtExceptionHandler == null) {
                                    throw th;
                                }
                                uncaughtExceptionHandler.onUncaughtException(th);
                            } catch (Throwable th2) {
                                this.duringMeasureLayout = false;
                                this.duringFullMeasureLayoutPass = false;
                                throw th2;
                            }
                        }
                    }
                    if (onLayout != null) {
                        onLayout.invoke();
                    }
                } else {
                    z = false;
                }
            } catch (Throwable th3) {
                th = th3;
                z = false;
            }
            this.duringMeasureLayout = false;
            this.duringFullMeasureLayoutPass = false;
            z4 = z;
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
        callOnLayoutCompletedListeners();
        return z4;
    }

    /* JADX INFO: renamed from: measureAndLayout-0kLqBqw, reason: not valid java name */
    public final void m4890measureAndLayout0kLqBqw(LayoutNode layoutNode, long constraints) {
        if (layoutNode.getIsDeactivated()) {
            return;
        }
        if (Intrinsics.areEqual(layoutNode, this.root)) {
            InlineClassHelperKt.throwIllegalArgumentException("measureAndLayout called on root");
        }
        if (!this.root.isAttached()) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        if (!this.root.isPlaced()) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        if (this.duringMeasureLayout) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            this.duringFullMeasureLayoutPass = false;
            try {
                this.relayoutNodes.remove(layoutNode);
                if (m4888doLookaheadRemeasuresdFAvZA(layoutNode, Constraints.m5962boximpl(constraints)) || layoutNode.getLookaheadLayoutPending$ui()) {
                    if (Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE)) {
                        layoutNode.lookaheadReplace$ui();
                    }
                }
                ensureSubtreeLookaheadReplaced(layoutNode);
                m4889doRemeasuresdFAvZA(layoutNode, Constraints.m5962boximpl(constraints));
                if (layoutNode.getLayoutPending$ui() && layoutNode.isPlaced()) {
                    layoutNode.replace$ui();
                    this.onPositionedDispatcher.onNodePositioned(layoutNode);
                }
                drainPostponedMeasureRequests();
            } catch (Throwable th) {
                try {
                    RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
                    if (uncaughtExceptionHandler == null) {
                        throw th;
                    }
                    uncaughtExceptionHandler.onUncaughtException(th);
                } catch (Throwable th2) {
                    this.duringMeasureLayout = false;
                    this.duringFullMeasureLayoutPass = false;
                    throw th2;
                }
            }
            this.duringMeasureLayout = false;
            this.duringFullMeasureLayoutPass = false;
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
        callOnLayoutCompletedListeners();
    }

    public final void measureOnly() {
        if (this.relayoutNodes.isNotEmpty()) {
            if (!this.root.isAttached()) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
            }
            if (!this.root.isPlaced()) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
            }
            if (this.duringMeasureLayout) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
            }
            if (this.rootConstraints != null) {
                this.duringMeasureLayout = true;
                this.duringFullMeasureLayoutPass = false;
                try {
                    if (this.relayoutNodes.getAffectsLookaheadMeasure()) {
                        LayoutNode lookaheadRoot = this.root.getLookaheadRoot();
                        LayoutNode layoutNode = this.root;
                        if (lookaheadRoot != null) {
                            remeasureOnly(layoutNode, true);
                        } else {
                            remeasureLookaheadRootsInSubtree(layoutNode);
                        }
                    }
                    remeasureOnly(this.root, false);
                } catch (Throwable th) {
                    try {
                        RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
                        if (uncaughtExceptionHandler == null) {
                            throw th;
                        }
                        uncaughtExceptionHandler.onUncaughtException(th);
                    } catch (Throwable th2) {
                        this.duringMeasureLayout = false;
                        this.duringFullMeasureLayoutPass = false;
                        throw th2;
                    }
                }
                this.duringMeasureLayout = false;
                this.duringFullMeasureLayoutPass = false;
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
            }
        }
    }

    public final void onNodeDetached(LayoutNode node) {
        this.relayoutNodes.remove(node);
        this.onPositionedDispatcher.remove(node);
    }

    public final void registerOnLayoutCompletedListener(Owner.OnLayoutCompletedListener listener) {
        this.onLayoutCompletedListeners.add(listener);
    }

    public final boolean requestLookaheadRelayout(LayoutNode layoutNode, boolean forced) {
        int i = WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4 && i != 5) {
                        bu8.a();
                        return false;
                    }
                }
            }
            if ((layoutNode.getLookaheadMeasurePending$ui() || layoutNode.getLookaheadLayoutPending$ui()) && !forced) {
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
                return false;
            }
            layoutNode.markLookaheadLayoutPending$ui();
            layoutNode.markLayoutPending$ui();
            if (layoutNode.getIsDeactivated()) {
                return false;
            }
            LayoutNode parent$ui = layoutNode.getParent$ui();
            if (Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE) && ((parent$ui == null || !parent$ui.getLookaheadMeasurePending$ui()) && (parent$ui == null || !parent$ui.getLookaheadLayoutPending$ui()))) {
                this.relayoutNodes.add(layoutNode, Invalidation.LookaheadPlacement);
            } else if (layoutNode.isPlaced() && ((parent$ui == null || !parent$ui.getLayoutPending$ui()) && (parent$ui == null || !parent$ui.getMeasurePending$ui()))) {
                this.relayoutNodes.add(layoutNode, Invalidation.Placement);
            }
            return !this.duringFullMeasureLayoutPass;
        }
        LayoutTreeConsistencyChecker layoutTreeConsistencyChecker2 = this.consistencyChecker;
        if (layoutTreeConsistencyChecker2 != null) {
            layoutTreeConsistencyChecker2.assertConsistent();
        }
        return false;
    }

    public final boolean requestLookaheadRemeasure(LayoutNode layoutNode, boolean forced) {
        LayoutNode parent$ui;
        LayoutNode parent$ui2;
        if (!(layoutNode.getLookaheadRoot() != null)) {
            InlineClassHelperKt.throwIllegalStateException("Error: requestLookaheadRemeasure cannot be called on a node outside LookaheadScope");
        }
        int i = WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui().ordinal()];
        if (i != 1) {
            if (i != 2 && i != 3 && i != 4) {
                if (i != 5) {
                    bu8.a();
                    return false;
                }
                if (layoutNode.getLookaheadMeasurePending$ui() && !forced) {
                    return false;
                }
                layoutNode.markLookaheadMeasurePending$ui();
                layoutNode.markMeasurePending$ui();
                if (layoutNode.getIsDeactivated()) {
                    return false;
                }
                if ((Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE) || getCanAffectParentInLookahead(layoutNode)) && ((parent$ui = layoutNode.getParent$ui()) == null || !parent$ui.getLookaheadMeasurePending$ui())) {
                    this.relayoutNodes.add(layoutNode, Invalidation.LookaheadMeasurement);
                } else if ((layoutNode.isPlaced() || getCanAffectPlacedParent(layoutNode)) && ((parent$ui2 = layoutNode.getParent$ui()) == null || !parent$ui2.getMeasurePending$ui())) {
                    this.relayoutNodes.add(layoutNode, Invalidation.Measurement);
                }
                return !this.duringFullMeasureLayoutPass;
            }
            this.postponedMeasureRequests.add(new PostponedRequest(layoutNode, true, forced));
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
        return false;
    }

    public final void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.onPositionedDispatcher.onNodePositioned(layoutNode);
    }

    public final boolean requestRelayout(LayoutNode layoutNode, boolean forced) {
        int i = WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui().ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
            return false;
        }
        if (i != 5) {
            bu8.a();
            return false;
        }
        LayoutNode parent$ui = layoutNode.getParent$ui();
        boolean z = parent$ui == null || parent$ui.isPlaced();
        if (!forced && (layoutNode.getMeasurePending$ui() || (layoutNode.getLayoutPending$ui() && layoutNode.isPlaced() == z && layoutNode.isPlaced() == layoutNode.isPlacedByParent()))) {
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker2 = this.consistencyChecker;
            if (layoutTreeConsistencyChecker2 != null) {
                layoutTreeConsistencyChecker2.assertConsistent();
            }
            return false;
        }
        layoutNode.markLayoutPending$ui();
        if (!layoutNode.getIsDeactivated() && layoutNode.isPlacedByParent() && z) {
            if ((parent$ui == null || !parent$ui.getLayoutPending$ui()) && (parent$ui == null || !parent$ui.getMeasurePending$ui())) {
                this.relayoutNodes.add(layoutNode, Invalidation.Placement);
            }
            if (!this.duringFullMeasureLayoutPass) {
                return true;
            }
        }
        return false;
    }

    public final boolean requestRemeasure(LayoutNode layoutNode, boolean forced) {
        int i = WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui().ordinal()];
        if (i != 1 && i != 2) {
            if (i != 3 && i != 4) {
                if (i != 5) {
                    bu8.a();
                    return false;
                }
                if (layoutNode.getMeasurePending$ui() && !forced) {
                    return false;
                }
                layoutNode.markMeasurePending$ui();
                if (layoutNode.getIsDeactivated()) {
                    return false;
                }
                if (!layoutNode.isPlaced() && !getCanAffectPlacedParent(layoutNode)) {
                    return false;
                }
                LayoutNode parent$ui = layoutNode.getParent$ui();
                if (parent$ui == null || !parent$ui.getMeasurePending$ui()) {
                    this.relayoutNodes.add(layoutNode, Invalidation.Measurement);
                }
                return !this.duringFullMeasureLayoutPass;
            }
            this.postponedMeasureRequests.add(new PostponedRequest(layoutNode, false, forced));
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
        return false;
    }

    public final void setDuringMeasureLayout$ui(boolean z) {
        this.duringMeasureLayout = z;
    }

    public final void setUncaughtExceptionHandler$ui(RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    /* JADX INFO: renamed from: updateRootConstraints-BRTryo0, reason: not valid java name */
    public final void m4891updateRootConstraintsBRTryo0(long constraints) {
        Constraints constraints2 = this.rootConstraints;
        if (constraints2 == null ? false : Constraints.m5968equalsimpl0(constraints2.getValue(), constraints)) {
            return;
        }
        if (this.duringMeasureLayout) {
            InlineClassHelperKt.throwIllegalArgumentException("updateRootConstraints called while measuring");
        }
        this.rootConstraints = Constraints.m5962boximpl(constraints);
        if (this.root.getLookaheadRoot() != null) {
            this.root.markLookaheadMeasurePending$ui();
        }
        this.root.markMeasurePending$ui();
        DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = this.relayoutNodes;
        LayoutNode layoutNode = this.root;
        depthSortedSetsForDifferentPasses.add(layoutNode, layoutNode.getLookaheadRoot() != null ? Invalidation.LookaheadMeasurement : Invalidation.Measurement);
    }
}
