package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2872vf extends Q0 {
    public final /* synthetic */ C2958wf c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2872vf(C2958wf c2958wf) {
        super(c2958wf);
        this.c = c2958wf;
    }

    @Override // com.android.tools.r8.internal.Q0
    public final InterfaceC1231cQ a() {
        return this.c;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final Object[] toArray() {
        ArrayList arrayList = new ArrayList(AL.a(this.b.c()));
        NC.a(arrayList, this.b.e());
        return arrayList.toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        ArrayList arrayList = new ArrayList(AL.a(this.b.c()));
        NC.a(arrayList, this.b.e());
        return arrayList.toArray(objArr);
    }
}
