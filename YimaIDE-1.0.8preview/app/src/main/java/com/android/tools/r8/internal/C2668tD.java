package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2668tD extends C2754uD {
    public static final C2583sD o = new C2583sD();
    public static final C2155nD p = new C2155nD("closed");
    public final ArrayList l;
    public String m;
    public AbstractC1643hD n;

    public C2668tD() {
        super(o);
        this.l = new ArrayList();
        this.n = C1813jD.b;
    }

    public final void a(AbstractC1643hD abstractC1643hD) {
        if (this.m != null) {
            if (!(abstractC1643hD instanceof C1813jD) || this.h) {
                ArrayList arrayList = this.l;
                ((C1898kD) ((AbstractC1643hD) arrayList.get(arrayList.size() - 1))).a(this.m, abstractC1643hD);
            }
            this.m = null;
            return;
        }
        if (this.l.isEmpty()) {
            this.n = abstractC1643hD;
            return;
        }
        ArrayList arrayList2 = this.l;
        AbstractC1643hD abstractC1643hD2 = (AbstractC1643hD) arrayList2.get(arrayList2.size() - 1);
        if (abstractC1643hD2 instanceof C1558gD) {
            ((C1558gD) abstractC1643hD2).b.add(abstractC1643hD);
        } else {
            g33.a();
        }
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void b(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.l.isEmpty() || this.m != null) {
            g33.a();
            return;
        }
        ArrayList arrayList = this.l;
        if (((AbstractC1643hD) arrayList.get(arrayList.size() - 1)) instanceof C1898kD) {
            this.m = str;
        } else {
            g33.a();
        }
    }

    @Override // com.android.tools.r8.internal.C2754uD, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.l.isEmpty()) {
            this.l.add(p);
        } else {
            a16.a("Incomplete document");
        }
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void d(String str) {
        if (str == null) {
            a(C1813jD.b);
        } else {
            a(new C2155nD(str));
        }
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void e() {
        C1898kD c1898kD = new C1898kD();
        a(c1898kD);
        this.l.add(c1898kD);
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void f() {
        if (this.l.isEmpty() || this.m != null) {
            g33.a();
            return;
        }
        ArrayList arrayList = this.l;
        if (!(((AbstractC1643hD) arrayList.get(arrayList.size() - 1)) instanceof C1558gD)) {
            g33.a();
        } else {
            ArrayList arrayList2 = this.l;
            arrayList2.remove(arrayList2.size() - 1);
        }
    }

    @Override // com.android.tools.r8.internal.C2754uD, java.io.Flushable
    public final void flush() {
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void g() {
        if (this.l.isEmpty() || this.m != null) {
            g33.a();
            return;
        }
        ArrayList arrayList = this.l;
        if (!(((AbstractC1643hD) arrayList.get(arrayList.size() - 1)) instanceof C1898kD)) {
            g33.a();
        } else {
            ArrayList arrayList2 = this.l;
            arrayList2.remove(arrayList2.size() - 1);
        }
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final C2754uD i() {
        a(C1813jD.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void d() {
        C1558gD c1558gD = new C1558gD();
        a(c1558gD);
        this.l.add(c1558gD);
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void a(Boolean bool) {
        if (bool == null) {
            a(C1813jD.b);
        } else {
            a(new C2155nD(bool));
        }
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void a(Number number) {
        if (number == null) {
            a(C1813jD.b);
            return;
        }
        if (!this.e) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                aca.a("JSON forbids NaN and infinities: ", number);
                return;
            }
        }
        a(new C2155nD(number));
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void a(boolean z) {
        a(new C2155nD(Boolean.valueOf(z)));
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void a(long j) {
        a(new C2155nD(Long.valueOf(j)));
    }

    @Override // com.android.tools.r8.internal.C2754uD
    public final void a(double d) {
        if (!this.e && (Double.isNaN(d) || Double.isInfinite(d))) {
            c72.a("JSON forbids NaN and infinities: ", d);
        } else {
            a(new C2155nD(Double.valueOf(d)));
        }
    }
}
