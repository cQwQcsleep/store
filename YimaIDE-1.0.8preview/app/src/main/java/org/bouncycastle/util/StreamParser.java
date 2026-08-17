package org.bouncycastle.util;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface StreamParser {
    Object read() throws StreamParsingException;

    Collection readAll() throws StreamParsingException;
}
