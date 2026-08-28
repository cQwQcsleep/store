package j$.time.temporal;

import j$.time.C0063a;

/* loaded from: /workspace/unpacked/classes3.dex */
enum k implements r {
    JULIAN_DAY("JulianDay", 2440588),
    MODIFIED_JULIAN_DAY("ModifiedJulianDay", 40587),
    RATA_DIE("RataDie", 719163);

    private static final long serialVersionUID = -7501623920830201812L;
    private final transient String a;
    private final transient w b;
    private final transient long c;

    @Override // j$.time.temporal.r
    public final boolean v() {
        return true;
    }

    static {
        b bVar = b.NANOS;
    }

    k(String str, long j) {
        this.a = str;
        this.b = w.j((-365243219162L) + j, 365241780471L + j);
        this.c = j;
    }

    @Override // j$.time.temporal.r
    public final w j() {
        return this.b;
    }

    @Override // j$.time.temporal.r
    public final boolean m(o oVar) {
        return oVar.f(a.EPOCH_DAY);
    }

    @Override // j$.time.temporal.r
    public final w s(o oVar) {
        if (!oVar.f(a.EPOCH_DAY)) {
            throw new C0063a("Unsupported field: " + this);
        }
        return this.b;
    }

    @Override // j$.time.temporal.r
    public final long k(o oVar) {
        return oVar.s(a.EPOCH_DAY) + this.c;
    }

    @Override // j$.time.temporal.r
    public final m n(m mVar, long j) {
        if (!this.b.i(j)) {
            throw new C0063a("Invalid value: " + this.a + " " + j);
        }
        return mVar.d(j$.com.android.tools.r8.a.m(j, this.c), a.EPOCH_DAY);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.a;
    }
}
