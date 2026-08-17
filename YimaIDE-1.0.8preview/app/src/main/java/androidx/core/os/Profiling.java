package androidx.core.os;

import android.content.Context;
import android.os.ProfilingManager;
import android.os.ProfilingResult;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import defpackage.fcb;
import defpackage.gcb;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0007\u001a&\u0010\n\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u0007\u001a\u001e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u0007\u001a2\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0013H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {Profiling.KEY_DURATION_MS, "", Profiling.KEY_SAMPLING_INTERVAL_BYTES, Profiling.KEY_TRACK_JAVA_ALLOCATIONS, Profiling.KEY_FREQUENCY_HZ, Profiling.KEY_SIZE_KB, Profiling.KEY_BUFFER_FILL_POLICY, "VALUE_BUFFER_FILL_POLICY_DISCARD", "", "VALUE_BUFFER_FILL_POLICY_RING_BUFFER", "registerForAllProfilingResults", "Lkotlinx/coroutines/flow/Flow;", "Landroid/os/ProfilingResult;", "context", "Landroid/content/Context;", "", "executor", "Ljava/util/concurrent/Executor;", "listener", "Ljava/util/function/Consumer;", "unregisterForAllProfilingResults", "requestProfiling", "profilingRequest", "Landroidx/core/os/ProfilingRequest;", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Profiling {
    private static final String KEY_BUFFER_FILL_POLICY = "KEY_BUFFER_FILL_POLICY";
    private static final String KEY_DURATION_MS = "KEY_DURATION_MS";
    private static final String KEY_FREQUENCY_HZ = "KEY_FREQUENCY_HZ";
    private static final String KEY_SAMPLING_INTERVAL_BYTES = "KEY_SAMPLING_INTERVAL_BYTES";
    private static final String KEY_SIZE_KB = "KEY_SIZE_KB";
    private static final String KEY_TRACK_JAVA_ALLOCATIONS = "KEY_TRACK_JAVA_ALLOCATIONS";
    private static final int VALUE_BUFFER_FILL_POLICY_DISCARD = 1;
    private static final int VALUE_BUFFER_FILL_POLICY_RING_BUFFER = 2;

    /* JADX INFO: renamed from: androidx.core.os.Profiling$registerForAllProfilingResults$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Landroid/os/ProfilingResult;"}, k = 3, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.core.os.Profiling$registerForAllProfilingResults$1", f = "Profiling.kt", i = {}, l = {79}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super ProfilingResult>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        public static void b(Runnable runnable) {
            runnable.run();
        }

        public static void d(ProducerScope producerScope, ProfilingResult profilingResult) {
            profilingResult.getClass();
            producerScope.trySend-JP2dKIU(profilingResult);
        }

        public static Unit g(ProfilingManager profilingManager, Consumer consumer) {
            profilingManager.unregisterForAllProfilingResults(consumer);
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$context, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(ProducerScope<? super ProfilingResult> producerScope, Continuation<? super Unit> continuation) {
            return create(producerScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                final Consumer consumer = new Consumer() { // from class: androidx.core.os.a
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        Profiling.AnonymousClass1.d(producerScope, (ProfilingResult) obj2);
                    }
                };
                final ProfilingManager profilingManagerA = gcb.a(this.$context.getSystemService(fcb.a()));
                profilingManagerA.registerForAllProfilingResults(new Executor() { // from class: androidx.core.os.b
                    @Override // java.util.concurrent.Executor
                    public final void execute(Runnable runnable) {
                        Profiling.AnonymousClass1.b(runnable);
                    }
                }, consumer);
                Function0 function0 = new Function0() { // from class: androidx.core.os.c
                    public final Object invoke() {
                        return Profiling.AnonymousClass1.g(profilingManagerA, consumer);
                    }
                };
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, function0, this) == coroutine_suspended) {
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

    public static final void registerForAllProfilingResults(Context context, Executor executor, Consumer<ProfilingResult> consumer) {
        context.getClass();
        executor.getClass();
        consumer.getClass();
        gcb.a(context.getSystemService(fcb.a())).registerForAllProfilingResults(executor, consumer);
    }

    public static final void requestProfiling(Context context, ProfilingRequest profilingRequest, Executor executor, Consumer<ProfilingResult> consumer) {
        context.getClass();
        profilingRequest.getClass();
        gcb.a(context.getSystemService(fcb.a())).requestProfiling(profilingRequest.getProfilingType(), profilingRequest.getParams(), profilingRequest.getTag(), profilingRequest.getCancellationSignal(), executor, consumer);
    }

    public static final void unregisterForAllProfilingResults(Context context, Consumer<ProfilingResult> consumer) {
        context.getClass();
        consumer.getClass();
        gcb.a(context.getSystemService(fcb.a())).unregisterForAllProfilingResults(consumer);
    }

    public static final Flow<ProfilingResult> registerForAllProfilingResults(Context context) {
        context.getClass();
        return FlowKt.callbackFlow(new AnonymousClass1(context, null));
    }
}
