package com.intellij.util.pico;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class AmbiguousComponentResolutionException extends PicoIntrospectionException {
    private final Object[] ambiguousComponentKeys;
    private final Class<?> ambiguousDependency;
    private Class<?> component;

    public AmbiguousComponentResolutionException(Class cls, Object[] objArr) {
        super("");
        this.ambiguousDependency = cls;
        this.ambiguousComponentKeys = (Object[]) objArr.clone();
    }

    public Object[] getAmbiguousComponentKeys() {
        return this.ambiguousComponentKeys;
    }

    @Override // com.intellij.util.pico.PicoException, java.lang.Throwable
    public /* bridge */ /* synthetic */ Throwable getCause() {
        return super.getCause();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.component + " has ambiguous dependency on " + this.ambiguousDependency + ", resolves to multiple classes: " + Arrays.asList(getAmbiguousComponentKeys());
    }

    @Override // com.intellij.util.pico.PicoException, java.lang.Throwable
    public /* bridge */ /* synthetic */ void printStackTrace() {
        super.printStackTrace();
    }

    public void setComponent(Class cls) {
        this.component = cls;
    }

    @Override // com.intellij.util.pico.PicoException, java.lang.Throwable
    public /* bridge */ /* synthetic */ void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
    }

    @Override // com.intellij.util.pico.PicoException, java.lang.Throwable
    public /* bridge */ /* synthetic */ void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
    }
}
