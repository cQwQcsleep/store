package org.jetbrains.kotlin.diagnostics.rendering;

import defpackage.f2f;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters1;
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters2;
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters3;
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4;
import org.jetbrains.kotlin.diagnostics.ParametrizedDiagnostic;
import org.jetbrains.kotlin.diagnostics.SimpleDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\b"}, d2 = {"parameters", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Companion;", "d", "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "fromDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RenderingContextUtilsKt {
    public static final RenderingContext fromDiagnostic(RenderingContext.Companion companion, Diagnostic diagnostic) {
        companion.getClass();
        diagnostic.getClass();
        return new RenderingContext.Impl(parameters(companion, diagnostic));
    }

    public static final List<Object> parameters(RenderingContext.Companion companion, Diagnostic diagnostic) {
        companion.getClass();
        diagnostic.getClass();
        if (diagnostic instanceof SimpleDiagnostic) {
            return CollectionsKt.emptyList();
        }
        if (diagnostic instanceof DiagnosticWithParameters1) {
            return CollectionsKt.listOf(((DiagnosticWithParameters1) diagnostic).getA());
        }
        if (diagnostic instanceof DiagnosticWithParameters2) {
            DiagnosticWithParameters2 diagnosticWithParameters2 = (DiagnosticWithParameters2) diagnostic;
            Object a = diagnosticWithParameters2.getA();
            a.getClass();
            Object b = diagnosticWithParameters2.getB();
            b.getClass();
            return CollectionsKt.listOf(new Object[]{a, b});
        }
        if (diagnostic instanceof DiagnosticWithParameters3) {
            DiagnosticWithParameters3 diagnosticWithParameters3 = (DiagnosticWithParameters3) diagnostic;
            Object a2 = diagnosticWithParameters3.getA();
            a2.getClass();
            Object b2 = diagnosticWithParameters3.getB();
            b2.getClass();
            Object c = diagnosticWithParameters3.getC();
            c.getClass();
            return CollectionsKt.listOf(new Object[]{a2, b2, c});
        }
        if (!(diagnostic instanceof DiagnosticWithParameters4)) {
            if (!(diagnostic instanceof ParametrizedDiagnostic)) {
                return CollectionsKt.emptyList();
            }
            f2f.a("Unexpected diagnostic: ", diagnostic.getClass());
            return null;
        }
        DiagnosticWithParameters4 diagnosticWithParameters4 = (DiagnosticWithParameters4) diagnostic;
        Object a3 = diagnosticWithParameters4.getA();
        a3.getClass();
        Object b3 = diagnosticWithParameters4.getB();
        b3.getClass();
        Object c2 = diagnosticWithParameters4.getC();
        c2.getClass();
        Object d = diagnosticWithParameters4.getD();
        d.getClass();
        return CollectionsKt.listOf(new Object[]{a3, b3, c2, d});
    }
}
