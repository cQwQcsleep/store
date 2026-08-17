package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P5 extends DB {
    public static final C3050xi0 a = C3050xi0.e("null");

    /* JADX WARN: Code duplicated, block: B:46:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:48:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:50:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:52:0x00d0  */
    public static O5 b(G g) throws C2222o2 {
        int i = g.a;
        if (i != 179) {
            if (i == 180) {
                String str = ((C1779ip) g).i;
                return a(C3050xi0.a(0, str.length(), str));
            }
            if (i != 198 && i != 199) {
                switch (i) {
                    case 116:
                        return O5.c;
                    case 117:
                        return O5.e;
                    case 118:
                        return O5.d;
                    case 119:
                        return O5.f;
                    default:
                        switch (i) {
                            case 132:
                            case 136:
                            case 139:
                            case 142:
                            case 145:
                            case 146:
                            case 147:
                                return O5.c;
                            case 133:
                            case 140:
                            case 143:
                                return O5.e;
                            case 134:
                            case 137:
                            case 144:
                                return O5.d;
                            case 135:
                            case 138:
                            case 141:
                                return O5.f;
                            default:
                                switch (i) {
                                    default:
                                        switch (i) {
                                            case 170:
                                            case 171:
                                            case 172:
                                            case 173:
                                            case 174:
                                            case 175:
                                            case 176:
                                                break;
                                            default:
                                                switch (i) {
                                                    case 188:
                                                        switch (((C1555gA) g).g) {
                                                            case 4:
                                                                return a(C3050xi0.a(0, 2, "[Z"));
                                                            case XmlPullParser.CDSECT /* 5 */:
                                                                return a(C3050xi0.a(0, 2, "[C"));
                                                            case XmlPullParser.ENTITY_REF /* 6 */:
                                                                return a(C3050xi0.a(0, 2, "[F"));
                                                            case 7:
                                                                return a(C3050xi0.a(0, 2, "[D"));
                                                            case 8:
                                                                return a(C3050xi0.a(0, 2, "[B"));
                                                            case 9:
                                                                return a(C3050xi0.a(0, 2, "[S"));
                                                            case XmlPullParser.DOCDECL /* 10 */:
                                                                return a(C3050xi0.a(0, 2, "[I"));
                                                            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                                                                return a(C3050xi0.a(0, 2, "[J"));
                                                            default:
                                                                throw new C2222o2(g, "Invalid array type");
                                                        }
                                                    case 189:
                                                        String str2 = "[" + C3050xi0.e(((C2710tj0) g).g);
                                                        return a(C3050xi0.a(0, str2.length(), str2));
                                                    case 190:
                                                        return O5.c;
                                                    case 191:
                                                        return null;
                                                    case 192:
                                                        return a(C3050xi0.e(((C2710tj0) g).g));
                                                    case 193:
                                                        return O5.c;
                                                    case 194:
                                                    case 195:
                                                        break;
                                                    default:
                                                        x1f.a();
                                                        return null;
                                                }
                                                break;
                                        }
                                    case 153:
                                    case 154:
                                    case 155:
                                    case 156:
                                    case 157:
                                    case 158:
                                        return null;
                                }
                                break;
                        }
                        break;
                }
            }
            return null;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x002b  */
    /* JADX WARN: Code duplicated, block: B:16:0x002e  */
    /* JADX WARN: Code duplicated, block: B:17:0x0031  */
    /* JADX WARN: Code duplicated, block: B:18:0x0034  */
    /* JADX WARN: Code duplicated, block: B:52:0x0109  */
    /* JADX WARN: Code duplicated, block: B:54:0x010c  */
    /* JADX WARN: Code duplicated, block: B:56:0x010f  */
    /* JADX WARN: Code duplicated, block: B:58:0x0112  */
    @Override // com.android.tools.r8.internal.DB
    public final O5 a(G g, InterfaceC2458ql0 interfaceC2458ql0, InterfaceC2458ql0 interfaceC2458ql1) throws C2222o2 {
        O5 o5A;
        O5 o5A2;
        O5 o5 = (O5) interfaceC2458ql0;
        O5 o6 = (O5) interfaceC2458ql1;
        int i = g.a;
        if (i != 181) {
            switch (i) {
                case 46:
                    o5A2 = a(C3050xi0.a(0, 2, "[I"));
                    o5A = O5.c;
                    break;
                case 47:
                    o5A2 = a(C3050xi0.a(0, 2, "[J"));
                    o5A = O5.c;
                    break;
                case 48:
                    o5A2 = a(C3050xi0.a(0, 2, "[F"));
                    o5A = O5.c;
                    break;
                case 49:
                    o5A2 = a(C3050xi0.a(0, 2, "[D"));
                    o5A = O5.c;
                    break;
                case 50:
                    o5A2 = a(C3050xi0.a(0, 19, "[Ljava/lang/Object;"));
                    o5A = O5.c;
                    break;
                case 51:
                    o5A2 = o5.equals(a(C3050xi0.a(0, 2, "[Z"))) ? a(C3050xi0.a(0, 2, "[Z")) : a(C3050xi0.a(0, 2, "[B"));
                    o5A = O5.c;
                    break;
                case 52:
                    o5A2 = a(C3050xi0.a(0, 2, "[C"));
                    o5A = O5.c;
                    break;
                case 53:
                    o5A2 = a(C3050xi0.a(0, 2, "[S"));
                    o5A = O5.c;
                    break;
                default:
                    switch (i) {
                        case 96:
                        case 100:
                        case 104:
                        case 108:
                        case 112:
                            o5A2 = O5.c;
                            o5A = o5A2;
                            break;
                        case 97:
                        case 101:
                        case 105:
                        case 109:
                        case 113:
                            o5A2 = O5.e;
                            o5A = o5A2;
                            break;
                        case 98:
                        case 102:
                        case 106:
                        case 110:
                        case 114:
                            o5A2 = O5.d;
                            o5A = o5A2;
                            break;
                        case 99:
                        case 103:
                        case 107:
                        case 111:
                        case 115:
                            o5A2 = O5.f;
                            o5A = o5A2;
                            break;
                        default:
                            switch (i) {
                                case 120:
                                case 122:
                                case 124:
                                case 126:
                                case 128:
                                case 130:
                                    o5A2 = O5.c;
                                    o5A = o5A2;
                                    break;
                                case 121:
                                case 123:
                                case 125:
                                    o5A2 = O5.e;
                                    o5A = O5.c;
                                    break;
                                case 127:
                                case 129:
                                case 131:
                                    o5A2 = O5.e;
                                    o5A = o5A2;
                                    break;
                                default:
                                    switch (i) {
                                        case 148:
                                            o5A2 = O5.e;
                                            o5A = o5A2;
                                            break;
                                        case 149:
                                        case 150:
                                            o5A2 = O5.d;
                                            o5A = o5A2;
                                            break;
                                        case 151:
                                        case 152:
                                            o5A2 = O5.f;
                                            o5A = o5A2;
                                            break;
                                        default:
                                            switch (i) {
                                                case 159:
                                                case 160:
                                                case 161:
                                                case 162:
                                                case 163:
                                                case 164:
                                                    o5A2 = O5.c;
                                                    break;
                                                case 165:
                                                case 166:
                                                    o5A2 = O5.g;
                                                    break;
                                                default:
                                                    x1f.a();
                                                    return null;
                                            }
                                            o5A = o5A2;
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            C1779ip c1779ip = (C1779ip) g;
            O5 o5A3 = a(C3050xi0.e(c1779ip.g));
            String str = c1779ip.i;
            o5A = a(C3050xi0.a(0, str.length(), str));
            o5A2 = o5A3;
        }
        if (!o5.equals(o5A2)) {
            throw new C2222o2(g, "First argument", o5A2, o5);
        }
        if (!o6.equals(o5A)) {
            throw new C2222o2(g, "Second argument", o5A, o6);
        }
        int i2 = g.a;
        if (i2 == 50) {
            return O5.g;
        }
        if (i2 != 181) {
            switch (i2) {
                case 46:
                case 51:
                case 52:
                case 53:
                    return O5.c;
                case 47:
                    return O5.e;
                case 48:
                    return O5.d;
                case 49:
                    return O5.f;
                case 50:
                    return O5.g;
                default:
                    switch (i2) {
                        case 96:
                        case 100:
                        case 104:
                        case 108:
                        case 112:
                            return O5.c;
                        case 97:
                        case 101:
                        case 105:
                        case 109:
                        case 113:
                            return O5.e;
                        case 98:
                        case 102:
                        case 106:
                        case 110:
                        case 114:
                            return O5.d;
                        case 99:
                        case 103:
                        case 107:
                        case 111:
                        case 115:
                            return O5.f;
                        default:
                            switch (i2) {
                                case 120:
                                case 122:
                                case 124:
                                case 126:
                                case 128:
                                case 130:
                                    return O5.c;
                                case 121:
                                case 123:
                                case 125:
                                case 127:
                                case 129:
                                case 131:
                                    return O5.e;
                                default:
                                    switch (i2) {
                                        case 148:
                                        case 149:
                                        case 150:
                                        case 151:
                                        case 152:
                                            return O5.c;
                                        default:
                                            switch (i2) {
                                                case 159:
                                                case 160:
                                                case 161:
                                                case 162:
                                                case 163:
                                                case 164:
                                                case 165:
                                                case 166:
                                                    break;
                                                default:
                                                    x1f.a();
                                                    return null;
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
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x003b  */
    /* JADX WARN: Code duplicated, block: B:24:0x003e  */
    /* JADX WARN: Code duplicated, block: B:25:0x0041  */
    /* JADX WARN: Code duplicated, block: B:26:0x0044  */
    @Override // com.android.tools.r8.internal.DB
    public final O5 b(G g, InterfaceC2458ql0 interfaceC2458ql0) throws C2222o2 {
        O5 o5A;
        O5 o5 = (O5) interfaceC2458ql0;
        int i = g.a;
        if (i == 179) {
            String str = ((C1779ip) g).i;
            o5A = a(C3050xi0.a(0, str.length(), str));
        } else {
            if (i != 180) {
                if (i != 198 && i != 199) {
                    switch (i) {
                        case 116:
                            o5A = O5.c;
                            break;
                        case 117:
                            o5A = O5.e;
                            break;
                        case 118:
                            o5A = O5.d;
                            break;
                        case 119:
                            o5A = O5.f;
                            break;
                        default:
                            switch (i) {
                                case 132:
                                case 133:
                                case 134:
                                case 135:
                                case 145:
                                case 146:
                                case 147:
                                    o5A = O5.c;
                                    break;
                                case 136:
                                case 137:
                                case 138:
                                    o5A = O5.e;
                                    break;
                                case 139:
                                case 140:
                                case 141:
                                    o5A = O5.d;
                                    break;
                                case 142:
                                case 143:
                                case 144:
                                    o5A = O5.f;
                                    break;
                                default:
                                    switch (i) {
                                        default:
                                            switch (i) {
                                                case 170:
                                                case 171:
                                                case 172:
                                                    break;
                                                case 173:
                                                    o5A = O5.e;
                                                    break;
                                                case 174:
                                                    o5A = O5.d;
                                                    break;
                                                case 175:
                                                    o5A = O5.f;
                                                    break;
                                                case 176:
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 188:
                                                        case 189:
                                                            break;
                                                        case 190:
                                                            if (o5.b()) {
                                                                return b(g);
                                                            }
                                                            throw new C2222o2(g, null, "an array reference", o5);
                                                        case 191:
                                                        case 192:
                                                        case 193:
                                                        case 194:
                                                        case 195:
                                                            break;
                                                        default:
                                                            x1f.a();
                                                            return null;
                                                    }
                                                    break;
                                            }
                                        case 153:
                                        case 154:
                                        case 155:
                                        case 156:
                                        case 157:
                                        case 158:
                                            o5A = O5.c;
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                }
                if (o5.b()) {
                    return b(g);
                }
                throw new C2222o2(g, null, "an object reference", o5);
            }
            o5A = a(C3050xi0.e(((C1779ip) g).g));
        }
        if (o5.equals(o5A)) {
            return b(g);
        }
        throw new C2222o2(g, null, o5A, o5);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0032  */
    /* JADX WARN: Code duplicated, block: B:19:0x0035  */
    /* JADX WARN: Code duplicated, block: B:20:0x0038  */
    /* JADX WARN: Code duplicated, block: B:21:0x003b  */
    /* JADX WARN: Code duplicated, block: B:25:0x0044  */
    @Override // com.android.tools.r8.internal.DB
    public final O5 a(G g, InterfaceC2458ql0 interfaceC2458ql0) throws C2222o2 {
        O5 o5;
        O5 o6 = (O5) interfaceC2458ql0;
        int i = g.a;
        switch (i) {
            case AndroidSdkVersion.LOLLIPOP /* 21 */:
                o5 = O5.c;
                if (!o5.equals(o6)) {
                    throw new C2222o2(g, null, o5, o6);
                }
                return o6;
            case 22:
                o5 = O5.e;
                if (!o5.equals(o6)) {
                    throw new C2222o2(g, null, o5, o6);
                }
                return o6;
            case AndroidSdkVersion.M /* 23 */:
                o5 = O5.d;
                if (!o5.equals(o6)) {
                    throw new C2222o2(g, null, o5, o6);
                }
                return o6;
            case AndroidSdkVersion.N /* 24 */:
                o5 = O5.f;
                if (!o5.equals(o6)) {
                    throw new C2222o2(g, null, o5, o6);
                }
                return o6;
            case 25:
                if (!o6.b()) {
                    throw new C2222o2(g, null, "an object reference", o6);
                }
                return o6;
            default:
                switch (i) {
                    case 54:
                        o5 = O5.c;
                        if (!o5.equals(o6)) {
                            throw new C2222o2(g, null, o5, o6);
                        }
                        return o6;
                    case 55:
                        o5 = O5.e;
                        if (!o5.equals(o6)) {
                            throw new C2222o2(g, null, o5, o6);
                        }
                        return o6;
                    case Fcntl.S_IRWXG /* 56 */:
                        o5 = O5.d;
                        if (!o5.equals(o6)) {
                            throw new C2222o2(g, null, o5, o6);
                        }
                        return o6;
                    case 57:
                        o5 = O5.f;
                        if (!o5.equals(o6)) {
                            throw new C2222o2(g, null, o5, o6);
                        }
                        return o6;
                    case 58:
                        if (!o6.b() && !O5.h.equals(o6)) {
                            throw new C2222o2(g, null, "an object reference or a return address", o6);
                        }
                    default:
                        return o6;
                }
                break;
        }
    }

    public static O5 a(C3050xi0 c3050xi0) {
        if (c3050xi0 == null) {
            return O5.b;
        }
        switch (c3050xi0.c()) {
            case 0:
                return null;
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
            case XmlPullParser.CDSECT /* 5 */:
                return O5.c;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return O5.d;
            case 7:
                return O5.e;
            case 8:
                return O5.f;
            case 9:
            case XmlPullParser.DOCDECL /* 10 */:
                return O5.g;
            default:
                x1f.a();
                return null;
        }
    }

    @Override // com.android.tools.r8.internal.DB
    public final O5 a(G g, ArrayList arrayList) throws C2222o2 {
        int i;
        String str;
        int i2 = g.a;
        if (i2 == 197) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                O5 o5 = (O5) it.next();
                O5 o6 = O5.c;
                if (!o6.equals(o5)) {
                    throw new C2222o2(g, null, o6, o5);
                }
            }
        } else {
            if (i2 == 184 || i2 == 186) {
                i = 0;
            } else {
                C3050xi0 c3050xi0E = C3050xi0.e(((C2594sO) g).g);
                if (!((O5) arrayList.get(0)).equals(a(c3050xi0E))) {
                    throw new C2222o2(g, "Method owner", a(c3050xi0E), (InterfaceC2458ql0) arrayList.get(0));
                }
                i = 1;
            }
            if (i2 == 186) {
                str = ((WB) g).h;
            } else {
                str = ((C2594sO) g).i;
            }
            C3050xi0[] c3050xi0ArrB = C3050xi0.b(str);
            int i3 = 0;
            while (i < arrayList.size()) {
                int i4 = i3 + 1;
                O5 o5A = a(c3050xi0ArrB[i3]);
                int i5 = i + 1;
                O5 o7 = (O5) arrayList.get(i);
                if (!o7.equals(o5A)) {
                    throw new C2222o2(g, CX.a(i4, "Argument "), o5A, o7);
                }
                i3 = i4;
                i = i5;
            }
        }
        int i6 = g.a;
        if (i6 == 197) {
            String str2 = ((C3107yP) g).g;
            return a(C3050xi0.a(0, str2.length(), str2));
        }
        if (i6 == 186) {
            String str3 = ((WB) g).h;
            return a(C3050xi0.a(C3050xi0.f(str3), str3.length(), str3));
        }
        String str4 = ((C2594sO) g).i;
        return a(C3050xi0.a(C3050xi0.f(str4), str4.length(), str4));
    }

    @Override // com.android.tools.r8.internal.DB
    public final O5 a(G g) throws C2222o2 {
        int i = g.a;
        if (i == 168) {
            return O5.h;
        }
        if (i == 178) {
            String str = ((C1779ip) g).i;
            return a(C3050xi0.a(0, str.length(), str));
        }
        if (i != 187) {
            switch (i) {
                case 1:
                    return a(a);
                case 2:
                case XmlPullParser.END_TAG /* 3 */:
                case 4:
                case XmlPullParser.CDSECT /* 5 */:
                case XmlPullParser.ENTITY_REF /* 6 */:
                case 7:
                case 8:
                    return O5.c;
                case 9:
                case XmlPullParser.DOCDECL /* 10 */:
                    return O5.e;
                case AndroidSdkVersion.HONEYCOMB /* 11 */:
                case 12:
                case 13:
                    return O5.d;
                case 14:
                case 15:
                    return O5.f;
                case Fcntl.S_IWGRP /* 16 */:
                case 17:
                    return O5.c;
                case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                    Object obj = ((BJ) g).g;
                    if (obj instanceof Integer) {
                        return O5.c;
                    }
                    if (obj instanceof Float) {
                        return O5.d;
                    }
                    if (obj instanceof Long) {
                        return O5.e;
                    }
                    if (obj instanceof Double) {
                        return O5.f;
                    }
                    if (obj instanceof String) {
                        return a(C3050xi0.e("java/lang/String"));
                    }
                    if (obj instanceof C3050xi0) {
                        int iC = ((C3050xi0) obj).c();
                        if (iC == 10 || iC == 9) {
                            return a(C3050xi0.e("java/lang/Class"));
                        }
                        if (iC == 11) {
                            return a(C3050xi0.e("java/lang/invoke/MethodType"));
                        }
                        throw new C2222o2(g, "Illegal LDC value " + obj);
                    }
                    if (obj instanceof C0497Fs) {
                        return a(C3050xi0.e("java/lang/invoke/MethodHandle"));
                    }
                    if (obj instanceof C2190ng) {
                        String str2 = ((C2190ng) obj).b;
                        return a(C3050xi0.a(0, str2.length(), str2));
                    }
                    throw new C2222o2(g, "Illegal LDC value " + obj);
                default:
                    x1f.a();
                    return null;
            }
        }
        return a(C3050xi0.e(((C2710tj0) g).g));
    }
}
