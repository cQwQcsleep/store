package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u0016\u001a\u00020\u000fH\u0096\u0080\u0004R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/RenderedDiagnostic;", "D", "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", Argument.Delimiters.none, "diagnostic", "renderer", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticRenderer;", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/Diagnostic;Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticRenderer;)V", "getDiagnostic", "()Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "getRenderer", "()Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticRenderer;", "text", Argument.Delimiters.none, "getText", "()Ljava/lang/String;", "factory", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory;", "toString", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RenderedDiagnostic<D extends Diagnostic> {
    private final D diagnostic;
    private final DiagnosticRenderer<D> renderer;
    private final String text;

    /* JADX WARN: Multi-variable type inference failed */
    public RenderedDiagnostic(D d, DiagnosticRenderer<? super D> diagnosticRenderer) {
        d.getClass();
        diagnosticRenderer.getClass();
        this.diagnostic = d;
        this.renderer = diagnosticRenderer;
        this.text = diagnosticRenderer.render(d);
    }

    public final D getDiagnostic() {
        return this.diagnostic;
    }

    public final DiagnosticFactory<?> getFactory() {
        return this.diagnostic.getFactory();
    }

    public final DiagnosticRenderer<D> getRenderer() {
        return this.renderer;
    }

    public final String getText() {
        return this.text;
    }

    public String toString() {
        return this.text;
    }
}
