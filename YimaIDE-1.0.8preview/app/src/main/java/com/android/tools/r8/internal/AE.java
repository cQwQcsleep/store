package com.android.tools.r8.internal;

import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AE {
    public C1476fH a = C1476fH.a();
    public int b = 0;

    public final BE a() {
        if (this.b == 0) {
            defpackage.l0.a("Invalid empty retention policy");
            return null;
        }
        if (!this.a.d()) {
            return new BE(this.a, this.b);
        }
        int i = this.b;
        if (i == 1) {
            return BE.d;
        }
        if (i == 2) {
            return BE.e;
        }
        if (i == 3) {
            return BE.c;
        }
        throw new C2499rF("Invalid retention policy value: " + i);
    }

    public final AE a(RetentionPolicy retentionPolicy) {
        int i = AbstractC3181zE.a[retentionPolicy.ordinal()];
        if (i == 1) {
            this.b |= 1;
            return this;
        }
        if (i == 2) {
            this.b |= 2;
            return this;
        }
        if (i == 3) {
            defpackage.l0.a("Retention policy SOURCE cannot be used in patterns");
            return null;
        }
        throw new C2499rF("Invalid policy: " + retentionPolicy);
    }
}
