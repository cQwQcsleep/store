package com.android.tools.r8.internal;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ib, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0558Ib extends C3106yO {
    public final /* synthetic */ XO y;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0558Ib(int i, int i2, String str, String str2, XO xo) {
        super(i, i2, str, str2, null);
        this.y = xo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0, types: [com.android.tools.r8.internal.Ib, com.android.tools.r8.internal.yO] */
    /* JADX WARN: Type inference failed for: r3v13, types: [com.android.tools.r8.internal.XO] */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.android.tools.r8.internal.n2] */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.android.tools.r8.internal.n2] */
    @Override // com.android.tools.r8.internal.C3106yO, com.android.tools.r8.internal.XO
    public final void c() {
        boolean z;
        boolean z2;
        int i = this.t;
        int i2 = this.s;
        XO xo = this.y;
        int i3 = 1;
        if (xo instanceof C0610Kb) {
            C0610Kb c0610Kb = (C0610Kb) xo;
            int i4 = c0610Kb.d.c;
            z2 = !((i4 & 1) == 1 || (i4 & 2) == 2);
            z = (c0610Kb.c & 65535) >= 51 && (i4 & 2) != 2;
        } else {
            z = false;
            z2 = false;
        }
        ?? c0532Hb = z ? new C0532Hb(new P5()) : new C2136n2(new P5());
        try {
            if (z2) {
                c0532Hb.a(this);
            } else {
                c0532Hb.b(this);
            }
            c0532Hb = this.y;
            if (c0532Hb != 0) {
                this.t = i;
                this.s = i2;
                a(c0532Hb);
            }
        } catch (C2222o2 | IndexOutOfBoundsException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter((Writer) stringWriter, true);
            Xg0 xg0 = new Xg0(589824);
            Nh0 nh0 = new Nh0(xg0);
            printWriter.println(this.d + this.e);
            int i5 = 0;
            while (true) {
                C2042lw c2042lw = this.q;
                if (i5 >= c2042lw.b) {
                    break;
                }
                c2042lw.j(i5).a(nh0);
                StringBuilder sb = new StringBuilder();
                C0936Wq c0936Wq = c0532Hb.e[i5];
                if (c0936Wq == null) {
                    sb.append('?');
                } else {
                    for (int i6 = 0; i6 < c0936Wq.c; i6++) {
                        sb.append(AbstractC0480Fb.e(((O5) c0936Wq.a(i6)).toString()));
                        sb.append(' ');
                    }
                    sb.append(" : ");
                    int i7 = 0;
                    while (i7 < c0936Wq.d) {
                        sb.append(AbstractC0480Fb.e(((O5) c0936Wq.b[c0936Wq.c + i7]).toString()));
                        sb.append(' ');
                        i7++;
                        i3 = i3;
                    }
                }
                int i8 = i3;
                while (sb.length() < this.s + this.t + 1) {
                    sb.append(' ');
                }
                printWriter.print(Integer.toString(100000 + i5).substring(i8));
                ArrayList arrayList = ((mY) xg0).c;
                printWriter.print(" " + ((Object) sb) + " : " + arrayList.get(arrayList.size() - i8));
                i5++;
                i3 = 1;
            }
            Iterator it = this.r.iterator();
            while (it.hasNext()) {
                ((C2708ti0) it.next()).a(nh0);
                ArrayList arrayList2 = ((mY) xg0).c;
                printWriter.print(" " + arrayList2.get(arrayList2.size() - 1));
            }
            printWriter.println();
            printWriter.close();
            throw new IllegalArgumentException(e.getMessage() + " " + stringWriter.toString(), e);
        }
    }
}
