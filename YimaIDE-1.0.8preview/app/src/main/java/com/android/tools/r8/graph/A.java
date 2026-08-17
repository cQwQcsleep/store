package com.android.tools.r8.graph;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A extends B {
    public final B1 a;

    public A(B1 b1) {
        this.a = b1;
    }

    @Override // com.android.tools.r8.graph.B
    public final String a(String str) {
        return str.equals("Lcom/android/tools/r8/RecordTag;") ? "Ljava/lang/Record;" : str;
    }

    @Override // com.android.tools.r8.graph.B
    public final I2 b(I2 i2) {
        B1 b1 = this.a;
        return i2 == b1.c2 ? b1.b2 : i2;
    }

    @Override // com.android.tools.r8.graph.B
    public final I2 a(I2 i2) {
        B1 b1 = this.a;
        return i2 == b1.b2 ? b1.c2 : i2;
    }
}
