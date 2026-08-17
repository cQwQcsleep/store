package org.jetbrains.kotlin.cli.common.messages;

import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class MessageCollectorUtil {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "messageCollector";
        } else {
            objArr[0] = K2JsArgumentConstants.RUNTIME_DIAGNOSTIC_EXCEPTION;
        }
        objArr[1] = "org/jetbrains/kotlin/cli/common/messages/MessageCollectorUtil";
        objArr[2] = "reportException";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static void reportException(MessageCollector messageCollector, Throwable th) {
        if (messageCollector == null) {
            $$$reportNull$$$0(0);
        }
        if (th == null) {
            $$$reportNull$$$0(1);
        }
        messageCollector.report(CompilerMessageSeverity.EXCEPTION, OutputMessageUtil.renderException(th), null);
    }
}
