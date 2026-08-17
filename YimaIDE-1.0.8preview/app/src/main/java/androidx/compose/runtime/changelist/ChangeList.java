package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.apk.ApkUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000eJ,\u0010\u000f\u001a\u00020\u000e2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0018\u0010 \u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\u0007J \u0010#\u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010!2\u0006\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u0007J\u0018\u0010&\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\b\u0010\u0019\u001a\u0004\u0018\u00010!J\u000e\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u0007J\u0006\u0010)\u001a\u00020\u000eJ\u0006\u0010*\u001a\u00020\u000eJ\u0010\u0010+\u001a\u00020\u000e2\b\u0010,\u001a\u0004\u0018\u00010!J\u0006\u0010-\u001a\u00020\u000eJ\u000e\u0010.\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%J\u0006\u0010/\u001a\u00020\u000eJ\u0006\u00100\u001a\u00020\u000eJ\u0006\u00101\u001a\u00020\u000eJ\u0016\u00102\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u00103\u001a\u000204J\u001e\u00102\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206J\u000e\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u0007J\"\u00109\u001a\u00020\u000e2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000e0;2\u0006\u0010=\u001a\u00020<J\u0010\u0010>\u001a\u00020\u000e2\b\u0010?\u001a\u0004\u0018\u00010!J>\u0010@\u001a\u00020\u000e\"\u0004\b\u0000\u0010A\"\u0004\b\u0001\u0010B2\u0006\u0010\u0019\u001a\u0002HB2\u001d\u0010C\u001a\u0019\u0012\u0004\u0012\u0002HA\u0012\u0004\u0012\u0002HB\u0012\u0004\u0012\u00020\u000e0D¢\u0006\u0002\bE¢\u0006\u0002\u0010FJ\u0016\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u0007J\u001e\u0010J\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0007J\u000e\u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020\u0007J\u000e\u0010N\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u0007J\u001b\u0010O\u001a\u00020\u000e2\u000e\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0Q¢\u0006\u0002\u0010RJ\u0014\u0010S\u001a\u00020\u000e2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\u000e0UJ\u0016\u0010V\u001a\u00020\u000e2\u0006\u0010W\u001a\u00020X2\u0006\u0010$\u001a\u00020%J\u001e\u0010Y\u001a\u00020\u000e2\u000e\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0Z2\u0006\u0010[\u001a\u00020XJ(\u0010\\\u001a\u00020\u000e2\b\u0010]\u001a\u0004\u0018\u00010^2\u0006\u0010_\u001a\u00020`2\u0006\u00103\u001a\u00020a2\u0006\u0010K\u001a\u00020aJ\u001e\u0010b\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020c2\u0006\u0010_\u001a\u00020`2\u0006\u0010d\u001a\u00020aJ\u0006\u0010e\u001a\u00020\u000eJ\u001a\u0010f\u001a\u00020\u000e2\u0006\u0010g\u001a\u00020\u00002\n\b\u0002\u0010[\u001a\u0004\u0018\u00010XJ\u0010\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020iH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006k"}, d2 = {"Landroidx/compose/runtime/changelist/ChangeList;", "Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "<init>", "()V", "operations", "Landroidx/compose/runtime/changelist/Operations;", "size", "", "getSize", "()I", "isEmpty", "", "isNotEmpty", "clear", "", "executeAndFlushAllPendingChanges", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/RememberManager;", "errorContext", "Landroidx/compose/runtime/changelist/OperationErrorContext;", "pushRemember", "value", "Landroidx/compose/runtime/RememberObserverHolder;", "pushRememberPausingScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "pushStartResumingScope", "pushEndResumingScope", "pushUpdateValue", "", "groupSlotIndex", "pushUpdateAnchoredValue", "anchor", "Landroidx/compose/runtime/Anchor;", "pushAppendValue", "pushTrimValues", "count", "pushResetSlots", "pushDeactivateCurrentGroup", "pushUpdateAuxData", ApkUtil.NAME_data, "pushEnsureRootStarted", "pushEnsureGroupStarted", "pushEndCurrentGroup", "pushSkipToEndOfCurrentGroup", "pushRemoveCurrentGroup", "pushInsertSlots", "from", "Landroidx/compose/runtime/SlotTable;", "fixups", "Landroidx/compose/runtime/changelist/FixupList;", "pushMoveCurrentGroup", "offset", "pushEndCompositionScope", "action", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "composition", "pushUseNode", "node", "pushUpdateNode", "T", "V", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "pushRemoveNode", "removeFrom", "moveCount", "pushMoveNode", "to", "pushAdvanceSlotsBy", "distance", "pushUps", "pushDowns", "nodes", "", "([Ljava/lang/Object;)V", "pushSideEffect", "effect", "Lkotlin/Function0;", "pushDetermineMovableContentNodeIndex", "effectiveNodeIndexOut", "Landroidx/compose/runtime/internal/IntRef;", "pushCopyNodesToNewAnchorLocation", "", "effectiveNodeIndex", "pushCopySlotTableToAnchorLocation", "resolvedState", "Landroidx/compose/runtime/MovableContentState;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "Landroidx/compose/runtime/MovableContentStateReference;", "pushReleaseMovableGroupAtCurrent", "Landroidx/compose/runtime/ControlledComposition;", "reference", "pushEndMovableContentPlacement", "pushExecuteOperationsIn", "changeList", "toDebugString", "", "linePrefix", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ChangeList extends OperationsDebugStringFormattable {
    public static final int $stable = 8;
    private final Operations operations = new Operations();

    public static /* synthetic */ void pushExecuteOperationsIn$default(ChangeList changeList, ChangeList changeList2, IntRef intRef, int i, Object obj) {
        if ((i & 2) != 0) {
            intRef = null;
        }
        changeList.pushExecuteOperationsIn(changeList2, intRef);
    }

    public final void clear() {
        this.operations.clear();
    }

    public final void executeAndFlushAllPendingChanges(Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
        this.operations.executeAndFlushAllPendingOperations(applier, slots, rememberManager, errorContext);
    }

    public final int getSize() {
        return this.operations.getOpCodesSize();
    }

    public final boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public final boolean isNotEmpty() {
        return this.operations.isNotEmpty();
    }

    public final void pushAdvanceSlotsBy(int distance) {
        Operations operations = this.operations;
        Operation.AdvanceSlotsBy advanceSlotsBy = Operation.AdvanceSlotsBy.INSTANCE;
        operations.pushOp(advanceSlotsBy);
        Operations operationsM2450constructorimpl = Operations.WriteScope.m2450constructorimpl(operations);
        operationsM2450constructorimpl.intArgs[operationsM2450constructorimpl.intArgsSize - operationsM2450constructorimpl.opCodes[operationsM2450constructorimpl.opCodesSize - 1].getInts()] = distance;
        operations.ensureAllArgumentsPushedFor(advanceSlotsBy);
    }

    public final void pushAppendValue(Anchor anchor, Object value) {
        Operations operations = this.operations;
        Operation.AppendValue appendValue = Operation.AppendValue.INSTANCE;
        operations.pushOp(appendValue);
        Operations.WriteScope.m2459setObjects4uCC6AY(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), anchor, Operation.ObjectParameter.m2427constructorimpl(1), value);
        operations.ensureAllArgumentsPushedFor(appendValue);
    }

    public final void pushCopyNodesToNewAnchorLocation(List<? extends Object> nodes, IntRef effectiveNodeIndex) {
        if (nodes.isEmpty()) {
            return;
        }
        Operations operations = this.operations;
        Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        operations.pushOp(copyNodesToNewAnchorLocation);
        Operations.WriteScope.m2459setObjects4uCC6AY(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(1), nodes, Operation.ObjectParameter.m2427constructorimpl(0), effectiveNodeIndex);
        operations.ensureAllArgumentsPushedFor(copyNodesToNewAnchorLocation);
    }

    public final void pushCopySlotTableToAnchorLocation(MovableContentState resolvedState, CompositionContext parentContext, MovableContentStateReference from, MovableContentStateReference to) {
        Operations operations = this.operations;
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        operations.pushOp(copySlotTableToAnchorLocation);
        Operations.WriteScope.m2460setObjectsOGa0p1M(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), resolvedState, Operation.ObjectParameter.m2427constructorimpl(1), parentContext, Operation.ObjectParameter.m2427constructorimpl(3), to, Operation.ObjectParameter.m2427constructorimpl(2), from);
        operations.ensureAllArgumentsPushedFor(copySlotTableToAnchorLocation);
    }

    public final void pushDeactivateCurrentGroup() {
        this.operations.push(Operation.DeactivateCurrentGroup.INSTANCE);
    }

    public final void pushDetermineMovableContentNodeIndex(IntRef effectiveNodeIndexOut, Anchor anchor) {
        Operations operations = this.operations;
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        operations.pushOp(determineMovableContentNodeIndex);
        Operations.WriteScope.m2459setObjects4uCC6AY(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), effectiveNodeIndexOut, Operation.ObjectParameter.m2427constructorimpl(1), anchor);
        operations.ensureAllArgumentsPushedFor(determineMovableContentNodeIndex);
    }

    public final void pushDowns(Object[] nodes) {
        if (nodes.length == 0) {
            return;
        }
        Operations operations = this.operations;
        Operation.Downs downs = Operation.Downs.INSTANCE;
        operations.pushOp(downs);
        Operations.WriteScope.m2458setObjectDKhxnng(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), nodes);
        operations.ensureAllArgumentsPushedFor(downs);
    }

    public final void pushEndCompositionScope(Function1<? super Composition, Unit> action, Composition composition) {
        Operations operations = this.operations;
        Operation.EndCompositionScope endCompositionScope = Operation.EndCompositionScope.INSTANCE;
        operations.pushOp(endCompositionScope);
        Operations.WriteScope.m2459setObjects4uCC6AY(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), action, Operation.ObjectParameter.m2427constructorimpl(1), composition);
        operations.ensureAllArgumentsPushedFor(endCompositionScope);
    }

    public final void pushEndCurrentGroup() {
        this.operations.push(Operation.EndCurrentGroup.INSTANCE);
    }

    public final void pushEndMovableContentPlacement() {
        this.operations.push(Operation.EndMovableContentPlacement.INSTANCE);
    }

    public final void pushEndResumingScope(RecomposeScopeImpl scope) {
        Operations operations = this.operations;
        Operation.EndResumingScope endResumingScope = Operation.EndResumingScope.INSTANCE;
        operations.pushOp(endResumingScope);
        Operations.WriteScope.m2458setObjectDKhxnng(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), scope);
        operations.ensureAllArgumentsPushedFor(endResumingScope);
    }

    public final void pushEnsureGroupStarted(Anchor anchor) {
        Operations operations = this.operations;
        Operation.EnsureGroupStarted ensureGroupStarted = Operation.EnsureGroupStarted.INSTANCE;
        operations.pushOp(ensureGroupStarted);
        Operations.WriteScope.m2458setObjectDKhxnng(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), anchor);
        operations.ensureAllArgumentsPushedFor(ensureGroupStarted);
    }

    public final void pushEnsureRootStarted() {
        this.operations.push(Operation.EnsureRootGroupStarted.INSTANCE);
    }

    public final void pushExecuteOperationsIn(ChangeList changeList, IntRef effectiveNodeIndex) {
        if (changeList.isNotEmpty()) {
            Operations operations = this.operations;
            Operation.ApplyChangeList applyChangeList = Operation.ApplyChangeList.INSTANCE;
            operations.pushOp(applyChangeList);
            Operations.WriteScope.m2459setObjects4uCC6AY(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), changeList, Operation.ObjectParameter.m2427constructorimpl(1), effectiveNodeIndex);
            operations.ensureAllArgumentsPushedFor(applyChangeList);
        }
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable from, FixupList fixups) {
        Operations operations = this.operations;
        Operation.InsertSlotsWithFixups insertSlotsWithFixups = Operation.InsertSlotsWithFixups.INSTANCE;
        operations.pushOp(insertSlotsWithFixups);
        Operations.WriteScope.m2461setObjectst7hvbck(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), anchor, Operation.ObjectParameter.m2427constructorimpl(1), from, Operation.ObjectParameter.m2427constructorimpl(2), fixups);
        operations.ensureAllArgumentsPushedFor(insertSlotsWithFixups);
    }

    public final void pushMoveCurrentGroup(int offset) {
        Operations operations = this.operations;
        Operation.MoveCurrentGroup moveCurrentGroup = Operation.MoveCurrentGroup.INSTANCE;
        operations.pushOp(moveCurrentGroup);
        Operations operationsM2450constructorimpl = Operations.WriteScope.m2450constructorimpl(operations);
        operationsM2450constructorimpl.intArgs[operationsM2450constructorimpl.intArgsSize - operationsM2450constructorimpl.opCodes[operationsM2450constructorimpl.opCodesSize - 1].getInts()] = offset;
        operations.ensureAllArgumentsPushedFor(moveCurrentGroup);
    }

    public final void pushMoveNode(int to, int from, int count) {
        Operations operations = this.operations;
        Operation.MoveNode moveNode = Operation.MoveNode.INSTANCE;
        operations.pushOp(moveNode);
        Operations operationsM2450constructorimpl = Operations.WriteScope.m2450constructorimpl(operations);
        int ints = operationsM2450constructorimpl.intArgsSize - operationsM2450constructorimpl.opCodes[operationsM2450constructorimpl.opCodesSize - 1].getInts();
        int[] iArr = operationsM2450constructorimpl.intArgs;
        iArr[ints + 1] = to;
        iArr[ints] = from;
        iArr[ints + 2] = count;
        operations.ensureAllArgumentsPushedFor(moveNode);
    }

    public final void pushReleaseMovableGroupAtCurrent(ControlledComposition composition, CompositionContext parentContext, MovableContentStateReference reference) {
        Operations operations = this.operations;
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        operations.pushOp(releaseMovableGroupAtCurrent);
        Operations.WriteScope.m2461setObjectst7hvbck(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), composition, Operation.ObjectParameter.m2427constructorimpl(1), parentContext, Operation.ObjectParameter.m2427constructorimpl(2), reference);
        operations.ensureAllArgumentsPushedFor(releaseMovableGroupAtCurrent);
    }

    public final void pushRemember(RememberObserverHolder value) {
        Operations operations = this.operations;
        Operation.Remember remember = Operation.Remember.INSTANCE;
        operations.pushOp(remember);
        Operations.WriteScope.m2458setObjectDKhxnng(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), value);
        operations.ensureAllArgumentsPushedFor(remember);
    }

    public final void pushRememberPausingScope(RecomposeScopeImpl scope) {
        Operations operations = this.operations;
        Operation.RememberPausingScope rememberPausingScope = Operation.RememberPausingScope.INSTANCE;
        operations.pushOp(rememberPausingScope);
        Operations.WriteScope.m2458setObjectDKhxnng(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), scope);
        operations.ensureAllArgumentsPushedFor(rememberPausingScope);
    }

    public final void pushRemoveCurrentGroup() {
        this.operations.push(Operation.RemoveCurrentGroup.INSTANCE);
    }

    public final void pushRemoveNode(int removeFrom, int moveCount) {
        Operations operations = this.operations;
        Operation.RemoveNode removeNode = Operation.RemoveNode.INSTANCE;
        operations.pushOp(removeNode);
        Operations operationsM2450constructorimpl = Operations.WriteScope.m2450constructorimpl(operations);
        int ints = operationsM2450constructorimpl.intArgsSize - operationsM2450constructorimpl.opCodes[operationsM2450constructorimpl.opCodesSize - 1].getInts();
        int[] iArr = operationsM2450constructorimpl.intArgs;
        iArr[ints] = removeFrom;
        iArr[ints + 1] = moveCount;
        operations.ensureAllArgumentsPushedFor(removeNode);
    }

    public final void pushResetSlots() {
        this.operations.push(Operation.ResetSlots.INSTANCE);
    }

    public final void pushSideEffect(Function0<Unit> effect) {
        Operations operations = this.operations;
        Operation.SideEffect sideEffect = Operation.SideEffect.INSTANCE;
        operations.pushOp(sideEffect);
        Operations.WriteScope.m2458setObjectDKhxnng(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), effect);
        operations.ensureAllArgumentsPushedFor(sideEffect);
    }

    public final void pushSkipToEndOfCurrentGroup() {
        this.operations.push(Operation.SkipToEndOfCurrentGroup.INSTANCE);
    }

    public final void pushStartResumingScope(RecomposeScopeImpl scope) {
        Operations operations = this.operations;
        Operation.StartResumingScope startResumingScope = Operation.StartResumingScope.INSTANCE;
        operations.pushOp(startResumingScope);
        Operations.WriteScope.m2458setObjectDKhxnng(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), scope);
        operations.ensureAllArgumentsPushedFor(startResumingScope);
    }

    public final void pushTrimValues(int count) {
        Operations operations = this.operations;
        Operation.TrimParentValues trimParentValues = Operation.TrimParentValues.INSTANCE;
        operations.pushOp(trimParentValues);
        Operations operationsM2450constructorimpl = Operations.WriteScope.m2450constructorimpl(operations);
        operationsM2450constructorimpl.intArgs[operationsM2450constructorimpl.intArgsSize - operationsM2450constructorimpl.opCodes[operationsM2450constructorimpl.opCodesSize - 1].getInts()] = count;
        operations.ensureAllArgumentsPushedFor(trimParentValues);
    }

    public final void pushUpdateAnchoredValue(Object value, Anchor anchor, int groupSlotIndex) {
        Operations operations = this.operations;
        Operation.UpdateAnchoredValue updateAnchoredValue = Operation.UpdateAnchoredValue.INSTANCE;
        operations.pushOp(updateAnchoredValue);
        Operations operationsM2450constructorimpl = Operations.WriteScope.m2450constructorimpl(operations);
        Operations.WriteScope.m2459setObjects4uCC6AY(operationsM2450constructorimpl, Operation.ObjectParameter.m2427constructorimpl(0), value, Operation.ObjectParameter.m2427constructorimpl(1), anchor);
        operationsM2450constructorimpl.intArgs[operationsM2450constructorimpl.intArgsSize - operationsM2450constructorimpl.opCodes[operationsM2450constructorimpl.opCodesSize - 1].getInts()] = groupSlotIndex;
        operations.ensureAllArgumentsPushedFor(updateAnchoredValue);
    }

    public final void pushUpdateAuxData(Object data) {
        Operations operations = this.operations;
        Operation.UpdateAuxData updateAuxData = Operation.UpdateAuxData.INSTANCE;
        operations.pushOp(updateAuxData);
        Operations.WriteScope.m2458setObjectDKhxnng(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), data);
        operations.ensureAllArgumentsPushedFor(updateAuxData);
    }

    public final <T, V> void pushUpdateNode(V value, Function2<? super T, ? super V, Unit> block) {
        Operations operations = this.operations;
        Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
        operations.pushOp(updateNode);
        Operations operationsM2450constructorimpl = Operations.WriteScope.m2450constructorimpl(operations);
        int iM2427constructorimpl = Operation.ObjectParameter.m2427constructorimpl(0);
        int iM2427constructorimpl2 = Operation.ObjectParameter.m2427constructorimpl(1);
        block.getClass();
        Operations.WriteScope.m2459setObjects4uCC6AY(operationsM2450constructorimpl, iM2427constructorimpl, value, iM2427constructorimpl2, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(block, 2));
        operations.ensureAllArgumentsPushedFor(updateNode);
    }

    public final void pushUpdateValue(Object value, int groupSlotIndex) {
        Operations operations = this.operations;
        Operation.UpdateValue updateValue = Operation.UpdateValue.INSTANCE;
        operations.pushOp(updateValue);
        Operations operationsM2450constructorimpl = Operations.WriteScope.m2450constructorimpl(operations);
        Operations.WriteScope.m2458setObjectDKhxnng(operationsM2450constructorimpl, Operation.ObjectParameter.m2427constructorimpl(0), value);
        operationsM2450constructorimpl.intArgs[operationsM2450constructorimpl.intArgsSize - operationsM2450constructorimpl.opCodes[operationsM2450constructorimpl.opCodesSize - 1].getInts()] = groupSlotIndex;
        operations.ensureAllArgumentsPushedFor(updateValue);
    }

    public final void pushUps(int count) {
        Operations operations = this.operations;
        Operation.Ups ups = Operation.Ups.INSTANCE;
        operations.pushOp(ups);
        Operations operationsM2450constructorimpl = Operations.WriteScope.m2450constructorimpl(operations);
        operationsM2450constructorimpl.intArgs[operationsM2450constructorimpl.intArgsSize - operationsM2450constructorimpl.opCodes[operationsM2450constructorimpl.opCodesSize - 1].getInts()] = count;
        operations.ensureAllArgumentsPushedFor(ups);
    }

    public final void pushUseNode(Object node) {
        if (node instanceof ComposeNodeLifecycleCallback) {
            this.operations.push(Operation.UseCurrentNode.INSTANCE);
        }
    }

    @Override // androidx.compose.runtime.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("ChangeList instance containing ");
        sb.append(getSize());
        sb.append(" operations");
        if (sb.length() > 0) {
            sb.append(":\n");
            sb.append(this.operations.toDebugString(linePrefix));
        }
        return sb.toString();
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable from) {
        Operations operations = this.operations;
        Operation.InsertSlots insertSlots = Operation.InsertSlots.INSTANCE;
        operations.pushOp(insertSlots);
        Operations.WriteScope.m2459setObjects4uCC6AY(Operations.WriteScope.m2450constructorimpl(operations), Operation.ObjectParameter.m2427constructorimpl(0), anchor, Operation.ObjectParameter.m2427constructorimpl(1), from);
        operations.ensureAllArgumentsPushedFor(insertSlots);
    }
}
