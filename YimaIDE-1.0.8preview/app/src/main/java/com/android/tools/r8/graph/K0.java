package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.Wf0;
import java.util.Map;
import java.util.TreeSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class K0 {
    public static final /* synthetic */ boolean h = true;
    public final boolean a;
    public final int b;
    public final H2 c = null;
    public final boolean d;
    public final boolean e;
    public final Map<Integer, C0230j0> f;
    public final AbstractC2004lX g;

    public K0(boolean z, int i, boolean z2, boolean z3, AbstractC0706Nu abstractC0706Nu, AbstractC2004lX abstractC2004lX) {
        this.a = z;
        this.b = i;
        this.d = z2;
        this.e = z3;
        this.f = abstractC0706Nu;
        this.g = abstractC2004lX;
        if (h || abstractC2004lX != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("pc ");
        }
        sb.append(Wf0.a(this.b, 2));
        if (this.c != null) {
            sb.append(", file ");
            sb.append(this.c);
        }
        sb.append(", ");
        sb.append(this.g);
        if (this.d) {
            sb.append(", prologue_end = true");
        }
        if (this.e) {
            sb.append(", epilogue_begin = true");
        }
        if (!this.f.isEmpty()) {
            sb.append(", locals: [");
            boolean z2 = true;
            for (Integer num : new TreeSet(this.f.keySet())) {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(", ");
                }
                sb.append(num);
                sb.append(" -> ");
                sb.append(this.f.get(num));
            }
            sb.append("]");
        }
        return sb.toString();
    }

    public AbstractC2004lX b() {
        return this.g;
    }

    public final String toString() {
        return a(true);
    }

    public int a() {
        return this.g.f();
    }
}
