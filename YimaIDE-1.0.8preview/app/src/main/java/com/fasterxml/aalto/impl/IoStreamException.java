package com.fasterxml.aalto.impl;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IoStreamException extends StreamExceptionBase {
    public IoStreamException(IOException iOException) {
        super(iOException);
    }

    public IoStreamException(String str) {
        super(str);
    }
}
