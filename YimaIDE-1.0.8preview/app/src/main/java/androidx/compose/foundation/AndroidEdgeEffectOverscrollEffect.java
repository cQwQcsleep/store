package androidx.compose.foundation;

import android.content.Context;
import android.widget.EdgeEffect;
import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ3\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0$H\u0016¢\u0006\u0004\b%\u0010&J<\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)2\"\u0010*\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0,\u0012\u0006\u0012\u0004\u0018\u00010-0+H\u0096@¢\u0006\u0004\b.\u0010/J\u0017\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u000201H\u0000¢\u0006\u0004\b5\u00106J\u000f\u00109\u001a\u00020\rH\u0000¢\u0006\u0004\b:\u0010;J\r\u0010B\u001a\u00020\u0013H\u0000¢\u0006\u0002\bCJ\b\u0010D\u001a\u00020\u0013H\u0002J\u0017\u0010E\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\rH\u0002¢\u0006\u0004\bF\u0010GJ\u0017\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bK\u0010LJ\u0017\u0010M\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bN\u0010LJ\u0017\u0010O\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bP\u0010LJ\u0017\u0010Q\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bR\u0010LR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00178\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u00102\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u001bR\u0010\u00107\u001a\u000208X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\u00020?X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010A¨\u0006S"}, d2 = {"Landroidx/compose/foundation/AndroidEdgeEffectOverscrollEffect;", "Landroidx/compose/foundation/OverscrollEffect;", "context", "Landroid/content/Context;", "density", "Landroidx/compose/ui/unit/Density;", "glowColor", "Landroidx/compose/ui/graphics/Color;", "glowDrawPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "<init>", "(Landroid/content/Context;Landroidx/compose/ui/unit/Density;JLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "edgeEffectWrapper", "Landroidx/compose/foundation/EdgeEffectWrapper;", "redrawSignal", "Landroidx/compose/runtime/MutableState;", "", "getRedrawSignal$foundation", "()Landroidx/compose/runtime/MutableState;", "invalidationEnabled", "", "getInvalidationEnabled$foundation$annotations", "()V", "getInvalidationEnabled$foundation", "()Z", "setInvalidationEnabled$foundation", "(Z)V", "scrollCycleInProgress", "applyToScroll", "delta", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "performScroll", "Lkotlin/Function1;", "applyToScroll-Rhakbz0", "(JILkotlin/jvm/functions/Function1;)J", "applyToFling", "velocity", "Landroidx/compose/ui/unit/Velocity;", "performFling", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "applyToFling-BMRW4eQ", "(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "containerSize", "Landroidx/compose/ui/geometry/Size;", "isInProgress", "updateSize", "size", "updateSize-uvyYCjk$foundation", "(J)V", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "displacement", "displacement-F1C5BW0$foundation", "()J", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "node", "Landroidx/compose/ui/node/DelegatableNode;", "getNode", "()Landroidx/compose/ui/node/DelegatableNode;", "invalidateOverscroll", "invalidateOverscroll$foundation", "animateToReleaseIfNeeded", "releaseOppositeOverscroll", "releaseOppositeOverscroll-k-4lQ0M", "(J)Z", "pullTop", "", "scroll", "pullTop-k-4lQ0M", "(J)F", "pullBottom", "pullBottom-k-4lQ0M", "pullLeft", "pullLeft-k-4lQ0M", "pullRight", "pullRight-k-4lQ0M", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidEdgeEffectOverscrollEffect implements OverscrollEffect {
    public static final int $stable = 0;
    private long containerSize;
    private final Density density;
    private final EdgeEffectWrapper edgeEffectWrapper;
    private boolean invalidationEnabled;
    private final DelegatableNode node;
    private long pointerId;
    private final SuspendingPointerInputModifierNode pointerInputNode;
    private long pointerPosition;
    private final MutableState<Unit> redrawSignal;
    private boolean scrollCycleInProgress;

    private AndroidEdgeEffectOverscrollEffect(Context context, Density density, long j, PaddingValues paddingValues) {
        this.density = density;
        this.pointerPosition = Offset.Companion.getUnspecified-F1C5BW0();
        EdgeEffectWrapper edgeEffectWrapper = new EdgeEffectWrapper(context, ColorKt.toArgb-8_81llA(j));
        this.edgeEffectWrapper = edgeEffectWrapper;
        this.redrawSignal = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.invalidationEnabled = true;
        this.containerSize = Size.Companion.getZero-NH-jbRc();
        this.pointerId = PointerId.constructor-impl(-1L);
        SuspendingPointerInputModifierNode SuspendingPointerInputModifierNode = SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1

            /* JADX INFO: renamed from: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1$1", f = "AndroidOverscroll.android.kt", i = {0, 1}, l = {783, 787}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, s = {"L$0", "L$0"}, v = 1)
            public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ AndroidEdgeEffectOverscrollEffect this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = androidEdgeEffectOverscrollEffect;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:26:0x0094  */
                /* JADX WARN: Code duplicated, block: B:29:0x00aa A[LOOP:1: B:25:0x0092->B:29:0x00aa, LOOP_END] */
                /* JADX WARN: Code duplicated, block: B:44:0x00ae A[EDGE_INSN: B:44:0x00ae->B:31:0x00ae BREAK  A[LOOP:1: B:25:0x0092->B:29:0x00aa], SYNTHETIC] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x005c -> B:18:0x005f). Please report as a decompilation issue!!! */
                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                    /*
                        Method dump skipped, instruction units count: 225
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass1(this.this$0, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        });
        this.pointerInputNode = SuspendingPointerInputModifierNode;
        this.node = new StretchOverscrollNode(SuspendingPointerInputModifierNode, this, edgeEffectWrapper);
    }

    private final void animateToReleaseIfNeeded() {
        boolean z;
        EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
        EdgeEffect edgeEffect = edgeEffectWrapper.topEffect;
        boolean z2 = true;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = !edgeEffect.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = edgeEffectWrapper.bottomEffect;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z = !edgeEffect2.isFinished() || z;
        }
        EdgeEffect edgeEffect3 = edgeEffectWrapper.leftEffect;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z = !edgeEffect3.isFinished() || z;
        }
        EdgeEffect edgeEffect4 = edgeEffectWrapper.rightEffect;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            if (edgeEffect4.isFinished() && !z) {
                z2 = false;
            }
            z = z2;
        }
        if (z) {
            invalidateOverscroll$foundation();
        }
    }

    public static /* synthetic */ void getInvalidationEnabled$foundation$annotations() {
    }

    /* JADX INFO: renamed from: pullBottom-k-4lQ0M, reason: not valid java name */
    private final float m295pullBottomk4lQ0M(long scroll) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (m302displacementF1C5BW0$foundation() >> 32));
        int i = (int) (scroll & 4294967295L);
        float fIntBitsToFloat2 = Float.intBitsToFloat(i) / Float.intBitsToFloat((int) (this.containerSize & 4294967295L));
        EdgeEffect orCreateBottomEffect = this.edgeEffectWrapper.getOrCreateBottomEffect();
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(orCreateBottomEffect) == 0.0f ? (-edgeEffectCompat.onPullDistanceCompat(orCreateBottomEffect, -fIntBitsToFloat2, 1.0f - fIntBitsToFloat)) * Float.intBitsToFloat((int) (this.containerSize & 4294967295L)) : Float.intBitsToFloat(i);
    }

    /* JADX INFO: renamed from: pullLeft-k-4lQ0M, reason: not valid java name */
    private final float m296pullLeftk4lQ0M(long scroll) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (m302displacementF1C5BW0$foundation() & 4294967295L));
        int i = (int) (scroll >> 32);
        float fIntBitsToFloat2 = Float.intBitsToFloat(i) / Float.intBitsToFloat((int) (this.containerSize >> 32));
        EdgeEffect orCreateLeftEffect = this.edgeEffectWrapper.getOrCreateLeftEffect();
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(orCreateLeftEffect) == 0.0f ? edgeEffectCompat.onPullDistanceCompat(orCreateLeftEffect, fIntBitsToFloat2, 1.0f - fIntBitsToFloat) * Float.intBitsToFloat((int) (this.containerSize >> 32)) : Float.intBitsToFloat(i);
    }

    /* JADX INFO: renamed from: pullRight-k-4lQ0M, reason: not valid java name */
    private final float m297pullRightk4lQ0M(long scroll) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (m302displacementF1C5BW0$foundation() & 4294967295L));
        int i = (int) (scroll >> 32);
        float fIntBitsToFloat2 = Float.intBitsToFloat(i) / Float.intBitsToFloat((int) (this.containerSize >> 32));
        EdgeEffect orCreateRightEffect = this.edgeEffectWrapper.getOrCreateRightEffect();
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(orCreateRightEffect) == 0.0f ? (-edgeEffectCompat.onPullDistanceCompat(orCreateRightEffect, -fIntBitsToFloat2, fIntBitsToFloat)) * Float.intBitsToFloat((int) (this.containerSize >> 32)) : Float.intBitsToFloat(i);
    }

    /* JADX INFO: renamed from: pullTop-k-4lQ0M, reason: not valid java name */
    private final float m298pullTopk4lQ0M(long scroll) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (m302displacementF1C5BW0$foundation() >> 32));
        int i = (int) (scroll & 4294967295L);
        float fIntBitsToFloat2 = Float.intBitsToFloat(i) / Float.intBitsToFloat((int) (this.containerSize & 4294967295L));
        EdgeEffect orCreateTopEffect = this.edgeEffectWrapper.getOrCreateTopEffect();
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(orCreateTopEffect) == 0.0f ? edgeEffectCompat.onPullDistanceCompat(orCreateTopEffect, fIntBitsToFloat2, fIntBitsToFloat) * Float.intBitsToFloat((int) (this.containerSize & 4294967295L)) : Float.intBitsToFloat(i);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x002d  */
    /* JADX INFO: renamed from: releaseOppositeOverscroll-k-4lQ0M, reason: not valid java name */
    private final boolean m299releaseOppositeOverscrollk4lQ0M(long delta) {
        boolean zIsLeftAnimating;
        if (this.edgeEffectWrapper.isLeftAnimating()) {
            int i = (int) (delta >> 32);
            if (Float.intBitsToFloat(i) < 0.0f) {
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateLeftEffect(), Float.intBitsToFloat(i));
                zIsLeftAnimating = this.edgeEffectWrapper.isLeftAnimating();
            } else {
                zIsLeftAnimating = false;
            }
        } else {
            zIsLeftAnimating = false;
        }
        if (this.edgeEffectWrapper.isRightAnimating()) {
            int i2 = (int) (delta >> 32);
            if (Float.intBitsToFloat(i2) > 0.0f) {
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateRightEffect(), Float.intBitsToFloat(i2));
                zIsLeftAnimating = zIsLeftAnimating || this.edgeEffectWrapper.isRightAnimating();
            }
        }
        if (this.edgeEffectWrapper.isTopAnimating()) {
            int i3 = (int) (delta & 4294967295L);
            if (Float.intBitsToFloat(i3) < 0.0f) {
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateTopEffect(), Float.intBitsToFloat(i3));
                zIsLeftAnimating = zIsLeftAnimating || this.edgeEffectWrapper.isTopAnimating();
            }
        }
        if (this.edgeEffectWrapper.isBottomAnimating()) {
            int i4 = (int) (delta & 4294967295L);
            if (Float.intBitsToFloat(i4) > 0.0f) {
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateBottomEffect(), Float.intBitsToFloat(i4));
                return zIsLeftAnimating || this.edgeEffectWrapper.isBottomAnimating();
            }
        }
        return zIsLeftAnimating;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
    
        if (r14.invoke(r11, r0) == r1) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0133, code lost:
    
        if (r15 == r1) goto L50;
     */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object mo300applyToFlingBMRW4eQ(long j, Function2<? super Velocity, ? super Continuation<? super Velocity>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        AndroidEdgeEffectOverscrollEffect$applyToFling$1 androidEdgeEffectOverscrollEffect$applyToFling$1;
        float fAbsorbToRelaxIfNeeded;
        float fAbsorbToRelaxIfNeeded2;
        long j2;
        if (continuation instanceof AndroidEdgeEffectOverscrollEffect$applyToFling$1) {
            androidEdgeEffectOverscrollEffect$applyToFling$1 = (AndroidEdgeEffectOverscrollEffect$applyToFling$1) continuation;
            int i = androidEdgeEffectOverscrollEffect$applyToFling$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                androidEdgeEffectOverscrollEffect$applyToFling$1.label = i - Integer.MIN_VALUE;
            } else {
                androidEdgeEffectOverscrollEffect$applyToFling$1 = new AndroidEdgeEffectOverscrollEffect$applyToFling$1(this, continuation);
            }
        } else {
            androidEdgeEffectOverscrollEffect$applyToFling$1 = new AndroidEdgeEffectOverscrollEffect$applyToFling$1(this, continuation);
        }
        Object objInvoke = androidEdgeEffectOverscrollEffect$applyToFling$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = androidEdgeEffectOverscrollEffect$applyToFling$1.label;
        if (i2 != 0) {
            if (i2 == 1) {
                ResultKt.throwOnFailure(objInvoke);
                return Unit.INSTANCE;
            }
            if (i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            j2 = androidEdgeEffectOverscrollEffect$applyToFling$1.J$0;
            ResultKt.throwOnFailure(objInvoke);
            long j3 = Velocity.minus-AH228Gc(j2, ((Velocity) objInvoke).unbox-impl());
            this.scrollCycleInProgress = false;
            if (Velocity.getX-impl(j3) > 0.0f) {
                EdgeEffectCompat.INSTANCE.onAbsorbCompat(this.edgeEffectWrapper.getOrCreateLeftEffect(), MathKt.roundToInt(Velocity.getX-impl(j3)));
            } else if (Velocity.getX-impl(j3) < 0.0f) {
                EdgeEffectCompat.INSTANCE.onAbsorbCompat(this.edgeEffectWrapper.getOrCreateRightEffect(), -MathKt.roundToInt(Velocity.getX-impl(j3)));
            }
            if (Velocity.getY-impl(j3) > 0.0f) {
                EdgeEffectCompat.INSTANCE.onAbsorbCompat(this.edgeEffectWrapper.getOrCreateTopEffect(), MathKt.roundToInt(Velocity.getY-impl(j3)));
            } else if (Velocity.getY-impl(j3) < 0.0f) {
                EdgeEffectCompat.INSTANCE.onAbsorbCompat(this.edgeEffectWrapper.getOrCreateBottomEffect(), -MathKt.roundToInt(Velocity.getY-impl(j3)));
            }
            animateToReleaseIfNeeded();
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(objInvoke);
        if (Size.isEmpty-impl(this.containerSize)) {
            Velocity velocity = Velocity.box-impl(j);
            androidEdgeEffectOverscrollEffect$applyToFling$1.label = 1;
        } else {
            if (!this.edgeEffectWrapper.isLeftStretched() || Velocity.getX-impl(j) >= 0.0f) {
                fAbsorbToRelaxIfNeeded = (!this.edgeEffectWrapper.isRightStretched() || Velocity.getX-impl(j) <= 0.0f) ? 0.0f : -EdgeEffectCompat.INSTANCE.absorbToRelaxIfNeeded(this.edgeEffectWrapper.getOrCreateRightEffect(), -Velocity.getX-impl(j), Float.intBitsToFloat((int) (this.containerSize >> 32)), this.density);
            } else {
                fAbsorbToRelaxIfNeeded = EdgeEffectCompat.INSTANCE.absorbToRelaxIfNeeded(this.edgeEffectWrapper.getOrCreateLeftEffect(), Velocity.getX-impl(j), Float.intBitsToFloat((int) (this.containerSize >> 32)), this.density);
            }
            if (!this.edgeEffectWrapper.isTopStretched() || Velocity.getY-impl(j) >= 0.0f) {
                fAbsorbToRelaxIfNeeded2 = (!this.edgeEffectWrapper.isBottomStretched() || Velocity.getY-impl(j) <= 0.0f) ? 0.0f : -EdgeEffectCompat.INSTANCE.absorbToRelaxIfNeeded(this.edgeEffectWrapper.getOrCreateBottomEffect(), -Velocity.getY-impl(j), Float.intBitsToFloat((int) (4294967295L & this.containerSize)), this.density);
            } else {
                fAbsorbToRelaxIfNeeded2 = EdgeEffectCompat.INSTANCE.absorbToRelaxIfNeeded(this.edgeEffectWrapper.getOrCreateTopEffect(), Velocity.getY-impl(j), Float.intBitsToFloat((int) (4294967295L & this.containerSize)), this.density);
            }
            long jVelocity = VelocityKt.Velocity(fAbsorbToRelaxIfNeeded, fAbsorbToRelaxIfNeeded2);
            if (!Velocity.equals-impl0(jVelocity, Velocity.Companion.getZero-9UxMQ8M())) {
                invalidateOverscroll$foundation();
            }
            j2 = Velocity.minus-AH228Gc(j, jVelocity);
            Velocity velocity2 = Velocity.box-impl(j2);
            androidEdgeEffectOverscrollEffect$applyToFling$1.J$0 = j2;
            androidEdgeEffectOverscrollEffect$applyToFling$1.label = 2;
            objInvoke = function2.invoke(velocity2, androidEdgeEffectOverscrollEffect$applyToFling$1);
        }
        return coroutine_suspended;
    }

    /* JADX WARN: Code duplicated, block: B:103:0x022a  */
    /* JADX WARN: Code duplicated, block: B:105:0x022f  */
    /* JADX WARN: Code duplicated, block: B:107:0x0237  */
    /* JADX WARN: Code duplicated, block: B:108:0x023b  */
    /* JADX WARN: Code duplicated, block: B:110:0x023e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:112:0x0242  */
    /* JADX WARN: Code duplicated, block: B:23:0x0081  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b8 A[PHI: r11
      0x00b8: PHI (r11v9 float) = (r11v8 float), (r11v12 float) binds: [B:43:0x00e9, B:32:0x00b1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:47:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:58:0x0132 A[PHI: r14
      0x0132: PHI (r14v9 float) = (r14v8 float), (r14v12 float) binds: [B:67:0x0162, B:56:0x012b] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToScroll-Rhakbz0, reason: not valid java name */
    public long mo301applyToScrollRhakbz0(long delta, int source, Function1<? super Offset, Offset> performScroll) {
        float fM295pullBottomk4lQ0M;
        float fIntBitsToFloat;
        float fM297pullRightk4lQ0M;
        float fIntBitsToFloat2;
        boolean z;
        boolean z2;
        int i;
        boolean z3;
        if (Size.isEmpty-impl(this.containerSize)) {
            return ((Offset) performScroll.invoke(Offset.box-impl(delta))).unbox-impl();
        }
        if (!this.scrollCycleInProgress) {
            if (this.edgeEffectWrapper.isLeftStretched()) {
                m296pullLeftk4lQ0M(Offset.Companion.getZero-F1C5BW0());
            }
            if (this.edgeEffectWrapper.isRightStretched()) {
                m297pullRightk4lQ0M(Offset.Companion.getZero-F1C5BW0());
            }
            if (this.edgeEffectWrapper.isTopStretched()) {
                m298pullTopk4lQ0M(Offset.Companion.getZero-F1C5BW0());
            }
            if (this.edgeEffectWrapper.isBottomStretched()) {
                m295pullBottomk4lQ0M(Offset.Companion.getZero-F1C5BW0());
            }
            this.scrollCycleInProgress = true;
        }
        float fM319destretchMultiplierGyEprt8 = AndroidOverscroll_androidKt.m319destretchMultiplierGyEprt8(source);
        long j = Offset.times-tuRUvjQ(delta, fM319destretchMultiplierGyEprt8);
        int i2 = (int) (delta & 4294967295L);
        if (Float.intBitsToFloat(i2) == 0.0f) {
            fIntBitsToFloat = 0.0f;
        } else if (this.edgeEffectWrapper.isTopStretched() && Float.intBitsToFloat(i2) < 0.0f) {
            fM295pullBottomk4lQ0M = m298pullTopk4lQ0M(j);
            if (!this.edgeEffectWrapper.isTopStretched()) {
                this.edgeEffectWrapper.getOrCreateTopEffect().finish();
            }
            if (fM295pullBottomk4lQ0M == Float.intBitsToFloat((int) (j & 4294967295L))) {
                fIntBitsToFloat = Float.intBitsToFloat(i2);
            } else {
                fIntBitsToFloat = fM295pullBottomk4lQ0M / fM319destretchMultiplierGyEprt8;
            }
        } else if (!this.edgeEffectWrapper.isBottomStretched() || Float.intBitsToFloat(i2) <= 0.0f) {
            fIntBitsToFloat = 0.0f;
        } else {
            fM295pullBottomk4lQ0M = m295pullBottomk4lQ0M(j);
            if (!this.edgeEffectWrapper.isBottomStretched()) {
                this.edgeEffectWrapper.getOrCreateBottomEffect().finish();
            }
            if (fM295pullBottomk4lQ0M == Float.intBitsToFloat((int) (j & 4294967295L))) {
                fIntBitsToFloat = Float.intBitsToFloat(i2);
            } else {
                fIntBitsToFloat = fM295pullBottomk4lQ0M / fM319destretchMultiplierGyEprt8;
            }
        }
        int i3 = (int) (delta >> 32);
        if (Float.intBitsToFloat(i3) == 0.0f) {
            fIntBitsToFloat2 = 0.0f;
        } else if (this.edgeEffectWrapper.isLeftStretched() && Float.intBitsToFloat(i3) < 0.0f) {
            fM297pullRightk4lQ0M = m296pullLeftk4lQ0M(j);
            if (!this.edgeEffectWrapper.isLeftStretched()) {
                this.edgeEffectWrapper.getOrCreateLeftEffect().finish();
            }
            if (fM297pullRightk4lQ0M == Float.intBitsToFloat((int) (j >> 32))) {
                fIntBitsToFloat2 = Float.intBitsToFloat(i3);
            } else {
                fIntBitsToFloat2 = fM297pullRightk4lQ0M / fM319destretchMultiplierGyEprt8;
            }
        } else if (!this.edgeEffectWrapper.isRightStretched() || Float.intBitsToFloat(i3) <= 0.0f) {
            fIntBitsToFloat2 = 0.0f;
        } else {
            fM297pullRightk4lQ0M = m297pullRightk4lQ0M(j);
            if (!this.edgeEffectWrapper.isRightStretched()) {
                this.edgeEffectWrapper.getOrCreateRightEffect().finish();
            }
            if (fM297pullRightk4lQ0M == Float.intBitsToFloat((int) (j >> 32))) {
                fIntBitsToFloat2 = Float.intBitsToFloat(i3);
            } else {
                fIntBitsToFloat2 = fM297pullRightk4lQ0M / fM319destretchMultiplierGyEprt8;
            }
        }
        long j2 = Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat2)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat)) & 4294967295L));
        Offset.Companion companion = Offset.Companion;
        if (!Offset.equals-impl0(j2, companion.getZero-F1C5BW0())) {
            invalidateOverscroll$foundation();
        }
        long j3 = Offset.minus-MK-Hz9U(delta, j2);
        long j4 = ((Offset) performScroll.invoke(Offset.box-impl(j3))).unbox-impl();
        long j5 = Offset.minus-MK-Hz9U(j3, j4);
        if ((Float.intBitsToFloat((int) (j3 >> 32)) != 0.0f || Float.intBitsToFloat((int) (j3 & 4294967295L)) != 0.0f) && (Float.intBitsToFloat((int) (j4 >> 32)) != 0.0f || Float.intBitsToFloat((int) (j4 & 4294967295L)) != 0.0f)) {
            EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
            if (edgeEffectWrapper.isLeftStretched() || edgeEffectWrapper.isTopStretched() || edgeEffectWrapper.isRightStretched() || edgeEffectWrapper.isBottomStretched()) {
                animateToReleaseIfNeeded();
            }
        }
        if (NestedScrollSource.equals-impl0(source, NestedScrollSource.Companion.getUserInput-WNlRxjI())) {
            int i4 = (int) (j5 >> 32);
            if (Float.intBitsToFloat(i4) > 0.5f) {
                m296pullLeftk4lQ0M(j5);
            } else {
                if (Float.intBitsToFloat(i4) < -0.5f) {
                    m297pullRightk4lQ0M(j5);
                } else {
                    z2 = false;
                }
                i = (int) (j5 & 4294967295L);
                if (Float.intBitsToFloat(i) > 0.5f) {
                    m298pullTopk4lQ0M(j5);
                } else {
                    if (Float.intBitsToFloat(i) < -0.5f) {
                        m295pullBottomk4lQ0M(j5);
                    } else {
                        z3 = false;
                    }
                    if (!z2 || z3) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
                z3 = true;
                if (z2) {
                }
                z = true;
            }
            z2 = true;
            i = (int) (j5 & 4294967295L);
            if (Float.intBitsToFloat(i) > 0.5f) {
                m298pullTopk4lQ0M(j5);
            } else {
                if (Float.intBitsToFloat(i) < -0.5f) {
                    m295pullBottomk4lQ0M(j5);
                } else {
                    z3 = false;
                }
                if (z2) {
                }
                z = true;
            }
            z3 = true;
            if (z2) {
            }
            z = true;
        } else {
            z = false;
        }
        if (!Offset.equals-impl0(j3, companion.getZero-F1C5BW0())) {
            z = m299releaseOppositeOverscrollk4lQ0M(delta) || z;
        }
        if (z) {
            invalidateOverscroll$foundation();
        }
        return Offset.plus-MK-Hz9U(j2, j4);
    }

    /* JADX INFO: renamed from: displacement-F1C5BW0$foundation, reason: not valid java name */
    public final long m302displacementF1C5BW0$foundation() {
        long j = this.pointerPosition;
        if ((SieveCacheKt.InvalidMapping & j) == 9205357640488583168L) {
            j = SizeKt.getCenter-uvyYCjk(this.containerSize);
        }
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) / Float.intBitsToFloat((int) (this.containerSize >> 32));
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L)) / Float.intBitsToFloat((int) (this.containerSize & 4294967295L)))) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32));
    }

    /* JADX INFO: renamed from: getInvalidationEnabled$foundation, reason: from getter */
    public final boolean getInvalidationEnabled() {
        return this.invalidationEnabled;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public DelegatableNode getNode() {
        return this.node;
    }

    public final MutableState<Unit> getRedrawSignal$foundation() {
        return this.redrawSignal;
    }

    public final void invalidateOverscroll$foundation() {
        if (this.invalidationEnabled) {
            this.redrawSignal.setValue(Unit.INSTANCE);
        }
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public boolean isInProgress() {
        EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
        EdgeEffect edgeEffect = edgeEffectWrapper.topEffect;
        if (edgeEffect != null && EdgeEffectCompat.INSTANCE.getDistanceCompat(edgeEffect) != 0.0f) {
            return true;
        }
        EdgeEffect edgeEffect2 = edgeEffectWrapper.bottomEffect;
        if (edgeEffect2 != null && EdgeEffectCompat.INSTANCE.getDistanceCompat(edgeEffect2) != 0.0f) {
            return true;
        }
        EdgeEffect edgeEffect3 = edgeEffectWrapper.leftEffect;
        if (edgeEffect3 != null && EdgeEffectCompat.INSTANCE.getDistanceCompat(edgeEffect3) != 0.0f) {
            return true;
        }
        EdgeEffect edgeEffect4 = edgeEffectWrapper.rightEffect;
        return (edgeEffect4 == null || EdgeEffectCompat.INSTANCE.getDistanceCompat(edgeEffect4) == 0.0f) ? false : true;
    }

    public final void setInvalidationEnabled$foundation(boolean z) {
        this.invalidationEnabled = z;
    }

    /* JADX INFO: renamed from: updateSize-uvyYCjk$foundation, reason: not valid java name */
    public final void m303updateSizeuvyYCjk$foundation(long size) {
        boolean z = Size.equals-impl0(this.containerSize, Size.Companion.getZero-NH-jbRc());
        boolean z2 = Size.equals-impl0(size, this.containerSize);
        this.containerSize = size;
        if (!z2) {
            EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
            int iRoundToInt = MathKt.roundToInt(Float.intBitsToFloat((int) (size >> 32)));
            edgeEffectWrapper.m379updateSizeozmzZPI(IntSize.constructor-impl((((long) MathKt.roundToInt(Float.intBitsToFloat((int) (size & 4294967295L)))) & 4294967295L) | (((long) iRoundToInt) << 32)));
        }
        if (z || z2) {
            return;
        }
        animateToReleaseIfNeeded();
    }

    public /* synthetic */ AndroidEdgeEffectOverscrollEffect(Context context, Density density, long j, PaddingValues paddingValues, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, density, j, paddingValues);
    }
}
