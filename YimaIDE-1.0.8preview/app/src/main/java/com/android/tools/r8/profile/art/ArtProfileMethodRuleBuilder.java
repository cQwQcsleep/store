package com.android.tools.r8.profile.art;

import com.android.tools.r8.references.MethodReference;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ArtProfileMethodRuleBuilder {
    ArtProfileMethodRuleBuilder setMethodReference(MethodReference methodReference);

    ArtProfileMethodRuleBuilder setMethodRuleInfo(Consumer<ArtProfileMethodRuleInfoBuilder> consumer);
}
