package com.android.tools.r8.naming;

import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.naming.J;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J {
    public static final /* synthetic */ boolean e = true;
    public final com.android.tools.r8.graph.E0 a;
    public final HashSet b = new HashSet();
    public final HashSet c = new HashSet();
    public final /* synthetic */ K d;

    public J(K k, com.android.tools.r8.graph.E0 e0) {
        this.d = k;
        this.a = e0;
    }

    public final Boolean a(com.android.tools.r8.graph.H0 h0, J j) {
        Iterator it = j.c.iterator();
        while (it.hasNext()) {
            Set setD = ((C3320e0) this.d.c.a.e.get((I2) it.next())).d(h0.getReference());
            if (!e && setD != null && setD.isEmpty()) {
                x1f.a();
                return null;
            }
            if (setD != null && setD.contains(h0.getReference().x0())) {
                return Boolean.TRUE;
            }
        }
        return null;
    }

    public final Boolean b(H2 h2, com.android.tools.r8.graph.H0 h0, J j) {
        Iterator it = j.c.iterator();
        while (it.hasNext()) {
            if (!this.d.c.a.b((I2) it.next()).b(h2, h0.getReference())) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public final /* synthetic */ void c(final H2 h2, final com.android.tools.r8.graph.H0 h0, J j) {
        j.c.forEach(new Consumer() { // from class: b77
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(h2, h0, (I2) obj);
            }
        });
    }

    public final void c(final com.android.tools.r8.graph.H0 h0, final H2 h2) {
        a(new Consumer() { // from class: d67
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c(h2, h0, (J) obj);
            }
        });
    }

    public final boolean b(final com.android.tools.r8.graph.H0 h0, final H2 h2) {
        Function function = new Function() { // from class: l67
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b(h2, h0, (J) obj);
            }
        };
        Object objApply = function.apply(this);
        if (objApply == null && (objApply = a(function)) == null) {
            objApply = b(function);
        }
        Boolean bool = (Boolean) objApply;
        return bool == null || bool.booleanValue();
    }

    public final void b(H2 h2, com.android.tools.r8.graph.H0 h0, I2 i2) {
        ((C3320e0) this.d.c.a.e.get(i2)).a(h0, h2);
    }

    public final Object b(Function function) {
        for (I2 i2 : this.a.h.b) {
            J j = (J) this.d.e.get(i2);
            if (j != null) {
                Object objApply = function.apply(j);
                if (objApply != null) {
                    return objApply;
                }
                Object objB = j.b(function);
                if (objB != null) {
                    return objB;
                }
            }
        }
        return null;
    }

    public final H2 a(final com.android.tools.r8.graph.H0 h0) {
        H2 h2A;
        if (this.d.a.M().H().s() && (h2A = this.d.c.a.b.a(h0)) != null) {
            return h2A;
        }
        Function function = new Function() { // from class: y67
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(h0, (J) obj);
            }
        };
        Object objApply = function.apply(this);
        if (objApply == null && (objApply = a(function)) == null) {
            objApply = b(function);
        }
        if (((Boolean) objApply) == null) {
            return null;
        }
        return h0.getReference().x0();
    }

    public final void a(final com.android.tools.r8.graph.H0 h0, final H2 h2) {
        a(new Consumer() { // from class: e77
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(h2, h0, (J) obj);
            }
        });
    }

    public final /* synthetic */ void a(final H2 h2, final com.android.tools.r8.graph.H0 h0, J j) {
        j.c.forEach(new Consumer() { // from class: o57
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(h2, h0, (I2) obj);
            }
        });
    }

    public final void a(H2 h2, com.android.tools.r8.graph.H0 h0, I2 i2) {
        ((C3312a0) this.d.c.a.b(i2).c(h0.getReference())).a(h2, h0.getReference());
    }

    public final void a(final Consumer consumer) {
        Function function = new Function() { // from class: v67
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return J.a(consumer, (J) obj);
            }
        };
        if (function.apply(this) == null && a(function) == null) {
            b(function);
        }
    }

    public static /* synthetic */ Object a(Consumer consumer, J j) {
        consumer.accept(j);
        return null;
    }

    public final Object a(Function function) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            J j = (J) this.d.e.get((I2) it.next());
            if (j != null) {
                Object objApply = function.apply(j);
                if (objApply != null) {
                    return objApply;
                }
                Object objA = j.a(function);
                if (objA != null) {
                    return objA;
                }
            }
        }
        return null;
    }
}
