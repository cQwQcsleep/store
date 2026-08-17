package defpackage;

import kotlin.Result;
import kotlin.coroutines.Continuation;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class m1c {
    public Continuation a;

    public final void a() {
        Continuation continuation = this.a;
        if (continuation != null) {
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m38constructorimpl(jw.b.a));
        }
        this.a = null;
    }

    public final void b() {
        this.a = null;
    }

    public final Continuation c() {
        return this.a;
    }

    public final void d(Continuation continuation) {
        this.a = continuation;
    }
}
