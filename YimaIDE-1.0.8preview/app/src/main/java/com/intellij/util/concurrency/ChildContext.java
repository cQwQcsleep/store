package com.intellij.util.concurrency;

import com.intellij.concurrency.IntelliJContextElement;
import com.intellij.concurrency.ThreadContext;
import com.intellij.openapi.application.AccessToken;
import com.intellij.util.SmartList;
import com.intellij.util.concurrency.ChildContext;
import com.intellij.util.lang.CompoundRuntimeException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0016\u001a\u00020\u00062\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019J'\u0010\u0016\u001a\u0002H\u001a\"\u0004\b\u0000\u0010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001d¢\u0006\u0002\u0010\u001eJ\u0012\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u001cH\u0007J\u0006\u0010\"\u001a\u00020\u0006J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u0011\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J5\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010'\u001a\u00020\u001c2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020,HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006-"}, d2 = {"Lcom/intellij/util/concurrency/ChildContext;", "", "context", "Lkotlin/coroutines/CoroutineContext;", "continuation", "Lkotlin/coroutines/Continuation;", "", "ijElements", "", "Lcom/intellij/concurrency/IntelliJContextElement;", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;Ljava/util/List;)V", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getContinuation", "()Lkotlin/coroutines/Continuation;", "getIjElements", "()Ljava/util/List;", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "runInChildContext", "action", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "T", "completeOnFinish", "", "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "applyContextActions", "Lcom/intellij/openapi/application/AccessToken;", "installThreadContext", "cancelAllIntelliJElements", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ChildContext {
    private final CoroutineContext context;
    private final Continuation<Unit> continuation;
    private final List<IntelliJContextElement> ijElements;

    /* JADX WARN: Multi-variable type inference failed */
    public ChildContext(CoroutineContext coroutineContext, Continuation<? super Unit> continuation, List<? extends IntelliJContextElement> list) {
        coroutineContext.getClass();
        list.getClass();
        this.context = coroutineContext;
        this.continuation = continuation;
        this.ijElements = list;
    }

    public static Unit a(ChildContext childContext, IntelliJContextElement intelliJContextElement) {
        intelliJContextElement.getClass();
        intelliJContextElement.afterChildCompleted(childContext.context);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ AccessToken applyContextActions$default(ChildContext childContext, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return childContext.applyContextActions(z);
    }

    public static Object b(ChildContext childContext, Function0 function0) {
        AccessToken accessTokenApplyContextActions$default = applyContextActions$default(childContext, false, 1, null);
        try {
            Object objInvoke = function0.invoke();
            AutoCloseableKt.closeFinally(accessTokenApplyContextActions$default, (Throwable) null);
            return objInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(accessTokenApplyContextActions$default, th);
                throw th2;
            }
        }
    }

    public final AccessToken applyContextActions(boolean installThreadContext) throws Throwable {
        final AccessToken accessTokenInstallThreadContext;
        ArrayList arrayList = new ArrayList();
        try {
            for (IntelliJContextElement intelliJContextElement : this.ijElements) {
                intelliJContextElement.beforeChildStarted(this.context);
                arrayList.add(intelliJContextElement);
            }
            if (installThreadContext) {
                accessTokenInstallThreadContext = ThreadContext.installThreadContext(this.context, false);
            } else {
                accessTokenInstallThreadContext = AccessToken.EMPTY_ACCESS_TOKEN;
                accessTokenInstallThreadContext.getClass();
            }
            return new AccessToken() { // from class: com.intellij.util.concurrency.ChildContext.applyContextActions.2
                public void finish() {
                    accessTokenInstallThreadContext.finish();
                    List listReversed = CollectionsKt.reversed(this.getIjElements());
                    ChildContext childContext = this;
                    Iterator it = listReversed.iterator();
                    SmartList smartList = null;
                    while (it.hasNext()) {
                        try {
                            ((IntelliJContextElement) it.next()).afterChildCompleted(childContext.getContext());
                        } catch (Throwable th) {
                            if (smartList == null) {
                                smartList = new SmartList();
                            }
                            smartList.add(th);
                        }
                    }
                    CompoundRuntimeException.throwIfNotEmpty(smartList);
                }
            };
        } catch (Throwable th) {
            Propagation.cleanupList(th, CollectionsKt.reversed(arrayList), new Function1() { // from class: hs1
                public final Object invoke(Object obj) {
                    return ChildContext.a(this.b, (IntelliJContextElement) obj);
                }
            });
            wq6.a();
            return null;
        }
    }

    public final void cancelAllIntelliJElements() {
        Iterator<IntelliJContextElement> it = this.ijElements.iterator();
        SmartList smartList = null;
        while (it.hasNext()) {
            try {
                it.next().childCanceled(this.context);
            } catch (Throwable th) {
                if (smartList == null) {
                    smartList = new SmartList();
                }
                smartList.add(th);
            }
        }
        CompoundRuntimeException.throwIfNotEmpty(smartList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChildContext)) {
            return false;
        }
        ChildContext childContext = (ChildContext) other;
        return Intrinsics.areEqual(this.context, childContext.context) && Intrinsics.areEqual(this.continuation, childContext.continuation) && Intrinsics.areEqual(this.ijElements, childContext.ijElements);
    }

    public final CoroutineContext getContext() {
        return this.context;
    }

    public final Continuation<Unit> getContinuation() {
        return this.continuation;
    }

    public final List<IntelliJContextElement> getIjElements() {
        return this.ijElements;
    }

    public final Job getJob() {
        CoroutineContext context;
        Continuation<Unit> continuation = this.continuation;
        if (continuation == null || (context = continuation.getContext()) == null) {
            return null;
        }
        return JobKt.getJob(context);
    }

    public int hashCode() {
        int iHashCode = this.context.hashCode() * 31;
        Continuation<Unit> continuation = this.continuation;
        return ((iHashCode + (continuation == null ? 0 : continuation.hashCode())) * 31) + this.ijElements.hashCode();
    }

    public final <T> T runInChildContext(boolean completeOnFinish, final Function0<? extends T> action) {
        action.getClass();
        Continuation<Unit> continuation = this.continuation;
        if (continuation != null) {
            return (T) Propagation.runAsCoroutine(continuation, completeOnFinish, new Function0() { // from class: gs1
                public final Object invoke() {
                    return ChildContext.b(this.b, action);
                }
            });
        }
        AccessToken accessTokenApplyContextActions$default = applyContextActions$default(this, false, 1, null);
        try {
            T t = (T) action.invoke();
            AutoCloseableKt.closeFinally(accessTokenApplyContextActions$default, (Throwable) null);
            return t;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(accessTokenApplyContextActions$default, th);
                throw th2;
            }
        }
    }

    public String toString() {
        return "ChildContext(context=" + this.context + ", continuation=" + this.continuation + ", ijElements=" + this.ijElements + ')';
    }

    /* JADX INFO: renamed from: com.intellij.util.concurrency.ChildContext$runInChildContext$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Unit> {
        public AnonymousClass1(Object obj) {
            super(0, obj, Runnable.class, "run", "run()V", 0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1873invoke() {
            ((Runnable) ((CallableReference) this).receiver).run();
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m1873invoke();
            return Unit.INSTANCE;
        }
    }

    public final void runInChildContext(Runnable action) {
        action.getClass();
        runInChildContext(true, new AnonymousClass1(action));
    }
}
