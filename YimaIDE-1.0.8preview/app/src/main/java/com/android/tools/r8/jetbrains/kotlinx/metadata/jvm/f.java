package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.AbstractC2654t40;
import com.android.tools.r8.internal.C1734iI;
import com.android.tools.r8.internal.InterfaceC2160nI;
import com.android.tools.r8.internal.KB;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class f implements InterfaceC2160nI {
    public static final C1734iI b = new C1734iI(AbstractC2654t40.a(f.class));
    public final f a;

    public f(int i) {
        this.a = null;
    }

    public void a(String str) {
        KB.c(str, "internalName");
        f fVar = this.a;
        if (fVar != null) {
            fVar.a(str);
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1818jI
    public final C1734iI getType() {
        return b;
    }

    public f() {
        this(0);
    }

    public void a(k kVar) {
        f fVar = this.a;
        if (fVar != null) {
            fVar.a(kVar);
        }
    }

    public final void a() {
        f fVar = this.a;
        if (fVar != null) {
            fVar.a();
        }
    }
}
