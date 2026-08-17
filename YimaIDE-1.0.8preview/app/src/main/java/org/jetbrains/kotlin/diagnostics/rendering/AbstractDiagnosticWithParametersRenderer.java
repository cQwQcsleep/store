package org.jetbrains.kotlin.diagnostics.rendering;

import java.text.MessageFormat;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.UnboundDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000f0\u000e2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/AbstractDiagnosticWithParametersRenderer;", "D", "Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticRenderer;", "message", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "messageFormat", "Ljava/text/MessageFormat;", "render", "diagnostic", "(Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;)Ljava/lang/String;", "renderParameters", Argument.Delimiters.none, Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;)[Ljava/lang/Object;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractDiagnosticWithParametersRenderer<D extends UnboundDiagnostic> implements DiagnosticRenderer<D> {
    private final MessageFormat messageFormat;

    public AbstractDiagnosticWithParametersRenderer(String str) {
        str.getClass();
        this.messageFormat = new MessageFormat(str);
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRenderer
    public String render(D diagnostic) {
        diagnostic.getClass();
        String str = this.messageFormat.format(renderParameters(diagnostic));
        str.getClass();
        return str;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRenderer
    public Object[] renderParameters(D diagnostic) {
        diagnostic.getClass();
        return new Object[0];
    }
}
