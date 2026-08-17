package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Yd0 extends AbstractC1333de0 implements InterfaceC1877k1 {
    public final com.android.tools.r8.graph.I2 b;

    public Yd0(com.android.tools.r8.graph.I2 i2) {
        this.b = i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1333de0
    public final void a(Appendable appendable) throws IOException {
        appendable.append(this.b.Z0());
    }

    @Override // com.android.tools.r8.internal.AbstractC1333de0
    public final com.android.tools.r8.graph.F2 c() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.b == ((Yd0) obj).b;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC1333de0
    public final void a(InterfaceC1936kh0 interfaceC1936kh0, InterfaceC1936kh0 interfaceC1936kh1) throws Throwable {
        interfaceC1936kh0.accept(this);
    }
}
