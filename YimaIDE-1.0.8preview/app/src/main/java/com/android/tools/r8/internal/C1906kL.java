package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.IdentityHashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kL, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1906kL extends XK {
    public static final /* synthetic */ boolean d = true;
    public final IdentityHashMap a = new IdentityHashMap();
    public final C2481r30 b = new C2481r30();
    public final ArrayList c = new ArrayList();

    @Override // com.android.tools.r8.internal.XK
    public final void a(H5 h5, int i) {
        if (!d && this.b.containsKey(h5)) {
            x1f.a();
            return;
        }
        this.b.b(i, h5);
        if (h5.q().isEmpty()) {
            return;
        }
        int i2 = 0;
        for (PW pw : h5.q()) {
            IdentityHashMap identityHashMap = this.a;
            int i3 = i2 + 1;
            if (i >= 32768) {
                throw new C1345dk0("No support for more than 15-bit block index.");
            }
            boolean z = W7.a;
            if (!z && !W7.b(i)) {
                x1f.a();
                return;
            }
            int i4 = (i & 65535) << 16;
            if (!z && !W7.b(i2)) {
                x1f.a();
                return;
            }
            int i5 = (i2 & 65535) | Integer.MIN_VALUE | i4;
            if (!C2249oL.b && i5 >= 0) {
                x1f.a();
                return;
            } else {
                identityHashMap.put(pw, new C2249oL(i5));
                i2 = i3;
            }
        }
        this.c.add(Integer.valueOf(i));
        this.c.add(Integer.valueOf(i2));
    }

    @Override // com.android.tools.r8.internal.XK
    public final boolean b(Object obj, int i) {
        C2249oL c2249oL = (C2249oL) this.a.get((C2543rl0) obj);
        boolean z = d;
        if (!z && c2249oL.a()) {
            x1f.a();
            return false;
        }
        if (z) {
            return true;
        }
        if (!C2249oL.b && c2249oL.a()) {
            x1f.a();
            return false;
        }
        if (i == c2249oL.a) {
            return true;
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.XK
    public final boolean b() {
        return false;
    }

    @Override // com.android.tools.r8.internal.XK
    public final Object a(Object obj) {
        return (C2249oL) this.a.get((C2543rl0) obj);
    }

    @Override // com.android.tools.r8.internal.XK
    public final Object a(Object obj, int i) {
        C2543rl0 c2543rl0 = (C2543rl0) obj;
        if (c2543rl0.j()) {
            C2249oL c2249oL = (C2249oL) this.a.get(c2543rl0);
            if (d || c2249oL != null) {
                return c2249oL;
            }
            x1f.a();
            return null;
        }
        if (!C2249oL.b && i < 0) {
            x1f.a();
            return null;
        }
        C2249oL c2249oL2 = new C2249oL(i);
        this.a.put(c2543rl0, c2249oL2);
        return c2249oL2;
    }

    @Override // com.android.tools.r8.internal.XK
    public final int a(H5 h5) {
        if (d || this.b.containsKey(h5)) {
            return this.b.b(h5);
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.XK
    public final AbstractC2420qL a() {
        if (this.c.isEmpty()) {
            return C2078mL.b;
        }
        int[] iArr = new int[this.c.size()];
        for (int i = 0; i < this.c.size(); i++) {
            iArr[i] = ((Integer) this.c.get(i)).intValue();
        }
        return new C2078mL(iArr);
    }
}
