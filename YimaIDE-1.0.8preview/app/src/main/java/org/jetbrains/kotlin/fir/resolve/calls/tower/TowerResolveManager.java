package org.jetbrains.kotlin.fir.resolve.calls.tower;

import java.util.PriorityQueue;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001eB\u001d\b\u0002\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0015\u001a\u00020\u0016H\u0082@¢\u0006\u0002\u0010\u0017J3\u0010\u0018\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00042\u001c\u0010\u0019\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\rH\u0002J\u0006\u0010\u001d\u001a\u00020\u000fR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;", Argument.Delimiters.none, "shouldStopAtTheLevel", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", Argument.Delimiters.none, "<init>", "(Lkotlin/jvm/functions/Function1;)V", "collector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;)V", "queue", "Ljava/util/PriorityQueue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager$SuspendedResolverTask;", "reset", Argument.Delimiters.none, "suspendResolverTask", "group", "(Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestGroup", "requested", "stopResolverTask", Argument.Delimiters.none, "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enqueueResolverTask", "task", "Lkotlin/coroutines/Continuation;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lkotlin/jvm/functions/Function1;)V", "resumeTask", "runTasks", "SuspendedResolverTask", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TowerResolveManager {
    private final PriorityQueue<SuspendedResolverTask> queue;
    private final Function1<TowerGroup, Boolean> shouldStopAtTheLevel;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.TowerResolveManager$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<TowerGroup, Boolean> {
        public AnonymousClass1(Object obj) {
            super(1, obj, CandidateCollector.class, "shouldStopAtTheGroup", "shouldStopAtTheGroup(Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;)Z", 0);
        }

        public final Boolean invoke(TowerGroup towerGroup) {
            towerGroup.getClass();
            return Boolean.valueOf(((CandidateCollector) ((CallableReference) this).receiver).shouldStopAtTheGroup(towerGroup));
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0000H\u0096\u0082\u0004J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J#\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager$SuspendedResolverTask;", Argument.Delimiters.none, "continuation", "Lkotlin/coroutines/Continuation;", Argument.Delimiters.none, "group", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "<init>", "(Lkotlin/coroutines/Continuation;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;)V", "getContinuation", "()Lkotlin/coroutines/Continuation;", "getGroup", "()Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "compareTo", Argument.Delimiters.none, "other", "component1", "component2", "copy", "equals", Argument.Delimiters.none, Argument.Delimiters.none, "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class SuspendedResolverTask implements Comparable<SuspendedResolverTask> {
        private final Continuation<Unit> continuation;
        private final TowerGroup group;

        public SuspendedResolverTask(Continuation<? super Unit> continuation, TowerGroup towerGroup) {
            continuation.getClass();
            towerGroup.getClass();
            this.continuation = continuation;
            this.group = towerGroup;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SuspendedResolverTask copy$default(SuspendedResolverTask suspendedResolverTask, Continuation continuation, TowerGroup towerGroup, int i, Object obj) {
            if ((i & 1) != 0) {
                continuation = suspendedResolverTask.continuation;
            }
            if ((i & 2) != 0) {
                towerGroup = suspendedResolverTask.group;
            }
            return suspendedResolverTask.copy(continuation, towerGroup);
        }

        @Override // java.lang.Comparable
        public int compareTo(SuspendedResolverTask other) {
            other.getClass();
            return this.group.compareTo(other.group);
        }

        public final Continuation<Unit> component1() {
            return this.continuation;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final TowerGroup getGroup() {
            return this.group;
        }

        public final SuspendedResolverTask copy(Continuation<? super Unit> continuation, TowerGroup group) {
            continuation.getClass();
            group.getClass();
            return new SuspendedResolverTask(continuation, group);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SuspendedResolverTask)) {
                return false;
            }
            SuspendedResolverTask suspendedResolverTask = (SuspendedResolverTask) other;
            return Intrinsics.areEqual(this.continuation, suspendedResolverTask.continuation) && Intrinsics.areEqual(this.group, suspendedResolverTask.group);
        }

        public final Continuation<Unit> getContinuation() {
            return this.continuation;
        }

        public final TowerGroup getGroup() {
            return this.group;
        }

        public int hashCode() {
            return (this.continuation.hashCode() * 31) + this.group.hashCode();
        }

        public String toString() {
            return "SuspendedResolverTask(continuation=" + this.continuation + ", group=" + this.group + ')';
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.TowerResolveManager$requestGroup$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00531 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C00531(Continuation<? super C00531> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TowerResolveManager.this.requestGroup(null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.TowerResolveManager$stopResolverTask$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00541 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C00541(Continuation<? super C00541> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TowerResolveManager.this.stopResolverTask(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TowerResolveManager(Function1<? super TowerGroup, Boolean> function1) {
        this.shouldStopAtTheLevel = function1;
        this.queue = new PriorityQueue<>();
    }

    public static /* synthetic */ void enqueueResolverTask$default(TowerResolveManager towerResolveManager, TowerGroup towerGroup, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            towerGroup = TowerGroup.INSTANCE.getStart();
        }
        towerResolveManager.enqueueResolverTask(towerGroup, function1);
    }

    private final void resumeTask(SuspendedResolverTask task) {
        if (((Boolean) this.shouldStopAtTheLevel.invoke(task.getGroup())).booleanValue()) {
            return;
        }
        Continuation<Unit> continuation = task.getContinuation();
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.constructor-impl(Unit.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object stopResolverTask(Continuation<?> continuation) {
        C00541 c00541;
        if (continuation instanceof C00541) {
            c00541 = (C00541) continuation;
            int i = c00541.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00541.label = i - Integer.MIN_VALUE;
            } else {
                c00541 = new C00541(continuation);
            }
        } else {
            c00541 = new C00541(continuation);
        }
        Object obj = c00541.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00541.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            c00541.label = 1;
            Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (coroutine_suspended2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(c00541);
            }
            if (coroutine_suspended2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        wq6.a();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object suspendResolverTask(TowerGroup towerGroup, Continuation<? super Unit> continuation) {
        this.queue.add(new SuspendedResolverTask(continuation, towerGroup));
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutine_suspended : Unit.INSTANCE;
    }

    public final void enqueueResolverTask(TowerGroup group, Function1<? super Continuation<? super Unit>, ? extends Object> task) {
        group.getClass();
        task.getClass();
        this.queue.add(new SuspendedResolverTask(IntrinsicsKt.createCoroutineUnintercepted(task, new Continuation<Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.tower.TowerResolveManager$enqueueResolverTask$continuation$1
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            public void resumeWith(Object result) {
                ResultKt.throwOnFailure(result);
            }
        }), group));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0060, code lost:
    
        if (stopResolverTask(r0) == r1) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object requestGroup(TowerGroup towerGroup, Continuation<? super Unit> continuation) {
        C00531 c00531;
        if (continuation instanceof C00531) {
            c00531 = (C00531) continuation;
            int i = c00531.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00531.label = i - Integer.MIN_VALUE;
            } else {
                c00531 = new C00531(continuation);
            }
        } else {
            c00531 = new C00531(continuation);
        }
        Object obj = c00531.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00531.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            if (((Boolean) this.shouldStopAtTheLevel.invoke(towerGroup)).booleanValue()) {
                c00531.L$0 = SpillingKt.nullOutSpilledVariable(towerGroup);
                c00531.label = 1;
            } else {
                SuspendedResolverTask suspendedResolverTaskPeek = this.queue.peek();
                if (suspendedResolverTaskPeek == null || suspendedResolverTaskPeek.getGroup().compareTo(towerGroup) > 0) {
                    return Unit.INSTANCE;
                }
                c00531.L$0 = SpillingKt.nullOutSpilledVariable(towerGroup);
                c00531.L$1 = SpillingKt.nullOutSpilledVariable(suspendedResolverTaskPeek);
                c00531.label = 2;
                Object objSuspendResolverTask = suspendResolverTask(towerGroup, c00531);
                if (objSuspendResolverTask != coroutine_suspended) {
                    return objSuspendResolverTask;
                }
            }
            return coroutine_suspended;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        wq6.a();
        return null;
    }

    public final void reset() {
        this.queue.clear();
    }

    public final void runTasks() {
        while (!this.queue.isEmpty()) {
            SuspendedResolverTask suspendedResolverTaskPoll = this.queue.poll();
            suspendedResolverTaskPoll.getClass();
            resumeTask(suspendedResolverTaskPoll);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TowerResolveManager(CandidateCollector candidateCollector) {
        this(new AnonymousClass1(candidateCollector));
        candidateCollector.getClass();
    }
}
