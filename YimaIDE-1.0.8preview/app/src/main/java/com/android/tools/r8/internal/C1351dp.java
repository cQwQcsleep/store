package com.android.tools.r8.internal;

import com.android.tools.r8.references.ClassReference;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1351dp extends AbstractC1523fp {
    public final ClassReference a;
    public final String b;

    public C1351dp(ClassReference classReference, String str) {
        this.a = classReference;
        this.b = str;
    }

    @Override // com.android.tools.r8.internal.AbstractC1523fp
    public final AbstractC1523fp a(ClassReference classReference) {
        return new C1351dp(classReference, this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C1351dp.class == obj.getClass()) {
            C1351dp c1351dp = (C1351dp) obj;
            if (this.a.equals(c1351dp.a) && this.b.equals(c1351dp.b)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1767ii
    public final ClassReference getHolderClass() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1767ii
    public final String getName() {
        return this.b;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }
}
