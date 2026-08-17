package org.jetbrains.kotlin.cli.common.messages;

import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class FilteringMessageCollector implements MessageCollector {
    private final Predicate<CompilerMessageSeverity> decline;
    private final MessageCollector messageCollector;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "decline";
        } else if (i == 2) {
            objArr[0] = "severity";
        } else if (i != 3) {
            objArr[0] = "messageCollector";
        } else {
            objArr[0] = "message";
        }
        objArr[1] = "org/jetbrains/kotlin/cli/common/messages/FilteringMessageCollector";
        if (i == 2 || i == 3) {
            objArr[2] = "report";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public FilteringMessageCollector(MessageCollector messageCollector, Predicate<CompilerMessageSeverity> predicate) {
        if (messageCollector == null) {
            $$$reportNull$$$0(0);
        }
        if (predicate == null) {
            $$$reportNull$$$0(1);
        }
        this.messageCollector = messageCollector;
        this.decline = predicate;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
    public void clear() {
        this.messageCollector.clear();
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
    public boolean hasErrors() {
        return this.messageCollector.hasErrors();
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
    public void report(CompilerMessageSeverity compilerMessageSeverity, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        if (compilerMessageSeverity == null) {
            $$$reportNull$$$0(2);
        }
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        if (this.decline.test(compilerMessageSeverity)) {
            return;
        }
        this.messageCollector.report(compilerMessageSeverity, str, compilerMessageSourceLocation);
    }
}
