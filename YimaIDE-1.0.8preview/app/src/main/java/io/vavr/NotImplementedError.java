package io.vavr;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class NotImplementedError extends Error {
    private static final long serialVersionUID = 1;

    public NotImplementedError() {
        super("An implementation is missing.");
    }

    public NotImplementedError(String str) {
        super(str);
    }
}
