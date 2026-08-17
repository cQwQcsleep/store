package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Pd0 extends C2543rl0 {
    public static final /* synthetic */ boolean s = true;
    public final Od0[] r;

    public Pd0(Od0... od0Arr) {
        super(-1, AbstractC2624sj0.f(), null);
        this.r = od0Arr;
        if (s || od0Arr.length >= 2) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean P() {
        return true;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean T() {
        return false;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final void a(boolean z) {
        if (s || !z) {
            return;
        }
        x1f.a();
    }

    public final Od0[] c0() {
        return this.r;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final AbstractC2624sj0 t() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Od0 od0 : this.r) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(od0);
        }
        sb.append(']');
        return sb.toString();
    }
}
