package androidx.compose.ui.spatial;

import android.os.Trace;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_androidKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.MatrixKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeKt;
import androidx.compose.ui.node.MeasurePassDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0019\u001a\u00020\u0010J5\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\u0006\u0010%\u001a\u00020\u0010J\u000e\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0012J\u0006\u0010(\u001a\u00020\u0010J\u0016\u0010)\u001a\u0004\u0018\u00010\u00012\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ:\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u0002012\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u001002J:\u00104\u001a\u00020,2\u0006\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u0002012\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u001002J\u0010\u00105\u001a\u00020\u00102\b\u00106\u001a\u0004\u0018\u00010\u0001J\u000e\u00107\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u0004J\u001e\u00109\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u00122\u0006\u0010;\u001a\u00020\u0012J\u000e\u0010<\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u0004J\u0018\u0010=\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u00042\b\b\u0002\u0010>\u001a\u00020\u0012J\u0015\u0010?\u001a\u00020\u001c2\u0006\u00108\u001a\u00020\u0004¢\u0006\u0004\b@\u0010AJ\f\u0010B\u001a\u00020\u0010*\u00020\u0004H\u0002J\u0010\u0010C\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u0004H\u0002J\u0010\u0010F\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u0004H\u0002J\u0014\u0010G\u001a\u00020\u0010*\u00020H2\u0006\u0010I\u001a\u00020EH\u0002J\f\u0010J\u001a\u00020\u0012*\u00020HH\u0002J\u0013\u0010K\u001a\u00020\u001c*\u00020\u0004H\u0002¢\u0006\u0004\bL\u0010AJ\u000e\u0010M\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u0004J\u001d\u0010N\u001a\u00020\u00122\u0006\u0010O\u001a\u00020!2\u0006\u0010P\u001a\u00020!H\u0000¢\u0006\u0002\bQJ7\u0010R\u001a\u0004\u0018\u00010S2\u0006\u0010T\u001a\u00020!2\u0006\u0010U\u001a\u00020!2\u0006\u0010V\u001a\u00020!2\u0006\u0010W\u001a\u00020!2\u0006\u0010X\u001a\u00020!H\u0000¢\u0006\u0002\bYJ1\u0010Z\u001a\u00020\u0012*\u0002012\u0006\u0010T\u001a\u00020!2\u0006\u0010U\u001a\u00020!2\u0006\u0010V\u001a\u00020!2\u0006\u0010W\u001a\u00020!H\u0000¢\u0006\u0002\b[J\u0019\u0010\\\u001a\u00020\u0012*\u00020\u00042\u0006\u0010]\u001a\u00020\u0004H\u0000¢\u0006\u0002\b^J\u000e\u0010_\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u0004R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"Landroidx/compose/ui/spatial/RectManager;", "", "layoutNodes", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/collection/IntObjectMap;)V", "rects", "Landroidx/compose/ui/spatial/RectList;", "getRects", "()Landroidx/compose/ui/spatial/RectList;", "throttledCallbacks", "Landroidx/compose/ui/spatial/ThrottledCallbacks;", "callbacks", "Landroidx/collection/MutableObjectList;", "Lkotlin/Function0;", "", "isDirty", "", "isScreenOrWindowDirty", "isFragmented", "dispatchToken", "scheduledDispatchDeadline", "", "dispatchLambda", "invalidate", "updateOffsets", "screenOffset", "Landroidx/compose/ui/unit/IntOffset;", "windowOffset", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "windowWidth", "", "windowHeight", "updateOffsets-gTq6Wqs", "(JJ[FII)V", "dispatchCallbacks", "scheduleDebounceCallback", "ensureSomethingScheduled", "removeScheduledCallback", "registerOnChangedCallback", "callback", "registerOnRectChangedCallback", "Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "id", "throttleMillis", "debounceMillis", "node", "Landroidx/compose/ui/node/DelegatableNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "registerOnGlobalLayoutCallback", "unregisterOnChangedCallback", "token", "invalidateCallbacksFor", "layoutNode", "updateFlagsFor", "focusable", "gesturable", "onLayoutLayerPositionalPropertiesChanged", "onLayoutPositionChanged", "forceUpdate", "getOffsetFromRectListFor", "getOffsetFromRectListFor-Bjo55l4", "(Landroidx/compose/ui/node/LayoutNode;)J", "resetHasPositionalLayerTransformationsForSubtreeIfNeeded", "insertOrUpdateTransformedNodeSubhierarchy", "cachedRect", "Landroidx/compose/ui/geometry/MutableRect;", "insertOrUpdateTransformedNode", "boundingRectInRoot", "Landroidx/compose/ui/node/NodeCoordinator;", "rect", "hasPositionalLayerTransformations", "outerToInnerOffset", "outerToInnerOffset-Bjo55l4", "remove", "isTargetDrawnFirst", "targetId", "otherId", "isTargetDrawnFirst$ui", "findFocusableNodeFromRect", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "left", "top", "right", "bottom", "containerId", "findFocusableNodeFromRect$ui", "intersects", "intersects$ui", "isDescendantOf", "container", "isDescendantOf$ui", "unsetHasCallbacksFor", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RectManager {
    public static final int $stable = 8;
    private final MutableRect cachedRect;
    private final MutableObjectList<Function0<Unit>> callbacks;
    private final Function0<Unit> dispatchLambda;
    private Object dispatchToken;
    private boolean isDirty;
    private boolean isFragmented;
    private boolean isScreenOrWindowDirty;
    private final IntObjectMap<LayoutNode> layoutNodes;
    private final RectList rects;
    private long scheduledDispatchDeadline;
    private final ThrottledCallbacks throttledCallbacks;

    public RectManager(IntObjectMap<LayoutNode> intObjectMap) {
        this.layoutNodes = intObjectMap;
        this.rects = new RectList();
        this.throttledCallbacks = new ThrottledCallbacks();
        this.callbacks = new MutableObjectList<>(0, 1, (DefaultConstructorMarker) null);
        this.scheduledDispatchDeadline = -1L;
        this.dispatchLambda = new Function0<Unit>() { // from class: androidx.compose.ui.spatial.RectManager$dispatchLambda$1
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m5273invoke() {
                this.this$0.dispatchToken = null;
                RectManager rectManager = this.this$0;
                Trace.beginSection("OnPositionedDispatch");
                try {
                    rectManager.dispatchCallbacks();
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.endSection();
                }
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m5273invoke();
                return Unit.INSTANCE;
            }
        };
        this.cachedRect = new MutableRect(0.0f, 0.0f, 0.0f, 0.0f);
    }

    private final void boundingRectInRoot(NodeCoordinator nodeCoordinator, MutableRect mutableRect) {
        while (nodeCoordinator != null) {
            LayoutNode layoutNode = nodeCoordinator.getLayoutNode();
            if (nodeCoordinator == layoutNode.getOuterCoordinator$ui() && !layoutNode.getHasPositionalLayerTransformationsInOffsetFromRoot()) {
                long jM5271getOffsetFromRectListForBjo55l4 = m5271getOffsetFromRectListForBjo55l4(layoutNode);
                if (!IntOffset.m6149equalsimpl0(jM5271getOffsetFromRectListForBjo55l4, IntOffset.INSTANCE.m6160getMaxnOccac())) {
                    float fM6150getXimpl = IntOffset.m6150getXimpl(jM5271getOffsetFromRectListForBjo55l4);
                    mutableRect.m2874translatek4lQ0M(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(IntOffset.m6151getYimpl(jM5271getOffsetFromRectListForBjo55l4))) & 4294967295L) | (Float.floatToRawIntBits(fM6150getXimpl) << 32)));
                    return;
                }
            }
            OwnedLayer layer = nodeCoordinator.getLayer();
            if (layer != null) {
                float[] fArrMo5006getUnderlyingMatrixsQKQjiQ = layer.mo5006getUnderlyingMatrixsQKQjiQ();
                if (!MatrixKt.m3403isIdentity58bKbWc(fArrMo5006getUnderlyingMatrixsQKQjiQ)) {
                    Matrix.m3386mapimpl(fArrMo5006getUnderlyingMatrixsQKQjiQ, mutableRect);
                }
            }
            long jMo4867getPositionnOccac = nodeCoordinator.getPosition();
            float fM6150getXimpl2 = IntOffset.m6150getXimpl(jMo4867getPositionnOccac);
            mutableRect.m2874translatek4lQ0M(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(IntOffset.m6151getYimpl(jMo4867getPositionnOccac))) & 4294967295L) | (Float.floatToRawIntBits(fM6150getXimpl2) << 32)));
            nodeCoordinator = nodeCoordinator.getWrappedBy();
        }
    }

    private final boolean hasPositionalLayerTransformations(NodeCoordinator nodeCoordinator) {
        OwnedLayer layer = nodeCoordinator.getLayer();
        return (layer == null || MatrixKt.m3403isIdentity58bKbWc(layer.mo5006getUnderlyingMatrixsQKQjiQ())) ? false : true;
    }

    private final void insertOrUpdateTransformedNode(LayoutNode layoutNode) {
        layoutNode.setHasPositionalLayerTransformationsInOffsetFromRoot$ui(true);
        layoutNode.m4848setLastOffsetFromParentgyyYBs$ui(IntOffset.INSTANCE.m6160getMaxnOccac());
        NodeCoordinator outerCoordinator$ui = layoutNode.getOuterCoordinator$ui();
        MeasurePassDelegate measurePassDelegate$ui = layoutNode.getMeasurePassDelegate$ui();
        int measuredWidth = measurePassDelegate$ui.getMeasuredWidth();
        int measuredHeight = measurePassDelegate$ui.getMeasuredHeight();
        MutableRect mutableRect = this.cachedRect;
        mutableRect.set(0.0f, 0.0f, measuredWidth, measuredHeight);
        boundingRectInRoot(outerCoordinator$ui, mutableRect);
        int left = (int) mutableRect.getLeft();
        int top = (int) mutableRect.getTop();
        int right = (int) mutableRect.getRight();
        int bottom = (int) mutableRect.getBottom();
        int semanticsId = layoutNode.getSemanticsId();
        boolean addedToRectList = layoutNode.getAddedToRectList();
        layoutNode.setAddedToRectList$ui(true);
        if (!addedToRectList || !this.rects.update(semanticsId, left, top, right, bottom)) {
            LayoutNode parent$ui = layoutNode.getParent$ui();
            RectList.insert$default(this.rects, semanticsId, left, top, right, bottom, parent$ui != null ? parent$ui.getSemanticsId() : -1, layoutNode.getNodes().m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(1024)), layoutNode.getNodes().m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(16)), this.throttledCallbacks.getRectChangedMap().containsKey(semanticsId), 0, 512, null);
        }
        invalidate();
    }

    private final void insertOrUpdateTransformedNodeSubhierarchy(LayoutNode layoutNode) {
        insertOrUpdateTransformedNode(layoutNode);
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode2 = layoutNodeArr[i];
            if (layoutNode2.isPlaced()) {
                insertOrUpdateTransformedNodeSubhierarchy(layoutNode2);
            }
        }
    }

    public static /* synthetic */ void onLayoutPositionChanged$default(RectManager rectManager, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        rectManager.onLayoutPositionChanged(layoutNode, z);
    }

    /* JADX INFO: renamed from: outerToInnerOffset-Bjo55l4, reason: not valid java name */
    private final long m5270outerToInnerOffsetBjo55l4(LayoutNode layoutNode) {
        NodeCoordinator outerCoordinator$ui = layoutNode.getOuterCoordinator$ui();
        long jM6161getZeronOccac = IntOffset.INSTANCE.m6161getZeronOccac();
        for (NodeCoordinator innerCoordinator$ui = layoutNode.getInnerCoordinator$ui(); innerCoordinator$ui != null && innerCoordinator$ui != outerCoordinator$ui; innerCoordinator$ui = innerCoordinator$ui.getWrappedBy()) {
            if (hasPositionalLayerTransformations(innerCoordinator$ui)) {
                return IntOffset.INSTANCE.m6160getMaxnOccac();
            }
            jM6161getZeronOccac = IntOffset.m6154plusqkQi6aY(jM6161getZeronOccac, innerCoordinator$ui.getPosition());
        }
        return jM6161getZeronOccac;
    }

    private final void resetHasPositionalLayerTransformationsForSubtreeIfNeeded(LayoutNode layoutNode) {
        if (!layoutNode.getHasPositionalLayerTransformationsInOffsetFromRoot() || hasPositionalLayerTransformations(layoutNode.getOuterCoordinator$ui())) {
            return;
        }
        layoutNode.setHasPositionalLayerTransformationsInOffsetFromRoot$ui(false);
        if (layoutNode.getOuterToInnerOffsetDirty()) {
            layoutNode.m4850setOuterToInnerOffsetgyyYBs$ui(m5270outerToInnerOffsetBjo55l4(layoutNode));
            layoutNode.setOuterToInnerOffsetDirty$ui(false);
        }
        if (IntOffset.m6149equalsimpl0(layoutNode.getOuterToInnerOffset(), IntOffset.INSTANCE.m6160getMaxnOccac())) {
            return;
        }
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            resetHasPositionalLayerTransformationsForSubtreeIfNeeded(layoutNodeArr[i]);
        }
    }

    public final void dispatchCallbacks() {
        removeScheduledCallback();
        long jCurrentTimeMillis = Actual_androidKt.currentTimeMillis();
        boolean z = this.isDirty;
        boolean z2 = z || this.isScreenOrWindowDirty;
        if (z) {
            this.isDirty = false;
            MutableObjectList<Function0<Unit>> mutableObjectList = this.callbacks;
            Object[] objArr = ((ObjectList) mutableObjectList).content;
            int i = ((ObjectList) mutableObjectList)._size;
            for (int i2 = 0; i2 < i; i2++) {
                ((Function0) objArr[i2]).invoke();
            }
            RectList rectList = this.rects;
            long[] jArr = rectList.items;
            int i3 = rectList.itemsSize;
            for (int i4 = 0; i4 < jArr.length - 2 && i4 < i3; i4 += 3) {
                long j = jArr[i4 + 2];
                if ((((int) (j >> 60)) & 1) != 0) {
                    this.throttledCallbacks.fireOnUpdatedRect(33554431 & ((int) j), jArr[i4], jArr[i4 + 1], jCurrentTimeMillis);
                }
            }
            this.rects.clearUpdated();
        }
        if (this.isScreenOrWindowDirty) {
            this.isScreenOrWindowDirty = false;
            this.throttledCallbacks.fireOnRectChangedEntries(jCurrentTimeMillis);
        }
        if (z2) {
            this.throttledCallbacks.fireGlobalChangeEntries(jCurrentTimeMillis);
        }
        if (this.isFragmented) {
            this.isFragmented = false;
            this.rects.defragment();
        }
        this.throttledCallbacks.triggerDebounced(jCurrentTimeMillis);
        if (this.throttledCallbacks.getMinDebounceDeadline() > 0) {
            scheduleDebounceCallback(true);
        }
    }

    /* JADX WARN: Code duplicated, block: B:78:0x01a4  */
    public final FocusTargetModifierNode findFocusableNodeFromRect$ui(int left, int top, int right, int bottom, int containerId) {
        int i;
        LayoutNode layoutNode;
        byte b;
        Modifier.Node nodePop;
        int i2;
        LayoutNode layoutNode2;
        boolean z;
        int i3;
        SemanticsInfo semanticsInfoRequireSemanticsInfo;
        RectManager rectManager = this;
        LayoutNode layoutNode3 = (LayoutNode) rectManager.layoutNodes.get(containerId);
        if (layoutNode3 == null) {
            return null;
        }
        FocusTargetNode activeFocusTargetNode = LayoutNodeKt.requireOwner(layoutNode3).getFocusOwner().getActiveFocusTargetNode();
        int semanticsId = (activeFocusTargetNode == null || (semanticsInfoRequireSemanticsInfo = DelegatableNodeKt.requireSemanticsInfo(activeFocusTargetNode)) == null) ? -1 : semanticsInfoRequireSemanticsInfo.getSemanticsId();
        RectList rectList = rectManager.rects;
        int i4 = top;
        long j = (((long) i4) & 4294967295L) | (((long) left) << 32);
        long j2 = (((long) bottom) & 4294967295L) | (((long) right) << 32);
        long[] jArr = rectList.items;
        int i5 = rectList.itemsSize;
        int depth = Integer.MAX_VALUE;
        FocusTargetNode focusTargetNode = null;
        int i6 = 0;
        while (i6 < jArr.length - 2 && i6 < i5) {
            int i7 = i6;
            long j3 = jArr[i6 + 2];
            boolean z2 = true;
            if ((((int) (j3 >> 61)) & 1) != 0) {
                if (((((j2 - jArr[i7]) - InlineClassHelperKt.Uint64Low32) | ((jArr[i7 + 1] - j) - InlineClassHelperKt.Uint64Low32)) & (-9223372034707292160L)) == 0) {
                    int i8 = ((int) j3) & 33554431;
                    LayoutNode layoutNode4 = (LayoutNode) rectManager.layoutNodes.get(i8);
                    if (layoutNode4 != null) {
                        if (semanticsId == i8) {
                            b = -1;
                            if (semanticsId != -1) {
                                return null;
                            }
                        } else {
                            b = -1;
                        }
                        if (layoutNode4.getDepth() >= depth || !rectManager.isDescendantOf$ui(layoutNode4, layoutNode3)) {
                            i = depth;
                            layoutNode = layoutNode3;
                        } else {
                            NodeChain nodes = layoutNode4.getNodes();
                            int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
                            if ((nodes.getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                                Modifier.Node head = nodes.getHead();
                                while (true) {
                                    if (head != null) {
                                        if ((head.getKindSet() & iM4949constructorimpl) != 0) {
                                            MutableVector mutableVector = null;
                                            nodePop = head;
                                            while (true) {
                                                if (nodePop != null) {
                                                    if (nodePop instanceof FocusTargetNode) {
                                                        i = depth;
                                                        layoutNode = layoutNode3;
                                                    } else {
                                                        if ((nodePop.getKindSet() & iM4949constructorimpl) == 0 || !(nodePop instanceof DelegatingNode)) {
                                                            i2 = depth;
                                                            layoutNode2 = layoutNode3;
                                                            z = true;
                                                        } else {
                                                            Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                                                            int i9 = 0;
                                                            while (delegate$ui != null) {
                                                                if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                                                    i9++;
                                                                    i3 = depth;
                                                                    if (i9 == 1) {
                                                                        nodePop = delegate$ui;
                                                                    } else {
                                                                        MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                                        if (nodePop != null) {
                                                                            mutableVector2.add(nodePop);
                                                                            nodePop = null;
                                                                        }
                                                                        mutableVector2.add(delegate$ui);
                                                                        mutableVector = mutableVector2;
                                                                        i9 = i9;
                                                                    }
                                                                    delegate$ui = delegate$ui.getChild();
                                                                    depth = i3;
                                                                    layoutNode3 = layoutNode3;
                                                                } else {
                                                                    i3 = depth;
                                                                }
                                                                layoutNode3 = layoutNode3;
                                                                delegate$ui = delegate$ui.getChild();
                                                                depth = i3;
                                                                layoutNode3 = layoutNode3;
                                                            }
                                                            i2 = depth;
                                                            layoutNode2 = layoutNode3;
                                                            z = true;
                                                            if (i9 == 1) {
                                                            }
                                                            z2 = z;
                                                            depth = i2;
                                                            layoutNode3 = layoutNode2;
                                                        }
                                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                                        z2 = z;
                                                        depth = i2;
                                                        layoutNode3 = layoutNode2;
                                                    }
                                                }
                                            }
                                        }
                                        i = depth;
                                        boolean z3 = z2;
                                        layoutNode = layoutNode3;
                                        if ((head.getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                                            head = head.getChild();
                                            z2 = z3;
                                            depth = i;
                                            layoutNode3 = layoutNode;
                                        } else {
                                            nodePop = null;
                                        }
                                    } else {
                                        i = depth;
                                        layoutNode = layoutNode3;
                                        nodePop = null;
                                    }
                                }
                            } else {
                                i = depth;
                                layoutNode = layoutNode3;
                                nodePop = null;
                            }
                            FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
                            if (focusTargetNode2 != null) {
                                if (intersects$ui(focusTargetNode2, left, i4, right, bottom)) {
                                    focusTargetNode = focusTargetNode2;
                                    depth = layoutNode4.getDepth();
                                }
                            }
                        }
                        depth = i;
                    } else {
                        i = depth;
                        layoutNode = layoutNode3;
                    }
                } else {
                    i = depth;
                    layoutNode = layoutNode3;
                }
                depth = i;
            } else {
                i = depth;
                layoutNode = layoutNode3;
                depth = i;
            }
            i6 = i7 + 3;
            rectManager = this;
            i4 = top;
            layoutNode3 = layoutNode;
        }
        return focusTargetNode;
    }

    /* JADX INFO: renamed from: getOffsetFromRectListFor-Bjo55l4, reason: not valid java name */
    public final long m5271getOffsetFromRectListForBjo55l4(LayoutNode layoutNode) {
        long topLeft = this.rects.getTopLeft(layoutNode.getSemanticsId());
        if (topLeft == Long.MAX_VALUE) {
            return IntOffset.INSTANCE.m6160getMaxnOccac();
        }
        int i = (int) (topLeft >> 32);
        return IntOffset.m6144constructorimpl((((long) ((int) topLeft)) & 4294967295L) | (((long) i) << 32));
    }

    public final RectList getRects() {
        return this.rects;
    }

    public final boolean intersects$ui(DelegatableNode delegatableNode, int i, int i2, int i3, int i4) {
        NodeCoordinator nodeCoordinatorM4787requireCoordinator64DMado = DelegatableNodeKt.m4787requireCoordinator64DMado(delegatableNode, NodeKind.m4949constructorimpl(1024));
        LayoutNode layoutNode = nodeCoordinatorM4787requireCoordinator64DMado.getLayoutNode();
        if (Intrinsics.areEqual(nodeCoordinatorM4787requireCoordinator64DMado, layoutNode.getOuterCoordinator$ui())) {
            return true;
        }
        long jMo4616localToRootMKHz9U = layoutNode.getOuterCoordinator$ui().mo4616localToRootMKHz9U(LayoutCoordinates.m4612localPositionOfS_NoaFU$default(layoutNode.getOuterCoordinator$ui(), nodeCoordinatorM4787requireCoordinator64DMado, 0L, false, 6, null));
        long jMo4613getSizeYbymL2g = nodeCoordinatorM4787requireCoordinator64DMado.mo4613getSizeYbymL2g();
        int iRound = Math.round(Float.intBitsToFloat((int) (jMo4616localToRootMKHz9U >> 32)));
        int i5 = ((int) (jMo4613getSizeYbymL2g >> 32)) + iRound;
        int iRound2 = Math.round(Float.intBitsToFloat((int) (jMo4616localToRootMKHz9U & 4294967295L)));
        return i < i5 && i3 > iRound && i2 < ((int) (jMo4613getSizeYbymL2g & 4294967295L)) + iRound2 && i4 > iRound2;
    }

    public final void invalidate() {
        this.isDirty = true;
    }

    public final void invalidateCallbacksFor(LayoutNode layoutNode) {
        if (layoutNode.getAddedToRectList()) {
            this.isDirty = true;
            this.rects.markUpdated(layoutNode.getSemanticsId());
        }
        scheduleDebounceCallback(true);
    }

    public final boolean isDescendantOf$ui(LayoutNode layoutNode, LayoutNode layoutNode2) {
        int depth = layoutNode.getDepth() - layoutNode2.getDepth();
        if (depth <= 0) {
            return false;
        }
        for (int i = 0; i < depth; i++) {
            layoutNode = layoutNode.getParent$ui();
            if (layoutNode == null) {
                return false;
            }
        }
        return layoutNode == layoutNode2;
    }

    public final boolean isTargetDrawnFirst$ui(int targetId, int otherId) {
        LayoutNode parent$ui;
        LayoutNode parent$ui2;
        LayoutNode parent$ui3 = (LayoutNode) this.layoutNodes.get(targetId);
        if (parent$ui3 != null && (parent$ui = (LayoutNode) this.layoutNodes.get(otherId)) != null && parent$ui3.getDepth() != 0 && parent$ui.getDepth() != 0) {
            while (parent$ui3.getDepth() > parent$ui.getDepth()) {
                parent$ui3 = parent$ui3.getParent$ui();
                if (parent$ui3 == null) {
                    return false;
                }
            }
            if (parent$ui3 == parent$ui) {
                return false;
            }
            while (parent$ui.getDepth() > parent$ui3.getDepth()) {
                parent$ui = parent$ui.getParent$ui();
                if (parent$ui == null) {
                    return false;
                }
            }
            if (parent$ui3 == parent$ui) {
                return false;
            }
            LayoutNode layoutNode = parent$ui3;
            LayoutNode layoutNode2 = layoutNode;
            LayoutNode layoutNode3 = parent$ui;
            while (layoutNode != parent$ui) {
                LayoutNode parent$ui4 = layoutNode.getParent$ui();
                if (parent$ui4 == null || (parent$ui2 = parent$ui.getParent$ui()) == null) {
                    return false;
                }
                layoutNode3 = parent$ui;
                parent$ui = parent$ui2;
                layoutNode2 = layoutNode;
                layoutNode = parent$ui4;
            }
            if (layoutNode2.getMeasurePassDelegate$ui().getZIndex() == layoutNode3.getMeasurePassDelegate$ui().getZIndex()) {
                return layoutNode2.getPlaceOrder$ui() < layoutNode3.getPlaceOrder$ui();
            }
            if (layoutNode2.getMeasurePassDelegate$ui().getZIndex() < layoutNode3.getMeasurePassDelegate$ui().getZIndex()) {
                return true;
            }
        }
        return false;
    }

    public final void onLayoutLayerPositionalPropertiesChanged(LayoutNode layoutNode) {
        if (layoutNode.isPlaced()) {
            long jM5270outerToInnerOffsetBjo55l4 = m5270outerToInnerOffsetBjo55l4(layoutNode);
            if (!RectManagerKt.m5277isSetgyyYBs(jM5270outerToInnerOffsetBjo55l4)) {
                insertOrUpdateTransformedNodeSubhierarchy(layoutNode);
                return;
            }
            layoutNode.m4850setOuterToInnerOffsetgyyYBs$ui(jM5270outerToInnerOffsetBjo55l4);
            layoutNode.setOuterToInnerOffsetDirty$ui(false);
            MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
            LayoutNode[] layoutNodeArr = mutableVector.content;
            int size = mutableVector.getSize();
            for (int i = 0; i < size; i++) {
                onLayoutPositionChanged$default(this, layoutNodeArr[i], false, 2, null);
            }
            invalidateCallbacksFor(layoutNode);
        }
    }

    public final void onLayoutPositionChanged(LayoutNode layoutNode, boolean forceUpdate) {
        long jM6161getZeronOccac;
        long j;
        if (layoutNode.isPlaced()) {
            LayoutNode parent$ui = layoutNode.getParent$ui();
            if (parent$ui == null || parent$ui.getHasPositionalLayerTransformationsInOffsetFromRoot()) {
                jM6161getZeronOccac = parent$ui == null ? IntOffset.INSTANCE.m6161getZeronOccac() : IntOffset.INSTANCE.m6160getMaxnOccac();
            } else {
                if (parent$ui.getOuterToInnerOffsetDirty()) {
                    parent$ui.setOuterToInnerOffsetDirty$ui(false);
                    parent$ui.m4850setOuterToInnerOffsetgyyYBs$ui(m5270outerToInnerOffsetBjo55l4(parent$ui));
                }
                jM6161getZeronOccac = parent$ui.getOuterToInnerOffset();
            }
            NodeCoordinator outerCoordinator$ui = layoutNode.getOuterCoordinator$ui();
            if (!RectManagerKt.m5277isSetgyyYBs(jM6161getZeronOccac) || hasPositionalLayerTransformations(outerCoordinator$ui)) {
                insertOrUpdateTransformedNodeSubhierarchy(layoutNode);
                return;
            }
            if (layoutNode.getHasPositionalLayerTransformationsInOffsetFromRoot()) {
                insertOrUpdateTransformedNodeSubhierarchy(layoutNode);
                resetHasPositionalLayerTransformationsForSubtreeIfNeeded(layoutNode);
                return;
            }
            long jM6154plusqkQi6aY = IntOffset.m6154plusqkQi6aY(jM6161getZeronOccac, outerCoordinator$ui.getPosition());
            MeasurePassDelegate measurePassDelegate$ui = layoutNode.getMeasurePassDelegate$ui();
            int measuredWidth = measurePassDelegate$ui.getMeasuredWidth();
            int measuredHeight = measurePassDelegate$ui.getMeasuredHeight();
            long jM6188constructorimpl = IntSize.m6188constructorimpl((((long) measuredWidth) << 32) | (((long) measuredHeight) & 4294967295L));
            int semanticsId = layoutNode.getSemanticsId();
            if (layoutNode.getAddedToRectList()) {
                if (forceUpdate || !IntOffset.m6149equalsimpl0(jM6154plusqkQi6aY, layoutNode.getLastOffsetFromParent()) || !IntSize.m6191equalsimpl0(jM6188constructorimpl, layoutNode.getLastSize())) {
                    RectList rectList = this.rects;
                    if (parent$ui != null) {
                        rectList.moveBasedOnParentOffset(semanticsId, parent$ui.getSemanticsId(), IntOffset.m6150getXimpl(jM6154plusqkQi6aY), IntOffset.m6151getYimpl(jM6154plusqkQi6aY), measuredWidth, measuredHeight);
                    } else {
                        rectList.move(semanticsId, IntOffset.m6150getXimpl(jM6154plusqkQi6aY), IntOffset.m6151getYimpl(jM6154plusqkQi6aY), IntOffset.m6150getXimpl(jM6154plusqkQi6aY) + measuredWidth, IntOffset.m6151getYimpl(jM6154plusqkQi6aY) + measuredHeight);
                    }
                    invalidate();
                }
                j = jM6154plusqkQi6aY;
                jM6188constructorimpl = jM6188constructorimpl;
            } else {
                layoutNode.setAddedToRectList$ui(true);
                boolean zM4905hasH91voCI$ui = layoutNode.getNodes().m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(1024));
                boolean zM4905hasH91voCI$ui2 = layoutNode.getNodes().m4905hasH91voCI$ui(NodeKind.m4949constructorimpl(16));
                boolean zContainsKey = this.throttledCallbacks.getRectChangedMap().containsKey(semanticsId);
                RectList rectList2 = this.rects;
                if (parent$ui != null) {
                    j = jM6154plusqkQi6aY;
                    rectList2.insertBasedOnParentOffset(semanticsId, parent$ui.getSemanticsId(), IntOffset.m6150getXimpl(jM6154plusqkQi6aY), IntOffset.m6151getYimpl(jM6154plusqkQi6aY), measuredWidth, measuredHeight, zM4905hasH91voCI$ui, zM4905hasH91voCI$ui2, zContainsKey);
                } else {
                    j = jM6154plusqkQi6aY;
                    RectList.insert$default(rectList2, semanticsId, IntOffset.m6150getXimpl(j), IntOffset.m6151getYimpl(j), IntOffset.m6150getXimpl(j) + measuredWidth, IntOffset.m6151getYimpl(j) + measuredHeight, 0, zM4905hasH91voCI$ui, zM4905hasH91voCI$ui2, zContainsKey, 0, 544, null);
                }
                invalidate();
            }
            layoutNode.m4849setLastSizeozmzZPI$ui(jM6188constructorimpl);
            layoutNode.m4848setLastOffsetFromParentgyyYBs$ui(j);
        }
    }

    public final Object registerOnChangedCallback(Function0<Unit> callback) {
        this.callbacks.add(callback);
        return callback;
    }

    public final DelegatableNode.RegistrationHandle registerOnGlobalLayoutCallback(int id, long throttleMillis, long debounceMillis, DelegatableNode node, Function1<? super RelativeLayoutBounds, Unit> callback) {
        return this.throttledCallbacks.registerOnGlobalChange(id, throttleMillis, debounceMillis, node, callback);
    }

    public final DelegatableNode.RegistrationHandle registerOnRectChangedCallback(int id, long throttleMillis, long debounceMillis, DelegatableNode node, Function1<? super RelativeLayoutBounds, Unit> callback) {
        DelegatableNode.RegistrationHandle registrationHandleRegisterOnRectChanged = this.throttledCallbacks.registerOnRectChanged(id, throttleMillis, debounceMillis, node, callback);
        if (DelegatableNodeKt.requireLayoutNode(node.getNode()).getAddedToRectList()) {
            this.rects.updateHasCallbacks(id, true);
        }
        invalidate();
        scheduleDebounceCallback(true);
        return registrationHandleRegisterOnRectChanged;
    }

    public final void remove(LayoutNode layoutNode) {
        if (layoutNode.getAddedToRectList()) {
            this.rects.remove(layoutNode.getSemanticsId());
            layoutNode.setAddedToRectList$ui(false);
            invalidate();
            this.isFragmented = true;
        }
    }

    public final void removeScheduledCallback() {
        Object obj = this.dispatchToken;
        if (obj != null) {
            Actual_androidKt.removePost(obj);
            this.dispatchToken = null;
        }
    }

    public final void scheduleDebounceCallback(boolean ensureSomethingScheduled) {
        boolean z = (ensureSomethingScheduled && this.dispatchToken == null) ? false : true;
        long minDebounceDeadline = this.throttledCallbacks.getMinDebounceDeadline();
        if (minDebounceDeadline >= 0 || !z) {
            if (this.scheduledDispatchDeadline == minDebounceDeadline && z) {
                return;
            }
            Object obj = this.dispatchToken;
            if (obj != null) {
                Actual_androidKt.removePost(obj);
            }
            long jCurrentTimeMillis = Actual_androidKt.currentTimeMillis();
            long jMax = Math.max(minDebounceDeadline, 16 + jCurrentTimeMillis);
            this.scheduledDispatchDeadline = jMax;
            this.dispatchToken = Actual_androidKt.postDelayed(jMax - jCurrentTimeMillis, this.dispatchLambda);
        }
    }

    public final void unregisterOnChangedCallback(Object token) {
        if ((TypeIntrinsics.isFunctionOfArity(token, 0) ? (Function0) token : null) == null) {
            return;
        }
        this.callbacks.remove(token);
    }

    public final void unsetHasCallbacksFor(LayoutNode layoutNode) {
        this.rects.updateHasCallbacks(layoutNode.getSemanticsId(), false);
    }

    public final void updateFlagsFor(LayoutNode layoutNode, boolean focusable, boolean gesturable) {
        if (layoutNode.isAttached()) {
            this.rects.updateFlagsFor(layoutNode.getSemanticsId(), focusable, gesturable);
        }
    }

    /* JADX INFO: renamed from: updateOffsets-gTq6Wqs, reason: not valid java name */
    public final void m5272updateOffsetsgTq6Wqs(long screenOffset, long windowOffset, float[] viewToWindowMatrix, int windowWidth, int windowHeight) {
        int iM5276analyzeComponents58bKbWc = RectManagerKt.m5276analyzeComponents58bKbWc(viewToWindowMatrix);
        ThrottledCallbacks throttledCallbacks = this.throttledCallbacks;
        if ((iM5276analyzeComponents58bKbWc & 2) != 0) {
            viewToWindowMatrix = null;
        }
        this.isScreenOrWindowDirty = throttledCallbacks.m5290updateOffsetsLDcG7Xg(screenOffset, windowOffset, viewToWindowMatrix, windowWidth, windowHeight) || this.isScreenOrWindowDirty;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public RectManager() {
        IntObjectMap intObjectMap = null;
        this(intObjectMap, 1, intObjectMap);
    }

    public /* synthetic */ RectManager(IntObjectMap intObjectMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? IntObjectMapKt.intObjectMapOf() : intObjectMap);
    }
}
