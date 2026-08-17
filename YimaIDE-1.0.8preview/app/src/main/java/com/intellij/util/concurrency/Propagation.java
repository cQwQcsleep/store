package com.intellij.util.concurrency;

import com.intellij.concurrency.ContextAwareCallable;
import com.intellij.concurrency.ContextAwareRunnable;
import com.intellij.concurrency.IntelliJContextElement;
import com.intellij.concurrency.ThreadContext;
import com.intellij.concurrency.client.ClientIdPropagation;
import com.intellij.openapi.application.AccessToken;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.CeProcessCanceledException;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.util.SmartList;
import com.intellij.util.concurrency.Propagation;
import com.intellij.util.lang.CompoundRuntimeException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0007\u001a\u0014\u0010\b\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0007\u001a\u0015\u0010\u000f\u001a\u00020\u00102\u000b\u0010\u0011\u001a\u00070\u0012¢\u0006\u0002\b\u0013H\u0007\u001a\u0015\u0010\u0014\u001a\u00020\u00102\u000b\u0010\u0011\u001a\u00070\u0012¢\u0006\u0002\b\u0013H\u0007\u001a\u0015\u0010\u0015\u001a\u00020\u00102\u000b\u0010\u0011\u001a\u00070\u0012¢\u0006\u0002\b\u0013H\u0007\u001a\u001d\u0010\u0016\u001a\u00020\u00102\u000b\u0010\u0011\u001a\u00070\u0012¢\u0006\u0002\b\u00132\u0006\u0010\u0017\u001a\u00020\nH\u0003\u001a*\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u00192\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\nH\u0002\u001a8\u0010\u001f\u001a\u00020 \"\u0004\b\u0000\u0010!2\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H!0\u001b2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u00020\u00040&H\u0002\u001a.\u0010'\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\n2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001c0+H\u0002\u001a#\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040-2\u000b\u0010\u0011\u001a\u00070\u0012¢\u0006\u0002\b\u00132\u0006\u0010.\u001a\u00020/H\u0002\u001a\u0018\u00100\u001a\u00060\u0006j\u0002`\u00072\n\u00101\u001a\u00060\u0006j\u0002`\u0007H\u0000\u001a\"\u00102\u001a\b\u0012\u0004\u0012\u0002H403\"\u0004\b\u0000\u001042\f\u00105\u001a\b\u0012\u0004\u0012\u0002H403H\u0000\u001a\u000e\u00106\u001a\u00020\n2\u0006\u0010\u0005\u001a\u000207\u001a7\u00108\u001a\u0002H!\"\u0004\b\u0000\u0010!2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00040-2\u0006\u0010:\u001a\u00020\n2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H!0;H\u0007¢\u0006\u0002\u0010<\u001a\"\u0010=\u001a\u00060\u0006j\u0002`\u00072\n\u0010>\u001a\u00060\u0006j\u0002`\u00072\b\b\u0002\u0010?\u001a\u00020\nH\u0000\u001a@\u0010=\u001a\u0016\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030A0@2\n\u0010>\u001a\u00060\u0006j\u0002`\u00072\n\u0010B\u001a\u0006\u0012\u0002\b\u00030A2\n\u0010C\u001a\u00060\u0006j\u0002`\u0007H\u0007\u001a4\u0010D\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002HF0E\"\u0004\b\u0000\u0010!\"\u0004\b\u0001\u0010F2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002HF0EH\u0007\u001a6\u0010H\u001a\b\u0012\u0004\u0012\u0002H!0A\"\u0004\b\u0000\u0010!2\u000e\u0010I\u001a\n\u0012\u0006\b\u0000\u0012\u0002H!0A2\u0006\u0010J\u001a\u00020\u00102\b\u0010K\u001a\u0004\u0018\u00010/H\u0002\u001a\"\u0010=\u001a\b\u0012\u0004\u0012\u0002H40L\"\u0004\b\u0000\u001042\f\u0010M\u001a\b\u0012\u0004\u0012\u0002H403H\u0000\u001a4\u0010=\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002HO0N\"\u0004\b\u0000\u0010!\"\u0004\b\u0001\u0010O2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002HO0NH\u0000\u001a6\u0010=\u001a\f\u0012\u0004\u0012\u0002H40QR\u00020R\"\u0004\b\u0000\u001042\u0006\u0010S\u001a\u00020R2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002H4032\u0006\u0010T\u001a\u00020UH\u0000\u001a4\u0010=\u001a\n\u0012\u0002\b\u00030QR\u00020R2\u0006\u0010S\u001a\u00020R2\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020UH\u0000\u001a\u0018\u0010W\u001a\u0006\u0012\u0002\b\u0003032\n\u0010>\u001a\u00060\u0006j\u0002`\u0007H\u0007\u001a\u0012\u0010X\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007\"\u0013\u0010\u0000\u001a\u00070\u0001¢\u0006\u0002\b\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\t\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000b\"\u0014\u0010\f\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000b\"\u0014\u0010\r\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000b¨\u0006Y"}, d2 = {"LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "runWithContextPropagationEnabled", "", "runnable", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "runWithImplicitBlockingContextEnabled", "isPropagateThreadContext", "", "()Z", "isCheckContextAssertions", "useImplicitBlockingContext", "getUseImplicitBlockingContext", "createChildContext", "Lcom/intellij/util/concurrency/ChildContext;", "debugName", "", "Lorg/jetbrains/annotations/NonNls;", "createChildContextWithContextJob", "createChildContextIgnoreStructuredConcurrency", "doCreateChildContext", "unconditionalCancellationPropagation", "gatherAppliedChildContext", "Lkotlin/Pair;", "Lkotlin/coroutines/CoroutineContext;", "", "Lcom/intellij/concurrency/IntelliJContextElement;", "parentContext", "isStructured", "cleanupList", "", "T", "original", "", "list", "action", "Lkotlin/Function1;", "produceChildContextElement", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "ijElements", "", "childContinuation", "Lkotlin/coroutines/Continuation;", "parent", "Lkotlinx/coroutines/Job;", "captureRunnableThreadContext", "command", "captureCallableThreadContext", "Ljava/util/concurrent/Callable;", "V", "callable", "isContextAwareComputation", "", "runAsCoroutine", "continuation", "completeOnFinish", "Lkotlin/Function0;", "(Lkotlin/coroutines/Continuation;ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "capturePropagationContext", "r", "forceUseContextJob", "Lcom/intellij/openapi/util/Pair;", "Lcom/intellij/openapi/util/Condition;", "expired", "signalRunnable", "captureBiConsumerThreadContext", "Ljava/util/function/BiConsumer;", "U", "f", "cleanupIfExpired", "expiredCondition", "childContext", "childJob", "Ljava/util/concurrent/FutureTask;", "c", "Ljava/util/function/Function;", "R", "function", "Lcom/intellij/util/concurrency/SchedulingWrapper$MyScheduledFutureTask;", "Lcom/intellij/util/concurrency/SchedulingWrapper;", "wrapper", "ns", "", "period", "contextAwareCallable", "unwrapContextRunnable", "intellij.platform.util"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class Propagation {
    private static final Logger LOG;

    /* JADX INFO: renamed from: com.intellij.util.concurrency.Propagation$childContinuation$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<Continuation<Unit>> $continuation;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Ref.ObjectRef<Continuation<Unit>> objectRef, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$continuation = objectRef;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$continuation, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<Continuation<Unit>> objectRef = this.$continuation;
                this.L$0 = objectRef;
                this.label = 1;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(this), 1);
                cancellableContinuationImpl.initCancellability();
                objectRef.element = cancellableContinuationImpl;
                Object result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(this);
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    static {
        Logger logger = Logger.getInstance("#com.intellij.concurrency");
        logger.getClass();
        LOG = logger;
    }

    public static Unit a(CoroutineContext coroutineContext, IntelliJContextElement intelliJContextElement) {
        intelliJContextElement.getClass();
        intelliJContextElement.childCanceled(coroutineContext);
        return Unit.INSTANCE;
    }

    public static CoroutineContext b(CoroutineContext coroutineContext, boolean z, SmartList smartList, CoroutineContext coroutineContext2, CoroutineContext.Element element) {
        coroutineContext2.getClass();
        element.getClass();
        return coroutineContext2.plus(produceChildContextElement(coroutineContext, element, z, smartList));
    }

    public static Unit c(boolean z, Continuation continuation, Throwable th) {
        if (th == null) {
            if (z) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(Unit.INSTANCE));
            }
        } else if ((th instanceof CancellationException) && !JobKt.getJob(continuation.getContext()).isCompleted()) {
            Result.Companion companion2 = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
        }
        return Unit.INSTANCE;
    }

    public static final SchedulingWrapper.MyScheduledFutureTask<?> capturePropagationContext(SchedulingWrapper schedulingWrapper, Runnable runnable, long j, long j2) {
        schedulingWrapper.getClass();
        runnable.getClass();
        final ChildContext childContextCreateChildContext = createChildContext(runnable + " (scheduled: " + j + ", period: " + j2 + ')');
        final Runnable runnableCaptureClientIdInRunnable = ClientIdPropagation.captureClientIdInRunnable(runnable);
        Runnable runnable2 = new Runnable() { // from class: com.intellij.util.concurrency.Propagation$capturePropagationContext$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                AccessToken accessTokenInstallThreadContext = ThreadContext.installThreadContext(childContextCreateChildContext.getContext(), false);
                try {
                    AccessToken accessTokenApplyContextActions = childContextCreateChildContext.applyContextActions(false);
                    try {
                        runnableCaptureClientIdInRunnable.run();
                        Unit unit = Unit.INSTANCE;
                        AutoCloseableKt.closeFinally(accessTokenApplyContextActions, (Throwable) null);
                        AutoCloseableKt.closeFinally(accessTokenInstallThreadContext, (Throwable) null);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            AutoCloseableKt.closeFinally(accessTokenApplyContextActions, th);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        AutoCloseableKt.closeFinally(accessTokenInstallThreadContext, th3);
                        throw th4;
                    }
                }
            }
        };
        Continuation<Unit> continuation = childContextCreateChildContext.getContinuation();
        Pair pair = continuation != null ? TuplesKt.to(new PeriodicCancellationRunnable(childContextCreateChildContext.getContinuation(), runnable2), JobKt.getJob(continuation.getContext())) : TuplesKt.to(runnable2, (Object) null);
        return new CancellationScheduledFutureTask(schedulingWrapper, childContextCreateChildContext, (Job) pair.component2(), (Runnable) pair.component1(), j, j2);
    }

    public static /* synthetic */ Runnable capturePropagationContext$default(Runnable runnable, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return capturePropagationContext(runnable, z);
    }

    public static final Runnable captureRunnableThreadContext(Runnable runnable) {
        runnable.getClass();
        return capturePropagationContext$default(runnable, false, 2, null);
    }

    private static final Continuation<Unit> childContinuation(String str, Job job) {
        if (job.isCompleted()) {
            LOG.warn("Attempt to create a child continuation for an already completed job", new Throwable());
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BuildersKt.launch(GlobalScope.INSTANCE, job.plus(new CoroutineName("IJ Structured concurrency: " + str)).plus(Dispatchers.getUnconfined()), CoroutineStart.UNDISPATCHED, new AnonymousClass1(objectRef, null));
        Object obj = objectRef.element;
        if (obj != null) {
            return (Continuation) obj;
        }
        Intrinsics.throwUninitializedPropertyAccessException("continuation");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> Void cleanupList(Throwable th, List<? extends T> list, Function1<? super T, Unit> function1) throws Throwable {
        try {
            Iterator<? extends T> it = list.iterator();
            SmartList smartList = null;
            while (it.hasNext()) {
                try {
                    function1.invoke(it.next());
                } catch (Throwable th2) {
                    if (smartList == null) {
                        smartList = new SmartList();
                    }
                    smartList.add(th2);
                }
            }
            CompoundRuntimeException.throwIfNotEmpty(smartList);
            throw th;
        } catch (Throwable th3) {
            ExceptionsKt.addSuppressed(th, th3);
            throw th;
        }
    }

    public static final ChildContext createChildContext(String str) {
        str.getClass();
        return doCreateChildContext(str, false);
    }

    public static final ChildContext createChildContextWithContextJob(String str) {
        str.getClass();
        return doCreateChildContext(str, true);
    }

    private static final ChildContext doCreateChildContext(String str, boolean z) throws Throwable {
        Job blockingJob;
        Pair pair;
        CoroutineContext coroutineContextCurrentThreadContext = ThreadContext.currentThreadContext();
        Pair<CoroutineContext, List<IntelliJContextElement>> pairGatherAppliedChildContext = gatherAppliedChildContext(coroutineContextCurrentThreadContext, z || coroutineContextCurrentThreadContext.get(BlockingJob.INSTANCE) != null);
        CoroutineContext coroutineContext = (CoroutineContext) pairGatherAppliedChildContext.component1();
        List list = (List) pairGatherAppliedChildContext.component2();
        if (z) {
            blockingJob = (Job) coroutineContextCurrentThreadContext.get(Job.Key);
        } else {
            BlockingJob blockingJob2 = (BlockingJob) coroutineContextCurrentThreadContext.get(BlockingJob.INSTANCE);
            blockingJob = blockingJob2 != null ? blockingJob2.getBlockingJob() : null;
        }
        if (blockingJob != null) {
            Continuation<Unit> continuationChildContinuation = childContinuation(str, blockingJob);
            EmptyCoroutineContext emptyCoroutineContext = (BlockingJob) coroutineContextCurrentThreadContext.get(BlockingJob.INSTANCE);
            if (emptyCoroutineContext == null) {
                emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
            }
            pair = new Pair(emptyCoroutineContext.plus(JobKt.getJob(continuationChildContinuation.getContext())), continuationChildContinuation);
        } else {
            pair = new Pair(EmptyCoroutineContext.INSTANCE, (Object) null);
        }
        return new ChildContext(coroutineContext.minusKey(Job.Key).plus((CoroutineContext) pair.component1()), (Continuation) pair.component2(), list);
    }

    private static final Pair<CoroutineContext, List<IntelliJContextElement>> gatherAppliedChildContext(final CoroutineContext coroutineContext, final boolean z) throws Throwable {
        final SmartList smartList = new SmartList();
        try {
            return new Pair<>((CoroutineContext) coroutineContext.fold(EmptyCoroutineContext.INSTANCE, new Function2() { // from class: agb
                public final Object invoke(Object obj, Object obj2) {
                    return Propagation.b(coroutineContext, z, smartList, (CoroutineContext) obj, (CoroutineContext.Element) obj2);
                }
            }), smartList);
        } catch (Throwable th) {
            cleanupList(th, CollectionsKt.reversed(smartList), new Function1() { // from class: bgb
                public final Object invoke(Object obj) {
                    return Propagation.a(coroutineContext, (IntelliJContextElement) obj);
                }
            });
            wq6.a();
            return null;
        }
    }

    public static final boolean getUseImplicitBlockingContext() {
        return Holder.INSTANCE.getUseImplicitBlockingContext();
    }

    public static final boolean isCheckContextAssertions() {
        return Holder.INSTANCE.getCheckIdeAssertion();
    }

    public static final boolean isContextAwareComputation(Object obj) {
        obj.getClass();
        return (obj instanceof Continuation) || (obj instanceof ContextAwareRunnable) || (obj instanceof ContextAwareCallable) || (obj instanceof CancellationFutureTask);
    }

    public static final boolean isPropagateThreadContext() {
        return Holder.INSTANCE.getPropagateThreadContext();
    }

    private static final CoroutineContext produceChildContextElement(CoroutineContext coroutineContext, CoroutineContext.Element element, boolean z, List<IntelliJContextElement> list) {
        if (!(element instanceof IntelliJContextElement)) {
            return z ? element : EmptyCoroutineContext.INSTANCE;
        }
        IntelliJContextElement intelliJContextElementProduceChildElement = ((IntelliJContextElement) element).produceChildElement(coroutineContext, z);
        if (intelliJContextElementProduceChildElement == null) {
            return EmptyCoroutineContext.INSTANCE;
        }
        list.add(intelliJContextElementProduceChildElement);
        return intelliJContextElementProduceChildElement;
    }

    public static final <T> T runAsCoroutine(final Continuation<? super Unit> continuation, final boolean z, Function0<? extends T> function0) throws ProcessCanceledException {
        continuation.getClass();
        function0.getClass();
        com.intellij.openapi.util.Ref ref = new com.intellij.openapi.util.Ref((Object) null);
        Deferred deferredAsync = BuildersKt.async(GlobalScope.INSTANCE, continuation.getContext(), CoroutineStart.UNDISPATCHED, new Propagation$runAsCoroutine$deferred$1(function0, ref, null));
        deferredAsync.invokeOnCompletion(new Function1() { // from class: zfb
            public final Object invoke(Object obj) {
                return Propagation.c(z, continuation, (Throwable) obj);
            }
        });
        ProcessCanceledException processCanceledException = (ProcessCanceledException) ref.get();
        if (processCanceledException != null) {
            throw processCanceledException;
        }
        try {
            return (T) deferredAsync.getCompleted();
        } catch (CancellationException e) {
            throw new CeProcessCanceledException(e);
        }
    }

    public static final Runnable unwrapContextRunnable(Runnable runnable) {
        runnable.getClass();
        if (!(runnable instanceof ContextRunnable)) {
            return runnable;
        }
        Runnable delegate = ((ContextRunnable) runnable).getDelegate();
        delegate.getClass();
        return unwrapContextRunnable(delegate);
    }

    public static final <V> FutureTask<V> capturePropagationContext(Callable<V> callable) {
        callable.getClass();
        if (isContextAwareComputation(callable)) {
            return new FutureTask<>(callable);
        }
        Callable callableCaptureClientIdInCallable = ClientIdPropagation.captureClientIdInCallable(callable);
        ChildContext childContextCreateChildContext = createChildContext(callable.toString());
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        ContextCallable contextCallable = new ContextCallable(false, childContextCreateChildContext, callableCaptureClientIdInCallable, atomicBoolean);
        Continuation<Unit> continuation = childContextCreateChildContext.getContinuation();
        if (continuation != null) {
            return new CancellationFutureTask(JobKt.getJob(continuation.getContext()), contextCallable, atomicBoolean, childContextCreateChildContext);
        }
        return new FutureTask<>(contextCallable);
    }

    public static final <V> SchedulingWrapper.MyScheduledFutureTask<V> capturePropagationContext(SchedulingWrapper schedulingWrapper, Callable<V> callable, long j) {
        CoroutineContext context;
        schedulingWrapper.getClass();
        callable.getClass();
        if (isContextAwareComputation(callable)) {
            return new SchedulingWrapper.MyScheduledFutureTask<>(schedulingWrapper, callable, j);
        }
        Callable callableCaptureClientIdInCallable = ClientIdPropagation.captureClientIdInCallable(callable);
        ChildContext childContextCreateChildContext = createChildContext(callable + " (scheduled: " + j + ')');
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        ContextCallable contextCallable = new ContextCallable(false, childContextCreateChildContext, callableCaptureClientIdInCallable, atomicBoolean);
        Continuation<Unit> continuation = childContextCreateChildContext.getContinuation();
        return new CancellationScheduledFutureTask(schedulingWrapper, childContextCreateChildContext, (continuation == null || (context = continuation.getContext()) == null) ? null : JobKt.getJob(context), atomicBoolean, contextCallable, j);
    }

    public static final Runnable capturePropagationContext(Runnable runnable, boolean z) {
        ChildContext childContextCreateChildContext;
        runnable.getClass();
        if (isContextAwareComputation(runnable)) {
            return runnable;
        }
        Runnable runnableCaptureClientIdInRunnable = ClientIdPropagation.captureClientIdInRunnable(runnable);
        if (z) {
            childContextCreateChildContext = createChildContextWithContextJob(runnable.toString());
        } else {
            childContextCreateChildContext = createChildContext(runnable.toString());
        }
        return new ContextRunnable(childContextCreateChildContext, runnableCaptureClientIdInRunnable);
    }
}
