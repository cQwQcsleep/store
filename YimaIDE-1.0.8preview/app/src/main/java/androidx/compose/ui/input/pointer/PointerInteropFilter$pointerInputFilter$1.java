package androidx.compose.ui.input.pointer;

import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J'\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0013\u001a\u00020\u000bH\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0002J\u0018\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"androidx/compose/ui/input/pointer/PointerInteropFilter$pointerInputFilter$1", "Landroidx/compose/ui/input/pointer/PointerInputFilter;", "state", "Landroidx/compose/ui/input/pointer/PointerInteropFilter$DispatchToViewState;", "shareWithSiblings", "", "getShareWithSiblings", "()Z", "lastEventDispatchedToInitialPass", "Landroidx/compose/ui/input/pointer/PointerEvent;", "onPointerEvent", "", "pointerEvent", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onCancel", "reset", "dispatchToView", "shouldConsume", "stopDispatching", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PointerInteropFilter$pointerInputFilter$1 extends PointerInputFilter {
    private PointerEvent lastEventDispatchedToInitialPass;
    private PointerInteropFilter.DispatchToViewState state = PointerInteropFilter.DispatchToViewState.Unknown;
    final /* synthetic */ PointerInteropFilter this$0;

    public PointerInteropFilter$pointerInputFilter$1(PointerInteropFilter pointerInteropFilter) {
        this.this$0 = pointerInteropFilter;
    }

    private final void dispatchToView(PointerEvent pointerEvent, boolean shouldConsume) {
        List<PointerInputChange> changes = pointerEvent.getChanges();
        List<PointerInputChange> list = changes;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (changes.get(i).isConsumed()) {
                stopDispatching(pointerEvent);
                return;
            }
        }
        LayoutCoordinates layoutCoordinates = getLayoutCoordinates();
        if (layoutCoordinates == null) {
            k2d.a("layoutCoordinates not set");
            return;
        }
        long jMo4616localToRootMKHz9U = layoutCoordinates.mo4616localToRootMKHz9U(Offset.INSTANCE.m2905getZeroF1C5BW0());
        final PointerInteropFilter pointerInteropFilter = this.this$0;
        PointerInteropUtils_androidKt.m4517toMotionEventScoped4ec7I(pointerEvent, jMo4616localToRootMKHz9U, new Function1<MotionEvent, Unit>() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter$pointerInputFilter$1$dispatchToView$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() != 0) {
                    pointerInteropFilter.getOnTouchEvent().invoke(motionEvent);
                } else {
                    this.this$0.state = ((Boolean) pointerInteropFilter.getOnTouchEvent().invoke(motionEvent)).booleanValue() ? PointerInteropFilter.DispatchToViewState.Dispatching : PointerInteropFilter.DispatchToViewState.NotDispatching;
                }
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((MotionEvent) obj);
                return Unit.INSTANCE;
            }
        });
        if (this.state == PointerInteropFilter.DispatchToViewState.Dispatching) {
            if (shouldConsume) {
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    changes.get(i2).consume();
                }
            }
            InternalPointerEvent internalPointerEvent$ui = pointerEvent.getInternalPointerEvent();
            if (internalPointerEvent$ui != null) {
                internalPointerEvent$ui.setSuppressMovementConsumption(!this.this$0.getDisallowIntercept());
            }
        }
    }

    private final void reset() {
        this.state = PointerInteropFilter.DispatchToViewState.Unknown;
        this.this$0.setDisallowIntercept$ui(false);
        this.lastEventDispatchedToInitialPass = null;
    }

    private final void stopDispatching(PointerEvent pointerEvent) {
        if (this.state == PointerInteropFilter.DispatchToViewState.Dispatching) {
            LayoutCoordinates layoutCoordinates = getLayoutCoordinates();
            if (layoutCoordinates == null) {
                k2d.a("layoutCoordinates not set");
                return;
            } else {
                long jMo4616localToRootMKHz9U = layoutCoordinates.mo4616localToRootMKHz9U(Offset.INSTANCE.m2905getZeroF1C5BW0());
                final PointerInteropFilter pointerInteropFilter = this.this$0;
                PointerInteropUtils_androidKt.m4516toCancelMotionEventScoped4ec7I(pointerEvent, jMo4616localToRootMKHz9U, new Function1<MotionEvent, Unit>() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter$pointerInputFilter$1$stopDispatching$1
                    {
                        super(1);
                    }

                    public final void invoke(MotionEvent motionEvent) {
                        pointerInteropFilter.getOnTouchEvent().invoke(motionEvent);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((MotionEvent) obj);
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        this.state = PointerInteropFilter.DispatchToViewState.NotDispatching;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputFilter
    public boolean getShareWithSiblings() {
        return true;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputFilter
    public void onCancel() {
        if (this.state == PointerInteropFilter.DispatchToViewState.Dispatching) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            final PointerInteropFilter pointerInteropFilter = this.this$0;
            PointerInteropUtils_androidKt.emptyCancelMotionEventScope(jUptimeMillis, new Function1<MotionEvent, Unit>() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter$pointerInputFilter$1$onCancel$1
                {
                    super(1);
                }

                public final void invoke(MotionEvent motionEvent) {
                    pointerInteropFilter.getOnTouchEvent().invoke(motionEvent);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((MotionEvent) obj);
                    return Unit.INSTANCE;
                }
            });
            reset();
        }
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputFilter
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo4479onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        boolean z;
        boolean z2;
        boolean z3;
        List<PointerInputChange> changes = pointerEvent.getChanges();
        List<PointerInputChange> list = changes;
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            }
            PointerInputChange pointerInputChange = changes.get(i);
            if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange) || PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                z = false;
                break;
            }
            i++;
        }
        if (!z) {
            z2 = false;
            break;
        }
        int size2 = list.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size2) {
                z2 = true;
                break;
            } else {
                if (changes.get(i2).isConsumed()) {
                    z2 = false;
                    break;
                }
                i2++;
            }
        }
        if (this.this$0.getDisallowIntercept()) {
            z3 = true;
            break;
        }
        int size3 = list.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size3) {
                if (!z2) {
                    z3 = false;
                    break;
                }
                break;
            } else {
                PointerInputChange pointerInputChange2 = changes.get(i3);
                if (!PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange2) && !PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange2)) {
                    i3++;
                }
            }
            z3 = true;
            break;
        }
        if (this.state != PointerInteropFilter.DispatchToViewState.NotDispatching) {
            if (pass == PointerEventPass.Initial && z3) {
                this.lastEventDispatchedToInitialPass = pointerEvent;
                dispatchToView(pointerEvent, !z || this.this$0.getDisallowIntercept());
            }
            if (pass == PointerEventPass.Main && z && Intrinsics.areEqual(pointerEvent, this.lastEventDispatchedToInitialPass) && this.this$0.getDisallowIntercept()) {
                int size4 = list.size();
                for (int i4 = 0; i4 < size4; i4++) {
                    changes.get(i4).consume();
                }
            }
            if (pass == PointerEventPass.Final && !z3 && !Intrinsics.areEqual(pointerEvent, this.lastEventDispatchedToInitialPass)) {
                dispatchToView(pointerEvent, true);
            }
        }
        if (pass == PointerEventPass.Final) {
            int size5 = list.size();
            int i5 = 0;
            while (true) {
                if (i5 >= size5) {
                    reset();
                    break;
                } else if (!PointerEventKt.changedToUpIgnoreConsumed(changes.get(i5))) {
                    break;
                } else {
                    i5++;
                }
            }
            if (Intrinsics.areEqual(pointerEvent, this.lastEventDispatchedToInitialPass) && z) {
                int size6 = list.size();
                for (int i6 = 0; i6 < size6; i6++) {
                    if (changes.get(i6).isConsumed()) {
                        if (this.this$0.getDisallowIntercept()) {
                            break;
                        }
                        stopDispatching(pointerEvent);
                        return;
                    }
                }
                int size7 = list.size();
                for (int i7 = 0; i7 < size7; i7++) {
                    changes.get(i7).consume();
                }
            }
        }
    }
}
