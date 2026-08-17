package com.google.common.io;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public interface LineProcessor<T> {
    T getResult();

    boolean processLine(String str) throws IOException;
}
