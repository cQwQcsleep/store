package com.android.tools.r8.graph;

import com.android.tools.r8.DataResourceProvider;
import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.ProgramResourceProvider;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.graph.C0243l;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0629Ku;
import com.android.tools.r8.internal.C0882Uo;
import com.android.tools.r8.internal.C2098md;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.Ck0;
import com.android.tools.r8.internal.InterfaceC2706th0;
import com.android.tools.r8.shaking.C3403i;
import defpackage.hih;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0243l {
    public static final /* synthetic */ boolean d = true;
    public final C0333y a;
    public final AbstractC3148ys b;
    public final Map c;

    /* JADX INFO: renamed from: com.android.tools.r8.graph.l$a */
    public static class a {
        public final C0333y a;
        public final C2752uB b;
        public final LinkedHashMap c = new LinkedHashMap();

        public a(C0333y c0333y) {
            this.a = c0333y;
            this.b = c0333y.M();
        }

        public C0243l a() {
            Ck0 it = this.a.g().b().a.iterator();
            while (it.hasNext()) {
                try {
                    ((DataResourceProvider) it.next()).accept(new C0236k(this, FeatureSplit.BASE));
                } catch (ResourceException e) {
                    throw new C0613Ke(e.getMessage(), e);
                }
            }
            C0882Uo c0882Uo = this.b.o;
            if (c0882Uo != null) {
                for (FeatureSplit featureSplit : c0882Uo.a) {
                    Iterator<ProgramResourceProvider> it2 = featureSplit.getProgramResourceProviders().iterator();
                    while (it2.hasNext()) {
                        DataResourceProvider dataResourceProvider = it2.next().getDataResourceProvider();
                        if (dataResourceProvider != null) {
                            try {
                                dataResourceProvider.accept(new C0236k(this, featureSplit));
                            } catch (ResourceException e2) {
                                throw new C0613Ke(e2.getMessage(), e2);
                            }
                        }
                    }
                }
            }
            return new C0243l(this.a, this.c);
        }
    }

    public C0243l(C0333y c0333y, Map map) {
        this.a = c0333y;
        this.b = c0333y.A();
        this.c = map;
    }

    public final boolean a(C0333y c0333y, I2 i2) {
        if (((C3403i) c0333y.g()).n().b()) {
            return false;
        }
        Map map = (Map) this.c.get(i2);
        if (map == null || map.isEmpty()) {
            if (d) {
                return true;
            }
            hih.a("Unexpected attempt to get service implementations for non-service type `", i2.m0(), "`");
            return false;
        }
        if (map.keySet().stream().anyMatch(new Predicate() { // from class: qih
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0243l.a((FeatureSplit) obj);
            }
        })) {
            return true;
        }
        if (!d && map.size() > 2) {
            x1f.a();
            return false;
        }
        D2 d2B = c0333y.b(i2);
        if (d2B != null && C2098md.a(d2B, c0333y)) {
            return true;
        }
        for (Map.Entry entry : map.entrySet()) {
            FeatureSplit featureSplit = (FeatureSplit) entry.getKey();
            if (!d && !featureSplit.isBase()) {
                x1f.a();
                return false;
            }
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                D2 d2B2 = c0333y.b((I2) it.next());
                if (d2B2 != null && C2098md.a(d2B2, c0333y)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final Set b() {
        if (!d) {
            c();
        }
        final Set setC = AbstractC2780ub0.c();
        this.c.forEach(new BiConsumer() { // from class: nih
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Map) obj2).forEach(new BiConsumer() { // from class: wih
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj3, Object obj4) {
                        set.addAll((List) obj4);
                    }
                });
            }
        });
        return setC;
    }

    public final void c() {
        for (Map.Entry entry : this.c.entrySet()) {
            if (!d) {
                if (entry.getKey() != this.a.A().d(this.b, (I2) entry.getKey())) {
                    x1f.a();
                    return;
                }
            }
            Iterator it = ((Map) entry.getValue()).entrySet().iterator();
            while (it.hasNext()) {
                for (I2 i2 : (List) ((Map.Entry) it.next()).getValue()) {
                    if (!d && i2 != this.a.A().d(this.b, i2)) {
                        x1f.a();
                        return;
                    }
                }
            }
        }
    }

    public final AbstractC0551Hu a(I2 i2) {
        boolean z = d;
        if (!z) {
            c();
        }
        Map map = (Map) this.c.get(i2);
        if (map == null) {
            if (z) {
                return AbstractC0551Hu.i();
            }
            hih.a("Unexpected attempt to get service implementations for non-service type `", i2.m0(), "`");
            return null;
        }
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            c0473EuG.b((Iterable) it.next());
        }
        return c0473EuG.a();
    }

    public final Set a() {
        if (!d) {
            c();
        }
        return this.c.keySet();
    }

    public static /* synthetic */ boolean a(FeatureSplit featureSplit) {
        return !featureSplit.isBase();
    }

    public final C0243l a(final AbstractC3148ys abstractC3148ys, Ch0 ch0) {
        return (C0243l) ch0.a("Rewrite AppServices", new InterfaceC2706th0() { // from class: vih
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(abstractC3148ys);
            }
        });
    }

    public final C0243l a(AbstractC3148ys abstractC3148ys) {
        C0629Ku c0629KuE = AbstractC0706Nu.e();
        for (Map.Entry entry : this.c.entrySet()) {
            I2 i2D = abstractC3148ys.d(this.b, (I2) entry.getKey());
            C0629Ku c0629KuE2 = AbstractC0706Nu.e();
            for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                C0473Eu c0473EuG = AbstractC0551Hu.g();
                Iterator it = ((List) entry2.getValue()).iterator();
                while (it.hasNext()) {
                    c0473EuG.a(abstractC3148ys.d(this.b, (I2) it.next()));
                }
                c0629KuE2.a((FeatureSplit) entry2.getKey(), c0473EuG.a());
            }
            c0629KuE.a(i2D, c0629KuE2.b());
        }
        return new C0243l(this.a, c0629KuE.b());
    }

    public final void a(final BiConsumer biConsumer) {
        this.c.forEach(new BiConsumer() { // from class: xih
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C0243l.a(biConsumer, (I2) obj, (Map) obj2);
            }
        });
    }

    public static /* synthetic */ void a(BiConsumer biConsumer, I2 i2, Map map) {
        final C0473Eu c0473EuG = AbstractC0551Hu.g();
        map.values().forEach(new Consumer() { // from class: uih
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c0473EuG.b((Iterable) obj);
            }
        });
        biConsumer.accept(i2, c0473EuG.a());
    }

    public static a a(C0333y<?> c0333y) {
        return new a(c0333y);
    }
}
