package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.exe;
import defpackage.pah;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S8 extends AbstractC3175z9 {
    public static final /* synthetic */ boolean e = true;
    public final EnumC0430Dd c;
    public final US d;

    public S8(EnumC0430Dd enumC0430Dd, US us) {
        boolean z = e;
        if (!z && enumC0430Dd == null) {
            x1f.a();
            throw null;
        }
        if (!z && us == null) {
            x1f.a();
            throw null;
        }
        if (!z && us != US.f && us != US.g && us != US.h) {
            x1f.a();
            throw null;
        }
        if (!z && us == US.f && enumC0430Dd != EnumC0430Dd.b) {
            x1f.a();
            throw null;
        }
        if (!z && us != US.f && enumC0430Dd == EnumC0430Dd.b) {
            x1f.a();
            throw null;
        }
        this.c = enumC0430Dd;
        this.d = us;
    }

    public static S8 a(int i) {
        switch (i) {
            case 148:
                return new S8(EnumC0430Dd.b, US.f);
            case 149:
                return new S8(EnumC0430Dd.d, US.g);
            case 150:
                return new S8(EnumC0430Dd.c, US.g);
            case 151:
                return new S8(EnumC0430Dd.d, US.h);
            case 152:
                return new S8(EnumC0430Dd.c, US.h);
            default:
                exe.a("Wrong ASM opcode for CfCmp ", i);
                return null;
        }
    }

    public final int T() {
        int i = R8.a[this.d.ordinal()];
        if (i == 1) {
            return 148;
        }
        if (i == 2) {
            return this.c == EnumC0430Dd.d ? 149 : 150;
        }
        if (i == 3) {
            return this.c == EnumC0430Dd.d ? 151 : 152;
        }
        pah.a("CfCmp has unknown type ", this.d);
        return 0;
    }

    public final EnumC0430Dd U() {
        return this.c;
    }

    public final US V() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int w() {
        return 1;
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
        c0602Jt.a(this.d, this.c, c0738Pa.a(new C0609Ka(El0.c)).a, c0738Pa.a().a, i);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final AbstractC1808j9 a(AbstractC1808j9 abstractC1808j9, C0333y c0333y, InterfaceC2576s8 interfaceC2576s8) {
        US us = this.d;
        abstractC1808j9.getClass();
        AbstractC1808j9 abstractC1808j9A = abstractC1808j9.a(c0333y, interfaceC2576s8, us.a(c0333y.a()));
        US us2 = this.d;
        abstractC1808j9A.getClass();
        return abstractC1808j9A.a(c0333y, interfaceC2576s8, us2.a(c0333y.a())).a(interfaceC2576s8, c0333y.a().B1);
    }
}
