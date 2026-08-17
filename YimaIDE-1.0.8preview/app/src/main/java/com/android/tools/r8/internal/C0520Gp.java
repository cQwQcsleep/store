package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.f63;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0520Gp {
    public static final C0520Gp d = new C0520Gp(0);
    public final Rc0 a;
    public boolean b;
    public boolean c;

    public C0520Gp(int i) {
        int i2 = Rc0.h;
        this.a = new Ec0(0);
        d();
        d();
    }

    public static void a(AbstractC0793Rd abstractC0793Rd, Nm0 nm0, Object obj) throws C0741Pd {
        switch (nm0.ordinal()) {
            case 0:
                double dDoubleValue = ((Double) obj).doubleValue();
                abstractC0793Rd.getClass();
                abstractC0793Rd.b(Double.doubleToRawLongBits(dDoubleValue));
                break;
            case 1:
                float fFloatValue = ((Float) obj).floatValue();
                abstractC0793Rd.getClass();
                abstractC0793Rd.d(Float.floatToRawIntBits(fFloatValue));
                break;
            case 2:
                abstractC0793Rd.c(((Long) obj).longValue());
                break;
            case XmlPullParser.END_TAG /* 3 */:
                abstractC0793Rd.c(((Long) obj).longValue());
                break;
            case 4:
                abstractC0793Rd.e(((Integer) obj).intValue());
                break;
            case XmlPullParser.CDSECT /* 5 */:
                abstractC0793Rd.b(((Long) obj).longValue());
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                abstractC0793Rd.d(((Integer) obj).intValue());
                break;
            case 7:
                abstractC0793Rd.a(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                break;
            case 8:
                if (!(obj instanceof U7)) {
                    abstractC0793Rd.b((String) obj);
                } else {
                    abstractC0793Rd.b((U7) obj);
                }
                break;
            case 9:
                abstractC0793Rd.getClass();
                ((TN) obj).a(abstractC0793Rd);
                break;
            case XmlPullParser.DOCDECL /* 10 */:
                TN tn = (TN) obj;
                C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
                c0689Nd.getClass();
                c0689Nd.f(tn.c());
                tn.a(c0689Nd);
                break;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                if (!(obj instanceof U7)) {
                    byte[] bArr = (byte[]) obj;
                    abstractC0793Rd.getClass();
                    int length = bArr.length;
                    C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
                    c0689Nd2.f(length);
                    c0689Nd2.a(bArr, 0, length);
                } else {
                    abstractC0793Rd.b((U7) obj);
                }
                break;
            case 12:
                abstractC0793Rd.f(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof InterfaceC1046aB)) {
                    abstractC0793Rd.e(((Integer) obj).intValue());
                } else {
                    abstractC0793Rd.e(((InterfaceC1046aB) obj).a());
                }
                break;
            case 14:
                abstractC0793Rd.d(((Integer) obj).intValue());
                break;
            case 15:
                abstractC0793Rd.b(((Long) obj).longValue());
                break;
            case Fcntl.S_IWGRP /* 16 */:
                int iIntValue = ((Integer) obj).intValue();
                Logger logger = AbstractC0793Rd.a;
                abstractC0793Rd.f((iIntValue >> 31) ^ (iIntValue << 1));
                break;
            case 17:
                long jLongValue = ((Long) obj).longValue();
                Logger logger2 = AbstractC0793Rd.a;
                abstractC0793Rd.c((jLongValue >> 63) ^ (jLongValue << 1));
                break;
        }
    }

    public static boolean b(Nm0 nm0, Object obj) {
        Charset charset = AbstractC1556gB.a;
        obj.getClass();
        switch (nm0.b.ordinal()) {
            case 0:
                return obj instanceof Integer;
            case 1:
                return obj instanceof Long;
            case 2:
                return obj instanceof Float;
            case XmlPullParser.END_TAG /* 3 */:
                return obj instanceof Double;
            case 4:
                return obj instanceof Boolean;
            case XmlPullParser.CDSECT /* 5 */:
                return obj instanceof String;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return (obj instanceof U7) || (obj instanceof byte[]);
            case 7:
                return (obj instanceof Integer) || (obj instanceof InterfaceC1046aB);
            case 8:
                return obj instanceof TN;
            default:
                return false;
        }
    }

    public final void c(Map.Entry entry) {
        InterfaceC0468Ep interfaceC0468Ep = (InterfaceC0468Ep) entry.getKey();
        Object value = entry.getValue();
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        if (c1856jk.m()) {
            Object objA = a((InterfaceC0468Ep) c1856jk);
            if (objA == null) {
                objA = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objA).add(a(it.next()));
            }
            this.a.a(c1856jk, objA);
            return;
        }
        if (c1856jk.h() != Pm0.k) {
            this.a.a(c1856jk, a(value));
            return;
        }
        Object objA2 = a((InterfaceC0468Ep) c1856jk);
        if (objA2 == null) {
            this.a.a(c1856jk, a(value));
        } else {
            this.a.a(c1856jk, ((H0) ((TN) objA2).d()).b((J0) ((TN) value)).build());
        }
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final C0520Gp m11clone() {
        Rc0 rc0;
        C0520Gp c0520Gp = new C0520Gp();
        int i = 0;
        while (true) {
            int size = this.a.c.size();
            rc0 = this.a;
            if (i >= size) {
                break;
            }
            Map.Entry entry = (Map.Entry) rc0.c.get(i);
            c0520Gp.b((InterfaceC0468Ep) entry.getKey(), entry.getValue());
            i++;
        }
        for (Map.Entry entry2 : rc0.i()) {
            c0520Gp.b((InterfaceC0468Ep) entry2.getKey(), entry2.getValue());
        }
        c0520Gp.c = this.c;
        return c0520Gp;
    }

    public final void d() {
        if (this.b) {
            return;
        }
        this.a.k();
        this.b = true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0520Gp) {
            return this.a.equals(((C0520Gp) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public C0520Gp() {
        int i = Rc0.h;
        this.a = new Ec0(16);
    }

    public C0520Gp(Rc0 rc0) {
        this.a = rc0;
        d();
    }

    public final void b(InterfaceC0468Ep interfaceC0468Ep, Object obj) {
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        if (c1856jk.m()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    c(c1856jk, it.next());
                }
                obj = arrayList;
            } else {
                w01.a("Wrong object type used with protocol message reflection.");
                return;
            }
        } else {
            c(c1856jk, obj);
        }
        this.a.a(c1856jk, obj);
    }

    public final boolean b(InterfaceC0468Ep interfaceC0468Ep) {
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        if (!c1856jk.m()) {
            return this.a.get(c1856jk) != null;
        }
        w01.a("hasField() can only be called on non-repeated fields.");
        return false;
    }

    public static boolean b(Map.Entry entry) {
        C1856jk c1856jk = (C1856jk) ((InterfaceC0468Ep) entry.getKey());
        if (c1856jk.h() == Pm0.k) {
            if (c1856jk.m()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((TN) it.next()).a()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof TN) {
                    if (!((TN) value).a()) {
                        return false;
                    }
                } else {
                    if (value instanceof AbstractC2931wJ) {
                        return true;
                    }
                    w01.a("Wrong object type used with protocol message reflection.");
                    return false;
                }
            }
        }
        return true;
    }

    public final int b() {
        Rc0 rc0;
        int i = 0;
        int iA = 0;
        while (true) {
            int size = this.a.c.size();
            rc0 = this.a;
            if (i >= size) {
                break;
            }
            Map.Entry entry = (Map.Entry) rc0.c.get(i);
            iA += a((InterfaceC0468Ep) entry.getKey(), entry.getValue());
            i++;
        }
        for (Map.Entry entry2 : rc0.i()) {
            iA += a((InterfaceC0468Ep) entry2.getKey(), entry2.getValue());
        }
        return iA;
    }

    public final boolean c() {
        int i = 0;
        while (true) {
            int size = this.a.c.size();
            Rc0 rc0 = this.a;
            if (i < size) {
                if (!b((Map.Entry) rc0.c.get(i))) {
                    return false;
                }
                i++;
            } else {
                Iterator it = rc0.i().iterator();
                while (it.hasNext()) {
                    if (!b((Map.Entry) it.next())) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    public static void c(InterfaceC0468Ep interfaceC0468Ep, Object obj) {
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        Nm0[] nm0Arr = C1856jk.o;
        if (b(nm0Arr[c1856jk.h.ordinal()], obj)) {
            return;
        }
        drd.a("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(c1856jk.c.g), nm0Arr[c1856jk.h.ordinal()].b, obj.getClass().getName()});
    }

    public static Ec0 a(Rc0 rc0, boolean z) {
        int i = Rc0.h;
        Ec0 ec0 = new Ec0(16);
        for (int i2 = 0; i2 < rc0.c.size(); i2++) {
            Map.Entry entry = (Map.Entry) rc0.c.get(i2);
            InterfaceC0468Ep interfaceC0468Ep = (InterfaceC0468Ep) entry.getKey();
            Object value = entry.getValue();
            if (z && (value instanceof List)) {
                ec0.a(interfaceC0468Ep, new ArrayList((List) value));
            } else {
                ec0.a(interfaceC0468Ep, value);
            }
        }
        for (Map.Entry entry2 : rc0.i()) {
            InterfaceC0468Ep interfaceC0468Ep2 = (InterfaceC0468Ep) entry2.getKey();
            Object value2 = entry2.getValue();
            if (z && (value2 instanceof List)) {
                ec0.a(interfaceC0468Ep2, new ArrayList((List) value2));
            } else {
                ec0.a(interfaceC0468Ep2, value2);
            }
        }
        return ec0;
    }

    public final Object a(InterfaceC0468Ep interfaceC0468Ep) {
        Object obj = this.a.get(interfaceC0468Ep);
        return obj instanceof AbstractC2931wJ ? ((AbstractC2931wJ) obj).a() : obj;
    }

    public final void a(C0520Gp c0520Gp) {
        Rc0 rc0;
        int i = 0;
        while (true) {
            int size = c0520Gp.a.c.size();
            rc0 = c0520Gp.a;
            if (i >= size) {
                break;
            }
            c((Map.Entry) rc0.c.get(i));
            i++;
        }
        Iterator it = rc0.i().iterator();
        while (it.hasNext()) {
            c((Map.Entry) it.next());
        }
    }

    public static Object a(Object obj) {
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static void a(Map.Entry entry, AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        C1856jk c1856jk = (C1856jk) ((InterfaceC0468Ep) entry.getKey());
        if (c1856jk.h() == Pm0.k && !c1856jk.m() && !c1856jk.l()) {
            Object value = entry.getValue();
            int i = ((C1856jk) ((InterfaceC0468Ep) entry.getKey())).c.g;
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(1, 3);
            c0689Nd.c(2, 0);
            c0689Nd.f(i);
            c0689Nd.a(3, (TN) value);
            c0689Nd.c(1, 4);
            return;
        }
        a(c1856jk, entry.getValue(), abstractC0793Rd);
    }

    public final Map a() {
        boolean z = this.c;
        Rc0 rc0 = this.a;
        if (!z) {
            return rc0.e ? rc0 : Collections.unmodifiableMap(rc0);
        }
        Ec0 ec0A = a(rc0, false);
        if (this.a.e) {
            ec0A.k();
        }
        return ec0A;
    }

    public static void a(InterfaceC0468Ep interfaceC0468Ep, Object obj, AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        Nm0 nm0 = C1856jk.o[c1856jk.h.ordinal()];
        int i = c1856jk.c.g;
        if (c1856jk.m()) {
            List list = (List) obj;
            if (c1856jk.l()) {
                abstractC0793Rd.c(i, 2);
                Iterator it = list.iterator();
                int iA = 0;
                while (it.hasNext()) {
                    iA += a(nm0, it.next());
                }
                abstractC0793Rd.f(iA);
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    a(abstractC0793Rd, nm0, it2.next());
                }
                return;
            }
            for (Object obj2 : list) {
                if (nm0 == Nm0.d) {
                    abstractC0793Rd.c(i, 3);
                    ((TN) obj2).a(abstractC0793Rd);
                    abstractC0793Rd.c(i, 4);
                } else {
                    abstractC0793Rd.c(i, nm0.c);
                    a(abstractC0793Rd, nm0, obj2);
                }
            }
            return;
        }
        if (nm0 == Nm0.d) {
            abstractC0793Rd.c(i, 3);
            ((TN) obj).a(abstractC0793Rd);
            abstractC0793Rd.c(i, 4);
        } else {
            abstractC0793Rd.c(i, nm0.c);
            a(abstractC0793Rd, nm0, obj);
        }
    }

    public static int a(Map.Entry entry) {
        InterfaceC0468Ep interfaceC0468Ep = (InterfaceC0468Ep) entry.getKey();
        Object value = entry.getValue();
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        if (c1856jk.h() == Pm0.k && !c1856jk.m() && !c1856jk.l()) {
            return AbstractC1292d60.a((TN) value, AbstractC0793Rd.b(3), AbstractC0484Ff.a(((C1856jk) ((InterfaceC0468Ep) entry.getKey())).c.g, AbstractC0793Rd.b(2), AbstractC0793Rd.b(1) * 2));
        }
        return a(c1856jk, value);
    }

    public static int a(Nm0 nm0, Object obj) {
        switch (nm0.ordinal()) {
            case 0:
                ((Double) obj).getClass();
                Logger logger = AbstractC0793Rd.a;
                return 8;
            case 1:
                ((Float) obj).getClass();
                Logger logger2 = AbstractC0793Rd.a;
                return 4;
            case 2:
                return AbstractC0793Rd.a(((Long) obj).longValue());
            case XmlPullParser.END_TAG /* 3 */:
                return AbstractC0793Rd.a(((Long) obj).longValue());
            case 4:
                int iIntValue = ((Integer) obj).intValue();
                if (iIntValue >= 0) {
                    return AbstractC0793Rd.c(iIntValue);
                }
                Logger logger3 = AbstractC0793Rd.a;
                return 10;
            case XmlPullParser.CDSECT /* 5 */:
                ((Long) obj).getClass();
                Logger logger4 = AbstractC0793Rd.a;
                return 8;
            case XmlPullParser.ENTITY_REF /* 6 */:
                ((Integer) obj).getClass();
                Logger logger5 = AbstractC0793Rd.a;
                return 4;
            case 7:
                ((Boolean) obj).getClass();
                Logger logger6 = AbstractC0793Rd.a;
                return 1;
            case 8:
                if (obj instanceof U7) {
                    return AbstractC0793Rd.a((U7) obj);
                }
                return AbstractC0793Rd.a((String) obj);
            case 9:
                Logger logger7 = AbstractC0793Rd.a;
                return ((TN) obj).c();
            case XmlPullParser.DOCDECL /* 10 */:
                return AbstractC0793Rd.a((TN) obj);
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                if (obj instanceof U7) {
                    return AbstractC0793Rd.a((U7) obj);
                }
                Logger logger8 = AbstractC0793Rd.a;
                int length = ((byte[]) obj).length;
                return AbstractC0793Rd.c(length) + length;
            case 12:
                return AbstractC0793Rd.c(((Integer) obj).intValue());
            case 13:
                if (obj instanceof InterfaceC1046aB) {
                    return AbstractC0793Rd.a(((InterfaceC1046aB) obj).a());
                }
                return AbstractC0793Rd.a(((Integer) obj).intValue());
            case 14:
                ((Integer) obj).getClass();
                Logger logger9 = AbstractC0793Rd.a;
                return 4;
            case 15:
                ((Long) obj).getClass();
                Logger logger10 = AbstractC0793Rd.a;
                return 8;
            case Fcntl.S_IWGRP /* 16 */:
                int iIntValue2 = ((Integer) obj).intValue();
                return AbstractC0793Rd.c((iIntValue2 >> 31) ^ (iIntValue2 << 1));
            case 17:
                long jLongValue = ((Long) obj).longValue();
                return AbstractC0793Rd.a((jLongValue >> 63) ^ (jLongValue << 1));
            default:
                f63.a("There is no way to get here, but the compiler thinks otherwise.");
                return 0;
        }
    }

    public static int a(InterfaceC0468Ep interfaceC0468Ep, Object obj) {
        int iB;
        int iA;
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        Nm0 nm0 = C1856jk.o[c1856jk.h.ordinal()];
        int i = c1856jk.c.g;
        if (c1856jk.m()) {
            int iA2 = 0;
            if (c1856jk.l()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iA2 += a(nm0, it.next());
                }
                iB = AbstractC0793Rd.b(i) + iA2;
                iA = AbstractC0793Rd.c(iA2);
            } else {
                for (Object obj2 : (List) obj) {
                    int iB2 = AbstractC0793Rd.b(i);
                    if (nm0 == Nm0.d) {
                        iB2 *= 2;
                    }
                    iA2 += a(nm0, obj2) + iB2;
                }
                return iA2;
            }
        } else {
            iB = AbstractC0793Rd.b(i);
            if (nm0 == Nm0.d) {
                iB *= 2;
            }
            iA = a(nm0, obj);
        }
        return iA + iB;
    }
}
