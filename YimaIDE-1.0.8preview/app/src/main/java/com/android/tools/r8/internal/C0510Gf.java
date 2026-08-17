package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0510Gf extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0951Xf c0951Xf = new C0951Xf();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    switch (iS) {
                        case 0:
                            break;
                        case 8:
                            c0951Xf.e = abstractC0663Md.t();
                            continue;
                        case Fcntl.S_IWGRP /* 16 */:
                            c0951Xf.f = abstractC0663Md.t();
                            continue;
                        case AndroidSdkVersion.O /* 26 */:
                            c0951Xf.g = abstractC0663Md.r();
                            continue;
                        case 32:
                            c0951Xf.h = abstractC0663Md.f();
                            continue;
                        case 40:
                            c0951Xf.i = abstractC0663Md.t();
                            continue;
                        case 48:
                            c0951Xf.j = abstractC0663Md.t();
                            continue;
                        case Fcntl.S_IRWXG /* 56 */:
                            c0951Xf.k = abstractC0663Md.t();
                            continue;
                        case 64:
                            c0951Xf.l = abstractC0663Md.t();
                            continue;
                        case 72:
                            c0951Xf.m = abstractC0663Md.t();
                            continue;
                        case 80:
                            c0951Xf.n = abstractC0663Md.f();
                            continue;
                        case 88:
                            c0951Xf.o = abstractC0663Md.f();
                            continue;
                        case 96:
                            c0951Xf.p = abstractC0663Md.f();
                            continue;
                        case 104:
                            c0951Xf.q = abstractC0663Md.f();
                            continue;
                        case 112:
                            c0951Xf.r = abstractC0663Md.f();
                            continue;
                        case 120:
                            c0951Xf.s = abstractC0663Md.f();
                            continue;
                        case 128:
                            c0951Xf.t = abstractC0663Md.f();
                            continue;
                        case 136:
                            c0951Xf.u = abstractC0663Md.f();
                            continue;
                        case 144:
                            c0951Xf.v = abstractC0663Md.t();
                            continue;
                        case 152:
                            c0951Xf.w = abstractC0663Md.f();
                            continue;
                        case 160:
                            c0951Xf.x = abstractC0663Md.f();
                            continue;
                        case 168:
                            c0951Xf.y = abstractC0663Md.f();
                            continue;
                        case 176:
                            c0951Xf.z = abstractC0663Md.f();
                            continue;
                        case 184:
                            c0951Xf.A = abstractC0663Md.f();
                            continue;
                        case 192:
                            c0951Xf.B = abstractC0663Md.t();
                            continue;
                        case 202:
                            c0951Xf.D = abstractC0663Md.r();
                            continue;
                        case 208:
                            c0951Xf.C = abstractC0663Md.f();
                            continue;
                        default:
                            if (!c2285ok0.a(iS, abstractC0663Md)) {
                                break;
                            }
                            break;
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c0951Xf;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c0951Xf;
                    throw rb;
                }
            } catch (Throwable th) {
                c0951Xf.d = c2285ok0.build();
                throw th;
            }
        }
        c0951Xf.d = c2285ok0.build();
        return c0951Xf;
    }
}
