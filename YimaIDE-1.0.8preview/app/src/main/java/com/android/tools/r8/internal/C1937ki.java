package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ki, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1937ki implements Ua0 {
    public final CharSequence a;
    public final int b;
    public final int c;
    public final InterfaceC2635sr d;

    public C1937ki(CharSequence charSequence, int i, int i2, InterfaceC2635sr interfaceC2635sr) {
        KB.c(charSequence, "input");
        this.a = charSequence;
        this.b = i;
        this.c = i2;
        this.d = interfaceC2635sr;
    }

    @Override // com.android.tools.r8.internal.Ua0
    public final Iterator iterator() {
        return new C1852ji(this);
    }
}
