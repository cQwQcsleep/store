package org.jetbrains.kotlin.util;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0017J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/util/DummyLogger;", "Lorg/jetbrains/kotlin/util/Logger;", "()V", "error", "", "message", "", "fatal", "", "log", "warning", "kotlin-util-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DummyLogger implements Logger {
    public static final DummyLogger INSTANCE = new DummyLogger();

    private DummyLogger() {
    }

    @Override // org.jetbrains.kotlin.util.Logger
    public void error(String message) {
        message.getClass();
        System.out.println((Object) ("e: " + message));
    }

    @Override // org.jetbrains.kotlin.util.Logger
    @Deprecated(message = "Invocation of fatal() may cause severe side effects such as throwing an exception or even terminating the current JVM process (check various implementations of this function for details). The code that uses Logger.fatal() sometimes expects a particular kind of side effect. This is an undesirable design. And it's definitely not a responsibility of Logger to influence the execution flow of the program.", replaceWith = @ReplaceWith(expression = "error(message)", imports = {}))
    public Void fatal(String message) {
        message.getClass();
        error(message);
        System.exit(1);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    @Override // org.jetbrains.kotlin.util.Logger
    public void log(String message) {
        message.getClass();
        System.out.println((Object) message);
    }

    @Override // org.jetbrains.kotlin.util.Logger
    public void warning(String message) {
        message.getClass();
        System.out.println((Object) ("w: " + message));
    }
}
