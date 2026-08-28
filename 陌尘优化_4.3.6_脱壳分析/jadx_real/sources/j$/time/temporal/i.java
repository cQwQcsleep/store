package j$.time.temporal;

import j$.time.Duration;

/* loaded from: /workspace/unpacked/classes3.dex */
enum i implements u {
    WEEK_BASED_YEARS("WeekBasedYears"),
    QUARTER_YEARS("QuarterYears");

    private final String a;

    static {
        Duration duration = Duration.c;
    }

    i(String str) {
        this.a = str;
    }

    @Override // j$.time.temporal.u
    public final m j(m mVar, long j) {
        int i = c.a[ordinal()];
        if (i == 1) {
            return mVar.d(j$.com.android.tools.r8.a.f(mVar.k(r0), j), j.c);
        }
        if (i == 2) {
            return mVar.e(j / 4, b.YEARS).e((j % 4) * 3, b.MONTHS);
        }
        throw new IllegalStateException("Unreachable");
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.a;
    }
}
