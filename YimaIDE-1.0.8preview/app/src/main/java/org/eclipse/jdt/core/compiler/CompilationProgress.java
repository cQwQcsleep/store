package org.eclipse.jdt.core.compiler;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class CompilationProgress {
    public abstract void begin(int i);

    public abstract void done();

    public abstract boolean isCanceled();

    public abstract void setTaskName(String str);

    public abstract void worked(int i, int i2);
}
