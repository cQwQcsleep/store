package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.r7, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2488r7 {
    public static final /* synthetic */ boolean b = true;
    public final C0919Vz a = new C0919Vz(16);

    public C2488r7(InterfaceC1981lA... interfaceC1981lAArr) {
        if (!b && interfaceC1981lAArr.length <= 0) {
            x1f.a();
            throw null;
        }
        for (InterfaceC1981lA interfaceC1981lA : interfaceC1981lAArr) {
            if (!b && interfaceC1981lA.size() <= 0) {
                x1f.a();
                throw null;
            }
            C0919Vz c0919Vz = this.a;
            c0919Vz.a(c0919Vz.size(), interfaceC1981lA);
        }
    }

    public final long a(AB ab) {
        long j;
        C0919Vz c0919Vz = this.a;
        long jI = (((long) c0919Vz.i(c0919Vz.c - 1)) - ((long) this.a.i(0))) + 1;
        if (!FA.a(ab, jI)) {
            return -9223372036854775807L;
        }
        long j2 = ab.a() ? 12 : 3;
        long j3 = this.a.c;
        long j4 = (ab.a() ? j3 * 8 : (j3 * 4) + 2) + j2;
        long j5 = ab.a() ? 16 : 3;
        if (!ab.a()) {
            j = (jI * 2) + 4;
        } else {
            if (!FA.n && jI > 4294967295L) {
                x1f.a();
                return 0L;
            }
            j = jI * 4;
        }
        return j4 - (j + j5);
    }
}
