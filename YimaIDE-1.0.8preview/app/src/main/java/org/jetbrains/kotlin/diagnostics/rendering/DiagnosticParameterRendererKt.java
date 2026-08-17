package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004\u001a,\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u00022\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b\u001a3\u0010\n\u001a\u0004\u0018\u00010\u000b\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u0002H\f2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u0002H\f\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\t¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Renderer", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "O", "block", "Lkotlin/Function1;", Argument.Delimiters.none, "ContextDependentRenderer", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "renderParameter", Argument.Delimiters.none, "P", "parameter", "renderer", "context", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;)Ljava/lang/Object;", "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticParameterRendererKt {
    public static final <O> DiagnosticParameterRenderer<O> ContextDependentRenderer(final Function2<? super O, ? super RenderingContext, String> function2) {
        function2.getClass();
        return new DiagnosticParameterRenderer<O>() { // from class: org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt.ContextDependentRenderer.1
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public String render(O obj, RenderingContext renderingContext) {
                renderingContext.getClass();
                return (String) function2.invoke(obj, renderingContext);
            }
        };
    }

    public static final <O> ContextIndependentParameterRenderer<O> Renderer(final Function1<? super O, String> function1) {
        function1.getClass();
        return new ContextIndependentParameterRenderer<O>() { // from class: org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt.Renderer.1
            @Override // org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer
            public String render(O obj) {
                return (String) function1.invoke(obj);
            }
        };
    }

    public static final <P> Object renderParameter(P p, DiagnosticParameterRenderer<? super P> diagnosticParameterRenderer, RenderingContext renderingContext) {
        String strRender;
        renderingContext.getClass();
        return (diagnosticParameterRenderer == null || (strRender = diagnosticParameterRenderer.render(p, renderingContext)) == null) ? p : strRender;
    }
}
