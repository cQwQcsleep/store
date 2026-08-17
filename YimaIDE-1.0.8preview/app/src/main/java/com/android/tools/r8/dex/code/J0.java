package com.android.tools.r8.dex.code;

import com.android.tools.r8.graph.X3;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.util.function.ToIntFunction;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class J0<T extends com.android.tools.r8.graph.X3> extends K {
    public static final /* synthetic */ boolean h = true;
    public final short f;
    public T g;

    /* JADX WARN: Multi-variable type inference failed */
    public J0(int i, com.android.tools.r8.graph.X3 x3) {
        if (!h && (i < 0 || i > 255)) {
            x1f.a();
            throw null;
        }
        this.f = (short) i;
        this.g = x3;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public String a(C1581ga0 c1581ga0) {
        short s = this.f;
        return a("v" + ((int) s) + ", " + this.g.l0());
    }

    public abstract void a(com.android.tools.r8.utils.structural.A a);

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public String b(C1581ga0 c1581ga0) {
        short s = this.f;
        return b("v" + ((int) s) + ", " + c1581ga0.a(this.g));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ ((this.g.hashCode() << 8) | this.f);
    }

    public J0(int i, InterfaceC0012a interfaceC0012a, com.android.tools.r8.graph.X3[] x3Arr) {
        super(interfaceC0012a);
        this.f = (short) i;
        this.g = (T) x3Arr[AbstractC0138z1.a(interfaceC0012a)];
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (J0) abstractC0138z1, (com.android.tools.r8.utils.structural.y<J0>) new com.android.tools.r8.utils.structural.y() { // from class: com.android.tools.r8.dex.code.v4
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(com.android.tools.r8.utils.structural.A a) {
                this.a.b(a);
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.a.a((int) this.f);
        new u4(this).a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    public final void b(com.android.tools.r8.utils.structural.A a) {
        com.android.tools.r8.utils.structural.A a2 = a.a(new ToIntFunction() { // from class: com.android.tools.r8.dex.code.w4
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((J0) obj).f;
            }
        });
        u4 u4Var = new u4(this);
        a2.getClass();
        u4Var.a(a2);
    }
}
