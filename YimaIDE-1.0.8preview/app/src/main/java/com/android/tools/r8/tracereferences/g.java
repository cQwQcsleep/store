package com.android.tools.r8.tracereferences;

import com.android.tools.r8.ClassFileResourceProvider;
import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.Cc0;
import com.android.tools.r8.origin.PathOrigin;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class g implements ClassFileResourceProvider {
    public final String a;
    public final ProgramResource b;

    public g(PathOrigin pathOrigin, byte[] bArr) {
        String strA = TraceReferencesCommand.Builder.a(bArr);
        this.a = strA;
        ProgramResource.Kind kind = ProgramResource.Kind.CF;
        int i = AbstractC2554rv.c;
        this.b = ProgramResource.fromBytes(pathOrigin, kind, bArr, new Cc0(strA));
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public final Set getClassDescriptors() {
        String str = this.a;
        int i = AbstractC2554rv.c;
        return new Cc0(str);
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public final ProgramResource getProgramResource(String str) {
        if (str.equals(this.a)) {
            return this.b;
        }
        return null;
    }
}
