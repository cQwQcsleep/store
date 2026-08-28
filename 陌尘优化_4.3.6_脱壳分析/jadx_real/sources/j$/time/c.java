package j$.time;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class c implements j$.time.temporal.o, j$.time.temporal.p {
    public static final c FRIDAY;
    public static final c MONDAY;
    public static final c SATURDAY;
    public static final c SUNDAY;
    public static final c THURSDAY;
    public static final c TUESDAY;
    public static final c WEDNESDAY;
    private static final c[] a;
    private static final /* synthetic */ c[] b;

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) b.clone();
    }

    static {
        c cVar = new c("MONDAY", 0);
        MONDAY = cVar;
        c cVar2 = new c("TUESDAY", 1);
        TUESDAY = cVar2;
        c cVar3 = new c("WEDNESDAY", 2);
        WEDNESDAY = cVar3;
        c cVar4 = new c("THURSDAY", 3);
        THURSDAY = cVar4;
        c cVar5 = new c("FRIDAY", 4);
        FRIDAY = cVar5;
        c cVar6 = new c("SATURDAY", 5);
        SATURDAY = cVar6;
        c cVar7 = new c("SUNDAY", 6);
        SUNDAY = cVar7;
        b = new c[]{cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar7};
        a = values();
    }

    public static c C(int i) {
        if (i < 1 || i > 7) {
            throw new C0063a("Invalid value for DayOfWeek: " + i);
        }
        return a[i - 1];
    }

    public final int getValue() {
        return ordinal() + 1;
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.DAY_OF_WEEK : rVar != null && rVar.m(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.DAY_OF_WEEK) {
            return rVar.j();
        }
        return j$.time.temporal.n.d(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.DAY_OF_WEEK) {
            return getValue();
        }
        return j$.time.temporal.n.a(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.DAY_OF_WEEK) {
            return getValue();
        }
        if (rVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        return rVar.k(this);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.DAYS;
        }
        return j$.time.temporal.n.c(this, tVar);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return mVar.d(getValue(), j$.time.temporal.a.DAY_OF_WEEK);
    }
}
