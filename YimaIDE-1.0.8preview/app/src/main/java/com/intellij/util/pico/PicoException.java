package com.intellij.util.pico;

import java.io.PrintStream;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
class PicoException extends RuntimeException {
    private Throwable cause;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/intellij/util/pico/PicoException", "<init>"));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PicoException(String str) {
        super(str);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.cause != null) {
            printStream.println("Caused by:\n");
            this.cause.printStackTrace(printStream);
        }
    }

    public PicoException(Throwable th) {
        this.cause = th;
    }

    public PicoException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.cause != null) {
            printWriter.println("Caused by:\n");
            this.cause.printStackTrace(printWriter);
        }
    }
}
