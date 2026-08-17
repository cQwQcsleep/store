package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class InvalidMappingFileException extends RuntimeException {
    public InvalidMappingFileException(Throwable th) {
        super(th);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Unable to parse mapping file";
    }
}
