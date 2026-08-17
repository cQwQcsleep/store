package androidx.compose.animation.core;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0002J<\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u001c\u0010\u0012\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013H\u0086@¢\u0006\u0002\u0010\u0015JU\u0010\u0016\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u000f2\u0006\u0010\u0018\u001a\u0002H\u00172\b\b\u0002\u0010\u0010\u001a\u00020\u00112'\u0010\u0012\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019¢\u0006\u0002\b\u001aH\u0086@¢\u0006\u0002\u0010\u001bR$\u0010\u0004\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0006`\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/animation/core/MutatorMutex;", "", "<init>", "()V", "currentMutator", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/animation/core/MutatorMutex$Mutator;", "Landroidx/compose/animation/core/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "tryMutateOrCancel", "", "mutator", "mutate", "R", "priority", "Landroidx/compose/animation/core/MutatePriority;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Landroidx/compose/animation/core/MutatePriority;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mutateWith", "T", "receiver", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Landroidx/compose/animation/core/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Mutator", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MutatorMutex {
    public static final int $stable = 0;
    private final AtomicReference<Mutator> currentMutator = new AtomicReference<>(null);
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, (Object) null);

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0000J\u0006\u0010\u000f\u001a\u00020\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Landroidx/compose/animation/core/MutatorMutex$Mutator;", "", "priority", "Landroidx/compose/animation/core/MutatePriority;", "job", "Lkotlinx/coroutines/Job;", "<init>", "(Landroidx/compose/animation/core/MutatePriority;Lkotlinx/coroutines/Job;)V", "getPriority", "()Landroidx/compose/animation/core/MutatePriority;", "getJob", "()Lkotlinx/coroutines/Job;", "canInterrupt", "", "other", "cancel", "", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Mutator {
        private final Job job;
        private final MutatePriority priority;

        public Mutator(MutatePriority mutatePriority, Job job) {
            this.priority = mutatePriority;
            this.job = job;
        }

        public final boolean canInterrupt(Mutator other) {
            return this.priority.compareTo(other.priority) >= 0;
        }

        public final void cancel() {
            this.job.cancel(new MutationInterruptedException());
        }

        public final Job getJob() {
            return this.job;
        }

        public final MutatePriority getPriority() {
            return this.priority;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.MutatorMutex$mutate$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.MutatorMutex$mutate$2", f = "InternalMutatorMutex.kt", i = {0, 0, 1, 1}, l = {178, 126}, m = "invokeSuspend", n = {"mutator", "$this$withLock_u24default$iv", "mutator", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 1)
    public static final class AnonymousClass2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
        final /* synthetic */ MutatePriority $priority;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MutatorMutex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(MutatePriority mutatePriority, MutatorMutex mutatorMutex, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$priority = mutatePriority;
            this.this$0 = mutatorMutex;
            this.$block = function1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$priority, this.this$0, this.$block, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.sync.Mutex] */
        public final Object invokeSuspend(Object obj) {
            Mutator mutator;
            Mutex mutex;
            MutatorMutex mutatorMutex;
            Function1<Continuation<? super R>, Object> function1;
            Throwable th;
            Mutator mutator2;
            MutatorMutex mutatorMutex2;
            Mutex mutex2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
                try {
                    if (r1 == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        MutatePriority mutatePriority = this.$priority;
                        Job job = coroutineScope.getCoroutineContext().get(Job.Key);
                        job.getClass();
                        mutator = new Mutator(mutatePriority, job);
                        this.this$0.tryMutateOrCancel(mutator);
                        mutex = this.this$0.mutex;
                        Function1<Continuation<? super R>, Object> function2 = this.$block;
                        mutatorMutex = this.this$0;
                        this.L$0 = mutator;
                        this.L$1 = mutex;
                        this.L$2 = function2;
                        this.L$3 = mutatorMutex;
                        this.label = 1;
                        if (mutex.lock((Object) null, this) != coroutine_suspended) {
                            function1 = function2;
                        }
                        return coroutine_suspended;
                    }
                    if (r1 != 1) {
                        if (r1 != 2) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        mutatorMutex2 = (MutatorMutex) this.L$2;
                        mutex2 = (Mutex) this.L$1;
                        mutator2 = (Mutator) this.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            mutatorMutex2.currentMutator.compareAndSet(mutator2, null);
                            mutex2.unlock((Object) null);
                            return obj;
                        } catch (Throwable th2) {
                            th = th2;
                            mutatorMutex2.currentMutator.compareAndSet(mutator2, null);
                            throw th;
                        }
                    }
                    MutatorMutex mutatorMutex3 = (MutatorMutex) this.L$3;
                    function1 = (Function1) this.L$2;
                    Mutex mutex3 = (Mutex) this.L$1;
                    Mutator mutator3 = (Mutator) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    mutatorMutex = mutatorMutex3;
                    mutator = mutator3;
                    mutex = mutex3;
                    this.L$0 = mutator;
                    this.L$1 = mutex;
                    this.L$2 = mutatorMutex;
                    this.L$3 = null;
                    this.label = 2;
                    Object objInvoke = function1.invoke(this);
                    if (objInvoke != coroutine_suspended) {
                        Mutex mutex4 = mutex;
                        obj = objInvoke;
                        mutator2 = mutator;
                        mutex2 = mutex4;
                        mutatorMutex2 = mutatorMutex;
                        mutatorMutex2.currentMutator.compareAndSet(mutator2, null);
                        mutex2.unlock((Object) null);
                        return obj;
                    }
                    return coroutine_suspended;
                } catch (Throwable th3) {
                    th = th3;
                    mutator2 = mutator;
                    mutatorMutex2 = mutatorMutex;
                    mutatorMutex2.currentMutator.compareAndSet(mutator2, null);
                    throw th;
                }
            } catch (Throwable th4) {
                r1.unlock((Object) null);
                throw th4;
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.MutatorMutex$mutateWith$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.MutatorMutex$mutateWith$2", f = "InternalMutatorMutex.kt", i = {0, 0, 1, 1}, l = {178, 165}, m = "invokeSuspend", n = {"mutator", "$this$withLock_u24default$iv", "mutator", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 1)
    public static final class C00832<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<T, Continuation<? super R>, Object> $block;
        final /* synthetic */ MutatePriority $priority;
        final /* synthetic */ T $receiver;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ MutatorMutex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00832(MutatePriority mutatePriority, MutatorMutex mutatorMutex, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, T t, Continuation<? super C00832> continuation) {
            super(2, continuation);
            this.$priority = mutatePriority;
            this.this$0 = mutatorMutex;
            this.$block = function2;
            this.$receiver = t;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00832 c00832 = new C00832(this.$priority, this.this$0, this.$block, this.$receiver, continuation);
            c00832.L$0 = obj;
            return c00832;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.sync.Mutex] */
        public final Object invokeSuspend(Object obj) {
            Mutator mutator;
            Mutex mutex;
            Function2 function2;
            MutatorMutex mutatorMutex;
            Object obj2;
            Throwable th;
            Mutator mutator2;
            MutatorMutex mutatorMutex2;
            Mutex mutex2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
                try {
                    if (r1 == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        MutatePriority mutatePriority = this.$priority;
                        Job job = coroutineScope.getCoroutineContext().get(Job.Key);
                        job.getClass();
                        mutator = new Mutator(mutatePriority, job);
                        this.this$0.tryMutateOrCancel(mutator);
                        mutex = this.this$0.mutex;
                        function2 = this.$block;
                        Object obj3 = this.$receiver;
                        mutatorMutex = this.this$0;
                        this.L$0 = mutator;
                        this.L$1 = mutex;
                        this.L$2 = function2;
                        this.L$3 = obj3;
                        this.L$4 = mutatorMutex;
                        this.label = 1;
                        if (mutex.lock((Object) null, this) != coroutine_suspended) {
                            obj2 = obj3;
                        }
                        return coroutine_suspended;
                    }
                    if (r1 != 1) {
                        if (r1 != 2) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        mutatorMutex2 = (MutatorMutex) this.L$2;
                        mutex2 = (Mutex) this.L$1;
                        mutator2 = (Mutator) this.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            mutatorMutex2.currentMutator.compareAndSet(mutator2, null);
                            mutex2.unlock((Object) null);
                            return obj;
                        } catch (Throwable th2) {
                            th = th2;
                            mutatorMutex2.currentMutator.compareAndSet(mutator2, null);
                            throw th;
                        }
                    }
                    MutatorMutex mutatorMutex3 = (MutatorMutex) this.L$4;
                    obj2 = this.L$3;
                    function2 = (Function2) this.L$2;
                    Mutex mutex3 = (Mutex) this.L$1;
                    Mutator mutator3 = (Mutator) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    mutatorMutex = mutatorMutex3;
                    mutator = mutator3;
                    mutex = mutex3;
                    this.L$0 = mutator;
                    this.L$1 = mutex;
                    this.L$2 = mutatorMutex;
                    this.L$3 = null;
                    this.L$4 = null;
                    this.label = 2;
                    Object objInvoke = function2.invoke(obj2, this);
                    if (objInvoke != coroutine_suspended) {
                        Mutex mutex4 = mutex;
                        obj = objInvoke;
                        mutator2 = mutator;
                        mutex2 = mutex4;
                        mutatorMutex2 = mutatorMutex;
                        mutatorMutex2.currentMutator.compareAndSet(mutator2, null);
                        mutex2.unlock((Object) null);
                        return obj;
                    }
                    return coroutine_suspended;
                } catch (Throwable th3) {
                    th = th3;
                    mutator2 = mutator;
                    mutatorMutex2 = mutatorMutex;
                    mutatorMutex2.currentMutator.compareAndSet(mutator2, null);
                    throw th;
                }
            } catch (Throwable th4) {
                r1.unlock((Object) null);
                throw th4;
            }
        }
    }

    public static /* synthetic */ Object mutate$default(MutatorMutex mutatorMutex, MutatePriority mutatePriority, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return mutatorMutex.mutate(mutatePriority, function1, continuation);
    }

    public static /* synthetic */ Object mutateWith$default(MutatorMutex mutatorMutex, Object obj, MutatePriority mutatePriority, Function2 function2, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return mutatorMutex.mutateWith(obj, mutatePriority, function2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tryMutateOrCancel(Mutator mutator) {
        Mutator mutator2;
        do {
            mutator2 = this.currentMutator.get();
            if (mutator2 != null && !mutator.canInterrupt(mutator2)) {
                throw new CancellationException("Current mutation had a higher priority");
            }
        } while (!this.currentMutator.compareAndSet(mutator2, mutator));
        if (mutator2 != null) {
            mutator2.cancel();
        }
    }

    public final <R> Object mutate(MutatePriority mutatePriority, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(mutatePriority, this, function1, null), continuation);
    }

    public final <T, R> Object mutateWith(T t, MutatePriority mutatePriority, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        return CoroutineScopeKt.coroutineScope(new C00832(mutatePriority, this, function2, t, null), continuation);
    }
}
