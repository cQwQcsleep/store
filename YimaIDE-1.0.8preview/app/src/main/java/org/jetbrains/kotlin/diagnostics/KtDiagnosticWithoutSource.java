package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithoutSource;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "message", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)V", "getMessage", "()Ljava/lang/String;", "getLocation", "()Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "getContext", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "isValid", Argument.Delimiters.none, "()Z", "firstRange", "Lcom/intellij/openapi/util/TextRange;", "getFirstRange", "()Lcom/intellij/openapi/util/TextRange;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticWithoutSource extends KtDiagnostic {
    private final DiagnosticBaseContext context;
    private final KtSourcelessDiagnosticFactory factory;
    private final CompilerMessageSourceLocation location;
    private final String message;
    private final Severity severity;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtDiagnosticWithoutSource(String str, CompilerMessageSourceLocation compilerMessageSourceLocation, Severity severity, KtSourcelessDiagnosticFactory ktSourcelessDiagnosticFactory, DiagnosticBaseContext diagnosticBaseContext) {
        super(null);
        str.getClass();
        severity.getClass();
        ktSourcelessDiagnosticFactory.getClass();
        diagnosticBaseContext.getClass();
        this.message = str;
        this.location = compilerMessageSourceLocation;
        this.severity = severity;
        this.factory = ktSourcelessDiagnosticFactory;
        this.context = diagnosticBaseContext;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public DiagnosticBaseContext getContext() {
        return this.context;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public TextRange getFirstRange() {
        TextRange textRange = TextRange.EMPTY_RANGE;
        textRange.getClass();
        return textRange;
    }

    public final CompilerMessageSourceLocation getLocation() {
        return this.location;
    }

    public final String getMessage() {
        return this.message;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic, org.jetbrains.kotlin.diagnostics.DiagnosticMarker, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public Severity getSeverity() {
        return this.severity;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public boolean isValid() {
        return true;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public KtSourcelessDiagnosticFactory getFactory() {
        return this.factory;
    }
}
