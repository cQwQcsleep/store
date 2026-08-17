package com.android.tools.r8.dex.code;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.android.tools.r8.graph.C0290r5;
import com.sun.jna.platform.linux.Fcntl;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class N {
    public static AbstractC0138z1 a(int i, int i2, A1 a1, C0290r5 c0290r5) {
        switch (i2) {
            case 0:
                return P2.a(i, a1);
            case 1:
                return new C0075m2(i, a1);
            case 2:
                return new C0085o2(i, a1);
            case XmlPullParser.END_TAG /* 3 */:
                return new C0070l2(i, a1);
            case 4:
                return new C0124w2(i, a1);
            case XmlPullParser.CDSECT /* 5 */:
                return new C0129x2(i, a1);
            case XmlPullParser.ENTITY_REF /* 6 */:
                return new C0119v2(i, a1);
            case 7:
                return new C0095q2(i, a1);
            case 8:
                return new C0099r2(i, a1);
            case 9:
                return new C0090p2(i, a1);
            case XmlPullParser.DOCDECL /* 10 */:
                return new C0104s2(i, a1);
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return new C0114u2(i, a1);
            case 12:
                return new C0109t2(i, a1);
            case 13:
                return new C0080n2(i, a1);
            case 14:
                return new C0081n3(i, a1);
            case 15:
                return new C0071l3(i, a1);
            case Fcntl.S_IWGRP /* 16 */:
                return new C0086o3(i, a1);
            case 17:
                return new C0076m3(i, a1);
            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                return new X(i, a1);
            case AndroidSdkVersion.KITKAT /* 19 */:
                return new W(i, a1);
            case 20:
                return new Y(i, a1);
            case AndroidSdkVersion.LOLLIPOP /* 21 */:
                return new C0013a0(i, a1);
            case 22:
                return new C0038f0(i, a1);
            case AndroidSdkVersion.M /* 23 */:
                return new C0043g0(i, a1);
            case AndroidSdkVersion.N /* 24 */:
                return new C0048h0(i, a1);
            case 25:
                return new C0053i0(i, a1);
            case AndroidSdkVersion.O /* 26 */:
                return new C0028d0(i, a1, c0290r5);
            case 27:
                return new C0033e0(i, a1, c0290r5);
            case AndroidSdkVersion.P /* 28 */:
                return new Z(i, a1, c0290r5);
            case AndroidSdkVersion.Q /* 29 */:
                return new C0060j2(i, a1);
            case AndroidSdkVersion.R /* 30 */:
                return new C0065k2(i, a1);
            case AndroidSdkVersion.S /* 31 */:
                return new O(i, a1, c0290r5);
            case 32:
                return new C0133y1(i, a1, c0290r5);
            case AndroidSdkVersion.T /* 33 */:
                return new I(i, a1);
            case AndroidSdkVersion.U /* 34 */:
                return new N2(i, a1, c0290r5);
            case 35:
                return new M2(i, a1, c0290r5);
            case 36:
                return new C0132y0(i, a1, c0290r5);
            case 37:
                return new C0137z0(i, a1, c0290r5);
            case 38:
                return new C0122w0(i, a1);
            case 39:
                return new c4(i, a1);
            case 40:
                return new C0029d1(i, a1);
            case 41:
                return new C0019b1(i, a1);
            case 42:
                return new C0024c1(i, a1);
            case 43:
                return new Y2(i, a1);
            case 44:
                return new K3(i, a1);
            case 45:
                return new U(i, a1);
            case 46:
                return new S(i, a1);
            case 47:
                return new T(i, a1);
            case 48:
                return new Q(i, a1);
            case 49:
                return new P(i, a1);
            case 50:
                return new C0034e1(i, a1);
            case 51:
                return new C0084o1(i, a1);
            case 52:
                return new C0074m1(i, a1);
            case 53:
                return new C0044g1(i, a1);
            case 54:
                return new C0054i1(i, a1);
            case 55:
                return new C0064k1(i, a1);
            case Fcntl.S_IRWXG /* 56 */:
                return new C0039f1(i, a1);
            case 57:
                return new C0089p1(i, a1);
            case 58:
                return new C0079n1(i, a1);
            case 59:
                return new C0049h1(i, a1);
            case 60:
                return new C0059j1(i, a1);
            case 61:
                return new C0069l1(i, a1);
            default:
                switch (i2) {
                    case 68:
                        return new C0082o(i, a1);
                    case 69:
                        return new C0111u(i, a1);
                    case 70:
                        return new C0101s(i, a1);
                    case 71:
                        return new C0087p(i, a1);
                    case 72:
                        return new C0092q(i, a1);
                    case 73:
                        return new r(i, a1);
                    case 74:
                        return new C0106t(i, a1);
                    case 75:
                        return new B(i, a1);
                    case 76:
                        return new H(i, a1);
                    case 77:
                        return new F(i, a1);
                    case 78:
                        return new C(i, a1);
                    case 79:
                        return new D(i, a1);
                    case 80:
                        return new E(i, a1);
                    case 81:
                        return new G(i, a1);
                    case 82:
                        return new C0094q1(i, a1, c0290r5);
                    case 83:
                        return new C0128x1(i, a1, c0290r5);
                    case 84:
                        return new C0113u1(i, a1, c0290r5);
                    case 85:
                        return new C0098r1(i, a1, c0290r5);
                    case 86:
                        return new C0103s1(i, a1, c0290r5);
                    case 87:
                        return new C0108t1(i, a1, c0290r5);
                    case 88:
                        return new C0123w1(i, a1, c0290r5);
                    case 89:
                        return new Y1(i, a1, c0290r5);
                    case 90:
                        return new C0035e2(i, a1, c0290r5);
                    case 91:
                        return new C0025c2(i, a1, c0290r5);
                    case 92:
                        return new Z1(i, a1, c0290r5);
                    case 93:
                        return new C0015a2(i, a1, c0290r5);
                    case 94:
                        return new C0020b2(i, a1, c0290r5);
                    case 95:
                        return new C0030d2(i, a1, c0290r5);
                    case 96:
                        return new C0105s3(i, a1, c0290r5);
                    case 97:
                        return new C0140z3(i, a1, c0290r5);
                    case 98:
                        return new C0125w3(i, a1, c0290r5);
                    case 99:
                        return new C0110t3(i, a1, c0290r5);
                    case 100:
                        return new C0115u3(i, a1, c0290r5);
                    case 101:
                        return new C0120v3(i, a1, c0290r5);
                    case 102:
                        return new C0135y3(i, a1, c0290r5);
                    case 103:
                        return new M3(i, a1, c0290r5);
                    case 104:
                        return new S3(i, a1, c0290r5);
                    case 105:
                        return new Q3(i, a1, c0290r5);
                    case 106:
                        return new N3(i, a1, c0290r5);
                    case 107:
                        return new O3(i, a1, c0290r5);
                    case 108:
                        return new P3(i, a1, c0290r5);
                    case 109:
                        return new R3(i, a1, c0290r5);
                    case 110:
                        return new W1(i, a1, c0290r5);
                    case 111:
                        return new U1(i, a1, c0290r5);
                    case 112:
                        return new K1(i, a1, c0290r5);
                    case 113:
                        return new S1(i, a1, c0290r5);
                    case 114:
                        return new M1(i, a1, c0290r5);
                    default:
                        switch (i2) {
                            case 116:
                                return new X1(i, a1, c0290r5);
                            case 117:
                                return new V1(i, a1, c0290r5);
                            case 118:
                                return new L1(i, a1, c0290r5);
                            case 119:
                                return new T1(i, a1, c0290r5);
                            case 120:
                                return new N1(i, a1, c0290r5);
                            default:
                                switch (i2) {
                                    case 123:
                                        return new K2(i, a1);
                                    case 124:
                                        return new Q2(i, a1);
                                    case 125:
                                        return new L2(i, a1);
                                    case 126:
                                        return new R2(i, a1);
                                    case 127:
                                        return new J2(i, a1);
                                    case 128:
                                        return new I2(i, a1);
                                    case 129:
                                        return new G1(i, a1);
                                    case 130:
                                        return new F1(i, a1);
                                    case 131:
                                        return new E1(i, a1);
                                    case 132:
                                        return new C0055i2(i, a1);
                                    case 133:
                                        return new C0050h2(i, a1);
                                    case 134:
                                        return new C0045g2(i, a1);
                                    case 135:
                                        return new B0(i, a1);
                                    case 136:
                                        return new C0(i, a1);
                                    case 137:
                                        return new A0(i, a1);
                                    case 138:
                                        return new C0112u0(i, a1);
                                    case 139:
                                        return new C0117v0(i, a1);
                                    case 140:
                                        return new C0107t0(i, a1);
                                    case 141:
                                        return new C1(i, a1);
                                    case 142:
                                        return new D1(i, a1);
                                    case 143:
                                        return new H1(i, a1);
                                    case 144:
                                        return new C0057j(i, a1);
                                    case 145:
                                        return new Y3(i, a1);
                                    case 146:
                                        return new D2(i, a1);
                                    case 147:
                                        return new C0083o0(i, a1);
                                    case 148:
                                        return new C0046g3(i, a1);
                                    case 149:
                                        return new C0121w(i, a1);
                                    case 150:
                                        return new T2(i, a1);
                                    case 151:
                                        return new j4(i, a1);
                                    case 152:
                                        return new B3(i, a1);
                                    case 153:
                                        return new G3(i, a1);
                                    case 154:
                                        return new e4(i, a1);
                                    case 155:
                                        return new C0077n(i, a1);
                                    case 156:
                                        return new a4(i, a1);
                                    case 157:
                                        return new H2(i, a1);
                                    case 158:
                                        return new C0102s0(i, a1);
                                    case 159:
                                        return new C0066k3(i, a1);
                                    case 160:
                                        return new A(i, a1);
                                    case 161:
                                        return new X2(i, a1);
                                    case 162:
                                        return new n4(i, a1);
                                    case 163:
                                        return new E3(i, a1);
                                    case 164:
                                        return new J3(i, a1);
                                    case 165:
                                        return new h4(i, a1);
                                    case 166:
                                        return new C0047h(i, a1);
                                    case 167:
                                        return new W3(i, a1);
                                    case 168:
                                        return new B2(i, a1);
                                    case 169:
                                        return new C0073m0(i, a1);
                                    case 170:
                                        return new C0036e3(i, a1);
                                    case 171:
                                        return new C0037f(i, a1);
                                    case 172:
                                        return new U3(i, a1);
                                    case 173:
                                        return new C0139z2(i, a1);
                                    case 174:
                                        return new C0063k0(i, a1);
                                    case 175:
                                        return new C0026c3(i, a1);
                                    case 176:
                                        return new C0052i(i, a1);
                                    case 177:
                                        return new X3(i, a1);
                                    case 178:
                                        return new C2(i, a1);
                                    case 179:
                                        return new C0078n0(i, a1);
                                    case 180:
                                        return new C0041f3(i, a1);
                                    case 181:
                                        return new C0116v(i, a1);
                                    case 182:
                                        return new S2(i, a1);
                                    case 183:
                                        return new i4(i, a1);
                                    case 184:
                                        return new A3(i, a1);
                                    case 185:
                                        return new F3(i, a1);
                                    case 186:
                                        return new d4(i, a1);
                                    case 187:
                                        return new C0072m(i, a1);
                                    case 188:
                                        return new Z3(i, a1);
                                    case 189:
                                        return new G2(i, a1);
                                    case 190:
                                        return new C0097r0(i, a1);
                                    case 191:
                                        return new C0061j3(i, a1);
                                    case 192:
                                        return new C0136z(i, a1);
                                    case 193:
                                        return new W2(i, a1);
                                    case 194:
                                        return new m4(i, a1);
                                    case 195:
                                        return new D3(i, a1);
                                    case 196:
                                        return new I3(i, a1);
                                    case 197:
                                        return new g4(i, a1);
                                    case 198:
                                        return new C0042g(i, a1);
                                    case 199:
                                        return new V3(i, a1);
                                    case 200:
                                        return new A2(i, a1);
                                    case 201:
                                        return new C0068l0(i, a1);
                                    case 202:
                                        return new C0031d3(i, a1);
                                    case 203:
                                        return new C0032e(i, a1);
                                    case 204:
                                        return new T3(i, a1);
                                    case 205:
                                        return new C0134y2(i, a1);
                                    case 206:
                                        return new C0058j0(i, a1);
                                    case 207:
                                        return new C0021b3(i, a1);
                                    case 208:
                                        return new C0062k(i, a1);
                                    case 209:
                                        return new C0091p3(i, a1);
                                    case 210:
                                        return new E2(i, a1);
                                    case 211:
                                        return new C0088p0(i, a1);
                                    case 212:
                                        return new C0051h3(i, a1);
                                    case 213:
                                        return new C0126x(i, a1);
                                    case 214:
                                        return new U2(i, a1);
                                    case 215:
                                        return new k4(i, a1);
                                    case 216:
                                        return new C0067l(i, a1);
                                    case 217:
                                        return new C0096q3(i, a1);
                                    case 218:
                                        return new F2(i, a1);
                                    case 219:
                                        return new C0093q0(i, a1);
                                    case 220:
                                        return new C0056i3(i, a1);
                                    case 221:
                                        return new C0131y(i, a1);
                                    case 222:
                                        return new V2(i, a1);
                                    case 223:
                                        return new l4(i, a1);
                                    case 224:
                                        return new C3(i, a1);
                                    case 225:
                                        return new H3(i, a1);
                                    case 226:
                                        return new f4(i, a1);
                                    default:
                                        switch (i2) {
                                            case 250:
                                                return new Q1(i, a1, c0290r5);
                                            case 251:
                                                return new R1(i, a1, c0290r5);
                                            case 252:
                                                return new I1(i, a1, c0290r5);
                                            case 253:
                                                return new J1(i, a1, c0290r5);
                                            case 254:
                                                return new C0018b0(i, a1, c0290r5);
                                            case 255:
                                                return new C0023c0(i, a1, c0290r5);
                                            default:
                                                z01.a("Illegal Opcode: 0x", Integer.toString(i2, 16));
                                                return null;
                                        }
                                }
                        }
                }
        }
    }
}
