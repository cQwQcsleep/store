package j$.util.stream;

import j$.util.Objects;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.f2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0123f2 extends AbstractC0163n2 {
    public final /* synthetic */ int b = 1;
    boolean c;
    Object d;
    final /* synthetic */ AbstractC0100b e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0123f2(W w, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.e = w;
        InterfaceC0182r2 interfaceC0182r22 = this.a;
        Objects.requireNonNull(interfaceC0182r22);
        this.d = new U(interfaceC0182r22);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0123f2(C0214y c0214y, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.e = c0214y;
        InterfaceC0182r2 interfaceC0182r22 = this.a;
        Objects.requireNonNull(interfaceC0182r22);
        this.d = new C0165o(interfaceC0182r22);
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        switch (this.b) {
            case 0:
                this.a.m(-1L);
                break;
            case 1:
                this.a.m(-1L);
                break;
            default:
                this.a.m(-1L);
                break;
        }
    }

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final void q(Object obj) throws Exception {
        switch (this.b) {
            case 0:
                InterfaceC0171p0 interfaceC0171p0 = (InterfaceC0171p0) ((C0095a) ((C0141j0) this.e).n).apply((C0095a) obj);
                if (interfaceC0171p0 != null) {
                    try {
                        boolean z = this.c;
                        C0121f0 c0121f0 = (C0121f0) this.d;
                        if (!z) {
                            interfaceC0171p0.sequential().forEach(c0121f0);
                        } else {
                            j$.util.N nSpliterator = interfaceC0171p0.sequential().spliterator();
                            while (!this.a.o() && nSpliterator.tryAdvance((LongConsumer) c0121f0)) {
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            interfaceC0171p0.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                if (interfaceC0171p0 != null) {
                    interfaceC0171p0.close();
                    return;
                }
                return;
            case 1:
                InterfaceC0116e0 interfaceC0116e0 = (InterfaceC0116e0) ((C0095a) ((W) this.e).n).apply((C0095a) obj);
                if (interfaceC0116e0 != null) {
                    try {
                        boolean z2 = this.c;
                        U u = (U) this.d;
                        if (!z2) {
                            interfaceC0116e0.sequential().forEach(u);
                        } else {
                            j$.util.K kSpliterator = interfaceC0116e0.sequential().spliterator();
                            while (!this.a.o() && kSpliterator.tryAdvance((IntConsumer) u)) {
                            }
                        }
                    } catch (Throwable th3) {
                        try {
                            interfaceC0116e0.close();
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                        throw th3;
                    }
                }
                if (interfaceC0116e0 != null) {
                    interfaceC0116e0.close();
                    return;
                }
                return;
            default:
                E e = (E) ((C0095a) ((C0214y) this.e).n).apply((C0095a) obj);
                if (e != null) {
                    try {
                        boolean z3 = this.c;
                        C0165o c0165o = (C0165o) this.d;
                        if (!z3) {
                            e.sequential().forEach(c0165o);
                        } else {
                            j$.util.H hSpliterator = e.sequential().spliterator();
                            while (!this.a.o() && hSpliterator.tryAdvance((DoubleConsumer) c0165o)) {
                            }
                        }
                    } catch (Throwable th5) {
                        try {
                            e.close();
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                        }
                        throw th5;
                    }
                }
                if (e != null) {
                    e.close();
                    return;
                }
                return;
        }
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        switch (this.b) {
            case 0:
                this.c = true;
                break;
            case 1:
                this.c = true;
                break;
            default:
                this.c = true;
                break;
        }
        return this.a.o();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0123f2(C0141j0 c0141j0, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.e = c0141j0;
        InterfaceC0182r2 interfaceC0182r22 = this.a;
        Objects.requireNonNull(interfaceC0182r22);
        this.d = new C0121f0(interfaceC0182r22);
    }
}
