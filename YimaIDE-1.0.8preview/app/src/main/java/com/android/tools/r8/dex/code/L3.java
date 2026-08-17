package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.L3;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.xk8;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L3 extends b4 {
    public static final /* synthetic */ boolean i = true;
    public final int f;
    public final int[] g;
    public final int[] h;

    public L3(int i2, A1 a1) {
        int i3;
        super(i2, a1);
        int iB = (char) (a1.b() & 65535);
        this.f = iB;
        this.g = new int[iB];
        int i4 = 0;
        while (true) {
            i3 = this.f;
            if (i4 >= i3) {
                break;
            }
            this.g[i4] = AbstractC0138z1.a(a1);
            i4++;
        }
        this.h = new int[i3];
        for (int i5 = 0; i5 < this.f; i5++) {
            this.h[i5] = AbstractC0138z1.a(a1);
        }
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final boolean D() {
        return true;
    }

    @Override // com.android.tools.r8.dex.code.b4
    public final int[] I() {
        return this.g;
    }

    @Override // com.android.tools.r8.dex.code.b4
    public final int[] J() {
        return this.h;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0, AbstractC0138z1 abstractC0138z1) {
        String string;
        StringBuilder sb = new StringBuilder("[SparseSwitchPayload");
        if (abstractC0138z1 == null) {
            sb.append(" offsets relative to associated SparseSwitch");
        }
        sb.append("]\n");
        for (int i2 = 0; i2 < this.f; i2++) {
            int[] iArr = this.h;
            if (abstractC0138z1 != null) {
                string = Wf0.a(abstractC0138z1.q() + iArr[i2], 2);
            } else {
                int i3 = iArr[i2];
                string = i3 >= 0 ? "+" + i3 : Integer.toString(i3);
            }
            Wf0.a(20, this.g[i2] + " -> " + string + "\n", sb);
        }
        return super.b(c1581ga0) + sb.toString();
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        xk8 xk8Var = new xk8();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        xk8Var.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return Arrays.hashCode(this.h) + ((Arrays.hashCode(this.g) + ((this.f + 17036887) * 31)) * 31);
    }

    @Override // com.android.tools.r8.dex.code.J, com.android.tools.r8.dex.code.AbstractC0138z1
    public final int t() {
        return (this.h.length * 2) + (this.g.length * 2) + 2;
    }

    @Override // com.android.tools.r8.dex.code.E0, com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        return a(c1581ga0, (AbstractC0138z1) null);
    }

    public L3(int[] iArr, int[] iArr2) {
        if (!i && iArr2.length <= 0) {
            x1f.a();
            throw null;
        }
        this.f = iArr2.length;
        this.g = iArr;
        this.h = iArr2;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: al8
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((L3) obj).f;
            }
        }).d(new Function() { // from class: cl8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((L3) obj).g;
            }
        }).d(new Function() { // from class: el8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((L3) obj).h;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (L3) abstractC0138z1, new xk8());
    }

    @Override // com.android.tools.r8.dex.code.E0, com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(2, 0, shortBuffer);
        shortBuffer.put((short) this.f);
        for (int i2 = 0; i2 < this.f; i2++) {
            AbstractC0138z1.a(this.g[i2], shortBuffer);
        }
        for (int i3 = 0; i3 < this.f; i3++) {
            AbstractC0138z1.a(this.h[i3], shortBuffer);
        }
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(AbstractC0138z1 abstractC0138z1) {
        StringBuilder sb = new StringBuilder("    .sparse-switch\n");
        for (int i2 = 0; i2 < this.g.length; i2++) {
            sb.append("      ");
            sb.append(Wf0.a(this.g[i2], 8));
            sb.append(" -> :label_");
            sb.append(abstractC0138z1.q() + this.h[i2]);
            sb.append("  # ");
            sb.append(this.g[i2]);
            sb.append("\n");
        }
        sb.append("    .end sparse-switch");
        return sb.toString();
    }
}
