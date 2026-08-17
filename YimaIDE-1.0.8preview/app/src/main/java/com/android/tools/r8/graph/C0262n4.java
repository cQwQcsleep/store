package com.android.tools.r8.graph;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0262n4;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.AbstractC1638h9;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.AbstractC2647t1;
import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.B8;
import com.android.tools.r8.internal.BX;
import com.android.tools.r8.internal.C0479Fa;
import com.android.tools.r8.internal.C0497Fs;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0842Ta;
import com.android.tools.r8.internal.C0919Vz;
import com.android.tools.r8.internal.C0920Wa;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C0946Xa;
import com.android.tools.r8.internal.C0972Ya;
import com.android.tools.r8.internal.C1129b9;
import com.android.tools.r8.internal.C1157ba;
import com.android.tools.r8.internal.C1211c8;
import com.android.tools.r8.internal.C1213c9;
import com.android.tools.r8.internal.C1490fV;
import com.android.tools.r8.internal.C1687hk0;
import com.android.tools.r8.internal.C1724i9;
import com.android.tools.r8.internal.C1752ia;
import com.android.tools.r8.internal.C1857jk0;
import com.android.tools.r8.internal.C2190ng;
import com.android.tools.r8.internal.C2322p9;
import com.android.tools.r8.internal.C2492r9;
import com.android.tools.r8.internal.C2663t9;
import com.android.tools.r8.internal.C2749u9;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2948wa;
import com.android.tools.r8.internal.C3004x8;
import com.android.tools.r8.internal.C3034xa;
import com.android.tools.r8.internal.C3050xi0;
import com.android.tools.r8.internal.C3088y8;
import com.android.tools.r8.internal.C3090y9;
import com.android.tools.r8.internal.C3173z8;
import com.android.tools.r8.internal.El0;
import com.android.tools.r8.internal.EnumC2211nu;
import com.android.tools.r8.internal.EnumC2509rP;
import com.android.tools.r8.internal.G9;
import com.android.tools.r8.internal.H9;
import com.android.tools.r8.internal.I9;
import com.android.tools.r8.internal.InterfaceC0425Cy;
import com.android.tools.r8.internal.InterfaceC0943Wx;
import com.android.tools.r8.internal.InterfaceC1101ar;
import com.android.tools.r8.internal.InterfaceC2942wU;
import com.android.tools.r8.internal.K8;
import com.android.tools.r8.internal.K9;
import com.android.tools.r8.internal.KN;
import com.android.tools.r8.internal.P9;
import com.android.tools.r8.internal.R9;
import com.android.tools.r8.internal.S8;
import com.android.tools.r8.internal.S9;
import com.android.tools.r8.internal.T9;
import com.android.tools.r8.internal.V8;
import com.android.tools.r8.internal.V9;
import com.android.tools.r8.internal.W8;
import com.android.tools.r8.internal.W9;
import com.android.tools.r8.internal.WI;
import com.android.tools.r8.internal.X8;
import com.android.tools.r8.internal.X9;
import com.android.tools.r8.internal.XO;
import com.android.tools.r8.internal.Y8;
import com.android.tools.r8.internal.Z8;
import com.android.tools.r8.internal.Z9;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.MethodPosition;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.position.TextPosition;
import com.android.tools.r8.position.TextRange;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.utils.StringDiagnostic;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.exe;
import defpackage.gk0;
import defpackage.n33;
import defpackage.x0g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Supplier;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.graph.n4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0262n4 extends XO {
    public static final /* synthetic */ boolean w = true;
    public final C0178b4 c;
    public final B1 d;
    public final C0241k4 e;
    public int f;
    public int g;
    public boolean h;
    public ArrayList i;
    public ArrayList j;
    public ArrayList k;
    public final HashMap l;
    public K9 m;
    public C0919Vz n;
    public IdentityHashMap o;
    public IdentityHashMap p;
    public final C0269o4 q;
    public final C0322w2 r;
    public final Origin s;
    public int t;
    public int u;
    public final Supplier v;

    public C0262n4(C0178b4 c0178b4, C0322w2 c0322w2, C0269o4 c0269o4, Origin origin, C0241k4 c0241k4, Supplier supplier) {
        super(589824, null);
        this.l = new HashMap();
        this.t = Integer.MAX_VALUE;
        this.u = -1;
        this.e = c0241k4;
        this.c = c0178b4;
        this.d = c0178b4.a.a;
        this.q = c0269o4;
        this.r = c0322w2;
        this.s = origin;
        this.v = supplier;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i) {
        if (i == 190) {
            a((AbstractC3175z9) new C3088y8());
            return;
        }
        if (i == 191) {
            a((AbstractC3175z9) new C0946Xa());
            return;
        }
        if (i == 194) {
            a((AbstractC3175z9) new S9(EnumC2509rP.b));
            return;
        }
        if (i == 195) {
            a((AbstractC3175z9) new S9(EnumC2509rP.c));
            return;
        }
        switch (i) {
            case 0:
                a((AbstractC3175z9) new Z9());
                break;
            case 1:
                a((AbstractC3175z9) new Z8());
                break;
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
            case XmlPullParser.CDSECT /* 5 */:
            case XmlPullParser.ENTITY_REF /* 6 */:
            case 7:
            case 8:
                a((AbstractC3175z9) new C1129b9(i - 3, El0.c));
                break;
            case 9:
            case XmlPullParser.DOCDECL /* 10 */:
                a((AbstractC3175z9) new C1129b9(i - 9, El0.e));
                break;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
            case 12:
            case 13:
                a((AbstractC3175z9) new C1129b9(Float.floatToRawIntBits(i - 11), El0.d));
                break;
            case 14:
            case 15:
                a((AbstractC3175z9) new C1129b9(Double.doubleToRawLongBits(i - 14), El0.f));
                break;
            default:
                switch (i) {
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                        a((AbstractC3175z9) new C3173z8(b(i)));
                        break;
                    default:
                        switch (i) {
                            case 79:
                            case 80:
                            case 81:
                            case 82:
                            case 83:
                            case 84:
                            case 85:
                            case 86:
                                a((AbstractC3175z9) new B8(b(i)));
                                break;
                            case 87:
                            case 88:
                            case 89:
                            case 90:
                            case 91:
                            case 92:
                            case 93:
                            case 94:
                            case 95:
                                a((AbstractC3175z9) C0479Fa.a(i));
                                break;
                            case 96:
                            case 97:
                            case 98:
                            case 99:
                            case 100:
                            case 101:
                            case 102:
                            case 103:
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                            case 108:
                            case 109:
                            case 110:
                            case 111:
                            case 112:
                            case 113:
                            case 114:
                            case 115:
                                a((AbstractC3175z9) C3004x8.a(i));
                                break;
                            case 116:
                            case 117:
                            case 118:
                            case 119:
                                a((AbstractC3175z9) V9.a(i));
                                break;
                            case 120:
                            case 121:
                            case 122:
                            case 123:
                            case 124:
                            case 125:
                            case 126:
                            case 127:
                            case 128:
                            case 129:
                            case 130:
                            case 131:
                                a((AbstractC3175z9) R9.a(i));
                                break;
                            default:
                                switch (i) {
                                    case 133:
                                    case 134:
                                    case 135:
                                    case 136:
                                    case 137:
                                    case 138:
                                    case 139:
                                    case 140:
                                    case 141:
                                    case 142:
                                    case 143:
                                    case 144:
                                    case 145:
                                    case 146:
                                    case 147:
                                        a((AbstractC3175z9) C1157ba.a(i));
                                        break;
                                    case 148:
                                    case 149:
                                    case 150:
                                    case 151:
                                    case 152:
                                        a((AbstractC3175z9) S8.a(i));
                                        break;
                                    default:
                                        switch (i) {
                                            case 172:
                                                a((AbstractC3175z9) new C2948wa(El0.c));
                                                break;
                                            case 173:
                                                a((AbstractC3175z9) new C2948wa(El0.e));
                                                break;
                                            case 174:
                                                a((AbstractC3175z9) new C2948wa(El0.d));
                                                break;
                                            case 175:
                                                a((AbstractC3175z9) new C2948wa(El0.f));
                                                break;
                                            case 176:
                                                a((AbstractC3175z9) new C2948wa(El0.b));
                                                break;
                                            case 177:
                                                a((AbstractC3175z9) new C3034xa());
                                                break;
                                            default:
                                                x0g.a("Unknown instruction");
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public final I2 b(Object obj) {
        if (!w && (obj == null || obj == 0)) {
            x1f.a();
            return null;
        }
        if (obj == 1) {
            return this.d.B1;
        }
        if (obj == 2) {
            return this.d.A1;
        }
        if (obj == 4) {
            return this.d.C1;
        }
        if (obj == 3) {
            return this.d.z1;
        }
        if (obj == 5) {
            return B1.e6;
        }
        if (obj instanceof String) {
            return a((String) obj);
        }
        gk0.a("Unexpected ASM type: ", obj);
        return null;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c() {
        if (this.i == null) {
            if (!w && (this.j != null || this.k != null || this.o != null)) {
                x1f.a();
                return;
            }
            Origin origin = this.s;
            MethodReference methodReferenceZ0 = this.r.z0();
            int i = this.t;
            throw new C0613Ke(origin, MethodPosition.create(methodReferenceZ0, i != Integer.MAX_VALUE ? i == this.u ? new TextPosition(0L, this.t, -1) : new TextRange(new TextPosition(0L, this.t, -1), new TextPosition(0L, this.u, -1)) : Position.UNKNOWN));
        }
        Iterator itO = this.n.o(0);
        while (itO.hasNext()) {
            int iQ = ((com.android.tools.r8.internal.W) itO).q();
            AbstractC3175z9 abstractC3175z9 = (AbstractC3175z9) this.i.get(iQ);
            if (!w && !abstractC3175z9.G()) {
                x1f.a();
                return;
            }
            C1724i9 c1724i9H = abstractC3175z9.h();
            C1724i9.a aVarT = C1724i9.T();
            InterfaceC0425Cy interfaceC0425Cy = c1724i9H.c;
            aVarT.a = interfaceC0425Cy;
            InterfaceC2942wU it = interfaceC0425Cy.b().iterator();
            while (it.hasNext()) {
                InterfaceC0943Wx interfaceC0943Wx = (InterfaceC0943Wx) it.next();
                InterfaceC1101ar interfaceC1101ar = (InterfaceC1101ar) interfaceC0943Wx.getValue();
                if (interfaceC1101ar.t() && interfaceC1101ar.w() == null) {
                    C1687hk0 c1687hk0U = interfaceC1101ar.u();
                    K9 k9 = c1687hk0U.c;
                    W9 w9 = (W9) this.p.get(k9);
                    if (w9 != null) {
                        c1687hk0U = new C1687hk0(w9.getType(), k9);
                    }
                    interfaceC0943Wx.setValue(c1687hk0U);
                }
            }
            for (BX bx : c1724i9H.d) {
                if (bx.t() && bx.w() == null) {
                    C1687hk0 c1687hk0U2 = bx.u();
                    K9 k10 = c1687hk0U2.c;
                    W9 w10 = (W9) this.p.get(k10);
                    if (w10 != null) {
                        c1687hk0U2 = new C1687hk0(w10.getType(), k10);
                    }
                    aVarT.a((BX) c1687hk0U2);
                } else {
                    aVarT.a(bx);
                }
            }
            this.i.set(iQ, aVarT.a());
        }
        C0269o4 c0269o4 = this.q;
        I2 i2 = this.r.f;
        int i3 = this.h ? Integer.MAX_VALUE : this.f;
        int i4 = this.g;
        ArrayList arrayList = this.i;
        ArrayList arrayList2 = this.j;
        ArrayList arrayList3 = this.k;
        int i5 = this.t;
        G g = new G(i2, i3, i4, arrayList, arrayList2, arrayList3, i5 == Integer.MAX_VALUE ? Position.UNKNOWN : i5 == this.u ? new TextPosition(0L, this.t, -1) : new TextRange(new TextPosition(0L, this.t, -1), new TextPosition(0L, this.u, -1)), C1211c8.b);
        boolean z = C0269o4.j;
        if (!z && c0269o4.g != null) {
            x1f.a();
            return;
        }
        if (!z && c0269o4.h == null) {
            x1f.a();
            return;
        }
        c0269o4.g = g;
        c0269o4.h = null;
        c0269o4.f = null;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0016  */
    /* JADX WARN: Code duplicated, block: B:11:0x0019  */
    /* JADX WARN: Code duplicated, block: B:12:0x001c  */
    /* JADX WARN: Code duplicated, block: B:15:0x0022  */
    /* JADX WARN: Code duplicated, block: B:8:0x0010  */
    /* JADX WARN: Code duplicated, block: B:9:0x0013  */
    @Override // com.android.tools.r8.internal.XO
    public final void d(int i, int i2) {
        El0 el0;
        if (i == 169) {
            a((AbstractC3175z9) new I9(i2));
            return;
        }
        switch (i) {
            case AndroidSdkVersion.LOLLIPOP /* 21 */:
                el0 = El0.c;
                if (21 > i && i <= 25) {
                    a((AbstractC3175z9) new P9(el0, i2));
                } else {
                    a((AbstractC3175z9) new C0842Ta(el0, i2));
                }
                break;
            case 22:
                el0 = El0.e;
                if (21 > i) {
                }
                a((AbstractC3175z9) new C0842Ta(el0, i2));
                break;
            case AndroidSdkVersion.M /* 23 */:
                el0 = El0.d;
                if (21 > i) {
                }
                a((AbstractC3175z9) new C0842Ta(el0, i2));
                break;
            case AndroidSdkVersion.N /* 24 */:
                el0 = El0.f;
                if (21 > i) {
                }
                a((AbstractC3175z9) new C0842Ta(el0, i2));
                break;
            case 25:
                el0 = El0.b;
                if (21 > i) {
                }
                a((AbstractC3175z9) new C0842Ta(el0, i2));
                break;
            default:
                switch (i) {
                    case 54:
                        el0 = El0.c;
                        break;
                    case 55:
                        el0 = El0.e;
                        break;
                    case Fcntl.S_IRWXG /* 56 */:
                        el0 = El0.d;
                        break;
                    case 57:
                        el0 = El0.f;
                        break;
                    case 58:
                        el0 = El0.b;
                        break;
                    default:
                        exe.a("Unexpected VarInsn opcode: ", i);
                        break;
                }
                if (21 > i) {
                }
                a((AbstractC3175z9) new C0842Ta(el0, i2));
                break;
        }
    }

    public final K9 b(WI wi) {
        return (K9) this.o.computeIfAbsent(wi, new Function() { // from class: yph
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0262n4.c((WI) obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b() {
        this.f = 0;
        this.g = 0;
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.m = null;
        this.n = new C0919Vz(16);
        this.o = new IdentityHashMap();
        this.p = new IdentityHashMap();
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0013  */
    /* JADX WARN: Code duplicated, block: B:12:0x0016  */
    /* JADX WARN: Code duplicated, block: B:14:0x0019  */
    /* JADX WARN: Code duplicated, block: B:16:0x001c  */
    /* JADX WARN: Code duplicated, block: B:18:0x001f  */
    /* JADX WARN: Code duplicated, block: B:20:0x0022  */
    /* JADX WARN: Code duplicated, block: B:6:0x000d  */
    /* JADX WARN: Code duplicated, block: B:8:0x0010  */
    public static KN b(int i) {
        switch (i) {
            case 46:
                return KN.f;
            case 47:
                return KN.h;
            case 48:
                return KN.g;
            case 49:
                return KN.i;
            case 50:
                return KN.b;
            case 51:
                return KN.c;
            case 52:
                return KN.d;
            case 53:
                return KN.e;
            default:
                switch (i) {
                    case 79:
                        return KN.f;
                    case 80:
                        return KN.h;
                    case 81:
                        return KN.g;
                    case 82:
                        return KN.i;
                    case 83:
                        return KN.b;
                    case 84:
                        return KN.c;
                    case 85:
                        return KN.d;
                    case 86:
                        return KN.e;
                    default:
                        exe.a("Unexpected array opcode ", i);
                        return null;
                }
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, int i2) {
        if (i == 16 || i == 17) {
            a((AbstractC3175z9) new C1129b9(i2, El0.c));
        } else if (i == 188) {
            B1 b1 = this.d;
            a((AbstractC3175z9) new X9(b1.a(1, a(i2, b1))));
        } else {
            exe.a("Unexpected int opcode ", i);
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, WI wi) {
        this.t = Math.min(i, this.t);
        this.u = Math.max(i, this.u);
        if (this.e.a) {
            a((AbstractC3175z9) new C1752ia(b(wi), AbstractC2004lX.b.s().a(i).a(this.r).a()));
        }
    }

    public final void a(AbstractC3175z9 abstractC3175z9) {
        this.i.add(abstractC3175z9);
        if (abstractC3175z9.G() || (abstractC3175z9 instanceof C1752ia)) {
            return;
        }
        this.m = null;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        if (!w && i != -1) {
            x1f.a();
            return;
        }
        C1724i9.a aVarT = C1724i9.T();
        for (int i4 = 0; i4 < i2; i4++) {
            aVarT.a(a(objArr[i4], aVarT));
        }
        aVarT.a(i3);
        for (int i5 = 0; i5 < i3; i5++) {
            InterfaceC1101ar interfaceC1101arA = a(objArr2[i5], aVarT);
            if (interfaceC1101arA.C()) {
                aVarT.a(interfaceC1101arA.y());
            } else {
                this.c.a.i.warning(new StringDiagnostic("Unexpected frame with imprecise value on stack", this.s));
                return;
            }
        }
        if (aVarT.c) {
            this.n.add(this.i.size());
        }
        a((AbstractC3175z9) aVarT.a());
    }

    public final InterfaceC1101ar a(Object obj, C1724i9.a aVar) {
        if (obj instanceof WI) {
            K9 k9B = b((WI) obj);
            W9 w9 = (W9) this.p.get(k9B);
            if (w9 != null) {
                I2 type = w9.getType();
                int i = InterfaceC1101ar.a;
                return new C1687hk0(type, k9B);
            }
            aVar.c = true;
            int i2 = InterfaceC1101ar.a;
            return new C1687hk0(null, k9B);
        }
        if (obj == 6) {
            int i3 = InterfaceC1101ar.a;
            return C1857jk0.c;
        }
        if (obj != null && obj != 0) {
            return InterfaceC1101ar.a(b(obj));
        }
        int i4 = InterfaceC1101ar.a;
        return C1490fV.c;
    }

    public final I2 a(String str) {
        if (w || str.indexOf(46) == -1) {
            return this.d.e(C3050xi0.e(str).b());
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ C0230j0 a(C0230j0 c0230j0, C0230j0 c0230j1) {
        return c0230j0;
    }

    public static I2 a(int i, B1 b1) {
        switch (i) {
            case 4:
                return b1.w1;
            case XmlPullParser.CDSECT /* 5 */:
                return b1.y1;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return b1.A1;
            case 7:
                return b1.z1;
            case 8:
                return b1.x1;
            case 9:
                return b1.D1;
            case XmlPullParser.DOCDECL /* 10 */:
                return b1.B1;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return b1.C1;
            default:
                exe.a("Unexpected array-type code ", i);
                return null;
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3) {
        C0245l1 c0245l1A = this.d.a(a(str), this.d.e(str3), str2);
        a((AbstractC3175z9) AbstractC1638h9.a(i, c0245l1A, c0245l1A));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3, boolean z) {
        C0178b4 c0178b4 = this.c;
        C0322w2 c0322w2A = c0178b4.a(c0178b4.f(str), str2, str3);
        if (!this.c.a.a.a(c0322w2A)) {
            a((AbstractC3175z9) new G9(i, c0322w2A, z));
        } else {
            n33.a("Invalid input code with a call to <clinit>");
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, C0497Fs c0497Fs, Object... objArr) {
        a((AbstractC3175z9) new H9(D0.a(this.c, this.r.f, str, str2, c0497Fs, objArr)));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, WI wi) {
        El0 el0;
        K9 k9B = b(wi);
        if (153 <= i && i <= 166) {
            if (i <= 158) {
                a((AbstractC3175z9) new C2492r9(c(i), El0.c, k9B));
                return;
            }
            if (i <= 164) {
                el0 = El0.c;
            } else {
                el0 = El0.b;
            }
            a((AbstractC3175z9) new C2663t9(c(i), el0, k9B));
            return;
        }
        if (i == 167) {
            a((AbstractC3175z9) new C2322p9(k9B));
            return;
        }
        if (i == 168) {
            throw new C0255m4();
        }
        if (i != 198 && i != 199) {
            exe.a("Unexpected JumpInsn opcode: ", i);
        } else {
            a((AbstractC3175z9) new C2492r9(i == 198 ? EnumC2211nu.b : EnumC2211nu.g, El0.b, k9B));
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi) {
        K9 k9B = b(wi);
        a((AbstractC3175z9) k9B);
        this.m = k9B;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(Object obj) {
        if (obj instanceof C3050xi0) {
            C3050xi0 c3050xi0 = (C3050xi0) obj;
            if (c3050xi0.c() == 11) {
                a((AbstractC3175z9) new Y8(this.c.c(c3050xi0.b())));
                return;
            } else {
                a((AbstractC3175z9) new V8(this.d.e(c3050xi0.b())));
                return;
            }
        }
        if (obj instanceof String) {
            a((AbstractC3175z9) new C1213c9(this.d.c((String) obj)));
            return;
        }
        if (obj instanceof Long) {
            a((AbstractC3175z9) new C1129b9(((Long) obj).longValue(), El0.e));
            return;
        }
        if (obj instanceof Double) {
            a((AbstractC3175z9) new C1129b9(Double.doubleToRawLongBits(((Double) obj).doubleValue()), El0.f));
            return;
        }
        if (obj instanceof Integer) {
            a((AbstractC3175z9) new C1129b9(((Integer) obj).intValue(), El0.c));
            return;
        }
        if (obj instanceof Float) {
            a((AbstractC3175z9) new C1129b9(Float.floatToRawIntBits(((Float) obj).floatValue()), El0.d));
            return;
        }
        if (obj instanceof C0497Fs) {
            a((AbstractC3175z9) new X8(C0336y2.a((C0497Fs) obj, this.c, this.r.f)));
            return;
        }
        if (obj instanceof C2190ng) {
            AbstractC2647t1 abstractC2647t1 = (AbstractC2647t1) this.v.get();
            int iIntValue = ((Integer) abstractC2647t1.getOrDefault(obj, -1)).intValue();
            if (iIntValue == -1) {
                iIntValue = abstractC2647t1.size();
                abstractC2647t1.b(iIntValue, (C2190ng) obj);
            }
            a((AbstractC3175z9) W8.a(iIntValue, (C2190ng) obj, this.c, this.r.f));
            return;
        }
        throw new C0613Ke("Unsupported constant: " + obj.toString());
    }

    public static /* synthetic */ K9 c(WI wi) {
        return new K9();
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, String str) {
        I2 i2E = this.d.e(C3050xi0.e(str).b());
        if (i == 187) {
            W9 w9 = new W9(i2E, this.m);
            if (w9.U()) {
                this.p.put(w9.T(), w9);
            }
            a((AbstractC3175z9) w9);
            return;
        }
        if (i == 189) {
            a(new X9(this.d.a(1, i2E)));
            return;
        }
        if (i == 192) {
            a(new K8(i2E));
        } else if (i == 193) {
            a(new C3090y9(i2E));
        } else {
            exe.a("Unexpected TypeInsn opcode: ", i);
        }
    }

    public static EnumC2211nu c(int i) {
        switch (i) {
            case 153:
            case 159:
            case 165:
                return EnumC2211nu.b;
            case 154:
            case 160:
            case 166:
                return EnumC2211nu.g;
            case 155:
            case 161:
                return EnumC2211nu.f;
            case 156:
            case 162:
                return EnumC2211nu.c;
            case 157:
            case 163:
                return EnumC2211nu.d;
            case 158:
            case 164:
                return EnumC2211nu.e;
            default:
                exe.a("Unexpected If instruction opcode: ", i);
                return null;
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2) {
        a((AbstractC3175z9) new C2749u9(i, i2));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, WI wi, WI... wiArr) {
        if (!w && i2 != (wiArr.length + i) - 1) {
            x1f.a();
            return;
        }
        ArrayList arrayList = new ArrayList(wiArr.length);
        for (WI wi2 : wiArr) {
            arrayList.add(b(wi2));
        }
        a((AbstractC3175z9) new C0920Wa(C0920Wa.a.c, b(wi), new int[]{i}, arrayList));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, int i2) {
        boolean z = w;
        if (!z && i < 0) {
            x1f.a();
        } else if (!z && i2 < 0) {
            x1f.a();
        } else {
            this.f = i;
            this.g = i2;
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, int[] iArr, WI[] wiArr) {
        ArrayList arrayList = new ArrayList(wiArr.length);
        for (WI wi2 : wiArr) {
            arrayList.add(b(wi2));
        }
        a((AbstractC3175z9) new C0920Wa(C0920Wa.a.b, b(wi), iArr, arrayList));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str) {
        C2752uB c2752uB = this.c.a;
        if ((c2752uB.j instanceof ClassFileConsumer) && !c2752uB.u1.Y) {
            a((AbstractC3175z9) new T9(i, this.d.e(str)));
            return;
        }
        a(Integer.valueOf(i));
        b(188, 10);
        for (int i2 = i - 1; i2 >= 0; i2--) {
            a(90);
            a(95);
            a(Integer.valueOf(i2));
            a(95);
            a(79);
        }
        String strSubstring = str.substring(i);
        AbstractC0706Nu abstractC0706Nu = C0929Wj.a;
        if (strSubstring.length() != 1 ? false : C0929Wj.a(strSubstring.charAt(0))) {
            C0245l1 c0245l1A = this.d.a(a(C0929Wj.b(strSubstring.charAt(0))), this.d.e("Ljava/lang/Class;"), "TYPE");
            a((AbstractC3175z9) AbstractC1638h9.a(178, c0245l1A, c0245l1A));
        } else if (C0929Wj.H(strSubstring)) {
            C0245l1 c0245l1A2 = this.d.a(a("java/lang/Void"), this.d.e("Ljava/lang/Class;"), "TYPE");
            a((AbstractC3175z9) AbstractC1638h9.a(178, c0245l1A2, c0245l1A2));
        } else {
            a(C3050xi0.a(0, strSubstring.length(), strSubstring));
        }
        a(95);
        a(184, "java/lang/reflect/Array", "newInstance", "(Ljava/lang/Class;[I)Ljava/lang/Object;", false);
        c(192, str);
        this.h = true;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, WI wi2, WI wi3, String str) {
        this.j.add(new C0972Ya(b(wi), b(wi2), Collections.singletonList(str == null ? this.d.o3 : a(str)), Collections.singletonList(b(wi3))));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, String str3, WI wi, WI wi2, int i) {
        if (this.e.b) {
            this.k.add(new G.a(i, a(new C0230j0(this.d.c(str), this.d.e(str2), str3 == null ? null : this.d.c(str3))), b(wi), b(wi2)));
        }
    }

    public final C0230j0 a(final C0230j0 c0230j0) {
        return (C0230j0) this.l.computeIfAbsent(c0230j0, new Function() { // from class: zph
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0262n4.a(c0230j0, (C0230j0) obj);
            }
        });
    }
}
