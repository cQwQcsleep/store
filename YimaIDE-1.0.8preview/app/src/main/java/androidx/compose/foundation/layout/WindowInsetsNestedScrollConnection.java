package androidx.compose.foundation.layout;

import android.graphics.Insets;
import android.os.CancellationSignal;
import android.view.View;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Velocity;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010#\u001a\u0004\u0018\u00010\u0016H\u0082@¢\u0006\u0002\u0010$J\u001f\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+J'\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b.\u0010/J\u001f\u00100\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u00101\u001a\u00020\u001cH\u0002¢\u0006\u0004\b2\u00103J\u0018\u00104\u001a\u0002052\u0006\u0010'\u001a\u000205H\u0096@¢\u0006\u0004\b6\u00107J \u00108\u001a\u0002052\u0006\u0010-\u001a\u0002052\u0006\u0010'\u001a\u000205H\u0096@¢\u0006\u0004\b9\u0010:J(\u0010;\u001a\u0002052\u0006\u0010'\u001a\u0002052\u0006\u0010<\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020\u0018H\u0082@¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020 2\u0006\u0010A\u001a\u00020\u001cH\u0002J\u0018\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020\u00162\u0006\u0010D\u001a\u00020EH\u0016J\u0006\u0010F\u001a\u00020 J\u0010\u0010G\u001a\u00020 2\u0006\u0010C\u001a\u00020\u0016H\u0016J\u0012\u0010H\u001a\u00020 2\b\u0010C\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010I\u001a\u00020 H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroid/view/WindowInsetsAnimationControlListener;", "windowInsets", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "view", "Landroid/view/View;", "sideCalculator", "Landroidx/compose/foundation/layout/SideCalculator;", "density", "Landroidx/compose/ui/unit/Density;", "<init>", "(Landroidx/compose/foundation/layout/AndroidWindowInsets;Landroid/view/View;Landroidx/compose/foundation/layout/SideCalculator;Landroidx/compose/ui/unit/Density;)V", "getWindowInsets", "()Landroidx/compose/foundation/layout/AndroidWindowInsets;", "getView", "()Landroid/view/View;", "getSideCalculator", "()Landroidx/compose/foundation/layout/SideCalculator;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "animationController", "Landroid/view/WindowInsetsAnimationController;", "isControllerRequested", "", "cancellationSignal", "Landroid/os/CancellationSignal;", "partialConsumption", "", "animationJob", "Lkotlinx/coroutines/Job;", "requestAnimationController", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "getAnimationController", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "scroll", "scrollAmount", "scroll-8S9VItk", "(JF)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fling", "flingAmount", "towardShown", "fling-huYlsQE", "(JFZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adjustInsets", "inset", "onReady", "controller", "types", "", "dispose", "onFinished", "onCancelled", "animationEnded", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class WindowInsetsNestedScrollConnection implements NestedScrollConnection, WindowInsetsAnimationControlListener {
    private WindowInsetsAnimationController animationController;
    private Job animationJob;
    private final CancellationSignal cancellationSignal = new CancellationSignal();
    private CancellableContinuation<? super WindowInsetsAnimationController> continuation;
    private final Density density;
    private boolean isControllerRequested;
    private float partialConsumption;
    private final SideCalculator sideCalculator;
    private final View view;
    private final AndroidWindowInsets windowInsets;

    public WindowInsetsNestedScrollConnection(AndroidWindowInsets androidWindowInsets, View view, SideCalculator sideCalculator, Density density) {
        this.windowInsets = androidWindowInsets;
        this.view = view;
        this.sideCalculator = sideCalculator;
        this.density = density;
    }

    public static Unit a(Throwable th, WindowInsetsAnimationController windowInsetsAnimationController, CoroutineContext coroutineContext) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adjustInsets(float inset) {
        WindowInsetsAnimationController windowInsetsAnimationController = this.animationController;
        if (windowInsetsAnimationController != null) {
            windowInsetsAnimationController.setInsetsAndAlpha(this.sideCalculator.adjustInsets(windowInsetsAnimationController.getCurrentInsets(), Math.round(inset)), 1.0f, 0.0f);
        }
    }

    private final void animationEnded() {
        WindowInsetsAnimationController windowInsetsAnimationController;
        WindowInsetsAnimationController windowInsetsAnimationController2 = this.animationController;
        if (windowInsetsAnimationController2 != null && windowInsetsAnimationController2.isReady() && (windowInsetsAnimationController = this.animationController) != null) {
            windowInsetsAnimationController.finish(this.windowInsets.isVisible());
        }
        this.animationController = null;
        CancellableContinuation<? super WindowInsetsAnimationController> cancellableContinuation = this.continuation;
        if (cancellableContinuation != null) {
            cancellableContinuation.resume((Object) null, new Function3() { // from class: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection.animationEnded.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    invoke((Throwable) obj, (Void) obj2, (CoroutineContext) obj3);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th, Void r2, CoroutineContext coroutineContext) {
                }
            });
        }
        this.continuation = null;
        Job job = this.animationJob;
        if (job != null) {
            job.cancel(new WindowInsetsAnimationCancelledException());
        }
        this.animationJob = null;
        this.partialConsumption = 0.0f;
        this.isControllerRequested = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    /* JADX INFO: renamed from: fling-huYlsQE, reason: not valid java name */
    public final Object m1019flinghuYlsQE(long j, float f, boolean z, Continuation<? super Velocity> continuation) {
        WindowInsetsNestedScrollConnection$fling$1 windowInsetsNestedScrollConnection$fling$1;
        Object animationController;
        long j2;
        Ref.FloatRef floatRef;
        long j3;
        long j4;
        float f2 = f;
        if (continuation instanceof WindowInsetsNestedScrollConnection$fling$1) {
            windowInsetsNestedScrollConnection$fling$1 = (WindowInsetsNestedScrollConnection$fling$1) continuation;
            int i = windowInsetsNestedScrollConnection$fling$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                windowInsetsNestedScrollConnection$fling$1.label = i - Integer.MIN_VALUE;
            } else {
                windowInsetsNestedScrollConnection$fling$1 = new WindowInsetsNestedScrollConnection$fling$1(this, continuation);
            }
        } else {
            windowInsetsNestedScrollConnection$fling$1 = new WindowInsetsNestedScrollConnection$fling$1(this, continuation);
        }
        WindowInsetsNestedScrollConnection$fling$1 windowInsetsNestedScrollConnection$fling$2 = windowInsetsNestedScrollConnection$fling$1;
        Object obj = windowInsetsNestedScrollConnection$fling$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = windowInsetsNestedScrollConnection$fling$2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Job job = this.animationJob;
            if (job != null) {
                job.cancel(new WindowInsetsAnimationCancelledException());
            }
            this.animationJob = null;
            this.partialConsumption = 0.0f;
            if ((f2 == 0.0f && !z) || (this.animationController == null && this.windowInsets.isVisible() == z)) {
                return Velocity.box-impl(Velocity.Companion.getZero-9UxMQ8M());
            }
            windowInsetsNestedScrollConnection$fling$2.J$0 = j;
            windowInsetsNestedScrollConnection$fling$2.F$0 = f2;
            windowInsetsNestedScrollConnection$fling$2.label = 1;
            animationController = getAnimationController(windowInsetsNestedScrollConnection$fling$2);
            if (animationController != coroutine_suspended) {
                j2 = j;
            }
            return coroutine_suspended;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                j3 = windowInsetsNestedScrollConnection$fling$2.J$0;
                floatRef = (Ref.FloatRef) windowInsetsNestedScrollConnection$fling$2.L$0;
                ResultKt.throwOnFailure(obj);
                return Velocity.box-impl(this.sideCalculator.mo964consumedVelocityQWom1Mo(j3, floatRef.element));
            }
            if (i2 != 3) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            j4 = windowInsetsNestedScrollConnection$fling$2.J$0;
            ResultKt.throwOnFailure(obj);
            return Velocity.box-impl(this.sideCalculator.mo964consumedVelocityQWom1Mo(j4, 0.0f));
        }
        f2 = windowInsetsNestedScrollConnection$fling$2.F$0;
        j2 = windowInsetsNestedScrollConnection$fling$2.J$0;
        ResultKt.throwOnFailure(obj);
        animationController = obj;
        float f3 = f2;
        WindowInsetsAnimationController windowInsetsAnimationController = (WindowInsetsAnimationController) animationController;
        if (windowInsetsAnimationController == null) {
            return Velocity.box-impl(Velocity.Companion.getZero-9UxMQ8M());
        }
        int iValueOf = this.sideCalculator.valueOf(windowInsetsAnimationController.getHiddenStateInsets());
        int iValueOf2 = this.sideCalculator.valueOf(windowInsetsAnimationController.getShownStateInsets());
        int iValueOf3 = this.sideCalculator.valueOf(windowInsetsAnimationController.getCurrentInsets());
        if ((f3 <= 0.0f && iValueOf3 == iValueOf) || (f3 >= 0.0f && iValueOf3 == iValueOf2)) {
            windowInsetsAnimationController.finish(iValueOf3 == iValueOf2);
            this.animationController = null;
            return Velocity.box-impl(Velocity.Companion.getZero-9UxMQ8M());
        }
        SplineBasedFloatDecayAnimationSpec splineBasedFloatDecayAnimationSpec = new SplineBasedFloatDecayAnimationSpec(this.density);
        float fFlingDistance = iValueOf3 + splineBasedFloatDecayAnimationSpec.flingDistance(f3);
        float f4 = iValueOf;
        boolean z2 = (fFlingDistance - f4) / ((float) (iValueOf2 - iValueOf)) > 0.5f;
        int i3 = z2 ? iValueOf2 : iValueOf;
        if (fFlingDistance > iValueOf2 || fFlingDistance < f4) {
            boolean z3 = z2;
            Ref.FloatRef floatRef2 = new Ref.FloatRef();
            WindowInsetsNestedScrollConnection$fling$2 windowInsetsNestedScrollConnection$fling$3 = new WindowInsetsNestedScrollConnection$fling$2(this, iValueOf3, f3, splineBasedFloatDecayAnimationSpec, iValueOf, iValueOf2, floatRef2, windowInsetsAnimationController, z3, null);
            windowInsetsNestedScrollConnection$fling$2.L$0 = floatRef2;
            windowInsetsNestedScrollConnection$fling$2.J$0 = j2;
            windowInsetsNestedScrollConnection$fling$2.label = 2;
            if (CoroutineScopeKt.coroutineScope(windowInsetsNestedScrollConnection$fling$3, windowInsetsNestedScrollConnection$fling$2) != coroutine_suspended) {
                floatRef = floatRef2;
                j3 = j2;
                return Velocity.box-impl(this.sideCalculator.mo964consumedVelocityQWom1Mo(j3, floatRef.element));
            }
        } else {
            WindowInsetsNestedScrollConnection$fling$3 windowInsetsNestedScrollConnection$fling$4 = new WindowInsetsNestedScrollConnection$fling$3(this, iValueOf3, i3, f3, windowInsetsAnimationController, z2, null);
            windowInsetsNestedScrollConnection$fling$2.J$0 = j2;
            windowInsetsNestedScrollConnection$fling$2.label = 3;
            if (CoroutineScopeKt.coroutineScope(windowInsetsNestedScrollConnection$fling$4, windowInsetsNestedScrollConnection$fling$2) != coroutine_suspended) {
                j4 = j2;
                return Velocity.box-impl(this.sideCalculator.mo964consumedVelocityQWom1Mo(j4, 0.0f));
            }
        }
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getAnimationController(Continuation<? super WindowInsetsAnimationController> continuation) {
        WindowInsetsAnimationController windowInsetsAnimationController = this.animationController;
        if (windowInsetsAnimationController != null) {
            return windowInsetsAnimationController;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        this.continuation = cancellableContinuationImpl;
        requestAnimationController();
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestAnimationController() {
        if (this.isControllerRequested) {
            return;
        }
        this.isControllerRequested = true;
        WindowInsetsController windowInsetsController = this.view.getWindowInsetsController();
        if (windowInsetsController != null) {
            windowInsetsController.controlWindowInsetsAnimation(this.windowInsets.getType(), -1L, null, this.cancellationSignal, this);
        }
    }

    /* JADX INFO: renamed from: scroll-8S9VItk, reason: not valid java name */
    private final long m1020scroll8S9VItk(long available, float scrollAmount) {
        Job job = this.animationJob;
        if (job != null) {
            job.cancel(new WindowInsetsAnimationCancelledException());
            this.animationJob = null;
        }
        WindowInsetsAnimationController windowInsetsAnimationController = this.animationController;
        if (scrollAmount != 0.0f) {
            if (this.windowInsets.isVisible() != (scrollAmount > 0.0f) || windowInsetsAnimationController != null) {
                if (windowInsetsAnimationController == null) {
                    this.partialConsumption = 0.0f;
                    requestAnimationController();
                    return this.sideCalculator.mo963consumedOffsetsMKHz9U(available);
                }
                int iValueOf = this.sideCalculator.valueOf(windowInsetsAnimationController.getHiddenStateInsets());
                int iValueOf2 = this.sideCalculator.valueOf(windowInsetsAnimationController.getShownStateInsets());
                Insets currentInsets = windowInsetsAnimationController.getCurrentInsets();
                int iValueOf3 = this.sideCalculator.valueOf(currentInsets);
                if (iValueOf3 == (scrollAmount > 0.0f ? iValueOf2 : iValueOf)) {
                    this.partialConsumption = 0.0f;
                    return Offset.Companion.getZero-F1C5BW0();
                }
                float f = iValueOf3 + scrollAmount + this.partialConsumption;
                int iCoerceIn = RangesKt.coerceIn(Math.round(f), iValueOf, iValueOf2);
                this.partialConsumption = f - Math.round(f);
                if (iCoerceIn != iValueOf3) {
                    windowInsetsAnimationController.setInsetsAndAlpha(this.sideCalculator.adjustInsets(currentInsets, iCoerceIn), 1.0f, 0.0f);
                }
                return this.sideCalculator.mo963consumedOffsetsMKHz9U(available);
            }
        }
        return Offset.Companion.getZero-F1C5BW0();
    }

    public final void dispose() {
        CancellableContinuation<? super WindowInsetsAnimationController> cancellableContinuation = this.continuation;
        if (cancellableContinuation != null) {
            cancellableContinuation.resume((Object) null, new Function3() { // from class: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection.dispose.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    invoke((Throwable) obj, (Void) obj2, (CoroutineContext) obj3);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th, Void r2, CoroutineContext coroutineContext) {
                }
            });
        }
        Job job = this.animationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        WindowInsetsAnimationController windowInsetsAnimationController = this.animationController;
        if (windowInsetsAnimationController != null) {
            windowInsetsAnimationController.finish(!Intrinsics.areEqual(windowInsetsAnimationController.getCurrentInsets(), windowInsetsAnimationController.getHiddenStateInsets()));
        }
    }

    public final Density getDensity() {
        return this.density;
    }

    public final SideCalculator getSideCalculator() {
        return this.sideCalculator;
    }

    public final View getView() {
        return this.view;
    }

    public final AndroidWindowInsets getWindowInsets() {
        return this.windowInsets;
    }

    @Override // android.view.WindowInsetsAnimationControlListener
    public void onCancelled(WindowInsetsAnimationController controller) {
        animationEnded();
    }

    @Override // android.view.WindowInsetsAnimationControlListener
    public void onFinished(WindowInsetsAnimationController controller) {
        animationEnded();
    }

    /* JADX INFO: renamed from: onPostFling-RZ2iAVY, reason: not valid java name */
    public Object m1021onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
        return m1019flinghuYlsQE(j2, this.sideCalculator.showMotion(Velocity.getX-impl(j2), Velocity.getY-impl(j2)), true, continuation);
    }

    /* JADX INFO: renamed from: onPostScroll-DzOQY0M, reason: not valid java name */
    public long m1022onPostScrollDzOQY0M(long consumed, long available, int source) {
        return m1020scroll8S9VItk(available, this.sideCalculator.showMotion(Float.intBitsToFloat((int) (available >> 32)), Float.intBitsToFloat((int) (4294967295L & available))));
    }

    /* JADX INFO: renamed from: onPreFling-QWom1Mo, reason: not valid java name */
    public Object m1023onPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation) {
        return m1019flinghuYlsQE(j, this.sideCalculator.hideMotion(Velocity.getX-impl(j), Velocity.getY-impl(j)), false, continuation);
    }

    /* JADX INFO: renamed from: onPreScroll-OzD1aCk, reason: not valid java name */
    public long m1024onPreScrollOzD1aCk(long available, int source) {
        return m1020scroll8S9VItk(available, this.sideCalculator.hideMotion(Float.intBitsToFloat((int) (available >> 32)), Float.intBitsToFloat((int) (4294967295L & available))));
    }

    @Override // android.view.WindowInsetsAnimationControlListener
    public void onReady(WindowInsetsAnimationController controller, int types) {
        this.animationController = controller;
        this.isControllerRequested = false;
        CancellableContinuation<? super WindowInsetsAnimationController> cancellableContinuation = this.continuation;
        if (cancellableContinuation != null) {
            cancellableContinuation.resume(controller, new Function3() { // from class: androidx.compose.foundation.layout.e0
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return WindowInsetsNestedScrollConnection.a((Throwable) obj, (WindowInsetsAnimationController) obj2, (CoroutineContext) obj3);
                }
            });
        }
        this.continuation = null;
    }
}
