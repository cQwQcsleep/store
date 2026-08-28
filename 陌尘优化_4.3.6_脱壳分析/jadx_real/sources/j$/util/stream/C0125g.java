package j$.util.stream;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.BaseStream;

/* renamed from: j$.util.stream.g, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0125g implements BaseStream {
    public final /* synthetic */ InterfaceC0130h a;

    private /* synthetic */ C0125g(InterfaceC0130h interfaceC0130h) {
        this.a = interfaceC0130h;
    }

    public static /* synthetic */ BaseStream k(InterfaceC0130h interfaceC0130h) {
        if (interfaceC0130h == null) {
            return null;
        }
        return interfaceC0130h instanceof C0120f ? ((C0120f) interfaceC0130h).a : interfaceC0130h instanceof E ? D.k((E) interfaceC0130h) : interfaceC0130h instanceof InterfaceC0116e0 ? C0111d0.k((InterfaceC0116e0) interfaceC0130h) : interfaceC0130h instanceof InterfaceC0171p0 ? C0166o0.k((InterfaceC0171p0) interfaceC0130h) : interfaceC0130h instanceof Stream ? C0114d3.k((Stream) interfaceC0130h) : new C0125g(interfaceC0130h);
    }

    @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
    public final /* synthetic */ void close() throws Exception {
        this.a.close();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        InterfaceC0130h interfaceC0130h = this.a;
        if (obj instanceof C0125g) {
            obj = ((C0125g) obj).a;
        }
        return interfaceC0130h.equals(obj);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ Iterator iterator() {
        return this.a.iterator();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream onClose(Runnable runnable) {
        return k(this.a.onClose(runnable));
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream parallel() {
        return k(this.a.parallel());
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream sequential() {
        return k(this.a.sequential());
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ Spliterator spliterator() {
        return j$.util.T.a(this.a.spliterator());
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream unordered() {
        return k(this.a.unordered());
    }
}
