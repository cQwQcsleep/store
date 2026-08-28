package j$.time.temporal;

import j$.time.chrono.AbstractC0073i;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class h implements r {
    public static final h DAY_OF_QUARTER;
    public static final h QUARTER_OF_YEAR;
    public static final h WEEK_BASED_YEAR;
    public static final h WEEK_OF_WEEK_BASED_YEAR;
    private static final int[] a;
    private static final /* synthetic */ h[] b;

    @Override // j$.time.temporal.r
    public final boolean v() {
        return true;
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) b.clone();
    }

    static {
        h hVar = new h() { // from class: j$.time.temporal.d
            @Override // j$.time.temporal.r
            public final w j() {
                return w.k(90L, 92L);
            }

            @Override // j$.time.temporal.r
            public final boolean m(o oVar) {
                if (oVar.f(a.DAY_OF_YEAR) && oVar.f(a.MONTH_OF_YEAR) && oVar.f(a.YEAR)) {
                    r rVar = j.a;
                    if (AbstractC0073i.p(oVar).equals(j$.time.chrono.u.d)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.r
            public final w s(o oVar) {
                if (!m(oVar)) {
                    throw new v("Unsupported field: DayOfQuarter");
                }
                long jS = oVar.s(h.QUARTER_OF_YEAR);
                if (jS == 1) {
                    long jS2 = oVar.s(a.YEAR);
                    j$.time.chrono.u.d.getClass();
                    return j$.time.chrono.u.m(jS2) ? w.j(1L, 91L) : w.j(1L, 90L);
                }
                if (jS == 2) {
                    return w.j(1L, 91L);
                }
                if (jS == 3 || jS == 4) {
                    return w.j(1L, 92L);
                }
                return j();
            }

            @Override // j$.time.temporal.r
            public final long k(o oVar) {
                if (!m(oVar)) {
                    throw new v("Unsupported field: DayOfQuarter");
                }
                int iK = oVar.k(a.DAY_OF_YEAR);
                int iK2 = oVar.k(a.MONTH_OF_YEAR);
                long jS = oVar.s(a.YEAR);
                int[] iArr = h.a;
                int i = (iK2 - 1) / 3;
                j$.time.chrono.u.d.getClass();
                return iK - iArr[i + (j$.time.chrono.u.m(jS) ? 4 : 0)];
            }

            @Override // j$.time.temporal.r
            public final m n(m mVar, long j) {
                long jK = k(mVar);
                j().b(j, this);
                a aVar = a.DAY_OF_YEAR;
                return mVar.d((j - jK) + mVar.s(aVar), aVar);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "DayOfQuarter";
            }
        };
        DAY_OF_QUARTER = hVar;
        h hVar2 = new h() { // from class: j$.time.temporal.e
            @Override // j$.time.temporal.r
            public final w j() {
                return w.j(1L, 4L);
            }

            @Override // j$.time.temporal.r
            public final boolean m(o oVar) {
                if (oVar.f(a.MONTH_OF_YEAR)) {
                    r rVar = j.a;
                    if (AbstractC0073i.p(oVar).equals(j$.time.chrono.u.d)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.r
            public final long k(o oVar) {
                if (!m(oVar)) {
                    throw new v("Unsupported field: QuarterOfYear");
                }
                return (oVar.s(a.MONTH_OF_YEAR) + 2) / 3;
            }

            @Override // j$.time.temporal.r
            public final w s(o oVar) {
                if (!m(oVar)) {
                    throw new v("Unsupported field: QuarterOfYear");
                }
                return j();
            }

            @Override // j$.time.temporal.r
            public final m n(m mVar, long j) {
                long jK = k(mVar);
                j().b(j, this);
                a aVar = a.MONTH_OF_YEAR;
                return mVar.d(((j - jK) * 3) + mVar.s(aVar), aVar);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "QuarterOfYear";
            }
        };
        QUARTER_OF_YEAR = hVar2;
        h hVar3 = new h() { // from class: j$.time.temporal.f
            @Override // j$.time.temporal.r
            public final w j() {
                return w.k(52L, 53L);
            }

            @Override // j$.time.temporal.r
            public final boolean m(o oVar) {
                if (oVar.f(a.EPOCH_DAY)) {
                    r rVar = j.a;
                    if (AbstractC0073i.p(oVar).equals(j$.time.chrono.u.d)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.r
            public final w s(o oVar) {
                if (!m(oVar)) {
                    throw new v("Unsupported field: WeekOfWeekBasedYear");
                }
                return h.F(j$.time.f.E(oVar));
            }

            @Override // j$.time.temporal.r
            public final long k(o oVar) {
                if (!m(oVar)) {
                    throw new v("Unsupported field: WeekOfWeekBasedYear");
                }
                return h.C(j$.time.f.E(oVar));
            }

            @Override // j$.time.temporal.r
            public final m n(m mVar, long j) {
                j().b(j, this);
                return mVar.e(j$.com.android.tools.r8.a.m(j, k(mVar)), b.WEEKS);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekOfWeekBasedYear";
            }
        };
        WEEK_OF_WEEK_BASED_YEAR = hVar3;
        h hVar4 = new h() { // from class: j$.time.temporal.g
            @Override // j$.time.temporal.r
            public final w j() {
                return a.YEAR.j();
            }

            @Override // j$.time.temporal.r
            public final boolean m(o oVar) {
                if (oVar.f(a.EPOCH_DAY)) {
                    r rVar = j.a;
                    if (AbstractC0073i.p(oVar).equals(j$.time.chrono.u.d)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.r
            public final long k(o oVar) {
                if (!m(oVar)) {
                    throw new v("Unsupported field: WeekBasedYear");
                }
                return h.G(j$.time.f.E(oVar));
            }

            @Override // j$.time.temporal.r
            public final w s(o oVar) {
                if (!m(oVar)) {
                    throw new v("Unsupported field: WeekBasedYear");
                }
                return j();
            }

            @Override // j$.time.temporal.r
            public final m n(m mVar, long j) {
                if (!m(mVar)) {
                    throw new v("Unsupported field: WeekBasedYear");
                }
                int iA = a.YEAR.j().a(j, h.WEEK_BASED_YEAR);
                j$.time.f fVarE = j$.time.f.E(mVar);
                int iK = fVarE.k(a.DAY_OF_WEEK);
                int iC = h.C(fVarE);
                if (iC == 53 && h.H(iA) == 52) {
                    iC = 52;
                }
                return mVar.m(j$.time.f.N(iA, 1, 4).R(((iC - 1) * 7) + (iK - r6.k(r0))));
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekBasedYear";
            }
        };
        WEEK_BASED_YEAR = hVar4;
        b = new h[]{hVar, hVar2, hVar3, hVar4};
        a = new int[]{0, 90, 181, 273, 0, 91, 182, 274};
    }

    static w F(j$.time.f fVar) {
        return w.j(1L, H(G(fVar)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int H(int i) {
        j$.time.f fVarN = j$.time.f.N(i, 1, 1);
        if (fVarN.G() != j$.time.c.THURSDAY) {
            return (fVarN.G() == j$.time.c.WEDNESDAY && fVarN.L()) ? 53 : 52;
        }
        return 53;
    }

    static int C(j$.time.f fVar) {
        int iOrdinal = fVar.G().ordinal();
        int i = 1;
        int iH = fVar.H() - 1;
        int i2 = (3 - iOrdinal) + iH;
        int i3 = i2 - ((i2 / 7) * 7);
        int i4 = i3 - 3;
        if (i4 < -3) {
            i4 = i3 + 4;
        }
        if (iH < i4) {
            return (int) w.j(1L, H(G(fVar.X(180).T(-1L)))).d();
        }
        int i5 = ((iH - i4) / 7) + 1;
        if (i5 != 53 || i4 == -3 || (i4 == -2 && fVar.L())) {
            i = i5;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int G(j$.time.f fVar) {
        int iJ = fVar.J();
        int iH = fVar.H();
        if (iH <= 3) {
            return iH - fVar.G().ordinal() < -2 ? iJ - 1 : iJ;
        }
        if (iH >= 363) {
            return ((iH - 363) - (fVar.L() ? 1 : 0)) - fVar.G().ordinal() >= 0 ? iJ + 1 : iJ;
        }
        return iJ;
    }
}
