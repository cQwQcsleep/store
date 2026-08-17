package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a$\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a$\u0010\n\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¨\u0006\u000b"}, d2 = {"probeCoroutineCreated", "Lkotlin/coroutines/Continuation;", "T", "completion", "Lkotlin/SinceKotlin;", "version", "1.3", "probeCoroutineResumed", HttpUrl.FRAGMENT_ENCODE_SET, "frame", "probeCoroutineSuspended", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class DebugProbesKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Continuation<T> probeCoroutineCreated(Continuation<? super T> continuation) {
        continuation.getClass();
        return continuation;
    }

    public static final void probeCoroutineResumed(Continuation<?> continuation) {
        continuation.getClass();
    }

    public static final void probeCoroutineSuspended(Continuation<?> continuation) {
        continuation.getClass();
    }
}
