package com.android.tools.r8.naming;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C1242cb;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.Ck0;
import com.android.tools.r8.internal.GV;
import com.android.tools.r8.internal.InterfaceC1326db;
import com.android.tools.r8.internal.InterfaceC1936kh0;
import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.naming.V;
import com.android.tools.r8.naming.mappinginformation.e;
import defpackage.f63;
import defpackage.hn9;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.naming.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3331k implements InterfaceC3325h {
    public static final List h = Collections.EMPTY_LIST;
    public static final /* synthetic */ boolean i = true;
    public final String a;
    public final String b;
    public final AbstractC0706Nu c;
    public final AbstractC0706Nu d;
    public final Map<String, c> e;
    public final Map f;
    public final List g;

    public C3331k(String str, String str2, HashMap map, HashMap map2, Map map3, HashMap map4, List list) {
        this.b = str;
        this.a = str2;
        this.c = AbstractC0706Nu.a(map);
        this.d = AbstractC0706Nu.a(map2);
        this.e = map3;
        this.f = map4;
        this.g = list;
    }

    public final void a(final InterfaceC1326db interfaceC1326db) {
        interfaceC1326db.a(this.a).a(" -> ").a(this.b).a(":\n");
        this.g.forEach(new Consumer() { // from class: xfh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                interfaceC1326db.a("# " + ((e) obj).r()).a("\n");
            }
        });
        final String str = "    ";
        InterfaceC1936kh0 interfaceC1936kh0 = new InterfaceC1936kh0() { // from class: cgh
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                C3331k.a(interfaceC1326db, str, (V) obj);
            }
        };
        ArrayList arrayList = new ArrayList(this.d.values());
        Collections.sort(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            interfaceC1936kh0.accept((V) it.next());
        }
        ArrayList<b> arrayList2 = new ArrayList();
        Iterator<c> it2 = this.e.values().iterator();
        while (it2.hasNext()) {
            arrayList2.addAll(it2.next().a);
        }
        arrayList2.sort(Comparator.comparingInt(new ToIntFunction() { // from class: hgh
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((C3331k.b) obj).h;
            }
        }));
        for (b bVar : arrayList2) {
            interfaceC1326db.a("    ").a(bVar.toString()).a("\n");
            Iterator it3 = bVar.d().iterator();
            while (it3.hasNext()) {
                interfaceC1326db.a("      # ").a(((com.android.tools.r8.naming.mappinginformation.e) it3.next()).r()).a("\n");
            }
        }
    }

    public V b(V.c cVar) {
        if (cVar.f() == 1) {
            Ck0 it = this.c.values().iterator();
            while (it.hasNext()) {
                V v = (V) it.next();
                if (v.b().equals(cVar)) {
                    return v;
                }
            }
            return null;
        }
        if (!i && cVar.f() != 2) {
            x1f.a();
            return null;
        }
        Ck0 it2 = this.d.values().iterator();
        while (it2.hasNext()) {
            V v2 = (V) it2.next();
            if (v2.b().equals(cVar)) {
                return v2;
            }
        }
        return null;
    }

    public List<com.android.tools.r8.naming.mappinginformation.e> c() {
        return Collections.unmodifiableList(this.g);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3331k)) {
            return false;
        }
        C3331k c3331k = (C3331k) obj;
        return this.a.equals(c3331k.a) && this.b.equals(c3331k.b) && this.c.equals(c3331k.c) && this.d.equals(c3331k.d) && this.e.equals(c3331k.e);
    }

    public int hashCode() {
        return this.e.hashCode() + ((this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        a(new C1242cb(new hn9(sb)));
        return sb.toString();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.naming.k$a */
    public static class a extends AbstractC3323g {
        public static final /* synthetic */ boolean i = true;
        public final String a;
        public final String b;
        public final HashMap c = new HashMap();
        public final HashMap d = new HashMap();
        public final HashMap e = new HashMap();
        public final HashMap f = new HashMap();
        public List g = C3331k.h;
        public final BiConsumer h;

        public a(String str, String str2, BiConsumer biConsumer) {
            this.a = str2;
            this.b = str;
            this.h = biConsumer;
        }

        @Override // com.android.tools.r8.naming.AbstractC3323g
        public final boolean a(V.b bVar) {
            List list = (List) this.e.get(bVar.c());
            if (list == null) {
                return true;
            }
            List listA = C2847vL.a((Collection) list, new Predicate() { // from class: xgh
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return C3331k.a.a((C3331k.b) obj);
                }
            });
            if (listA.isEmpty()) {
                return true;
            }
            Iterator it = new c(listA).b().iterator();
            while (it.hasNext()) {
                List<b> listA2 = ((c) it.next()).a();
                Comparator comparatorComparing = Comparator.comparing(new Function() { // from class: ygh
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Integer.valueOf(((C3331k.b) obj).b.a);
                    }
                });
                ArrayList<b> arrayList = new ArrayList(listA2);
                arrayList.sort(comparatorComparing);
                N0 n0 = new N0(-1, -1, false);
                for (b bVar2 : arrayList) {
                    if (!bVar2.b.equals(n0)) {
                        N0 n1 = bVar2.b;
                        if (n1.a <= n0.b) {
                            if (i) {
                                return false;
                            }
                            x1f.a();
                            return false;
                        }
                        n0 = n1;
                    }
                }
            }
            return true;
        }

        public static /* synthetic */ List b(String str) {
            return new ArrayList();
        }

        @Override // com.android.tools.r8.naming.AbstractC3323g
        public final b a(N0 n0, V.b bVar, N0 n1, String str) {
            b bVar2 = new b(n0, bVar, n1, str);
            ((List) this.e.computeIfAbsent(str, new Function() { // from class: zgh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C3331k.a.a((String) obj);
                }
            })).add(bVar2);
            return bVar2;
        }

        @Override // com.android.tools.r8.naming.AbstractC3323g
        public final AbstractC3323g a(V v) {
            if (v.b.f() == 1) {
                this.c.put(v.c().b(), v);
                return this;
            }
            this.d.put(v.c().a(), v);
            ((List) this.f.computeIfAbsent(v.a(), new Function() { // from class: ahh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C3331k.a.b((String) obj);
                }
            })).add(v);
            return this;
        }

        public static /* synthetic */ List a(String str) {
            return new ArrayList();
        }

        @Override // com.android.tools.r8.naming.AbstractC3323g
        public final void a(Consumer consumer, com.android.tools.r8.naming.mappinginformation.e eVar) {
            if (this.g == C3331k.h) {
                this.g = new ArrayList();
            }
            for (com.android.tools.r8.naming.mappinginformation.e eVar2 : this.g) {
                if (!eVar2.a(eVar)) {
                    consumer.accept(eVar2);
                    return;
                }
            }
            this.g.add(eVar);
            eVar.getClass();
            if (eVar instanceof com.android.tools.r8.naming.mappinginformation.a) {
                this.h.accept(this.a, eVar.a().a);
            }
        }

        @Override // com.android.tools.r8.naming.AbstractC3323g
        public final V a(V.c cVar) {
            cVar.getClass();
            if (cVar instanceof V.a) {
                return (V) this.d.get(cVar.a());
            }
            return (V) this.c.get(cVar.b());
        }

        public static /* synthetic */ boolean a(b bVar) {
            return bVar.b != null;
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.naming.k$b */
    public static class b implements U {
        public static int j = 0;
        public static final /* synthetic */ boolean k = true;
        public final N0 b;
        public final V.b c;
        public final N0 d;
        public final String e;
        public V.b f = null;
        public boolean g = false;
        public final int h;
        public List i;

        public b(N0 n0, V.b bVar, N0 n1, String str) {
            int i;
            synchronized (this) {
                i = j;
                j = i + 1;
            }
            this.h = i;
            this.i = C3331k.h;
            this.b = n0;
            this.c = bVar;
            this.d = n1;
            this.e = str;
        }

        public final AbstractC0551Hu a(Function function, Predicate predicate) {
            C0473Eu c0473EuG = AbstractC0551Hu.g();
            for (com.android.tools.r8.naming.mappinginformation.e eVar : this.i) {
                if (predicate.test(eVar)) {
                    c0473EuG.a(function.apply(eVar));
                }
            }
            return c0473EuG.a();
        }

        public int b(int i) {
            N0 n0 = this.b;
            if (n0 != null) {
                if (!k && !n0.a(i)) {
                    x1f.a();
                    return 0;
                }
                N0 n1 = this.d;
                if (n1 != null) {
                    int i2 = n1.b;
                    int i3 = n1.a;
                    return i2 == i3 ? i2 : (i3 + i) - this.b.a;
                }
            }
            return i;
        }

        public final List d() {
            return Collections.unmodifiableList(this.i);
        }

        public final int e() {
            N0 n0 = this.d;
            if (n0 != null) {
                return n0.b;
            }
            N0 n1 = this.b;
            if (n1 != null) {
                return n1.b;
            }
            return Integer.MAX_VALUE;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return Objects.equals(this.b, bVar.b) && Objects.equals(this.d, bVar.d) && this.c.equals(bVar.c) && this.e.equals(bVar.e) && Objects.equals(this.f, bVar.f) && Objects.equals(this.i, bVar.i);
        }

        public final N0 f() {
            N0 n0 = this.d;
            return n0 != null ? n0 : this.b;
        }

        public final V.b g() {
            return this.c;
        }

        public final AbstractC0551Hu h() {
            return a(new Function() { // from class: ghh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((e) obj).c();
                }
            }, new Predicate() { // from class: fhh
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((e) obj).m();
                }
            });
        }

        public final int hashCode() {
            int iHashCode = Objects.hashCode(this.i) + ((Objects.hashCode(this.f) + ((this.e.hashCode() + ((this.c.hashCode() + ((Objects.hashCode(this.d) + (Objects.hashCode(this.b) * 31)) * 31)) * 31)) * 31)) * 31);
            this.g = true;
            return iHashCode;
        }

        public final GV i() {
            AbstractC0551Hu abstractC0551HuA = a(new Function() { // from class: ehh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((e) obj).d();
                }
            }, new Predicate() { // from class: dhh
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((e) obj).n();
                }
            });
            if (!k && abstractC0551HuA.size() > 1) {
                x1f.a();
                return null;
            }
            if (abstractC0551HuA.isEmpty()) {
                return null;
            }
            return (GV) abstractC0551HuA.get(0);
        }

        @Override // com.android.tools.r8.naming.U
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public final V.b c() {
            V.b bVar = this.f;
            if (bVar != null) {
                return bVar;
            }
            V.b bVar2 = this.c;
            return new V.b(this.e, bVar2.c, bVar2.d).b();
        }

        public final AbstractC0551Hu k() {
            return a(new Function() { // from class: chh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((e) obj).j();
                }
            }, new Predicate() { // from class: bhh
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((e) obj).q();
                }
            });
        }

        public final boolean l() {
            N0 n0 = this.d;
            return n0 != null && n0.a == 0 && n0.b == 0;
        }

        public final boolean m() {
            for (com.android.tools.r8.naming.mappinginformation.e eVar : this.i) {
                eVar.getClass();
                if (eVar instanceof GV) {
                    return true;
                }
            }
            return false;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            N0 n0 = this.b;
            if (n0 != null) {
                sb.append(n0);
                sb.append(':');
            }
            sb.append(this.c);
            N0 n1 = this.d;
            if (n1 != null && !n1.equals(this.b)) {
                sb.append(":");
                sb.append(this.d);
            }
            sb.append(" -> ");
            sb.append(this.e);
            return sb.toString();
        }

        @Override // com.android.tools.r8.naming.U
        public final V.c b() {
            return this.c;
        }

        public final void a(Consumer consumer, com.android.tools.r8.naming.mappinginformation.e eVar) {
            if (this.i == C3331k.h) {
                this.i = new ArrayList();
            }
            com.android.tools.r8.naming.mappinginformation.e.a(this.i, eVar, consumer);
        }

        @Override // com.android.tools.r8.naming.U
        public String a() {
            return this.e;
        }

        public final int a(int i) {
            N0 n0 = this.d;
            if (n0 == null) {
                N0 n1 = this.b;
                return n1 != null ? n1.a : i;
            }
            return n0.a;
        }

        public final void a(V.b bVar) {
            if (k || !this.g) {
                this.f = bVar;
            } else {
                x1f.a();
            }
        }

        public final void a(List list) {
            this.i = list;
        }

        public final b a(N0 n0) {
            return n0.equals(this.b) ? this : new b(n0, this.c, this.d, this.e);
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.naming.k$c */
    public static class c {
        public static final c b = new c(Collections.EMPTY_LIST);
        public static final /* synthetic */ boolean c = true;
        public final List a;

        public c(List list) {
            this.a = list;
        }

        public final List a(int i, boolean z) {
            b bVar = null;
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                b bVar2 = (b) this.a.get(i2);
                N0 n0 = bVar2.b;
                if (n0 != null) {
                    if (n0.a(i)) {
                        int i3 = i2 + 1;
                        while (i3 < this.a.size() && Objects.equals(((b) this.a.get(i3)).b, bVar2.b)) {
                            i3++;
                        }
                        return this.a.subList(i2, i3);
                    }
                } else if (bVar == null && z) {
                    bVar = bVar2;
                }
            }
            return bVar == null ? Collections.EMPTY_LIST : Collections.singletonList(bVar);
        }

        public final List b() {
            if (this.a.size() <= 1) {
                return Collections.singletonList(this);
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int iA = S.a(this.a, 0, arrayList2);
            if (((b) C2847vL.b(arrayList2)).c.equals(((b) C2847vL.b(this.a)).c)) {
                return Collections.singletonList(this);
            }
            while (iA < this.a.size()) {
                ArrayList arrayList3 = new ArrayList();
                iA = S.a(this.a, iA, arrayList3);
                if (!((b) C2847vL.b(arrayList2)).c.equals(((b) C2847vL.b(arrayList3)).c)) {
                    arrayList.add(new c(arrayList2));
                    arrayList2 = new ArrayList();
                }
                arrayList2.addAll(arrayList3);
            }
            arrayList.add(new c(arrayList2));
            return arrayList;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.a.equals(((c) obj).a);
        }

        public final int hashCode() {
            return this.a.hashCode();
        }

        public List<b> a(int i) {
            return a(i, true);
        }

        public List<b> a() {
            return this.a;
        }

        public b b(int i) {
            b bVar = null;
            for (b bVar2 : this.a) {
                N0 n0 = bVar2.b;
                if (n0 != null) {
                    if (n0.a(i)) {
                        return bVar2;
                    }
                } else if (bVar == null) {
                    bVar = bVar2;
                }
            }
            return bVar;
        }
    }

    public List<V> b(String str) {
        ArrayList arrayList = new ArrayList();
        Ck0 it = this.c.values().iterator();
        while (it.hasNext()) {
            V v = (V) it.next();
            if (v.b().a.equals(str)) {
                arrayList.add(v);
            }
        }
        Ck0 it2 = this.d.values().iterator();
        while (it2.hasNext()) {
            V v2 = (V) it2.next();
            if (v2.b().a.equals(str)) {
                arrayList.add(v2);
            }
        }
        return arrayList;
    }

    public Collection<V> b() {
        return this.c.values();
    }

    public final C3331k a(C3331k c3331k) {
        boolean zEquals = this.a.equals(c3331k.a);
        String str = this.b;
        if (!zEquals) {
            throw new RuntimeException("Cannot combine mapping for " + str + " because it maps back to both " + this.a + " and " + c3331k.a + ".");
        }
        if (!str.equals(c3331k.b)) {
            String str2 = this.a;
            throw new RuntimeException("Cannot combine mapping for " + str2 + " because it maps forward to both " + str2 + " and " + c3331k.a + ".");
        }
        if (this.c.isEmpty() && this.d.isEmpty()) {
            return c3331k;
        }
        if (c3331k.c.isEmpty() && c3331k.d.isEmpty()) {
            return this;
        }
        f63.a("R8 Retrace do not support merging of partial class mappings.");
        return null;
    }

    @Override // com.android.tools.r8.naming.InterfaceC3325h
    public V a(V.c cVar) {
        if (cVar.f() == 1) {
            if (!i && !(cVar instanceof V.b)) {
                x1f.a();
                return null;
            }
            return (V) this.c.get(cVar);
        }
        boolean z = i;
        if (!z && cVar.f() != 2) {
            x1f.a();
            return null;
        }
        if (!z && !(cVar instanceof V.a)) {
            x1f.a();
            return null;
        }
        return (V) this.d.get(cVar);
    }

    public Collection<V> a() {
        return this.d.values();
    }

    public final void a(final Consumer consumer) {
        this.f.values().forEach(new Consumer() { // from class: mgh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((List) obj).forEach(new Consumer() { // from class: zeh
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        C3331k.a(consumer, (V) obj2);
                    }
                });
            }
        });
        this.e.values().forEach(new Consumer() { // from class: qgh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C3331k.c) obj).a.forEach(new Consumer() { // from class: peh
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        C3331k.a(consumer, (C3331k.b) obj2);
                    }
                });
            }
        });
    }

    public static /* synthetic */ void a(Consumer consumer, V v) {
        if (v.b().e()) {
            consumer.accept(v.b().g());
        }
    }

    public static /* synthetic */ void a(Consumer consumer, b bVar) {
        if (bVar.c.e()) {
            consumer.accept(bVar.c.g());
        }
    }

    public c a(String str) {
        return this.e.get(str);
    }

    public static /* synthetic */ void a(InterfaceC1326db interfaceC1326db, String str, V v) {
        interfaceC1326db.a(str).a(v.toString()).a("\n");
        Iterator it = v.d().iterator();
        while (it.hasNext()) {
            interfaceC1326db.a(str + "  # ").a(((com.android.tools.r8.naming.mappinginformation.e) it.next()).r()).a("\n");
        }
    }
}
