package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ih0 implements BiConsumer {
    public final /* synthetic */ H50 a;

    public Ih0(H50 h50) {
        this.a = h50;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = (String) obj2;
        if (str == null) {
            return;
        }
        int iHashCode = str.hashCode();
        if (iHashCode == -1354971316) {
            if (str.equals("shrinkMode") && str2 != null && str2.hashCode() == -891986231 && str2.equals("strict")) {
                this.a.b.i = false;
                return;
            }
            return;
        }
        if (iHashCode != 3287941) {
            if (iHashCode != 1671366814 || !str.equals("discard")) {
                return;
            }
            R50 r50 = this.a.b;
            KB.b(str2, "value");
            r50.getClass();
            C1502fd0 c1502fd0 = new C1502fd0(new C1838jb(','));
            C2180nb c2180nb = C2180nb.c;
            C2266ob c2266ob = C2266ob.d;
            c2266ob.getClass();
            Iterator itA = c1502fd0.a(new C1928kd0(c1502fd0, true, c2266ob, Integer.MAX_VALUE), str2);
            while (true) {
                AbstractC1759id0 abstractC1759id0 = (AbstractC1759id0) itA;
                if (!abstractC1759id0.hasNext()) {
                    return;
                }
                r50.h.add((String) abstractC1759id0.next());
            }
        } else {
            if (!str.equals("keep")) {
                return;
            }
            R50 r51 = this.a.b;
            KB.b(str2, "value");
            r51.getClass();
            C1502fd0 c1502fd1 = new C1502fd0(new C1838jb(','));
            C2180nb c2180nb2 = C2180nb.c;
            C2266ob c2266ob2 = C2266ob.d;
            c2266ob2.getClass();
            Iterator itA2 = c1502fd1.a(new C1928kd0(c1502fd1, true, c2266ob2, Integer.MAX_VALUE), str2);
            while (true) {
                AbstractC1759id0 abstractC1759id1 = (AbstractC1759id0) itA2;
                if (!abstractC1759id1.hasNext()) {
                    return;
                }
                r51.g.add((String) abstractC1759id1.next());
            }
        }
    }
}
