package androidx.compose.foundation.lazy.layout;

import android.os.Trace;
import android.view.Choreographer;
import android.view.Display;
import android.view.View;
import androidx.compose.foundation.lazy.layout.AndroidPrefetchScheduler;
import androidx.compose.foundation.lazy.layout.PriorityTask;
import androidx.compose.ui.util.AndroidTrace_androidKt;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 %2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002$%B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J\b\u0010\u001c\u001a\u00020\u0018H\u0002J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0007H\u0016J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0007H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/foundation/lazy/layout/AndroidPrefetchScheduler;", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "Landroidx/compose/foundation/lazy/layout/PriorityPrefetchScheduler;", "Landroid/view/View$OnAttachStateChangeListener;", "Ljava/lang/Runnable;", "Landroid/view/Choreographer$FrameCallback;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "prefetchRequests", "Ljava/util/PriorityQueue;", "Landroidx/compose/foundation/lazy/layout/PriorityTask;", "prefetchScheduled", "", "choreographer", "Landroid/view/Choreographer;", "kotlin.jvm.PlatformType", "scope", "Landroidx/compose/foundation/lazy/layout/AndroidPrefetchScheduler$PrefetchRequestScopeImpl;", "isActive", "frameStartTimeNanos", "", "run", "", "runRequest", "doFrame", "frameTimeNanos", "startExecution", "scheduleLowPriorityPrefetch", "prefetchRequest", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "scheduleHighPriorityPrefetch", "onViewAttachedToWindow", "v", "onViewDetachedFromWindow", "PrefetchRequestScopeImpl", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidPrefetchScheduler implements PrefetchScheduler, PriorityPrefetchScheduler, View.OnAttachStateChangeListener, Runnable, Choreographer.FrameCallback {
    private static long frameIntervalNs;
    private long frameStartTimeNanos;
    private boolean isActive;
    private boolean prefetchScheduled;
    private final View view;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private final PriorityQueue<PriorityTask> prefetchRequests = new PriorityQueue<>(11, new Comparator() { // from class: n60
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return AndroidPrefetchScheduler.a((PriorityTask) obj, (PriorityTask) obj2);
        }
    });
    private final Choreographer choreographer = Choreographer.getInstance();
    private final PrefetchRequestScopeImpl scope = new PrefetchRequestScopeImpl();

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\nH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/lazy/layout/AndroidPrefetchScheduler$PrefetchRequestScopeImpl;", "Landroidx/compose/foundation/lazy/layout/PrefetchRequestScope;", "<init>", "()V", "isFrameIdle", "", "()Z", "setFrameIdle", "(Z)V", "nextFrameTimeNs", "", "getNextFrameTimeNs", "()J", "setNextFrameTimeNs", "(J)V", "availableTimeNanos", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class PrefetchRequestScopeImpl implements PrefetchRequestScope {
        public static final int $stable = 8;
        private boolean isFrameIdle;
        private long nextFrameTimeNs;

        @Override // androidx.compose.foundation.lazy.layout.PrefetchRequestScope
        public long availableTimeNanos() {
            if (this.isFrameIdle) {
                return Long.MAX_VALUE;
            }
            return Math.max(0L, this.nextFrameTimeNs - System.nanoTime());
        }

        public final long getNextFrameTimeNs() {
            return this.nextFrameTimeNs;
        }

        /* JADX INFO: renamed from: isFrameIdle, reason: from getter */
        public final boolean getIsFrameIdle() {
            return this.isFrameIdle;
        }

        public final void setFrameIdle(boolean z) {
            this.isFrameIdle = z;
        }

        public final void setNextFrameTimeNs(long j) {
            this.nextFrameTimeNs = j;
        }
    }

    public AndroidPrefetchScheduler(View view) {
        this.view = view;
        INSTANCE.calculateFrameIntervalIfNeeded(view);
        view.addOnAttachStateChangeListener(this);
        if (view.isAttachedToWindow()) {
            onViewAttachedToWindow(view);
        }
    }

    public static int a(PriorityTask priorityTask, PriorityTask priorityTask2) {
        return Intrinsics.compare(priorityTask2.getPriority(), priorityTask.getPriority());
    }

    private final boolean runRequest() {
        long jAvailableTimeNanos = this.scope.availableTimeNanos();
        AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:available_time_nanos", jAvailableTimeNanos);
        boolean z = true;
        if (jAvailableTimeNanos > 0) {
            PriorityTask priorityTaskPeek = this.prefetchRequests.peek();
            priorityTaskPeek.getClass();
            if (!priorityTaskPeek.getRequest().execute(this.scope)) {
                this.prefetchRequests.poll();
                z = false;
            }
            this.scope.setFrameIdle(false);
        }
        return z;
    }

    private final void startExecution() {
        if (this.prefetchScheduled) {
            return;
        }
        this.prefetchScheduled = true;
        this.view.post(this);
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long frameTimeNanos) {
        if (this.isActive) {
            this.frameStartTimeNanos = frameTimeNanos;
            this.view.post(this);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View v) {
        this.isActive = true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View v) {
        this.isActive = false;
        this.view.removeCallbacks(this);
        this.choreographer.removeFrameCallback(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.prefetchRequests.isEmpty() || !this.prefetchScheduled || !this.isActive || this.view.getWindowVisibility() != 0) {
            this.prefetchScheduled = false;
            return;
        }
        long nanos = TimeUnit.MILLISECONDS.toNanos(this.view.getDrawingTime());
        this.scope.setFrameIdle(System.nanoTime() > (2 * frameIntervalNs) + nanos);
        this.scope.setNextFrameTimeNs(Math.max(this.frameStartTimeNanos, nanos) + frameIntervalNs);
        boolean zRunRequest = false;
        while (!this.prefetchRequests.isEmpty() && !zRunRequest) {
            if (this.scope.getIsFrameIdle()) {
                Trace.beginSection("compose:lazy:prefetch:idle_frame");
                try {
                    zRunRequest = runRequest();
                    Trace.endSection();
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } else {
                zRunRequest = runRequest();
            }
        }
        if (zRunRequest) {
            this.choreographer.postFrameCallback(this);
        } else {
            this.prefetchScheduled = false;
        }
        AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:available_time_nanos", 0L);
    }

    @Override // androidx.compose.foundation.lazy.layout.PriorityPrefetchScheduler
    public void scheduleHighPriorityPrefetch(PrefetchRequest prefetchRequest) {
        this.prefetchRequests.add(new PriorityTask(PriorityTask.INSTANCE.getHigh(), prefetchRequest));
        startExecution();
    }

    @Override // androidx.compose.foundation.lazy.layout.PriorityPrefetchScheduler
    public void scheduleLowPriorityPrefetch(PrefetchRequest prefetchRequest) {
        this.prefetchRequests.add(new PriorityTask(PriorityTask.INSTANCE.getLow(), prefetchRequest));
        startExecution();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/lazy/layout/AndroidPrefetchScheduler$Companion;", "", "<init>", "()V", "frameIntervalNs", "", "calculateFrameIntervalIfNeeded", "", "view", "Landroid/view/View;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:10:0x0021  */
        public final void calculateFrameIntervalIfNeeded(View view) {
            float refreshRate;
            if (AndroidPrefetchScheduler.frameIntervalNs == 0) {
                Display display = view.getDisplay();
                if (view.isInEditMode() || display == null) {
                    refreshRate = 60.0f;
                } else {
                    refreshRate = display.getRefreshRate();
                    if (refreshRate < 30.0f) {
                        refreshRate = 60.0f;
                    }
                }
                AndroidPrefetchScheduler.frameIntervalNs = (long) (1.0E9f / refreshRate);
            }
        }

        private Companion() {
        }
    }
}
