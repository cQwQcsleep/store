package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.material3.SheetState;
import androidx.compose.material3.SheetValue;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0007\u0018\u0000 L2\u00020\u0001:\u0001LBU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eBC\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u0011J\u0006\u0010\u001e\u001a\u00020\u0006J\u000e\u0010#\u001a\u00020$H\u0086@¢\u0006\u0002\u0010%J\u000e\u0010&\u001a\u00020$H\u0086@¢\u0006\u0002\u0010%J\u000e\u0010'\u001a\u00020$H\u0086@¢\u0006\u0002\u0010%J\u000e\u0010(\u001a\u00020$H\u0086@¢\u0006\u0002\u0010%J0\u0010)\u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\t2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060+2\b\b\u0002\u0010,\u001a\u00020\u0006H\u0080@¢\u0006\u0004\b-\u0010.J\u0018\u0010/\u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\tH\u0080@¢\u0006\u0004\b0\u00101J\u0018\u00102\u001a\u00020$2\u0006\u0010,\u001a\u00020\u0006H\u0080@¢\u0006\u0004\b3\u00104R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R \u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\f\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0017\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\u001d\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u001f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b \u0010\u0013R\u0011\u0010!\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0013R \u00105\u001a\b\u0012\u0004\u0012\u00020\u000606X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R \u0010;\u001a\b\u0012\u0004\u0012\u00020\t0<X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR \u0010D\u001a\b\u0012\u0004\u0012\u00020\u00060+X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR \u0010I\u001a\b\u0012\u0004\u0012\u00020\u00060+X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010F\"\u0004\bK\u0010H¨\u0006M"}, d2 = {"Landroidx/compose/material3/SheetState;", "", "skipPartiallyExpanded", "", "positionalThreshold", "Lkotlin/Function0;", "", "velocityThreshold", "initialValue", "Landroidx/compose/material3/SheetValue;", "confirmValueChange", "Lkotlin/Function1;", "skipHiddenState", "<init>", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SheetValue;Lkotlin/jvm/functions/Function1;Z)V", "density", "Landroidx/compose/ui/unit/Density;", "(ZLandroidx/compose/ui/unit/Density;Landroidx/compose/material3/SheetValue;Lkotlin/jvm/functions/Function1;Z)V", "getSkipPartiallyExpanded$material3", "()Z", "getConfirmValueChange$material3", "()Lkotlin/jvm/functions/Function1;", "getSkipHiddenState$material3", "currentValue", "getCurrentValue", "()Landroidx/compose/material3/SheetValue;", "targetValue", "getTargetValue", "isVisible", "isAnimationRunning", "requireOffset", "hasExpandedState", "getHasExpandedState", "hasPartiallyExpandedState", "getHasPartiallyExpandedState", "expand", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "partialExpand", "show", "hide", "animateTo", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "velocity", "animateTo$material3", "(Landroidx/compose/material3/SheetValue;Landroidx/compose/animation/core/FiniteAnimationSpec;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "snapTo", "snapTo$material3", "(Landroidx/compose/material3/SheetValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "settle", "settle$material3", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "anchoredDraggableMotionSpec", "Landroidx/compose/animation/core/AnimationSpec;", "getAnchoredDraggableMotionSpec$material3", "()Landroidx/compose/animation/core/AnimationSpec;", "setAnchoredDraggableMotionSpec$material3", "(Landroidx/compose/animation/core/AnimationSpec;)V", "anchoredDraggableState", "Landroidx/compose/material3/internal/AnchoredDraggableState;", "getAnchoredDraggableState$material3", "()Landroidx/compose/material3/internal/AnchoredDraggableState;", "setAnchoredDraggableState$material3", "(Landroidx/compose/material3/internal/AnchoredDraggableState;)V", "offset", "getOffset$material3", "()F", "showMotionSpec", "getShowMotionSpec$material3", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "setShowMotionSpec$material3", "(Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "hideMotionSpec", "getHideMotionSpec$material3", "setHideMotionSpec$material3", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SheetState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AnimationSpec<Float> anchoredDraggableMotionSpec;
    private AnchoredDraggableState<SheetValue> anchoredDraggableState;
    private final Function1<SheetValue, Boolean> confirmValueChange;
    private FiniteAnimationSpec<Float> hideMotionSpec;
    private FiniteAnimationSpec<Float> showMotionSpec;
    private final boolean skipHiddenState;
    private final boolean skipPartiallyExpanded;

    /* JADX WARN: Multi-variable type inference failed */
    public SheetState(boolean z, final Function0<Float> function0, Function0<Float> function1, SheetValue sheetValue, Function1<? super SheetValue, Boolean> function2, boolean z2) {
        this.skipPartiallyExpanded = z;
        this.confirmValueChange = function2;
        this.skipHiddenState = z2;
        if (z && sheetValue == SheetValue.PartiallyExpanded) {
            w01.a("The initial value must not be set to PartiallyExpanded if skipPartiallyExpanded is set to true.");
            throw null;
        }
        if (z2 && sheetValue == SheetValue.Hidden) {
            w01.a("The initial value must not be set to Hidden if skipHiddenState is set to true.");
            throw null;
        }
        this.anchoredDraggableMotionSpec = SheetDefaultsKt.BottomSheetAnimationSpec;
        this.anchoredDraggableState = new AnchoredDraggableState<>(sheetValue, new Function1() { // from class: c9d
            public final Object invoke(Object obj) {
                return Float.valueOf(SheetState.c(function0, ((Float) obj).floatValue()));
            }
        }, function1, new Function0() { // from class: d9d
            public final Object invoke() {
                return SheetState.b(this.b);
            }
        }, function2);
        this.showMotionSpec = AnimationSpecKt.snap$default(0, 1, (Object) null);
        this.hideMotionSpec = AnimationSpecKt.snap$default(0, 1, (Object) null);
    }

    public static boolean a(SheetValue sheetValue) {
        return true;
    }

    public static /* synthetic */ Object animateTo$material3$default(SheetState sheetState, SheetValue sheetValue, FiniteAnimationSpec finiteAnimationSpec, float f, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            f = sheetState.anchoredDraggableState.getLastVelocity();
        }
        return sheetState.animateTo$material3(sheetValue, finiteAnimationSpec, f, continuation);
    }

    public static AnimationSpec b(SheetState sheetState) {
        return sheetState.anchoredDraggableMotionSpec;
    }

    public static float c(Function0 function0, float f) {
        return ((Number) function0.invoke()).floatValue();
    }

    public static float d(Density density) {
        return density.mo4557toPx0680j_4(BottomSheetDefaults.INSTANCE.m123getVelocityThresholdD9Ej5fM$material3());
    }

    public static boolean e(SheetValue sheetValue) {
        return true;
    }

    public static float f(Density density) {
        return density.mo4557toPx0680j_4(BottomSheetDefaults.INSTANCE.m120getPositionalThresholdD9Ej5fM$material3());
    }

    public final Object animateTo$material3(SheetValue sheetValue, FiniteAnimationSpec<Float> finiteAnimationSpec, float f, Continuation<? super Unit> continuation) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(this.anchoredDraggableState, sheetValue, null, new SheetState$animateTo$2(this, f, finiteAnimationSpec, null), continuation, 2, null);
        return objAnchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnchoredDrag$default : Unit.INSTANCE;
    }

    public final Object expand(Continuation<? super Unit> continuation) {
        Object objAnimateTo$material3$default;
        Function1<SheetValue, Boolean> function1 = this.confirmValueChange;
        SheetValue sheetValue = SheetValue.Expanded;
        return (((Boolean) function1.invoke(sheetValue)).booleanValue() && (objAnimateTo$material3$default = animateTo$material3$default(this, sheetValue, this.showMotionSpec, 0.0f, continuation, 4, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAnimateTo$material3$default : Unit.INSTANCE;
    }

    public final AnimationSpec<Float> getAnchoredDraggableMotionSpec$material3() {
        return this.anchoredDraggableMotionSpec;
    }

    public final AnchoredDraggableState<SheetValue> getAnchoredDraggableState$material3() {
        return this.anchoredDraggableState;
    }

    public final Function1<SheetValue, Boolean> getConfirmValueChange$material3() {
        return this.confirmValueChange;
    }

    public final SheetValue getCurrentValue() {
        return this.anchoredDraggableState.getCurrentValue();
    }

    public final boolean getHasExpandedState() {
        return this.anchoredDraggableState.getAnchors().hasAnchorFor(SheetValue.Expanded);
    }

    public final boolean getHasPartiallyExpandedState() {
        return this.anchoredDraggableState.getAnchors().hasAnchorFor(SheetValue.PartiallyExpanded);
    }

    public final FiniteAnimationSpec<Float> getHideMotionSpec$material3() {
        return this.hideMotionSpec;
    }

    public final float getOffset$material3() {
        return this.anchoredDraggableState.getOffset();
    }

    public final FiniteAnimationSpec<Float> getShowMotionSpec$material3() {
        return this.showMotionSpec;
    }

    /* JADX INFO: renamed from: getSkipHiddenState$material3, reason: from getter */
    public final boolean getSkipHiddenState() {
        return this.skipHiddenState;
    }

    /* JADX INFO: renamed from: getSkipPartiallyExpanded$material3, reason: from getter */
    public final boolean getSkipPartiallyExpanded() {
        return this.skipPartiallyExpanded;
    }

    public final SheetValue getTargetValue() {
        return this.anchoredDraggableState.getTargetValue();
    }

    public final Object hide(Continuation<? super Unit> continuation) {
        Object objAnimateTo$material3$default;
        if (this.skipHiddenState) {
            k2d.a("Attempted to animate to hidden when skipHiddenState was enabled. Set skipHiddenState to false to use this function.");
            return null;
        }
        Function1<SheetValue, Boolean> function1 = this.confirmValueChange;
        SheetValue sheetValue = SheetValue.Hidden;
        return (((Boolean) function1.invoke(sheetValue)).booleanValue() && (objAnimateTo$material3$default = animateTo$material3$default(this, sheetValue, this.hideMotionSpec, 0.0f, continuation, 4, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAnimateTo$material3$default : Unit.INSTANCE;
    }

    public final boolean isAnimationRunning() {
        return this.anchoredDraggableState.isAnimationRunning();
    }

    public final boolean isVisible() {
        return this.anchoredDraggableState.getCurrentValue() != SheetValue.Hidden;
    }

    public final Object partialExpand(Continuation<? super Unit> continuation) {
        Object objAnimateTo$material3$default;
        if (this.skipPartiallyExpanded) {
            k2d.a("Attempted to animate to partial expanded when skipPartiallyExpanded was enabled. Set skipPartiallyExpanded to false to use this function.");
            return null;
        }
        Function1<SheetValue, Boolean> function1 = this.confirmValueChange;
        SheetValue sheetValue = SheetValue.PartiallyExpanded;
        return (((Boolean) function1.invoke(sheetValue)).booleanValue() && (objAnimateTo$material3$default = animateTo$material3$default(this, sheetValue, this.hideMotionSpec, 0.0f, continuation, 4, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAnimateTo$material3$default : Unit.INSTANCE;
    }

    public final float requireOffset() {
        return this.anchoredDraggableState.requireOffset();
    }

    public final void setAnchoredDraggableMotionSpec$material3(AnimationSpec<Float> animationSpec) {
        this.anchoredDraggableMotionSpec = animationSpec;
    }

    public final void setAnchoredDraggableState$material3(AnchoredDraggableState<SheetValue> anchoredDraggableState) {
        this.anchoredDraggableState = anchoredDraggableState;
    }

    public final void setHideMotionSpec$material3(FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.hideMotionSpec = finiteAnimationSpec;
    }

    public final void setShowMotionSpec$material3(FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.showMotionSpec = finiteAnimationSpec;
    }

    public final Object settle$material3(float f, Continuation<? super Unit> continuation) {
        Object obj = this.anchoredDraggableState.settle(f, continuation);
        return obj == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj : Unit.INSTANCE;
    }

    public final Object show(Continuation<? super Unit> continuation) {
        Object objAnimateTo$material3$default;
        SheetValue sheetValue = getHasPartiallyExpandedState() ? SheetValue.PartiallyExpanded : SheetValue.Expanded;
        return (((Boolean) this.confirmValueChange.invoke(sheetValue)).booleanValue() && (objAnimateTo$material3$default = animateTo$material3$default(this, sheetValue, this.showMotionSpec, 0.0f, continuation, 4, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAnimateTo$material3$default : Unit.INSTANCE;
    }

    public final Object snapTo$material3(SheetValue sheetValue, Continuation<? super Unit> continuation) {
        Object objSnapTo = AnchoredDraggableKt.snapTo(this.anchoredDraggableState, sheetValue, continuation);
        return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JR\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u0010\u001a\u00020\tJ@\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\tH\u0007¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/SheetState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/SheetState;", "Landroidx/compose/material3/SheetValue;", "skipPartiallyExpanded", "", "positionalThreshold", "Lkotlin/Function0;", "", "velocityThreshold", "confirmValueChange", "Lkotlin/Function1;", "skipHiddenState", "density", "Landroidx/compose/ui/unit/Density;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static SheetState a(boolean z, Function0 function0, Function0 function1, Function1 function2, boolean z2, SheetValue sheetValue) {
            return new SheetState(z, function0, function1, sheetValue, function2, z2);
        }

        public static float b(Density density) {
            return density.mo4557toPx0680j_4(BottomSheetDefaults.INSTANCE.m120getPositionalThresholdD9Ej5fM$material3());
        }

        public static SheetValue c(SaverScope saverScope, SheetState sheetState) {
            return sheetState.getCurrentValue();
        }

        public static float d(Density density) {
            return density.mo4557toPx0680j_4(BottomSheetDefaults.INSTANCE.m123getVelocityThresholdD9Ej5fM$material3());
        }

        public final Saver<SheetState, SheetValue> Saver(final boolean skipPartiallyExpanded, final Function0<Float> positionalThreshold, final Function0<Float> velocityThreshold, final Function1<? super SheetValue, Boolean> confirmValueChange, final boolean skipHiddenState) {
            return SaverKt.Saver(new Function2() { // from class: i9d
                public final Object invoke(Object obj, Object obj2) {
                    return SheetState.Companion.c((SaverScope) obj, (SheetState) obj2);
                }
            }, new Function1() { // from class: j9d
                public final Object invoke(Object obj) {
                    return SheetState.Companion.a(skipPartiallyExpanded, positionalThreshold, velocityThreshold, confirmValueChange, skipHiddenState, (SheetValue) obj);
                }
            });
        }

        private Companion() {
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility.")
        public final /* synthetic */ Saver Saver(boolean skipPartiallyExpanded, Function1 confirmValueChange, final Density density, boolean skipHiddenState) {
            return Saver(skipPartiallyExpanded, new Function0() { // from class: k9d
                public final Object invoke() {
                    return Float.valueOf(SheetState.Companion.b(density));
                }
            }, new Function0() { // from class: l9d
                public final Object invoke() {
                    return Float.valueOf(SheetState.Companion.d(density));
                }
            }, confirmValueChange, skipHiddenState);
        }
    }

    public /* synthetic */ SheetState(boolean z, Function0 function0, Function0 function1, SheetValue sheetValue, Function1 function2, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, function0, function1, (i & 8) != 0 ? SheetValue.Hidden : sheetValue, (i & 16) != 0 ? new Function1() { // from class: e9d
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SheetState.a((SheetValue) obj));
            }
        } : function2, (i & 32) != 0 ? false : z2);
    }

    public /* synthetic */ SheetState(boolean z, Density density, SheetValue sheetValue, Function1 function1, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, density, (i & 4) != 0 ? SheetValue.Hidden : sheetValue, (i & 8) != 0 ? new Function1() { // from class: f9d
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SheetState.e((SheetValue) obj));
            }
        } : function1, (i & 16) != 0 ? false : z2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility.")
    public /* synthetic */ SheetState(boolean z, final Density density, SheetValue sheetValue, Function1 function1, boolean z2) {
        this(z, new Function0() { // from class: g9d
            public final Object invoke() {
                return Float.valueOf(SheetState.f(density));
            }
        }, new Function0() { // from class: h9d
            public final Object invoke() {
                return Float.valueOf(SheetState.d(density));
            }
        }, sheetValue, function1, z2);
    }
}
