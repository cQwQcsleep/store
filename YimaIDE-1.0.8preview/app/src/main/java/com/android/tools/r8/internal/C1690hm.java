package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1690hm implements InterfaceC1775im {
    public static final /* synthetic */ boolean g = true;
    public final H5 b;
    public final H5 c;
    public final Set d;
    public final Sm0 e = new Sm0(2);
    public H5 f;

    public C1690hm(H5 h5, H5 h6, Set set) {
        this.b = h5;
        this.c = h6;
        this.d = set;
        this.f = h6;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1775im
    public final boolean a(H5 h5) {
        H5 h5W;
        boolean zA;
        if (!g && this.f == null) {
            x01.a("DominatorChecker cannot be used after returning false.");
            return false;
        }
        Set set = this.d;
        if (set.contains(h5)) {
            return true;
        }
        if (h5.C()) {
            h5W = h5;
            do {
                if (!g && h5W == this.c) {
                    x1f.a();
                    return false;
                }
                h5W = h5W.w();
            } while (h5W.C());
            if (set.contains(h5W)) {
                set.add(h5);
                return true;
            }
        } else {
            h5W = h5;
        }
        if (this.e.b.contains(h5)) {
            this.e.b.clear();
            zA = a(this.f, this.c, h5W, this.e);
            this.f = h5W;
        } else {
            zA = a(this.b, this.f, h5, this.e);
            this.f = h5;
        }
        if (!zA) {
            this.f = null;
            return zA;
        }
        set.add(h5);
        if (h5W != h5) {
            set.add(h5W);
        }
        return zA;
    }

    public static boolean a(H5 h5, H5 h6, H5 h7, Sm0 sm0) {
        if (!g && sm0.b()) {
            x1f.a();
            return false;
        }
        sm0.b.add(h7);
        sm0.b((Iterable) h6.s());
        while (sm0.b()) {
            H5 h8 = (H5) sm0.e();
            if (h8 == h5) {
                return false;
            }
            if (!g && h8.s().isEmpty()) {
                x01.a("subgraphEntryBlock did not dominate subgraphExitBlock");
                return false;
            }
            sm0.b((Iterable) h8.s());
        }
        return true;
    }
}
