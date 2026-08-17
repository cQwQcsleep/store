package com.android.tools.r8.internal;

import com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder;
import com.android.tools.r8.references.MethodReference;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1119b4 implements ArtProfileMethodRuleBuilder {
    public MethodReference a;

    public C1119b4() {
        C2140n4.b();
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder
    public final ArtProfileMethodRuleBuilder setMethodReference(MethodReference methodReference) {
        this.a = methodReference;
        return this;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder
    public final ArtProfileMethodRuleBuilder setMethodRuleInfo(Consumer consumer) {
        C2140n4.a aVarA = C2140n4.a();
        consumer.accept(aVarA);
        aVarA.a();
        return this;
    }
}
