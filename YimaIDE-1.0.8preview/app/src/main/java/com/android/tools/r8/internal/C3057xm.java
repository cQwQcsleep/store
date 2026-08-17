package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.android.tools.r8.AndroidResourceProvider;
import com.android.tools.r8.CompilationMode;
import com.android.tools.r8.internal.C3057xm;
import defpackage.o0e;
import defpackage.obi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3057xm {
    public final com.android.tools.r8.dex.W.a a;
    public final com.android.tools.r8.dex.W.b b;
    public final CompilationMode c;
    public final int d;
    public final boolean e;
    public final int f;
    public final C2752uB.g g;
    public final Optional h;
    public final Optional i;
    public final Optional j;
    public final Optional k;
    public final Optional l;
    public final Optional m;
    public final InterfaceC0852Tk n;
    public final C0882Uo o;
    public final com.android.tools.r8.shaking.R2 p;
    public final List q;
    public final Collection r;
    public final Collection s;
    public final boolean t;
    public final boolean u;
    public final AndroidResourceProvider v;
    public final Map w;
    public final String x;
    public final boolean y = false;

    public C3057xm(com.android.tools.r8.dex.W.a aVar, com.android.tools.r8.dex.W.b bVar, CompilationMode compilationMode, int i, InterfaceC0852Tk interfaceC0852Tk, boolean z, int i2, C2752uB.g gVar, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, C0882Uo c0882Uo, com.android.tools.r8.shaking.R2 r2, List list, List list2, List list3, boolean z2, boolean z3, HashMap map, String str, AndroidResourceProvider androidResourceProvider) {
        this.a = aVar;
        this.b = bVar;
        this.c = compilationMode;
        this.d = i;
        this.n = interfaceC0852Tk;
        this.e = z;
        this.f = i2;
        this.g = gVar;
        this.h = optional;
        this.i = optional2;
        this.j = optional3;
        this.k = optional4;
        this.l = optional5;
        this.m = optional6;
        this.o = c0882Uo;
        this.p = r2;
        this.q = list;
        this.r = list2;
        this.s = list3;
        this.t = z2;
        this.u = z3;
        this.w = map;
        this.x = str;
        this.v = androidResourceProvider;
    }

    public static void a(a aVar, String str) {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return;
        }
        int iIndexOf = strTrim.indexOf(61);
        if (iIndexOf < 0) {
            obi.a("Invalid dump line. Expected = in line: '", strTrim, "'");
            return;
        }
        byte b = 0;
        String strTrim2 = strTrim.substring(0, iIndexOf).trim();
        String strTrim3 = strTrim.substring(iIndexOf + 1).trim();
        strTrim2.getClass();
        switch (strTrim2.hashCode()) {
            case -1714535540:
                if (!strTrim2.equals("thread-count")) {
                    b = -1;
                }
                break;
            case -1572360679:
                b = !strTrim2.equals("desugar-state") ? (byte) -1 : (byte) 1;
                break;
            case -859717383:
                b = !strTrim2.equals("intermediate") ? (byte) -1 : (byte) 2;
                break;
            case -457962420:
                b = !strTrim2.equals("minification") ? (byte) -1 : (byte) 3;
                break;
            case -347208044:
                b = !strTrim2.equals("backend") ? (byte) -1 : (byte) 4;
                break;
            case -145476656:
                b = !strTrim2.equals("optimize-multidex-for-linear-alloc") ? (byte) -1 : (byte) 5;
                break;
            case -82867811:
                b = !strTrim2.equals("force-proguard-compatibility") ? (byte) -1 : (byte) 6;
                break;
            case 3357091:
                b = !strTrim2.equals("mode") ? (byte) -1 : (byte) 7;
                break;
            case 3565976:
                b = !strTrim2.equals("tool") ? (byte) -1 : (byte) 8;
                break;
            case 223606663:
                b = !strTrim2.equals("include-data-resources") ? (byte) -1 : (byte) 9;
                break;
            case 1062738975:
                b = !strTrim2.equals("min-api") ? (byte) -1 : (byte) 10;
                break;
            case 1096335309:
                b = !strTrim2.equals("isolated-splits") ? (byte) -1 : (byte) 11;
                break;
            case 1733466900:
                b = !strTrim2.equals("tree-shaking") ? (byte) -1 : (byte) 12;
                break;
            case 1763872211:
                b = !strTrim2.equals("trace_references_consumer") ? (byte) -1 : (byte) 13;
                break;
            case 1829996623:
                b = !strTrim2.equals("enable-missing-library-api-modeling") ? (byte) -1 : (byte) 14;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
                aVar.f = Integer.parseInt(strTrim3);
                return;
            case 1:
                aVar.g = (C2752uB.g) Enum.valueOf(C2752uB.g.class, strTrim3);
                return;
            case 2:
                boolean z = Boolean.parseBoolean(strTrim3);
                aVar.getClass();
                aVar.h = Optional.of(Boolean.valueOf(z));
                return;
            case XmlPullParser.END_TAG /* 3 */:
                boolean z2 = Boolean.parseBoolean(strTrim3);
                aVar.getClass();
                aVar.l = Optional.of(Boolean.valueOf(z2));
                return;
            case 4:
                aVar.a((com.android.tools.r8.dex.W.a) Enum.valueOf(com.android.tools.r8.dex.W.a.class, strTrim3));
                return;
            case XmlPullParser.CDSECT /* 5 */:
                aVar.e = Boolean.parseBoolean(strTrim3);
                return;
            case XmlPullParser.ENTITY_REF /* 6 */:
                boolean z3 = Boolean.parseBoolean(strTrim3);
                aVar.getClass();
                aVar.m = Optional.of(Boolean.valueOf(z3));
                return;
            case 7:
                if (strTrim3.equals("debug")) {
                    aVar.c = CompilationMode.DEBUG;
                    return;
                } else if (strTrim3.equals("release")) {
                    aVar.c = CompilationMode.RELEASE;
                    return;
                } else {
                    a(strTrim2, strTrim3);
                    throw null;
                }
            case 8:
                aVar.b = (com.android.tools.r8.dex.W.b) Enum.valueOf(com.android.tools.r8.dex.W.b.class, strTrim3);
                return;
            case 9:
                aVar.i = Optional.of(Boolean.valueOf(Boolean.parseBoolean(strTrim3)));
                return;
            case XmlPullParser.DOCDECL /* 10 */:
                aVar.d = Integer.parseInt(strTrim3);
                return;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                boolean z4 = Boolean.parseBoolean(strTrim3);
                aVar.getClass();
                aVar.j = Optional.of(Boolean.valueOf(z4));
                return;
            case 12:
                boolean z5 = Boolean.parseBoolean(strTrim3);
                aVar.getClass();
                aVar.k = Optional.of(Boolean.valueOf(z5));
                return;
            case 13:
                aVar.w = strTrim3;
                return;
            case 14:
                aVar.u = Boolean.parseBoolean(strTrim3);
                return;
            default:
                if (!strTrim2.startsWith("system-property-")) {
                    a(strTrim2, strTrim3);
                    throw null;
                }
                aVar.x.put(strTrim2.substring(16), strTrim3);
                return;
        }
    }

    public final AndroidResourceProvider b() {
        return this.v;
    }

    public final Collection c() {
        return this.r;
    }

    public final LinkedHashMap d() {
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("tool", Objects.toString(this.b.name()));
        int i = this.f;
        if (i != -1) {
            linkedHashMap.put("thread-count", Objects.toString(Integer.valueOf(i)));
        }
        if (this.b != com.android.tools.r8.dex.W.b.g) {
            linkedHashMap.put("backend", Objects.toString(this.a.name()));
            linkedHashMap.put("mode", this.c == CompilationMode.DEBUG ? "debug" : "release");
            linkedHashMap.put("min-api", Objects.toString(Integer.valueOf(this.d)));
            linkedHashMap.put("optimize-multidex-for-linear-alloc", Objects.toString(Boolean.valueOf(this.e)));
            linkedHashMap.put("desugar-state", Objects.toString(this.g));
            linkedHashMap.put("enable-missing-library-api-modeling", Objects.toString(Boolean.valueOf(this.t)));
            boolean z = this.u;
            if (z) {
                linkedHashMap.put("android-platform-build", Objects.toString(Boolean.valueOf(z)));
            }
            a(linkedHashMap, "intermediate", this.h);
            a(linkedHashMap, "include-data-resources", this.i);
            a(linkedHashMap, "isolated-splits", this.j);
            a(linkedHashMap, "tree-shaking", this.k);
            a(linkedHashMap, "force-proguard-compatibility", this.m);
        } else {
            linkedHashMap.put("trace_references_consumer", Objects.toString(this.x));
        }
        a(linkedHashMap, "minification", this.l);
        ArrayList arrayList = new ArrayList(this.w.keySet());
        arrayList.sort(new o0e());
        arrayList.forEach(new Consumer() { // from class: qsi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(linkedHashMap, (String) obj);
            }
        });
        return linkedHashMap;
    }

    public final String e() {
        final StringBuilder sb = new StringBuilder();
        d().forEach(new BiConsumer() { // from class: osi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C3057xm.a(sb, (String) obj, (String) obj2);
            }
        });
        return sb.toString();
    }

    public CompilationMode f() {
        return this.c;
    }

    public final String g() {
        InterfaceC0852Tk interfaceC0852Tk = this.n;
        if (interfaceC0852Tk == null || interfaceC0852Tk.isEmpty()) {
            return null;
        }
        return this.n.b();
    }

    public final C0882Uo h() {
        return this.o;
    }

    public final List i() {
        return this.q;
    }

    public int j() {
        return this.d;
    }

    public final String k() {
        com.android.tools.r8.shaking.R2 r2 = this.p;
        if (r2 == null) {
            return null;
        }
        return r2.a;
    }

    public final Collection l() {
        return this.s;
    }

    public final boolean m() {
        return this.v != null;
    }

    public final boolean n() {
        Collection collection = this.r;
        return (collection == null || collection.isEmpty()) ? false : true;
    }

    public final boolean o() {
        List list = this.q;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public final boolean p() {
        Collection collection = this.s;
        return (collection == null || collection.isEmpty()) ? false : true;
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.xm$a */
    public static class a {
        public static final /* synthetic */ boolean y = true;
        public com.android.tools.r8.dex.W.b b;
        public CompilationMode c;
        public int d;
        public boolean e;
        public int f;
        public C2752uB.g g;
        public InterfaceC0852Tk n;
        public C0882Uo o;
        public com.android.tools.r8.shaking.R2 p;
        public List q;
        public Collection r;
        public Collection s;
        public AndroidResourceProvider t;
        public com.android.tools.r8.dex.W.a a = com.android.tools.r8.dex.W.a.c;
        public Optional h = Optional.empty();
        public Optional i = Optional.empty();
        public Optional j = Optional.empty();
        public Optional k = Optional.empty();
        public Optional l = Optional.empty();
        public Optional m = Optional.empty();
        public boolean u = false;
        public boolean v = false;
        public String w = null;
        public final HashMap x = new HashMap();

        public static Map<String, String> b() {
            final TreeMap treeMap = new TreeMap();
            System.getProperties().stringPropertyNames().forEach(new Consumer() { // from class: rsi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    C3057xm.a.a(treeMap, (String) obj);
                }
            });
            return treeMap;
        }

        public C3057xm a() {
            boolean z = y;
            if (!z && this.b == null) {
                x1f.a();
                return null;
            }
            if (!z && this.b != com.android.tools.r8.dex.W.b.g && this.a == null) {
                x1f.a();
                return null;
            }
            com.android.tools.r8.dex.W.a aVar = this.a;
            com.android.tools.r8.dex.W.b bVar = this.b;
            CompilationMode compilationMode = this.c;
            int i = this.d;
            InterfaceC0852Tk interfaceC0852Tk = this.n;
            boolean z2 = this.e;
            int i2 = this.f;
            C2752uB.g gVar = this.g;
            Optional optional = this.h;
            Optional optional2 = this.i;
            Optional optional3 = this.j;
            Optional optional4 = this.k;
            Optional optional5 = this.l;
            Optional optional6 = this.m;
            C0882Uo c0882Uo = this.o;
            com.android.tools.r8.shaking.R2 r2 = this.p;
            List list = this.q;
            Collection collection = this.r;
            return new C3057xm(aVar, bVar, compilationMode, i, interfaceC0852Tk, z2, i2, gVar, optional, optional2, optional3, optional4, optional5, optional6, c0882Uo, r2, list, (List) collection, (List) this.s, this.u, this.v, this.x, this.w, this.t);
        }

        public final a c() {
            b().forEach(new BiConsumer() { // from class: ssi
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a((String) obj, (String) obj2);
                }
            });
            return this;
        }

        public final a d(boolean z) {
            this.l = Optional.of(Boolean.valueOf(z));
            return this;
        }

        public final a e(boolean z) {
            this.k = Optional.of(Boolean.valueOf(z));
            return this;
        }

        public final void c(boolean z) {
            this.j = Optional.of(Boolean.valueOf(z));
        }

        public final a b(boolean z) {
            this.h = Optional.of(Boolean.valueOf(z));
            return this;
        }

        public a a(com.android.tools.r8.dex.W.a aVar) {
            this.a = aVar;
            return this;
        }

        public final a a(boolean z) {
            this.m = Optional.of(Boolean.valueOf(z));
            return this;
        }

        public final a a(String str, String str2) {
            this.x.put(str, str2);
            return this;
        }

        public static /* synthetic */ void a(Map map, String str) {
            if (str.startsWith("com.android.tools.r8.")) {
                map.put(str, System.getProperty(str));
            }
        }

        public final a a(boolean z, Consumer consumer) {
            if (z) {
                consumer.accept(this);
            }
            return this;
        }
    }

    public final void a(Map map, String str) {
        map.put("system-property-" + str, Objects.toString(this.w.get(str)));
    }

    public static void a(String str, final a aVar) {
        Wf0.a(str, '\n', new Consumer() { // from class: nsi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C3057xm.a(aVar, (String) obj);
            }
        });
    }

    public static /* synthetic */ void a(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append("=");
        sb.append(str2);
        sb.append("\n");
    }

    public static void a(String str, String str2) {
        throw new RuntimeException("Unknown key value pair: " + str + " = " + str2);
    }

    public final void a(final LinkedHashMap linkedHashMap, final String str, Optional optional) {
        optional.ifPresent(new Consumer() { // from class: psi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(linkedHashMap, str, obj);
            }
        });
    }

    public final void a(Map map, String str, Object obj) {
        map.put(str, Objects.toString(obj));
    }

    public final boolean a() {
        return this.y;
    }

    public static a a(com.android.tools.r8.dex.W.b bVar) {
        a aVar = new a();
        aVar.b = bVar;
        return aVar;
    }
}
