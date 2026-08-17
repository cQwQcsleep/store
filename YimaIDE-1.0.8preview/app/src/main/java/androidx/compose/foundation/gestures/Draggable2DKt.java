package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.Draggable2DKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u001a!\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0088\u0001\u0010\b\u001a\u00020\t*\u00020\t2\u0006\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\f2#\b\u0002\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00050\u00032#\b\u0002\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0002\u0010\u0017\u001a\u00020\fH\u0007\")\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\")\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Draggable2DState", "Landroidx/compose/foundation/gestures/Draggable2DState;", "onDelta", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "", "rememberDraggable2DState", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/Draggable2DState;", "draggable2D", "Landroidx/compose/ui/Modifier;", "state", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "startDragImmediately", "onDragStarted", "Lkotlin/ParameterName;", "name", "startedPosition", "onDragStopped", "Landroidx/compose/ui/unit/Velocity;", "velocity", "reverseDirection", "NoOpOnDragStart", "NoOpOnDragStop", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class Draggable2DKt {
    private static final Function1<Offset, Unit> NoOpOnDragStart = new Function1() { // from class: gy3
        public final Object invoke(Object obj) {
            return Draggable2DKt.a((Offset) obj);
        }
    };
    private static final Function1<Velocity, Unit> NoOpOnDragStop = new Function1() { // from class: hy3
        public final Object invoke(Object obj) {
            return Draggable2DKt.c((Velocity) obj);
        }
    };

    public static final Draggable2DState Draggable2DState(Function1<? super Offset, Unit> function1) {
        return new DefaultDraggable2DState(function1);
    }

    public static Unit a(Offset offset) {
        return Unit.INSTANCE;
    }

    public static Unit c(Velocity velocity) {
        return Unit.INSTANCE;
    }

    public static final Modifier draggable2D(Modifier modifier, Draggable2DState draggable2DState, boolean z, MutableInteractionSource mutableInteractionSource, boolean z2, Function1<? super Offset, Unit> function1, Function1<? super Velocity, Unit> function2, boolean z3) {
        return modifier.then(new Draggable2DElement(draggable2DState, z, mutableInteractionSource, z2, function1, function2, z3));
    }

    public static final Draggable2DState rememberDraggable2DState(Function1<? super Offset, Unit> function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1150277615, i, -1, "androidx.compose.foundation.gestures.rememberDraggable2DState (Draggable2D.kt:106)");
        }
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composer, i & 14);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = Draggable2DState(new Function1() { // from class: fy3
                public final Object invoke(Object obj) {
                    return Draggable2DKt.rememberDraggable2DState$lambda$0$0(stateRememberUpdatedState, (Offset) obj);
                }
            });
            composer.updateRememberedValue(objRememberedValue);
        }
        Draggable2DState draggable2DState = (Draggable2DState) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return draggable2DState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberDraggable2DState$lambda$0$0(State state, Offset offset) {
        ((Function1) state.getValue()).invoke(offset);
        return Unit.INSTANCE;
    }
}
