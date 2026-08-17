package org.jline.reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class SyntaxError extends RuntimeException {
    private static final long serialVersionUID = 1;
    private final int column;
    private final int line;

    public SyntaxError(int i, int i2, String str) {
        super(str);
        this.line = i;
        this.column = i2;
    }
}
