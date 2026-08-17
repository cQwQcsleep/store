package androidx.compose.ui.scrollcapture;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CancellationSignal;
import android.view.ScrollCaptureCallback;
import android.view.ScrollCaptureSession;
import android.view.View;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntRect;
import androidx.core.internal.view.SupportMenu;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;
import kotlin.random.Random;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.NonCancellable;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001:\u0001%B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J \u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u001cH\u0016J.\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\u001e\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010 J\u0010\u0010!\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u001cH\u0016J\f\u0010\"\u001a\u00020\u0013*\u00020#H\u0002J\f\u0010$\u001a\u00020\u0013*\u00020#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/ui/scrollcapture/ComposeScrollCaptureCallback;", "Landroid/view/ScrollCaptureCallback;", "node", "Landroidx/compose/ui/semantics/SemanticsNode;", "viewportBoundsInWindow", "Landroidx/compose/ui/unit/IntRect;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "listener", "Landroidx/compose/ui/scrollcapture/ComposeScrollCaptureCallback$ScrollCaptureSessionListener;", "composeView", "Landroid/view/View;", "<init>", "(Landroidx/compose/ui/semantics/SemanticsNode;Landroidx/compose/ui/unit/IntRect;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/scrollcapture/ComposeScrollCaptureCallback$ScrollCaptureSessionListener;Landroid/view/View;)V", "scrollTracker", "Landroidx/compose/ui/scrollcapture/RelativeScroller;", "requestCount", "", "onScrollCaptureSearch", "", "signal", "Landroid/os/CancellationSignal;", "onReady", "Ljava/util/function/Consumer;", "Landroid/graphics/Rect;", "onScrollCaptureStart", "session", "Landroid/view/ScrollCaptureSession;", "Ljava/lang/Runnable;", "onScrollCaptureImageRequest", "captureArea", "onComplete", "(Landroid/view/ScrollCaptureSession;Landroidx/compose/ui/unit/IntRect;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onScrollCaptureEnd", "drawDebugBackground", "Landroid/graphics/Canvas;", "drawDebugOverlay", "ScrollCaptureSessionListener", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposeScrollCaptureCallback implements ScrollCaptureCallback {
    public static final int $stable = 8;
    private final View composeView;
    private final CoroutineScope coroutineScope;
    private final ScrollCaptureSessionListener listener;
    private final SemanticsNode node;
    private int requestCount;
    private final RelativeScroller scrollTracker;
    private final IntRect viewportBoundsInWindow;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/scrollcapture/ComposeScrollCaptureCallback$ScrollCaptureSessionListener;", "", "onSessionStarted", "", "onSessionEnded", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public interface ScrollCaptureSessionListener {
        void onSessionEnded();

        void onSessionStarted();
    }

    /* JADX INFO: renamed from: androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback$onScrollCaptureEnd$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback$onScrollCaptureEnd$1", f = "ComposeScrollCaptureCallback.android.kt", i = {}, l = {193}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Runnable $onReady;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Runnable runnable, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$onReady = runnable;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ComposeScrollCaptureCallback.this.new AnonymousClass1(this.$onReady, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                RelativeScroller relativeScroller = ComposeScrollCaptureCallback.this.scrollTracker;
                this.label = 1;
                if (relativeScroller.scrollTo(0.0f, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            ComposeScrollCaptureCallback.this.listener.onSessionEnded();
            this.$onReady.run();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback$onScrollCaptureImageRequest$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback$onScrollCaptureImageRequest$1", f = "ComposeScrollCaptureCallback.android.kt", i = {}, l = {121}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Rect $captureArea;
        final /* synthetic */ Consumer<Rect> $onComplete;
        final /* synthetic */ ScrollCaptureSession $session;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01121(ScrollCaptureSession scrollCaptureSession, Rect rect, Consumer<Rect> consumer, Continuation<? super C01121> continuation) {
            super(2, continuation);
            this.$session = scrollCaptureSession;
            this.$captureArea = rect;
            this.$onComplete = consumer;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ComposeScrollCaptureCallback.this.new C01121(this.$session, this.$captureArea, this.$onComplete, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ComposeScrollCaptureCallback composeScrollCaptureCallback = ComposeScrollCaptureCallback.this;
                ScrollCaptureSession scrollCaptureSession = this.$session;
                IntRect composeIntRect = RectHelper_androidKt.toComposeIntRect(this.$captureArea);
                this.label = 1;
                obj = composeScrollCaptureCallback.onScrollCaptureImageRequest(scrollCaptureSession, composeIntRect, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            this.$onComplete.accept(RectHelper_androidKt.toAndroidRect((IntRect) obj));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback$onScrollCaptureImageRequest$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback", f = "ComposeScrollCaptureCallback.android.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {136, 138, 142}, m = "onScrollCaptureImageRequest", n = {"session", "captureArea", "targetMin", "targetMax", "session", "captureArea", "targetMin", "targetMax", "session", "captureArea", "targetMin", "targetMax"}, s = {"L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"}, v = 1)
    public static final class AnonymousClass2 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ComposeScrollCaptureCallback.this.onScrollCaptureImageRequest(null, null, this);
        }
    }

    public ComposeScrollCaptureCallback(SemanticsNode semanticsNode, IntRect intRect, CoroutineScope coroutineScope, ScrollCaptureSessionListener scrollCaptureSessionListener, View view) {
        this.node = semanticsNode;
        this.viewportBoundsInWindow = intRect;
        this.listener = scrollCaptureSessionListener;
        this.composeView = view;
        this.coroutineScope = CoroutineScopeKt.plus(coroutineScope, DisableAnimationMotionDurationScale.INSTANCE);
        this.scrollTracker = new RelativeScroller(intRect.getHeight(), new ComposeScrollCaptureCallback$scrollTracker$1(this, null));
    }

    private final void drawDebugBackground(Canvas canvas) {
        canvas.drawColor(ColorKt.m3188toArgb8_81llA(Color.Companion.m3158hslJlNiLsg$default(Color.INSTANCE, 360.0f * Random.Default.nextFloat(), 0.75f, 0.5f, 1.0f, null, 16, null)));
    }

    private final void drawDebugOverlay(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(SupportMenu.CATEGORY_MASK);
        paint.setTextSize(48.0f);
        canvas.drawCircle(0.0f, 0.0f, 20.0f, paint);
        canvas.drawCircle(canvas.getWidth(), 0.0f, 20.0f, paint);
        canvas.drawCircle(canvas.getWidth(), canvas.getHeight(), 20.0f, paint);
        canvas.drawCircle(0.0f, canvas.getHeight(), 20.0f, paint);
        canvas.drawText(String.valueOf(this.requestCount), canvas.getWidth() / 2.0f, canvas.getHeight() / 2.0f, paint);
        this.requestCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:32:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:34:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object onScrollCaptureImageRequest(ScrollCaptureSession scrollCaptureSession, IntRect intRect, Continuation<? super IntRect> continuation) {
        AnonymousClass2 anonymousClass2;
        int top;
        int bottom;
        ScrollCaptureSession scrollCaptureSession2;
        int i;
        int i2;
        IntRect intRect2;
        int iMapOffsetToViewport;
        int iMapOffsetToViewport2;
        IntRect intRectCopy$default;
        Canvas canvasLockHardwareCanvas;
        if (continuation instanceof AnonymousClass2) {
            anonymousClass2 = (AnonymousClass2) continuation;
            int i3 = anonymousClass2.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                anonymousClass2.label = i3 - Integer.MIN_VALUE;
            } else {
                anonymousClass2 = new AnonymousClass2(continuation);
            }
        } else {
            anonymousClass2 = new AnonymousClass2(continuation);
        }
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = anonymousClass2.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            top = intRect.getTop();
            bottom = intRect.getBottom();
            boolean z = ComposeUiFlags.isScrollCaptureCenteringEnabled;
            RelativeScroller relativeScroller = this.scrollTracker;
            if (z) {
                anonymousClass2.L$0 = scrollCaptureSession;
                anonymousClass2.L$1 = intRect;
                anonymousClass2.I$0 = top;
                anonymousClass2.I$1 = bottom;
                anonymousClass2.label = 1;
                if (relativeScroller.scrollRangeToCenter(top, bottom, anonymousClass2) != coroutine_suspended) {
                }
            } else {
                anonymousClass2.L$0 = scrollCaptureSession;
                anonymousClass2.L$1 = intRect;
                anonymousClass2.I$0 = top;
                anonymousClass2.I$1 = bottom;
                anonymousClass2.label = 2;
                if (relativeScroller.scrollRangeIntoView(top, bottom, anonymousClass2) != coroutine_suspended) {
                }
            }
            return coroutine_suspended;
        }
        if (i4 == 1 || i4 == 2) {
            int i5 = anonymousClass2.I$1;
            int i6 = anonymousClass2.I$0;
            IntRect intRect3 = (IntRect) anonymousClass2.L$1;
            ScrollCaptureSession scrollCaptureSession3 = (ScrollCaptureSession) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj);
            top = i6;
            intRect = intRect3;
            bottom = i5;
            scrollCaptureSession = scrollCaptureSession3;
        } else {
            if (i4 != 3) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            i = anonymousClass2.I$1;
            i2 = anonymousClass2.I$0;
            IntRect intRect4 = (IntRect) anonymousClass2.L$1;
            ScrollCaptureSession scrollCaptureSession4 = (ScrollCaptureSession) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj);
            scrollCaptureSession2 = scrollCaptureSession4;
            intRect2 = intRect4;
        }
        iMapOffsetToViewport = this.scrollTracker.mapOffsetToViewport(i2);
        iMapOffsetToViewport2 = this.scrollTracker.mapOffsetToViewport(i);
        intRectCopy$default = IntRect.copy$default(intRect2, 0, iMapOffsetToViewport, 0, iMapOffsetToViewport2, 5, null);
        if (iMapOffsetToViewport == iMapOffsetToViewport2) {
            return IntRect.INSTANCE.getZero();
        }
        canvasLockHardwareCanvas = scrollCaptureSession2.getSurface().lockHardwareCanvas();
        try {
            canvasLockHardwareCanvas.save();
            canvasLockHardwareCanvas.translate(-intRectCopy$default.getLeft(), -intRectCopy$default.getTop());
            canvasLockHardwareCanvas.translate(-this.viewportBoundsInWindow.getLeft(), -this.viewportBoundsInWindow.getTop());
            this.composeView.getRootView().draw(canvasLockHardwareCanvas);
            return intRectCopy$default.translate(0, MathKt.roundToInt(this.scrollTracker.getScrollAmount()));
        } finally {
            scrollCaptureSession2.getSurface().unlockCanvasAndPost(canvasLockHardwareCanvas);
        }
        int i7 = top;
        scrollCaptureSession2 = scrollCaptureSession;
        i = bottom;
        IntRect intRect5 = intRect;
        i2 = i7;
        AnonymousClass3 anonymousClass3 = new Function1<Long, Unit>() { // from class: androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback.onScrollCaptureImageRequest.3
            public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                invoke(((Number) obj2).longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
            }
        };
        anonymousClass2.L$0 = scrollCaptureSession2;
        anonymousClass2.L$1 = intRect5;
        anonymousClass2.I$0 = i2;
        anonymousClass2.I$1 = i;
        anonymousClass2.label = 3;
        if (MonotonicFrameClockKt.withFrameNanos(anonymousClass3, anonymousClass2) != coroutine_suspended) {
            intRect2 = intRect5;
            iMapOffsetToViewport = this.scrollTracker.mapOffsetToViewport(i2);
            iMapOffsetToViewport2 = this.scrollTracker.mapOffsetToViewport(i);
            intRectCopy$default = IntRect.copy$default(intRect2, 0, iMapOffsetToViewport, 0, iMapOffsetToViewport2, 5, null);
            if (iMapOffsetToViewport == iMapOffsetToViewport2) {
                return IntRect.INSTANCE.getZero();
            }
            canvasLockHardwareCanvas = scrollCaptureSession2.getSurface().lockHardwareCanvas();
            canvasLockHardwareCanvas.save();
            canvasLockHardwareCanvas.translate(-intRectCopy$default.getLeft(), -intRectCopy$default.getTop());
            canvasLockHardwareCanvas.translate(-this.viewportBoundsInWindow.getLeft(), -this.viewportBoundsInWindow.getTop());
            this.composeView.getRootView().draw(canvasLockHardwareCanvas);
            return intRectCopy$default.translate(0, MathKt.roundToInt(this.scrollTracker.getScrollAmount()));
        }
        return coroutine_suspended;
    }

    @Override // android.view.ScrollCaptureCallback
    public void onScrollCaptureEnd(Runnable onReady) {
        BuildersKt.launch$default(this.coroutineScope, NonCancellable.INSTANCE, (CoroutineStart) null, new AnonymousClass1(onReady, null), 2, (Object) null);
    }

    @Override // android.view.ScrollCaptureCallback
    public void onScrollCaptureSearch(CancellationSignal signal, Consumer<Rect> onReady) {
        onReady.accept(RectHelper_androidKt.toAndroidRect(this.viewportBoundsInWindow));
    }

    @Override // android.view.ScrollCaptureCallback
    public void onScrollCaptureStart(ScrollCaptureSession session, CancellationSignal signal, Runnable onReady) {
        this.scrollTracker.reset();
        this.requestCount = 0;
        this.listener.onSessionStarted();
        onReady.run();
    }

    @Override // android.view.ScrollCaptureCallback
    public void onScrollCaptureImageRequest(ScrollCaptureSession session, CancellationSignal signal, Rect captureArea, Consumer<Rect> onComplete) {
        ComposeScrollCaptureCallback_androidKt.launchWithCancellationSignal(this.coroutineScope, signal, new C01121(session, captureArea, onComplete, null));
    }
}
