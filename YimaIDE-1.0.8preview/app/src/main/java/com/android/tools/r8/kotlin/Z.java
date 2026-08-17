package com.android.tools.r8.kotlin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z extends com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.m {
    public static final /* synthetic */ boolean d = true;
    public final /* synthetic */ a0 c;

    public Z(a0 a0Var) {
        this.c = a0Var;
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.m
    public final void a(int i, com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.e eVar, com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar, com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar2) {
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar3;
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar4;
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.e eVar2;
        boolean z = d;
        if (!z && (eVar2 = this.c.b.a) != null) {
            x01.a(eVar2.a());
            return;
        }
        b0 b0Var = this.c.b;
        b0Var.a = eVar;
        if (!z && (kVar4 = b0Var.b) != null) {
            x01.a(kVar4.a());
            return;
        }
        b0Var.b = kVar;
        if (z || (kVar3 = b0Var.c) == null) {
            b0Var.c = kVar2;
        } else {
            x01.a(kVar3.a());
        }
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.m
    public final void a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar) {
        if (d || this.c.b.d == null) {
            this.c.b.d = kVar;
        } else {
            x01.a(kVar.a());
        }
    }
}
