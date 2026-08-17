package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1352dq extends C2543rl0 {
    public final PW r;

    public C1352dq(PW pw) {
        super(pw.s(), pw.t(), pw.r());
        this.r = pw;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean J() {
        return false;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean T() {
        return true;
    }

    public final PW c0() {
        return this.r;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final String toString() {
        return "fixed:v" + this.r.s();
    }
}
