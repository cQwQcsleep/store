package org.bouncycastle.x509.util;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface StreamParser {
    Object read() throws StreamParsingException;

    Collection readAll() throws StreamParsingException;
}
