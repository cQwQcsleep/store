package com.android.tools.r8.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Es, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0471Es {
    public final ThreadLocal a;
    public final ConcurrentHashMap b;
    public final C0692Ng c;
    public final C1472fD d;
    public final List e;
    public final boolean f;

    public C0471Es() {
        C2975wo c2975wo = C2975wo.d;
        Map map = Collections.EMPTY_MAP;
        List list = Collections.EMPTY_LIST;
        this.a = new ThreadLocal();
        this.b = new ConcurrentHashMap();
        C0692Ng c0692Ng = new C0692Ng(list, map);
        this.c = c0692Ng;
        this.f = true;
        ArrayList arrayList = new ArrayList();
        arrayList.add(AbstractC2197nj0.A);
        arrayList.add(UU.b);
        arrayList.add(c2975wo);
        arrayList.addAll(list);
        arrayList.add(AbstractC2197nj0.p);
        arrayList.add(AbstractC2197nj0.g);
        arrayList.add(AbstractC2197nj0.d);
        arrayList.add(AbstractC2197nj0.e);
        arrayList.add(AbstractC2197nj0.f);
        Di0 di0 = AbstractC2197nj0.k;
        arrayList.add(new C1090aj0(Long.TYPE, Long.class, di0));
        arrayList.add(new C1090aj0(Double.TYPE, Double.class, new C3234zs()));
        arrayList.add(new C1090aj0(Float.TYPE, Float.class, new C0367As()));
        arrayList.add(DS.a);
        arrayList.add(AbstractC2197nj0.h);
        arrayList.add(AbstractC2197nj0.i);
        arrayList.add(new Zi0(AtomicLong.class, new C3134yi0(new C0393Bs(di0))));
        arrayList.add(new Zi0(AtomicLongArray.class, new C3134yi0(new C0419Cs(di0))));
        arrayList.add(AbstractC2197nj0.j);
        arrayList.add(AbstractC2197nj0.l);
        arrayList.add(AbstractC2197nj0.q);
        arrayList.add(AbstractC2197nj0.r);
        arrayList.add(new Zi0(BigDecimal.class, AbstractC2197nj0.m));
        arrayList.add(new Zi0(BigInteger.class, AbstractC2197nj0.n));
        arrayList.add(new Zi0(qJ.class, AbstractC2197nj0.o));
        arrayList.add(AbstractC2197nj0.s);
        arrayList.add(AbstractC2197nj0.t);
        arrayList.add(AbstractC2197nj0.v);
        arrayList.add(AbstractC2197nj0.w);
        arrayList.add(AbstractC2197nj0.y);
        arrayList.add(AbstractC2197nj0.u);
        arrayList.add(AbstractC2197nj0.b);
        arrayList.add(C0538Hh.b);
        arrayList.add(AbstractC2197nj0.x);
        if (AbstractC2527rd0.a) {
            arrayList.add(AbstractC2527rd0.c);
            arrayList.add(AbstractC2527rd0.b);
            arrayList.add(AbstractC2527rd0.d);
        }
        arrayList.add(P3.b);
        arrayList.add(AbstractC2197nj0.a);
        arrayList.add(new C1588ge(c0692Ng));
        arrayList.add(new HM(c0692Ng));
        C1472fD c1472fD = new C1472fD(c0692Ng);
        this.d = c1472fD;
        arrayList.add(c1472fD);
        arrayList.add(AbstractC2197nj0.B);
        arrayList.add(new I40(c0692Ng, c2975wo, c1472fD, list));
        this.e = Collections.unmodifiableList(arrayList);
    }

    public final AbstractC3220zi0 a(Fj0 fj0) {
        boolean z;
        AbstractC3220zi0 abstractC3220zi0 = (AbstractC3220zi0) this.b.get(fj0);
        if (abstractC3220zi0 != null) {
            return abstractC3220zi0;
        }
        Map map = (Map) this.a.get();
        if (map == null) {
            map = new HashMap();
            this.a.set(map);
            z = true;
        } else {
            AbstractC3220zi0 abstractC3220zi1 = (AbstractC3220zi0) map.get(fj0);
            if (abstractC3220zi1 != null) {
                return abstractC3220zi1;
            }
            z = false;
        }
        try {
            C0445Ds c0445Ds = new C0445Ds();
            map.put(fj0, c0445Ds);
            Iterator it = this.e.iterator();
            AbstractC3220zi0 abstractC3220zi0A = null;
            while (it.hasNext()) {
                abstractC3220zi0A = ((Ai0) it.next()).a(this, fj0);
                if (abstractC3220zi0A != null) {
                    if (c0445Ds.a != null) {
                        throw new AssertionError("Delegate is already set");
                    }
                    c0445Ds.a = abstractC3220zi0A;
                    map.put(fj0, abstractC3220zi0A);
                    break;
                }
            }
            if (z) {
                this.a.remove();
            }
            if (abstractC3220zi0A == null) {
                aca.a("GSON (2.10.1) cannot handle ", fj0);
                return null;
            }
            if (z) {
                this.b.putAll(map);
            }
            return abstractC3220zi0A;
        } catch (Throwable th) {
            if (z) {
                this.a.remove();
            }
            throw th;
        }
    }

    public final String toString() {
        return "{serializeNulls:false,factories:" + this.e + ",instanceCreators:" + this.c + "}";
    }

    public final String a(LinkedHashMap linkedHashMap) {
        Class cls = linkedHashMap.getClass();
        StringWriter stringWriter = new StringWriter();
        try {
            a(linkedHashMap, cls, a(stringWriter));
            return stringWriter.toString();
        } catch (IOException e) {
            throw new C1729iD(e);
        }
    }

    public final void a(LinkedHashMap linkedHashMap, Class cls, C2754uD c2754uD) {
        AbstractC3220zi0 abstractC3220zi0A = a(new Fj0(cls));
        boolean z = c2754uD.e;
        c2754uD.e = true;
        boolean z2 = c2754uD.f;
        c2754uD.f = this.f;
        boolean z3 = c2754uD.h;
        c2754uD.h = false;
        try {
            try {
                try {
                    abstractC3220zi0A.a(c2754uD, linkedHashMap);
                    c2754uD.e = z;
                    c2754uD.f = z2;
                    c2754uD.h = z3;
                } catch (IOException e) {
                    throw new C1729iD(e);
                }
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.10.1): " + e2.getMessage(), e2);
            }
        } catch (Throwable th) {
            c2754uD.e = z;
            c2754uD.f = z2;
            c2754uD.h = z3;
            throw th;
        }
    }

    public final C2754uD a(Writer writer) {
        C2754uD c2754uD = new C2754uD(writer);
        c2754uD.f = this.f;
        c2754uD.e = false;
        c2754uD.h = false;
        return c2754uD;
    }
}
