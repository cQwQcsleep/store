package org.jetbrains.kotlin.cli.common.messages;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.kotlin.analyzer.CompilationErrorException;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0017J\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/CompilerLoggerAdapter;", "Lorg/jetbrains/kotlin/util/Logger;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "treatWarningsAsErrors", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;Z)V", K2JsArgumentConstants.RUNTIME_DIAGNOSTIC_LOG, Argument.Delimiters.none, "message", Argument.Delimiters.none, "warning", "strongWarning", "error", "fatal", Argument.Delimiters.none, "orError", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class CompilerLoggerAdapter implements Logger {
    private final MessageCollector messageCollector;
    private final boolean treatWarningsAsErrors;

    public CompilerLoggerAdapter(MessageCollector messageCollector, boolean z) {
        messageCollector.getClass();
        this.messageCollector = messageCollector;
        this.treatWarningsAsErrors = z;
    }

    private final CompilerMessageSeverity orError(CompilerMessageSeverity compilerMessageSeverity) {
        return this.treatWarningsAsErrors ? CompilerMessageSeverity.ERROR : compilerMessageSeverity;
    }

    public void error(String message) {
        message.getClass();
        this.messageCollector.report(CompilerMessageSeverity.ERROR, message, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.analyzer.CompilationErrorException */
    @Deprecated(message = "Invocation of fatal() may cause severe side effects such as throwing an exception or even terminating the current JVM process (check various implementations of this function for details). The code that uses Logger.fatal() sometimes expects a particular kind of side effect. This is an undesirable design. And it's definitely not a responsibility of Logger to influence the execution flow of the program.", replaceWith = @ReplaceWith(expression = "error(message)", imports = {}))
    public Void fatal(String message) throws CompilationErrorException {
        message.getClass();
        error(message);
        MessageCollector messageCollector = this.messageCollector;
        GroupingMessageCollector groupingMessageCollector = messageCollector instanceof GroupingMessageCollector ? (GroupingMessageCollector) messageCollector : null;
        if (groupingMessageCollector != null) {
            groupingMessageCollector.flush();
        }
        throw new CompilationErrorException(message);
    }

    public void log(String message) {
        message.getClass();
        this.messageCollector.report(CompilerMessageSeverity.LOGGING, message, null);
    }

    public void strongWarning(String message) {
        message.getClass();
        this.messageCollector.report(orError(CompilerMessageSeverity.STRONG_WARNING), message, null);
    }

    public void warning(String message) {
        message.getClass();
        this.messageCollector.report(orError(CompilerMessageSeverity.WARNING), message, null);
    }
}
