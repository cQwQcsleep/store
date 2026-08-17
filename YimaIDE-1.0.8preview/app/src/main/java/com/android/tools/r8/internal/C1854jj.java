package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1854jj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C2110mj c2110mj = new C2110mj();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    switch (iS) {
                        case 0:
                            break;
                        case XmlPullParser.DOCDECL /* 10 */:
                            Q7 q7D = abstractC0663Md.d();
                            c2110mj.f |= 1;
                            c2110mj.g = q7D;
                            continue;
                        case 66:
                            Q7 q7D2 = abstractC0663Md.d();
                            c2110mj.f = 2 | c2110mj.f;
                            c2110mj.h = q7D2;
                            continue;
                        case 72:
                            int iF = abstractC0663Md.f();
                            if (iF == 1 || iF == 2 || iF == 3) {
                                c2110mj.f |= 32;
                                c2110mj.l = iF;
                                continue;
                            } else {
                                c2285ok0.a(9, iF);
                            }
                            break;
                        case 80:
                            c2110mj.f |= 4;
                            c2110mj.i = abstractC0663Md.c();
                            continue;
                        case 90:
                            Q7 q7D3 = abstractC0663Md.d();
                            c2110mj.f |= 64;
                            c2110mj.m = q7D3;
                            continue;
                        case 128:
                            c2110mj.f |= 128;
                            c2110mj.n = abstractC0663Md.c();
                            continue;
                        case 136:
                            c2110mj.f |= Fcntl.S_IRUSR;
                            c2110mj.o = abstractC0663Md.c();
                            continue;
                        case 144:
                            c2110mj.f |= 512;
                            c2110mj.p = abstractC0663Md.c();
                            continue;
                        case 160:
                            c2110mj.f |= 8;
                            c2110mj.j = abstractC0663Md.c();
                            continue;
                        case 184:
                            c2110mj.f |= Fcntl.S_ISUID;
                            c2110mj.r = abstractC0663Md.c();
                            continue;
                        case 216:
                            c2110mj.f |= 16;
                            c2110mj.k = abstractC0663Md.c();
                            continue;
                        case 248:
                            c2110mj.f |= 4096;
                            c2110mj.s = abstractC0663Md.c();
                            continue;
                        case 290:
                            Q7 q7D4 = abstractC0663Md.d();
                            c2110mj.f |= 8192;
                            c2110mj.t = q7D4;
                            continue;
                        case 298:
                            Q7 q7D5 = abstractC0663Md.d();
                            c2110mj.f |= 16384;
                            c2110mj.u = q7D5;
                            continue;
                        case 314:
                            Q7 q7D6 = abstractC0663Md.d();
                            c2110mj.f |= 32768;
                            c2110mj.v = q7D6;
                            continue;
                        case 322:
                            Q7 q7D7 = abstractC0663Md.d();
                            c2110mj.f |= 65536;
                            c2110mj.w = q7D7;
                            continue;
                        case 330:
                            Q7 q7D8 = abstractC0663Md.d();
                            c2110mj.f |= 131072;
                            c2110mj.x = q7D8;
                            continue;
                        case 336:
                            c2110mj.f |= Fcntl.S_ISGID;
                            c2110mj.q = abstractC0663Md.c();
                            continue;
                        case 354:
                            Q7 q7D9 = abstractC0663Md.d();
                            c2110mj.f |= 262144;
                            c2110mj.y = q7D9;
                            continue;
                        case 362:
                            Q7 q7D10 = abstractC0663Md.d();
                            c2110mj.f |= 524288;
                            c2110mj.z = q7D10;
                            continue;
                        case 7994:
                            if ((c & 0) == 0) {
                                c2110mj.A = new ArrayList();
                                c = 0;
                            }
                            c2110mj.A.add(abstractC0663Md.a(C0851Tj.o, c0415Co));
                            continue;
                        default:
                            if (!c2110mj.a(abstractC0663Md, c2285ok0, c0415Co, iS)) {
                                break;
                            }
                            break;
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c2110mj;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c2110mj;
                    throw rb;
                }
            } catch (Throwable th) {
                if ((c & 0) != 0) {
                    c2110mj.A = Collections.unmodifiableList(c2110mj.A);
                }
                c2110mj.d = c2285ok0.build();
                c2110mj.e.d();
                throw th;
            }
        }
        if ((c & 0) != 0) {
            c2110mj.A = Collections.unmodifiableList(c2110mj.A);
        }
        c2110mj.d = c2285ok0.build();
        c2110mj.e.d();
        return c2110mj;
    }
}
