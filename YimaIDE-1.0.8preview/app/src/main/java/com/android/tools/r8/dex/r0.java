package com.android.tools.r8.dex;

import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C1131bA;
import com.android.tools.r8.internal.DC;
import com.android.tools.r8.internal.HC;
import com.android.tools.r8.internal.NC;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class r0 {
    public static final /* synthetic */ boolean h = true;
    public final List a;
    public final ArrayList b;
    public final C0333y c;
    public final C1131bA d;
    public DC e;
    public HC f;
    public final FeatureSplit g;

    public r0(List list, List list2, C0333y c0333y, C1131bA c1131bA) {
        this.a = list;
        ArrayList arrayList = new ArrayList(list2);
        this.b = arrayList;
        this.c = c0333y;
        this.d = c1131bA;
        if (list2.size() > 0) {
            this.g = ((t0) list2.get(0)).d;
        }
        this.e = new DC(arrayList);
        c();
    }

    public final t0 a(Predicate predicate) {
        t0 t0Var;
        do {
            if (this.f.hasNext()) {
                t0Var = (t0) this.f.next();
            } else {
                t0Var = new t0(this.d.b(), this.c, this.g);
                this.a.add(t0Var);
                this.b.add(t0Var);
                this.e = NC.a(this.b);
            }
            if (t0Var.b.c.isEmpty()) {
                if (h || predicate.test(t0Var)) {
                    break;
                    break;
                }
                x1f.a();
                return null;
            }
        } while (!predicate.test(t0Var));
        return t0Var;
    }

    public final void b() {
        this.e = NC.a(this.b);
        c();
    }

    public final void c() {
        DC dc = this.e;
        int size = this.b.size();
        dc.getClass();
        if (size >= 0) {
            this.f = new HC(size, dc);
        } else {
            w01.a("limit is negative");
        }
    }

    public final t0 a() {
        return (t0) this.f.next();
    }
}
