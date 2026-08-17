package org.jetbrains.kotlin.diagnostics;

import java.text.MessageFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0005\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticWithParametersRenderer;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticRenderer;", "message", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "messageFormat", "Ljava/text/MessageFormat;", "render", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters1Renderer;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters2Renderer;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters3Renderer;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters4Renderer;", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticRenderer;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractKtDiagnosticWithParametersRenderer extends KtDiagnosticRenderer {
    private final String message;
    private final MessageFormat messageFormat;

    private AbstractKtDiagnosticWithParametersRenderer(String str) {
        super(null);
        this.message = str;
        this.messageFormat = new MessageFormat(str);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderer
    public final String getMessage() {
        return this.message;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderer
    public final String render(KtDiagnostic diagnostic) {
        diagnostic.getClass();
        String str = this.messageFormat.format(renderParameters(diagnostic));
        str.getClass();
        return str;
    }

    public /* synthetic */ AbstractKtDiagnosticWithParametersRenderer(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
