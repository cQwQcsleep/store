package j$.time.chrono;

import j$.time.C0063a;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/* renamed from: j$.time.chrono.a, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public abstract class AbstractC0065a implements n {
    private static final ConcurrentHashMap a = new ConcurrentHashMap();
    private static final ConcurrentHashMap b = new ConcurrentHashMap();
    public static final /* synthetic */ int c = 0;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return i().compareTo(((n) obj).i());
    }

    static {
        new Locale("ja", "JP", "JP");
    }

    static n k(AbstractC0065a abstractC0065a, String str) {
        String strR;
        n nVar = (n) a.putIfAbsent(str, abstractC0065a);
        if (nVar == null && (strR = abstractC0065a.r()) != null) {
            b.putIfAbsent(strR, abstractC0065a);
        }
        return nVar;
    }

    static n j(String str) {
        Objects.a(str, "id");
        while (true) {
            ConcurrentHashMap concurrentHashMap = a;
            n nVar = (n) concurrentHashMap.get(str);
            if (nVar == null) {
                nVar = (n) b.get(str);
            }
            if (nVar != null) {
                return nVar;
            }
            if (concurrentHashMap.get("ISO") != null) {
                Iterator it = ServiceLoader.load(n.class).iterator();
                while (it.hasNext()) {
                    n nVar2 = (n) it.next();
                    if (str.equals(nVar2.i()) || str.equals(nVar2.r())) {
                        return nVar2;
                    }
                }
                throw new C0063a("Unknown chronology: ".concat(str));
            }
            q qVar = q.o;
            k(qVar, qVar.i());
            x xVar = x.d;
            k(xVar, xVar.i());
            C c2 = C.d;
            k(c2, c2.i());
            I i = I.d;
            k(i, i.i());
            try {
                for (AbstractC0065a abstractC0065a : Arrays.asList(new AbstractC0065a[0])) {
                    if (!abstractC0065a.i().equals("ISO")) {
                        k(abstractC0065a, abstractC0065a.i());
                    }
                }
                u uVar = u.d;
                k(uVar, uVar.i());
            } catch (Throwable th) {
                throw new ServiceConfigurationError(th.getMessage(), th);
            }
        }
    }

    protected AbstractC0065a() {
    }

    @Override // j$.time.chrono.n
    public InterfaceC0069e o(j$.time.h hVar) {
        try {
            return l(hVar).u(j$.time.j.E(hVar));
        } catch (C0063a e) {
            throw new C0063a("Unable to obtain ChronoLocalDateTime from TemporalAccessor: " + j$.time.h.class, e);
        }
    }

    @Override // j$.time.chrono.n
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AbstractC0065a) && i().compareTo(((AbstractC0065a) obj).i()) == 0;
    }

    @Override // j$.time.chrono.n
    public final int hashCode() {
        return getClass().hashCode() ^ i().hashCode();
    }

    @Override // j$.time.chrono.n
    public final String toString() {
        return i();
    }
}
