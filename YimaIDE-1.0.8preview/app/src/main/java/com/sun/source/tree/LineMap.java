package com.sun.source.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LineMap {
    long getColumnNumber(long j);

    long getLineNumber(long j);

    long getPosition(long j, long j2);

    long getStartPosition(long j);
}
