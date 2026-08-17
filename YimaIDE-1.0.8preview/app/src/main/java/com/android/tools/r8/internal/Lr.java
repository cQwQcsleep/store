package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Lr extends AbstractC0729Or {
    public final C0494Fp b;

    public Lr(AbstractC0600Jr abstractC0600Jr) {
        abstractC0600Jr.c.a();
        abstractC0600Jr.d = false;
        this.b = abstractC0600Jr.c;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003b  */
    public final boolean a(C0638Ld c0638Ld, C0767Qd c0767Qd, C0389Bo c0389Bo, int i) throws QB {
        boolean z;
        Object objC;
        L0 l0;
        C0494Fp c0494Fp = this.b;
        int i2 = i & 7;
        C0703Nr c0703Nr = (C0703Nr) c0389Bo.a.get(new C0363Ao(i >>> 3, b()));
        boolean z2 = false;
        if (c0703Nr == null) {
            z = false;
            z2 = true;
        } else {
            C0677Mr c0677Mr = c0703Nr.d;
            Mm0 mm0 = c0677Mr.c;
            C0494Fp c0494Fp2 = C0494Fp.d;
            if (i2 == mm0.c) {
                z = false;
            } else if (c0677Mr.d && mm0.a()) {
                Mm0 mm1 = c0703Nr.d.c;
                if (i2 == 2) {
                    z = true;
                } else {
                    z = false;
                    z2 = true;
                }
            } else {
                z = false;
                z2 = true;
            }
        }
        if (z2) {
            return c0638Ld.a(i, c0767Qd);
        }
        AbstractC0574Ir abstractC0574IrD = null;
        if (z) {
            int iB = c0638Ld.b(c0638Ld.f());
            if (c0703Nr.d.c != Mm0.h) {
                while (c0638Ld.a() > 0) {
                    c0494Fp.a(c0703Nr.d, C0494Fp.a(c0638Ld, c0703Nr.d.c));
                }
            } else if (c0638Ld.a() > 0) {
                c0638Ld.f();
                c0703Nr.d.getClass();
                throw null;
            }
            c0638Ld.h = iB;
            c0638Ld.j();
        } else {
            int iOrdinal = c0703Nr.d.c.b.ordinal();
            if (iOrdinal == 7) {
                c0638Ld.f();
                c0703Nr.d.getClass();
                throw null;
            }
            C0677Mr c0677Mr2 = c0703Nr.d;
            if (iOrdinal != 8) {
                objC = C0494Fp.a(c0638Ld, c0677Mr2.c);
            } else {
                if (!c0677Mr2.d && (l0 = (L0) c0494Fp.a.get(c0677Mr2)) != null) {
                    abstractC0574IrD = l0.e();
                }
                if (abstractC0574IrD == null) {
                    abstractC0574IrD = c0703Nr.c.d();
                }
                C0677Mr c0677Mr3 = c0703Nr.d;
                if (c0677Mr3.c == Mm0.f) {
                    int i3 = c0677Mr3.b;
                    int i4 = c0638Ld.i;
                    if (i4 >= 64) {
                        throw new QB("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
                    }
                    c0638Ld.i = i4 + 1;
                    abstractC0574IrD.a(c0638Ld, c0389Bo);
                    if (c0638Ld.f != ((i3 << 3) | 4)) {
                        throw new QB("Protocol message end-group tag did not match expected tag.");
                    }
                    c0638Ld.i--;
                } else {
                    int iF = c0638Ld.f();
                    if (c0638Ld.i >= 64) {
                        throw new QB("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
                    }
                    int iB2 = c0638Ld.b(iF);
                    c0638Ld.i++;
                    abstractC0574IrD.a(c0638Ld, c0389Bo);
                    if (c0638Ld.f != 0) {
                        throw new QB("Protocol message end-group tag did not match expected tag.");
                    }
                    c0638Ld.i--;
                    c0638Ld.h = iB2;
                    c0638Ld.j();
                }
                objC = abstractC0574IrD.c();
            }
            C0677Mr c0677Mr4 = c0703Nr.d;
            if (c0677Mr4.d) {
                c0494Fp.a(c0677Mr4, c0703Nr.b(objC));
            } else {
                c0494Fp.c(c0677Mr4, c0703Nr.b(objC));
            }
        }
        return true;
    }

    public final boolean f() {
        C0494Fp c0494Fp = this.b;
        int i = 0;
        while (true) {
            int size = c0494Fp.a.c.size();
            Dc0 dc0 = c0494Fp.a;
            if (i >= size) {
                Iterator it = (dc0.d.isEmpty() ? Jc0.b : dc0.d.entrySet()).iterator();
                while (it.hasNext()) {
                    if (!C0494Fp.a((Map.Entry) it.next())) {
                        break;
                    }
                }
                return true;
            }
            if (!C0494Fp.a((Map.Entry) dc0.c.get(i))) {
                break;
            }
            i++;
        }
        return false;
    }

    public final int g() {
        Dc0 dc0;
        C0494Fp c0494Fp = this.b;
        int i = 0;
        int iB = 0;
        while (true) {
            int size = c0494Fp.a.c.size();
            dc0 = c0494Fp.a;
            if (i >= size) {
                break;
            }
            Map.Entry entry = (Map.Entry) dc0.c.get(i);
            iB += C0494Fp.b((C0677Mr) entry.getKey(), entry.getValue());
            i++;
        }
        for (Map.Entry entry2 : dc0.d.isEmpty() ? Jc0.b : dc0.d.entrySet()) {
            iB += C0494Fp.b((C0677Mr) entry2.getKey(), entry2.getValue());
        }
        return iB;
    }

    public Lr() {
        this.b = new C0494Fp();
    }

    public final Object a(C0703Nr c0703Nr) {
        if (c0703Nr.a == b()) {
            C0494Fp c0494Fp = this.b;
            Object obj = c0494Fp.a.get(c0703Nr.d);
            if (obj == null) {
                return c0703Nr.b;
            }
            C0677Mr c0677Mr = c0703Nr.d;
            if (c0677Mr.d) {
                if (c0677Mr.c.b != Om0.j) {
                    return obj;
                }
                ArrayList arrayList = new ArrayList();
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    arrayList.add(c0703Nr.a(it.next()));
                }
                return arrayList;
            }
            return c0703Nr.a(obj);
        }
        w01.a("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        return null;
    }
}
