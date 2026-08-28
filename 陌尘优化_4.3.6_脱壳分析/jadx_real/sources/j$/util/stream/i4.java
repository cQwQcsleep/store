package j$.util.stream;

import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class i4 extends AbstractC0105c {
    private final AbstractC0100b j;
    private final IntFunction k;
    private final boolean l;
    private long m;
    private boolean n;
    private volatile boolean o;

    @Override // j$.util.stream.AbstractC0105c
    protected final void h() {
        this.i = true;
        if (this.l && this.o) {
            f(A0.L(this.j.I()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    @Override // j$.util.stream.AbstractC0115e, java.util.concurrent.CountedCompleter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void onCompletion(CountedCompleter countedCompleter) {
        Object objI;
        AbstractC0115e abstractC0115e = this.d;
        if (abstractC0115e != null) {
            this.n = ((i4) abstractC0115e).n | ((i4) this.e).n;
            if (this.l && this.i) {
                this.m = 0L;
                objI = A0.L(this.j.I());
            } else if (this.l) {
                i4 i4Var = (i4) this.d;
                if (i4Var.n) {
                    this.m = i4Var.m;
                    objI = (M0) i4Var.c();
                } else {
                    i4 i4Var2 = (i4) this.d;
                    long j = i4Var2.m;
                    i4 i4Var3 = (i4) this.e;
                    this.m = j + i4Var3.m;
                    if (i4Var2.m == 0) {
                        objI = (M0) i4Var3.c();
                    } else if (i4Var3.m == 0) {
                        objI = (M0) i4Var2.c();
                    } else {
                        objI = A0.I(this.j.I(), (M0) ((i4) this.d).c(), (M0) ((i4) this.e).c());
                    }
                }
            }
            f(objI);
        }
        this.o = true;
        super.onCompletion(countedCompleter);
    }

    i4(AbstractC0100b abstractC0100b, AbstractC0100b abstractC0100b2, j$.util.U u, IntFunction intFunction) {
        super(abstractC0100b2, u);
        this.j = abstractC0100b;
        this.k = intFunction;
        this.l = EnumC0129g3.ORDERED.n(abstractC0100b2.K());
    }

    i4(i4 i4Var, j$.util.U u) {
        super(i4Var, u);
        this.j = i4Var.j;
        this.k = i4Var.k;
        this.l = i4Var.l;
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final AbstractC0115e e(j$.util.U u) {
        return new i4(this, u);
    }

    @Override // j$.util.stream.AbstractC0105c
    protected final Object j() {
        return A0.L(this.j.I());
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final Object a() {
        E0 e0N = this.a.N(-1L, this.k);
        InterfaceC0182r2 interfaceC0182r2R = this.j.R(this.a.K(), e0N);
        AbstractC0100b abstractC0100b = this.a;
        boolean zB = abstractC0100b.B(this.b, abstractC0100b.W(interfaceC0182r2R));
        this.n = zB;
        if (zB) {
            i();
        }
        M0 m0A = e0N.a();
        this.m = m0A.count();
        return m0A;
    }
}
