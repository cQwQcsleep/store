package androidx.compose.material3;

import androidx.collection.IntList;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationResult;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u001dH\u0086@¢\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020\u0005H\u0002J\u0010\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0016H\u0002J\u001c\u0010)\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u001dH\u0086@¢\u0006\u0002\u0010\u001eJ.\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u00162\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u001d2\b\b\u0002\u0010,\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010-J\b\u00105\u001a\u00020\u001bH\u0002J\f\u00109\u001a\u00020\u0016*\u00020\u0016H\u0002J\f\u0010<\u001a\u00020/*\u00020\u0016H\u0002J\f\u0010=\u001a\u00020/*\u00020\u0016H\u0002J\u0010\u0010>\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u0016H\u0002R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0010R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020(0'X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00100\u001a\u00020/2\u0006\u0010.\u001a\u00020/8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104R$\u00106\u001a\u00020/2\u0006\u0010.\u001a\u00020/8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u00102\"\u0004\b8\u00104R\u000e\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010?\u001a\u00020\u0005X\u0096\u000f¢\u0006\f\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u0018\u0010C\u001a\u00020DX\u0096\u000f¢\u0006\f\u001a\u0004\bE\u00102\"\u0004\bF\u00104¨\u0006G"}, d2 = {"Landroidx/compose/material3/AnalogTimePickerState;", "Landroidx/compose/material3/TimePickerState;", "state", "userOverride", "Landroidx/compose/ui/node/Ref;", "", "<init>", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/node/Ref;)V", "getState", "()Landroidx/compose/material3/TimePickerState;", "getUserOverride", "()Landroidx/compose/ui/node/Ref;", "<set-?>", "Landroidx/compose/ui/unit/Dp;", "currentDiameter", "getCurrentDiameter-D9Ej5fM", "()F", "setCurrentDiameter-0680j_4", "(F)V", "currentDiameter$delegate", "Landroidx/compose/runtime/MutableState;", "currentAngle", "", "getCurrentAngle", "hourAngle", "minuteAngle", "animateToCurrent", "", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isUpdated", "clockFaceValues", "Landroidx/collection/IntList;", "getClockFaceValues", "()Landroidx/collection/IntList;", "endValueForAnimation", "new", "anim", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "onGestureEnd", "rotateTo", "angle", "animate", "(FLandroidx/compose/animation/core/AnimationSpec;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "value", "", "minute", "getMinute", "()I", "setMinute", "(I)V", "updateBaseStateMinute", "hour", "getHour", "setHour", "normalize", "mutex", "Landroidx/compose/foundation/MutatorMutex;", "toHour", "toMinute", "offsetAngle", "is24hour", "()Z", "set24hour", "(Z)V", "selection", "Landroidx/compose/material3/TimePickerSelectionMode;", "getSelection-yecRtBI", "setSelection-6_8s6DQ", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AnalogTimePickerState implements TimePickerState {
    public static final int $stable = 8;
    private Animatable<Float, AnimationVector1D> anim;

    /* JADX INFO: renamed from: currentDiameter$delegate, reason: from kotlin metadata */
    private final MutableState currentDiameter;
    private float hourAngle;
    private float minuteAngle;
    private final MutatorMutex mutex;
    private final TimePickerState state;
    private final Ref<Boolean> userOverride;

    /* JADX INFO: renamed from: androidx.compose.material3.AnalogTimePickerState$animateToCurrent$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/AnimationResult;", "", "Landroidx/compose/animation/core/AnimationVector1D;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.AnalogTimePickerState$animateToCurrent$2", f = "TimePicker.kt", i = {}, l = {754}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super AnimationResult<Float, AnimationVector1D>>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ float $end;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f, AnimationSpec<Float> animationSpec, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$end = f;
            this.$animationSpec = animationSpec;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return AnalogTimePickerState.this.new AnonymousClass2(this.$end, this.$animationSpec, continuation);
        }

        public final Object invoke(Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    return obj;
                }
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            Animatable animatable = AnalogTimePickerState.this.anim;
            Float fBoxFloat = Boxing.boxFloat(this.$end);
            AnimationSpec<Float> animationSpec = this.$animationSpec;
            this.label = 1;
            Object objAnimateTo$default = Animatable.animateTo$default(animatable, fBoxFloat, animationSpec, (Object) null, (Function1) null, this, 12, (Object) null);
            return objAnimateTo$default == coroutine_suspended ? coroutine_suspended : objAnimateTo$default;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnalogTimePickerState$onGestureEnd$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/AnimationResult;", "", "Landroidx/compose/animation/core/AnimationVector1D;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.AnalogTimePickerState$onGestureEnd$2", f = "TimePicker.kt", i = {}, l = {804}, m = "invokeSuspend", n = {}, s = {})
    public static final class C00362 extends SuspendLambda implements Function1<Continuation<? super AnimationResult<Float, AnimationVector1D>>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ float $end;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00362(float f, AnimationSpec<Float> animationSpec, Continuation<? super C00362> continuation) {
            super(1, continuation);
            this.$end = f;
            this.$animationSpec = animationSpec;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return AnalogTimePickerState.this.new C00362(this.$end, this.$animationSpec, continuation);
        }

        public final Object invoke(Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    return obj;
                }
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            Animatable animatable = AnalogTimePickerState.this.anim;
            Float fBoxFloat = Boxing.boxFloat(this.$end);
            AnimationSpec<Float> animationSpec = this.$animationSpec;
            this.label = 1;
            Object objAnimateTo$default = Animatable.animateTo$default(animatable, fBoxFloat, animationSpec, (Object) null, (Function1) null, this, 12, (Object) null);
            return objAnimateTo$default == coroutine_suspended ? coroutine_suspended : objAnimateTo$default;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnalogTimePickerState$rotateTo$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.AnalogTimePickerState$rotateTo$2", f = "TimePicker.kt", i = {}, l = {823, 826}, m = "invokeSuspend", n = {}, s = {})
    public static final class C00372 extends SuspendLambda implements Function1<Continuation<? super Object>, Object> {
        final /* synthetic */ float $angle;
        final /* synthetic */ boolean $animate;
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00372(float f, boolean z, AnimationSpec<Float> animationSpec, Continuation<? super C00372> continuation) {
            super(1, continuation);
            this.$angle = f;
            this.$animate = z;
            this.$animationSpec = animationSpec;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return AnalogTimePickerState.this.new C00372(this.$angle, this.$animate, this.$animationSpec, continuation);
        }

        public final Object invoke(Continuation<Object> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x00a3, code lost:
        
            if (r12.snapTo(r1, r11) == r0) goto L28;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                boolean zM1163equalsimpl0 = TimePickerSelectionMode.m1163equalsimpl0(AnalogTimePickerState.this.mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI());
                AnalogTimePickerState analogTimePickerState = AnalogTimePickerState.this;
                if (zM1163equalsimpl0) {
                    analogTimePickerState.hourAngle = (analogTimePickerState.toHour(this.$angle) % 12) * 0.5235988f;
                    TimePickerState state = AnalogTimePickerState.this.getState();
                    AnalogTimePickerState analogTimePickerState2 = AnalogTimePickerState.this;
                    state.setHour((analogTimePickerState2.toHour(analogTimePickerState2.hourAngle) % 12) + (TimePickerKt.isPm(AnalogTimePickerState.this) ? 12 : 0));
                } else {
                    analogTimePickerState.minuteAngle = analogTimePickerState.toMinute(this.$angle) * 0.10471976f;
                    TimePickerState state2 = AnalogTimePickerState.this.getState();
                    AnalogTimePickerState analogTimePickerState3 = AnalogTimePickerState.this;
                    state2.setMinute(analogTimePickerState3.toMinute(analogTimePickerState3.minuteAngle));
                }
                boolean z = this.$animate;
                AnalogTimePickerState analogTimePickerState4 = AnalogTimePickerState.this;
                if (z) {
                    float fEndValueForAnimation = analogTimePickerState4.endValueForAnimation(analogTimePickerState4.offsetAngle(this.$angle));
                    Animatable animatable = AnalogTimePickerState.this.anim;
                    Float fBoxFloat = Boxing.boxFloat(fEndValueForAnimation);
                    AnimationSpec<Float> animationSpec = this.$animationSpec;
                    this.label = 2;
                    Object objAnimateTo$default = Animatable.animateTo$default(animatable, fBoxFloat, animationSpec, (Object) null, (Function1) null, this, 12, (Object) null);
                    if (objAnimateTo$default != coroutine_suspended) {
                        return objAnimateTo$default;
                    }
                } else {
                    Animatable animatable2 = analogTimePickerState4.anim;
                    Float fBoxFloat2 = Boxing.boxFloat(AnalogTimePickerState.this.offsetAngle(this.$angle));
                    this.label = 1;
                }
                return coroutine_suspended;
            }
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return obj;
                }
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public AnalogTimePickerState(TimePickerState timePickerState, Ref<Boolean> ref) {
        this.state = timePickerState;
        this.userOverride = ref;
        this.currentDiameter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Dp.m6020boximpl(Dp.m6022constructorimpl(0.0f)), null, 2, null);
        this.hourAngle = ((timePickerState.getHour() % 12) * 0.5235988f) - 1.5707964f;
        this.minuteAngle = (timePickerState.getMinute() * 0.10471976f) - 1.5707964f;
        this.anim = AnimatableKt.Animatable$default(this.hourAngle, 0.0f, 2, (Object) null);
        this.mutex = new MutatorMutex();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float endValueForAnimation(float f) {
        float fFloatValue = ((Number) this.anim.getValue()).floatValue() - f;
        while (fFloatValue > 3.1415927f) {
            fFloatValue -= 6.2831855f;
        }
        while (fFloatValue <= -3.1415927f) {
            fFloatValue += 6.2831855f;
        }
        return ((Number) this.anim.getValue()).floatValue() - fFloatValue;
    }

    private final boolean isUpdated() {
        int iMo74getSelectionyecRtBI = mo74getSelectionyecRtBI();
        TimePickerSelectionMode.Companion companion = TimePickerSelectionMode.INSTANCE;
        if (TimePickerSelectionMode.m1163equalsimpl0(iMo74getSelectionyecRtBI, companion.m1167getHouryecRtBI()) && normalize(((Number) this.anim.getTargetValue()).floatValue()) == normalize(this.hourAngle)) {
            return false;
        }
        return (TimePickerSelectionMode.m1163equalsimpl0(mo74getSelectionyecRtBI(), companion.m1168getMinuteyecRtBI()) && normalize(((Number) this.anim.getTargetValue()).floatValue()) == normalize(this.minuteAngle)) ? false : true;
    }

    private final float normalize(float f) {
        double d = ((double) f) % 6.283185307179586d;
        if (d < 0.0d) {
            d += 6.283185307179586d;
        }
        return (float) d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float offsetAngle(float angle) {
        float f = angle + 1.5707964f;
        return f < 0.0f ? f + 6.2831855f : f;
    }

    public static /* synthetic */ Object rotateTo$default(AnalogTimePickerState analogTimePickerState, float f, AnimationSpec animationSpec, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return analogTimePickerState.rotateTo(f, animationSpec, z, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toHour(float f) {
        return ((int) ((((double) f) + 1.8325957218792777d) / 0.5235987901687622d)) % 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toMinute(float f) {
        return ((int) ((((double) f) + 1.6231562058117728d) / 0.10471975803375244d)) % 60;
    }

    private final void updateBaseStateMinute() {
        Snapshot.Companion companion = Snapshot.INSTANCE;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            this.state.setMinute(getMinute());
            Unit unit = Unit.INSTANCE;
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        }
    }

    public final Object animateToCurrent(AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        if (!isUpdated()) {
            return Unit.INSTANCE;
        }
        Object objMutate = this.mutex.mutate(MutatePriority.PreventUserInput, new AnonymousClass2(TimePickerSelectionMode.m1163equalsimpl0(mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI()) ? endValueForAnimation(this.hourAngle) : endValueForAnimation(this.minuteAngle), animationSpec, null), continuation);
        return objMutate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate : Unit.INSTANCE;
    }

    public final IntList getClockFaceValues() {
        return TimePickerSelectionMode.m1163equalsimpl0(mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI()) ? TimePickerKt.Minutes : TimePickerKt.Hours;
    }

    public final float getCurrentAngle() {
        return ((Number) this.anim.getValue()).floatValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getCurrentDiameter-D9Ej5fM, reason: not valid java name */
    public final float m73getCurrentDiameterD9Ej5fM() {
        return ((Dp) this.currentDiameter.getValue()).m6036unboximpl();
    }

    @Override // androidx.compose.material3.TimePickerState
    public int getHour() {
        return this.state.getHour();
    }

    @Override // androidx.compose.material3.TimePickerState
    public int getMinute() {
        return this.state.getMinute();
    }

    @Override // androidx.compose.material3.TimePickerState
    /* JADX INFO: renamed from: getSelection-yecRtBI, reason: not valid java name */
    public int mo74getSelectionyecRtBI() {
        return this.state.mo74getSelectionyecRtBI();
    }

    public final TimePickerState getState() {
        return this.state;
    }

    public final Ref<Boolean> getUserOverride() {
        return this.userOverride;
    }

    @Override // androidx.compose.material3.TimePickerState
    public boolean is24hour() {
        return this.state.is24hour();
    }

    public final Object onGestureEnd(AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        Object objMutate = this.mutex.mutate(MutatePriority.PreventUserInput, new C00362(endValueForAnimation(TimePickerSelectionMode.m1163equalsimpl0(mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI()) ? this.hourAngle : this.minuteAngle), animationSpec, null), continuation);
        return objMutate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate : Unit.INSTANCE;
    }

    public final Object rotateTo(float f, AnimationSpec<Float> animationSpec, boolean z, Continuation<? super Unit> continuation) {
        this.userOverride.setValue(Boxing.boxBoolean(false));
        Object objMutate = this.mutex.mutate(MutatePriority.UserInput, new C00372(f, z, animationSpec, null), continuation);
        return objMutate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.TimePickerState
    public void set24hour(boolean z) {
        this.state.set24hour(z);
    }

    /* JADX INFO: renamed from: setCurrentDiameter-0680j_4, reason: not valid java name */
    public final void m75setCurrentDiameter0680j_4(float f) {
        this.currentDiameter.setValue(Dp.m6020boximpl(f));
    }

    @Override // androidx.compose.material3.TimePickerState
    public void setHour(int i) {
        this.hourAngle = ((i % 12) * 0.5235988f) - 1.5707964f;
        this.state.setHour(i);
        if (TimePickerSelectionMode.m1163equalsimpl0(mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI())) {
            this.anim = AnimatableKt.Animatable$default(this.hourAngle, 0.0f, 2, (Object) null);
        }
    }

    @Override // androidx.compose.material3.TimePickerState
    public void setMinute(int i) {
        this.minuteAngle = (i * 0.10471976f) - 1.5707964f;
        this.state.setMinute(i);
        if (TimePickerSelectionMode.m1163equalsimpl0(mo74getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI())) {
            this.anim = AnimatableKt.Animatable$default(this.minuteAngle, 0.0f, 2, (Object) null);
        }
        updateBaseStateMinute();
    }

    @Override // androidx.compose.material3.TimePickerState
    /* JADX INFO: renamed from: setSelection-6_8s6DQ, reason: not valid java name */
    public void mo76setSelection6_8s6DQ(int i) {
        this.state.mo76setSelection6_8s6DQ(i);
    }

    public /* synthetic */ AnalogTimePickerState(TimePickerState timePickerState, Ref ref, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(timePickerState, (i & 2) != 0 ? new Ref() : ref);
    }
}
