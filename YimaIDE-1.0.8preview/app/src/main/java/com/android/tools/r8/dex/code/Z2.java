package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.Z2;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.g6g;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z2 extends b4 {
    public static final /* synthetic */ boolean i = true;
    public final int f;
    public final int g;
    public final int[] h;

    public Z2(int i2, A1 a1) {
        super(i2, a1);
        int iB = (char) (a1.b() & 65535);
        this.f = iB;
        this.g = AbstractC0138z1.a(a1);
        this.h = new int[iB];
        for (int i3 = 0; i3 < this.f; i3++) {
            this.h[i3] = AbstractC0138z1.a(a1);
        }
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final boolean D() {
        return true;
    }

    @Override // com.android.tools.r8.dex.code.b4
    public final int[] I() {
        return new int[]{this.g};
    }

    @Override // com.android.tools.r8.dex.code.b4
    public final int[] J() {
        return this.h;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0, AbstractC0138z1 abstractC0138z1) {
        StringBuilder sb = new StringBuilder("[PackedSwitchPayload");
        if (abstractC0138z1 == null) {
            sb.append(" offsets relative to associated PackedSwitch");
        }
        sb.append("]\n");
        for (int i2 = 0; i2 < this.f; i2++) {
            int[] iArr = this.h;
            Wf0.a(20, (this.g + i2) + " -> " + (abstractC0138z1 != null ? AbstractC0138z1.c(abstractC0138z1.q() + iArr[i2]) : AbstractC0138z1.b(iArr[i2])) + "\n", sb);
        }
        return super.b(c1581ga0) + sb.toString();
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        g6g g6gVar = new g6g();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        g6gVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return Arrays.hashCode(this.h) + ((((this.f + 17036887) * 31) + this.g) * 31);
    }

    @Override // com.android.tools.r8.dex.code.J, com.android.tools.r8.dex.code.AbstractC0138z1
    public final int t() {
        return (this.h.length * 2) + 4;
    }

    @Override // com.android.tools.r8.dex.code.E0, com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        return a(c1581ga0, (AbstractC0138z1) null);
    }

    public Z2(int[] iArr, int i2) {
        if (!i && iArr.length <= 0) {
            x1f.a();
            throw null;
        }
        this.f = iArr.length;
        this.g = i2;
        this.h = iArr;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: h6g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Z2) obj).f;
            }
        }).a(new ToIntFunction() { // from class: i6g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Z2) obj).g;
            }
        }).d(new Function() { // from class: j6g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Z2) obj).h;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.P2, com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (Z2) abstractC0138z1, new g6g());
    }

    @Override // com.android.tools.r8.dex.code.E0, com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(1, 0, shortBuffer);
        shortBuffer.put((short) this.f);
        AbstractC0138z1.a(this.g, shortBuffer);
        for (int i2 = 0; i2 < this.f; i2++) {
            AbstractC0138z1.a(this.h[i2], shortBuffer);
        }
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(AbstractC0138z1 abstractC0138z1) {
        StringBuilder sb = new StringBuilder("    .packed-switch ");
        sb.append(Wf0.a(this.g, 8));
        sb.append("  # ");
        sb.append(this.g);
        sb.append("\n");
        for (int i2 : this.h) {
            sb.append("      :label_");
            sb.append(abstractC0138z1.q() + i2);
            sb.append("\n");
        }
        sb.append("    .end packed-switch");
        return sb.toString();
    }
}
