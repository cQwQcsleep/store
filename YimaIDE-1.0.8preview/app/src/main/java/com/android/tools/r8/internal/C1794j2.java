package com.android.tools.r8.internal;

import com.android.tools.r8.profile.art.ArtProfileClassRuleInfo;
import com.android.tools.r8.profile.art.ArtProfileMethodRuleInfo;
import com.android.tools.r8.profile.art.ArtProfileRulePredicate;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: renamed from: com.android.tools.r8.internal.j2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1794j2 implements ArtProfileRulePredicate {
    @Override // com.android.tools.r8.profile.art.ArtProfileRulePredicate
    public final boolean testClassRule(ClassReference classReference, ArtProfileClassRuleInfo artProfileClassRuleInfo) {
        return true;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileRulePredicate
    public final boolean testMethodRule(MethodReference methodReference, ArtProfileMethodRuleInfo artProfileMethodRuleInfo) {
        return true;
    }
}
