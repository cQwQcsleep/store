package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.MissingDefinitionInfo;
import com.android.tools.r8.internal.AbstractC0551Hu;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class j implements MissingDefinitionInfo {
    public final Collection a;

    public j(AbstractC0551Hu abstractC0551Hu) {
        this.a = abstractC0551Hu;
    }

    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    public final String getDiagnosticMessage() {
        StringBuilder sb = new StringBuilder();
        k.a(sb, this);
        return sb.toString();
    }

    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    public final Collection getReferencedFromContexts() {
        return this.a;
    }
}
