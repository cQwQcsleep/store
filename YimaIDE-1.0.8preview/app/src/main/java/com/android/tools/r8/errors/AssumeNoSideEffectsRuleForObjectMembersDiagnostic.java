package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.internal.MO;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.MethodReference;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AssumeNoSideEffectsRuleForObjectMembersDiagnostic implements Diagnostic {
    private final List b;
    private final Origin c;
    private final Position d;

    private AssumeNoSideEffectsRuleForObjectMembersDiagnostic(List list, Origin origin, Position position) {
        this.b = list;
        this.c = origin;
        this.d = position;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        Iterator it = this.b.iterator();
        StringBuilder sb = new StringBuilder("The -assumenosideeffects rule matches the following method(s) on java.lang.Object: ");
        sb.append(MO.a((MethodReference) it.next(), false, false));
        while (it.hasNext()) {
            MethodReference methodReference = (MethodReference) it.next();
            sb.append(it.hasNext() ? ", " : " and ");
            sb.append(MO.a(methodReference, false, false));
        }
        sb.append(". This is most likely not intended. Consider specifying the methods more precisely.");
        return sb.toString();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.c;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.d;
    }
}
