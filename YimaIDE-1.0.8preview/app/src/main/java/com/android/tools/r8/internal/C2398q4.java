package com.android.tools.r8.internal;

import com.android.tools.r8.profile.art.ArtProfileClassRuleBuilder;
import com.android.tools.r8.references.ClassReference;

/* JADX INFO: renamed from: com.android.tools.r8.internal.q4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2398q4 implements ArtProfileClassRuleBuilder {
    public final /* synthetic */ C2568s4 a;

    public C2398q4(C2568s4 c2568s4) {
        this.a = c2568s4;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileClassRuleBuilder
    public final ArtProfileClassRuleBuilder setClassReference(ClassReference classReference) {
        AbstractC2653t4.a(this.a.a, C1758id.a(classReference));
        return this;
    }
}
