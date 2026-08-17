package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.shaking.AbstractC3390f1;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.f1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3390f1 {
    public static final /* synthetic */ boolean d = true;
    public final AbstractC3385e1 a;
    public final HashSet b = new HashSet();
    public final Set c = AbstractC2780ub0.c();

    public AbstractC3390f1(AbstractC3385e1 abstractC3385e1) {
        this.a = abstractC3385e1;
    }

    public AbstractC3390f1 a(AbstractC3390f1 abstractC3390f1) {
        AbstractC3385e1 abstractC3385e1 = abstractC3390f1.a;
        a(!abstractC3385e1.b, new Consumer() { // from class: mug
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC3390f1) obj).e();
            }
        });
        a(!abstractC3385e1.c, new Consumer() { // from class: nug
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC3390f1) obj).f();
            }
        });
        a(!abstractC3385e1.d, new Consumer() { // from class: oug
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC3390f1) obj).g();
            }
        });
        a(!abstractC3385e1.e, new Consumer() { // from class: pug
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC3390f1) obj).h();
            }
        });
        a(!abstractC3385e1.f, new Consumer() { // from class: qug
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC3390f1) obj).i();
            }
        });
        a(!abstractC3385e1.g, new Consumer() { // from class: rug
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC3390f1) obj).j();
            }
        });
        a(!abstractC3385e1.h, new Consumer() { // from class: sug
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC3390f1) obj).k();
            }
        });
        a(abstractC3385e1.i, new Consumer() { // from class: tug
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC3390f1) obj).p();
            }
        });
        this.b.addAll(abstractC3390f1.b);
        this.c.addAll(abstractC3390f1.c);
        return o();
    }

    public C3375c1 b() {
        return null;
    }

    public AbstractC3415k1 c() {
        return null;
    }

    public C3430n1 d() {
        return null;
    }

    public final AbstractC3390f1 e() {
        AbstractC3385e1 abstractC3385e1 = this.a;
        abstractC3385e1.b = false;
        abstractC3385e1.j();
        return o();
    }

    public final AbstractC3390f1 f() {
        AbstractC3385e1 abstractC3385e1 = this.a;
        abstractC3385e1.c = false;
        abstractC3385e1.j();
        return o();
    }

    public final AbstractC3390f1 g() {
        AbstractC3385e1 abstractC3385e1 = this.a;
        abstractC3385e1.d = false;
        abstractC3385e1.j();
        return o();
    }

    public AbstractC3390f1 h() {
        this.a.b();
        return o();
    }

    public AbstractC3390f1 i() {
        this.a.c();
        return o();
    }

    public AbstractC3390f1 j() {
        this.a.d();
        return o();
    }

    public final AbstractC3390f1 k() {
        AbstractC3385e1 abstractC3385e1 = this.a;
        abstractC3385e1.h = false;
        abstractC3385e1.j();
        return o();
    }

    public final boolean l() {
        AbstractC3385e1 abstractC3385e1 = this.a;
        return abstractC3385e1.b(abstractC3385e1.f());
    }

    public final boolean m() {
        return this.a.i;
    }

    public final AbstractC3395g1 n() {
        AbstractC3395g1 abstractC3395g1A = this.a.a();
        AbstractC3395g1 abstractC3395g1 = this.a.a;
        if (d || ((abstractC3395g1.a || !abstractC3395g1A.a) && ((abstractC3395g1.b || !abstractC3395g1A.b) && ((abstractC3395g1.c || !abstractC3395g1A.c) && ((abstractC3395g1.d || !abstractC3395g1A.d) && ((abstractC3395g1.e || !abstractC3395g1A.e) && ((abstractC3395g1.f || !abstractC3395g1A.f) && ((abstractC3395g1.g || !abstractC3395g1A.g) && (!abstractC3395g1.h || abstractC3395g1A.h))))))))) {
            return abstractC3395g1A;
        }
        x1f.a();
        return null;
    }

    public abstract AbstractC3390f1 o();

    public final AbstractC3390f1 p() {
        AbstractC3385e1 abstractC3385e1 = this.a;
        abstractC3385e1.i = true;
        abstractC3385e1.j();
        return o();
    }

    public final AbstractC3390f1 q() {
        this.a.i();
        return o();
    }

    public final AbstractC3390f1 a(boolean z, Consumer consumer) {
        if (z) {
            consumer.accept(o());
        }
        return o();
    }

    public X0 a() {
        return null;
    }

    public final void a(C2752uB c2752uB) {
        boolean z = d;
        if (!z && this.a.g) {
            x1f.a();
        } else if (!z && this.b.isEmpty() && this.c.isEmpty() && c2752uB.g0()) {
            x1f.a();
        }
    }
}
