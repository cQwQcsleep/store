package com.android.tools.r8.internal;

import java.io.IOException;
import java.io.OutputStreamWriter;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1371e4 extends AbstractC2824v4 implements InterfaceC1877k1 {
    public final com.android.tools.r8.graph.I2 b;

    public C1371e4(com.android.tools.r8.graph.I2 i2) {
        this.b = i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2824v4
    public final void a(OutputStreamWriter outputStreamWriter) throws IOException {
        outputStreamWriter.write(this.b.Z0());
    }

    @Override // com.android.tools.r8.internal.AbstractC2824v4
    public final com.android.tools.r8.graph.F2 c() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.b == ((C1371e4) obj).b;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return this.b.Z0();
    }

    @Override // com.android.tools.r8.internal.AbstractC2824v4
    public final Object a(InterfaceC2022lh0 interfaceC2022lh0, InterfaceC2022lh0 interfaceC2022lh1) {
        return interfaceC2022lh0.apply(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2824v4
    public final void a(InterfaceC1936kh0 interfaceC1936kh0, InterfaceC1936kh0 interfaceC1936kh1) throws Throwable {
        interfaceC1936kh0.accept(this);
    }
}
