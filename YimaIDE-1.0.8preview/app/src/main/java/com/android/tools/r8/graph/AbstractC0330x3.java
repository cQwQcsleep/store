package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0288r3;
import com.android.tools.r8.graph.AbstractC0330x3;
import com.android.tools.r8.internal.AbstractC2173nV;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.x3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0330x3 extends D4<C0210g1, C0245l1> {
    public static final /* synthetic */ boolean a = true;

    public static a a(E0 e0, E0 e1, C0210g1 c0210g1) {
        if (e1.b0()) {
            return new C0337y3(e0, e1.Z(), c0210g1);
        }
        if (e1 instanceof I0) {
            return new C0323w3(e0, e1.m(), c0210g1);
        }
        if (a || e1.a0()) {
            return new C0344z3(e0, e1.X(), c0210g1);
        }
        x1f.a();
        return null;
    }

    public abstract void a(Consumer consumer, Consumer consumer2, Consumer consumer3);

    public final void b(Consumer consumer) {
        a(consumer, consumer, new Consumer() { // from class: nqi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC0330x3.a((AbstractC0288r3) obj);
            }
        });
    }

    @Override // com.android.tools.r8.graph.D4
    public final AbstractC0330x3 e() {
        return this;
    }

    @Override // com.android.tools.r8.graph.D4
    public final boolean i() {
        return true;
    }

    @Override // com.android.tools.r8.graph.D4
    public boolean j() {
        return false;
    }

    public C0323w3 k() {
        return null;
    }

    public a<?> l() {
        return null;
    }

    public C0344z3 m() {
        return null;
    }

    @Override // com.android.tools.r8.graph.D4
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public a g() {
        return null;
    }

    public C0346z5 o() {
        return null;
    }

    public F0 p() {
        return null;
    }

    public C0210g1 q() {
        return null;
    }

    public C0346z5 r() {
        return null;
    }

    public boolean s() {
        return false;
    }

    public boolean t() {
        return false;
    }

    public boolean u() {
        return false;
    }

    public boolean v() {
        return false;
    }

    public boolean w() {
        return false;
    }

    public boolean x() {
        return false;
    }

    public boolean y() {
        return false;
    }

    /* JADX INFO: renamed from: com.android.tools.r8.graph.x3$a */
    public static abstract class a<T extends E0> extends AbstractC0330x3 implements T5 {
        public static final /* synthetic */ boolean e = true;
        public final E0 b;
        public final E0 c;
        public final C0210g1 d;

        public a(E0 e0, E0 e1, C0210g1 c0210g1) {
            if (!e && e1.e != c0210g1.E0()) {
                x1f.a();
                throw null;
            }
            this.b = e0;
            this.c = e1;
            this.d = c0210g1;
        }

        @Override // com.android.tools.r8.graph.D4
        public final AbstractC2173nV a(InterfaceC0332x5 interfaceC0332x5, C0333y c0333y, C0229j c0229j) {
            return AbstractC0194e.a(c(), a(), interfaceC0332x5, c0333y, c0229j);
        }

        @Override // com.android.tools.r8.graph.T5
        public final AbstractC0217h1 b() {
            return this.d;
        }

        @Override // com.android.tools.r8.graph.T5
        public final G0 c() {
            return F0.a(this.c, this.d);
        }

        @Override // com.android.tools.r8.graph.T5
        public T d() {
            return (T) this.c;
        }

        @Override // com.android.tools.r8.graph.AbstractC0330x3, com.android.tools.r8.graph.D4
        public final T5 g() {
            return this;
        }

        @Override // com.android.tools.r8.graph.AbstractC0330x3, com.android.tools.r8.graph.D4
        public final boolean j() {
            return true;
        }

        @Override // com.android.tools.r8.graph.AbstractC0330x3
        public final a l() {
            return this;
        }

        @Override // com.android.tools.r8.graph.AbstractC0330x3
        /* JADX INFO: renamed from: n */
        public final a g() {
            return this;
        }

        @Override // com.android.tools.r8.graph.AbstractC0330x3
        public final F0 p() {
            return F0.a(this.c, this.d);
        }

        @Override // com.android.tools.r8.graph.AbstractC0330x3
        public final C0210g1 q() {
            return this.d;
        }

        @Override // com.android.tools.r8.graph.AbstractC0330x3
        public final boolean v() {
            return true;
        }

        @Override // com.android.tools.r8.graph.AbstractC0330x3
        public final boolean y() {
            return true;
        }

        @Override // com.android.tools.r8.graph.AbstractC0330x3
        public final E0 a() {
            return this.b;
        }
    }

    public static /* synthetic */ void a(AbstractC0288r3 abstractC0288r3) {
    }

    public final void a(Consumer<AbstractC0330x3> consumer) {
        a(consumer, consumer, consumer);
    }

    public E0 a() {
        return null;
    }
}
