package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Vm0 {
    public static final C2690tZ a(AH ah, C1814jE c1814jE) {
        KB.c(ah, "<this>");
        KB.c(c1814jE, "strings");
        C2861vZ c2861vZ = C2861vZ.q;
        C2690tZ c2690tZ = new C2690tZ();
        if (ah instanceof CH) {
            EnumC2776uZ enumC2776uZ = EnumC2776uZ.c;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ;
            c2690tZ.a(((CH) ah).a);
            return c2690tZ;
        }
        if (ah instanceof DH) {
            EnumC2776uZ enumC2776uZ2 = EnumC2776uZ.d;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ2;
            c2690tZ.a(((DH) ah).a);
            return c2690tZ;
        }
        if (ah instanceof LH) {
            EnumC2776uZ enumC2776uZ3 = EnumC2776uZ.e;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ3;
            c2690tZ.a(((LH) ah).a);
            return c2690tZ;
        }
        if (ah instanceof HH) {
            EnumC2776uZ enumC2776uZ4 = EnumC2776uZ.f;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ4;
            c2690tZ.a(((HH) ah).a);
            return c2690tZ;
        }
        if (ah instanceof KH) {
            EnumC2776uZ enumC2776uZ5 = EnumC2776uZ.g;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ5;
            c2690tZ.a(((KH) ah).a);
            return c2690tZ;
        }
        if (ah instanceof GH) {
            EnumC2776uZ enumC2776uZ6 = EnumC2776uZ.h;
            int i = c2690tZ.c;
            c2690tZ.d = enumC2776uZ6;
            float f = ((GH) ah).a;
            c2690tZ.c = i | 5;
            c2690tZ.f = f;
            return c2690tZ;
        }
        if (ah instanceof EH) {
            EnumC2776uZ enumC2776uZ7 = EnumC2776uZ.i;
            int i2 = c2690tZ.c;
            c2690tZ.d = enumC2776uZ7;
            double d = ((EH) ah).a;
            c2690tZ.c = i2 | 9;
            c2690tZ.g = d;
            return c2690tZ;
        }
        if (ah instanceof BH) {
            EnumC2776uZ enumC2776uZ8 = EnumC2776uZ.j;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ8;
            c2690tZ.a(((BH) ah).a ? 1L : 0L);
            return c2690tZ;
        }
        if (ah instanceof NH) {
            EnumC2776uZ enumC2776uZ9 = EnumC2776uZ.c;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ9;
            c2690tZ.a(((long) ((NH) ah).a) & 255);
            C2549rq c2549rq = AbstractC2805uq.O;
            c2549rq.getClass();
            int i3 = 1 << c2549rq.a;
            c2690tZ.c |= Fcntl.S_ISGID;
            c2690tZ.n = i3;
            return c2690tZ;
        }
        if (ah instanceof QH) {
            EnumC2776uZ enumC2776uZ10 = EnumC2776uZ.e;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ10;
            c2690tZ.a(((long) ((QH) ah).a) & 65535);
            C2549rq c2549rq2 = AbstractC2805uq.O;
            c2549rq2.getClass();
            int i4 = 1 << c2549rq2.a;
            c2690tZ.c |= Fcntl.S_ISGID;
            c2690tZ.n = i4;
            return c2690tZ;
        }
        if (ah instanceof OH) {
            EnumC2776uZ enumC2776uZ11 = EnumC2776uZ.f;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ11;
            c2690tZ.a(((long) ((OH) ah).a) & 4294967295L);
            C2549rq c2549rq3 = AbstractC2805uq.O;
            c2549rq3.getClass();
            int i5 = 1 << c2549rq3.a;
            c2690tZ.c |= Fcntl.S_ISGID;
            c2690tZ.n = i5;
            return c2690tZ;
        }
        if (ah instanceof PH) {
            EnumC2776uZ enumC2776uZ12 = EnumC2776uZ.g;
            c2690tZ.c |= 1;
            c2690tZ.d = enumC2776uZ12;
            c2690tZ.a(((PH) ah).a);
            C2549rq c2549rq4 = AbstractC2805uq.O;
            c2549rq4.getClass();
            int i6 = 1 << c2549rq4.a;
            c2690tZ.c |= Fcntl.S_ISGID;
            c2690tZ.n = i6;
            return c2690tZ;
        }
        if (ah instanceof MH) {
            EnumC2776uZ enumC2776uZ13 = EnumC2776uZ.k;
            c2690tZ.c = 1 | c2690tZ.c;
            c2690tZ.d = enumC2776uZ13;
            int iA = c1814jE.a(((MH) ah).a);
            c2690tZ.c |= 16;
            c2690tZ.h = iA;
            return c2690tZ;
        }
        if (ah instanceof IH) {
            EnumC2776uZ enumC2776uZ14 = EnumC2776uZ.l;
            c2690tZ.c = 1 | c2690tZ.c;
            c2690tZ.d = enumC2776uZ14;
            int iA2 = a(c1814jE, ((IH) ah).a);
            c2690tZ.c |= 32;
            c2690tZ.i = iA2;
            return c2690tZ;
        }
        if (ah instanceof C3184zH) {
            EnumC2776uZ enumC2776uZ15 = EnumC2776uZ.l;
            c2690tZ.c = 1 | c2690tZ.c;
            c2690tZ.d = enumC2776uZ15;
            C3184zH c3184zH = (C3184zH) ah;
            int iA3 = a(c1814jE, c3184zH.a);
            int i7 = c2690tZ.c;
            c2690tZ.i = iA3;
            int i8 = c3184zH.b;
            c2690tZ.c = i7 | 544;
            c2690tZ.m = i8;
            return c2690tZ;
        }
        if (ah instanceof FH) {
            EnumC2776uZ enumC2776uZ16 = EnumC2776uZ.m;
            c2690tZ.c = 1 | c2690tZ.c;
            c2690tZ.d = enumC2776uZ16;
            FH fh = (FH) ah;
            int iA4 = a(c1814jE, fh.a);
            c2690tZ.c |= 32;
            c2690tZ.i = iA4;
            int iA5 = c1814jE.a(fh.b);
            c2690tZ.c |= 64;
            c2690tZ.j = iA5;
            return c2690tZ;
        }
        if (ah instanceof C3099yH) {
            EnumC2776uZ enumC2776uZ17 = EnumC2776uZ.n;
            c2690tZ.c = 1 | c2690tZ.c;
            c2690tZ.d = enumC2776uZ17;
            C3117yZ c3117yZE = a(((C3099yH) ah).a, c1814jE).e();
            if (!c3117yZE.a()) {
                defpackage.bk.a();
                return null;
            }
            c2690tZ.k = c3117yZE;
            c2690tZ.c |= 128;
            return c2690tZ;
        }
        if (ah instanceof AH.a) {
            EnumC2776uZ enumC2776uZ18 = EnumC2776uZ.o;
            c2690tZ.c = 1 | c2690tZ.c;
            c2690tZ.d = enumC2776uZ18;
            Iterator<AH> it = ((AH.a) ah).a().iterator();
            while (it.hasNext()) {
                C2690tZ c2690tZA = a(it.next(), c1814jE);
                if ((c2690tZ.c & Fcntl.S_IRUSR) != 256) {
                    c2690tZ.l = new ArrayList(c2690tZ.l);
                    c2690tZ.c |= Fcntl.S_IRUSR;
                }
                List list = c2690tZ.l;
                C2861vZ c2861vZE = c2690tZA.e();
                if (!c2861vZE.a()) {
                    defpackage.bk.a();
                    return null;
                }
                list.add(c2861vZE);
            }
        }
        return c2690tZ;
    }

    public static final C3033xZ a(C3015xH c3015xH, C1814jE c1814jE) {
        KB.c(c3015xH, "<this>");
        KB.c(c1814jE, "strings");
        C3117yZ c3117yZ = C3117yZ.h;
        C3033xZ c3033xZ = new C3033xZ();
        int iA = a(c1814jE, c3015xH.b());
        c3033xZ.c |= 1;
        c3033xZ.d = iA;
        for (Map.Entry<String, AH> entry : c3015xH.a().entrySet()) {
            String key = entry.getKey();
            AH value = entry.getValue();
            C2947wZ c2947wZ = C2947wZ.h;
            C2519rZ c2519rZ = new C2519rZ();
            int iA2 = c1814jE.a(key);
            c2519rZ.c |= 1;
            c2519rZ.d = iA2;
            C2861vZ c2861vZE = a(value, c1814jE).e();
            if (c2861vZE.a()) {
                c2519rZ.e = c2861vZE;
                c2519rZ.c |= 2;
                if ((c3033xZ.c & 2) != 2) {
                    c3033xZ.e = new ArrayList(c3033xZ.e);
                    c3033xZ.c |= 2;
                }
                List list = c3033xZ.e;
                C2947wZ c2947wZE = c2519rZ.e();
                if (c2947wZE.a()) {
                    list.add(c2947wZE);
                } else {
                    defpackage.bk.a();
                    return null;
                }
            } else {
                defpackage.bk.a();
                return null;
            }
        }
        return c3033xZ;
    }

    public static final int a(C1814jE c1814jE, String str) {
        KB.c(c1814jE, "<this>");
        KB.c(str, TypeBlock.NAME_name);
        if (AbstractC1679hg0.a(str, ".")) {
            String strSubstring = str.substring(1);
            KB.b(strSubstring, "substring(...)");
            return c1814jE.a(strSubstring, true);
        }
        return c1814jE.a(str, false);
    }
}
