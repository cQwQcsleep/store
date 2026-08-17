package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0936Wq {
    public InterfaceC2458ql0 a;
    public InterfaceC2458ql0[] b;
    public int c;
    public int d;
    public int e;

    public C0936Wq(int i, int i2) {
        this.b = new InterfaceC2458ql0[(i2 >= 0 ? i2 : 4) + i];
        this.c = i;
        this.d = 0;
        this.e = i2 < 0 ? 65536 : i2;
    }

    public final void a(G g, DB db) throws C2222o2 {
        int i;
        InterfaceC2458ql0 interfaceC2458ql0A;
        O5 o5A;
        O5 o5;
        int i2 = g.a;
        switch (i2) {
            case 0:
                return;
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
            case XmlPullParser.CDSECT /* 5 */:
            case XmlPullParser.ENTITY_REF /* 6 */:
            case 7:
            case 8:
            case 9:
            case XmlPullParser.DOCDECL /* 10 */:
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
            case 12:
            case 13:
            case 14:
            case 15:
            case Fcntl.S_IWGRP /* 16 */:
            case 17:
            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                a(db.a(g));
                return;
            default:
                switch (i2) {
                    case AndroidSdkVersion.LOLLIPOP /* 21 */:
                    case 22:
                    case AndroidSdkVersion.M /* 23 */:
                    case AndroidSdkVersion.N /* 24 */:
                    case 25:
                        a(db.a(g, a(((Ll0) g).g)));
                        return;
                    default:
                        switch (i2) {
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                                break;
                            case 54:
                            case 55:
                            case Fcntl.S_IRWXG /* 56 */:
                            case 57:
                            case 58:
                                O5 o5A2 = db.a(g, a());
                                int i3 = ((Ll0) g).g;
                                a(i3, o5A2);
                                if (o5A2.a() == 2) {
                                    C3050xi0 c3050xi0 = P5.a;
                                    a(i3 + 1, O5.b);
                                }
                                if (i3 <= 0 || (interfaceC2458ql0A = a((i = i3 - 1))) == null || ((O5) interfaceC2458ql0A).a() != 2) {
                                    return;
                                }
                                C3050xi0 c3050xi1 = P5.a;
                                a(i, O5.b);
                                return;
                            default:
                                switch (i2) {
                                    case 79:
                                    case 80:
                                    case 81:
                                    case 82:
                                    case 83:
                                    case 84:
                                    case 85:
                                    case 86:
                                        InterfaceC2458ql0 interfaceC2458ql0A2 = a();
                                        InterfaceC2458ql0 interfaceC2458ql0A3 = a();
                                        InterfaceC2458ql0 interfaceC2458ql0A4 = a();
                                        ((P5) db).getClass();
                                        O5 o6 = (O5) interfaceC2458ql0A4;
                                        O5 o7 = (O5) interfaceC2458ql0A3;
                                        O5 o8 = (O5) interfaceC2458ql0A2;
                                        switch (g.a) {
                                            case 79:
                                                o5A = P5.a(C3050xi0.a(0, 2, "[I"));
                                                o5 = O5.c;
                                                break;
                                            case 80:
                                                o5A = P5.a(C3050xi0.a(0, 2, "[J"));
                                                o5 = O5.e;
                                                break;
                                            case 81:
                                                o5A = P5.a(C3050xi0.a(0, 2, "[F"));
                                                o5 = O5.d;
                                                break;
                                            case 82:
                                                o5A = P5.a(C3050xi0.a(0, 2, "[D"));
                                                o5 = O5.f;
                                                break;
                                            case 83:
                                                o5 = O5.g;
                                                o5A = o6;
                                                break;
                                            case 84:
                                                o5A = o6.equals(P5.a(C3050xi0.a(0, 2, "[Z"))) ? P5.a(C3050xi0.a(0, 2, "[Z")) : P5.a(C3050xi0.a(0, 2, "[B"));
                                                o5 = O5.c;
                                                break;
                                            case 85:
                                                o5A = P5.a(C3050xi0.a(0, 2, "[C"));
                                                o5 = O5.c;
                                                break;
                                            case 86:
                                                o5A = P5.a(C3050xi0.a(0, 2, "[S"));
                                                o5 = O5.c;
                                                break;
                                            default:
                                                x1f.a();
                                                return;
                                        }
                                        if (!o6.equals(o5A)) {
                                            throw new C2222o2(g, "First argument", "a " + o5A + " array reference", o6);
                                        }
                                        O5 o9 = O5.c;
                                        if (!o9.equals(o7)) {
                                            throw new C2222o2(g, "Second argument", o9, o7);
                                        }
                                        if (!o8.equals(o5)) {
                                            throw new C2222o2(g, "Third argument", o5, o8);
                                        }
                                        return;
                                    case 87:
                                        if (((O5) a()).a() == 2) {
                                            throw new C2222o2(g, "Illegal use of POP");
                                        }
                                        return;
                                    case 88:
                                        if (((O5) a()).a() == 1 && ((O5) a()).a() != 1) {
                                            throw new C2222o2(g, "Illegal use of POP2");
                                        }
                                        return;
                                    case 89:
                                        O5 o10 = (O5) a();
                                        if (o10.a() != 1) {
                                            throw new C2222o2(g, "Illegal use of DUP");
                                        }
                                        a(o10);
                                        a(db.a(g, o10));
                                        return;
                                    case 90:
                                        InterfaceC2458ql0 interfaceC2458ql0A5 = a();
                                        InterfaceC2458ql0 interfaceC2458ql0A6 = a();
                                        O5 o11 = (O5) interfaceC2458ql0A5;
                                        if (o11.a() == 1) {
                                            O5 o12 = (O5) interfaceC2458ql0A6;
                                            if (o12.a() == 1) {
                                                a(db.a(g, o11));
                                                a(o12);
                                                a(o11);
                                                return;
                                            }
                                        }
                                        throw new C2222o2(g, "Illegal use of DUP_X1");
                                    case 91:
                                        O5 o13 = (O5) a();
                                        if (o13.a() == 1) {
                                            O5 o14 = (O5) a();
                                            if (o14.a() != 1) {
                                                a(db.a(g, o13));
                                                a(o14);
                                                a(o13);
                                                return;
                                            } else {
                                                O5 o15 = (O5) a();
                                                if (o15.a() == 1) {
                                                    a(db.a(g, o13));
                                                    a(o15);
                                                    a(o14);
                                                    a(o13);
                                                    return;
                                                }
                                            }
                                        }
                                        throw new C2222o2(g, "Illegal use of DUP_X2");
                                    case 92:
                                        O5 o16 = (O5) a();
                                        if (o16.a() != 1) {
                                            a(o16);
                                            a(db.a(g, o16));
                                            return;
                                        }
                                        O5 o17 = (O5) a();
                                        if (o17.a() != 1) {
                                            throw new C2222o2(g, "Illegal use of DUP2");
                                        }
                                        a(o17);
                                        a(o16);
                                        a(db.a(g, o17));
                                        a(db.a(g, o16));
                                        return;
                                    case 93:
                                        O5 o18 = (O5) a();
                                        if (o18.a() == 1) {
                                            O5 o19 = (O5) a();
                                            if (o19.a() == 1) {
                                                O5 o20 = (O5) a();
                                                if (o20.a() == 1) {
                                                    a(db.a(g, o19));
                                                    a(db.a(g, o18));
                                                    a(o20);
                                                    a(o19);
                                                    a(o18);
                                                    return;
                                                }
                                            }
                                        } else {
                                            O5 o21 = (O5) a();
                                            if (o21.a() == 1) {
                                                a(db.a(g, o18));
                                                a(o21);
                                                a(o18);
                                                return;
                                            }
                                        }
                                        throw new C2222o2(g, "Illegal use of DUP2_X1");
                                    case 94:
                                        O5 o22 = (O5) a();
                                        if (o22.a() == 1) {
                                            O5 o23 = (O5) a();
                                            if (o23.a() == 1) {
                                                O5 o24 = (O5) a();
                                                if (o24.a() != 1) {
                                                    a(db.a(g, o23));
                                                    a(db.a(g, o22));
                                                    a(o24);
                                                    a(o23);
                                                    a(o22);
                                                    return;
                                                }
                                                O5 o25 = (O5) a();
                                                if (o25.a() == 1) {
                                                    a(db.a(g, o23));
                                                    a(db.a(g, o22));
                                                    a(o25);
                                                    a(o24);
                                                    a(o23);
                                                    a(o22);
                                                    return;
                                                }
                                            }
                                        } else {
                                            O5 o26 = (O5) a();
                                            if (o26.a() != 1) {
                                                a(db.a(g, o22));
                                                a(o26);
                                                a(o22);
                                                return;
                                            } else {
                                                O5 o27 = (O5) a();
                                                if (o27.a() == 1) {
                                                    a(db.a(g, o22));
                                                    a(o27);
                                                    a(o26);
                                                    a(o22);
                                                    return;
                                                }
                                            }
                                        }
                                        throw new C2222o2(g, "Illegal use of DUP2_X2");
                                    case 95:
                                        InterfaceC2458ql0 interfaceC2458ql0A7 = a();
                                        O5 o28 = (O5) a();
                                        if (o28.a() == 1) {
                                            O5 o29 = (O5) interfaceC2458ql0A7;
                                            if (o29.a() == 1) {
                                                a(db.a(g, o29));
                                                a(db.a(g, o28));
                                                return;
                                            }
                                        }
                                        throw new C2222o2(g, "Illegal use of SWAP");
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
                                    case 148:
                                    case 149:
                                    case 150:
                                    case 151:
                                    case 152:
                                        break;
                                    case 116:
                                    case 117:
                                    case 118:
                                    case 119:
                                        a(db.b(g, a()));
                                        return;
                                    case 132:
                                        int i4 = ((C2383pu) g).g;
                                        a(i4, db.b(g, a(i4)));
                                        return;
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
                                        a(db.b(g, a()));
                                        return;
                                    case 153:
                                    case 154:
                                    case 155:
                                    case 156:
                                    case 157:
                                    case 158:
                                        db.b(g, a());
                                        return;
                                    case 159:
                                    case 160:
                                    case 161:
                                    case 162:
                                    case 163:
                                    case 164:
                                    case 165:
                                    case 166:
                                    case 181:
                                        db.a(g, a(), a());
                                        return;
                                    case 167:
                                    case 169:
                                        return;
                                    case 168:
                                        a(db.a(g));
                                        return;
                                    case 170:
                                    case 171:
                                        db.b(g, a());
                                        return;
                                    case 172:
                                    case 173:
                                    case 174:
                                    case 175:
                                    case 176:
                                        InterfaceC2458ql0 interfaceC2458ql0A8 = a();
                                        db.b(g, interfaceC2458ql0A8);
                                        O5 o30 = (O5) interfaceC2458ql0A8;
                                        O5 o31 = (O5) this.a;
                                        if (!o30.equals(o31)) {
                                            throw new C2222o2(g, "Incompatible return type", o31, o30);
                                        }
                                        return;
                                    case 177:
                                        if (this.a != null) {
                                            throw new C2222o2(g, "Incompatible return type");
                                        }
                                        return;
                                    case 178:
                                        a(db.a(g));
                                        return;
                                    case 179:
                                        db.b(g, a());
                                        return;
                                    case 180:
                                        a(db.b(g, a()));
                                        return;
                                    case 182:
                                    case 183:
                                    case 184:
                                    case 185:
                                        a(g, ((C2594sO) g).i, db);
                                        return;
                                    case 186:
                                        a(g, ((WB) g).h, db);
                                        return;
                                    case 187:
                                        a(db.a(g));
                                        return;
                                    case 188:
                                    case 189:
                                    case 190:
                                        a(db.b(g, a()));
                                        return;
                                    case 191:
                                        db.b(g, a());
                                        return;
                                    case 192:
                                    case 193:
                                        a(db.b(g, a()));
                                        return;
                                    case 194:
                                    case 195:
                                        db.b(g, a());
                                        return;
                                    default:
                                        switch (i2) {
                                            case 197:
                                                ArrayList arrayList = new ArrayList();
                                                for (int i5 = ((C3107yP) g).h; i5 > 0; i5--) {
                                                    arrayList.add(0, a());
                                                }
                                                a(db.a(g, arrayList));
                                                return;
                                            case 198:
                                            case 199:
                                                db.b(g, a());
                                                return;
                                            default:
                                                throw new C2222o2(g, CX.a(g.a, "Illegal opcode "));
                                        }
                                }
                                break;
                        }
                        a(db.a(g, a(), a()));
                        return;
                }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.c; i++) {
            sb.append(a(i));
        }
        sb.append(' ');
        for (int i2 = 0; i2 < this.d; i2++) {
            sb.append(this.b[this.c + i2].toString());
        }
        return sb.toString();
    }

    public final C0936Wq a(C0936Wq c0936Wq) {
        this.a = c0936Wq.a;
        InterfaceC2458ql0[] interfaceC2458ql0Arr = this.b;
        int length = interfaceC2458ql0Arr.length;
        InterfaceC2458ql0[] interfaceC2458ql0Arr2 = c0936Wq.b;
        if (length < interfaceC2458ql0Arr2.length) {
            this.b = (InterfaceC2458ql0[]) interfaceC2458ql0Arr2.clone();
        } else {
            System.arraycopy(interfaceC2458ql0Arr2, 0, interfaceC2458ql0Arr, 0, interfaceC2458ql0Arr2.length);
        }
        this.c = c0936Wq.c;
        this.d = c0936Wq.d;
        this.e = c0936Wq.e;
        return this;
    }

    public final InterfaceC2458ql0 a(int i) {
        if (i < this.c) {
            return this.b[i];
        }
        jb9.a(CX.a(i, "Trying to get an inexistant local variable "));
        return null;
    }

    public final void a(int i, O5 o5) {
        if (i < this.c) {
            this.b[i] = o5;
        } else {
            jb9.a(CX.a(i, "Trying to set an inexistant local variable "));
        }
    }

    public final InterfaceC2458ql0 a() {
        int i = this.d;
        if (i != 0) {
            InterfaceC2458ql0[] interfaceC2458ql0Arr = this.b;
            int i2 = this.c;
            int i3 = i - 1;
            this.d = i3;
            return interfaceC2458ql0Arr[i2 + i3];
        }
        jb9.a("Cannot pop operand off an empty stack.");
        return null;
    }

    public final void a(O5 o5) {
        int i = this.c + this.d;
        InterfaceC2458ql0[] interfaceC2458ql0Arr = this.b;
        if (i >= interfaceC2458ql0Arr.length) {
            if (i < this.e) {
                InterfaceC2458ql0[] interfaceC2458ql0Arr2 = new InterfaceC2458ql0[interfaceC2458ql0Arr.length * 2];
                this.b = interfaceC2458ql0Arr2;
                System.arraycopy(interfaceC2458ql0Arr, 0, interfaceC2458ql0Arr2, 0, interfaceC2458ql0Arr.length);
            } else {
                jb9.a("Insufficient maximum stack size.");
                return;
            }
        }
        InterfaceC2458ql0[] interfaceC2458ql0Arr3 = this.b;
        int i2 = this.c;
        int i3 = this.d;
        this.d = i3 + 1;
        interfaceC2458ql0Arr3[i2 + i3] = o5;
    }

    public final void a(G g, String str, DB db) {
        ArrayList arrayList = new ArrayList();
        for (int iA = C3050xi0.a(str); iA > 0; iA--) {
            arrayList.add(0, a());
        }
        int i = g.a;
        if (i != 184 && i != 186) {
            arrayList.add(0, a());
        }
        if (C3050xi0.a(C3050xi0.f(str), str.length(), str) == C3050xi0.e) {
            db.a(g, arrayList);
        } else {
            a(db.a(g, arrayList));
        }
    }

    public final boolean a(C0936Wq c0936Wq, DB db) throws C2222o2 {
        if (this.d == c0936Wq.d) {
            boolean z = false;
            for (int i = 0; i < this.c + this.d; i++) {
                InterfaceC2458ql0 interfaceC2458ql0 = this.b[i];
                InterfaceC2458ql0 interfaceC2458ql1 = c0936Wq.b[i];
                ((P5) db).getClass();
                O5 o5 = (O5) interfaceC2458ql0;
                if (!o5.equals((O5) interfaceC2458ql1)) {
                    o5 = O5.b;
                }
                if (!o5.equals(this.b[i])) {
                    this.b[i] = o5;
                    z = true;
                }
            }
            return z;
        }
        throw new C2222o2(null, "Incompatible stack heights");
    }
}
