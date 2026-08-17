package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters1;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0012\u0012\u000e\u0012\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ'\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000b2\u0010\u0010\r\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0003H\u0016¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticWithParametersMultiRenderer;", "A", "Lorg/jetbrains/kotlin/diagnostics/rendering/AbstractDiagnosticWithParametersRenderer;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters1;", "message", Argument.Delimiters.none, "renderer", "Lorg/jetbrains/kotlin/diagnostics/rendering/MultiRenderer;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/rendering/MultiRenderer;)V", "renderParameters", Argument.Delimiters.none, Argument.Delimiters.none, "diagnostic", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters1;)[Ljava/lang/Object;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticWithParametersMultiRenderer<A> extends AbstractDiagnosticWithParametersRenderer<DiagnosticWithParameters1<?, A>> {
    private final MultiRenderer<A> renderer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DiagnosticWithParametersMultiRenderer(String str, MultiRenderer<? super A> multiRenderer) {
        super(str);
        str.getClass();
        multiRenderer.getClass();
        this.renderer = multiRenderer;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.AbstractDiagnosticWithParametersRenderer, org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRenderer
    public Object[] renderParameters(DiagnosticWithParameters1<?, A> diagnostic) {
        diagnostic.getClass();
        return this.renderer.render(diagnostic.getA());
    }
}
