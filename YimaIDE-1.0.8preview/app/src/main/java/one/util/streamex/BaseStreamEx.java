package one.util.streamex;

import defpackage.k2d;
import java.util.Spliterator;
import java.util.stream.BaseStream;
import one.util.streamex.BaseStreamEx;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class BaseStreamEx<T, S extends BaseStream<T, S>, SPLTR extends Spliterator<T>, B extends BaseStreamEx<T, S, SPLTR, B>> implements BaseStream<T, S> {
    StreamContext context;
    SPLTR spliterator;
    private S stream;

    public BaseStreamEx(S s, StreamContext streamContext) {
        this.stream = s;
        this.context = streamContext;
    }

    @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
    public void close() {
        this.context.close();
    }

    public abstract S createStream();

    @Override // java.util.stream.BaseStream
    public boolean isParallel() {
        return this.context.parallel;
    }

    @Override // java.util.stream.BaseStream
    public S onClose(Runnable runnable) {
        this.context = this.context.onClose(runnable);
        return this;
    }

    @Override // java.util.stream.BaseStream
    public S parallel() {
        this.context = this.context.parallel();
        S s = this.stream;
        if (s != null) {
            this.stream = (S) s.parallel();
        }
        return this;
    }

    @Override // java.util.stream.BaseStream
    public S sequential() {
        this.context = this.context.sequential();
        S s = this.stream;
        if (s != null) {
            this.stream = (S) s.sequential();
        }
        return this;
    }

    @Override // java.util.stream.BaseStream, java.lang.Iterable
    public SPLTR spliterator() {
        S s = this.stream;
        if (s != null) {
            return (SPLTR) s.spliterator();
        }
        SPLTR spltr = this.spliterator;
        if (spltr != null) {
            this.spliterator = null;
            return spltr;
        }
        k2d.a("Stream is already consumed");
        return null;
    }

    public final S stream() {
        S s = this.stream;
        if (s != null) {
            return s;
        }
        if (this.spliterator == null) {
            k2d.a("Stream is already consumed");
            return null;
        }
        S s2 = (S) createStream();
        this.stream = s2;
        this.spliterator = null;
        return s2;
    }

    @Override // java.util.stream.BaseStream
    public S unordered() {
        this.stream = (S) stream().unordered();
        return this;
    }

    public BaseStreamEx(SPLTR spltr, StreamContext streamContext) {
        this.spliterator = spltr;
        this.context = streamContext;
    }
}
