package com.android.tools.r8.internal;

import com.android.tools.r8.profile.art.ArtProfileConsumer;
import com.android.tools.r8.profile.art.ArtProfileProvider;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1969l4 {
    public final ArtProfileProvider a;
    public final ArtProfileConsumer b;

    public C1969l4(ArtProfileProvider artProfileProvider, ArtProfileConsumer artProfileConsumer) {
        this.a = artProfileProvider;
        this.b = artProfileConsumer;
    }

    public ArtProfileProvider a() {
        return this.a;
    }

    public ArtProfileConsumer b() {
        return this.b;
    }
}
