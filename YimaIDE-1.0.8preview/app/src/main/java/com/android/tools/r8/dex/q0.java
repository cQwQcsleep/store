package com.android.tools.r8.dex;

import com.android.tools.r8.DiagnosticsLevel;
import com.android.tools.r8.errors.StartupClassesNonStartupFractionDiagnostic;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.C1131bA;
import com.android.tools.r8.internal.C1727iB;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.C2855vT;
import com.android.tools.r8.internal.NC;
import com.android.tools.r8.internal.VP;
import com.android.tools.r8.internal.Vd0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class q0 {
    public static final /* synthetic */ boolean g = true;
    public final p0 a;
    public final Map b;
    public final B1 c;
    public final C2752uB d;
    public final r0 e;
    public final Vd0 f;

    public q0(List list, List list2, C0333y c0333y, Collection collection, Map map, Vd0 vd0, C1131bA c1131bA) {
        this.a = p0.a(collection, map, vd0);
        this.b = map;
        this.c = c0333y.a();
        this.d = c0333y.M();
        this.e = new r0(list, list2, c0333y, c1131bA);
        this.f = vd0;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x008d  */
    public final void a(ArrayList arrayList) {
        boolean zStartsWith;
        String strA;
        C2855vT c2855vT = new C2855vT();
        r0 r0Var = this.e;
        if (r0Var.b.isEmpty()) {
            t0 t0Var = new t0(r0Var.d.b(), r0Var.c, r0Var.g);
            r0Var.a.add(t0Var);
            r0Var.b.add(t0Var);
            r0Var.e = NC.a(r0Var.b);
            r0Var.c();
        }
        t0 t0Var2 = (t0) r0Var.f.next();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        int i2 = 0;
        String strA2 = null;
        int i3 = 4;
        while (i < arrayList.size()) {
            D2 d2 = (D2) arrayList.get(i);
            String str = (String) this.b.get(d2);
            if (strA2 == null) {
                zStartsWith = false;
            } else if (strA2.endsWith(".*")) {
                zStartsWith = str.startsWith(strA2.substring(0, strA2.length() - 2));
            } else if (str.startsWith(strA2) && str.lastIndexOf(46) == strA2.length()) {
                zStartsWith = true;
            } else {
                zStartsWith = false;
            }
            if (!zStartsWith) {
                if (strA2 != null) {
                    t0Var2.c.a();
                    boolean z = g;
                    if (!z) {
                        if (!z && c2855vT.containsKey(strA2)) {
                            x1f.a();
                            return;
                        }
                        c2855vT.b(t0Var2.e(), strA2);
                    }
                    this.e.c();
                    i3 = 3;
                }
                do {
                    i3++;
                    strA = a(i3, str, false);
                    if (strA2 == null) {
                        break;
                    }
                } while (strA2.startsWith(strA));
                if (!strA.equals(XmlPullParser.NO_NAMESPACE)) {
                    strA2 = a(i3, str, true);
                }
                i2 = i;
            }
            if (strA2 == null) {
                boolean z2 = g;
                if (!z2 && d2.g == null) {
                    x1f.a();
                    return;
                } else {
                    if (!z2 && !t0Var2.c.b()) {
                        x1f.a();
                        return;
                    }
                    arrayList2.add(d2);
                }
            } else {
                if (!g && d2.g == null && d2.e != this.c.a2) {
                    x1f.a();
                    return;
                }
                t0Var2.a(d2);
                if (a(t0Var2, this.d)) {
                    int i4 = (i - i2) + 1;
                    n0 n0Var = t0Var2.c;
                    int size = n0Var.b.c.size() + n0Var.d.size();
                    t0Var2.a();
                    if (i4 <= size / 5 || i3 >= 7) {
                        if (!this.e.f.hasNext()) {
                            if (t0Var2.b.c.isEmpty()) {
                                while (i2 <= i) {
                                    arrayList2.add((D2) arrayList.get(i2));
                                    i2++;
                                }
                                i2 = i + 1;
                            }
                            r0 r0Var2 = this.e;
                            r0Var2.getClass();
                            t0 t0Var3 = new t0(r0Var2.d.b(), r0Var2.c, r0Var2.g);
                            r0Var2.a.add(t0Var3);
                            r0Var2.b.add(t0Var3);
                            r0Var2.e = NC.a(r0Var2.b);
                            r0Var2.c();
                        }
                        i = i2 - 1;
                        t0Var2 = (t0) this.e.f.next();
                        strA2 = null;
                        i3 = 4;
                    } else {
                        i = i2 - 1;
                        i3++;
                        strA2 = null;
                    }
                }
            }
            i++;
        }
        t0Var2.c.a();
        boolean z3 = g;
        if (!z3 && strA2 != null) {
            if (!z3 && c2855vT.containsKey(strA2)) {
                x1f.a();
                return;
            }
            c2855vT.b(t0Var2.e(), strA2);
        }
        a(this.e, arrayList2);
    }

    public final void a() {
        Collection collection;
        if (!this.a.a.isEmpty()) {
            if (!g && ((collection = this.d.L().g) == null || collection.isEmpty())) {
                x1f.a();
                return;
            }
            t0 t0Var = (t0) this.e.f.next();
            Iterator it = this.a.a.iterator();
            while (it.hasNext()) {
                t0Var.a((D2) it.next());
            }
            boolean zA = a(t0Var, this.d);
            if (!zA) {
                t0Var.c.a();
            } else {
                t0Var.a();
                VP.a(this.d, this.f).a((ArrayList) this.a.a, this, t0Var, this.e);
                this.d.i.warning(com.android.tools.r8.errors.f.a(this.e.b.size()));
            }
            C2742u50 c2742u50 = this.d.i;
            StartupClassesNonStartupFractionDiagnostic startupClassesNonStartupFractionDiagnosticA = com.android.tools.r8.errors.e.a((ArrayList) this.a.a, this.f);
            synchronized (c2742u50) {
                c2742u50.a(DiagnosticsLevel.INFO, startupClassesNonStartupFractionDiagnosticA);
            }
            boolean z = this.d.L().a;
            r0 r0Var = this.e;
            if (z) {
                t0 t0Var2 = (t0) C2847vL.b(r0Var.b);
                r0 r0Var2 = this.e;
                r0Var2.b.clear();
                r0Var2.e = NC.a(r0Var2.b);
                r0Var2.c();
                if (zA) {
                    r0 r0Var3 = this.e;
                    r0Var3.b.add(t0Var2);
                    r0Var3.e = NC.a(r0Var3.b);
                    r0Var3.c();
                }
            } else {
                r0Var.c();
            }
        }
        a((ArrayList) this.a.b);
    }

    public final t0 a(r0 r0Var) {
        t0 t0Var = null;
        while (r0Var.f.hasNext()) {
            t0Var = (t0) r0Var.f.next();
            if (!a(t0Var, this.d)) {
                break;
            }
        }
        if (t0Var != null && !a(t0Var, this.d)) {
            return t0Var;
        }
        t0 t0Var2 = new t0(r0Var.d.b(), r0Var.c, r0Var.g);
        r0Var.a.add(t0Var2);
        r0Var.b.add(t0Var2);
        r0Var.e = NC.a(r0Var.b);
        r0Var.c();
        return t0Var2;
    }

    public static String a(int i, String str, boolean z) {
        int iIndexOf = 0;
        int i2 = 0;
        int i3 = 0;
        while (iIndexOf != -1) {
            int i4 = i2 + 1;
            if (i2 >= i) {
                i2 = i4;
                break;
            }
            i3 = iIndexOf;
            iIndexOf = str.indexOf(46, iIndexOf + 1);
            i2 = i4;
        }
        String strSubstring = str.substring(0, i3);
        return (!z || i2 < i) ? strSubstring : strSubstring.concat(".*");
    }

    public static boolean a(t0 t0Var, C2752uB c2752uB) {
        if (c2752uB.u1.R0 > 0) {
            n0 n0Var = t0Var.c;
            if (n0Var.b.c.size() + n0Var.d.size() > c2752uB.u1.R0) {
                return true;
            }
        }
        return t0Var.a(65536);
    }

    public final void a(r0 r0Var, ArrayList arrayList) {
        if (arrayList.isEmpty()) {
            return;
        }
        r0Var.c();
        t0 t0VarA = r0Var.a();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            D2 d2 = (D2) it.next();
            if (t0VarA.i()) {
                t0VarA = a(r0Var);
            }
            t0VarA.a(d2);
            while (t0VarA.i()) {
                t0VarA.a();
                t0VarA = a(r0Var);
                boolean zH = t0VarA.h();
                t0VarA.a(d2);
                if (zH && t0VarA.i()) {
                    throw new C1727iB("Class " + d2.toString() + " does not fit into a single dex file.");
                }
            }
            t0VarA.c();
        }
    }
}
