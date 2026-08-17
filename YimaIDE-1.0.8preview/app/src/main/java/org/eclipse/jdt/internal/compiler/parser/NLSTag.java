package org.eclipse.jdt.internal.compiler.parser;

import org.fusesource.jansi.AnsiRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class NLSTag {
    public int end;
    public int index;
    public int lineNumber;
    public int start;

    public NLSTag(int i, int i2, int i3, int i4) {
        this.start = i;
        this.end = i2;
        this.lineNumber = i3;
        this.index = i4;
    }

    public String toString() {
        return "NLSTag(" + this.start + AnsiRenderer.CODE_LIST_SEPARATOR + this.end + AnsiRenderer.CODE_LIST_SEPARATOR + this.lineNumber + ")";
    }
}
