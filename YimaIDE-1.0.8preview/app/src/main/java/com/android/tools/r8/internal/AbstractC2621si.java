package com.android.tools.r8.internal;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.si, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2621si {
    public static final /* synthetic */ boolean c = true;
    public final ArrayDeque a = new ArrayDeque();
    public final IdentityHashMap b = new IdentityHashMap();

    public final AbstractC1597gi0 a(H5 h5) {
        AbstractC1597gi0 abstractC1597gi0B = b((Collection) Collections.singletonList(h5));
        Function functionIdentity = Function.identity();
        Function function = new Function() { // from class: yai
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((List) obj).get(0);
            }
        };
        if (abstractC1597gi0B.c()) {
            return new C1341di0(functionIdentity.apply(abstractC1597gi0B.a().e()));
        }
        if (AbstractC1597gi0.a || abstractC1597gi0B.d()) {
            return new C1512fi0(function.apply(abstractC1597gi0B.b().e()));
        }
        x1f.a();
        return null;
    }

    public abstract AbstractC1597gi0 a(C2280oi c2280oi);

    public abstract C2280oi a(Object obj);

    public abstract List a(Collection collection);

    public abstract AbstractC1597gi0 b(C2280oi c2280oi);

    public final AbstractC1597gi0 b(Collection collection) {
        AbstractC1597gi0 abstractC1597gi0B;
        collection.forEach(new Consumer() { // from class: wai
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(obj);
            }
        });
        while (!this.a.isEmpty()) {
            C2280oi c2280oi = (C2280oi) this.a.removeLast();
            int i = c2280oi.b;
            if (i != 3) {
                if (i == 1) {
                    this.a.addLast(c2280oi);
                    c2280oi.b = 2;
                    abstractC1597gi0B = b(c2280oi);
                } else {
                    if (!c && i != 2) {
                        x1f.a();
                        return null;
                    }
                    AbstractC1597gi0 abstractC1597gi0A = a(c2280oi);
                    if (!C2280oi.c && c2280oi.b == 3) {
                        x1f.a();
                        return null;
                    }
                    c2280oi.b = 3;
                    abstractC1597gi0B = abstractC1597gi0A;
                }
                if (abstractC1597gi0B.c()) {
                    return new C1341di0(abstractC1597gi0B.a().e());
                }
            }
        }
        return new C1512fi0(a(collection));
    }

    public final C2280oi b(Object obj) {
        C2280oi c2280oi = (C2280oi) this.b.computeIfAbsent(obj, new Function() { // from class: xai
            @Override // java.util.function.Function
            public final Object apply(Object obj2) {
                return this.b.a(obj2);
            }
        });
        if (c2280oi.b == 1) {
            this.a.addLast(c2280oi);
        }
        return c2280oi;
    }
}
