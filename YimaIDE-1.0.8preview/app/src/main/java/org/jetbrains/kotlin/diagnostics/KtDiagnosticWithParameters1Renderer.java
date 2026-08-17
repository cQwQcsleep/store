package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\t\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0002\u0010\u000eR\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters1Renderer;", "A", "Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticWithParametersRenderer;", "message", Argument.Delimiters.none, "rendererForA", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;)V", "renderParameters", Argument.Delimiters.none, Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;)[Ljava/lang/Object;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticWithParameters1Renderer<A> extends AbstractKtDiagnosticWithParametersRenderer {
    private final DiagnosticParameterRenderer<A> rendererForA;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public KtDiagnosticWithParameters1Renderer(String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer) {
        super(str, null);
        str.getClass();
        this.rendererForA = diagnosticParameterRenderer;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderer
    public Object[] renderParameters(KtDiagnostic diagnostic) {
        diagnostic.getClass();
        if (!(diagnostic instanceof KtDiagnosticWithParameters1)) {
            w01.a("Failed requirement.");
            return null;
        }
        RenderingContext.Companion companion = RenderingContext.INSTANCE;
        DiagnosticBaseContext context = diagnostic.getContext();
        KtDiagnosticWithParameters1 ktDiagnosticWithParameters1 = (KtDiagnosticWithParameters1) diagnostic;
        return new Object[]{DiagnosticParameterRendererKt.renderParameter(ktDiagnosticWithParameters1.getA(), this.rendererForA, companion.of(context, ktDiagnosticWithParameters1.getA()))};
    }
}
