package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC1808j9;
import com.android.tools.r8.internal.BX;
import com.android.tools.r8.internal.C0470Er;
import com.android.tools.r8.internal.C0479Fa;
import com.android.tools.r8.internal.InterfaceC1938ki0;
import com.android.tools.r8.internal.InterfaceC2576s8;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.n33;
import defpackage.vm4;
import defpackage.x0g;
import java.util.function.BiFunction;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fa, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0479Fa extends AbstractC3175z9 {
    public static final /* synthetic */ boolean d = true;
    public final a c;

    /* JADX INFO: renamed from: com.android.tools.r8.internal.Fa$a */
    public enum a {
        c("Pop"),
        d("Pop2"),
        e("Dup"),
        f("DupX1"),
        g("DupX2"),
        h("Dup2"),
        i("Dup2X1"),
        j("Dup2X2"),
        k("Swap");

        public final int b;

        a(String str) {
            this.b = i;
        }
    }

    public C0479Fa(a aVar) {
        this.c = aVar;
    }

    public static void b(C0602Jt c0602Jt, C0738Pa c0738Pa, C0583Ja c0583Ja, C0583Ja c0583Ja2, C0583Ja c0583Ja3) {
        c0738Pa.getClass();
        C0583Ja c0583JaA = c0738Pa.a(c0583Ja2.d);
        C0583Ja c0583JaA2 = c0738Pa.a(c0583Ja.d);
        C0583Ja c0583JaA3 = c0738Pa.a(c0583Ja3.d);
        C0583Ja c0583JaA4 = c0738Pa.a(c0583Ja2.d);
        C0583Ja c0583JaA5 = c0738Pa.a(c0583Ja.d);
        El0 el0 = c0583Ja.b;
        int i = c0583JaA5.a;
        int i2 = c0583Ja.a;
        c0602Jt.getClass();
        c0602Jt.a(Gl0.a(el0), i, i2);
        El0 el1 = c0583Ja2.b;
        c0602Jt.a(Gl0.a(el1), c0583JaA4.a, c0583Ja2.a);
        El0 el2 = c0583Ja3.b;
        c0602Jt.a(Gl0.a(el2), c0583JaA3.a, c0583Ja3.a);
        El0 el3 = c0583Ja.b;
        c0602Jt.a(Gl0.a(el3), c0583JaA2.a, c0583JaA5.a);
        El0 el4 = c0583Ja2.b;
        c0602Jt.a(Gl0.a(el4), c0583JaA.a, c0583JaA4.a);
    }

    public static AbstractC1808j9 d(InterfaceC2576s8 interfaceC2576s8, AbstractC1808j9 abstractC1808j9, BX bx, BX bx2) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2);
    }

    public a T() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0602Jt c0602Jt, C0738Pa c0738Pa, C0401Ca c0401Ca) {
        switch (AbstractC0453Ea.a[this.c.ordinal()]) {
            case 1:
                C0583Ja c0583JaA = c0738Pa.a();
                if (!d && c0583JaA.b.b()) {
                    x01.a("Expected stack type to be single width");
                    break;
                }
                break;
            case 2:
                if (!c0738Pa.a().b.b()) {
                    C0583Ja c0583JaA2 = c0738Pa.a();
                    if (!d && c0583JaA2.b.b()) {
                        x1f.a();
                        break;
                    }
                }
                break;
            case XmlPullParser.END_TAG /* 3 */:
                C0583Ja c0583JaC = c0738Pa.a.c();
                if (!d && c0583JaC.b.b()) {
                    x1f.a();
                } else {
                    El0 el0 = c0583JaC.b;
                    int i = c0738Pa.a(c0583JaC.d).a;
                    int i2 = c0583JaC.a;
                    c0602Jt.getClass();
                    c0602Jt.a(Gl0.a(el0), i, i2);
                }
                break;
            case 4:
                C0583Ja c0583JaA3 = c0738Pa.a();
                C0583Ja c0583JaA4 = c0738Pa.a();
                boolean z = d;
                if (!z && c0583JaA3.b.b()) {
                    x1f.a();
                } else if (!z && c0583JaA4.b.b()) {
                    x1f.a();
                } else {
                    a(c0602Jt, c0738Pa, c0583JaA3, c0583JaA4);
                }
                break;
            case XmlPullParser.CDSECT /* 5 */:
                C0583Ja c0583JaA5 = c0738Pa.a();
                C0583Ja c0583JaA6 = c0738Pa.a();
                boolean z2 = d;
                if (!z2 && c0583JaA5.b.b()) {
                    x1f.a();
                } else if (!c0583JaA6.b.b()) {
                    C0583Ja c0583JaA7 = c0738Pa.a();
                    if (!z2 && c0583JaA7.b.b()) {
                        x1f.a();
                    } else {
                        a(c0602Jt, c0738Pa, c0583JaA5, c0583JaA6, c0583JaA7);
                    }
                } else {
                    a(c0602Jt, c0738Pa, c0583JaA5, c0583JaA6);
                }
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                C0583Ja c0583JaC2 = c0738Pa.a.c();
                if (!c0583JaC2.b.b()) {
                    AbstractC0686Na abstractC0686Na = c0738Pa.a;
                    C0583Ja c0583JaB = abstractC0686Na.b(abstractC0686Na.d() - 2);
                    El0 el1 = c0583JaB.b;
                    int i3 = c0738Pa.a(c0583JaB.d).a;
                    int i4 = c0583JaB.a;
                    c0602Jt.getClass();
                    c0602Jt.a(Gl0.a(el1), i3, i4);
                    c0602Jt.a(Gl0.a(c0583JaC2.b), c0738Pa.a(c0583JaC2.d).a, c0583JaC2.a);
                } else {
                    El0 el2 = c0583JaC2.b;
                    int i5 = c0738Pa.a(c0583JaC2.d).a;
                    int i6 = c0583JaC2.a;
                    c0602Jt.getClass();
                    c0602Jt.a(Gl0.a(el2), i5, i6);
                }
                break;
            case 7:
                C0583Ja c0583JaA8 = c0738Pa.a();
                C0583Ja c0583JaA9 = c0738Pa.a();
                boolean z3 = d;
                if (!z3 && c0583JaA9.b.b()) {
                    x1f.a();
                } else if (!c0583JaA8.b.b()) {
                    C0583Ja c0583JaA10 = c0738Pa.a();
                    if (!z3 && c0583JaA10.b.b()) {
                        x1f.a();
                    } else {
                        b(c0602Jt, c0738Pa, c0583JaA8, c0583JaA9, c0583JaA10);
                    }
                } else {
                    a(c0602Jt, c0738Pa, c0583JaA8, c0583JaA9);
                }
                break;
            case 8:
                C0583Ja c0583JaA11 = c0738Pa.a();
                C0583Ja c0583JaA12 = c0738Pa.a();
                if (c0583JaA11.b.b() && c0583JaA12.b.b()) {
                    a(c0602Jt, c0738Pa, c0583JaA11, c0583JaA12);
                } else {
                    C0583Ja c0583JaA13 = c0738Pa.a();
                    if (!c0583JaA11.b.b()) {
                        if (c0583JaA12.b.b()) {
                            n33.a("Invalid dup2x2 with types: ..., wide, single");
                        } else if (!c0583JaA13.b.b()) {
                            C0583Ja c0583JaA14 = c0738Pa.a();
                            if (!c0583JaA14.b.b()) {
                                C0583Ja c0583JaA15 = c0738Pa.a(c0583JaA12.d);
                                C0583Ja c0583JaA16 = c0738Pa.a(c0583JaA11.d);
                                C0583Ja c0583JaA17 = c0738Pa.a(c0583JaA14.d);
                                C0583Ja c0583JaA18 = c0738Pa.a(c0583JaA13.d);
                                C0583Ja c0583JaA19 = c0738Pa.a(c0583JaA12.d);
                                C0583Ja c0583JaA20 = c0738Pa.a(c0583JaA11.d);
                                El0 el3 = c0583JaA11.b;
                                int i7 = c0583JaA20.a;
                                int i8 = c0583JaA11.a;
                                c0602Jt.getClass();
                                c0602Jt.a(Gl0.a(el3), i7, i8);
                                c0602Jt.a(Gl0.a(c0583JaA12.b), c0583JaA19.a, c0583JaA12.a);
                                c0602Jt.a(Gl0.a(c0583JaA13.b), c0583JaA18.a, c0583JaA13.a);
                                c0602Jt.a(Gl0.a(c0583JaA14.b), c0583JaA17.a, c0583JaA14.a);
                                c0602Jt.a(Gl0.a(c0583JaA20.b), c0583JaA16.a, c0583JaA20.a);
                                c0602Jt.a(Gl0.a(c0583JaA19.b), c0583JaA15.a, c0583JaA19.a);
                            } else {
                                n33.a("Invalid dup2x2 with types: wide, single, single, single");
                            }
                        } else {
                            b(c0602Jt, c0738Pa, c0583JaA11, c0583JaA12, c0583JaA13);
                        }
                    } else if (!c0583JaA13.b.b()) {
                        a(c0602Jt, c0738Pa, c0583JaA11, c0583JaA12, c0583JaA13);
                    } else {
                        n33.a("Invalid dup2x2 with types: wide, single, wide");
                    }
                }
                break;
            case 9:
                C0583Ja c0583JaA21 = c0738Pa.a();
                C0583Ja c0583JaA22 = c0738Pa.a();
                boolean z4 = d;
                if (!z4 && c0583JaA21.b.b()) {
                    x1f.a();
                } else if (!z4 && c0583JaA22.b.b()) {
                    x1f.a();
                } else {
                    a(c0602Jt, c0738Pa, c0583JaA21, c0583JaA22);
                    c0738Pa.a();
                }
                break;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int w() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean y() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int z() {
        return this.c.b;
    }

    public static AbstractC1808j9 e(InterfaceC2576s8 interfaceC2576s8, AbstractC1808j9 abstractC1808j9, BX bx) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx);
    }

    public static AbstractC1808j9 c(InterfaceC2576s8 interfaceC2576s8, BX bx, AbstractC1808j9 abstractC1808j9, BX bx2) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx);
    }

    public static AbstractC1808j9 c(InterfaceC2576s8 interfaceC2576s8, AbstractC1808j9 abstractC1808j9, BX bx, BX bx2) {
        return abstractC1808j9.a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx);
    }

    public static AbstractC1808j9 b(InterfaceC2576s8 interfaceC2576s8, BX bx, AbstractC1808j9 abstractC1808j9, BX bx2, BX bx3) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx3).a(interfaceC2576s8, bx);
    }

    public static AbstractC1808j9 b(InterfaceC2576s8 interfaceC2576s8, BX bx, BX bx2, AbstractC1808j9 abstractC1808j9, BX bx3) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx3).a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2);
    }

    public static AbstractC1808j9 b(InterfaceC2576s8 interfaceC2576s8, BX bx, AbstractC1808j9 abstractC1808j9, BX bx2) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx);
    }

    public static C0479Fa a(int i) {
        switch (i) {
            case 87:
                return new C0479Fa(a.c);
            case 88:
                return new C0479Fa(a.d);
            case 89:
                return new C0479Fa(a.e);
            case 90:
                return new C0479Fa(a.f);
            case 91:
                return new C0479Fa(a.g);
            case 92:
                return new C0479Fa(a.h);
            case 93:
                return new C0479Fa(a.i);
            case 94:
                return new C0479Fa(a.j);
            case 95:
                return new C0479Fa(a.k);
            default:
                x0g.a("Invalid opcode for CfStackInstruction");
                return null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int a(AbstractC3175z9 abstractC3175z9, AbstractC3519a abstractC3519a, com.android.tools.r8.graph.O o) {
        com.android.tools.r8.graph.O.a(this, abstractC3175z9);
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B1 b1, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, AbstractC0837Sv abstractC0837Sv, AbstractC3345r0 abstractC3345r0, RJ rj, YO yo) {
        yo.a(this.c.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C2520ra c2520ra) {
        c2520ra.a(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
    }

    public static AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, AbstractC1808j9 abstractC1808j9, BX bx) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx);
    }

    public static AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, AbstractC1808j9 abstractC1808j9, BX bx, BX bx2) {
        return abstractC1808j9.a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2);
    }

    public static AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, BX bx, AbstractC1808j9 abstractC1808j9, BX bx2) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx);
    }

    public static AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, BX bx, AbstractC1808j9 abstractC1808j9, BX bx2, BX bx3) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx3).a(interfaceC2576s8, bx);
    }

    public static void a(C0602Jt c0602Jt, C0738Pa c0738Pa, C0583Ja c0583Ja, C0583Ja c0583Ja2) {
        c0738Pa.getClass();
        C0583Ja c0583JaA = c0738Pa.a(c0583Ja.d);
        C0583Ja c0583JaA2 = c0738Pa.a(c0583Ja2.d);
        C0583Ja c0583JaA3 = c0738Pa.a(c0583Ja.d);
        El0 el0 = c0583Ja.b;
        int i = c0583JaA3.a;
        int i2 = c0583Ja.a;
        c0602Jt.getClass();
        c0602Jt.a(Gl0.a(el0), i, i2);
        El0 el1 = c0583Ja2.b;
        c0602Jt.a(Gl0.a(el1), c0583JaA2.a, c0583Ja2.a);
        El0 el2 = c0583JaA3.b;
        c0602Jt.a(Gl0.a(el2), c0583JaA.a, c0583JaA3.a);
    }

    public static AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, BX bx, BX bx2, AbstractC1808j9 abstractC1808j9, BX bx3) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx3).a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2);
    }

    public static void a(C0602Jt c0602Jt, C0738Pa c0738Pa, C0583Ja c0583Ja, C0583Ja c0583Ja2, C0583Ja c0583Ja3) {
        c0738Pa.getClass();
        C0583Ja c0583JaA = c0738Pa.a(c0583Ja.d);
        C0583Ja c0583JaA2 = c0738Pa.a(c0583Ja3.d);
        C0583Ja c0583JaA3 = c0738Pa.a(c0583Ja2.d);
        C0583Ja c0583JaA4 = c0738Pa.a(c0583Ja.d);
        El0 el0 = c0583Ja.b;
        int i = c0583JaA4.a;
        int i2 = c0583Ja.a;
        c0602Jt.getClass();
        c0602Jt.a(Gl0.a(el0), i, i2);
        El0 el1 = c0583Ja2.b;
        c0602Jt.a(Gl0.a(el1), c0583JaA3.a, c0583Ja2.a);
        El0 el2 = c0583Ja3.b;
        c0602Jt.a(Gl0.a(el2), c0583JaA2.a, c0583Ja3.a);
        El0 el3 = c0583JaA4.b;
        c0602Jt.a(Gl0.a(el3), c0583JaA.a, c0583JaA4.a);
    }

    public static AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, BX bx, BX bx2, AbstractC1808j9 abstractC1808j9, BX bx3, BX bx4) {
        return abstractC1808j9.a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2).a(interfaceC2576s8, bx3).a(interfaceC2576s8, bx4).a(interfaceC2576s8, bx).a(interfaceC2576s8, bx2);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final AbstractC1808j9 a(AbstractC1808j9 abstractC1808j9, C0333y c0333y, final InterfaceC2576s8 interfaceC2576s8) {
        switch (AbstractC0453Ea.a[this.c.ordinal()]) {
            case 1:
                return abstractC1808j9.h();
            case 2:
                return abstractC1808j9.a(new InterfaceC1938ki0() { // from class: um4
                    @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                    public final Object a(Object obj, Object obj2, Object obj3) {
                        return (AbstractC1808j9) C0470Er.a((AbstractC1808j9) obj, (BX) obj2, (BX) obj3);
                    }
                }, new vm4());
            case XmlPullParser.END_TAG /* 3 */:
                return abstractC1808j9.c(new BiFunction() { // from class: lm4
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return C0479Fa.a(interfaceC2576s8, (AbstractC1808j9) obj, (BX) obj2);
                    }
                });
            case 4:
                return abstractC1808j9.a(new InterfaceC1938ki0() { // from class: km4
                    @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                    public final Object a(Object obj, Object obj2, Object obj3) {
                        return C0479Fa.a(interfaceC2576s8, (AbstractC1808j9) obj, (BX) obj2, (BX) obj3);
                    }
                });
            case XmlPullParser.CDSECT /* 5 */:
                return abstractC1808j9.c(new BiFunction() { // from class: jm4
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        InterfaceC2576s8 interfaceC2576s9 = interfaceC2576s8;
                        BX bx = (BX) obj2;
                        return ((AbstractC1808j9) obj).a(new InterfaceC1938ki0() { // from class: rm4
                            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                            public final Object a(Object obj3, Object obj4, Object obj5) {
                                return C0479Fa.b(interfaceC2576s9, bx, (AbstractC1808j9) obj3, (BX) obj4, (BX) obj5);
                            }
                        }, new BiFunction() { // from class: sm4
                            @Override // java.util.function.BiFunction
                            public final Object apply(Object obj3, Object obj4) {
                                return C0479Fa.b(interfaceC2576s9, bx, (AbstractC1808j9) obj3, (BX) obj4);
                            }
                        });
                    }
                });
            case XmlPullParser.ENTITY_REF /* 6 */:
                return abstractC1808j9.a(new InterfaceC1938ki0() { // from class: hm4
                    @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                    public final Object a(Object obj, Object obj2, Object obj3) {
                        return C0479Fa.d(interfaceC2576s8, (AbstractC1808j9) obj, (BX) obj2, (BX) obj3);
                    }
                }, new BiFunction() { // from class: im4
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return C0479Fa.e(interfaceC2576s8, (AbstractC1808j9) obj, (BX) obj2);
                    }
                });
            case 7:
                return abstractC1808j9.a(new InterfaceC1938ki0() { // from class: fm4
                    @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                    public final Object a(Object obj, Object obj2, Object obj3) {
                        return ((AbstractC1808j9) obj).c(new BiFunction() { // from class: om4
                            @Override // java.util.function.BiFunction
                            public final Object apply(Object obj4, Object obj5) {
                                return C0479Fa.b(interfaceC2576s8, bx, bx, (AbstractC1808j9) obj4, (BX) obj5);
                            }
                        });
                    }
                }, new BiFunction() { // from class: gm4
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return ((AbstractC1808j9) obj).c(new BiFunction() { // from class: qm4
                            @Override // java.util.function.BiFunction
                            public final Object apply(Object obj3, Object obj4) {
                                return C0479Fa.c(interfaceC2576s8, bx, (AbstractC1808j9) obj3, (BX) obj4);
                            }
                        });
                    }
                });
            case 8:
                return abstractC1808j9.a(new InterfaceC1938ki0() { // from class: wm4
                    @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                    public final Object a(Object obj, Object obj2, Object obj3) {
                        InterfaceC2576s8 interfaceC2576s9 = interfaceC2576s8;
                        BX bx = (BX) obj2;
                        BX bx2 = (BX) obj3;
                        return ((AbstractC1808j9) obj).a(new InterfaceC1938ki0() { // from class: mm4
                            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                            public final Object a(Object obj4, Object obj5, Object obj6) {
                                return C0479Fa.a(interfaceC2576s9, bx, bx2, (AbstractC1808j9) obj4, (BX) obj5, (BX) obj6);
                            }
                        }, new BiFunction() { // from class: nm4
                            @Override // java.util.function.BiFunction
                            public final Object apply(Object obj4, Object obj5) {
                                return C0479Fa.a(interfaceC2576s9, bx, bx2, (AbstractC1808j9) obj4, (BX) obj5);
                            }
                        });
                    }
                }, new BiFunction() { // from class: xm4
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        InterfaceC2576s8 interfaceC2576s9 = interfaceC2576s8;
                        BX bx = (BX) obj2;
                        return ((AbstractC1808j9) obj).a(new InterfaceC1938ki0() { // from class: em4
                            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                            public final Object a(Object obj3, Object obj4, Object obj5) {
                                return C0479Fa.a(interfaceC2576s9, bx, (AbstractC1808j9) obj3, (BX) obj4, (BX) obj5);
                            }
                        }, new BiFunction() { // from class: pm4
                            @Override // java.util.function.BiFunction
                            public final Object apply(Object obj3, Object obj4) {
                                return C0479Fa.a(interfaceC2576s9, bx, (AbstractC1808j9) obj3, (BX) obj4);
                            }
                        });
                    }
                });
            case 9:
                return abstractC1808j9.a(new InterfaceC1938ki0() { // from class: tm4
                    @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                    public final Object a(Object obj, Object obj2, Object obj3) {
                        return C0479Fa.c(interfaceC2576s8, (AbstractC1808j9) obj, (BX) obj2, (BX) obj3);
                    }
                });
            default:
                x0g.a("Invalid opcode for CfStackInstruction");
                return null;
        }
    }
}
