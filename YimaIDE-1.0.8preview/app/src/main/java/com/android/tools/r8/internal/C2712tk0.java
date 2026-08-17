package com.android.tools.r8.internal;

import defpackage.e7f;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tk0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2712tk0 implements TN {
    public static final C2712tk0 c = new C2712tk0(new TreeMap());
    public final TreeMap b;

    public C2712tk0(TreeMap treeMap) {
        this.b = treeMap;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        for (Map.Entry entry : this.b.entrySet()) {
            C2456qk0 c2456qk0 = (C2456qk0) entry.getValue();
            int iIntValue = ((Integer) entry.getKey()).intValue();
            Iterator it = c2456qk0.a.iterator();
            while (it.hasNext()) {
                long jLongValue = ((Long) it.next()).longValue();
                C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
                c0689Nd.c(iIntValue, 0);
                c0689Nd.c(jLongValue);
            }
            Iterator it2 = c2456qk0.b.iterator();
            while (it2.hasNext()) {
                int iIntValue2 = ((Integer) it2.next()).intValue();
                C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
                c0689Nd2.c(iIntValue, 5);
                c0689Nd2.d(iIntValue2);
            }
            Iterator it3 = c2456qk0.c.iterator();
            while (it3.hasNext()) {
                long jLongValue2 = ((Long) it3.next()).longValue();
                C0689Nd c0689Nd3 = (C0689Nd) abstractC0793Rd;
                c0689Nd3.c(iIntValue, 1);
                c0689Nd3.b(jLongValue2);
            }
            for (U7 u7 : c2456qk0.d) {
                C0689Nd c0689Nd4 = (C0689Nd) abstractC0793Rd;
                c0689Nd4.c(iIntValue, 2);
                c0689Nd4.b(u7);
            }
            for (C2712tk0 c2712tk0 : c2456qk0.e) {
                abstractC0793Rd.c(iIntValue, 3);
                c2712tk0.a(abstractC0793Rd);
                abstractC0793Rd.c(iIntValue, 4);
            }
        }
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        if (this.b.isEmpty()) {
            return 0;
        }
        int i = 0;
        for (Map.Entry entry : this.b.entrySet()) {
            C2456qk0 c2456qk0 = (C2456qk0) entry.getValue();
            int iIntValue = ((Integer) entry.getKey()).intValue();
            Iterator it = c2456qk0.a.iterator();
            int iC = 0;
            while (it.hasNext()) {
                iC += AbstractC0793Rd.a(((Long) it.next()).longValue()) + AbstractC0793Rd.b(iIntValue);
            }
            Iterator it2 = c2456qk0.b.iterator();
            while (it2.hasNext()) {
                ((Integer) it2.next()).getClass();
                iC += AbstractC0793Rd.b(iIntValue) + 4;
            }
            Iterator it3 = c2456qk0.c.iterator();
            while (it3.hasNext()) {
                ((Long) it3.next()).getClass();
                iC += AbstractC0793Rd.b(iIntValue) + 8;
            }
            Iterator it4 = c2456qk0.d.iterator();
            while (it4.hasNext()) {
                iC += AbstractC0793Rd.a((U7) it4.next()) + AbstractC0793Rd.b(iIntValue);
            }
            Iterator it5 = c2456qk0.e.iterator();
            while (it5.hasNext()) {
                iC += ((C2712tk0) it5.next()).c() + (AbstractC0793Rd.b(iIntValue) * 2);
            }
            i += iC;
        }
        return i;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return new C2285ok0().a(this);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C2712tk0) && this.b.equals(((C2712tk0) obj).b);
    }

    public final int hashCode() {
        if (this.b.isEmpty()) {
            return 0;
        }
        return this.b.hashCode();
    }

    public final String toString() {
        Logger logger = Sg0.a;
        Qg0.b.getClass();
        try {
            StringBuilder sb = new StringBuilder();
            Qg0.a(this, new Rg0(sb));
            return sb.toString();
        } catch (IOException e) {
            e7f.a(e);
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        return true;
    }
}
