package com.android.tools.r8.internal;

import com.android.tools.r8.profile.art.ArtProfileClassRuleBuilder;
import com.android.tools.r8.references.ClassReference;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1033a4 implements ArtProfileClassRuleBuilder {
    public ClassReference a;

    @Override // com.android.tools.r8.profile.art.ArtProfileClassRuleBuilder
    public final ArtProfileClassRuleBuilder setClassReference(ClassReference classReference) {
        this.a = classReference;
        return this;
    }
}
