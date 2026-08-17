package androidx.compose.ui.layout;

import androidx.collection.IntList;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableOrderedScatterSet;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PausableComposition;
import androidx.compose.runtime.PausedComposition;
import androidx.compose.runtime.ReusableComposition;
import androidx.compose.runtime.ShouldPauseCallback;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutNodeSubcompositionsState;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeKt;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.LookaheadPassDelegate;
import androidx.compose.ui.node.MeasurePassDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.OutOfFrameExecutor;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.SubcompositionKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.window.reflection.WindowExtensionsConstants;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0001\u0018\u00002\u00020\u0001:\u0003opqB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020)H\u0016J\b\u0010+\u001a\u00020)H\u0016J.\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2¢\u0006\u0002\u00103J:\u0010,\u001a\u00020)2\u0006\u00104\u001a\u00020\u00032\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0006\u00105\u001a\u0002062\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2H\u0002¢\u0006\u0002\u00107J \u0010,\u001a\u00020)2\u0006\u00104\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\u00182\u0006\u00105\u001a\u000206H\u0002J \u0010=\u001a\u0004\u0018\u00010\u001a2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00030-2\u0006\u0010?\u001a\u00020\u0014H\u0002J\u000e\u0010@\u001a\u00020)2\u0006\u0010A\u001a\u00020\u0014J\u0014\u0010B\u001a\u00020)*\u00020\u00182\u0006\u0010C\u001a\u000209H\u0002J\u0010\u0010D\u001a\u00020)2\u0006\u0010E\u001a\u000206H\u0002J\b\u0010F\u001a\u00020)H\u0002J\u0006\u0010G\u001a\u00020)J\f\u0010H\u001a\u00020)*\u00020\u0003H\u0002J\u0014\u0010I\u001a\u0004\u0018\u00010\u00032\b\u0010/\u001a\u0004\u0018\u00010\u001aH\u0002J%\u0010J\u001a\u00020K2\u001d\u0010L\u001a\u0019\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020O\u0012\u0004\u0012\u00020P0M¢\u0006\u0002\bQJ\b\u0010R\u001a\u00020)H\u0002J!\u0010S\u001a\u00020P2\u0006\u0010T\u001a\u00020P2\u000e\b\u0004\u0010U\u001a\b\u0012\u0004\u0012\u00020)01H\u0082\bJ(\u0010X\u001a\u00020#2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2¢\u0006\u0002\u0010YJ2\u0010X\u001a\u00020)2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b22\u0006\u00105\u001a\u000206H\u0002¢\u0006\u0002\u0010ZJ\u0014\u0010[\u001a\u00020)*\u00020\u00182\u0006\u0010\\\u001a\u000206H\u0002J\f\u0010]\u001a\u00020)*\u00020\u0018H\u0002J\u0012\u0010^\u001a\u00020)2\b\u0010/\u001a\u0004\u0018\u00010\u001aH\u0002J\u0012\u0010_\u001a\u00020#2\b\u0010/\u001a\u0004\u0018\u00010\u001aH\u0002J(\u0010`\u001a\u00020a2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2¢\u0006\u0002\u0010bJ\u0006\u0010c\u001a\u00020)J\u0010\u0010d\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\u0014H\u0002J\"\u0010e\u001a\u00020)2\u0006\u0010f\u001a\u00020\u00142\u0006\u0010g\u001a\u00020\u00142\b\b\u0002\u0010h\u001a\u00020\u0014H\u0002J\"\u0010i\u001a\u0002Hj\"\u0004\b\u0000\u0010j2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002Hj01H\u0082\b¢\u0006\u0002\u0010kJ\u0014\u0010l\u001a\u00020)*\u00020\u00182\u0006\u0010m\u001a\u000206H\u0002J0\u0010n\u001a\b\u0012\u0004\u0012\u00020.0-2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020)01¢\u0006\u0002\b2H\u0002¢\u0006\u0002\u00103R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u00030\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u00060\u001cR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00060\u001eR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u00030\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\"\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020#0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00108\u001a\u0004\u0018\u0001098BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u000e\u0010V\u001a\u00020WX\u0082D¢\u0006\u0002\n\u0000¨\u0006r"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "root", "Landroidx/compose/ui/node/LayoutNode;", "slotReusePolicy", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;)V", "compositionContext", "Landroidx/compose/runtime/CompositionContext;", "getCompositionContext", "()Landroidx/compose/runtime/CompositionContext;", "setCompositionContext", "(Landroidx/compose/runtime/CompositionContext;)V", "value", "getSlotReusePolicy", "()Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "setSlotReusePolicy", "(Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;)V", "currentIndex", "", "currentApproachIndex", "nodeToNodeState", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$NodeState;", "slotIdToNode", "", "scope", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$Scope;", "approachMeasureScope", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$ApproachMeasureScopeImpl;", "precomposeMap", "reusableSlotIdsSet", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy$SlotIdsSet;", "approachPrecomposeSlotHandleMap", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "slotIdsOfCompositionsNeededInApproach", "Landroidx/compose/runtime/collection/MutableVector;", "reusableCount", "precomposedCount", "onReuse", "", "onDeactivate", "onRelease", "subcompose", "", "Landroidx/compose/ui/layout/Measurable;", "slotId", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "node", "pausable", "", "(Landroidx/compose/ui/node/LayoutNode;Ljava/lang/Object;ZLkotlin/jvm/functions/Function2;)V", "outOfFrameExecutor", "Landroidx/compose/ui/node/OutOfFrameExecutor;", "getOutOfFrameExecutor", "()Landroidx/compose/ui/node/OutOfFrameExecutor;", "nodeState", "getSlotIdAtIndex", "foldedChildren", "index", "disposeOrReuseStartingFromIndex", "startIndex", "deactivateOutOfFrame", "executor", "markActiveNodesAsReused", "deactivate", "disposeCurrentNodes", "makeSureStateIsConsistent", "resetLayoutState", "takeNodeFromReusables", "createMeasurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "block", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "disposeUnusedSlotsInApproach", "createMeasureResult", "result", "placeChildrenBlock", "NoIntrinsicsMessage", "", "precompose", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Z)V", "reuseComposition", "forceDeactivate", "cancelPausedPrecomposition", "disposePrecomposedSlot", "createPrecomposedSlotHandle", "precomposePaused", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PausedPrecomposition;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/layout/SubcomposeLayoutState$PausedPrecomposition;", "forceRecomposeChildren", "createNodeAt", "move", "from", "to", "count", "ignoreRemeasureRequests", "T", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "applyPausedPrecomposition", "shouldComplete", "approachSubcompose", "NodeState", "Scope", "ApproachMeasureScopeImpl", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LayoutNodeSubcompositionsState implements ComposeNodeLifecycleCallback {
    public static final int $stable = 8;
    private CompositionContext compositionContext;
    private int currentApproachIndex;
    private int currentIndex;
    private int precomposedCount;
    private int reusableCount;
    private final LayoutNode root;
    private SubcomposeSlotReusePolicy slotReusePolicy;
    private final MutableScatterMap<LayoutNode, NodeState> nodeToNodeState = ScatterMapKt.mutableScatterMapOf();
    private final MutableScatterMap<Object, LayoutNode> slotIdToNode = ScatterMapKt.mutableScatterMapOf();
    private final Scope scope = new Scope();
    private final ApproachMeasureScopeImpl approachMeasureScope = new ApproachMeasureScopeImpl();
    private final MutableScatterMap<Object, LayoutNode> precomposeMap = ScatterMapKt.mutableScatterMapOf();
    private final SubcomposeSlotReusePolicy.SlotIdsSet reusableSlotIdsSet = new SubcomposeSlotReusePolicy.SlotIdsSet((MutableOrderedScatterSet) null, 1, (DefaultConstructorMarker) null);
    private final MutableScatterMap<Object, SubcomposeLayoutState.PrecomposedSlotHandle> approachPrecomposeSlotHandleMap = ScatterMapKt.mutableScatterMapOf();
    private final MutableVector<Object> slotIdsOfCompositionsNeededInApproach = new MutableVector<>(new Object[16], 0);
    private final String NoIntrinsicsMessage = "Asking for intrinsic measurements of SubcomposeLayout layouts is not supported. This includes components that are built on top of SubcomposeLayout, such as lazy lists, BoxWithConstraints, TabRow, etc. To mitigate this:\n- if intrinsic measurements are used to achieve 'match parent' sizing, consider replacing the parent of the component with a custom layout which controls the order in which children are measured, making intrinsic measurement not needed\n- adding a size modifier to the component, in order to fast return the queried intrinsic measurement.";

    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\u0002\b\u001eH\u0016¢\u0006\u0002\u0010\u001fJ`\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020#0&2\u0019\u0010(\u001a\u0015\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001d\u0018\u00010)¢\u0006\u0002\b+2\u0017\u0010,\u001a\u0013\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u001d0)¢\u0006\u0002\b+H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015¨\u0006."}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$Scope;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "<init>", "(Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;)V", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "density", "", "getDensity", "()F", "setDensity", "(F)V", "fontScale", "getFontScale", "setFontScale", "isLookingAhead", "", "()Z", "subcompose", "", "Landroidx/compose/ui/layout/Measurable;", "slotId", "", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", WindowExtensionsConstants.LAYOUT_PACKAGE, "Landroidx/compose/ui/layout/MeasureResult;", "width", "", "height", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "rulers", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/RulerScope;", "Lkotlin/ExtensionFunctionType;", "placementBlock", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public final class Scope implements SubcomposeMeasureScope {
        private float density;
        private float fontScale;
        private LayoutDirection layoutDirection = LayoutDirection.Rtl;

        public Scope() {
        }

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return this.density;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return this.fontScale;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public boolean isLookingAhead() {
            return LayoutNodeSubcompositionsState.this.root.getLayoutState$ui() == LayoutNode.LayoutState.LookaheadLayingOut || LayoutNodeSubcompositionsState.this.root.getLayoutState$ui() == LayoutNode.LayoutState.LookaheadMeasuring;
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public MeasureResult layout(final int width, final int height, final Map<AlignmentLine, Integer> alignmentLines, final Function1<? super RulerScope, Unit> rulers, final Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
            if (!((width & (-16777216)) == 0 && ((-16777216) & height) == 0)) {
                InlineClassHelperKt.throwIllegalStateException("Size(" + width + " x " + height + ") is out of range. Each dimension must be between 0 and 16777215.");
            }
            final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
            return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$Scope$layout$1
                @Override // androidx.compose.ui.layout.MeasureResult
                public Map<AlignmentLine, Integer> getAlignmentLines() {
                    return alignmentLines;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                /* JADX INFO: renamed from: getHeight, reason: from getter */
                public int get$height() {
                    return height;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public Function1<RulerScope, Unit> getRulers() {
                    return rulers;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                /* JADX INFO: renamed from: getWidth, reason: from getter */
                public int get$width() {
                    return width;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public void placeChildren() {
                    LookaheadDelegate lookaheadDelegate;
                    if (!this.isLookingAhead() || (lookaheadDelegate = layoutNodeSubcompositionsState.root.getInnerCoordinator$ui().getLookaheadDelegate()) == null) {
                        placementBlock.invoke(layoutNodeSubcompositionsState.root.getInnerCoordinator$ui().getPlacementScope());
                    } else {
                        placementBlock.invoke(lookaheadDelegate.getPlacementScope());
                    }
                }
            };
        }

        public void setDensity(float f) {
            this.density = f;
        }

        public void setFontScale(float f) {
            this.fontScale = f;
        }

        public void setLayoutDirection(LayoutDirection layoutDirection) {
            this.layoutDirection = layoutDirection;
        }

        @Override // androidx.compose.ui.layout.SubcomposeMeasureScope
        public List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
            return LayoutNodeSubcompositionsState.this.subcompose(slotId, content);
        }
    }

    public LayoutNodeSubcompositionsState(LayoutNode layoutNode, SubcomposeSlotReusePolicy subcomposeSlotReusePolicy) {
        this.root = layoutNode;
        this.slotReusePolicy = subcomposeSlotReusePolicy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyPausedPrecomposition(NodeState nodeState, boolean z) {
        PausedComposition pausedComposition = nodeState.getPausedComposition();
        if (pausedComposition != null) {
            Snapshot.Companion companion = Snapshot.INSTANCE;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                LayoutNode layoutNode = this.root;
                layoutNode.ignoreRemeasureRequests = true;
                if (z) {
                    while (!pausedComposition.isComplete()) {
                        try {
                            pausedComposition.resume(new ShouldPauseCallback() { // from class: yo8
                                @Override // androidx.compose.runtime.ShouldPauseCallback
                                public final boolean shouldPause() {
                                    return LayoutNodeSubcompositionsState.applyPausedPrecomposition$lambda$0$0$0();
                                }
                            });
                        } catch (Throwable th) {
                            MutableIntList operations = nodeState.getOperations();
                            if (operations == null) {
                                throw th;
                            }
                            throw new SubcomposeLayoutPausableCompositionException(operations, nodeState.getSlotId(), th);
                        }
                    }
                }
                pausedComposition.apply();
                nodeState.setPausedComposition(null);
                Unit unit = Unit.INSTANCE;
                layoutNode.ignoreRemeasureRequests = false;
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            } catch (Throwable th2) {
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean applyPausedPrecomposition$lambda$0$0$0() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Measurable> approachSubcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
        if (!(this.slotIdsOfCompositionsNeededInApproach.getSize() >= this.currentApproachIndex)) {
            InlineClassHelperKt.throwIllegalArgumentException("Error: currentApproachIndex cannot be greater than the size of theapproachComposedSlotIds list.");
        }
        LayoutNode layoutNode = (LayoutNode) this.slotIdToNode.get(slotId);
        int size = this.slotIdsOfCompositionsNeededInApproach.getSize();
        int i = this.currentApproachIndex;
        MutableVector<Object> mutableVector = this.slotIdsOfCompositionsNeededInApproach;
        if (size == i) {
            mutableVector.add(slotId);
        } else {
            mutableVector.set(i, slotId);
        }
        this.currentApproachIndex++;
        boolean zContains = this.precomposeMap.contains(slotId);
        if (zContains || layoutNode != null) {
            if (!zContains && layoutNode != null) {
                move(this.root.getFoldedChildren$ui().indexOf(layoutNode), this.root.getFoldedChildren$ui().size(), 1);
                this.precomposedCount++;
                this.slotIdToNode.remove(slotId);
                this.precomposeMap.set(slotId, layoutNode);
                this.approachPrecomposeSlotHandleMap.set(slotId, createPrecomposedSlotHandle(slotId));
                if (this.root.isAttached()) {
                    makeSureStateIsConsistent();
                }
            }
            LayoutNode layoutNode2 = (LayoutNode) this.precomposeMap.get(slotId);
            NodeState nodeState = layoutNode2 != null ? (NodeState) this.nodeToNodeState.get(layoutNode2) : null;
            if (nodeState != null && nodeState.getForceRecompose()) {
                subcompose(layoutNode2, slotId, false, content);
            }
            if ((nodeState != null ? nodeState.getPausedComposition() : null) != null) {
                applyPausedPrecomposition(nodeState, true);
            }
        } else {
            this.approachPrecomposeSlotHandleMap.set(slotId, precompose(slotId, content));
        }
        LayoutNode layoutNode3 = (LayoutNode) this.precomposeMap.get(slotId);
        if (layoutNode3 != null) {
            List<MeasurePassDelegate> childDelegates$ui = layoutNode3.getMeasurePassDelegate$ui().getChildDelegates$ui();
            int size2 = childDelegates$ui.size();
            for (int i2 = 0; i2 < size2; i2++) {
                childDelegates$ui.get(i2).markDetachedFromParentLookaheadPass$ui();
            }
            if (childDelegates$ui != null) {
                return childDelegates$ui;
            }
        }
        return CollectionsKt.emptyList();
    }

    private final void cancelPausedPrecomposition(NodeState nodeState) {
        PausedComposition pausedComposition = nodeState.getPausedComposition();
        if (pausedComposition != null) {
            pausedComposition.cancel();
            nodeState.setPausedComposition(null);
            ReusableComposition composition = nodeState.getComposition();
            if (composition != null) {
                composition.dispose();
            }
            nodeState.setComposition(null);
        }
    }

    private final MeasureResult createMeasureResult(final MeasureResult result, final Function0<Unit> placeChildrenBlock) {
        return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createMeasureResult.1
            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return result.getAlignmentLines();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getHeight */
            public int get$height() {
                return result.get$height();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Function1<RulerScope, Unit> getRulers() {
                return result.getRulers();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getWidth */
            public int get$width() {
                return result.get$width();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                placeChildrenBlock.invoke();
            }
        };
    }

    private final LayoutNode createNodeAt(int index) {
        LayoutNode layoutNode = new LayoutNode(true, 0, 2, null);
        LayoutNode layoutNode2 = this.root;
        layoutNode2.ignoreRemeasureRequests = true;
        this.root.insertAt$ui(index, layoutNode);
        Unit unit = Unit.INSTANCE;
        layoutNode2.ignoreRemeasureRequests = false;
        return layoutNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SubcomposeLayoutState.PrecomposedSlotHandle createPrecomposedSlotHandle(final Object slotId) {
        return !this.root.isAttached() ? new SubcomposeLayoutState.PrecomposedSlotHandle() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createPrecomposedSlotHandle.1
            public void dispose() {
            }
        } : new SubcomposeLayoutState.PrecomposedSlotHandle() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createPrecomposedSlotHandle.2
            private final MutableIntSet hasPremeasured = IntSetKt.mutableIntSetOf();

            public void dispose() {
                LayoutNodeSubcompositionsState.this.disposePrecomposedSlot(slotId);
            }

            public final MutableIntSet getHasPremeasured() {
                return this.hasPremeasured;
            }

            public int getPlaceablesCount() {
                List<LayoutNode> children$ui;
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode == null || (children$ui = layoutNode.getChildren$ui()) == null) {
                    return 0;
                }
                return children$ui.size();
            }

            /* JADX INFO: renamed from: getSize-YEO4UFw, reason: not valid java name */
            public long m4632getSizeYEO4UFw(int index) {
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode != null && layoutNode.isAttached()) {
                    int size = layoutNode.getChildren$ui().size();
                    if (index < 0 || index >= size) {
                        InlineClassHelperKt.throwIndexOutOfBoundsException("Index (" + index + ") is out of bound of [0, " + size + ')');
                    }
                    if (this.hasPremeasured.contains(index)) {
                        int width = layoutNode.getChildren$ui().get(index).getWidth();
                        return IntSize.m6188constructorimpl((((long) layoutNode.getChildren$ui().get(index).getHeight()) & 4294967295L) | (((long) width) << 32));
                    }
                }
                return IntSize.INSTANCE.m6198getZeroYbymL2g();
            }

            /* JADX INFO: renamed from: premeasure-0kLqBqw, reason: not valid java name */
            public void m4633premeasure0kLqBqw(int index, long constraints) {
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode == null || !layoutNode.isAttached()) {
                    return;
                }
                int size = layoutNode.getChildren$ui().size();
                if (index < 0 || index >= size) {
                    InlineClassHelperKt.throwIndexOutOfBoundsException("Index (" + index + ") is out of bound of [0, " + size + ')');
                }
                if (layoutNode.isPlaced()) {
                    InlineClassHelperKt.throwIllegalArgumentException("Pre-measure called on node that is not placed");
                }
                LayoutNode layoutNode2 = LayoutNodeSubcompositionsState.this.root;
                layoutNode2.ignoreRemeasureRequests = true;
                LayoutNodeKt.requireOwner(layoutNode).mo5016measureAndLayout0kLqBqw(layoutNode.getChildren$ui().get(index), constraints);
                Unit unit = Unit.INSTANCE;
                layoutNode2.ignoreRemeasureRequests = false;
                this.hasPremeasured.add(index);
            }

            public void traverseDescendants(Object key, Function1<? super TraversableNode, ? extends TraversableNode.Companion.TraverseDescendantsAction> block) {
                NodeChain nodes;
                Modifier.Node head;
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode == null || (nodes = layoutNode.getNodes()) == null || (head = nodes.getHead()) == null) {
                    return;
                }
                TraversableNodeKt.traverseDescendants(head, key, block);
            }
        };
    }

    private final void deactivateOutOfFrame(final NodeState nodeState, OutOfFrameExecutor outOfFrameExecutor) {
        outOfFrameExecutor.schedule(new Function0<Unit>() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.deactivateOutOfFrame.1
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m4634invoke() {
                ReusableComposition composition;
                if (nodeState.getActive() || (composition = nodeState.getComposition()) == null) {
                    return;
                }
                composition.deactivate();
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m4634invoke();
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:17:0x0050 A[LOOP:0: B:5:0x0013->B:17:0x0050, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:21:0x0053 A[EDGE_INSN: B:21:0x0053->B:18:0x0053 BREAK  A[LOOP:0: B:5:0x0013->B:17:0x0050], SYNTHETIC] */
    private final void disposeCurrentNodes() {
        ReusableComposition composition;
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        MutableScatterMap<LayoutNode, NodeState> mutableScatterMap = this.nodeToNodeState;
        Object[] objArr = ((ScatterMap) mutableScatterMap).values;
        long[] jArr = ((ScatterMap) mutableScatterMap).metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i != length) {
                        break;
                        break;
                    }
                    i++;
                } else {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128 && (composition = ((NodeState) objArr[(i << 3) + i3]).getComposition()) != null) {
                            composition.dispose();
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    } else if (i != length) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        this.root.removeAll$ui();
        Unit unit = Unit.INSTANCE;
        layoutNode.ignoreRemeasureRequests = false;
        this.nodeToNodeState.clear();
        this.slotIdToNode.clear();
        this.precomposedCount = 0;
        this.reusableCount = 0;
        this.precomposeMap.clear();
        makeSureStateIsConsistent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disposePrecomposedSlot(Object slotId) {
        makeSureStateIsConsistent();
        LayoutNode layoutNode = (LayoutNode) this.precomposeMap.remove(slotId);
        if (layoutNode != null) {
            if (!(this.precomposedCount > 0)) {
                InlineClassHelperKt.throwIllegalStateException("No pre-composed items to dispose");
            }
            int iIndexOf = this.root.getFoldedChildren$ui().indexOf(layoutNode);
            if (!(iIndexOf >= this.root.getFoldedChildren$ui().size() - this.precomposedCount)) {
                InlineClassHelperKt.throwIllegalStateException("Item is not in pre-composed item range");
            }
            this.reusableCount++;
            this.precomposedCount--;
            NodeState nodeState = (NodeState) this.nodeToNodeState.get(layoutNode);
            if (nodeState != null) {
                cancelPausedPrecomposition(nodeState);
            }
            int size = (this.root.getFoldedChildren$ui().size() - this.precomposedCount) - this.reusableCount;
            move(iIndexOf, size, 1);
            disposeOrReuseStartingFromIndex(size);
        }
        if (this.slotIdsOfCompositionsNeededInApproach.contains(slotId)) {
            LayoutNode.requestRemeasure$ui$default(this.root, true, false, false, 6, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disposeUnusedSlotsInApproach() {
        MutableScatterMap<Object, SubcomposeLayoutState.PrecomposedSlotHandle> mutableScatterMap = this.approachPrecomposeSlotHandleMap;
        long[] jArr = ((ScatterMap) mutableScatterMap).metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        Object obj = ((ScatterMap) mutableScatterMap).keys[i4];
                        SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = (SubcomposeLayoutState.PrecomposedSlotHandle) ((ScatterMap) mutableScatterMap).values[i4];
                        int iIndexOf = this.slotIdsOfCompositionsNeededInApproach.indexOf(obj);
                        if (iIndexOf < 0 || iIndexOf >= this.currentApproachIndex) {
                            if (iIndexOf >= 0) {
                                this.slotIdsOfCompositionsNeededInApproach.set(iIndexOf, SubcomposeLayoutKt.UnspecifiedSlotId);
                            }
                            if (this.precomposeMap.contains(obj)) {
                                precomposedSlotHandle.dispose();
                            }
                            mutableScatterMap.removeValueAt(i4);
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    private final OutOfFrameExecutor getOutOfFrameExecutor() {
        return LayoutNodeKt.requireOwner(this.root).getOutOfFrameExecutor();
    }

    private final Object getSlotIdAtIndex(List<LayoutNode> foldedChildren, int index) {
        Object obj = this.nodeToNodeState.get(foldedChildren.get(index));
        obj.getClass();
        return ((NodeState) obj).getSlotId();
    }

    private final <T> T ignoreRemeasureRequests(Function0<? extends T> block) {
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        T t = (T) block.invoke();
        layoutNode.ignoreRemeasureRequests = false;
        return t;
    }

    private final void markActiveNodesAsReused(boolean deactivate) {
        this.precomposedCount = 0;
        this.precomposeMap.clear();
        List<LayoutNode> foldedChildren$ui = this.root.getFoldedChildren$ui();
        int size = foldedChildren$ui.size();
        if (this.reusableCount != size) {
            this.reusableCount = size;
            Snapshot.Companion companion = Snapshot.INSTANCE;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            for (int i = 0; i < size; i++) {
                try {
                    LayoutNode layoutNode = foldedChildren$ui.get(i);
                    NodeState nodeState = (NodeState) this.nodeToNodeState.get(layoutNode);
                    if (nodeState != null && nodeState.getActive()) {
                        resetLayoutState(layoutNode);
                        reuseComposition(nodeState, deactivate);
                        nodeState.setSlotId(SubcomposeLayoutKt.ReusedSlotId);
                    }
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            Unit unit = Unit.INSTANCE;
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            this.slotIdToNode.clear();
        }
        makeSureStateIsConsistent();
    }

    private final void move(int from, int to, int count) {
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        this.root.move$ui(from, to, count);
        Unit unit = Unit.INSTANCE;
        layoutNode.ignoreRemeasureRequests = false;
    }

    public static /* synthetic */ void move$default(LayoutNodeSubcompositionsState layoutNodeSubcompositionsState, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i3 = 1;
        }
        layoutNodeSubcompositionsState.move(i, i2, i3);
    }

    private final void precompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content, boolean pausable) {
        if (this.root.isAttached()) {
            makeSureStateIsConsistent();
            if (this.slotIdToNode.containsKey(slotId)) {
                return;
            }
            this.approachPrecomposeSlotHandleMap.remove(slotId);
            MutableScatterMap<Object, LayoutNode> mutableScatterMap = this.precomposeMap;
            Object objTakeNodeFromReusables = mutableScatterMap.get(slotId);
            if (objTakeNodeFromReusables == null) {
                objTakeNodeFromReusables = takeNodeFromReusables(slotId);
                LayoutNode layoutNode = this.root;
                if (objTakeNodeFromReusables != null) {
                    move(layoutNode.getFoldedChildren$ui().indexOf(objTakeNodeFromReusables), this.root.getFoldedChildren$ui().size(), 1);
                    this.precomposedCount++;
                } else {
                    objTakeNodeFromReusables = createNodeAt(layoutNode.getFoldedChildren$ui().size());
                    this.precomposedCount++;
                }
                mutableScatterMap.set(slotId, objTakeNodeFromReusables);
            }
            subcompose((LayoutNode) objTakeNodeFromReusables, slotId, pausable, content);
        }
    }

    private final void resetLayoutState(LayoutNode layoutNode) {
        MeasurePassDelegate measurePassDelegate$ui = layoutNode.getMeasurePassDelegate$ui();
        LayoutNode.UsageByParent usageByParent = LayoutNode.UsageByParent.NotUsed;
        measurePassDelegate$ui.setMeasuredByParent$ui(usageByParent);
        LookaheadPassDelegate lookaheadPassDelegate$ui = layoutNode.getLookaheadPassDelegate$ui();
        if (lookaheadPassDelegate$ui != null) {
            lookaheadPassDelegate$ui.setMeasuredByParent$ui(usageByParent);
        }
    }

    private final void reuseComposition(NodeState nodeState, boolean z) {
        ReusableComposition composition;
        if (z || !nodeState.getComposedWithReusableContentHost()) {
            nodeState.setActiveState(SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null));
        } else {
            nodeState.setActive(false);
        }
        if (nodeState.getPausedComposition() != null) {
            cancelPausedPrecomposition(nodeState);
            return;
        }
        if (z) {
            ReusableComposition composition2 = nodeState.getComposition();
            if (composition2 != null) {
                composition2.deactivate();
                return;
            }
            return;
        }
        OutOfFrameExecutor outOfFrameExecutor = getOutOfFrameExecutor();
        if (outOfFrameExecutor != null) {
            deactivateOutOfFrame(nodeState, outOfFrameExecutor);
        } else {
            if (nodeState.getComposedWithReusableContentHost() || (composition = nodeState.getComposition()) == null) {
                return;
            }
            composition.deactivate();
        }
    }

    private final LayoutNode takeNodeFromReusables(Object slotId) {
        int i;
        if (this.reusableCount == 0) {
            return null;
        }
        List<LayoutNode> foldedChildren$ui = this.root.getFoldedChildren$ui();
        int size = foldedChildren$ui.size() - this.precomposedCount;
        int i2 = size - this.reusableCount;
        int i3 = size - 1;
        int i4 = i3;
        while (true) {
            if (i4 < i2) {
                i = -1;
                break;
            }
            if (Intrinsics.areEqual(getSlotIdAtIndex(foldedChildren$ui, i4), slotId)) {
                i = i4;
                break;
            }
            i4--;
        }
        if (i == -1) {
            while (true) {
                if (i3 < i2) {
                    i4 = i3;
                    break;
                }
                Object obj = this.nodeToNodeState.get(foldedChildren$ui.get(i3));
                obj.getClass();
                NodeState nodeState = (NodeState) obj;
                if (nodeState.getSlotId() == SubcomposeLayoutKt.ReusedSlotId || this.slotReusePolicy.areCompatible(slotId, nodeState.getSlotId())) {
                    nodeState.setSlotId(slotId);
                    i4 = i3;
                    i = i4;
                    break;
                }
                i3--;
            }
        }
        if (i == -1) {
            return null;
        }
        if (i4 != i2) {
            move(i4, i2, 1);
        }
        this.reusableCount--;
        LayoutNode layoutNode = foldedChildren$ui.get(i2);
        Object obj2 = this.nodeToNodeState.get(layoutNode);
        obj2.getClass();
        NodeState nodeState2 = (NodeState) obj2;
        nodeState2.setActiveState(SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null));
        nodeState2.setForceReuse(true);
        nodeState2.setForceRecompose(true);
        return layoutNode;
    }

    public final MeasurePolicy createMeasurePolicy(final Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> block) {
        return new LayoutNode.NoIntrinsicsMeasurePolicy(this.NoIntrinsicsMessage) { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createMeasurePolicy.1
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                LayoutNodeSubcompositionsState.this.scope.setLayoutDirection(measureScope.getLayoutDirection());
                LayoutNodeSubcompositionsState.this.scope.setDensity(measureScope.getDensity());
                LayoutNodeSubcompositionsState.this.scope.setFontScale(measureScope.getFontScale());
                if (measureScope.isLookingAhead() || LayoutNodeSubcompositionsState.this.root.getLookaheadRoot() == null) {
                    LayoutNodeSubcompositionsState.this.currentIndex = 0;
                    final MeasureResult measureResult = (MeasureResult) block.invoke(LayoutNodeSubcompositionsState.this.scope, Constraints.m5962boximpl(j));
                    final int i = LayoutNodeSubcompositionsState.this.currentIndex;
                    final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                    return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1$measure-3p2s80s$$inlined$createMeasureResult$2
                        @Override // androidx.compose.ui.layout.MeasureResult
                        public Map<AlignmentLine, Integer> getAlignmentLines() {
                            return measureResult.getAlignmentLines();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        /* JADX INFO: renamed from: getHeight */
                        public int get$height() {
                            return measureResult.get$height();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        public Function1<RulerScope, Unit> getRulers() {
                            return measureResult.getRulers();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        /* JADX INFO: renamed from: getWidth */
                        public int get$width() {
                            return measureResult.get$width();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        public void placeChildren() {
                            layoutNodeSubcompositionsState.currentIndex = i;
                            measureResult.placeChildren();
                            if (layoutNodeSubcompositionsState.root.getLookaheadRoot() == null) {
                                LayoutNodeSubcompositionsState layoutNodeSubcompositionsState2 = layoutNodeSubcompositionsState;
                                layoutNodeSubcompositionsState2.disposeOrReuseStartingFromIndex(layoutNodeSubcompositionsState2.currentIndex);
                            }
                        }
                    };
                }
                LayoutNodeSubcompositionsState.this.currentApproachIndex = 0;
                final MeasureResult measureResult2 = (MeasureResult) block.invoke(LayoutNodeSubcompositionsState.this.approachMeasureScope, Constraints.m5962boximpl(j));
                final int i2 = LayoutNodeSubcompositionsState.this.currentApproachIndex;
                final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState2 = LayoutNodeSubcompositionsState.this;
                return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1$measure-3p2s80s$$inlined$createMeasureResult$1
                    @Override // androidx.compose.ui.layout.MeasureResult
                    public Map<AlignmentLine, Integer> getAlignmentLines() {
                        return measureResult2.getAlignmentLines();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getHeight */
                    public int get$height() {
                        return measureResult2.get$height();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public Function1<RulerScope, Unit> getRulers() {
                        return measureResult2.getRulers();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getWidth */
                    public int get$width() {
                        return measureResult2.get$width();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public void placeChildren() {
                        layoutNodeSubcompositionsState2.currentApproachIndex = i2;
                        measureResult2.placeChildren();
                        layoutNodeSubcompositionsState2.disposeUnusedSlotsInApproach();
                        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState3 = layoutNodeSubcompositionsState2;
                        layoutNodeSubcompositionsState3.disposeOrReuseStartingFromIndex(layoutNodeSubcompositionsState3.currentIndex);
                    }
                };
            }
        };
    }

    public final void disposeOrReuseStartingFromIndex(int startIndex) {
        boolean z = false;
        this.reusableCount = 0;
        List<LayoutNode> foldedChildren$ui = this.root.getFoldedChildren$ui();
        int size = (foldedChildren$ui.size() - this.precomposedCount) - 1;
        if (startIndex <= size) {
            this.reusableSlotIdsSet.clear();
            if (startIndex <= size) {
                int i = startIndex;
                while (true) {
                    this.reusableSlotIdsSet.add$ui(getSlotIdAtIndex(foldedChildren$ui, i));
                    if (i == size) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            this.slotReusePolicy.getSlotsToRetain(this.reusableSlotIdsSet);
            Snapshot.Companion companion = Snapshot.INSTANCE;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            boolean z2 = false;
            while (size >= startIndex) {
                try {
                    LayoutNode layoutNode = foldedChildren$ui.get(size);
                    Object obj = this.nodeToNodeState.get(layoutNode);
                    obj.getClass();
                    NodeState nodeState = (NodeState) obj;
                    Object slotId = nodeState.getSlotId();
                    if (this.reusableSlotIdsSet.contains(slotId)) {
                        this.reusableCount++;
                        if (nodeState.getActive()) {
                            resetLayoutState(layoutNode);
                            reuseComposition(nodeState, false);
                            if (nodeState.getComposedWithReusableContentHost()) {
                                z2 = true;
                            }
                        }
                    } else {
                        LayoutNode layoutNode2 = this.root;
                        layoutNode2.ignoreRemeasureRequests = true;
                        this.nodeToNodeState.remove(layoutNode);
                        ReusableComposition composition = nodeState.getComposition();
                        if (composition != null) {
                            composition.dispose();
                        }
                        this.root.removeAt$ui(size, 1);
                        Unit unit = Unit.INSTANCE;
                        layoutNode2.ignoreRemeasureRequests = false;
                    }
                    this.slotIdToNode.remove(slotId);
                    size--;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            Unit unit2 = Unit.INSTANCE;
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            z = z2;
        }
        if (z) {
            Snapshot.INSTANCE.sendApplyNotifications();
        }
        makeSureStateIsConsistent();
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0051 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:17:0x0053 A[LOOP:0: B:7:0x001b->B:17:0x0053, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:28:0x0056 A[EDGE_INSN: B:28:0x0056->B:18:0x0056 BREAK  A[LOOP:0: B:7:0x001b->B:17:0x0053], SYNTHETIC] */
    public final void forceRecomposeChildren() {
        if (this.reusableCount != this.root.getFoldedChildren$ui().size()) {
            MutableScatterMap<LayoutNode, NodeState> mutableScatterMap = this.nodeToNodeState;
            Object[] objArr = ((ScatterMap) mutableScatterMap).values;
            long[] jArr = ((ScatterMap) mutableScatterMap).metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i != length) {
                            break;
                            break;
                        }
                        i++;
                    } else {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                ((NodeState) objArr[(i << 3) + i3]).setForceRecompose(true);
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        } else if (i != length) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            LayoutNode lookaheadRoot = this.root.getLookaheadRoot();
            LayoutNode layoutNode = this.root;
            if (lookaheadRoot != null) {
                if (layoutNode.getLookaheadMeasurePending$ui()) {
                    return;
                }
                LayoutNode.requestLookaheadRemeasure$ui$default(this.root, false, false, false, 7, null);
            } else {
                if (layoutNode.getMeasurePending$ui()) {
                    return;
                }
                LayoutNode.requestRemeasure$ui$default(this.root, false, false, false, 7, null);
            }
        }
    }

    public final CompositionContext getCompositionContext() {
        return this.compositionContext;
    }

    public final SubcomposeSlotReusePolicy getSlotReusePolicy() {
        return this.slotReusePolicy;
    }

    public final void makeSureStateIsConsistent() {
        int size = this.root.getFoldedChildren$ui().size();
        if (!(this.nodeToNodeState.getSize() == size)) {
            InlineClassHelperKt.throwIllegalArgumentException("Inconsistency between the count of nodes tracked by the state (" + this.nodeToNodeState.getSize() + ") and the children count on the SubcomposeLayout (" + size + "). Are you trying to use the state of the disposed SubcomposeLayout?");
        }
        if (!((size - this.reusableCount) - this.precomposedCount >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("Incorrect state. Total children " + size + ". Reusable children " + this.reusableCount + ". Precomposed children " + this.precomposedCount);
        }
        if (this.precomposeMap.getSize() == this.precomposedCount) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("Incorrect state. Precomposed children " + this.precomposedCount + ". Map size " + this.precomposeMap.getSize());
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onDeactivate() {
        markActiveNodesAsReused(true);
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onRelease() {
        disposeCurrentNodes();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onReuse() {
        markActiveNodesAsReused(false);
    }

    public final SubcomposeLayoutState.PausedPrecomposition precomposePaused(final Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
        if (!this.root.isAttached()) {
            return new PausedPrecompositionImpl() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.precomposePaused.1
                private final boolean isComplete = true;

                public SubcomposeLayoutState.PrecomposedSlotHandle apply() {
                    return LayoutNodeSubcompositionsState.this.createPrecomposedSlotHandle(slotId);
                }

                public void cancel() {
                }

                /* JADX INFO: renamed from: isComplete, reason: from getter */
                public boolean getIsComplete() {
                    return this.isComplete;
                }

                public boolean resume(ShouldPauseCallback shouldPause) {
                    return true;
                }
            };
        }
        precompose(slotId, content, true);
        return new PausedPrecompositionImpl() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.precomposePaused.2
            private final NodeState getNodeState() {
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode != null) {
                    return (NodeState) LayoutNodeSubcompositionsState.this.nodeToNodeState.get(layoutNode);
                }
                return null;
            }

            public SubcomposeLayoutState.PrecomposedSlotHandle apply() {
                NodeState nodeState = getNodeState();
                if (nodeState != null) {
                    LayoutNodeSubcompositionsState.this.applyPausedPrecomposition(nodeState, false);
                }
                return LayoutNodeSubcompositionsState.this.createPrecomposedSlotHandle(slotId);
            }

            public void cancel() {
                NodeState nodeState = getNodeState();
                if ((nodeState != null ? nodeState.getPausedComposition() : null) != null) {
                    LayoutNodeSubcompositionsState.this.disposePrecomposedSlot(slotId);
                }
            }

            public boolean isComplete() {
                PausedComposition pausedComposition;
                NodeState nodeState = getNodeState();
                if (nodeState == null || (pausedComposition = nodeState.getPausedComposition()) == null) {
                    return true;
                }
                return pausedComposition.isComplete();
            }

            public boolean resume(ShouldPauseCallback shouldPause) {
                NodeState nodeState = getNodeState();
                PausedComposition pausedComposition = nodeState != null ? nodeState.getPausedComposition() : null;
                if (pausedComposition == null || pausedComposition.isComplete()) {
                    return true;
                }
                Snapshot.Companion companion = Snapshot.INSTANCE;
                LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                Object obj = slotId;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    LayoutNode layoutNode = layoutNodeSubcompositionsState.root;
                    layoutNode.ignoreRemeasureRequests = true;
                    try {
                        boolean zResume = pausedComposition.resume(shouldPause);
                        layoutNode.ignoreRemeasureRequests = false;
                        companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                        return zResume;
                    } catch (Throwable th) {
                        if (nodeState.getOperations() != null) {
                            throw new SubcomposeLayoutPausableCompositionException(nodeState.getOperations(), obj, th);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th2;
                }
            }
        };
    }

    public final void setCompositionContext(CompositionContext compositionContext) {
        this.compositionContext = compositionContext;
    }

    public final void setSlotReusePolicy(SubcomposeSlotReusePolicy subcomposeSlotReusePolicy) {
        if (this.slotReusePolicy != subcomposeSlotReusePolicy) {
            this.slotReusePolicy = subcomposeSlotReusePolicy;
            markActiveNodesAsReused(false);
            LayoutNode.requestRemeasure$ui$default(this.root, false, false, false, 7, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:41:0x00a9  */
    public final List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState;
        makeSureStateIsConsistent();
        LayoutNode.LayoutState layoutState$ui = this.root.getLayoutState$ui();
        LayoutNode.LayoutState layoutState = LayoutNode.LayoutState.Measuring;
        if (!(layoutState$ui == layoutState || layoutState$ui == LayoutNode.LayoutState.LayingOut || layoutState$ui == LayoutNode.LayoutState.LookaheadMeasuring || layoutState$ui == LayoutNode.LayoutState.LookaheadLayingOut)) {
            InlineClassHelperKt.throwIllegalStateException("subcompose can only be used inside the measure or layout blocks");
        }
        MutableScatterMap<Object, LayoutNode> mutableScatterMap = this.slotIdToNode;
        Object objTakeNodeFromReusables = mutableScatterMap.get(slotId);
        if (objTakeNodeFromReusables == null) {
            objTakeNodeFromReusables = (LayoutNode) this.precomposeMap.remove(slotId);
            if (objTakeNodeFromReusables != null) {
                if (!(this.precomposedCount > 0)) {
                    InlineClassHelperKt.throwIllegalStateException("Check failed.");
                }
                this.precomposedCount--;
            } else {
                objTakeNodeFromReusables = takeNodeFromReusables(slotId);
                if (objTakeNodeFromReusables == null) {
                    objTakeNodeFromReusables = createNodeAt(this.currentIndex);
                }
            }
            mutableScatterMap.set(slotId, objTakeNodeFromReusables);
        }
        LayoutNode layoutNode = (LayoutNode) objTakeNodeFromReusables;
        if (CollectionsKt.getOrNull(this.root.getFoldedChildren$ui(), this.currentIndex) == layoutNode) {
            layoutNodeSubcompositionsState = this;
        } else {
            int iIndexOf = this.root.getFoldedChildren$ui().indexOf(layoutNode);
            if (!(iIndexOf >= this.currentIndex)) {
                InlineClassHelperKt.throwIllegalArgumentException("Key \"" + slotId + "\" was already used. If you are using LazyColumn/Row please make sure you provide a unique key for each item.");
            }
            int i = this.currentIndex;
            if (i != iIndexOf) {
                layoutNodeSubcompositionsState = this;
                move$default(layoutNodeSubcompositionsState, iIndexOf, i, 0, 4, null);
            } else {
                layoutNodeSubcompositionsState = this;
            }
        }
        layoutNodeSubcompositionsState.currentIndex++;
        layoutNodeSubcompositionsState.subcompose(layoutNode, slotId, false, content);
        return (layoutState$ui == layoutState || layoutState$ui == LayoutNode.LayoutState.LayingOut) ? layoutNode.getChildMeasurables$ui() : layoutNode.getChildLookaheadMeasurables$ui();
    }

    @Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J0\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0016¢\u0006\u0002\u0010\u000eJF\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120\u00152\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f0\u0018¢\u0006\u0002\b\u001aH\u0096\u0001Ja\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120\u00152\u0019\u0010\u001b\u001a\u0015\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\f\u0018\u00010\u0018¢\u0006\u0002\b\u001a2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f0\u0018¢\u0006\u0002\b\u001aH\u0096\u0001J\u0014\u0010\u001d\u001a\u00020\u0012*\u00020\u001eH\u0097\u0001¢\u0006\u0004\b\u001f\u0010 J\u0014\u0010\u001d\u001a\u00020\u0012*\u00020!H\u0097\u0001¢\u0006\u0004\b\"\u0010#J\u0014\u0010$\u001a\u00020\u001e*\u00020\u0012H\u0097\u0001¢\u0006\u0004\b%\u0010&J\u0014\u0010$\u001a\u00020\u001e*\u00020'H\u0097\u0001¢\u0006\u0004\b%\u0010(J\u0014\u0010$\u001a\u00020\u001e*\u00020!H\u0097\u0001¢\u0006\u0004\b)\u0010*J\u0014\u0010+\u001a\u00020,*\u00020-H\u0097\u0001¢\u0006\u0004\b.\u0010/J\u0014\u00100\u001a\u00020'*\u00020\u001eH\u0097\u0001¢\u0006\u0004\b1\u0010(J\u0014\u00100\u001a\u00020'*\u00020!H\u0097\u0001¢\u0006\u0004\b2\u0010*J\r\u00103\u001a\u000204*\u000205H\u0097\u0001J\u0014\u00106\u001a\u00020-*\u00020,H\u0097\u0001¢\u0006\u0004\b7\u0010/J\u0014\u00108\u001a\u00020!*\u00020\u0012H\u0097\u0001¢\u0006\u0004\b9\u0010:J\u0014\u00108\u001a\u00020!*\u00020'H\u0097\u0001¢\u0006\u0004\b9\u0010;J\u0014\u00108\u001a\u00020!*\u00020\u001eH\u0097\u0001¢\u0006\u0004\b<\u0010;R\u0014\u0010=\u001a\u00020'8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020'8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\bA\u0010?R\u0014\u0010B\u001a\u00020C8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\bB\u0010DR\u0012\u0010E\u001a\u00020FX\u0096\u0005¢\u0006\u0006\u001a\u0004\bG\u0010H¨\u0006I"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$ApproachMeasureScopeImpl;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/layout/MeasureScope;", "<init>", "(Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;)V", "subcompose", "", "Landroidx/compose/ui/layout/Measurable;", "slotId", "", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", WindowExtensionsConstants.LAYOUT_PACKAGE, "Landroidx/compose/ui/layout/MeasureResult;", "width", "", "height", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "placementBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lkotlin/ExtensionFunctionType;", "rulers", "Landroidx/compose/ui/layout/RulerScope;", "roundToPx", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(I)F", "", "(F)F", "toDp-GaN1DYA", "(J)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-kPz2Gy4", "(I)J", "(F)J", "toSp-0xMU5do", "density", "getDensity", "()F", "fontScale", "getFontScale", "isLookingAhead", "", "()Z", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public final class ApproachMeasureScopeImpl implements SubcomposeMeasureScope, MeasureScope {
        private final /* synthetic */ Scope $$delegate_0;

        public ApproachMeasureScopeImpl() {
            this.$$delegate_0 = LayoutNodeSubcompositionsState.this.scope;
        }

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return this.$$delegate_0.getDensity();
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return this.$$delegate_0.getFontScale();
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public LayoutDirection getLayoutDirection() {
            return this.$$delegate_0.getLayoutDirection();
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public boolean isLookingAhead() {
            return this.$$delegate_0.isLookingAhead();
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
            return this.$$delegate_0.layout(width, height, alignmentLines, placementBlock);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx--R2X_6o */
        public int mo4550roundToPxR2X_6o(long j) {
            return this.$$delegate_0.mo4550roundToPxR2X_6o(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx-0680j_4 */
        public int mo4551roundToPx0680j_4(float f) {
            return this.$$delegate_0.mo4551roundToPx0680j_4(f);
        }

        @Override // androidx.compose.ui.layout.SubcomposeMeasureScope
        public List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
            LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.slotIdToNode.get(slotId);
            return (layoutNode == null || LayoutNodeSubcompositionsState.this.root.getFoldedChildren$ui().indexOf(layoutNode) >= LayoutNodeSubcompositionsState.this.currentIndex) ? LayoutNodeSubcompositionsState.this.approachSubcompose(slotId, content) : layoutNode.getChildMeasurables$ui();
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public float mo4552toDpGaN1DYA(long j) {
            return this.$$delegate_0.mo4552toDpGaN1DYA(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo4553toDpu2uoSUM(float f) {
            return this.$$delegate_0.mo4553toDpu2uoSUM(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDpSize-k-rfVVM */
        public long mo4555toDpSizekrfVVM(long j) {
            return this.$$delegate_0.mo4555toDpSizekrfVVM(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public float mo4556toPxR2X_6o(long j) {
            return this.$$delegate_0.mo4556toPxR2X_6o(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx-0680j_4 */
        public float mo4557toPx0680j_4(float f) {
            return this.$$delegate_0.mo4557toPx0680j_4(f);
        }

        @Override // androidx.compose.ui.unit.Density
        public Rect toRect(DpRect dpRect) {
            return this.$$delegate_0.toRect(dpRect);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSize-XkaWNTQ */
        public long mo4558toSizeXkaWNTQ(long j) {
            return this.$$delegate_0.mo4558toSizeXkaWNTQ(j);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toSp-0xMU5do */
        public long mo4559toSp0xMU5do(float f) {
            return this.$$delegate_0.mo4559toSp0xMU5do(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public long mo4560toSpkPz2Gy4(float f) {
            return this.$$delegate_0.mo4560toSpkPz2Gy4(f);
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super RulerScope, Unit> rulers, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
            return this.$$delegate_0.layout(width, height, alignmentLines, rulers, placementBlock);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo4554toDpu2uoSUM(int i) {
            return this.$$delegate_0.mo4554toDpu2uoSUM(i);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public long mo4561toSpkPz2Gy4(int i) {
            return this.$$delegate_0.mo4561toSpkPz2Gy4(i);
        }
    }

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B0\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0015\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020:¢\u0006\u0004\b;\u0010<R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR'\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R \u0010'\u001a\b\u0012\u0004\u0012\u00020\u00190(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001b\"\u0004\b/\u0010\u001dR$\u00101\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u00198F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b2\u0010\u001b\"\u0004\b3\u0010\u001dR\u0013\u00104\u001a\u0004\u0018\u000105¢\u0006\b\n\u0000\u001a\u0004\b6\u00107¨\u0006="}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$NodeState;", "", "slotId", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "composition", "Landroidx/compose/runtime/ReusableComposition;", "<init>", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/ReusableComposition;)V", "getSlotId", "()Ljava/lang/Object;", "setSlotId", "(Ljava/lang/Object;)V", "getContent", "()Lkotlin/jvm/functions/Function2;", "setContent", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getComposition", "()Landroidx/compose/runtime/ReusableComposition;", "setComposition", "(Landroidx/compose/runtime/ReusableComposition;)V", "forceRecompose", "", "getForceRecompose", "()Z", "setForceRecompose", "(Z)V", "forceReuse", "getForceReuse", "setForceReuse", "pausedComposition", "Landroidx/compose/runtime/PausedComposition;", "getPausedComposition", "()Landroidx/compose/runtime/PausedComposition;", "setPausedComposition", "(Landroidx/compose/runtime/PausedComposition;)V", "activeState", "Landroidx/compose/runtime/MutableState;", "getActiveState", "()Landroidx/compose/runtime/MutableState;", "setActiveState", "(Landroidx/compose/runtime/MutableState;)V", "composedWithReusableContentHost", "getComposedWithReusableContentHost", "setComposedWithReusableContentHost", "value", "active", "getActive", "setActive", "operations", "Landroidx/collection/MutableIntList;", "getOperations", "()Landroidx/collection/MutableIntList;", "record", "op", "Landroidx/compose/ui/layout/SLOperation;", "record-Fsph7yY", "(I)V", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class NodeState {
        private MutableState<Boolean> activeState;
        private boolean composedWithReusableContentHost;
        private ReusableComposition composition;
        private Function2<? super Composer, ? super Integer, Unit> content;
        private boolean forceRecompose;
        private boolean forceReuse;
        private final MutableIntList operations;
        private PausedComposition pausedComposition;
        private Object slotId;

        public NodeState(Object obj, Function2<? super Composer, ? super Integer, Unit> function2, ReusableComposition reusableComposition) {
            this.slotId = obj;
            this.content = function2;
            this.composition = reusableComposition;
            this.activeState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
            this.operations = null;
        }

        public final boolean getActive() {
            return this.activeState.getValue().booleanValue();
        }

        public final MutableState<Boolean> getActiveState() {
            return this.activeState;
        }

        public final boolean getComposedWithReusableContentHost() {
            return this.composedWithReusableContentHost;
        }

        public final ReusableComposition getComposition() {
            return this.composition;
        }

        public final Function2<Composer, Integer, Unit> getContent() {
            return this.content;
        }

        public final boolean getForceRecompose() {
            return this.forceRecompose;
        }

        public final boolean getForceReuse() {
            return this.forceReuse;
        }

        public final MutableIntList getOperations() {
            return this.operations;
        }

        public final PausedComposition getPausedComposition() {
            return this.pausedComposition;
        }

        public final Object getSlotId() {
            return this.slotId;
        }

        /* JADX INFO: renamed from: record-Fsph7yY, reason: not valid java name */
        public final void m4631recordFsph7yY(int op) {
            MutableIntList mutableIntList = this.operations;
            if (mutableIntList == null) {
                return;
            }
            mutableIntList.add(op);
            if (((IntList) mutableIntList)._size >= 50) {
                mutableIntList.removeRange(0, 10);
            }
        }

        public final void setActive(boolean z) {
            this.activeState.setValue(Boolean.valueOf(z));
        }

        public final void setActiveState(MutableState<Boolean> mutableState) {
            this.activeState = mutableState;
        }

        public final void setComposedWithReusableContentHost(boolean z) {
            this.composedWithReusableContentHost = z;
        }

        public final void setComposition(ReusableComposition reusableComposition) {
            this.composition = reusableComposition;
        }

        public final void setContent(Function2<? super Composer, ? super Integer, Unit> function2) {
            this.content = function2;
        }

        public final void setForceRecompose(boolean z) {
            this.forceRecompose = z;
        }

        public final void setForceReuse(boolean z) {
            this.forceReuse = z;
        }

        public final void setPausedComposition(PausedComposition pausedComposition) {
            this.pausedComposition = pausedComposition;
        }

        public final void setSlotId(Object obj) {
            this.slotId = obj;
        }

        public /* synthetic */ NodeState(Object obj, Function2 function2, ReusableComposition reusableComposition, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, function2, (i & 4) != 0 ? null : reusableComposition);
        }
    }

    public final SubcomposeLayoutState.PrecomposedSlotHandle precompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
        precompose(slotId, content, false);
        return createPrecomposedSlotHandle(slotId);
    }

    private final void subcompose(LayoutNode node, Object slotId, boolean pausable, Function2<? super Composer, ? super Integer, Unit> content) {
        MutableScatterMap<LayoutNode, NodeState> mutableScatterMap = this.nodeToNodeState;
        Object obj = mutableScatterMap.get(node);
        if (obj == null) {
            NodeState nodeState = new NodeState(slotId, ComposableSingletons$SubcomposeLayoutKt.INSTANCE.getLambda$641200809$ui(), null, 4, null);
            mutableScatterMap.set(node, nodeState);
            obj = nodeState;
        }
        NodeState nodeState2 = (NodeState) obj;
        boolean z = nodeState2.getContent() != content;
        if (nodeState2.getPausedComposition() != null) {
            if (z) {
                cancelPausedPrecomposition(nodeState2);
            } else if (pausable) {
                return;
            } else {
                applyPausedPrecomposition(nodeState2, true);
            }
        }
        ReusableComposition composition = nodeState2.getComposition();
        boolean hasInvalidations = composition != null ? composition.getHasInvalidations() : true;
        if (z || hasInvalidations || nodeState2.getForceRecompose()) {
            nodeState2.setContent(content);
            subcompose(node, nodeState2, pausable);
            nodeState2.setForceRecompose(false);
        }
    }

    private final void subcompose(LayoutNode node, final NodeState nodeState, boolean pausable) {
        if (!(nodeState.getPausedComposition() == null)) {
            InlineClassHelperKt.throwIllegalArgumentException("new subcompose call while paused composition is still active");
        }
        Snapshot.Companion companion = Snapshot.INSTANCE;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            LayoutNode layoutNode = this.root;
            layoutNode.ignoreRemeasureRequests = true;
            ReusableComposition composition = nodeState.getComposition();
            CompositionContext compositionContext = this.compositionContext;
            if (compositionContext != null) {
                if (composition == null || composition.isDisposed()) {
                    if (pausable) {
                        composition = SubcompositionKt.createPausableSubcomposition(node, compositionContext);
                    } else {
                        composition = SubcompositionKt.createSubcomposition(node, compositionContext);
                    }
                }
                nodeState.setComposition(composition);
                final Function2<? super Composer, ? super Integer, Unit> content = nodeState.getContent();
                if (getOutOfFrameExecutor() != null) {
                    nodeState.setComposedWithReusableContentHost(false);
                } else {
                    nodeState.setComposedWithReusableContentHost(true);
                    content = ComposableLambdaKt.composableLambdaInstance(1524156494, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$subcompose$4$1$composable$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer, int i) {
                            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                                composer.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1524156494, i, -1, "androidx.compose.ui.layout.LayoutNodeSubcompositionsState.subcompose.<anonymous>.<anonymous>.<anonymous> (SubcomposeLayout.kt:706)");
                            }
                            boolean active = nodeState.getActive();
                            Function2<Composer, Integer, Unit> function2 = content;
                            composer.startReusableGroup(ComposerKt.reuseKey, Boolean.valueOf(active));
                            boolean zChanged = composer.changed(active);
                            if (active) {
                                function2.invoke(composer, 0);
                            } else {
                                composer.deactivateToEndGroup(zChanged);
                            }
                            composer.endReusableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
                if (pausable) {
                    composition.getClass();
                    if (nodeState.getForceReuse()) {
                        nodeState.setPausedComposition(((PausableComposition) composition).setPausableContentWithReuse(content));
                    } else {
                        nodeState.setPausedComposition(((PausableComposition) composition).setPausableContent(content));
                    }
                } else if (nodeState.getForceReuse()) {
                    composition.setContentWithReuse(content);
                } else {
                    composition.setContent(content);
                }
                nodeState.setForceReuse(false);
                Unit unit = Unit.INSTANCE;
                layoutNode.ignoreRemeasureRequests = false;
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                return;
            }
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("parent composition reference not set");
            throw new KotlinNothingValueException();
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            throw th;
        }
    }
}
