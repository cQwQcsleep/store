package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.MissingClassInfo;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.references.ClassReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class h extends j implements MissingClassInfo {
    public final ClassReference b;

    public h(ClassReference classReference, AbstractC0551Hu abstractC0551Hu) {
        super(abstractC0551Hu);
        this.b = classReference;
    }

    @Override // com.android.tools.r8.diagnostic.MissingClassInfo
    public final ClassReference getClassReference() {
        return this.b;
    }
}
