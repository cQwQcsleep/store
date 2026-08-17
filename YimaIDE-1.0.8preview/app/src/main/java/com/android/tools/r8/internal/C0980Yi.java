package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Yi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0980Yi extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1258cj c1258cj = new C1258cj();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int iS = abstractC0663Md.s();
                        switch (iS) {
                            case 0:
                                break;
                            case XmlPullParser.DOCDECL /* 10 */:
                                Q7 q7D = abstractC0663Md.d();
                                c1258cj.e |= 1;
                                c1258cj.f = q7D;
                                continue;
                            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                                Q7 q7D2 = abstractC0663Md.d();
                                c1258cj.e |= 32;
                                c1258cj.k = q7D2;
                                continue;
                            case AndroidSdkVersion.N /* 24 */:
                                c1258cj.e |= 2;
                                c1258cj.g = abstractC0663Md.j();
                                continue;
                            case 32:
                                int iF = abstractC0663Md.f();
                                if (iF == 1 || iF == 2 || iF == 3) {
                                    c1258cj.e |= 4;
                                    c1258cj.h = iF;
                                    continue;
                                } else {
                                    c2285ok0.a(4, iF);
                                }
                                break;
                            case 40:
                                int iF2 = abstractC0663Md.f();
                                if (AbstractC1175bj.a(iF2) == 0) {
                                    c2285ok0.a(5, iF2);
                                } else {
                                    c1258cj.e |= 8;
                                    c1258cj.i = iF2;
                                    continue;
                                }
                                break;
                            case 50:
                                Q7 q7D3 = abstractC0663Md.d();
                                c1258cj.e |= 16;
                                c1258cj.j = q7D3;
                                continue;
                            case 58:
                                Q7 q7D4 = abstractC0663Md.d();
                                c1258cj.e |= 64;
                                c1258cj.l = q7D4;
                                continue;
                            case 66:
                                C1427ej c1427ejD = (c1258cj.e & 512) != 0 ? c1258cj.o.d() : null;
                                C1513fj c1513fj = (C1513fj) abstractC0663Md.a(C1513fj.p, c0415Co);
                                c1258cj.o = c1513fj;
                                if (c1427ejD != null) {
                                    c1427ejD.a(c1513fj);
                                    c1258cj.o = c1427ejD.i();
                                }
                                c1258cj.e |= 512;
                                continue;
                            case 72:
                                c1258cj.e |= 128;
                                c1258cj.m = abstractC0663Md.j();
                                continue;
                            case 82:
                                Q7 q7D5 = abstractC0663Md.d();
                                c1258cj.e |= Fcntl.S_IRUSR;
                                c1258cj.n = q7D5;
                                continue;
                            case 136:
                                c1258cj.e |= Fcntl.S_ISGID;
                                c1258cj.p = abstractC0663Md.c();
                                continue;
                            default:
                                if (!c2285ok0.a(iS, abstractC0663Md)) {
                                    break;
                                }
                                break;
                        }
                        z = true;
                    } catch (IOException e) {
                        RB rb = new RB(e);
                        rb.b = c1258cj;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = c1258cj;
                    throw e2;
                }
            } catch (Throwable th) {
                c1258cj.d = c2285ok0.build();
                throw th;
            }
        }
        c1258cj.d = c2285ok0.build();
        return c1258cj;
    }
}
