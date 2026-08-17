package com.android.tools.r8.errors;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class a {
    public final ArrayList a = new ArrayList();
    public Origin b;
    public Position c;

    public final AssumeNoSideEffectsRuleForObjectMembersDiagnostic a() {
        return new AssumeNoSideEffectsRuleForObjectMembersDiagnostic(this.a, this.b, this.c);
    }
}
