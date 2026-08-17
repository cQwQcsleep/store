package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.AbstractC2654t40;
import com.android.tools.r8.internal.C1734iI;
import com.android.tools.r8.internal.InterfaceC2930wI;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class m implements InterfaceC2930wI {
    public static final C1734iI b = new C1734iI(AbstractC2654t40.a(m.class));
    public final m a;

    public m(int i) {
        this.a = null;
    }

    public void a(int i, e eVar, k kVar, k kVar2) {
        m mVar = this.a;
        if (mVar != null) {
            mVar.a(i, eVar, kVar, kVar2);
        }
        a();
    }

    public void b(k kVar) {
        m mVar = this.a;
        if (mVar != null) {
            mVar.b(kVar);
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1818jI
    public final C1734iI getType() {
        return b;
    }

    public m() {
        this(0);
    }

    public final void b() {
        m mVar = this.a;
        if (mVar != null) {
            mVar.b();
        }
    }

    public final void a() {
        m mVar = this.a;
        if (mVar != null) {
            mVar.a();
        }
    }

    public void a(k kVar) {
        m mVar = this.a;
        if (mVar != null) {
            mVar.a(kVar);
        }
    }
}
