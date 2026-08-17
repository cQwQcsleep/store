package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0000¨\u0006\b"}, d2 = {"makeDefaultSelectionModifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "selectableId", "", "layoutCoordinates", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SelectionControllerKt {
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeDefaultSelectionModifier$longPressDragObserver$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeDefaultSelectionModifier$mouseSelectionObserver$1, java.lang.Object] */
    public static final Modifier makeDefaultSelectionModifier(final SelectionRegistrar selectionRegistrar, final long j, final Function0<? extends LayoutCoordinates> function0) {
        final ?? r0 = new TextDragObserver() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeDefaultSelectionModifier$longPressDragObserver$1
            private long dragTotalDistance;
            private long lastPosition;
            private SelectionAdjustment selectionAdjustmentMode;

            {
                Offset.Companion companion = Offset.Companion;
                this.lastPosition = companion.getZero-F1C5BW0();
                this.dragTotalDistance = companion.getZero-F1C5BW0();
                this.selectionAdjustmentMode = SelectionAdjustment.INSTANCE.getNone();
            }

            public final long getDragTotalDistance() {
                return this.dragTotalDistance;
            }

            public final long getLastPosition() {
                return this.lastPosition;
            }

            public final SelectionAdjustment getSelectionAdjustmentMode() {
                return this.selectionAdjustmentMode;
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                if (SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                    selectionRegistrar.notifySelectionUpdateEnd();
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1409onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo1410onDragk4lQ0M(long delta) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) function0.invoke();
                if (layoutCoordinates != null) {
                    SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                    long j2 = j;
                    if (layoutCoordinates.isAttached() && SelectionRegistrarKt.hasSelection(selectionRegistrar2, j2)) {
                        long j3 = Offset.plus-MK-Hz9U(this.dragTotalDistance, delta);
                        this.dragTotalDistance = j3;
                        long j4 = Offset.plus-MK-Hz9U(this.lastPosition, j3);
                        if (selectionRegistrar2.mo1799notifySelectionUpdatenjBpvok(layoutCoordinates, j4, this.lastPosition, false, this.selectionAdjustmentMode, true)) {
                            this.lastPosition = j4;
                            this.dragTotalDistance = Offset.Companion.getZero-F1C5BW0();
                        }
                    }
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public void mo1411onStart3MmeM6k(long startPoint, SelectionAdjustment selectionAdjustment) {
                this.selectionAdjustmentMode = selectionAdjustment;
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) function0.invoke();
                if (layoutCoordinates != null) {
                    SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                    if (!layoutCoordinates.isAttached()) {
                        return;
                    }
                    selectionRegistrar2.mo1800notifySelectionUpdateStartubNVwUQ(layoutCoordinates, startPoint, this.selectionAdjustmentMode, true);
                    this.lastPosition = startPoint;
                }
                if (SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                    this.dragTotalDistance = Offset.Companion.getZero-F1C5BW0();
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                if (SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                    selectionRegistrar.notifySelectionUpdateEnd();
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
            }

            public final void setDragTotalDistance(long j2) {
                this.dragTotalDistance = j2;
            }

            public final void setLastPosition(long j2) {
                this.lastPosition = j2;
            }

            public final void setSelectionAdjustmentMode(SelectionAdjustment selectionAdjustment) {
                this.selectionAdjustmentMode = selectionAdjustment;
            }
        };
        final ?? r1 = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeDefaultSelectionModifier$mouseSelectionObserver$1
            private long lastPosition = Offset.Companion.getZero-F1C5BW0();

            public final long getLastPosition() {
                return this.lastPosition;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onDrag-3MmeM6k */
            public boolean mo1662onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) function0.invoke();
                if (layoutCoordinates == null) {
                    return true;
                }
                SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                long j2 = j;
                if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar2, j2)) {
                    return false;
                }
                if (!selectionRegistrar2.mo1799notifySelectionUpdatenjBpvok(layoutCoordinates, dragPosition, this.lastPosition, false, adjustment, false)) {
                    return true;
                }
                this.lastPosition = dragPosition;
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            public void onDragDone() {
                selectionRegistrar.notifySelectionUpdateEnd();
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtend-k-4lQ0M */
            public boolean mo1663onExtendk4lQ0M(long downPosition) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) function0.invoke();
                if (layoutCoordinates == null) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                long j2 = j;
                if (!layoutCoordinates.isAttached()) {
                    return false;
                }
                if (selectionRegistrar2.mo1799notifySelectionUpdatenjBpvok(layoutCoordinates, downPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone(), false)) {
                    this.lastPosition = downPosition;
                }
                return SelectionRegistrarKt.hasSelection(selectionRegistrar2, j2);
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M */
            public boolean mo1664onExtendDragk4lQ0M(long dragPosition) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) function0.invoke();
                if (layoutCoordinates == null) {
                    return true;
                }
                SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                long j2 = j;
                if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar2, j2)) {
                    return false;
                }
                if (!selectionRegistrar2.mo1799notifySelectionUpdatenjBpvok(layoutCoordinates, dragPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone(), false)) {
                    return true;
                }
                this.lastPosition = dragPosition;
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onStart-9KIMszo */
            public boolean mo1665onStart9KIMszo(long downPosition, SelectionAdjustment adjustment, int clickCount) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) function0.invoke();
                if (layoutCoordinates == null) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                long j2 = j;
                if (!layoutCoordinates.isAttached()) {
                    return false;
                }
                selectionRegistrar2.mo1800notifySelectionUpdateStartubNVwUQ(layoutCoordinates, downPosition, adjustment, false);
                this.lastPosition = downPosition;
                return SelectionRegistrarKt.hasSelection(selectionRegistrar2, j2);
            }

            public final void setLastPosition(long j2) {
                this.lastPosition = j2;
            }
        };
        return SuspendingPointerInputFilterKt.pointerInput(Modifier.Companion, (Object) r1, (Object) r0, new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt.makeDefaultSelectionModifier.1
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objAwaitSelectionGestures = SelectionGesturesKt.awaitSelectionGestures(pointerInputScope, r1, r0, continuation);
                return objAwaitSelectionGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitSelectionGestures : Unit.INSTANCE;
            }
        });
    }
}
