package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aB\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u001a\u001c\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000fH\u0082@¢\u0006\u0004\b\u0012\u0010\u0013\"\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0014\u001a\u00020\u0015*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\"\u0018\u0010\u0019\u001a\u00020\u0015*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018¨\u0006\u001b"}, d2 = {"scrollable2D", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "enabled", "", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "NoOpScrollScope", "Landroidx/compose/foundation/gestures/Scroll2DScope;", "semanticsScrollBy", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/foundation/gestures/ScrollingLogic2D;", "offset", "semanticsScrollBy-d-4ec7I", "(Landroidx/compose/foundation/gestures/ScrollingLogic2D;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "magnitude", "", "Landroidx/compose/ui/unit/Velocity;", "getMagnitude-TH1AsA0", "(J)F", "angle", "getAngle-TH1AsA0", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class Scrollable2DKt {
    private static final Scroll2DScope NoOpScrollScope = new Scroll2DScope() { // from class: androidx.compose.foundation.gestures.Scrollable2DKt$NoOpScrollScope$1
        @Override // androidx.compose.foundation.gestures.Scroll2DScope
        /* JADX INFO: renamed from: scrollBy-MK-Hz9U */
        public long mo544scrollByMKHz9U(long delta) {
            return delta;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getAngle-TH1AsA0, reason: not valid java name */
    public static final float m678getAngleTH1AsA0(long j) {
        return (float) Math.atan2(Velocity.getY-impl(j), Velocity.getX-impl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMagnitude-TH1AsA0, reason: not valid java name */
    public static final float m679getMagnitudeTH1AsA0(long j) {
        return (float) Math.sqrt(((float) Math.pow(Velocity.getX-impl(j), 2.0d)) + ((float) Math.pow(Velocity.getY-impl(j), 2.0d)));
    }

    public static final Modifier scrollable2D(Modifier modifier, Scrollable2DState scrollable2DState, boolean z, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource) {
        return modifier.then(new Scrollable2DElement(scrollable2DState, overscrollEffect, z, flingBehavior, mutableInteractionSource));
    }

    public static /* synthetic */ Modifier scrollable2D$default(Modifier modifier, Scrollable2DState scrollable2DState, boolean z, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return scrollable2D(modifier, scrollable2DState, z, (i & 4) != 0 ? null : overscrollEffect, (i & 8) != 0 ? null : flingBehavior, (i & 16) != 0 ? null : mutableInteractionSource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX INFO: renamed from: semanticsScrollBy-d-4ec7I, reason: not valid java name */
    public static final Object m680semanticsScrollByd4ec7I(ScrollingLogic2D scrollingLogic2D, long j, Continuation<? super Offset> continuation) {
        Scrollable2DKt$semanticsScrollBy$1 scrollable2DKt$semanticsScrollBy$1;
        Ref.LongRef longRef;
        if (continuation instanceof Scrollable2DKt$semanticsScrollBy$1) {
            scrollable2DKt$semanticsScrollBy$1 = (Scrollable2DKt$semanticsScrollBy$1) continuation;
            int i = scrollable2DKt$semanticsScrollBy$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                scrollable2DKt$semanticsScrollBy$1.label = i - Integer.MIN_VALUE;
            } else {
                scrollable2DKt$semanticsScrollBy$1 = new Scrollable2DKt$semanticsScrollBy$1(continuation);
            }
        } else {
            scrollable2DKt$semanticsScrollBy$1 = new Scrollable2DKt$semanticsScrollBy$1(continuation);
        }
        Object obj = scrollable2DKt$semanticsScrollBy$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = scrollable2DKt$semanticsScrollBy$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.LongRef longRef2 = new Ref.LongRef();
            longRef2.element = Offset.Companion.getZero-F1C5BW0();
            MutatePriority mutatePriority = MutatePriority.Default;
            Scrollable2DKt$semanticsScrollBy$2 scrollable2DKt$semanticsScrollBy$2 = new Scrollable2DKt$semanticsScrollBy$2(j, longRef2, null);
            scrollable2DKt$semanticsScrollBy$1.L$0 = longRef2;
            scrollable2DKt$semanticsScrollBy$1.label = 1;
            if (scrollingLogic2D.scroll(mutatePriority, scrollable2DKt$semanticsScrollBy$2, scrollable2DKt$semanticsScrollBy$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            longRef = longRef2;
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            longRef = (Ref.LongRef) scrollable2DKt$semanticsScrollBy$1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Offset.box-impl(longRef.element);
    }
}
