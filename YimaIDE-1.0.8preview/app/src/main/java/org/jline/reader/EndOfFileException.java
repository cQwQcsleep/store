package org.jline.reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class EndOfFileException extends RuntimeException {
    private static final long serialVersionUID = 528485360925144689L;
    private String partialLine;

    public EndOfFileException() {
    }

    public String getPartialLine() {
        return this.partialLine;
    }

    public EndOfFileException partialLine(String str) {
        this.partialLine = str;
        return this;
    }

    public EndOfFileException(String str) {
        super(str);
    }

    public EndOfFileException(String str, Throwable th) {
        super(str, th);
    }

    public EndOfFileException(Throwable th) {
        super(th);
    }

    public EndOfFileException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }
}
