package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.exe;
import defpackage.pah;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class R9 extends AbstractC3175z9 {
    public static final /* synthetic */ boolean e = true;
    public final Q9 c;
    public final US d;

    public R9(Q9 q9, US us) {
        boolean z = e;
        if (!z && q9 == null) {
            x1f.a();
            throw null;
        }
        if (!z && us == null) {
            x1f.a();
            throw null;
        }
        if (!z && (us == US.g || us == US.h)) {
            x1f.a();
            throw null;
        }
        this.c = q9;
        this.d = us;
    }

    public static R9 a(int i) {
        switch (i) {
            case 120:
                return new R9(Q9.b, US.e);
            case 121:
                return new R9(Q9.b, US.f);
            case 122:
                return new R9(Q9.c, US.e);
            case 123:
                return new R9(Q9.c, US.f);
            case 124:
                return new R9(Q9.d, US.e);
            case 125:
                return new R9(Q9.d, US.f);
            case 126:
                return new R9(Q9.e, US.e);
            case 127:
                return new R9(Q9.e, US.f);
            case 128:
                return new R9(Q9.f, US.e);
            case 129:
                return new R9(Q9.f, US.f);
            case 130:
                return new R9(Q9.g, US.e);
            case 131:
                return new R9(Q9.g, US.f);
            default:
                exe.a("Wrong ASM opcode for CfLogicalBinop ", i);
                return null;
        }
    }

    public final Q9 T() {
        return this.c;
    }

    public US U() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int w() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int z() {
        return a(this.c, this.d);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int a(AbstractC3175z9 abstractC3175z9, AbstractC3519a abstractC3519a, com.android.tools.r8.graph.O o) {
        com.android.tools.r8.graph.O.a(this, abstractC3175z9);
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B1 b1, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, AbstractC0837Sv abstractC0837Sv, AbstractC3345r0 abstractC3345r0, RJ rj, YO yo) {
        yo.a(a(this.c, this.d));
    }

    public static int a(Q9 q9, US us) {
        int iOrdinal = q9.ordinal();
        if (iOrdinal == 0) {
            return us.a() ? 121 : 120;
        }
        if (iOrdinal == 1) {
            return us.a() ? 123 : 122;
        }
        if (iOrdinal == 2) {
            return us.a() ? 125 : 124;
        }
        if (iOrdinal == 3) {
            return us.a() ? 127 : 126;
        }
        if (iOrdinal == 4) {
            return us.a() ? 129 : 128;
        }
        if (iOrdinal == 5) {
            return us.a() ? 131 : 130;
        }
        defpackage.gk0.a("CfLogicalBinop has unknown opcode ", q9);
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C2520ra c2520ra) {
        c2520ra.a(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0602Jt c0602Jt, C0738Pa c0738Pa, C0401Ca c0401Ca) {
        int i = c0738Pa.a().a;
        int i2 = c0738Pa.a().a;
        int i3 = c0738Pa.a(El0.a(this.d)).a;
        int iOrdinal = this.c.ordinal();
        if (iOrdinal == 0) {
            c0602Jt.n(this.d, i3, i2, i);
            return;
        }
        if (iOrdinal == 1) {
            c0602Jt.o(this.d, i3, i2, i);
            return;
        }
        if (iOrdinal == 2) {
            c0602Jt.q(this.d, i3, i2, i);
            return;
        }
        if (iOrdinal == 3) {
            c0602Jt.c(this.d, i3, i2, i);
            return;
        }
        if (iOrdinal == 4) {
            c0602Jt.i(this.d, i3, i2, i);
        } else if (iOrdinal == 5) {
            c0602Jt.r(this.d, i3, i2, i);
        } else {
            pah.a("CfLogicalBinop has unknown opcode ", this.c);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final AbstractC1808j9 a(AbstractC1808j9 abstractC1808j9, C0333y c0333y, InterfaceC2576s8 interfaceC2576s8) {
        US us = this.d;
        int iOrdinal = this.c.ordinal();
        US us2 = (iOrdinal == 3 || iOrdinal == 4 || iOrdinal == 5) ? us : US.e;
        abstractC1808j9.getClass();
        AbstractC1808j9 abstractC1808j9A = abstractC1808j9.a(c0333y, interfaceC2576s8, us2.a(c0333y.a()));
        abstractC1808j9A.getClass();
        AbstractC1808j9 abstractC1808j9A2 = abstractC1808j9A.a(c0333y, interfaceC2576s8, us.a(c0333y.a()));
        abstractC1808j9A2.getClass();
        return abstractC1808j9A2.a(interfaceC2576s8, us.a(c0333y.a()));
    }
}
