package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b9\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002½\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001eJ\r\u0010\\\u001a\u00020\u001bH\u0000¢\u0006\u0002\b]J\u001d\u0010i\u001a\u00020\u001b2\u0012\u0010j\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001b0KH\u0082\bJ\b\u0010m\u001a\u00020\u001bH\u0016J\b\u0010p\u001a\u00020\u001bH\u0002J\u0015\u0010q\u001a\u00020\u001b2\u0006\u0010r\u001a\u00020\nH\u0000¢\u0006\u0002\bsJ\u0014\u0010t\u001a\u000e\u0012\u0004\u0012\u00020v\u0012\u0004\u0012\u00020!0uH\u0016J\u001c\u0010z\u001a\u00020\u001b2\u0012\u0010j\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001b0KH\u0016J\b\u0010{\u001a\u00020\u001bH\u0016J\b\u0010|\u001a\u00020\u001bH\u0016J\u0006\u0010}\u001a\u00020\u001bJ\u0019\u0010~\u001a\u00020\u00012\u0006\u0010\u007f\u001a\u00020AH\u0016¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J\u0012\u0010\u0082\u0001\u001a\u00020\u001b2\u0007\u0010\u0083\u0001\u001a\u00020\u0017H\u0002J\u001a\u0010\u008d\u0001\u001a\u00020\u001b2\u0006\u0010\u007f\u001a\u00020AH\u0000¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J\u0018\u0010\u0090\u0001\u001a\u00020\n2\u0006\u0010\u007f\u001a\u00020A¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J@\u0010\u0093\u0001\u001a\u00020\u001b2\u0007\u0010\u0094\u0001\u001a\u00020F2\u0007\u0010\u0095\u0001\u001a\u00020I2\u001a\u0010\u0096\u0001\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u001b\u0018\u00010K¢\u0006\u0002\bMH\u0014¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J-\u0010\u0093\u0001\u001a\u00020\u001b2\u0007\u0010\u0094\u0001\u001a\u00020F2\u0007\u0010\u0095\u0001\u001a\u00020I2\u0007\u0010\u0099\u0001\u001a\u00020OH\u0014¢\u0006\u0006\b\u0097\u0001\u0010\u009a\u0001J\u0012\u0010\u009d\u0001\u001a\u00020\u001b2\u0007\u0010\u009e\u0001\u001a\u00020\nH\u0016JK\u0010 \u0001\u001a\u00020\u001b2\u0007\u0010\u0094\u0001\u001a\u00020F2\u0007\u0010\u0095\u0001\u001a\u00020I2\u001a\u0010\u0096\u0001\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u001b\u0018\u00010K¢\u0006\u0002\bM2\t\u0010\u0099\u0001\u001a\u0004\u0018\u00010OH\u0002¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\u0013\u0010§\u0001\u001a\u00020!2\u0007\u0010¨\u0001\u001a\u00020vH\u0096\u0002J\u0012\u0010©\u0001\u001a\u00020!2\u0007\u0010ª\u0001\u001a\u00020!H\u0016J\u0012\u0010«\u0001\u001a\u00020!2\u0007\u0010ª\u0001\u001a\u00020!H\u0016J\u0012\u0010¬\u0001\u001a\u00020!2\u0007\u0010\u00ad\u0001\u001a\u00020!H\u0016J\u0012\u0010®\u0001\u001a\u00020!2\u0007\u0010\u00ad\u0001\u001a\u00020!H\u0016J\t\u0010¯\u0001\u001a\u00020\u001bH\u0002J\u0010\u0010°\u0001\u001a\u00020\u001b2\u0007\u0010±\u0001\u001a\u00020\nJ\u0007\u0010²\u0001\u001a\u00020\u001bJ\u0007\u0010³\u0001\u001a\u00020\nJ\u000f\u0010µ\u0001\u001a\u00020\u001bH\u0000¢\u0006\u0003\b¶\u0001J\t\u0010·\u0001\u001a\u00020\u001bH\u0002J\t\u0010¸\u0001\u001a\u00020\u001bH\u0002J\t\u0010¹\u0001\u001a\u00020\u001bH\u0002J\u0007\u0010º\u0001\u001a\u00020\u001bJ\u0007\u0010»\u0001\u001a\u00020\u001bJ\u0007\u0010¼\u0001\u001a\u00020\u001bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR$\u0010\u0013\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\"\u001a\u00020!2\u0006\u0010\t\u001a\u00020!@PX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020.8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0014\u00101\u001a\u0002028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R$\u00106\u001a\u0002052\u0006\u0010\t\u001a\u0002058B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u000e\u0010;\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\r\"\u0004\b>\u0010\u000fR\u000e\u0010?\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010@\u001a\u0004\u0018\u00010A8F¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0010\u0010D\u001a\u0004\u0018\u00010AX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u00020FX\u0082\u000e¢\u0006\u0004\n\u0002\u0010GR\u000e\u0010H\u001a\u00020IX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010J\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u001b\u0018\u00010K¢\u0006\u0002\bMX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u0004\u0018\u00010OX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010P\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010\rR\u000e\u0010R\u001a\u00020SX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010T\u001a\u0002028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bU\u00104R\u0014\u0010V\u001a\u00020WX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0014\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00000[X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010^\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b_\u0010\rR\u001a\u0010`\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\r\"\u0004\bb\u0010\u000fR\u001a\u0010c\u001a\b\u0012\u0004\u0012\u00020\u00000d8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\be\u0010fR\u001e\u0010g\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\rR\u0014\u0010k\u001a\b\u0012\u0004\u0012\u00020\u001b0lX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010n\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bo\u0010\rR\u0016\u0010w\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bx\u0010yR\u000f\u0010\u0084\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R'\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0085\u00012\t\u0010\t\u001a\u0005\u0018\u00010\u0085\u0001@RX\u0096\u000e¢\u0006\n\n\u0000\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0011\u0010\u0089\u0001\u001a\u00020AX\u0082\u000e¢\u0006\u0004\n\u0002\u0010GR\u001d\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u001b0lX\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u001d\u0010\u009b\u0001\u001a\u00020\nX\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0001\u0010\r\"\u0005\b\u009c\u0001\u0010\u000fR\u0015\u0010\u009f\u0001\u001a\b\u0012\u0004\u0012\u00020\u001b0lX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010£\u0001\u001a\u00020!8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¤\u0001\u0010$R\u0016\u0010¥\u0001\u001a\u00020!8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¦\u0001\u0010$R\u000f\u0010´\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006¾\u0001"}, d2 = {"Landroidx/compose/ui/node/LookaheadPassDelegate;", "Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "Landroidx/compose/ui/node/MotionReferencePlacementDelegate;", "layoutNodeLayoutDelegate", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "<init>", "(Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;)V", "value", "", "measurePending", "getMeasurePending", "()Z", "setMeasurePending", "(Z)V", "layoutPending", "getLayoutPending", "setLayoutPending", "layoutPendingForAlignment", "getLayoutPendingForAlignment", "setLayoutPendingForAlignment", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "markLayoutPending", "", "markLayoutPending$ui", "markMeasurePending", "markMeasurePending$ui", "relayoutWithoutParentInProgress", "previousPlaceOrder", "", "placeOrder", "getPlaceOrder", "()I", "setPlaceOrder$ui", "(I)V", "measuredByParent", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "getMeasuredByParent$ui", "()Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "setMeasuredByParent$ui", "(Landroidx/compose/ui/node/LayoutNode$UsageByParent;)V", "measurePassDelegate", "Landroidx/compose/ui/node/MeasurePassDelegate;", "getMeasurePassDelegate$ui", "()Landroidx/compose/ui/node/MeasurePassDelegate;", "outerCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "getOuterCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "Landroidx/compose/ui/node/LayoutNode$LayoutState;", "layoutState", "getLayoutState", "()Landroidx/compose/ui/node/LayoutNode$LayoutState;", "setLayoutState", "(Landroidx/compose/ui/node/LayoutNode$LayoutState;)V", "duringAlignmentLinesQuery", "placedOnce", "getPlacedOnce$ui", "setPlacedOnce$ui", "measuredOnce", "lastConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLastConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "lookaheadConstraints", "lastPosition", "Landroidx/compose/ui/unit/IntOffset;", "J", "lastZIndex", "", "lastLayerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "lastExplicitLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "isPlaced", "isPlaced$ui", "_placedState", "Landroidx/compose/ui/node/LookaheadPassDelegate$PlacedState;", "innerCoordinator", "getInnerCoordinator", "alignmentLines", "Landroidx/compose/ui/node/AlignmentLines;", "getAlignmentLines", "()Landroidx/compose/ui/node/AlignmentLines;", "_childDelegates", "Landroidx/compose/runtime/collection/MutableVector;", "onApproachPlacement", "onApproachPlacement$ui", "needsToBePlacedInApproach", "getNeedsToBePlacedInApproach", "childDelegatesDirty", "getChildDelegatesDirty$ui", "setChildDelegatesDirty$ui", "childDelegates", "", "getChildDelegates$ui", "()Ljava/util/List;", "layingOutChildren", "getLayingOutChildren", "forEachChildDelegate", "block", "layoutChildrenBlock", "Lkotlin/Function0;", "layoutChildren", "detachedFromParentLookaheadPlacement", "getDetachedFromParentLookaheadPlacement", "checkChildrenPlaceOrderForUpdates", "markNodeAndSubtreeAsNotPlaced", "inLookahead", "markNodeAndSubtreeAsNotPlaced$ui", "calculateAlignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "parentAlignmentLinesOwner", "getParentAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "forEachChildAlignmentLinesOwner", "requestLayout", "requestMeasure", "notifyChildrenUsingLookaheadCoordinatesWhilePlacing", "measure", "constraints", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "trackLookaheadMeasurementByParent", "node", "parentDataDirty", "", "parentData", "getParentData", "()Ljava/lang/Object;", "performMeasureConstraints", "performMeasureBlock", "getPerformMeasureBlock$ui", "()Lkotlin/jvm/functions/Function0;", "performMeasure", "performMeasure-BRTryo0$ui", "(J)V", "remeasure", "remeasure-BRTryo0", "(J)Z", "placeAt", "position", "zIndex", "layerBlock", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "layer", "(JFLandroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "isPlacedUnderMotionFrameOfReference", "setPlacedUnderMotionFrameOfReference", "updatePlacedUnderMotionFrameOfReference", "newMFR", "layoutModifierBlock", "placeSelf", "placeSelf-MLgxB_4", "(JFLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "measuredWidth", "getMeasuredWidth", "measuredHeight", "getMeasuredHeight", "get", "alignmentLine", "minIntrinsicWidth", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "onIntrinsicsQueried", "invalidateIntrinsicsParent", "forceRequest", "invalidateParentData", "updateParentData", "onNodePlacedCalled", "onNodePlaced", "onNodePlaced$ui", "clearPlaceOrder", "markNodeAndSubtreeAsPlaced", "onBeforeLayoutChildren", "replace", "onNodeDetached", "onAttachedToNullParent", "PlacedState", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LookaheadPassDelegate extends Placeable implements Measurable, AlignmentLinesOwner, MotionReferencePlacementDelegate {
    public static final int $stable = 8;
    private boolean duringAlignmentLinesQuery;
    private boolean isPlacedUnderMotionFrameOfReference;
    private GraphicsLayer lastExplicitLayer;
    private Function1<? super GraphicsLayerScope, Unit> lastLayerBlock;
    private float lastZIndex;
    private boolean layingOutChildren;
    private final LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
    private Constraints lookaheadConstraints;
    private boolean measuredOnce;
    private boolean onNodePlacedCalled;
    private boolean placedOnce;
    private boolean relayoutWithoutParentInProgress;
    private int previousPlaceOrder = Integer.MAX_VALUE;
    private int placeOrder = Integer.MAX_VALUE;
    private LayoutNode.UsageByParent measuredByParent = LayoutNode.UsageByParent.NotUsed;
    private long lastPosition = IntOffset.INSTANCE.m6161getZeronOccac();
    private PlacedState _placedState = PlacedState.IsNotPlaced;
    private final AlignmentLines alignmentLines = new LookaheadAlignmentLines(this);
    private final MutableVector<LookaheadPassDelegate> _childDelegates = new MutableVector<>(new LookaheadPassDelegate[16], 0);
    private boolean childDelegatesDirty = true;
    private final Function0<Unit> layoutChildrenBlock = new Function0<Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$layoutChildrenBlock$1
        {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4885invoke() {
            this.this$0.clearPlaceOrder();
            this.this$0.forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$layoutChildrenBlock$1.1
                public final void invoke(AlignmentLinesOwner alignmentLinesOwner) {
                    alignmentLinesOwner.getAlignmentLines().setUsedDuringParentLayout$ui(false);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((AlignmentLinesOwner) obj);
                    return Unit.INSTANCE;
                }
            });
            LookaheadDelegate lookaheadDelegate = this.this$0.getInnerCoordinator().getLookaheadDelegate();
            if (lookaheadDelegate != null) {
                boolean isPlacingForAlignment = lookaheadDelegate.getIsPlacingForAlignment();
                List<LayoutNode> children$ui = this.this$0.getLayoutNode().getChildren$ui();
                int size = children$ui.size();
                for (int i = 0; i < size; i++) {
                    LookaheadDelegate lookaheadDelegate2 = children$ui.get(i).getOuterCoordinator$ui().getLookaheadDelegate();
                    if (lookaheadDelegate2 != null) {
                        lookaheadDelegate2.setPlacingForAlignment$ui(isPlacingForAlignment);
                    }
                }
            }
            LookaheadDelegate lookaheadDelegate3 = this.this$0.getInnerCoordinator().getLookaheadDelegate();
            lookaheadDelegate3.getClass();
            lookaheadDelegate3.getMeasureResult$ui().placeChildren();
            LookaheadDelegate lookaheadDelegate4 = this.this$0.getInnerCoordinator().getLookaheadDelegate();
            if (lookaheadDelegate4 != null) {
                lookaheadDelegate4.getIsPlacingForAlignment();
                List<LayoutNode> children$ui2 = this.this$0.getLayoutNode().getChildren$ui();
                int size2 = children$ui2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    LookaheadDelegate lookaheadDelegate5 = children$ui2.get(i2).getOuterCoordinator$ui().getLookaheadDelegate();
                    if (lookaheadDelegate5 != null) {
                        lookaheadDelegate5.setPlacingForAlignment$ui(false);
                    }
                }
            }
            this.this$0.checkChildrenPlaceOrderForUpdates();
            this.this$0.forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$layoutChildrenBlock$1.4
                public final void invoke(AlignmentLinesOwner alignmentLinesOwner) {
                    alignmentLinesOwner.getAlignmentLines().setPreviousUsedDuringParentLayout$ui(alignmentLinesOwner.getAlignmentLines().getUsedDuringParentLayout());
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((AlignmentLinesOwner) obj);
                    return Unit.INSTANCE;
                }
            });
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m4885invoke();
            return Unit.INSTANCE;
        }
    };
    private boolean parentDataDirty = true;
    private Object parentData = getMeasurePassDelegate$ui().getParentData();
    private long performMeasureConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);
    private final Function0<Unit> performMeasureBlock = new Function0<Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$performMeasureBlock$1
        {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4887invoke() {
            LookaheadDelegate lookaheadDelegate = this.this$0.getOuterCoordinator().getLookaheadDelegate();
            lookaheadDelegate.getClass();
            lookaheadDelegate.mo4605measureBRTryo0(this.this$0.performMeasureConstraints);
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m4887invoke();
            return Unit.INSTANCE;
        }
    };
    private final Function0<Unit> layoutModifierBlock = new Function0<Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$layoutModifierBlock$1
        {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4886invoke() {
            LookaheadDelegate lookaheadDelegate;
            Placeable.PlacementScope placementScope = null;
            if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(this.this$0.getLayoutNode()) || this.this$0.layoutNodeLayoutDelegate.getDetachedFromParentLookaheadPlacement()) {
                NodeCoordinator wrappedBy = this.this$0.getOuterCoordinator().getWrappedBy();
                if (wrappedBy != null) {
                    placementScope = wrappedBy.getPlacementScope();
                }
            } else {
                NodeCoordinator wrappedBy2 = this.this$0.getOuterCoordinator().getWrappedBy();
                if (wrappedBy2 != null && (lookaheadDelegate = wrappedBy2.getLookaheadDelegate()) != null) {
                    placementScope = lookaheadDelegate.getPlacementScope();
                }
            }
            if (placementScope == null) {
                placementScope = LayoutNodeKt.requireOwner(this.this$0.getLayoutNode()).getPlacementScope();
            }
            LookaheadPassDelegate lookaheadPassDelegate = this.this$0;
            LookaheadDelegate lookaheadDelegate2 = lookaheadPassDelegate.getOuterCoordinator().getLookaheadDelegate();
            lookaheadDelegate2.getClass();
            Placeable.PlacementScope.m4676place70tqf50$default(placementScope, lookaheadDelegate2, lookaheadPassDelegate.lastPosition, 0.0f, 2, null);
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m4886invoke();
            return Unit.INSTANCE;
        }
    };

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/node/LookaheadPassDelegate$PlacedState;", "", "<init>", "(Ljava/lang/String;I)V", "IsPlacedInLookahead", "IsPlacedInApproach", "IsNotPlaced", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public enum PlacedState {
        IsPlacedInLookahead,
        IsPlacedInApproach,
        IsNotPlaced;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<PlacedState> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

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
                iArr[LayoutNode.LayoutState.LayingOut.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LayoutNode.LayoutState.LookaheadLayingOut.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[LayoutNode.UsageByParent.values().length];
            try {
                iArr2[LayoutNode.UsageByParent.InMeasureBlock.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[LayoutNode.UsageByParent.InLayoutBlock.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public LookaheadPassDelegate(LayoutNodeLayoutDelegate layoutNodeLayoutDelegate) {
        this.layoutNodeLayoutDelegate = layoutNodeLayoutDelegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkChildrenPlaceOrderForUpdates() {
        MutableVector<LayoutNode> mutableVector = getLayoutNode().get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LookaheadPassDelegate lookaheadPassDelegate = layoutNodeArr[i].getLayoutDelegate().getLookaheadPassDelegate();
            lookaheadPassDelegate.getClass();
            if (lookaheadPassDelegate.previousPlaceOrder != lookaheadPassDelegate.getPlaceOrder() && lookaheadPassDelegate.getPlaceOrder() == Integer.MAX_VALUE) {
                lookaheadPassDelegate.markNodeAndSubtreeAsNotPlaced$ui(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearPlaceOrder() {
        this.layoutNodeLayoutDelegate.setNextChildLookaheadPlaceOrder$ui(0);
        MutableVector<LayoutNode> mutableVector = getLayoutNode().get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LookaheadPassDelegate lookaheadPassDelegate = layoutNodeArr[i].getLayoutDelegate().getLookaheadPassDelegate();
            lookaheadPassDelegate.getClass();
            lookaheadPassDelegate.previousPlaceOrder = lookaheadPassDelegate.getPlaceOrder();
            lookaheadPassDelegate.setPlaceOrder$ui(Integer.MAX_VALUE);
            if (lookaheadPassDelegate.measuredByParent == LayoutNode.UsageByParent.InLayoutBlock) {
                lookaheadPassDelegate.measuredByParent = LayoutNode.UsageByParent.NotUsed;
            }
        }
    }

    private final void forEachChildDelegate(Function1<? super LookaheadPassDelegate, Unit> block) {
        MutableVector<LayoutNode> mutableVector = getLayoutNode().get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LookaheadPassDelegate lookaheadPassDelegate = layoutNodeArr[i].getLayoutDelegate().getLookaheadPassDelegate();
            lookaheadPassDelegate.getClass();
            block.invoke(lookaheadPassDelegate);
        }
    }

    private final boolean getDetachedFromParentLookaheadPlacement() {
        return this.layoutNodeLayoutDelegate.getDetachedFromParentLookaheadPlacement();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutNode getLayoutNode() {
        return this.layoutNodeLayoutDelegate.getLayoutNode();
    }

    private final boolean getLayoutPending() {
        return this.layoutNodeLayoutDelegate.getLookaheadLayoutPending();
    }

    private final boolean getLayoutPendingForAlignment() {
        return this.layoutNodeLayoutDelegate.getLookaheadLayoutPendingForAlignment();
    }

    private final LayoutNode.LayoutState getLayoutState() {
        return this.layoutNodeLayoutDelegate.getLayoutState();
    }

    private final boolean getMeasurePending() {
        return this.layoutNodeLayoutDelegate.getLookaheadMeasurePending();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NodeCoordinator getOuterCoordinator() {
        return this.layoutNodeLayoutDelegate.getOuterCoordinator();
    }

    private final void markNodeAndSubtreeAsPlaced() {
        PlacedState placedState = this._placedState;
        if (getDetachedFromParentLookaheadPlacement()) {
            this._placedState = PlacedState.IsPlacedInApproach;
        } else {
            this._placedState = PlacedState.IsPlacedInLookahead;
        }
        if (placedState != PlacedState.IsPlacedInLookahead && this.layoutNodeLayoutDelegate.getLookaheadMeasurePending()) {
            LayoutNode.requestLookaheadRemeasure$ui$default(getLayoutNode(), true, false, false, 6, null);
        }
        MutableVector<LayoutNode> mutableVector = getLayoutNode().get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode = layoutNodeArr[i];
            LookaheadPassDelegate lookaheadPassDelegate$ui = layoutNode.getLookaheadPassDelegate$ui();
            if (lookaheadPassDelegate$ui == null) {
                w01.a("Error: Child node's lookahead pass delegate cannot be null when in a lookahead scope.");
                return;
            }
            if (lookaheadPassDelegate$ui.getPlaceOrder() != Integer.MAX_VALUE) {
                lookaheadPassDelegate$ui.markNodeAndSubtreeAsPlaced();
                layoutNode.rescheduleRemeasureOrRelayout$ui(layoutNode);
            }
        }
    }

    private final void onBeforeLayoutChildren() {
        MutableVector<LayoutNode> mutableVector = getLayoutNode().get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode = layoutNodeArr[i];
            if (layoutNode.getLookaheadMeasurePending$ui() && layoutNode.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.InMeasureBlock) {
                LookaheadPassDelegate lookaheadPassDelegate = layoutNode.getLayoutDelegate().getLookaheadPassDelegate();
                lookaheadPassDelegate.getClass();
                Constraints constraintsM4863getLastLookaheadConstraintsDWUhwKw = layoutNode.getLayoutDelegate().m4863getLastLookaheadConstraintsDWUhwKw();
                constraintsM4863getLastLookaheadConstraintsDWUhwKw.getClass();
                if (lookaheadPassDelegate.m4884remeasureBRTryo0(constraintsM4863getLastLookaheadConstraintsDWUhwKw.getValue())) {
                    LayoutNode.requestLookaheadRemeasure$ui$default(getLayoutNode(), false, false, false, 7, null);
                }
            }
        }
    }

    private final void onIntrinsicsQueried() {
        LayoutNode.UsageByParent intrinsicsUsageByParent;
        LayoutNode.requestLookaheadRemeasure$ui$default(getLayoutNode(), false, false, false, 7, null);
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        if (parent$ui == null || getLayoutNode().getIntrinsicsUsageByParent() != LayoutNode.UsageByParent.NotUsed) {
            return;
        }
        LayoutNode layoutNode = getLayoutNode();
        int i = WhenMappings.$EnumSwitchMapping$0[parent$ui.getLayoutState$ui().ordinal()];
        if (i != 2) {
            intrinsicsUsageByParent = i != 3 ? parent$ui.getIntrinsicsUsageByParent() : LayoutNode.UsageByParent.InLayoutBlock;
        } else {
            intrinsicsUsageByParent = LayoutNode.UsageByParent.InMeasureBlock;
        }
        layoutNode.setIntrinsicsUsageByParent$ui(intrinsicsUsageByParent);
    }

    /* JADX INFO: renamed from: placeSelf-MLgxB_4, reason: not valid java name */
    private final void m4881placeSelfMLgxB_4(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock, GraphicsLayer layer) throws Throwable {
        LayoutNode layoutNode = getLayoutNode();
        try {
            LayoutNode parent$ui = getLayoutNode().getParent$ui();
            LayoutNode.LayoutState layoutState$ui = parent$ui != null ? parent$ui.getLayoutState$ui() : null;
            LayoutNode.LayoutState layoutState = LayoutNode.LayoutState.LookaheadLayingOut;
            if (layoutState$ui == layoutState) {
                this.layoutNodeLayoutDelegate.setDetachedFromParentLookaheadPlacement$ui(false);
            }
            if (getLayoutNode().getIsDeactivated()) {
                InlineClassHelperKt.throwIllegalArgumentException("place is called on a deactivated node");
            }
            setLayoutState(layoutState);
            this.placedOnce = true;
            this.onNodePlacedCalled = false;
            if (!IntOffset.m6149equalsimpl0(position, this.lastPosition)) {
                if (this.layoutNodeLayoutDelegate.getLookaheadCoordinatesAccessedDuringModifierPlacement() || this.layoutNodeLayoutDelegate.getLookaheadCoordinatesAccessedDuringPlacement()) {
                    setLayoutPending(true);
                }
                notifyChildrenUsingLookaheadCoordinatesWhilePlacing();
            }
            Owner ownerRequireOwner = LayoutNodeKt.requireOwner(getLayoutNode());
            this.lastPosition = position;
            if (getLayoutPending() || !isPlaced$ui()) {
                this.layoutNodeLayoutDelegate.setLookaheadCoordinatesAccessedDuringModifierPlacement(false);
                getAlignmentLines().setUsedByModifierLayout$ui(false);
                OwnerSnapshotObserver snapshotObserver = ownerRequireOwner.getSnapshotObserver();
                snapshotObserver.observer.observeReads(getLayoutNode(), snapshotObserver.onCommitAffectingLayoutModifierInLookahead, this.layoutModifierBlock);
            } else {
                LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
                lookaheadDelegate.getClass();
                lookaheadDelegate.m4878placeSelfApparentToRealOffsetgyyYBs$ui(position);
                onNodePlaced$ui();
            }
            this.lastZIndex = zIndex;
            this.lastLayerBlock = layerBlock;
            this.lastExplicitLayer = layer;
            setLayoutState(LayoutNode.LayoutState.Idle);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            layoutNode.rethrowWithComposeStackTrace(th);
            wq6.a();
        }
    }

    private final void setLayoutPending(boolean z) {
        this.layoutNodeLayoutDelegate.setLookaheadLayoutPending$ui(z);
    }

    private final void setLayoutPendingForAlignment(boolean z) {
        this.layoutNodeLayoutDelegate.setLookaheadLayoutPendingForAlignment$ui(z);
    }

    private final void setLayoutState(LayoutNode.LayoutState layoutState) {
        this.layoutNodeLayoutDelegate.setLayoutState$ui(layoutState);
    }

    private final void setMeasurePending(boolean z) {
        this.layoutNodeLayoutDelegate.setLookaheadMeasurePending$ui(z);
    }

    private final void trackLookaheadMeasurementByParent(LayoutNode node) {
        LayoutNode.UsageByParent usageByParent;
        LayoutNode parent$ui = node.getParent$ui();
        if (parent$ui == null) {
            this.measuredByParent = LayoutNode.UsageByParent.NotUsed;
            return;
        }
        if (!(this.measuredByParent == LayoutNode.UsageByParent.NotUsed || node.getCanMultiMeasure())) {
            InlineClassHelperKt.throwIllegalStateException(LayoutNodeLayoutDelegateKt.MeasuredTwiceErrorMessage);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[parent$ui.getLayoutState$ui().ordinal()];
        if (i == 1 || i == 2) {
            usageByParent = LayoutNode.UsageByParent.InMeasureBlock;
        } else {
            if (i != 3 && i != 4) {
                sle.a("Measurable could be only measured from the parent's measure or layout block. Parents state is ", parent$ui.getLayoutState$ui());
                return;
            }
            usageByParent = LayoutNode.UsageByParent.InLayoutBlock;
        }
        this.measuredByParent = usageByParent;
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public Map<AlignmentLine, Integer> calculateAlignmentLines() {
        if (!this.duringAlignmentLinesQuery) {
            if (getLayoutState() == LayoutNode.LayoutState.LookaheadMeasuring) {
                getAlignmentLines().setUsedByModifierMeasurement$ui(true);
                if (getAlignmentLines().getDirty()) {
                    this.layoutNodeLayoutDelegate.markLookaheadLayoutPending$ui();
                }
            } else {
                getAlignmentLines().setUsedByModifierLayout$ui(true);
            }
        }
        LookaheadDelegate lookaheadDelegate = getInnerCoordinator().getLookaheadDelegate();
        if (lookaheadDelegate != null) {
            lookaheadDelegate.setPlacingForAlignment$ui(true);
        }
        layoutChildren();
        LookaheadDelegate lookaheadDelegate2 = getInnerCoordinator().getLookaheadDelegate();
        if (lookaheadDelegate2 != null) {
            lookaheadDelegate2.setPlacingForAlignment$ui(false);
        }
        return getAlignmentLines().getLastCalculation();
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void forEachChildAlignmentLinesOwner(Function1<? super AlignmentLinesOwner, Unit> block) {
        MutableVector<LayoutNode> mutableVector = getLayoutNode().get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui = layoutNodeArr[i].getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui();
            lookaheadAlignmentLinesOwner$ui.getClass();
            block.invoke(lookaheadAlignmentLinesOwner$ui);
        }
    }

    @Override // androidx.compose.ui.layout.Measured
    public int get(AlignmentLine alignmentLine) {
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        if ((parent$ui != null ? parent$ui.getLayoutState$ui() : null) == LayoutNode.LayoutState.LookaheadMeasuring) {
            getAlignmentLines().setUsedDuringParentMeasurement$ui(true);
        } else {
            LayoutNode parent$ui2 = getLayoutNode().getParent$ui();
            if ((parent$ui2 != null ? parent$ui2.getLayoutState$ui() : null) == LayoutNode.LayoutState.LookaheadLayingOut) {
                getAlignmentLines().setUsedDuringParentLayout$ui(true);
            }
        }
        this.duringAlignmentLinesQuery = true;
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        lookaheadDelegate.getClass();
        int i = lookaheadDelegate.get(alignmentLine);
        this.duringAlignmentLinesQuery = false;
        return i;
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public AlignmentLines getAlignmentLines() {
        return this.alignmentLines;
    }

    public final List<LookaheadPassDelegate> getChildDelegates$ui() {
        getLayoutNode().getChildren$ui();
        if (!this.childDelegatesDirty) {
            return this._childDelegates.asMutableList();
        }
        LayoutNode layoutNode = getLayoutNode();
        MutableVector<LookaheadPassDelegate> mutableVector = this._childDelegates;
        MutableVector<LayoutNode> mutableVector2 = layoutNode.get_children$ui();
        LayoutNode[] layoutNodeArr = mutableVector2.content;
        int size = mutableVector2.getSize();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode2 = layoutNodeArr[i];
            if (mutableVector.getSize() <= i) {
                LookaheadPassDelegate lookaheadPassDelegate = layoutNode2.getLayoutDelegate().getLookaheadPassDelegate();
                lookaheadPassDelegate.getClass();
                mutableVector.add(lookaheadPassDelegate);
            } else {
                LookaheadPassDelegate lookaheadPassDelegate2 = layoutNode2.getLayoutDelegate().getLookaheadPassDelegate();
                lookaheadPassDelegate2.getClass();
                mutableVector.set(i, lookaheadPassDelegate2);
            }
        }
        mutableVector.removeRange(layoutNode.getChildren$ui().size(), mutableVector.getSize());
        this.childDelegatesDirty = false;
        return this._childDelegates.asMutableList();
    }

    /* JADX INFO: renamed from: getChildDelegatesDirty$ui, reason: from getter */
    public final boolean getChildDelegatesDirty() {
        return this.childDelegatesDirty;
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public NodeCoordinator getInnerCoordinator() {
        return getLayoutNode().getInnerCoordinator$ui();
    }

    /* JADX INFO: renamed from: getLastConstraints-DWUhwKw, reason: not valid java name and from getter */
    public final Constraints getLookaheadConstraints() {
        return this.lookaheadConstraints;
    }

    public final boolean getLayingOutChildren() {
        return this.layingOutChildren;
    }

    public final MeasurePassDelegate getMeasurePassDelegate$ui() {
        return this.layoutNodeLayoutDelegate.getMeasurePassDelegate();
    }

    /* JADX INFO: renamed from: getMeasuredByParent$ui, reason: from getter */
    public final LayoutNode.UsageByParent getMeasuredByParent() {
        return this.measuredByParent;
    }

    @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
    public int getMeasuredHeight() {
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        lookaheadDelegate.getClass();
        return lookaheadDelegate.getMeasuredHeight();
    }

    @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
    public int getMeasuredWidth() {
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        lookaheadDelegate.getClass();
        return lookaheadDelegate.getMeasuredWidth();
    }

    public final boolean getNeedsToBePlacedInApproach() {
        return LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode()) || getDetachedFromParentLookaheadPlacement();
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public AlignmentLinesOwner getParentAlignmentLinesOwner() {
        LayoutNodeLayoutDelegate layoutDelegate;
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        if (parent$ui == null || (layoutDelegate = parent$ui.getLayoutDelegate()) == null) {
            return null;
        }
        return layoutDelegate.getLookaheadAlignmentLinesOwner$ui();
    }

    @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        return this.parentData;
    }

    public final Function0<Unit> getPerformMeasureBlock$ui() {
        return this.performMeasureBlock;
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public int getPlaceOrder() {
        return this.placeOrder;
    }

    /* JADX INFO: renamed from: getPlacedOnce$ui, reason: from getter */
    public final boolean getPlacedOnce() {
        return this.placedOnce;
    }

    public final void invalidateIntrinsicsParent(boolean forceRequest) {
        LayoutNode layoutNode;
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        LayoutNode.UsageByParent intrinsicsUsageByParent = getLayoutNode().getIntrinsicsUsageByParent();
        if (parent$ui == null || intrinsicsUsageByParent == LayoutNode.UsageByParent.NotUsed) {
            return;
        }
        do {
            layoutNode = parent$ui;
            if (layoutNode.getIntrinsicsUsageByParent() != intrinsicsUsageByParent) {
                break;
            } else {
                parent$ui = layoutNode.getParent$ui();
            }
        } while (parent$ui != null);
        int i = WhenMappings.$EnumSwitchMapping$1[intrinsicsUsageByParent.ordinal()];
        if (i == 1) {
            if (layoutNode.getLookaheadRoot() != null) {
                LayoutNode.requestLookaheadRemeasure$ui$default(layoutNode, forceRequest, false, false, 6, null);
                return;
            } else {
                LayoutNode.requestRemeasure$ui$default(layoutNode, forceRequest, false, false, 6, null);
                return;
            }
        }
        if (i != 2) {
            k2d.a("Intrinsics isn't used by the parent");
        } else if (layoutNode.getLookaheadRoot() != null) {
            layoutNode.requestLookaheadRelayout$ui(forceRequest);
        } else {
            layoutNode.requestRelayout$ui(forceRequest);
        }
    }

    public final void invalidateParentData() {
        this.parentDataDirty = true;
    }

    public final boolean isPlaced$ui() {
        return this._placedState != PlacedState.IsNotPlaced;
    }

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    /* JADX INFO: renamed from: isPlacedUnderMotionFrameOfReference, reason: from getter */
    public boolean getIsPlacedUnderMotionFrameOfReference() {
        return this.isPlacedUnderMotionFrameOfReference;
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void layoutChildren() {
        this.layingOutChildren = true;
        getAlignmentLines().recalculateQueryOwner();
        if (getLayoutPending()) {
            onBeforeLayoutChildren();
        }
        LookaheadDelegate lookaheadDelegate = getInnerCoordinator().getLookaheadDelegate();
        lookaheadDelegate.getClass();
        if (getLayoutPendingForAlignment() || (!this.duringAlignmentLinesQuery && !lookaheadDelegate.getIsPlacingForAlignment() && getLayoutPending())) {
            setLayoutPending(false);
            LayoutNode.LayoutState layoutState = getLayoutState();
            setLayoutState(LayoutNode.LayoutState.LookaheadLayingOut);
            this.layoutNodeLayoutDelegate.setLookaheadCoordinatesAccessedDuringPlacement(false);
            OwnerSnapshotObserver snapshotObserver = LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
            LayoutNode layoutNode = getLayoutNode();
            Function0<Unit> function0 = this.layoutChildrenBlock;
            snapshotObserver.observer.observeReads(layoutNode, snapshotObserver.onCommitAffectingLookahead, function0);
            setLayoutState(layoutState);
            if (this.layoutNodeLayoutDelegate.getLookaheadCoordinatesAccessedDuringPlacement() && lookaheadDelegate.getIsPlacingForAlignment()) {
                requestLayout();
            }
            setLayoutPendingForAlignment(false);
        }
        if (getAlignmentLines().getUsedDuringParentLayout()) {
            getAlignmentLines().setPreviousUsedDuringParentLayout$ui(true);
        }
        if (getAlignmentLines().getDirty() && getAlignmentLines().getRequired$ui()) {
            getAlignmentLines().recalculate();
        }
        this.layingOutChildren = false;
    }

    public final void markLayoutPending$ui() {
        setLayoutPending(true);
        setLayoutPendingForAlignment(true);
    }

    public final void markMeasurePending$ui() {
        setMeasurePending(true);
    }

    public final void markNodeAndSubtreeAsNotPlaced$ui(boolean inLookahead) {
        if (inLookahead && getNeedsToBePlacedInApproach()) {
            return;
        }
        if (inLookahead || getNeedsToBePlacedInApproach()) {
            this._placedState = PlacedState.IsNotPlaced;
            MutableVector<LayoutNode> mutableVector = getLayoutNode().get_children$ui();
            LayoutNode[] layoutNodeArr = mutableVector.content;
            int size = mutableVector.getSize();
            for (int i = 0; i < size; i++) {
                LookaheadPassDelegate lookaheadPassDelegate = layoutNodeArr[i].getLayoutDelegate().getLookaheadPassDelegate();
                lookaheadPassDelegate.getClass();
                lookaheadPassDelegate.markNodeAndSubtreeAsNotPlaced$ui(true);
            }
        }
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicHeight(int width) {
        onIntrinsicsQueried();
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        lookaheadDelegate.getClass();
        return lookaheadDelegate.maxIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicWidth(int height) {
        onIntrinsicsQueried();
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        lookaheadDelegate.getClass();
        return lookaheadDelegate.maxIntrinsicWidth(height);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0027  */
    @Override // androidx.compose.ui.layout.Measurable
    /* JADX INFO: renamed from: measure-BRTryo0 */
    public Placeable mo4605measureBRTryo0(long constraints) throws Throwable {
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        if ((parent$ui != null ? parent$ui.getLayoutState$ui() : null) == LayoutNode.LayoutState.LookaheadMeasuring) {
            this.layoutNodeLayoutDelegate.setDetachedFromParentLookaheadPass$ui(false);
        } else {
            LayoutNode parent$ui2 = getLayoutNode().getParent$ui();
            if ((parent$ui2 != null ? parent$ui2.getLayoutState$ui() : null) == LayoutNode.LayoutState.LookaheadLayingOut) {
                this.layoutNodeLayoutDelegate.setDetachedFromParentLookaheadPass$ui(false);
            }
        }
        trackLookaheadMeasurementByParent(getLayoutNode());
        if (getLayoutNode().getIntrinsicsUsageByParent() == LayoutNode.UsageByParent.NotUsed) {
            getLayoutNode().clearSubtreeIntrinsicsUsage$ui();
        }
        m4884remeasureBRTryo0(constraints);
        return this;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicHeight(int width) {
        onIntrinsicsQueried();
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        lookaheadDelegate.getClass();
        return lookaheadDelegate.minIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicWidth(int height) {
        onIntrinsicsQueried();
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        lookaheadDelegate.getClass();
        return lookaheadDelegate.minIntrinsicWidth(height);
    }

    public final void notifyChildrenUsingLookaheadCoordinatesWhilePlacing() {
        if (this.layoutNodeLayoutDelegate.getChildrenAccessingLookaheadCoordinatesDuringPlacement() > 0) {
            MutableVector<LayoutNode> mutableVector = getLayoutNode().get_children$ui();
            LayoutNode[] layoutNodeArr = mutableVector.content;
            int size = mutableVector.getSize();
            for (int i = 0; i < size; i++) {
                LayoutNode layoutNode = layoutNodeArr[i];
                LayoutNodeLayoutDelegate layoutDelegate = layoutNode.getLayoutDelegate();
                if ((layoutDelegate.getLookaheadCoordinatesAccessedDuringPlacement() || layoutDelegate.getLookaheadCoordinatesAccessedDuringModifierPlacement()) && !layoutDelegate.getLookaheadLayoutPending()) {
                    LayoutNode.requestLookaheadRelayout$ui$default(layoutNode, false, 1, null);
                }
                LookaheadPassDelegate lookaheadPassDelegate = layoutDelegate.getLookaheadPassDelegate();
                if (lookaheadPassDelegate != null) {
                    lookaheadPassDelegate.notifyChildrenUsingLookaheadCoordinatesWhilePlacing();
                }
            }
        }
    }

    public final void onApproachPlacement$ui() {
        if (this._placedState != PlacedState.IsNotPlaced || LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode())) {
            return;
        }
        this.layoutNodeLayoutDelegate.setDetachedFromParentLookaheadPlacement$ui(true);
    }

    public final void onAttachedToNullParent() {
        this._placedState = PlacedState.IsPlacedInLookahead;
    }

    public final void onNodeDetached() {
        setPlaceOrder$ui(Integer.MAX_VALUE);
        this.previousPlaceOrder = Integer.MAX_VALUE;
        this._placedState = PlacedState.IsNotPlaced;
    }

    public final void onNodePlaced$ui() {
        this.onNodePlacedCalled = true;
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        if ((this._placedState != PlacedState.IsPlacedInLookahead && !getDetachedFromParentLookaheadPlacement()) || (this._placedState != PlacedState.IsPlacedInApproach && getDetachedFromParentLookaheadPlacement())) {
            markNodeAndSubtreeAsPlaced();
            if (this.relayoutWithoutParentInProgress && parent$ui != null) {
                LayoutNode.requestLookaheadRelayout$ui$default(parent$ui, false, 1, null);
            }
        }
        if (parent$ui == null) {
            setPlaceOrder$ui(0);
        } else if (!this.relayoutWithoutParentInProgress && (parent$ui.getLayoutState$ui() == LayoutNode.LayoutState.LayingOut || parent$ui.getLayoutState$ui() == LayoutNode.LayoutState.LookaheadLayingOut)) {
            if (!(getPlaceOrder() == Integer.MAX_VALUE)) {
                InlineClassHelperKt.throwIllegalStateException("Place was called on a node which was placed already");
            }
            setPlaceOrder$ui(parent$ui.getLayoutDelegate().getNextChildLookaheadPlaceOrder());
            LayoutNodeLayoutDelegate layoutDelegate = parent$ui.getLayoutDelegate();
            layoutDelegate.setNextChildLookaheadPlaceOrder$ui(layoutDelegate.getNextChildLookaheadPlaceOrder() + 1);
        }
        layoutChildren();
    }

    /* JADX INFO: renamed from: performMeasure-BRTryo0$ui, reason: not valid java name */
    public final void m4883performMeasureBRTryo0$ui(long constraints) {
        setLayoutState(LayoutNode.LayoutState.LookaheadMeasuring);
        setMeasurePending(false);
        this.performMeasureConstraints = constraints;
        OwnerSnapshotObserver snapshotObserver = LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
        LayoutNode layoutNode = getLayoutNode();
        Function0<Unit> function0 = this.performMeasureBlock;
        snapshotObserver.observer.observeReads(layoutNode, snapshotObserver.onCommitAffectingLookaheadMeasure, function0);
        markLayoutPending$ui();
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode())) {
            getMeasurePassDelegate$ui().markLayoutPending();
        } else {
            getMeasurePassDelegate$ui().markMeasurePending$ui();
        }
        setLayoutState(LayoutNode.LayoutState.Idle);
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo4606placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) throws Throwable {
        m4881placeSelfMLgxB_4(position, zIndex, layerBlock, null);
    }

    /* JADX INFO: renamed from: remeasure-BRTryo0, reason: not valid java name */
    public final boolean m4884remeasureBRTryo0(long constraints) throws Throwable {
        LayoutNode layoutNode = getLayoutNode();
        try {
            if (getLayoutNode().getIsDeactivated()) {
                InlineClassHelperKt.throwIllegalArgumentException("measure is called on a deactivated node");
            }
            LayoutNode parent$ui = getLayoutNode().getParent$ui();
            getLayoutNode().setCanMultiMeasure$ui(getLayoutNode().getCanMultiMeasure() || (parent$ui != null && parent$ui.getCanMultiMeasure()));
            if (!getLayoutNode().getLookaheadMeasurePending$ui()) {
                Constraints constraints2 = this.lookaheadConstraints;
                if (constraints2 == null ? false : Constraints.m5968equalsimpl0(constraints2.getValue(), constraints)) {
                    Owner owner = getLayoutNode().getOwner();
                    if (owner != null) {
                        owner.forceMeasureTheSubtree(getLayoutNode(), true);
                    }
                    getLayoutNode().resetSubtreeIntrinsicsUsage$ui();
                    return false;
                }
            }
            this.lookaheadConstraints = Constraints.m5962boximpl(constraints);
            m4675setMeasurementConstraintsBRTryo0(constraints);
            getAlignmentLines().setUsedByModifierMeasurement$ui(false);
            forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$remeasure$1$2
                public final void invoke(AlignmentLinesOwner alignmentLinesOwner) {
                    alignmentLinesOwner.getAlignmentLines().setUsedDuringParentMeasurement$ui(false);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((AlignmentLinesOwner) obj);
                    return Unit.INSTANCE;
                }
            });
            long measuredSize = this.measuredOnce ? getMeasuredSize() : IntSize.m6188constructorimpl(-9223372034707292160L);
            this.measuredOnce = true;
            LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
            if (!(lookaheadDelegate != null)) {
                InlineClassHelperKt.throwIllegalStateException("Lookahead result from lookaheadRemeasure cannot be null");
            }
            this.layoutNodeLayoutDelegate.m4864performLookaheadMeasureBRTryo0$ui(constraints);
            m4674setMeasuredSizeozmzZPI(IntSize.m6188constructorimpl((((long) lookaheadDelegate.getWidth()) << 32) | (((long) lookaheadDelegate.getHeight()) & 4294967295L)));
            return (((int) (measuredSize >> 32)) == lookaheadDelegate.getWidth() && ((int) (measuredSize & 4294967295L)) == lookaheadDelegate.getHeight()) ? false : true;
        } catch (Throwable th) {
            layoutNode.rethrowWithComposeStackTrace(th);
            wq6.a();
            return false;
        }
    }

    public final void replace() {
        LookaheadPassDelegate lookaheadPassDelegate;
        LayoutNode parent$ui;
        try {
            this.relayoutWithoutParentInProgress = true;
            if (!this.placedOnce) {
                InlineClassHelperKt.throwIllegalStateException("replace() called on item that was not placed");
            }
            this.onNodePlacedCalled = false;
            boolean zIsPlaced$ui = isPlaced$ui();
            lookaheadPassDelegate = this;
            try {
                lookaheadPassDelegate.m4881placeSelfMLgxB_4(this.lastPosition, 0.0f, this.lastLayerBlock, this.lastExplicitLayer);
                if (zIsPlaced$ui && !lookaheadPassDelegate.onNodePlacedCalled && (parent$ui = lookaheadPassDelegate.getLayoutNode().getParent$ui()) != null) {
                    LayoutNode.requestLookaheadRelayout$ui$default(parent$ui, false, 1, null);
                }
                lookaheadPassDelegate.relayoutWithoutParentInProgress = false;
            } catch (Throwable th) {
                th = th;
                lookaheadPassDelegate.relayoutWithoutParentInProgress = false;
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            lookaheadPassDelegate = this;
        }
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void requestLayout() {
        LayoutNode.requestLookaheadRelayout$ui$default(getLayoutNode(), false, 1, null);
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void requestMeasure() {
        LayoutNode.requestLookaheadRemeasure$ui$default(getLayoutNode(), false, false, false, 7, null);
    }

    public final void setChildDelegatesDirty$ui(boolean z) {
        this.childDelegatesDirty = z;
    }

    public final void setMeasuredByParent$ui(LayoutNode.UsageByParent usageByParent) {
        this.measuredByParent = usageByParent;
    }

    public void setPlaceOrder$ui(int i) {
        this.placeOrder = i;
    }

    public final void setPlacedOnce$ui(boolean z) {
        this.placedOnce = z;
    }

    public void setPlacedUnderMotionFrameOfReference(boolean z) {
        this.isPlacedUnderMotionFrameOfReference = z;
    }

    public final boolean updateParentData() {
        if (getParentData() == null) {
            LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
            lookaheadDelegate.getClass();
            if (lookaheadDelegate.getParentData() == null) {
                return false;
            }
        }
        if (!this.parentDataDirty) {
            return false;
        }
        this.parentDataDirty = false;
        LookaheadDelegate lookaheadDelegate2 = getOuterCoordinator().getLookaheadDelegate();
        lookaheadDelegate2.getClass();
        this.parentData = lookaheadDelegate2.getParentData();
        return true;
    }

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    public void updatePlacedUnderMotionFrameOfReference(boolean newMFR) {
        LookaheadDelegate lookaheadDelegate;
        LookaheadDelegate lookaheadDelegate2 = getOuterCoordinator().getLookaheadDelegate();
        if (!Intrinsics.areEqual(Boolean.valueOf(newMFR), lookaheadDelegate2 != null ? Boolean.valueOf(lookaheadDelegate2.getIsPlacedUnderMotionFrameOfReference()) : null) && (lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate()) != null) {
            lookaheadDelegate.setPlacedUnderMotionFrameOfReference(newMFR);
        }
        setPlacedUnderMotionFrameOfReference(newMFR);
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo4673placeAtf8xVGno(long position, float zIndex, GraphicsLayer layer) throws Throwable {
        m4881placeSelfMLgxB_4(position, zIndex, null, layer);
    }
}
