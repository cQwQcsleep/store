package j$.util.stream;

import java.util.Iterator;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/* renamed from: j$.util.stream.f, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0120f implements InterfaceC0130h {
    public final /* synthetic */ BaseStream a;

    private /* synthetic */ C0120f(BaseStream baseStream) {
        this.a = baseStream;
    }

    public static /* synthetic */ InterfaceC0130h k(BaseStream baseStream) {
        if (baseStream == null) {
            return null;
        }
        return baseStream instanceof C0125g ? ((C0125g) baseStream).a : baseStream instanceof DoubleStream ? C.k((DoubleStream) baseStream) : baseStream instanceof IntStream ? C0106c0.k((IntStream) baseStream) : baseStream instanceof LongStream ? C0161n0.k((LongStream) baseStream) : baseStream instanceof java.util.stream.Stream ? C0109c3.k((java.util.stream.Stream) baseStream) : new C0120f(baseStream);
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.a.close();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        BaseStream baseStream = this.a;
        if (obj instanceof C0120f) {
            obj = ((C0120f) obj).a;
        }
        return baseStream.equals(obj);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ Iterator iterator() {
        return this.a.iterator();
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h onClose(Runnable runnable) {
        return k(this.a.onClose(runnable));
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h parallel() {
        return k(this.a.parallel());
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h sequential() {
        return k(this.a.sequential());
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ j$.util.U spliterator() {
        return j$.util.S.a(this.a.spliterator());
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h unordered() {
        return k(this.a.unordered());
    }
}
