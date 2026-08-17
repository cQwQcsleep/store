package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2882vk extends AbstractC2540rk {
    public static final /* synthetic */ boolean c = true;
    public final ArrayList a = new ArrayList();
    public InterfaceC2625sk b = null;

    public final C2882vk a(InterfaceC2625sk interfaceC2625sk) {
        boolean z = c;
        if (!z && this.b != null) {
            x1f.a();
            return null;
        }
        if (z || interfaceC2625sk != null) {
            this.b = interfaceC2625sk;
            return this;
        }
        x1f.a();
        return null;
    }

    public final AbstractC2540rk a(InterfaceC2968wk interfaceC2968wk) {
        if (c || interfaceC2968wk != null) {
            this.a.add(interfaceC2968wk);
            return this;
        }
        x1f.a();
        return null;
    }

    public final C3053xk a() {
        return new C2796uk(this);
    }
}
