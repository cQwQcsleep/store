package com.android.tools.r8.shaking;

import com.android.tools.r8.shaking.X0;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X0 extends AbstractC3390f1 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X0(Y0 y0) {
        super(new W0(y0));
        y0.getClass();
    }

    @Override // com.android.tools.r8.shaking.AbstractC3390f1
    public final X0 a(X0 x0) {
        return (X0) ((X0) ((X0) ((X0) ((X0) ((X0) ((X0) ((X0) super.a((AbstractC3390f1) x0)).a(((W0) x0.a).p, new Consumer() { // from class: pvf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((X0) obj).x();
            }
        })).a(!((W0) x0.a).j, new Consumer() { // from class: svf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((X0) obj).r();
            }
        })).a(!((W0) x0.a).k, new Consumer() { // from class: vvf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((X0) obj).s();
            }
        })).a(!((W0) x0.a).l, new Consumer() { // from class: xvf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((X0) obj).t();
            }
        })).a(!((W0) x0.a).m, new Consumer() { // from class: yvf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((X0) obj).u();
            }
        })).a(!((W0) x0.a).n, new Consumer() { // from class: zvf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((X0) obj).v();
            }
        })).a(!((W0) x0.a).o, new Consumer() { // from class: awf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((X0) obj).w();
            }
        });
    }

    @Override // com.android.tools.r8.shaking.AbstractC3390f1
    public final AbstractC3390f1 o() {
        return this;
    }

    public final X0 r() {
        ((W0) this.a).j = false;
        return this;
    }

    public final X0 s() {
        ((W0) this.a).k = false;
        return this;
    }

    public final X0 t() {
        ((W0) this.a).l = false;
        return this;
    }

    public final X0 u() {
        ((W0) this.a).m = false;
        return this;
    }

    public final X0 v() {
        ((W0) this.a).n = false;
        return this;
    }

    public final X0 w() {
        ((W0) this.a).o = false;
        return this;
    }

    public final X0 x() {
        ((W0) this.a).p = true;
        return this;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3390f1
    public final X0 a() {
        return this;
    }
}
