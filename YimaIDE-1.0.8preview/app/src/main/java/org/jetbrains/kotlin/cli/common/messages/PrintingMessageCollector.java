package org.jetbrains.kotlin.cli.common.messages;

import java.io.PrintStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class PrintingMessageCollector implements MessageCollector {
    private final PrintStream errStream;
    private boolean hasErrors;
    private final MessageRenderer messageRenderer;
    private final boolean verbose;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "messageRenderer";
        } else if (i == 2) {
            objArr[0] = "severity";
        } else if (i != 3) {
            objArr[0] = "errStream";
        } else {
            objArr[0] = "message";
        }
        objArr[1] = "org/jetbrains/kotlin/cli/common/messages/PrintingMessageCollector";
        if (i == 2 || i == 3) {
            objArr[2] = "report";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public PrintingMessageCollector(PrintStream printStream, MessageRenderer messageRenderer, boolean z) {
        if (printStream == null) {
            $$$reportNull$$$0(0);
        }
        if (messageRenderer == null) {
            $$$reportNull$$$0(1);
        }
        this.hasErrors = false;
        this.verbose = z;
        this.errStream = printStream;
        this.messageRenderer = messageRenderer;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
    public void clear() {
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
    public boolean hasErrors() {
        return this.hasErrors;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
    public void report(CompilerMessageSeverity compilerMessageSeverity, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        if (compilerMessageSeverity == null) {
            $$$reportNull$$$0(2);
        }
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        if (this.verbose || !CompilerMessageSeverity.VERBOSE.contains(compilerMessageSeverity)) {
            this.hasErrors |= compilerMessageSeverity.isError();
            this.errStream.println(this.messageRenderer.render(compilerMessageSeverity, str, compilerMessageSourceLocation));
        }
    }
}
