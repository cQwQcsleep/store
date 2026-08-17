package com.android.tools.r8.naming;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.AbstractC2895vu;
import com.android.tools.r8.internal.C0629Ku;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1242cb;
import com.android.tools.r8.internal.C2522rb;
import com.android.tools.r8.internal.C2607sb;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2809uu;
import com.android.tools.r8.internal.Ck0;
import com.android.tools.r8.internal.InterfaceC1326db;
import com.android.tools.r8.internal.S5;
import com.android.tools.r8.naming.C3313b;
import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.position.Position;
import defpackage.hn9;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.naming.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3313b {
    public static final /* synthetic */ boolean g = true;
    public final AbstractC0706Nu a;
    public S5 b;
    public final ConcurrentHashMap c = new ConcurrentHashMap();
    public final LinkedHashSet d;
    public final Map e;
    public List f;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.android.tools.r8.naming.b$b, reason: collision with other inner class name */
    public static final class EnumC0003b {
        public static final EnumC0003b b = new EnumC0003b(0, "MISSING_FILE_IS_EMPTY_MAP");
        public static final EnumC0003b c = new EnumC0003b(1, "MISSING_FILE_IS_ERROR");

        public EnumC0003b(int i, String str) {
            super(str, i);
        }
    }

    public C3313b(AbstractC0706Nu abstractC0706Nu, LinkedHashSet linkedHashSet, HashMap map, List list) {
        this.a = abstractC0706Nu;
        this.d = linkedHashSet;
        this.e = map;
        this.f = list;
    }

    public static C3313b c(String str) throws IOException {
        Reader readerA = (str != null ? new C2607sb(str) : new C2522rb(str)).a();
        return a(readerA instanceof BufferedReader ? (BufferedReader) readerA : new BufferedReader(readerA), (DiagnosticsHandler) null, false, false, false);
    }

    public static C3313b d(String str) throws IOException {
        Reader readerA = (str != null ? new C2607sb(str) : new C2522rb(str)).a();
        return a(readerA instanceof BufferedReader ? (BufferedReader) readerA : new BufferedReader(readerA), (DiagnosticsHandler) null, false, false, true);
    }

    public final C3313b a(C3313b c3313b) {
        List list;
        if (c3313b == null) {
            return this;
        }
        if (c3313b.a.isEmpty() && c3313b.f.isEmpty()) {
            return this;
        }
        if (this.a.isEmpty() && this.f.isEmpty()) {
            return c3313b;
        }
        final C0629Ku c0629KuE = AbstractC0706Nu.e();
        AbstractC0706Nu abstractC0706Nu = c3313b.a;
        Ck0 it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            C3331k c3331k = (C3331k) abstractC0706Nu.get(entry.getKey());
            if (c3331k == null) {
                c0629KuE.a(entry.getKey(), entry.getValue());
            } else {
                c0629KuE.a((String) entry.getKey(), ((C3331k) entry.getValue()).a(c3331k));
            }
        }
        abstractC0706Nu.forEach(new BiConsumer() { // from class: wfg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(c0629KuE, (String) obj, (C3331k) obj2);
            }
        });
        LinkedHashSet linkedHashSet = new LinkedHashSet(d());
        linkedHashSet.addAll(c3313b.d());
        HashMap map = new HashMap(this.e);
        map.putAll(c3313b.e);
        List list2 = Collections.EMPTY_LIST;
        if (!this.f.isEmpty() || !c3313b.f.isEmpty()) {
            list = list2;
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.f);
            arrayList.addAll(c3313b.f);
            list = arrayList;
        }
        list = list2;
        return new C3313b(c0629KuE.b(), linkedHashSet, map, list);
    }

    public C3331k b(String str) {
        return (C3331k) this.a.get(str);
    }

    public final HashSet e() {
        final HashSet hashSet = new HashSet();
        this.a.forEach(new BiConsumer() { // from class: ofg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                hashSet.add(C0929Wj.r((String) obj));
            }
        });
        return hashSet;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C3313b) && this.a.equals(((C3313b) obj).a);
    }

    public S5<String, String> f() {
        if (this.b == null) {
            C2809uu c2809uu = new C2809uu();
            Ck0 it = this.a.keySet().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                c2809uu.a((Object) ((C3331k) this.a.get(str)).a, str);
            }
            AbstractC2895vu abstractC2895vuC = c2809uu.b();
            this.b = new S5(abstractC2895vuC, abstractC2895vuC.f());
        }
        return this.b;
    }

    public C3313b g() {
        final C0629Ku c0629KuE = AbstractC0706Nu.e();
        Comparator comparatorComparing = Comparator.comparing(new Function() { // from class: seg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C3331k) obj).a;
            }
        });
        if (!(c0629KuE.a == null)) {
            k2d.a("valueComparator was already set");
            return null;
        }
        if (comparatorComparing == null) {
            x0e.a("valueComparator");
            return null;
        }
        c0629KuE.a = comparatorComparing;
        this.a.forEach(new BiConsumer() { // from class: efg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c0629KuE.a((String) obj, (C3331k) obj2);
            }
        });
        return new C3313b(c0629KuE.b(), this.d, (HashMap) this.e, this.f);
    }

    public final int hashCode() {
        return this.a.hashCode() * 31;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        a(new C1242cb(new hn9(sb)));
        return sb.toString();
    }

    public final AbstractC0706Nu b() {
        return this.a;
    }

    public static void b(boolean z, a aVar) {
        aVar.a = z;
    }

    /* JADX INFO: renamed from: com.android.tools.r8.naming.b$a */
    public static class a extends x0 {
        public boolean a = false;
        public boolean b = false;
        public final ArrayList c = new ArrayList();
        public final HashMap d = new HashMap();
        public final LinkedHashSet e = new LinkedHashSet();
        public final HashMap f = new HashMap();

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.HashMap] */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v4, types: [java.util.Map] */
        /* JADX WARN: Type inference failed for: r10v0, types: [java.util.Map] */
        public static void a(C0629Ku c0629Ku, String str, C3331k.a aVar) {
            ?? map;
            if (aVar.e.isEmpty()) {
                map = Collections.EMPTY_MAP;
            } else {
                map = new HashMap(aVar.e.size());
                for (Map.Entry entry : aVar.e.entrySet()) {
                    map.put((String) entry.getKey(), new C3331k.c((List) entry.getValue()));
                }
            }
            c0629Ku.a(str, new C3331k(aVar.b, aVar.a, aVar.c, aVar.d, map, aVar.f, aVar.g));
        }

        public final AbstractC0706Nu b() {
            final C0629Ku c0629KuE = AbstractC0706Nu.e();
            this.d.forEach(new BiConsumer() { // from class: hgg
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    C3313b.a.a(c0629KuE, (String) obj, (C3331k.a) obj2);
                }
            });
            return c0629KuE.b();
        }

        public final boolean b(String str) {
            return this.d.containsKey(str);
        }

        public C3331k.a a(String str, String str2, Position position) {
            final HashMap map = this.f;
            Objects.requireNonNull(map);
            BiConsumer biConsumer = new BiConsumer() { // from class: igg
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    map.put((String) obj, (String) obj2);
                }
            };
            List list = C3331k.h;
            C3331k.a aVar = new C3331k.a(str, str2, biConsumer);
            this.d.put(str, aVar);
            return aVar;
        }

        @Override // com.android.tools.r8.naming.x0
        public final void a(String str) {
            if (this.a) {
                this.c.add(str);
            }
        }

        @Override // com.android.tools.r8.naming.x0
        public final /* bridge */ /* synthetic */ AbstractC3323g a(String str, String str2, F0 f0) {
            return a(str, str2, (Position) f0);
        }

        public C3313b a() {
            return new C3313b(b(), this.e, this.f, this.c);
        }

        @Override // com.android.tools.r8.naming.x0
        public final x0 a(com.android.tools.r8.naming.mappinginformation.b bVar) {
            this.e.add(bVar);
            if (this.b) {
                a("# " + bVar.r());
            }
            return this;
        }

        @Override // com.android.tools.r8.naming.x0
        public final x0 a(String str, String str2) {
            this.f.put(str, str2);
            return this;
        }
    }

    public final com.android.tools.r8.naming.mappinginformation.b c() {
        if (this.d.isEmpty()) {
            return null;
        }
        return (com.android.tools.r8.naming.mappinginformation.b) this.d.iterator().next();
    }

    public Set<com.android.tools.r8.naming.mappinginformation.b> d() {
        return this.d;
    }

    public static void a(boolean z, a aVar) {
        aVar.a = z;
    }

    public static a a() {
        return new a();
    }

    public static C3313b a(Path path) throws IOException {
        return a(path, EnumC0003b.c);
    }

    public static C3313b a(Path path, EnumC0003b enumC0003b) throws IOException {
        if (!g && enumC0003b != EnumC0003b.b && enumC0003b != EnumC0003b.c) {
            x1f.a();
            return null;
        }
        if (enumC0003b == EnumC0003b.b && !path.toFile().exists()) {
            return c(XmlPullParser.NO_NAMESPACE);
        }
        return a(Files.newBufferedReader(path, StandardCharsets.UTF_8), (DiagnosticsHandler) null, false, false, false);
    }

    public static C3313b a(P p, MapVersion mapVersion, DiagnosticsHandler diagnosticsHandler, boolean z, boolean z2, Consumer consumer) {
        if (diagnosticsHandler == null) {
            diagnosticsHandler = new C2742u50();
        }
        H0 h0 = new H0(p, diagnosticsHandler, z, z2, mapVersion);
        try {
            a aVarA = a();
            consumer.accept(aVarA);
            h0.b(aVarA);
            h0.a(aVarA);
            C3313b c3313bA = aVarA.a();
            h0.a.close();
            return c3313bA;
        } catch (Throwable th) {
            try {
                h0.a.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public String a(String str) {
        C3331k c3331k = (C3331k) this.a.get(str);
        return c3331k == null ? str : c3331k.a;
    }

    public final /* synthetic */ void a(C0629Ku c0629Ku, String str, C3331k c3331k) {
        if (this.a.containsKey(str)) {
            return;
        }
        c0629Ku.a(str, c3331k);
    }

    public static C3313b a(BufferedReader bufferedReader, DiagnosticsHandler diagnosticsHandler, boolean z, boolean z2, final boolean z3) throws IOException {
        return a(new O(bufferedReader), MapVersion.MAP_VERSION_NONE, diagnosticsHandler, z, z2, new Consumer() { // from class: sfg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C3313b.a(z3, (C3313b.a) obj);
            }
        });
    }

    public final void a(InterfaceC1326db interfaceC1326db) {
        if (!g) {
            Iterator it = g().a.entrySet().iterator();
            for (Map.Entry entry : this.a.entrySet()) {
                Map.Entry entry2 = (Map.Entry) it.next();
                boolean z = g;
                if (!z && !((String) entry.getKey()).equals(entry2.getKey())) {
                    x1f.a();
                    return;
                } else if (!z && entry.getValue() != entry2.getValue()) {
                    x1f.a();
                    return;
                }
            }
        }
        Iterator it2 = this.a.values().iterator();
        while (it2.hasNext()) {
            ((C3331k) it2.next()).a(interfaceC1326db);
        }
    }

    public final V.b a(C0322w2 c0322w2) {
        V vA;
        String strB = C0929Wj.b(c0322w2.f.f.toString());
        I2[] i2Arr = c0322w2.i.f.b;
        String[] strArr = new String[i2Arr.length];
        for (int i = 0; i < i2Arr.length; i++) {
            strArr[i] = C0929Wj.a(i2Arr[i].Z0(), this);
        }
        V.c bVar = new V.b(c0322w2.g.toString(), C0929Wj.a(c0322w2.i.e.Z0(), this), strArr);
        V.c cVar = (V.c) this.c.get(bVar);
        if (cVar != null) {
            bVar = cVar;
        } else {
            this.c.put(bVar, bVar);
        }
        V.b bVar2 = (V.b) bVar;
        C3331k c3331kB = b(strB);
        return (c3331kB == null || (vA = c3331kB.a(bVar2)) == null) ? bVar2 : vA.b().b();
    }

    public String a(I2 i2) {
        return C0929Wj.a(i2.f.toString(), this);
    }

    public static C3313b a(String str, DiagnosticsHandler diagnosticsHandler) throws IOException {
        C2522rb c2522rb;
        BufferedReader bufferedReader;
        if (str != null) {
            c2522rb = new C2607sb(str);
        } else {
            c2522rb = new C2522rb(str);
        }
        Reader readerA = c2522rb.a();
        if (readerA instanceof BufferedReader) {
            bufferedReader = (BufferedReader) readerA;
        } else {
            bufferedReader = new BufferedReader(readerA);
        }
        return a(bufferedReader, diagnosticsHandler, false, false, false);
    }

    public static C3313b a(String str, DiagnosticsHandler diagnosticsHandler, boolean z, boolean z2, final boolean z3) throws IOException {
        C2522rb c2522rb;
        BufferedReader bufferedReader;
        if (str != null) {
            c2522rb = new C2607sb(str);
        } else {
            c2522rb = new C2522rb(str);
        }
        Reader readerA = c2522rb.a();
        if (readerA instanceof BufferedReader) {
            bufferedReader = (BufferedReader) readerA;
        } else {
            bufferedReader = new BufferedReader(readerA);
        }
        return a(new O(bufferedReader), MapVersion.MAP_VERSION_NONE, diagnosticsHandler, z, z2, new Consumer() { // from class: jfg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C3313b.b(z3, (C3313b.a) obj);
            }
        });
    }
}
