package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1476fH;
import defpackage.y68;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KW extends OW {
    public static final /* synthetic */ boolean n = true;
    public final C1476fH e;
    public final ME f;
    public final Map g;
    public final List h;
    public final List i;
    public final NW j;
    public int k;
    public String l;
    public final HashMap m;

    public KW(List list, C3097yF c3097yF, TG tg, C1647hH c1647hH, Map map, List list2, NW nw) {
        super(c3097yF, tg, C1902kH.a);
        this.k = 1;
        this.l = null;
        this.m = new HashMap();
        ME me = c1647hH.a;
        this.e = me.a;
        this.f = me;
        this.g = map;
        this.h = list;
        this.i = list2;
        this.j = nw;
    }

    @Override // com.android.tools.r8.internal.OW
    public final void a(StringBuilder sb, HE he) {
        FG fg = (FG) this.g.get(he);
        C2778ua0 c2778ua0 = new C2778ua0(sb, new y68(this));
        AbstractC3035xa0.a(fg, c2778ua0, this.c);
        this.m.put(he, c2778ua0.d.toString());
    }

    public final void b(StringBuilder sb, StringBuilder sb2, C1476fH c1476fH) {
        boolean z = n;
        if (!z && !c1476fH.equals(this.e)) {
            x1f.a();
            return;
        }
        if (d()) {
            sb2.append(this.l);
        } else if (!z && this.l != null) {
            x1f.a();
        } else {
            AbstractC3035xa0.b(new C2949wa0(sb), this.e);
        }
    }

    @Override // com.android.tools.r8.internal.OW
    public final void c(final StringBuilder sb) {
        AbstractC3035xa0.a(sb, this.f, new BiConsumer() { // from class: z68
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.b(sb, (StringBuilder) obj, (C1476fH) obj2);
            }
        });
        if (this.i.isEmpty()) {
            NW nw = this.j;
            nw.getClass();
            if (nw != NW.f) {
                sb.append(" { void finalize(); }");
            }
        }
    }

    @Override // com.android.tools.r8.internal.OW
    public final boolean d() {
        return !(this.h.isEmpty() && this.j == NW.c);
    }

    public final int e() {
        int i = this.k;
        this.k = i + 1;
        return i;
    }

    @Override // com.android.tools.r8.internal.OW
    public final List c() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.OW
    public final void a(final StringBuilder sb) {
        AbstractC3035xa0.a(sb, this.f, new BiConsumer() { // from class: a78
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(sb, (StringBuilder) obj, (C1476fH) obj2);
            }
        });
    }

    public final void a(StringBuilder sb, StringBuilder sb2, C1476fH c1476fH) {
        C2778ua0 c2778ua0 = new C2778ua0(sb, new y68(this));
        AbstractC3035xa0.b(c2778ua0, this.e);
        this.l = c2778ua0.d.toString();
    }

    @Override // com.android.tools.r8.internal.OW
    public final List a() {
        return this.h;
    }

    @Override // com.android.tools.r8.internal.OW
    public final String b() {
        return this.j.b;
    }

    @Override // com.android.tools.r8.internal.OW
    public final void b(StringBuilder sb, HE he) {
        String str;
        if (d() && (str = (String) this.m.get(he)) != null) {
            sb.append(str);
        } else {
            AbstractC3035xa0.a((FG) this.g.get(he), new C2949wa0(sb), this.c);
        }
    }
}
