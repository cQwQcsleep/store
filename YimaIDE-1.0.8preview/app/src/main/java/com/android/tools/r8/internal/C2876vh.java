package com.android.tools.r8.internal;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.DataResource;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0231j1;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2876vh extends IO {
    public static final /* synthetic */ boolean h = true;
    public final AbstractC2775uY a;
    public final C1239cY b;
    public final KO c;
    public final Set d = AbstractC2780ub0.c();
    public final Cg0 e;
    public final Cg0 f;
    public C0509Ge g;

    public C2876vh(AbstractC2775uY abstractC2775uY, C1239cY c1239cY, ExecutorService executorService) {
        this.a = abstractC2775uY;
        this.b = c1239cY;
        KO ko = JO.a;
        abstractC2775uY.getClass();
        this.c = abstractC2775uY instanceof C1233cS ? ko : new CY(abstractC2775uY.a(), ko);
        this.g = c1239cY.a.m();
        this.e = new Cg0(c1239cY.j, executorService);
        this.f = new Cg0(c1239cY.j, executorService);
    }

    public final void a(com.android.tools.r8.graph.B5 b5, E9 e9) {
        this.b.b(b5, e9, com.android.tools.r8.ir.optimize.info.B.b, this, this.g.a(b5));
    }

    public final void b(com.android.tools.r8.graph.B5 b5, E9 e9) {
        String string;
        com.android.tools.r8.graph.H2 h2;
        com.android.tools.r8.graph.H2 h3;
        C1239cY c1239cY = this.b;
        C0483Fe c0483FeA = this.g.a(b5);
        c1239cY.getClass();
        C0231j1 c0231j1E = b5.e();
        if ((c1239cY.j.j instanceof ClassFileConsumer) && c0231j1E.h1()) {
            c0231j1E.a(c1239cY.a.M().a(c0231j1E.T0()));
        }
        if (c0231j1E.U0() != null && c1239cY.j.a(c0231j1E)) {
            if (c1239cY.a.M().u) {
                com.android.tools.r8.graph.H2 h4 = b5.s().f;
                for (com.android.tools.r8.graph.H2 h5 : c1239cY.E.a) {
                    h4.getClass();
                    boolean z = true;
                    if (!h4.b(h5.f)) {
                        Iterator it = c1239cY.E.b.iterator();
                        while (it.hasNext()) {
                            if (!h4.b(((com.android.tools.r8.graph.H2) it.next()).f)) {
                                c1239cY.F.getAndSet(true);
                                break;
                            }
                        }
                    } else {
                        c1239cY.G.getAndSet(true);
                    }
                    if (c1239cY.G.get() && c1239cY.F.get()) {
                        synchronized (c1239cY) {
                            try {
                                string = c1239cY.H;
                                if (string == null) {
                                    StringBuilder sb = new StringBuilder("Merging DEX file containing classes with prefix");
                                    sb.append(c1239cY.E.a.size() > 1 ? "es " : " ");
                                    int i = 0;
                                    while (i < c1239cY.E.a.size()) {
                                        sb.append("'");
                                        sb.append(((com.android.tools.r8.graph.H2) c1239cY.E.a.get(i)).toString().substring(1).replace(DataResource.SEPARATOR, '.'));
                                        sb.append("'");
                                        sb.append(i < c1239cY.E.a.size() - 1 ? ", " : XmlPullParser.NO_NAMESPACE);
                                        i++;
                                    }
                                    if (c1239cY.E.b.isEmpty()) {
                                        sb.append(" with classes with any other prefixes");
                                    } else {
                                        sb.append(" with other classes, except classes with prefix");
                                        sb.append(c1239cY.E.b.size() > 1 ? "es " : " ");
                                        int i2 = 0;
                                        while (i2 < c1239cY.E.b.size()) {
                                            sb.append("'");
                                            sb.append(((com.android.tools.r8.graph.H2) c1239cY.E.b.get(i2)).toString().substring(1).replace(DataResource.SEPARATOR, '.'));
                                            sb.append("'");
                                            sb.append(i2 < c1239cY.E.b.size() - 1 ? ", " : XmlPullParser.NO_NAMESPACE);
                                            i2++;
                                        }
                                        sb.append(",");
                                    }
                                    sb.append(" is not allowed: ");
                                    int i3 = 11;
                                    for (com.android.tools.r8.graph.D2 d2 : c1239cY.a.g().e()) {
                                        com.android.tools.r8.graph.H2 h6 = d2.e.f;
                                        h6.getClass();
                                        if (!h6.b(h5.f)) {
                                            Iterator it2 = c1239cY.E.b.iterator();
                                            do {
                                                if (!it2.hasNext()) {
                                                    int i4 = i3 - 1;
                                                    if (i3 < 0) {
                                                        sb.append("..");
                                                        break;
                                                    }
                                                    if (z) {
                                                        z = false;
                                                    } else {
                                                        sb.append(", ");
                                                    }
                                                    sb.append(d2.e);
                                                    i3 = i4;
                                                    break;
                                                }
                                                h2 = (com.android.tools.r8.graph.H2) it2.next();
                                                h3 = d2.e.f;
                                                h3.getClass();
                                            } while (!h3.b(h2.f));
                                        }
                                    }
                                    sb.append(".");
                                    string = sb.toString();
                                    c1239cY.H = string;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        throw new C0613Ke(string);
                    }
                }
            }
            C2752uB c2752uB = c1239cY.j;
            if (!(c2752uB.j instanceof ClassFileConsumer) && c2752uB.t && c0231j1E.U0().y0()) {
                if (C1239cY.K || c0231j1E.U0().y0()) {
                    return;
                }
                x1f.a();
            } else {
                c1239cY.b(b5, e9, c1239cY.B, this, c0483FeA);
            }
        }
    }

    public final void c(final com.android.tools.r8.graph.B5 b5, final E9 e9) {
        if (!this.d.contains(b5.s())) {
            com.android.tools.r8.synthesis.J jG = this.b.a.a.g();
            com.android.tools.r8.graph.D2 d2A = b5.a();
            jG.getClass();
            if (!jG.g(d2A.e)) {
                return;
            }
        }
        Cg0 cg0 = this.f;
        InterfaceC1681hh0 interfaceC1681hh0 = new InterfaceC1681hh0() { // from class: gmi
            @Override // com.android.tools.r8.internal.InterfaceC1681hh0
            public final void b() {
                this.a.a(b5, e9);
            }
        };
        cg0.getClass();
        try {
            cg0.b(interfaceC1681hh0);
        } catch (ExecutionException e) {
            throw new Xj0(e);
        }
    }

    public final void d(com.android.tools.r8.graph.B5 b5) {
        this.b.c(b5, com.android.tools.r8.ir.optimize.info.B.b, this, this.g.a(b5), AbstractC2166nO.b(this.b.a));
    }

    public final void g() {
        this.f.a((Consumer) null);
        this.e.a((Consumer) null);
    }

    @Override // com.android.tools.r8.internal.IO
    public final boolean a(com.android.tools.r8.graph.B5 b5) {
        return true;
    }

    public final void a(Iterable iterable) {
        iterable.forEach(new Consumer() { // from class: imi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b((B5) obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.IO
    public final KO d() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.IO
    public final boolean c(com.android.tools.r8.graph.B5 b5) {
        return true;
    }

    @Override // com.android.tools.r8.internal.IO
    public final AbstractC1891k8 c() {
        throw new Kk0("Invalid attempt to obtain call-site information in D8");
    }

    @Override // com.android.tools.r8.internal.IO
    public final void b(final com.android.tools.r8.graph.B5 b5) {
        if (!this.d.contains(b5.s())) {
            com.android.tools.r8.synthesis.J jG = this.b.a.a.g();
            com.android.tools.r8.graph.D2 d2A = b5.a();
            jG.getClass();
            if (!jG.g(d2A.e)) {
                return;
            }
        }
        if (b5.e().k1()) {
            return;
        }
        Cg0 cg0 = this.e;
        InterfaceC1681hh0 interfaceC1681hh0 = new InterfaceC1681hh0() { // from class: hmi
            @Override // com.android.tools.r8.internal.InterfaceC1681hh0
            public final void b() {
                this.a.d(b5);
            }
        };
        cg0.getClass();
        try {
            cg0.b(interfaceC1681hh0);
        } catch (ExecutionException e) {
            throw new Xj0(e);
        }
    }
}
