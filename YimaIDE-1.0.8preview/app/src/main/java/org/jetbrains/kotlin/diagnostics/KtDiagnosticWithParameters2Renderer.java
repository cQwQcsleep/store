package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters2Renderer;", "A", "B", "Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticWithParametersRenderer;", "message", Argument.Delimiters.none, "rendererForA", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "rendererForB", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;)V", "renderParameters", Argument.Delimiters.none, Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;)[Ljava/lang/Object;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticWithParameters2Renderer<A, B> extends AbstractKtDiagnosticWithParametersRenderer {
    private final DiagnosticParameterRenderer<A> rendererForA;
    private final DiagnosticParameterRenderer<B> rendererForB;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public KtDiagnosticWithParameters2Renderer(String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer, DiagnosticParameterRenderer<? super B> diagnosticParameterRenderer2) {
        super(str, null);
        str.getClass();
        this.rendererForA = diagnosticParameterRenderer;
        this.rendererForB = diagnosticParameterRenderer2;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderer
    public Object[] renderParameters(KtDiagnostic diagnostic) {
        diagnostic.getClass();
        if (!(diagnostic instanceof KtDiagnosticWithParameters2)) {
            w01.a("Failed requirement.");
            return null;
        }
        RenderingContext.Companion companion = RenderingContext.INSTANCE;
        DiagnosticBaseContext context = diagnostic.getContext();
        KtDiagnosticWithParameters2 ktDiagnosticWithParameters2 = (KtDiagnosticWithParameters2) diagnostic;
        RenderingContext renderingContextOf = companion.of(context, ktDiagnosticWithParameters2.getA(), ktDiagnosticWithParameters2.getB());
        return new Object[]{DiagnosticParameterRendererKt.renderParameter(ktDiagnosticWithParameters2.getA(), this.rendererForA, renderingContextOf), DiagnosticParameterRendererKt.renderParameter(ktDiagnosticWithParameters2.getB(), this.rendererForB, renderingContextOf)};
    }
}
