package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.AnchoredDraggableDefaults;
import androidx.compose.foundation.gestures.AnchoredDraggableKt;
import androidx.compose.foundation.gestures.AnchoredDraggableState;
import androidx.compose.material3.SwipeToDismissBoxState;
import androidx.compose.material3.SwipeToDismissBoxValue;
import androidx.compose.material3.internal.DraggableAnchorsKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.core.app.NotificationCompat;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 42\u00020\u0001:\u00014B4\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\n\u0010\u000bBR\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u0005\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\n\u0010\u0010J\u0006\u0010\u001f\u001a\u00020\u0006J\u0016\u0010-\u001a\u00020.2\u0006\u0010#\u001a\u00020\u0003H\u0086@¢\u0006\u0002\u0010/J\u000e\u00100\u001a\u00020.H\u0086@¢\u0006\u0002\u00101J\u0016\u00102\u001a\u00020.2\u0006\u00103\u001a\u00020\u0003H\u0086@¢\u0006\u0002\u0010/R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010 \u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b$\u0010\"R\u0011\u0010%\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b&\u0010\"R\u0017\u0010'\u001a\u00020\u00068G¢\u0006\f\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010\u001eR\u0011\u0010+\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b,\u0010\"¨\u00065"}, d2 = {"Landroidx/compose/material3/SwipeToDismissBoxState;", "", "initialValue", "Landroidx/compose/material3/SwipeToDismissBoxValue;", "positionalThreshold", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "totalDistance", "<init>", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Lkotlin/jvm/functions/Function1;)V", "density", "Landroidx/compose/ui/unit/Density;", "confirmValueChange", "", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Landroidx/compose/ui/unit/Density;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "anchoredDraggableState", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "getAnchoredDraggableState$material3", "()Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "getPositionalThreshold$material3", "()Lkotlin/jvm/functions/Function1;", "setPositionalThreshold$material3", "(Lkotlin/jvm/functions/Function1;)V", "useFlingBehavior", "getUseFlingBehavior$material3", "()Z", "offset", "getOffset$material3", "()F", "requireOffset", "currentValue", "getCurrentValue", "()Landroidx/compose/material3/SwipeToDismissBoxValue;", "targetValue", "getTargetValue", "settledValue", "getSettledValue", NotificationCompat.CATEGORY_PROGRESS, "getProgress$annotations", "()V", "getProgress", "dismissDirection", "getDismissDirection", "snapTo", "", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reset", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dismiss", "direction", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SwipeToDismissBoxState {
    private final AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState;
    public Function1<? super Float, Float> positionalThreshold;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Deprecated(level = DeprecationLevel.WARNING, message = DraggableAnchorsKt.ConfirmValueChangeDeprecated, replaceWith = @ReplaceWith(expression = "SwipeToDismissBoxState(initialValue, density, positionalThreshold)", imports = {}))
    public SwipeToDismissBoxState(SwipeToDismissBoxValue swipeToDismissBoxValue, final Density density, Function1<? super SwipeToDismissBoxValue, Boolean> function1, Function1<? super Float, Float> function2) {
        AnchoredDraggableDefaults anchoredDraggableDefaults = AnchoredDraggableDefaults.INSTANCE;
        this.anchoredDraggableState = AnchoredDraggableKt.AnchoredDraggableState(swipeToDismissBoxValue, function2, new Function0() { // from class: pvd
            public final Object invoke() {
                return Float.valueOf(SwipeToDismissBoxState.b(density));
            }
        }, anchoredDraggableDefaults.getSnapAnimationSpec(), anchoredDraggableDefaults.getDecayAnimationSpec(), function1);
    }

    public static boolean a(SwipeToDismissBoxValue swipeToDismissBoxValue) {
        return true;
    }

    public static float b(Density density) {
        return density.mo4557toPx0680j_4(SwipeToDismissBoxKt.DismissVelocityThreshold);
    }

    public static /* synthetic */ void getProgress$annotations() {
    }

    public final Object dismiss(SwipeToDismissBoxValue swipeToDismissBoxValue, Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, swipeToDismissBoxValue, (AnimationSpec) null, continuation, 2, (Object) null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    public final AnchoredDraggableState<SwipeToDismissBoxValue> getAnchoredDraggableState$material3() {
        return this.anchoredDraggableState;
    }

    public final SwipeToDismissBoxValue getCurrentValue() {
        return (SwipeToDismissBoxValue) this.anchoredDraggableState.getCurrentValue();
    }

    public final SwipeToDismissBoxValue getDismissDirection() {
        if (getOffset$material3() == 0.0f || Float.isNaN(getOffset$material3())) {
            return SwipeToDismissBoxValue.Settled;
        }
        return getOffset$material3() > 0.0f ? SwipeToDismissBoxValue.StartToEnd : SwipeToDismissBoxValue.EndToStart;
    }

    public final float getOffset$material3() {
        return this.anchoredDraggableState.getOffset();
    }

    public final Function1<Float, Float> getPositionalThreshold$material3() {
        Function1 function1 = this.positionalThreshold;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("positionalThreshold");
        return null;
    }

    public final float getProgress() {
        return this.anchoredDraggableState.getProgress();
    }

    public final SwipeToDismissBoxValue getSettledValue() {
        return (SwipeToDismissBoxValue) this.anchoredDraggableState.getSettledValue();
    }

    public final SwipeToDismissBoxValue getTargetValue() {
        return (SwipeToDismissBoxValue) this.anchoredDraggableState.getTargetValue();
    }

    public final boolean getUseFlingBehavior$material3() {
        return this.positionalThreshold != null;
    }

    public final float requireOffset() {
        return this.anchoredDraggableState.requireOffset();
    }

    public final Object reset(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, SwipeToDismissBoxValue.Settled, (AnimationSpec) null, continuation, 2, (Object) null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    public final void setPositionalThreshold$material3(Function1<? super Float, Float> function1) {
        this.positionalThreshold = function1;
    }

    public final Object snapTo(SwipeToDismissBoxValue swipeToDismissBoxValue, Continuation<? super Unit> continuation) {
        Object objSnapTo = AnchoredDraggableKt.snapTo(this.anchoredDraggableState, swipeToDismissBoxValue, continuation);
        return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JS\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\t2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\f0\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J5\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\f0\t¨\u0006\u0012"}, d2 = {"Landroidx/compose/material3/SwipeToDismissBoxState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/SwipeToDismissBoxState;", "Landroidx/compose/material3/SwipeToDismissBoxValue;", "confirmValueChange", "Lkotlin/Function1;", "", "positionalThreshold", "", "Lkotlin/ParameterName;", "name", "totalDistance", "density", "Landroidx/compose/ui/unit/Density;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static SwipeToDismissBoxState a(Density density, Function1 function1, Function1 function2, SwipeToDismissBoxValue swipeToDismissBoxValue) {
            return new SwipeToDismissBoxState(swipeToDismissBoxValue, density, function1, function2);
        }

        public static SwipeToDismissBoxValue b(SaverScope saverScope, SwipeToDismissBoxState swipeToDismissBoxState) {
            return swipeToDismissBoxState.getCurrentValue();
        }

        public static SwipeToDismissBoxState c(Function1 function1, SwipeToDismissBoxValue swipeToDismissBoxValue) {
            return new SwipeToDismissBoxState(swipeToDismissBoxValue, function1);
        }

        public static SwipeToDismissBoxValue d(SaverScope saverScope, SwipeToDismissBoxState swipeToDismissBoxState) {
            return swipeToDismissBoxState.getCurrentValue();
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = DraggableAnchorsKt.ConfirmValueChangeDeprecated, replaceWith = @ReplaceWith(expression = "Saver(positionalThreshold, density)", imports = {}))
        public final Saver<SwipeToDismissBoxState, SwipeToDismissBoxValue> Saver(final Function1<? super SwipeToDismissBoxValue, Boolean> confirmValueChange, final Function1<? super Float, Float> positionalThreshold, final Density density) {
            return SaverKt.Saver(new Function2() { // from class: svd
                public final Object invoke(Object obj, Object obj2) {
                    return SwipeToDismissBoxState.Companion.b((SaverScope) obj, (SwipeToDismissBoxState) obj2);
                }
            }, new Function1() { // from class: tvd
                public final Object invoke(Object obj) {
                    return SwipeToDismissBoxState.Companion.a(density, confirmValueChange, positionalThreshold, (SwipeToDismissBoxValue) obj);
                }
            });
        }

        private Companion() {
        }

        public final Saver<SwipeToDismissBoxState, SwipeToDismissBoxValue> Saver(final Function1<? super Float, Float> positionalThreshold) {
            return SaverKt.Saver(new Function2() { // from class: qvd
                public final Object invoke(Object obj, Object obj2) {
                    return SwipeToDismissBoxState.Companion.d((SaverScope) obj, (SwipeToDismissBoxState) obj2);
                }
            }, new Function1() { // from class: rvd
                public final Object invoke(Object obj) {
                    return SwipeToDismissBoxState.Companion.c(positionalThreshold, (SwipeToDismissBoxValue) obj);
                }
            });
        }
    }

    public /* synthetic */ SwipeToDismissBoxState(SwipeToDismissBoxValue swipeToDismissBoxValue, Density density, Function1 function1, Function1 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(swipeToDismissBoxValue, density, (i & 4) != 0 ? new Function1() { // from class: ovd
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SwipeToDismissBoxState.a((SwipeToDismissBoxValue) obj));
            }
        } : function1, function2);
    }

    public SwipeToDismissBoxState(SwipeToDismissBoxValue swipeToDismissBoxValue, Function1<? super Float, Float> function1) {
        this.anchoredDraggableState = new AnchoredDraggableState<>(swipeToDismissBoxValue);
        setPositionalThreshold$material3(function1);
    }
}
