package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2536ri;
import com.android.tools.r8.internal.C2366pi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ri, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2536ri extends AbstractC2621si {
    public static final /* synthetic */ boolean e = true;
    public final IdentityHashMap d = new IdentityHashMap();

    public static /* synthetic */ List c(C2366pi c2366pi) {
        if (e) {
            return new ArrayList();
        }
        x01.a("Unexpected joining of not visited node");
        return null;
    }

    public abstract C1512fi0 a(C2366pi c2366pi, List list);

    @Override // com.android.tools.r8.internal.AbstractC2621si
    public final AbstractC1597gi0 a(C2366pi c2366pi) {
        return a(c2366pi, (List) this.d.computeIfAbsent(c2366pi, new Function() { // from class: e8i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC2536ri.c((C2366pi) obj);
            }
        }));
    }

    public abstract AbstractC1597gi0 a(C2366pi c2366pi, Function function);

    @Override // com.android.tools.r8.internal.AbstractC2621si
    public final AbstractC1597gi0 b(C2366pi c2366pi) {
        final ArrayList arrayList = new ArrayList();
        List list = (List) this.d.put(c2366pi, arrayList);
        if (e || list == null) {
            return a(c2366pi, new Function() { // from class: d8i
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a(arrayList, obj);
                }
            });
        }
        x1f.a();
        return null;
    }

    public final Object c(Object obj) {
        return ((C2366pi) ((C2280oi) this.b.get(obj))).d;
    }

    @Override // com.android.tools.r8.internal.AbstractC2621si
    public final C2280oi a(Object obj) {
        return new C2366pi(obj);
    }

    public final /* synthetic */ C2366pi a(List list, Object obj) {
        C2366pi c2366pi = (C2366pi) b(obj);
        list.add(c2366pi);
        return c2366pi;
    }

    @Override // com.android.tools.r8.internal.AbstractC2621si
    public final List a(Collection collection) {
        return C2847vL.a(collection, new Function() { // from class: c8i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.c(obj);
            }
        });
    }
}
