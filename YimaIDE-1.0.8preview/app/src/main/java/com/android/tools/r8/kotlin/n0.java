package com.android.tools.r8.kotlin;

import com.android.tools.r8.internal.InterfaceC1936kh0;
import com.android.tools.r8.internal.Ra0;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.kotlin.m0;
import com.android.tools.r8.kotlin.n0;
import com.android.tools.r8.naming.N0;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class n0 {
    public static final /* synthetic */ boolean a = true;

    public static class a {
        public final c a;
        public final N0 b;

        public a(c cVar, N0 n0) {
            this.a = cVar;
            this.b = n0;
        }

        public N0 a() {
            return this.b;
        }

        public c b() {
            return this.a;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.b.a);
            sb.append("#");
            sb.append(this.a);
            N0 n0 = this.b;
            if (n0.b != n0.a) {
                sb.append(",");
                sb.append(this.b.b);
            }
            sb.append(":");
            return sb.toString();
        }
    }

    public static class c {
        public final String a;
        public final String b;

        public c(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public final String toString() {
            return this.b + "(" + this.a + ")";
        }
    }

    public static void a(String str, o0 o0Var) throws m0 {
        int i;
        int i2;
        try {
            int iIndexOf = str.indexOf(58);
            int i3 = iIndexOf + 1;
            int iIndexOf2 = str.indexOf(44, iIndexOf);
            if (iIndexOf2 > -1) {
                i = Integer.parseInt(str.substring(i3, iIndexOf2));
                i2 = Integer.parseInt(str.substring(iIndexOf2 + 1));
            } else {
                i = Integer.parseInt(str.substring(i3));
                i2 = 1;
            }
            String strSubstring = str.substring(0, iIndexOf);
            int iIndexOf3 = strSubstring.indexOf(35);
            int i4 = Integer.parseInt(strSubstring.substring(0, iIndexOf3));
            String strSubstring2 = strSubstring.substring(iIndexOf3 + 1);
            int iIndexOf4 = strSubstring2.indexOf(44);
            if (iIndexOf4 > -1) {
                if (!a && iIndexOf4 <= 0) {
                    throw new AssertionError();
                }
                i2 = Integer.parseInt(strSubstring2.substring(iIndexOf4 + 1));
            } else {
                iIndexOf4 = strSubstring2.length();
            }
            int i5 = Integer.parseInt(strSubstring2.substring(0, iIndexOf4));
            c cVar = (c) o0Var.b.get(Integer.valueOf(i5));
            if (cVar == null) {
                throw new m0("Could not find file with index " + i5);
            }
            int i6 = i2 - 1;
            o0Var.a.a(i, i6 + i, new a(cVar, new N0(i4, i4 + i6)));
        } catch (NumberFormatException unused) {
            throw new m0("Could not convert position to number");
        }
    }

    public static /* synthetic */ boolean b(String str) {
        return str.equals("*E") || str.startsWith("*S");
    }

    public static b c(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                l0 l0Var = new l0(str);
                try {
                    String line = l0Var.a.readLine();
                    l0Var.b = line;
                    if (!line.equals("SMAP")) {
                        l0Var.a.close();
                        return null;
                    }
                    while (!"*S Kotlin".equals(l0Var.b) && !l0Var.c()) {
                        l0Var.b = l0Var.a.readLine();
                    }
                    if (l0Var.c()) {
                        l0Var.a.close();
                        return null;
                    }
                    o0 o0Var = new o0();
                    o0 o0Var2 = new o0();
                    String strA = a(l0Var, o0Var);
                    if (strA.equals("*E")) {
                        String line2 = l0Var.a.readLine();
                        l0Var.b = line2;
                        if (l0Var.c()) {
                            if (!a && line2 != null) {
                                throw new AssertionError();
                            }
                            b bVar = new b(o0Var.a, o0Var2.a);
                            l0Var.a.close();
                            return bVar;
                        }
                        if (!line2.equals("*S KotlinDebug")) {
                            l0Var.a.close();
                            return null;
                        }
                    } else if (!strA.equals("*S KotlinDebug")) {
                        l0Var.a.close();
                        return null;
                    }
                    a(l0Var, o0Var2);
                    if (l0Var.c() && !l0Var.b.equals("*E")) {
                        throw new m0("Unexpected EOF when parsing SMAP debug entries");
                    }
                    b bVar2 = new b(o0Var.a, o0Var2.a);
                    l0Var.a.close();
                    return bVar2;
                } catch (Throwable th) {
                    try {
                        l0Var.a.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (m0 | IOException unused) {
            }
        }
        return null;
    }

    public static class b {
        public final Ra0 a;
        public final Ra0 b;

        public b(Ra0 ra0, Ra0 ra1) {
            this.a = ra0;
            this.b = ra1;
        }

        public Map.Entry<Integer, a> a(int i) {
            return this.a.a(Integer.valueOf(i));
        }

        public int a() {
            return this.a.a();
        }
    }

    public static void a(String str, String str2, o0 o0Var) throws m0 {
        String[] strArrA = Wf0.a(str.trim(), ' ', 3);
        if (strArrA != null && strArrA[0].equals("+")) {
            String str3 = strArrA[2];
            if (!str3.isEmpty()) {
                if (str2 != null && !str2.isEmpty()) {
                    int iA = a(strArrA[1]);
                    c cVar = new c(str3, str2);
                    if (((c) o0Var.b.put(Integer.valueOf(iA), cVar)) == null) {
                        return;
                    }
                    throw new m0("File index " + iA + " was already mapped to an existing source: " + cVar);
                }
                throw new m0("Did not expect file path to be null or empty for ".concat(str));
            }
            throw new m0("Did not expect file name to be empty for line ".concat(str));
        }
        throw new m0("Wrong number of entries on line ".concat(str));
    }

    public static int a(String str) throws m0 {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            throw new m0("Could not parse number " + str);
        }
    }

    public static String a(l0 l0Var, final o0 o0Var) throws Throwable {
        l0Var.d();
        l0Var.a(new InterfaceC1936kh0() { // from class: wnh
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) throws m0 {
                List list = (List) obj;
                n0.a((String) list.get(0), (String) list.get(1), o0Var);
            }
        });
        if (!l0Var.c()) {
            return l0Var.a(new Predicate() { // from class: goh
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return n0.b((String) obj);
                }
            }, 1, new InterfaceC1936kh0() { // from class: ioh
                @Override // com.android.tools.r8.internal.InterfaceC1936kh0
                public final void accept(Object obj) throws m0 {
                    n0.a((String) ((List) obj).get(0), o0Var);
                }
            });
        }
        throw new m0("Unexpected EOF - no debug line positions");
    }
}
