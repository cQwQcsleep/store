package org.jetbrains.kotlin.incremental.util;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"reportException", "", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "e", "", "location", "Lorg/jetbrains/kotlin/incremental/util/ExceptionLocation;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class MessageCollectorUtilKt {
    public static final void reportException(MessageCollector messageCollector, Throwable th, ExceptionLocation exceptionLocation) {
        messageCollector.getClass();
        th.getClass();
        exceptionLocation.getClass();
        MessageCollector.report$default(messageCollector, CompilerMessageSeverity.EXCEPTION, exceptionLocation.getReadableName() + " failed: " + th.getMessage() + '\n' + ExceptionsKt.stackTraceToString(th), (CompilerMessageSourceLocation) null, 4, (Object) null);
    }
}
