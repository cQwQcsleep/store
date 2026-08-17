package org.jetbrains.kotlin.diagnostics.rendering;

import org.jetbrains.kotlin.diagnostics.Diagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class SimpleDiagnosticRenderer implements DiagnosticRenderer<Diagnostic> {
    private final String message;

    /* JADX WARN: Code duplicated, block: B:16:0x0022  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 2 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "diagnostic";
        } else if (i == 2) {
            objArr[0] = "org/jetbrains/kotlin/diagnostics/rendering/SimpleDiagnosticRenderer";
        } else if (i != 3) {
            objArr[0] = "message";
        } else {
            objArr[0] = "diagnostic";
        }
        if (i != 2) {
            objArr[1] = "org/jetbrains/kotlin/diagnostics/rendering/SimpleDiagnosticRenderer";
        } else {
            objArr[1] = "render";
        }
        if (i == 1) {
            objArr[2] = "render";
        } else if (i != 2) {
            if (i != 3) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "renderParameters";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 2) {
            throw new IllegalStateException(str2);
        }
    }

    public SimpleDiagnosticRenderer(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        this.message = str;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRenderer
    public String render(Diagnostic diagnostic) {
        if (diagnostic == null) {
            $$$reportNull$$$0(1);
        }
        String str = this.message;
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        return str;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRenderer
    public Object[] renderParameters(Diagnostic diagnostic) {
        if (diagnostic == null) {
            $$$reportNull$$$0(3);
        }
        return new Object[0];
    }
}
