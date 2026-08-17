package com.android.tools.r8.internal;

import com.android.tools.r8.TextInputStream;
import com.android.tools.r8.graph.AbstractC0327x0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AbstractC1333de0;
import com.android.tools.r8.internal.Vd0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.startup.StartupProfileBuilder;
import com.android.tools.r8.startup.StartupProfileProvider;
import com.android.tools.r8.startup.diagnostic.MissingStartupProfileItemsDiagnostic;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Vd0 implements InterfaceC1708i1 {
    public static Vd0 a(C2752uB c2752uB, Function function) throws Exception {
        Vd0 vd0A;
        Collection collection = c2752uB.L().g;
        if (collection == null || collection.isEmpty()) {
            vd0A = null;
        } else {
            Collection<StartupProfileProvider> collection2 = c2752uB.L().g;
            ArrayList arrayList = new ArrayList(collection2.size());
            for (StartupProfileProvider startupProfileProvider : collection2) {
                MissingStartupProfileItemsDiagnostic.a aVar = (MissingStartupProfileItemsDiagnostic.a) function.apply(startupProfileProvider.getOrigin());
                a aVarA = a(c2752uB, aVar, startupProfileProvider);
                startupProfileProvider.getStartupProfile(aVarA);
                arrayList.add(aVarA.build());
                if (!aVar.b.isEmpty()) {
                    c2752uB.i.warning(aVar.a());
                }
            }
            vd0A = a(arrayList);
        }
        return vd0A != null ? vd0A : new C1350dn();
    }

    public abstract Vd0 a(com.android.tools.r8.graph.I5 i5, Ch0 ch0);

    public abstract Vd0 a(AbstractC3148ys abstractC3148ys, Ch0 ch0);

    public abstract <E extends Exception> void a(InterfaceC1936kh0<? super AbstractC1333de0, E> interfaceC1936kh0) throws Exception;

    public abstract boolean a();

    public abstract Vd0 b(C0333y c0333y);

    public abstract boolean b(com.android.tools.r8.graph.I2 i2);

    public abstract Vd0 c(C0333y c0333y);

    public static class a implements InterfaceC1622h1, StartupProfileBuilder {
        public final com.android.tools.r8.graph.B1 a;
        public final MissingStartupProfileItemsDiagnostic.a b;
        public C2742u50 c;
        public final StartupProfileProvider d;
        public final LinkedHashMap e;

        public a(C2752uB c2752uB, MissingStartupProfileItemsDiagnostic.a aVar, StartupProfileProvider startupProfileProvider) {
            this.a = c2752uB.s();
            this.b = aVar;
            this.c = c2752uB.i;
            this.e = new LinkedHashMap();
            this.d = startupProfileProvider;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1622h1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Vd0 build() {
            return this.e.isEmpty() ? new C1350dn() : new VR(this.e);
        }

        @Override // com.android.tools.r8.startup.StartupProfileBuilder
        public final StartupProfileBuilder addHumanReadableArtProfile(TextInputStream textInputStream, Consumer consumer) {
            C2467qt.a aVarA = C2467qt.a().a(this.c).a(C1203c4.a(this));
            consumer.accept(aVarA);
            aVarA.a().a(textInputStream, this.d.getOrigin());
            return this;
        }

        @Override // com.android.tools.r8.startup.StartupProfileBuilder
        public final StartupProfileBuilder addStartupClass(Consumer consumer) {
            Xd0 xd0 = new Xd0(this.a);
            consumer.accept(xd0);
            com.android.tools.r8.graph.I2 i2 = xd0.b;
            Yd0 yd0 = new Yd0(i2);
            MissingStartupProfileItemsDiagnostic.a aVar = this.b;
            InterfaceC0189d1 interfaceC0189d1 = aVar.a;
            if (interfaceC0189d1 == null || interfaceC0189d1.f(i2)) {
                this.e.put(i2, yd0);
                return this;
            }
            com.android.tools.r8.graph.H2 h2 = aVar.a.a().L;
            com.android.tools.r8.graph.H2 h2Z0 = i2.z().z0();
            h2Z0.getClass();
            if (!h2Z0.b(h2.f)) {
                aVar.b.add(i2);
            }
            return this;
        }

        @Override // com.android.tools.r8.startup.StartupProfileBuilder
        public final StartupProfileBuilder addStartupMethod(Consumer consumer) {
            Zd0 zd0 = new Zd0(this.a);
            consumer.accept(zd0);
            C1080ae0 c1080ae0 = new C1080ae0(zd0.b);
            MissingStartupProfileItemsDiagnostic.a aVar = this.b;
            InterfaceC0189d1 interfaceC0189d1 = aVar.a;
            if (interfaceC0189d1 == null || interfaceC0189d1.a(c1080ae0.c())) {
                this.e.put(c1080ae0.c(), c1080ae0);
                return this;
            }
            C0322w2 c0322w2C = c1080ae0.c();
            com.android.tools.r8.graph.H2 h2 = aVar.a.a().L;
            com.android.tools.r8.graph.H2 h2Z0 = c0322w2C.z().z0();
            h2Z0.getClass();
            if (!h2Z0.b(h2.f)) {
                aVar.b.add(c0322w2C);
            }
            return this;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1622h1
        public final InterfaceC1622h1 a(InterfaceC2134n1 interfaceC2134n1) {
            interfaceC2134n1.getClass();
            AbstractC1333de0 abstractC1333de0 = (AbstractC1333de0) interfaceC2134n1;
            this.e.put(abstractC1333de0.c(), abstractC1333de0);
            return this;
        }

        public a(int i) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.e = new LinkedHashMap(i);
            this.d = null;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1622h1
        public final a a(C1080ae0 c1080ae0) {
            this.e.put(c1080ae0.c(), c1080ae0);
            return this;
        }

        public final a a(AbstractC1333de0 abstractC1333de0) {
            this.e.put(abstractC1333de0.c(), abstractC1333de0);
            return this;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1622h1
        public final InterfaceC1622h1 a(InterfaceC1877k1 interfaceC1877k1) {
            Yd0 yd0 = (Yd0) interfaceC1877k1;
            this.e.put(yd0.c(), yd0);
            return this;
        }

        public a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.e = new LinkedHashMap();
            this.d = null;
        }
    }

    public static Vd0 a(ArrayList arrayList) throws Exception {
        final a aVar = new a();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Vd0) it.next()).a(new InterfaceC1936kh0() { // from class: haf
                @Override // com.android.tools.r8.internal.InterfaceC1936kh0
                public final void accept(Object obj) {
                    aVar.a((AbstractC1333de0) obj);
                }
            });
        }
        return aVar.build();
    }

    public static a a(C2752uB c2752uB, MissingStartupProfileItemsDiagnostic.a aVar, StartupProfileProvider startupProfileProvider) {
        return new a(c2752uB, aVar, startupProfileProvider);
    }

    public static Vd0 a(final C0333y c0333y) {
        return a(c0333y.M(), new Function() { // from class: faf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Vd0.a(c0333y, (Origin) obj);
            }
        });
    }

    public static MissingStartupProfileItemsDiagnostic.a a(C0333y c0333y, Origin origin) {
        MissingStartupProfileItemsDiagnostic.a aVar = new MissingStartupProfileItemsDiagnostic.a(c0333y);
        aVar.c = origin;
        return aVar;
    }

    public static Vd0 a(AbstractC0327x0 abstractC0327x0) {
        return a(abstractC0327x0.d, new Function() { // from class: gaf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MissingStartupProfileItemsDiagnostic.a.b();
            }
        });
    }
}
