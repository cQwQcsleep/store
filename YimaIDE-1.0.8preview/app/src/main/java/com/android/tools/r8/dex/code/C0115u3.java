package com.android.tools.r8.dex.code;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0290r5;
import com.android.tools.r8.graph.Z5;
import com.android.tools.r8.internal.C0602Jt;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.u3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0115u3 extends AbstractC0130x3 implements InterfaceC0027d {
    public C0115u3(int i, A1 a1, C0290r5 c0290r5) {
        super(i, a1, c0290r5.a());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.a(this.f, super.getField());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final boolean i() {
        return true;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "SgetByte";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 100;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "sget-byte";
    }

    public C0115u3(int i, C0245l1 c0245l1) {
        super(i, c0245l1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(Z5 z5) {
        z5.a(this);
    }
}
