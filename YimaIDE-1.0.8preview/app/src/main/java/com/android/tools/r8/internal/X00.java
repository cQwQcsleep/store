package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C0346z5;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X00 {
    public static final /* synthetic */ boolean e = true;
    public final int a;
    public final Z00 b;
    public OptionalInt c;
    public final List d;

    public X00(int i, Z00 z00, OptionalInt optionalInt, ArrayList arrayList) {
        this.a = i;
        this.b = z00;
        this.c = optionalInt;
        this.d = arrayList;
    }

    public final com.android.tools.r8.graph.I2 a(C1028a10 c1028a10) {
        Z00 z00 = this.b;
        z00.getClass();
        boolean z = z00 instanceof C2221o10;
        Z00 z01 = this.b;
        if (z) {
            int i = c1028a10.a(z01.a().h() - 51).a;
            if (i != 17 && i != 9) {
                return null;
            }
            AbstractC1964l10 abstractC1964l10 = (AbstractC1964l10) this.d.get(0);
            if (!e) {
                abstractC1964l10.getClass();
                if (!(abstractC1964l10 instanceof C3160z10)) {
                    x1f.a();
                    return null;
                }
            }
            return abstractC1964l10.d().a;
        }
        int i2 = z01.a;
        if (i2 == 9 || i2 == 17) {
            AbstractC1964l10 abstractC1964l11 = (AbstractC1964l10) this.d.get(0);
            if (!e) {
                abstractC1964l11.getClass();
                if (!(abstractC1964l11 instanceof DL)) {
                    x1f.a();
                    return null;
                }
            }
            return abstractC1964l11.a().a.i;
        }
        if (i2 != 27 && i2 != 49) {
            return null;
        }
        AbstractC1964l10 abstractC1964l12 = (AbstractC1964l10) this.d.get(1);
        if (!e) {
            abstractC1964l12.getClass();
            if (!(abstractC1964l12 instanceof C3160z10)) {
                x1f.a();
                return null;
            }
        }
        return abstractC1964l12.d().a;
    }

    public final C0346z5 b(C0333y c0333y, C1366e10 c1366e10) {
        boolean z = e;
        if (!z) {
            Z00 z00 = this.b;
            z00.getClass();
            if (!(z00 instanceof C2221o10)) {
                x1f.a();
                return null;
            }
        }
        Y00 y00 = ((C2307p10) c1366e10.e.get(a())).b;
        if (!z) {
            y00.getClass();
            if (!(y00 instanceof DL)) {
                x1f.a();
                return null;
            }
        }
        return ((C0229j) c0333y.g()).c(y00.a().a).r();
    }

    public final C0346z5 c(C0333y c0333y, C1366e10 c1366e10) {
        Z00 z00 = this.b;
        z00.getClass();
        AbstractC1964l10 abstractC1964l10 = z00 instanceof C2221o10 ? ((C2307p10) c1366e10.e.get(a())).a : (AbstractC1964l10) this.d.get(0);
        if (!e) {
            abstractC1964l10.getClass();
            if (!(abstractC1964l10 instanceof DL)) {
                x1f.a();
                return null;
            }
        }
        return ((C0229j) c0333y.g()).c(abstractC1964l10.a().a).o();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ProtoFieldInfo(number=");
        sb.append(this.a);
        sb.append(", type=");
        sb.append(this.b);
        sb.append(", aux data=");
        sb.append(this.c);
        sb.append(", objects=[");
        if (this.d.size() > 0) {
            sb.append(this.d.get(0));
            for (int i = 1; i < this.d.size(); i++) {
                sb.append(", ");
                sb.append(this.d.get(i));
            }
        }
        sb.append("])");
        return sb.toString();
    }

    public final Z00 b() {
        return this.b;
    }

    public final boolean c() {
        return this.c.isPresent();
    }

    public final int a() {
        if (!e && !this.c.isPresent()) {
            x1f.a();
            return 0;
        }
        return this.c.getAsInt();
    }

    public final C0346z5 a(C0333y c0333y, C1366e10 c1366e10) {
        boolean z = e;
        if (!z && !this.b.a(B10.a(c1366e10.b))) {
            x1f.a();
            return null;
        }
        int iA = a() / 32;
        if (!z) {
            LinkedList linkedList = c1366e10.d;
            if (iA >= (linkedList != null ? linkedList.size() : 0)) {
                x1f.a();
                return null;
            }
        }
        AbstractC1964l10 abstractC1964l10 = (AbstractC1964l10) c1366e10.d.get(iA);
        if (!z) {
            abstractC1964l10.getClass();
            if (!(abstractC1964l10 instanceof DL)) {
                x1f.a();
                return null;
            }
        }
        return ((C0229j) c0333y.g()).c(abstractC1964l10.a().a).r();
    }
}
