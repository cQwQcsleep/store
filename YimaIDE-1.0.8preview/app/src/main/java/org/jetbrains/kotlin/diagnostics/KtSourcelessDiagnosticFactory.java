package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticFactory;", ModuleXmlParser.NAME, Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "rendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;)V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithoutSource;", "message", "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtSourcelessDiagnosticFactory extends AbstractKtDiagnosticFactory {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtSourcelessDiagnosticFactory(String str, Severity severity, BaseDiagnosticRendererFactory baseDiagnosticRendererFactory) {
        super(str, severity, baseDiagnosticRendererFactory, null);
        str.getClass();
        severity.getClass();
        baseDiagnosticRendererFactory.getClass();
    }

    public final KtDiagnosticWithoutSource create(String message, CompilerMessageSourceLocation location, DiagnosticBaseContext context) {
        message.getClass();
        context.getClass();
        Severity effectiveSeverity = getEffectiveSeverity(context.getLanguageVersionSettings());
        if (effectiveSeverity == null) {
            return null;
        }
        return new KtDiagnosticWithoutSource(message, location, effectiveSeverity, this, context);
    }
}
