package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.internal.C0416Cp;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.shaking.C3412j3;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class InlinableStaticFinalFieldPreconditionDiagnostic implements Diagnostic {
    private final C3412j3 b;
    private final List c;

    public InlinableStaticFinalFieldPreconditionDiagnostic(C3412j3 c3412j3, List<C0245l1> list) {
        this.b = c3412j3;
        this.c = C2847vL.a((Collection) list, new Function() { // from class: mp6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0245l1) obj).z0();
            }
        });
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return Wf0.b("Rule precondition matches static final fields javac has inlined.", "Such rules are unsound as the shrinker cannot infer the inlining precisely.", "Consider adding !static to the rule.", "Matched fields are: ") + Wf0.a((Collection) C2847vL.a((Collection) this.c, new Function() { // from class: lp6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0416Cp.a((FieldReference) obj);
            }
        }));
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.b.i();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.b.b;
    }
}
