package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.exe;
import defpackage.pah;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3004x8 extends AbstractC3175z9 {
    public static final /* synthetic */ boolean e = true;
    public final a c;
    public final US d;

    /* JADX INFO: renamed from: com.android.tools.r8.internal.x8$a */
    public enum a {
        b,
        c,
        d,
        e,
        f;

        a() {
        }
    }

    public C3004x8(a aVar, US us) {
        boolean z = e;
        if (!z && aVar == null) {
            x1f.a();
            throw null;
        }
        if (!z && us == null) {
            x1f.a();
            throw null;
        }
        this.c = aVar;
        this.d = us;
    }

    public static C3004x8 a(int i) {
        switch (i) {
            case 96:
                return new C3004x8(a.b, US.e);
            case 97:
                return new C3004x8(a.b, US.f);
            case 98:
                return new C3004x8(a.b, US.g);
            case 99:
                return new C3004x8(a.b, US.h);
            case 100:
                return new C3004x8(a.c, US.e);
            case 101:
                return new C3004x8(a.c, US.f);
            case 102:
                return new C3004x8(a.c, US.g);
            case 103:
                return new C3004x8(a.c, US.h);
            case 104:
                return new C3004x8(a.d, US.e);
            case 105:
                return new C3004x8(a.d, US.f);
            case 106:
                return new C3004x8(a.d, US.g);
            case 107:
                return new C3004x8(a.d, US.h);
            case 108:
                return new C3004x8(a.e, US.e);
            case 109:
                return new C3004x8(a.e, US.f);
            case 110:
                return new C3004x8(a.e, US.g);
            case 111:
                return new C3004x8(a.e, US.h);
            case 112:
                return new C3004x8(a.f, US.e);
            case 113:
                return new C3004x8(a.f, US.f);
            case 114:
                return new C3004x8(a.f, US.g);
            case 115:
                return new C3004x8(a.f, US.h);
            default:
                exe.a("Wrong ASM opcode for CfArithmeticBinop ", i);
                return null;
        }
    }

    public int T() {
        return a(this.c, this.d);
    }

    public a U() {
        return this.c;
    }

    public US V() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int w() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean x() {
        US us = this.d;
        if (us == US.g || us == US.h) {
            return false;
        }
        a aVar = this.c;
        return aVar == a.e || aVar == a.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int z() {
        return T();
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int a(AbstractC3175z9 abstractC3175z9, AbstractC3519a abstractC3519a, com.android.tools.r8.graph.O o) {
        com.android.tools.r8.graph.O.a(this, abstractC3175z9);
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
    }

    public static int a(a aVar, US us) {
        int i;
        int i2 = AbstractC2918w8.b[us.ordinal()];
        if (i2 == 1) {
            i = 1;
        } else if (i2 != 2) {
            i = i2 != 3 ? 0 : 3;
        } else {
            i = 2;
        }
        int i3 = AbstractC2918w8.a[aVar.ordinal()];
        if (i3 == 1) {
            return i + 96;
        }
        if (i3 == 2) {
            return i + 100;
        }
        if (i3 == 3) {
            return i + 104;
        }
        if (i3 == 4) {
            return i + 108;
        }
        if (i3 == 5) {
            return i + 112;
        }
        defpackage.gk0.a("CfArithmeticBinop has unknown opcode ", aVar);
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C2520ra c2520ra) {
        c2520ra.a(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B1 b1, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, AbstractC0837Sv abstractC0837Sv, AbstractC3345r0 abstractC3345r0, RJ rj, YO yo) {
        yo.a(T());
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0602Jt c0602Jt, C0738Pa c0738Pa, C0401Ca c0401Ca) {
        int i = c0738Pa.a().a;
        int i2 = c0738Pa.a().a;
        int i3 = c0738Pa.a(El0.a(this.d)).a;
        int i4 = AbstractC2918w8.a[this.c.ordinal()];
        if (i4 == 1) {
            c0602Jt.a(this.d, i3, i2, i);
            return;
        }
        if (i4 == 2) {
            c0602Jt.p(this.d, i3, i2, i);
            return;
        }
        if (i4 == 3) {
            c0602Jt.g(this.d, i3, i2, i);
            return;
        }
        if (i4 == 4) {
            c0602Jt.e(this.d, i3, i2, i);
        } else if (i4 == 5) {
            c0602Jt.k(this.d, i3, i2, i);
        } else {
            pah.a("CfArithmeticBinop has unknown opcode ", this.c);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final AbstractC1808j9 a(AbstractC1808j9 abstractC1808j9, C0333y c0333y, InterfaceC2576s8 interfaceC2576s8) {
        US us = this.d;
        abstractC1808j9.getClass();
        AbstractC1808j9 abstractC1808j9A = abstractC1808j9.a(c0333y, interfaceC2576s8, us.a(c0333y.a()));
        US us2 = this.d;
        abstractC1808j9A.getClass();
        AbstractC1808j9 abstractC1808j9A2 = abstractC1808j9A.a(c0333y, interfaceC2576s8, us2.a(c0333y.a()));
        US us3 = this.d;
        abstractC1808j9A2.getClass();
        return abstractC1808j9A2.a(interfaceC2576s8, us3.a(c0333y.a()));
    }
}
