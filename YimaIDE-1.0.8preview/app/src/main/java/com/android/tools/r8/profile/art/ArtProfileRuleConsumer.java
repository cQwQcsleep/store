package com.android.tools.r8.profile.art;

import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ArtProfileRuleConsumer {
    void acceptClassRule(ClassReference classReference, ArtProfileClassRuleInfo artProfileClassRuleInfo);

    void acceptMethodRule(MethodReference methodReference, ArtProfileMethodRuleInfo artProfileMethodRuleInfo);
}
