package com.android.tools.r8.internal;

import com.android.tools.r8.profile.art.ArtProfileClassRuleBuilder;
import com.android.tools.r8.references.ClassReference;

/* JADX INFO: renamed from: com.android.tools.r8.internal.d4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1287d4 extends AbstractC2739u4 implements ArtProfileClassRuleBuilder, InterfaceC1792j1 {
    public static final /* synthetic */ boolean c = true;
    public final com.android.tools.r8.graph.B1 a;
    public com.android.tools.r8.graph.I2 b;

    public C1287d4() {
        this.a = null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1792j1
    public final InterfaceC1877k1 build() {
        return new C1371e4(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC2739u4
    public final AbstractC2824v4 c() {
        return new C1371e4(this.b);
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileClassRuleBuilder
    public final ArtProfileClassRuleBuilder setClassReference(ClassReference classReference) {
        if (c || this.a != null) {
            this.b = this.a.e(classReference.getDescriptor());
            return this;
        }
        x1f.a();
        return null;
    }

    public C1287d4(com.android.tools.r8.graph.B1 b1) {
        this.a = b1;
    }
}
