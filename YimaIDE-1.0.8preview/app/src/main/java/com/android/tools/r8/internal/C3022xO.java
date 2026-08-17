package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3022xO extends ArrayList {
    public final /* synthetic */ C3106yO b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3022xO(C3106yO c3106yO) {
        super(0);
        this.b = c3106yO;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        this.b.l = obj;
        return super.add(obj);
    }
}
