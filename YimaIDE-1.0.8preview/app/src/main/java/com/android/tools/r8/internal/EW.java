package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.EW;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class EW {
    public static final /* synthetic */ boolean a = true;

    public static void a(C1650hK c1650hK, final List list) {
        if (!a && list.isEmpty()) {
            x1f.a();
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) it.next();
            for (C2543rl0 c2543rl0 : abstractC0890Uw.c) {
                c2543rl0.d.add(abstractC0890Uw);
                c2543rl0.e = null;
            }
            c1650hK.add(abstractC0890Uw);
        }
        AbstractC0890Uw next = (AbstractC0890Uw) c1650hK.a(new Predicate() { // from class: j44
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return EW.a(list, (AbstractC0890Uw) obj);
            }
        });
        for (int i = 0; i < list.size(); i++) {
            if (!a && next != list.get(i)) {
                x1f.a();
                return;
            } else {
                c1650hK.p();
                next = c1650hK.next();
            }
        }
        c1650hK.c(new Predicate() { // from class: k44
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return EW.b(list, (AbstractC0890Uw) obj);
            }
        });
        c1650hK.next();
    }

    public static int b(AbstractC0890Uw abstractC0890Uw) {
        C2543rl0 c2543rl0C = abstractC0890Uw.c();
        if (c2543rl0C instanceof Pd0) {
            return ((Pd0) c2543rl0C).r.length;
        }
        return (c2543rl0C == null || !c2543rl0C.P()) ? 0 : 1;
    }

    public static /* synthetic */ boolean b(List list, AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw == list.get(list.size() - 1);
    }

    public static /* synthetic */ boolean a(Predicate predicate, AbstractC0890Uw abstractC0890Uw) {
        if (!predicate.test(abstractC0890Uw) || abstractC0890Uw.a1()) {
            return false;
        }
        return abstractC0890Uw.c() == null || !abstractC0890Uw.c().y();
    }

    public static void a(C1650hK c1650hK, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            c1650hK.previous();
        }
    }

    public static Predicate a(final Predicate predicate) {
        return new Predicate() { // from class: i44
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return EW.a(predicate, (AbstractC0890Uw) obj);
            }
        };
    }

    public static /* synthetic */ boolean a(List list, AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw == list.get(0);
    }

    public static int a(AbstractC0890Uw abstractC0890Uw) {
        int i = 0;
        for (int size = abstractC0890Uw.c.size() - 1; size >= 0; size--) {
            if (((C2543rl0) abstractC0890Uw.c.get(size)).P()) {
                i++;
            }
        }
        return i;
    }
}
