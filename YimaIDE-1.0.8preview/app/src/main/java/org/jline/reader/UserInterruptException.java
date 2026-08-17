package org.jline.reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class UserInterruptException extends RuntimeException {
    private static final long serialVersionUID = 6172232572140736750L;
    private final String partialLine;

    public UserInterruptException(String str) {
        this.partialLine = str;
    }

    public String getPartialLine() {
        return this.partialLine;
    }
}
