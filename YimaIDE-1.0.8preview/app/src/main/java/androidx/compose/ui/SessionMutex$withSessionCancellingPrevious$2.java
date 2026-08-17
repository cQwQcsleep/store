package androidx.compose.ui;

import androidx.compose.ui.spatial.RectListKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
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
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.ui.SessionMutex$withSessionCancellingPrevious$2", f = "SessionMutex.kt", i = {0, 1}, l = {RectListKt.BitOffsetForFocusable, 63}, m = "invokeSuspend", n = {"newSession", "newSession"}, s = {"L$0", "L$0"}, v = 1)
public final class SessionMutex$withSessionCancellingPrevious$2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-SessionMutex$-this$0, reason: not valid java name */
    final /* synthetic */ AtomicReference<SessionMutex.Session<T>> f38$$v$c$androidxcomposeuiSessionMutex$this$0;
    final /* synthetic */ Function2<T, Continuation<? super R>, Object> $session;
    final /* synthetic */ Function1<CoroutineScope, T> $sessionInitializer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SessionMutex$withSessionCancellingPrevious$2(Function1<? super CoroutineScope, ? extends T> function1, AtomicReference<SessionMutex.Session<T>> atomicReference, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super SessionMutex$withSessionCancellingPrevious$2> continuation) {
        super(2, continuation);
        this.$sessionInitializer = function1;
        this.f38$$v$c$androidxcomposeuiSessionMutex$this$0 = atomicReference;
        this.$session = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SessionMutex$withSessionCancellingPrevious$2 sessionMutex$withSessionCancellingPrevious$2 = new SessionMutex$withSessionCancellingPrevious$2(this.$sessionInitializer, this.f38$$v$c$androidxcomposeuiSessionMutex$this$0, this.$session, continuation);
        sessionMutex$withSessionCancellingPrevious$2.L$0 = obj;
        return sessionMutex$withSessionCancellingPrevious$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final Object invokeSuspend(Object obj) throws Throwable {
        SessionMutex.Session session;
        Job job;
        SessionMutex.Session session2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                session = new SessionMutex.Session(JobKt.getJob(coroutineScope.getCoroutineContext()), this.$sessionInitializer.invoke(coroutineScope));
                SessionMutex.Session session3 = (SessionMutex.Session) this.f38$$v$c$androidxcomposeuiSessionMutex$this$0.getAndSet((SessionMutex.Session<T>) session);
                if (session3 != null && (job = session3.getJob()) != null) {
                    this.L$0 = session;
                    this.label = 1;
                    if (JobKt.cancelAndJoin(job, this) != coroutine_suspended) {
                    }
                }
                return coroutine_suspended;
            }
            if (i != 1) {
                if (i != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                session2 = (SessionMutex.Session) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    this.f38$$v$c$androidxcomposeuiSessionMutex$this$0.compareAndSet((SessionMutex.Session<T>) session2, null);
                    return obj;
                } catch (Throwable th) {
                    th = th;
                    this.f38$$v$c$androidxcomposeuiSessionMutex$this$0.compareAndSet((SessionMutex.Session<T>) session2, null);
                    throw th;
                }
            }
            session = (SessionMutex.Session) this.L$0;
            ResultKt.throwOnFailure(obj);
            Function2<T, Continuation<? super R>, Object> function2 = this.$session;
            Object value = session.getValue();
            this.L$0 = session;
            this.label = 2;
            obj = function2.invoke(value, this);
            if (obj != coroutine_suspended) {
                session2 = session;
                this.f38$$v$c$androidxcomposeuiSessionMutex$this$0.compareAndSet((SessionMutex.Session<T>) session2, null);
                return obj;
            }
            return coroutine_suspended;
        } catch (Throwable th2) {
            th = th2;
            session2 = session;
            this.f38$$v$c$androidxcomposeuiSessionMutex$this$0.compareAndSet((SessionMutex.Session<T>) session2, null);
            throw th;
        }
    }
}
