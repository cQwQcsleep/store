package j$.time.format;

import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class o {
    private static final b f = new b();
    private o a;
    private final o b;
    private final ArrayList c;
    private final boolean d;
    private int e;

    static {
        HashMap map = new HashMap();
        map.put('G', j$.time.temporal.a.ERA);
        map.put('y', j$.time.temporal.a.YEAR_OF_ERA);
        map.put('u', j$.time.temporal.a.YEAR);
        j$.time.temporal.r rVar = j$.time.temporal.j.a;
        map.put('Q', rVar);
        map.put('q', rVar);
        j$.time.temporal.a aVar = j$.time.temporal.a.MONTH_OF_YEAR;
        map.put('M', aVar);
        map.put('L', aVar);
        map.put('D', j$.time.temporal.a.DAY_OF_YEAR);
        map.put('d', j$.time.temporal.a.DAY_OF_MONTH);
        map.put('F', j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        j$.time.temporal.a aVar2 = j$.time.temporal.a.DAY_OF_WEEK;
        map.put('E', aVar2);
        map.put('c', aVar2);
        map.put('e', aVar2);
        map.put('a', j$.time.temporal.a.AMPM_OF_DAY);
        map.put('H', j$.time.temporal.a.HOUR_OF_DAY);
        map.put('k', j$.time.temporal.a.CLOCK_HOUR_OF_DAY);
        map.put('K', j$.time.temporal.a.HOUR_OF_AMPM);
        map.put('h', j$.time.temporal.a.CLOCK_HOUR_OF_AMPM);
        map.put('m', j$.time.temporal.a.MINUTE_OF_HOUR);
        map.put('s', j$.time.temporal.a.SECOND_OF_MINUTE);
        j$.time.temporal.a aVar3 = j$.time.temporal.a.NANO_OF_SECOND;
        map.put('S', aVar3);
        map.put('A', j$.time.temporal.a.MILLI_OF_DAY);
        map.put('n', aVar3);
        map.put('N', j$.time.temporal.a.NANO_OF_DAY);
        map.put('g', j$.time.temporal.l.a);
    }

    public o() {
        this.a = this;
        this.c = new ArrayList();
        this.e = -1;
        this.b = null;
        this.d = false;
    }

    private o(o oVar) {
        this.a = this;
        this.c = new ArrayList();
        this.e = -1;
        this.b = oVar;
        this.d = true;
    }

    public final void q() {
        d(l.SENSITIVE);
    }

    public final void p() {
        d(l.INSENSITIVE);
    }

    public final void s() {
        d(l.STRICT);
    }

    public final void r() {
        d(l.LENIENT);
    }

    public final void k(j$.time.temporal.r rVar, int i) {
        Objects.a(rVar, "field");
        if (i < 1 || i > 19) {
            throw new IllegalArgumentException("The width must be from 1 to 19 inclusive but was " + i);
        }
        j(new j(rVar, i, i, v.NOT_NEGATIVE));
    }

    public final void l(j$.time.temporal.r rVar, int i, int i2, v vVar) {
        if (i == i2 && vVar == v.NOT_NEGATIVE) {
            k(rVar, i2);
            return;
        }
        Objects.a(rVar, "field");
        Objects.a(vVar, "signStyle");
        if (i < 1 || i > 19) {
            throw new IllegalArgumentException("The minimum width must be from 1 to 19 inclusive but was " + i);
        }
        if (i2 < 1 || i2 > 19) {
            throw new IllegalArgumentException("The maximum width must be from 1 to 19 inclusive but was " + i2);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + i2 + " < " + i);
        }
        j(new j(rVar, i, i2, vVar));
    }

    private void j(j jVar) {
        j jVarB;
        o oVar = this.a;
        int i = oVar.e;
        if (i >= 0) {
            j jVar2 = (j) oVar.c.get(i);
            int i2 = jVar.b;
            int i3 = jVar.c;
            if (i2 == i3 && jVar.d == v.NOT_NEGATIVE) {
                jVarB = jVar2.c(i3);
                d(jVar.b());
                this.a.e = i;
            } else {
                jVarB = jVar2.b();
                this.a.e = d(jVar);
            }
            this.a.c.set(i, jVarB);
            return;
        }
        oVar.e = d(jVar);
    }

    public final void b(j$.time.temporal.a aVar) {
        h hVar = new h(aVar, 0, 9, true, 0);
        Objects.a(aVar, "field");
        if (aVar.j().g()) {
            d(hVar);
        } else {
            throw new IllegalArgumentException("Field must have a fixed set of values: " + aVar);
        }
    }

    public final void i(j$.time.temporal.a aVar, HashMap map) {
        Objects.a(aVar, "field");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        w wVar = w.FULL;
        d(new n(aVar, wVar, new c(new s(Collections.singletonMap(wVar, linkedHashMap)))));
    }

    public final void c() {
        d(new i());
    }

    public final void h() {
        d(k.e);
    }

    public final void g(String str, String str2) {
        d(new k(str, str2));
    }

    public final void m() {
        d(new m(f, 1));
    }

    public final void e(char c) {
        d(new e(c));
    }

    public final void f(String str) {
        if (str.isEmpty()) {
            return;
        }
        if (str.length() == 1) {
            d(new e(str.charAt(0)));
        } else {
            d(new m(str, 0));
        }
    }

    public final void a(a aVar) {
        d(aVar.e());
    }

    public final void o() {
        o oVar = this.a;
        oVar.e = -1;
        this.a = new o(oVar);
    }

    public final void n() {
        o oVar = this.a;
        if (oVar.b == null) {
            throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
        }
        if (oVar.c.size() > 0) {
            o oVar2 = this.a;
            f fVar = new f(oVar2.c, oVar2.d);
            this.a = this.a.b;
            d(fVar);
            return;
        }
        this.a = this.a.b;
    }

    private int d(g gVar) {
        Objects.a(gVar, "pp");
        o oVar = this.a;
        oVar.getClass();
        oVar.c.add(gVar);
        this.a.e = -1;
        return r2.c.size() - 1;
    }

    public final void v() {
        u(Locale.getDefault(), u.SMART, null);
    }

    final a t(u uVar, j$.time.chrono.u uVar2) {
        return u(Locale.getDefault(), uVar, uVar2);
    }

    private a u(Locale locale, u uVar, j$.time.chrono.u uVar2) {
        Objects.a(locale, "locale");
        while (this.a.b != null) {
            n();
        }
        f fVar = new f(this.c, false);
        t tVar = t.a;
        return new a(fVar, locale, uVar, uVar2);
    }
}
