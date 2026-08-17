package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BD extends com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.f implements InterfaceC2075mI {
    public com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k c;
    public String d;

    public BD() {
        super(0);
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.f
    public final void a(String str) {
        KB.c(str, "internalName");
        this.d = str;
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.f
    public final void a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar) {
        this.c = kVar;
    }
}
