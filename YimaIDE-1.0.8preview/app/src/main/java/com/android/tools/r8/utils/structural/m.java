package com.android.tools.r8.utils.structural;

import com.android.tools.r8.internal.E;
import com.android.tools.r8.internal.InterfaceC0990Ys;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class m implements n {
    public final InterfaceC0990Ys a;

    public m(InterfaceC0990Ys interfaceC0990Ys) {
        this.a = interfaceC0990Ys;
    }

    @Override // com.android.tools.r8.utils.structural.n
    public final void a(float f) {
        E e = (E) this.a;
        e.getClass();
        e.a(Float.floatToRawIntBits(f));
    }

    @Override // com.android.tools.r8.utils.structural.n
    public final void a(int i) {
        this.a.a(i);
    }

    @Override // com.android.tools.r8.utils.structural.n
    public final void a(boolean z) {
        ((E) this.a).a(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.android.tools.r8.utils.structural.n
    public final void a(long j) {
        this.a.a(j);
    }

    @Override // com.android.tools.r8.utils.structural.n
    public final void a(double d) {
        E e = (E) this.a;
        e.getClass();
        e.a(Double.doubleToRawLongBits(d));
    }

    @Override // com.android.tools.r8.utils.structural.n
    public final void a(byte[] bArr) {
        this.a.a(bArr);
    }

    @Override // com.android.tools.r8.utils.structural.n
    public final String a() {
        return this.a.a().toString();
    }
}
