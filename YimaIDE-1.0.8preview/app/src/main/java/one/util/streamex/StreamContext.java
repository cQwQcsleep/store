package one.util.streamex;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.BaseStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class StreamContext {
    Runnable closeHandler;
    ForkJoinPool fjp;
    boolean parallel;
    static final StreamContext SEQUENTIAL = new StreamContext(false);
    static final StreamContext PARALLEL = new StreamContext(true);

    private StreamContext(boolean z) {
        this.parallel = z;
    }

    public static /* synthetic */ void a(Runnable runnable, Runnable runnable2) {
        try {
            runnable.run();
            runnable2.run();
        } catch (Throwable th) {
            try {
                runnable2.run();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static Runnable compose(final Runnable runnable, final Runnable runnable2) {
        return runnable == null ? runnable2 : new Runnable() { // from class: one.util.streamex.g
            @Override // java.lang.Runnable
            public final void run() {
                StreamContext.a(runnable, runnable2);
            }
        };
    }

    public static StreamContext of(final BaseStream<?, ?> baseStream) {
        return baseStream instanceof BaseStreamEx ? ((BaseStreamEx) baseStream).context : new StreamContext(baseStream.isParallel()).onClose(new Runnable() { // from class: ipd
            @Override // java.lang.Runnable
            public final void run() {
                baseStream.close();
            }
        });
    }

    public void close() {
        Runnable runnable = this.closeHandler;
        if (runnable != null) {
            this.closeHandler = null;
            runnable.run();
        }
    }

    public StreamContext combine(BaseStream<?, ?> baseStream) {
        if (baseStream == null) {
            return this;
        }
        StreamContext streamContextOf = of(baseStream);
        if (baseStream.isParallel() && !this.parallel) {
            this = parallel();
        }
        Runnable runnable = streamContextOf.closeHandler;
        return runnable != null ? this.onClose(runnable) : this;
    }

    public StreamContext detach() {
        return (this == PARALLEL || this == SEQUENTIAL) ? new StreamContext(this.parallel) : this;
    }

    public StreamContext onClose(Runnable runnable) {
        StreamContext streamContextDetach = detach();
        streamContextDetach.closeHandler = compose(streamContextDetach.closeHandler, runnable);
        return streamContextDetach;
    }

    public StreamContext parallel() {
        if (this == SEQUENTIAL) {
            return PARALLEL;
        }
        this.parallel = true;
        this.fjp = null;
        return this;
    }

    public StreamContext sequential() {
        if (this == PARALLEL) {
            return SEQUENTIAL;
        }
        this.parallel = false;
        this.fjp = null;
        return this;
    }

    public <T> T terminate(final Supplier<T> supplier) {
        ForkJoinPool forkJoinPool = this.fjp;
        Objects.requireNonNull(supplier);
        return forkJoinPool.submit((Callable) new Callable() { // from class: hpd
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return supplier.get();
            }
        }).join();
    }

    public <T, U> T terminate(final U u, final Function<U, T> function) {
        return this.fjp.submit((Callable) new Callable() { // from class: one.util.streamex.h
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return function.apply(u);
            }
        }).join();
    }
}
