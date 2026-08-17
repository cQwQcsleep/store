package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2746u70 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C70 c70 = new C70();
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
                                A70 a70D = c70.e == 1 ? ((B70) c70.f).d() : null;
                                TN tnA = abstractC0663Md.a(B70.g, c0415Co);
                                c70.f = tnA;
                                if (a70D != null) {
                                    a70D.a((B70) tnA);
                                    B70 b70 = new B70(a70D);
                                    a70D.o();
                                    c70.f = b70;
                                }
                                c70.e = 1;
                                continue;
                            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                                C3003x70 c3003x70D = c70.e == 2 ? ((C3087y70) c70.f).d() : null;
                                TN tnA2 = abstractC0663Md.a(C3087y70.g, c0415Co);
                                c70.f = tnA2;
                                if (c3003x70D != null) {
                                    c3003x70D.a((C3087y70) tnA2);
                                    C3087y70 c3087y70 = new C3087y70(c3003x70D);
                                    c3003x70D.o();
                                    c70.f = c3087y70;
                                }
                                c70.e = 2;
                                continue;
                            case AndroidSdkVersion.Q /* 29 */:
                                c70.f = Float.valueOf(abstractC0663Md.i());
                                c70.e = 3;
                                continue;
                            case 37:
                                c70.f = Float.valueOf(abstractC0663Md.i());
                                c70.e = 4;
                                continue;
                            case 45:
                                c70.f = Float.valueOf(abstractC0663Md.i());
                                c70.e = 5;
                                continue;
                            case 48:
                                c70.f = Integer.valueOf(abstractC0663Md.j());
                                c70.e = 6;
                                continue;
                            case Fcntl.S_IRWXG /* 56 */:
                                c70.f = Integer.valueOf(abstractC0663Md.t());
                                c70.e = 7;
                                continue;
                            case 64:
                                c70.f = Boolean.valueOf(abstractC0663Md.c());
                                c70.e = 8;
                                continue;
                            case 72:
                                c70.f = Integer.valueOf(abstractC0663Md.t());
                                c70.e = 9;
                                continue;
                            case 80:
                                c70.f = Integer.valueOf(abstractC0663Md.t());
                                c70.e = 10;
                                continue;
                            case 88:
                                c70.f = Integer.valueOf(abstractC0663Md.t());
                                c70.e = 11;
                                continue;
                            case 96:
                                c70.f = Integer.valueOf(abstractC0663Md.t());
                                c70.e = 12;
                                continue;
                            case 104:
                                c70.f = Integer.valueOf(abstractC0663Md.t());
                                c70.e = 13;
                                continue;
                            case 112:
                                c70.f = Integer.valueOf(abstractC0663Md.t());
                                c70.e = 14;
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
                        rb.b = c70;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = c70;
                    throw e2;
                }
            } catch (Throwable th) {
                c70.d = c2285ok0.build();
                throw th;
            }
        }
        c70.d = c2285ok0.build();
        return c70;
    }
}
