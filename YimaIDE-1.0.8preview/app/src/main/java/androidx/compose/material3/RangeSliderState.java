package androidx.compose.material3;

import androidx.compose.material3.RangeSliderState;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b*\b\u0007\u0018\u0000 y2\u00020\u0001:\u0001yBG\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010f\u001a\u00020\t2\u0006\u0010g\u001a\u00020P2\u0006\u0010h\u001a\u00020\u0003H\u0000¢\u0006\u0002\biJ/\u0010r\u001a\u00020)2\u0006\u0010g\u001a\u00020P2\u0006\u0010b\u001a\u00020\u00032\u0006\u0010^\u001a\u00020\u00032\u0006\u0010h\u001a\u00020)H\u0002¢\u0006\u0004\bs\u0010tJ \u0010u\u001a\u00020\u00032\u0006\u0010b\u001a\u00020\u00032\u0006\u0010^\u001a\u00020\u00032\u0006\u0010v\u001a\u00020\u0003H\u0002J\r\u0010w\u001a\u00020\tH\u0000¢\u0006\u0002\bxR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R+\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR+\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\u001d\u001a\u0004\b\u001f\u0010\u0019\"\u0004\b \u0010\u001bR$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001bR$\u0010\u0004\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b%\u0010\u0019\"\u0004\b&\u0010\u001bR(\u0010'\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\t\u0018\u00010(X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020/X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R+\u00102\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010\u001d\u001a\u0004\b3\u0010\u0019\"\u0004\b4\u0010\u001bR+\u00106\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b9\u0010\u001d\u001a\u0004\b7\u0010\u0019\"\u0004\b8\u0010\u001bR+\u0010:\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b=\u0010\u001d\u001a\u0004\b;\u0010\u0019\"\u0004\b<\u0010\u001bR+\u0010>\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010\u001d\u001a\u0004\b?\u0010\u0019\"\u0004\b@\u0010\u001bR+\u0010B\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00068@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bC\u0010\u000f\"\u0004\bD\u0010ER+\u0010H\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bK\u0010\u001d\u001a\u0004\bI\u0010\u0019\"\u0004\bJ\u0010\u001bR+\u0010L\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bO\u0010\u001d\u001a\u0004\bM\u0010\u0019\"\u0004\bN\u0010\u001bR+\u0010Q\u001a\u00020P2\u0006\u0010\u0016\u001a\u00020P8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bV\u0010W\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR+\u0010X\u001a\u00020P2\u0006\u0010\u0016\u001a\u00020P8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b[\u0010W\u001a\u0004\bY\u0010S\"\u0004\bZ\u0010UR \u0010\\\u001a\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020\t0(X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010+R+\u0010^\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\ba\u0010\u001d\u001a\u0004\b_\u0010\u0019\"\u0004\b`\u0010\u001bR+\u0010b\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\be\u0010\u001d\u001a\u0004\bc\u0010\u0019\"\u0004\bd\u0010\u001bR\u0014\u0010j\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bk\u0010\u0019R\u0014\u0010l\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bm\u0010\u0019R\u0014\u0010n\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bo\u0010\u000fR\u0014\u0010p\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bq\u0010\u000f¨\u0006z"}, d2 = {"Landroidx/compose/material3/RangeSliderState;", "", "activeRangeStart", "", "activeRangeEnd", "steps", "", "onValueChangeFinished", "Lkotlin/Function0;", "", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "<init>", "(FFILkotlin/jvm/functions/Function0;Lkotlin/ranges/ClosedFloatingPointRange;)V", "getSteps", "()I", "getOnValueChangeFinished", "()Lkotlin/jvm/functions/Function0;", "setOnValueChangeFinished", "(Lkotlin/jvm/functions/Function0;)V", "getValueRange", "()Lkotlin/ranges/ClosedFloatingPointRange;", "<set-?>", "activeRangeStartState", "getActiveRangeStartState", "()F", "setActiveRangeStartState", "(F)V", "activeRangeStartState$delegate", "Landroidx/compose/runtime/MutableFloatState;", "activeRangeEndState", "getActiveRangeEndState", "setActiveRangeEndState", "activeRangeEndState$delegate", "newVal", "getActiveRangeStart", "setActiveRangeStart", "getActiveRangeEnd", "setActiveRangeEnd", "onValueChange", "Lkotlin/Function1;", "Landroidx/compose/material3/SliderRange;", "getOnValueChange$material3", "()Lkotlin/jvm/functions/Function1;", "setOnValueChange$material3", "(Lkotlin/jvm/functions/Function1;)V", "tickFractions", "", "getTickFractions$material3", "()[F", "startThumbWidth", "getStartThumbWidth$material3", "setStartThumbWidth$material3", "startThumbWidth$delegate", "startThumbHeight", "getStartThumbHeight$material3", "setStartThumbHeight$material3", "startThumbHeight$delegate", "endThumbWidth", "getEndThumbWidth$material3", "setEndThumbWidth$material3", "endThumbWidth$delegate", "endThumbHeight", "getEndThumbHeight$material3", "setEndThumbHeight$material3", "endThumbHeight$delegate", "totalWidth", "getTotalWidth$material3", "setTotalWidth$material3", "(I)V", "totalWidth$delegate", "Landroidx/compose/runtime/MutableIntState;", "rawOffsetStart", "getRawOffsetStart$material3", "setRawOffsetStart$material3", "rawOffsetStart$delegate", "rawOffsetEnd", "getRawOffsetEnd$material3", "setRawOffsetEnd$material3", "rawOffsetEnd$delegate", "", "isDragging", "isDragging$material3", "()Z", "setDragging$material3", "(Z)V", "isDragging$delegate", "Landroidx/compose/runtime/MutableState;", "isRtl", "isRtl$material3", "setRtl$material3", "isRtl$delegate", "gestureEndAction", "getGestureEndAction$material3", "maxPx", "getMaxPx", "setMaxPx", "maxPx$delegate", "minPx", "getMinPx", "setMinPx", "minPx$delegate", "onDrag", "isStart", "offset", "onDrag$material3", "coercedActiveRangeStartAsFraction", "getCoercedActiveRangeStartAsFraction$material3", "coercedActiveRangeEndAsFraction", "getCoercedActiveRangeEndAsFraction$material3", "startSteps", "getStartSteps$material3", "endSteps", "getEndSteps$material3", "scaleToUserValue", "scaleToUserValue-8bqG3aw", "(ZFFJ)J", "scaleToOffset", "userValue", "updateMinMaxPx", "updateMinMaxPx$material3", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RangeSliderState {

    /* JADX INFO: renamed from: activeRangeEndState$delegate, reason: from kotlin metadata */
    private final MutableFloatState activeRangeEndState;

    /* JADX INFO: renamed from: activeRangeStartState$delegate, reason: from kotlin metadata */
    private final MutableFloatState activeRangeStartState;

    /* JADX INFO: renamed from: endThumbHeight$delegate, reason: from kotlin metadata */
    private final MutableFloatState endThumbHeight;

    /* JADX INFO: renamed from: endThumbWidth$delegate, reason: from kotlin metadata */
    private final MutableFloatState endThumbWidth;
    private final Function1<Boolean, Unit> gestureEndAction;

    /* JADX INFO: renamed from: isDragging$delegate, reason: from kotlin metadata */
    private final MutableState isDragging;

    /* JADX INFO: renamed from: isRtl$delegate, reason: from kotlin metadata */
    private final MutableState isRtl;

    /* JADX INFO: renamed from: maxPx$delegate, reason: from kotlin metadata */
    private final MutableFloatState maxPx;

    /* JADX INFO: renamed from: minPx$delegate, reason: from kotlin metadata */
    private final MutableFloatState minPx;
    private Function1<? super SliderRange, Unit> onValueChange;
    private Function0<Unit> onValueChangeFinished;

    /* JADX INFO: renamed from: rawOffsetEnd$delegate, reason: from kotlin metadata */
    private final MutableFloatState rawOffsetEnd;

    /* JADX INFO: renamed from: rawOffsetStart$delegate, reason: from kotlin metadata */
    private final MutableFloatState rawOffsetStart;

    /* JADX INFO: renamed from: startThumbHeight$delegate, reason: from kotlin metadata */
    private final MutableFloatState startThumbHeight;

    /* JADX INFO: renamed from: startThumbWidth$delegate, reason: from kotlin metadata */
    private final MutableFloatState startThumbWidth;
    private final int steps;
    private final float[] tickFractions;

    /* JADX INFO: renamed from: totalWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState totalWidth;
    private final ClosedFloatingPointRange<Float> valueRange;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public RangeSliderState(float f, float f2, int i, Function0<Unit> function0, ClosedFloatingPointRange<Float> closedFloatingPointRange) {
        this.steps = i;
        this.onValueChangeFinished = function0;
        this.valueRange = closedFloatingPointRange;
        this.activeRangeStartState = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.activeRangeEndState = PrimitiveSnapshotStateKt.mutableFloatStateOf(f2);
        this.tickFractions = SliderKt.stepsToTickFractions(i);
        this.startThumbWidth = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.startThumbHeight = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.endThumbWidth = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.endThumbHeight = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.totalWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.rawOffsetStart = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.rawOffsetEnd = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        Boolean bool = Boolean.FALSE;
        this.isDragging = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.isRtl = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.gestureEndAction = new Function1() { // from class: e6c
            public final Object invoke(Object obj) {
                return RangeSliderState.a(this.b, ((Boolean) obj).booleanValue());
            }
        };
        this.maxPx = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.minPx = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
    }

    public static Unit a(RangeSliderState rangeSliderState, boolean z) {
        Function0<Unit> function0 = rangeSliderState.onValueChangeFinished;
        if (function0 != null) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    private final float getActiveRangeEndState() {
        return this.activeRangeEndState.getFloatValue();
    }

    private final float getActiveRangeStartState() {
        return this.activeRangeStartState.getFloatValue();
    }

    private final float getMaxPx() {
        return this.maxPx.getFloatValue();
    }

    private final float getMinPx() {
        return this.minPx.getFloatValue();
    }

    private final float scaleToOffset(float minPx, float maxPx, float userValue) {
        return SliderKt.scale(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue(), userValue, minPx, maxPx);
    }

    /* JADX INFO: renamed from: scaleToUserValue-8bqG3aw, reason: not valid java name */
    private final long m773scaleToUserValue8bqG3aw(boolean isStart, float minPx, float maxPx, long offset) {
        return SliderKt.m922scale2geJ7wY(isStart, minPx, maxPx, offset, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue());
    }

    private final void setActiveRangeEndState(float f) {
        this.activeRangeEndState.setFloatValue(f);
    }

    private final void setActiveRangeStartState(float f) {
        this.activeRangeStartState.setFloatValue(f);
    }

    private final void setMaxPx(float f) {
        this.maxPx.setFloatValue(f);
    }

    private final void setMinPx(float f) {
        this.minPx.setFloatValue(f);
    }

    public final float getActiveRangeEnd() {
        return getActiveRangeEndState();
    }

    public final float getActiveRangeStart() {
        return getActiveRangeStartState();
    }

    public final float getCoercedActiveRangeEndAsFraction$material3() {
        return SliderKt.calcFraction(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue(), getActiveRangeEnd());
    }

    public final float getCoercedActiveRangeStartAsFraction$material3() {
        return SliderKt.calcFraction(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue(), getActiveRangeStart());
    }

    public final int getEndSteps$material3() {
        return (int) Math.floor(this.steps * (1.0f - getCoercedActiveRangeStartAsFraction$material3()));
    }

    public final float getEndThumbHeight$material3() {
        return this.endThumbHeight.getFloatValue();
    }

    public final float getEndThumbWidth$material3() {
        return this.endThumbWidth.getFloatValue();
    }

    public final Function1<Boolean, Unit> getGestureEndAction$material3() {
        return this.gestureEndAction;
    }

    public final Function1<SliderRange, Unit> getOnValueChange$material3() {
        return this.onValueChange;
    }

    public final Function0<Unit> getOnValueChangeFinished() {
        return this.onValueChangeFinished;
    }

    public final float getRawOffsetEnd$material3() {
        return this.rawOffsetEnd.getFloatValue();
    }

    public final float getRawOffsetStart$material3() {
        return this.rawOffsetStart.getFloatValue();
    }

    public final int getStartSteps$material3() {
        return (int) Math.floor(this.steps * getCoercedActiveRangeEndAsFraction$material3());
    }

    public final float getStartThumbHeight$material3() {
        return this.startThumbHeight.getFloatValue();
    }

    public final float getStartThumbWidth$material3() {
        return this.startThumbWidth.getFloatValue();
    }

    public final int getSteps() {
        return this.steps;
    }

    /* JADX INFO: renamed from: getTickFractions$material3, reason: from getter */
    public final float[] getTickFractions() {
        return this.tickFractions;
    }

    public final int getTotalWidth$material3() {
        return this.totalWidth.getIntValue();
    }

    public final ClosedFloatingPointRange<Float> getValueRange() {
        return this.valueRange;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isDragging$material3() {
        return ((Boolean) this.isDragging.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isRtl$material3() {
        return ((Boolean) this.isRtl.getValue()).booleanValue();
    }

    public final void onDrag$material3(boolean isStart, float offset) {
        long jSliderRange;
        if (isStart) {
            setRawOffsetStart$material3(getRawOffsetStart$material3() + offset);
            setRawOffsetEnd$material3(scaleToOffset(getMinPx(), getMaxPx(), getActiveRangeEnd()));
            float rawOffsetEnd$material3 = getRawOffsetEnd$material3();
            jSliderRange = SliderKt.SliderRange(RangesKt.coerceAtMost(SliderKt.snapValueToTick(RangesKt.coerceIn(getRawOffsetStart$material3(), getMinPx(), rawOffsetEnd$material3), this.tickFractions, getMinPx(), getMaxPx()), rawOffsetEnd$material3), rawOffsetEnd$material3);
        } else {
            setRawOffsetEnd$material3(getRawOffsetEnd$material3() + offset);
            setRawOffsetStart$material3(scaleToOffset(getMinPx(), getMaxPx(), getActiveRangeStart()));
            float rawOffsetStart$material3 = getRawOffsetStart$material3();
            jSliderRange = SliderKt.SliderRange(rawOffsetStart$material3, RangesKt.coerceAtLeast(SliderKt.snapValueToTick(RangesKt.coerceIn(getRawOffsetEnd$material3(), rawOffsetStart$material3, getMaxPx()), this.tickFractions, getMinPx(), getMaxPx()), rawOffsetStart$material3));
        }
        long jM773scaleToUserValue8bqG3aw = m773scaleToUserValue8bqG3aw(isStart, getMinPx(), getMaxPx(), jSliderRange);
        if (SliderRange.m928equalsimpl0(jM773scaleToUserValue8bqG3aw, SliderKt.SliderRange(getActiveRangeStart(), getActiveRangeEnd()))) {
            return;
        }
        Function1<? super SliderRange, Unit> function1 = this.onValueChange;
        if (function1 == null) {
            setActiveRangeStart(SliderRange.m930getStartimpl(jM773scaleToUserValue8bqG3aw));
            setActiveRangeEnd(SliderRange.m929getEndInclusiveimpl(jM773scaleToUserValue8bqG3aw));
        } else if (function1 != null) {
            function1.invoke(SliderRange.m925boximpl(jM773scaleToUserValue8bqG3aw));
        }
    }

    public final void setActiveRangeEnd(float f) {
        setActiveRangeEndState(SliderKt.snapValueToTick(RangesKt.coerceIn(f, getActiveRangeStart(), ((Number) this.valueRange.getEndInclusive()).floatValue()), this.tickFractions, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue()));
    }

    public final void setActiveRangeStart(float f) {
        setActiveRangeStartState(SliderKt.snapValueToTick(RangesKt.coerceIn(f, ((Number) this.valueRange.getStart()).floatValue(), getActiveRangeEnd()), this.tickFractions, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.getEndInclusive()).floatValue()));
    }

    public final void setDragging$material3(boolean z) {
        this.isDragging.setValue(Boolean.valueOf(z));
    }

    public final void setEndThumbHeight$material3(float f) {
        this.endThumbHeight.setFloatValue(f);
    }

    public final void setEndThumbWidth$material3(float f) {
        this.endThumbWidth.setFloatValue(f);
    }

    public final void setOnValueChange$material3(Function1<? super SliderRange, Unit> function1) {
        this.onValueChange = function1;
    }

    public final void setOnValueChangeFinished(Function0<Unit> function0) {
        this.onValueChangeFinished = function0;
    }

    public final void setRawOffsetEnd$material3(float f) {
        this.rawOffsetEnd.setFloatValue(f);
    }

    public final void setRawOffsetStart$material3(float f) {
        this.rawOffsetStart.setFloatValue(f);
    }

    public final void setRtl$material3(boolean z) {
        this.isRtl.setValue(Boolean.valueOf(z));
    }

    public final void setStartThumbHeight$material3(float f) {
        this.startThumbHeight.setFloatValue(f);
    }

    public final void setStartThumbWidth$material3(float f) {
        this.startThumbWidth.setFloatValue(f);
    }

    public final void setTotalWidth$material3(int i) {
        this.totalWidth.setIntValue(i);
    }

    public final void updateMinMaxPx$material3() {
        float fMax = Math.max(getTotalWidth$material3() - (getEndThumbWidth$material3() / 2.0f), 0.0f);
        float fMin = Math.min(getStartThumbWidth$material3() / 2.0f, fMax);
        if (isDragging$material3()) {
            return;
        }
        if (getMinPx() == fMin && getMaxPx() == fMax && getActiveRangeStart() == getActiveRangeEnd()) {
            return;
        }
        setMinPx(fMin);
        setMaxPx(fMax);
        setRawOffsetStart$material3(scaleToOffset(getMinPx(), getMaxPx(), getActiveRangeStart()));
        setRawOffsetEnd$material3(scaleToOffset(getMinPx(), getMaxPx(), getActiveRangeEnd()));
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u00052\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¨\u0006\r"}, d2 = {"Landroidx/compose/material3/RangeSliderState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/RangeSliderState;", "onValueChangeFinished", "Lkotlin/Function0;", "", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static RangeSliderState a(Function0 function0, ClosedFloatingPointRange closedFloatingPointRange, List list) {
            Object obj = list.get(0);
            obj.getClass();
            float fFloatValue = ((Float) obj).floatValue();
            Object obj2 = list.get(1);
            obj2.getClass();
            float fFloatValue2 = ((Float) obj2).floatValue();
            Object obj3 = list.get(2);
            obj3.getClass();
            return new RangeSliderState(fFloatValue, fFloatValue2, ((Integer) obj3).intValue(), function0, closedFloatingPointRange);
        }

        public static List b(SaverScope saverScope, RangeSliderState rangeSliderState) {
            return CollectionsKt.listOf(new Object[]{Float.valueOf(rangeSliderState.getActiveRangeStart()), Float.valueOf(rangeSliderState.getActiveRangeEnd()), Integer.valueOf(rangeSliderState.getSteps())});
        }

        public final Saver<RangeSliderState, ?> Saver(final Function0<Unit> onValueChangeFinished, final ClosedFloatingPointRange<Float> valueRange) {
            return ListSaverKt.listSaver(new Function2() { // from class: f6c
                public final Object invoke(Object obj, Object obj2) {
                    return RangeSliderState.Companion.b((SaverScope) obj, (RangeSliderState) obj2);
                }
            }, new Function1() { // from class: g6c
                public final Object invoke(Object obj) {
                    return RangeSliderState.Companion.a(onValueChangeFinished, valueRange, (List) obj);
                }
            });
        }

        private Companion() {
        }
    }

    public RangeSliderState() {
        this(0.0f, 0.0f, 0, null, null, 31, null);
    }

    public /* synthetic */ RangeSliderState(float f, float f2, int i, Function0 function0, ClosedFloatingPointRange closedFloatingPointRange, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.0f : f, (i2 & 2) != 0 ? 1.0f : f2, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : function0, (i2 & 16) != 0 ? RangesKt.rangeTo(0.0f, 1.0f) : closedFloatingPointRange);
    }
}
