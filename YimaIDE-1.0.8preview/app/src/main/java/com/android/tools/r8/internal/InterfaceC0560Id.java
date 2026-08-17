package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2173nV;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Id, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0560Id {
    public static final C0534Hd a = new C0534Hd(false);
    public static final C0534Hd b = new C0534Hd(true);
    public static final InterfaceC0560Id c = new InterfaceC0560Id() { // from class: wi6
        @Override // com.android.tools.r8.internal.InterfaceC0560Id
        public final AbstractC2173nV a() {
            return AbstractC2173nV.g();
        }
    };

    static C0534Hd a(boolean z) {
        return z ? b : a;
    }

    AbstractC2173nV a();
}
