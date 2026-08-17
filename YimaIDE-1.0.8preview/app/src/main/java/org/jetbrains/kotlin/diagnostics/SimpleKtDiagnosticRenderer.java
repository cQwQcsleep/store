package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u001f\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/SimpleKtDiagnosticRenderer;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticRenderer;", "message", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "render", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "renderParameters", Argument.Delimiters.none, Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;)[Ljava/lang/Object;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SimpleKtDiagnosticRenderer extends KtDiagnosticRenderer {
    private final String message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleKtDiagnosticRenderer(String str) {
        super(null);
        str.getClass();
        this.message = str;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderer
    public String getMessage() {
        return this.message;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderer
    public String render(KtDiagnostic diagnostic) {
        diagnostic.getClass();
        if (diagnostic instanceof KtSimpleDiagnostic) {
            return getMessage();
        }
        w01.a("Failed requirement.");
        return null;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderer
    public Object[] renderParameters(KtDiagnostic diagnostic) {
        diagnostic.getClass();
        if (diagnostic instanceof KtSimpleDiagnostic) {
            return new Object[0];
        }
        w01.a("Failed requirement.");
        return null;
    }
}
