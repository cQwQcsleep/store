package com.android.tools.r8.naming;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1674he;
import com.android.tools.r8.internal.C3050xi0;
import com.android.tools.r8.internal.EX;
import com.android.tools.r8.internal.GV;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.R3;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.naming.V;
import com.android.tools.r8.naming.mappinginformation.d;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.TypeReference;
import defpackage.j3f;
import defpackage.ykg;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class V implements U, Comparable<V> {
    public static final List f = Collections.EMPTY_LIST;
    public final c b;
    public final c c;
    public final Position d;
    public List e = f;

    public static abstract class c {
        public static final /* synthetic */ boolean b = true;
        public final String a;

        public c(String str) {
            this.a = str;
        }

        public a a() {
            return null;
        }

        public abstract c a(String str);

        public abstract c a(Function function, String str);

        public abstract void a(StringWriter stringWriter);

        public b b() {
            return null;
        }

        public abstract c b(String str);

        public String c() {
            return this.a;
        }

        public boolean d() {
            return this instanceof b;
        }

        public boolean e() {
            return this.a.indexOf(46) != -1;
        }

        public abstract boolean equals(Object obj);

        public abstract int f();

        public final String g() {
            if (b || e()) {
                String str = this.a;
                return str.substring(0, str.lastIndexOf(46));
            }
            x1f.a();
            return null;
        }

        public final String h() {
            if (b || e()) {
                String str = this.a;
                return str.substring(str.lastIndexOf(46) + 1);
            }
            x1f.a();
            return null;
        }

        public abstract int hashCode();

        public String toString() {
            try {
                StringWriter stringWriter = new StringWriter();
                a(stringWriter);
                return stringWriter.toString();
            } catch (IOException e) {
                throw new Kk0(e);
            }
        }
    }

    public V(c cVar, c cVar2, Position position) {
        this.b = cVar;
        this.c = cVar2;
        this.d = position;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(V v) {
        return Comparator.comparing(new Function() { // from class: d3f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((V) obj).h());
            }
        }).thenComparing(new Function() { // from class: g3f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((V) obj).a();
            }
        }).thenComparing(new j3f()).thenComparing(new Function() { // from class: m3f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b((V) obj);
            }
        }).compare(this, v);
    }

    public final /* synthetic */ String b(V v) {
        return b().toString();
    }

    @Override // com.android.tools.r8.naming.U
    public c c() {
        return this.c;
    }

    public final List d() {
        return this.e;
    }

    public final String e() {
        return this.b.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof V)) {
            return false;
        }
        V v = (V) obj;
        return this.b.equals(v.b) && this.c.equals(v.c);
    }

    public final boolean f() {
        return AbstractC3179zC.b(this.e, new EX() { // from class: z2f
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((d) obj).p();
            }
        });
    }

    public boolean g() {
        for (com.android.tools.r8.naming.mappinginformation.e eVar : this.e) {
            if (eVar.l() || (eVar instanceof GV)) {
                return true;
            }
        }
        return false;
    }

    public final boolean h() {
        return this.b.f() == 2;
    }

    public final int hashCode() {
        return this.c.hashCode() + (this.b.hashCode() * 31);
    }

    public final String toString() {
        return this.b.toString() + " -> " + this.c.c();
    }

    @Override // com.android.tools.r8.naming.U
    public c b() {
        return this.b;
    }

    public static class b extends c {
        public static final /* synthetic */ boolean e = true;
        public final String c;
        public final String[] d;

        public b(String str, String str2, Collection<String> collection) {
            super(str);
            this.c = str2;
            this.d = (String[]) collection.toArray(Wf0.b);
        }

        @Override // com.android.tools.r8.naming.V.c
        public final void a(StringWriter stringWriter) throws IOException {
            stringWriter.append((CharSequence) this.c).append(' ').append((CharSequence) this.a).append('(');
            int i = 0;
            while (true) {
                String[] strArr = this.d;
                if (i >= strArr.length) {
                    stringWriter.append(')');
                    return;
                }
                stringWriter.append((CharSequence) strArr[i]);
                if (i < this.d.length - 1) {
                    stringWriter.append(',');
                }
                i++;
            }
        }

        @Override // com.android.tools.r8.naming.V.c
        public final c b(String str) {
            return new b(str + "." + this.a, this.c, this.d);
        }

        @Override // com.android.tools.r8.naming.V.c
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.c.equals(bVar.c) && this.a.equals(bVar.a) && Arrays.equals(this.d, bVar.d);
        }

        @Override // com.android.tools.r8.naming.V.c
        public final int f() {
            return 1;
        }

        @Override // com.android.tools.r8.naming.V.c
        public final int hashCode() {
            return ((this.a.hashCode() + (this.c.hashCode() * 17)) * 31) + Arrays.hashCode(this.d);
        }

        public String i() {
            StringBuilder sb = new StringBuilder("(");
            for (String str : this.d) {
                sb.append(C0929Wj.I(str));
            }
            sb.append(')');
            sb.append(C0929Wj.I(this.c));
            return sb.toString();
        }

        public b j() {
            if (e || e()) {
                return new b(h(), this.c, this.d);
            }
            x1f.a();
            return null;
        }

        public final b k() {
            return e() ? new b(h(), this.c, this.d) : this;
        }

        public final b l() {
            return e() ? new b(h(), this.c, this.d) : this;
        }

        @Override // com.android.tools.r8.naming.V.c
        public String toString() {
            return this.c + " " + this.a + "(" + String.join(",", this.d) + ")";
        }

        public b(String str, String str2, String[] strArr) {
            super(str);
            this.c = str2;
            this.d = strArr;
        }

        @Override // com.android.tools.r8.naming.V.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final b a(final Function function, String str) {
            return new b(str, C0929Wj.a(function, this.c), R3.a((Object[]) this.d, new Function() { // from class: v3f
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C0929Wj.a(function, (String) obj);
                }
            }));
        }

        @Override // com.android.tools.r8.naming.V.c
        public b b() {
            return this;
        }

        public static b a(C0322w2 c0322w2) {
            return a(c0322w2, false);
        }

        public static b a(C0322w2 c0322w2, boolean z) {
            String[] strArr = new String[c0322w2.A0()];
            I2[] i2Arr = c0322w2.i.f.b;
            for (int i = 0; i < i2Arr.length; i++) {
                strArr[i] = i2Arr[i].m0();
            }
            return new b(z ? c0322w2.E0() : c0322w2.g.m0(), c0322w2.i.e.m0(), strArr);
        }

        public static b a(MethodReference methodReference) {
            TypeReference returnType = methodReference.getReturnType();
            return new b(methodReference.getMethodName(), returnType == null ? "void" : returnType.getTypeName(), C1674he.a(methodReference.getFormalTypes(), new ykg()));
        }

        public C0322w2 a(B1 b1, I2 i2) {
            I2[] i2Arr = new I2[this.d.length];
            int i = 0;
            while (true) {
                String[] strArr = this.d;
                if (i < strArr.length) {
                    i2Arr[i] = b1.e(C0929Wj.I(strArr[i]));
                    i++;
                } else {
                    return b1.a(i2, b1.a(b1.e(C0929Wj.I(this.c)), i2Arr), b1.c(this.a));
                }
            }
        }

        public static b a(String[] strArr) {
            return new b("<init>", "void", strArr);
        }

        @Override // com.android.tools.r8.naming.V.c
        public final c a(String str) {
            return new b(str, this.c, this.d);
        }

        public static b a(String str, String str2) {
            return new b(str, C0929Wj.b(C3050xi0.a(C3050xi0.f(str2), str2.length(), str2).b()), R3.a((Object[]) C3050xi0.b(str2), new Function() { // from class: u3f
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C0929Wj.b(((C3050xi0) obj).b());
                }
            }));
        }
    }

    public static class a extends c {
        public static final /* synthetic */ boolean d = true;
        public final String c;

        public a(String str, String str2) {
            super(str);
            this.c = str2;
        }

        public C0245l1 a(B1 b1, I2 i2) {
            return b1.a(i2, b1.e(C0929Wj.I(this.c)), b1.c(this.a));
        }

        @Override // com.android.tools.r8.naming.V.c
        public final c b(String str) {
            if (!d && e()) {
                x1f.a();
                return null;
            }
            return new a(str + "." + this.a, this.c);
        }

        @Override // com.android.tools.r8.naming.V.c
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a.equals(aVar.a) && this.c.equals(aVar.c);
        }

        @Override // com.android.tools.r8.naming.V.c
        public final int f() {
            return 2;
        }

        @Override // com.android.tools.r8.naming.V.c
        public final int hashCode() {
            return this.c.hashCode() + (this.a.hashCode() * 31);
        }

        @Override // com.android.tools.r8.naming.V.c
        public final String toString() {
            return this.c + " " + this.a;
        }

        public static a a(C0245l1 c0245l1) {
            return new a(c0245l1.g.m0(), c0245l1.i.m0());
        }

        @Override // com.android.tools.r8.naming.V.c
        public final a a() {
            return this;
        }

        @Override // com.android.tools.r8.naming.V.c
        public final c a(String str) {
            return new a(str, this.c);
        }

        @Override // com.android.tools.r8.naming.V.c
        public final c a(Function function, String str) {
            return new a(str, C0929Wj.a(function, this.c));
        }

        @Override // com.android.tools.r8.naming.V.c
        public final void a(StringWriter stringWriter) throws IOException {
            stringWriter.append((CharSequence) this.c);
            stringWriter.append(' ');
            stringWriter.append((CharSequence) this.a);
        }
    }

    @Override // com.android.tools.r8.naming.U
    public String a() {
        return this.c.c();
    }
}
