package androidx.compose.foundation;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.Snapshot;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000s\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001'\b\u0007\u0018\u0000 N2\u00020\u0001:\u0001NB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J?\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2'\u0010-\u001a#\b\u0001\u0012\u0004\u0012\u00020/\u0012\n\u0012\b\u0012\u0004\u0012\u00020*00\u0012\u0006\u0012\u0004\u0018\u0001010.¢\u0006\u0002\b2H\u0096@¢\u0006\u0002\u00103J\u0010\u00104\u001a\u00020$2\u0006\u00105\u001a\u00020$H\u0016J&\u0010H\u001a\u00020*2\u0006\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010I\u001a\b\u0012\u0004\u0012\u00020$0JH\u0086@¢\u0006\u0002\u0010KJ\u0016\u0010L\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\u0003H\u0086@¢\u0006\u0002\u0010MR+\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00038G@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0005R$\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00038F@@X\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u0005R+\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00038F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R+\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\f\u001a\u0004\b\u001a\u0010\t\"\u0004\b\u001b\u0010\u0005R\u0014\u0010\u001d\u001a\u00020\u001eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0004\n\u0002\u0010(R\u0014\u00106\u001a\u0002078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00108R\u001b\u00109\u001a\u0002078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b:\u00108R\u001b\u0010=\u001a\u0002078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b?\u0010<\u001a\u0004\b>\u00108R\u0014\u0010@\u001a\u0002078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u00108R\u0014\u0010B\u001a\u0002078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u00108R\u0016\u0010D\u001a\u0004\u0018\u00010E8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010G¨\u0006O"}, d2 = {"Landroidx/compose/foundation/ScrollState;", "Landroidx/compose/foundation/gestures/ScrollableState;", "initial", "", "<init>", "(I)V", "<set-?>", "value", "getValue", "()I", "setValue", "value$delegate", "Landroidx/compose/runtime/MutableIntState;", "newMax", "maxValue", "getMaxValue", "setMaxValue$foundation", "viewportSize", "getViewportSize", "setViewportSize$foundation", "viewportSize$delegate", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/InteractionSource;", "contentSize", "getContentSize$foundation", "setContentSize$foundation", "contentSize$delegate", "internalInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getInternalInteractionSource$foundation", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "_maxValueState", "Landroidx/compose/runtime/MutableIntState;", "accumulator", "", "scrollableState", "_scrollIndicatorState", "androidx/compose/foundation/ScrollState$_scrollIndicatorState$1", "Landroidx/compose/foundation/ScrollState$_scrollIndicatorState$1;", "scroll", "", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchRawDelta", "delta", "isScrollInProgress", "", "()Z", "canScrollForward", "getCanScrollForward", "canScrollForward$delegate", "Landroidx/compose/runtime/State;", "canScrollBackward", "getCanScrollBackward", "canScrollBackward$delegate", "lastScrolledForward", "getLastScrolledForward", "lastScrolledBackward", "getLastScrolledBackward", "scrollIndicatorState", "Landroidx/compose/foundation/ScrollIndicatorState;", "getScrollIndicatorState", "()Landroidx/compose/foundation/ScrollIndicatorState;", "animateScrollTo", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(ILandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollTo", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ScrollState implements ScrollableState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<ScrollState, ?> Saver = SaverKt.Saver(new Function2() { // from class: jyc
        public final Object invoke(Object obj, Object obj2) {
            return ScrollState.b((SaverScope) obj, (ScrollState) obj2);
        }
    }, new Function1() { // from class: kyc
        public final Object invoke(Object obj) {
            return ScrollState.d(((Integer) obj).intValue());
        }
    });
    private float accumulator;

    /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableIntState value;

    /* JADX INFO: renamed from: viewportSize$delegate, reason: from kotlin metadata */
    private final MutableIntState viewportSize = SnapshotIntStateKt.mutableIntStateOf(0);

    /* JADX INFO: renamed from: contentSize$delegate, reason: from kotlin metadata */
    private final MutableIntState contentSize = SnapshotIntStateKt.mutableIntStateOf(0);
    private final MutableInteractionSource internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
    private MutableIntState _maxValueState = SnapshotIntStateKt.mutableIntStateOf(Integer.MAX_VALUE);
    private final ScrollableState scrollableState = ScrollableStateKt.ScrollableState(new Function1() { // from class: gyc
        public final Object invoke(Object obj) {
            return Float.valueOf(ScrollState.e(this.b, ((Float) obj).floatValue()));
        }
    });
    private final ScrollState$_scrollIndicatorState$1 _scrollIndicatorState = new ScrollIndicatorState() { // from class: androidx.compose.foundation.ScrollState$_scrollIndicatorState$1
        @Override // androidx.compose.foundation.ScrollIndicatorState
        public int getContentSize() {
            return this.this$0.getContentSize$foundation();
        }

        @Override // androidx.compose.foundation.ScrollIndicatorState
        public int getScrollOffset() {
            return this.this$0.getValue();
        }

        @Override // androidx.compose.foundation.ScrollIndicatorState
        public int getViewportSize() {
            return this.this$0.getViewportSize();
        }
    };

    /* JADX INFO: renamed from: canScrollForward$delegate, reason: from kotlin metadata */
    private final State canScrollForward = SnapshotStateKt.derivedStateOf(new Function0() { // from class: hyc
        public final Object invoke() {
            return Boolean.valueOf(ScrollState.c(this.b));
        }
    });

    /* JADX INFO: renamed from: canScrollBackward$delegate, reason: from kotlin metadata */
    private final State canScrollBackward = SnapshotStateKt.derivedStateOf(new Function0() { // from class: iyc
        public final Object invoke() {
            return Boolean.valueOf(ScrollState.a(this.b));
        }
    });

    /* JADX WARN: Type inference failed for: r2v9, types: [androidx.compose.foundation.ScrollState$_scrollIndicatorState$1] */
    public ScrollState(int i) {
        this.value = SnapshotIntStateKt.mutableIntStateOf(i);
    }

    public static boolean a(ScrollState scrollState) {
        return scrollState.getValue() > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateScrollTo$default(ScrollState scrollState, int i, AnimationSpec animationSpec, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            animationSpec = new SpringSpec(0.0f, 0.0f, null, 7, null);
        }
        return scrollState.animateScrollTo(i, animationSpec, continuation);
    }

    public static Integer b(SaverScope saverScope, ScrollState scrollState) {
        return Integer.valueOf(scrollState.getValue());
    }

    public static boolean c(ScrollState scrollState) {
        return scrollState.getValue() < scrollState.getMaxValue();
    }

    public static ScrollState d(int i) {
        return new ScrollState(i);
    }

    public static float e(ScrollState scrollState, float f) {
        float value = scrollState.getValue() + f + scrollState.accumulator;
        float fCoerceIn = RangesKt.coerceIn(value, 0.0f, scrollState.getMaxValue());
        boolean z = value == fCoerceIn;
        float value2 = fCoerceIn - scrollState.getValue();
        int iRound = Math.round(value2);
        scrollState.setValue(scrollState.getValue() + iRound);
        scrollState.accumulator = value2 - iRound;
        return !z ? value2 : f;
    }

    private final void setValue(int i) {
        this.value.setIntValue(i);
    }

    public final Object animateScrollTo(int i, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        Object objAnimateScrollBy = ScrollExtensionsKt.animateScrollBy(this, i - getValue(), animationSpec, continuation);
        return objAnimateScrollBy == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateScrollBy : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public float dispatchRawDelta(float delta) {
        return this.scrollableState.dispatchRawDelta(delta);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getCanScrollBackward() {
        return ((Boolean) this.canScrollBackward.getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getCanScrollForward() {
        return ((Boolean) this.canScrollForward.getValue()).booleanValue();
    }

    public final int getContentSize$foundation() {
        return this.contentSize.getIntValue();
    }

    public final InteractionSource getInteractionSource() {
        return this.internalInteractionSource;
    }

    /* JADX INFO: renamed from: getInternalInteractionSource$foundation, reason: from getter */
    public final MutableInteractionSource getInternalInteractionSource() {
        return this.internalInteractionSource;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getLastScrolledBackward() {
        return this.scrollableState.getLastScrolledBackward();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getLastScrolledForward() {
        return this.scrollableState.getLastScrolledForward();
    }

    public final int getMaxValue() {
        return this._maxValueState.getIntValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public ScrollIndicatorState getScrollIndicatorState() {
        return this._scrollIndicatorState;
    }

    public final int getValue() {
        return this.value.getIntValue();
    }

    public final int getViewportSize() {
        return this.viewportSize.getIntValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public Object scroll(MutatePriority mutatePriority, Function2<? super ScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objScroll = this.scrollableState.scroll(mutatePriority, function2, continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    public final Object scrollTo(int i, Continuation<? super Float> continuation) {
        return ScrollExtensionsKt.scrollBy(this, i - getValue(), continuation);
    }

    public final void setContentSize$foundation(int i) {
        this.contentSize.setIntValue(i);
    }

    public final void setMaxValue$foundation(int i) {
        this._maxValueState.setIntValue(i);
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            if (getValue() > i) {
                setValue(i);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        }
    }

    public final void setViewportSize$foundation(int i) {
        this.viewportSize.setIntValue(i);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/ScrollState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/ScrollState;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Saver<ScrollState, ?> getSaver() {
            return ScrollState.Saver;
        }

        private Companion() {
        }
    }
}
