package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MD extends AbstractC0729Or {
    public static final MD p;
    public static final KD q = new KD();
    public final T7 b;
    public int c;
    public Object d;
    public InterfaceC3186zJ e;
    public List f;
    public int g;
    public InterfaceC3186zJ h;
    public InterfaceC3186zJ i;
    public List j;
    public int k;
    public List l;
    public int m;
    public byte n;
    public int o;

    static {
        MD md = new MD();
        p = md;
        md.d = XmlPullParser.NO_NAMESPACE;
        Hk0 hk0 = C3017xJ.c;
        md.e = hk0;
        List list = Collections.EMPTY_LIST;
        md.f = list;
        md.h = hk0;
        md.i = hk0;
        md.j = list;
        md.l = list;
    }

    public MD(C0638Ld c0638Ld) {
        this.g = -1;
        this.k = -1;
        this.m = -1;
        this.n = (byte) -1;
        this.o = -1;
        this.d = XmlPullParser.NO_NAMESPACE;
        Hk0 hk0 = C3017xJ.c;
        this.e = hk0;
        List list = Collections.EMPTY_LIST;
        this.f = list;
        this.h = hk0;
        this.i = hk0;
        this.j = list;
        this.l = list;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int i2 = c0638Ld.i();
                    switch (i2) {
                        case 0:
                            break;
                        case XmlPullParser.DOCDECL /* 10 */:
                            CL clB = c0638Ld.b();
                            this.c |= 1;
                            this.d = clB;
                            continue;
                        case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                            CL clB2 = c0638Ld.b();
                            if ((i & 2) != 2) {
                                this.e = new C3017xJ();
                                i |= 2;
                            }
                            this.e.a(clB2);
                            continue;
                        case AndroidSdkVersion.N /* 24 */:
                            if ((i & 4) != 4) {
                                this.f = new ArrayList();
                                i |= 4;
                            }
                            this.f.add(Integer.valueOf(c0638Ld.f()));
                            continue;
                        case AndroidSdkVersion.O /* 26 */:
                            int iB = c0638Ld.b(c0638Ld.f());
                            if ((i & 4) != 4 && c0638Ld.a() > 0) {
                                this.f = new ArrayList();
                                i |= 4;
                            }
                            while (c0638Ld.a() > 0) {
                                this.f.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB;
                            c0638Ld.j();
                            continue;
                        case AndroidSdkVersion.U /* 34 */:
                            CL clB3 = c0638Ld.b();
                            if ((i & 8) != 8) {
                                this.h = new C3017xJ();
                                i |= 8;
                            }
                            this.h.a(clB3);
                            continue;
                        case 42:
                            CL clB4 = c0638Ld.b();
                            if ((i & 16) != 16) {
                                this.i = new C3017xJ();
                                i |= 16;
                            }
                            this.i.a(clB4);
                            continue;
                        case 48:
                            if ((i & 64) != 64) {
                                this.l = new ArrayList();
                                i |= 64;
                            }
                            this.l.add(Integer.valueOf(c0638Ld.f()));
                            continue;
                        case 50:
                            int iB2 = c0638Ld.b(c0638Ld.f());
                            if ((i & 64) != 64 && c0638Ld.a() > 0) {
                                this.l = new ArrayList();
                                i |= 64;
                            }
                            while (c0638Ld.a() > 0) {
                                this.l.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB2;
                            c0638Ld.j();
                            continue;
                        case Fcntl.S_IRWXG /* 56 */:
                            if ((i & 32) != 32) {
                                this.j = new ArrayList();
                                i |= 32;
                            }
                            this.j.add(Integer.valueOf(c0638Ld.f()));
                            continue;
                        case 58:
                            int iB3 = c0638Ld.b(c0638Ld.f());
                            if ((i & 32) != 32 && c0638Ld.a() > 0) {
                                this.j = new ArrayList();
                                i |= 32;
                            }
                            while (c0638Ld.a() > 0) {
                                this.j.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB3;
                            c0638Ld.j();
                            continue;
                        default:
                            if (!c0638Ld.a(i2, c0767Qd)) {
                                break;
                            }
                            break;
                    }
                    z = true;
                } catch (Throwable th) {
                    if ((i & 2) == 2) {
                        this.e = this.e.f();
                    }
                    if ((i & 4) == 4) {
                        this.f = Collections.unmodifiableList(this.f);
                    }
                    if ((i & 8) == 8) {
                        this.h = this.h.f();
                    }
                    if ((i & 16) == 16) {
                        this.i = this.i.f();
                    }
                    if ((i & 64) == 64) {
                        this.l = Collections.unmodifiableList(this.l);
                    }
                    if ((i & 32) == 32) {
                        this.j = Collections.unmodifiableList(this.j);
                    }
                    try {
                        c0767Qd.a();
                    } catch (IOException unused) {
                    } finally {
                        this.b = r7.c();
                    }
                    throw th;
                }
            } catch (QB e) {
                e.b = this;
                throw e;
            } catch (IOException e2) {
                QB qb = new QB(e2.getMessage());
                qb.b = this;
                throw qb;
            }
        }
        if ((i & 2) == 2) {
            this.e = this.e.f();
        }
        if ((i & 4) == 4) {
            this.f = Collections.unmodifiableList(this.f);
        }
        if ((i & 8) == 8) {
            this.h = this.h.f();
        }
        if ((i & 16) == 16) {
            this.i = this.i.f();
        }
        if ((i & 64) == 64) {
            this.l = Collections.unmodifiableList(this.l);
        }
        if ((i & 32) == 32) {
            this.j = Collections.unmodifiableList(this.j);
        }
        try {
            c0767Qd.a();
        } catch (IOException unused2) {
        } finally {
            this.b = r7.c();
        }
    }

    @Override // com.android.tools.r8.internal.L0
    public final void a(C0767Qd c0767Qd) throws IOException {
        T7 t7A;
        c();
        if ((this.c & 1) == 1) {
            Object obj = this.d;
            if (obj instanceof String) {
                t7A = T7.a((String) obj);
                this.d = t7A;
            } else {
                t7A = (T7) obj;
            }
            c0767Qd.a(1, t7A);
        }
        for (int i = 0; i < this.e.size(); i++) {
            c0767Qd.a(2, this.e.g(i));
        }
        if (this.f.size() > 0) {
            c0767Qd.g(26);
            c0767Qd.g(this.g);
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            c0767Qd.d(((Integer) this.f.get(i2)).intValue());
        }
        for (int i3 = 0; i3 < this.h.size(); i3++) {
            c0767Qd.a(4, this.h.g(i3));
        }
        for (int i4 = 0; i4 < this.i.size(); i4++) {
            c0767Qd.a(5, this.i.g(i4));
        }
        if (this.l.size() > 0) {
            c0767Qd.g(50);
            c0767Qd.g(this.m);
        }
        for (int i5 = 0; i5 < this.l.size(); i5++) {
            c0767Qd.d(((Integer) this.l.get(i5)).intValue());
        }
        if (this.j.size() > 0) {
            c0767Qd.g(58);
            c0767Qd.g(this.k);
        }
        for (int i6 = 0; i6 < this.j.size(); i6++) {
            c0767Qd.d(((Integer) this.j.get(i6)).intValue());
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int size;
        InterfaceC3186zJ interfaceC3186zJ;
        List list;
        InterfaceC3186zJ interfaceC3186zJ2;
        InterfaceC3186zJ interfaceC3186zJ3;
        List list2;
        List list3;
        T7 t7A;
        int i = this.o;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.c & 1) == 1) {
            Object obj = this.d;
            if (obj instanceof String) {
                t7A = T7.a((String) obj);
                this.d = t7A;
            } else {
                t7A = (T7) obj;
            }
            size = t7A.size() + C0767Qd.b(t7A.size()) + C0767Qd.c(1);
        } else {
            size = 0;
        }
        int i3 = 0;
        int size2 = 0;
        while (true) {
            int size3 = this.e.size();
            interfaceC3186zJ = this.e;
            if (i3 >= size3) {
                break;
            }
            T7 t7G = interfaceC3186zJ.g(i3);
            size2 += t7G.size() + C0767Qd.b(t7G.size());
            i3++;
        }
        int size4 = interfaceC3186zJ.size() + size + size2;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int size5 = this.f.size();
            list = this.f;
            int iB = 10;
            if (i4 >= size5) {
                break;
            }
            int iIntValue = ((Integer) list.get(i4)).intValue();
            if (iIntValue >= 0) {
                iB = C0767Qd.b(iIntValue);
            }
            i5 += iB;
            i4++;
        }
        int iB2 = size4 + i5;
        if (!list.isEmpty()) {
            iB2 = iB2 + 1 + (i5 >= 0 ? C0767Qd.b(i5) : 10);
        }
        this.g = i5;
        int i6 = 0;
        int size6 = 0;
        while (true) {
            int size7 = this.h.size();
            interfaceC3186zJ2 = this.h;
            if (i6 >= size7) {
                break;
            }
            T7 t7G2 = interfaceC3186zJ2.g(i6);
            size6 += t7G2.size() + C0767Qd.b(t7G2.size());
            i6++;
        }
        int size8 = interfaceC3186zJ2.size() + iB2 + size6;
        int i7 = 0;
        int size9 = 0;
        while (true) {
            int size10 = this.i.size();
            interfaceC3186zJ3 = this.i;
            if (i7 >= size10) {
                break;
            }
            T7 t7G3 = interfaceC3186zJ3.g(i7);
            size9 += t7G3.size() + C0767Qd.b(t7G3.size());
            i7++;
        }
        int size11 = interfaceC3186zJ3.size() + size8 + size9;
        int i8 = 0;
        int iB3 = 0;
        while (true) {
            int size12 = this.l.size();
            list2 = this.l;
            if (i8 >= size12) {
                break;
            }
            int iIntValue2 = ((Integer) list2.get(i8)).intValue();
            iB3 += iIntValue2 >= 0 ? C0767Qd.b(iIntValue2) : 10;
            i8++;
        }
        int iB4 = size11 + iB3;
        if (!list2.isEmpty()) {
            iB4 = iB4 + 1 + (iB3 >= 0 ? C0767Qd.b(iB3) : 10);
        }
        this.m = iB3;
        int iB5 = 0;
        while (true) {
            int size13 = this.j.size();
            list3 = this.j;
            if (i2 >= size13) {
                break;
            }
            int iIntValue3 = ((Integer) list3.get(i2)).intValue();
            iB5 += iIntValue3 >= 0 ? C0767Qd.b(iIntValue3) : 10;
            i2++;
        }
        int iB6 = iB4 + iB5;
        if (!list3.isEmpty()) {
            iB6 = iB6 + 1 + (iB5 >= 0 ? C0767Qd.b(iB5) : 10);
        }
        this.k = iB5;
        int size14 = this.b.size() + iB6;
        this.o = size14;
        return size14;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new LD();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new LD().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.n;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.c & 1) == 1) {
            this.n = (byte) 1;
            return true;
        }
        this.n = (byte) 0;
        return false;
    }

    public MD() {
        this.g = -1;
        this.k = -1;
        this.m = -1;
        this.n = (byte) -1;
        this.o = -1;
        this.b = T7.b;
    }

    public MD(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.g = -1;
        this.k = -1;
        this.m = -1;
        this.n = (byte) -1;
        this.o = -1;
        this.b = abstractC0574Ir.b;
    }
}
