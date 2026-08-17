package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ae0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1080ae0 extends AbstractC1333de0 implements InterfaceC2049m1 {
    public final C0322w2 b;

    public C1080ae0(C0322w2 c0322w2) {
        this.b = c0322w2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1333de0
    public final void a(Appendable appendable) throws IOException {
        appendable.append(this.b.l0());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.b == ((C1080ae0) obj).b;
    }

    @Override // com.android.tools.r8.internal.AbstractC1333de0
    /* JADX INFO: renamed from: getReference, reason: merged with bridge method [inline-methods] */
    public C0322w2 c() {
        return this.b;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC1333de0
    public final void a(InterfaceC1936kh0 interfaceC1936kh0, InterfaceC1936kh0 interfaceC1936kh1) throws Throwable {
        interfaceC1936kh1.accept(this);
    }
}
