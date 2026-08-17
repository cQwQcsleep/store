package androidx.compose.material3;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.material3.SliderState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 |2\u00020\u0001:\u0001|B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010 \u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0003H\u0002J?\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#2'\u0010$\u001a#\b\u0001\u0012\u0004\u0012\u00020&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0'\u0012\u0006\u0012\u0004\u0018\u00010(0%¢\u0006\u0002\b)H\u0096@¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u0003H\u0016J\u001d\u0010`\u001a\u00020\b2\u0006\u0010a\u001a\u00020\u00052\u0006\u0010b\u001a\u00020\u0005H\u0000¢\u0006\u0002\bcJ\u0017\u0010f\u001a\u00020\b2\u0006\u0010g\u001a\u00020hH\u0000¢\u0006\u0004\bi\u0010jJ \u0010v\u001a\u00020\u00032\u0006\u0010w\u001a\u00020\u00032\u0006\u0010x\u001a\u00020\u00032\u0006\u0010y\u001a\u00020\u0003H\u0002J \u0010z\u001a\u00020\u00032\u0006\u0010w\u001a\u00020\u00032\u0006\u0010x\u001a\u00020\u00032\u0006\u0010{\u001a\u00020\u0003H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R+\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0018\"\u0004\b\u001f\u0010\u001aR(\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001c\u00103\u001a\u0002048GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00105\"\u0004\b6\u00107R\u0014\u00108\u001a\u000209X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R+\u0010<\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\b=\u0010\u000e\"\u0004\b>\u0010?R+\u0010B\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010A\u001a\u0004\bC\u0010\u000e\"\u0004\bD\u0010?R\u001a\u0010F\u001a\u000204X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u00105\"\u0004\bH\u00107R+\u0010I\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00058@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bL\u0010A\u001a\u0004\bJ\u0010\u000e\"\u0004\bK\u0010?R+\u0010M\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00058@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bP\u0010A\u001a\u0004\bN\u0010\u000e\"\u0004\bO\u0010?R\u001a\u0010Q\u001a\u00020RX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001a\u0010W\u001a\u000204X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u00105\"\u0004\bY\u00107R\u0011\u0010Z\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b[\u0010\u0018R+\u0010\\\u001a\u0002042\u0006\u0010\u0015\u001a\u0002048F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b^\u0010_\u001a\u0004\b\\\u00105\"\u0004\b]\u00107R\u001a\u0010d\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\be\u0010\u0010R+\u0010k\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bn\u0010\u001c\u001a\u0004\bl\u0010\u0018\"\u0004\bm\u0010\u001aR+\u0010o\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\br\u0010\u001c\u001a\u0004\bp\u0010\u0018\"\u0004\bq\u0010\u001aR\u000e\u0010s\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020uX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006}"}, d2 = {"Landroidx/compose/material3/SliderState;", "Landroidx/compose/foundation/gestures/DraggableState;", "value", "", "steps", "", "onValueChangeFinished", "Lkotlin/Function0;", "", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "<init>", "(FILkotlin/jvm/functions/Function0;Lkotlin/ranges/ClosedFloatingPointRange;)V", "getSteps", "()I", "getOnValueChangeFinished", "()Lkotlin/jvm/functions/Function0;", "setOnValueChangeFinished", "(Lkotlin/jvm/functions/Function0;)V", "getValueRange", "()Lkotlin/ranges/ClosedFloatingPointRange;", "<set-?>", "valueState", "getValueState", "()F", "setValueState", "(F)V", "valueState$delegate", "Landroidx/compose/runtime/MutableFloatState;", "newVal", "getValue", "setValue", "calculateSnappedValue", "drag", "dragPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/DragScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchRawDelta", "delta", "onValueChange", "Lkotlin/Function1;", "getOnValueChange", "()Lkotlin/jvm/functions/Function1;", "setOnValueChange", "(Lkotlin/jvm/functions/Function1;)V", "shouldAutoSnap", "", "()Z", "setShouldAutoSnap", "(Z)V", "tickFractions", "", "getTickFractions$material3", "()[F", "totalWidth", "getTotalWidth", "setTotalWidth", "(I)V", "totalWidth$delegate", "Landroidx/compose/runtime/MutableIntState;", "totalHeight", "getTotalHeight", "setTotalHeight", "totalHeight$delegate", "isRtl", "isRtl$material3", "setRtl$material3", "thumbWidth", "getThumbWidth$material3", "setThumbWidth$material3", "thumbWidth$delegate", "thumbHeight", "getThumbHeight$material3", "setThumbHeight$material3", "thumbHeight$delegate", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "getOrientation$material3", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientation$material3", "(Landroidx/compose/foundation/gestures/Orientation;)V", "reverseVerticalDirection", "getReverseVerticalDirection$material3", "setReverseVerticalDirection$material3", "coercedValueAsFraction", "getCoercedValueAsFraction", "isDragging", "setDragging", "isDragging$delegate", "Landroidx/compose/runtime/MutableState;", "updateDimensions", "newTotalWidth", "newTotalHeight", "updateDimensions$material3", "gestureEndAction", "getGestureEndAction$material3", "onPress", "pos", "Landroidx/compose/ui/geometry/Offset;", "onPress-k-4lQ0M$material3", "(J)V", "rawOffset", "getRawOffset", "setRawOffset", "rawOffset$delegate", "pressOffset", "getPressOffset", "setPressOffset", "pressOffset$delegate", "dragScope", "scrollMutex", "Landroidx/compose/foundation/MutatorMutex;", "scaleToUserValue", "minPx", "maxPx", "offset", "scaleToOffset", "userValue", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SliderState implements DraggableState {
    private final DragScope dragScope;
    private final Function0<Unit> gestureEndAction;

    /* JADX INFO: renamed from: isDragging$delegate, reason: from kotlin metadata */
    private final MutableState isDragging;
    private boolean isRtl;
    private Function1<? super Float, Unit> onValueChange;
    private Function0<Unit> onValueChangeFinished;
    private Orientation orientation;

    /* JADX INFO: renamed from: pressOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState pressOffset;

    /* JADX INFO: renamed from: rawOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState rawOffset;
    private boolean reverseVerticalDirection;
    private final MutatorMutex scrollMutex;
    private boolean shouldAutoSnap;
    private final int steps;

    /* JADX INFO: renamed from: thumbHeight$delegate, reason: from kotlin metadata */
    private final MutableIntState thumbHeight;

    /* JADX INFO: renamed from: thumbWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState thumbWidth;
    private final float[] tickFractions;

    /* JADX INFO: renamed from: totalHeight$delegate, reason: from kotlin metadata */
    private final MutableIntState totalHeight;

    /* JADX INFO: renamed from: totalWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState totalWidth;
    private final ClosedFloatingPointRange<Float> valueRange;

    /* JADX INFO: renamed from: valueState$delegate, reason: from kotlin metadata */
    private final MutableFloatState valueState;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: renamed from: androidx.compose.material3.SliderState$drag$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.SliderState$drag$2", f = "Slider.kt", i = {}, l = {2766}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<DragScope, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ MutatePriority $dragPriority;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(MutatePriority mutatePriority, Function2<? super DragScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$dragPriority = mutatePriority;
            this.$block = function2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SliderState.this.new AnonymousClass2(this.$dragPriority, this.$block, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SliderState.this.setDragging(true);
                MutatorMutex mutatorMutex = SliderState.this.scrollMutex;
                DragScope dragScope = SliderState.this.dragScope;
                MutatePriority mutatePriority = this.$dragPriority;
                Function2<DragScope, Continuation<? super Unit>, Object> function2 = this.$block;
                this.label = 1;
                if (mutatorMutex.mutateWith(dragScope, mutatePriority, function2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            SliderState.this.setDragging(false);
            return Unit.INSTANCE;
        }
    }

    public SliderState(float f, int i, Function0<Unit> function0, ClosedFloatingPointRange<Float> closedFloatingPointRange) {
        this.steps = i;
        this.onValueChangeFinished = function0;
        this.valueRange = closedFloatingPointRange;
        this.valueState = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.shouldAutoSnap = true;
        this.tickFractions = SliderKt.stepsToTickFractions(i);
        this.totalWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.totalHeight = SnapshotIntStateKt.mutableIntStateOf(0);
        this.thumbWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.thumbHeight = SnapshotIntStateKt.mutableIntStateOf(0);
        this.orientation = Orientation.Horizontal;
        this.isDragging = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
        this.gestureEndAction = new Function0() { // from class: jgd
            public final Object invoke() {
                return SliderState.a(this.b);
            }
        };
        this.rawOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(scaleToOffset(0.0f, 0.0f, f));
        this.pressOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.dragScope = new DragScope() { // from class: androidx.compose.material3.SliderState$dragScope$1
            public void dragBy(float pixels) {
                this.this$0.dispatchRawDelta(pixels);
            }
        };
        this.scrollMutex = new MutatorMutex();
    }

    public static Unit a(SliderState sliderState) {
        Function0<Unit> function0;
        if (!sliderState.isDragging() && (function0 = sliderState.onValueChangeFinished) != null) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    private final float calculateSnappedValue(float newVal) {
        return SliderKt.snapValueToTick(RangesKt.coerceIn(newVal, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue()), this.tickFractions, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue());
    }

    private final float getPressOffset() {
        return this.pressOffset.getFloatValue();
    }

    private final float getRawOffset() {
        return this.rawOffset.getFloatValue();
    }

    private final int getTotalHeight() {
        return this.totalHeight.getIntValue();
    }

    private final int getTotalWidth() {
        return this.totalWidth.getIntValue();
    }

    private final float getValueState() {
        return this.valueState.getFloatValue();
    }

    private final float scaleToOffset(float minPx, float maxPx, float userValue) {
        return SliderKt.scale(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue(), userValue, minPx, maxPx);
    }

    private final float scaleToUserValue(float minPx, float maxPx, float offset) {
        return SliderKt.scale(minPx, maxPx, offset, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDragging(boolean z) {
        this.isDragging.setValue(Boolean.valueOf(z));
    }

    private final void setPressOffset(float f) {
        this.pressOffset.setFloatValue(f);
    }

    private final void setRawOffset(float f) {
        this.rawOffset.setFloatValue(f);
    }

    private final void setTotalHeight(int i) {
        this.totalHeight.setIntValue(i);
    }

    private final void setTotalWidth(int i) {
        this.totalWidth.setIntValue(i);
    }

    private final void setValueState(float f) {
        this.valueState.setFloatValue(f);
    }

    public void dispatchRawDelta(float delta) {
        float fMax;
        float fMin;
        if (this.orientation == Orientation.Vertical) {
            fMax = Math.max(getTotalHeight() - (getThumbHeight$material3() / 2.0f), 0.0f);
            fMin = Math.min(getThumbHeight$material3() / 2.0f, fMax);
        } else {
            fMax = Math.max(getTotalWidth() - (getThumbWidth$material3() / 2.0f), 0.0f);
            fMin = Math.min(getThumbWidth$material3() / 2.0f, fMax);
        }
        setRawOffset(getRawOffset() + delta + getPressOffset());
        setPressOffset(0.0f);
        float fScaleToUserValue = scaleToUserValue(fMin, fMax, SliderKt.snapValueToTick(getRawOffset(), this.tickFractions, fMin, fMax));
        if (fScaleToUserValue == getValue()) {
            return;
        }
        Function1<? super Float, Unit> function1 = this.onValueChange;
        if (function1 == null) {
            setValue(fScaleToUserValue);
        } else if (function1 != null) {
            function1.invoke(Float.valueOf(fScaleToUserValue));
        }
    }

    public Object drag(MutatePriority mutatePriority, Function2<? super DragScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(mutatePriority, function2, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public final float getCoercedValueAsFraction() {
        return SliderKt.calcFraction(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue(), RangesKt.coerceIn(getValue(), ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue()));
    }

    public final Function0<Unit> getGestureEndAction$material3() {
        return this.gestureEndAction;
    }

    public final Function1<Float, Unit> getOnValueChange() {
        return this.onValueChange;
    }

    public final Function0<Unit> getOnValueChangeFinished() {
        return this.onValueChangeFinished;
    }

    /* JADX INFO: renamed from: getOrientation$material3, reason: from getter */
    public final Orientation getOrientation() {
        return this.orientation;
    }

    /* JADX INFO: renamed from: getReverseVerticalDirection$material3, reason: from getter */
    public final boolean getReverseVerticalDirection() {
        return this.reverseVerticalDirection;
    }

    public final int getSteps() {
        return this.steps;
    }

    public final int getThumbHeight$material3() {
        return this.thumbHeight.getIntValue();
    }

    public final int getThumbWidth$material3() {
        return this.thumbWidth.getIntValue();
    }

    /* JADX INFO: renamed from: getTickFractions$material3, reason: from getter */
    public final float[] getTickFractions() {
        return this.tickFractions;
    }

    public final float getValue() {
        return getValueState();
    }

    public final ClosedFloatingPointRange<Float> getValueRange() {
        return this.valueRange;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isDragging() {
        return ((Boolean) this.isDragging.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: isRtl$material3, reason: from getter */
    public final boolean getIsRtl() {
        return this.isRtl;
    }

    /* JADX INFO: renamed from: onPress-k-4lQ0M$material3, reason: not valid java name */
    public final void m936onPressk4lQ0M$material3(long pos) {
        float fIntBitsToFloat;
        float totalWidth;
        float fIntBitsToFloat2;
        if (this.orientation == Orientation.Vertical) {
            if (this.reverseVerticalDirection) {
                totalWidth = getTotalHeight();
                fIntBitsToFloat2 = Float.intBitsToFloat((int) (pos & 4294967295L));
                fIntBitsToFloat = totalWidth - fIntBitsToFloat2;
            } else {
                fIntBitsToFloat = Float.intBitsToFloat((int) (pos & 4294967295L));
            }
        } else if (this.isRtl) {
            totalWidth = getTotalWidth();
            fIntBitsToFloat2 = Float.intBitsToFloat((int) (pos >> 32));
            fIntBitsToFloat = totalWidth - fIntBitsToFloat2;
        } else {
            fIntBitsToFloat = Float.intBitsToFloat((int) (pos >> 32));
        }
        setPressOffset(fIntBitsToFloat - getRawOffset());
    }

    public final void setOnValueChange(Function1<? super Float, Unit> function1) {
        this.onValueChange = function1;
    }

    public final void setOnValueChangeFinished(Function0<Unit> function0) {
        this.onValueChangeFinished = function0;
    }

    public final void setOrientation$material3(Orientation orientation) {
        this.orientation = orientation;
    }

    public final void setReverseVerticalDirection$material3(boolean z) {
        this.reverseVerticalDirection = z;
    }

    public final void setRtl$material3(boolean z) {
        this.isRtl = z;
    }

    public final void setShouldAutoSnap(boolean z) {
        this.shouldAutoSnap = z;
    }

    public final void setThumbHeight$material3(int i) {
        this.thumbHeight.setIntValue(i);
    }

    public final void setThumbWidth$material3(int i) {
        this.thumbWidth.setIntValue(i);
    }

    public final void setValue(float f) {
        if (this.shouldAutoSnap) {
            f = calculateSnappedValue(f);
        }
        setValueState(f);
    }

    /* JADX INFO: renamed from: shouldAutoSnap, reason: from getter */
    public final boolean getShouldAutoSnap() {
        return this.shouldAutoSnap;
    }

    public final void updateDimensions$material3(int newTotalWidth, int newTotalHeight) {
        setTotalWidth(newTotalWidth);
        setTotalHeight(newTotalHeight);
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u00052\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¨\u0006\r"}, d2 = {"Landroidx/compose/material3/SliderState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/SliderState;", "onValueChangeFinished", "Lkotlin/Function0;", "", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static SliderState a(Function0 function0, ClosedFloatingPointRange closedFloatingPointRange, List list) {
            Object obj = list.get(0);
            obj.getClass();
            float fFloatValue = ((Float) obj).floatValue();
            Object obj2 = list.get(1);
            obj2.getClass();
            return new SliderState(fFloatValue, ((Integer) obj2).intValue(), function0, closedFloatingPointRange);
        }

        public static List b(SaverScope saverScope, SliderState sliderState) {
            return CollectionsKt.listOf(new Object[]{Float.valueOf(sliderState.getValue()), Integer.valueOf(sliderState.getSteps())});
        }

        public final Saver<SliderState, ?> Saver(final Function0<Unit> onValueChangeFinished, final ClosedFloatingPointRange<Float> valueRange) {
            return ListSaverKt.listSaver(new Function2() { // from class: kgd
                public final Object invoke(Object obj, Object obj2) {
                    return SliderState.Companion.b((SaverScope) obj, (SliderState) obj2);
                }
            }, new Function1() { // from class: lgd
                public final Object invoke(Object obj) {
                    return SliderState.Companion.a(onValueChangeFinished, valueRange, (List) obj);
                }
            });
        }

        private Companion() {
        }
    }

    public SliderState() {
        this(0.0f, 0, null, null, 15, null);
    }

    public /* synthetic */ SliderState(float f, int i, Function0 function0, ClosedFloatingPointRange closedFloatingPointRange, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.0f : f, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? null : function0, (i2 & 8) != 0 ? RangesKt.rangeTo(0.0f, 1.0f) : closedFloatingPointRange);
    }
}
