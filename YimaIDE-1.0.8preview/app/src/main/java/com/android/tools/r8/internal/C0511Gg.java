package com.android.tools.r8.internal;

import defpackage.h3c;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0511Gg implements AU {
    public final /* synthetic */ Class a;

    public C0511Gg(Class cls) {
        this.a = cls;
    }

    @Override // com.android.tools.r8.internal.AU
    public final Object a() {
        try {
            return Wk0.a.a(this.a);
        } catch (Exception e) {
            h3c.a("Unable to create instance of ", this.a, ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
            return null;
        }
    }
}
