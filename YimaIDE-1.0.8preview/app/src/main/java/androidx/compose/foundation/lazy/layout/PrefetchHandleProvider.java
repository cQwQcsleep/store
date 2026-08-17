package androidx.compose.foundation.lazy.layout;

import android.os.Trace;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ShouldPauseCallback;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.layout.SubcomposeLayoutState$PausedPrecomposition;
import androidx.compose.ui.layout.SubcomposeLayoutState$PrecomposedSlotHandle;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.util.AndroidTrace_androidKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.time.Duration;
import kotlin.time.TimeSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001:\u0001+B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u001bJH\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u000b2\u0019\u0010\u001f\u001a\u0015\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001b\u0018\u00010 ¢\u0006\u0002\b\"¢\u0006\u0004\b#\u0010$J\u001a\u0010%\u001a\u00020\u001b*\u00020\u00072\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0017\u001a\u00020\u000bJ%\u0010(\u001a\u00020'2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b)\u0010*J\u0016\u0010(\u001a\u00020'2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u000b8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006,"}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;", "", "itemContentFactory", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;", "subcomposeLayoutState", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "executor", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;Landroidx/compose/ui/layout/SubcomposeLayoutState;Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;)V", "isStateActive", "", "shouldPauseBetweenPrecompositionAndPremeasure", "getShouldPauseBetweenPrecompositionAndPremeasure$foundation$annotations", "()V", "getShouldPauseBetweenPrecompositionAndPremeasure$foundation", "()Z", "setShouldPauseBetweenPrecompositionAndPremeasure$foundation", "(Z)V", "schedulePrecomposition", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "index", "", "isHighPriority", "prefetchMetrics", "Landroidx/compose/foundation/lazy/layout/PrefetchMetrics;", "onDisposed", "", "schedulePremeasure", "constraints", "Landroidx/compose/ui/unit/Constraints;", "onItemPremeasured", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchResultScope;", "Lkotlin/ExtensionFunctionType;", "schedulePremeasure-m8Kt_7k", "(IJLandroidx/compose/foundation/lazy/layout/PrefetchMetrics;ZLkotlin/jvm/functions/Function1;)Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "executeWithPriority", "request", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "createNestedPrefetchRequest", "createNestedPrefetchRequest-VKLhPVY", "(IJLandroidx/compose/foundation/lazy/layout/PrefetchMetrics;)Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "HandleAndRequestImpl", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PrefetchHandleProvider {
    public static final int $stable = 8;
    private final PrefetchScheduler executor;
    private boolean isStateActive = true;
    private final LazyLayoutItemContentFactory itemContentFactory;
    private boolean shouldPauseBetweenPrecompositionAndPremeasure;
    private final SubcomposeLayoutState subcomposeLayoutState;

    public PrefetchHandleProvider(LazyLayoutItemContentFactory lazyLayoutItemContentFactory, SubcomposeLayoutState subcomposeLayoutState, PrefetchScheduler prefetchScheduler) {
        this.itemContentFactory = lazyLayoutItemContentFactory;
        this.subcomposeLayoutState = subcomposeLayoutState;
        this.executor = prefetchScheduler;
    }

    public static /* synthetic */ void getShouldPauseBetweenPrecompositionAndPremeasure$foundation$annotations() {
    }

    public final PrefetchRequest createNestedPrefetchRequest(int index, PrefetchMetrics prefetchMetrics) {
        PrefetchScheduler prefetchScheduler = this.executor;
        return new HandleAndRequestImpl(index, prefetchMetrics, prefetchScheduler instanceof PriorityPrefetchScheduler ? (PriorityPrefetchScheduler) prefetchScheduler : null, null);
    }

    /* JADX INFO: renamed from: createNestedPrefetchRequest-VKLhPVY, reason: not valid java name */
    public final PrefetchRequest m1164createNestedPrefetchRequestVKLhPVY(int index, long constraints, PrefetchMetrics prefetchMetrics) {
        PrefetchScheduler prefetchScheduler = this.executor;
        return new HandleAndRequestImpl(this, index, constraints, prefetchMetrics, prefetchScheduler instanceof PriorityPrefetchScheduler ? (PriorityPrefetchScheduler) prefetchScheduler : null, null, null);
    }

    public final void executeWithPriority(PrefetchScheduler prefetchScheduler, PrefetchRequest prefetchRequest, boolean z) {
        if (!(prefetchScheduler instanceof PriorityPrefetchScheduler)) {
            prefetchScheduler.schedulePrefetch(prefetchRequest);
        } else if (z) {
            ((PriorityPrefetchScheduler) prefetchScheduler).scheduleHighPriorityPrefetch(prefetchRequest);
        } else {
            ((PriorityPrefetchScheduler) prefetchScheduler).scheduleLowPriorityPrefetch(prefetchRequest);
        }
    }

    /* JADX INFO: renamed from: getShouldPauseBetweenPrecompositionAndPremeasure$foundation, reason: from getter */
    public final boolean getShouldPauseBetweenPrecompositionAndPremeasure() {
        return this.shouldPauseBetweenPrecompositionAndPremeasure;
    }

    public final void onDisposed() {
        this.isStateActive = false;
    }

    public final LazyLayoutPrefetchState.PrefetchHandle schedulePrecomposition(int index, boolean isHighPriority, PrefetchMetrics prefetchMetrics) {
        PrefetchScheduler prefetchScheduler = this.executor;
        HandleAndRequestImpl handleAndRequestImpl = new HandleAndRequestImpl(index, prefetchMetrics, prefetchScheduler instanceof PriorityPrefetchScheduler ? (PriorityPrefetchScheduler) prefetchScheduler : null, null);
        executeWithPriority(this.executor, handleAndRequestImpl, isHighPriority);
        AndroidTrace_androidKt.traceValue("compose:lazy:schedule_prefetch:index", index);
        return handleAndRequestImpl;
    }

    /* JADX INFO: renamed from: schedulePremeasure-m8Kt_7k, reason: not valid java name */
    public final LazyLayoutPrefetchState.PrefetchHandle m1165schedulePremeasurem8Kt_7k(int index, long constraints, PrefetchMetrics prefetchMetrics, boolean isHighPriority, Function1<? super LazyLayoutPrefetchState.PrefetchResultScope, Unit> onItemPremeasured) {
        PrefetchScheduler prefetchScheduler = this.executor;
        HandleAndRequestImpl handleAndRequestImpl = new HandleAndRequestImpl(this, index, constraints, prefetchMetrics, prefetchScheduler instanceof PriorityPrefetchScheduler ? (PriorityPrefetchScheduler) prefetchScheduler : null, onItemPremeasured, null);
        executeWithPriority(this.executor, handleAndRequestImpl, isHighPriority);
        AndroidTrace_androidKt.traceValue("compose:lazy:schedule_prefetch:index", index);
        return handleAndRequestImpl;
    }

    public final void setShouldPauseBetweenPrecompositionAndPremeasure$foundation(boolean z) {
        this.shouldPauseBetweenPrecompositionAndPremeasure = z;
    }

    @Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0083\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001MB<\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0019\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0004\b\u000e\u0010\u000fBF\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0019\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0004\b\u000e\u0010\u0012J\b\u0010'\u001a\u00020\fH\u0016J\b\u0010(\u001a\u00020\fH\u0016J\u0017\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0005H\u0016¢\u0006\u0004\b.\u0010/J\u0018\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000202H\u0002J\u0010\u00109\u001a\u00020\f2\u0006\u00104\u001a\u000202H\u0002J\b\u0010:\u001a\u00020\fH\u0002J\f\u0010;\u001a\u00020\u001b*\u00020<H\u0016J\b\u0010=\u001a\u00020\fH\u0002J\f\u0010>\u001a\u00020\u001b*\u00020<H\u0002J&\u0010@\u001a\u00020\f*\u00020<2\u0006\u0010A\u001a\u00020\u001f2\b\u0010B\u001a\u0004\u0018\u00010\u001f2\u0006\u0010C\u001a\u00020DH\u0002J\u001a\u0010E\u001a\u00020\f2\u0006\u0010A\u001a\u00020\u001f2\b\u0010B\u001a\u0004\u0018\u00010\u001fH\u0002J\b\u0010F\u001a\u00020\fH\u0002J\u0017\u0010G\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0004\bH\u0010IJ\u0012\u0010J\u001a\f\u0018\u00010\"R\u00060\u0000R\u00020#H\u0002J\b\u0010K\u001a\u00020LH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\b\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\f\u0018\u00010\"R\u00060\u0000R\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010)\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u0014R\u000e\u00104\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u000207X\u0082\u000e¢\u0006\u0004\n\u0002\u00108R\u000e\u0010?\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchResultScope;", "index", "", "prefetchMetrics", "Landroidx/compose/foundation/lazy/layout/PrefetchMetrics;", "priorityPrefetchScheduler", "Landroidx/compose/foundation/lazy/layout/PriorityPrefetchScheduler;", "onItemPremeasured", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;ILandroidx/compose/foundation/lazy/layout/PrefetchMetrics;Landroidx/compose/foundation/lazy/layout/PriorityPrefetchScheduler;Lkotlin/jvm/functions/Function1;)V", "constraints", "Landroidx/compose/ui/unit/Constraints;", "(Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;IJLandroidx/compose/foundation/lazy/layout/PrefetchMetrics;Landroidx/compose/foundation/lazy/layout/PriorityPrefetchScheduler;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getIndex", "()I", "premeasureConstraints", "precomposeHandle", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "pausedPrecomposition", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PausedPrecomposition;", "isMeasured", "", "isCanceled", "isApplied", "keyUsedForComposition", "", "hasResolvedNestedPrefetches", "nestedPrefetchController", "Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl$NestedPrefetchController;", "Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;", "isUrgent", "isComposed", "()Z", "cancel", "markAsUrgent", "placeablesCount", "getPlaceablesCount", "getSize", "Landroidx/compose/ui/unit/IntSize;", "placeableIndex", "getSize-YEO4UFw", "(I)J", "shouldExecute", "available", "", "average", "availableTimeNanos", "elapsedTimeNanos", "startTime", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "J", "resetAvailableTimeTo", "updateElapsedAndAvailableTime", "execute", "Landroidx/compose/foundation/lazy/layout/PrefetchRequestScope;", "cleanUp", "executeRequest", "pauseRequested", "performPausableComposition", "key", "contentType", "averages", "Landroidx/compose/foundation/lazy/layout/Averages;", "performFullComposition", "performApply", "performMeasure", "performMeasure-BRTryo0", "(J)V", "resolveNestedPrefetchStates", "toString", "", "NestedPrefetchController", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class HandleAndRequestImpl implements LazyLayoutPrefetchState.PrefetchHandle, PrefetchRequest, LazyLayoutPrefetchState.PrefetchResultScope {
        private long availableTimeNanos;
        private long elapsedTimeNanos;
        private boolean hasResolvedNestedPrefetches;
        private final int index;
        private boolean isApplied;
        private boolean isCanceled;
        private boolean isMeasured;
        private boolean isUrgent;
        private Object keyUsedForComposition;
        private NestedPrefetchController nestedPrefetchController;
        private final Function1<LazyLayoutPrefetchState.PrefetchResultScope, Unit> onItemPremeasured;
        private boolean pauseRequested;
        private SubcomposeLayoutState$PausedPrecomposition pausedPrecomposition;
        private SubcomposeLayoutState$PrecomposedSlotHandle precomposeHandle;
        private final PrefetchMetrics prefetchMetrics;
        private Constraints premeasureConstraints;
        private final PriorityPrefetchScheduler priorityPrefetchScheduler;
        private long startTime;

        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0014\u001a\u00020\u000f*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u000fJ\u0006\u0010\u0018\u001a\u00020\fJ\u0006\u0010\u0019\u001a\u00020\fR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00030\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl$NestedPrefetchController;", "", "states", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "<init>", "(Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl;Ljava/util/List;)V", "requestsByState", "", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "[Ljava/util/List;", "stateIndex", "", "requestIndex", "executedNestedPrefetch", "", "getExecutedNestedPrefetch", "()Z", "setExecutedNestedPrefetch", "(Z)V", "executeNestedPrefetches", "Landroidx/compose/foundation/lazy/layout/PrefetchRequestScope;", "nestedPrefetchCount", "isUrgent", "collectIdealNestedPrefetchCount", "collectNestedPrefetchedItemsCount", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public final class NestedPrefetchController {
            private boolean executedNestedPrefetch;
            private int requestIndex;
            private final List<PrefetchRequest>[] requestsByState;
            private int stateIndex;
            private final List<LazyLayoutPrefetchState> states;

            public NestedPrefetchController(List<LazyLayoutPrefetchState> list) {
                this.states = list;
                this.requestsByState = new List[list.size()];
                if (list.isEmpty()) {
                    InlineClassHelperKt.throwIllegalArgumentException("NestedPrefetchController shouldn't be created with no states");
                }
            }

            public final int collectIdealNestedPrefetchCount() {
                List<LazyLayoutPrefetchState> list = this.states;
                int size = list.size();
                int iMin = Integer.MAX_VALUE;
                for (int i = 0; i < size; i++) {
                    iMin = Math.min(iMin, list.get(i).getIdealNestedPrefetchCount());
                }
                if (iMin == Integer.MAX_VALUE) {
                    return 0;
                }
                return iMin;
            }

            public final int collectNestedPrefetchedItemsCount() {
                List<LazyLayoutPrefetchState> list = this.states;
                int size = list.size();
                int iMin = Integer.MAX_VALUE;
                for (int i = 0; i < size; i++) {
                    iMin = Math.min(iMin, list.get(i).getLastNumberOfNestedPrefetchItems());
                }
                if (iMin == Integer.MAX_VALUE) {
                    return 0;
                }
                return iMin;
            }

            public final boolean executeNestedPrefetches(PrefetchRequestScope prefetchRequestScope, int i, boolean z) {
                if (this.stateIndex >= this.states.size()) {
                    return false;
                }
                if (HandleAndRequestImpl.this.isCanceled) {
                    InlineClassHelperKt.throwIllegalStateException("Should not execute nested prefetch on canceled request");
                }
                Trace.beginSection("compose:lazy:prefetch:update_nested_prefetch_count");
                try {
                    List<LazyLayoutPrefetchState> list = this.states;
                    int size = list.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        list.get(i2).setRealizedNestedPrefetchCount$foundation(i);
                    }
                    Unit unit = Unit.INSTANCE;
                    Trace.endSection();
                    Trace.beginSection("compose:lazy:prefetch:nested");
                    while (this.stateIndex < this.states.size()) {
                        try {
                            if (this.requestsByState[this.stateIndex] == null) {
                                if (prefetchRequestScope.availableTimeNanos() <= 0) {
                                    Trace.endSection();
                                    return true;
                                }
                                List<PrefetchRequest>[] listArr = this.requestsByState;
                                int i3 = this.stateIndex;
                                listArr[i3] = this.states.get(i3).collectNestedPrefetchRequests$foundation();
                            }
                            List<PrefetchRequest> list2 = this.requestsByState[this.stateIndex];
                            list2.getClass();
                            while (this.requestIndex < list2.size()) {
                                PrefetchRequest prefetchRequest = list2.get(this.requestIndex);
                                if (z) {
                                    HandleAndRequestImpl handleAndRequestImpl = prefetchRequest instanceof HandleAndRequestImpl ? (HandleAndRequestImpl) prefetchRequest : null;
                                    if (handleAndRequestImpl != null) {
                                        handleAndRequestImpl.markAsUrgent();
                                    }
                                }
                                this.executedNestedPrefetch = true;
                                if (prefetchRequest.execute(prefetchRequestScope)) {
                                    Trace.endSection();
                                    return true;
                                }
                                this.requestIndex++;
                            }
                            this.requestIndex = 0;
                            this.stateIndex++;
                        } catch (Throwable th) {
                            Trace.endSection();
                            throw th;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    Trace.endSection();
                    return false;
                } catch (Throwable th2) {
                    Trace.endSection();
                    throw th2;
                }
            }

            public final boolean getExecutedNestedPrefetch() {
                return this.executedNestedPrefetch;
            }

            public final void setExecutedNestedPrefetch(boolean z) {
                this.executedNestedPrefetch = z;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public HandleAndRequestImpl(int i, PrefetchMetrics prefetchMetrics, PriorityPrefetchScheduler priorityPrefetchScheduler, Function1<? super LazyLayoutPrefetchState.PrefetchResultScope, Unit> function1) {
            this.index = i;
            this.prefetchMetrics = prefetchMetrics;
            this.priorityPrefetchScheduler = priorityPrefetchScheduler;
            this.onItemPremeasured = function1;
            this.startTime = TimeSource.Monotonic.INSTANCE.markNow-z9LOYto();
        }

        public static TraversableNode.Companion.TraverseDescendantsAction a(Ref.ObjectRef objectRef, TraversableNode traversableNode) {
            traversableNode.getClass();
            LazyLayoutPrefetchState prefetchState = ((TraversablePrefetchStateNode) traversableNode).getPrefetchState();
            List listMutableListOf = (List) objectRef.element;
            if (listMutableListOf != null) {
                listMutableListOf.add(prefetchState);
            } else {
                listMutableListOf = CollectionsKt.mutableListOf(new LazyLayoutPrefetchState[]{prefetchState});
            }
            objectRef.element = listMutableListOf;
            return TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
        }

        public static boolean b(HandleAndRequestImpl handleAndRequestImpl, Averages averages) {
            if (!handleAndRequestImpl.pauseRequested) {
                handleAndRequestImpl.updateElapsedAndAvailableTime();
                averages.saveResumeTimeNanos(handleAndRequestImpl.elapsedTimeNanos);
                handleAndRequestImpl.pauseRequested = !handleAndRequestImpl.shouldExecute(handleAndRequestImpl.availableTimeNanos, averages.getResumeTimeNanos() + averages.getPauseTimeNanos());
            }
            return handleAndRequestImpl.pauseRequested;
        }

        private final void cleanUp() {
            SubcomposeLayoutState$PausedPrecomposition subcomposeLayoutState$PausedPrecomposition = this.pausedPrecomposition;
            if (subcomposeLayoutState$PausedPrecomposition != null) {
                subcomposeLayoutState$PausedPrecomposition.cancel();
            }
            this.pausedPrecomposition = null;
            SubcomposeLayoutState$PrecomposedSlotHandle subcomposeLayoutState$PrecomposedSlotHandle = this.precomposeHandle;
            if (subcomposeLayoutState$PrecomposedSlotHandle != null) {
                subcomposeLayoutState$PrecomposedSlotHandle.dispose();
            }
            this.precomposeHandle = null;
            this.nestedPrefetchController = null;
        }

        private final boolean executeRequest(PrefetchRequestScope prefetchRequestScope) {
            AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:execute:item", getIndex());
            LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) PrefetchHandleProvider.this.itemContentFactory.getItemProvider().invoke();
            if (!this.isCanceled) {
                int itemCount = lazyLayoutItemProvider.getItemCount();
                int index = getIndex();
                if (index >= 0 && index < itemCount) {
                    Object key = lazyLayoutItemProvider.getKey(getIndex());
                    Object obj = this.keyUsedForComposition;
                    if (obj != null && !Intrinsics.areEqual(key, obj)) {
                        cleanUp();
                        return false;
                    }
                    Object contentType = lazyLayoutItemProvider.getContentType(getIndex());
                    Averages average = this.prefetchMetrics.getAverage(contentType);
                    boolean zIsComposed = isComposed();
                    resetAvailableTimeTo(prefetchRequestScope.availableTimeNanos());
                    if (!isComposed()) {
                        boolean z = ComposeFoundationFlags.isPausableCompositionInPrefetchEnabled;
                        long j = this.availableTimeNanos;
                        if (z) {
                            if (shouldExecute(j, average.getResumeTimeNanos() + average.getPauseTimeNanos())) {
                                Trace.beginSection("compose:lazy:prefetch:compose");
                                try {
                                    performPausableComposition(prefetchRequestScope, key, contentType, average);
                                    Unit unit = Unit.INSTANCE;
                                    Trace.endSection();
                                } catch (Throwable th) {
                                    Trace.endSection();
                                    throw th;
                                }
                            }
                        } else if (shouldExecute(j, average.getCompositionTimeNanos())) {
                            Trace.beginSection("compose:lazy:prefetch:compose");
                            try {
                                performFullComposition(key, contentType);
                                Unit unit2 = Unit.INSTANCE;
                                Trace.endSection();
                                updateElapsedAndAvailableTime();
                                average.saveCompositionTimeNanos(this.elapsedTimeNanos);
                            } catch (Throwable th2) {
                                Trace.endSection();
                                throw th2;
                            }
                        }
                        if (!isComposed()) {
                            return true;
                        }
                    }
                    if (this.pausedPrecomposition != null) {
                        if (!shouldExecute(this.availableTimeNanos, average.getApplyTimeNanos())) {
                            return true;
                        }
                        Trace.beginSection("compose:lazy:prefetch:apply");
                        try {
                            performApply();
                            Unit unit3 = Unit.INSTANCE;
                            Trace.endSection();
                            updateElapsedAndAvailableTime();
                            average.saveApplyTimeNanos(this.elapsedTimeNanos);
                        } catch (Throwable th3) {
                            Trace.endSection();
                            throw th3;
                        }
                    }
                    if (!this.hasResolvedNestedPrefetches) {
                        if (this.availableTimeNanos <= 0) {
                            return true;
                        }
                        Trace.beginSection("compose:lazy:prefetch:resolve-nested");
                        try {
                            this.nestedPrefetchController = resolveNestedPrefetchStates();
                            this.hasResolvedNestedPrefetches = true;
                            Unit unit4 = Unit.INSTANCE;
                            Trace.endSection();
                        } catch (Throwable th4) {
                            Trace.endSection();
                            throw th4;
                        }
                    }
                    NestedPrefetchController nestedPrefetchController = this.nestedPrefetchController;
                    if (nestedPrefetchController != null ? nestedPrefetchController.executeNestedPrefetches(prefetchRequestScope, average.getNestedPrefetchCount(), this.isUrgent) : false) {
                        return true;
                    }
                    NestedPrefetchController nestedPrefetchController2 = this.nestedPrefetchController;
                    if (nestedPrefetchController2 != null && nestedPrefetchController2.getExecutedNestedPrefetch()) {
                        updateElapsedAndAvailableTime();
                        AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:execute:item", getIndex());
                        NestedPrefetchController nestedPrefetchController3 = this.nestedPrefetchController;
                        if (nestedPrefetchController3 != null) {
                            nestedPrefetchController3.setExecutedNestedPrefetch(false);
                        }
                    }
                    Constraints constraints = this.premeasureConstraints;
                    if (!this.isMeasured && constraints != null) {
                        if ((PrefetchHandleProvider.this.getShouldPauseBetweenPrecompositionAndPremeasure() && !zIsComposed) || !shouldExecute(this.availableTimeNanos, average.getMeasureTimeNanos())) {
                            return true;
                        }
                        Trace.beginSection("compose:lazy:prefetch:measure");
                        try {
                            m1166performMeasureBRTryo0(constraints.unbox-impl());
                            Unit unit5 = Unit.INSTANCE;
                            Trace.endSection();
                            updateElapsedAndAvailableTime();
                            average.saveMeasureTimeNanos(this.elapsedTimeNanos);
                            Function1<LazyLayoutPrefetchState.PrefetchResultScope, Unit> function1 = this.onItemPremeasured;
                            if (function1 != null) {
                                function1.invoke(this);
                            }
                        } catch (Throwable th5) {
                            Trace.endSection();
                            throw th5;
                        }
                    }
                    NestedPrefetchController nestedPrefetchController4 = this.nestedPrefetchController;
                    if (this.isMeasured && this.hasResolvedNestedPrefetches && nestedPrefetchController4 != null) {
                        int iCollectIdealNestedPrefetchCount = nestedPrefetchController4.collectIdealNestedPrefetchCount();
                        average.saveNestedPrefetchCount(iCollectIdealNestedPrefetchCount);
                        if (nestedPrefetchController4.collectNestedPrefetchedItemsCount() < iCollectIdealNestedPrefetchCount) {
                            average.clearMeasureTime();
                        }
                    }
                    return false;
                }
            }
            cleanUp();
            return false;
        }

        private final boolean isComposed() {
            SubcomposeLayoutState$PausedPrecomposition subcomposeLayoutState$PausedPrecomposition;
            return this.isApplied || ((subcomposeLayoutState$PausedPrecomposition = this.pausedPrecomposition) != null && subcomposeLayoutState$PausedPrecomposition.isComplete());
        }

        private final void performApply() {
            SubcomposeLayoutState$PausedPrecomposition subcomposeLayoutState$PausedPrecomposition = this.pausedPrecomposition;
            if (subcomposeLayoutState$PausedPrecomposition == null) {
                w01.a("Nothing to apply!");
                return;
            }
            this.precomposeHandle = subcomposeLayoutState$PausedPrecomposition.apply();
            this.pausedPrecomposition = null;
            this.isApplied = true;
        }

        private final void performFullComposition(Object key, Object contentType) {
            if (!(this.precomposeHandle == null)) {
                InlineClassHelperKt.throwIllegalArgumentException("Request was already composed!");
            }
            Function2<Composer, Integer, Unit> content = PrefetchHandleProvider.this.itemContentFactory.getContent(getIndex(), key, contentType);
            this.keyUsedForComposition = key;
            this.precomposeHandle = PrefetchHandleProvider.this.subcomposeLayoutState.precompose(key, content);
            this.isApplied = true;
        }

        /* JADX INFO: renamed from: performMeasure-BRTryo0, reason: not valid java name */
        private final void m1166performMeasureBRTryo0(long constraints) {
            if (this.isCanceled) {
                InlineClassHelperKt.throwIllegalArgumentException("Callers should check whether the request is still valid before calling performMeasure()");
            }
            if (this.isMeasured) {
                InlineClassHelperKt.throwIllegalArgumentException("Request was already measured!");
            }
            this.isMeasured = true;
            SubcomposeLayoutState$PrecomposedSlotHandle subcomposeLayoutState$PrecomposedSlotHandle = this.precomposeHandle;
            if (subcomposeLayoutState$PrecomposedSlotHandle == null) {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("performComposition() must be called before performMeasure()");
                wq6.a();
                return;
            }
            int placeablesCount = subcomposeLayoutState$PrecomposedSlotHandle.getPlaceablesCount();
            for (int i = 0; i < placeablesCount; i++) {
                subcomposeLayoutState$PrecomposedSlotHandle.m1859premeasure0kLqBqw(i, constraints);
            }
        }

        private final void performPausableComposition(PrefetchRequestScope prefetchRequestScope, Object obj, Object obj2, final Averages averages) {
            SubcomposeLayoutState$PausedPrecomposition subcomposeLayoutState$PausedPrecompositionCreatePausedPrecomposition = this.pausedPrecomposition;
            if (subcomposeLayoutState$PausedPrecompositionCreatePausedPrecomposition == null) {
                PrefetchHandleProvider prefetchHandleProvider = PrefetchHandleProvider.this;
                subcomposeLayoutState$PausedPrecompositionCreatePausedPrecomposition = prefetchHandleProvider.subcomposeLayoutState.createPausedPrecomposition(obj, prefetchHandleProvider.itemContentFactory.getContent(getIndex(), obj, obj2));
                this.pausedPrecomposition = subcomposeLayoutState$PausedPrecompositionCreatePausedPrecomposition;
                this.keyUsedForComposition = obj;
            }
            this.pauseRequested = false;
            while (!subcomposeLayoutState$PausedPrecompositionCreatePausedPrecomposition.isComplete() && !this.pauseRequested) {
                subcomposeLayoutState$PausedPrecompositionCreatePausedPrecomposition.resume(new ShouldPauseCallback() { // from class: androidx.compose.foundation.lazy.layout.s
                    public final boolean shouldPause() {
                        return PrefetchHandleProvider.HandleAndRequestImpl.b(this.a, averages);
                    }
                });
            }
            updateElapsedAndAvailableTime();
            boolean z = this.pauseRequested;
            long j = this.elapsedTimeNanos;
            if (z) {
                averages.savePauseTimeNanos(j);
            } else {
                averages.saveResumeTimeNanos(j);
            }
        }

        private final void resetAvailableTimeTo(long availableTimeNanos) {
            this.availableTimeNanos = availableTimeNanos;
            this.startTime = TimeSource.Monotonic.INSTANCE.markNow-z9LOYto();
            this.elapsedTimeNanos = 0L;
            AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:available_time_nanos", availableTimeNanos);
        }

        private final NestedPrefetchController resolveNestedPrefetchStates() {
            SubcomposeLayoutState$PrecomposedSlotHandle subcomposeLayoutState$PrecomposedSlotHandle = this.precomposeHandle;
            if (subcomposeLayoutState$PrecomposedSlotHandle == null) {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("Should precompose before resolving nested prefetch states");
                wq6.a();
                return null;
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            subcomposeLayoutState$PrecomposedSlotHandle.traverseDescendants("androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode", new Function1() { // from class: androidx.compose.foundation.lazy.layout.r
                public final Object invoke(Object obj) {
                    return PrefetchHandleProvider.HandleAndRequestImpl.a(objectRef, (TraversableNode) obj);
                }
            });
            List list = (List) objectRef.element;
            if (list != null) {
                return new NestedPrefetchController(list);
            }
            return null;
        }

        private final boolean shouldExecute(long available, long average) {
            if (this.isUrgent) {
                average = 0;
            }
            return available > average;
        }

        private final void updateElapsedAndAvailableTime() {
            long j = TimeSource.Monotonic.INSTANCE.markNow-z9LOYto();
            long j2 = Duration.getInWholeNanoseconds-impl(TimeSource.Monotonic.ValueTimeMark.minus-6eNON_k(j, this.startTime));
            this.elapsedTimeNanos = j2;
            long j3 = this.availableTimeNanos - j2;
            this.availableTimeNanos = j3;
            this.startTime = j;
            AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:available_time_nanos", j3);
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public void cancel() {
            if (this.isCanceled) {
                return;
            }
            this.isCanceled = true;
            cleanUp();
        }

        @Override // androidx.compose.foundation.lazy.layout.PrefetchRequest
        public boolean execute(PrefetchRequestScope prefetchRequestScope) {
            boolean zExecuteRequest;
            if (!PrefetchHandleProvider.this.isStateActive) {
                return false;
            }
            if (this.isUrgent) {
                Trace.beginSection("compose:lazy:prefetch:execute:urgent");
                try {
                    zExecuteRequest = executeRequest(prefetchRequestScope);
                    Trace.endSection();
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } else {
                zExecuteRequest = executeRequest(prefetchRequestScope);
            }
            AndroidTrace_androidKt.traceValue("compose:lazy:prefetch:execute:item", -1L);
            return zExecuteRequest;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchResultScope
        public int getIndex() {
            return this.index;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchResultScope
        public int getPlaceablesCount() {
            SubcomposeLayoutState$PrecomposedSlotHandle subcomposeLayoutState$PrecomposedSlotHandle = this.precomposeHandle;
            if (subcomposeLayoutState$PrecomposedSlotHandle != null) {
                return subcomposeLayoutState$PrecomposedSlotHandle.getPlaceablesCount();
            }
            return 0;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchResultScope
        /* JADX INFO: renamed from: getSize-YEO4UFw */
        public long mo1151getSizeYEO4UFw(int placeableIndex) {
            SubcomposeLayoutState$PrecomposedSlotHandle subcomposeLayoutState$PrecomposedSlotHandle = this.precomposeHandle;
            return subcomposeLayoutState$PrecomposedSlotHandle != null ? subcomposeLayoutState$PrecomposedSlotHandle.m1858getSizeYEO4UFw(placeableIndex) : IntSize.Companion.getZero-YbymL2g();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public void markAsUrgent() {
            this.isUrgent = true;
        }

        public String toString() {
            return "HandleAndRequestImpl { index = " + getIndex() + ", constraints = " + this.premeasureConstraints + ", isComposed = " + isComposed() + ", isMeasured = " + this.isMeasured + ", isCanceled = " + this.isCanceled + " }";
        }

        public /* synthetic */ HandleAndRequestImpl(PrefetchHandleProvider prefetchHandleProvider, int i, long j, PrefetchMetrics prefetchMetrics, PriorityPrefetchScheduler priorityPrefetchScheduler, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
            this(prefetchHandleProvider, i, j, prefetchMetrics, priorityPrefetchScheduler, function1);
        }

        private HandleAndRequestImpl(PrefetchHandleProvider prefetchHandleProvider, int i, long j, PrefetchMetrics prefetchMetrics, PriorityPrefetchScheduler priorityPrefetchScheduler, Function1<? super LazyLayoutPrefetchState.PrefetchResultScope, Unit> function1) {
            this(i, prefetchMetrics, priorityPrefetchScheduler, function1);
            this.premeasureConstraints = Constraints.box-impl(j);
        }
    }
}
