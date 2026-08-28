package j$.util.stream;

/* renamed from: j$.util.stream.l, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0150l extends AbstractC0163n2 {
    public final /* synthetic */ int b = 2;
    boolean c;
    Object d;

    public /* synthetic */ C0150l(InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0150l(Q3 q3, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.d = q3;
        this.c = true;
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        switch (this.b) {
            case 0:
                this.c = false;
                this.d = null;
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
                InterfaceC0182r2 interfaceC0182r2 = this.a;
                if (obj == null) {
                    if (this.c) {
                        return;
                    }
                    this.c = true;
                    this.d = null;
                    interfaceC0182r2.q((InterfaceC0182r2) null);
                    return;
                }
                Object obj2 = this.d;
                if (obj2 == null || !obj.equals(obj2)) {
                    this.d = obj;
                    interfaceC0182r2.q((InterfaceC0182r2) obj);
                    return;
                }
                return;
            case 1:
                Stream stream = (Stream) ((C0095a) ((C0189t) this.d).n).apply((C0095a) obj);
                if (stream != null) {
                    try {
                        boolean z = this.c;
                        InterfaceC0182r2 interfaceC0182r22 = this.a;
                        if (!z) {
                            ((Stream) stream.sequential()).forEach(interfaceC0182r22);
                        } else {
                            j$.util.U uSpliterator = ((Stream) stream.sequential()).spliterator();
                            while (!interfaceC0182r22.o() && uSpliterator.tryAdvance(interfaceC0182r22)) {
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            stream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                if (stream != null) {
                    stream.close();
                    return;
                }
                return;
            default:
                if (this.c) {
                    boolean zTest = ((Q3) this.d).m.test(obj);
                    this.c = zTest;
                    if (zTest) {
                        this.a.q((InterfaceC0182r2) obj);
                        return;
                    }
                    return;
                }
                return;
        }
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public boolean o() {
        switch (this.b) {
            case 1:
                this.c = true;
                return this.a.o();
            case 2:
                return !this.c || this.a.o();
            default:
                return super.o();
        }
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public void l() {
        switch (this.b) {
            case 0:
                this.c = false;
                this.d = null;
                this.a.l();
                break;
            default:
                super.l();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0150l(C0189t c0189t, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.d = c0189t;
    }
}
