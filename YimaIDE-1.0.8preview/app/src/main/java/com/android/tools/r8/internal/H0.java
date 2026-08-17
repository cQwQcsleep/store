package com.android.tools.r8.internal;

import defpackage.h3c;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class H0 implements SN, WN {
    public static C1601gk0 c(J0 j0) {
        ArrayList arrayList = new ArrayList();
        AbstractC1059aO.a(j0, XmlPullParser.NO_NAMESPACE, arrayList);
        return new C1601gk0(arrayList);
    }

    public H0 a(J0 j0) {
        Map mapF = j0.f();
        if (j0.e() != e()) {
            w01.a("mergeFrom(Message) can only merge messages of the same type.");
            return null;
        }
        for (Map.Entry entry : mapF.entrySet()) {
            C1856jk c1856jk = (C1856jk) entry.getKey();
            if (c1856jk.m()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    a(c1856jk, it.next());
                }
            } else if (c1856jk.h.b == EnumC1686hk.k) {
                J0 j1 = (J0) a(c1856jk);
                if (j1 == j1.b()) {
                    b(c1856jk, entry.getValue());
                } else {
                    b(c1856jk, j1.h().b(j1).b((J0) entry.getValue()).h());
                }
            } else {
                b(c1856jk, entry.getValue());
            }
        }
        a(j0.g());
        return this;
    }

    public abstract H0 a(C1856jk c1856jk, Object obj);

    public abstract H0 a(C2712tk0 c2712tk0);

    public abstract H0 b(J0 j0);

    @Override // com.android.tools.r8.internal.SN
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public H0 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        abstractC0663Md.getClass();
        C2712tk0 c2712tk0G = g();
        C2712tk0 c2712tk0 = C2712tk0.c;
        C2285ok0 c2285ok0A = new C2285ok0().a(c2712tk0G);
        while (true) {
            int iS = abstractC0663Md.s();
            if (iS == 0) {
                break;
            }
            AbstractC0663Md abstractC0663Md2 = abstractC0663Md;
            C0415Co c0415Co2 = c0415Co;
            if (!AbstractC1059aO.a(abstractC0663Md2, c2285ok0A, c0415Co2, e(), new XN(this), iS)) {
                break;
            }
            abstractC0663Md = abstractC0663Md2;
            c0415Co = c0415Co2;
        }
        b(c2285ok0A.build());
        return this;
    }

    public abstract H0 b(C1856jk c1856jk, Object obj);

    public abstract H0 b(C2712tk0 c2712tk0);

    public abstract H0 c(C1856jk c1856jk);

    public abstract C0955Xj e();

    public abstract J0 h();

    public abstract J0 i();

    public void j() {
        throw new IllegalStateException("Should be overridden by subclasses.");
    }

    public final String toString() {
        Logger logger = Sg0.a;
        return Qg0.b.a(this);
    }

    public final H0 a(U7 u7) throws RB {
        try {
            try {
                Q7 q7 = (Q7) u7;
                byte[] bArr = q7.e;
                int iD = q7.d();
                int size = q7.size();
                C0586Jd c0586Jd = new C0586Jd(bArr, iD, size, true);
                try {
                    c0586Jd.c(size);
                    a(c0586Jd, C3144yo.e);
                    c0586Jd.a(0);
                    return this;
                } catch (RB e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IOException e2) {
                h3c.a("Reading ", getClass().getName(), " from a ByteString threw an IOException (should never happen).", e2);
                return null;
            }
        } catch (RB e3) {
            throw e3;
        }
    }
}
