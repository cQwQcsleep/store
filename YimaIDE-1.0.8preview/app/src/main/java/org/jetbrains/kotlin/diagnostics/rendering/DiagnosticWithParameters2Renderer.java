package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters2;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00040\u0003B/\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ/\u0010\f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000e0\r2\u0016\u0010\u000f\u001a\u0012\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016¢\u0006\u0002\u0010\u0010R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticWithParameters2Renderer;", "A", "B", "Lorg/jetbrains/kotlin/diagnostics/rendering/AbstractDiagnosticWithParametersRenderer;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters2;", "message", Argument.Delimiters.none, "rendererForA", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "rendererForB", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;)V", "renderParameters", Argument.Delimiters.none, Argument.Delimiters.none, "diagnostic", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters2;)[Ljava/lang/Object;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticWithParameters2Renderer<A, B> extends AbstractDiagnosticWithParametersRenderer<DiagnosticWithParameters2<?, A, B>> {
    private final DiagnosticParameterRenderer<A> rendererForA;
    private final DiagnosticParameterRenderer<B> rendererForB;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DiagnosticWithParameters2Renderer(String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer, DiagnosticParameterRenderer<? super B> diagnosticParameterRenderer2) {
        super(str);
        str.getClass();
        this.rendererForA = diagnosticParameterRenderer;
        this.rendererForB = diagnosticParameterRenderer2;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.AbstractDiagnosticWithParametersRenderer, org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRenderer
    public Object[] renderParameters(DiagnosticWithParameters2<?, A, B> diagnostic) {
        diagnostic.getClass();
        RenderingContext renderingContextOf = RenderingContext.INSTANCE.of(diagnostic.getA(), diagnostic.getB());
        return new Object[]{DiagnosticParameterRendererKt.renderParameter(diagnostic.getA(), this.rendererForA, renderingContextOf), DiagnosticParameterRendererKt.renderParameter(diagnostic.getB(), this.rendererForB, renderingContextOf)};
    }
}
