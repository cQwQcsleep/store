package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u0002*\b\b\u0003\u0010\u0005*\u00020\u00022$\u0012 \u0012\u001e\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00070\u0006BO\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000b\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u000b\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u000b¢\u0006\u0004\b\u000f\u0010\u0010J;\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u00122\"\u0010\u0013\u001a\u001e\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0007H\u0016¢\u0006\u0002\u0010\u0014R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticWithParameters4Renderer;", "A", Argument.Delimiters.none, "B", "C", "D", "Lorg/jetbrains/kotlin/diagnostics/rendering/AbstractDiagnosticWithParametersRenderer;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters4;", "message", Argument.Delimiters.none, "rendererForA", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "rendererForB", "rendererForC", "rendererForD", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;)V", "renderParameters", Argument.Delimiters.none, "diagnostic", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters4;)[Ljava/lang/Object;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticWithParameters4Renderer<A, B, C, D> extends AbstractDiagnosticWithParametersRenderer<DiagnosticWithParameters4<?, A, B, C, D>> {
    private final DiagnosticParameterRenderer<A> rendererForA;
    private final DiagnosticParameterRenderer<B> rendererForB;
    private final DiagnosticParameterRenderer<C> rendererForC;
    private final DiagnosticParameterRenderer<D> rendererForD;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DiagnosticWithParameters4Renderer(String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer, DiagnosticParameterRenderer<? super B> diagnosticParameterRenderer2, DiagnosticParameterRenderer<? super C> diagnosticParameterRenderer3, DiagnosticParameterRenderer<? super D> diagnosticParameterRenderer4) {
        super(str);
        str.getClass();
        this.rendererForA = diagnosticParameterRenderer;
        this.rendererForB = diagnosticParameterRenderer2;
        this.rendererForC = diagnosticParameterRenderer3;
        this.rendererForD = diagnosticParameterRenderer4;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.AbstractDiagnosticWithParametersRenderer, org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRenderer
    public Object[] renderParameters(DiagnosticWithParameters4<?, A, B, C, D> diagnostic) {
        diagnostic.getClass();
        RenderingContext renderingContextOf = RenderingContext.INSTANCE.of(diagnostic.getA(), diagnostic.getB(), diagnostic.getC(), diagnostic.getD());
        return new Object[]{DiagnosticParameterRendererKt.renderParameter(diagnostic.getA(), this.rendererForA, renderingContextOf), DiagnosticParameterRendererKt.renderParameter(diagnostic.getB(), this.rendererForB, renderingContextOf), DiagnosticParameterRendererKt.renderParameter(diagnostic.getC(), this.rendererForC, renderingContextOf), DiagnosticParameterRendererKt.renderParameter(diagnostic.getD(), this.rendererForD, renderingContextOf)};
    }
}
